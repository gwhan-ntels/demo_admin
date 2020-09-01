package com.ntels.ccbs.charge.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.charge.domain.common.Bill;
import com.ntels.ccbs.charge.domain.common.CBillComm;
import com.ntels.ccbs.charge.domain.common.Deposit;

public interface PaymentMapper_1 {


	Double getBillRcptAmt(CBillComm bill);
	
	int updateBillCancel(CBillComm bill); 
	
	int updateBillMastCancel(CBillComm bill);
	
	List<Bill> getBillSeqNo(@Param("soId") String soId, @Param("pymAcntId") String pymAcntId);
	

	int updateDpstProc(@Param("deposit") Deposit deposit);
	
	Double getUnpayAmtFromBillMast(@Param("billSeqNo") String billSeqNo);
	

	List<CBillComm> getBillListByBillSeqNo(@Param("billSeqNo") String billSeqNo);
	

	int updateBillMastRcptAmt(@Param("bill") CBillComm bill);

	
	int updateFullPayBill(@Param("bill") CBillComm bill);
	
	int updateBillRcptAmt(@Param("bill") CBillComm bill);

//	int getPymAcntCnt(@Param("pymAcntId") String pymAcntId);
//	
//	List<Bill> getBillDataList(@Param("pymAcntId") String pymAcntId, @Param("billSeqNo") String billSeqNo, @Param("ctrtId") String ctrtId);
	
//	Double getUnpayAmtFromBillMast(@Param("billSeqNo") String billSeqNo);
//	
//	CBillComm getBillMastInfo(@Param("billSeqNo") String billSeqNo);
//	
//	List<CBillComm> getBillListByBillSeqNo(@Param("billSeqNo") String billSeqNo);
//	
//	int insertRcptDtlSelectBill(@Param("receiptDetail") ReceiptDetail receiptDetail);
//	
//	int insertRcptSelectBill(@Param("receipt") Receipt receipt);
//	
//	int updateBillMastRcptAmt(@Param("bill") CBillComm bill);
//	
//	int updateFullPayBill(@Param("bill") CBillComm bill);
//	
//	int updateBillRcptAmt(@Param("bill") CBillComm bill);
//	
//	int updateDpstProc(@Param("deposit") Deposit deposit);
//	
//	int updateDpstPayProc(@Param("deposit") Deposit deposit);
//	
//	int updatePrepayTgtYn(@Param("deposit") Deposit deposit);
//	
//	int updateAmbgTgtYn(@Param("deposit") Deposit deposit);
//
//	
}