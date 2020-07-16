package com.ntels.ccbs.product.controller.product.productDev;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.domain.product.productDev.ProductDevMgt;
import com.ntels.ccbs.product.service.product.productDev.ProductDevMgtService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Controller
@RequestMapping(value = "/product/product/productDev/productApproval")
public class ProductApprovalController {
	@Autowired
	private ProductDevMgtService productDevMgtService;	
	
	@Autowired
	private SequenceService sequenceSevice;		
	
	@Autowired
	private CommonDataService commonDataService;	
	
	private static String URL = "product/product/productDev/productApproval";	
	
	//오늘날짜 가져오기
    String currentDay =  DateUtil.getDateStringYYYYMMDD(0);	
	List<Map<String,Object>> soAuthList;
	
	@RequestMapping(value = "main", method = RequestMethod.POST)
	public String viewInit(	Model model,
			HttpServletRequest request) {
		
		String dvlpStatus = "PP00107";
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();

		model.addAttribute("dvlpStatus", commonDataService.getCommonCodeList(dvlpStatus, lngTyp));
		
		return URL + "/main";
	}		
	
	@RequestMapping(value = "getTransOperList", method = RequestMethod.POST)
	public void getTransOperList(
			ProductDevMgt productDevMgt
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
	
		List<ProductDevMgt> transOperList = null;
		int count = 0;

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));			
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		soAuthList = sessionUser.getSoAuthList();	        
		String usrId = sessionUser.getUserId();			
		
		productDevMgt.setLngTyp(lngTyp);
		
		count = productDevMgtService.getTransOperListCount(productDevMgt, soAuthList);		

		if (count > 0) { 
			transOperList = productDevMgtService.getTransOperList(productDevMgt, page, perPageRow, soAuthList);
			model.addAttribute("records", transOperList.size()); //현화면의 리스트갯수	
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", transOperList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	}		
	
	@RequestMapping(value = "transfer", method = RequestMethod.POST)
	public void transfer (
			ProductDevMgt productDevMgt
	      , Model model
	      , HttpServletRequest request
		  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();	
		
	    productDevMgt.setRegrId(usrId);
		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());
		System.out.println("getSoId=>"+productDevMgt.getSoId());
		productDevMgt.setSoId(productDevMgt.getSoId());

		String result = productDevMgtService.transfer(productDevMgt);
		
        if ( result.equals( "response Success" ) )
        {
            //인터페이스 구현 부분
        }		
		
		model.addAttribute("result", result);
	}	
	
}
