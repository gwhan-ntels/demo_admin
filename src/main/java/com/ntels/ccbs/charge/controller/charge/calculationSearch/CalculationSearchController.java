package com.ntels.ccbs.charge.controller.charge.calculationSearch;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.charge.domain.charge.calculationSearch.CalculationSearchVO;
import com.ntels.ccbs.charge.domain.charge.calculationSearch.PaymentChargeCalculationVo;
import com.ntels.ccbs.charge.service.charge.charge.PaymentChargeCalculationService;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/charge/charge/calculationSearch/calculationSearchMng")
public class CalculationSearchController {
	
	private static String URL = "charge/charge/calculationSearch/calculationSearchMng";
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private PaymentChargeCalculationService paymentChargeCalculationService;
	
/*	
	@Autowired
	private CustomerDocumentService customerDocumentService;
*/	
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "chargCalculationResult", method = RequestMethod.POST)
	public String chargCalculationResult(Model model, CalculationSearchVO calculationSearchVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		return URL + "/chargCalculationResult";
	}

	
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: paymentChargeCalculation
	 * 2. ClassName : CalculationSearchController
	 * 3. Comment   : 납부계정별요금계산내역 화면
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 4. 27. 오전 9:43:27
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param calculationSearchVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "paymentChargeCalculation", method = RequestMethod.POST)
	public String paymentChargeCalculation(Model model, CalculationSearchVO calculationSearchVO, HttpServletRequest request) throws Exception {

		return URL + "/paymentChargeCalculation";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getChrgeInfoListAction
	 * 2. ClassName : CalculationSearchController
	 * 3. Comment   : 납부계정별요금계산내역 - 요금내역 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 4. 27. 오전 10:45:18
	 * </PRE>
	 *   @return void
	 *   @param paymentChargeCalculationVo
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "getChrgeInfoListAction", method = RequestMethod.POST)
	public void getChrgeInfoListAction(
			PaymentChargeCalculationVo paymentChargeCalculationVo
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		
		int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		paymentChargeCalculationVo.setLngTyp(lngTyp);
		
		int count = 0;
		count = paymentChargeCalculationService.getChrgeInfoCount(paymentChargeCalculationVo);
		
		List<PaymentChargeCalculationVo> returnList = new ArrayList<PaymentChargeCalculationVo>();
		if (count > 0){
			returnList = paymentChargeCalculationService.getChrgeInfoList(paymentChargeCalculationVo, page, perPageRow);
		}
		
		model.addAttribute("rows", returnList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", returnList.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	

	@RequestMapping(value = "getDtlListAction", method =  RequestMethod.POST ) 
	public void getDtlListAction(Model model, PaymentChargeCalculationVo paymentChargeCalculationVo, HttpServletRequest request) {

		List<PaymentChargeCalculationVo> list = paymentChargeCalculationService.getDtlList(paymentChargeCalculationVo);
		
		
		model.addAttribute("rows", list);

//		return URL + "/ajax/paymentChargeCalculation";

	}
	
	
	
	
	
	
	@RequestMapping(value = "paymentDiscountSearch", method = RequestMethod.POST)
	public String paymentDiscountSearch(Model model, CalculationSearchVO calculationSearchVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		return URL + "/paymentDiscountSearch";
	}
	
	@RequestMapping(value = "useSearch", method = RequestMethod.POST)
	public String useSearch(Model model, CalculationSearchVO calculationSearchVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		return URL + "/useSearch";
	}	

}
