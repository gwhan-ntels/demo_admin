package com.ntels.ccbs.charge.service.common;

import java.util.List;

import com.ntels.ccbs.charge.domain.common.Bill;
import com.ntels.ccbs.charge.domain.common.CBillComm;
import com.ntels.ccbs.charge.domain.common.Deposit;
import com.ntels.ccbs.charge.domain.common.PaymentResult;
import com.ntels.ccbs.charge.domain.common.Receipt;

public interface PaymentService_1 {


	
	Double getBillRcptAmt(String billSeqNo, String useYymm, String prodCmpsId, String svcCmpsId, String chrgItmCd);

	int updateBillCancel(CBillComm bill);
	

	int updateBillMastCancel(CBillComm bill);
	
	List<Bill> getBillSeqNo(String soId, String pymAcntId);
	

	int updateDpstProc(Deposit deposit);
	

	public interface ProcessPaymentCallback {
		
		Receipt getReceipt();
		
	}

	/**
	 * 수납 처리를 진행한다.
	 * @param billSeqNo
	 * @param pymSeqNo
	 * @param dpstSeqNo
	 * @param dpstAmt
	 * @return
	 */
	PaymentResult processPayment(String billSeqNo, String pymSeqNo, double dpstAmt, ProcessPaymentCallback callback);
	

	int updateBillMastRcptAmt(List<CBillComm> billList);
	

	int updateFullPayBill(CBillComm bill);

	int updateBillRcptAmt(CBillComm bill);
	

//	int getBillSeqNo(String pymAcntId);
//
//	List<Bill> getBillDataList(String pymAcntId, String billSeqNo, String ctrtId);
}