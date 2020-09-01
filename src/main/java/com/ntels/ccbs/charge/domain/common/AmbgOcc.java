package com.ntels.ccbs.charge.domain.common;

import java.sql.Timestamp;

public class AmbgOcc {

	/**
	 * Table Sequence 번호
	 */
	private String ambgOccSeqNo;

	/**
	 * 미확인입금입금발생일시(YYYMMDDHHMMSS)
	 */
	private String ambgOccDttm;

	/**
	 * 미확인입금발생구분(BLPY009) 01: 건별입금 02: 지로일괄입금 03: 자동이체입금 04: CMS일괄입금 05: 가상계좌입금
	 * 06: 신용카드일괄입금
	 */
	private String ambgOccTp;

	/**
	 * BLPY010 1: 납입자번호확인불가능 2: 선수금대체
	 */
	private String ambgOccResn;

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
	 * ?????? ???????? C ???????? ????
	 */
	private String dpstBnkAcntCd;

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
	 * 미확인입금처리상태(BLPY012) 1: 미확인입금발생 2: 미확인입금대체적용중 3: 미확인입금대체완료
	 */
	private String ambgProcStat;

	/**
	 * 미확인입금발생금액
	 */
	private double ambgOccAmt;

	/**
	 * 미확인입금대체금액
	 */
	private double ambgTransAmt;

	/**
	 * 미확인입금잔액
	 */
	private double ambgBal;

	/**
	 * 대체완료여부 N: 대체미완료 Y: 대체완료
	 */
	private String transCmplYn;

	/**
	 * 원화미확인입금발생금액
	 */
	private double wonAmbgOccAmt;

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
	 * SO_ID or RO_ID
	 */
	private String soId;

	/**
	 * 변경자ID
	 */
	private String chgrId;

	/**
	 * 납부계정ID
	 */
	private String pymAcntId;

	/**
	 * 통장에 입금된 일자(YYYYMMDD)
	 */
	private String transDt;

	/**
	 * 발생지점ID
	 */
	private String occSoId;

	public String getAmbgOccSeqNo() {
		return ambgOccSeqNo;
	}

	public void setAmbgOccSeqNo(String ambgOccSeqNo) {
		this.ambgOccSeqNo = ambgOccSeqNo;
	}

	public String getAmbgOccDttm() {
		return ambgOccDttm;
	}

	public void setAmbgOccDttm(String ambgOccDttm) {
		this.ambgOccDttm = ambgOccDttm;
	}

	public String getAmbgOccTp() {
		return ambgOccTp;
	}

	public void setAmbgOccTp(String ambgOccTp) {
		this.ambgOccTp = ambgOccTp;
	}

	public String getAmbgOccResn() {
		return ambgOccResn;
	}

	public void setAmbgOccResn(String ambgOccResn) {
		this.ambgOccResn = ambgOccResn;
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

	public String getDpstBnkAcntCd() {
		return dpstBnkAcntCd;
	}

	public void setDpstBnkAcntCd(String dpstBnkAcntCd) {
		this.dpstBnkAcntCd = dpstBnkAcntCd;
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

	public String getAmbgProcStat() {
		return ambgProcStat;
	}

	public void setAmbgProcStat(String ambgProcStat) {
		this.ambgProcStat = ambgProcStat;
	}

	public double getAmbgOccAmt() {
		return ambgOccAmt;
	}

	public void setAmbgOccAmt(double ambgOccAmt) {
		this.ambgOccAmt = ambgOccAmt;
	}

	public double getAmbgTransAmt() {
		return ambgTransAmt;
	}

	public void setAmbgTransAmt(double ambgTransAmt) {
		this.ambgTransAmt = ambgTransAmt;
	}

	public double getAmbgBal() {
		return ambgBal;
	}

	public void setAmbgBal(double ambgBal) {
		this.ambgBal = ambgBal;
	}

	public String getTransCmplYn() {
		return transCmplYn;
	}

	public void setTransCmplYn(String transCmplYn) {
		this.transCmplYn = transCmplYn;
	}

	public double getWonAmbgOccAmt() {
		return wonAmbgOccAmt;
	}

	public void setWonAmbgOccAmt(double wonAmbgOccAmt) {
		this.wonAmbgOccAmt = wonAmbgOccAmt;
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

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

	public String getChgrId() {
		return chgrId;
	}

	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}

	public String getPymAcntId() {
		return pymAcntId;
	}

	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}

	public String getTransDt() {
		return transDt;
	}

	public void setTransDt(String transDt) {
		this.transDt = transDt;
	}

	public String getOccSoId() {
		return occSoId;
	}

	public void setOccSoId(String occSoId) {
		this.occSoId = occSoId;
	}

}
