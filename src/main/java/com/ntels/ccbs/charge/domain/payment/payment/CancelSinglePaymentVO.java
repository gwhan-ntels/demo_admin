package com.ntels.ccbs.charge.domain.payment.payment;

import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("CancelSinglePaymentVO")
public class CancelSinglePaymentVO extends PagingValue {

	private String orgId;		//조직id
	private String orgNm;		//조직
	private String soId;		//사업자Id
	private String soNm;		//사업자명
	private String usrId;		//사용자id
	private String lngTyp;
	private String sidx;
	private String sord;
	private String chk;
	
	private String regrId;
	private String regrNm;          //등록자명
	private Date regDate;         //등록일
	private String pymAcntId;	    //납부계정id
	private String pymAcntNm;	    //납부계정
	private String pymMthd;	        //납부방법
	private String pymMthdNm;	    //납부방법명
	private String dpstCl;
	private String dpstClNm;
	private String dpstTp;
	private String dpstTpNm;
	private String payCorpTp;
	private String payCorpTpNm;
	private String payCorpCd;
	private String payCorpCdNm;
    private String acntCardNo; 		//계좌 및 카드번호
    private String dpstAmt;
    private String dpstDt;
    private String transDt;
    private String dpstProcDt;
    private String ambgTgtYn;
    private String prepayTgtYn;
    private String payProcDt;
    private String payProcYn;       //수납처리여부
    private String dpstSeqNo;
    private String dpstTpSeqNo;

	private String cnclResn; //취소사유
	private String cnclrId; 
	private String cnclDttm;
	
	
	/* 입금관련 */
	private String dpstDtFrom;   //입금일자 from
	private String dpstDtTo;     //입금일자 to
	private String procDt;       //sysdate
	private String cnclYn;
	private String chgrId;
	
	
	private String billSeqNo;
	private String custId;
	private String ctrtId;
	private String feeAmt;
	private String wonDpstAmt;
	private String crncyCd;
	private String exrate;
	private String exrateAplyDt;
	
	private String grpId;
	
	// bill 정보

	private String pymSeqNo;
	private String useYymm;
	private String prodCmpsId;
	private String svcCmpsId;
	private String chrgItmCd;
	private Double billAmt;
	private Double rcptAmt;
	private Double preRcptAmt;	
	private String preSoId;
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
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
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getLngTyp() {
		return lngTyp;
	}
	public void setLngTyp(String lngTyp) {
		this.lngTyp = lngTyp;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String getRegrId() {
		return regrId;
	}
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	public String getRegrNm() {
		return regrNm;
	}
	public void setRegrNm(String regrNm) {
		this.regrNm = regrNm;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getPymAcntId() {
		return pymAcntId;
	}
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}
	public String getPymAcntNm() {
		return pymAcntNm;
	}
	public void setPymAcntNm(String pymAcntNm) {
		this.pymAcntNm = pymAcntNm;
	}
	public String getPymMthd() {
		return pymMthd;
	}
	public void setPymMthd(String pymMthd) {
		this.pymMthd = pymMthd;
	}
	public String getPymMthdNm() {
		return pymMthdNm;
	}
	public void setPymMthdNm(String pymMthdNm) {
		this.pymMthdNm = pymMthdNm;
	}
	public String getDpstCl() {
		return dpstCl;
	}
	public void setDpstCl(String dpstCl) {
		this.dpstCl = dpstCl;
	}
	public String getDpstClNm() {
		return dpstClNm;
	}
	public void setDpstClNm(String dpstClNm) {
		this.dpstClNm = dpstClNm;
	}
	public String getDpstTp() {
		return dpstTp;
	}
	public void setDpstTp(String dpstTp) {
		this.dpstTp = dpstTp;
	}
	public String getDpstTpNm() {
		return dpstTpNm;
	}
	public void setDpstTpNm(String dpstTpNm) {
		this.dpstTpNm = dpstTpNm;
	}
	public String getPayCorpTp() {
		return payCorpTp;
	}
	public void setPayCorpTp(String payCorpTp) {
		this.payCorpTp = payCorpTp;
	}
	public String getPayCorpTpNm() {
		return payCorpTpNm;
	}
	public void setPayCorpTpNm(String payCorpTpNm) {
		this.payCorpTpNm = payCorpTpNm;
	}
	public String getPayCorpCd() {
		return payCorpCd;
	}
	public void setPayCorpCd(String payCorpCd) {
		this.payCorpCd = payCorpCd;
	}
	public String getPayCorpCdNm() {
		return payCorpCdNm;
	}
	public void setPayCorpCdNm(String payCorpCdNm) {
		this.payCorpCdNm = payCorpCdNm;
	}
	public String getAcntCardNo() {
		return acntCardNo;
	}
	public void setAcntCardNo(String acntCardNo) {
		this.acntCardNo = acntCardNo;
	}
	public String getDpstAmt() {
		return dpstAmt;
	}
	public void setDpstAmt(String dpstAmt) {
		this.dpstAmt = dpstAmt;
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
	public String getDpstProcDt() {
		return dpstProcDt;
	}
	public void setDpstProcDt(String dpstProcDt) {
		this.dpstProcDt = dpstProcDt;
	}
	public String getAmbgTgtYn() {
		return ambgTgtYn;
	}
	public void setAmbgTgtYn(String ambgTgtYn) {
		this.ambgTgtYn = ambgTgtYn;
	}
	public String getPrepayTgtYn() {
		return prepayTgtYn;
	}
	public void setPrepayTgtYn(String prepayTgtYn) {
		this.prepayTgtYn = prepayTgtYn;
	}
	public String getPayProcDt() {
		return payProcDt;
	}
	public void setPayProcDt(String payProcDt) {
		this.payProcDt = payProcDt;
	}
	public String getPayProcYn() {
		return payProcYn;
	}
	public void setPayProcYn(String payProcYn) {
		this.payProcYn = payProcYn;
	}
	public String getDpstSeqNo() {
		return dpstSeqNo;
	}
	public void setDpstSeqNo(String dpstSeqNo) {
		this.dpstSeqNo = dpstSeqNo;
	}
	public String getDpstTpSeqNo() {
		return dpstTpSeqNo;
	}
	public void setDpstTpSeqNo(String dpstTpSeqNo) {
		this.dpstTpSeqNo = dpstTpSeqNo;
	}
	public String getCnclResn() {
		return cnclResn;
	}
	public void setCnclResn(String cnclResn) {
		this.cnclResn = cnclResn;
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
	public String getDpstDtFrom() {
		return dpstDtFrom;
	}
	public void setDpstDtFrom(String dpstDtFrom) {
		this.dpstDtFrom = dpstDtFrom;
	}
	public String getDpstDtTo() {
		return dpstDtTo;
	}
	public void setDpstDtTo(String dpstDtTo) {
		this.dpstDtTo = dpstDtTo;
	}
	public String getProcDt() {
		return procDt;
	}
	public void setProcDt(String procDt) {
		this.procDt = procDt;
	}
	public String getCnclYn() {
		return cnclYn;
	}
	public void setCnclYn(String cnclYn) {
		this.cnclYn = cnclYn;
	}
	public String getChgrId() {
		return chgrId;
	}
	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}
	public String getBillSeqNo() {
		return billSeqNo;
	}
	public void setBillSeqNo(String billSeqNo) {
		this.billSeqNo = billSeqNo;
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
	public String getFeeAmt() {
		return feeAmt;
	}
	public void setFeeAmt(String feeAmt) {
		this.feeAmt = feeAmt;
	}
	public String getWonDpstAmt() {
		return wonDpstAmt;
	}
	public void setWonDpstAmt(String wonDpstAmt) {
		this.wonDpstAmt = wonDpstAmt;
	}
	public String getCrncyCd() {
		return crncyCd;
	}
	public void setCrncyCd(String crncyCd) {
		this.crncyCd = crncyCd;
	}
	public String getExrate() {
		return exrate;
	}
	public void setExrate(String exrate) {
		this.exrate = exrate;
	}
	public String getExrateAplyDt() {
		return exrateAplyDt;
	}
	public void setExrateAplyDt(String exrateAplyDt) {
		this.exrateAplyDt = exrateAplyDt;
	}
	public String getGrpId() {
		return grpId;
	}
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}
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
	public Double getBillAmt() {
		return billAmt;
	}
	public void setBillAmt(Double billAmt) {
		this.billAmt = billAmt;
	}
	public Double getRcptAmt() {
		return rcptAmt;
	}
	public void setRcptAmt(Double rcptAmt) {
		this.rcptAmt = rcptAmt;
	}
	public Double getPreRcptAmt() {
		return preRcptAmt;
	}
	public void setPreRcptAmt(Double preRcptAmt) {
		this.preRcptAmt = preRcptAmt;
	}
	public String getPreSoId() {
		return preSoId;
	}
	public void setPreSoId(String preSoId) {
		this.preSoId = preSoId;
	}
	

	
	
	
	// 화면 권한 (여신 + 조직관련)
//	private String deptCd;
//	private String loanAvlAmt;
//	private String loanGvFlg;
	
	
	
}
