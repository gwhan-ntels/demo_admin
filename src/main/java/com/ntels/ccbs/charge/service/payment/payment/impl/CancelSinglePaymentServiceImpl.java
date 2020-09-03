package com.ntels.ccbs.charge.service.payment.payment.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.common.CBillComm;
import com.ntels.ccbs.charge.domain.common.Deposit;
import com.ntels.ccbs.charge.domain.common.Receipt;
import com.ntels.ccbs.charge.domain.common.ReceiptDetail;
import com.ntels.ccbs.charge.domain.payment.payment.CancelSinglePaymentVO;
import com.ntels.ccbs.charge.mapper.payment.payment.CancelSinglePaymentMapper;
import com.ntels.ccbs.charge.service.common.AmbgService;
import com.ntels.ccbs.charge.service.common.AssrService;
import com.ntels.ccbs.charge.service.common.DepositService;
import com.ntels.ccbs.charge.service.common.PaymentService_1;
import com.ntels.ccbs.charge.service.common.PrepayService;
import com.ntels.ccbs.charge.service.common.ReceiptService;
import com.ntels.ccbs.charge.service.payment.payment.CancelSinglePaymentService;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.system.domain.common.service.SessionUser;

@Service
public class CancelSinglePaymentServiceImpl implements CancelSinglePaymentService {
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */

	@Autowired
	private DepositService depositService;

	@Autowired
	private AmbgService ambgService;

	@Autowired
	private ReceiptService receiptService;

	@Autowired
	private PaymentService_1 paymentService_1;
	
	@Autowired
	private PrepayService prepayService;
	
	@Autowired
	private AssrService assrService;
	
	@Autowired
	private CancelSinglePaymentMapper cancelSinglePaymentMapper;

	@Override
	public int getCaseByCancelListCount(CancelSinglePaymentVO cancdlSinglePaymentVO) {
		return cancelSinglePaymentMapper.getCaseByCancelListCount(cancdlSinglePaymentVO);
	}

	@Override
	public List<CancelSinglePaymentVO> getCaseByCancelList(CancelSinglePaymentVO cancdlSinglePaymentVO) {
		return cancelSinglePaymentMapper.getCaseByCancelList(cancdlSinglePaymentVO);
	}

	@Override
	public int getCancelCheck(String dpstSeqNo) {
		int returnFlag = 1;

		// 선수금, 불명금, 보증금 대체건, 환불이 있는지 CHECK 한다.
		if (cancelSinglePaymentMapper.getTransCheckAmt(dpstSeqNo) > 0) {
			returnFlag = -1;
		}

		return returnFlag;
	}

	@Override
	public int insertAction(CancelSinglePaymentVO cancelSinglePaymentVO) {
		int returnFlag = 1;

		boolean existReceiptData = true;

		SessionUser session = CommonUtil.getSessionManager();
		String userId = session.getUserId();

		String dpstSeqNo = cancelSinglePaymentVO.getDpstSeqNo();
		String cnclResn = cancelSinglePaymentVO.getCnclResn();

		// 1. TBLPY_DPST CNCL_YN = 'Y' update 취소처리
		Deposit deposit = depositService.updateCancelDeposit(dpstSeqNo);

		// 2. TBLPY_DPST_CNCL insert
		int insertCount = depositService.insertDepositCancelInfo(userId, cnclResn, dpstSeqNo);

		if (insertCount <= 0) {
			logger.debug("TBLPY_DPST_CNCL(insert) Error");
			throw new RuntimeException("TBLPY_DPST(insert) Error");
		}

		// 선납 입금(선수금 아님 주의)
		if (deposit.getDpstTp().equals("4") == true) {
			// kb 는 없어서 만들지 않았음. 향후 로직 추가해야 함.
			// 선납계약정보에서 입급취소처리
			// TODO TCMBL_PREPAY_CTRT에서 금액을 조회한 후 prepdAplyAmt or aplDgr이 0보다 크면
			// 이미 적용중인 선납계약의 입금이므로 취소불가 처리를 하고
			// 그렇지 않은 경우에는 갱신처리를 해주어야함. 하지만 현재 TCMBL_PREPAY_CTRT테이블이 제거되어 해당 작업을 수행할 수 없음

			// 선납입금정보에 입금취소처리
			// prepayService.updatePrepayCancel(regrId, dpstSeqNo);

			// 종료
			// return RepeatStatus.FINISHED;
		}

		if (deposit.getPayProcYn().equals("N") == true) {
			// kb는 불명금 없음.
			ambgService.updateAmbgCancel(dpstSeqNo);
		} else if (deposit.getPayProcYn().equals("Y") == true) {
			// 수납 상세내역 조회
			List<ReceiptDetail> receiptDetailList = receiptService.getReceiptDetailList(cancelSinglePaymentVO.getDpstSeqNo(), null);

			// 청구내역의 수납금액,완납여부를 수정한다.
			for (ReceiptDetail receiptDetail : receiptDetailList) {

				CBillComm bill = new CBillComm();

				bill.setRcptAmt(receiptDetail.getRcptAmt());
				bill.setBillSeqNo(receiptDetail.getBillSeqNo());
				bill.setUseYymm(receiptDetail.getUseYymm());
				bill.setProdCmpsId(receiptDetail.getProdCmpsId());
				bill.setSvcCmpsId(receiptDetail.getSvcCmpsId());
				bill.setChrgItmCd(receiptDetail.getChrgItmCd());
				bill.setChgrId(userId);
				bill.setTimeInfo();

				// 3. TBLIV_BILL RCPT_AMT, FULL_PAY_YN = 'N' update
				paymentService_1.updateBillCancel(bill);
			}

			List<Receipt> billInfoReceiptList = receiptService.getReceiptBillInfo(dpstSeqNo);

			for (Receipt receipt : billInfoReceiptList) {
				CBillComm bill = new CBillComm();

				bill.setBillSeqNo(receipt.getBillSeqNo());
				bill.setRcptAmt(receipt.getRcptAmt());
				bill.setChgrId(userId);
				bill.setTimeInfo();

				// 4. TBLIV_BILL_MAST RCPT_AMT, UNPAY_AMT, FULL_PAY_YN update
				paymentService_1.updateBillMastCancel(bill);
			}

			// 5. TBLPY_RCPT CNCL_YN = 'Y' update 수납내역의 취소여부('Y')를 수정한다.
			receiptService.updateReceiptCancel(dpstSeqNo, null, userId);

			if (billInfoReceiptList.isEmpty() == true) {
				existReceiptData = false;
			} else {
				existReceiptData = true;
			}
			
			for (Receipt receipt : billInfoReceiptList) {
				// 선수금발생내역에 있으면서 선수금대체내역에 있는지 체크 후 대체가 있으면 에러 , 없으면 UPDATE
				// 6. TBLPY_PREPAY_OCC CNCL_YN = 'Y', CNCL_DTTM = SYSDATE update.
				prepayService.updatePrepayOccCancel(receipt.getPymSeqNo());

				// kb 보증금 없음. 보증금발생내역를 수정한다.
				assrService.updateAssrOccCancel(dpstSeqNo);
			}
		}

		if (existReceiptData == false) {
			prepayService.updatePrepayOccCancel(dpstSeqNo);
		}

		return returnFlag;
	}

}
