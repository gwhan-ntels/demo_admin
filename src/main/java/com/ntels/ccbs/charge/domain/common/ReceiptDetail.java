package com.ntels.ccbs.charge.domain.common;

import java.sql.Timestamp;

public class ReceiptDetail {

	/**
	 * 수납일련번호
	 */
	private String pymSeqNo;

	/**
	 * 사용년월(YYYYMM)
	 */
	private String useYymm;

	/**
	 * 상품구성ID
	 */
	private String prodCmpsId;

	/**
	 * 서비스구성ID
	 */
	private String svcCmpsId;

	/**
	 * 청구Process단위에서 관리되는 항목으로 약관에서 정의하는 기준의 요금을 관리 Sequence : CI99999999
	 */
	private String chrgItmCd;

	/**
	 * SO_ID or RO_ID
	 */
	private String soId;

	/**
	 * 단체ID
	 */
	private String grpId;

	/**
	 * 고객ID
	 */
	private String custId;

	/**
	 * 계약ID
	 */
	private String ctrtId;

	/**
	 * 청구금액
	 */
	private Double billAmt;

	/**
	 * 수납금액
	 */
	private Double rcptAmt;

	/**
	 * BLIV023 DEM: Germany (Mark)-Deutch Mark EUR: Europe (Euro) GBP: Great
	 * Britain (Pound) HKD: Hong Kong (Dollar) JPY: Japan (Yen) KRW: South Korea
	 * (Won) RMB: China (Renminbi)-CNY SDR: Special Drawing Rights SGD:
	 * Singapore (Dollar) USD: United States (Dollar)
	 */
	private String crncyCd;

	/**
	 * 환율
	 */
	private Double exrate;

	/**
	 * 환불적용일자(YYYYMMDD)
	 */
	private String exrateAplyDt;

	/**
	 * 등록일
	 */
	private Timestamp regDate;

	/**
	 * 이전수납금액
	 */
	private Double preRcptAmt;

	/**
	 * 이전지점ID
	 */
	private String preSoId;

	private String dpstSeqNo;
	public String getDpstSeqNo() {
		return dpstSeqNo;
	}

	public void setDpstSeqNo(String dpstSeqNo) {
		this.dpstSeqNo = dpstSeqNo;
	}
	
	/**
	 * 해당 청구월, 청구주기에 청구대상이 되는 납부계정 정보에 대한 청구서 번호(20자리) 청구년월(4) + 주기(2) +
	 * 청구일자DD(2) + 납부계정ID(10) + 일련번호(2)
	 */
	private String billSeqNo;
	
	private String pymAcntId;
	
	private double minusReplcAmt;

	public String getPymSeqNo() {
		return pymSeqNo;
	}

	public void setPymSeqNo(String pymSeqNo) {
		this.pymSeqNo = pymSeqNo;
	}

	public String getUseYymm() {
		return useYymm;
	}

	public void setUseYymm(String useYymm) {
		this.useYymm = useYymm;
	}

	public String getProdCmpsId() {
		return prodCmpsId;
	}

	public void setProdCmpsId(String prodCmpsId) {
		this.prodCmpsId = prodCmpsId;
	}

	public String getSvcCmpsId() {
		return svcCmpsId;
	}

	public void setSvcCmpsId(String svcCmpsId) {
		this.svcCmpsId = svcCmpsId;
	}

	public String getChrgItmCd() {
		return chrgItmCd;
	}

	public void setChrgItmCd(String chrgItmCd) {
		this.chrgItmCd = chrgItmCd;
	}

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

	public String getGrpId() {
		return grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
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

	public Double getBillAmt() {
		return billAmt;
	}

	public void setBillAmt(double billAmt) {
		this.billAmt = billAmt;
	}

	public Double getRcptAmt() {
		return rcptAmt;
	}

	public void setRcptAmt(double rcptAmt) {
		this.rcptAmt = rcptAmt;
	}

	public String getCrncyCd() {
		return crncyCd;
	}

	public void setCrncyCd(String crncyCd) {
		this.crncyCd = crncyCd;
	}

	public Double getExrate() {
		return exrate;
	}

	public void setExrate(double exrate) {
		this.exrate = exrate;
	}

	public String getExrateAplyDt() {
		return exrateAplyDt;
	}

	public void setExrateAplyDt(String exrateAplyDt) {
		this.exrateAplyDt = exrateAplyDt;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public Double getPreRcptAmt() {
		return preRcptAmt;
	}

	public void setPreRcptAmt(double preRcptAmt) {
		this.preRcptAmt = preRcptAmt;
	}

	public String getPreSoId() {
		return preSoId;
	}

	public void setPreSoId(String preSoId) {
		this.preSoId = preSoId;
	}

	public String getBillSeqNo() {
		return billSeqNo;
	}

	public void setBillSeqNo(String billSeqNo) {
		this.billSeqNo = billSeqNo;
	}

	public String getPymAcntId() {
		return pymAcntId;
	}

	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}

	public double getMinusReplcAmt() {
		return minusReplcAmt;
	}

	public void setMinusReplcAmt(double minusReplcAmt) {
		this.minusReplcAmt = minusReplcAmt;
	}

	public void setBillAmt(Double billAmt) {
		this.billAmt = billAmt;
	}

	public void setRcptAmt(Double rcptAmt) {
		this.rcptAmt = rcptAmt;
	}

	public void setExrate(Double exrate) {
		this.exrate = exrate;
	}

	public void setPreRcptAmt(Double preRcptAmt) {
		this.preRcptAmt = preRcptAmt;
	}

}
