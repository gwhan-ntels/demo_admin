package com.ntels.ccbs.charge.domain.common;

import java.lang.reflect.Field;
import java.sql.Timestamp;

public class EachDeposit {

	/**
	 * Table Sequence 번호
	 */
	private String eachDpstSeq;
	/**
	 * 입금구분(BLPY001) 01: 지로 02: 자동이체 03: CMS 04: 가상계좌 05: 신용카드
	 */
	private String dpstCl;
	/**
	 * BLPY002 1: 고객방문입금 2: 방문수금 3: 수금사원 - 입금구분이 현금인 경우에 Setting됨
	 */
	private String cashDpstCl;
	/**
	 * 고객이 납부한 일자(YYYYMMDD) 1. 지로: 은행납부일자 2. CMS: 통장인출일자 3. 카드: 전표매입일자 4. 무통장(가상계좌): 입금일자
	 */
	private String dpstDt;
	/**
	 * 입금금액
	 */
	private double dpstAmt;
	/**
	 * 입금수수료유형(BLPY025) 1: 지로입금(장표) 2: 인터넷입금 3: A장표(장표)
	 */
	private String dpstFeeTp;
	/**
	 * 수수료
	 */
	private double feeAmt;
	/**
	 * 해당 청구월, 청구주기에 청구대상이 되는 납부계정 정보에 대한 청구서 번호(20자리) 청구년월(4) + 주기(2) + 청구일자DD(2) + 납부계정ID(10) + 일련번호(2)
	 */
	private String billSeqNo;
	
	private String billYymm;
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
	 * 고객ID
	 */
	private String custId;
	/**
	 * 계약ID
	 */
	private String ctrtId;
	/**
	 * 적요
	 */
	private String smry;
	/**
	 * ?????? ???????? C ???????? ????
	 */
	private String dpstBnkAcntCd;
	/**
	 * 통장에 입금된 일자(YYYYMMDD)
	 */
	private String transDt;
	/**
	 * 입금처리일자(YYYYMMDD)
	 */
	private String dpstProcDt;
	/**
	 * Table Sequence 번호
	 */
	private String dpstSeqNo;
	/**
	 * 원화입금금액
	 */
	private double wonDpstAmt;
	/**
	 * 수금사원ID
	 */
	private String rcptEmpId;
	/**
	 * BLIV023 DEM: Germany (Mark)-Deutch Mark EUR: Europe (Euro) GBP: Great Britain (Pound) 
	 * HKD: Hong Kong (Dollar) JPY: Japan (Yen) KRW: South Korea (Won) RMB: China (Renminbi)-CNY 
	 * SDR: Special Drawing Rights SGD: Singapore (Dollar) USD: United States (Dollar)
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
	 * 등록자ID
	 */
	private String chgrId;
	/**
	 * 등록일
	 */
	private Timestamp chgDate;
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

	/**
	 * 영수사원ID
	 */
	private String rcptBillEmpId;
	/**
	 * orgId
	 */
	private String orgId;
	
	/**
	 * 납부 카드 번호?
	 */
	private String acntCardNo;
	
	/**
	 * 유통인터페이스 유무 1. 인터페이스처리 
	 */
	private String ifChk;
	
	/**
	 * 한번에 여러건의 청구를 처리할 수 있으므로 배열을 준비한다.
	 * 청구일련번호 배열
	 */
	private String[] billSeqArray;
	
	/**
	 * 청구년월 배열
	 */
	private String[] billYymmArray;
	
	/**
	 * 해당 dpstSeqNo의 입금금액
	 */
	double prepayAplyAmt;
	
	/**
	 * 납부 시퀀스 번호
	 */
	private String pymSeqNo;
	
	private int arrsRcptFlag;
	
	public String getEachDpstSeq() {
		return eachDpstSeq;
	}
	public void setEachDpstSeq(String eachDpstSeq) {
		this.eachDpstSeq = eachDpstSeq;
	}
	public String getDpstCl() {
		return dpstCl;
	}
	public void setDpstCl(String dpstCl) {
		this.dpstCl = dpstCl;
	}
	public String getCashDpstCl() {
		return cashDpstCl;
	}
	public void setCashDpstCl(String cashDpstCl) {
		this.cashDpstCl = cashDpstCl;
	}
	public String getDpstDt() {
		return dpstDt;
	}
	public void setDpstDt(String dpstDt) {
		this.dpstDt = dpstDt;
	}
	public double getDpstAmt() {
		return dpstAmt;
	}
	public void setDpstAmt(double dpstAmt) {
		this.dpstAmt = dpstAmt;
	}
	public String getDpstFeeTp() {
		return dpstFeeTp;
	}
	public void setDpstFeeTp(String dpstFeeTp) {
		this.dpstFeeTp = dpstFeeTp;
	}
	public double getFeeAmt() {
		return feeAmt;
	}
	public void setFeeAmt(double feeAmt) {
		this.feeAmt = feeAmt;
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
	public String getSmry() {
		return smry;
	}
	public void setSmry(String smry) {
		this.smry = smry;
	}
	public String getDpstBnkAcntCd() {
		return dpstBnkAcntCd;
	}
	public void setDpstBnkAcntCd(String dpstBnkAcntCd) {
		this.dpstBnkAcntCd = dpstBnkAcntCd;
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
	public String getDpstSeqNo() {
		return dpstSeqNo;
	}
	public void setDpstSeqNo(String dpstSeqNo) {
		this.dpstSeqNo = dpstSeqNo;
	}
	public double getWonDpstAmt() {
		return wonDpstAmt;
	}
	public void setWonDpstAmt(double wonDpstAmt) {
		this.wonDpstAmt = wonDpstAmt;
	}
	public String getRcptEmpId() {
		return rcptEmpId;
	}
	public void setRcptEmpId(String rcptEmpId) {
		this.rcptEmpId = rcptEmpId;
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
	public String getRcptBillEmpId() {
		return rcptBillEmpId;
	}
	public void setRcptBillEmpId(String rcptBillEmpId) {
		this.rcptBillEmpId = rcptBillEmpId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getAcntCardNo() {
		return acntCardNo;
	}
	public void setAcntCardNo(String acntCardNo) {
		this.acntCardNo = acntCardNo;
	}
	public String getIfChk() {
		return ifChk;
	}
	public void setIfChk(String ifChk) {
		this.ifChk = ifChk;
	}
	public String[] getBillSeqArray() {
		return billSeqArray;
	}
	public void setBillSeqArray(String[] billSeqArray) {
		this.billSeqArray = billSeqArray;
	}
	public String[] getBillYymmArray() {
		return billYymmArray;
	}
	public void setBillYymmArray(String[] billYymmArray) {
		this.billYymmArray = billYymmArray;
	}
	public double getPrepayAplyAmt() {
		return prepayAplyAmt;
	}
	public void setPrepayAplyAmt(double prepayAplyAmt) {
		this.prepayAplyAmt = prepayAplyAmt;
	}
	public String getPymSeqNo() {
		return pymSeqNo;
	}
	public void setPymSeqNo(String pymSeqNo) {
		this.pymSeqNo = pymSeqNo;
	}
	public int getArrsRcptFlag() {
		return arrsRcptFlag;
	}
	public void setArrsRcptFlag(int arrsRcptFlag) {
		this.arrsRcptFlag = arrsRcptFlag;
	}
	
	@Override
	public String toString() {
		Field[] fields = this.getClass().getDeclaredFields();
		
		StringBuffer sb = new StringBuffer();
		
		try {
			for (Field f : fields) {
				sb.append(f.getName());
				sb.append(" : ");
				sb.append(f.get(this));
				sb.append("\n");
			}
		} catch (Exception e) {
			return super.toString();
		}
		
		return sb.toString();
		
	}
	
}
