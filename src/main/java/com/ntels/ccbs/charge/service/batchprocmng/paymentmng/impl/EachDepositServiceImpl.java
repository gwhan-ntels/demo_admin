package com.ntels.ccbs.charge.service.batchprocmng.paymentmng.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.DepositVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.EachDepositVO;
import com.ntels.ccbs.charge.mapper.batchprocmng.paymentmng.PaymentMapper;
import com.ntels.ccbs.charge.service.batchprocmng.paymentmng.EachDepositService;
import com.ntels.ccbs.charge.service.batchprocmng.paymentmng.PaymentService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.CUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class EachDepositServiceImpl implements EachDepositService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PaymentMapper paymentMapper;

	@Autowired
	private SequenceService sequenceService;

	@Autowired
	private PaymentService paymentService;

	/**
	 * 개별 입금을 처리한다.
	 * 
	 * @param eachDpstSeq,
	 *            inptMenuId, workId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int processEachDeposit(String eachDpstSeq, String inptMenuId, String workId, String rdoDpstGubn) throws Exception {
		int resultFlag = 1;

		// 개별입금/수납을 처리한다.
		logger.debug("====================================");
		logger.debug("  processEachDeposit Start...       ");
		logger.debug("====================================");

		EachDepositVO eachDepositVO = new EachDepositVO();
		eachDepositVO = paymentMapper.getEachDeposit(eachDpstSeq);

		String pymAcntId = eachDepositVO.getPymAcntId();
		String dpstSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.PY_DPST_NO, 10); 

		// 수납금액 CHECK
		double dpstAmt = eachDepositVO.getDpstAmt();

		if (dpstAmt > 0) {
			// 입금내역 등록을 위한 값 할당
			DepositVO depositInfo = new DepositVO();
			depositInfo = setEachDepositInfo(dpstSeqNo, eachDepositVO, inptMenuId, workId);

			// 입금/수납 처리
			int rcptCnt = paymentService.processReceipt("A", pymAcntId, dpstSeqNo, depositInfo, inptMenuId, workId, rdoDpstGubn);

			if (rcptCnt == -1) {
				resultFlag = -1;
				throw new Exception("FAIL PROCESS RECEIPT");
			} else {
				// 수납 처리 결과 반영
				eachDepositVO.setDpstSeqNo(dpstSeqNo);
				eachDepositVO.setDpstProcDt(depositInfo.getDpstProcDt()); // 입금처리일 설정
				eachDepositVO.setRegDate(DateUtil.sysdate());
				eachDepositVO.setRegrId(workId);

				int updCnt = paymentMapper.updateEachDeposit(eachDepositVO);
				if (updCnt <= 0) {
					resultFlag = -1;
					throw new Exception("FAIL UPDATE TBLPY_EACH_DPST");
				}
			}
		}

		logger.debug("====================================");
		logger.debug("  processEachDeposit End...         ");
		logger.debug("====================================");

		return resultFlag;
	}

	/**
	 * 개별입금 입금 내역 값 할당
	 * 
	 * @param dpstSeqNo,
	 *            eachDepositVO, inptMenuId, workId
	 * @return
	 */
	private DepositVO setEachDepositInfo(String dpstSeqNo, EachDepositVO eachDepositVO, String inptMenuId, String workId) {
		DepositVO depositInfo = new DepositVO();

		CUtil.copyObjectValue(eachDepositVO, depositInfo);

		depositInfo.setDpstSeqNo(dpstSeqNo);
		depositInfo.setDpstProcDt(DateUtil.getDateStringYYYYMMDD(0));
		// 입금타입 설정-입금 구분이 방문수금 센터 입금(05)이면 입금 타입은 건별 방문수금(06), 계좌이체 센터입금(06)이면
		// 건별 고객 계좌이체(07), 센터 총액 입금(07)이면 센터 총액 입금(08)
		if ("05".equals(eachDepositVO.getDpstCl())) {
			depositInfo.setDpstTp("06");
		} else if ("06".equals(eachDepositVO.getDpstCl())) {
			depositInfo.setDpstTp("07");
		} else if ("07".equals(eachDepositVO.getDpstCl())) {
			depositInfo.setDpstTp("08");
		} else if ("11".equals(eachDepositVO.getDpstCl())) { // sds 버전 현금입금
			depositInfo.setDpstTp("3");
		}
		
		depositInfo.setDpstTpSeqNo(eachDepositVO.getEachDpstSeq());
		depositInfo.setPayCorpTp("01");
		depositInfo.setPayCorpCd(eachDepositVO.getDpstBnkAcntCd());
		depositInfo.setAcntCardNo("");
		depositInfo.setPayProcYn("N");
		depositInfo.setPayProcDt("");
		depositInfo.setCnclYn("N");
		depositInfo.setPayCnclYn("N");
		depositInfo.setPrepayTgtYn("N");
		depositInfo.setPrepayOccSeqNo("");
		depositInfo.setAmbgTgtYn("N");
		depositInfo.setAmbgOccSeqNo("");
		depositInfo.setAssrTgtYn("N");
		depositInfo.setAssrOccSeqNo("");
		depositInfo.setDpstOpetrId(workId);
		depositInfo.setCashRcptIssYn("N");
		depositInfo.setDpststdrErpSndYn("N");
		depositInfo.setTrnsstdrErpSndYn("N");
		depositInfo.setInptMenuId(inptMenuId);
		depositInfo.setRegrId(workId);
		depositInfo.setRegDate(DateUtil.sysdate());

		return depositInfo;
	}

}
