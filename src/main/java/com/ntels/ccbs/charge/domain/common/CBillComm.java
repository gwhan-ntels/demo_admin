/**
 * @FileName
 * CBillComm.java
 * @Project
 * ccbs_batch
 * @Date
 * 2016. 5. 10.
 * @Writter
 * ntels_shlee
 * @EditHistory
 *
 * @Discript
 */
package com.ntels.ccbs.charge.domain.common;
import java.sql.Timestamp;
import java.util.Date;
public class CBillComm {
	
	private Double adjAmt; /* 조정금액 */
	private Double adjPrvBillAmt; /* 조정전청구금액 */
	private Double billAmt; /* 청구금액 */
	private Double exrate; /* 환율 */
	private Double rcptAmt; /* 수납금액 */
	private Double unpayAmt; /* 미납금액 */
	private Double wonBillAmt; /* 원화청구금액 */
	private Long   usgAmt; /* 사용량 */
	private Long   usgCnt; /* 사용건수 */
	private Long   usgQty; /* 사용건수 */
	private String atrtCorpId; /* ATRT_CORP_ID */
	private String atrtEmpId; /* ATRT_EMP_ID */
	private String billCycl; /* 청구주기 */	
	private String billDt; /* 청구일자 */
	private String billMmTp; /* 0: 당월 1: 전월 2: 전전월 */
	private String billSeqNo; /* 청구일련번호 */
	private String billYymm; /* 청구년월 */
	private String bizCl; /* 사업구분 */
	private String checkDt;
	private String chgrId; /* 수정자 */
	private String chrgCtgry;
	private String chrgItmCd; /* 청구Process단위에서 관리되는 항목으로 약관에서 정의하는 기준의 요금을 관리 Sequence : CI99999999 */
	private String clcWrkNo  ;    
	private String crncyCd; /* 화폐코드 */
	private String ctrtId; /* 계약ID */
	private String custId; /* 고객ID */
	private String debtProcYn; /* 제각처리여부 */
	private String exrateAplyDt; /* 환율 적용일자 */
	private String fullPayYn; /* 완납 여부 */
	private String grpId; /* 단체ID */
	private String hotbillSeqNo;
	private String payDueDt; /* 납기마감일자(YYYYMMDD) */
	private String prepayOccSeqNo; /* PREPAY_OCC_SEQ_NO */
	private String prepdCtrtId; /* 선납계약ID */
	private String prodCd; /* PROD_CD */
	private String prodCmpsId; /* 상품구성ID */
	private String prodGrp; /* PROD_GRP */
	private String pymAcntId; /* 납부계정ID */
	private String regrId; /* 등록자 */
	private String saleItmCd; /* SALE_ITM_CD */
	private String saleTp; /* BLIV016 01: 정상매출(+) 02: 정상매출(-) 03: 재과금매출 04: 선납청구 05: 선납대상매출 06: 재과금수수료 07: 선수금대상매출 08: 정산매출 */
	private String soId; /* 사업구분 */
	private String svcCd; /* 사업자가 제공하는 물리적인 서비스 Sequence : SV99999999 */
	private String svcCmpsId; /* 서비스구성ID */
	private String svcGrp; /* 서비스군 */
	private String useYymm; /* 사용년월(YYYYMM) */
	private Timestamp chgDate; /* 수정일시 */
	private Timestamp regDate; /* 등록일시 */
	private String beforeYymm; /*  */
	private String before2Yymm;
	private Double aplyAmt; /* 적용 금액 */
	
	private String pSeq;
	
	
	
	
	/* 분리과금 항목  NBLIV01M03  */
	private String spltChrgItmCd;
	private String spltPymTp;
	private String itllmtVal;
	private String itllmtCl;
	private String diviKey;
	private String toPymAcntId;
	private Double toItlmtVal;
    /* --------------------------------------*/
	
	/* 연체료 생성 관련항목 - NBLIV01M05 */
	private String billAplyYn;
	private Double delayFee;
	private String delayLevyYn;
	private Double upayAmt;
	private String chAppYn;
	private String chAppYymm;
	private String chDelayImposeYn;
	private Double delayRate;
	private String befBillYymm;
	private String befPayDueDt;
	private String setItmId;
	
    /* --------------------------------------*/
	
	
	/* 연체료 생성 관련항목 - NBLIV01M06 */
	private String oldBillSeqNo;
	/* --------------------------------------*/
	
	
	/* 연체료 생성 관련항목 - NBLIV01M07 */
	private Double autoAcRate;
    /* --------------------------------------*/
	
	/* 요금 조정  - NBLIV01M08 */
	private Double adjNo;
	private String adjUpdYn;
	private Double adjApplAmt;
	/* --------------------------------------*/
	
	/* 세금 관련 */
	private Double vat;
	private Double sct;
	private Double vatAdjAmt;
	private Double sctAdjAmt;
	/* --------------------------------------*/
	
	
	 
		public void setTimeInfo()
		{
//			Timestamp tmpT;
//			Date      tmpD = new Date();
//			   	 tmpT  = new Timestamp(tmpD.getTime());
		         this.setRegDate(new Timestamp(new Date().getTime()));
		         this.setChgDate(new Timestamp(new Date().getTime()));
		}


		/**
		 * @return the clcWrkNo
		 */
		public String getClcWrkNo() {
			return clcWrkNo;
		}


		/**
		 * @param clcWrkNo the clcWrkNo to set
		 */
		public void setClcWrkNo(String clcWrkNo) {
			this.clcWrkNo = clcWrkNo;
		}


		/**
		 * @return the atrtCorpId
		 */
		public String getAtrtCorpId() {
			return atrtCorpId;
		}


		/**
		 * @param atrtCorpId the atrtCorpId to set
		 */
		public void setAtrtCorpId(String atrtCorpId) {
			this.atrtCorpId = atrtCorpId;
		}


		/**
		 * @return the atrtEmpId
		 */
		public String getAtrtEmpId() {
			return atrtEmpId;
		}


		/**
		 * @param atrtEmpId the atrtEmpId to set
		 */
		public void setAtrtEmpId(String atrtEmpId) {
			this.atrtEmpId = atrtEmpId;
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


		/**
		 * @return the billMmTp
		 */
		public String getBillMmTp() {
			return billMmTp;
		}


		/**
		 * @param billMmTp the billMmTp to set
		 */
		public void setBillMmTp(String billMmTp) {
			this.billMmTp = billMmTp;
		}


		/**
		 * @return the billSeqNo
		 */
		public String getBillSeqNo() {
			return billSeqNo;
		}


		/**
		 * @param billSeqNo the billSeqNo to set
		 */
		public void setBillSeqNo(String billSeqNo) {
			this.billSeqNo = billSeqNo;
		}


		/**
		 * @return the billYymm
		 */
		public String getBillYymm() {
			return billYymm;
		}


		/**
		 * @param billYymm the billYymm to set
		 */
		public void setBillYymm(String billYymm) {
			this.billYymm = billYymm;
		}


		/**
		 * @return the bizCl
		 */
		public String getBizCl() {
			return bizCl;
		}


		/**
		 * @param bizCl the bizCl to set
		 */
		public void setBizCl(String bizCl) {
			this.bizCl = bizCl;
		}


		/**
		 * @return the chgrId
		 */
		public String getChgrId() {
			return chgrId;
		}


		/**
		 * @param chgrId the chgrId to set
		 */
		public void setChgrId(String chgrId) {
			this.chgrId = chgrId;
		}


		/**
		 * @return the chrgItmCd
		 */
		public String getChrgItmCd() {
			return chrgItmCd;
		}


		/**
		 * @param chrgItmCd the chrgItmCd to set
		 */
		public void setChrgItmCd(String chrgItmCd) {
			this.chrgItmCd = chrgItmCd;
		}


		/**
		 * @return the crncyCd
		 */
		public String getCrncyCd() {
			return crncyCd;
		}


		/**
		 * @param crncyCd the crncyCd to set
		 */
		public void setCrncyCd(String crncyCd) {
			this.crncyCd = crncyCd;
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
		 * @return the debtProcYn
		 */
		public String getDebtProcYn() {
			return debtProcYn;
		}


		/**
		 * @param debtProcYn the debtProcYn to set
		 */
		public void setDebtProcYn(String debtProcYn) {
			this.debtProcYn = debtProcYn;
		}


		/**
		 * @return the exrateAplyDt
		 */
		public String getExrateAplyDt() {
			return exrateAplyDt;
		}


		/**
		 * @param exrateAplyDt the exrateAplyDt to set
		 */
		public void setExrateAplyDt(String exrateAplyDt) {
			this.exrateAplyDt = exrateAplyDt;
		}


		/**
		 * @return the fullPayYn
		 */
		public String getFullPayYn() {
			return fullPayYn;
		}


		/**
		 * @param fullPayYn the fullPayYn to set
		 */
		public void setFullPayYn(String fullPayYn) {
			this.fullPayYn = fullPayYn;
		}


		/**
		 * @return the grpId
		 */
		public String getGrpId() {
			return grpId;
		}


		/**
		 * @param grpId the grpId to set
		 */
		public void setGrpId(String grpId) {
			this.grpId = grpId;
		}


		/**
		 * @return the payDueDt
		 */
		public String getPayDueDt() {
			return payDueDt;
		}


		/**
		 * @param payDueDt the payDueDt to set
		 */
		public void setPayDueDt(String payDueDt) {
			this.payDueDt = payDueDt;
		}


		/**
		 * @return the prepayOccSeqNo
		 */
		public String getPrepayOccSeqNo() {
			return prepayOccSeqNo;
		}


		/**
		 * @param prepayOccSeqNo the prepayOccSeqNo to set
		 */
		public void setPrepayOccSeqNo(String prepayOccSeqNo) {
			this.prepayOccSeqNo = prepayOccSeqNo;
		}


		/**
		 * @return the prepdCtrtId
		 */
		public String getPrepdCtrtId() {
			return prepdCtrtId;
		}


		/**
		 * @param prepdCtrtId the prepdCtrtId to set
		 */
		public void setPrepdCtrtId(String prepdCtrtId) {
			this.prepdCtrtId = prepdCtrtId;
		}


		/**
		 * @return the prodCd
		 */
		public String getProdCd() {
			return prodCd;
		}


		/**
		 * @param prodCd the prodCd to set
		 */
		public void setProdCd(String prodCd) {
			this.prodCd = prodCd;
		}


		/**
		 * @return the prodCmpsId
		 */
		public String getProdCmpsId() {
			return prodCmpsId;
		}


		/**
		 * @param prodCmpsId the prodCmpsId to set
		 */
		public void setProdCmpsId(String prodCmpsId) {
			this.prodCmpsId = prodCmpsId;
		}


		/**
		 * @return the prodGrp
		 */
		public String getProdGrp() {
			return prodGrp;
		}


		/**
		 * @param prodGrp the prodGrp to set
		 */
		public void setProdGrp(String prodGrp) {
			this.prodGrp = prodGrp;
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
		 * @return the regrId
		 */
		public String getRegrId() {
			return regrId;
		}


		/**
		 * @param regrId the regrId to set
		 */
		public void setRegrId(String regrId) {
			this.regrId = regrId;
		}


		/**
		 * @return the saleItmCd
		 */
		public String getSaleItmCd() {
			return saleItmCd;
		}


		/**
		 * @param saleItmCd the saleItmCd to set
		 */
		public void setSaleItmCd(String saleItmCd) {
			this.saleItmCd = saleItmCd;
		}


		/**
		 * @return the saleTp
		 */
		public String getSaleTp() {
			return saleTp;
		}


		/**
		 * @param saleTp the saleTp to set
		 */
		public void setSaleTp(String saleTp) {
			this.saleTp = saleTp;
		}


		/**
		 * @return the soId
		 */
		public String getSoId() {
			return soId;
		}


		/**
		 * @param soId the soId to set
		 */
		public void setSoId(String soId) {
			this.soId = soId;
		}


		/**
		 * @return the svcCd
		 */
		public String getSvcCd() {
			return svcCd;
		}


		/**
		 * @param svcCd the svcCd to set
		 */
		public void setSvcCd(String svcCd) {
			this.svcCd = svcCd;
		}


		/**
		 * @return the svcCmpsId
		 */
		public String getSvcCmpsId() {
			return svcCmpsId;
		}


		/**
		 * @param svcCmpsId the svcCmpsId to set
		 */
		public void setSvcCmpsId(String svcCmpsId) {
			this.svcCmpsId = svcCmpsId;
		}


		/**
		 * @return the svcGrp
		 */
		public String getSvcGrp() {
			return svcGrp;
		}


		/**
		 * @param svcGrp the svcGrp to set
		 */
		public void setSvcGrp(String svcGrp) {
			this.svcGrp = svcGrp;
		}


		/**
		 * @return the useYymm
		 */
		public String getUseYymm() {
			return useYymm;
		}


		/**
		 * @param useYymm the useYymm to set
		 */
		public void setUseYymm(String useYymm) {
			this.useYymm = useYymm;
		}


		/**
		 * @return the chgDate
		 */
		public Timestamp getChgDate() {
			return chgDate;
		}


		/**
		 * @param chgDate the chgDate to set
		 */
		public void setChgDate(Timestamp chgDate) {
			this.chgDate = chgDate;
		}


		/**
		 * @return the regDate
		 */
		public Timestamp getRegDate() {
			return regDate;
		}


		/**
		 * @param regDate the regDate to set
		 */
		public void setRegDate(Timestamp regDate) {
			this.regDate = regDate;
		}


		/**
		 * @return the adjAmt
		 */
		public Double getAdjAmt() {
			return adjAmt;
		}


		/**
		 * @param adjAmt the adjAmt to set
		 */
		public void setAdjAmt(Double adjAmt) {
			this.adjAmt = adjAmt;
		}


		/**
		 * @return the adjPrvBillAmt
		 */
		public Double getAdjPrvBillAmt() {
			return adjPrvBillAmt;
		}


		/**
		 * @param adjPrvBillAmt the adjPrvBillAmt to set
		 */
		public void setAdjPrvBillAmt(Double adjPrvBillAmt) {
			this.adjPrvBillAmt = adjPrvBillAmt;
		}


		/**
		 * @return the billAmt
		 */
		public Double getBillAmt() {
			return billAmt;
		}


		/**
		 * @param billAmt the billAmt to set
		 */
		public void setBillAmt(Double billAmt) {
			this.billAmt = billAmt;
		}


		/**
		 * @return the exrate
		 */
		public Double getExrate() {
			return exrate;
		}


		/**
		 * @param exrate the exrate to set
		 */
		public void setExrate(Double exrate) {
			this.exrate = exrate;
		}


		/**
		 * @return the rcptAmt
		 */
		public Double getRcptAmt() {
			return rcptAmt;
		}


		/**
		 * @param rcptAmt the rcptAmt to set
		 */
		public void setRcptAmt(Double rcptAmt) {
			this.rcptAmt = rcptAmt;
		}


		/**
		 * @return the unpayAmt
		 */
		public Double getUnpayAmt() {
			return unpayAmt;
		}


		/**
		 * @param unpayAmt the unpayAmt to set
		 */
		public void setUnpayAmt(Double unpayAmt) {
			this.unpayAmt = unpayAmt;
		}


		/**
		 * @return the wonBillAmt
		 */
		public Double getWonBillAmt() {
			return wonBillAmt;
		}


		/**
		 * @param wonBillAmt the wonBillAmt to set
		 */
		public void setWonBillAmt(Double wonBillAmt) {
			this.wonBillAmt = wonBillAmt;
		}


		/**
		 * @return the usgAmt
		 */
		public Long getUsgAmt() {
			return usgAmt;
		}


		/**
		 * @param usgAmt the usgAmt to set
		 */
		public void setUsgAmt(Long usgAmt) {
			this.usgAmt = usgAmt;
		}


		/**
		 * @return the usgCnt
		 */
		public Long getUsgCnt() {
			return usgCnt;
		}


		/**
		 * @param usgCnt the usgCnt to set
		 */
		public void setUsgCnt(Long usgCnt) {
			this.usgCnt = usgCnt;
		}


		/**
		 * @return the usgQty
		 */
		public Long getUsgQty() {
			return usgQty;
		}


		/**
		 * @param usgQty the usgQty to set
		 */
		public void setUsgQty(Long usgQty) {
			this.usgQty = usgQty;
		}


		/**
		 * @return the chrgCtgry
		 */
		public String getChrgCtgry() {
			return chrgCtgry;
		}


		/**
		 * @param chrgCtgry the chrgCtgry to set
		 */
		public void setChrgCtgry(String chrgCtgry) {
			this.chrgCtgry = chrgCtgry;
		}


		/**
		 * @return the beforeYymm
		 */
		public String getBeforeYymm() {
			return beforeYymm;
		}


		/**
		 * @param beforeYymm the beforeYymm to set
		 */
		public void setBeforeYymm(String beforeYymm) {
			this.beforeYymm = beforeYymm;
		}


		/**
		 * @return the before2Yymm
		 */
		public String getBefore2Yymm() {
			return before2Yymm;
		}


		/**
		 * @param before2Yymm the before2Yymm to set
		 */
		public void setBefore2Yymm(String before2Yymm) {
			this.before2Yymm = before2Yymm;
		}


		/**
		 * @return the aplyAmt
		 */
		public Double getAplyAmt() {
			return aplyAmt;
		}


		/**
		 * @param aplyAmt the aplyAmt to set
		 */
		public void setAplyAmt(Double aplyAmt) {
			this.aplyAmt = aplyAmt;
		}


		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "CBillComm [adjAmt=" + adjAmt + ", adjPrvBillAmt=" + adjPrvBillAmt + ", billAmt=" + billAmt
					+ ", exrate=" + exrate + ", rcptAmt=" + rcptAmt + ", unpayAmt=" + unpayAmt + ", wonBillAmt="
					+ wonBillAmt + ", usgAmt=" + usgAmt + ", usgCnt=" + usgCnt + ", usgQty=" + usgQty + ", atrtCorpId="
					+ atrtCorpId + ", atrtEmpId=" + atrtEmpId + ", billCycl=" + billCycl + ", billDt=" + billDt
					+ ", billMmTp=" + billMmTp + ", billSeqNo=" + billSeqNo + ", billYymm=" + billYymm + ", bizCl="
					+ bizCl + ", checkDt=" + checkDt + ", chgrId=" + chgrId + ", chrgCtgry=" + chrgCtgry
					+ ", chrgItmCd=" + chrgItmCd + ", clcWrkNo=" + clcWrkNo + ", crncyCd=" + crncyCd + ", ctrtId="
					+ ctrtId + ", custId=" + custId + ", debtProcYn=" + debtProcYn + ", exrateAplyDt=" + exrateAplyDt
					+ ", fullPayYn=" + fullPayYn + ", grpId=" + grpId + ", hotbillSeqNo=" + hotbillSeqNo + ", payDueDt="
					+ payDueDt + ", prepayOccSeqNo=" + prepayOccSeqNo + ", prepdCtrtId=" + prepdCtrtId + ", prodCd="
					+ prodCd + ", prodCmpsId=" + prodCmpsId + ", prodGrp=" + prodGrp + ", pymAcntId=" + pymAcntId
					+ ", regrId=" + regrId + ", saleItmCd=" + saleItmCd + ", saleTp=" + saleTp + ", soId=" + soId
					+ ", svcCd=" + svcCd + ", svcCmpsId=" + svcCmpsId + ", svcGrp=" + svcGrp + ", useYymm=" + useYymm
					+ ", chgDate=" + chgDate + ", regDate=" + regDate + ", beforeYymm=" + beforeYymm + ", before2Yymm="
					+ before2Yymm + ", aplyAmt=" + aplyAmt + ", pSeq=" + pSeq + ", spltChrgItmCd=" + spltChrgItmCd
					+ ", spltPymTp=" + spltPymTp + ", itllmtVal=" + itllmtVal + ", itllmtCl=" + itllmtCl + ", diviKey="
					+ diviKey + ", toPymAcntId=" + toPymAcntId + ", toItlmtVal=" + toItlmtVal + ", billAplyYn="
					+ billAplyYn + ", delayFee=" + delayFee + ", delayLevyYn=" + delayLevyYn + ", upayAmt=" + upayAmt
					+ ", chAppYn=" + chAppYn + ", chAppYymm=" + chAppYymm + ", chDelayImposeYn=" + chDelayImposeYn
					+ ", delayRate=" + delayRate + ", befBillYymm=" + befBillYymm + ", befPayDueDt=" + befPayDueDt
					+ ", setItmId=" + setItmId + ", oldBillSeqNo=" + oldBillSeqNo + ", autoAcRate=" + autoAcRate
					+ ", adjNo=" + adjNo + ", adjUpdYn=" + adjUpdYn + ", adjApplAmt=" + adjApplAmt + ", vat=" + vat
					+ ", sct=" + sct + ", vatAdjAmt=" + vatAdjAmt + ", sctAdjAmt=" + sctAdjAmt + "]";
		}


		/**
		 * @return the hotbillSeqNo
		 */
		public String getHotbillSeqNo() {
			return hotbillSeqNo;
		}


		/**
		 * @param hotbillSeqNo the hotbillSeqNo to set
		 */
		public void setHotbillSeqNo(String hotbillSeqNo) {
			this.hotbillSeqNo = hotbillSeqNo;
		}


		/**
		 * @return the checkDt
		 */
		public String getCheckDt() {
			return checkDt;
		}


		/**
		 * @param checkDt the checkDt to set
		 */
		public void setCheckDt(String checkDt) {
			this.checkDt = checkDt;
		}


		/**
		 * @return the spltChrgItmCd
		 */
		public String getSpltChrgItmCd() {
			return spltChrgItmCd;
		}


		/**
		 * @param spltChrgItmCd the spltChrgItmCd to set
		 */
		public void setSpltChrgItmCd(String spltChrgItmCd) {
			this.spltChrgItmCd = spltChrgItmCd;
		}


		/**
		 * @return the spltPymTp
		 */
		public String getSpltPymTp() {
			return spltPymTp;
		}


		/**
		 * @param spltPymTp the spltPymTp to set
		 */
		public void setSpltPymTp(String spltPymTp) {
			this.spltPymTp = spltPymTp;
		}


		/**
		 * @return the itllmtVal
		 */
		public String getItllmtVal() {
			return itllmtVal;
		}


		/**
		 * @param itllmtVal the itllmtVal to set
		 */
		public void setItllmtVal(String itllmtVal) {
			this.itllmtVal = itllmtVal;
		}


		/**
		 * @return the itllmtCl
		 */
		public String getItllmtCl() {
			return itllmtCl;
		}


		/**
		 * @param itllmtCl the itllmtCl to set
		 */
		public void setItllmtCl(String itllmtCl) {
			this.itllmtCl = itllmtCl;
		}


		/**
		 * @return the diviKey
		 */
		public String getDiviKey() {
			return diviKey;
		}


		/**
		 * @param diviKey the diviKey to set
		 */
		public void setDiviKey(String diviKey) {
			this.diviKey = diviKey;
		}


		public String getToPymAcntId() {
			return toPymAcntId;
		}


		public void setToPymAcntId(String toPymAcntId) {
			this.toPymAcntId = toPymAcntId;
		}


		/**
		 * @return the toItlmtVal
		 */
		public Double getToItlmtVal() {
			return toItlmtVal;
		}


		/**
		 * @param toItlmtVal the toItlmtVal to set
		 */
		public void setToItlmtVal(Double toItlmtVal) {
			this.toItlmtVal = toItlmtVal;
		}


		/**
		 * @return the billAplyYn
		 */
		public String getBillAplyYn() {
			return billAplyYn;
		}


		/**
		 * @param billAplyYn the billAplyYn to set
		 */
		public void setBillAplyYn(String billAplyYn) {
			this.billAplyYn = billAplyYn;
		}


		/**
		 * @return the delayFee
		 */
		public Double getDelayFee() {
			return delayFee;
		}


		/**
		 * @param delayFee the delayFee to set
		 */
		public void setDelayFee(Double delayFee) {
			this.delayFee = delayFee;
		}


		/**
		 * @return the delayLevyYn
		 */
		public String getDelayLevyYn() {
			return delayLevyYn;
		}


		/**
		 * @param delayLevyYn the delayLevyYn to set
		 */
		public void setDelayLevyYn(String delayLevyYn) {
			this.delayLevyYn = delayLevyYn;
		}


		/**
		 * @return the upayAmt
		 */
		public Double getUpayAmt() {
			return upayAmt;
		}


		/**
		 * @param upayAmt the upayAmt to set
		 */
		public void setUpayAmt(Double upayAmt) {
			this.upayAmt = upayAmt;
		}


		/**
		 * @return the chAppYn
		 */
		public String getChAppYn() {
			return chAppYn;
		}


		/**
		 * @param chAppYn the chAppYn to set
		 */
		public void setChAppYn(String chAppYn) {
			this.chAppYn = chAppYn;
		}


		/**
		 * @return the chAppYymm
		 */
		public String getChAppYymm() {
			return chAppYymm;
		}


		/**
		 * @param chAppYymm the chAppYymm to set
		 */
		public void setChAppYymm(String chAppYymm) {
			this.chAppYymm = chAppYymm;
		}


		/**
		 * @return the chDelayImposeYn
		 */
		public String getChDelayImposeYn() {
			return chDelayImposeYn;
		}


		/**
		 * @param chDelayImposeYn the chDelayImposeYn to set
		 */
		public void setChDelayImposeYn(String chDelayImposeYn) {
			this.chDelayImposeYn = chDelayImposeYn;
		}


		/**
		 * @return the delayRate
		 */
		public Double getDelayRate() {
			return delayRate;
		}


		/**
		 * @param delayRate the delayRate to set
		 */
		public void setDelayRate(Double delayRate) {
			this.delayRate = delayRate;
		}


		/**
		 * @return the befBillYymm
		 */
		public String getBefBillYymm() {
			return befBillYymm;
		}


		/**
		 * @param befBillYymm the befBillYymm to set
		 */
		public void setBefBillYymm(String befBillYymm) {
			this.befBillYymm = befBillYymm;
		}


		/**
		 * @return the befPayDueDt
		 */
		public String getBefPayDueDt() {
			return befPayDueDt;
		}


		/**
		 * @param befPayDueDt the befPayDueDt to set
		 */
		public void setBefPayDueDt(String befPayDueDt) {
			this.befPayDueDt = befPayDueDt;
		}


		/**
		 * @return the setItmId
		 */
		public String getSetItmId() {
			return setItmId;
		}


		/**
		 * @param setItmId the setItmId to set
		 */
		public void setSetItmId(String setItmId) {
			this.setItmId = setItmId;
		}


		/**
		 * @return the autoAcRate
		 */
		public Double getAutoAcRate() {
			return autoAcRate;
		}


		/**
		 * @param autoAcRate the autoAcRate to set
		 */
		public void setAutoAcRate(Double autoAcRate) {
			this.autoAcRate = autoAcRate;
		}


		/**
		 * @return the adjNo
		 */
		public Double getAdjNo() {
			return adjNo;
		}


		/**
		 * @param adjNo the adjNo to set
		 */
		public void setAdjNo(Double adjNo) {
			this.adjNo = adjNo;
		}



		/**
		 * @return the adjUpdYn
		 */
		public String getAdjUpdYn() {
			return adjUpdYn;
		}


		/**
		 * @param adjUpdYn the adjUpdYn to set
		 */
		public void setAdjUpdYn(String adjUpdYn) {
			this.adjUpdYn = adjUpdYn;
		}


		/**
		 * @return the oldBillSeqNo
		 */
		public String getOldBillSeqNo() {
			return oldBillSeqNo;
		}


		/**
		 * @param oldBillSeqNo the oldBillSeqNo to set
		 */
		public void setOldBillSeqNo(String oldBillSeqNo) {
			this.oldBillSeqNo = oldBillSeqNo;
		}


		/**
		 * @return the vat
		 */
		public Double getVat() {
			return vat;
		}


		/**
		 * @param vat the vat to set
		 */
		public void setVat(Double vat) {
			this.vat = vat;
		}


		/**
		 * @return the sct
		 */
		public Double getSct() {
			return sct;
		}


		/**
		 * @param sct the sct to set
		 */
		public void setSct(Double sct) {
			this.sct = sct;
		}


		/**
		 * @return the vatAdjAmt
		 */
		public Double getVatAdjAmt() {
			return vatAdjAmt;
		}


		/**
		 * @param vatAdjAmt the vatAdjAmt to set
		 */
		public void setVatAdjAmt(Double vatAdjAmt) {
			this.vatAdjAmt = vatAdjAmt;
		}


		/**
		 * @return the sctAdjAmt
		 */
		public Double getSctAdjAmt() {
			return sctAdjAmt;
		}


		/**
		 * @param sctAdjAmt the sctAdjAmt to set
		 */
		public void setSctAdjAmt(Double sctAdjAmt) {
			this.sctAdjAmt = sctAdjAmt;
		}


		/**
		 * @return the pSeq
		 */
		public String getpSeq() {
			return pSeq;
		}


		/**
		 * @param pSeq the pSeq to set
		 */
		public void setpSeq(String pSeq) {
			this.pSeq = pSeq;
		}


		/**
		 * @return the adjApplAmt
		 */
		public Double getAdjApplAmt() {
			return adjApplAmt;
		}


		/**
		 * @param adjApplAmt the adjApplAmt to set
		 */
		public void setAdjApplAmt(Double adjApplAmt) {
			this.adjApplAmt = adjApplAmt;
		}
	
}
