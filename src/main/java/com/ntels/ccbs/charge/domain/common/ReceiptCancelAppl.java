package com.ntels.ccbs.charge.domain.common;

import java.sql.Timestamp;

public class ReceiptCancelAppl {

	/**
	 * 수납일련번호
	 */
	private String pymSeqNo;

	/**
	 * 납부계정ID
	 */
	private String pymAcntId;

	/**
	 * 수납처리일자(YYYYMMDD)
	 */
	private String payProcDt;

	/**
	 * 입금구분(BLPY001) 01: 지로 02: 자동이체 03: CMS 04: 가상계좌 05: 신용카드
	 */
	private String dpstCl;

	/**
	 * 접수자ID
	 */
	private String rcptPsnId;

	/**
	 * 접수일시(YYYYMMDDHHMMSS)
	 */
	private String rcptDttm;

	/**
	 * 결재요청자ID
	 */
	private String apprReqrId;

	/**
	 * YYYYMMDDHHMMSS
	 */
	private String apprReqDttm;

	/**
	 * 결재처리상태(BLIV013) 01: 신청 02: 결재요청중 03: 결재완료 04: 반송 05: 처리완료
	 */
	private String dcsnProcStat;

	/**
	 * 결재자ID
	 */
	private String apprrId;

	/**
	 * 결재일시(YYYYMMDDHHMMSS)
	 */
	private String apprDttm;

	/**
	 * 취소사유
	 */
	private String cnclResn;

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
	public String getDpstCl() {
		return dpstCl;
	}
	public void setDpstCl(String dpstCl) {
		this.dpstCl = dpstCl;
	}
	public String getRcptPsnId() {
		return rcptPsnId;
	}
	public void setRcptPsnId(String rcptPsnId) {
		this.rcptPsnId = rcptPsnId;
	}
	public String getRcptDttm() {
		return rcptDttm;
	}
	public void setRcptDttm(String rcptDttm) {
		this.rcptDttm = rcptDttm;
	}
	public String getApprReqrId() {
		return apprReqrId;
	}
	public void setApprReqrId(String apprReqrId) {
		this.apprReqrId = apprReqrId;
	}
	public String getApprReqDttm() {
		return apprReqDttm;
	}
	public void setApprReqDttm(String apprReqDttm) {
		this.apprReqDttm = apprReqDttm;
	}
	public String getDcsnProcStat() {
		return dcsnProcStat;
	}
	public void setDcsnProcStat(String dcsnProcStat) {
		this.dcsnProcStat = dcsnProcStat;
	}
	public String getApprrId() {
		return apprrId;
	}
	public void setApprrId(String apprrId) {
		this.apprrId = apprrId;
	}
	public String getApprDttm() {
		return apprDttm;
	}
	public void setApprDttm(String apprDttm) {
		this.apprDttm = apprDttm;
	}
	public String getCnclResn() {
		return cnclResn;
	}
	public void setCnclResn(String cnclResn) {
		this.cnclResn = cnclResn;
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
