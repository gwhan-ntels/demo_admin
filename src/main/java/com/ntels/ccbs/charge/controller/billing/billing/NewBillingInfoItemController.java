package com.ntels.ccbs.charge.controller.billing.billing;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.charge.domain.billing.billing.NewBillingInfoItemVO;
import com.ntels.ccbs.charge.service.billing.billing.NewBillingInfoItemService;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/charge/billing/billing/newBillingInfoItem")
public class NewBillingInfoItemController {
	
	private static String URL = "charge/billing/billing/newBillingInfoItem";
	
	@Autowired
	private NewBillingInfoItemService newBillingInfoItemService;
	
	@Autowired
	private CommonDataService commonDataService;
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: newBillingInfoItemList
	 * 2. ClassName : NewBillingInfoItemController
	 * 3. Comment   : 빌링 설정정보 관리 화면
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param BillingInfoItemVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "newBillingInfoItemList", method = RequestMethod.POST)
	public String newBillingInfoItemList(Model model, NewBillingInfoItemVO newBillingInfoItemVO, HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		model.addAttribute("searchReferenceTypeList", commonDataService.getCommonCodeList("BL00013", lngTyp));		//빌링설정위치
		model.addAttribute("referenceType_billingSettingInfoList", newBillingInfoItemService.getBillSetupItemComboList(newBillingInfoItemVO, "1"));		//빌링설정위치
		model.addAttribute("referenceType_billingCycleInfoList", newBillingInfoItemService.getBillSetupItemComboList(newBillingInfoItemVO, "2"));		//빌링설정위치
		model.addAttribute("fieldNatureList", commonDataService.getCommonCodeList("BL00034", lngTyp));     //필드성격
		model.addAttribute("newSetItmList", newBillingInfoItemService.getNewSetItmNm(newBillingInfoItemVO)); // 아래 그리드에 세팅할 [설정항목코드]설정항목명
		
		
		return URL + "/newBillingInfoItemList";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: newBillingInfoItemListAction
	 * 2. ClassName : NewBillingInfoItemController
	 * 3. Comment   : 빌링 설정정보 조회
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 *   @return void
	 *   @param BillingInfoItemVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "newBillingInfoItemListAction", method = RequestMethod.POST)
	public void newBillingInfoItemListAction(
			NewBillingInfoItemVO newBillingInfoItemVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		newBillingInfoItemVO.setLngTyp(lngTyp);
		newBillingInfoItemVO.setSidx(sidx);
		newBillingInfoItemVO.setSord(sord);
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		System.out.println("## session.getUserId() : " +session.getUserId());
		
        List<NewBillingInfoItemVO> list = new ArrayList<NewBillingInfoItemVO>();
		int count = 0;
		count = newBillingInfoItemService.newBillingInfoItemListCount(newBillingInfoItemVO);		
		if (count > 0) list = newBillingInfoItemService.newBillingInfoItemListAction(newBillingInfoItemVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: newBillingInfoItemList
	 * 2. ClassName : NewBillingInfoItemController
	 * 3. Comment   : 빌링 설정정보 관리 화면
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param BillingInfoItemVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "getBillSetupItemComboList", method = RequestMethod.POST)
	public String getBillSetupItemComboList(Model model, NewBillingInfoItemVO newBillingInfoItemVO, HttpServletRequest request) throws Exception {
		
		String referenceTypeCd = newBillingInfoItemVO.getReferenceType();
		
		List<NewBillingInfoItemVO> list = new ArrayList<NewBillingInfoItemVO>();
		
		list = newBillingInfoItemService.getBillSetupItemComboList(newBillingInfoItemVO, referenceTypeCd);
		System.out.println("## list : " + list);
		
		
		model.addAttribute("comboList", list);		//빌링설정위치
		model.addAttribute("newBillingInfoItem", newBillingInfoItemVO);
		
		return URL + "/newBillingInfoItemList";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertNewBillingInfoItemVO
	 * 2. ClassName : NewBillingInfoItemController
	 * 3. Comment   : 빌링 설정정보 등록
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 *   @return void
	 *   @param newBillingInfoItemVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	
	@RequestMapping(value = "insertNewBillingInfoItemInfo", method = RequestMethod.POST)
	public void insertNewBillingInfoItemInfo(
			NewBillingInfoItemVO newBillingInfoItemVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		System.out.println("##setVal: " + newBillingInfoItemVO.getSetVal());
		newBillingInfoItemVO.setRegId(session.getUserId());
		newBillingInfoItemVO.setChgId(session.getUserId());
		newBillingInfoItemVO.setRegDate(DateUtil.sysdate());
		newBillingInfoItemVO.setChgDate(DateUtil.sysdate());
		newBillingInfoItemVO.setLngTyp(lngTyp);
		
		int result = newBillingInfoItemService.insertNewBillingInfoItemInfo(newBillingInfoItemVO);
		
		model.addAttribute("result", result);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateNewBillingInfoItemVO
	 * 2. ClassName : NewBillingInfoItemController
	 * 3. Comment   : 빌링 설정정보 수정
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 *   @return void
	 *   @param newBillingInfoItemVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	
	@RequestMapping(value = "updateNewBillingInfoItemInfo", method = RequestMethod.POST)
	public void updateNewBillingInfoItemInfo(
			NewBillingInfoItemVO newBillingInfoItemVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		newBillingInfoItemVO.setRegId(session.getUserId());
		newBillingInfoItemVO.setChgId(session.getUserId());
		newBillingInfoItemVO.setRegDate(DateUtil.sysdate());
		newBillingInfoItemVO.setChgDate(DateUtil.sysdate());
		newBillingInfoItemVO.setLngTyp(lngTyp);

		int result = newBillingInfoItemService.updateNewBillingInfoItemInfo(newBillingInfoItemVO);
		
		model.addAttribute("result", result);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: deleteNewBillingInfoItemVO
	 * 2. ClassName : NewBillingInfoItemController
	 * 3. Comment   : 빌링 설정정보 수정
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 *   @return void
	 *   @param newBillingInfoItemVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	
	@RequestMapping(value = "deleteNewBillingInfoItemInfo", method = RequestMethod.POST)
	public void deleteNewBillingInfoItemInfo(
			NewBillingInfoItemVO newBillingInfoItemVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		newBillingInfoItemVO.setRegId(session.getUserId());
		newBillingInfoItemVO.setChgId(session.getUserId());
		newBillingInfoItemVO.setRegDate(DateUtil.sysdate());
		newBillingInfoItemVO.setChgDate(DateUtil.sysdate());
		newBillingInfoItemVO.setLngTyp(lngTyp);

		int result = newBillingInfoItemService.deleteNewBillingInfoItemInfo(newBillingInfoItemVO);
		
		model.addAttribute("result", result);
	
	}
	
}
