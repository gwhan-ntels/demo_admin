package com.ntels.ccbs.system.controller.sample.sample;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.domain.refInfo.commonInfo.AttrTypMap;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.domain.sample.sample.TreeSampleVO;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.sample.sample.TreeSampleService;

/**
 * <PRE>
 * 1. ClassName: TreeSampleController
 * 2. FileName : TreeSampleController.java
 * 3. Package  : com.ntels.ccbs.system.controller.sample.sample
 * 4. Comment  : 샘플 Tree
 * 5. 작성자   : 윤은국
 * 6. 작성일   : 2017. 7. 25. 오후 1:40:05
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2017. 7. 25.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/system/sample/sample/example")
public class TreeSampleController {

	private static String URL = "system/sample/sample/example";

	@Autowired TreeSampleService treeSampleService;
	
	@Autowired
	private CommonDataService commonDataService;
	
	List<TreeSampleVO> getMenuList;	
	/*
	@RequestMapping(value = "treeSample", method = RequestMethod.POST)
	public String treeSample(	Model model,TreeSampleVO treeSample ,HttpServletRequest request) {
		String svcCd = "S01";
		List<TreeSampleVO>  comboList = treeSampleService.getComboList(svcCd);
		getMenuList = treeSampleService.getMenuList(svcCd);
		
		model.addAttribute("comboList", comboList);
		model.addAttribute("getMenuList", getMenuList);
		
		return URL + "/treeSample";
	}
	
	@RequestMapping(value = "getMenuListAction", method = RequestMethod.POST)
	public @ResponseBody Object getMenuListAction(	Model model,TreeSampleVO treeSample ,HttpServletRequest request) {
		
		List<Map<String,Object>> menuList = new ArrayList<Map<String,Object>>();
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		String svcCd = "S01";
				
		ArrayList<Object> root = new ArrayList<Object>(); // json data
		
		
		Map<String, Object> rootNode = new HashMap<String, Object>();
		rootNode.put("title", "/");
		rootNode.put("isFolder",true);
		rootNode.put("key", "ROOT"); 
		rootNode.put("expand", true);
		rootNode.put("order", 0);
		rootNode.put("upMenuNo", 0);
		rootNode.put("stepNo", 0);
		rootNode.put("upMenuName", "/");

		getMenuList = treeSampleService.getMenuList(svcCd);
		List<Map<String,Object>> childMenuList = new ArrayList<Map<String,Object>>();
		for(int i=0; i<getMenuList.size(); i ++ ){
			Map<String,Object> menuData = new HashMap<String,Object>();
		
			if(getMenuList.get(i).getFolder().equals("Y") || getMenuList.get(i).getFolder() == "Y"){
				menuData.put("isFolder", true);
			}else{
				menuData.put("isFolder", false);
			}
			menuData.put("title", getMenuList.get(i).getMenuName());
			
			menuData.put("key", getMenuList.get(i).getMenuNo());
			menuData.put("expand", false);
			menuData.put("order", i);
			menuData.put("searchKey", getMenuList.get(i).getMenuNo()+getMenuList.get(i).getMenuName());
			menuData.put("upMenuNo",0);
			menuData.put("stepNo", Integer.parseInt(getMenuList.get(i).getStepNo()));
			menuData.put("upMenuName", getMenuList.get(i).getUpMenuName());
			childMenuList.add(menuData);
			//menuData.put("children", childMenuList);
			//menuList.add(menuData);
		}
		

		
		rootNode.put("children", menuList);
		
		root.add(rootNode);
		
		return root;
	}
	
	*/
	@RequestMapping(value = "getMenuListAction", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Object getTreeAction(HttpServletRequest request,TreeSampleVO treeSample,String soId) {
		
		System.out.println("soId====================>"+soId);
		System.out.println("treeSample.soId====================>"+treeSample.getSoId());
		String sessionLanguage = (String)request.getSession().getAttribute("sessionLanguage");
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		
		ArrayList<Object> root = new ArrayList<Object>(); // json data
		Map<String, Object> rootNode = new HashMap<String, Object>();
		Map<String, Boolean> removeNode = new HashMap<String, Boolean>();		
		String svcCd = "S01";
		
		getMenuList = treeSampleService.getMenuList(svcCd,soId);
		
		rootNode.put("title", "PRODUCT");
		rootNode.put("targetCd", "PRODUCT");	
		rootNode.put("isFolder", "TRUE");
		rootNode.put("key", "PRODUCT"); 
		rootNode.put("prodList", "");
		rootNode.put("prodGrp", "ROOT");
		rootNode.put("children", makeMenu2Json(rootNode, 0, removeNode));
		rootNode.put("soId", "");
		
		root.add(rootNode);
		
		return root;
	}
	

	private ArrayList<Object> makeMenu2Json(Map<String, Object> parent, int index, Map<String, Boolean> removeNode) {
		
		TreeSampleVO treeSampleVO = null;
		ArrayList<Object> folder = new ArrayList<Object>();		
		String parent_menu_no = "00";
	
		parent_menu_no = parent.get("key").toString();
		
		
		
		for(int i = index; i < getMenuList.size(); i++) {
			treeSampleVO = getMenuList.get(i);
			/*
			System.out.println("order====>"+i);
			System.out.println("parent_menu_no====>"+parent_menu_no);
			System.out.println("getMenuNo=====>"+treeSampleVO.getMenuNo());
			System.out.println("getUpMenuNo=====>"+treeSampleVO.getUpMenuNo());
			System.out.println("removeNode=====>"+removeNode.get(treeSampleVO.getMenuNo()));
			System.out.println("TITLE=====>"+treeSampleVO.getMenuName());
			System.out.println("getFolder=====>"+treeSampleVO.getFolder());
			System.out.println("<============================================================================>");
			*/
			if (!treeSampleVO.getUpMenuNo().equals(parent_menu_no)) {
				//System.out.println("first cont=====>"+treeSampleVO.getUpMenuNo());
				continue;
			}
/*					
			if (removeNode.get(treeSampleVO.getMenuNo()) != null) {
				System.out.println("second cont=====>"+treeSampleVO.getMenuNo());
				continue;
			}			
*/
			if ("Y".equals(treeSampleVO.getFolder())) {
				
				Map<String, Object> node = new HashMap<String, Object>();
			
				node.put("title", treeSampleVO.getMenuName());	
				
				if(treeSampleVO.getFolder().equals("Y") || treeSampleVO.getFolder() == "Y"){
					node.put("isFolder", "TRUE");
				}				
				node.put("key", treeSampleVO.getMenuNo()); 
				node.put("prodList", treeSampleVO.getProdList());
				node.put("expand", "true");
				node.put("treeLevel", treeSampleVO.getStepNo());				
				ArrayList<Object> tmpList = makeMenu2Json(node, index + 1, removeNode);				
				if (tmpList.size() > 0) {
					node.put("children", tmpList);
				}
				folder.add(node);
				removeNode.put(treeSampleVO.getMenuNo(), Boolean.TRUE);				
				
			} else {
				
				Map<String, Object> leaf = new HashMap<String, Object>();				
				leaf.put("title",  treeSampleVO.getMenuName());	
				leaf.put("key", treeSampleVO.getMenuNo());
				leaf.put("prodList", treeSampleVO.getProdList());
				leaf.put("treeLevel",  treeSampleVO.getStepNo());
				folder.add(leaf);				
				removeNode.put(treeSampleVO.getMenuNo(), Boolean.TRUE);	
				
			}	
	
		}
		return folder;		
		
	}	
	
/*
	@RequestMapping(value = "getDownMenuListAction", method = RequestMethod.POST)
	public String getDownMenuListAction(	Model model,MenuMngVO menu ,HttpServletRequest request,String condUpMenuNo,String topMenu
			,String stepNo
			,String sidx,
			String sord) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		if(topMenu==null || topMenu==""){
			return URL + "/ajax/menuMng";
		}
		menu.setTopMenu(topMenu);
		
		int stepNo1=Integer.parseInt(menu.getStepNo())+1;
		
		if(stepNo1>4){
			stepNo1=4;
		}
		menu.setStepNo(Integer.toString(stepNo1));

		List<MenuMngVO> downMenuList=menuMngService.getDownMenuList(lng,condUpMenuNo,topMenu,menu,sidx,sord);
		model.addAttribute("downMenuList", downMenuList);
		return URL + "/ajax/menuMng";
	}
	*/

		
	@RequestMapping(value = "getPordListAction", method = {RequestMethod.POST})
	public void getPordListAction(Model model,TreeSampleVO treeSample,HttpServletRequest request,String prodCd) throws Exception {		
		String sessionLanguage = (String)request.getSession().getAttribute("sessionLanguage");
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		model.addAttribute("prodList", treeSampleService.getPordListAction(prodCd));
	}	

	@RequestMapping(value = "getInputProdList", method = {RequestMethod.POST})
	public void getInputProdList(Model model,TreeSampleVO treeSample,HttpServletRequest request,String prodCd,String StartDt,String basicYn) throws Exception {		
		
		String sessionLanguage = (String)request.getSession().getAttribute("sessionLanguage");
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		List<TreeSampleVO> totalInputProdList = null;
		 int count = 0;
		
		 if(basicYn=="B" || basicYn.equals("B")){
			 count = treeSampleService.getInputProdCnt(prodCd,StartDt);
		 }else if(basicYn=="V" || basicYn.equals("V")){
			 count = treeSampleService.getInputProdCnt1(prodCd,StartDt);
		 }
		 
		 
        if (count > 0) {
   		 if(basicYn=="B" || basicYn.equals("B")){
   			 System.out.println("basicYn==>"+basicYn);
   			totalInputProdList = treeSampleService.getInputProdList(prodCd,StartDt);
   		 }else if(basicYn=="V" || basicYn.equals("V")){
   			totalInputProdList = treeSampleService.getInputProdList1(prodCd,StartDt);
   		 }
        	
        	
            int size = totalInputProdList.size();
            for (int i=0; i < size; i++) {
            	TreeSampleVO tempTreeSample = totalInputProdList.get(i);
            	String tmpGrp = tempTreeSample.getCommonGrp();
            	String tmpProdCd = tempTreeSample.getProdCd();
            	
            	String qry = tempTreeSample.getQry();
            	System.out.println("getQry================>"+tempTreeSample.getQry());
            	//String StartDt = "20170906";
            	
            	if(qry != null && StringUtils.isNotEmpty(qry)){
            		
            		tempTreeSample.setCommonCodeList(commonDataService.getCommonCodeListProd(tmpGrp, tmpProdCd, qry.replaceAll("STRT_DT", StartDt)));
            	}else{
            		tempTreeSample.setCommonCodeList(null);
            	}
        		model.addAttribute("prodList", totalInputProdList);
            }
        } else {
        	model.addAttribute("prodInputSize", "0");
    		model.addAttribute("prodList", null);
        }

	}
	
	@RequestMapping(value = "inputInsertPopUp", method = RequestMethod.POST)
	public String inputInsertPopUp(TreeSampleVO treeSample,String prodCd, Model model, HttpServletRequest request ) throws Exception {	
		System.out.println("getBasicYn=======>"+treeSample.getBasicYn());
		
		model.addAttribute("prodCd", treeSample.getProdCd());
		model.addAttribute("basicYn", treeSample.getBasicYn());
		model.addAttribute("StartDt", treeSample.getStartDt());
		
		return  URL + "/ajax/inputInsertPopUp";
	}

	@RequestMapping(value = "estimatePopUp", method = RequestMethod.POST)
	public String estimatePopUp(TreeSampleVO treeSample,String soId,String orderId, Model model, HttpServletRequest request ) throws Exception {	


		
		model.addAttribute("soId", treeSample.getSoId());
		model.addAttribute("orderId", treeSample.getOrderId());
		
		return  URL + "/ajax/estimatePopUp";
	}
	@RequestMapping(value = "getEstimate", method = {RequestMethod.POST})
	public void getEstimate(Model model,TreeSampleVO treeSample,HttpServletRequest request,String soId, String orderId) throws Exception {		

		List<TreeSampleVO> estimateList = null;
		List<TreeSampleVO> estimateList2 = null;
		
		estimateList = treeSampleService.processEstimateList(soId, orderId);
		//System.out.println("toString============>"+estimateList.toString());
		estimateList2 = treeSampleService.getEstimateList(soId, orderId);
		model.addAttribute("estimateList", estimateList);
		model.addAttribute("estimateList2", estimateList2);
	
	}
	
}