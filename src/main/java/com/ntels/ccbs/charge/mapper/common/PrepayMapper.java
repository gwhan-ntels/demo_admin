package com.ntels.ccbs.charge.mapper.common;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.common.PrepayOcc;
import com.ntels.ccbs.charge.domain.common.PrepayTransHistory;

@Component
public interface PrepayMapper {


	int insertPrepayOcc(@Param("prepayOcc") PrepayOcc prepayOcc);
	
	int updatePrepayTransHistoryCancel(@Param("prepayTransSeqNo") String prepayTransSeqNo, @Param("chgrId") String chgrId, @Param("chgDate") Timestamp chgDate);


	
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
	int updatePrepayOccCancel(@Param("cnclDttm") String cnclDttm, @Param("prepayOccTgtSeqNo") String prepayOccTgtSeqNo, @Param("chgrId") String chgrId, @Param("chgDate") Timestamp chgDate);

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
	int updatePrepayTransStat(@Param("payObjAmt") double payObjAmt, @Param("prepayOccSeqNo") String prepayOccSeqNo, @Param("chgrId") String chgrId, @Param("chgDate") Timestamp chgDate);

	/**
	 * 선수금 대체금액 및 선수금 잔액을 조회한다.
	 * @param prepayOccSeqNo
	 * @return
	 */
	PrepayOcc getPrepayAmount(@Param("prepayOccSeqNo") String prepayOccSeqNo);

	List<String> getBillSeqNoCheck(@Param("billSeqNo") String billSeqNo);

	/**
	 * 선수금 발생이력 조회
	 * @param prepayOccSeqNo
	 * @return
	 */
	PrepayOcc getPrepayOcc(@Param("prepayOccSeqNo") String prepayOccSeqNo);
	
	/**
	 * 선수금 진행 상태 조회
	 * @param prepayOccSeqNo
	 * @return
	 */
	String getPrepayProcStat(@Param("prepayOccSeqNo") String prepayOccSeqNo);
	

	/**
	 * 선수금발생내역을 수정한다.
	 * @param prepayOcc
	 * @return
	 */
	int updatePrepayOcc(@Param("prepayOcc") PrepayOcc prepayOcc);
	

	/**
	 * 선수금대체이력에 등록한다.
	 * @param prepayTransHistory
	 * @return
	 */
	int insertPrepayTransHistory(@Param("prepayTransHistory") PrepayTransHistory prepayTransHistory);
	
}
