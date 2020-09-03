package com.ntels.ccbs.charge.service.payment.payment.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.common.CBillComm;
import com.ntels.ccbs.charge.domain.common.PrepayOcc;
import com.ntels.ccbs.charge.domain.common.Receipt;
import com.ntels.ccbs.charge.domain.common.ReceiptDetail;
import com.ntels.ccbs.charge.domain.payment.payment.ReceiptCancelVO;
import com.ntels.ccbs.charge.mapper.payment.payment.ReceiptCancelMapper;
import com.ntels.ccbs.charge.service.common.AmbgService;
import com.ntels.ccbs.charge.service.common.AssrService;
import com.ntels.ccbs.charge.service.common.PaymentService_1;
import com.ntels.ccbs.charge.service.common.PrepayService;
import com.ntels.ccbs.charge.service.common.ReceiptService;
import com.ntels.ccbs.charge.service.payment.payment.ReceiptCancelService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class ReceiptCancelServiceImpl implements ReceiptCancelService {
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private ReceiptCancelMapper receiptCancelMapper;

	@Autowired
	private PaymentService_1 paymentService_1;

	@Autowired
	private ReceiptService receiptService;

	@Autowired
	private SequenceService sequenceService;

	@Autowired
	private PrepayService prepayService;
	
	@Autowired
	private AssrService assrService;
	
	@Autowired
	private AmbgService ambgService;

	@Override
	public int rcptListCnt(ReceiptCancelVO receiptCancel) {
		return receiptCancelMapper.rcptListCnt(receiptCancel);
	}

	@Override
	public List<ReceiptCancelVO> rcptList(ReceiptCancelVO receiptCancel, int page, int perPage) {
		int start = 0;
		int end = 0;

		start = (page - 1) * perPage;
		end = perPage;

		return receiptCancelMapper.rcptList(receiptCancel, start, end);
	}

	@Override
	public int receiptCancelResultListCount(ReceiptCancelVO receiptCancel) {
		return receiptCancelMapper.receiptCancelResultListCount(receiptCancel);
	}

	@Override
	public List<ReceiptCancelVO> receiptCancelResultList(ReceiptCancelVO receiptCancel, int page, int perPage) {
		int start = 0;
		int end = 0;

		start = (page - 1) * perPage;
		end = perPage;

		return receiptCancelMapper.receiptCancelResultList(receiptCancel, start, end);
	}

	@Override
	public int getReceiptCancelAbleCheck(String pymSeqNo) {
		int returnFlag = 1;

		if (receiptCancelMapper.getTransApplCheckCnt(pymSeqNo) > 0) {
			returnFlag = -1;
		}

		return returnFlag;
	}

	@Override
	public int insertAction(ReceiptCancelVO receiptCancelVO ) {
		int returnFlag = 1;
		String pymSeqNo = receiptCancelVO.getPymSeqNo();
		
		SessionUser session = CommonUtil.getSessionManager();
		String userId = session.getUserId();

		String cnclYn = receiptService.getCancelYn(pymSeqNo);

		if ("Y".equals(cnclYn) == true) {
			logger.debug("Receipt had already been canceled. " + pymSeqNo);
			throw new RuntimeException("Receipt had already been canceled. " + pymSeqNo);
		}

		// 수납 상세내역 조회
		List<ReceiptDetail> receiptDetailList = receiptService.getReceiptDetailList(null, pymSeqNo);

		if (receiptDetailList.isEmpty() == true) {
			throw new RuntimeException("Receipt detail not exists!!");
		}

		// 수납금액의 합
		double payObjAmt = 0.0;

		// 청구내역의 수납금액 취소
		for (ReceiptDetail receiptDetail : receiptDetailList) {
			payObjAmt += receiptDetail.getRcptAmt();

			if (receiptDetail.getRcptAmt() != 0) {

				Double billRcptAmt = paymentService_1.getBillRcptAmt(receiptDetail.getBillSeqNo(), receiptDetail.getUseYymm(), receiptDetail.getProdCmpsId(),
						receiptDetail.getSvcCmpsId(), receiptDetail.getChrgItmCd());

				billRcptAmt = billRcptAmt == null ? 0 : billRcptAmt;

				if (billRcptAmt < receiptDetail.getRcptAmt()) {
					throw new RuntimeException(
							String.format("Bill amount is less than the cancellation amount. Bill amount[%f],Receipt amount[%f]", billRcptAmt, receiptDetail.getRcptAmt()));
				}

				CBillComm bill = new CBillComm();

				bill.setRcptAmt(receiptDetail.getRcptAmt());
				bill.setBillSeqNo(receiptDetail.getBillSeqNo());
				bill.setUseYymm(receiptDetail.getUseYymm());
				bill.setProdCmpsId(receiptDetail.getProdCmpsId());
				bill.setSvcCmpsId(receiptDetail.getSvcCmpsId());
				bill.setChrgItmCd(receiptDetail.getChrgItmCd());
				bill.setChgrId(userId);
				bill.setTimeInfo();

				// 1 TBLIV_BILL rcpt_amt, full_pay_yn update.
				paymentService_1.updateBillCancel(bill);
			}
		}

		// 수납일련변호로 수납취소 처리
		// 2. TBLPY_RCPT UPDATE, cncl_yn
		receiptService.updateReceiptCancel(null, pymSeqNo, userId);

		Receipt receipt = receiptService.getReceipt(pymSeqNo);
		receipt.setPayObjAmt(receipt.getPayObjAmt() - receipt.getPrepayAplyAmt());

		CBillComm updateBillMast = new CBillComm();	
		
		updateBillMast.setBillSeqNo(receipt.getBillSeqNo());
		updateBillMast.setRcptAmt(receipt.getRcptAmt());
		updateBillMast.setChgrId(userId);
		updateBillMast.setTimeInfo();		

		// 2. TBLIV_BILL_MAST rcpt_amt, unpay_amt, full_pay_yn update
		paymentService_1.updateBillMastCancel(updateBillMast);
		
		String payTp = receipt.getPayTp();

		// 수납코드 정의
		// 1: 정상수납, 2: 선수금수납, 3: 상이납자수납, 4: 미확인입금수납(불명금), 7: (-)매출수납, 9: 대체손각

		if ("1".equals(payTp) == true || "3".equals(payTp) == true) {
			if (payObjAmt != 0.0) {
				// 선수금 발생내역에 등록
				String prepayOccSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.MOD_ID_PREPAY_OCC, 10);

				PrepayOcc newPrepayOcc = new PrepayOcc();

				newPrepayOcc.setSoId(receipt.getSoId());
				newPrepayOcc.setPrepayOccSeqNo(prepayOccSeqNo);
				newPrepayOcc.setPymAcntId(receipt.getPymAcntId());
				newPrepayOcc.setPrepayOccDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(1));
				newPrepayOcc.setPrepayOccTp("3");	// TODO prepay_occ_tp 1(수납처리 과납)에서 3(수납취소)로 들어가야 하는게 아닌지 ? 2020.09.03
				newPrepayOcc.setPrepayOccResn("5");
				newPrepayOcc.setPrepayOccTgtSeqNo(receipt.getPymSeqNo());
				newPrepayOcc.setDpstDt(receipt.getDpstDt());
				// newPrepayOcc.setDpstProcDttm(receipt.getDpstProcDt());
				newPrepayOcc.setDpstProcDt(receipt.getDpstProcDt());
				newPrepayOcc.setDpstCl(receipt.getDpstCl());
				newPrepayOcc.setPrepayProcStat("1");
				newPrepayOcc.setPrepayOccAmt(payObjAmt);
				newPrepayOcc.setPrepayTransAmt(0.0);
				newPrepayOcc.setPrepayBal(payObjAmt);
				newPrepayOcc.setTransCmplYn("N");
				newPrepayOcc.setWonPrepayOccAmt(payObjAmt);
				newPrepayOcc.setCrncyCd(receipt.getCrncyCd());
				newPrepayOcc.setExrate(receipt.getExrate());
				newPrepayOcc.setExrateAplyDt(receipt.getExrateAplyDt());
				newPrepayOcc.setRegrId(userId);
				newPrepayOcc.setRegDate(new Timestamp(new Date().getTime()));
				newPrepayOcc.setCnclYn("N");
				newPrepayOcc.setCnclDttm("");
				newPrepayOcc.setChgDate(new Timestamp(new Date().getTime()));
				newPrepayOcc.setTransDt(receipt.getTransDt());

				// 3. TBLPY_PREPAY_OCC insert
				prepayService.insertPrepayOcc(newPrepayOcc);
			}
		} else if ("2".equals(payTp) == true) {
			prepayService.updatePrepayTransHistory(pymSeqNo, receipt.getPrepayTransSeqNo(), payObjAmt);
		} else if ("8".equals(payTp) == true) {
			// kb 없음
			// assrService.updateAssrOcc(pymSeqNo, receipt.getAssrTransSeqNo(), payObjAmt);
		} else if ("4".equals(payTp) == true) {
			// kb 없음
			ambgService.updateAmbg(receipt.getDpstSeqNo(), receipt.getAmbgTransSeqNo(), payObjAmt);
		} else {
			logger.debug(String.format("receipt type was wrong with the code value[%s]", payTp));
		}

		// 5.TBLPY_RCPT_CNCL_APPL insert
		receiptService.insertReceiptCancelAppl(pymSeqNo, userId);
		// 6.TBLPY_RCPT_CNCL, TBLPY_RCPT_CNCl_DTL INSERT
		receiptService.insertReceiptCancel(receiptCancelVO.getCnclResn(), pymSeqNo, "0000000000", userId); // payTp 에서 userId 로 수정 2020.09.03

		return returnFlag;
	}

}
