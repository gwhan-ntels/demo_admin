package com.ntels.ccbs.charge.domain.common;

import java.sql.Timestamp;

public class Bill {

	private String soId;
	private String billSeqNo;
	private String useYymm;
	private String svcCmpsId;
	private String prodCmpsId;
	private String chrgItmCd;
	private String billYymm;
	private String billCycl;
	private String billDt;
	private String grpId;
	private String ctrtId;
	private String custId;
	private String pymAcntId;
	private double billAmt;
	private double rcptAmt;
	private String lastRcptDt;
	private String fullPayYn;
	
	/**
	 * 입금적용순서1-마이너스 금액 먼저
	 */
	private int	aplyOrder1;

	/**
	 * 입금적용순서2-부가세 먼저
	 */
	private int	aplyOrder2;
	
	private String regrId;
	private Timestamp regDate;
	private String chgrId;	
	private Timestamp chgDate;
	private String pymSeqNo;
	private String invItmCd;
	private String rcptVal;
	private String rateItmTypCd;
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getBillSeqNo() {
		return billSeqNo;
	}
	public void setBillSeqNo(String billSeqNo) {
		this.billSeqNo = billSeqNo;
	}
	public String getUseYymm() {
		return useYymm;
	}
	public void setUseYymm(String useYymm) {
		this.useYymm = useYymm;
	}
	public String getSvcCmpsId() {
		return svcCmpsId;
	}
	public void setSvcCmpsId(String svcCmpsId) {
		this.svcCmpsId = svcCmpsId;
	}
	public String getProdCmpsId() {
		return prodCmpsId;
	}
	public void setProdCmpsId(String prodCmpsId) {
		this.prodCmpsId = prodCmpsId;
	}
	public String getChrgItmCd() {
		return chrgItmCd;
	}
	public void setChrgItmCd(String chrgItmCd) {
		this.chrgItmCd = chrgItmCd;
	}
	public String getBillYymm() {
		return billYymm;
	}
	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm;
	}
	public String getBillCycl() {
		return billCycl;
	}
	public void setBillCycl(String billCycl) {
		this.billCycl = billCycl;
	}
	public String getBillDt() {
		return billDt;
	}
	public void setBillDt(String billDt) {
		this.billDt = billDt;
	}
	public String getGrpId() {
		return grpId;
	}
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}
	public String getCtrtId() {
		return ctrtId;
	}
	public void setCtrtId(String ctrtId) {
		this.ctrtId = ctrtId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getPymAcntId() {
		return pymAcntId;
	}
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}
	public double getBillAmt() {
		return billAmt;
	}
	public void setBillAmt(double billAmt) {
		this.billAmt = billAmt;
	}
	public double getRcptAmt() {
		return rcptAmt;
	}
	public void setRcptAmt(double rcptAmt) {
		this.rcptAmt = rcptAmt;
	}
	public String getLastRcptDt() {
		return lastRcptDt;
	}
	public void setLastRcptDt(String lastRcptDt) {
		this.lastRcptDt = lastRcptDt;
	}
	public String getFullPayYn() {
		return fullPayYn;
	}
	public void setFullPayYn(String fullPayYn) {
		this.fullPayYn = fullPayYn;
	}
	public int getAplyOrder1() {
		return aplyOrder1;
	}
	public void setAplyOrder1(int aplyOrder1) {
		this.aplyOrder1 = aplyOrder1;
	}
	public int getAplyOrder2() {
		return aplyOrder2;
	}
	public void setAplyOrder2(int aplyOrder2) {
		this.aplyOrder2 = aplyOrder2;
	}
	public String getRegrId() {
		return regrId;
	}
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public String getChgrId() {
		return chgrId;
	}
	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}
	public Timestamp getChgDate() {
		return chgDate;
	}
	public void setChgDate(Timestamp chgDate) {
		this.chgDate = chgDate;
	}
	public String getPymSeqNo() {
		return pymSeqNo;
	}
	public void setPymSeqNo(String pymSeqNo) {
		this.pymSeqNo = pymSeqNo;
	}
	public String getInvItmCd() {
		return invItmCd;
	}
	public void setInvItmCd(String invItmCd) {
		this.invItmCd = invItmCd;
	}
	public String getRcptVal() {
		return rcptVal;
	}
	public void setRcptVal(String rcptVal) {
		this.rcptVal = rcptVal;
	}
	public String getRateItmTypCd() {
		return rateItmTypCd;
	}
	public void setRateItmTypCd(String rateItmTypCd) {
		this.rateItmTypCd = rateItmTypCd;
	}
	
	
}