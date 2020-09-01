package com.ntels.ccbs.charge.mapper.common;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.common.ExrateInfo;

@Component
public interface PyCommMapper {

	ExrateInfo getExrateInfo(@Param("exrateAplyDt") String exrateAplyDt, @Param("crncyCd") String crncyCd);
	
	int getPymAcntCnt(@Param("pymAcntId") String pymAcntId);
	
}
