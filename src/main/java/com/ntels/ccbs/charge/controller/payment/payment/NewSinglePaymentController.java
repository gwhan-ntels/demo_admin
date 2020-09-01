package com.ntels.ccbs.charge.controller.payment.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ntels.ccbs.charge.domain.common.EachDeposit;
import com.ntels.ccbs.charge.domain.payment.payment.NewSinglePaymentVO;
import com.ntels.ccbs.charge.service.payment.payment.NewSinglePaymentService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.consts.Consts.ExcelFormatType;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.common.view.ExcelCellVO;
import com.ntels.ccbs.common.view.ExcelColumnVO;
import com.ntels.ccbs.common.view.ExcelFileVO;
import com.ntels.ccbs.common.view.ExcelRowVO;
import com.ntels.ccbs.common.view.ExcelSheetVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.message.MessageUtil;

@Controller
@RequestMapping(value = "/charge/payment/payment/newSinglePayment")
public class NewSinglePaymentController {

	private static String URL = "charge/payment/payment/newSinglePayment";

	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommonDataService commonDataService;

	@Autowired
	private NewSinglePaymentService newSinglePaymentService;

	@Autowired
	private SequenceService sequenceService;

	/**
	 * 
	 * <PRE>
	 * 1. MethodName: newSinglePaymentList
	 * 2. ClassName : NewSinglePaymentController
	 * 3. Comment   : 건별입금등록 관리 화면
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 * 
	 * @return String
	 * @param model
	 * @param NewSinglePaymentVO
	 * @param request
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "newSinglePaymentList", method = RequestMethod.POST)
	public String newSinglePaymentList(Model model, NewSinglePaymentVO newSinglePaymentVO, HttpServletRequest request) throws Exception {

		String lngTyp = (String) request.getSession().getAttribute("sessionLanguage");

		model.addAttribute("receiptGb", commonDataService.getCommonCodeList("BL00070", lngTyp)); // 건별입금구분
		model.addAttribute("cashGb", commonDataService.getCommonCodeList("BL00068", lngTyp));    // 현금입금구분

		return URL + "/newSinglePaymentList";
	}

	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getAnonyReceiptSubListAction
	 * 2. ClassName : getAnonyReceiptSubListController
	 * 3. Comment   : 건별입금,미확인수납처리,선수금수납처리 : 청구내역 조회
	 * 4. 작성자    : pjh
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 * 
	 * @return void
	 * @param NewSinglePaymentVO
	 * @param model
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "getAnonyReceiptSubListAction", method = RequestMethod.POST)
	public void getAnonyReceiptSubListAction(NewSinglePaymentVO newSinglePaymentVO, Model model, HttpServletRequest request) throws Exception {

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		newSinglePaymentVO.setLngTyp(lngTyp);

		List<NewSinglePaymentVO> pymList = new ArrayList<NewSinglePaymentVO>();
		int count = 0;
		count = newSinglePaymentService.getAnonyReceiptSubListCount(newSinglePaymentVO);
		if (count > 0)
			pymList = newSinglePaymentService.getAnonyReceiptSubList(newSinglePaymentVO);

		model.addAttribute("rows", pymList); // 목록리스트
	}

	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getAnonyReceiptSubExcelAction
	 * 2. ClassName : NewSinglePaymentController
	 * 3. Comment   : 건별입금,미확인수납처리,선수금수납처리 : 청구내역 조회  엑셀 다운로드
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 4. 13. 오후 2:49:13
	 * </PRE>
	 * 
	 * @return String
	 * @param model
	 * @param newSinglePaymentVO
	 * @param request
	 * @param billStrtYymm
	 * @param billEndYymm
	 * @param soId
	 * @param pymAcntId
	 * @return
	 */
	@RequestMapping(value = "getAnonyReceiptSubExcelAction", method = RequestMethod.POST)
	public String getAnonyReceiptSubExcelAction(Model model, NewSinglePaymentVO newSinglePaymentVO, HttpServletRequest request) {

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		newSinglePaymentVO.setLngTyp(lngTyp);
		newSinglePaymentVO.setUsrId(session.getUserId());

		List<Map<String, Object>> list = newSinglePaymentService.listAnonyReceiptSubExcel(newSinglePaymentVO);

		if (list != null) {
			Map<String, Object> excelMap = new HashMap<String, Object>();
			for (int i = 0; i < list.size(); i++) {
				String billYymm = (String) list.get(i).get("BILL_YYMM");
				String billDate = (String) list.get(i).get("BILL_DATE");
				String paymentDate = (String) list.get(i).get("PAYMENT_DT");
				String rcptProcDate = (String) list.get(i).get("RECEIPT_PROCESSING_DT");

				String formatBillYymm = DateUtil.getLngDateFormat_yymm(billYymm, lngTyp);
				String formatBillDate = DateUtil.getLngDateFormat(billDate, lngTyp);
				String formatPaymentDate = DateUtil.getLngDateFormat(paymentDate, lngTyp);
				String formatRcptProcDate = DateUtil.getLngDateFormat(rcptProcDate, lngTyp);

				list.get(i).put("BILL_YYMM", formatBillYymm);
				list.get(i).put("BILL_DATE", formatBillDate);
				list.get(i).put("PAYMENT_DT", formatPaymentDate);
				list.get(i).put("RECEIPT_PROCESSING_DT", formatRcptProcDate);

			}
		}
		/*
		 * Header 작성
		 */
		List<ExcelColumnVO> columnList = new ArrayList<ExcelColumnVO>();
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M10.LAB00040"), "BILL_DATE", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M10.LAB00033"), "BILL_YYMM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(13, MessageUtil.getMessage("LAB.M09.LAB00137"), "BILL_AMT_BFR_ADJ", ExcelFormatType.NUMBER_WITH_COMMA));
		columnList.add(new ExcelColumnVO(13, MessageUtil.getMessage("LAB.M09.LAB00134"), "ADJ_AMT", ExcelFormatType.NUMBER_WITH_COMMA));
		columnList.add(new ExcelColumnVO(13, MessageUtil.getMessage("LAB.M10.LAB00031"), "BILL_AMT", ExcelFormatType.NUMBER_WITH_COMMA));
		columnList.add(new ExcelColumnVO(13, MessageUtil.getMessage("LAB.M07.LAB00237"), "RECEIPT_AMT", ExcelFormatType.NUMBER_WITH_COMMA));
		columnList.add(new ExcelColumnVO(13, MessageUtil.getMessage("LAB.M05.LAB00048"), "UNPAID_AMT", ExcelFormatType.NUMBER_WITH_COMMA));
		columnList.add(new ExcelColumnVO(13, MessageUtil.getMessage("LAB.M02.LAB00004"), "PAYMENT_DT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(13, MessageUtil.getMessage("LAB.M10.LAB00030"), "BILLING_CATEGORY", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(12, MessageUtil.getMessage("LAB.M08.LAB00043"), "FULL_PAY_YN", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(12, MessageUtil.getMessage("LAB.M07.LAB00212"), "SMALL_AMT_YN", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M07.LAB00243"), "RECEIPT_PROCESSING_DATE", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(13, MessageUtil.getMessage("LAB.M07.LAB00236"), "RECEIPT_TYPE", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(13, MessageUtil.getMessage("LAB.M07.LAB00194"), "OVER_PAYMENT_APLY", ExcelFormatType.NUMBER_WITH_COMMA));

		/*
		 * 데이터 세팅
		 */
		List<ExcelRowVO> rowList = new ArrayList<ExcelRowVO>();
		for (Map<String, Object> row : list) {
			ExcelRowVO rowVo = new ExcelRowVO();
			// Row
			Map<String, ExcelCellVO> rowMap = new HashMap<String, ExcelCellVO>();
			for (ExcelColumnVO col : columnList) {
				// Col 세팅
				ExcelCellVO cell = new ExcelCellVO();
				cell.setValue(row.get(col.getKey()));
				rowMap.put(col.getKey(), cell);
			}
			rowVo.setRowData(rowMap);
			rowList.add(rowVo);
		}

		/*
		 * Sheet 작성
		 */
		List<ExcelSheetVO> shList = new ArrayList<ExcelSheetVO>();
		ExcelSheetVO sh = new ExcelSheetVO();
		sh.setSheetName(MessageUtil.getMessage("LAB.M10.LAB00032"));
		sh.setDataList(rowList);
		sh.setTitleList(columnList);
		shList.add(sh);

		/*
		 * 파일작성
		 */
		ExcelFileVO file = new ExcelFileVO();
		file.setFileName(MessageUtil.getMessage("LAB.M10.LAB00032"));
		file.setSheetCount(1);
		file.setSheetList(shList);

		/*
		 * Model Set
		 */
		model.addAttribute("excelDataFile", file);

		return "excelXlsxView";
	}

	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getMyReceiptListAction
	 * 2. ClassName : getMyReceiptListController
	 * 3. Comment   : 건별입금 : 나의 입금내역 조회
	 * 4. 작성자    : pjh
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 * 
	 * @return void
	 * @param NewSinglePaymentVO
	 * @param model
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "getMyReceiptListAction", method = RequestMethod.POST)
	public void getMyReceiptListAction(NewSinglePaymentVO newSinglePaymentVO, Model model, HttpServletRequest request) throws Exception {

		System.out.println(">>>> print 1");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		newSinglePaymentVO.setLngTyp(lngTyp);
		newSinglePaymentVO.setUsrId(session.getUserId());

		List<NewSinglePaymentVO> receiptList = new ArrayList<NewSinglePaymentVO>();

		int count = 0;
		count = newSinglePaymentService.getMyReceiptListCount(newSinglePaymentVO);
		if (count > 0)
			receiptList = newSinglePaymentService.getMyReceiptList(newSinglePaymentVO);

		model.addAttribute("rows", receiptList); // 목록리스트
	}

	@RequestMapping(value = "getMyReceiptExcelAction", method = RequestMethod.POST)
	public String getMyReceiptExcelAction(Model model, NewSinglePaymentVO newSinglePaymentVO, HttpServletRequest request, String soId) {

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		newSinglePaymentVO.setLngTyp(lngTyp);
		newSinglePaymentVO.setUsrId(session.getUserId());

		List<Map<String, Object>> list = newSinglePaymentService.listMyReceiptExcel(soId, session.getUserId(), lngTyp);

		if (list != null) {
			Map<String, Object> excelMap = new HashMap<String, Object>();
			for (int i = 0; i < list.size(); i++) {
				String depositDate = (String) list.get(i).get("DEPOSIT_DT");
				String transferDate = (String) list.get(i).get("TRANSFER_DT");
				String rcptProcDate = (String) list.get(i).get("RECEIPT_PROCESSING_DATE");

				String formatBillYymm = DateUtil.getLngDateFormat(depositDate, lngTyp);
				String formatBillDate = DateUtil.getLngDateFormat(transferDate, lngTyp);
				String formatRcptProcDate = DateUtil.getLngDateFormat(rcptProcDate, lngTyp);

				list.get(i).put("DEPOSIT_DT", formatBillYymm);
				list.get(i).put("TRANSFER_DT", formatBillDate);
				list.get(i).put("RECEIPT_PROCESSING_DATE", formatRcptProcDate);

			}
		}

		/*
		 * Header 작성
		 */
		List<ExcelColumnVO> columnList = new ArrayList<ExcelColumnVO>();
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M08.LAB00173"), "DEPOSIT_DT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M02.LAB00006"), "PYM_ACNT_ID", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M02.LAB00018"), "PYM_ACNT_NM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M08.LAB00177"), "DEPOSIT_TYP_NM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M08.LAB00166"), "DEPOSIT_OPTION_NM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M08.LAB00169"), "FINANCIAL_INSTITUTION", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M08.LAB00168"), "DEPOSIT_AMT", ExcelFormatType.NUMBER_WITH_COMMA));
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M07.LAB00250"), "COMMISSION", ExcelFormatType.NUMBER_WITH_COMMA));
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M08.LAB00127"), "TRANSFER_DT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M07.LAB00242"), "RECEIVED", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M07.LAB00243"), "RECEIPT_PROCESSING_DATE", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M05.LAB00055"), "UNKNOWN_PAYMENT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(15, MessageUtil.getMessage("LAB.M06.LAB00093"), "REMARK", ExcelFormatType.STRING));

		/*
		 * 데이터 세팅
		 */
		List<ExcelRowVO> rowList = new ArrayList<ExcelRowVO>();
		for (Map<String, Object> row : list) {
			ExcelRowVO rowVo = new ExcelRowVO();
			// Row
			Map<String, ExcelCellVO> rowMap = new HashMap<String, ExcelCellVO>();
			for (ExcelColumnVO col : columnList) {
				// Col 세팅
				ExcelCellVO cell = new ExcelCellVO();
				cell.setValue(row.get(col.getKey()));
				rowMap.put(col.getKey(), cell);
			}
			rowVo.setRowData(rowMap);
			rowList.add(rowVo);
		}

		/*
		 * Sheet 작성
		 */
		List<ExcelSheetVO> shList = new ArrayList<ExcelSheetVO>();
		ExcelSheetVO sh = new ExcelSheetVO();
		sh.setSheetName(MessageUtil.getMessage("LAB.M02.LAB00001"));
		sh.setDataList(rowList);
		sh.setTitleList(columnList);
		shList.add(sh);

		/*
		 * 파일작성
		 */
		ExcelFileVO file = new ExcelFileVO();
		file.setFileName(MessageUtil.getMessage("LAB.M02.LAB00001"));
		file.setSheetCount(1);
		file.setSheetList(shList);

		/*
		 * Model Set
		 */
		model.addAttribute("excelDataFile", file);

		return "excelXlsxView";
	}

	@RequestMapping(value = "getCashGb", method = RequestMethod.POST) // 사용안함
	public String getCashGb(Model model, HttpServletRequest request, String grpCd) throws Exception {
		String lngTyp = (String) request.getSession().getAttribute("sessionLanguage");

		model.addAttribute("cashGb", commonDataService.getCommonCodeList("BL00068", lngTyp));

		return URL + "/newSinglePaymentList";
	}

	@RequestMapping(value = "getPgmExeSeqNo", method = RequestMethod.POST)
	public String getPgmExeSeqNo(Model model, NewSinglePaymentVO newSinglePaymentVO, HttpServletRequest request) throws Exception {

		String pgmExeSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.BL_PGM_EXE_SEQ_NO, 10);

		model.addAttribute("pgmExeSeqNo", pgmExeSeqNo); // 실행시퀀스
		return pgmExeSeqNo;

	}

	@RequestMapping(value = "getBillInfo", method = RequestMethod.POST)
	public void getBillInfo(NewSinglePaymentVO newSinglePaymentVO, Model model, HttpServletRequest request, String billSeqNo) throws Exception {

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		newSinglePaymentVO.setLngTyp(lngTyp);

		System.out.println("### new newSinglePaymentVO.getBillSeqNo : " + newSinglePaymentVO.getBillSeqNo());
		System.out.println("### new newSinglePaymentVO.getBillSeqNo 2 : " + billSeqNo);

		List<NewSinglePaymentVO> getBillList = new ArrayList<NewSinglePaymentVO>();
		int count = 0;
		count = newSinglePaymentService.getBillInfoCount(newSinglePaymentVO);
		if (count > 0)
			getBillList = newSinglePaymentService.getBillInfo(newSinglePaymentVO);

		model.addAttribute("billList", getBillList); // 목록리스트
	}

	@RequestMapping(value = "getPermitOrg", method = RequestMethod.POST) // 사용안함
	public void getPermitOrg(NewSinglePaymentVO newSinglePaymentVO, Model model, HttpServletRequest request) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		newSinglePaymentVO.setLngTyp(lngTyp);
		newSinglePaymentVO.setUsrId(session.getUserId());

		List<NewSinglePaymentVO> PermitOrgList = new ArrayList<NewSinglePaymentVO>();
		int count = 0;
		count = newSinglePaymentService.getPermitOrgCount(newSinglePaymentVO);
		if (count > 0)
			PermitOrgList = newSinglePaymentService.getPermitOrg(newSinglePaymentVO);

		model.addAttribute("permitOrgList", PermitOrgList); // 목록리스트
	}

	@RequestMapping(value = "getLoanAvlAmt", method = RequestMethod.POST) // 사용안함
	public void getLoanAvlAmt(NewSinglePaymentVO newSinglePaymentVO, Model model, HttpServletRequest request) throws Exception {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		newSinglePaymentVO.setLngTyp(lngTyp);
		newSinglePaymentVO.setUsrId(session.getUserId());

		List<NewSinglePaymentVO> LoanAvlAmtList = new ArrayList<NewSinglePaymentVO>();
		int count = 0;
		count = newSinglePaymentService.getLoanAvlAmtCount(newSinglePaymentVO);
		if (count > 0)
			LoanAvlAmtList = newSinglePaymentService.getLoanAvlAmt(newSinglePaymentVO);

		model.addAttribute("loanAvlAmtList", LoanAvlAmtList); // 목록리스트
	}

	
	
	
	
	
	
	
	@RequestMapping(value = "insertAction", method = RequestMethod.POST)
	public String insertDeposit(EachDeposit eachDeposit, HttpServletRequest request, Model model) {
		
		int result = newSinglePaymentService.insertAction(eachDeposit);		
		
		model.addAttribute("result", result);
		
		return  URL + "/ajax/newSinglePaymentList"; 
		

		
//		ModelMap modelMap = new ModelMap();
//
//		try {
//			newSinglePaymentService.insertDeposit(newSinglePaymentVO);
//			modelMap.addAttribute("success", true);
//		} catch (Exception e) {
//			modelMap.addAttribute("success", false);
//			modelMap.addAttribute("message", e.getMessage());
//		}
		
	}

}
