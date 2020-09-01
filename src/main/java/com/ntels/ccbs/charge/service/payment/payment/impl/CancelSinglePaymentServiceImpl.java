package com.ntels.ccbs.charge.service.payment.payment.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.ntels.ccbs.charge.domain.common.CBillComm;
import com.ntels.ccbs.charge.domain.common.Receipt;
import com.ntels.ccbs.charge.domain.common.ReceiptDetail;
import com.ntels.ccbs.charge.domain.payment.payment.CancelSinglePaymentVO;
import com.ntels.ccbs.charge.mapper.payment.payment.CancelSinglePaymentMapper;
import com.ntels.ccbs.charge.service.payment.payment.CancelSinglePaymentService;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.common.service.SessionUser;

@Service
public class CancelSinglePaymentServiceImpl implements CancelSinglePaymentService {
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
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

		// 1. TBLPY_DPST update 취소처리
		CancelSinglePaymentVO dpstVo = updateCancelDeposit(cancelSinglePaymentVO.getDpstSeqNo());

		// 2. TBLPY_DPST_CNCL insert
		int insertCount = insertDepositCancelInfo(cancelSinglePaymentVO.getCnclResn(), cancelSinglePaymentVO.getDpstSeqNo());

		if (insertCount <= 0) {
			logger.debug("TBLPY_DPST_CNCL(insert) Error");
			throw new RuntimeException("TBLPY_DPST(insert) Error");
		}

		// 선납 입금(선수금 아님 주의)
		if (dpstVo.getDpstTp().equals("4") == true) {
			// kb 는 없어서 만들지 않았음. 향후 로직 추가해야 함.
		}

		if (dpstVo.getPayProcYn().equals("N") == true) {
			// kb 는 없어서 만들지 않았음. 향후 로직 추가해야 함.
			// ambgService.updateAmbgCancel(dpstSeqNo);
		} else if (dpstVo.getPayProcYn().equals("Y") == true) {
			SessionUser session = CommonUtil.getSessionManager();
			String userId = session.getUserId();

			// 수납 상세내역 조회
			List<ReceiptDetail> receiptDetailList = getReceiptDetailList(cancelSinglePaymentVO.getDpstSeqNo(), null);

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

				// 3. TBLIV_BILL update
				cancelSinglePaymentMapper.updateBillCancel(bill);
			}

			List<Receipt> billInfoReceiptList = cancelSinglePaymentMapper.getReceiptBillInfo(cancelSinglePaymentVO.getDpstSeqNo());

			// 수납내역의 데이터를 바탕으로 TBLIV_BILL_MAST테이블의 데이터를 수정한다.
			List<CBillComm> updateBillMastList = new ArrayList<>();

			for (Receipt receipt : billInfoReceiptList) {
				CBillComm bill = new CBillComm();

				bill.setBillSeqNo(receipt.getBillSeqNo());
				bill.setRcptAmt(receipt.getRcptAmt());
				bill.setChgrId(userId);
				bill.setTimeInfo();

				// 4. TBLIV_BILL_MAST update
				cancelSinglePaymentMapper.updateBillMastCancel(bill);
			}

			// 5. TBLPY_RCPT update
			cancelSinglePaymentMapper.updateRcptCancel(cancelSinglePaymentVO.getDpstSeqNo(), userId);

			for (Receipt receipt : billInfoReceiptList) {
				// 6. TBLPY_PREPAY_OCC update.
				updatePrepayOccCancel(receipt.getPymSeqNo());

				// kb 보증금 없어서 삭제 - 향후추가해야 함 . 보증금발생내역를 수정한다.
				// assrService.updateAssrOccCancel(dpstSeqNo);
			}
		}

		return returnFlag;
	}

	public CancelSinglePaymentVO updateCancelDeposit(String dpstSeqNo) {

		SessionUser session = CommonUtil.getSessionManager();
		String userId = session.getUserId();

		CancelSinglePaymentVO dpstVO = cancelSinglePaymentMapper.getDepositForCancel(dpstSeqNo);

		if (dpstVO.getCnclYn().equals("Y") == true) {
			logger.debug("Payment had already been canceled.");
			throw new RuntimeException("Payment had already been canceled.");
		}

		CancelSinglePaymentVO depositCancelVO = new CancelSinglePaymentVO();
		depositCancelVO.setCnclYn("Y");
		depositCancelVO.setChgrId(userId);
		depositCancelVO.setDpstSeqNo(dpstSeqNo);

		// 입금내역에 취소상태를 취소(Y)로 업데이트
		int cnt = cancelSinglePaymentMapper.updateCnclYn(depositCancelVO);

		if (cnt <= 0) {
			logger.debug("TBLPY_DPST(Update) Error");
			throw new RuntimeException("TBLPY_DPST(Update) Error");
		}

		return dpstVO;
	}

	public int insertDepositCancelInfo(String cnclResn, String dpstSeqNo) {

		SessionUser session = CommonUtil.getSessionManager();
		String userId = session.getUserId();

		CancelSinglePaymentVO depositCancelVO = cancelSinglePaymentMapper.getDepositCancelInfo(dpstSeqNo);

		depositCancelVO.setCnclResn(cnclResn);
		depositCancelVO.setCnclDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		depositCancelVO.setCnclrId(userId);
		depositCancelVO.setRegDate(DateUtil.sysdate());

		return cancelSinglePaymentMapper.insertDepositCancel(depositCancelVO);
	}

	public List<ReceiptDetail> getReceiptDetailList(String dpstSeqNo, String pymSeqNo) {

		if (StringUtils.isEmptyOrWhitespaceOnly(dpstSeqNo) == true && StringUtils.isEmptyOrWhitespaceOnly(pymSeqNo)) {
			throw new RuntimeException("dpstSeqNo또는 pymSeqNo값이 없어서 조회할 수 없습니다.");
		}

		ReceiptDetail receiptDetail = new ReceiptDetail();
		receiptDetail.setPymSeqNo(pymSeqNo);
		receiptDetail.setDpstSeqNo(dpstSeqNo);
		return cancelSinglePaymentMapper.getReceiptDetailList(dpstSeqNo, pymSeqNo);
	}

	public List<Receipt> getReceiptBillInfo(String dpstSeqNo) {

		if (StringUtils.isEmptyOrWhitespaceOnly(dpstSeqNo) == true) {
			throw new RuntimeException("dpstSeqNo 값이 없어서 BILL정보를  조회할 수 없습니다.");
		}

		return cancelSinglePaymentMapper.getReceiptBillInfo(dpstSeqNo);

	}

	public int updatePrepayOccCancel(String pymSeqNo) {
		SessionUser session = CommonUtil.getSessionManager();

		int count = cancelSinglePaymentMapper.getPrepayTransCount(pymSeqNo);

		// 선수금발생내역에 있으면서 선수금대체내역에 있으면 처리할 수 없음
		if (count > 0) {
			throw new RuntimeException("count is greater than 0.");
		}

		String cnclDttm = DateUtil.getDateStringYYYYMMDDHH24MISS(0);

		return cancelSinglePaymentMapper.updatePrepayOccCancel(cnclDttm, pymSeqNo, session.getUserId());

	}
	
	
//	
//	@Override
//	public List<CancelSinglePaymentVO> getPermitOrg(CancelSinglePaymentVO cancdlSinglePaymentVO) {
//		String today = DateUtil.getDateStringYYYYMMDD(0);
//		cancdlSinglePaymentVO.setProcDt(today);
//		return cancelSinglePaymentMapper.getPermitOrg(cancdlSinglePaymentVO);
//	}
//
//	@Override
//	public int getPermitOrgCount(CancelSinglePaymentVO cancdlSinglePaymentVO) {
//		String today = DateUtil.getDateStringYYYYMMDD(0);
//		cancdlSinglePaymentVO.setProcDt(today);
//		return cancelSinglePaymentMapper.getPermitOrgCount(cancdlSinglePaymentVO);
//	}
//	
//	@Override
//	public List<CancelSinglePaymentVO> getLoanAvlAmt(CancelSinglePaymentVO cancdlSinglePaymentVO) {
//		String today = DateUtil.getDateStringYYYYMMDD(0);
//		cancdlSinglePaymentVO.setProcDt(today);
//		return cancelSinglePaymentMapper.getLoanAvlAmt(cancdlSinglePaymentVO);
//	}
//
//	@Override
//	public int getLoanAvlAmtCount(CancelSinglePaymentVO cancdlSinglePaymentVO) {
//		String today = DateUtil.getDateStringYYYYMMDD(0);
//		cancdlSinglePaymentVO.setProcDt(today);
//		return cancelSinglePaymentMapper.getLoanAvlAmtCount(cancdlSinglePaymentVO);
//	}
//	
//	@Override
//	public List<CancelSinglePaymentVO> getRcptCnclCnt(CancelSinglePaymentVO cancdlSinglePaymentVO) {
//		return cancelSinglePaymentMapper.getRcptCnclCnt(cancdlSinglePaymentVO);
//	}
//	
//	@Override
//	public List<Map<String,Object>> getCaseByCancelListExcel(CancelSinglePaymentVO cancdlSinglePaymentVO, String lngTyp) {
//		return cancelSinglePaymentMapper.getCaseByCancelListExcel(cancdlSinglePaymentVO, lngTyp);
//	}
//	
	
	

	
	
}
