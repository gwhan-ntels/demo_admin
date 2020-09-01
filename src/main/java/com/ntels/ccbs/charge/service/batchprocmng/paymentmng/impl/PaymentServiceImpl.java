package com.ntels.ccbs.charge.service.batchprocmng.paymentmng.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ntels.ccbs.charge.domain.batchprocmng.ambgtransmng.AmbgOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.assrtransmng.AssrOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.BillListVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.DepositVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.ReceiptDtlVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.ReceiptVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.PrepayOccVO;
import com.ntels.ccbs.charge.mapper.batchprocmng.paymentmng.PaymentMapper;
import com.ntels.ccbs.charge.service.batchprocmng.billupdmng.BillMastService;
import com.ntels.ccbs.charge.service.batchprocmng.paymentmng.PaymentService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.CUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class PaymentServiceImpl implements PaymentService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SequenceService sequenceService;

	@Autowired
	private PaymentMapper paymentMapper;

	@Autowired
	private BillMastService billMastService;

	/**
	 * 입금/수납을 처리한다.
	 * 
	 * @param procScopeFlag(A-입금/수납
	 *            처리,R-수납만 처리), pymAcntId, dpstSeqNo, depositInfo, inptMenuId,
	 *            workId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int processReceipt(String procScopeFlag, String pymAcntId, String dpstSeqNo, DepositVO depositInfo, String inptMenuId, String workId, String rdoDpstGubn) throws Exception {
		int resultFlag = 1;

		// 작업변수 정의
		String prepayTgtYn = "N";
		String prepayOccSeqNo = "";
		String ambgTgtYn = "N";
		String ambgOccSeqNo = "";
		String assrTgtYn = "N";
		String assrOccSeqNo = "";
		String payProcDt = "";

		// 입금내역 등록
		if ("A".equals(procScopeFlag)) {
			// 1. insert tblpy_dpst
			int insDpst = paymentMapper.insertDeposit(depositInfo);

			if (insDpst <= 0) {
				resultFlag = -1;
				throw new Exception("FAIL INSERT TBLPY_DPST");
			}
		}

		// 납부계정을 CHECK 한다.
		int acntCnt = paymentMapper.getPymAcntCnt(pymAcntId);

		// 입금내역 정보를 조회한다.
		DepositVO depositVO = new DepositVO();
		depositVO = paymentMapper.getDeposit(dpstSeqNo);

		String bddbtTgtYn = "N";

		int bddbtTgtCnt = depositVO.getBddbtTgtCnt();

		if (bddbtTgtCnt > 0) {
			bddbtTgtYn = "Y";
		}

		// 존재하지 않는 납부 계정이거나 입금구분이 센터 총액 입금(07) 일때 불명금 처리를 한다.
		if (acntCnt == 0 || "07".equals(depositVO.getDpstCl())) {
			ambgOccSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.BL_AMBG_NO, 10);

			boolean ambgInsRes = insertAmbgOccFromDeposit(ambgOccSeqNo, depositVO, inptMenuId, workId);

			if (ambgInsRes == false) {
				resultFlag = -1;
				throw new Exception("FAIL INSERT TBLPY_AMBG_OCC FROM DEPOSIT");
			} else {
				ambgTgtYn = "Y";
			}
		} else {
			// 수납처리를 위한 입금액 가져오기
			double dpstAmt = depositVO.getDpstAmt();
			String sbillSeqNo = depositVO.getBillSeqNo();
			String sctrtId = depositVO.getCtrtId();

			logger.debug("====================================");
			logger.debug("  Start Receipt Proccess !!! ");
			logger.debug("====================================");

			List<BillListVO> billList = new ArrayList<BillListVO>();

			// [추후확인필요] 수납 적용 순서를 위한 변경사항을 반영한다.
			billList = paymentMapper.getBillDataList(pymAcntId, sbillSeqNo, sctrtId);
			int loopCnt = billList.size();

			String pymSeqNo = "";
			payProcDt = DateUtil.getDateStringYYYYMMDD(0);

			// 청구내역 건수 만큼 수납처리를 수행한다.
			for (int i = 0; i < loopCnt; i++) {
				BillListVO billInfo = new BillListVO();

				billInfo = billList.get(i);
				String newRcptFlag = billInfo.getRcptVal();

				String billYymm = "";
				String billCycl = "";
				String billDt = "";
				String billSeqNo = "";

				// BILL_SEQ_NO별로 수납내역에 넣기위해 billYymm, billCycl, billDt,
				// chkBillSeqNo, pymSeqNo 값 저장-BILL_SEQ_NO 변경시마다
				// if (i == 0) {
				if ("1".equals(newRcptFlag)) {
					billYymm = billInfo.getBillYymm();
					billCycl = billInfo.getBillCycl();
					billDt = billInfo.getBillDt();
					billSeqNo = billInfo.getBillSeqNo();
					pymSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.PY_RCPT_NO, 10);
				}

				double rcptAmt = 0;
				double billAmt = billInfo.getBillAmt();

				// 청구금액 입금금액 비교해서 수납금액 계산
				double calAmt = dpstAmt - billAmt;

				if (dpstAmt < billAmt) {
					rcptAmt = dpstAmt;
				} else {
					rcptAmt = billAmt;
				}
				// 청구내역에 수납금액 반영
				billInfo.setRcptAmt(rcptAmt);

				// 요금항목이 보증금, 영상보증금, 보석상 이면 보증금 발생내역을 생성한다. (2017년 Project NSOK 에서 사용하던 로직) 
//				String chrgItmCd = billInfo.getChrgItmCd();
//				if ("SR029".equals(chrgItmCd) || "SR030".equals(chrgItmCd) || "SR031".equals(chrgItmCd)) {
//					assrOccSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.BL_ASSR_NO, 10);
//					boolean assrInsRes = insertAssrOccFromDeposit(assrOccSeqNo, depositVO, billInfo, pymSeqNo, rcptAmt, inptMenuId, workId);
//					if (assrInsRes == false) {
//						resultFlag = -1;
//						throw new Exception("FAIL INSERT TBLPY_ASSR_OCC FROM DEPOSIT");
//					} else {
//						assrTgtYn = "Y";
//					}
//				}

				// 2. insert tblpy_rcpt_dtl 입금상세 내역 생성 
				boolean rcptDtlInsRes = insertReceiptDtlFromBillData(pymSeqNo, billInfo, inptMenuId, workId);
				if (rcptDtlInsRes == false) {
					resultFlag = -1;
					throw new Exception("FAIL INSERT TBLPY_RCPT_DTL FROM BILL DATA");
				}

				// 최종수납일 설정
				billInfo.setLastRcptDt(payProcDt);

				// 3. update tbliv_bill 
				boolean billUpdRes = updateBillReceiptAmt(billInfo, workId);
				if (billUpdRes == false) {
					resultFlag = -1;
					throw new Exception("FAIL UPDATE TBLIV_BILL-RCPT_AMT");
				}

				// 4. update tbliv_bill_mast  청구 TBLIV_BILL_MAST 수납금액, 최종수납일 반영
				billMastService.billMastUpdate(billInfo.getSoId(), billInfo.getBillYymm(), billInfo.getBillSeqNo(), billInfo.getPymAcntId(), workId);

				// 5. insert tblpy_rcpt  수납 내역 생성
				boolean rcptInsRes = false;
				if (rdoDpstGubn.equals("S")) {
					rcptInsRes = insertReceipt(pymSeqNo, dpstSeqNo, billSeqNo, billYymm, billCycl, billDt, pymAcntId, payProcDt, "01", bddbtTgtYn, inptMenuId, workId, billInfo.getSoId());
				} else {
					// 전체미납금액 입금 처리 시 billSeqNo, billYymm 를 채우지 않기 위함.
					billSeqNo = "";
					billYymm = "";
					billCycl = "";
					rcptInsRes = insertReceipt(pymSeqNo, dpstSeqNo, billSeqNo, billYymm, billCycl, billDt, pymAcntId, payProcDt, "01", bddbtTgtYn, inptMenuId, workId, billInfo.getSoId());
				}

				if (rcptInsRes == false) {
					resultFlag = -1;
					throw new Exception("FAIL INSERT RCPT");
				}

				// 수납 반영 후 입금액 변경
				dpstAmt = calAmt;

				if (calAmt <= 0)
					break;
			}

			// 수납 반영후 입금잔액이 남은 경우 선수금 발생내역 생성
			if (dpstAmt > 0) {
				prepayOccSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.PY_PRPY_NO, 10);

				// 6. insert tblpy_prepay_occ
				boolean prepayInsRes = insertPrepayOccFromDeposit(prepayOccSeqNo, depositVO, pymSeqNo, dpstAmt, inptMenuId, workId);

				if (prepayInsRes == false) {
					resultFlag = -1;
					throw new Exception("FAIL INSERT TBLPY_PREPAY_OCC FROM DEPOSIT");
				} else {
					prepayTgtYn = "Y";
				}

				// 7. update tblpy_rcpt (prepay_occ_seq_no) -- 수납정보 변경-선수발생 일련번호 UPDATE
				boolean occnoUpdRes = updateReceiptPrepayOccNo(pymSeqNo, prepayOccSeqNo, workId);
				if (occnoUpdRes == false) {
					resultFlag = -1;
					throw new Exception("FAIL UPDATE PREPAY OCC SEQ NO");
				}
			}

		}

		// 8. update tblpy_dpst
		boolean dpstUpdres = updateDepositReceiptRslt(dpstSeqNo, payProcDt, prepayTgtYn, prepayOccSeqNo, ambgTgtYn, ambgOccSeqNo, assrTgtYn, assrOccSeqNo, workId);

		if (dpstUpdres == false) {
			resultFlag = -1;
			throw new Exception("FAIL UPDATE TBLPY_DPST-RECEIPT RESULT");
		}

		logger.debug("====================================");
		logger.debug("  End Receipt Proccess !!! ");
		logger.debug("====================================");

		return resultFlag;
	}

	/**
	 * 불명금 내역 생성
	 * 
	 * @param ambgOccSeqNo,
	 *            depositVO, inptMenuId, workId
	 * @return
	 */
	private boolean insertAmbgOccFromDeposit(String ambgOccSeqNo, DepositVO depositVO, String inptMenuId, String workId) {
		boolean result = true;

		logger.debug("===========================================");
		logger.debug("  insertAmbgOccFromDeposit Process...  ");
		logger.debug("===========================================");

		AmbgOccVO ambgOccVO = new AmbgOccVO();

		// 입금 정보 데이터 불명금 발생 데이터로 복사
		CUtil.copyObjectValue(depositVO, ambgOccVO);

		ambgOccVO.setAmbgOccSeqNo(ambgOccSeqNo);
		ambgOccVO.setAmbgOccDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		ambgOccVO.setAmbgOccTp("01"); // 불명 발생 타입 : 수납처리
		// 불명 발생 사유 값 할당 - 입금구분이 센터 총액 입금(07) 일 경우 센터 선입금 처리용(01), 납부계정이 없을때는
		// 납입자번호 확인불가능(02)
		if ("07".equals(depositVO.getDpstCl())) {
			ambgOccVO.setAmbgOccResn("01"); // 불명 발생 사유 : 센터 선입금 처리용
		} else {
			ambgOccVO.setAmbgOccResn("02"); // 불명 발생 사유 : 납입자번호 확인불가능
		}
		ambgOccVO.setDpstTpSeqNo(depositVO.getDpstSeqNo());
		ambgOccVO.setAmbgProcStat("01"); // 불명 처리 상태 : 불명금 발생
		ambgOccVO.setAmbgOccAmt(depositVO.getDpstAmt());
		ambgOccVO.setAmbgTransAmt(0.0);
		ambgOccVO.setAmbgBal(depositVO.getDpstAmt());
		ambgOccVO.setTransCmplYn("N");
		ambgOccVO.setCnclYn("N");
		ambgOccVO.setCnclDttm("");
		ambgOccVO.setTransDt("");
		ambgOccVO.setMngCnterOrgId(depositVO.getDpstProcCnterCd());
		ambgOccVO.setMngBrOrgId("");
		ambgOccVO.setErpSndYn("N");
		ambgOccVO.setInptMenuId(inptMenuId);
		ambgOccVO.setRegrId(workId);
		ambgOccVO.setRegDate(DateUtil.sysdate());

		int insCnt = paymentMapper.insertAmbgOcc(ambgOccVO);

		if (insCnt <= 0) {
			logger.debug("FAIL INSERT TBLPY_AMBG_OCC");
			logger.debug("불명금 발생내역 생성 실패");
			result = false;
		}

		return result;
	}

	/**
	 * 보증금 내역 생성
	 * 
	 * @param assrOccSeqNo,
	 *            nBlpy00DpstVO, billInfo, pymSeqNo, mngCnterOrgId, mngBrOrgId,
	 *            inptMenuId, workId
	 * @return
	 */
	private boolean insertAssrOccFromDeposit(String assrOccSeqNo, DepositVO depositVO, BillListVO billInfo, String pymSeqNo, double rcptAmt, String inptMenuId, String workId) {
		boolean result = true;

		logger.debug("===========================================");
		logger.debug("  insertAssrOccFromDeposit Process...  ");
		logger.debug("===========================================");

		AssrOccVO assrOccVO = new AssrOccVO();

		// 청구 정보 데이터 보증금 발생 데이터로 복사
		CUtil.copyObjectValue(billInfo, assrOccVO);

		assrOccVO.setAssrOccSeqNo(assrOccSeqNo);
		assrOccVO.setPymSeqNo(pymSeqNo);
		assrOccVO.setAssrOccDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		assrOccVO.setDpstTpSeqNo(depositVO.getDpstSeqNo());
		assrOccVO.setDpstDt(depositVO.getDpstDt());
		assrOccVO.setDpstCl(depositVO.getDpstCl());
		assrOccVO.setAssrProcStat("01"); // 보증 처리 상태 : 보증금발생
		assrOccVO.setAssrOccAmt(rcptAmt);
		assrOccVO.setAssrTransAmt(0);
		assrOccVO.setAssrBal(rcptAmt);
		assrOccVO.setTransCmplYn("N");
		assrOccVO.setCnclYn("N");
		assrOccVO.setCnclDttm("");
		assrOccVO.setTransDt("");
		assrOccVO.setMngCnterOrgId(depositVO.getDpstProcCnterCd());
		assrOccVO.setMngBrOrgId("");
		assrOccVO.setErpSndYn("N");
		assrOccVO.setInptMenuId(inptMenuId);
		assrOccVO.setRegrId(workId);
		assrOccVO.setRegDate(DateUtil.sysdate());

		int insCnt = paymentMapper.insertAssrOcc(assrOccVO);

		if (insCnt <= 0) {
			logger.debug("FAIL INSERT TBLPY_ASSR_OCC");
			logger.debug("보증금 발생내역 생성 실패");
			result = false;
		}

		return result;
	}

	/**
	 * 수납 상세 내역 생성
	 * 
	 * @param pymSeqNo,
	 *            billInfo, inptMenuId, workId
	 * @return
	 */
	private boolean insertReceiptDtlFromBillData(String pymSeqNo, BillListVO billInfo, String inptMenuId, String workId) {
		boolean result = true;

		logger.debug("============================================");
		logger.debug("  insertReceiptDtlFromBillData Process...  ");
		logger.debug("============================================");

		ReceiptDtlVO receiptDtlVO = new ReceiptDtlVO();

		// 청구 정보 데이터 입금 상세 데이터로 복사
		CUtil.copyObjectValue(billInfo, receiptDtlVO);

		receiptDtlVO.setPymSeqNo(pymSeqNo);
		receiptDtlVO.setPrePymAmt(0.0); // [추후확인필요]컬럼 용도 확인 필요
		receiptDtlVO.setPreSoId(billInfo.getSoId()); // [추후확인필요]컬럼 용도 확인 필요
		receiptDtlVO.setInptMenuId(inptMenuId);
		receiptDtlVO.setRegrId(workId);
		receiptDtlVO.setRegDate(DateUtil.sysdate());

		int insCnt = paymentMapper.insertReceiptDtl(receiptDtlVO);

		if (insCnt <= 0) {
			logger.debug("FAIL INSERT TBLPY_RCPT_DTL");
			logger.debug("수납 상세 내역 생성 실패");
			result = false;
		}

		return result;
	}

	/**
	 * 청구내역에 수납금액 반영
	 * 
	 * @param billListVO,
	 *            workId
	 * @return
	 */
	private boolean updateBillReceiptAmt(BillListVO billListVO, String workId) {
		boolean result = true;

		logger.debug("===========================================");
		logger.debug("  updateBillReceiptAmt Process...  ");
		logger.debug("===========================================");

		billListVO.setChgrId(workId);
		billListVO.setChgDate(DateUtil.sysdate());

		int updCnt = paymentMapper.updateBillData(billListVO);

		if (updCnt <= 0) {
			logger.debug("FAIL INSERT TBLIV_BILL");
			logger.debug("청구내역 수납금액 반영 실패");
			result = false;
		}

		return result;
	}

	/**
	 * 수납 내역 생성
	 * 
	 * @param pymSeqNo,
	 *            dpstSeqNo, billSeqNo, billYymm, billCycl, billDt, pymAcntId,
	 *            payProcDt, payTp, bddbtTgtYn, inptMenuId, workId
	 * @return
	 */
	private boolean insertReceipt(String pymSeqNo, String dpstSeqNo, String billSeqNo, String billYymm, String billCycl, String billDt, String pymAcntId,
			String payProcDt, String payTp, String bddbtTgtYn, String inptMenuId, String workId, String soId) {
		boolean result = true;

		logger.debug("===========================================");
		logger.debug("  insertReceipt Process...  ");
		logger.debug("===========================================");

		ReceiptVO receiptVO = new ReceiptVO();
		receiptVO.setPymSeqNo(pymSeqNo);
		receiptVO.setDpstSeqNo(dpstSeqNo);
		receiptVO.setBillSeqNo(billSeqNo);
		receiptVO.setBillYymm(billYymm);
		receiptVO.setBillCycl(billCycl);
		receiptVO.setBillDt(billDt);
		receiptVO.setPymAcntId(pymAcntId);
		receiptVO.setPayProcDt(payProcDt);
		receiptVO.setPayTp(payTp);
		receiptVO.setPrepdCtrtId("");
		receiptVO.setCnclYn("N");
		receiptVO.setRcptOpetrId(workId);
		receiptVO.setBddbtRcptYn(bddbtTgtYn);
		receiptVO.setErpSndYn("N");
		receiptVO.setInptMenuId(inptMenuId);
		receiptVO.setRegrId(workId);
		receiptVO.setRegDate(DateUtil.sysdate());
		receiptVO.setSoId(soId);

		int insCnt = paymentMapper.insertReceipt(receiptVO);

		if (insCnt <= 0) {
			logger.debug("FAIL INSERT TBLPY_RCPT");
			logger.debug("수납 내역 생성 실패");
			result = false;
		}

		return result;
	}

	/**
	 * 선수금 내역 생성
	 * 
	 * @param prepayOccSeqNo,
	 *            depositVO, pymSeqNo, remainAmt, inptMenuId, workId
	 * @return
	 */
	private boolean insertPrepayOccFromDeposit(String prepayOccSeqNo, DepositVO depositVO, String pymSeqNo, double remainAmt, String inptMenuId,
			String workId) {
		boolean result = true;

		logger.debug("=============================================");
		logger.debug("  insertPrepayOccFromDeposit Process...  ");
		logger.debug("=============================================");

		PrepayOccVO prepayOccVO = new PrepayOccVO();

		// 입금 정보 데이터 불명금 발생 데이터로 복사
		CUtil.copyObjectValue(depositVO, prepayOccVO);

		prepayOccVO.setPrepayOccSeqNo(prepayOccSeqNo);
		prepayOccVO.setPrepayOccDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		prepayOccVO.setPrepayOccTp("01"); // 선수 발생 타입 : 수납처리
		prepayOccVO.setPrepayOccResn("01"); // 선수 발생 사유 : 과오납
		prepayOccVO.setPrepayOccTgtSeqNo(depositVO.getDpstSeqNo()); // 입금번호 : 전체가 선수금으로 가는 경우도 있으므로 입금번호로 변경
		prepayOccVO.setPrepayProcStat("01"); // 선수 처리 상태 : 선수금발생
		prepayOccVO.setPrepayOccAmt(remainAmt);
		prepayOccVO.setPrepayTransAmt(0.0);
		prepayOccVO.setPrepayBal(remainAmt);
		prepayOccVO.setTransCmplYn("N");
		prepayOccVO.setCnclYn("N");
		prepayOccVO.setCnclDttm("");
		prepayOccVO.setCnclResn("");
		prepayOccVO.setTransDt("");
		prepayOccVO.setMngCnterOrgId(depositVO.getDpstProcCnterCd());
		prepayOccVO.setMngBrOrgId("");
		prepayOccVO.setErpSndYn("N");
		prepayOccVO.setInptMenuId(inptMenuId);
		prepayOccVO.setRegrId(workId);
		prepayOccVO.setRegDate(DateUtil.sysdate());

		int insCnt = paymentMapper.insertPrepayOcc(prepayOccVO);

		if (insCnt <= 0) {
			logger.debug("FAIL INSERT TBLPY_PREPAY_OCC");
			logger.debug("선수금 발생내역 생성 실패");
			result = false;
		}

		return result;
	}

	/**
	 * 수납내역에 선수금 발생번호 반영
	 * 
	 * @param pymSeqNo,
	 *            prepayOccSeqNo, workId
	 * @return
	 */
	private boolean updateReceiptPrepayOccNo(String pymSeqNo, String prepayOccSeqNo, String workId) {
		boolean result = true;

		logger.debug("===========================================");
		logger.debug("  updateReceiptPrepayOccNo Process...  ");
		logger.debug("===========================================");

		ReceiptVO receiptVO = new ReceiptVO();

		receiptVO.setPymSeqNo(pymSeqNo);
		receiptVO.setPrepayOccSeqNo(prepayOccSeqNo);
		receiptVO.setRegrId(workId);
		receiptVO.setRegDate(DateUtil.sysdate());

		int updCnt = paymentMapper.updateReceipt(receiptVO);

		// 입금금액이 전액 선수금 발생하는 경우도 있으므로 <= 0 가 나인 < 0
		if (updCnt < 0) {
			logger.debug("FAIL UPDATE TBLIV_RCPT-PREPAY_OCC_SEQ_NO");
			logger.debug("수납내역 선수금 발생번호 반영 실패");
			result = false;
		}

		return result;
	}

	/**
	 * 입금내역 수납 처리 결과 반영
	 * 
	 * @param dpstSeqNo,
	 *            prepayTgtYn, prepayOccSeqNo, ambgTgtYn, ambgOccSeqNo,
	 *            assrTgtYn, assrOccSeqNo, workId
	 * @return
	 */
	private boolean updateDepositReceiptRslt(String dpstSeqNo, String payProcDt, String prepayTgtYn, String prepayOccSeqNo, String ambgTgtYn,
			String ambgOccSeqNo, String assrTgtYn, String assrOccSeqNo, String workId) {
		boolean result = true;

		logger.debug("===========================================");
		logger.debug("  updateDepositReceiptRslt Process...  ");
		logger.debug("===========================================");

		DepositVO depositInfo = new DepositVO();

		// 입금정보 변경
		depositInfo.setDpstSeqNo(dpstSeqNo);
		if (!"".equals(payProcDt)) {
			depositInfo.setPayProcYn("Y");
		} else {
			depositInfo.setPayProcYn("N");
		}
		depositInfo.setPayProcDt(payProcDt);
		depositInfo.setPrepayTgtYn(prepayTgtYn);
		depositInfo.setPrepayOccSeqNo(prepayOccSeqNo);
		depositInfo.setAmbgTgtYn(ambgTgtYn);
		depositInfo.setAmbgOccSeqNo(ambgOccSeqNo);
		depositInfo.setAssrTgtYn(assrTgtYn);
		depositInfo.setAssrOccSeqNo(assrOccSeqNo);
		depositInfo.setRegrId(workId);
		depositInfo.setRegDate(DateUtil.sysdate());

		int updCnt = paymentMapper.updateDeposit(depositInfo);

		if (updCnt <= 0) {
			logger.debug("FAIL UPDATE TBLIV_DPST-RCPT RESULT");
			logger.debug("입금내역 수납 처리 결과 반영 실패");
			result = false;
		}

		return result;
	}

}
