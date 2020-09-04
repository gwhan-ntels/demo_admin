package com.ntels.ccbs.charge.domain.payment.advanceReceived;

import java.io.Serializable;
import java.sql.Timestamp;

public class AdvanceReceivedVO implements Serializable{

	private static final long serialVersionUID = 2112029555953455633L;
	
	private String soId;
	private String prepayOccSeqNo;		//PREPAY_OCC_SEQ_NO
	private String pymAcntId;			//PYM_ACNT_ID
	private String acntNm;				//ACNT_NM
	private String prepayOccDttm;		//PREPAY_OCC_DTTM
	private String dpstDt;				//DPST_DT
	private String transDt;				//TRANS_DT
	private String prepayOccAmt;		//PREPAY_OCC_AMT
	private String prepayTransAmt;		//PREPAY_TRANS_AMT
	private String prepayBal;			//PREPAY_BAL
	private Timestamp regDate;				//REG_DATE
	private Timestamp chgDate;				//CHG_DATE
	private String prepayOccTp;			//PREPAY_OCC_TP
	private String prepayProcStat;		//PREPAY_PROC_STAT
	private String dpstCl;				//DPST_CL
	private String prepayOccResn;		//PREPAY_OCC_RESN
	private String regrId;		//REGR_ID
	private String chgrId;		//CHGR_ID
	
	private String prepayOccNm;			//PREPAY_OCC_NM
	private String prepayProcStatNm;	//PREPAY_PROC_STAT_NM
	private String dpstClNm;			//DPST_CL_NM
	private String prepayOccResnNm;		//PREPAY_OCC_RESN_NM
	
	private String regrNm;		//REGR_NM
	private String chgrNm;		//CHGR_NM
	
	private String dtTp;		//
	private String startDt;		//
	private String endDt;		//
	
	private String lngTyp;

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

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

	public String getAcntNm() {
		return acntNm;
	}

	public void setAcntNm(String acntNm) {
		this.acntNm = acntNm;
	}

	public String getPrepayOccDttm() {
		return prepayOccDttm;
	}

	public void setPrepayOccDttm(String prepayOccDttm) {
		this.prepayOccDttm = prepayOccDttm;
	}

	public String getDpstDt() {
		return dpstDt;
	}

	public void setDpstDt(String dpstDt) {
		this.dpstDt = dpstDt;
	}

	public String getTransDt() {
		return transDt;
	}

	public void setTransDt(String transDt) {
		this.transDt = transDt;
	}

	public String getPrepayOccAmt() {
		return prepayOccAmt;
	}

	public void setPrepayOccAmt(String prepayOccAmt) {
		this.prepayOccAmt = prepayOccAmt;
	}

	public String getPrepayTransAmt() {
		return prepayTransAmt;
	}

	public void setPrepayTransAmt(String prepayTransAmt) {
		this.prepayTransAmt = prepayTransAmt;
	}

	public String getPrepayBal() {
		return prepayBal;
	}

	public void setPrepayBal(String prepayBal) {
		this.prepayBal = prepayBal;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public Timestamp getChgDate() {
		return chgDate;
	}

	public void setChgDate(Timestamp chgDate) {
		this.chgDate = chgDate;
	}

	public String getPrepayOccTp() {
		return prepayOccTp;
	}

	public void setPrepayOccTp(String prepayOccTp) {
		this.prepayOccTp = prepayOccTp;
	}

	public String getPrepayProcStat() {
		return prepayProcStat;
	}

	public void setPrepayProcStat(String prepayProcStat) {
		this.prepayProcStat = prepayProcStat;
	}

	public String getDpstCl() {
		return dpstCl;
	}

	public void setDpstCl(String dpstCl) {
		this.dpstCl = dpstCl;
	}

	public String getPrepayOccResn() {
		return prepayOccResn;
	}

	public void setPrepayOccResn(String prepayOccResn) {
		this.prepayOccResn = prepayOccResn;
	}

	public String getRegrId() {
		return regrId;
	}

	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}

	public String getChgrId() {
		return chgrId;
	}

	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}

	public String getPrepayOccNm() {
		return prepayOccNm;
	}

	public void setPrepayOccNm(String prepayOccNm) {
		this.prepayOccNm = prepayOccNm;
	}

	public String getPrepayProcStatNm() {
		return prepayProcStatNm;
	}

	public void setPrepayProcStatNm(String prepayProcStatNm) {
		this.prepayProcStatNm = prepayProcStatNm;
	}

	public String getDpstClNm() {
		return dpstClNm;
	}

	public void setDpstClNm(String dpstClNm) {
		this.dpstClNm = dpstClNm;
	}

	public String getPrepayOccResnNm() {
		return prepayOccResnNm;
	}

	public void setPrepayOccResnNm(String prepayOccResnNm) {
		this.prepayOccResnNm = prepayOccResnNm;
	}

	public String getRegrNm() {
		return regrNm;
	}

	public void setRegrNm(String regrNm) {
		this.regrNm = regrNm;
	}

	public String getChgrNm() {
		return chgrNm;
	}

	public void setChgrNm(String chgrNm) {
		this.chgrNm = chgrNm;
	}

	public String getDtTp() {
		return dtTp;
	}

	public void setDtTp(String dtTp) {
		this.dtTp = dtTp;
	}

	public String getStartDt() {
		return startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public String getLngTyp() {
		return lngTyp;
	}

	public void setLngTyp(String lngTyp) {
		this.lngTyp = lngTyp;
	}

	
}
