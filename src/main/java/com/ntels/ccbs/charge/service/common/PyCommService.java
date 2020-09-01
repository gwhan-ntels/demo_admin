package com.ntels.ccbs.charge.service.common;

import com.ntels.ccbs.charge.domain.common.ExrateInfo;

public interface PyCommService {
	
	
	ExrateInfo getExrateInfo();

	int getPymAcntCnt(String pymAcntId);
	
//	boolean isTest();
//	
//	/**
//	 * Job런처가 실행될 때 Arguments로 넘어온 데이터를 파싱하여
//	 * PyCommEntity객체로 넘겨준다.
//	 * @param arg
//	 * @return
//	 */
//	PyCommEntity parseArgData(String arg);
//	
//	void insertBondRcpt(String orgId, String ifChk, String dpstSeqNo, String pymSeqNo, String regrId);
//	
//	/**
//	 * 납부계정ID 조회
//	 * @param pymAcntId
//	 * @return
//	 */
//	
//	String getDpstSeqNo();
//	
//	String getEachDpstSeqNo();
//	
//	String getPymSeqNo();
//	
//	String getPrepayOccSeqNo();
//	
//	String getAmbgOccSeqNo();
//	
//	String getPrepayTransSeqNo();
//	
//	String getAmbgTransSeqNo();
	
}
