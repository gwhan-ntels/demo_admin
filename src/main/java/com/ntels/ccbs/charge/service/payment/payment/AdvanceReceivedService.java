package com.ntels.ccbs.charge.service.payment.payment;

import java.util.List;

import com.ntels.ccbs.charge.domain.billing.billing.BillingStatisticsVO;
import com.ntels.ccbs.charge.domain.common.PrepayOcc;
import com.ntels.ccbs.charge.domain.payment.advanceReceived.AdvanceReceivedVO;

public interface AdvanceReceivedService {

	public int getPrepayOccCount(AdvanceReceivedVO advanceReceivedVO);

	public List<AdvanceReceivedVO> getPrepayOccList(AdvanceReceivedVO advanceReceivedVO, int page, int perPage);

	public int getBillInvoiceCount(BillingStatisticsVO billingStatisticsVO);

	public List<BillingStatisticsVO> getBillInvoiceList(BillingStatisticsVO billingStatisticsVO, int page, int perPage);

	int getRefundAppliedCnt(String seqNo);

	int insertAction(PrepayOcc prepayOcc);
}
