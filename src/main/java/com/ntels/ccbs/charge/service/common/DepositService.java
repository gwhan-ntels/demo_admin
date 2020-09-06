package com.ntels.ccbs.charge.service.common;

import java.sql.Timestamp;

import com.ntels.ccbs.charge.domain.common.Deposit;
import com.ntels.ccbs.charge.domain.common.EachDeposit;

public interface DepositService {
	

	int insertEachDeposit(EachDeposit eachDeposit);
	
	int insertDepositFromEachDeposit(EachDeposit eachDeposit);
	
	int updateEachDeposit(EachDeposit eachDeposit);

	double getDpstAmt(String dpstSeqNo);
	
	/**
	 * 입금내역에 등록된 데이타를 수납내역에 등록하기위해 참조하는 Dpst를 가져온다.
	 * @param dpstSeqNo
	 * @return
	 */
	Deposit getDepositForRcpt(String dpstSeqNo);

	int updatePayProcDt(String dpstSeqNo, String payProcDt, String chgrId);
	

	Deposit updateCancelDeposit(String dpstSeqNo, String chgrId);
	
	int insertDepositCancelInfo(String regrId, String cnclRsn, String dpstSeqNo);
	
//
//	/**
//	 * 건별입금내역을 저장한다.
//	 * @param eachDeposit
//	 * @return
//	 */
//	int insertEachDeposit(EachDeposit eachDeposit);
//	
//	/**
//	 * 건별입금내역에 등록된 데이타를 조회한다.
//	 * @param eachDpstSeq
//	 * @return
//	 */
//	EachDeposit getEachDeposit(EachDeposit eachDeposit);
//	 
//	/**
//	 * 입금내역에 등록된 데이타에 대해서 건별입금내역의 입금처리일자를 수정한다.
//	 * @param eachDeposit
//	 * @return
//	 */
//	int updateEachDeposit(EachDeposit eachDeposit);
//	
//	/**
//	 * 입금금액을 조회한다.
//	 * @param dpstSeqNo
//	 * @return
//	 */
//	double getDpstAmt(String dpstSeqNo);
//	
//	/**
//	 * 보증금발생 이력을 저장하기 위해 참조하는 Dpst를 가져온다.
//	 * @param dpstSeqNo
//	 * @return
//	 */
//	Deposit getDepositForAssrOcc(String dpstSeqNo);
//
//	
//	/**
//	 * 수납 처리 여부를 Y로 수정해준다.
//	 * @param dpstSeqNo
//	 * @param payProcDt
//	 * @return
//	 */
//	int updatePayProcDt(String dpstSeqNo, String payProcDt);
//	
//	/**
//	 * <pre>
//	 * 건별입금을 취소한다.
//	 * TBLPY_DPST테이블의 CNCL_YN의 값을 조회하여 해당 값이 Y라면 이미 취소된 내역이다.
//	 * 값이 N이라면 테이블의 CNCL_YN의 값을 Y로 업데이트 한다 
//	 * </pre>
//	 * @param dpstSeqNo
//	 * @return
//	 */
//	Deposit updateCancelDeposit(String dpstSeqNo);
//	
//	/**
//	 * 취소된 입금내역을 TBLPY_DPST_CNCL에 등록한다.
//	 * @param regrId
//	 * @param reason
//	 * @param dpstSeqNo
//	 * @return
//	 */
//	int insertDepositCancelInfo(String regrId, String reason, String dpstSeqNo);
//	
//	/**
//	 * 일괄 수납처리를 위한 입금내역을 조회한다.
//	 * @param parallelCnt
//	 * @param parallelNo
//	 * @return
//	 */
//	List<Deposit> getDepositListForBatchReceipt(int parallelCnt, int parallelNo);
//	
//	/**
//	 * 입금내역 수납처리 결과반영
//	 * @param ambgTgtYn
//	 * @param payProcDt
//	 * @param prepayTgtYn
//	 * @param dpstSeqNo
//	 */
//	int updateDepositBatchReceipt(String ambgTgtYn, String payProcDt, String prepayTgtYn, String dpstSeqNo);
	
}
