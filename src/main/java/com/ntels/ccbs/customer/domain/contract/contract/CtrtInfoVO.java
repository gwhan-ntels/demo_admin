package com.ntels.ccbs.customer.domain.contract.contract;

import java.io.Serializable;
import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class CtrtInfoVO implements Serializable,CommonVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1610553872217756093L;
	/*
	 * 계약정보
	 */
	private String soId;
	private String soNm;
	private String ctrtId;
	private String custId;
	private String custNm;
	private String svcTelNo;
	private String svcTelNoAsMask;
	private String orderId;
	private String orderTp;
	private String orderTpNm;
	private String rateStrtDt;
	private String termDt;
	private String openDd;
	private String pymAcntId;
	private String pymAcntNm;
	private String pymCustNm;
	private String pymMthd;
	private String pymMthdNm;
	private String acntCustRel;
	private String acntCustRelNm;
	private String billMdmGiroYn;
	private String billMdmGiroYnNm;
	private String billMdmEmlYn;
	private String billMdmEmlYnNm;
	private String billMdmSmsYn;
	private String billMdmSmsYnNm;
	private String basicProdGrp;
	private String basicProdGrpNm;
	private String basicProdCd;
	private String basicProdCdNm;
	private String instlZipCd;
	private String instlBaseAddr;
	private String instlAddrDtl;
	private String instlCity;
	private String instlState;
	private String instlStateNm;
	private String ctrtStat;
	private String ctrtStatNm;
	private String serviceId;
	private String firstUsrId;
	private String firstUsrNm;
	private String firstOrgId;
	private String firstOrgNm;
	private Date regDate;
	private String salesUsrId;
	private String salesUsrNm;
	private String salesOrgId;
	private String salesOrgNm;
	private String usrId;
	private String usrNm;
	private String orgId;
	private String orgNm;
	private Date chgDate;
	
	private String custTp;
	private String custTpNm;
	private String custCl;
	private String custClNm;
	private String corpRegNoAsMask;
	private String procRate;
	private String ctrtNm;
	/**
	 * @return the soId
	 */
	public String getSoId() {
		return soId;
	}
	public String getCtrtNm() {
		return ctrtNm;
	}
	public void setCtrtNm(String ctrtNm) {
		this.ctrtNm = ctrtNm;
	}
	/**
	 * @param soId the soId to set
	 */
	public void setSoId(String soId) {
		this.soId = soId;
	}
	/**
	 * @return the soNm
	 */
	public String getSoNm() {
		return soNm;
	}
	/**
	 * @param soNm the soNm to set
	 */
	public void setSoNm(String soNm) {
		this.soNm = soNm;
	}
	/**
	 * @return the ctrtId
	 */
	public String getCtrtId() {
		return ctrtId;
	}
	/**
	 * @param ctrtId the ctrtId to set
	 */
	public void setCtrtId(String ctrtId) {
		this.ctrtId = ctrtId;
	}
	/**
	 * @return the custId
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * @return the custNm
	 */
	public String getCustNm() {
		return custNm;
	}
	/**
	 * @param custNm the custNm to set
	 */
	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}
	/**
	 * @return the svcTelNo
	 */
	public String getSvcTelNo() {
		return svcTelNo;
	}
	/**
	 * @param svcTelNo the svcTelNo to set
	 */
	public void setSvcTelNo(String svcTelNo) {
		this.svcTelNo = svcTelNo;
	}
	/**
	 * @return the svcTelNoAsMask
	 */
	public String getSvcTelNoAsMask() {
		return svcTelNoAsMask;
	}
	/**
	 * @param svcTelNoAsMask the svcTelNoAsMask to set
	 */
	public void setSvcTelNoAsMask(String svcTelNoAsMask) {
		this.svcTelNoAsMask = svcTelNoAsMask;
	}
	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the orderTp
	 */
	public String getOrderTp() {
		return orderTp;
	}
	/**
	 * @param orderTp the orderTp to set
	 */
	public void setOrderTp(String orderTp) {
		this.orderTp = orderTp;
	}
	/**
	 * @return the orderTpNm
	 */
	public String getOrderTpNm() {
		return orderTpNm;
	}
	/**
	 * @param orderTpNm the orderTpNm to set
	 */
	public void setOrderTpNm(String orderTpNm) {
		this.orderTpNm = orderTpNm;
	}
	/**
	 * @return the rateStrtDt
	 */
	public String getRateStrtDt() {
		return rateStrtDt;
	}
	/**
	 * @param rateStrtDt the rateStrtDt to set
	 */
	public void setRateStrtDt(String rateStrtDt) {
		this.rateStrtDt = rateStrtDt;
	}
	/**
	 * @return the termDt
	 */
	public String getTermDt() {
		return termDt;
	}
	/**
	 * @param termDt the termDt to set
	 */
	public void setTermDt(String termDt) {
		this.termDt = termDt;
	}
	/**
	 * @return the openDd
	 */
	public String getOpenDd() {
		return openDd;
	}
	/**
	 * @param openDd the openDd to set
	 */
	public void setOpenDd(String openDd) {
		this.openDd = openDd;
	}
	/**
	 * @return the pymAcntId
	 */
	public String getPymAcntId() {
		return pymAcntId;
	}
	/**
	 * @param pymAcntId the pymAcntId to set
	 */
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}
	/**
	 * @return the pymAcntNm
	 */
	public String getPymAcntNm() {
		return pymAcntNm;
	}
	/**
	 * @param pymAcntNm the pymAcntNm to set
	 */
	public void setPymAcntNm(String pymAcntNm) {
		this.pymAcntNm = pymAcntNm;
	}
	/**
	 * @return the pymCustNm
	 */
	public String getPymCustNm() {
		return pymCustNm;
	}
	/**
	 * @param pymCustNm the pymCustNm to set
	 */
	public void setPymCustNm(String pymCustNm) {
		this.pymCustNm = pymCustNm;
	}
	/**
	 * @return the pymMthd
	 */
	public String getPymMthd() {
		return pymMthd;
	}
	/**
	 * @param pymMthd the pymMthd to set
	 */
	public void setPymMthd(String pymMthd) {
		this.pymMthd = pymMthd;
	}
	/**
	 * @return the pymMthdNm
	 */
	public String getPymMthdNm() {
		return pymMthdNm;
	}
	/**
	 * @param pymMthdNm the pymMthdNm to set
	 */
	public void setPymMthdNm(String pymMthdNm) {
		this.pymMthdNm = pymMthdNm;
	}
	/**
	 * @return the acntCustRel
	 */
	public String getAcntCustRel() {
		return acntCustRel;
	}
	/**
	 * @param acntCustRel the acntCustRel to set
	 */
	public void setAcntCustRel(String acntCustRel) {
		this.acntCustRel = acntCustRel;
	}
	/**
	 * @return the acntCustRelNm
	 */
	public String getAcntCustRelNm() {
		return acntCustRelNm;
	}
	/**
	 * @param acntCustRelNm the acntCustRelNm to set
	 */
	public void setAcntCustRelNm(String acntCustRelNm) {
		this.acntCustRelNm = acntCustRelNm;
	}
	/**
	 * @return the billMdmGiroYn
	 */
	public String getBillMdmGiroYn() {
		return billMdmGiroYn;
	}
	/**
	 * @param billMdmGiroYn the billMdmGiroYn to set
	 */
	public void setBillMdmGiroYn(String billMdmGiroYn) {
		this.billMdmGiroYn = billMdmGiroYn;
	}
	/**
	 * @return the billMdmGiroYnNm
	 */
	public String getBillMdmGiroYnNm() {
		return billMdmGiroYnNm;
	}
	/**
	 * @param billMdmGiroYnNm the billMdmGiroYnNm to set
	 */
	public void setBillMdmGiroYnNm(String billMdmGiroYnNm) {
		this.billMdmGiroYnNm = billMdmGiroYnNm;
	}
	/**
	 * @return the billMdmEmlYn
	 */
	public String getBillMdmEmlYn() {
		return billMdmEmlYn;
	}
	/**
	 * @param billMdmEmlYn the billMdmEmlYn to set
	 */
	public void setBillMdmEmlYn(String billMdmEmlYn) {
		this.billMdmEmlYn = billMdmEmlYn;
	}
	/**
	 * @return the billMdmEmlYnNm
	 */
	public String getBillMdmEmlYnNm() {
		return billMdmEmlYnNm;
	}
	/**
	 * @param billMdmEmlYnNm the billMdmEmlYnNm to set
	 */
	public void setBillMdmEmlYnNm(String billMdmEmlYnNm) {
		this.billMdmEmlYnNm = billMdmEmlYnNm;
	}
	/**
	 * @return the billMdmSmsYn
	 */
	public String getBillMdmSmsYn() {
		return billMdmSmsYn;
	}
	/**
	 * @param billMdmSmsYn the billMdmSmsYn to set
	 */
	public void setBillMdmSmsYn(String billMdmSmsYn) {
		this.billMdmSmsYn = billMdmSmsYn;
	}
	/**
	 * @return the billMdmSmsYnNm
	 */
	public String getBillMdmSmsYnNm() {
		return billMdmSmsYnNm;
	}
	/**
	 * @param billMdmSmsYnNm the billMdmSmsYnNm to set
	 */
	public void setBillMdmSmsYnNm(String billMdmSmsYnNm) {
		this.billMdmSmsYnNm = billMdmSmsYnNm;
	}
	/**
	 * @return the basicProdGrp
	 */
	public String getBasicProdGrp() {
		return basicProdGrp;
	}
	/**
	 * @param basicProdGrp the basicProdGrp to set
	 */
	public void setBasicProdGrp(String basicProdGrp) {
		this.basicProdGrp = basicProdGrp;
	}
	/**
	 * @return the basicProdGrpNm
	 */
	public String getBasicProdGrpNm() {
		return basicProdGrpNm;
	}
	/**
	 * @param basicProdGrpNm the basicProdGrpNm to set
	 */
	public void setBasicProdGrpNm(String basicProdGrpNm) {
		this.basicProdGrpNm = basicProdGrpNm;
	}
	/**
	 * @return the basicProdCd
	 */
	public String getBasicProdCd() {
		return basicProdCd;
	}
	/**
	 * @param basicProdCd the basicProdCd to set
	 */
	public void setBasicProdCd(String basicProdCd) {
		this.basicProdCd = basicProdCd;
	}
	/**
	 * @return the basicProdCdNm
	 */
	public String getBasicProdCdNm() {
		return basicProdCdNm;
	}
	/**
	 * @param basicProdCdNm the basicProdCdNm to set
	 */
	public void setBasicProdCdNm(String basicProdCdNm) {
		this.basicProdCdNm = basicProdCdNm;
	}
	/**
	 * @return the instlZipCd
	 */
	public String getInstlZipCd() {
		return instlZipCd;
	}
	/**
	 * @param instlZipCd the instlZipCd to set
	 */
	public void setInstlZipCd(String instlZipCd) {
		this.instlZipCd = instlZipCd;
	}
	/**
	 * @return the instlBaseAddr
	 */
	public String getInstlBaseAddr() {
		return instlBaseAddr;
	}
	/**
	 * @param instlBaseAddr the instlBaseAddr to set
	 */
	public void setInstlBaseAddr(String instlBaseAddr) {
		this.instlBaseAddr = instlBaseAddr;
	}
	/**
	 * @return the instlAddrDtl
	 */
	public String getInstlAddrDtl() {
		return instlAddrDtl;
	}
	/**
	 * @param instlAddrDtl the instlAddrDtl to set
	 */
	public void setInstlAddrDtl(String instlAddrDtl) {
		this.instlAddrDtl = instlAddrDtl;
	}
	/**
	 * @return the instlCity
	 */
	public String getInstlCity() {
		return instlCity;
	}
	/**
	 * @param instlCity the instlCity to set
	 */
	public void setInstlCity(String instlCity) {
		this.instlCity = instlCity;
	}
	/**
	 * @return the instlState
	 */
	public String getInstlState() {
		return instlState;
	}
	/**
	 * @param instlState the instlState to set
	 */
	public void setInstlState(String instlState) {
		this.instlState = instlState;
	}
	/**
	 * @return the instlStateNm
	 */
	public String getInstlStateNm() {
		return instlStateNm;
	}
	/**
	 * @param instlStateNm the instlStateNm to set
	 */
	public void setInstlStateNm(String instlStateNm) {
		this.instlStateNm = instlStateNm;
	}
	/**
	 * @return the ctrtStat
	 */
	public String getCtrtStat() {
		return ctrtStat;
	}
	/**
	 * @param ctrtStat the ctrtStat to set
	 */
	public void setCtrtStat(String ctrtStat) {
		this.ctrtStat = ctrtStat;
	}
	/**
	 * @return the ctrtStatNm
	 */
	public String getCtrtStatNm() {
		return ctrtStatNm;
	}
	/**
	 * @param ctrtStatNm the ctrtStatNm to set
	 */
	public void setCtrtStatNm(String ctrtStatNm) {
		this.ctrtStatNm = ctrtStatNm;
	}
	/**
	 * @return the serviceId
	 */
	public String getServiceId() {
		return serviceId;
	}
	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	/**
	 * @return the firstUsrId
	 */
	public String getFirstUsrId() {
		return firstUsrId;
	}
	/**
	 * @param firstUsrId the firstUsrId to set
	 */
	public void setFirstUsrId(String firstUsrId) {
		this.firstUsrId = firstUsrId;
	}
	/**
	 * @return the firstUsrNm
	 */
	public String getFirstUsrNm() {
		return firstUsrNm;
	}
	/**
	 * @param firstUsrNm the firstUsrNm to set
	 */
	public void setFirstUsrNm(String firstUsrNm) {
		this.firstUsrNm = firstUsrNm;
	}
	/**
	 * @return the firstOrgId
	 */
	public String getFirstOrgId() {
		return firstOrgId;
	}
	/**
	 * @param firstOrgId the firstOrgId to set
	 */
	public void setFirstOrgId(String firstOrgId) {
		this.firstOrgId = firstOrgId;
	}
	/**
	 * @return the firstOrgNm
	 */
	public String getFirstOrgNm() {
		return firstOrgNm;
	}
	/**
	 * @param firstOrgNm the firstOrgNm to set
	 */
	public void setFirstOrgNm(String firstOrgNm) {
		this.firstOrgNm = firstOrgNm;
	}
	/**
	 * @return the regDate
	 */
	public Date getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	/**
	 * @return the salesUsrId
	 */
	public String getSalesUsrId() {
		return salesUsrId;
	}
	/**
	 * @param salesUsrId the salesUsrId to set
	 */
	public void setSalesUsrId(String salesUsrId) {
		this.salesUsrId = salesUsrId;
	}
	/**
	 * @return the salesUsrNm
	 */
	public String getSalesUsrNm() {
		return salesUsrNm;
	}
	/**
	 * @param salesUsrNm the salesUsrNm to set
	 */
	public void setSalesUsrNm(String salesUsrNm) {
		this.salesUsrNm = salesUsrNm;
	}
	/**
	 * @return the salesOrgId
	 */
	public String getSalesOrgId() {
		return salesOrgId;
	}
	/**
	 * @param salesOrgId the salesOrgId to set
	 */
	public void setSalesOrgId(String salesOrgId) {
		this.salesOrgId = salesOrgId;
	}
	/**
	 * @return the salesOrgNm
	 */
	public String getSalesOrgNm() {
		return salesOrgNm;
	}
	/**
	 * @param salesOrgNm the salesOrgNm to set
	 */
	public void setSalesOrgNm(String salesOrgNm) {
		this.salesOrgNm = salesOrgNm;
	}
	/**
	 * @return the usrId
	 */
	public String getUsrId() {
		return usrId;
	}
	/**
	 * @param usrId the usrId to set
	 */
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	/**
	 * @return the usrNm
	 */
	public String getUsrNm() {
		return usrNm;
	}
	/**
	 * @param usrNm the usrNm to set
	 */
	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}
	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}
	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	/**
	 * @return the orgNm
	 */
	public String getOrgNm() {
		return orgNm;
	}
	/**
	 * @param orgNm the orgNm to set
	 */
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	/**
	 * @return the chgDate
	 */
	public Date getChgDate() {
		return chgDate;
	}
	/**
	 * @param chgDate the chgDate to set
	 */
	public void setChgDate(Date chgDate) {
		this.chgDate = chgDate;
	}
	/**
	 * @return the custTp
	 */
	public String getCustTp() {
		return custTp;
	}
	/**
	 * @param custTp the custTp to set
	 */
	public void setCustTp(String custTp) {
		this.custTp = custTp;
	}
	/**
	 * @return the custTpNm
	 */
	public String getCustTpNm() {
		return custTpNm;
	}
	/**
	 * @param custTpNm the custTpNm to set
	 */
	public void setCustTpNm(String custTpNm) {
		this.custTpNm = custTpNm;
	}
	/**
	 * @return the custCl
	 */
	public String getCustCl() {
		return custCl;
	}
	/**
	 * @param custCl the custCl to set
	 */
	public void setCustCl(String custCl) {
		this.custCl = custCl;
	}
	/**
	 * @return the custClNm
	 */
	public String getCustClNm() {
		return custClNm;
	}
	/**
	 * @param custClNm the custClNm to set
	 */
	public void setCustClNm(String custClNm) {
		this.custClNm = custClNm;
	}
	/**
	 * @return the corpRegNoAsMask
	 */
	public String getCorpRegNoAsMask() {
		return corpRegNoAsMask;
	}
	/**
	 * @param corpRegNoAsMask the corpRegNoAsMask to set
	 */
	public void setCorpRegNoAsMask(String corpRegNoAsMask) {
		this.corpRegNoAsMask = corpRegNoAsMask;
	}
	/**
	 * @return the procRate
	 */
	public String getProcRate() {
		return procRate;
	}
	/**
	 * @param procRate the procRate to set
	 */
	public void setProcRate(String procRate) {
		this.procRate = procRate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CtrtInfoVO [soId=");
		builder.append(soId);
		builder.append(", ctrtId=");
		builder.append(ctrtId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", custNm=");
		builder.append(custNm);
		builder.append(", svcTelNo=");
		builder.append(svcTelNo);
		builder.append(", svcTelNoAsMask=");
		builder.append(svcTelNoAsMask);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", orderTp=");
		builder.append(orderTp);
		builder.append(", orderTpNm=");
		builder.append(orderTpNm);
		builder.append(", rateStrtDt=");
		builder.append(rateStrtDt);
		builder.append(", termDt=");
		builder.append(termDt);
		builder.append(", openDd=");
		builder.append(openDd);
		builder.append(", pymAcntId=");
		builder.append(pymAcntId);
		builder.append(", pymAcntNm=");
		builder.append(pymAcntNm);
		builder.append(", pymCustNm=");
		builder.append(pymCustNm);
		builder.append(", pymMthd=");
		builder.append(pymMthd);
		builder.append(", pymMthdNm=");
		builder.append(pymMthdNm);
		builder.append(", acntCustRel=");
		builder.append(acntCustRel);
		builder.append(", acntCustRelNm=");
		builder.append(acntCustRelNm);
		builder.append(", billMdmGiroYn=");
		builder.append(billMdmGiroYn);
		builder.append(", billMdmGiroYnNm=");
		builder.append(billMdmGiroYnNm);
		builder.append(", billMdmEmlYn=");
		builder.append(billMdmEmlYn);
		builder.append(", billMdmEmlYnNm=");
		builder.append(billMdmEmlYnNm);
		builder.append(", billMdmSmsYn=");
		builder.append(billMdmSmsYn);
		builder.append(", billMdmSmsYnNm=");
		builder.append(billMdmSmsYnNm);
		builder.append(", basicProdGrp=");
		builder.append(basicProdGrp);
		builder.append(", basicProdGrpNm=");
		builder.append(basicProdGrpNm);
		builder.append(", basicProdCd=");
		builder.append(basicProdCd);
		builder.append(", basicProdCdNm=");
		builder.append(basicProdCdNm);
		builder.append(", instlZipCd=");
		builder.append(instlZipCd);
		builder.append(", instlBaseAddr=");
		builder.append(instlBaseAddr);
		builder.append(", instlAddrDtl=");
		builder.append(instlAddrDtl);
		builder.append(", instlCity=");
		builder.append(instlCity);
		builder.append(", instlState=");
		builder.append(instlState);
		builder.append(", instlStateNm=");
		builder.append(instlStateNm);
		builder.append(", ctrtStat=");
		builder.append(ctrtStat);
		builder.append(", ctrtStatNm=");
		builder.append(ctrtStatNm);
		builder.append(", serviceId=");
		builder.append(serviceId);
		builder.append(", firstUsrId=");
		builder.append(firstUsrId);
		builder.append(", firstUsrNm=");
		builder.append(firstUsrNm);
		builder.append(", firstOrgId=");
		builder.append(firstOrgId);
		builder.append(", firstOrgNm=");
		builder.append(firstOrgNm);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append(", salesUsrId=");
		builder.append(salesUsrId);
		builder.append(", salesUsrNm=");
		builder.append(salesUsrNm);
		builder.append(", salesOrgId=");
		builder.append(salesOrgId);
		builder.append(", salesOrgNm=");
		builder.append(salesOrgNm);
		builder.append(", usrId=");
		builder.append(usrId);
		builder.append(", usrNm=");
		builder.append(usrNm);
		builder.append(", orgId=");
		builder.append(orgId);
		builder.append(", orgNm=");
		builder.append(orgNm);
		builder.append(", chgDate=");
		builder.append(chgDate);
		builder.append("]");
		return builder.toString();
	}

	
}

