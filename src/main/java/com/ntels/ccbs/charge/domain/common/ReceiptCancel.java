package com.ntels.ccbs.charge.domain.common;

import java.sql.Timestamp;

public class ReceiptCancel {

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
	private String prepayTransSeqNo;

	/**
	 * Table Sequence 번호
	 */
	private String ambgTransSeqNo;

	/**
	 * 입금구분에 따라 해당 Table에서의 참조 일련번호
	 */
	private String dpstTpSeqNo;

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
	 * 취소자ID
	 */
	private String cnclrId;

	/**
	 * 취소일시(YYYYMMDDHHMMSS)
	 */
	private String cnclDttm;

	/**
	 * 취소사유
	 */
	private String cnclResn;

	/**
	 * 이전수납금액
	 */
	private double preRcptAmt;

	/**
	 * 고객관리의 상담 접수ID - 고객의 요청에 의해 상담원이 요금조정을 신청하는 경우 접수ID를 연결하여 요금조정화면을 이용하여
	 * 요금조정신청을 함
	 */
	private String rcptId;
	
	private String regrId;
	private Timestamp regDate;
	private String chgrId;
	private Timestamp chgDate;

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
	public String getDpstTpSeqNo() {
		return dpstTpSeqNo;
	}
	public void setDpstTpSeqNo(String dpstTpSeqNo) {
		this.dpstTpSeqNo = dpstTpSeqNo;
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
	public String getCnclrId() {
		return cnclrId;
	}
	public void setCnclrId(String cnclrId) {
		this.cnclrId = cnclrId;
	}
	public String getCnclDttm() {
		return cnclDttm;
	}
	public void setCnclDttm(String cnclDttm) {
		this.cnclDttm = cnclDttm;
	}
	public String getCnclResn() {
		return cnclResn;
	}
	public void setCnclResn(String cnclResn) {
		this.cnclResn = cnclResn;
	}
	public double getPreRcptAmt() {
		return preRcptAmt;
	}
	public void setPreRcptAmt(double preRcptAmt) {
		this.preRcptAmt = preRcptAmt;
	}
	public String getRcptId() {
		return rcptId;
	}
	public void setRcptId(String rcptId) {
		this.rcptId = rcptId;
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


}
