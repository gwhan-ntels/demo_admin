package com.ntels.ccbs.charge.service.common;

import com.ntels.ccbs.charge.domain.common.AmbgOcc;

public interface AmbgService {
	int insertAmbgOcc(String ambgOccSeqNo, String regrId, String dpstSeqNo);

	/**
	 * 불명금(납부계정이 없는경우)에 대해서 불명금발생내역에 등록한다.
	 * 
	 * @param pyEachDpst
	 * @return
	 */
	int insertAmbgOcc(String regrId, String dpstSeqNo);

	/**
	 * 미리 생성된 불명금 내역을 시퀀스를 발급하여 등록한다.
	 * 
	 * @param ambgOcc
	 * @return
	 */
	int insertAmbgOcc(AmbgOcc ambgOcc);

	int updateAmbgCancel(String dpstSeqNo, String chgrId);

	/**
	 * 불명금대체이력및 불명금발생내역를 수정한다.
	 * 
	 * @param dpstSeqNo
	 * @param ambgTransSeqNo
	 * @param payObjAmt
	 * @return
	 */
	int updateAmbg(String dpstSeqNo, String ambgTransSeqNo, double payObjAmt, String chgrId);
	
//	
//	/**
//	 * 미리 생성된 불명금 내역을 시퀀스를 발급하여 등록한다.
//	 * @param ambgOcc
//	 * @return
//	 */
//	int insertAmbgOcc(List<AmbgOcc> ambgOcc);
//	
//	/**
//	 * 불명금 발생내역에 있으면서 불명금 대체내역에 있으면 취소가 되지 않는다.
//	 * 그 경우가 아니라면 불명금 발생내역에서 취소처리
//	 * @param dpstSeqNo
//	 * @return
//	 */
//	int updateAmbgCancel(String dpstSeqNo);
//	
//	
//	/**
//	 * 불명금 조회
//	 * @param ambgOccSeqNo
//	 * @return
//	 */
//	AmbgOcc getAmbgBal(String ambgOccSeqNo);
//	
//	/**
//	 * 보증금 발생 내역에 저장할 불명금 데이터 조회
//	 * @param ambgOccSeqNo
//	 * @return
//	 */
//	AmbgOcc getAmbgForAssr(String ambgOccSeqNo);
//	
//	/**
//	 * 불명금 진행 상태 조회
//	 * @param ambgOccSeqNo
//	 * @return
//	 */
//	String getAmbgProcStat(String ambgOccSeqNo);
//	
//	/**
//	 * 불명금발생내역을 수정한다.
//	 * @param ambgOcc
//	 * @return
//	 */
//	int updateAmbgOccByAmbgOccSeqNo(AmbgOcc ambgOcc);
//	
//	/**
//	 * 불명금대체이력에 등록한다.
//	 * @param ambgTransHistory
//	 * @return
//	 */
//	int insertAmbgTransHistory(AmbgTransHistory ambgTransHistory);
	
}
