package com.ntels.ccbs.charge.domain.payment.payment;

import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("NewSinglePaymentVO")
public class NewSinglePaymentVO extends PagingValue {

	private String procDt;      //sysdate
	
	private String orgId;		//조직id
	private String orgNm;		//조직
	private String soId;		//사업자Id
	private String soNm;		//사업자명
	private String pymAcntId;	//납부계정id
	private String pymAcntNm;	//납부계정
	private String usrId;		//사용자id
//안
	private String pymDtTp;
	private String billStrtYymm; //청구시작년월
	private String billEndYymm;	 //청구종료년월
	private String lngTyp;
	private String sidx;
	private String sord;
	
	/* 수납관련 */
	
	private String billDate;		//청구일자
	private String billYymm;		//청구년월
	private String billAmtBfrAdj;   //조정전청구금액
	private String adjAmt;			//조정금액
	private String billAmt;			//청구금액
	private String receiptAmt;		//수납금
	private String unpaidAmt;		//미납금액
	private String paymentDt;		//납기일자
	private String billingCategory; //청구구분
	private String fullPayYn;		//완납여부
	private String smallAmtYn;		//소액여부
	private String receiptProcessingDt;	//수납처리일자
	private String receiptTyp;		//수납구분
	private String receiptTypNm;    
	private String overPaymentAply;	//선수금적용
	private String billSeqNo;       //빌 시퀀스
	
	/* 입금관련 */
	private String dpstAmt;         //입금금액
	private String dpstDt;          //입금일자
	private String dpstCl;          //입금구분 
	private String rcptEmpId;       //영수사원
	private String transDt;         //이체일
	private String cashDpstCl;      //현금입금구분
	private String smry;            //적요
	private String confirmedPayment;//입금처리구분
	private String dpstFeeTp;
	private double feeAmt;
	private String workYymm;
	private String user;
	private String regrId; // 등록자ID
	private Date regDate; // 등록일시
	private String chgrId; // 수정자ID
	private Date chgDate; // 수정일시
	private String eachDpstSeq;
	private String inptMenuId;
	private String custId;
	private String ctrtId;
	private String grpId;
	private String dpstBnkAcntCd;
	private String dpstSeqNo;
	private String wonDpstAmt;
	private String rcptBillEmpId;
	private String crncyCd;
	
	
	private String depositDt;		//입금일자
	private String depositTyp;		//입금형태
	private String depositTypNm;    
	private String depositOption;	//입금구분
	private String depositOptionNm; 
	private String financialInstitution;	//입금기관
	private String depositAmt;		//입금금액
	private String commission;		//수수료
	private String transferDt;		//이체일
	private String received;		//수납처리여부
	private String unknownPayment;	//미확인입금대상
	private String remark;			//적요
	private String depositSeqNo;    //입금시퀀스
	
	private String rdoDpstGubn; // 건별, 전체미납 입금 구분 2020.08.18 gwhan

	private double exrate;
	private String exrateAplyDt;
	
	
	public double getExrate() {
		return exrate;
	}
	public void setExrate(double exrate) {
		this.exrate = exrate;
	}
	public String getRdoDpstGubn() {
		return rdoDpstGubn;
	}
	public void setRdoDpstGubn(String rdoDpstGubn) {
		this.rdoDpstGubn = rdoDpstGubn;
	}
	public String getProcDt() {
		return procDt;
	}
	public void setProcDt(String procDt) {
		this.procDt = procDt;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getSoNm() {
		return soNm;
	}
	public void setSoNm(String soNm) {
		this.soNm = soNm;
	}
	public String getPymAcntId() {
		return pymAcntId;
	}
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}
	public String getPymAcntNm() {
		return pymAcntNm;
	}
	public void setPymAcntNm(String pymAcntNm) {
		this.pymAcntNm = pymAcntNm;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getPymDtTp() {
		return pymDtTp;
	}
	public void setPymDtTp(String pymDtTp) {
		this.pymDtTp = pymDtTp;
	}
	public String getBillStrtYymm() {
		return billStrtYymm;
	}
	public void setBillStrtYymm(String billStrtYymm) {
		this.billStrtYymm = billStrtYymm;
	}
	public String getBillEndYymm() {
		return billEndYymm;
	}
	public void setBillEndYymm(String billEndYymm) {
		this.billEndYymm = billEndYymm;
	}
	public String getLngTyp() {
		return lngTyp;
	}
	public void setLngTyp(String lngTyp) {
		this.lngTyp = lngTyp;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getBillYymm() {
		return billYymm;
	}
	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm;
	}
	public String getBillAmtBfrAdj() {
		return billAmtBfrAdj;
	}
	public void setBillAmtBfrAdj(String billAmtBfrAdj) {
		this.billAmtBfrAdj = billAmtBfrAdj;
	}
	public String getAdjAmt() {
		return adjAmt;
	}
	public void setAdjAmt(String adjAmt) {
		this.adjAmt = adjAmt;
	}
	public String getBillAmt() {
		return billAmt;
	}
	public void setBillAmt(String billAmt) {
		this.billAmt = billAmt;
	}
	public String getReceiptAmt() {
		return receiptAmt;
	}
	public void setReceiptAmt(String receiptAmt) {
		this.receiptAmt = receiptAmt;
	}
	public String getUnpaidAmt() {
		return unpaidAmt;
	}
	public void setUnpaidAmt(String unpaidAmt) {
		this.unpaidAmt = unpaidAmt;
	}
	public String getPaymentDt() {
		return paymentDt;
	}
	public void setPaymentDt(String paymentDt) {
		this.paymentDt = paymentDt;
	}
	public String getBillingCategory() {
		return billingCategory;
	}
	public void setBillingCategory(String billingCategory) {
		this.billingCategory = billingCategory;
	}
	public String getFullPayYn() {
		return fullPayYn;
	}
	public void setFullPayYn(String fullPayYn) {
		this.fullPayYn = fullPayYn;
	}
	public String getSmallAmtYn() {
		return smallAmtYn;
	}
	public void setSmallAmtYn(String smallAmtYn) {
		this.smallAmtYn = smallAmtYn;
	}
	public String getReceiptProcessingDt() {
		return receiptProcessingDt;
	}
	public void setReceiptProcessingDt(String receiptProcessingDt) {
		this.receiptProcessingDt = receiptProcessingDt;
	}
	public String getReceiptTyp() {
		return receiptTyp;
	}
	public void setReceiptTyp(String receiptTyp) {
		this.receiptTyp = receiptTyp;
	}
	public String getReceiptTypNm() {
		return receiptTypNm;
	}
	public void setReceiptTypNm(String receiptTypNm) {
		this.receiptTypNm = receiptTypNm;
	}
	public String getOverPaymentAply() {
		return overPaymentAply;
	}
	public void setOverPaymentAply(String overPaymentAply) {
		this.overPaymentAply = overPaymentAply;
	}
	public String getBillSeqNo() {
		return billSeqNo;
	}
	public void setBillSeqNo(String billSeqNo) {
		this.billSeqNo = billSeqNo;
	}
	public String getDpstAmt() {
		return dpstAmt;
	}
	public void setDpstAmt(String dpstAmt) {
		this.dpstAmt = dpstAmt;
	}
	public String getDpstDt() {
		return dpstDt;
	}
	public void setDpstDt(String dpstDt) {
		this.dpstDt = dpstDt;
	}
	public String getDpstCl() {
		return dpstCl;
	}
	public void setDpstCl(String dpstCl) {
		this.dpstCl = dpstCl;
	}
	public String getRcptEmpId() {
		return rcptEmpId;
	}
	public void setRcptEmpId(String rcptEmpId) {
		this.rcptEmpId = rcptEmpId;
	}
	public String getTransDt() {
		return transDt;
	}
	public void setTransDt(String transDt) {
		this.transDt = transDt;
	}
	public String getCashDpstCl() {
		return cashDpstCl;
	}
	public void setCashDpstCl(String cashDpstCl) {
		this.cashDpstCl = cashDpstCl;
	}
	public String getSmry() {
		return smry;
	}
	public void setSmry(String smry) {
		this.smry = smry;
	}
	public String getConfirmedPayment() {
		return confirmedPayment;
	}
	public void setConfirmedPayment(String confirmedPayment) {
		this.confirmedPayment = confirmedPayment;
	}
	public String getDpstFeeTp() {
		return dpstFeeTp;
	}
	public void setDpstFeeTp(String dpstFeeTp) {
		this.dpstFeeTp = dpstFeeTp;
	}
	public double getFeeAmt() {
		return feeAmt;
	}
	public void setFeeAmt(double feeAmt) {
		this.feeAmt = feeAmt;
	}
	public String getWorkYymm() {
		return workYymm;
	}
	public void setWorkYymm(String workYymm) {
		this.workYymm = workYymm;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getRegrId() {
		return regrId;
	}
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getChgrId() {
		return chgrId;
	}
	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}
	public Date getChgDate() {
		return chgDate;
	}
	public void setChgDate(Date chgDate) {
		this.chgDate = chgDate;
	}
	public String getEachDpstSeq() {
		return eachDpstSeq;
	}
	public void setEachDpstSeq(String eachDpstSeq) {
		this.eachDpstSeq = eachDpstSeq;
	}
	public String getInptMenuId() {
		return inptMenuId;
	}
	public void setInptMenuId(String inptMenuId) {
		this.inptMenuId = inptMenuId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCtrtId() {
		return ctrtId;
	}
	public void setCtrtId(String ctrtId) {
		this.ctrtId = ctrtId;
	}
	public String getGrpId() {
		return grpId;
	}
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}
	public String getDpstBnkAcntCd() {
		return dpstBnkAcntCd;
	}
	public void setDpstBnkAcntCd(String dpstBnkAcntCd) {
		this.dpstBnkAcntCd = dpstBnkAcntCd;
	}
	public String getDpstSeqNo() {
		return dpstSeqNo;
	}
	public void setDpstSeqNo(String dpstSeqNo) {
		this.dpstSeqNo = dpstSeqNo;
	}
	public String getWonDpstAmt() {
		return wonDpstAmt;
	}
	public void setWonDpstAmt(String wonDpstAmt) {
		this.wonDpstAmt = wonDpstAmt;
	}
	public String getRcptBillEmpId() {
		return rcptBillEmpId;
	}
	public void setRcptBillEmpId(String rcptBillEmpId) {
		this.rcptBillEmpId = rcptBillEmpId;
	}
	public String getCrncyCd() {
		return crncyCd;
	}
	public void setCrncyCd(String crncyCd) {
		this.crncyCd = crncyCd;
	}
	public String getExrateAplyDt() {
		return exrateAplyDt;
	}
	public void setExrateAplyDt(String exrateAplyDt) {
		this.exrateAplyDt = exrateAplyDt;
	}
	public String getDepositDt() {
		return depositDt;
	}
	public void setDepositDt(String depositDt) {
		this.depositDt = depositDt;
	}
	public String getDepositTyp() {
		return depositTyp;
	}
	public void setDepositTyp(String depositTyp) {
		this.depositTyp = depositTyp;
	}
	public String getDepositTypNm() {
		return depositTypNm;
	}
	public void setDepositTypNm(String depositTypNm) {
		this.depositTypNm = depositTypNm;
	}
	public String getDepositOption() {
		return depositOption;
	}
	public void setDepositOption(String depositOption) {
		this.depositOption = depositOption;
	}
	public String getDepositOptionNm() {
		return depositOptionNm;
	}
	public void setDepositOptionNm(String depositOptionNm) {
		this.depositOptionNm = depositOptionNm;
	}
	public String getFinancialInstitution() {
		return financialInstitution;
	}
	public void setFinancialInstitution(String financialInstitution) {
		this.financialInstitution = financialInstitution;
	}
	public String getDepositAmt() {
		return depositAmt;
	}
	public void setDepositAmt(String depositAmt) {
		this.depositAmt = depositAmt;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getTransferDt() {
		return transferDt;
	}
	public void setTransferDt(String transferDt) {
		this.transferDt = transferDt;
	}
	public String getReceived() {
		return received;
	}
	public void setReceived(String received) {
		this.received = received;
	}
	public String getUnknownPayment() {
		return unknownPayment;
	}
	public void setUnknownPayment(String unknownPayment) {
		this.unknownPayment = unknownPayment;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDepositSeqNo() {
		return depositSeqNo;
	}
	public void setDepositSeqNo(String depositSeqNo) {
		this.depositSeqNo = depositSeqNo;
	}
	

	
//	// 화면 권한 (여신 + 조직관련)
//	private String deptCd;
//	private String loanAvlAmt;
//	private String loanGvFlg;

	
	
	
	
	
}
