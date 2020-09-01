package com.ntels.ccbs.charge.domain.common;

import java.sql.Timestamp;

public class Deposit {

	/**
	 * Table Sequence 번호
	 */
	private String dpstSeqNo;

	/**
	 * 해당 청구월, 청구주기에 청구대상이 되는 납부계정 정보에 대한 청구서 번호(20자리) 청구년월(4) + 주기(2) +
	 * 청구일자DD(2) + 납부계정ID(10) + 일련번호(2)
	 */
	private String billSeqNo;
	
	/**
	 * 청구년월
	 */
	private String billYymm;
	
	/**
	 * 청구 주기
	 */
	private String billCycl;
	
	/**
	 * 청구일자
	 */
	private String billDt;

	/**
	 * 통장에 입금된 일자(YYYYMMDD)
	 */
	private String transDt;

	/**
	 * 입금처리일자(YYYYMMDD)
	 */
	private String dpstProcDt;

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
	 * 체크용 납부계정ID
	 */
	private String checkPymAcntId;

	/**
	 * 고객ID
	 */
	private String custId;

	/**
	 * 계약ID
	 */
	private String ctrtId;

	/**
	 * 고객이 납부한 일자(YYYYMMDD) 1. 지로: 은행납부일자 2. CMS: 통장인출일자 3. 카드: 전표매입일자 4.
	 * 무통장(가상계좌): 입금일자
	 */
	private String dpstDt;

	/**
	 * 입금형태(BLPY003) 1: 일괄입금(지로,자동이체,신용카드) 2: 가상계좌입금 3: 건별입금(A장표,계좌이체,현금)
	 */
	private String dpstTp;

	/**
	 * 입금구분(BLPY001) 01: 지로 02: 자동이체 03: CMS 04: 가상계좌 05: 신용카드
	 */
	private String dpstCl;

	/**
	 * 입금구분에 따라 해당 Table에서의 참조 일련번호
	 */
	private String dpstTpSeqNo;

	/**
	 * 수납기관유형(BLPY014) 01: 은행 02: 카드사 03: 훼미리마트 04: KT합산 05: 휴대폰합산
	 */
	private String payCorpTp;

	/**
	 * 고객이 납부한 금융기관 코드 - 자동이체(CMS), 가상계좌, 지로인 경우는 은행코드 - 카드이체인 경우 카드사 코드
	 */
	private String payCorpCd;

	/**
	 * 고객이 납부한 계좌 및 카드번호 - 자동이체(CMS), 지로자동이체: 계좌번호 - 가상계좌: 가상계좌번호 - 지로: 지로번호 -
	 * 카드자동이체: 카드번호
	 */
	private String acntCardNo;

	/**
	 * 입금금액
	 */
	private double dpstAmt;
	
	/**
	 * 수납금액
	 */
	private double rcptAmt;

	/**
	 * 고객이 입금하는 C 은행계좌 코드
	 */
	private String dpstBnkAcntCd;

	/**
	 * 수수료
	 */
	private double feeAmt;

	/**
	 * 수납처리여부 N: 수납미처리 Y: 수납처리완료
	 */
	private String payProcYn;

	/**
	 * 수납처리일자(YYYYMMDD)
	 */
	private String payProcDt;

	/**
	 * 미납입금대상여부
	 */
	private String ambgTgtYn;

	/**
	 * 원화입금금액
	 */
	private double wonDpstAmt;

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
	 * 수납취소여부 Y: 수납취소 N: 정상
	 */
	private String payCnclYn;

	/**
	 * 등록일
	 */
	private Timestamp regDate;

	/**
	 * 선수금대상여부
	 */
	private String prepayTgtYn;

	/**
	 * 등록자ID
	 */
	private String regrId;
	
	private String fullPayYn;
	
	/**
	 * 청구금액
	 */
	private double billAmt;
	
	private String prepayOccSeqNo; 
	private String ambgOccSeqNo; 
	private String assrOccSeqNo;	

	public String getPrepayOccSeqNo() {
		return prepayOccSeqNo;
	}

	public void setPrepayOccSeqNo(String prepayOccSeqNo) {
		this.prepayOccSeqNo = prepayOccSeqNo;
	}

	public String getAmbgOccSeqNo() {
		return ambgOccSeqNo;
	}

	public void setAmbgOccSeqNo(String ambgOccSeqNo) {
		this.ambgOccSeqNo = ambgOccSeqNo;
	}

	public String getAssrOccSeqNo() {
		return assrOccSeqNo;
	}

	public void setAssrOccSeqNo(String assrOccSeqNo) {
		this.assrOccSeqNo = assrOccSeqNo;
	}

	public String getDpstSeqNo() {
		return dpstSeqNo;
	}

	public void setDpstSeqNo(String dpstSeqNo) {
		this.dpstSeqNo = dpstSeqNo;
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

	/**
	 * @return the billCycl
	 */
	public String getBillCycl() {
		return billCycl;
	}

	/**
	 * @param billCycl the billCycl to set
	 */
	public void setBillCycl(String billCycl) {
		this.billCycl = billCycl;
	}

	/**
	 * @return the billDt
	 */
	public String getBillDt() {
		return billDt;
	}

	/**
	 * @param billDt the billDt to set
	 */
	public void setBillDt(String billDt) {
		this.billDt = billDt;
	}

	public String getTransDt() {
		return transDt;
	}

	public void setTransDt(String transDt) {
		this.transDt = transDt;
	}

	public String getDpstProcDt() {
		return dpstProcDt;
	}

	public void setDpstProcDt(String dpstProcDt) {
		this.dpstProcDt = dpstProcDt;
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

	public String getCheckPymAcntId() {
		return checkPymAcntId;
	}

	public void setCheckPymAcntId(String checkPymAcntId) {
		this.checkPymAcntId = checkPymAcntId;
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

	public String getDpstDt() {
		return dpstDt;
	}

	public void setDpstDt(String dpstDt) {
		this.dpstDt = dpstDt;
	}

	public String getDpstTp() {
		return dpstTp;
	}

	public void setDpstTp(String dpstTp) {
		this.dpstTp = dpstTp;
	}

	public String getDpstCl() {
		return dpstCl;
	}

	public void setDpstCl(String dpstCl) {
		this.dpstCl = dpstCl;
	}

	public String getDpstTpSeqNo() {
		return dpstTpSeqNo;
	}

	public void setDpstTpSeqNo(String dpstTpSeqNo) {
		this.dpstTpSeqNo = dpstTpSeqNo;
	}

	public String getPayCorpTp() {
		return payCorpTp;
	}

	public void setPayCorpTp(String payCorpTp) {
		this.payCorpTp = payCorpTp;
	}

	public String getPayCorpCd() {
		return payCorpCd;
	}

	public void setPayCorpCd(String payCorpCd) {
		this.payCorpCd = payCorpCd;
	}

	public String getAcntCardNo() {
		return acntCardNo;
	}

	public void setAcntCardNo(String acntCardNo) {
		this.acntCardNo = acntCardNo;
	}

	public double getDpstAmt() {
		return dpstAmt;
	}

	public void setDpstAmt(double dpstAmt) {
		this.dpstAmt = dpstAmt;
	}

	public double getRcptAmt() {
		return rcptAmt;
	}

	public void setRcptAmt(double rcptAmt) {
		this.rcptAmt = rcptAmt;
	}

	public String getDpstBnkAcntCd() {
		return dpstBnkAcntCd;
	}

	public void setDpstBnkAcntCd(String dpstBnkAcntCd) {
		this.dpstBnkAcntCd = dpstBnkAcntCd;
	}

	public double getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(double feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getPayProcYn() {
		return payProcYn;
	}

	public void setPayProcYn(String payProcYn) {
		this.payProcYn = payProcYn;
	}

	public String getPayProcDt() {
		return payProcDt;
	}

	public void setPayProcDt(String payProcDt) {
		this.payProcDt = payProcDt;
	}

	public String getAmbgTgtYn() {
		return ambgTgtYn;
	}

	public void setAmbgTgtYn(String ambgTgtYn) {
		this.ambgTgtYn = ambgTgtYn;
	}

	public double getWonDpstAmt() {
		return wonDpstAmt;
	}

	public void setWonDpstAmt(double wonDpstAmt) {
		this.wonDpstAmt = wonDpstAmt;
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

	public String getPayCnclYn() {
		return payCnclYn;
	}

	public void setPayCnclYn(String payCnclYn) {
		this.payCnclYn = payCnclYn;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public String getPrepayTgtYn() {
		return prepayTgtYn;
	}

	public void setPrepayTgtYn(String prepayTgtYn) {
		this.prepayTgtYn = prepayTgtYn;
	}

	public String getRegrId() {
		return regrId;
	}

	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}

	public String getFullPayYn() {
		return fullPayYn;
	}

	public void setFullPayYn(String fullPayYn) {
		this.fullPayYn = fullPayYn;
	}

	/**
	 * @return the billAmt
	 */
	public double getBillAmt() {
		return billAmt;
	}

	/**
	 * @param billAmt the billAmt to set
	 */
	public void setBillAmt(double billAmt) {
		this.billAmt = billAmt;
	}

}
