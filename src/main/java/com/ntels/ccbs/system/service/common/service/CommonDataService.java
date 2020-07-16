package com.ntels.ccbs.system.service.common.service;

import java.util.List;

import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonGrpVO;

public interface CommonDataService {

	/**
	 * <PRE>
	 * 1. MethodName: getCommonCode
	 * 2. ClassName : ChargeService
	 * 3. Comment   공통코드 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 4. 28. 오전 10:16:32
	 * </PRE>
	 *   @return CommonCodeVO
	 *   @param grpCd
	 *   @param code
	 *   @param lng
	 *   @return
	 */
	public CommonCodeVO getCommonCode(String grpCd, String code, String lng);
	
	/**
	 * <PRE>
	 * 1. MethodName: getCommonCodeList
	 * 2. ClassName : ChargeService
	 * 3. Comment   : 공통코드 리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 4. 28. 오전 10:55:45
	 * </PRE>
	 *   @return List<CommonCodeVO>
	 *   @param grpCd
	 *   @param lng
	 *   @return
	 */
	public List<CommonCodeVO> getCommonCodeList(String grpCd, String lng);
	

	/**
	 * <PRE>
	 * 1. MethodName: getCommonCodeListByRef1
	 * 2. ClassName : ChargeService
	 * 3. Comment   : 공통코드리스트 조회(참조1)
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 9. 오후 3:54:39
	 * </PRE>
	 *   @return List<CommonCodeVO>
	 *   @param grpCd
	 *   @param ref1
	 *   @param lng
	 *   @return
	 */
	public List<CommonCodeVO> getCommonCodeListByRef1(String grpCd, String ref1, String lng);
	
	/**
	 * <PRE>
	 * 1. MethodName: getCommonCodeListByRef2
	 * 2. ClassName : ChargeService
	 * 3. Comment   :공통코드리스트 조회(참조2)
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 9. 오후 3:54:40
	 * </PRE>
	 *   @return List<CommonCodeVO>
	 *   @param grpCd
	 *   @param ref2
	 *   @param lng
	 *   @return
	 */
	public List<CommonCodeVO> getCommonCodeListByRef2(String grpCd, String ref2, String lng);
	
	/**
	 * <PRE>
	 * 1. MethodName: getCommonCodeListByRef3
	 * 2. ClassName : ChargeService
	 * 3. Comment   : 공통코드리스트 조회(참조3)
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 9. 오후 3:54:42
	 * </PRE>
	 *   @return List<CommonCodeVO>
	 *   @param grpCd
	 *   @param ref3
	 *   @param lng
	 *   @return
	 */
	public List<CommonCodeVO> getCommonCodeListByRef3(String grpCd, String ref3, String lng);
	
	/**
	 * <PRE>
	 * 1. MethodName: getCommonCodeListByRef4
	 * 2. ClassName : ChargeService
	 * 3. Comment   : 공통코드리스트 조회(참조4)
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 9. 오후 3:54:44
	 * </PRE>
	 *   @return List<CommonCodeVO>
	 *   @param grpCd
	 *   @param ref4
	 *   @param lng
	 *   @return
	 */
	public List<CommonCodeVO> getCommonCodeListByRef4(String grpCd, String ref4, String lng);
	
	/**
	 * <PRE>
	 * 1. MethodName: getCommonCodeList
	 * 2. ClassName : ChargeService
	 * 3. Comment   : 공통코드 리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 4. 28. 오전 10:55:45
	 * </PRE>
	 *   @return List<CommonCodeVO>
	 *   @param grpCd
	 *   @param prodCd
	 *   @return
	 */
	public List<CommonCodeVO> getCommonCodeListProd(String grpCd, String prodCd, String qry);
	
}
 