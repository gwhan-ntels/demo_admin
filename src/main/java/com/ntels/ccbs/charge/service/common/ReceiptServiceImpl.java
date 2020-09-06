package com.ntels.ccbs.charge.service.common;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.ntels.ccbs.charge.domain.common.Receipt;
import com.ntels.ccbs.charge.domain.common.ReceiptCancel;
import com.ntels.ccbs.charge.domain.common.ReceiptCancelAppl;
import com.ntels.ccbs.charge.domain.common.ReceiptDetail;
import com.ntels.ccbs.charge.mapper.common.ReceiptMapper;
import com.ntels.ccbs.common.util.CUtil;
import com.ntels.ccbs.common.util.DateUtil;

@Service
public class ReceiptServiceImpl implements ReceiptService {

	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ReceiptMapper receiptMapper;

	@Override
	public String getCancelYn(String pymSeqNo) {

		if (StringUtils.isEmptyOrWhitespaceOnly(pymSeqNo) == true && StringUtils.isEmptyOrWhitespaceOnly(pymSeqNo)) {
			throw new RuntimeException("dpstSeqNo또는 pymSeqNo값이 없어서 조회할 수 없습니다.");
		}

		return receiptMapper.getCancelYn(pymSeqNo);
	}

	@Override
	public List<ReceiptDetail> getReceiptDetailList(String dpstSeqNo, String pymSeqNo) {

		if (StringUtils.isEmptyOrWhitespaceOnly(dpstSeqNo) == true && StringUtils.isEmptyOrWhitespaceOnly(pymSeqNo)) {
			throw new RuntimeException("dpstSeqNo또는 pymSeqNo값이 없어서 조회할 수 없습니다.");
		}

		ReceiptDetail receiptDetail = new ReceiptDetail();
		receiptDetail.setPymSeqNo(pymSeqNo);
		receiptDetail.setDpstSeqNo(dpstSeqNo);

		return receiptMapper.getReceiptDetailList(dpstSeqNo, pymSeqNo);
	}

	@Override
	public int updateReceiptCancel(String dpstSeqNo, String pymSeqNo, String userId) {
	
		if (StringUtils.isEmptyOrWhitespaceOnly(dpstSeqNo) == true 
				&& StringUtils.isEmptyOrWhitespaceOnly(pymSeqNo)) {
			throw new RuntimeException("dpstSeqNo또는 pymSeqNo값이 없어서 조회할 수 없습니다.");
		}
		
		Timestamp chgDate = new Timestamp(new Date().getTime());
		
		return receiptMapper.updateReceiptCancel(dpstSeqNo, pymSeqNo, userId, chgDate);
		
	}

	@Override
	public Receipt getReceipt(String pymSeqNo) {
		return receiptMapper.getReceipt(pymSeqNo);
	}

	@Override
	public int insertReceiptCancelAppl(String pymSeqNo, String regrId) {
		if (StringUtils.isEmptyOrWhitespaceOnly(pymSeqNo) == true) {
			throw new RuntimeException("pymSeqNo값이 없어서 조회할 수 없습니다.");
		}
		
		Receipt receipt = receiptMapper.getReceipt(pymSeqNo);
		
		regrId = regrId == null ? " " : regrId;
		
		ReceiptCancelAppl receiptCancelAppl = new ReceiptCancelAppl();
		CUtil.copyObjectValue(receipt, receiptCancelAppl);
		
		receiptCancelAppl.setRcptPsnId(regrId);
		receiptCancelAppl.setRcptDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		receiptCancelAppl.setApprReqrId(regrId);
		receiptCancelAppl.setApprReqDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(1));
		receiptCancelAppl.setDcsnProcStat("01");
		receiptCancelAppl.setCnclResn("RECEIPT CANCELLATIONI");		
		receiptCancelAppl.setRegrId(regrId);
		receiptCancelAppl.setRegDate(new Timestamp(new Date().getTime()));
		receiptCancelAppl.setChgrId(regrId);
		receiptCancelAppl.setChgDate(new Timestamp(new Date().getTime()));
		
		return receiptMapper.insertReceiptCancelAppl(receiptCancelAppl);
	}

	@Override
	public int insertReceiptCancel(String cnclResn, String pymSeqNo, String receiptId, String regrId) {
		if (StringUtils.isEmptyOrWhitespaceOnly(pymSeqNo) == true) {
			throw new RuntimeException("pymSeqNo값이 없어서 조회할 수 없습니다.");
		}
		if (StringUtils.isEmptyOrWhitespaceOnly(receiptId) == true) {
			throw new RuntimeException("receiptId값이 없어서 조회할 수 없습니다.");
		}

		Receipt receipt = receiptMapper.getReceipt(pymSeqNo);

		ReceiptCancel receiptCancel = new ReceiptCancel();
		CUtil.copyObjectValue(receipt, receiptCancel);

//		receiptCancel.setRcptId(receiptId);
		receiptCancel.setDpstTpSeqNo(receipt.getDpstSeqNo() == null ? "0000000000" : receipt.getDpstSeqNo());
		receiptCancel.setCnclrId(regrId);
		receiptCancel.setCnclDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(1));
		receiptCancel.setCnclResn(cnclResn);
		receiptCancel.setRegrId(regrId);
		receiptCancel.setRegDate(new Timestamp(new Date().getTime()));
		receiptCancel.setChgrId(regrId);
		receiptCancel.setChgDate(new Timestamp(new Date().getTime()));

		receiptMapper.insertReceiptCancel(receiptCancel);

		List<ReceiptDetail> receiptDetailList = receiptMapper.getReceiptDetail(pymSeqNo);

		for (ReceiptDetail receiptDetail : receiptDetailList) {
			receiptMapper.insertReceiptCancelDetail(receiptDetail);
		}

		// TODO 성공 실패여부..
		return 0;
	}
	

	@Override
	public int insertReceipt(final List<Receipt> receiptList) {
		int cnt = 0;
		
		for (Receipt receipt : receiptList) {
			receiptMapper.insertReceipt(receipt);
			cnt++;
		}
		
		return cnt;
	}
	

	@Override
	public int insertReceiptDetail(final List<ReceiptDetail> receiptDetailList) {
		int cnt = 0;
		
		for (ReceiptDetail receiptDetail : receiptDetailList) {
			receiptMapper.insertReceiptDetail(receiptDetail); 
			cnt++;
		}
		
		return cnt;
	}

	@Override
	public List<Receipt> getReceiptBillInfo(String dpstSeqNo) {

		if (StringUtils.isEmptyOrWhitespaceOnly(dpstSeqNo) == true) {
			throw new RuntimeException("dpstSeqNo값이 없어서 조회할 수 없습니다.");
		}
		
		return receiptMapper.getReceiptBillInfo(dpstSeqNo);
	}
}