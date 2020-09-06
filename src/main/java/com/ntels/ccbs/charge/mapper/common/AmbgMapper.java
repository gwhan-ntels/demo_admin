package com.ntels.ccbs.charge.mapper.common;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.common.AmbgOcc;

@Component
public interface AmbgMapper {

	/**
	 * 불명금(납부계정이 없는경우)에 대해서 불명금발생내역을 조회한다.
	 * @param dpstSeqNo
	 * @return
	 */
	AmbgOcc getAmbgOcc(@Param("dpstSeqNo") String dpstSeqNo);
	
	/**
	 * 불명금(납부계정이 없는경우)에 대해서 불명금발생내역에 등록한다.
	 * @param pyAmbgOcc
	 * @return
	 */
	int insertAmbgOcc(@Param("pyAmbgOcc") AmbgOcc pyAmbgOcc);
	
	/**
	 * 불명금발생내역에 있으면서 불명금대체내역에 있는가.. 조회
	 * @param dpstTpSeqNo
	 * @return
	 */
	int getAmbgCount(@Param("dpstTpSeqNo") String dpstTpSeqNo);
	
	/**
	 * 불명금 발생내역을 취소한다.
	 * @param cnclDttm
	 * @param dpstTpSeqNo
	 * @return
	 */
	int updateAmbgCancel(@Param("cnclDttm") String cnclDttm, @Param("dpstTpSeqNo") String dpstTpSeqNo, @Param("chgrId") String chgrId, @Param("chgDate") Timestamp chgDate);
	
	/**
	 * 불명금 대체내역을 취소처리한다.
	 * @param cnclDttm
	 * @param ambgTransSeqNo
	 * @return
	 */
	int updateAmbgTransHistCancel(@Param("cnclDttm") String cnclDttm, @Param("ambgTransSeqNo") String ambgTransSeqNo, @Param("chgrId") String chgrId, @Param("chgDate") Timestamp chgDate);

	/**
	 * 불명금 발생이력의 상태 및 금액을 업데이트한다.
	 * @param payObjAmt
	 * @param chgDate
	 * @param dpstTpSeqNo
	 * @return
	 */
	int updateAmbgOccStat(@Param("payObjAmt") double payObjAmt, @Param("dpstTpSeqNo") String dpstTpSeqNo, @Param("chgrId") String chgrId, @Param("chgDate") Timestamp chgDate);

	/**
	 * 불명금 관련 금액들을 조회한다.
	 * @param dpstTpSeqNo
	 * @return
	 */
	AmbgOcc getAmbgAmount(@Param("dpstTpSeqNo") String dpstTpSeqNo);
//	
//	/**
//	 * 불명금 조회
//	 * @param ambgOccSeqNo
//	 * @return
//	 */
//	AmbgOcc getAmbgBal(@Param("ambgOccSeqNo") String ambgOccSeqNo);
//	
//	/**
//	 * 보증금 발생 내역에 저장할 불명금 데이터 조회
//	 * @param ambgOccSeqNo
//	 * @return
//	 */
//	AmbgOcc getAmbgForAssr(@Param("ambgOccSeqNo") String ambgOccSeqNo);
//	
//	/**
//	 * 불명금 진행 상태 조회
//	 * @param ambgOccSeqNo
//	 * @return
//	 */
//	String getAmbgProcStat(@Param("ambgOccSeqNo") String ambgOccSeqNo);
//	
//	/**
//	 * 불명금발생내역을 수정한다.
//	 * @param ambgOcc
//	 * @return
//	 */
//	int updateAmbgOccByAmbgOccSeqNo(@Param("ambgOcc") AmbgOcc ambgOcc);
//	
//	/**
//	 * 불명금대체이력에 등록한다.
//	 * @param ambgTransHistory
//	 * @return
//	 */
//	int insertAmbgTransHistory(@Param("ambgTransHistory") AmbgTransHistory ambgTransHistory);
	
}
