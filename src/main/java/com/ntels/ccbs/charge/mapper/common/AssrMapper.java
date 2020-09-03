package com.ntels.ccbs.charge.mapper.common;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.common.AssrOcc;

@Component
public interface AssrMapper {

	/**
	 * 보증금발생내역 테이블에 등록한다.
	 * @param pyAssrOcc
	 * @return
	 */
	int insertAssrOcc(@Param("assrOcc") AssrOcc pyAssrOcc);
	
	/**
	 * 보증금발생내역에 있으면서 보증금대체내역에 있는지 체크
	 * @param dpstTpSeqNo
	 * @return
	 */
	int getAssrCount(@Param("dpstTpSeqNo") String dpstTpSeqNo);
	
	/**
	 * 보증금발생내역(취소여부, 취소일자)을 수정한다.
	 * @param cnclDttm
	 * @param dpstTpSeqNo
	 * @return
	 */
	int updateAssrOccCancel(@Param("cnclDttm") String cnclDttm, @Param("dpstTpSeqNo") String dpstTpSeqNo);
	
	/**
	 * 보증금발생내역(취소여부, 취소일자)을 수정한다.
	 * @param AssrOcc
	 * @return
	 */
	int updateAssrOccCancelByAssrOccSeqNo(@Param("assrOcc") AssrOcc AssrOcc);
	
	/**
	 * 보증금발생내역 조회
	 * @param pymSeqNo
	 * @return
	 */
	List<AssrOcc> getAssrOccByPymSeqNo(@Param("pymSeqNo") String pymSeqNo);
	
	/**
	 * 보증금 대체이력을 취소처리한다.
	 * @param cnclDttm
	 * @param assrTransSeqNo
	 * @return
	 */
	int updateAssrTransHistCancel(@Param("cnclDttm") String cnclDttm, @Param("assrTransSeqNo") String assrTransSeqNo);
	
	/**
	 * assrTransSeqNo값을 가지고 assrOccSeqNo값을 조회한다.
	 * @param assrTransSeqNo
	 * @return
	 */
	String getAssrOccSeqNoByAssrTransSeqNo(@Param("assrTransSeqNo") String assrTransSeqNo);
	
	/**
	 * assrOccSeqNo에 해당하는 보증금 관련 금액들을 조회한다.
	 * @param assrOccSeqNo
	 * @return
	 */
	AssrOcc getAssrAmountByAssrOccSeqNo(@Param("assrOccSeqNo") String assrOccSeqNo);

	/**
	 * 보증금 발생이력의 상태 및 금액을 업데이트한다.
	 * @param payObjAmt
	 * @param chgDate
	 * @param assrOccSeqNo
	 * @return
	 */
	int updateAssrOccStat(@Param("payObjAmt") double payObjAmt, @Param("chgDate") Timestamp chgDate, @Param("assrOccSeqNo") String assrOccSeqNo);
	
}
