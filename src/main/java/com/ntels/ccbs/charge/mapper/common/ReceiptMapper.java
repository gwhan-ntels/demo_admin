package com.ntels.ccbs.charge.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.charge.domain.common.Receipt;
import com.ntels.ccbs.charge.domain.common.ReceiptCancel;
import com.ntels.ccbs.charge.domain.common.ReceiptCancelAppl;
import com.ntels.ccbs.charge.domain.common.ReceiptDetail;

public interface ReceiptMapper {

	String getCancelYn(@Param("pymSeqNo") String pymSeqNo);

	List<ReceiptDetail> getReceiptDetailList(@Param("dpstSeqNo") String dpstSeqNo, @Param("pymSeqNo") String pymSeqNo);

	int updateReceiptCancel(@Param("dpstSeqNo") String dpstSeqNo, @Param("pymSeqNo") String pymSeqNo, @Param("userId") String userId);

	Receipt getReceipt(@Param("pymSeqNo") String pymSeqNo);

	int insertReceiptCancelAppl(@Param("receiptCancelAppl") ReceiptCancelAppl receiptCancelAppl);

	int insertReceiptCancel(@Param("receiptCancel") ReceiptCancel receiptCancel);	

	List<ReceiptDetail> getReceiptDetail(String pymSeqNo);

	int insertReceiptCancelDetail(@Param("receiptDetail") ReceiptDetail receiptDetail);
	

	int insertReceipt(@Param("receipt") Receipt receipt);

	int insertReceiptDetail(@Param("receiptDetail") ReceiptDetail receiptDetail);
}