package com.ntels.ccbs.charge.controller.payment.payment;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.charge.domain.payment.payment.ReceiptCancelVO;
import com.ntels.ccbs.charge.service.payment.payment.ReceiptCancelService;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/charge/payment/payment/receiptCancel")
public class ReceiptCancelController {
	
	private static String URL = "charge/payment/payment/receiptCancel";
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private ReceiptCancelService receiptCancelService;
	
	@RequestMapping(value = "receiptCancelList", method = RequestMethod.POST)
	public String receiptCancelList(Model model, ReceiptCancelVO receiptCancelVO, HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("dateTp", commonDataService.getCommonCodeList("BL00122", lngTyp));	 //일자구분
		model.addAttribute("paymentTp", commonDataService.getCommonCodeList("BL00067", lngTyp)); //입금구분
		model.addAttribute("receiptTp", commonDataService.getCommonCodeList("BL00079", lngTyp)); //수납유형
		
		return URL + "/receiptCancelList";
	}	

	@RequestMapping(value = "recCnclListAction", method = RequestMethod.POST)
	public void recStatisticsListAction(ReceiptCancelVO receiptCancel, Model model, HttpServletRequest request) throws Exception {

		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		receiptCancel.setLngTyp(lngTyp);

		int count = 0;
		count = receiptCancelService.rcptListCnt(receiptCancel);

		List<ReceiptCancelVO> pymList = new ArrayList<ReceiptCancelVO>();

		if (count > 0) {
			pymList = receiptCancelService.rcptList(receiptCancel, page, perPageRow);
		}
		
		model.addAttribute("rows", pymList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", pymList.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}

	@RequestMapping(value = "chkIsCancelAble", method = RequestMethod.POST)
	public String chkIsCancelAble(Model model, ReceiptCancelVO receiptCancelVO, HttpServletRequest request) throws Exception {
		int result = receiptCancelService.getReceiptCancelAbleCheck(receiptCancelVO.getPymSeqNo());

		model.addAttribute("returnResult", result);

		return URL + "/ajax/receiptCancelList";
	}

	@RequestMapping(value = "insertAction", method = { RequestMethod.POST })
	public String insertAction(Model model, ReceiptCancelVO receiptCancelVO, HttpServletRequest request) {

		int result = receiptCancelService.insertAction(receiptCancelVO);
				
		model.addAttribute("result", result);

		return  URL + "/ajax/receiptCancelList"; 
		
//
//		SessionUser sessionUser = CommonUtil.getSessionManager();
//
//		String pymSeqNo = receiptCancelVO.getPymSeqNo();
//		String cnclResnTxt = receiptCancelVO.getCnclResn();
//		String inptMenuId = receiptCancelVO.getInptMenuId();
//
//		int result = -1;
//		try {
//			result = receiptCancelService.processReceiptCancelMain(pymSeqNo, cnclResnTxt, inptMenuId, sessionUser.getUserId());
//		} catch (Exception e) {
//			throw new ServiceException("MSG.M10.MSG00005"); // MSG.M10.MSG00005=처리에 실패했습니다. 관리자에게 문의해 주세요.
//		}
//		model.addAttribute("data", result);
//
//		return URL + "/ajax/eachDepositRegList";
	}

	
	
	
	
	@RequestMapping(value = "receiptCancelResultList", method = RequestMethod.POST)
	public String receiptCancelResultList(Model model, ReceiptCancelVO receiptCancelVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("dateTp", commonDataService.getCommonCodeList("BL00096", lng));	 //일자구분

		
		return URL + "/receiptCancelResultList";
	}
	
	@RequestMapping(value = "receiptCancelResultListAction", method = RequestMethod.POST)
	public void receiptCancelResultListAction(
			ReceiptCancelVO receiptCancel
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		
		int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		receiptCancel.setLngTyp(lngTyp);
		
		int count = 0;
		count = receiptCancelService.receiptCancelResultListCount(receiptCancel);
		
		List<ReceiptCancelVO> pymList = new ArrayList<ReceiptCancelVO>();
		if (count > 0){
			pymList = receiptCancelService.receiptCancelResultList(receiptCancel, page, perPageRow);
		}
		
		model.addAttribute("rows", pymList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", pymList.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}

	
}
