package com.ntels.ccbs.charge.service.common;

import com.ntels.ccbs.charge.domain.common.AssrOcc;
	
public interface AssrService {
	
	/**
	 * 보증금발생내역를 수정한다.
	 * @param seqNo
	 * @return
	 */
	int updateAssrOccCancel(String seqNo, String chgrId);

//	/**
//	 * 보증금발생내역 테이블에 등록한다.
//	 * @param assrOcc
//	 * @return
//	 */
//	int insertAssrOcc(AssrOcc assrOcc);
//	
//
//	
//	/**
//	 * 보증금발생내역을 취소한다.
//	 * @param pymSeqNo
//	 * @return
//	 */
//	int updateAssrOccCancelByPymSeqNu(String pymSeqNo);
//
//	/**
//	 * 보증금대체이력및 불명금발생내역를 수정한다.
//	 * @param pymSeqNo
//	 * @param assrTransSeqNo
//	 * @param payObjAmt
//	 * @return
//	 */
//	int updateAssrOcc(String pymSeqNo, String assrTransSeqNo, double payObjAmt);
//	
//	/**
//	 * 보증금 발생 대상인지 체크
//	 * @param chrgItmCd
//	 * @return
//	 */
//	boolean isAssrOccTarget(String chrgItmCd);
	
}
