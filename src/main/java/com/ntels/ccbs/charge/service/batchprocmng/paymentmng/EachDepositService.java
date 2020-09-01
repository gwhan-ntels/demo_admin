package com.ntels.ccbs.charge.service.batchprocmng.paymentmng;

public interface EachDepositService {
	
	/**
	 * 개별 입금을 처리한다.
	 * @param eachDpstSeq, inptMenuId, workId
	 * @return
	 */
	//public int processEachDeposit(String eachDpstSeq, String inptMenuId, String workId) throws Exception;
	public int processEachDeposit(String eachDpstSeq, String inptMenuId, String workId, String rdoDpstGubn) throws Exception;

}
