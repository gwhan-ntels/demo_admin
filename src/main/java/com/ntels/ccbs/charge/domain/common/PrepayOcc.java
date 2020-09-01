package com.ntels.ccbs.charge.domain.common;

import java.sql.Timestamp;

public class PrepayOcc {

	/**
	 * Table Sequence 번호
	 */
	private String prepayOccSeqNo;

	/**
	 * 납부계정ID
	 */
	private String pymAcntId;

	/**
	 * 선수금발생일시(YYYYMMDDHHMMSS)
	 */
	private String prepayOccDttm;

	/**
	 * 선수금발생구분(BLPY005) 1: 수납처리 2: 미확인입금대체
	 */
	private String prepayOccTp;

	/**
	 * 선수금발생사유(BLPY006) 1: 과오납 2: 이중납 3: 자금이체수납에의한과납 4: 수납취소(조정) 5: 수납취소(고객요청)
	 */
	private String prepayOccResn;

	/**
	 * 선수금이 발생하게 된 원인에 대한 일련번호 1(수납처리): 수납일련번호 2(미확인입금대체): 미확인입금발생일련번호
	 */
	private String prepayOccTgtSeqNo;

	/**
	 * 고객이 납부한 일자(YYYYMMDD) 1. 지로: 은행납부일자 2. CMS: 통장인출일자 3. 카드: 전표매입일자 4.
	 * 무통장(가상계좌): 입금일자
	 */
	private String dpstDt;

	/**
	 * 입금처리일시(YYYYMMDDHHMMSS)
	 */
	private String dpstProcDttm;

	private String dpstProcDt;
	/**
	 * 입금구분(BLPY001) 01: 지로 02: 자동이체 03: CMS 04: 가상계좌 05: 신용카드
	 */
	private String dpstCl;

	/**
	 * 선수금처리상태(BLPY028) 1: 선수금발생 2: 선수금대체적용중 3: 선수금대체완료
	 */
	private String prepayProcStat;

	/**
	 * 선수금발생금액
	 */
	private Double prepayOccAmt;

	/**
	 * 선수금대체금액
	 */
	private Double prepayTransAmt;

	/**
	 * 선수금잔액
	 */
	private Double prepayBal;

	/**
	 * 대체완료여부 N: 대체미완료 Y: 대체완료
	 */
	private String transCmplYn;

	/**
	 * 원화선수금발생금액
	 */
	private Double wonPrepayOccAmt;

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
	 * 취소사유
	 */
	private String cnclResn;

	/**
	 * 마이그레이션적용여부
	 */
	private String migAplyTp;

	/**
	 * 통장에 입금된 일자(YYYYMMDD)
	 */
	private String transDt;

	public String getPrepayOccSeqNo() {
		return prepayOccSeqNo;
	}

	public void setPrepayOccSeqNo(String prepayOccSeqNo) {
		this.prepayOccSeqNo = prepayOccSeqNo;
	}

	public String getPymAcntId() {
		return pymAcntId;
	}

	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}

	public String getPrepayOccDttm() {
		return prepayOccDttm;
	}

	public void setPrepayOccDttm(String prepayOccDttm) {
		this.prepayOccDttm = prepayOccDttm;
	}

	public String getPrepayOccTp() {
		return prepayOccTp;
	}

	public void setPrepayOccTp(String prepayOccTp) {
		this.prepayOccTp = prepayOccTp;
	}

	public String getPrepayOccResn() {
		return prepayOccResn;
	}

	public void setPrepayOccResn(String prepayOccResn) {
		this.prepayOccResn = prepayOccResn;
	}

	public String getPrepayOccTgtSeqNo() {
		return prepayOccTgtSeqNo;
	}

	public void setPrepayOccTgtSeqNo(String prepayOccTgtSeqNo) {
		this.prepayOccTgtSeqNo = prepayOccTgtSeqNo;
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

	public String getPrepayProcStat() {
		return prepayProcStat;
	}

	public void setPrepayProcStat(String prepayProcStat) {
		this.prepayProcStat = prepayProcStat;
	}

	public Double getPrepayOccAmt() {
		return prepayOccAmt;
	}

	public void setPrepayOccAmt(Double prepayOccAmt) {
		this.prepayOccAmt = prepayOccAmt;
	}

	public Double getPrepayTransAmt() {
		return prepayTransAmt;
	}

	public void setPrepayTransAmt(Double prepayTransAmt) {
		this.prepayTransAmt = prepayTransAmt;
	}

	public Double getPrepayBal() {
		return prepayBal;
	}

	public void setPrepayBal(Double prepayBal) {
		this.prepayBal = prepayBal;
	}

	public String getTransCmplYn() {
		return transCmplYn;
	}

	public void setTransCmplYn(String transCmplYn) {
		this.transCmplYn = transCmplYn;
	}

	public Double getWonPrepayOccAmt() {
		return wonPrepayOccAmt;
	}

	public void setWonPrepayOccAmt(Double wonPrepayOccAmt) {
		this.wonPrepayOccAmt = wonPrepayOccAmt;
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

	public void setExrate(Double exrate) {
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

	public String getCnclResn() {
		return cnclResn;
	}

	public void setCnclResn(String cnclResn) {
		this.cnclResn = cnclResn;
	}

	public String getMigAplyTp() {
		return migAplyTp;
	}

	public String getDpstProcDt() {
		return dpstProcDt;
	}

	public void setDpstProcDt(String dpstProcDt) {
		this.dpstProcDt = dpstProcDt;
	}

	public void setMigAplyTp(String migAplyTp) {
		this.migAplyTp = migAplyTp;
	}

	public String getTransDt() {
		return transDt;
	}

	public void setTransDt(String transDt) {
		this.transDt = transDt;
	}

}
