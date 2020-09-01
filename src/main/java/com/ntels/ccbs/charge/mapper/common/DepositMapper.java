package com.ntels.ccbs.charge.mapper.common;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.common.Deposit;
import com.ntels.ccbs.charge.domain.common.EachDeposit;

@Component
public interface DepositMapper {
	int insertEachDeposit(@Param("eachDeposit") EachDeposit eachDeposit);

	EachDeposit getEachDeposit(@Param("eachDeposit") EachDeposit eachDeposit);
	

	int insertDeposit(@Param("deposit") Deposit deposit);


	int updateEachDeposit(@Param("eachDeposit") EachDeposit eachDeposit);

	Double getDpstAmt(@Param("dpstSeqNo") String dpstSeqNo);

	Deposit getDepositForRcpt(@Param("dpstSeqNo") String dpstSeqNo);

	int updatePayProcDt(@Param("dpstSeqNo") String dpstSeqNo, @Param("payProcDt") String payProcDt);
}
