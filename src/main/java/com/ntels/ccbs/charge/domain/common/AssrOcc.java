package com.ntels.ccbs.charge.domain.common;

import java.sql.Timestamp;

public class AssrOcc {

	/**
	 * Table Sequence 번호
	 */
	private String assrOccSeqNo;

	/**
	 * 수납일련번호
	 */
	private String pymSeqNo;

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
	 * 납부계정ID
	 */
	private String pymAcntId;

	/**
	 * 고객ID
	 */
	private String custId;

	/**
	 * 계약ID
	 */
	private String ctrtId;

	/**
	 * YYYYMMDDHHMMSS
	 */
	private String assrOccDttm;

	/**
	 * 입금구분에 따라 해당 Table에서의 참조 일련번호
	 */
	private String dpstTpSeqNo;

	/**
	 * 고객이 납부한 일자(YYYYMMDD) 1. 지로: 은행납부일자 2. CMS: 통장인출일자 3. 카드: 전표매입일자 4.
	 * 무통장(가상계좌): 입금일자
	 */
	private String dpstDt;

	/**
	 * 입금처리일시(YYYYMMDDHHMMSS)
	 */
	private String dpstProcDttm;

	/**
	 * 입금구분(BLPY001) 01: 지로 02: 자동이체 03: CMS 04: 가상계좌 05: 신용카드
	 */
	private String dpstCl;

	/**
	 * BLPY031 1: 보증금발생 2: 보증금대체적용중 3: 보증금대체완료
	 */
	private String assrProcStat;

	/**
	 * 보증금발생금액
	 */
	private double assrOccAmt;

	/**
	 * 보증금대체금액
	 */
	private double assrTransAmt;

	/**
	 * 보증금잔액
	 */
	private double assrBal;

	/**
	 * 대체완료여부 N: 대체미완료 Y: 대체완료
	 */
	private String transCmplYn;

	/**
	 * 원화보증금발생금액
	 */
	private double wonAssrOccAmt;

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
	 * 등록자ID
	 */
	private String regrId;

	/**
	 * 등록일
	 */
	private Timestamp regDate;

	/**
	 * 취소여부 N: 미취소(Default) Y: 취소
	 */
	private String cnclYn;

	/**
	 * 취소일시(YYYYMMDDHHMMSS)
	 */
	private String cnclDttm;

	/**
	 * 변경일
	 */
	private Timestamp chgDate;

	/**
	 * 통장에 입금된 일자(YYYYMMDD)
	 */
	private String transDt;

	public String getAssrOccSeqNo() {
		return assrOccSeqNo;
	}

	public void setAssrOccSeqNo(String assrOccSeqNo) {
		this.assrOccSeqNo = assrOccSeqNo;
	}

	public String getPymSeqNo() {
		return pymSeqNo;
	}

	public void setPymSeqNo(String pymSeqNo) {
		this.pymSeqNo = pymSeqNo;
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

	public String getPymAcntId() {
		return pymAcntId;
	}

	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
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

	public String getAssrOccDttm() {
		return assrOccDttm;
	}

	public void setAssrOccDttm(String assrOccDttm) {
		this.assrOccDttm = assrOccDttm;
	}

	public String getDpstTpSeqNo() {
		return dpstTpSeqNo;
	}

	public void setDpstTpSeqNo(String dpstTpSeqNo) {
		this.dpstTpSeqNo = dpstTpSeqNo;
	}

	public String getDpstDt() {
		return dpstDt;
	}

	public void setDpstDt(String dpstDt) {
		this.dpstDt = dpstDt;
	}

	public String getDpstProcDttm() {
		return dpstProcDttm;
	}

	public void setDpstProcDttm(String dpstProcDttm) {
		this.dpstProcDttm = dpstProcDttm;
	}

	public String getDpstCl() {
		return dpstCl;
	}

	public void setDpstCl(String dpstCl) {
		this.dpstCl = dpstCl;
	}

	public String getAssrProcStat() {
		return assrProcStat;
	}

	public void setAssrProcStat(String assrProcStat) {
		this.assrProcStat = assrProcStat;
	}

	public double getAssrOccAmt() {
		return assrOccAmt;
	}

	public void setAssrOccAmt(double assrOccAmt) {
		this.assrOccAmt = assrOccAmt;
	}

	public double getAssrTransAmt() {
		return assrTransAmt;
	}

	public void setAssrTransAmt(double assrTransAmt) {
		this.assrTransAmt = assrTransAmt;
	}

	public double getAssrBal() {
		return assrBal;
	}

	public void setAssrBal(double assrBal) {
		this.assrBal = assrBal;
	}

	public String getTransCmplYn() {
		return transCmplYn;
	}

	public void setTransCmplYn(String transCmplYn) {
		this.transCmplYn = transCmplYn;
	}

	public double getWonAssrOccAmt() {
		return wonAssrOccAmt;
	}

	public void setWonAssrOccAmt(double wonAssrOccAmt) {
		this.wonAssrOccAmt = wonAssrOccAmt;
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

	public String getCnclYn() {
		return cnclYn;
	}

	public void setCnclYn(String cnclYn) {
		this.cnclYn = cnclYn;
	}

	public String getCnclDttm() {
		return cnclDttm;
	}

	public void setCnclDttm(String cnclDttm) {
		this.cnclDttm = cnclDttm;
	}

	public Timestamp getChgDate() {
		return chgDate;
	}

	public void setChgDate(Timestamp chgDate) {
		this.chgDate = chgDate;
	}

	public String getTransDt() {
		return transDt;
	}

	public void setTransDt(String transDt) {
		this.transDt = transDt;
	}

}
