package com.ntels.ccbs.charge.domain.common;

import java.util.ArrayList;
import java.util.List;

public class PaymentResult {

	private String billSeqNo;
	
	private String pymSeqNo;
	
	private String fullPayYn;
	
	private String pymAcntId;
	
	private CBillComm updateBillMast;
	
	private List<CBillComm> updateBillList;
	
	private List<ReceiptDetail> receiptDetailList;
	
	private List<Receipt> receiptList;
	
	private Deposit deposit;
	
	private AmbgOcc ambgOcc;
	
	private PrepayOcc prepayOcc;
	
	private double remainAmt;

	/**
	 * @return the billSeqNo
	 */
	public String getBillSeqNo() {
		return billSeqNo;
	}

	/**
	 * @param billSeqNo the billSeqNo to set
	 */
	public void setBillSeqNo(String billSeqNo) {
		this.billSeqNo = billSeqNo;
	}

	/**
	 * @return the pymSeqNo
	 */
	public String getPymSeqNo() {
		return pymSeqNo;
	}

	/**
	 * @param pymSeqNo the pymSeqNo to set
	 */
	public void setPymSeqNo(String pymSeqNo) {
		this.pymSeqNo = pymSeqNo;
	}

	/**
	 * @return the fullPayYn
	 */
	public String getFullPayYn() {
		return fullPayYn;
	}

	/**
	 * @param fullPayYn the fullPayYn to set
	 */
	public void setFullPayYn(String fullPayYn) {
		this.fullPayYn = fullPayYn;
	}

	/**
	 * @return the pymAcntId
	 */
	public String getPymAcntId() {
		return pymAcntId;
	}

	/**
	 * @param pymAcntId the pymAcntId to set
	 */
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}

	/**
	 * @return the updateBillMast
	 */
	public CBillComm getUpdateBillMast() {
		return updateBillMast;
	}

	/**
	 * @param updateBillMast the updateBillMast to set
	 */
	public void setUpdateBillMast(CBillComm updateBillMast) {
		this.updateBillMast = updateBillMast;
	}

	/**
	 * @return the updateBillList
	 */
	public List<CBillComm> getUpdateBillList() {
		return updateBillList;
	}

	/**
	 * @param updateBillList the updateBillList to set
	 */
	public void addUpdateBill(CBillComm updateBill) {
		if (updateBillList == null) {
			updateBillList = new ArrayList<>();
		}
		
		updateBillList.add(updateBill);
	}

	/**
	 * @return the receiptDetailList
	 */
	public List<ReceiptDetail> getReceiptDetailList() {
		return receiptDetailList;
	}

	/**
	 * @param receiptDetailList the receiptDetailList to set
	 */
	public void addReceiptDetail(ReceiptDetail receiptDetail) {
		if (receiptDetailList == null) {
			receiptDetailList = new ArrayList<>();
		}
		
		receiptDetailList.add(receiptDetail);
	}

	/**
	 * @return the receiptList
	 */
	public List<Receipt> getReceiptList() {
		return receiptList;
	}

	/**
	 * @param receiptList the receiptList to set
	 */
	public void addReceipt(Receipt receipt) {
		if (receiptList == null) {
			receiptList = new ArrayList<>();
		}
		
		receiptList.add(receipt);
	}

	/**
	 * @return the deposit
	 */
	public Deposit getDeposit() {
		return deposit;
	}

	/**
	 * @param deposit the deposit to set
	 */
	public void setDeposit(Deposit deposit) {
		this.deposit = deposit;
	}

	/**
	 * @return the ambgOcc
	 */
	public AmbgOcc getAmbgOcc() {
		return ambgOcc;
	}

	/**
	 * @param ambgOcc the ambgOcc to set
	 */
	public void setAmbgOcc(AmbgOcc ambgOcc) {
		this.ambgOcc = ambgOcc;
	}

	/**
	 * @return the prepayOcc
	 */
	public PrepayOcc getPrepayOcc() {
		return prepayOcc;
	}

	/**
	 * @param prepayOcc the prepayOcc to set
	 */
	public void setPrepayOcc(PrepayOcc prepayOcc) {
		this.prepayOcc = prepayOcc;
	}

	/**
	 * @return the remainAmt
	 */
	public double getRemainAmt() {
		return remainAmt;
	}

	/**
	 * @param remainAmt the remainAmt to set
	 */
	public void setRemainAmt(double remainAmt) {
		this.remainAmt = remainAmt;
	}
	
	
	
}
