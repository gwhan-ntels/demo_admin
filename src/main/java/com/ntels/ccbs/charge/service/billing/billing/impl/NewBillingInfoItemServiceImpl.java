package com.ntels.ccbs.charge.service.billing.billing.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.billing.billing.NewBillingInfoItemVO;
import com.ntels.ccbs.charge.mapper.billing.billing.NewBillingInfoItemMapper;
import com.ntels.ccbs.charge.service.billing.billing.NewBillingInfoItemService;
import com.ntels.ccbs.common.exception.ServiceException;

@Service
public class NewBillingInfoItemServiceImpl implements NewBillingInfoItemService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private NewBillingInfoItemMapper newBillingInfoItemMapper;

	@Override
	public List<NewBillingInfoItemVO> newBillingInfoItemListAction(NewBillingInfoItemVO newBillingInfoItemVO, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return newBillingInfoItemMapper.newBillingInfoItemListAction(newBillingInfoItemVO, start, end);
	}
	
	@Override
	public int newBillingInfoItemListCount(NewBillingInfoItemVO newBillingInfoItemVO) {
		return newBillingInfoItemMapper.newBillingInfoItemListCount(newBillingInfoItemVO);
	}

	@Override
	public List<NewBillingInfoItemVO> getNewSetItmNm(NewBillingInfoItemVO newBillingInfoItemVO) {

		return newBillingInfoItemMapper.getNewSetItmNm(newBillingInfoItemVO);
	}
	
	public List<NewBillingInfoItemVO> getBillSetupItemComboList(NewBillingInfoItemVO newBillingInfoItemVO, String referenceTypeCd) {
		newBillingInfoItemVO.setReferenceType(referenceTypeCd);
		return newBillingInfoItemMapper.getBillSetupItemComboList(newBillingInfoItemVO, referenceTypeCd);
	}
	
	@Override
	public int getBillSetupItemInsertValidate(NewBillingInfoItemVO newBillingInfoItemVO) {
		return newBillingInfoItemMapper.getBillSetupItemInsertValidate(newBillingInfoItemVO);
	}
	
	@Override
	public int getBillSetupItemAddValidate(NewBillingInfoItemVO newBillingInfoItemVO) {
		return newBillingInfoItemMapper.getBillSetupItemAddValidate(newBillingInfoItemVO);
	}

	@Override
	public int insertNewBillingInfoItemInfo(NewBillingInfoItemVO newBillingInfoItemVO) {
		String setItmCd = newBillingInfoItemVO.getSetItmCd();
		
		int dupSetItmCount = newBillingInfoItemMapper.getBillSetupItemInsertValidate(newBillingInfoItemVO);
		int dupSetCount = newBillingInfoItemMapper.getBillSetupItemAddValidate(newBillingInfoItemVO);
		
		if(dupSetItmCount > 0 && dupSetCount > 0)
				throw new ServiceException("MSG.M03.MSG00025");
		
		if(setItmCd == null || setItmCd.equals("") || setItmCd.equals("00000")) {
			String newSetItmCd = newBillingInfoItemMapper.getBillSetupItemMaxId(newBillingInfoItemVO);
			newBillingInfoItemVO.setSetItmCd(newSetItmCd);
		}
		
		if(dupSetItmCount == 0) {
			newBillingInfoItemMapper.insertBillSetupItem(newBillingInfoItemVO);
		}
		
		int count = newBillingInfoItemMapper.insertBillSetup(newBillingInfoItemVO);
		return count;
	}

	@Override
	public int updateNewBillingInfoItemInfo(NewBillingInfoItemVO newBillingInfoItemVO) {
		int updateValidateCount = newBillingInfoItemMapper.getBillSetupItemUpdateValidate(newBillingInfoItemVO);
		if(updateValidateCount > 0)
			throw new ServiceException("MSG.M09.MSG00052");
		
		newBillingInfoItemMapper.updateBillSetupItemCycle(newBillingInfoItemVO);
		newBillingInfoItemMapper.updateBillSetup(newBillingInfoItemVO);
		int count = newBillingInfoItemMapper.updateBillSetupItem(newBillingInfoItemVO);
		
		return count;
	}

	@Override
	public int deleteNewBillingInfoItemInfo(NewBillingInfoItemVO newBillingInfoItemVO) {
		int count = newBillingInfoItemMapper.deleteBillSetup(newBillingInfoItemVO);

		newBillingInfoItemMapper.deleteBillSetupItem(newBillingInfoItemVO);
		
		return count;
	}
}
