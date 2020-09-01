package com.ntels.ccbs.charge.service.common;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.ntels.ccbs.charge.domain.common.Deposit;
import com.ntels.ccbs.charge.domain.common.EachDeposit;
import com.ntels.ccbs.charge.mapper.common.DepositMapper;
import com.ntels.ccbs.common.util.CUtil;
import com.ntels.ccbs.common.util.DateUtil;

@Service
public class DepositServiceImpl implements DepositService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DepositMapper depositMapper;

	@Override
	public int insertEachDeposit(EachDeposit eachDeposit) {		
		return depositMapper.insertEachDeposit(eachDeposit);
	}
	
	
	@Override
	public int insertDepositFromEachDeposit(EachDeposit eachDeposit) {
		String dpstSeqNo = eachDeposit.getDpstSeqNo();
		
		eachDeposit = getEachDeposit(eachDeposit);		
		eachDeposit.setDpstSeqNo(dpstSeqNo);
		
		// 가져온 데이터를 바탕으로 PyDpst객체를 생성한다.
		Deposit deposit = new Deposit();
		
		CUtil.copyObjectValue(eachDeposit, deposit);
		
		deposit.setDpstProcDt(DateUtil.getDateStringYYYYMMDD(0)); // date YYYYMMDD
		deposit.setDpstTp("3");
		deposit.setDpstTpSeqNo(eachDeposit.getEachDpstSeq());
		
		if (eachDeposit.getDpstCl().equals("05") == true) { // 카드
			deposit.setPayCorpTp("02"); // 카드
		} else if (eachDeposit.getDpstCl().equals("11") == true) {
			deposit.setPayCorpTp("");    // deposit.setPayCorpTp("03"); 에서 수정  2020.08.31 GWHAN
		} else {
			deposit.setPayCorpTp("01");
		}
		
		if (!StringUtils.isNullOrEmpty(eachDeposit.getDpstBnkAcntCd())) {
			if (eachDeposit.getDpstCl().equals("05") == true) {
				deposit.setPayCorpCd(eachDeposit.getDpstBnkAcntCd().substring(0, 2));
			} else {
				deposit.setPayCorpCd(eachDeposit.getDpstBnkAcntCd().substring(0, 3));
			}	
		}
		
		// TODO 고객 카드 번호 암호화 추가 필요함
		deposit.setAcntCardNo(eachDeposit.getAcntCardNo());
		deposit.setPayProcYn("N");
		deposit.setPayProcDt("");
		deposit.setAmbgTgtYn("N");
		deposit.setCnclYn("N");
		deposit.setPayCnclYn("N");
		deposit.setRegDate(new Timestamp(new Date().getTime()));
		deposit.setPrepayTgtYn("N");
		
		return depositMapper.insertDeposit(deposit);
	}

	public EachDeposit getEachDeposit(EachDeposit eachDeposit) {
		String eachDpstSeqNo = eachDeposit.getEachDpstSeq();

		if (StringUtils.isEmptyOrWhitespaceOnly(eachDpstSeqNo) == true) {
			throw new RuntimeException("eachDpstSeqNo값이 없어서 조회할 수 없습니다.");
		}
		
		return depositMapper.getEachDeposit(eachDeposit);
	}
	

	@Override
	public int updateEachDeposit(EachDeposit eachDeposit) {
		
		if (eachDeposit == null || StringUtils.isNullOrEmpty(eachDeposit.getEachDpstSeq()) == true) {
			logger.debug("eachDpstSeq값을 찾을 수 없어 업데이트를 수행할 수 없습니다.");
			throw new RuntimeException("eachDpstSeq값을 찾을 수 없어 업데이트를 수행할 수 없습니다.");
		}
		
		eachDeposit.setDpstProcDt(DateUtil.getDateStringYYYYMMDD(0));
		
		return depositMapper.updateEachDeposit(eachDeposit);
	}
	
	@Override
	public double getDpstAmt(String dpstSeqNo) {
		if (StringUtils.isNullOrEmpty(dpstSeqNo)) {
			logger.debug("dpstSeq값을 찾을 수 없어 입금금액을 조회 할 수 없습니다.");
			throw new RuntimeException("dpstSeq값을 찾을 수 없어 입금금액을 조회 할 수 없습니다.");			
		}
		
		Double value = depositMapper.getDpstAmt(dpstSeqNo);
		
		if (value == null) {
			return 0.0;
		}
		
		return value;
	}

	@Override
	public Deposit getDepositForRcpt(String dpstSeqNo) {
		
		return depositMapper.getDepositForRcpt(dpstSeqNo); 
	}
	
	@Override
	public int updatePayProcDt(String dpstSeqNo, String payProcDt) {
		return depositMapper.updatePayProcDt(dpstSeqNo, payProcDt);
	}
	
}
