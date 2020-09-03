package com.ntels.ccbs.charge.domain.payment.payment;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ReceiptDetailVO")
public class ReceiptCancelVO extends PagingValue {

	private String orgId;
	private String orgNm;
	private String soId;
	private String soNm;
	private String pymAcntId;
	private String usrId;
	
	private String pymDtTp;
	private String strtDt;
	private String endDt;
	private String lngTyp;
	private String sidx;
	private String sord;
	
	private String divDpst;
	private String rcpt01;
	private String rcpt02;
	private String rcpt04;
	private String rcpt05;
	private String rcpt08;
	private String rcpt09;
	private String rcpt11;
	private String rcpt16;
	private String rcpt17;
	private String rcpt30;
	private String rcpt31;
	private String rcpt40;
	private String tot;
	private String cncl;
	private String cnclYn;
	private String dpstCl;
	private String acntNm;
	private String dpstDt;
	private String transDt;
	private String payProcDt;
	private String billYymm;
	private String payObjAmt;
	private String billAmt;
	private String preRcptAmt;
	private String rcptAmt;
	private String prepayAplyAmt;
	private String dpstClNm;
	private String payTpNm;
	private String regrId;
	private String usrNm;
	private String regDate;
	private String custId;
	private String custNm;
	private String billDt;
	private String pymSeqNo;
	private String dpstSeqNo;
	private String prepayTransSeqNo;
	private String ambgTransSeqNo;
	private String payTp;
	private String billSeqNo;
	private String allotPrd;
	private String svcTelNo;
	private String useYymm;
	private String prodNm;
	private String svcNm;
	private String chgItmNm;
	private String ctrtId;
	private String prodConfigId;
	private String svcConfigId;
	private String chgItmCd;
	private String pymTp;
	private String rcptTp;
	
	private String searchSoId;
	private String searchCustId;
	private String searchDateTp;
	private String searchStatDt;
	private String searchEndDt;
	private String searchPymAcntId;
	
	private String cnclResn; // 취소사유
	private String inptMenuId; // 입력 메뉴ID	
	private String cnclDt;
	private String cnclEmpId;
	private String dpstProcDt;
	private String cnclEmpNm;
	private String cnclDttm;
	private String cnclrId;

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
	public String getCnclEmpNm() {
		return cnclEmpNm;
	}
	public void setCnclEmpNm(String cnclEmpNm) {
		this.cnclEmpNm = cnclEmpNm;
	}
	public String getDpstProcDt() {
		return dpstProcDt;
	}
	public void setDpstProcDt(String dpstProcDt) {
		this.dpstProcDt = dpstProcDt;
	}
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
	public String getPymAcntId() {
		return pymAcntId;
	}
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getPymDtTp() {
		return pymDtTp;
	}
	public void setPymDtTp(String pymDtTp) {
		this.pymDtTp = pymDtTp;
	}
	public String getStrtDt() {
		return strtDt;
	}
	public void setStrtDt(String strtDt) {
		this.strtDt = strtDt;
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
	public String getDivDpst() {
		return divDpst;
	}
	public void setDivDpst(String divDpst) {
		this.divDpst = divDpst;
	}
	public String getRcpt01() {
		return rcpt01;
	}
	public void setRcpt01(String rcpt01) {
		this.rcpt01 = rcpt01;
	}
	public String getRcpt02() {
		return rcpt02;
	}
	public void setRcpt02(String rcpt02) {
		this.rcpt02 = rcpt02;
	}
	public String getRcpt04() {
		return rcpt04;
	}
	public void setRcpt04(String rcpt04) {
		this.rcpt04 = rcpt04;
	}
	public String getRcpt05() {
		return rcpt05;
	}
	public void setRcpt05(String rcpt05) {
		this.rcpt05 = rcpt05;
	}
	public String getRcpt08() {
		return rcpt08;
	}
	public void setRcpt08(String rcpt08) {
		this.rcpt08 = rcpt08;
	}
	public String getRcpt09() {
		return rcpt09;
	}
	public void setRcpt09(String rcpt09) {
		this.rcpt09 = rcpt09;
	}
	public String getRcpt11() {
		return rcpt11;
	}
	public void setRcpt11(String rcpt11) {
		this.rcpt11 = rcpt11;
	}
	public String getRcpt16() {
		return rcpt16;
	}
	public void setRcpt16(String rcpt16) {
		this.rcpt16 = rcpt16;
	}
	public String getRcpt17() {
		return rcpt17;
	}
	public void setRcpt17(String rcpt17) {
		this.rcpt17 = rcpt17;
	}
	public String getRcpt30() {
		return rcpt30;
	}
	public void setRcpt30(String rcpt30) {
		this.rcpt30 = rcpt30;
	}
	public String getRcpt31() {
		return rcpt31;
	}
	public void setRcpt31(String rcpt31) {
		this.rcpt31 = rcpt31;
	}
	public String getRcpt40() {
		return rcpt40;
	}
	public void setRcpt40(String rcpt40) {
		this.rcpt40 = rcpt40;
	}
	public String getTot() {
		return tot;
	}
	public void setTot(String tot) {
		this.tot = tot;
	}
	public String getCncl() {
		return cncl;
	}
	public void setCncl(String cncl) {
		this.cncl = cncl;
	}
	public String getCnclYn() {
		return cnclYn;
	}
	public void setCnclYn(String cnclYn) {
		this.cnclYn = cnclYn;
	}
	public String getDpstCl() {
		return dpstCl;
	}
	public void setDpstCl(String dpstCl) {
		this.dpstCl = dpstCl;
	}
	public String getAcntNm() {
		return acntNm;
	}
	public void setAcntNm(String acntNm) {
		this.acntNm = acntNm;
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
	public String getPayProcDt() {
		return payProcDt;
	}
	public void setPayProcDt(String payProcDt) {
		this.payProcDt = payProcDt;
	}
	public String getBillYymm() {
		return billYymm;
	}
	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm;
	}
	public String getPayObjAmt() {
		return payObjAmt;
	}
	public void setPayObjAmt(String payObjAmt) {
		this.payObjAmt = payObjAmt;
	}
	public String getBillAmt() {
		return billAmt;
	}
	public void setBillAmt(String billAmt) {
		this.billAmt = billAmt;
	}
	public String getPreRcptAmt() {
		return preRcptAmt;
	}
	public void setPreRcptAmt(String preRcptAmt) {
		this.preRcptAmt = preRcptAmt;
	}
	public String getRcptAmt() {
		return rcptAmt;
	}
	public void setRcptAmt(String rcptAmt) {
		this.rcptAmt = rcptAmt;
	}
	public String getPrepayAplyAmt() {
		return prepayAplyAmt;
	}
	public void setPrepayAplyAmt(String prepayAplyAmt) {
		this.prepayAplyAmt = prepayAplyAmt;
	}
	public String getDpstClNm() {
		return dpstClNm;
	}
	public void setDpstClNm(String dpstClNm) {
		this.dpstClNm = dpstClNm;
	}
	public String getPayTpNm() {
		return payTpNm;
	}
	public void setPayTpNm(String payTpNm) {
		this.payTpNm = payTpNm;
	}
	public String getRegrId() {
		return regrId;
	}
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	public String getUsrNm() {
		return usrNm;
	}
	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustNm() {
		return custNm;
	}
	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}
	public String getBillDt() {
		return billDt;
	}
	public void setBillDt(String billDt) {
		this.billDt = billDt;
	}
	public String getPymSeqNo() {
		return pymSeqNo;
	}
	public void setPymSeqNo(String pymSeqNo) {
		this.pymSeqNo = pymSeqNo;
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
	public String getPayTp() {
		return payTp;
	}
	public void setPayTp(String payTp) {
		this.payTp = payTp;
	}
	public String getBillSeqNo() {
		return billSeqNo;
	}
	public void setBillSeqNo(String billSeqNo) {
		this.billSeqNo = billSeqNo;
	}
	public String getAllotPrd() {
		return allotPrd;
	}
	public void setAllotPrd(String allotPrd) {
		this.allotPrd = allotPrd;
	}
	public String getSvcTelNo() {
		return svcTelNo;
	}
	public void setSvcTelNo(String svcTelNo) {
		this.svcTelNo = svcTelNo;
	}
	public String getUseYymm() {
		return useYymm;
	}
	public void setUseYymm(String useYymm) {
		this.useYymm = useYymm;
	}
	public String getProdNm() {
		return prodNm;
	}
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}
	public String getSvcNm() {
		return svcNm;
	}
	public void setSvcNm(String svcNm) {
		this.svcNm = svcNm;
	}
	public String getChgItmNm() {
		return chgItmNm;
	}
	public void setChgItmNm(String chgItmNm) {
		this.chgItmNm = chgItmNm;
	}
	public String getCtrtId() {
		return ctrtId;
	}
	public void setCtrtId(String ctrtId) {
		this.ctrtId = ctrtId;
	}
	public String getProdConfigId() {
		return prodConfigId;
	}
	public void setProdConfigId(String prodConfigId) {
		this.prodConfigId = prodConfigId;
	}
	public String getSvcConfigId() {
		return svcConfigId;
	}
	public void setSvcConfigId(String svcConfigId) {
		this.svcConfigId = svcConfigId;
	}
	public String getChgItmCd() {
		return chgItmCd;
	}
	public void setChgItmCd(String chgItmCd) {
		this.chgItmCd = chgItmCd;
	}
	public String getPymTp() {
		return pymTp;
	}
	public void setPymTp(String pymTp) {
		this.pymTp = pymTp;
	}
	public String getRcptTp() {
		return rcptTp;
	}
	public void setRcptTp(String rcptTp) {
		this.rcptTp = rcptTp;
	}
	public String getSearchSoId() {
		return searchSoId;
	}
	public void setSearchSoId(String searchSoId) {
		this.searchSoId = searchSoId;
	}
	public String getSearchCustId() {
		return searchCustId;
	}
	public void setSearchCustId(String searchCustId) {
		this.searchCustId = searchCustId;
	}
	public String getSearchDateTp() {
		return searchDateTp;
	}
	public void setSearchDateTp(String searchDateTp) {
		this.searchDateTp = searchDateTp;
	}
	public String getSearchStatDt() {
		return searchStatDt;
	}
	public void setSearchStatDt(String searchStatDt) {
		this.searchStatDt = searchStatDt;
	}
	public String getSearchEndDt() {
		return searchEndDt;
	}
	public void setSearchEndDt(String searchEndDt) {
		this.searchEndDt = searchEndDt;
	}
	public String getSearchPymAcntId() {
		return searchPymAcntId;
	}
	public void setSearchPymAcntId(String searchPymAcntId) {
		this.searchPymAcntId = searchPymAcntId;
	}
	public String getCnclResn() {
		return cnclResn;
	}
	public void setCnclResn(String cnclResn) {
		this.cnclResn = cnclResn;
	}
	public String getInptMenuId() {
		return inptMenuId;
	}
	public void setInptMenuId(String inptMenuId) {
		this.inptMenuId = inptMenuId;
	}
	public String getCnclDt() {
		return cnclDt;
	}
	public void setCnclDt(String cnclDt) {
		this.cnclDt = cnclDt;
	}	
	public String getCnclEmpId() {
		return cnclEmpId;
	}
	public void setCnclEmpId(String cnclEmpId) {
		this.cnclEmpId = cnclEmpId;
	}
}
