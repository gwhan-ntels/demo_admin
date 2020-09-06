package com.ntels.ccbs.charge.service.payment.payment.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.ntels.ccbs.charge.domain.common.Bill;
import com.ntels.ccbs.charge.domain.common.CBillComm;
import com.ntels.ccbs.charge.domain.common.Deposit;
import com.ntels.ccbs.charge.domain.common.EachDeposit;
import com.ntels.ccbs.charge.domain.common.ExrateInfo;
import com.ntels.ccbs.charge.domain.common.PaymentResult;
import com.ntels.ccbs.charge.domain.common.PrepayOcc;
import com.ntels.ccbs.charge.domain.common.Receipt;
import com.ntels.ccbs.charge.domain.payment.payment.NewSinglePaymentVO;
import com.ntels.ccbs.charge.mapper.payment.payment.NewSinglePaymentMapper;
import com.ntels.ccbs.charge.service.common.AmbgService;
import com.ntels.ccbs.charge.service.common.DepositService;
import com.ntels.ccbs.charge.service.common.PaymentService_1;
import com.ntels.ccbs.charge.service.common.PaymentService_1.ProcessPaymentCallback;
import com.ntels.ccbs.charge.service.common.PrepayService;
import com.ntels.ccbs.charge.service.common.PyCommService;
import com.ntels.ccbs.charge.service.common.ReceiptService;
import com.ntels.ccbs.charge.service.payment.payment.NewSinglePaymentService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class NewSinglePaymentServiceImpl implements NewSinglePaymentService {
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private NewSinglePaymentMapper newSinglePaymentMapper;

	@Autowired
	private SequenceService sequenceService;
	
	@Autowired
	private DepositService depositService;
	
	@Autowired
	private AmbgService ambgService;	

	@Autowired
	private PyCommService pyCommService;
	
	@Autowired
	private PaymentService_1 paymentService_1;
	
	@Autowired
	private ReceiptService receiptService;

	@Autowired
	private PrepayService prepayService;

	@Override
	public List<NewSinglePaymentVO> getAnonyReceiptSubList(NewSinglePaymentVO newSinglePaymentVO) {
		return newSinglePaymentMapper.getAnonyReceiptSubList(newSinglePaymentVO);
	}

	@Override
	public List<NewSinglePaymentVO> getMyReceiptList(NewSinglePaymentVO newSinglePaymentVO) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		newSinglePaymentVO.setProcDt(today);
		return newSinglePaymentMapper.getMyReceiptList(newSinglePaymentVO);
	}

	@Override
	public int getAnonyReceiptSubListCount(NewSinglePaymentVO newSinglePaymentVO) {
		return newSinglePaymentMapper.getAnonyReceiptSubListCount(newSinglePaymentVO);
	}

	@Override
	public int getMyReceiptListCount(NewSinglePaymentVO newSinglePaymentVO) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		newSinglePaymentVO.setProcDt(today);
		return newSinglePaymentMapper.getMyReceiptListCount(newSinglePaymentVO);
	}

	@Override
	public List<NewSinglePaymentVO> getBillInfo(NewSinglePaymentVO newSinglePaymentVO) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		newSinglePaymentVO.setProcDt(today);
		return newSinglePaymentMapper.getBillInfo(newSinglePaymentVO);
	}

	@Override
	public int getBillInfoCount(NewSinglePaymentVO newSinglePaymentVO) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		newSinglePaymentVO.setProcDt(today);
		return newSinglePaymentMapper.getBillInfoCount(newSinglePaymentVO);
	}

	@Override
	public List<NewSinglePaymentVO> getPermitOrg(NewSinglePaymentVO newSinglePaymentVO) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		newSinglePaymentVO.setProcDt(today);
		return newSinglePaymentMapper.getPermitOrg(newSinglePaymentVO);
	}

	@Override
	public int getPermitOrgCount(NewSinglePaymentVO newSinglePaymentVO) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		newSinglePaymentVO.setProcDt(today);
		return newSinglePaymentMapper.getPermitOrgCount(newSinglePaymentVO);
	}

	@Override
	public List<NewSinglePaymentVO> getLoanAvlAmt(NewSinglePaymentVO newSinglePaymentVO) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		newSinglePaymentVO.setProcDt(today);
		return newSinglePaymentMapper.getLoanAvlAmt(newSinglePaymentVO);
	}

	@Override
	public int getLoanAvlAmtCount(NewSinglePaymentVO newSinglePaymentVO) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		newSinglePaymentVO.setProcDt(today);
		return newSinglePaymentMapper.getLoanAvlAmtCount(newSinglePaymentVO);
	}

	@Override
	public List<Map<String, Object>> listAnonyReceiptSubExcel(NewSinglePaymentVO newSinglePaymentVO) {
		return newSinglePaymentMapper.listAnonyReceiptSubExcel(newSinglePaymentVO);
	}

	@Override
	public List<Map<String, Object>> listMyReceiptExcel(String soId, String usrId, String lngTyp) {
		String procDt = DateUtil.getDateStringYYYYMMDD(0);
		return newSinglePaymentMapper.listMyReceiptExcel(soId, usrId, lngTyp, procDt);
	}

	@Override
	public int insertAction(EachDeposit eachDeposit) {
		int returnFlag = 1;
		int length = 0; 
		
		String[] billSeqArray = null;
		String[] billYymmArray = null; 

		List<Bill> billSeqNoInfoList = null;
		Deposit updateDeposit = new Deposit();

		SessionUser session = CommonUtil.getSessionManager();
		String userId = session.getUserId();

		// billSeqNo 을 eachDeposit에 set 하기 위함.
		if (StringUtils.isNullOrEmpty(eachDeposit.getBillSeqNo()) == true) {
			// 전체미납입금 시 billSeqNo == null 이므로 미납대상 billSeqNo 를 구한다.
			billSeqNoInfoList = paymentService_1.getBillSeqNo(eachDeposit.getSoId(), eachDeposit.getPymAcntId());
			length = billSeqNoInfoList.size();

			billSeqArray = new String[length];
			billYymmArray = new String[length];

			for (int i = 0; i < length; i++) {
				billSeqArray[i] = billSeqNoInfoList.get(i).getBillSeqNo();
				billYymmArray[i] = billSeqNoInfoList.get(i).getBillYymm();
			}
			eachDeposit.setBillSeqArray(billSeqArray);
			eachDeposit.setBillYymmArray(billYymmArray);
		} else {
			billSeqArray = new String[1];
			billYymmArray = new String[1];

			billSeqArray[0] = eachDeposit.getBillSeqNo();
			billYymmArray[0] = eachDeposit.getBillYymm();

			eachDeposit.setBillSeqArray(billSeqArray);
			eachDeposit.setBillYymmArray(billYymmArray);
		}

		int loopCnt = eachDeposit.getBillSeqArray().length;

		ExrateInfo exrateInfo = pyCommService.getExrateInfo();

		eachDeposit.setExrate(exrateInfo.getExrate()); // 환율
		eachDeposit.setExrateAplyDt(exrateInfo.getExrateAplyDt()); // 환율적용일자

		logger.debug("exrateAplyDt : " + eachDeposit.getExrateAplyDt());

		// 카드아닌 경우 each_dpst_seq_no 를 생성
		if (eachDeposit.getDpstCl().equals("05") == false) {
			// 신용카드결제인경우 건별 입금 일련번호는 화면에서 넘겨온 번호로 넣는다.
			// VADS에서는 현금 결제만 있다고 함. 2017.05.12
			// TODO KB에서 신용카드인경우 DPST_SEQ_NO 어떻게 할것인지 확인 필요 한광욱 2020.08.31
			eachDeposit.setEachDpstSeq(sequenceService.createNewSequence(Consts.SEQ_CODE.MOD_ID_EACH_DPST, 10));
		}

		String dpstSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.MOD_ID_DPST, 10);

		eachDeposit.setDpstSeqNo(dpstSeqNo);
		eachDeposit.setBillSeqNo(eachDeposit.getBillSeqArray()[0]);
		eachDeposit.setRegrId(userId);
		eachDeposit.setRegDate(new Timestamp(new Date().getTime()));
		eachDeposit.setChgrId(userId);
		eachDeposit.setChgDate(new Timestamp(new Date().getTime()));

		updateDeposit.setDpstSeqNo(dpstSeqNo);

		// 1. tblpy_each_dpst insert
		int insertCnt = depositService.insertEachDeposit(eachDeposit);

		if (insertCnt <= 0) {
			logger.debug("FAIL INSERT TBLPY_EACH_DPST");
			logger.debug("건별입금내역 등록 실패");
			throw new RuntimeException("FAIL INSERT TBLPY_EACH_DPST");
		}

		// 2. tblpy_dpst insert
		insertCnt = depositService.insertDepositFromEachDeposit(eachDeposit);

		if (insertCnt <= 0) {
			logger.debug("FAIL INSERT TBLPY_DPST");
			logger.debug("입금내역 등록 실패");
			throw new RuntimeException("FAIL INSERT TBLPY_DPST");
		}

		// 3. tblpy_each_dpst dpst_proc_dt update (건별입금내역 입금처리일시를 수정)
		insertCnt = depositService.updateEachDeposit(eachDeposit);

		if (insertCnt <= 0) {
			logger.debug("FAIL UPDATE DPST PROC DT");
			logger.debug("입금내역처리일시 수정 실패");
			throw new RuntimeException("FAIL UPDATE DPST PROC DT");
		}

		double dpstAmt = depositService.getDpstAmt(dpstSeqNo);

		Receipt lastReceipt = null;

		for (int i = 0; i < loopCnt; i++) {
			// 수납상세내역 시퀀스는 한번만 발급하여 동일한 값을 사용한다.
			String pymSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.MOD_ID_PYM, 10);

			String billSeqNo = eachDeposit.getBillSeqArray()[i];

			// 납부계정을 CHECK 한다.
			int pymAcntCnt = pyCommService.getPymAcntCnt(eachDeposit.getPymAcntId());

			// 납부계정ID가 존재하지 앟으면 수납처리가 불가능하다. 불명금발생 등록!
			if (StringUtils.isNullOrEmpty(eachDeposit.getPymAcntId()) == true || pymAcntCnt == 0) {
				String ambgOccSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.MOD_ID_AMBG_OCC, 10);

				// 4-1. TBLPY_AMBG_OCC insert (불명금 등록)
				ambgService.insertAmbgOcc(ambgOccSeqNo, eachDeposit.getRegrId(), dpstSeqNo);

				updateDeposit.setAmbgTgtYn("Y");
				updateDeposit.setAmbgOccSeqNo(ambgOccSeqNo);
				updateDeposit.setChgrId(userId);
				updateDeposit.setChgDate(new Timestamp(new Date().getTime()));

				// 4-2. TBLPY_DPST ambg_tgt_yn = Y, ambg_occ_seq_no update
				paymentService_1.updateDpstProc(updateDeposit);

				// 불명금으로 전환되었기 때문에 남은 입금금액 없음!
				dpstAmt = 0;
				break;
			} else {
				// 납부계정ID 존재시 수납 처리
				final String paramDpstSeqNo = dpstSeqNo;

				PaymentResult result = paymentService_1.processPayment(billSeqNo, pymSeqNo, dpstAmt, userId, new ProcessPaymentCallback() {

					@Override
					public Receipt getReceipt() {
						Deposit deposit = depositService.getDepositForRcpt(paramDpstSeqNo);

						Receipt receipt = new Receipt();
						receipt.setSoId(deposit.getSoId());
						receipt.setPymAcntId(deposit.getPymAcntId());
						receipt.setDpstProcDt(deposit.getDpstProcDt());
						receipt.setDpstDt(deposit.getDpstDt());
						receipt.setDpstCl(deposit.getDpstCl());
						receipt.setDpstSeqNo(deposit.getDpstSeqNo());
						receipt.setCrncyCd(deposit.getCrncyCd());
						receipt.setExrate(deposit.getExrate());
						receipt.setExrateAplyDt(deposit.getExrateAplyDt());
						receipt.setCnclYn(deposit.getCnclYn());
						receipt.setTransDt(deposit.getTransDt());
						receipt.setPayTp("1");
						receipt.setRegrId(deposit.getRegrId());
						return receipt;
					}
				});

				logger.debug("수납처리 완료 후 생성된 데이터 반영 처리");
				List<CBillComm> updateBillMastList = new ArrayList<CBillComm>();

				// 4. TBLIV_BILL_MAST update
				updateBillMastList.add(result.getUpdateBillMast());
				int update = paymentService_1.updateBillMastRcptAmt(updateBillMastList);
				// logger.debug("TBLIV_BILL_MAST테이블 수납금액 반영 결과 : " + update);

				List<CBillComm> updateBillList = result.getUpdateBillList();

				for (CBillComm bill : updateBillList) {
					// 5. TBLIV_BILL rcpt_amt, full_pay_yn update
					if ("Y".equals(result.getFullPayYn()) == true) {
						update = paymentService_1.updateFullPayBill(bill);
					} else {
						update = paymentService_1.updateBillRcptAmt(bill);
					}

					logger.debug(String.format("billSeqNo : %s, useYymm : %s, prodCmpsId : %s, svcCmpsId : %s", bill.getBillSeqNo(), bill.getUseYymm(), bill.getProdCmpsId(),
							bill.getSvcCmpsId()));
					logger.debug("청구내역에 대한 수납금액 반영 결과 : " + update);
				}

				// 6. TBLPY_RCPT insert
				int insert = receiptService.insertReceipt(result.getReceiptList());
				logger.debug("수납내역 등록 : " + insert);

				// 7. TBLPY_RCPT_DTL insert
				insert = receiptService.insertReceiptDetail(result.getReceiptDetailList());
				logger.debug("수납상세내역 등록 : " + insert);

				if (insert > 0) {
					// 8. TBLPY_DPST pay_prc_yn, pay_proc_dt update
					depositService.updatePayProcDt(dpstSeqNo, DateUtil.getDateStringYYYYMMDD(0), userId);
				}

				// 수납처리 후 남은 금액
				dpstAmt = result.getRemainAmt();
				logger.debug("수납처리 완료 후 남은 금액 : " + dpstAmt);

				lastReceipt = result.getReceiptList().get(result.getReceiptList().size() - 1);
			}

		}

		if (dpstAmt > 0) {
			logger.debug("선수금이 발생하였음");
			String prepayOccSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.MOD_ID_PREPAY_OCC, 10);

			PrepayOcc prepayOcc = new PrepayOcc();

			prepayOcc.setSoId(lastReceipt.getSoId());
			prepayOcc.setPrepayOccSeqNo(prepayOccSeqNo);
			prepayOcc.setPymAcntId(lastReceipt.getPymAcntId());
			prepayOcc.setPrepayOccDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
			prepayOcc.setPrepayOccTp("1");
			prepayOcc.setPrepayOccResn("1");
			prepayOcc.setPrepayOccTgtSeqNo(lastReceipt.getPymSeqNo()); // 마지막 수납일련번호가 들어감
			prepayOcc.setDpstDt(lastReceipt.getDpstDt());
			// prepayOcc.setDpstProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(1));
			prepayOcc.setDpstProcDt(DateUtil.getDateStringYYYYMMDD(0));
			prepayOcc.setDpstCl(lastReceipt.getDpstCl());
			prepayOcc.setPrepayProcStat("1"); // 선수금발생코드
			prepayOcc.setPrepayOccAmt(dpstAmt);
			prepayOcc.setPrepayTransAmt(0.0);
			prepayOcc.setPrepayBal(dpstAmt);
			prepayOcc.setTransCmplYn("N");
			prepayOcc.setWonPrepayOccAmt(0.0);
			prepayOcc.setCrncyCd(lastReceipt.getCrncyCd());
			prepayOcc.setExrate(lastReceipt.getExrate());
			prepayOcc.setExrateAplyDt(lastReceipt.getExrateAplyDt());
			prepayOcc.setCnclYn("N");
			prepayOcc.setCnclDttm("");
			prepayOcc.setTransDt(lastReceipt.getTransDt());
			prepayOcc.setRegrId(userId);
			prepayOcc.setRegDate(new Timestamp(new Date().getTime()));
			prepayOcc.setChgrId(userId);
			prepayOcc.setChgDate(new Timestamp(new Date().getTime()));

			// 9-1. TBLPY_PREPAY_OCC insert
			prepayService.insertPrepayOcc(prepayOcc);

			updateDeposit.setPayProcDt(DateUtil.getDateStringYYYYMMDD(0));
			updateDeposit.setPrepayTgtYn("Y");
			updateDeposit.setPayProcYn("Y");
			updateDeposit.setPrepayOccSeqNo(prepayOccSeqNo); // 2020.09.01 추가
			updateDeposit.setChgrId(userId);
			updateDeposit.setChgDate(new Timestamp(new Date().getTime()));

			// 9-2. TBLPY_DPST PAY_PROC_DT, PREPAY_TGT_YN, PAY_PROC_YN
			paymentService_1.updateDpstProc(updateDeposit);

		} else {
			// 선수금 발생없이 수납처리만 되었음!
			updateDeposit.setPayProcDt(DateUtil.getDateStringYYYYMMDD(0));
			updateDeposit.setPayProcYn("Y");
			updateDeposit.setChgrId(userId);
			updateDeposit.setChgDate(new Timestamp(new Date().getTime()));

			// 9. TBLPY_DPST PAY_PROC_DT, PAY_PROC_YN
			paymentService_1.updateDpstProc(updateDeposit);
		}

		return returnFlag;

	}

}
