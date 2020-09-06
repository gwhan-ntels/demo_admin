package com.ntels.ccbs.charge.service.common;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.common.Bill;
import com.ntels.ccbs.charge.domain.common.CBillComm;
import com.ntels.ccbs.charge.domain.common.Deposit;
import com.ntels.ccbs.charge.domain.common.PaymentResult;
import com.ntels.ccbs.charge.domain.common.Receipt;
import com.ntels.ccbs.charge.domain.common.ReceiptDetail;
import com.ntels.ccbs.charge.mapper.common.PaymentMapper_1;
import com.ntels.ccbs.common.util.DateUtil;

@Service
public class PaymentServiceImpl_1 implements PaymentService_1 {

	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PaymentMapper_1 paymentMapper_1;

	@Override
	public Double getBillRcptAmt(String billSeqNo, String useYymm, String prodCmpsId, String svcCmpsId, String chrgItmCd) {
		CBillComm bill = new CBillComm();

		bill.setBillSeqNo(billSeqNo);
		bill.setUseYymm(useYymm);
		bill.setProdCmpsId(prodCmpsId);
		bill.setSvcCmpsId(svcCmpsId);
		bill.setChrgItmCd(chrgItmCd);

		return paymentMapper_1.getBillRcptAmt(bill);
	}

	@Override
	public int updateBillCancel(CBillComm bill) {
		return paymentMapper_1.updateBillCancel(bill);
	}

	@Override
	public int updateBillMastCancel(CBillComm bill) {
		return paymentMapper_1.updateBillMastCancel(bill);
	}

	@Override
	public List<Bill> getBillSeqNo(String soId, String pymAcntId) {
		return paymentMapper_1.getBillSeqNo(soId, pymAcntId);
	}

	@Override
	public int updateDpstProc(Deposit deposit) {
		return paymentMapper_1.updateDpstProc(deposit);
	}
	

	@Override
	public PaymentResult processPayment(String billSeqNo, String pymSeqNo, double dpstAmt, String userId, ProcessPaymentCallback callback) {
		PaymentResult paymentResult = new PaymentResult();
		
		// 완납여부 체크를 하기위해 청구 마스터 테이블에서 납부 대상금액(미납금액)을 조회한다.
		Double unpayAmt = paymentMapper_1.getUnpayAmtFromBillMast(billSeqNo); 
		double remainAmt = dpstAmt - unpayAmt;
		paymentResult.setRemainAmt(remainAmt);

		double payAmt = 0;

		logger.debug("");
		logger.debug("==============================");
		logger.debug(String.format(" billSeqNo %s에 대한 수납처리 시작! 입금금액 : %f, 미납금액 : %f", billSeqNo, dpstAmt, unpayAmt));

		String pymAcntId = null;

		// 입금 금액이 납부 대상금액보다 크거나 같다면 완납조건은 성립한다.
		if (unpayAmt <= dpstAmt) {
			paymentResult.setFullPayYn("Y");
			payAmt = unpayAmt;

			// 수냅내역에 등록하기 위한 청구내역 조회 TODO 납부우선순위 적용 시 쿼리 수정 해야 함.(현재는 납부금액이 큰순으로정렬)
			logger.debug("수납내역에 등록하기위한 청구내역 조회 billSeqNo : " + billSeqNo);
			List<CBillComm> billList = paymentMapper_1.getBillListByBillSeqNo(billSeqNo);

			double sumPreRcptAmt = 0;
			double sumBillAmt = 0;
			double sumRcptAmt = 0;
			// 선수금 발생 금액
			double prepayAmt = dpstAmt - unpayAmt;

			String billYymm = null;
			String billCycl = null;
			String billDt = null;

			for (CBillComm bill : billList) {
				ReceiptDetail receiptDetail = new ReceiptDetail();
				receiptDetail.setSoId(bill.getSoId());
				receiptDetail.setPymSeqNo(pymSeqNo);
				receiptDetail.setProdCmpsId(bill.getProdCmpsId());
				receiptDetail.setSvcCmpsId(bill.getSvcCmpsId());
				receiptDetail.setChrgItmCd(bill.getChrgItmCd());
				receiptDetail.setBillAmt(bill.getBillAmt());
				receiptDetail.setPreRcptAmt(bill.getRcptAmt());
				receiptDetail.setRcptAmt(bill.getBillAmt());
				receiptDetail.setPreSoId(bill.getSoId());
				receiptDetail.setGrpId(bill.getGrpId());
				receiptDetail.setCustId(bill.getCustId());
				receiptDetail.setCtrtId(bill.getCtrtId());
				receiptDetail.setCrncyCd(bill.getCrncyCd());
				receiptDetail.setExrate(bill.getExrate());
				receiptDetail.setExrateAplyDt(bill.getExrateAplyDt());
				receiptDetail.setUseYymm(bill.getUseYymm());
				receiptDetail.setBillSeqNo(bill.getBillSeqNo());
				receiptDetail.setRegrId(userId);
				receiptDetail.setRegDate(new Timestamp(new Date().getTime()));
				receiptDetail.setChgrId(userId);
				receiptDetail.setChgDate(new Timestamp(new Date().getTime()));

				paymentResult.addReceiptDetail(receiptDetail);

				sumBillAmt += bill.getBillAmt();
				sumRcptAmt += bill.getBillAmt();
				sumPreRcptAmt += bill.getRcptAmt();

				if (billYymm == null) {
					billYymm = bill.getBillYymm();
					billCycl = bill.getBillCycl();
					billDt = bill.getBillDt();
					pymAcntId = bill.getPymAcntId();
				}
			}

			// TBLIV_BILL테이블에 완납된 청구번호에 해당하는 모든 데이터를 완납처리함
			CBillComm updateBill = new CBillComm();
			updateBill.setBillSeqNo(billSeqNo);
			updateBill.setChgrId(userId);
			updateBill.setChgDate(new Timestamp(new Date().getTime()));
			paymentResult.addUpdateBill(updateBill);

			// 수납내역 등록
			Receipt receipt = callback.getReceipt();

			if (receipt.getPymAcntId() == null) {
				receipt.setPymAcntId(pymAcntId);
			}

			receipt.setPymSeqNo(pymSeqNo);
			receipt.setBillSeqNo(billSeqNo);
			receipt.setBillYymm(billYymm);
			receipt.setBillCycl(billCycl);
			receipt.setBillDt(billDt);
			receipt.setPayProcDt(DateUtil.getDateStringYYYYMMDD(0));
			receipt.setPreRcptAmt(sumPreRcptAmt);
			receipt.setPayObjAmt(dpstAmt);
			receipt.setPayAplyAmt(sumBillAmt);
			receipt.setPrepayAplyAmt(prepayAmt);
//			receipt.setPrepayTransSeqNo("");
//			receipt.setAmbgTransSeqNo("");
//			receipt.setAssrTransSeqNo("");
			receipt.setRcptAmt(sumRcptAmt);
			receipt.setCnclYn("N");
			receipt.setRegrId(userId);
			receipt.setRegDate(new Timestamp(new Date().getTime()));
			receipt.setChgrId(userId);
			receipt.setChgDate(new Timestamp(new Date().getTime()));

			paymentResult.addReceipt(receipt);
		} else {
			paymentResult.setFullPayYn("N");

			List<CBillComm> billList = paymentMapper_1.getBillListByBillSeqNo(billSeqNo);

			if (billList.isEmpty() == true) {
				throw new RuntimeException();
			}

			payAmt = dpstAmt;

//			double payObjAmt = dpstAmt;
			double sumBillAmt = 0;
			double sumRcptAmt = 0;
			double sumPreRcptAmt = 0;

			String billYymm = null;
			String billCycl = null;
			String billDt = null;

			for (CBillComm bill : billList) {

				// 이미 완납이 된 청구이거나 입금금액이 더이상 남아있지 않다면 패스!
				if ("Y".equals(bill.getFullPayYn()) == true || dpstAmt <= 0) {
					continue;
				}

				logger.debug("");

				// 청구금액
				double billAmt = bill.getBillAmt();
				// 이전 수납금액
//				double preRcptAmt = bill.getRcptAmt();
				// 수납금액
				double rcptAmt = 0;

				if (billAmt < 0) {
					// 청구금액이 0보다 작은 경우에는..
					rcptAmt = billAmt;
					dpstAmt += (-1) * billAmt;
				} else if (billAmt == dpstAmt) {
					// 청구금액과 입금금액이 같다면 남은 입금금액은 없다.
					rcptAmt = billAmt;
					dpstAmt = 0;
				} else if (billAmt > dpstAmt) {
					rcptAmt = dpstAmt;
					dpstAmt = 0;
				} else if (billAmt < dpstAmt) {
					rcptAmt = billAmt;
					dpstAmt = dpstAmt - billAmt;
				}

				logger.debug(String.format(" BillSeqNo : %s, ChrgItmCd : %s, ProdCmpsId : %s, SvcCmpsId : %s", bill.getBillSeqNo(), bill.getChrgItmCd(), bill.getProdCmpsId(),
						bill.getSvcCmpsId()));
				logger.debug(" dpstAmt : " + dpstAmt);
				logger.debug(" rcptAmt : " + rcptAmt);
				logger.debug(" billAmt : " + billAmt);

				CBillComm updateBill = new CBillComm();
				updateBill.setRcptAmt(rcptAmt);
				updateBill.setBillSeqNo(bill.getBillSeqNo());
				updateBill.setProdCmpsId(bill.getProdCmpsId());
				updateBill.setSvcCmpsId(bill.getSvcCmpsId());
				updateBill.setChrgItmCd(bill.getChrgItmCd());
				updateBill.setChgrId(userId);
				updateBill.setChgDate(new Timestamp(new Date().getTime()));

				paymentResult.addUpdateBill(updateBill);

				// 수납 상세내역에 등록
				ReceiptDetail receiptDetail = new ReceiptDetail();
				receiptDetail.setPymSeqNo(pymSeqNo);
				receiptDetail.setProdCmpsId(bill.getProdCmpsId());
				receiptDetail.setSvcCmpsId(bill.getSvcCmpsId());
				receiptDetail.setChrgItmCd(bill.getChrgItmCd());
				receiptDetail.setBillAmt(bill.getBillAmt());
				receiptDetail.setPreRcptAmt(bill.getRcptAmt());
				receiptDetail.setRcptAmt(rcptAmt);
				receiptDetail.setPreSoId(bill.getSoId());
				receiptDetail.setSoId(bill.getSoId());
				receiptDetail.setGrpId(bill.getGrpId());
				receiptDetail.setCustId(bill.getCustId());
				receiptDetail.setCtrtId(bill.getCtrtId());
				receiptDetail.setCrncyCd(bill.getCrncyCd());
				receiptDetail.setExrate(bill.getExrate());
				receiptDetail.setExrateAplyDt(bill.getExrateAplyDt());
				receiptDetail.setUseYymm(bill.getUseYymm());
				receiptDetail.setBillSeqNo(bill.getBillSeqNo());
				receiptDetail.setRegrId(userId);
				receiptDetail.setRegDate(new Timestamp(new Date().getTime()));
				receiptDetail.setChgrId(userId);
				receiptDetail.setChgDate(new Timestamp(new Date().getTime()));

				paymentResult.addReceiptDetail(receiptDetail);

				sumPreRcptAmt += bill.getRcptAmt();
				sumRcptAmt += rcptAmt;
				sumBillAmt += bill.getBillAmt();

				if (billYymm == null) {
					billYymm = bill.getBillYymm();
					billCycl = bill.getBillCycl();
					billDt = bill.getBillDt();
					pymAcntId = bill.getPymAcntId();
				}

			}

			// 수납내역 등록
			Receipt receipt = callback.getReceipt();

			if (receipt.getPymAcntId() == null) {
				receipt.setPymAcntId(pymAcntId);
			}

			receipt.setPymSeqNo(pymSeqNo);
			receipt.setBillSeqNo(billSeqNo);
			receipt.setBillYymm(billYymm);
			receipt.setBillCycl(billCycl);
			receipt.setBillDt(billDt);
			receipt.setPayProcDt(DateUtil.getDateStringYYYYMMDD(0));
			receipt.setPreRcptAmt(sumPreRcptAmt);
			receipt.setPayObjAmt(dpstAmt);
			receipt.setPayAplyAmt(sumBillAmt);
			receipt.setPrepayAplyAmt(0);
			receipt.setPrepayTransSeqNo("");
			receipt.setAmbgTransSeqNo("");
			receipt.setAssrTransSeqNo("");
			receipt.setRcptAmt(sumRcptAmt);
			receipt.setCnclYn("N");
			receipt.setRegrId(userId);
			receipt.setRegDate(new Timestamp(new Date().getTime()));
			receipt.setChgrId(userId);
			receipt.setChgDate(new Timestamp(new Date().getTime()));

			paymentResult.addReceipt(receipt);
		}

		CBillComm updateBill = new CBillComm();

		updateBill.setBillSeqNo(billSeqNo);
		updateBill.setRcptAmt(payAmt);
		updateBill.setFullPayYn(paymentResult.getFullPayYn());
		updateBill.setChgrId(userId);
		updateBill.setChgDate(new Timestamp(new Date().getTime()));
		
		paymentResult.setUpdateBillMast(updateBill);
		paymentResult.setPymAcntId(pymAcntId);

//		clogService.writeLog("수납처리 완료");
//		clogService.writeLog("결과 오브젝트 출력 : " + ToStringBuilder.reflectionToString(paymentResult, ToStringStyle.MULTI_LINE_STYLE));
//		clogService.writeLog("==============================");

		return paymentResult;
	}

	@Override
	public int updateBillMastRcptAmt(final List<CBillComm> billList) {
		int cnt = 0;

		for (CBillComm bill : billList) {
			paymentMapper_1.updateBillMastRcptAmt(bill);
			cnt++;
		}

		return cnt;
	}

	@Override
	public int updateFullPayBill(CBillComm bill) {
		return paymentMapper_1.updateFullPayBill(bill);
	}

	@Override
	public int updateBillRcptAmt(CBillComm bill) {
		return paymentMapper_1.updateBillRcptAmt(bill);
	}
}