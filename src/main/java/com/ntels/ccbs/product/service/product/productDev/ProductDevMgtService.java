package com.ntels.ccbs.product.service.product.productDev;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.product.domain.product.productDev.ProductDevMgt;
import com.ntels.ccbs.system.domain.common.service.SessionUser;

public interface ProductDevMgtService {
	public List<ProductDevMgt> getDProdTree(String sessionLanguage, List<Map<String,Object>> soAuthList);
	public List<ProductDevMgt> getDProdTree2(String sessionLanguage, List<Map<String,Object>> soAuthList, String currentDay);
	public int getProductListCount (ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getProductList(ProductDevMgt productDevMgt, int page, int perPage);
	public int getProductListCount2 (ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getProductList2(ProductDevMgt productDevMgt, int page, int perPage);	
	public String getNextDispPriNo();
	public List<ProductDevMgt> getDualProductNm(ProductDevMgt productDevMgt);
	public int addProduct(ProductDevMgt productDevMgt);
	public int addProductDvlpHist(ProductDevMgt productDevMgt);
	public int modProduct(ProductDevMgt productDevMgt);
	public int modProductDvlpHist(ProductDevMgt productDevMgt);
	public int getProdDvlpListCount(ProductDevMgt productDevMgt, List<Map<String,Object>> soAuthList);
	public List<ProductDevMgt> getProdDvlpList(ProductDevMgt productDevMgt, int page, int perPage, List<Map<String,Object>> soAuthList);
	public List<ProductDevMgt> getProdConfHistCnt(ProductDevMgt productMgt);
	public int addProdConfHist(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> transProdConfDtlList(ProductDevMgt productDevMgt);
	public int modProdDvlpHistConfReq(ProductDevMgt productDevMgt);
	public int modProdConfHistConfReq(ProductDevMgt productDevMgt);
	public ProductDevMgt getModProductInit(ProductDevMgt productDevMgt);
	public String delProduct(ProductDevMgt productDevMgt);
	public String addCopyProduct(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getCopyProductList(ProductDevMgt productDevMgt, List<Map<String,Object>> soAuthList);
	public List<ProductDevMgt> getEqtClCdComboList(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getProductUpdateExtractList(ProductDevMgt productDevMgt, List<Map<String,Object>> soAuthList);
	public String addExtractProduct(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getProductServiceList(ProductDevMgt productDevMgt, int page, int perPage);
	public int getProductServiceListCount(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getProductServiceList2(ProductDevMgt productDevMgt, int page, int perPage);
	public int getProductServiceListCount2(ProductDevMgt productDevMgt);	
	public List<ProductDevMgt> getAddProductServiceInit(ProductDevMgt productDevMgt);
	public String addProductService(ProductDevMgt productDevMgt);
	public String modProductService(ProductDevMgt productDevMgt);
	public String delProductService(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getSuprtEquipList(ProductDevMgt productDevMgt, int page, int perPage);
	public int getSuprtEquipListCount(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getSuprtEquipList2(ProductDevMgt productDevMgt, int page, int perPage);
	public int getSuprtEquipListCount2(ProductDevMgt productDevMgt);	
	
	public List<ProductDevMgt> getEqtClCdComboList2(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getSbjProdCdComboList(ProductDevMgt productDevMgt);
	public int getNextDispPriNo2(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getSbjSvcCdComboList(ProductDevMgt productDevMgt);
	public String addSuprtEquip(ProductDevMgt productDevMgt);
	public ProductDevMgt getSuprtEquip(ProductDevMgt productDevMgt);
	public String modSuprtEquip(ProductDevMgt productDevMgt);
	public String delSuprtEquip(ProductDevMgt productDevMgt);
	public int getProductAttrListCount(ProductDevMgt productDevMgt);	
	public List<ProductDevMgt> getProductAttrList(ProductDevMgt productDevMgt, int page, int perPage);
	public int getProductAttrListCount3(ProductDevMgt productDevMgt);	
	public List<ProductDevMgt> getProductAttrList3(ProductDevMgt productDevMgt, int page, int perPage);	
	
	public List<ProductDevMgt> getProductAttrList2(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getCommonGrpCdList(List<Map<String,Object>> commonGrp, String lngTyp);
	public int getProductAttrCnt(ProductDevMgt productDevMgt);
	int addProductAttribute(ProductDevMgt productDevMgt);
	int modProductAttribute(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getProductRelationshipList(ProductDevMgt productDevMgt, int page, int perPage);
	public int getProductRelationshipListCount(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getProductRelationshipList2(ProductDevMgt productDevMgt, int page, int perPage);
	public int getProductRelationshipListCount2(ProductDevMgt productDevMgt);	
	
	public List<ProductDevMgt> getProductRelComboList(ProductDevMgt productDevMgt);
	String addProductRelationship(ProductDevMgt productDevMgt);
	String delProductRelationship(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getRateItmList(ProductDevMgt productDevMgt, int page, int perPage);
	public List<ProductDevMgt> getRateItmList2(ProductDevMgt productDevMgt, int page, int perPage);	
	public int getRateItmListCount(ProductDevMgt productDevMgt);
	public int getRateItmListCount2(ProductDevMgt productDevMgt);	
	public String addRetrieveRateItm(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getAddRateItmInit(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getDefltRateItemList(ProductDevMgt productDevMgt);
	public String addRateItm(ProductDevMgt productDevMgt);
	public ProductDevMgt getRateItm(ProductDevMgt productDevMgt);
	public String modRateItm(ProductDevMgt productDevMgt);
	public String delRateItm(ProductDevMgt productDevMgt);
	public int getRateItmFctrListCount(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getRateItmFctrList(ProductDevMgt productDevMgt, int page, int perPage);
	public int getRateItmFctrListCount2(ProductDevMgt productDevMgt);	
	public List<ProductDevMgt> getRateItmFctrList2(ProductDevMgt productDevMgt, int page, int perPage);	
	public List<ProductDevMgt> getRateItmRegCnt(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getRetrieveRateItemFactor(ProductDevMgt productDevMgt);
	public int getRetrieveRateItemFactorNumber(String rateItmCd);
	public int addRetrieveRateItemFactor_2(ProductDevMgt productDevMgt);
	public ProductDevMgt getModRateItmFctrInit(ProductDevMgt productDevMgt);
	public String modRateItmFctr(ProductDevMgt productDevMgt);
	public String delRateItmFctr(ProductDevMgt productDevMgt);
	public int getRateItmAttrListCount(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getRateItmAttrList(ProductDevMgt productDevMgt, int page, int perPage);
	public String addRetrieveRateItemAttribute(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getRateItmAttrList2(ProductDevMgt productDevMgt);
	public int getRateItmAttributeCnt(ProductDevMgt productDevMgt);
	public int addRateItmAttribute(ProductDevMgt productDevMgt);
	public int modRateItmAttribute(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getTableDataList(Map<String,String> conTableMap);
	public int getRateItmCondListCount(ProductDevMgt productDevMgt);
	public int getRateItmCondListCount2(ProductDevMgt productDevMgt);	
	public List<ProductDevMgt> getRateItmCondList(ProductDevMgt productDevMgt, int page, int perPage);
	public List<ProductDevMgt> getRateItmCondList2(ProductDevMgt productDevMgt, int page, int perPage);	
	public List<ProductDevMgt> getAddRateItmCondInit(ProductDevMgt productDevMgt);
	public String addRateItmCond(ProductDevMgt productDevMgt);
	public ProductDevMgt getRateItmCond(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getRateItmCondComboList(ProductDevMgt productDevMgt);
	public String modRateItmCond(ProductDevMgt productDevMgt);
	public String delRateItmCond(ProductDevMgt productDevMgt);
	public int getAllowanceListCount(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getAllowanceList(ProductDevMgt productDevMgt, int page, int perPage);
	public int getAllowanceListCount2(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getAllowanceList2(ProductDevMgt productDevMgt, int page, int perPage);		
	public List<ProductDevMgt> getAddRateItmRateFctrInit(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getAddRateItmRateFctrList(ProductDevMgt productDevMgt);	
	public List<ProductDevMgt> getOprndNm(ProductDevMgt productDevMgt);	
	
	public String addRate(ProductDevMgt productDevMgt);
	public ProductDevMgt getRateItmRate(ProductDevMgt productDevMgt);
	public String modRate(ProductDevMgt productDevMgt);
	public String delRate(ProductDevMgt productDevMgt);
	public int getConfSbjListCount(ProductDevMgt productDevMgt, List<Map<String,Object>> soAuthList);
	public List<ProductDevMgt> getConfSbjList(ProductDevMgt productDevMgt, int page, int perPage, List<Map<String,Object>> soAuthList);	
	public int getTransOperListCount(ProductDevMgt productDevMgt, List<Map<String,Object>> soAuthList);
	public List<ProductDevMgt> getTransOperList(ProductDevMgt productDevMgt, int page, int perPage, List<Map<String,Object>> soAuthList);		
	public List<ProductDevMgt> getConfDeptComboList(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getConfUsrIdList(ProductDevMgt productDevMgt, List<Map<String,Object>> soAuthList);		
	public String addProdConfDtl(List<ProductDevMgt> params, SessionUser sessionUser);
	public String modProdConfReqProc(List<ProductDevMgt> params);
	public String modProdConfReqCancelProc(ProductDevMgt productDevMgt);
	public String modConfReturn(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getProductRelationshipListAll(ProductDevMgt productDevMgt, List<Map<String,Object>> soAuthList);
	public List<ProductDevMgt> getProductdefultRelationsList_res(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getProductdefultRelationsList_req(ProductDevMgt productDevMgt);	
	public ProductDevMgt getProdect(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getProductRelationshipListAll_prod_case_1(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getProductRelationshipListAll_prod_case_2(ProductDevMgt productDevMgt);	
	public String setProductRelationship(List<ProductDevMgt> params, SessionUser sessionUser);
	public String setProductCancel(List<ProductDevMgt> params);
	public String transfer(ProductDevMgt productDevMgt);
	public String delProdConfDtl(List<ProductDevMgt> params, SessionUser sessionUser);
	public ProductDevMgt getProdDvlpStatus(ProductDevMgt productDevMgt);
	public ProductDevMgt getModProductServiceInit(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getDualRateItmNm(ProductDevMgt productDevMgt); 
	public int getDiscExclListCount(ProductDevMgt productDevMgt);
	public int getDiscExclListCount2(ProductDevMgt productDevMgt);	
	public List<ProductDevMgt> getDiscExclList(ProductDevMgt productDevMgt, int page, int perPage);
	public List<ProductDevMgt> getDiscExclList2(ProductDevMgt productDevMgt, int page, int perPage);	
	public List<ProductDevMgt> getAddDiscExclInit(ProductDevMgt productDevMgt);
	public String addDiscExcl(List<ProductDevMgt> params, SessionUser sessionUser);
	public String delDiscExclusionRelationship(ProductDevMgt productDevMgt);
	public int getDiscSvcRateItmTypListCount(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getDiscSvcRateItmTypList(ProductDevMgt productDevMgt, int page, int perPage);
	public int getDiscSvcRateItmTypListCount2(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getDiscSvcRateItmTypList2(ProductDevMgt productDevMgt, int page, int perPage);	
	public List<ProductDevMgt> getAddDiscSvcRateItmTypInit(ProductDevMgt productDevMgt);
	public String addDiscSvcRateItmTyp(List<ProductDevMgt> params, SessionUser sessionUser);
	public String delDiscSvcRateItmTyp(ProductDevMgt productDevMgt);
	public int getDiscPerdListCount(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getDiscPerdList(ProductDevMgt productDevMgt, int page, int perPage);
	public int getDiscPerdListCount2(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getDiscPerdList2(ProductDevMgt productDevMgt, int page, int perPage);	
	public List<ProductDevMgt> getOprndFctrCd(ProductDevMgt productDevMgt);
	public String addDiscPerd(ProductDevMgt productDevMgt);
	public String delDiscPerd(ProductDevMgt productDevMgt);
	public ProductDevMgt getDiscPerd(ProductDevMgt productDevMgt);
	public String modDiscPerd(ProductDevMgt productDevMgt);
	public String addRateExtractList(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getCommonCodeComboList(ProductDevMgt productDevMgt);
	
	public int getRateItmAttrListCount2(ProductDevMgt productDevMgt);
	public List<ProductDevMgt> getRateItmAttrList2(ProductDevMgt productDevMgt, int page, int perPage);	
}