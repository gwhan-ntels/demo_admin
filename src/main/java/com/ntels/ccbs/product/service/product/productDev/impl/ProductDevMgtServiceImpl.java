package com.ntels.ccbs.product.service.product.productDev.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.domain.product.productDev.ProductDevMgt;
import com.ntels.ccbs.product.mapper.product.productDev.ProductDevMgtMapper;
import com.ntels.ccbs.product.service.product.productDev.ProductDevMgtService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class ProductDevMgtServiceImpl implements ProductDevMgtService {

	@Autowired
	private SequenceService sequenceSevice;		
	
	@Autowired
	private ProductDevMgtMapper productDevMgtMapper;
	
	//오늘날짜 가져오기
    String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
    String currentDay1 =  DateUtil.getDateStringYYYYMMDDHH24MISS(0);
    
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
    
    public static Date getPreviousDate(Date date) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
          
        cal.add(Calendar.DATE, -1);  
          
        return cal.getTime();  
    } 		
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<ProductDevMgt> getDProdTree(String sessionLanguage, List<Map<String, Object>> soAuthList) {
		return productDevMgtMapper.getDProdTree(sessionLanguage,soAuthList);
	}

	@Override
	public List<ProductDevMgt> getDProdTree2(String sessionLanguage, List<Map<String, Object>> soAuthList, String currentDay) {
		return productDevMgtMapper.getDProdTree2(sessionLanguage,soAuthList, currentDay);
	}	
	
	@Override
	public int getProductListCount(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getProductListCount(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getProductList(ProductDevMgt productDevMgt,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return productDevMgtMapper.getProductList(productDevMgt, start, end);
	}
	
	@Override
	public int getProductListCount2(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getProductListCount2(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getProductList2(ProductDevMgt productDevMgt,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return productDevMgtMapper.getProductList2(productDevMgt, start, end);
	}	

	@Override
	public String getNextDispPriNo() {
		return productDevMgtMapper.getNextDispPriNo();
	}

	@Override
	public List<ProductDevMgt> getDualProductNm(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getDualProductNm(productDevMgt);
	}

	@Override
	public int addProduct(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.addProduct(productDevMgt);
	}

	@Override
	public int addProductDvlpHist(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.addProductDvlpHist(productDevMgt);
	}

	@Override
	public int modProduct(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.modProduct(productDevMgt);
	}

	@Override
	public int modProductDvlpHist(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.modProductDvlpHist(productDevMgt);
	}

	@Override
	public int getProdDvlpListCount(ProductDevMgt productDevMgt, List<Map<String, Object>> soAuthList) {
		return productDevMgtMapper.getProdDvlpListCount(productDevMgt, soAuthList);
	}



	@Override
	public List<ProductDevMgt> getProdDvlpList(ProductDevMgt productDevMgt,
			int page, int perPage, List<Map<String, Object>> soAuthList) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return productDevMgtMapper.getProdDvlpList(productDevMgt, start, end, soAuthList);
	}

	@Override
	public List<ProductDevMgt> getProdConfHistCnt(ProductDevMgt productMgt) {
		return productDevMgtMapper.getProdConfHistCnt(productMgt);
	}

	@Override
	public int addProdConfHist(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.addProdConfHist(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> transProdConfDtlList(ProductDevMgt productDevMgt) {
		List<ProductDevMgt> cntList = productDevMgtMapper.getProdConfHistCnt(productDevMgt);
		
		if (cntList.size() == 0) {
			
			String confReqCd = sequenceSevice.createNewSequence(Consts.SEQ_CODE.PD_TPMBI_PROD_CONF_HIST, "CR", 8);
			productDevMgt.setConfReqCd(confReqCd);
			
			productDevMgtMapper.addProdConfHist(productDevMgt);
		} 
		
		List<ProductDevMgt> confDtlList = productDevMgtMapper.getProdConfDtlList(productDevMgt);
				
		return confDtlList;
	}

	@Override
	public int modProdDvlpHistConfReq(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.modProdDvlpHistConfReq(productDevMgt);
	}

	@Override
	public int modProdConfHistConfReq(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.modProdConfHistConfReq(productDevMgt);
	}

	@Override
	public ProductDevMgt getModProductInit(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getModProductInit(productDevMgt);
	}

	@Override
	public String delProduct(ProductDevMgt productDevMgt) {

		productDevMgt.setDelFl("PROD_DELETE");
		//상품등록 삭제
		int count = productDevMgtMapper.delProduct(productDevMgt);
		//상품관계 삭제
		productDevMgtMapper.delProductRelationship(productDevMgt);
		//상품제공사은품 삭제
		productDevMgtMapper.delProductRelationshipGift(productDevMgt);
		//상품가입조건 삭제
		productDevMgtMapper.delProductSubscripCond(productDevMgt);
		//상품속성 삭제
		productDevMgtMapper.delProductAttribute(productDevMgt);
		//상품제공장비 삭제
		productDevMgtMapper.delSuprtEquip(productDevMgt);
		//상품별서비스의 삭제
		productDevMgtMapper.delProductService(productDevMgt);
		//과금항목속성 삭제
		productDevMgtMapper.delRateItemAttribute(productDevMgt);
		//과금항목요소 삭제
		productDevMgtMapper.delRateItemFactor(productDevMgt);
		//할인대상 과금항목 삭제
		productDevMgtMapper.delDiscSvcRateItmTyp(productDevMgt);
		//과금조건 삭제
		productDevMgtMapper.delRateItemCondition(productDevMgt);
		//과금요율 삭제
		productDevMgtMapper.delRate(productDevMgt);
		//할인배타 삭제
		productDevMgtMapper.delDiscExclusionRelationship(productDevMgt);
		//할인기간 삭제
		productDevMgtMapper.delDiscPeriod(productDevMgt);
		//공제정보의 삭제
		productDevMgtMapper.delAllowance(productDevMgt);
		//과금항목 삭제
		productDevMgtMapper.delRateItem(productDevMgt);
		//상품개발이력 업데이트
		productDevMgt.setDvlpStatus(Consts.PROD_MGT_CODE.DEV_CANCEL);
		productDevMgtMapper.cancelProductDvlpHist(productDevMgt);
	
		return String.valueOf(count);
	}

	@Override
	public String addCopyProduct(ProductDevMgt productDevMgt) {
		int count = -1;
		List<ProductDevMgt> tmpProdList =  productDevMgtMapper.getDualProductNm(productDevMgt);
		
		if (tmpProdList.size() == 0) {
			String prodCd = sequenceSevice.createNewSequence(Consts.SEQ_CODE.PD_TPMPD_D_PROD, "PD", 10); //8
			
			productDevMgt.setProdCd(prodCd);
			
			count = productDevMgtMapper.addProduct(productDevMgt);
			count = productDevMgtMapper.addCopyProductRelationship(productDevMgt);
			count = productDevMgtMapper.addCopyProductSubscripCond(productDevMgt);
			count = productDevMgtMapper.addCopyProductAttribute(productDevMgt);
			count = productDevMgtMapper.addCopyProductService(productDevMgt);
			count = productDevMgtMapper.addCopyProdSuprtGift(productDevMgt);
			count = productDevMgtMapper.addCopySuprtEquip(productDevMgt);
			
			List<ProductDevMgt> rateItmCdList = productDevMgtMapper.getRateItmCdList(productDevMgt);
			Iterator it = rateItmCdList.iterator();
			

			
			while(it.hasNext()) {
				ProductDevMgt tmpProductDevMgt = (ProductDevMgt) it.next();
				String newRateItmCd = sequenceSevice.createNewSequence(Consts.SEQ_CODE.PD_TPMPD_RATE_ITM, "RI", 5); //5
				tmpProductDevMgt.setProdCd(prodCd);
				tmpProductDevMgt.setRateItmCd(tmpProductDevMgt.getRateItmCd());
				tmpProductDevMgt.setNewRateItmCd(newRateItmCd);				
				tmpProductDevMgt.setCurrentDay(productDevMgt.getCurrentDay());
				tmpProductDevMgt.setRegrId(productDevMgt.getRegrId());
				tmpProductDevMgt.setChgrId(productDevMgt.getChgrId());
				tmpProductDevMgt.setSysdate(DateUtil.sysdate());
				tmpProductDevMgt.setCurrentDay(productDevMgt.getCurrentDay());
				
				count = productDevMgtMapper.addCopyRateItem(tmpProductDevMgt);
				count = productDevMgtMapper.addCopyRateItemAttribute(tmpProductDevMgt);
				count = productDevMgtMapper.addCopyRateItemFactor(tmpProductDevMgt);
				count = productDevMgtMapper.addCopyDiscSvcRateItmTyp(tmpProductDevMgt);
				count = productDevMgtMapper.addCopyRateItemCondition(tmpProductDevMgt);
				count = productDevMgtMapper.addCopyRate(tmpProductDevMgt);
				count = productDevMgtMapper.addCopyDiscExclusionRelationship(tmpProductDevMgt);
				count = productDevMgtMapper.addCopyDiscPeriod(tmpProductDevMgt);
				
			}	
			
			String newAlwnceCd = sequenceSevice.createNewSequence(Consts.SEQ_CODE.PD_TPMPD_ALWNCE, "AR", 8);
			productDevMgt.setNewAlwnceCd(newAlwnceCd);
			List<ProductDevMgt> alwnclCdList = productDevMgtMapper.getAlwnceCdList(productDevMgt);
			Iterator alwnclIt = alwnclCdList.iterator();
			while(alwnclIt.hasNext()) {
				ProductDevMgt alwnclProductDevMgt = (ProductDevMgt) it.next();
				alwnclProductDevMgt.setCurrentDay(productDevMgt.getCurrentDay());
				count = productDevMgtMapper.modCopyRate(alwnclProductDevMgt);
				count = productDevMgtMapper.addCopyAllowance(alwnclProductDevMgt);
			}
			
			productDevMgt.setDvlpTyp(Consts.PROD_MGT_CODE.PROD_CPY);
			productDevMgt.setDvlpStatus(Consts.PROD_MGT_CODE.DEV_ING);
			
			count = productDevMgtMapper.addProductDvlpHist(productDevMgt);
		}
		
		return String.valueOf(count);
	}

	@Override
	public List<ProductDevMgt> getCopyProductList(ProductDevMgt productDevMgt,
			List<Map<String, Object>> soAuthList) {
		return productDevMgtMapper.getCopyProductList(productDevMgt, soAuthList);
	}

	@Override
	public List<ProductDevMgt> getEqtClCdComboList(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getEqtClCdComboList(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getProductUpdateExtractList(
			ProductDevMgt productDevMgt, List<Map<String, Object>> soAuthList) {
		return productDevMgtMapper.getProductUpdateExtractList(productDevMgt, soAuthList);
	}

	@Override
	public String addExtractProduct(ProductDevMgt productDevMgt) {
		int count = -1;
		
		count = productDevMgtMapper.addExtractProduct(productDevMgt);
		count = productDevMgtMapper.addExtractProductRelationship(productDevMgt);
		count = productDevMgtMapper.addExtractProductSubscripCond(productDevMgt);
		count = productDevMgtMapper.addExtractProductAttribute(productDevMgt);
		count = productDevMgtMapper.addExtractProductService(productDevMgt);
		count = productDevMgtMapper.addExtractProdSuprtGift(productDevMgt);
		count = productDevMgtMapper.addExtractSuprtEquip(productDevMgt);
		
		int rateItmCnt = productDevMgtMapper.getExtractRateItem(productDevMgt);
		
		if (rateItmCnt > 0) {
			count = productDevMgtMapper.addExtractRateItem(productDevMgt);
		} 
		
		count = productDevMgtMapper.addExtractRateItemAttribute(productDevMgt);
		count = productDevMgtMapper.addExtractRateItemFactor(productDevMgt);
		count = productDevMgtMapper.addExtractDiscSvcRateItmTyp(productDevMgt);
		count = productDevMgtMapper.addExtractRateItemCondition(productDevMgt);
		count = productDevMgtMapper.addExtractRate(productDevMgt);
		count = productDevMgtMapper.addExtractDiscExclusionRelationship(productDevMgt);
		count = productDevMgtMapper.addExtractDiscPeriod(productDevMgt);
		count = productDevMgtMapper.addExtractAllowance(productDevMgt);
		
		productDevMgt.setDvlpTyp(Consts.PROD_MGT_CODE.PROD_UPT);
		productDevMgt.setDvlpStatus(Consts.PROD_MGT_CODE.DEV_ING);
		
		count = productDevMgtMapper.addProductDvlpHist(productDevMgt);
		
		return String.valueOf(count);
	}

	
	
	@Override
	public int getProductServiceListCount(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getProductServiceListCount(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getProductServiceList(
			ProductDevMgt productDevMgt, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return productDevMgtMapper.getProductServiceList(productDevMgt, start, end);
	}

	@Override
	public int getProductServiceListCount2(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getProductServiceListCount2(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getProductServiceList2(
			ProductDevMgt productDevMgt, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return productDevMgtMapper.getProductServiceList2(productDevMgt, start, end);
	}	
	
	@Override
	public List<ProductDevMgt> getAddProductServiceInit(
			ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getAddProductServiceInit(productDevMgt);
	}

	@Override
	public String addProductService(ProductDevMgt productDevMgt) {
		int count = -1;
		
		count = productDevMgtMapper.addProductService(productDevMgt);
		
		return String.valueOf(count);
	}

	@Override
	public String modProductService(ProductDevMgt productDevMgt) {

		int count = productDevMgtMapper.modProductService(productDevMgt);
		
		return String.valueOf(count);
	}

	@Override
	public String delProductService(ProductDevMgt productDevMgt) {
		int count = -1;
		
		productDevMgt.setDelFl("SERVICE_DELETE");
		
		count = productDevMgtMapper.delRateItemAttribute(productDevMgt);
		count = productDevMgtMapper.delRateItemFactor(productDevMgt);
		count = productDevMgtMapper.delDiscSvcRateItmTyp(productDevMgt);
		count = productDevMgtMapper.delRateItemCondition(productDevMgt);
		count = productDevMgtMapper.delRate(productDevMgt);
		count = productDevMgtMapper.delDiscExclusionRelationship(productDevMgt);
		count = productDevMgtMapper.delDiscPeriod(productDevMgt);
		count = productDevMgtMapper.delRateItem(productDevMgt);
		count = productDevMgtMapper.delProductService(productDevMgt);
		
		return String.valueOf(count);
	}

	@Override
	public List<ProductDevMgt> getSuprtEquipList(
			ProductDevMgt productDevMgt, int page, int perPage) {			
			int start = 0;
			int end = 0;
			
			start = (page-1)*perPage;
			end = perPage;		
			
			return productDevMgtMapper.getSuprtEquipList(productDevMgt, start, end);
	}

	@Override
	public int getSuprtEquipListCount(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getSuprtEquipListCount(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getSuprtEquipList2(
			ProductDevMgt productDevMgt, int page, int perPage) {			
			int start = 0;
			int end = 0;
			
			start = (page-1)*perPage;
			end = perPage;		
			
			return productDevMgtMapper.getSuprtEquipList2(productDevMgt, start, end);
	}

	@Override
	public int getSuprtEquipListCount2(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getSuprtEquipListCount2(productDevMgt);
	}	
	
	@Override
	public List<ProductDevMgt> getEqtClCdComboList2(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getEqtClCdComboList2(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getSbjProdCdComboList(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getSbjProdCdComboList(productDevMgt);
	}

	@Override
	public int getNextDispPriNo2(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getNextDispPriNo2(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getSbjSvcCdComboList(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getSbjSvcCdComboList(productDevMgt);
	}

	@Override
	public String addSuprtEquip(ProductDevMgt productDevMgt) {
		int count = productDevMgtMapper.addSuprtEquip(productDevMgt);
		
		return String.valueOf(count);
	}

	@Override
	public ProductDevMgt getSuprtEquip(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getSuprtEquip(productDevMgt);
	}

	@Override
	public String modSuprtEquip(ProductDevMgt productDevMgt) {
		int count = productDevMgtMapper.modSuprtEquip(productDevMgt);
		
		return String.valueOf(count);
	}

	@Override
	public String delSuprtEquip(ProductDevMgt productDevMgt) {
		int count = productDevMgtMapper.delSuprtEquip(productDevMgt);
		
		return String.valueOf(count);
	}

	@Override
	public int getProductAttrListCount(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getProductAttrListCount(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getProductAttrList(ProductDevMgt productDevMgt,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return productDevMgtMapper.getProductAttrList(productDevMgt, start, end);
	}

	@Override
	public int getProductAttrListCount3(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getProductAttrListCount3(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getProductAttrList3(ProductDevMgt productDevMgt,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return productDevMgtMapper.getProductAttrList3(productDevMgt, start, end);
	}	
	
	@Override
	public List<ProductDevMgt> getProductAttrList2(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getProductAttrList2(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getCommonGrpCdList(
			List<Map<String, Object>> commonGrp, String lngTyp) {
		return productDevMgtMapper.getCommonGrpCdList(commonGrp, lngTyp);
	}

	@Override
	public int getProductAttrCnt(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getProductAttrCnt(productDevMgt);
	}

	@Override
	public int addProductAttribute(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.addProductAttribute(productDevMgt);
	}

	@Override
	public int modProductAttribute(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.modProductAttribute(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getProductRelationshipList(
			ProductDevMgt productDevMgt, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return productDevMgtMapper.getProductRelationshipList(productDevMgt, start, end);
	}

	@Override
	public int getProductRelationshipListCount(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getProductRelationshipListCount(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getProductRelationshipList2(
			ProductDevMgt productDevMgt, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return productDevMgtMapper.getProductRelationshipList2(productDevMgt, start, end);
	}

	@Override
	public int getProductRelationshipListCount2(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getProductRelationshipListCount2(productDevMgt);
	}	
	
	@Override
	public List<ProductDevMgt> getProductRelComboList(
			ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getProductRelComboList(productDevMgt);
	}

	@Override
	public String addProductRelationship(ProductDevMgt productDevMgt) {
		int count = -1;		
			count =	productDevMgtMapper.addProductRelationship(productDevMgt);
			
		return String.valueOf(count);	
	}

	@Override
	public String delProductRelationship(ProductDevMgt productDevMgt) {
		int count = -1;
			
		count = productDevMgtMapper.delProductRelationship(productDevMgt);
		count = productDevMgtMapper.delProductRelationshipGift(productDevMgt);
		
		return String.valueOf(count);	
	}

	@Override
	public List<ProductDevMgt> getRateItmList(ProductDevMgt productDevMgt,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return productDevMgtMapper.getRateItmList(productDevMgt, start, end);
	}
	@Override
	public List<ProductDevMgt> getRateItmList2(ProductDevMgt productDevMgt,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return productDevMgtMapper.getRateItmList2(productDevMgt, start, end);
	}
	@Override
	public int getRateItmListCount(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getRateItmListCount(productDevMgt);
	}
	@Override
	public int getRateItmListCount2(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getRateItmListCount2(productDevMgt);
	}
	@Override
	public String addRetrieveRateItm(ProductDevMgt productDevMgt) {
		int count = -1;
		
		productDevMgt.setRateItmCd("");	
		List<ProductDevMgt> rateItmCdList = productDevMgtMapper.getRetrieveSvcRateItmTypList(productDevMgt);
		
		if (rateItmCdList.size() > 0) {
			Iterator it = rateItmCdList.iterator();
			
			while(it.hasNext()) {
				String rateItmCd = sequenceSevice.createNewSequence(Consts.SEQ_CODE.PD_RATE_ITM_CD, "RI", 5);					
				ProductDevMgt tmpProductDevMgt = (ProductDevMgt) it.next();
				tmpProductDevMgt.setRateItmCd(rateItmCd);				
				tmpProductDevMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
				tmpProductDevMgt.setRateRefTyp(Consts.PROD_MGT_CODE.RATE_REF_TYP);
				tmpProductDevMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
				tmpProductDevMgt.setCurrentDay(productDevMgt.getCurrentDay());
				tmpProductDevMgt.setRegrId(productDevMgt.getRegrId());
				tmpProductDevMgt.setChgrId(productDevMgt.getChgrId());
				tmpProductDevMgt.setSysdate(DateUtil.sysdate());				
				
				count = productDevMgtMapper.addRetrieveRateItem(tmpProductDevMgt);
				count = productDevMgtMapper.addRetrieveRateItemAttribute(tmpProductDevMgt);
				count = productDevMgtMapper.addRetrieveRateItemFactor(tmpProductDevMgt);
			}
		}
		
		
		return String.valueOf(count);	
	}

	@Override
	public List<ProductDevMgt> getAddRateItmInit(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getAddRateItmInit(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getDefltRateItemList(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getDefltRateItemList(productDevMgt);
	}

	
	
	@Override
	public List<ProductDevMgt> getDualRateItmNm(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getDualRateItmNm(productDevMgt);
	}

	@Override
	public String addRateItm(ProductDevMgt productDevMgt) {
		int count = -1;
		String rateItmCd = sequenceSevice.createNewSequence(Consts.SEQ_CODE.PD_RATE_ITM_CD, "RI", 5);
		productDevMgt.setRateItmCd(rateItmCd);	
		count = productDevMgtMapper.addRateItm(productDevMgt);
		
		
		return String.valueOf(count);
	}

	@Override
	public ProductDevMgt getRateItm(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getRateItm(productDevMgt);
	}

	@Override
	public String modRateItm(ProductDevMgt productDevMgt) {
		int count = -1;
		
		List<ProductDevMgt> nmList = productDevMgtMapper.getDualRateItmNm(productDevMgt);
		
		if (nmList.size() == 0) {
			count = productDevMgtMapper.modRateItm(productDevMgt);
		} 
		
		return String.valueOf(count);
	}

	@Override
	public String delRateItm(ProductDevMgt productDevMgt) {
		int count = -1;
		productDevMgt.setDelFl("RATE_ITM_DELETE");
		List<ProductDevMgt> nmList = productDevMgtMapper.getRateItmRateItmCd(productDevMgt);
		
		if (nmList.size() <= 0) {
			count = productDevMgtMapper.delRateItemAttribute(productDevMgt);
			count = productDevMgtMapper.delRateItemFactor(productDevMgt);
			count = productDevMgtMapper.delDiscSvcRateItmTyp(productDevMgt);
			count = productDevMgtMapper.delRateItemCondition(productDevMgt);
			count = productDevMgtMapper.delRate(productDevMgt);
			count = productDevMgtMapper.delDiscExclusionRelationship(productDevMgt);
			count = productDevMgtMapper.delDiscPeriod(productDevMgt);
			count = productDevMgtMapper.delRateItem(productDevMgt);
		} 		
		
		return String.valueOf(count);
	}

	@Override
	public int getRateItmFctrListCount(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getRateItmFctrListCount(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getRateItmFctrList(ProductDevMgt productDevMgt,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return productDevMgtMapper.getRateItmFctrList(productDevMgt, start, end);
	}
	
	@Override
	public int getRateItmFctrListCount2(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getRateItmFctrListCount2(productDevMgt);
	}
	@Override
	public List<ProductDevMgt> getRateItmFctrList2(ProductDevMgt productDevMgt,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return productDevMgtMapper.getRateItmFctrList2(productDevMgt, start, end);
	}@Override
	public List<ProductDevMgt> getRateItmRegCnt(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getRateItmRegCnt(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getRetrieveRateItemFactor(
			ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getRetrieveRateItemFactor(productDevMgt);
	}

	@Override
	public int getRetrieveRateItemFactorNumber(String rateItmCd) {
		return productDevMgtMapper.getRetrieveRateItemFactorNumber(rateItmCd);
	}

	@Override
	public int addRetrieveRateItemFactor_2(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.addRetrieveRateItemFactor_2(productDevMgt);
	}

	@Override
	public ProductDevMgt getModRateItmFctrInit(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getModRateItmFctrInit(productDevMgt);
	}

	@Override
	public String modRateItmFctr(ProductDevMgt productDevMgt) {
		int count = -1;
		
		List<ProductDevMgt> noList = productDevMgtMapper.getDualRateItmFctrNo(productDevMgt);
		
		if (noList.size() == 0 ) {
			count = productDevMgtMapper.modRateItmFctr(productDevMgt);
		} 
		
		return String.valueOf(count);
	}

	@Override
	public String delRateItmFctr(ProductDevMgt productDevMgt) {
		int count = -1;
		List<ProductDevMgt> rateItmCondList = productDevMgtMapper.getRateItmCondFctrList(productDevMgt);
		
		if (rateItmCondList.size() > 0 ) {
			count = -2;
			return String.valueOf(count);
		} 
		
		List<ProductDevMgt> discPerdList = productDevMgtMapper.getDiscPeriodFctrList(productDevMgt);
		
		if (discPerdList.size() > 0 ) {
			count = -3;
			return String.valueOf(count);
		} 
		
		productDevMgt.setDelFl("FACTOR_DELETE");
		count = productDevMgtMapper.delRateItemFactor(productDevMgt);
		
		return String.valueOf(count);
	}

	@Override
	public int getRateItmAttrListCount(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getRateItmAttrListCount(productDevMgt);
	}
	
	@Override
	public int getRateItmAttrListCount2(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getRateItmAttrListCount2(productDevMgt);
	}
	
	@Override
	public List<ProductDevMgt> getRateItmAttrList(ProductDevMgt productDevMgt,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return productDevMgtMapper.getRateItmAttrList(productDevMgt, start, end);
	}
	@Override
	public List<ProductDevMgt> getRateItmAttrList2(ProductDevMgt productDevMgt,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return productDevMgtMapper.getRateItmAttrList2(productDevMgt, start, end);
	}
	@Override
	public String addRetrieveRateItemAttribute(ProductDevMgt productDevMgt) {
		int count = -1;
		
		count = productDevMgtMapper.addRetrieveRateItemAttribute(productDevMgt);
		return String.valueOf(count);
	}

	@Override
	public List<ProductDevMgt> getRateItmAttrList2(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getRateItmAttrList2(productDevMgt);
	}

	@Override
	public int getRateItmAttributeCnt(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getRateItmAttributeCnt(productDevMgt);
	}

	@Override
	public int addRateItmAttribute(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.addRateItmAttribute(productDevMgt);
	}

	@Override
	public int modRateItmAttribute(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.modRateItmAttribute(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getTableDataList(Map<String, String> conTableMap) {
		return productDevMgtMapper.getTableDataList(conTableMap);
	}

	@Override
	public int getRateItmCondListCount(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getRateItmCondListCount(productDevMgt);
	}
	@Override
	public int getRateItmCondListCount2(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getRateItmCondListCount2(productDevMgt);
	}
	@Override
	public List<ProductDevMgt> getRateItmCondList(ProductDevMgt productDevMgt,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return productDevMgtMapper.getRateItmCondList(productDevMgt, start, end);
	}
	@Override
	public List<ProductDevMgt> getRateItmCondList2(ProductDevMgt productDevMgt,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return productDevMgtMapper.getRateItmCondList2(productDevMgt, start, end);
	}
	@Override
	public List<ProductDevMgt> getAddRateItmCondInit(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getAddRateItmCondInit(productDevMgt);
	}

	@Override
	public String addRateItmCond(ProductDevMgt productDevMgt) {
		int count = -1;
	
		count = productDevMgtMapper.addRateItmCond(productDevMgt);
		return String.valueOf(count);
	}

	@Override
	public ProductDevMgt getRateItmCond(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getRateItmCond(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getRateItmCondComboList(
			ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getAddRateItmCondInit(productDevMgt);
	}

	@Override
	public String modRateItmCond(ProductDevMgt productDevMgt) {
		int count = -1;
		count = productDevMgtMapper.modRateItmCond(productDevMgt);
		return String.valueOf(count);
	}

	@Override
	public String delRateItmCond(ProductDevMgt productDevMgt) {
		int count = -1;
		count = productDevMgtMapper.delRateItemCondition(productDevMgt);
		return String.valueOf(count);
	}

	@Override
	public int getAllowanceListCount(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getAllowanceListCount(productDevMgt);
	}
	@Override
	public int getAllowanceListCount2(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getAllowanceListCount2(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getAllowanceList(ProductDevMgt productDevMgt,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;			
		return productDevMgtMapper.getAllowanceList(productDevMgt, start, end);
	}	
	@Override
	public List<ProductDevMgt> getAllowanceList2(ProductDevMgt productDevMgt,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;			
		return productDevMgtMapper.getAllowanceList2(productDevMgt, start, end);
	}	
	@Override
	public String addRateExtractList(ProductDevMgt productDevMgt) {
		int count = -1;
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("RATE_ITM_CD", productDevMgt.getRateItmCd());
		map.put("REGR_ID", productDevMgt.getRegrId());
		map.put("CHGR_ID", productDevMgt.getChgrId());
		map.put("INACT_DT", productDevMgt.getInactDt());
		map.put("MSTR_FL", productDevMgt.getMstrFl());
		map.put("CURRENT_DAY", productDevMgt.getCurrentDay());
		map.put("SYS_DATE", productDevMgt.getSysdate());
		
        for ( int iIdx = 1; iIdx <= 9; iIdx++ )
        {
            map.put( "COLUMN_ID" + Integer.toString( iIdx ), "NULL" );
            map.put( "COND" + Integer.toString( iIdx ), "" );
            map.put( "TABLE_ID" + Integer.toString( iIdx ), "" );
        }
		
        List<ProductDevMgt> fctrList = productDevMgtMapper.getRateFctrInfo(productDevMgt);
        
	    Iterator fctrIt = fctrList.iterator();
	    while ( fctrIt.hasNext() )
	    {
	    	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) fctrIt.next();	
	    	
	    	if (tmpProductDevMgt.getDataTyp().equals("4")){
	    		map.put("COLUMN_ID" + tmpProductDevMgt.getFctrNo(), "COMMON_CD");
	    		map.put("TABLE_ID" + tmpProductDevMgt.getFctrNo(), ",TSYCO_CODE_DETAIL");
	    		map.put( "COND" + tmpProductDevMgt.getFctrNo(), " AND COMMON_GRP    = '" + tmpProductDevMgt.getRefCd() + "'" );
	    		
	    		
	    	} else if (tmpProductDevMgt.getDataTyp().equals("6")) {
                map.put( "COLUMN_ID" + tmpProductDevMgt.getFctrNo(), tmpProductDevMgt.getRefColmnId() );
                map.put( "TABLE_ID" + tmpProductDevMgt.getFctrNo(), "," + tmpProductDevMgt.getRefTableId() );
                if ( tmpProductDevMgt.getRefTableCond() != null ) {
                    map.put( "COND" + tmpProductDevMgt.getFctrNo(), "AND " + tmpProductDevMgt.getRefTableCond() );
                }    
	    	} else {
	    		map.put("COLUMN_ID" + tmpProductDevMgt.getFctrNo(), "NULL");
	    	}
		
	    }        
	    
	    count = productDevMgtMapper.addRateExtractList(map);
      	
		return String.valueOf(count);
	}

	@Override
	public List<ProductDevMgt> getAddRateItmRateFctrInit(
			ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getAddRateItmRateFctrInit(productDevMgt);
	}

	
	@Override
	public List<ProductDevMgt> getOprndNm(ProductDevMgt productDevMgt) {
		List<ProductDevMgt> resultList = null;
        resultList = productDevMgtMapper.getOprndNm(productDevMgt); 
		return resultList;
	}
	
	@Override
	public List<ProductDevMgt> getAddRateItmRateFctrList(
			ProductDevMgt productDevMgt) {
		List<ProductDevMgt> resultList = null;
        resultList = productDevMgtMapper.getAddRateItmRateFctrList(productDevMgt); 
		return resultList;
	}
	
	@Override
	public String addRate(ProductDevMgt productDevMgt) {
		int count = -1;
		
		
		List<ProductDevMgt> resultList = productDevMgtMapper.getDupRateItmRateList(productDevMgt);
		
		if (resultList.size() == 0) {
			count = productDevMgtMapper.addRate(productDevMgt);
		}
		
		
		return String.valueOf(count);
	}

	@Override
	public ProductDevMgt getRateItmRate(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getRateItmRate(productDevMgt);
	}

	@Override
	public String modRate(ProductDevMgt productDevMgt) {
		int count = -1;
		count = productDevMgtMapper.modRate(productDevMgt);
		return String.valueOf(count);
	}

	@Override
	public String delRate(ProductDevMgt productDevMgt) {
		int count = -1;
		count = productDevMgtMapper.delRate(productDevMgt);
		return String.valueOf(count);
	}

	@Override
	public int getConfSbjListCount(ProductDevMgt productDevMgt,
			List<Map<String, Object>> soAuthList) {
		return productDevMgtMapper.getConfSbjListCount(productDevMgt, soAuthList);
	}

	@Override
	public List<ProductDevMgt> getConfSbjList(ProductDevMgt productDevMgt,
			int page, int perPage, List<Map<String, Object>> soAuthList) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return productDevMgtMapper.getConfSbjList(productDevMgt, start, end, soAuthList);
	}

	@Override
	public int getTransOperListCount(ProductDevMgt productDevMgt,
			List<Map<String, Object>> soAuthList) {
		return productDevMgtMapper.getTransOperListCount(productDevMgt, soAuthList);
	}

	@Override
	public List<ProductDevMgt> getTransOperList(ProductDevMgt productDevMgt,
			int page, int perPage, List<Map<String, Object>> soAuthList) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return productDevMgtMapper.getTransOperList(productDevMgt, start, end, soAuthList);
	}

	@Override
	public List<ProductDevMgt> getConfDeptComboList(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getConfDeptComboList(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getConfUsrIdList(ProductDevMgt productDevMgt,
			List<Map<String, Object>> soAuthList) {
		return productDevMgtMapper.getConfUsrIdList(productDevMgt, soAuthList);
	}

	@Override
	public String addProdConfDtl(List<ProductDevMgt> params, SessionUser sessionUser) {
		int result = -1;
		
		String usrId = sessionUser.getUserId();		
		
		for (int i=0; i<params.size(); i++) {
			params.get(i).setChgrId(usrId);	
			
			List<ProductDevMgt> dupList = productDevMgtMapper.getDupConferCd(params.get(i));
			if ( dupList.size() == 0 ){
				params.get(i).setSysdate(DateUtil.sysdate());
				params.get(i).setCurrentDay(currentDay);
				result = productDevMgtMapper.addProdConfDtl(params.get(i));
				
				params.get(i).setConfStatus(Consts.PROD_MGT_CODE.CONF_REQ_ING);
				result = productDevMgtMapper.addProdConfHist(params.get(i));
				
			} else {
				result = -1;
				return String.valueOf(result);
			}
				
		}		
		
		return String.valueOf(result);
	}

	@Override
	public String modProdConfReqProc(List<ProductDevMgt> params) {
		int result = -1;
		
		for (int i=0; i<params.size(); i++) {
			params.get(i).setModFl("CONF_REQ");
			params.get(i).setDvlpStatus(Consts.PROD_MGT_CODE.SUBMIT_ING);
			result = productDevMgtMapper.modProdDvlpHistConfReq(params.get(i));
			
			params.get(i).setConfStatus(Consts.PROD_MGT_CODE.CONF_REQ);
			params.get(i).setCurrentDay(currentDay1);
			result = productDevMgtMapper.modProdConfHistConfReq(params.get(i));	
		}
		return String.valueOf(result);
	}

	@Override
	public String modProdConfReqCancelProc(ProductDevMgt productDevMgt) {
		int result = -1;
		
		result = productDevMgtMapper.modProdDvlpHistConfReq(productDevMgt);
		result = productDevMgtMapper.modProdConfHistConfReq(productDevMgt);
		result = productDevMgtMapper.delProdConfDtl(productDevMgt);
		
		return String.valueOf(result);
	}

	@Override
	public String modConfReturn(ProductDevMgt productDevMgt) {
		int result = -1;
		result = productDevMgtMapper.modConfReturn(productDevMgt);
		
		if (productDevMgt.getConfRslt().equals(Consts.PROD_MGT_CODE.CONF_RSLT_RETURN))
		{
			productDevMgt.setModFl("CONF_REQ_CANCEL");
			productDevMgt.setDvlpStatus(Consts.PROD_MGT_CODE.CONFIRMRETURN);
			result = productDevMgtMapper.modProdDvlpHistConfReq(productDevMgt);
		} else 
		{
			ProductDevMgt cnt = productDevMgtMapper.getConfCnt(productDevMgt);
			if(cnt.getConfReqCnt().equals(cnt.getConfrOkCnt())) {
				productDevMgt.setModFl("CONF_REQ_CANCEL");
				productDevMgt.setDvlpStatus(Consts.PROD_MGT_CODE.CONFIRMOK);
				result = productDevMgtMapper.modProdDvlpHistConfReq(productDevMgt);
				productDevMgt.setModFl("CONF_REQ_CANCEL");
				productDevMgt.setConfStatus(Consts.PROD_MGT_CODE.CONF_RETURN_OK);
				result = productDevMgtMapper.modProdConfHistConfReq(productDevMgt);
			}
		}
			
		
		return String.valueOf(result);
	}

	@Override
	public List<ProductDevMgt> getProductRelationshipListAll(
			ProductDevMgt productDevMgt, List<Map<String, Object>> soAuthList) {
		return productDevMgtMapper.getProductRelationshipListAll(productDevMgt, soAuthList);
	}

	@Override
	public List<ProductDevMgt> getProductdefultRelationsList_res(
			ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getProductdefultRelationsList_res(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getProductdefultRelationsList_req(
			ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getProductdefultRelationsList_req(productDevMgt);
	}

	@Override
	public ProductDevMgt getProdect(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getProdect(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getProductRelationshipListAll_prod_case_1(
			ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getProductRelationshipListAll_prod_case_1(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getProductRelationshipListAll_prod_case_2(
			ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getProductRelationshipListAll_prod_case_2(productDevMgt);
	}

	@Override
	public String setProductRelationship(List<ProductDevMgt> params,
			SessionUser sessionUser) {
		int result = -1;
		
		String usrId = sessionUser.getUserId();		
		
		for (int i=0; i<params.size(); i++) {
			params.get(i).setChgrId(usrId);
			params.get(i).setRegrId(usrId);
			params.get(i).setSysdate(DateUtil.sysdate());
			params.get(i).setCurrentDay(currentDay);
			params.get(i).setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
			params.get(i).setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);	
				
			if (params.get(i).getDefYn().equals("Yes")) {
				params.get(i).setDefYn("Y");
			} else if (params.get(i).getDefYn().equals("No")) {
				params.get(i).setDefYn("N");
			}
			
			if (params.get(i).getProdCase().equals("1")) {
				result = productDevMgtMapper.setRelationship_1(params.get(i));
				result = productDevMgtMapper.setProductRelationship_prod_case_1(params.get(i));
			} else if (params.get(i).getProdCase().equals("2")) {
				result = productDevMgtMapper.setRelationship_2(params.get(i));
				result = productDevMgtMapper.setProductRelationship_prod_case_2(params.get(i));				
			}
				
		}		
		
		return String.valueOf(result);
	}

	@Override
	public String setProductCancel(List<ProductDevMgt> params) {
		int result = -1;
		
		for (int i=0; i<params.size(); i++) {
			
			if (params.get(i).getProdCase().equals("1")) {
				result = productDevMgtMapper.setProductCancel(params.get(i));
			} else if (params.get(i).getProdCase().equals("2")) {
				result = productDevMgtMapper.setProductCancel_1(params.get(i));		
			}
				
		}		
		
		return String.valueOf(result);
	}
	
	@Override
	public ProductDevMgt getProdDvlpStatus(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getProdDvlpStatus(productDevMgt);
	}
	
	@Override
	public ProductDevMgt getModProductServiceInit(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getModProductServiceInit(productDevMgt);
	}

	@Override
	public String transfer(ProductDevMgt productDevMgt) {
		int result = -1;

		System.out.println("getDvlpTyp================>"+productDevMgt.getDvlpTyp());

		if (productDevMgt.getDvlpTyp().equals(Consts.PROD_MGT_CODE.PROD_INSERT)){
			
			System.out.println("등록================>");
			
			result = productDevMgtMapper.addTransferProduct(productDevMgt);
			int count = productDevMgtMapper.getTransProdRel(productDevMgt);
			if (count != 0 ){
				result = productDevMgtMapper.addTransProdRel(productDevMgt);
			}
			
			result = productDevMgtMapper.addTransferProductSubscripCond(productDevMgt);
			result = productDevMgtMapper.addTransferProductAttribute(productDevMgt);
			result = productDevMgtMapper.addTransferProductService(productDevMgt);
			result = productDevMgtMapper.addTransferProdSuprtGift(productDevMgt);
			
			int count1 = productDevMgtMapper.getTransferSuprtEquip(productDevMgt);
			if (count1 != 0 ){
				result = productDevMgtMapper.addTransferSuprtEquip(productDevMgt);
			}
			
			int count2 = productDevMgtMapper.getTransferRateItem(productDevMgt);
			if (count2 != 0 ){
				result = productDevMgtMapper.addTransferRateItem(productDevMgt);
			}			
			
			result = productDevMgtMapper.addTransferRateItemAttribute(productDevMgt);
			result = productDevMgtMapper.addTransferRateItemFactor(productDevMgt);
			result = productDevMgtMapper.addTransferDiscSvcRateItmTyp(productDevMgt);
			result = productDevMgtMapper.addTransferRateItemCondition(productDevMgt);
			result = productDevMgtMapper.addTransferRate(productDevMgt);
			result = productDevMgtMapper.addTransferDiscExcl(productDevMgt);
			result = productDevMgtMapper.addTransferDiscPeriod(productDevMgt);
			result = productDevMgtMapper.addTransferAllowance(productDevMgt);			
		}
		System.out.println("productDevMgt.getModTyp()================>"+productDevMgt.getModTyp());
		//if (productDevMgt.getDvlpTyp().equals(Consts.PROD_MGT_CODE.PROD_UPT) && productDevMgt.getModTyp().equals(Consts.PROD_MGT_CODE.PROD_MOD_TYP_UPDATE)){
		if (productDevMgt.getDvlpTyp().equals(Consts.PROD_MGT_CODE.PROD_UPT)){	
			System.out.println("수정1================>");
			
            transferProdUpdate(productDevMgt); //상품 변경
            transferProdRelUpdate(productDevMgt); //상품관계 변경
            transferProdSubscripUpdate(productDevMgt); //상품가입조건 변경
            transferProdAttrUpdate(productDevMgt); //상품속성 변경
            transferProdSvcUpdate(productDevMgt); //상품별서비스 변경
            transferProdSuprtGiftUpdate(productDevMgt); //상품제공사은품 변경
            transferSuprtEquipUpdate(productDevMgt); //상품제공장비 변경
            transferRateItmUpdate(productDevMgt); //과금항목 변경
            transferRateItmAttrUpdate(productDevMgt); //과금항목속성 변경
            transferRateItmFctrUpdate(productDevMgt); //과금항목요소 변경
            transferRateItmCondUpdate(productDevMgt); //과금조건 변경
            transferRateUpdate(productDevMgt); //과금요율 변경
            transferDiscSvcRateItmTypUpdate(productDevMgt); //할인대상 과금항목 변경            
            transferDiscExclUpdate(productDevMgt); //할인배타 변경
            transferDiscPerdUpdate(productDevMgt); //할인기간 변경
            transferAlwnceUpdate(productDevMgt); //공제정보 변경	
            
            //result = 0;
			
		}	
		
		if (productDevMgt.getDvlpTyp().equals(Consts.PROD_MGT_CODE.PROD_UPT)
				&& productDevMgt.getModTyp().equals(Consts.PROD_MGT_CODE.PROD_MOD_TYP_EXPIRE)){
			System.out.println("수정2================>");
			modTransferExpiration(productDevMgt);
			
		}
		
		deleteDvlpTableData(productDevMgt);
		
		productDevMgt.setModFl("CONF_REQ_CANCEL");
		productDevMgt.setDvlpStatus(Consts.PROD_MGT_CODE.TRANSFER);
		result = productDevMgtMapper.modProdDvlpHistConfReq(productDevMgt);
        //운영이관후 과금항목에 대한 삭제가 이루어진경우 운영의 적용종료일자일자보다 적용시작일이 더 지게 된다.
        //운영이관후 과금항목에 대한 삭제.		
		result = productDevMgtMapper.delProdrateReq(productDevMgt);		
		
		return String.valueOf(result);
	}

	@Override
	public String delProdConfDtl(List<ProductDevMgt> params,
			SessionUser sessionUser) {
		int result = -1;
		
		String usrId = sessionUser.getUserId();		
		
		for (int i=0; i<params.size(); i++) {
			
			result = productDevMgtMapper.delProdConfDtl(params.get(i));
			result = productDevMgtMapper.delProdConfHist(params.get(i));
				
		}		
		
		return String.valueOf(result);
	}

	@Override
	public int getDiscExclListCount(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getDiscExclListCount(productDevMgt);
	}
	@Override
	public int getDiscExclListCount2(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getDiscExclListCount2(productDevMgt);
	}
	@Override
	public List<ProductDevMgt> getDiscExclList(ProductDevMgt productDevMgt,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;			
		return productDevMgtMapper.getDiscExclList(productDevMgt, start, end);
	}
	@Override
	public List<ProductDevMgt> getDiscExclList2(ProductDevMgt productDevMgt,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;			
		return productDevMgtMapper.getDiscExclList2(productDevMgt, start, end);
	}
	@Override
	public List<ProductDevMgt> getAddDiscExclInit(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getAddDiscExclInit(productDevMgt);
	}

	@Override
	public String addDiscExcl(List<ProductDevMgt> params, SessionUser sessionUser) {
		int result = -1;
		
		String usrId = sessionUser.getUserId();		
		
		for (int i=0; i<params.size(); i++) {
			params.get(i).setChgrId(usrId);
			params.get(i).setRegrId(usrId);
			params.get(i).setSysdate(DateUtil.sysdate());
			params.get(i).setCurrentDay(currentDay);
			params.get(i).setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
			params.get(i).setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);	
				
			result = productDevMgtMapper.addDiscExcl(params.get(i));
				
		}		
		
		return String.valueOf(result);
	}

	@Override
	public String delDiscExclusionRelationship(ProductDevMgt productDevMgt) {
		int result = -1;
		result = productDevMgtMapper.delDiscExclusionRelationship(productDevMgt);
		
		return String.valueOf(result);
	}

	@Override
	public int getDiscSvcRateItmTypListCount(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getDiscSvcRateItmTypListCount(productDevMgt);
	}

	@Override
	public int getDiscSvcRateItmTypListCount2(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getDiscSvcRateItmTypListCount2(productDevMgt);
	}
	
	@Override
	public List<ProductDevMgt> getDiscSvcRateItmTypList(
			ProductDevMgt productDevMgt, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;			
		return productDevMgtMapper.getDiscSvcRateItmTypList(productDevMgt, start, end);
	}
	@Override
	public List<ProductDevMgt> getDiscSvcRateItmTypList2(
			ProductDevMgt productDevMgt, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;			
		return productDevMgtMapper.getDiscSvcRateItmTypList2(productDevMgt, start, end);
	}
	@Override
	public List<ProductDevMgt> getAddDiscSvcRateItmTypInit(
			ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getAddDiscSvcRateItmTypInit(productDevMgt);
	}

	@Override
	public String addDiscSvcRateItmTyp(List<ProductDevMgt> params, SessionUser sessionUser) {
		int result = -1;
		
		String usrId = sessionUser.getUserId();		
		
		for (int i=0; i<params.size(); i++) {
			params.get(i).setChgrId(usrId);
			params.get(i).setRegrId(usrId);
			params.get(i).setSysdate(DateUtil.sysdate());
			params.get(i).setCurrentDay(currentDay);
			params.get(i).setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
			params.get(i).setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);	
				
			result = productDevMgtMapper.addDiscSvcRateItmTyp(params.get(i));
				
		}		
		
		return String.valueOf(result);
	}

	@Override
	public String delDiscSvcRateItmTyp(ProductDevMgt productDevMgt) {
		int result = -1;
		result = productDevMgtMapper.delDiscSvcRateItmTyp(productDevMgt);
		
		return String.valueOf(result);
	}

	@Override
	public int getDiscPerdListCount(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getDiscPerdListCount(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getDiscPerdList(ProductDevMgt productDevMgt,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;			
		return productDevMgtMapper.getDiscPerdList(productDevMgt, start, end);
	}

	@Override
	public int getDiscPerdListCount2(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getDiscPerdListCount2(productDevMgt);
	}

	@Override
	public List<ProductDevMgt> getDiscPerdList2(ProductDevMgt productDevMgt,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;			
		return productDevMgtMapper.getDiscPerdList2(productDevMgt, start, end);
	}
	
	@Override
	public List<ProductDevMgt> getOprndFctrCd(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getOprndFctrCd(productDevMgt);
	}

	@Override
	public String addDiscPerd(ProductDevMgt productDevMgt) {
		int result = -1;
		result = productDevMgtMapper.addDiscPerd(productDevMgt);
		
		return String.valueOf(result);
	}

	@Override
	public String delDiscPerd(ProductDevMgt productDevMgt) {
		int result = -1;
		result = productDevMgtMapper.delDiscPerd(productDevMgt);
		
		return String.valueOf(result);
	}

	@Override
	public ProductDevMgt getDiscPerd(ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getDiscPerd(productDevMgt);
	}

	@Override
	public String modDiscPerd(ProductDevMgt productDevMgt) {
		int result = -1;
		result = productDevMgtMapper.modDiscPerd(productDevMgt);
		return String.valueOf(result);
	}

	@Override
	public List<ProductDevMgt> getCommonCodeComboList(
			ProductDevMgt productDevMgt) {
		return productDevMgtMapper.getCommonCodeComboList(productDevMgt);
	}

public void transferProdUpdate(ProductDevMgt productDevMgt)  throws ServiceException{
	ProductDevMgt prodOper = productDevMgtMapper.getTransProdOper(productDevMgt);
	
	System.out.println("productDevMgt.getTransferApplyDt()================>"+productDevMgt.getTransferApplyDt());
	System.out.println("prodOper.getActDt()================>"+prodOper.getActDt());
	
	if (productDevMgt.getTransferApplyDt().compareTo( prodOper.getActDt() ) < 0 ){
        System.out.println("값이 작은 경우");
		throw new ServiceException( "상품TABLE" + "%" + "PM_PD_TRANSFER_DUPERR" );
    
	}else if ( productDevMgt.getTransferApplyDt().compareTo(prodOper.getActDt()) == 0 ) {
    
    	System.out.println("값이 같은 경우");
    	productDevMgtMapper.modTransProd(productDevMgt);
    
    }else{
    	System.out.println("값이 큰 경우");
		
    	Date tmpTransferApplyDt = null;
		
		try {
			logger.debug("getActDt : {}", productDevMgt.getTransferApplyDt());
			tmpTransferApplyDt = transFormat.parse(productDevMgt.getTransferApplyDt());
		} catch (ParseException e) {
			logger.error("error", e);
		}
		
		String baseTransfreApplyDt = productDevMgt.getTransferApplyDt();
		productDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt)));
    	productDevMgtMapper.modTransProdExpirateDt(productDevMgt);
    	productDevMgt.setTransferApplyDt(baseTransfreApplyDt);
    	productDevMgtMapper.addTransferProduct(productDevMgt);
    }    		
	
}
    	
public void transferProdRelUpdate(ProductDevMgt productDevMgt)
    throws ServiceException
{
	Date tmpTransferApplyDt = null;
	try {
		logger.debug("getActDt : {}", productDevMgt.getTransferApplyDt());
		tmpTransferApplyDt = transFormat.parse(productDevMgt.getTransferApplyDt());
	} catch (ParseException e) {
		logger.error("error", e);
	}
	
	String baseTransfreApplyDt = productDevMgt.getTransferApplyDt();
	productDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt)));
	productDevMgtMapper.modTransUpdDelProdRel(productDevMgt);
	productDevMgt.setTransferApplyDt(baseTransfreApplyDt);
	
	List<ProductDevMgt> updateList = productDevMgtMapper.getTransProdRelDvlpList(productDevMgt);     	
	
    Iterator updatelt = updateList.iterator();
    while ( updatelt.hasNext() )
    {
    	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) updatelt.next();	
    	tmpProductDevMgt.setTransferApplyDt(productDevMgt.getTransferApplyDt());
    	tmpProductDevMgt.setRegrId(productDevMgt.getRegrId());
    	tmpProductDevMgt.setChgrId(productDevMgt.getChgrId());

        List<ProductDevMgt> dualList = productDevMgtMapper.getTransDualListProdRel(tmpProductDevMgt);
        if ( dualList.size() > 0 ){
            //무의미하게 삭제 후 재등록한 경우임
            //종료일만 '9999231'로 변경이 필요할까?????
        }else{
            //변경-신규 등록 시 이전에 종료된 관계의 MSTR_FL을 0으로 변경
        	productDevMgtMapper.modTransProdRelMstrFl(tmpProductDevMgt);
            //신규등록
        	productDevMgtMapper.addTransProdRel(tmpProductDevMgt);
        }
    }
}

public void transferProdSubscripUpdate(ProductDevMgt productDevMgt)
    throws ServiceException
{
	Date tmpTransferApplyDt = null;
	try {
		logger.debug("getActDt : {}", productDevMgt.getTransferApplyDt());
		tmpTransferApplyDt = transFormat.parse(productDevMgt.getTransferApplyDt());
	} catch (ParseException e) {
		logger.error("error", e);
	}
	
	String baseTransfreApplyDt = productDevMgt.getTransferApplyDt();
	productDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt)));	
    //상품가입조건 삭제 (개발에서 삭제한 데이터)
	productDevMgtMapper.modTransUpdDelProdSubscripCond(productDevMgt);
	productDevMgt.setTransferApplyDt(baseTransfreApplyDt);
    //변경(상품코드='상품코드' AND 최종일자 <> '20010101') LOOP
    List<ProductDevMgt> updateList = productDevMgtMapper.getTransProdSubscripCondList(productDevMgt);
    Iterator updateIt = updateList.iterator();
    while ( updateIt.hasNext() )
    {
    	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) updateIt.next();
    	tmpProductDevMgt.setTransferApplyDt(productDevMgt.getTransferApplyDt());
    	tmpProductDevMgt.setRegrId(productDevMgt.getRegrId());
    	tmpProductDevMgt.setChgrId(productDevMgt.getChgrId());
    	
        //신규체크(MSTR_FL = '1' AND 종료일 > '적용일')
        List<ProductDevMgt> dualList = productDevMgtMapper.getTransDualListProdSubscrip(tmpProductDevMgt);
        if ( dualList.size() > 0 )
        {	
            //변경사항 유(운영DB에 유, 불일치) 
        	tmpProductDevMgt.setTableId("TPMPD_PROD_SUBSCRIP_COND");
        	List<ProductDevMgt> operComp = productDevMgtMapper.getTransProdSubscripCondComp(tmpProductDevMgt);
        	
        	tmpProductDevMgt.setTableId("TPMPD_D_PROD_SUBSCRIP_COND");
        	List<ProductDevMgt> dvlpComp = productDevMgtMapper.getTransProdSubscripCondComp(tmpProductDevMgt);
        	
            if ( !operComp.equals( dvlpComp ) )
            {
        		if (productDevMgt.getTransferApplyDt().compareTo( tmpProductDevMgt.getActDt()) < 0 )
                {
                    throw new ServiceException( "상품TABLE" + "%" + "PM_PD_TRANSFER_DUPERR" );
                }
                else if ( productDevMgt.getTransferApplyDt().compareTo(tmpProductDevMgt.getActDt()) == 0 )
                {
                	productDevMgtMapper.modTransProdSubscripCond(tmpProductDevMgt);
                }
                else
                {
                	Date tmpTransferApplyDt1 = null;
                	try {
                		tmpTransferApplyDt1 = transFormat.parse(tmpProductDevMgt.getTransferApplyDt());
                	} catch (ParseException e) {
                		logger.error("error", e);
                	}                	
                	
                	String baseTransfreApplyDt1 = tmpProductDevMgt.getTransferApplyDt();
                	tmpProductDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt1)));	
                	productDevMgtMapper.modTransProdSubscripCondExpirateDt(tmpProductDevMgt);
                	tmpProductDevMgt.setTransferApplyDt(baseTransfreApplyDt1);
                	
                	productDevMgtMapper.addTransferProductSubscripCond(tmpProductDevMgt);
                }                     	
            	
            }
        }
        else
        {
        	productDevMgtMapper.addTransferProductSubscripCond(tmpProductDevMgt);
        }
    }
}

public void transferProdAttrUpdate(ProductDevMgt productDevMgt)
    throws ServiceException
{
	Date tmpTransferApplyDt = null;
	try {
		logger.debug("getActDt : {}", productDevMgt.getTransferApplyDt());
		tmpTransferApplyDt = transFormat.parse(productDevMgt.getTransferApplyDt());
	} catch (ParseException e) {
		logger.error("error", e);
	}
	
	String baseTransfreApplyDt = productDevMgt.getTransferApplyDt();
	productDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt)));	
	
    //상품속성 삭제 (개발에서 삭제한 데이터)
	productDevMgtMapper.modTransUpdDelProdAttr(productDevMgt);
	productDevMgt.setTransferApplyDt(baseTransfreApplyDt);
    //변경(상품코드='상품코드' AND 최종일자 <> '20010101') LOOP
    List<ProductDevMgt> updateList = productDevMgtMapper.getTransProdAttrList(productDevMgt);
    Iterator updateIt = updateList.iterator();
    while ( updateIt.hasNext() )
    {
    	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) updateIt.next();
    	tmpProductDevMgt.setSoId(productDevMgt.getSoId());
    	tmpProductDevMgt.setTransferApplyDt(productDevMgt.getTransferApplyDt());
    	tmpProductDevMgt.setRegrId(productDevMgt.getRegrId());
    	tmpProductDevMgt.setChgrId(productDevMgt.getChgrId());            	
    	
    	List<ProductDevMgt> dualList = productDevMgtMapper.getTransDualListProdAttr(tmpProductDevMgt);
    	
        if ( dualList.size() > 0 )
        {
        	tmpProductDevMgt.setTableId("TPMPD_PROD_ATTR");
        	List<ProductDevMgt> operComp = productDevMgtMapper.getTransProdAttrComp(tmpProductDevMgt);
        	
        	tmpProductDevMgt.setTableId("TPMPD_D_PROD_ATTR");
        	List<ProductDevMgt> dvlpComp = productDevMgtMapper.getTransProdAttrComp(tmpProductDevMgt);

            if ( !operComp.equals( dvlpComp ) ){
        		if (productDevMgt.getTransferApplyDt().compareTo( tmpProductDevMgt.getActDt()) < 0 )
                {
                    throw new ServiceException( "상품TABLE" + "%" + "PM_PD_TRANSFER_DUPERR" );
                }else if ( productDevMgt.getTransferApplyDt().compareTo(tmpProductDevMgt.getActDt()) == 0 ){
                	productDevMgtMapper.modTransProdAttr(tmpProductDevMgt);
                }else{
                	productDevMgtMapper.modTransProdAttrMstrFl2(tmpProductDevMgt); //이름변경
                	productDevMgtMapper.addTransferProductAttribute2(tmpProductDevMgt);
                }                     	
            	
            }
        	
        }else{
            //변경-신규 등록 시 이전에 종료된 서비스의 MSTR_FL을 0으로 변경
        	productDevMgtMapper.modTransProdAttrMstrFl(tmpProductDevMgt);
            //신규등록
        	productDevMgtMapper.addTransferProductAttribute(tmpProductDevMgt);
        }
    	
    }
}

public void transferProdSvcUpdate(ProductDevMgt productDevMgt)
    throws ServiceException
{
	Date tmpTransferApplyDt = null;
	try {
		logger.debug("getActDt : {}", productDevMgt.getTransferApplyDt());
		tmpTransferApplyDt = transFormat.parse(productDevMgt.getTransferApplyDt());
	} catch (ParseException e) {
		logger.error("error", e);
	}
	
	String baseTransfreApplyDt = productDevMgt.getTransferApplyDt();
	productDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt)));		
	
	productDevMgtMapper.modTransUpdDelProdSvc(productDevMgt);
	productDevMgt.setTransferApplyDt(baseTransfreApplyDt);
    List<ProductDevMgt> updateList = productDevMgtMapper.getTransProdSvcList(productDevMgt);
    Iterator updateIt = updateList.iterator();
    while ( updateIt.hasNext() )
    {
    	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) updateIt.next();
    	tmpProductDevMgt.setTransferApplyDt(productDevMgt.getTransferApplyDt());
    	tmpProductDevMgt.setRegrId(productDevMgt.getRegrId());
    	tmpProductDevMgt.setChgrId(productDevMgt.getChgrId());           	
    	tmpProductDevMgt.setSoId(productDevMgt.getSoId());
    	
    	List<ProductDevMgt> dualList = productDevMgtMapper.getTransDualListProdSvc(tmpProductDevMgt);
    	
        if ( dualList.size() > 0 )
        {
        	tmpProductDevMgt.setTableId("TPMPD_PROD_SVC");
        	List<ProductDevMgt> operComp = productDevMgtMapper.getTransProdSvcComp(tmpProductDevMgt);
        	
        	tmpProductDevMgt.setTableId("TPMPD_D_PROD_SVC");
        	List<ProductDevMgt> dvlpComp = productDevMgtMapper.getTransProdSvcComp(tmpProductDevMgt);
        	
            if ( !operComp.equals( dvlpComp ) )
            {
        		if (productDevMgt.getTransferApplyDt().compareTo( tmpProductDevMgt.getActDt()) < 0 )
                {
                    throw new ServiceException( "상품TABLE" + "%" + "PM_PD_TRANSFER_DUPERR" );
                }
                else if ( productDevMgt.getTransferApplyDt().compareTo(tmpProductDevMgt.getActDt()) == 0 )
                {
                	productDevMgtMapper.modTransProdSvc(tmpProductDevMgt);
                }
                else
                {
                	Date tmpTransferApplyDt1 = null;
                	try {
                		tmpTransferApplyDt1 = transFormat.parse(tmpProductDevMgt.getTransferApplyDt());
                	} catch (ParseException e) {
                		logger.error("error", e);
                	}                	
                	
                	String baseTransfreApplyDt1 = tmpProductDevMgt.getTransferApplyDt();
                	tmpProductDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt1)));
                	
                	productDevMgtMapper.modTransProdSvcExpirateDt(tmpProductDevMgt); //이름변경
                	tmpProductDevMgt.setTransferApplyDt(baseTransfreApplyDt1);
                	
                	productDevMgtMapper.addTransferProductService(tmpProductDevMgt);
                }                     	
            	
            }
        	
        }
        else
        {
        	productDevMgtMapper.modTransProdSvcMstrFl(tmpProductDevMgt);
        	productDevMgtMapper.addTransferProductService2(tmpProductDevMgt);
        }
    	
    }
}

public void transferProdSuprtGiftUpdate(ProductDevMgt productDevMgt)
    throws ServiceException
{
	Date tmpTransferApplyDt = null;
	try {
		logger.debug("getActDt : {}", productDevMgt.getTransferApplyDt());
		tmpTransferApplyDt = transFormat.parse(productDevMgt.getTransferApplyDt());
	} catch (ParseException e) {
		logger.error("error", e);
	}
	
	String baseTransfreApplyDt = productDevMgt.getTransferApplyDt();
	productDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt)));		
	
    //상품제공사은품 삭제
	productDevMgtMapper.modTransUpdDelProdSuprtGift(productDevMgt);
	productDevMgt.setTransferApplyDt(baseTransfreApplyDt);
    //변경 (상품코드 = '상품코드' AND 최종일자 <> '20010101') LOOP
    List<ProductDevMgt> updateList = productDevMgtMapper.getTransProdSuprtGiftDvlpList(productDevMgt);
    Iterator updatelt = updateList.iterator();
    while ( updatelt.hasNext() )
    {
    	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) updatelt.next();
    	tmpProductDevMgt.setTransferApplyDt(productDevMgt.getTransferApplyDt());
    	tmpProductDevMgt.setRegrId(productDevMgt.getRegrId());
    	tmpProductDevMgt.setChgrId(productDevMgt.getChgrId());           	
    	
    	List<ProductDevMgt> dualList = productDevMgtMapper.getTransDualListProdSuprtGift(tmpProductDevMgt);
        if ( dualList.size() > 0 )
        {
            //무의미하게 삭제 후 재등록한 경우임
            //종료일만 '9999231'로 변경이 필요할까?????
        }
        else
        {
            //변경-신규 등록 시 이전에 종료된 관계의 MSTR_FL을 0으로 변경
        	productDevMgtMapper.modTransProdSuprtGiftMstrFl(tmpProductDevMgt);
            //신규등록
        	productDevMgtMapper.addTransferProdSuprtGift(tmpProductDevMgt);
        }
    }
}

public void transferSuprtEquipUpdate(ProductDevMgt productDevMgt)
    throws ServiceException
{
	Date tmpTransferApplyDt = null;
	try {
		logger.debug("getActDt : {}", productDevMgt.getTransferApplyDt());
		tmpTransferApplyDt = transFormat.parse(productDevMgt.getTransferApplyDt());
	} catch (ParseException e) {
		logger.error("error", e);
	}
	
	String baseTransfreApplyDt = productDevMgt.getTransferApplyDt();
	productDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt)));			
	
	productDevMgtMapper.modTransUpdDelSuprtEquip(productDevMgt);
	productDevMgt.setTransferApplyDt(baseTransfreApplyDt);
    List<ProductDevMgt> updateList = productDevMgtMapper.getTransSuprtEquipList(productDevMgt);
    Iterator updateIt = updateList.iterator();
    while ( updateIt.hasNext() )
    {
    	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) updateIt.next();
    	tmpProductDevMgt.setTransferApplyDt(productDevMgt.getTransferApplyDt());
    	tmpProductDevMgt.setRegrId(productDevMgt.getRegrId());
    	tmpProductDevMgt.setChgrId(productDevMgt.getChgrId());           	
    	
    	List<ProductDevMgt> dualList = productDevMgtMapper.getTransDualListSuprtEquip(tmpProductDevMgt);
    	
        if ( dualList.size() > 0 )
        {
        	tmpProductDevMgt.setTableId("TPMPD_SUPRT_EQUIP");
        	List<ProductDevMgt> operComp = productDevMgtMapper.getTransSuprtEquipComp(tmpProductDevMgt);
        	
        	tmpProductDevMgt.setTableId("TPMPD_D_SUPRT_EQUIP");
        	List<ProductDevMgt> dvlpComp = productDevMgtMapper.getTransSuprtEquipComp(tmpProductDevMgt);
        	
            if ( !operComp.equals( dvlpComp ) )
            {
        		if (productDevMgt.getTransferApplyDt().substring( 0, 5 ).compareTo( tmpProductDevMgt.getActDt().substring( 0,
                        5 ) ) < 0 )
                {
                    throw new ServiceException( "상품TABLE" + "%" + "PM_PD_TRANSFER_DUPERR" );
                }
                else if ( productDevMgt.getTransferApplyDt().compareTo(tmpProductDevMgt.getActDt()) == 0 )
                {
                	productDevMgtMapper.modTransSuprtEquip(tmpProductDevMgt);
                }
                else
                {
                	productDevMgtMapper.modTransSuprtEquip(tmpProductDevMgt); //이름변경
                	productDevMgtMapper.addTransferSuprtEquip(tmpProductDevMgt);
                }                     	
            	
            }
        	
        }
        else
        {
        	productDevMgtMapper.modTransSuprtEquipMstrFl(tmpProductDevMgt);
        	productDevMgtMapper.addTransferSuprtEquip(tmpProductDevMgt);
        }
    	
    }
}
	//여기부터
public void transferRateItmUpdate(ProductDevMgt productDevMgt)
    throws ServiceException
{
	Date tmpTransferApplyDt = null;
	try {
		logger.debug("getActDt : {}", productDevMgt.getTransferApplyDt());
		tmpTransferApplyDt = transFormat.parse(productDevMgt.getTransferApplyDt());
	} catch (ParseException e) {
		logger.error("error", e);
	}
	
	String baseTransfreApplyDt = productDevMgt.getTransferApplyDt();
	productDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt)));			
	
	productDevMgtMapper.modTransUpdDelRateItm(productDevMgt);
	productDevMgt.setTransferApplyDt(baseTransfreApplyDt);
	
    List updateList = productDevMgtMapper.getTransRateItmList(productDevMgt);
    Iterator updateIt = updateList.iterator();
    while ( updateIt.hasNext() )
    {
    	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) updateIt.next();
    	tmpProductDevMgt.setTransferApplyDt(productDevMgt.getTransferApplyDt());
    	tmpProductDevMgt.setRegrId(productDevMgt.getRegrId());
    	tmpProductDevMgt.setChgrId(productDevMgt.getChgrId()); 
    	tmpProductDevMgt.setSoId(productDevMgt.getSoId());
        //신규체크
        List dualList = productDevMgtMapper.getTransDualListRateItm(tmpProductDevMgt);
        if ( dualList.size() > 0 )
        {
        	
        	tmpProductDevMgt.setTableId("TPMPD_RATE_ITM");
        	List<ProductDevMgt> operComp = productDevMgtMapper.getTransRateItmComp(tmpProductDevMgt);
        	
        	tmpProductDevMgt.setTableId("TPMPD_D_RATE_ITM");
        	List<ProductDevMgt> dvlpComp = productDevMgtMapper.getTransRateItmComp(tmpProductDevMgt);				

            if ( !operComp.equals( dvlpComp ) )
            {
        		if (productDevMgt.getTransferApplyDt().compareTo( tmpProductDevMgt.getActDt() ) < 0 )
                {
                    throw new ServiceException( "과금항목" + "%" + "PM_PD_TRANSFER_DUPERR" );
                }
                else if ( productDevMgt.getTransferApplyDt().compareTo(tmpProductDevMgt.getActDt()) == 0 )
                {
                	productDevMgtMapper.modTransRateItm(tmpProductDevMgt);
                }
                else
                {
                	Date tmpTransferApplyDt1 = null;
                	try {
                		tmpTransferApplyDt1 = transFormat.parse(tmpProductDevMgt.getTransferApplyDt());
                	} catch (ParseException e) {
                		logger.error("error", e);
                	}                	
                	
                	String baseTransfreApplyDt1 = tmpProductDevMgt.getTransferApplyDt();
                	tmpProductDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt1)));
                	
                	productDevMgtMapper.modTransRateItmExpirateDt(tmpProductDevMgt);
                	tmpProductDevMgt.setTransferApplyDt(baseTransfreApplyDt1);
                	
                	productDevMgtMapper.addTransferRateItem(tmpProductDevMgt);
                }                     	
            	
            }        	
        }
        else
        {
        	productDevMgtMapper.modTransRateItmMstrFl(tmpProductDevMgt);
        	productDevMgtMapper.addTransferRateItem2(tmpProductDevMgt);
        }
    }
}

public void transferRateItmAttrUpdate(ProductDevMgt productDevMgt)
    throws ServiceException
{
	Date tmpTransferApplyDt = null;
	try {
		logger.debug("getActDt : {}", productDevMgt.getTransferApplyDt());
		tmpTransferApplyDt = transFormat.parse(productDevMgt.getTransferApplyDt());
	} catch (ParseException e) {
		logger.error("error", e);
	}
	
	String baseTransfreApplyDt = productDevMgt.getTransferApplyDt();
	productDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt)));		
	
	productDevMgtMapper.modTransUpdDelRateItmAttr(productDevMgt);
	productDevMgt.setTransferApplyDt(baseTransfreApplyDt);
	
	
    List updateList = productDevMgtMapper.getTransRateItmAttrList(productDevMgt);
    Iterator updateIt = updateList.iterator();
    while ( updateIt.hasNext() )
    {
    	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) updateIt.next();
    	tmpProductDevMgt.setTransferApplyDt(productDevMgt.getTransferApplyDt());
    	tmpProductDevMgt.setRegrId(productDevMgt.getRegrId());
    	tmpProductDevMgt.setChgrId(productDevMgt.getChgrId()); 
    	tmpProductDevMgt.setSoId(productDevMgt.getSoId());
    	tmpProductDevMgt.setProdCd(productDevMgt.getProdCd());
        //신규체크
        List dualList = productDevMgtMapper.getTransDualListRateItmAttr(tmpProductDevMgt);
        if ( dualList.size() > 0 )
        {
        	
        	tmpProductDevMgt.setTableId("TPMPD_RATE_ITM_ATTR");
        	List<ProductDevMgt> operComp = productDevMgtMapper.getTransRateItmAttrComp(tmpProductDevMgt);
        	
        	tmpProductDevMgt.setTableId("TPMPD_D_RATE_ITM_ATTR");
        	List<ProductDevMgt> dvlpComp = productDevMgtMapper.getTransRateItmAttrComp(tmpProductDevMgt);				

            if ( !operComp.equals( dvlpComp ) )
            {
        		if (productDevMgt.getTransferApplyDt().compareTo( tmpProductDevMgt.getActDt()) < 0 )
                {
                    throw new ServiceException( "과금항목속성" + "%" + "PM_PD_TRANSFER_DUPERR" );
                }
                else if ( productDevMgt.getTransferApplyDt().compareTo(tmpProductDevMgt.getActDt()) == 0 )
                {
                	productDevMgtMapper.modTransRateItmAttr(tmpProductDevMgt);
                }
                else
                {
                	Date tmpTransferApplyDt1 = null;
                	try {
                		tmpTransferApplyDt1 = transFormat.parse(tmpProductDevMgt.getTransferApplyDt());
                	} catch (ParseException e) {
                		logger.error("error", e);
                	}                	
                	
                	String baseTransfreApplyDt1 = tmpProductDevMgt.getTransferApplyDt();
                	tmpProductDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt1)));
                	
                	productDevMgtMapper.modTransRateItmAttrExpirateDt(tmpProductDevMgt);
                	tmpProductDevMgt.setTransferApplyDt(baseTransfreApplyDt1);
                	
                	productDevMgtMapper.addTransferRateItemAttribute(tmpProductDevMgt);
                }  
            }        	
        }else{
        	System.out.println("getRateItmCd()====>"+tmpProductDevMgt.getRateItmCd());
        	System.out.println("getAttrCd()====>"+tmpProductDevMgt.getAttrCd());
        	productDevMgtMapper.modTransRateItmAttrMstrFl(tmpProductDevMgt);
        	productDevMgtMapper.addTransferRateItemAttribute2(tmpProductDevMgt);
        }
    }
}

public void transferRateItmFctrUpdate(ProductDevMgt productDevMgt)
    throws ServiceException
{
	Date tmpTransferApplyDt = null;
	try {
		logger.debug("getActDt : {}", productDevMgt.getTransferApplyDt());
		tmpTransferApplyDt = transFormat.parse(productDevMgt.getTransferApplyDt());
	} catch (ParseException e) {
		logger.error("error", e);
	}
	
	String baseTransfreApplyDt = productDevMgt.getTransferApplyDt();
	productDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt)));	
	
	productDevMgtMapper.modTransUpdDelRateItmFctr(productDevMgt);
	productDevMgt.setTransferApplyDt(baseTransfreApplyDt);
	
    List updateList = productDevMgtMapper.getTransRateItmFctrList(productDevMgt);
    Iterator updateIt = updateList.iterator();
    while ( updateIt.hasNext() )  {
    	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) updateIt.next();
    	tmpProductDevMgt.setTransferApplyDt(productDevMgt.getTransferApplyDt());
    	tmpProductDevMgt.setRegrId(productDevMgt.getRegrId());
    	tmpProductDevMgt.setChgrId(productDevMgt.getChgrId()); 
    	tmpProductDevMgt.setSoId(productDevMgt.getSoId());
    	tmpProductDevMgt.setProdCd(productDevMgt.getProdCd());
        //신규체크
        List dualList = productDevMgtMapper.getTransDualListRateItmFctr(tmpProductDevMgt);
        if ( dualList.size() > 0 )
        {
        	
        	tmpProductDevMgt.setTableId("TPMPD_RATE_ITM_FCTR");
        	List<ProductDevMgt> operComp = productDevMgtMapper.getTransRateItmFctrComp(tmpProductDevMgt);
        	
        	tmpProductDevMgt.setTableId("TPMPD_D_RATE_ITM_FCTR");
        	List<ProductDevMgt> dvlpComp = productDevMgtMapper.getTransRateItmFctrComp(tmpProductDevMgt);				

            if ( !operComp.equals( dvlpComp ) )
            {
        		if (productDevMgt.getTransferApplyDt().compareTo( tmpProductDevMgt.getActDt() ) < 0 )
                {
                    throw new ServiceException( "과금항목요소" + "%" + "PM_PD_TRANSFER_DUPERR" );
                }
                else if ( productDevMgt.getTransferApplyDt().compareTo(tmpProductDevMgt.getActDt()) == 0 )
                {
                	productDevMgtMapper.modTransRateItmFctr(tmpProductDevMgt);
                }
                else
                {
                	Date tmpTransferApplyDt1 = null;
                	try {
                		tmpTransferApplyDt1 = transFormat.parse(tmpProductDevMgt.getTransferApplyDt());
                	} catch (ParseException e) {
                		logger.error("error", e);
                	}                	
                	
                	String baseTransfreApplyDt1 = tmpProductDevMgt.getTransferApplyDt();
                	tmpProductDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt1)));
                	
                	productDevMgtMapper.modTransRateItmFctrExpirateDt(tmpProductDevMgt);
                	tmpProductDevMgt.setTransferApplyDt(baseTransfreApplyDt1);
                	
                	productDevMgtMapper.addTransferRateItemFactor(tmpProductDevMgt);
                }                     	
            	
            }        	
        }
        else
        {
        	productDevMgtMapper.modTransRateItmFctrMstrFl(tmpProductDevMgt);
        	productDevMgtMapper.addTransferRateItemFactor2(tmpProductDevMgt);
        }
    }
}

public void transferDiscSvcRateItmTypUpdate(ProductDevMgt productDevMgt)
    throws ServiceException
{
	Date tmpTransferApplyDt = null;
	try {
		logger.debug("getActDt : {}", productDevMgt.getTransferApplyDt());
		tmpTransferApplyDt = transFormat.parse(productDevMgt.getTransferApplyDt());
	} catch (ParseException e) {
		logger.error("error", e);
	}
	
	String baseTransfreApplyDt = productDevMgt.getTransferApplyDt();
	productDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt)));		
	
	productDevMgtMapper.modTransUpdDelDiscSvcRateItmTyp(productDevMgt);
	productDevMgt.setTransferApplyDt(baseTransfreApplyDt);
	
	List<ProductDevMgt> updateList = productDevMgtMapper.getTransDiscSvcRateItmTypDvlpList(productDevMgt);     	
	
    Iterator updatelt = updateList.iterator();
    while ( updatelt.hasNext() )
    {
    	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) updatelt.next();	
    	tmpProductDevMgt.setTransferApplyDt(productDevMgt.getTransferApplyDt());
    	tmpProductDevMgt.setRegrId(productDevMgt.getRegrId());
    	tmpProductDevMgt.setChgrId(productDevMgt.getChgrId());
    	tmpProductDevMgt.setProdCd(productDevMgt.getProdCd());
    	tmpProductDevMgt.setSoId(productDevMgt.getSoId());
        List<ProductDevMgt> dualList = productDevMgtMapper.getTransDualListDiscSvcRateItmTyp2(tmpProductDevMgt);
        if ( dualList.size() > 0 ){

        	tmpProductDevMgt.setTableId("TPMPD_DISC_SVC_RATE_ITM_TYP");
        	List<ProductDevMgt> operComp = productDevMgtMapper.getTransDiscSvcRateItmComp2(tmpProductDevMgt);
        	
        	tmpProductDevMgt.setTableId("TPMPD_D_DISC_SVC_RT_ITM_TYP");
        	List<ProductDevMgt> dvlpComp = productDevMgtMapper.getTransDiscSvcRateItmComp2(tmpProductDevMgt);				

            if ( !operComp.equals( dvlpComp ) ){
        		if (productDevMgt.getTransferApplyDt().compareTo( tmpProductDevMgt.getActDt()) < 0 )
                {
                    throw new ServiceException( "할인대상항목" + "%" + "PM_PD_TRANSFER_DUPERR" );
                }else if ( productDevMgt.getTransferApplyDt().compareTo(tmpProductDevMgt.getActDt()) == 0 ){
                	productDevMgtMapper.modTransDiscSvcRateItmComp(tmpProductDevMgt);
                }else{
                	Date tmpTransferApplyDt1 = null;
                	try {
                		tmpTransferApplyDt1 = transFormat.parse(tmpProductDevMgt.getTransferApplyDt());
                	} catch (ParseException e) {
                		logger.error("error", e);
                	}                	
                	System.out.println("11111111111111111111");
                	String baseTransfreApplyDt1 = tmpProductDevMgt.getTransferApplyDt();
                	tmpProductDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt1)));
                	
                	productDevMgtMapper.modTransDiscSvcRateItmTypExpirateDt(tmpProductDevMgt);
                	tmpProductDevMgt.setTransferApplyDt(baseTransfreApplyDt1);
                	
                	productDevMgtMapper.addTransferDiscSvcRateItmTyp2(tmpProductDevMgt);
                }                     	
            	
            } 
        }else{
        	System.out.println("2222222222222222222");
        	productDevMgtMapper.modTransDiscSvcRateItmTypMstrFl2(tmpProductDevMgt);
        	productDevMgtMapper.addTransferDiscSvcRateItmTyp2(tmpProductDevMgt);
        }
    }
}

public void transferRateItmCondUpdate(ProductDevMgt productDevMgt)
    throws ServiceException
{
	Date tmpTransferApplyDt = null;
	try {
		logger.debug("getActDt : {}", productDevMgt.getTransferApplyDt());
		tmpTransferApplyDt = transFormat.parse(productDevMgt.getTransferApplyDt());
	} catch (ParseException e) {
		logger.error("error", e);
	}
	
	String baseTransfreApplyDt = productDevMgt.getTransferApplyDt();
	productDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt)));		
	
	productDevMgtMapper.modTransUpdDelRateItmCond(productDevMgt);
	productDevMgt.setTransferApplyDt(baseTransfreApplyDt);
	
    List updateList = productDevMgtMapper.getTransRateItmCondList(productDevMgt);
    Iterator updateIt = updateList.iterator();
    while ( updateIt.hasNext() )
    {
    	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) updateIt.next();
    	tmpProductDevMgt.setTransferApplyDt(productDevMgt.getTransferApplyDt());
    	tmpProductDevMgt.setRegrId(productDevMgt.getRegrId());
    	tmpProductDevMgt.setChgrId(productDevMgt.getChgrId());
    	tmpProductDevMgt.setProdCd(productDevMgt.getProdCd());
    	tmpProductDevMgt.setSoId(productDevMgt.getSoId());
        //신규체크
        List dualList = productDevMgtMapper.getTransDualListRateItmCond(tmpProductDevMgt);
        if ( dualList.size() > 0 )
        {
        	
        	tmpProductDevMgt.setTableId("TPMPD_RATE_ITM_COND");
        	List<ProductDevMgt> operComp = productDevMgtMapper.getTransRateItmCondComp(tmpProductDevMgt);
        	
        	tmpProductDevMgt.setTableId("TPMPD_D_RATE_ITM_COND");
        	List<ProductDevMgt> dvlpComp = productDevMgtMapper.getTransRateItmCondComp(tmpProductDevMgt);				

            if ( !operComp.equals( dvlpComp ) )
            {
        		if (productDevMgt.getTransferApplyDt().compareTo( tmpProductDevMgt.getActDt()) < 0 )
                {
                    throw new ServiceException( "과금항목조건" + "%" + "PM_PD_TRANSFER_DUPERR" );
                }
                else if ( productDevMgt.getTransferApplyDt().compareTo(tmpProductDevMgt.getActDt()) == 0 )
                {
                	productDevMgtMapper.modTransRateItmCond(tmpProductDevMgt);
                }
                else
                {
                	Date tmpTransferApplyDt1 = null;
                	try {
                		tmpTransferApplyDt1 = transFormat.parse(tmpProductDevMgt.getTransferApplyDt());
                	} catch (ParseException e) {
                		logger.error("error", e);
                	}                	
                	
                	String baseTransfreApplyDt1 = tmpProductDevMgt.getTransferApplyDt();
                	tmpProductDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt1)));
                	
                	productDevMgtMapper.modTransRateItmCondExpirateDt(tmpProductDevMgt);
                	tmpProductDevMgt.setTransferApplyDt(baseTransfreApplyDt1);
                	
                	productDevMgtMapper.addTransferRateItemCondition(tmpProductDevMgt);
                }                     	
            	
            }        	
        }
        else
        {
        	productDevMgtMapper.modTransRateItmCondMstrFl(tmpProductDevMgt);
        	productDevMgtMapper.addTransferRateItemCondition2(tmpProductDevMgt);
        }
    }
}

public void transferRateUpdate(ProductDevMgt productDevMgt)
    throws ServiceException
{
	Date tmpTransferApplyDt = null;
	try {
		logger.debug("getActDt : {}", productDevMgt.getTransferApplyDt());
		tmpTransferApplyDt = transFormat.parse(productDevMgt.getTransferApplyDt());
	} catch (ParseException e) {
		logger.error("error", e);
	}
	
	String baseTransfreApplyDt = productDevMgt.getTransferApplyDt();
	productDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt)));			
	
	productDevMgtMapper.modTransUpdDelRate(productDevMgt);
	productDevMgt.setTransferApplyDt(baseTransfreApplyDt);

    List updateList = productDevMgtMapper.getTransRateList(productDevMgt);
    Iterator updateIt = updateList.iterator();
    while ( updateIt.hasNext() )
    {
    	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) updateIt.next();
    	tmpProductDevMgt.setTransferApplyDt(productDevMgt.getTransferApplyDt());
    	tmpProductDevMgt.setRegrId(productDevMgt.getRegrId());
    	tmpProductDevMgt.setChgrId(productDevMgt.getChgrId());
    	tmpProductDevMgt.setProdCd(productDevMgt.getProdCd());
    	tmpProductDevMgt.setSoId(productDevMgt.getSoId());
        //신규체크
        List dualList = productDevMgtMapper.getTransDualListRate(tmpProductDevMgt);
        if ( dualList.size() > 0 )
        {
        	
        	tmpProductDevMgt.setTableId("TPMPD_RATE");
        	List<ProductDevMgt> operComp = productDevMgtMapper.getTransRateComp(tmpProductDevMgt);
        	
        	tmpProductDevMgt.setTableId("TPMPD_D_RATE");
        	List<ProductDevMgt> dvlpComp = productDevMgtMapper.getTransRateComp(tmpProductDevMgt);				

            if ( !operComp.equals( dvlpComp ) )
            {
        		if (productDevMgt.getTransferApplyDt().compareTo( tmpProductDevMgt.getActDt()) < 0 )
                {
                    throw new ServiceException( "과금요율" + "%" + "PM_PD_TRANSFER_DUPERR" );
                }
                else if ( productDevMgt.getTransferApplyDt().compareTo(tmpProductDevMgt.getActDt()) == 0 )
                {
                	productDevMgtMapper.modTransRate(tmpProductDevMgt);
                }
                else
                {
                	Date tmpTransferApplyDt1 = null;
                	try {
                		tmpTransferApplyDt1 = transFormat.parse(tmpProductDevMgt.getTransferApplyDt());
                	} catch (ParseException e) {
                		logger.error("error", e);
                	}                	
                	
                	String baseTransfreApplyDt1 = tmpProductDevMgt.getTransferApplyDt();
                	tmpProductDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt1)));
                	
                	productDevMgtMapper.modTransRateExpirateDt(tmpProductDevMgt);
                	tmpProductDevMgt.setTransferApplyDt(baseTransfreApplyDt1);
                	
                	productDevMgtMapper.addTransferRate(tmpProductDevMgt);
                }                     	
            	
            }        	
        }
        else
        {
        	productDevMgtMapper.modTransRateMstrFl(tmpProductDevMgt);
        	productDevMgtMapper.addTransferRate2(tmpProductDevMgt);
        }
    }
}

public void transferDiscExclUpdate(ProductDevMgt productDevMgt)
    throws ServiceException
{
	Date tmpTransferApplyDt = null;
	try {
		logger.debug("getActDt : {}", productDevMgt.getTransferApplyDt());
		tmpTransferApplyDt = transFormat.parse(productDevMgt.getTransferApplyDt());
	} catch (ParseException e) {
		logger.error("error", e);
	}
	
	String baseTransfreApplyDt = productDevMgt.getTransferApplyDt();
	productDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt)));	
	
	productDevMgtMapper.modTransUpdDelDiscExcl(productDevMgt);
	productDevMgt.setTransferApplyDt(baseTransfreApplyDt);
	
	List<ProductDevMgt> updateList = productDevMgtMapper.getTransDiscExclDvlpList(productDevMgt);     	
	
    Iterator updatelt = updateList.iterator();
    while ( updatelt.hasNext() )
    {
    	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) updatelt.next();	
    	tmpProductDevMgt.setTransferApplyDt(productDevMgt.getTransferApplyDt());
    	tmpProductDevMgt.setRegrId(productDevMgt.getRegrId());
    	tmpProductDevMgt.setChgrId(productDevMgt.getChgrId());
    	tmpProductDevMgt.setProdCd(productDevMgt.getProdCd());
    	tmpProductDevMgt.setSoId(productDevMgt.getSoId());
    	
        List<ProductDevMgt> dualList = productDevMgtMapper.getTransDualListDiscExcl(tmpProductDevMgt);
        if ( dualList.size() > 0 )
        {

        }
        else
        {
        	productDevMgtMapper.modTransDiscExclMstrFl(tmpProductDevMgt);
        	productDevMgtMapper.addTransferDiscExcl(tmpProductDevMgt);
        }
    }
}

public void transferDiscPerdUpdate(ProductDevMgt productDevMgt)
    throws ServiceException
{
	Date tmpTransferApplyDt = null;
	try {
		logger.debug("getActDt : {}", productDevMgt.getTransferApplyDt());
		tmpTransferApplyDt = transFormat.parse(productDevMgt.getTransferApplyDt());
	} catch (ParseException e) {
		logger.error("error", e);
	}
	
	String baseTransfreApplyDt = productDevMgt.getTransferApplyDt();
	productDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt)));		
	
	productDevMgtMapper.modTransUpdDelDiscPerd(productDevMgt);
	productDevMgt.setTransferApplyDt(baseTransfreApplyDt);

    List updateList = productDevMgtMapper.getTransDiscPerdList(productDevMgt);
    Iterator updateIt = updateList.iterator();
    while ( updateIt.hasNext() )
    {
    	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) updateIt.next();
    	tmpProductDevMgt.setTransferApplyDt(productDevMgt.getTransferApplyDt());
    	tmpProductDevMgt.setRegrId(productDevMgt.getRegrId());
    	tmpProductDevMgt.setChgrId(productDevMgt.getChgrId()); 
    	tmpProductDevMgt.setProdCd(productDevMgt.getProdCd());
    	tmpProductDevMgt.setSoId(productDevMgt.getSoId());
    	
        //신규체크
        List dualList = productDevMgtMapper.getTransDualListDiscPerd(tmpProductDevMgt);
        if ( dualList.size() > 0 )
        {
        	
        	tmpProductDevMgt.setTableId("TPMPD_DISC_PERD");
        	List<ProductDevMgt> operComp = productDevMgtMapper.getTransDiscPerdComp(tmpProductDevMgt);
        	
        	tmpProductDevMgt.setTableId("TPMPD_D_DISC_PERD");
        	List<ProductDevMgt> dvlpComp = productDevMgtMapper.getTransDiscPerdComp(tmpProductDevMgt);				

            if ( !operComp.equals( dvlpComp ) )
            {
        		if (productDevMgt.getTransferApplyDt().compareTo( tmpProductDevMgt.getActDt() ) < 0 )
                {
                    throw new ServiceException( "할인기간" + "%" + "PM_PD_TRANSFER_DUPERR" );
                }
                else if ( productDevMgt.getTransferApplyDt().compareTo(tmpProductDevMgt.getActDt()) == 0 )
                {
                	productDevMgtMapper.modTransDiscPerd(tmpProductDevMgt);
                }
                else
                {
                	Date tmpTransferApplyDt1 = null;
                	try {
                		tmpTransferApplyDt1 = transFormat.parse(tmpProductDevMgt.getTransferApplyDt());
                	} catch (ParseException e) {
                		logger.error("error", e);
                	}                	
                	
                	String baseTransfreApplyDt1 = tmpProductDevMgt.getTransferApplyDt();
                	tmpProductDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt1)));
                	
                	productDevMgtMapper.modTransDiscPerdExpirateDt(tmpProductDevMgt);
                	tmpProductDevMgt.setTransferApplyDt(baseTransfreApplyDt1);
                	
                	productDevMgtMapper.addTransferDiscPeriod(tmpProductDevMgt);
                }                     	
            	
            }        	
        }
        else
        {
        	productDevMgtMapper.modTransDiscPerdMstrFl(tmpProductDevMgt);
        	productDevMgtMapper.addTransferDiscPeriod(tmpProductDevMgt);
        }
    }
}

public void transferAlwnceUpdate(ProductDevMgt productDevMgt)
    throws ServiceException
{
	Date tmpTransferApplyDt = null;
	try {
		logger.debug("getActDt : {}", productDevMgt.getTransferApplyDt());
		tmpTransferApplyDt = transFormat.parse(productDevMgt.getTransferApplyDt());
	} catch (ParseException e) {
		logger.error("error", e);
	}
	
	String baseTransfreApplyDt = productDevMgt.getTransferApplyDt();
	productDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt)));	
	
	productDevMgtMapper.modTransUpdDelAlwnce(productDevMgt);
	productDevMgt.setTransferApplyDt(baseTransfreApplyDt);
	
    List<ProductDevMgt> updateList = productDevMgtMapper.getTransAlwnceList(productDevMgt);
    Iterator updateIt = updateList.iterator();
    while ( updateIt.hasNext() )
    {
    	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) updateIt.next();
    	tmpProductDevMgt.setTransferApplyDt(productDevMgt.getTransferApplyDt());
    	tmpProductDevMgt.setRegrId(productDevMgt.getRegrId());
    	tmpProductDevMgt.setChgrId(productDevMgt.getChgrId()); 
    	tmpProductDevMgt.setSoId(productDevMgt.getSoId());
    	
        //신규체크
        List dualList = productDevMgtMapper.getTransDualListAlwnce(tmpProductDevMgt);
        if ( dualList.size() > 0 )
        {
        	
        	tmpProductDevMgt.setTableId("TPMPD_ALWNCE");
        	List<ProductDevMgt> operComp = productDevMgtMapper.getTransAlwnceComp(tmpProductDevMgt);
        	
        	tmpProductDevMgt.setTableId("TPMPD_D_ALWNCE");
        	List<ProductDevMgt> dvlpComp = productDevMgtMapper.getTransAlwnceComp(tmpProductDevMgt);				

            if ( !operComp.equals( dvlpComp ) )
            {
        		if (productDevMgt.getTransferApplyDt().substring( 0, 5 ).compareTo( tmpProductDevMgt.getActDt().substring( 0,
                        5 ) ) < 0 )
                {
                    throw new ServiceException( "공제" + "%" + "PM_PD_TRANSFER_DUPERR" );
                }
                else if ( productDevMgt.getTransferApplyDt().compareTo(tmpProductDevMgt.getActDt()) == 0 )
                {
                	productDevMgtMapper.modTransAlwnce(tmpProductDevMgt);
                }
                else
                {
                	Date tmpTransferApplyDt1 = null;
                	try {
                		tmpTransferApplyDt1 = transFormat.parse(tmpProductDevMgt.getTransferApplyDt());
                	} catch (ParseException e) {
                		logger.error("error", e);
                	}                	
                	
                	String baseTransfreApplyDt1 = tmpProductDevMgt.getTransferApplyDt();
                	tmpProductDevMgt.setTransferApplyDt(transFormat.format(getPreviousDate(tmpTransferApplyDt1)));
                	
                	productDevMgtMapper.modTransAlwnceExpirateDt(tmpProductDevMgt);
                	tmpProductDevMgt.setTransferApplyDt(baseTransfreApplyDt1);
                	
                	productDevMgtMapper.addTransferAllowance(tmpProductDevMgt);
                }                     	
            	
            }        	
        }
        else
        {
        	productDevMgtMapper.modTransAlwnceMstrFl(tmpProductDevMgt);
        	productDevMgtMapper.addTransferAllowance(tmpProductDevMgt);
        }
    }
}
   	                                      	
public void modTransferExpiration(ProductDevMgt productDevMgt){
	productDevMgt.setTableId("TPMPD_PROD");
	productDevMgt.setUpdCond("PROD_CD = '" + productDevMgt.getProdCd() + "'");
	
	productDevMgtMapper.modTransferExpiration(productDevMgt);
}

public void deleteDvlpTableData(ProductDevMgt productDevMgt){
    //(개발)상품등록 삭제
	productDevMgt.setTableId("TPMPD_D_PROD");
	productDevMgt.setDeleteCond("PROD_CD = '" + productDevMgt.getProdCd() + "'");
	productDevMgtMapper.delTransDvlpTable(productDevMgt);
    //(개발)상품관계 삭제
	productDevMgt.setTableId("TPMPD_D_PROD_REL");
	productDevMgt.setDeleteCond("PROD_CD = '" + productDevMgt.getProdCd() + "'");
    productDevMgtMapper.delTransDvlpTable(productDevMgt);
    //(개발)상품가입조건 삭제
	productDevMgt.setTableId("TPMPD_D_PROD_SUBSCRIP_COND");
	productDevMgt.setDeleteCond("PROD_CD = '" + productDevMgt.getProdCd() + "'");
    productDevMgtMapper.delTransDvlpTable(productDevMgt);
    //(개발)상품속성 삭제
	productDevMgt.setTableId("TPMPD_D_PROD_ATTR");
	productDevMgt.setDeleteCond("PROD_CD = '" + productDevMgt.getProdCd() + "'");
    productDevMgtMapper.delTransDvlpTable(productDevMgt);
    //(개발)상품별서비스의 삭제
	productDevMgt.setTableId("TPMPD_D_PROD_SVC");
	productDevMgt.setDeleteCond("PROD_CD = '" + productDevMgt.getProdCd() + "'");
    productDevMgtMapper.delTransDvlpTable(productDevMgt);
    //(개발)상품제공사은품 삭제
	productDevMgt.setTableId("TPMPD_D_PROD_SUPRT_GIFT");
	productDevMgt.setDeleteCond("PROD_CD = '" + productDevMgt.getProdCd() + "'");
    productDevMgtMapper.delTransDvlpTable(productDevMgt);
    //(개발)상품제공장비 삭제
	productDevMgt.setTableId("TPMPD_D_SUPRT_EQUIP");
	productDevMgt.setDeleteCond("PROD_CD = '" + productDevMgt.getProdCd() + "'");
    productDevMgtMapper.delTransDvlpTable(productDevMgt);
    //(개발)과금항목속성 삭제
	productDevMgt.setTableId("TPMPD_D_RATE_ITM_ATTR");
	productDevMgt.setDeleteCond("RATE_ITM_CD IN (SELECT RATE_ITM_CD FROM TPMPD_D_RATE_ITM WHERE PROD_CD = '" + productDevMgt.getProdCd() + "')");
    productDevMgtMapper.delTransDvlpTable(productDevMgt);
    //(개발)과금항목요소 삭제
	productDevMgt.setTableId("TPMPD_D_RATE_ITM_FCTR");
	productDevMgt.setDeleteCond("RATE_ITM_CD IN (SELECT RATE_ITM_CD FROM TPMPD_D_RATE_ITM WHERE PROD_CD = '" + productDevMgt.getProdCd() + "')");
    productDevMgtMapper.delTransDvlpTable(productDevMgt);
    //(개발)할인대상 과금항목 삭제
	productDevMgt.setTableId("TPMPD_D_DISC_SVC_RT_ITM_TYP");
	productDevMgt.setDeleteCond("RATE_ITM_CD IN (SELECT RATE_ITM_CD FROM TPMPD_D_RATE_ITM WHERE PROD_CD = '" + productDevMgt.getProdCd() + "')");
    productDevMgtMapper.delTransDvlpTable(productDevMgt);
    //(개발)누적대상 과금항목 삭제 -- 테이블 존재하지 않음
	//productDevMgt.setTableId("TPMPD_D_ACCUM_SVC_RATE_ITM_TYP");
	//productDevMgt.setDeleteCond("RATE_ITM_CD IN (SELECT RATE_ITM_CD FROM TPMPD_D_RATE_ITM WHERE PROD_CD = '" + productDevMgt.getProdCd() + "')");
    //productDevMgtMapper.delTransDvlpTable(productDevMgt);
    //(개발)과금조건 삭제
	productDevMgt.setTableId("TPMPD_D_RATE_ITM_COND");
	productDevMgt.setDeleteCond("RATE_ITM_CD IN (SELECT RATE_ITM_CD FROM TPMPD_D_RATE_ITM WHERE PROD_CD = '" + productDevMgt.getProdCd() + "')");
    productDevMgtMapper.delTransDvlpTable(productDevMgt);
    //(개발)과금요율 삭제
	productDevMgt.setTableId("TPMPD_D_RATE");
	productDevMgt.setDeleteCond("RATE_ITM_CD IN (SELECT RATE_ITM_CD FROM TPMPD_D_RATE_ITM WHERE PROD_CD = '" + productDevMgt.getProdCd() + "')");
    productDevMgtMapper.delTransDvlpTable(productDevMgt);
    //(개발)할인배타 삭제
	productDevMgt.setTableId("TPMPD_D_DISC_EXCL");
	productDevMgt.setDeleteCond("RATE_ITM_CD IN (SELECT RATE_ITM_CD FROM TPMPD_D_RATE_ITM WHERE PROD_CD = '" + productDevMgt.getProdCd() + "')");
    productDevMgtMapper.delTransDvlpTable(productDevMgt);
    //(개발)할인기간 삭제
	productDevMgt.setTableId("TPMPD_D_DISC_PERD");
	productDevMgt.setDeleteCond("RATE_ITM_CD IN (SELECT RATE_ITM_CD FROM TPMPD_D_RATE_ITM WHERE PROD_CD = '" + productDevMgt.getProdCd() + "')");
    productDevMgtMapper.delTransDvlpTable(productDevMgt);
    //(개발)공제정보의 삭제
	productDevMgt.setTableId("TPMPD_D_ALWNCE");
	productDevMgt.setDeleteCond("PROD_CD = '" + productDevMgt.getProdCd() + "'");
    productDevMgtMapper.delTransDvlpTable(productDevMgt);
    //(개발)과금항목 삭제 - 꼭 맨 나중에...
	productDevMgt.setTableId("TPMPD_D_RATE_ITM");
	productDevMgt.setDeleteCond("PROD_CD = '" + productDevMgt.getProdCd() + "'");
    productDevMgtMapper.delTransDvlpTable(productDevMgt);	
	
}
	
	
	

	
	
	
}
