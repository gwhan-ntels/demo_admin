package com.ntels.ccbs.product.mapper.product.productDev;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.product.domain.product.productDev.ProductDevMgt;

public interface ProductDevMgtMapper {
	List<ProductDevMgt> getDProdTree(@Param(value ="sessionLanguage") String sessionLanguage,
						@Param(value ="soAuthList") List<Map<String, Object>> soAuthList);
	
	List<ProductDevMgt> getDProdTree2(@Param(value ="sessionLanguage") String sessionLanguage,
			@Param(value ="soAuthList") List<Map<String, Object>> soAuthList, @Param(value ="currentDay") String currentDay);	
	
	int getProductListCount(@Param(value ="productDevMgt") ProductDevMgt productDevMgt); 

	List<ProductDevMgt> getProductList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
						 @Param(value="start")int start
						, @Param(value="end") int end);		
	
	int getProductListCount2(@Param(value ="productDevMgt") ProductDevMgt productDevMgt); 

	List<ProductDevMgt> getProductList2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
						 @Param(value="start")int start
						, @Param(value="end") int end);		
	
	String getNextDispPriNo();
	
	List<ProductDevMgt> getDualProductNm(@Param(value ="productDevMgt") ProductDevMgt productDevMgt); 

	int addProduct(@Param(value ="productDevMgt") ProductDevMgt productDevMgt); 
	
	int addProductDvlpHist(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	
	int modProduct(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	
	int modProductDvlpHist(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	
	int getProdDvlpListCount(@Param(value ="productDevMgt") ProductDevMgt productDevMgt,
							@Param(value ="soAuthList") List<Map<String, Object>> soAuthList);
	
	List<ProductDevMgt> getProdDvlpList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end, @Param(value ="soAuthList") List<Map<String, Object>> soAuthList);	
	
	List<ProductDevMgt> getProdConfHistCnt(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	
	int addProdConfHist(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	
	List<ProductDevMgt> getProdConfDtlList(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	
	int modProdDvlpHistConfReq(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	
	int modProdConfHistConfReq(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	
	ProductDevMgt getModProductInit(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	
	int delProduct(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int delProductRelationship(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int delProductRelationshipGift(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int delProductSubscripCond(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int delProductAttribute(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int delSuprtEquip(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int delProductService(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int delRateItemAttribute(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int delRateItemFactor(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int delDiscSvcRateItmTyp(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int delRateItemCondition(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int delRate(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int delDiscExclusionRelationship(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int delDiscPeriod(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int delAllowance(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int delRateItem(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int cancelProductDvlpHist(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	
	int addCopyProductRelationship(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addCopyProductSubscripCond(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addCopyProductAttribute(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addCopyProductService(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addCopyProdSuprtGift(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addCopySuprtEquip(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getRateItmCdList(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addCopyRateItem(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addCopyRateItemAttribute(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addCopyRateItemFactor(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addCopyDiscSvcRateItmTyp(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addCopyRateItemCondition(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addCopyRate(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addCopyDiscExclusionRelationship(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addCopyDiscPeriod(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getAlwnceCdList(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int modCopyRate(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addCopyAllowance(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getCopyProductList(@Param(value ="productDevMgt") ProductDevMgt productDevMgt,
			@Param(value ="soAuthList") List<Map<String, Object>> soAuthList);
	List<ProductDevMgt> getEqtClCdComboList(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getProductUpdateExtractList(@Param(value ="productDevMgt") ProductDevMgt productDevMgt,
			@Param(value ="soAuthList") List<Map<String, Object>> soAuthList);
	int addExtractProduct(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addExtractProductRelationship(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addExtractProductSubscripCond(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addExtractProductAttribute(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addExtractProductService(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addExtractProdSuprtGift(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addExtractSuprtEquip(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int getExtractRateItem(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addExtractRateItem(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addExtractRateItemAttribute(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addExtractRateItemFactor(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addExtractDiscSvcRateItmTyp(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addExtractRateItemCondition(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addExtractRate(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addExtractDiscExclusionRelationship(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addExtractDiscPeriod(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addExtractAllowance(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int getProductServiceListCount(@Param(value ="productDevMgt") ProductDevMgt productDevMgt); 
	List<ProductDevMgt> getProductServiceList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);
	int getProductServiceListCount2(@Param(value ="productDevMgt") ProductDevMgt productDevMgt); 
	List<ProductDevMgt> getProductServiceList2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);	
	
	List<ProductDevMgt> getAddProductServiceInit(@Param(value ="productDevMgt") ProductDevMgt productDevMgt); 
	int addProductService(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int modProductService(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int getSuprtEquipListCount(@Param(value ="productDevMgt") ProductDevMgt productDevMgt); 
	List<ProductDevMgt> getSuprtEquipList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);	
	int getSuprtEquipListCount2(@Param(value ="productDevMgt") ProductDevMgt productDevMgt); 
	List<ProductDevMgt> getSuprtEquipList2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);		
	List<ProductDevMgt> getEqtClCdComboList2(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getSbjProdCdComboList(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int getNextDispPriNo2(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getSbjSvcCdComboList(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addSuprtEquip(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	ProductDevMgt getSuprtEquip(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int modSuprtEquip(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getProductAttrList (@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);
	int getProductAttrListCount(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getProductAttrList3 (@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);
	int getProductAttrListCount3(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);	
	List<ProductDevMgt> getProductAttrList2(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getCommonGrpCdList(@Param(value ="commonGrp") List<Map<String, Object>> soAuthList
								,@Param(value ="lngTyp") String lngTyp);
	int getProductAttrCnt(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addProductAttribute(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int modProductAttribute(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getProductRelationshipList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);
	
	int getProductRelationshipListCount(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);

	List<ProductDevMgt> getProductRelationshipList2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);
	
	int getProductRelationshipListCount2(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);	
	
	List<ProductDevMgt> getProductRelComboList(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addProductRelationship(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);	
	List<ProductDevMgt> getRateItmList (@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);
	List<ProductDevMgt> getRateItmList2 (@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);	
	int getRateItmListCount(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);	
	int getRateItmListCount2(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);	
	List<ProductDevMgt> getRetrieveSvcRateItmTypList(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addRetrieveRateItem(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addRetrieveRateItemAttribute(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addRetrieveRateItemFactor(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getAddRateItmInit(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getDefltRateItemList(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addRateItm(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	ProductDevMgt getRateItm(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int modRateItm(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getDualRateItmNm(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getRateItmRateItmCd(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int getRateItmFctrListCount(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getRateItmFctrList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);
	int getRateItmFctrListCount2(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getRateItmFctrList2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);	
	List<ProductDevMgt> getRateItmRegCnt(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getRetrieveRateItemFactor(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int getRetrieveRateItemFactorNumber(@Param(value ="rateItmCd") String rateItmCd);
	int addRetrieveRateItemFactor_2(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	ProductDevMgt getModRateItmFctrInit(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getDualRateItmFctrNo(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int modRateItmFctr(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getRateItmCondFctrList(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getDiscPeriodFctrList(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int getRateItmAttrListCount(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getRateItmAttrList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);
	int getRateItmAttrListCount2(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getRateItmAttrList2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);	
	List<ProductDevMgt> getRateItmAttrList2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int getRateItmAttributeCnt(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int addRateItmAttribute(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modRateItmAttribute(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int getRateItmCondListCount(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int getRateItmCondListCount2(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);	
	List<ProductDevMgt> getRateItmCondList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);	
	List<ProductDevMgt> getRateItmCondList2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);		
	List<ProductDevMgt> getTableDataList (@Param(value = "conTableMap") Map<String, String> conTableMap);
	List<ProductDevMgt> getAddRateItmCondInit (@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addRateItmCond(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	ProductDevMgt getRateItmCond(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int modRateItmCond(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int getAllowanceListCount(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getAllowanceList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);	
	int getAllowanceListCount2(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getAllowanceList2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);		
	List<ProductDevMgt> getAddRateItmRateFctrInit(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getOprndNm(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);	
	List<ProductDevMgt> getAddRateItmRateFctrList(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getDupRateItmRateList (@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addRate(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	ProductDevMgt getRateItmRate(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int modRate(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int getConfSbjListCount(@Param(value ="productDevMgt") ProductDevMgt productDevMgt,
			@Param(value ="soAuthList") List<Map<String, Object>> soAuthList);
	List<ProductDevMgt> getConfSbjList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
	@Param(value="start")int start
	, @Param(value="end") int end, @Param(value ="soAuthList") List<Map<String, Object>> soAuthList);
	int getTransOperListCount(@Param(value ="productDevMgt") ProductDevMgt productDevMgt,
			@Param(value ="soAuthList") List<Map<String, Object>> soAuthList);
	List<ProductDevMgt> getTransOperList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
	@Param(value="start")int start
	, @Param(value="end") int end, @Param(value ="soAuthList") List<Map<String, Object>> soAuthList);
	List<ProductDevMgt> getConfDeptComboList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getConfUsrIdList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
						@Param(value ="soAuthList") List<Map<String, Object>> soAuthList);
	List<ProductDevMgt> getDupConferCd(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int addProdConfDtl(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modProdDvlpConfReq(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modConfReturn(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	ProductDevMgt getConfCnt(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getProductRelationshipListAll(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			@Param(value ="soAuthList") List<Map<String, Object>> soAuthList);
	List<ProductDevMgt> getProductdefultRelationsList_res(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getProductdefultRelationsList_req(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);	
	ProductDevMgt getProdect(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);	
	List<ProductDevMgt> getProductRelationshipListAll_prod_case_1(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getProductRelationshipListAll_prod_case_2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);	
	int setRelationship_1(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int setProductRelationship_prod_case_1(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int setRelationship_2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int setProductRelationship_prod_case_2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);	
	int setProductCancel(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);	
	int setProductCancel_1(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int addTransferProduct(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int getTransProdRel(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int addTransProdRel(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int addTransferProductSubscripCond(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int addTransferProductAttribute(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int addTransferProductAttribute2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);	
	int addTransferProductService(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int addTransferProductService2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);	
	int addTransferProdSuprtGift(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int getTransferSuprtEquip(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int addTransferSuprtEquip(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int getTransferRateItem(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int addTransferRateItem(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int addTransferRateItem2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);	
	int addTransferRateItemAttribute(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int addTransferRateItemAttribute2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);	
	int addTransferRateItemFactor(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int addTransferRateItemFactor2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);	
	int addTransferDiscSvcRateItmTyp(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int addTransferDiscSvcRateItmTyp2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);	
	int addTransferRateItemCondition(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int addTransferRateItemCondition2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);	
	int addTransferRate(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int addTransferRate2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);	
	int addTransferDiscExcl(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int addTransferDiscPeriod(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int addTransferAllowance(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int delProdConfDtl(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int delProdConfHist(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	ProductDevMgt getProdDvlpStatus(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	ProductDevMgt getModProductServiceInit(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int getDiscExclListCount(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getDiscExclList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);		
	int getDiscExclListCount2(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getDiscExclList2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);		
	List<ProductDevMgt> getAddDiscExclInit(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addDiscExcl(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int getDiscSvcRateItmTypListCount(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getDiscSvcRateItmTypList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);		
	int getDiscSvcRateItmTypListCount2(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getDiscSvcRateItmTypList2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);		
	List<ProductDevMgt> getAddDiscSvcRateItmTypInit(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addDiscSvcRateItmTyp(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int getDiscPerdListCount(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getDiscPerdList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);	
	int getDiscPerdListCount2(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	List<ProductDevMgt> getDiscPerdList2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt,
			 @Param(value="start")int start
			, @Param(value="end") int end);	
	List<ProductDevMgt> getOprndFctrCd(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int addDiscPerd(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int delDiscPerd(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	ProductDevMgt getDiscPerd(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	int modDiscPerd(@Param(value ="productDevMgt") ProductDevMgt productDevMgt);
	
	
	ProductDevMgt getTransProdOper(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransProd(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransProdExpirateDt(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransUpdDelProdRel(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransProdRelDvlpList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransProdRelMstrFl(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransUpdDelProdSubscripCond(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransProdSubscripCondList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransProdSubscripCond(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransProdSubscripCondExpirateDt(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransUpdDelProdAttr(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransProdAttrList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransProdAttrComp(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransProdAttr(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransProdAttrMstrFl(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransProdAttrMstrFl2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransUpdDelProdSvc(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransProdSvcList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransProdSvcComp(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransProdSvc(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransProdSvcExpirateDt(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransProdSvcMstrFl(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransUpdDelProdSuprtGift(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransProdSuprtGiftDvlpList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransProdSuprtGiftMstrFl(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransUpdDelSuprtEquip(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransSuprtEquipList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransSuprtEquipComp(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransSuprtEquip(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransSuprtEquipMstrFl(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransUpdDelRateItm(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransRateItmList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransRateItm(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransRateItmExpirateDt(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransRateItmMstrFl(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransUpdDelRateItmAttr(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransRateItmAttrList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransRateItmComp(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransRateItmAttr(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransRateItmAttrExpirateDt(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransRateItmAttrMstrFl(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransUpdDelRateItmFctr(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransRateItmFctrList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransRateItmFctrComp(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransRateItmFctrExpirateDt(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransRateItmFctrMstrFl(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransUpdDelDiscSvcRateItmTyp(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransDiscSvcRateItmTypDvlpList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransDiscSvcRateItmTypMstrFl(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransDiscSvcRateItmTypMstrFl2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);	
	int modTransUpdDelRateItmCond(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransRateItmCondList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransRateItmCondComp(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	
	List<ProductDevMgt> getTransDiscSvcRateItmComp(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransDiscSvcRateItmComp2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransRateItmCond(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransDiscSvcRateItmComp(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	
	int modTransRateItmCondExpirateDt(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransDiscSvcRateItmTypExpirateDt(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransRateItmCondMstrFl(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransUpdDelRate(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransRateList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransRateComp(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransRate(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransRateExpirateDt(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransRateMstrFl(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransUpdDelDiscExcl(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransDiscExclDvlpList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransDiscExclMstrFl(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransUpdDelDiscPerd(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransDiscPerdList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransDiscPerdComp(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransDiscPerd(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransUpdDelAlwnce(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransRateItmAttrComp(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransProdSubscripCondComp(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransRateItmFctr(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransDiscPerdExpirateDt(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransDiscPerdMstrFl(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransAlwnceList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransAlwnceComp(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransAlwnce(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransAlwnceExpirateDt(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransAlwnceMstrFl(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransDualListProdRel(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransDualListProdSubscrip(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransDualListProdAttr(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransDualListProdSvc(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransDualListProdSuprtGift(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransDualListSuprtEquip(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransDualListRateItm(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransDualListRateItmAttr(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransDualListRateItmFctr(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransDualListDiscSvcRateItmTyp(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransDualListDiscSvcRateItmTyp2(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);	
	List<ProductDevMgt> getTransDualListRateItmCond(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransDualListRate(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransDualListDiscExcl(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransDualListDiscPerd(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getTransDualListAlwnce(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int modTransferExpiration(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int delTransDvlpTable(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int delProdrateReq(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	List<ProductDevMgt> getRateFctrInfo(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
	int addRateExtractList(@Param(value = "conTableMap") Map<String, Object> conTableMap);	
	List<ProductDevMgt> getCommonCodeComboList(@Param(value = "productDevMgt")ProductDevMgt productDevMgt);
}
