package com.ntels.ccbs.charge.service.billing.billingAdjust;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAdjustVO;
import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAfterAdjustSearchVO;

public interface BillingAfterAdjustSearchService {

	Map<String, Object> getBillChargeAdjustReportList(BillingAfterAdjustSearchVO billingAfterAdjustSearchVO,
			String sidx,
	        String sord,
	        int page, 
        	int rows, 
        	String lng);
	
	Map<String, Object> billingAfterSearchPopupDtlList(BillingAdjustVO billingAdVO,
			String sidx,
	        String sord,
	        int page, 
        	int rows, 
        	String lng);
	
	List<Map<String, Object>> listExcel(BillingAfterAdjustSearchVO billingAfterAdjustSearchVO);
	List<Map<String, Object>> popUpListExcel(BillingAdjustVO billingAdVO);
}
