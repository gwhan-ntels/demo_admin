package com.ntels.ccbs.customer.controller.contract.contract;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.crypto.AES256Cipher;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.customer.service.contract.contract.InvoiceDetailService;
//import com.ntels.cipher.LEACipher;
import com.ntels.nisf.util.message.MessageUtil;

/**
 * <PRE>
 * 1. ClassName: InvoiceDetailController
 * 2. FileName : InvoiceDetailController.java
 * 3. Package  : com.ntels.ccbs.customer.controller.contract.contract
 * 4. Comment  : 청구내역조회 팝업
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2017. 4. 26. 오후 3:51:29
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2017. 4. 26.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/customer/contract/contract/invoiceDetail")
public class InvoiceDetailController {
private static String URL = "customer/contract/contract/invoiceDetail";
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${invoice.path}")
	private String invoicePath;
	
	@Autowired 
	private InvoiceDetailService invoiceDetailService;
	
	public static final String ENC_KEY 	= "#Cy1C@QfC%Ng8j3mM!jyy5%51$0%#0Hq";
	
	/**
	 * <PRE>
	 * 1. MethodName: openInvoiceDetail
	 * 2. ClassName : InvoiceDetailController
	 * 3. Comment   : 청구내역조회 오픈
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 26. 오후 3:51:37
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param pymAcntId 납부고객ID
	 */
	@RequestMapping(value = "openInvoiceDetail", method = RequestMethod.POST)
	public String openInvoiceDetail(Model model
			,HttpServletRequest request
			,String soId
			,String custId
			,String ctrtId
			,String pymAcntId
			) {
		model.addAttribute("soId", soId);
		model.addAttribute("custId", custId);
		model.addAttribute("ctrtId", ctrtId);
		model.addAttribute("pymAcntId", pymAcntId);
		return URL + "/ajax/invoiceDetailPopup";
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName: getBillList
	 * 2. ClassName : InvoiceDetailController
	 * 3. Comment   : 청구내역조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 26. 오후 3:52:32
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param pymAcntId  납부계정ID
	 */
	@RequestMapping(value = "getBillListAction", method = RequestMethod.POST)
	public String getBillList(Model model, HttpServletRequest request, String soId, String custId, String ctrtId, String pymAcntId) {
		
		model.addAttribute("billList", invoiceDetailService.getBillList(soId, custId, ctrtId, pymAcntId));
		
		
		return URL + "/invoiceDetailPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getBillCtrtList
	 * 2. ClassName : InvoiceDetailController
	 * 3. Comment   : 계약별 청구내역 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 26. 오후 3:53:20
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param billSeqNo Billing Seq
	 *   @param billYymm 청구년월
	 *   @param billDt 청구일
	 *   @param pymAcntId 납부계정ID
	 */
	@RequestMapping(value = "getBillCtrtListAction", method = RequestMethod.POST)
	public String getBillCtrtList(Model model, HttpServletRequest request, String billSeqNo, String billYymm, String billDt, String pymAcntId) {
		
		model.addAttribute("billCtrtList", invoiceDetailService.getBillCtrtList(billSeqNo, billYymm, billDt, pymAcntId));
		
		
		return URL + "/invoiceDetailPopup";
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName: getBillCtrtDtlList
	 * 2. ClassName : InvoiceDetailController
	 * 3. Comment   : 계약별 청구내역 상세
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 26. 오후 5:46:18
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param billSeqNo Bill Seq
	 *   @param billYymm 청구년월
	 *   @param billDt 청구일자
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID 
	 *   @param pymAcntId 납부계정ID
	 */
	@RequestMapping(value = "getBillCtrtDtlListAction", method = RequestMethod.POST)
	public String getBillCtrtDtlList(Model model, HttpServletRequest request 
			, String billSeqNo
			, String billYymm
			, String billDt
			, String soId
			, String ctrtId
			, String pymAcntId) {
		
		model.addAttribute("billDtlList", invoiceDetailService.getBillCtrtDtlList(billSeqNo, billYymm, billDt, soId, ctrtId, pymAcntId));
		
		
		return URL + "/invoiceDetailPopup";
	}
	
	@RequestMapping(value = "getInvoiceDownloadCheckAction", method = RequestMethod.POST)
	public String getInvoiceDownloadCheck(Model model, HttpServletRequest request 
			, String billSeqNo) {
		
		
		if(StringUtils.isEmpty(billSeqNo)){
			String[] arg = {MessageUtil.getMessage("LAB.M10.LAB00039")};
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		try{
			StringBuffer fileName = new StringBuffer();
			fileName.append(invoicePath);
			fileName.append("/");
			fileName.append(billSeqNo.substring(0, 2));
			fileName.append("/");
			fileName.append(billSeqNo.substring(2, 4));
			fileName.append("/");
			fileName.append(billSeqNo);
			fileName.append(".pdf");
			
			logger.debug("Invoice File : " + fileName);
			File file = new File(fileName.toString()); 
			
			if(file == null || file.exists() == false){
				throw new ServiceException("MSG.M03.MSG00004");
				
			}
			
		//	model.addAttribute("billSeqNo", LEACipher.ECB_Encode(billSeqNo, ENC_KEY));
		}catch(ServiceException e){
			throw e;	
		}catch(Exception e){
			throw new ServiceException("MSG.M08.MSG00022");
		}
		
		
		
		return URL + "/invoiceDetailPopup";
	}
	
	
	@RequestMapping(value = "getInvoiceDownloadAction")
	public void getInvoiceDownload(Model model, HttpServletRequest request ,HttpServletResponse response
			, String billSeqNo) {
		
		
		if(StringUtils.isEmpty(billSeqNo)){
			String[] arg = {MessageUtil.getMessage("LAB.M10.LAB00039")};
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		
		FileInputStream fileInputStream = null;
		ServletOutputStream servletOutputStream = null;
		try{
			String seqNo = billSeqNo;
			StringBuffer fileName = new StringBuffer();
			fileName.append(invoicePath);
			fileName.append("/");
			fileName.append(seqNo.substring(0, 2));
			fileName.append("/");
			fileName.append(seqNo.substring(2, 4));
			fileName.append("/");
			fileName.append(seqNo);
			fileName.append(".pdf");
			
			StringBuffer pdfName = new StringBuffer();
			pdfName.append(seqNo);
			pdfName.append(".pdf");
			
			File file = new File(fileName.toString()); 
			
			if(file == null || file.exists() == false){
				throw new ServiceException("MSG.M03.MSG00004");
				
			}
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Transfer-Encoding", "binary;");
			response.setHeader("Pragma", "no-cache;");
			response.setHeader("Expires", "-1;");
			
			String header = getBrowser(request);
			if (header.contains("MSIE")) {
			       String docName = URLEncoder.encode(pdfName.toString(),"UTF-8").replaceAll("\\+", "%20");
			       response.setHeader("Content-Disposition", "attachment;filename=" + docName + ";");
			} else if (header.contains("Firefox")) {
			       String docName = new String(pdfName.toString().getBytes("UTF-8"), "ISO-8859-1");
			       response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
			} else if (header.contains("Opera")) {
			       String docName = new String(pdfName.toString().getBytes("UTF-8"), "ISO-8859-1");
			       response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
			} else if (header.contains("Chrome")) {
			       String docName = new String(pdfName.toString().getBytes("UTF-8"), "ISO-8859-1");
			       response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
			}
			
			fileInputStream = new FileInputStream(file);
			servletOutputStream = response.getOutputStream();
			
			byte b [] = new byte[1024];
			int data = 0;
			
			while((data=(fileInputStream.read(b, 0, b.length))) != -1)
			{
				servletOutputStream.write(b, 0, data);
			}
			
			servletOutputStream.flush();
			servletOutputStream.close();
			fileInputStream.close();
		}catch(ServiceException e){
			throw e;	
		}catch(Exception e){
			throw new ServiceException("MSG.M07.MSG00126");
			
		}finally{
			if(servletOutputStream != null){
				try {
					servletOutputStream.close();
				} catch (IOException e) {
				}
				servletOutputStream = null;
			}
			
			if(fileInputStream != null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
				}
				fileInputStream = null;
			}
		}
	}
	private String getBrowser(HttpServletRequest request) {
        String header =request.getHeader("User-Agent");
        if (header.contains("MSIE")) {
               return "MSIE";
        } else if(header.contains("Chrome")) {
               return "Chrome";
        } else if(header.contains("Opera")) {
               return "Opera";
        }
        return "Firefox";
  }

}