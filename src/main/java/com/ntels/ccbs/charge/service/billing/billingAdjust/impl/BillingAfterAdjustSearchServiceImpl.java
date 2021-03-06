package com.ntels.ccbs.charge.service.billing.billingAdjust.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAdjustVO;
import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAfterAdjustSearchVO;
import com.ntels.ccbs.charge.mapper.billing.billingAdjust.BillingAfterAdjustSearchMapper;
import com.ntels.ccbs.charge.service.billing.billingAdjust.BillingAfterAdjustSearchService;

@Service
public class BillingAfterAdjustSearchServiceImpl implements BillingAfterAdjustSearchService{

	@Autowired
	private BillingAfterAdjustSearchMapper billingAfterAdjustSearchMapper;
	@Override
	public Map<String, Object> getBillChargeAdjustReportList(BillingAfterAdjustSearchVO billingAfterAdjustSearchVO,
			String sidx, String sord, int page, int rows, String lng) {
		// TODO Auto-generated method stub
		
		Map<String, Object> billChargeAdjustReport = new HashMap<String, Object>();
		Integer totalCount = billingAfterAdjustSearchMapper.totalCount(billingAfterAdjustSearchVO);
		
		/*
		 *  page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		
		if(totalCount.intValue() == 0){
			billChargeAdjustReport.put("billChargeAdjustReportList", new ArrayList<BillingAfterAdjustSearchVO>());
			billChargeAdjustReport.put("totalCount", totalCount);
			billChargeAdjustReport.put("totalPages", new Integer(0));
			billChargeAdjustReport.put("page", new Integer(1));
		}else {
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			
			List<BillingAfterAdjustSearchVO> billChargeAdjustReportList = billingAfterAdjustSearchMapper.getBillChargeAdjustReportList(billingAfterAdjustSearchVO, sidx, sord, start, end, lng);
			
			billChargeAdjustReport.put("billChargeAdjustReportList", billChargeAdjustReportList);
			billChargeAdjustReport.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			billChargeAdjustReport.put("totalPages", totalPages);
			billChargeAdjustReport.put("page", new Integer(page));
		}
		
		return billChargeAdjustReport;
	}
	
	@Override
	public Map<String, Object> billingAfterSearchPopupDtlList(BillingAdjustVO billingAdVO, String sidx, String sord,
			int page, int rows, String lng) {
		// TODO Auto-generated method stub
		
		Map<String, Object> billChrgAdjReport = new HashMap<String, Object>();
		Integer totalCount = billingAfterAdjustSearchMapper.dtTotalCount(billingAdVO);
		
		/*
		 *  page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		
		if(totalCount.intValue() == 0){
			billChrgAdjReport.put("billChargeAdjustReportList", new ArrayList<BillingAfterAdjustSearchVO>());
			billChrgAdjReport.put("totalCount", totalCount);
			billChrgAdjReport.put("totalPages", new Integer(0));
			billChrgAdjReport.put("page", new Integer(1));
		}else {
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			
			List<BillingAdjustVO> billChargeAdjustReportList = billingAfterAdjustSearchMapper.billingAfterSearchPopupDtlList(billingAdVO, sidx, sord, start, end, lng);
			
			billChrgAdjReport.put("billChargeAdjustReportList", billChargeAdjustReportList);
			billChrgAdjReport.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			billChrgAdjReport.put("totalPages", totalPages);
			billChrgAdjReport.put("page", new Integer(page));
		}
		
		return billChrgAdjReport;
		
	}

	@Override
	public List<Map<String, Object>> listExcel(BillingAfterAdjustSearchVO billingAfterAdjustSearchVO) {
		// TODO Auto-generated method stub
		return billingAfterAdjustSearchMapper.listExcel(billingAfterAdjustSearchVO);
	}

	@Override
	public List<Map<String, Object>> popUpListExcel(BillingAdjustVO billingAdVO) {
		// TODO Auto-generated method stub
		return billingAfterAdjustSearchMapper.popUpListExcel(billingAdVO);
	}
	
	
}
