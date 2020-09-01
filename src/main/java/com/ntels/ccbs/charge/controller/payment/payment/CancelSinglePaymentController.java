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

import com.ntels.ccbs.charge.domain.payment.payment.CancelSinglePaymentVO;
import com.ntels.ccbs.charge.service.payment.payment.CancelSinglePaymentService;
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
@RequestMapping(value = "/charge/payment/payment/cancelSinglePayment")
public class CancelSinglePaymentController {
	
	private static String URL = "charge/payment/payment/cancelSinglePayment";
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private CancelSinglePaymentService cancelSinglePaymentService;
	
	@Autowired
	private SequenceService sequenceService;
	
	@RequestMapping(value = "cancelSinglePaymentList", method = RequestMethod.POST)
	public String cancelSinglePaymentList(Model model, CancelSinglePaymentVO cancelSinglePaymentVO, HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("dpstTp", commonDataService.getCommonCodeList("BL00067", lngTyp));	//입금구분
		
		return URL + "/cancelSinglePaymentList";
	}
	
	@RequestMapping(value = "getCaseByCancelListAction", method = RequestMethod.POST)
	public void getCaseByCancelListAction(CancelSinglePaymentVO cancelSinglePaymentVO, Model model, HttpServletRequest request) throws Exception {
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		cancelSinglePaymentVO.setLngTyp(lngTyp);
			
		List<CancelSinglePaymentVO> list = new ArrayList<CancelSinglePaymentVO>();
		int count = 0;
		
		count = cancelSinglePaymentService.getCaseByCancelListCount(cancelSinglePaymentVO);
		
		if (count > 0) list = cancelSinglePaymentService.getCaseByCancelList(cancelSinglePaymentVO);
		
		model.addAttribute("rows", list);	// 목록리스트
	}

	// 건별입금취소 가능 여부 체크
	@RequestMapping(value = "cancelCheck", method = RequestMethod.POST)
	public String cancelCheck(CancelSinglePaymentVO cancelSinglePaymentVO, HttpServletRequest request, Model model) {
		int result = cancelSinglePaymentService.getCancelCheck(cancelSinglePaymentVO.getDpstSeqNo()); // 1: 가능 , -1 불가능
		
		model.addAttribute("returnResult", result);

		return  URL + "/ajax/cancelSinglePaymentList"; 
	}

	@RequestMapping(value = "insertAction", method = RequestMethod.POST)
	public String insertAction(CancelSinglePaymentVO cancelSinglePaymentVO, HttpServletRequest request, Model model) {

		int result = cancelSinglePaymentService.insertAction(cancelSinglePaymentVO);
				
		model.addAttribute("result", result);

		return  URL + "/ajax/cancelSinglePaymentList"; 
	}
	
	
//	@RequestMapping(value = "getPgmExeSeqNo", method = RequestMethod.POST)
//	public String getPgmExeSeqNo(Model model, CancelSinglePaymentVO cancelSinglePaymentVO, HttpServletRequest request) throws Exception {
//		
//		String pgmExeSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.BL_PGM_EXE_SEQ_NO, 10);
//		
//		model.addAttribute("pgmExeSeqNo", pgmExeSeqNo);  //실행시퀀스
//		return pgmExeSeqNo;
//		
//	}
	
	
//	@RequestMapping(value = "getCaseByCancelListExcelAction", method = RequestMethod.POST)
//	public String getCaseByCancelListExcelAction(
//			 Model model 
//			,CancelSinglePaymentVO cancelSinglePaymentVO 
//			,HttpServletRequest request) {
//		
//		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
//		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
//		cancelSinglePaymentVO.setLngTyp(lngTyp);
//		
//		System.out.println(">> EXCEL << ");
//		System.out.println(">> cancelSinglePaymentVO.getsoId << " +cancelSinglePaymentVO.getSoId());
//		System.out.println(">> cancelSinglePaymentVO.getdpstfrom << "+cancelSinglePaymentVO.getDepositDtFrom());
//		System.out.println(">> cancelSinglePaymentVO.getdpstto << "+cancelSinglePaymentVO.getDepositDtTo());
//		System.out.println(">> cancelSinglePaymentVO.getpayprocyn << " +cancelSinglePaymentVO.getPayProcYn());
//		System.out.println(">> cancelSinglePaymentVO.getAcntcardno << "+cancelSinglePaymentVO.getAcntCardNo());
//		System.out.println(">> cancelSinglePaymentVO.getdpstopti << " +cancelSinglePaymentVO.getDepositOption());
//		System.out.println(">> cancelSinglePaymentVO.getRegId << " + cancelSinglePaymentVO.getRegId());
//		
//		List<Map<String,Object>> list = cancelSinglePaymentService.getCaseByCancelListExcel(cancelSinglePaymentVO, lngTyp);
//		
//		if(list != null) {
//			Map<String, Object> excelMap = new HashMap<String, Object>();
//			for(int i = 0; i < list.size(); i++) {
//				String paymentDate = (String) list.get(i).get("PAYMENT_DT");
//				String transferDate = (String) list.get(i).get("TRANSFER_DT");
//				String dpstProcDate = (String) list.get(i).get("DEPOSIT_PROCESSING_DT");
//				String rcptProcDate = (String) list.get(i).get("RECEIPT_PROCESSING_DT");
//				
//				String formatPaymentDate = DateUtil.getLngDateFormat(paymentDate, lngTyp);
//				String formatTransferDate = DateUtil.getLngDateFormat(transferDate, lngTyp);
//				String formatDpstProcDate = DateUtil.getLngDateFormat(dpstProcDate, lngTyp);
//				String formatRcptProcDate = DateUtil.getLngDateFormat(rcptProcDate, lngTyp);
//				
//				list.get(i).put("PAYMENT_DT", formatPaymentDate);
//				list.get(i).put("TRANSFER_DT", formatTransferDate);
//				list.get(i).put("DEPOSIT_PROCESSING_DT", formatDpstProcDate); 
//				list.get(i).put("RECEIPT_PROCESSING_DT", formatRcptProcDate); 
//				
//			}
//		}
//		
//		/*
//		 * Header 작성
//		 */
//		List<ExcelColumnVO> columnList = new ArrayList<ExcelColumnVO>();
//		columnList.add(new ExcelColumnVO(11, MessageUtil.getMessage("LAB.M03.LAB00084"), "REGISTANT_NM", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(27, MessageUtil.getMessage("LAB.M03.LAB00079"), "REGIST_DATE", ExcelFormatType.DATE));
//		columnList.add(new ExcelColumnVO(11, MessageUtil.getMessage("LAB.M02.LAB00006"), "PYM_ACNT_ID", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(11, MessageUtil.getMessage("LAB.M02.LAB00018"), "PYM_ACNT_NM", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M02.LAB00017"), "PYM_ACNT_METHOD_NM", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M08.LAB00178"), "DEPOSIT_TYP_NM", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M08.LAB00167"), "DEPOSIT_OPTION_NM", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M08.LAB00170"), "FINANCIAL_INSTITUTION_NM", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(12, MessageUtil.getMessage("LAB.M01.LAB00198"), "FINANCIAL_INSTITUTION_TYPE_NM", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(12, MessageUtil.getMessage("LAB.M01.LAB00042"), "ACNT_CARD_NO", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M08.LAB00171"), "DEPOSIT_AMT", ExcelFormatType.NUMBER_WITH_COMMA));
//		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M08.LAB00173"), "PAYMENT_DT", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M08.LAB00127"), "TRANSFER_DT", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M08.LAB00175"), "DEPOSIT_PROCESSING_DT", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(12, MessageUtil.getMessage("LAB.M05.LAB00055"), "UNKNOWN_PAYMENT", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(12, MessageUtil.getMessage("LAB.M07.LAB00191"), "OVER_PAYMENT_RECOVERED", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(12, MessageUtil.getMessage("LAB.M07.LAB00243"), "RECEIPT_PROCESSING_DT", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M07.LAB00242"), "PAY_PROC_YN", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M03.LAB00082"), "REG_ID", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M08.LAB00172"), "DEPOSIT_SEQ_NO", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M08.LAB00177"), "DEPOSIT_TYP", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M08.LAB00166"), "DEPOSIT_OPTION", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M08.LAB00169"), "FINANCIAL_INSTITUTION", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M01.LAB00197"), "INSTITUTION_TYPE", ExcelFormatType.STRING));
//		columnList.add(new ExcelColumnVO(10, MessageUtil.getMessage("LAB.M02.LAB00016"), "PYM_ACNT_METHOD", ExcelFormatType.STRING));
//		
//		/*
//		 * 데이터 세팅
//		 */
//		List<ExcelRowVO> rowList = new ArrayList<ExcelRowVO>();
//		for(Map<String,Object> row : list){
//			ExcelRowVO rowVo = new ExcelRowVO();
//			//Row
//			Map<String, ExcelCellVO> rowMap = new HashMap<String, ExcelCellVO>();
//			for(ExcelColumnVO col : columnList){
//				//Col 세팅
//				ExcelCellVO cell = new ExcelCellVO();
//				cell.setValue(row.get(col.getKey()));
//				rowMap.put(col.getKey(), cell);
//			}
//			rowVo.setRowData(rowMap);
//			rowList.add(rowVo);
//		}
//		
//		
//		/*
//		 * Sheet 작성
//		 */
//		List<ExcelSheetVO> shList = new ArrayList<ExcelSheetVO>();
//		ExcelSheetVO sh = new ExcelSheetVO();
//		sh.setSheetName(MessageUtil.getMessage("LAB.M01.LAB00022"));
//		sh.setDataList(rowList);
//		sh.setTitleList(columnList);
//		shList.add(sh);
//		
//		/*
//		 * 파일작성
//		 */
//		ExcelFileVO file = new ExcelFileVO();
//		file.setFileName(MessageUtil.getMessage("LAB.M01.LAB00022"));
//		file.setSheetCount(1);
//		file.setSheetList(shList);
//		
//		
//		/*
//		 * Model Set
//		 */
//		model.addAttribute("excelDataFile", file);
//		
//		return "excelXlsxView";
//	}
//	
//	
//	
//
//	
//	
//	
//	
//	
//	
//	@RequestMapping(value = "getRcptCnclCnt", method = RequestMethod.POST)
//	public void getRcptCnclCnt(
//			Model model, 
//			CancelSinglePaymentVO cancelSinglePaymentVO, 
//			HttpServletRequest request,
//			String depositSeqNo,
//			String depositOption) throws Exception {
//		cancelSinglePaymentVO.setDepositSeqNo(depositSeqNo);
//		cancelSinglePaymentVO.setDepositOption(depositOption);
//		List<CancelSinglePaymentVO> list = new ArrayList<CancelSinglePaymentVO>();
//		
//		list = cancelSinglePaymentService.getRcptCnclCnt(cancelSinglePaymentVO);
//		
//		model.addAttribute("rcptCnclCnt", list.size());
//	}
//	
//	@RequestMapping(value = "getPermitOrg", method = RequestMethod.POST)
//	public void getPermitOrg(
//			CancelSinglePaymentVO cancelSinglePaymentVO
//	      , Model model
//	      , HttpServletRequest request
//		  ) throws Exception {
//
//		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
//		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
//		cancelSinglePaymentVO.setLngTyp(lngTyp);
//		cancelSinglePaymentVO.setUsrId(session.getUserId());
//			
//		List<CancelSinglePaymentVO> PermitOrgList = new ArrayList<CancelSinglePaymentVO>();
//		int count = 0;
//		count = cancelSinglePaymentService.getPermitOrgCount(cancelSinglePaymentVO);
//		if (count > 0) PermitOrgList = cancelSinglePaymentService.getPermitOrg(cancelSinglePaymentVO);
//		
//		model.addAttribute("permitOrgList", PermitOrgList);	// 목록리스트
//	}
//	
//	@RequestMapping(value = "getLoanAvlAmt", method = RequestMethod.POST)
//	public void getLoanAvlAmt(
//			CancelSinglePaymentVO cancelSinglePaymentVO
//	      , Model model
//	      , HttpServletRequest request
//		  ) throws Exception {
//
//		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
//		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
//		cancelSinglePaymentVO.setLngTyp(lngTyp);
//		cancelSinglePaymentVO.setUsrId(session.getUserId());
//			
//		List<CancelSinglePaymentVO> LoanAvlAmtList = new ArrayList<CancelSinglePaymentVO>();
//		int count = 0;
//		count = cancelSinglePaymentService.getLoanAvlAmtCount(cancelSinglePaymentVO);
//		if (count > 0) LoanAvlAmtList = cancelSinglePaymentService.getLoanAvlAmt(cancelSinglePaymentVO);
//		
//		model.addAttribute("loanAvlAmtList", LoanAvlAmtList);	// 목록리스트
//	}
//	
//	
//
	
	
	
}
