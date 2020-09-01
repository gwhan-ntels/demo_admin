package com.ntels.ccbs.charge.service.payment.payment;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.charge.domain.common.EachDeposit;
import com.ntels.ccbs.charge.domain.payment.payment.NewSinglePaymentVO;

public interface NewSinglePaymentService {
	public List<NewSinglePaymentVO> getAnonyReceiptSubList(NewSinglePaymentVO newSinglePaymentVO);

	public int getAnonyReceiptSubListCount(NewSinglePaymentVO newSinglePaymentVO);

	public List<NewSinglePaymentVO> getMyReceiptList(NewSinglePaymentVO newSinglePaymentVO);

	public int getMyReceiptListCount(NewSinglePaymentVO newSinglePaymentVO);

	public List<NewSinglePaymentVO> getBillInfo(NewSinglePaymentVO newSinglePaymentVO);

	public int getBillInfoCount(NewSinglePaymentVO newSinglePaymentVO);

	public List<NewSinglePaymentVO> getPermitOrg(NewSinglePaymentVO newSinglePaymentVO);

	public int getPermitOrgCount(NewSinglePaymentVO newSinglePaymentVO);

	public List<NewSinglePaymentVO> getLoanAvlAmt(NewSinglePaymentVO newSinglePaymentVO);

	public int getLoanAvlAmtCount(NewSinglePaymentVO newSinglePaymentVO);

	List<Map<String, Object>> listAnonyReceiptSubExcel(NewSinglePaymentVO newSinglePaymentVO);

	List<Map<String, Object>> listMyReceiptExcel(String soId, String usrId, String lngTyp);

	
	
	
	public int insertAction(EachDeposit eachDeposit);

}
