package com.ntels.ccbs.charge.service.common;

import java.util.List;

import com.ntels.ccbs.charge.domain.common.PrepayOcc;

public interface PrepayService {

	/**
	 * 선수금 발생내역을 일괄등록한다.
	 * 
	 * @param prepayOccList
	 * @return
	 */
	int insertPrepayOcc(PrepayOcc prepayOcc);

	/**
	 * 선수금대체이력및 선수금발생내역를 수정한다.
	 * 
	 * @param pymSeqNo
	 * @param prepayTransSeqNo
	 * @param payObjAmt
	 * @return
	 */
	int updatePrepayTransHistory(String pymSeqNo, String prepayTransSeqNo, double payObjAmt);
	

	/**
	 * 선수금대체이력및 선수금발생내역를 수정한다.
	 * @param pymSeqNo
	 * @return
	 */
	int updatePrepayOccCancel(String pymSeqNo);
	

	/**
	 * 선수금 대체금액 및 선수금 잔액을 조회한다.
	 * @param prepayOccSeqNo
	 * @return
	 */
	PrepayOcc getPrepayAmount(String prepayOccSeqNo);
	

	List<String> getBillSeqNoCheck(String billSeqNo);
	

	/**
	 * 선수금 발생이력 조회
	 * @param prepayOccSeqNo
	 * @return
	 */
	PrepayOcc getPrepayOcc(String prepayOccSeqNo);
	

	/**
	 * 선수금발생내역을 수정한다.
	 * @param prepayOccSeqNo
	 * @param payAplyAmt
	 * @return
	 */
	int updatePrepayOcc(PrepayOcc prepayOcc);

	/**
	 * 선수금대체이력에 등록한다.
	 * @param prepayOccSeqNo
	 * @param payAplyAmt
	 * @param prepayReplTp
	 * @param regrId
	 * @return
	 */
	String insertPrepayTransHistory(PrepayOcc prepayOcc);
	
	

	/**
	 * 기존의 선수금발생 내역의 데이터를 가지고 새로운 선수금 발생내역을 등록한다.
	 * @param prepayOccSeqNo
	 * @return
	 */
	String insertNewPrepayOccFromPrepayOcc(PrepayOcc prepayOcc);
	

//	/**
//	 * 선수금 발생내역을 등록한다.
//	 * @param pymSeqNo
//	 * @return
//	 */
//	int insertPrepayOcc(String pymSeqNo, String prepayOccTp, String prepayOccResn, String regrId, double amt);
	
//	/**
//	 * 선수금 발생내역을 일괄등록한다.
//	 * @param prepayOccList
//	 * @return
//	 */
//	int insertPrepayOcc(List<PrepayOcc> prepayOccList);
//	
//	/**
//	 * 선수금 발생내역을 일괄등록한다.
//	 * @param prepayOccList
//	 * @return
//	 */
//	int insertPrepayOcc(PrepayOcc prepayOcc);
//	
//	/**
//	 * 선납계약정보에 입금취소처리
//	 * @param regrId
//	 * @param dpstSeqNo
//	 * @return
//	 */
//	int updatePrepayCancel(String regrId, String dpstSeqNo);
//	
//	
//	/**
//	 * 선수금 발생내역을 조회한다.
//	 * @param pymSeqNo
//	 * @return
//	 */
//	PrepayOcc getPrepayDeposit(String pymSeqNo);
//	
//	/**
//	 * prepayTransSeqNo로 prepayOccSeqNo를 조회하여 선수금 발생내역을 취소처리한다.
//	 * @param prepayTransSeqNo
//	 * @return
//	 */
//	int updatePrepayOccByPrepayTransSeqNo(String prepayTransSeqNo);
//	
//	
//	
//	
//	/**
//	 * 
//	 * @param prepayOcc
//	 * @return
//	 */
//	int updatePrepayOccTransAmt(PrepayOcc prepayOcc);
//	
//	/**
//	 * 
//	 * @param prepayOccList
//	 * @return
//	 */
//	int updatePrepayOccTransAmt(List<PrepayOcc> prepayOccList);
//	
//	
//	/**
//	 * 선수금대체이력에 등록한다.
//	 * @param prepayTransHistory
//	 * @return
//	 */
//	int insertPrepayTransHistory(List<PrepayTransHistory> prepayTransHistory);
//	
//
//	
//	/**
//	 * 수납이 완료되지 않은 청구건에 대해 선수금 발생내역을 조회한다.
//	 * @param soId
//	 * @return
//	 */
//	List<PrepayOcc> getPrepayOccForUnpaidBill(String soId);
//	
//	int updatePrepayOccProcStat(PrepayOcc prepayOcc);
//	
//	int updatePrepayOccProcStat(List<PrepayOcc> prepayOcc);
	
}
