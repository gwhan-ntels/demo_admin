package com.ntels.ccbs.charge.service.payment.payment;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.charge.domain.payment.payment.CancelSinglePaymentVO;

public interface CancelSinglePaymentService {
	
	public int getCaseByCancelListCount(CancelSinglePaymentVO cancelSinglePaymentVO);
	
	public List<CancelSinglePaymentVO> getCaseByCancelList(CancelSinglePaymentVO cancelSinglePaymentVO);

	int getCancelCheck(String dpstSeqNo);
	
	int insertAction(CancelSinglePaymentVO cancelSinglePaymentVO);
	
	
	
	
	
//	public List<CancelSinglePaymentVO> getPermitOrg(CancelSinglePaymentVO cancelSinglePaymentVO);
//	
//	public int getPermitOrgCount(CancelSinglePaymentVO cancelSinglePaymentVO);
//	 
//	public List<CancelSinglePaymentVO> getLoanAvlAmt(CancelSinglePaymentVO cancelSinglePaymentVO);
//	
//	public int getLoanAvlAmtCount(CancelSinglePaymentVO cancelSinglePaymentVO);
//	
//	public List<CancelSinglePaymentVO> getRcptCnclCnt(CancelSinglePaymentVO cancelSinglePaymentVO);
//	
//	List<Map<String, Object>> getCaseByCancelListExcel(
//			CancelSinglePaymentVO cancelSinglePaymentVO,
//			String lngTyp);

	
	

	
}
