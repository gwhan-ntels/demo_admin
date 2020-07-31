package com.ntels.ccbs.charge.domain.charge.calculationSearch;

public class ChargCalculationResultVO {

	private String soId;
	private String soNm;
	private String prodNm;
	private String custCnt;
	private String ctrtCnt;
	private String prodCmpsCnt;
	private String svcCmpsCnt;
	private String useAmt;
	private String useQty;
	private String useCnt;
	
	private String billYymm;
	private String condSoId;
	
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
	public String getProdNm() {
		return prodNm;
	}
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}
	public String getCustCnt() {
		return custCnt;
	}
	public void setCustCnt(String custCnt) {
		this.custCnt = custCnt;
	}
	public String getCtrtCnt() {
		return ctrtCnt;
	}
	public void setCtrtCnt(String ctrtCnt) {
		this.ctrtCnt = ctrtCnt;
	}
	public String getProdCmpsCnt() {
		return prodCmpsCnt;
	}
	public void setProdCmpsCnt(String prodCmpsCnt) {
		this.prodCmpsCnt = prodCmpsCnt;
	}
	public String getSvcCmpsCnt() {
		return svcCmpsCnt;
	}
	public void setSvcCmpsCnt(String svcCmpsCnt) {
		this.svcCmpsCnt = svcCmpsCnt;
	}
	public String getUseAmt() {
		return useAmt;
	}
	public void setUseAmt(String useAmt) {
		this.useAmt = useAmt;
	}
	public String getUseQty() {
		return useQty;
	}
	public void setUseQty(String useQty) {
		this.useQty = useQty;
	}
	public String getUseCnt() {
		return useCnt;
	}
	public void setUseCnt(String useCnt) {
		this.useCnt = useCnt;
	}
	public String getBillYymm() {
		return billYymm;
	}
	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm;
	}
	public String getCondSoId() {
		return condSoId;
	}
	public void setCondSoId(String condSoId) {
		this.condSoId = condSoId;
	}
	@Override
	public String toString() {
		return "ChargCalculationResultVO [soId=" + soId + ", soNm=" + soNm + ", prodNm=" + prodNm + ", custCnt="
				+ custCnt + ", ctrtCnt=" + ctrtCnt + ", prodCmpsCnt=" + prodCmpsCnt + ", svcCmpsCnt=" + svcCmpsCnt
				+ ", useAmt=" + useAmt + ", useQty=" + useQty + ", useCnt=" + useCnt + "]";
	}
	
	
}
