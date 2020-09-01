package com.ntels.ccbs.charge.service.common;

import java.util.List;

import com.ntels.ccbs.charge.domain.common.Receipt;
import com.ntels.ccbs.charge.domain.common.ReceiptDetail;

public interface ReceiptService {

	String getCancelYn(String pymSeqNo);

	List<ReceiptDetail> getReceiptDetailList(String dpstSeqNo, String pymSeqNo);
	
	int updateReceiptCancel(String dpstSeqNo, String pymSeqNo, String userId);
	
	// 선수금발생 내역을 저장하기 위해 납부내역을 조회한다.
	Receipt getReceipt(String pymSeqNo);
	
	// 수납취소결재신청에 등록한다.
	int insertReceiptCancelAppl(String pymSeqNo, String regrId);
	
	// 수납취소내역과 수납취소 상세상세내역 등록한다. 
	int insertReceiptCancel(String cnclResn, String pymSeqNo, String receiptId, String regrId);
	
	// 입금내역에 등록된 데이타를 수납내역에 등록한다.
	int insertReceipt(List<Receipt> receiptList);
	
	// 청구내역에 수정된 데이타를 수납상세내역에 등록한다.
	int insertReceiptDetail(List<ReceiptDetail> receiptDetailList);
}