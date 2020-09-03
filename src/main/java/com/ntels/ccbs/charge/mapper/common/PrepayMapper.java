package com.ntels.ccbs.charge.mapper.common;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.common.PrepayOcc;

@Component
public interface PrepayMapper {


	int insertPrepayOcc(@Param("prepayOcc") PrepayOcc prepayOcc);
	
	int updatePrepayTransHistoryCancel(@Param("prepayTransSeqNo") String prepayTransSeqNo);


	
	/**
	 * 선수금발생내역에 있으면서 선수금대체내역에 있는지 체크
	 * @param prepayOccTgtSeqNo
	 * @return
	 */
	int getPrepayCount(@Param("prepayOccTgtSeqNo") String prepayOccTgtSeqNo);

	/**
	 * 선수금발생내역(취소여부, 취소일자)을 수정한다.
	 * @param cnclDttm
	 * @param prepayOccTgtSeqNo
	 * @return
	 */
	int updatePrepayOccCancel(@Param("cnclDttm") String cnclDttm, @Param("prepayOccTgtSeqNo") String prepayOccTgtSeqNo);

	/**
	 * prepayTransSeqNo로 prepayOccSeqNo를 조회한다.
	 * @param prepayTransSeqNo
	 * @return
	 */
	String getPrepayOccSeqNoFromPrepayTransHistory(@Param("prepayTransSeqNo") String prepayTransSeqNo);

	/**
	 * TBLPY_PREPAY_OCC테이블에 대해서 선수금대체 금액 및 선수금 잔액과 선수금 대치 완료여부를 'N'으로 업데이트한다.
	 * @param payObjAmt
	 * @param prepayOccSeqNo
	 * @return
	 */
	int updatePrepayTransStat(@Param("payObjAmt") double payObjAmt, @Param("chgDate") Timestamp chgDate, @Param("prepayOccSeqNo") String prepayOccSeqNo);

	/**
	 * 선수금 대체금액 및 선수금 잔액을 조회한다.
	 * @param prepayOccSeqNo
	 * @return
	 */
	PrepayOcc getPrepayAmount(@Param("prepayOccSeqNo") String prepayOccSeqNo);
}
