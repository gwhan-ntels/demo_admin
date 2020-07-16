package com.ntels.ccbs.system.mapper.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonGrpVO;

@Component
public interface CommonDataMapper {

	
	CommonCodeVO getCommonCode(@Param(value = "grpCd") String grpCd, @Param(value = "code")String code, @Param(value = "lng")String lng);
	
	List<CommonCodeVO> getCommonCodeList(@Param(value = "grpCd") String grpCd, @Param(value = "lng")String lng);
	
	List<CommonCodeVO> getCommonCodeListByRef1(@Param(value = "grpCd") String grpCd,@Param(value="ref1") String ref1, @Param(value = "lng")String lng);
	
	List<CommonCodeVO> getCommonCodeListByRef2(@Param(value = "grpCd") String grpCd,@Param(value="ref2") String ref2, @Param(value = "lng")String lng);
	
	List<CommonCodeVO> getCommonCodeListByRef3(@Param(value = "grpCd") String grpCd,@Param(value="ref3") String ref3, @Param(value = "lng")String lng);
	
	List<CommonCodeVO> getCommonCodeListByRef4(@Param(value = "grpCd") String grpCd,@Param(value="ref4") String ref4, @Param(value = "lng")String lng);

	List<CommonCodeVO> getCommonCodeListProd(@Param(value = "grpCd") String grpCd,@Param(value="prodCd") String prodCd,@Param(value="qry") String qry);
}
