package com.ntels.ccbs.charge.controller.payment.advanceReceived;

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

import com.ntels.ccbs.charge.domain.billing.billing.BillingStatisticsVO;
import com.ntels.ccbs.charge.domain.common.PrepayOcc;
import com.ntels.ccbs.charge.domain.payment.advanceReceived.AdvanceReceivedVO;
import com.ntels.ccbs.charge.service.payment.payment.AdvanceReceivedService;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/charge/payment/advanceReceived/advanceReceivedMng")
public class AdvanceReceivedController {
	
	private static String URL = "charge/payment/advanceReceived/advanceReceivedMng";
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private AdvanceReceivedService advanceReceivedService;
/*	
	@Autowired
	private CustomerDocumentService customerDocumentService;
*/	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "advanceReceivedSearch", method = RequestMethod.POST)
	public String advanceReceivedSearch(Model model, AdvanceReceivedVO advanceReceivedVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("prepayOccStat", commonDataService.getCommonCodeList("BL00084", lng));
		model.addAttribute("prepayOccTp", commonDataService.getCommonCodeList("BL00071", lng));
		model.addAttribute("dpstCl", commonDataService.getCommonCodeList("BL00067", lng));
		model.addAttribute("dtTp", commonDataService.getCommonCodeList("BL00095", lng));
		
		return URL + "/advanceReceivedSearch";
	}

	
	@RequestMapping(value = "getPrepayOccInfoListAction", method = RequestMethod.POST)
	public void getPrepayOccInfoListAction(
			AdvanceReceivedVO advanceReceivedVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		
		int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		advanceReceivedVO.setLngTyp(lngTyp);
		
		int count = 0;
		count = advanceReceivedService.getPrepayOccCount(advanceReceivedVO);
		
		List<AdvanceReceivedVO> returnList = new ArrayList<AdvanceReceivedVO>();
		if (count > 0){
			returnList = advanceReceivedService.getPrepayOccList(advanceReceivedVO, page, perPageRow);
		}
		
		model.addAttribute("rows", returnList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", returnList.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	
	
	@RequestMapping(value = "advanceReceivedPayment", method = RequestMethod.POST)
	public String advanceReceivedPayment(Model model, AdvanceReceivedVO advanceReceivedVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("prepayOccStat", commonDataService.getCommonCodeList("BL00084", lng));
		model.addAttribute("prepayOccTp", commonDataService.getCommonCodeList("BL00071", lng));
		model.addAttribute("dpstCl", commonDataService.getCommonCodeList("BL00067", lng));   // 한광욱 수정 해야 할 
		model.addAttribute("dtTp", commonDataService.getCommonCodeList("BL00095", lng));

		model.addAttribute("transTp", commonDataService.getCommonCodeList("BL00099", lng));
		
		return URL + "/advanceReceivedPayment";
	}


	@RequestMapping(value = "getBillInvoiceListAction", method = RequestMethod.POST)
	public void getBillInvoiceListAction(BillingStatisticsVO billingStatisticsVO, Model model,
			HttpServletRequest request) throws Exception {

		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		billingStatisticsVO.setLngTyp(lngTyp);

		int count = 0;
		count = advanceReceivedService.getBillInvoiceCount(billingStatisticsVO);

		List<BillingStatisticsVO> returnList = new ArrayList<BillingStatisticsVO>();
		if (count > 0) {
			returnList = advanceReceivedService.getBillInvoiceList(billingStatisticsVO, page, perPageRow);
		}

		model.addAttribute("rows", returnList); // 목록리스트
		model.addAttribute("total", Math.ceil((double) count / (double) perPageRow)); // 페이지
																						// 사이즈
		model.addAttribute("records", returnList.size()); // 현화면의 리스트갯수
		model.addAttribute("page", page); // 현재 페이지
		model.addAttribute("perpage", perPageRow);
	}


	@RequestMapping(value = "chkIsRefundApplied", method = { RequestMethod.POST })
	public void chkIsRefundApplied(Model model, HttpServletRequest request) {

		String seqNo = (String) request.getParameter("seqNo");

		int cnt = advanceReceivedService.getRefundAppliedCnt(seqNo);

		model.addAttribute("cnt", cnt);

		//return URL + "/prepayTransMngList";
	}

	@RequestMapping(value = "insertAction", method = { RequestMethod.POST })
	public String insertAction(Model model, PrepayOcc prepayOcc) {

		
		int result = advanceReceivedService.insertAction(prepayOcc);		
		
		model.addAttribute("result", result);
		
		return  URL + "/ajax/advanceReceivedPayment"; 
	}

}
