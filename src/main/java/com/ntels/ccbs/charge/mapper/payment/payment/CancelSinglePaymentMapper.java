package com.ntels.ccbs.charge.mapper.payment.payment;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.common.CBillComm;
import com.ntels.ccbs.charge.domain.common.Receipt;
import com.ntels.ccbs.charge.domain.common.ReceiptDetail;
import com.ntels.ccbs.charge.domain.payment.payment.CancelSinglePaymentVO;

@Component
public interface CancelSinglePaymentMapper {

	int getCaseByCancelListCount(@Param(value = "cancelSinglePaymentVO") CancelSinglePaymentVO cancelSinglePaymentVO);

	List<CancelSinglePaymentVO> getCaseByCancelList(@Param(value = "cancelSinglePaymentVO") CancelSinglePaymentVO cancelSinglePaymentVO);
	

	Double getTransCheckAmt(@Param("dpstSeqNo") String dpstSeqNo);
	
	int getReceiptCheckCnt(@Param("dpstSeqNo") String dpstSeqNo);


	CancelSinglePaymentVO getDepositForCancel(@Param("dpstSeqNo") String dpstSeqNo);
	
	int updateCnclYn(@Param(value = "cancelSinglePaymentVO") CancelSinglePaymentVO cancelSinglePaymentVO);
	
	CancelSinglePaymentVO getDepositCancelInfo(@Param("dpstSeqNo") String dpstSeqNo);	

	int insertDepositCancel(@Param("cancelSinglePaymentVO") CancelSinglePaymentVO cancelSinglePaymentVO);

	List<ReceiptDetail> getReceiptDetailList(@Param("dpstSeqNo") String dpstSeqNo, @Param("pymSeqNo") String pymSeqNo);

	int updateBillCancel(CBillComm bill);

	List<Receipt> getReceiptBillInfo(@Param("dpstSeqNo") String dpstSeqNo);
	
	int updateBillMastCancel(CBillComm bill);
	
	int updateRcptCancel(@Param("dpstSeqNo") String dpstSeqNo, @Param("chgrId") String chgrId);	

	int getPrepayTransCount(@Param("prepayOccTgtSeqNo") String prepayOccTgtSeqNo);
	
	int updatePrepayOccCancel(@Param("cnclDttm") String cnclDttm, @Param("prepayOccTgtSeqNo") String prepayOccTgtSeqNo, @Param("chgrId") String chgrId);
	
	
	
	
	
//	List<CancelSinglePaymentVO> getPermitOrg(
//			@Param(value ="cancelSinglePaymentVO") CancelSinglePaymentVO cancelSinglePaymentVO
//	);
//	
//	int getPermitOrgCount(
//			@Param(value ="cancelSinglePaymentVO") CancelSinglePaymentVO cancelSinglePaymentVO
//	);
//	
//	List<CancelSinglePaymentVO> getLoanAvlAmt(
//			@Param(value ="cancelSinglePaymentVO") CancelSinglePaymentVO cancelSinglePaymentVO
//	);
//	
//	int getLoanAvlAmtCount(
//			@Param(value ="cancelSinglePaymentVO") CancelSinglePaymentVO cancelSinglePaymentVO
//	);
//	
//	List<CancelSinglePaymentVO> getRcptCnclCnt(	
//			@Param(value ="cancelSinglePaymentVO") CancelSinglePaymentVO cancelSinglePaymentVO
//	);
//	
//	List<Map<String,Object>> getCaseByCancelListExcel(
//			@Param(value ="cancelSinglePaymentVO") CancelSinglePaymentVO cancelSinglePaymentVO,
//			@Param(value="lngTyp") String lngTyp
//	);
}
