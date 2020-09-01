package com.ntels.ccbs.charge.mapper.common;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.common.PrepayOcc;

@Component
public interface PrepayMapper {


	int insertPrepayOcc(@Param("prepayOcc") PrepayOcc prepayOcc);
	
	int updatePrepayTransHistoryCancel(@Param("prepayTransSeqNo") String prepayTransSeqNo);
}
