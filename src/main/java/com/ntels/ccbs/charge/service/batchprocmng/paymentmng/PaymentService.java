package com.ntels.ccbs.charge.service.batchprocmng.paymentmng;

import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.DepositVO;

public interface PaymentService {
	
	/**
	 * 입금/수납을 처리한다.
	 * @param procScopeFlag(A-입금/수납 처리,R-수납만 처리), pymAcntId, dpstSeqNo, depositInfo, inptMenuId, workId
	 * @return
	 */
	//int processReceipt(String procScopeFlag, String pymAcntId, String dpstSeqNo, DepositVO depositInfo, String inptMenuId, String workId) throws Exception;
	int processReceipt(String procScopeFlag, String pymAcntId, String dpstSeqNo, DepositVO depositInfo, String inptMenuId, String workId, String rdoDpstGubn) throws Exception;

}
