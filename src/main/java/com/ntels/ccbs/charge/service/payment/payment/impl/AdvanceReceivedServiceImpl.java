package com.ntels.ccbs.charge.service.payment.payment.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.ntels.ccbs.charge.domain.billing.billing.BillingStatisticsVO;
import com.ntels.ccbs.charge.domain.common.Bill;
import com.ntels.ccbs.charge.domain.common.CBillComm;
import com.ntels.ccbs.charge.domain.common.PaymentResult;
import com.ntels.ccbs.charge.domain.common.PrepayOcc;
import com.ntels.ccbs.charge.domain.common.Receipt;
import com.ntels.ccbs.charge.domain.payment.advanceReceived.AdvanceReceivedVO;
import com.ntels.ccbs.charge.mapper.payment.payment.AdvanceReceivedMapper;
import com.ntels.ccbs.charge.service.batchprocmng.ambgtransmng.AmbgTransService;
import com.ntels.ccbs.charge.service.batchprocmng.prepaytransmng.PrepayTransService;
import com.ntels.ccbs.charge.service.common.PaymentService_1;
import com.ntels.ccbs.charge.service.common.PaymentService_1.ProcessPaymentCallback;
import com.ntels.ccbs.charge.service.common.PrepayService;
import com.ntels.ccbs.charge.service.common.PyCommService;
import com.ntels.ccbs.charge.service.common.ReceiptService;
import com.ntels.ccbs.charge.service.payment.payment.AdvanceReceivedService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class AdvanceReceivedServiceImpl implements AdvanceReceivedService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private AdvanceReceivedMapper advanceReceivedMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Autowired
	private PrepayTransService prepayTransService;

	@Autowired
	private AmbgTransService ambgTransService;

	@Autowired
	private PaymentService_1 paymentService_1;
	
	@Autowired
	private PrepayService prepayService;
	
	@Autowired
	private PyCommService pyCommService;

	@Autowired
	private ReceiptService receiptService;

	@Override
	public int getPrepayOccCount(AdvanceReceivedVO advanceReceivedVO) {
		return advanceReceivedMapper.getPrepayOccCount(advanceReceivedVO);
	}
	private String prepayOccSeq = null;
	
	private double prepayAplyAmt;

	@Override
	public List<AdvanceReceivedVO> getPrepayOccList(AdvanceReceivedVO advanceReceivedVO, int page, int perPage) {
		int start = 0;
		int end = 0;

		start = (page - 1) * perPage;
		end = perPage;

		return advanceReceivedMapper.getPrepayOccList(advanceReceivedVO, start, end);
	}

	@Override
	public int getBillInvoiceCount(BillingStatisticsVO billingStatisticsVO) {
		return advanceReceivedMapper.getBillInvoiceCount(billingStatisticsVO);
	}

	@Override
	public List<BillingStatisticsVO> getBillInvoiceList(BillingStatisticsVO billingStatisticsVO, int page, int perPage) {
		int start = 0;
		int end = 0;

		start = (page - 1) * perPage;
		end = perPage;

		return advanceReceivedMapper.getBillInvoiceList(billingStatisticsVO, start, end);
	}

	@Override
	public int getRefundAppliedCnt(String seqNo) {
		return advanceReceivedMapper.getRefundAppliedCnt(seqNo);
	}
	

	@Override
	public int insertAction(PrepayOcc prepayOcc) {
		int returnFlag = 1;
		int length = 0;

		Double prepayBal = 0.0;

		String[] billSeqArray = null;
		List<Bill> billSeqNoInfoList = null;
		String pymAcntId = null; 
		
		SessionUser session = CommonUtil.getSessionManager();
		String userId = session.getUserId();

		// transTp 01:선택청구 02:납부계정 03:선수이전처리
		if (prepayOcc.getTransTp().equals("03")) {
			throw new RuntimeException("kb 에서는 사용하지 않는 건입니다.");
		} else {

			prepayAplyAmt = 0;
			
			prepayBal = prepayOcc.getPrepayBal();
			prepayOccSeq = prepayOcc.getPrepayOccSeqNo();
			pymAcntId = prepayOcc.getPymAcntId(); 

			// billSeqNo 을 prepayOcc에 set 하기 위함.
			if (StringUtils.isNullOrEmpty(prepayOcc.getBillSeqNo()) == true) {
				// 납부계정에 대한 전체미납대체처리 시 billSeqNo == null 이므로 미납대상 billSeqNo를
				// 구한다.
				billSeqNoInfoList = paymentService_1.getBillSeqNo(prepayOcc.getSoId(), prepayOcc.getPymAcntId());
				length = billSeqNoInfoList.size();

				billSeqArray = new String[length];

				for (int i = 0; i < length; i++) {
					billSeqArray[i] = billSeqNoInfoList.get(i).getBillSeqNo();
				}
				prepayOcc.setBillSeqArray(billSeqArray);
			} else {
				billSeqArray = new String[1];

				billSeqArray[0] = prepayOcc.getBillSeqNo();

				prepayOcc.setBillSeqArray(billSeqArray);
			}

			int loopCnt = prepayOcc.getBillSeqArray().length;

			// 실제 로직 처리부분 시작
			for (int i = 0; i < loopCnt; i++) {
				PrepayOcc prepayAmount = prepayService.getPrepayAmount(prepayOccSeq);

				if (i == 0) {
					if (prepayAmount.getPrepayBal().doubleValue() != prepayBal.doubleValue()) {
						logger.debug("Error reason(PrepayBal != inPrepayBal).");
						throw new RuntimeException("Error reason(PrepayBal != inPrepayBal).");
					}
				}

				String billSeqNo = prepayOcc.getBillSeqArray()[i];

				List<String> billSeqNoList = prepayService.getBillSeqNoCheck(billSeqNo);

				if (billSeqNoList.size() <= 0) {
					throw new RuntimeException("청구 내역이 없습니다.");
				}

				String pymSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.MOD_ID_PYM, 10);
				

				PaymentResult result = paymentService_1.processPayment(billSeqNo, pymSeqNo, prepayBal, new ProcessPaymentCallback() {					
					@Override
					public Receipt getReceipt() {						
						PrepayOcc prepayOcc = prepayService.getPrepayOcc(prepayOccSeq); 
						
						Receipt receipt = new Receipt();
						receipt.setPymAcntId(prepayOcc.getPymAcntId());
						receipt.setDpstProcDt(prepayOcc.getDpstProcDt());
						receipt.setDpstDt(prepayOcc.getDpstDt());
						receipt.setDpstCl("16"); // 선수금대체 
						receipt.setDpstSeqNo(prepayOcc.getPrepayOccTgtSeqNo());
						receipt.setCrncyCd(prepayOcc.getCrncyCd());
						receipt.setExrate(prepayOcc.getExrate());
						receipt.setExrateAplyDt(prepayOcc.getExrateAplyDt());
						receipt.setCnclYn(prepayOcc.getCnclYn());
						receipt.setTransDt(prepayOcc.getTransDt());
						receipt.setSoId(prepayOcc.getSoId());
						receipt.setRegrId(prepayOcc.getRegrId());
						return receipt;
					}
				});
				
				Receipt receipt = result.getReceiptList().get(0);
				String prepayTransSeqNo = "";

				if (receipt.getRcptAmt() > 0.0) {
					PrepayOcc updatePrepayOcc = new PrepayOcc();
					
					updatePrepayOcc.setSoId(receipt.getSoId());
					updatePrepayOcc.setPrepayOccSeqNo(prepayOccSeq);
					updatePrepayOcc.setPrepayBal(receipt.getRcptAmt());
					updatePrepayOcc.setRegrId(userId);
					
					// 1. TBLPY_PREPAY_OCC PREPAY_TRANS_AMT, PREPAY_BAL, PREPAY_PROC_STAT = '3', TRANS_CMPL_YN update
					prepayService.updatePrepayOcc(updatePrepayOcc);
					
					if (result.getPymAcntId().equals(pymAcntId) == true) {
						receipt.setPayTp("2");
						updatePrepayOcc.setPrepayOccTp("02");
					} else {
						receipt.setPayTp("3");
						updatePrepayOcc.setPrepayOccTp("03");
					}
					//2. TBLPY_PREPAY_TRANS_HIST insert
					prepayTransSeqNo = prepayService.insertPrepayTransHistory(updatePrepayOcc);
				}
				
				List<CBillComm> updateBillMastList = new ArrayList<>();
				
				updateBillMastList.add(result.getUpdateBillMast());
				int update = paymentService_1.updateBillMastRcptAmt(updateBillMastList);

				List<CBillComm> updateBillList = result.getUpdateBillList();

				for (CBillComm bill : updateBillList) {
					// 5. TBLIV_BILL rcpt_amt, full_pay_yn update
					if ("Y".equals(result.getFullPayYn()) == true) {
						update = paymentService_1.updateFullPayBill(bill);
					} else {
						update = paymentService_1.updateBillRcptAmt(bill);
					}

					logger.debug(String.format("billSeqNo : %s, useYymm : %s, prodCmpsId : %s, svcCmpsId : %s", bill.getBillSeqNo(), bill.getUseYymm(), bill.getProdCmpsId(),
							bill.getSvcCmpsId()));
					logger.debug("청구내역에 대한 수납금액 반영 결과 : " + update);
				}

				
				result.getReceiptList().get(0).setPrepayTransSeqNo(prepayTransSeqNo);

				// 6. TBLPY_RCPT insert
				receiptService.insertReceipt(result.getReceiptList());
				
				// 7. TBLPY_RCPT_DTL insert
				receiptService.insertReceiptDetail(result.getReceiptDetailList());

				prepayAplyAmt = result.getRemainAmt();

//				if (prepayAplyAmt > 0.0 || "Y".equals(prepayGubun) == true) { // 2020.09.04 수정  아래 내용으로 수정
				if (prepayAplyAmt > 0.0) {
					
					PrepayOcc updateprepayOcc = new PrepayOcc();
					updateprepayOcc.setPrepayOccSeqNo(prepayOccSeq);
					updateprepayOcc.setPrepayBal(prepayAplyAmt);
					updateprepayOcc.setRegrId(userId);
					
					prepayService.updatePrepayOcc(updateprepayOcc);
					
					updateprepayOcc.setPrepayOccTp("05");
					prepayTransSeqNo = prepayService.insertPrepayTransHistory(updateprepayOcc);

					updateprepayOcc.setPrepayOccTgtSeqNo(prepayTransSeqNo);
					prepayService.insertNewPrepayOccFromPrepayOcc(updateprepayOcc);
				}
			}
			
		}
		

		return returnFlag;
	}
	

//	@Override
//	public int insertAction(BillingStatisticsVO billingStatisticsVO) {
//
//		int result = -1;
//
//		SessionUser session = CommonUtil.getSessionManager();
//		String userId = session.getUserId();
//		String userNm = session.getUserName();
//		String seqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.PY_TRNS_NO, 10);
//
//		billingStatisticsVO.setRegrId(userId);
//		billingStatisticsVO.setRegDate(DateUtil.sysdate());
//		billingStatisticsVO.setChgrId(userId);
//		billingStatisticsVO.setChgDate(DateUtil.sysdate());
//		billingStatisticsVO.setAppntId(userId);
//		billingStatisticsVO.setAppntNm(userNm);
//		billingStatisticsVO.setTransApplNo(seqNo);
//		billingStatisticsVO.setDcsnProcStat(Consts.DCSN_PROC_STAT.APPLIED); // 결제처리상태 BL00018 : 신청 = "01"
//
//		int cnt = advanceReceivedMapper.insertAction(billingStatisticsVO);
//
//		if (cnt > 0) {
//			String transApplCl = billingStatisticsVO.getTransApplCl();
//
//			try {
//				if (Consts.TRANS_APPL_CL.PRE_PAY.equals(transApplCl)) {
//					String typeCd = Consts.AMBG_REPL_TP.PRE_PAY_REP_RECEIPT;
//
//					if (!StringUtil.isEmpty(billingStatisticsVO.getTransTp()) && "02".equals(billingStatisticsVO.getTransTp())) {
//						typeCd = Consts.AMBG_REPL_TP.PRE_PAY_PYM_ACNT;
//					}
//
//					result = prepayTransService.processPrepayTrans(seqNo, typeCd, billingStatisticsVO.getInptMenuId(), userId);
//				} else if (Consts.TRANS_APPL_CL.AMBG.equals(transApplCl)) {
//					result = ambgTransService.processAmbgTrans(seqNo, AMBG_REPL_TP.AMBG_REP_RECEIPT, billingStatisticsVO.getInptMenuId(), userId);
//				}
//			} catch (Exception e) {
//				throw new ServiceException("MSG.M10.MSG00005"); // MSG.M10.MSG00005=처리에 실패했습니다. 관리자에게 문의해 주세요.
//			}
//		}
//		return result;
//	}
}
