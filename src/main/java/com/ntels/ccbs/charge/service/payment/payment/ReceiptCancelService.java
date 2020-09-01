package com.ntels.ccbs.charge.service.payment.payment;

import java.util.List;

import com.ntels.ccbs.charge.domain.payment.payment.ReceiptCancelVO;

public interface ReceiptCancelService {
	public int rcptListCnt(ReceiptCancelVO receiptCancel);
	
	public List<ReceiptCancelVO> rcptList(ReceiptCancelVO receiptCancel, int page, int perPage);
	
	public int receiptCancelResultListCount(ReceiptCancelVO receiptCancel);
	
	public List<ReceiptCancelVO> receiptCancelResultList(ReceiptCancelVO receiptCancel, int page, int perPage);
	
	public int getReceiptCancelAbleCheck(String pymSeqNo);
	
	public int insertAction(ReceiptCancelVO receiptCancelVO);

//	public int processReceiptCancelMain(String pymSeqNo, String cnclResnTxt, String inptMenuId, String workId) throws Exception;
}
