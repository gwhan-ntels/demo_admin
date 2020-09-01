package com.ntels.ccbs.charge.domain.common;

import java.sql.Timestamp;

public class ExrateInfo {

	/**
	 * BLIV023 DEM: Germany (Mark)-Deutch Mark EUR: Europe (Euro) GBP: Great Britain (Pound)
	 * HKD: Hong Kong (Dollar) JPY: Japan (Yen) KRW: South Korea (Won) RMB: China (Renminbi)-CNY 
	 * SDR: Special Drawing Rights SGD: Singapore (Dollar) USD: United States (Dollar)
	 */
	private String crncyCd;

	/**
	 * YYYYMMDD
	 */
	private String exrateAplyDt;

	/**
	 * 환율
	 */
	private Double exrate;

	/**
	 * 등록자ID
	 */
	private String regrId;

	/**
	 * 등록일
	 */
	private Timestamp regDate;

	/**
	 * 변경자ID
	 */
	private String chgrId;

	/**
	 * 변경일
	 */
	private Timestamp chgDate;

	public String getCrncyCd() {
		return crncyCd;
	}

	public void setCrncyCd(String crncyCd) {
		this.crncyCd = crncyCd;
	}

	public String getExrateAplyDt() {
		return exrateAplyDt;
	}

	public void setExrateAplyDt(String exrateAplyDt) {
		this.exrateAplyDt = exrateAplyDt;
	}

	public Double getExrate() {
		return exrate;
	}

	public void setExrate(Double exrate) {
		this.exrate = exrate;
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
