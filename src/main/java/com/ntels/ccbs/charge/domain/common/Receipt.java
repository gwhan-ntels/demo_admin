package com.ntels.ccbs.charge.domain.common;

import java.sql.Timestamp;

public class Receipt {

	/**
	 * 수납일련번호
	 */
	private String pymSeqNo;

	/**
	 * 해당 청구월, 청구주기에 청구대상이 되는 납부계정 정보에 대한 청구서 번호(20자리) 청구년월(4) + 주기(2) +
	 * 청구일자DD(2) + 납부계정ID(10) + 일련번호(2)
	 */
	private String billSeqNo;

	/**
	 * 청구년월(YYYYMM)
	 */
	private String billYymm;

	/**
	 * 청구주기
	 */
	private String billCycl;

	/**
	 * 청구일자(YYYYMMDD)
	 */
	private String billDt;

	/**
	 * 납부계정ID
	 */
	private String pymAcntId;

	/**
	 * 수납처리일자(YYYYMMDD)
	 */
	private String payProcDt;

	/**
	 * 입금처리일자(YYYYMMDD)
	 */
	private String dpstProcDt;

	/**
	 * 고객이 납부한 일자(YYYYMMDD) 1. 지로: 은행납부일자 2. CMS: 통장인출일자 3. 카드: 전표매입일자 4.
	 * 무통장(가상계좌): 입금일자
	 */
	private String dpstDt;

	/**
	 * 입금구분(BLPY001) 01: 지로 02: 자동이체 03: CMS 04: 가상계좌 05: 신용카드
	 */
	private String dpstCl;

	/**
	 * 수납대상금액
	 */
	private double payObjAmt;

	/**
	 * 수납적용액
	 */
	private double payAplyAmt;

	/**
	 * 선수금적용액
	 */
	private double prepayAplyAmt;

	/**
	 * BLPY013 1: 정상수납 2: 선수금수납 3: 상이납입자수납 4: 미확인입금수납
	 */
	private String payTp;

	/**
	 * Table Sequence 번호
	 */
	private String dpstSeqNo;

	/**
	 * Table Sequence 번호
	 */
	private String prepayTransSeqNo;

	/**
	 * Table Sequence 번호
	 */
	private String ambgTransSeqNo;

	/**
	 * Table Sequence 번호
	 */
	private String assrTransSeqNo;

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
	private double exrate;

	/**
	 * 환불적용일자(YYYYMMDD)
	 */
	private String exrateAplyDt;

	/**
	 * 취소여부 N: 미취소(Default) Y: 취소
	 */
	private String cnclYn;

	/**
	 * 등록일
	 */
	private Timestamp regDate;

	/**
	 * 이전수납금액
	 */
	private double preRcptAmt;

	/**
	 * 수납금액
	 */
	private double rcptAmt;

	/**
	 * 통장에 입금된 일자(YYYYMMDD)
	 */
	private String transDt;

	/**
	 * SO_ID or RO_ID
	 */
	private String soId;

	/**
	 * 선납계약ID
	 */
	private String prepdCtrtId;

	/**
	 * 등록자ID
	 */
	private String regrId;

	public String getPymSeqNo() {
		return pymSeqNo;
	}

	public void setPymSeqNo(String pymSeqNo) {
		this.pymSeqNo = pymSeqNo;
	}

	public String getBillSeqNo() {
		return billSeqNo;
	}

	public void setBillSeqNo(String billSeqNo) {
		this.billSeqNo = billSeqNo;
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

	public String getPymAcntId() {
		return pymAcntId;
	}

	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}

	public String getPayProcDt() {
		return payProcDt;
	}

	public void setPayProcDt(String payProcDt) {
		this.payProcDt = payProcDt;
	}

	public String getDpstProcDt() {
		return dpstProcDt;
	}

	public void setDpstProcDt(String dpstProcDt) {
		this.dpstProcDt = dpstProcDt;
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

	public double getPayObjAmt() {
		return payObjAmt;
	}

	public void setPayObjAmt(double payObjAmt) {
		this.payObjAmt = payObjAmt;
	}

	public double getPayAplyAmt() {
		return payAplyAmt;
	}

	public void setPayAplyAmt(double payAplyAmt) {
		this.payAplyAmt = payAplyAmt;
	}

	public double getPrepayAplyAmt() {
		return prepayAplyAmt;
	}

	public void setPrepayAplyAmt(double prepayAplyAmt) {
		this.prepayAplyAmt = prepayAplyAmt;
	}

	public String getPayTp() {
		return payTp;
	}

	public void setPayTp(String payTp) {
		this.payTp = payTp;
	}

	public String getDpstSeqNo() {
		return dpstSeqNo;
	}

	public void setDpstSeqNo(String dpstSeqNo) {
		this.dpstSeqNo = dpstSeqNo;
	}

	public String getPrepayTransSeqNo() {
		return prepayTransSeqNo;
	}

	public void setPrepayTransSeqNo(String prepayTransSeqNo) {
		this.prepayTransSeqNo = prepayTransSeqNo;
	}

	public String getAmbgTransSeqNo() {
		return ambgTransSeqNo;
	}

	public void setAmbgTransSeqNo(String ambgTransSeqNo) {
		this.ambgTransSeqNo = ambgTransSeqNo;
	}

	public String getAssrTransSeqNo() {
		return assrTransSeqNo;
	}

	public void setAssrTransSeqNo(String assrTransSeqNo) {
		this.assrTransSeqNo = assrTransSeqNo;
	}

	public String getCrncyCd() {
		return crncyCd;
	}

	public void setCrncyCd(String crncyCd) {
		this.crncyCd = crncyCd;
	}

	public double getExrate() {
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

	public String getCnclYn() {
		return cnclYn;
	}

	public void setCnclYn(String cnclYn) {
		this.cnclYn = cnclYn;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public double getPreRcptAmt() {
		return preRcptAmt;
	}

	public void setPreRcptAmt(double preRcptAmt) {
		this.preRcptAmt = preRcptAmt;
	}

	public double getRcptAmt() {
		return rcptAmt;
	}

	public void setRcptAmt(double rcptAmt) {
		this.rcptAmt = rcptAmt;
	}

	public String getTransDt() {
		return transDt;
	}

	public void setTransDt(String transDt) {
		this.transDt = transDt;
	}

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

	public String getPrepdCtrtId() {
		return prepdCtrtId;
	}

	public void setPrepdCtrtId(String prepdCtrtId) {
		this.prepdCtrtId = prepdCtrtId;
	}

	public String getRegrId() {
		return regrId;
	}

	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}

}
