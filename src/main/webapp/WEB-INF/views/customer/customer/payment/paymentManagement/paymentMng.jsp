<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<style type="text/css">table.ui-datepicker-calendar { display:none; }</style>
<script type="text/javascript">

$(document).ready(function(){
	
	//화면 초기화 처리
	pageInit();
	
	//그리드 처리
	$("#paymentGrid").jqGrid({
		url : '/customer/customer/payment/paymentManagement/getPymAcntInfoListAction.json',
		datatype : 'json',
		mtype: 'POST',
		postData : {
			soId : $("#condSoId").val(),
			custId : $("#condCustId").val(),
			isUnmaskYn : $("#isUnmaskYn").val()
		},
		colModel: [
			{ label: 'soId' , name: 'soId', hidden:true,width : 0},
			{ label: 'custId' , name: 'custId', hidden:true,width : 0},
			{ label: 'custNm' , name: 'custNm', hidden:true,width : 0},
			{ label: 'zipCd' , name: 'zipCd', hidden:true,width : 0},
			{ label: 'baseAddr' , name: 'baseAddr', hidden:true,width : 0},
			{ label: 'addrDtlAsMask' , name: 'addrDtlAsMask', hidden:true,width : 0},
			{ label: 'emlAsMask' , name: 'emlAsMask', hidden:true,width : 0},
			{ label: 'telNo' , name: 'telNo', hidden:true,width : 0,formatter:telNoFormatter},
			{ label: 'mtelNoAsMask' , name: 'mtelNoAsMask', hidden:true,width : 0,formatter:telNoFormatter},
			{ label: 'faxNo' , name: 'faxNo', hidden:true,width : 0,formatter:telNoFormatter},
			{ label: 'pymMthd' , name: 'pymMthd', hidden:true,width : 0},
			{ label: 'pmcResn' , name: 'pmcResn', hidden:true,width : 0},
			{ label: 'billMdmGiroYn' , name: 'billMdmGiroYn', hidden:true,width : 0},
			{ label: 'billMdmEmlYn' , name: 'billMdmEmlYn', hidden:true,width : 0},
			{ label: 'billMdmSmsYn' , name: 'billMdmSmsYn', hidden:true,width : 0},
			{ label: 'bnkCd' , name: 'bnkCd', hidden:true,width : 0},
			{ label: 'acntOwnerNm' , name: 'acntOwnerNm', hidden:true,width : 0},
			{ label: 'acntNoAsMask' , name: 'acntNoAsMask', hidden:true,width : 0},
			{ label: 'cdtcdExpDt' , name: 'cdtcdExpDt', hidden:true, formatter:stringTypeFormatterYYYYMM,width : 0},
			{ label: 'pymDt' , name: 'pymDt', hidden:true,width : 0},
			{ label: 'cmsCl' , name: 'cmsCl', hidden:true,width : 0},
			{ label: 'tbrFlg' , name: 'tbrFlg', hidden:true,width : 0},
			{ label: 'arrsNobillYn' , name: 'arrsNobillYn', hidden:true,width : 0},
			{ label: 'useAcntNmYn' , name: 'useAcntNmYn', hidden:true,width : 0},
			{ label: 'rcptId' , name: 'rcptId', hidden:true,width : 0},
			{ label: 'billCyclCd' , name: 'billCyclCd', hidden:true,width : 0},
			{ label: 'mstBnkCd' , name: 'mstBnkCd', hidden:true,width : 0},
			{ label: 'vrAcntNo' , name: 'vrAcntNo', hidden:true,width : 0},
			{ label: 'orgId' , name: 'orgId', hidden:true,width : 0},
			{ label: 'orgNm' , name: 'orgNm', hidden:true,width : 0},
			{ label: 'usrId' , name: 'usrId', hidden:true,width : 0},
			{ label: 'usrNm' , name: 'usrNm', hidden:true,width : 0},
			{ label: 'extId' , name: 'extId', hidden:true,width : 0},
			{ label: 'regrId' , name: 'regrId', hidden:true,width : 0},
			{ label: 'chgrId' , name: 'chgrId', hidden:true,width : 0},
			{ label: 'chgrOrgId' , name: 'chgrOrgId', hidden:true,width : 0},
			{ label: 'chgrOrgNm' , name: 'chgrOrgNm', hidden:true,width : 0},
		    { label: '<spring:message code="LAB.M02.LAB00006"/>', name: 'pymAcntId', width : 150, align:"center"},
		    { label: '<spring:message code="LAB.M02.LAB00008"/>', name: 'acntNm', width : 200},
		    { label: '<spring:message code="LAB.M02.LAB00016"/>', name: 'pymMthdNm', width : 150}, 
		    { label: '<spring:message code="LAB.M07.LAB00256"/>', name: 'chgrNm', width : 150},           
		    { label: '<spring:message code="LAB.M07.LAB00254"/>', name: 'chgDate', formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center"},    
		    { label: '<spring:message code="LAB.M03.LAB00082"/>', name: 'regrNm', width : 150},
		    { label: '<spring:message code="LAB.M03.LAB00080"/>', name: 'regDate',  formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center"},
		],
		viewrecords: true,
		/* autowidth: true, */
		shrinkToFit:false,
		height: 120,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "pymAcntInfoList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 5,
        pager: "#paymentGridPager",
        onCellSelect : function(rowid, index, contents, event){
        	setSelectedData(rowid);
        },
       	loadComplete : function () {
       		
       		//입력부 초기화
        	inputInit();
        	
	   	    //버튼 컨트롤
  	      	btnCtrl('I');
	   	    
  	      	$("#paymentGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#paymentGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});
	
	
	
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#paymentGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	
	//달력처리
	if($(".month-picker").length > 0){
		if(lng == 'ko'){
			format = 'yy-mm';
		}else if (lng == 'en'){
			format = 'mm/yy';
		}
		 $('.month-picker').datepicker( {
		        changeMonth: true,
		        changeYear: true,
		        showButtonPanel: true,
		        dateFormat: format,
		        onClose: function(dateText, inst) {
		            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
		            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
		            $(this).datepicker('setDate', new Date(year, month, 1));
		        }
		    }
		 );
	}
	
	
	//조건의 SO select 변경 이벤트
    $('#condSo').selectmenu({
        change: function() {
        	$("#condSoId").val('');
        	$("#condCustId").val('');
        }
    });
	
	//고객명 키 이벤트
    $( "#condCustNm" ).keypress(function(event) {
   		if(event.keyCode == 13){
   			$('#isUnmaskYn').val('N');
   			$("#condCustId").val('');
   			searchCustInfo();
   		}
   	});
	
  	//초기화 버튼 이벤트
   	$('#initBtn').on('click',function (e) {
	   		if($("#initBtn").hasClass('not-active')){
				return;
			}
    		initBtn();
		}
    );
  	
	//조회 버튼 이벤트
    $('#searchPymBtn').on('click',function (e) {
	    	if($("#searchPymBtn").hasClass('not-active')){
				return;
			}
	    	$('#isUnmaskYn').val('N');
	    	$("#condCustId").val('');
    		searchCustInfo();
		}
    );
  	//납부방법 변경 이벤트
    $('#inputPymMthd').selectmenu({
        change: function() {
        	chagedPymMthd();
        }
    });
  	
  	//신규등록 버튼 이벤트
    $('#newBtn').on('click',function (e) {
    	if($("#newBtn").hasClass('not-active')){
			return;
		}
    		insertNewPymAcnt();
		}
    );
  	
  	//변경 버튼 이벤트
    $('#updateBtn').on('click',function (e) {
    	if($("#updateBtn").hasClass('not-active')){
			return;
		}
    		updatePymAcnt();
		}
    );
  	
  	//납부정보 변경 이력 버튼 이벤트
  	$('#acntChngHistBtn').on('click',function (e) {
    	if($("#acntChngHistBtn").hasClass('not-active')){
			return;
		}
  		// 변경된 값만 파라미터로 넘김
  		var rowid  = $("#paymentGrid").jqGrid('getGridParam', 'selrow');
  		var data = $("#paymentGrid").getRowData(rowid);
  		if(rowid ==null){
  			alert('<spring:message code="MSG.M03.MSG00015"/>');
  			return;
  		}
  		
	  	$.ajax({
				type : "post",
				url : '/customer/customer/payment/paymentManagement/openPymChngHistPopup.ajax',
				data : {
					soId : data.soId,
					pymAcntId : data.pymAcntId,
					isUnmaskYn : $("#isUnmaskYn").val()
				},
				async: true,
				success : function(data) {
					var html = data;
					$("#popup_dialog").html(html);
				},		
				complete : function(){
					wrapWindowByMask(); // 팝업 오픈
				}
			}); 
		}
    );
  	
  	
  	//고객정보 복사 이벤트
    $('#copyCustInfo').on('click',function (e) {
    	if($("#copyCustInfo").hasClass('not-active')){
			return;
		}
    		$('#inputPymAcntNm').val($('#condCustNmB').val());
    		$('#inputPostNo').val($('#condZipCd').val());
    		$('#inputBaseAddr').val($('#condBaseAddr').val());
    		$('#inputDtlAddr').val($('#condAddrDtl').val());
    		$('#inputTelNo').val(telNoAutoFormatter($('#condTelNo').val()));
    		$('#inputFaxNo').val(telNoAutoFormatter($('#condFaxNo').val()));
    		$('#inputCellPhoneNo').val(telNoAutoFormatter($('#condMtelNo').val()));
    		$('#inputEmail').val($('#condEml').val());
		}
    );
  	
  	//전화번호 keyup이벤트
  	$('#inputTelNo').keyup(function(){
  		$('#inputTelNo').val(telNoAutoFormatter($('#inputTelNo').val()));
  		$('#inputTelNo').val(getMaxStr($('#inputTelNo').val(), 20));
  		}
	);
  	
  	//팩스번호 keyup이벤트
  	$('#inputFaxNo').keyup(function(){
  		$('#inputFaxNo').val(telNoAutoFormatter($('#inputFaxNo').val()));
  		$('#inputFaxNo').val(getMaxStr($('#inputFaxNo').val(), 20));
  		}
	);
  	
  	//휴대번호 keyup이벤트
  	$('#inputCellPhoneNo').keyup(function(){
  		$('#inputCellPhoneNo').val(telNoAutoFormatter($('#inputCellPhoneNo').val()));
  		$('#inputCellPhoneNo').val(getMaxStr($('#inputCellPhoneNo').val(), 20));
  		}
	);
  	
  	//납부계정명 keyup 이벤트
  	$('#inputPymAcntNm').keyup(function(){
	  		var str = getMaxStr($('#inputPymAcntNm').val(), 150);
	  		if(str != $('#inputPymAcntNm').val()){
	  			$('#inputPymAcntNm').val(str);
	  		}
  		}
	);
  	
  	//Email keyup 이벤트
  	$('#inputEmail').keyup(function(){
	  		var str = getMaxStr($('#inputEmail').val(), 50);
	  		if(str != $('#inputEmail').val()){
	  			$('#inputEmail').val(str);
	  		}
	  		
	  		
  		}
	);
 	// 고객조회
	$('#btnCustSearch').on('click',function (e) {
			if($("#btnCustSearch").hasClass('not-active')){
				return;
			}
			openCustSearchPopup();	
		}
	);
 	
	  //우편번호 keyup 이벤트
  	$('#inputPostNo').keyup(function(){
  		if (!(event.keyCode >=37 && event.keyCode<=40)) {
	        var inputVal = $(this).val();
	        $(this).val(inputVal.replace(/[^a-z0-9]/gi,''));
	     }
  		
  		var str = getMaxStr($('#inputPostNo').val(), 6);
  		if(str != $('#inputPostNo').val()){
  			$('#inputPostNo').val(str);
  		}
  	});

  	//주소변경 Popup
    $('#searchAddrBtn').on('click',function (e) {
    	if($("#searchAddrBtn").hasClass('not-active')){
			return;
		}
    	var parameter = new Object();
    	parameter.zipCd = 'inputPostNo';
    	parameter.baseAddr = 'inputBaseAddr';
    	parameter.addrDtl = 'inputDtlAddr';
			$.ajax({
				type : "post",
				url : '/system/common/common/postMng/postPopup.ajax',
				data : parameter,
				async: true,
				success : function(data) {
					var html = data;
					$("#popup_dialog").html(html);
					
				},		
				complete : function(){
					wrapWindowByMask(); // 팝업 오픈
				}
			}); 
		}
    );

});


/*
 * 화면 초기화 함수
 */
function pageInit(){
	
	//검색정보 초기화
	//$("#condSoId").val('');
	$("#condCustId").val('');
	/* $("#condCustNm").val('');
	$("#condZipCd").val('');
	$("#condBaseAddr").val('');
	$("#condAddrDtl").val('');
	$("#condTelNo").val('');
	$("#condFaxNo").val('');
	$("#condMtelNo").val('');
	$("#condEml").val(''); */
	/* $('#condSo').val('SEL');
	$('#condSo').selectmenu("refresh"); */
	$("#paymentGrid").clearGridData();
	
	//입력부 초기화
	inputInit();
	
	//버튼 컨트롤
	btnCtrl('I');
}

/*
 * 입력부 초기화 함수
 */
function inputInit(){
 	//납부계정정보 Disable처리
	$("#pymAcntBasicInfo input:text").val('');
	$("#pymAcntBasicInfo input:text").addClass('not-active');
	$("#pymAcntBasicInfo input:text").attr('disabled', true);
	
	$('#inputSoId').val('SEL');
	$('#inputSoId').selectmenu("refresh");
	$('#inputPymMthd').val('SEL');
	$('#inputPymMthd').selectmenu("refresh");
	$('#inputchgResn').val('SEL');
	$('#inputchgResn').selectmenu("refresh");
	$('#inputBillCyclCd').val('SEL');
	$('#inputBillCyclCd').selectmenu("refresh");
	$("#pymAcntBasicInfo select").selectmenu("disable");
	
	$("#pymAcntBasicInfo input:checkbox").removeAttr("checked");
	$("#pymAcntBasicInfo input:checkbox").addClass('not-active');
	$("#pymAcntBasicInfo input:checkbox").attr('disabled',true);
	$("#pymAcntBasicInfo label").addClass('not-active');
	$("#pymAcntBasicInfo label").attr('disabled',true);

	//청구서 정보 Disable 처리 invoiceInfo
	$("#invoiceInfo input:text").val('');
	$("#invoiceInfo input:text").addClass('not-active');
	$("#invoiceInfo input:text").attr('disabled', true);
	
	$('#inputState').val('SEL');
	$('#inputState').selectmenu("refresh");
	$('#inputPaperInvoice').val('SEL');
	$('#inputPaperInvoice').selectmenu("refresh");
	$('#inputEmailInvoice').val('SEL');
	$('#inputEmailInvoice').selectmenu("refresh");
	$('#inputSmsInvoice').val('SEL');
	$('#inputSmsInvoice').selectmenu("refresh");
	$("#invoiceInfo select").selectmenu("disable");
	
	//카드/은행정보 Disable 처리
	
	$("#cardRegInfo input:text").val('');
	$("#cardRegInfo input:text").addClass('not-active');
	$("#cardRegInfo input:text").attr('disabled', true);
	
	$('#inputCardBankCd').val('SEL');
	$('#inputCardBankCd').selectmenu("refresh");
	$("#cardRegInfo select").selectmenu("disable");
	
	$("#cardRegInfo").hide();
	
	btnDisable('authBtn');
	
	//등록정보 초기화
	$("#regInfo input:text").val('');
}

/*
 * 데이터 선택 이벤트 처리
 */
function setSelectedData(rowId){
	//기본정보 활성화 처리
	$('#inputPymAcntNm').removeClass('not-active');
	$("#inputPymAcntNm").removeAttr('disabled');
	$('#inputTelNo').removeClass('not-active');
	$("#inputTelNo").removeAttr('disabled');
	$('#inputFaxNo').removeClass('not-active');
	$("#inputFaxNo").removeAttr('disabled');
	$('#inputEmail').removeClass('not-active');
	$("#inputEmail").removeAttr('disabled');
	$('#inputCellPhoneNo').removeClass('not-active');
	$("#inputCellPhoneNo").removeAttr('disabled');

	$("#pymAcntBasicInfo input:checkbox").removeClass('not-active');
	$("#pymAcntBasicInfo input:checkbox").removeAttr('disabled');
	$("#pymAcntBasicInfo label").removeClass('not-active');
	$("#pymAcntBasicInfo label").removeAttr('disabled');
	
	$("#inputPymMthd").selectmenu("enable");
	$("#inputchgResn").selectmenu("enable");
	$("#inputBillCyclCd").selectmenu("enable");
	
	//청구서 정보 활성화 처리
	$("#invoiceInfo input:text").removeClass('not-active');
	$("#invoiceInfo input:text").removeAttr('disabled');
	$("#inputPostNo").addClass('not-active');
	$("#inputPostNo").attr('disabled', true);
	$("#inputBaseAddr").addClass('not-active');
	$("#inputBaseAddr").attr('disabled', true);
	
	$("#invoiceInfo select").selectmenu('enable');
	
	//카드/은행정보 활성화 처리
	$("#cardRegInfo input:text").removeClass('not-active');
	$("#cardRegInfo input:text").removeAttr('disabled');
	$("#cardRegInfo select").selectmenu('enable');
	
	var data = $("#paymentGrid").getRowData(rowId);
	$("#inputPymAcntId").val(data.pymAcntId);
	$("#inputSoId").val(data.soId);
	$('#inputSoId').selectmenu("refresh");
	$('#inputCustNm').val(data.custNm);
	$('#inputPymAcntNm').val(data.acntNm);
	$('#inputPymMthd').val(data.pymMthd);
	$('#inputPymMthd').selectmenu("refresh");
	$('#inputchgResn').val(data.chgResn);
	$('#inputchgResn').selectmenu("refresh");
	$('#inputTelNo').val(data.telNo);
	$('#inputFaxNo').val(data.faxNo);
	$('#inputchgResn').val(data.pmcResn);
	$('#inputchgResn').selectmenu("refresh");
	$('#inputCellPhoneNo').val(data.mtelNoAsMask);
	$('#inputEmail').val(data.emlAsMask);
	$('#inputPrintPymNm').prop("checked",data.useAcntNmYn == 'Y' ? true : false);
	$('#inputPostNo').val(data.zipCd);
	$('#inputBaseAddr').val(data.baseAddr);
	$('#inputDtlAddr').val(data.addrDtlAsMask);
	$('#inputBillCyclCd').val(data.billCyclCd);
	$('#inputBillCyclCd').selectmenu("refresh");
	$('#inputPaperInvoice').val(data.billMdmGiroYn);
	$('#inputPaperInvoice').selectmenu("refresh");
	$('#inputEmailInvoice').val(data.billMdmEmlYn);
	$('#inputEmailInvoice').selectmenu("refresh");
	$('#inputSmsInvoice').val(data.billMdmSmsYn);
	$('#inputSmsInvoice').selectmenu("refresh");
	
	//납부방법변경 이벤트 호출
	chagedPymMthd();
	
	
	$('#inputCardBankCd').val(data.bnkCd);
	$('#inputCardBankCd').selectmenu("refresh");
	$('#inputCardBankOwnNo').val(data.acntNoAsMask);
	$('#inputCardBankOwnNm').val(data.acntOwnerNm);
	$('#inputExp').val(data.cdtcdExpDt);
	
	$('#inputRegrNm').val(getNameAndId(data.usrId, data.usrNm));
	$('#inputRegrOrgNm').val(getNameAndId(data.orgId, data.orgNm));
	$('#inputRegrDt').val(data.regDate);
	$('#inputChgrNm').val(getNameAndId(data.chgrId, data.chgrNm));
	$('#inputChgrOrgNm').val(getNameAndId(data.chgrOrgId, data.chgrOrgNm));
	$('#inputChgrDt').val(data.chgDate);
	
	//버튼 컨트롤 호출
	btnCtrl('U');
}


/*
 * 명칭 포맷팅
 */
function getNameAndId(id, name){
	if(name == '' || name == null){
		return id;
	}else{
		return name + '(' + id + ')'; 
	}
	 
}


/*
 * 납부방법 변경 처리
 */
function chagedPymMthd(){
	if($('#inputPymMthd').val() == '02'){
        $('#inputCardBankCd').each( function() {
            $('#inputCardBankCd option:gt(0)').remove();
        });
		<c:forEach items='${bankCodeList}' var='bankCode' varStatus='status'>
		 	var str = '<option value="${bankCode.commonCd}">${bankCode.commonCdNm}</option>';
			$('#inputCardBankCd').append(str);
		</c:forEach>
		$("#inputExp").addClass('not-active');
		$("#inputExp").attr('disabled',true);
		$("#btnCal").addClass('not-active');
		$("#btnCal").attr('disabled',true);
		$("#cardRegInfo").show();
	}else if($('#inputPymMthd').val() == '03'){
        $('#inputCardBankCd').each( function() {
        	$('#inputCardBankCd option:gt(0)').remove();
        });
		<c:forEach items="${cardCodeList}" var="cardCode" varStatus="status">
		 	var str = '<option value="${cardCode.commonCd}">${cardCode.commonCdNm}</option>';
		 	$('#inputCardBankCd').append(str);
		</c:forEach>

		$("#inputExp").removeClass('not-active');
		$("#inputExp").removeAttr('disabled');
		$("#btnCal").removeClass('not-active');
		$("#btnCal").removeAttr('disabled');
		
        $("#cardRegInfo").show();
	}else{
        $('#inputCardBankCd').each( function() {
        	$('#inputCardBankCd option:gt(0)').remove();
        });
        $("#cardRegInfo").hide();
	}
	$('#inputCardBankCd').val('SEL');
	$('#inputCardBankCd').selectmenu("refresh");
	$('#inputCardBankOwnNm').val('');
	$('#inputCardBankOwnNo').val('');
	$('#inputExp').val('');	
}

/*
 * 초기화 버튼 이벤트
 */
function initBtn(){
	var soId = $('#condSoId').val();
	var custId = $('#condCustId').val();
	
	if(soId == '' || custId == ''){
		alert('<spring:message code="MSG.M01.MSG00015"/>');
		return;
	}
	
	//입력정보 초기화
	inputInit();
	
	//고객정보 세팅
	$("#inputSoId").val($("#condSoId").val());
	$('#inputSoId').selectmenu("refresh");
	$('#inputCustNm').val($('#condCustNmB').val());
	$('#inputPymAcntId').val('');
	
	//기본정보 활성화 처리
	$('#inputPymAcntNm').removeClass('not-active');
	$("#inputPymAcntNm").removeAttr('disabled');
	$('#inputPymAcntNm').focus();
	$('#inputTelNo').removeClass('not-active');
	$("#inputTelNo").removeAttr('disabled');
	$('#inputFaxNo').removeClass('not-active');
	$("#inputFaxNo").removeAttr('disabled');
	$('#inputEmail').removeClass('not-active');
	$("#inputEmail").removeAttr('disabled');
	$('#inputCellPhoneNo').removeClass('not-active');
	$("#inputCellPhoneNo").removeAttr('disabled');

	$("#pymAcntBasicInfo input:checkbox").removeClass('not-active');
	$("#pymAcntBasicInfo input:checkbox").removeAttr('disabled');
	$("#pymAcntBasicInfo label").removeClass('not-active');
	$("#pymAcntBasicInfo label").removeAttr('disabled');
	
	
	$("#inputPymMthd").selectmenu("enable");
	$("#inputchgResn").selectmenu("enable");
	$("#inputBillCyclCd").selectmenu("enable");
	
	// 청구서 정보 활성화 처리
	$("#invoiceInfo input:text").removeClass('not-active');
	$("#invoiceInfo input:text").removeAttr('disabled');
	$("#invoiceInfo select").selectmenu("enable");
	$("#inputPostNo").addClass('not-active');
	$("#inputPostNo").attr('disabled', true);
	$("#inputBaseAddr").addClass('not-active');
	$("#inputBaseAddr").attr('disabled', true);
	
	// 카드은행정보 활성화 처리
	$("#cardRegInfo input:text").removeClass('not-active');
	$("#cardRegInfo input:text").removeAttr('disabled');
	$("#cardRegInfo select").selectmenu("enable");
	
	//버튼 컨트롤
	btnCtrl('N');
	$("#paymentGrid").jqGrid("resetSelection");
}

/*
 * 버튼처리
 */
function btnCtrl(mode){
	//공통 버튼 처리
	btnDisable('funcBtn a');
	btnDisable('copyCustInfo');
	btnDisable('commonBtn a');
	btnDisable('acntChngHistBtn'); //나중에지우기
	if(mode == 'I'){
		//고객정보 존재시에 초기화 버튼 활성화
		if($("#condSoId").val() != '' && $("#condCustId").val() != ''){
			btnEnable('initBtn');
		} 
	}else if(mode =='N'){
		btnEnable('initBtn');
		btnEnable('newBtn');
		btnEnable('copyCustInfo');
		
	}else if(mode =='U'){
		btnEnable('initBtn');
		btnEnable('updateBtn');
		btnEnable('deleteBtn');
		btnEnable('acntChngHistBtn');
	}
}
/*
 * 고객정보조회
 */
function searchCustInfo(){
	
	var checkR = "<c:out value='${menuAuthR}'/>"; 
	if(checkR == 'N') return;
	
	var soId = $('#condSo').val();
	var custNm = $('#condCustNm').val();
	var custId = $('#condCustId').val();
	var isUnmaskYn = $('#isUnmaskYn').val();
	
	
	if(soId == ''){
		alert('<spring:message code="MSG.M07.MSG00002"/>');
		return;
	}else if(custNm == ''|| custNm == null){
		alert('<spring:message code="MSG.M01.MSG00017"/>');
		return;
	}
	var url = '/customer/customer/payment/paymentManagement/getPymCustInfoListAction.json';
	
	$('#condCustNmB').val($('#condCustNm').val());
	
	$.ajax({
          url:url,
          type:'POST',
          data : {
          	soId : soId,
          	custId : custId,
          	custNm : custNm,
            isUnmaskYn : isUnmaskYn
          	},
          dataType: 'json',
          success: function(data){
          	if(data.custListCnt == '0'){
          		alert('<spring:message code="MSG.M09.MSG00039"/>');	
          		
          	}else if(data.custListCnt == 1){
          		$('#condSo').val(data.custList[0].so_id);
          		$('#condSo').selectmenu("refresh");
          		$('#condSoId').val(data.custList[0].so_id);
            	$('#condCustId').val(data.custList[0].cust_id);
            	$('#condZipCd').val(data.custList[0].zip_cd);
            	$('#condBaseAddr').val(data.custList[0].base_addr);
            	$('#condAddrDtl').val(data.custList[0].addr_dtl);
            	$('#condTelNo').val(data.custList[0].tel_no);
            	$('#condFaxNo').val(data.custList[0].fax_no);
            	$('#condMtelNo').val(data.custList[0].mtel_no);
            	$('#condEml').val(data.custList[0].eml);
            	
          		$("#paymentGrid").clearGridData();
 	        		//입력부 초기화
  	   	      	inputInit();
  	   	      	
  	   	      	//버튼 컨트롤
  	   	      	btnCtrl('I');
  	   	      	
          		$("#paymentGrid").setGridParam({
	   	   	        postData : {
	   	   				soId : $("#condSoId").val(),
	   	   				custId : $("#condCustId").val(),
	   	   				isUnmaskYn : $("#isUnmaskYn").val()
	   	   			}
          		});
  	   	      	
 	          	$("#paymentGrid").trigger("reloadGrid"); 
          	}else{
          		//다수 존재시 팝업호출
          		openCustSearchPopup();
          	}
          	
          },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		if(err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
     			alert(err.responseJSON.exceptionMsg);
     		}else{
     			alert('<spring:message code="MSG.M10.MSG00005"/>');	
     		}
       	}
      });
}


/*
 * 고객조회팝업
 */
function openCustSearchPopup(){
	
	$.ajax({
		type : "post",
		url : '/system/common/common/customerSearch/customerSearchPopup.ajax',
		data : {
			 inputSoId : $('#condSo').val()   //input SO Id
			,inputCustNm : $('#condCustNm').val()   //input Customer Name
			,inputIsUnmaskYn : $('#isUnmaskYn').val() //마스크 처리 해제 Y
			,outputSoId : 'condSo'            //output SO ID Select
			,outputCustNm : 'condCustNm'            //output Customer Name Text
			,outputCustId : 'condCustId'            //output Customer ID Text

		},
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog").html(html);
		},		
		complete : function(){
			wrapWindowByMask(); // 팝업 오픈
			$("#txtCustSearchCustNm").focus(); //오픈 후 focus위치
		}
	}); 
}

function customerSearchCallback(){
	searchCustInfo();
}

/**
 * 신규 등록 처리
 */
function insertNewPymAcnt(){
	if(checkValidation('I') == false){
		return;
	}

	var url = '/customer/customer/payment/paymentManagement/insertPymCustInfoAction.json';
	var paymentAccountInfo = $("#pymAcntBasicInfo input:text").serialize();
	paymentAccountInfo = paymentAccountInfo + '&' + $("#invoiceInfo input:text").serialize();
	paymentAccountInfo = paymentAccountInfo + '&' + $("#cardRegInfo input:text").serialize();
	paymentAccountInfo = paymentAccountInfo + '&soId=' + $('#condSoId').val();
	paymentAccountInfo = paymentAccountInfo + '&custId=' + $('#condCustId').val();
	paymentAccountInfo = paymentAccountInfo + '&useAcntNmYn=' + ($('#inputPrintPymNm').prop("checked") ? 'Y' : 'N');
	paymentAccountInfo = paymentAccountInfo + '&billCyclCd=' + $('#inputBillCyclCd').val();
	paymentAccountInfo = paymentAccountInfo + '&zipCd=' + $('#inputPostNo').val();
	paymentAccountInfo = paymentAccountInfo + '&baseAddr=' + $('#inputBaseAddr').val();
	paymentAccountInfo = paymentAccountInfo + '&telNo=' + getTelNo($('#inputTelNo').val());
	paymentAccountInfo = paymentAccountInfo + '&faxNo=' + getTelNo($('#inputFaxNo').val());
	paymentAccountInfo = paymentAccountInfo + '&mtelNo=' + getTelNo($('#inputCellPhoneNo').val());
	paymentAccountInfo = paymentAccountInfo + '&pymMthd=' + $('#inputPymMthd').val();
	paymentAccountInfo = paymentAccountInfo + '&pmcResn=' + $('#inputchgResn').val();
	paymentAccountInfo = paymentAccountInfo + '&state=' + '';
	paymentAccountInfo = paymentAccountInfo + '&city=' + '';
	paymentAccountInfo = paymentAccountInfo + '&billMdmGiroYn=' + ($('#inputPaperInvoice').val() == 'SEL' ? 'N' : $('#inputPaperInvoice').val());
	paymentAccountInfo = paymentAccountInfo + '&billMdmEmlYn=' + ($('#inputEmailInvoice').val() == 'SEL' ? 'N' : $('#inputEmailInvoice').val());
	paymentAccountInfo = paymentAccountInfo + '&billMdmSmsYn=' + ($('#inputSmsInvoice').val() == 'SEL' ? 'N' : $('#inputSmsInvoice').val());
	paymentAccountInfo = paymentAccountInfo + '&bnkCd=' + ($('#inputCardBankCd').val() == 'SEL' ? '' : $('#inputCardBankCd').val());
	paymentAccountInfo = paymentAccountInfo + '&cdtcdExpDt=' + dateFormatToStringYYYYMM($('#inputExp').val());
	
	$.ajax({
        url:url,
        type:'POST',
        data : paymentAccountInfo,
        dataType: 'json',
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
          	$("#paymentGrid").clearGridData();
        	//입력부 초기화
   	      	inputInit();
   	      	
   	      	//버튼 컨트롤
   	      	btnCtrl('I');
   	      	
          	$("#paymentGrid").setGridParam({
 	   	        postData : {
 	   				soId : $("#condSoId").val(),
 	   				custId : $("#condCustId").val(),
 	   				isUnmaskYn : $("#isUnmaskYn").val()
 	   			}
          	});
      		$("#paymentGrid").trigger("reloadGrid");
        	
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		if(err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
     			alert(err.responseJSON.exceptionMsg);
     		}else{
     			alert('<spring:message code="MSG.M10.MSG00005"/>');	
     		}
     		
     	}
    });
}


/*
 * 변경처리
 */
function updatePymAcnt(){
	if(checkValidation('U') == false){
		return;
	}
	
	// 변경된 값만 파라미터로 넘김
	var rowid  = $("#paymentGrid").jqGrid('getGridParam', 'selrow');
	var data = $("#paymentGrid").getRowData(rowid);

	//이메일 체크 처리
	if($('#inputEmail').val() != data.emlAsMask){
		if(checkEmailStr($('#inputEmail').val() ) == false){
			$('#inputEmail').focus();
			alert('<spring:message code="MSG.M08.MSG00046" />');
			return false;
		}
	}
	

	var isChanged = false;
	var paymentAccountInfo = 'pymAcntId=' + $('#inputPymAcntId').val();
	paymentAccountInfo = paymentAccountInfo + '&soId=' + $('#condSoId').val();
	paymentAccountInfo = paymentAccountInfo + '&isUnmaskYn=' + $('#isUnmaskYn').val();
	
	if($('#inputPymAcntNm').val() != data.acntNm){
		paymentAccountInfo = paymentAccountInfo + '&acntNm=' + $('#inputPymAcntNm').val();
		isChanged = true;
	}
	
	if($('#inputPymMthd').val() != data.pymMthd){
		paymentAccountInfo = paymentAccountInfo + '&pymMthd=' + $('#inputPymMthd').val();
		isChanged = true;
	}
	//변경사유
	if($('#inputchgResn').val() != data.pmcResn){
		paymentAccountInfo = paymentAccountInfo + '&pmcResn=' + $('#inputchgResn').val();
		isChanged = true;
	}
	
	if($('#inputTelNo').val() != data.telNo){
		paymentAccountInfo = paymentAccountInfo + '&telNo=' + getTelNo($('#inputTelNo').val());
		isChanged = true;
	}
	
	if($('#inputFaxNo').val() != data.faxNo){
		paymentAccountInfo = paymentAccountInfo + '&faxNo=' + getTelNo($('#inputFaxNo').val());
		isChanged = true;
	}
	
	if($('#inputCellPhoneNo').val() != data.mtelNoAsMask){
		paymentAccountInfo = paymentAccountInfo + '&mtelNo=' + getTelNo($('#inputCellPhoneNo').val());
		isChanged = true;
	}
	
	if($('#inputEmail').val() != data.emlAsMask){
		paymentAccountInfo = paymentAccountInfo + '&eml=' + $('#inputEmail').val();
		isChanged = true;
	}
	
	if($('#inputPostNo').val() != data.zipCd){
		paymentAccountInfo = paymentAccountInfo + '&zipCd=' +$('#inputPostNo').val();
		isChanged = true;
	}
	
	if($('#inputBaseAddr').val() != data.baseAddr){
		paymentAccountInfo = paymentAccountInfo + '&baseAddr=' +$('#inputBaseAddr').val();
		isChanged = true;
	}
	
	if($('#inputDtlAddr').val() != data.addrDtlAsMask){
		paymentAccountInfo = paymentAccountInfo + '&addrDtl=' +$('#inputDtlAddr').val();
		isChanged = true;
	}
	
	
	if($('#inputPrintPymNm').prop("checked") == true && data.useAcntNmYn == 'N'){
		paymentAccountInfo = paymentAccountInfo + '&useAcntNmYn=' + ($('#inputPrintPymNm').prop("checked") ? 'Y' : 'N');
		isChanged = true;
	}
	
	if($('#inputPrintPymNm').prop("checked") == false && data.useAcntNmYn == 'Y'){
		paymentAccountInfo = paymentAccountInfo + '&useAcntNmYn=' + ($('#inputPrintPymNm').prop("checked") ? 'Y' : 'N');
		isChanged = true;
	}

	//지로청구서
	if($('#inputPaperInvoice').val() != 'SEL' && data.billMdmGiroYn != 'N' && $('#inputPaperInvoice').val() != data.billMdmGiroYn){
		paymentAccountInfo = paymentAccountInfo + '&billMdmGiroYn=' + ($('#inputPaperInvoice').val() == 'SEL' ? 'N' : $('#inputPaperInvoice').val());
	  isChanged = true;
	}

	if($('#inputPaperInvoice').val() == 'SEL' && data.billMdmGiroYn != 'N' && $('#inputPaperInvoice').val() != data.billMdmGiroYn){
	 	paymentAccountInfo = paymentAccountInfo + '&billMdmGiroYn=' + ($('#inputPaperInvoice').val() == 'SEL' ? 'N' : $('#inputPaperInvoice').val());
	  	isChanged = true;
	}	
	
	//이메일청구서
	if($('#inputEmailInvoice').val() != 'SEL' && data.billMdmEmlYn != 'N' && $('#inputEmailInvoice').val() != data.billMdmEmlYn){
		paymentAccountInfo = paymentAccountInfo + '&billMdmEmlYn=' + ($('#inputEmailInvoice').val() == 'SEL' ? 'N' : $('#inputEmailInvoice').val());
	  isChanged = true;
	}

	if($('#inputEmailInvoice').val() == 'SEL' && data.billMdmEmlYn != 'N' && $('#inputEmailInvoice').val() != data.billMdmEmlYn){
	 	paymentAccountInfo = paymentAccountInfo + '&billMdmEmlYn=' + ($('#inputEmailInvoice').val() == 'SEL' ? 'N' : $('#inputEmailInvoice').val());
	  	isChanged = true;
	}
	//sms
	if($('#inputSmsInvoice').val() != 'SEL' && data.billMdmSmsYn != 'N' && $('#inputSmsInvoice').val() != data.billMdmSmsYn){
		paymentAccountInfo = paymentAccountInfo + '&billMdmSmsYn=' + ($('#inputSmsInvoice').val() == 'SEL' ? 'N' : $('#inputSmsInvoice').val());
	  isChanged = true;
	}

	if($('#inputSmsInvoice').val() == 'SEL' && data.billMdmSmsYn != 'N' && $('#inputSmsInvoice').val() != data.billMdmSmsYn){
	 	paymentAccountInfo = paymentAccountInfo + '&billMdmSmsYn=' + ($('#inputSmsInvoice').val() == 'SEL' ? 'N' : $('#inputSmsInvoice').val());
	  	isChanged = true;
	}
	
	if($('#inputCardBankCd').val() != null && $('#inputCardBankCd').val() != data.bnkCd){
		paymentAccountInfo = paymentAccountInfo + '&bnkCd=' + ($('#inputCardBankCd').val() == 'SEL' ? '' : $('#inputCardBankCd').val());
		isChanged = true;
	}
	
	if($('#inputCardBankOwnNm').val() != data.acntOwnerNm){
		paymentAccountInfo = paymentAccountInfo + '&acntOwnerNm=' +$('#inputCardBankOwnNm').val();
		isChanged = true;
	}
	
	if($('#inputCardBankOwnNo').val() != data.acntNoAsMask){
		paymentAccountInfo = paymentAccountInfo + '&acntNo=' +$('#inputCardBankOwnNo').val();
		isChanged = true;
	}
	
	if($('#inputExp').val() != data.cdtcdExpDt){
		paymentAccountInfo = paymentAccountInfo + '&cdtcdExpDt=' +dateFormatToStringYYYYMM($('#inputExp').val());
		isChanged = true;
	}
	
	if($('#inputBillCyclCd').val() != data.billCyclCd){
		paymentAccountInfo = paymentAccountInfo + '&billCyclCd=' + $('#inputBillCyclCd').val();
		isChanged = true;
	}
	
	if(isChanged == false){
		alert('<spring:message code="MSG.M06.MSG00026" />');
		return;
	}
	
	var url = '/customer/customer/payment/paymentManagement/updatePymCustInfoAction.json';
	
 	$.ajax({
        url:url,
        type:'POST',
        data : paymentAccountInfo,
        dataType: 'json',
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
          	$("#paymentGrid").clearGridData();
        	//입력부 초기화
   	      	inputInit();
   	      	
   	      	//버튼 컨트롤
   	      	btnCtrl('I');
   	      	
          	$("#paymentGrid").setGridParam({
 	   	        postData : {
 	   				soId : $("#condSoId").val(),
 	   				custId : $("#condCustId").val(),
 	   				isUnmaskYn : $("#isUnmaskYn").val()
 	   			}
          	});
      		$("#paymentGrid").trigger("reloadGrid");
        	
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		if(err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
     			alert(err.responseJSON.exceptionMsg);
     		}else{
     			alert('<spring:message code="MSG.M10.MSG00005"/>');	
     		}
     	}
    });
}


/*
 * 저장 체크
 */
function checkValidation(mode){
	//납부자명 필수 체크
	var pymNm = $("#inputPymAcntNm").val();
	if(pymNm == null || pymNm.length == 0){
		$("#inputPymAcntNm").focus();
		var item = '<spring:message code="LAB.M02.LAB00008" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//납부방법 필수 체크
	if($('#inputPymMthd').val()== 'SEL'){
		$('#inputPymMthd-button').focus();
		var item = '<spring:message code="LAB.M02.LAB00016" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//변경 사유 필수 체크
	if($('#inputchgResn').val()== 'SEL'){
		$('#inputchgResn-button').focus();
		var item = '<spring:message code="LAB.M06.LAB00058" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//휴대번호 필수 체크
	var celTelNo = $("#inputCellPhoneNo").val();
	if(celTelNo == null || celTelNo.length == 0){
		$('#inputCellPhoneNo').focus();
		var item = '<spring:message code="LAB.M14.LAB00076" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//Email 필수 체크
	var email = $("#inputEmail").val();
	if(email == null || email.length == 0){
		$('#inputEmail').focus();
		var item = '<spring:message code="LAB.M08.LAB00119" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	// Email형식체크-
	if(mode != 'U' && checkEmailStr(email) == false){
		$('#inputEmail').focus();
		alert('<spring:message code="MSG.M08.MSG00046" />');
		return false;
	}
	
	if($("#inputBillCyclCd").val() =='SEL'){
		$('#inputBillCyclCd-button').focus();
		var item = '<spring:message code="LAB.M10.LAB00042" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//우편번호 체크(주소는 우편번호의 유무만으로 체크)
	var postNo = $("#inputPostNo").val();
	if(postNo == null || postNo.length == 0){
		$('#inputPostNo').focus();
		var item = '<spring:message code="LAB.M09.LAB00190" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	//기본주소 체크
	var baseAddr = $("#inputBaseAddr").val();
	if(baseAddr == null || baseAddr.length == 0){
		$('#inputBaseAddr').focus();
		var item = '<spring:message code="LAB.M01.LAB00218" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//청구서 유형 체크(3개중의 하나는 필수 선택)
	 if($('#inputPaperInvoice').val()== 'SEL' && $('#inputEmailInvoice').val()== 'SEL' && $('#inputSmsInvoice').val()== 'SEL' ){
		$('#inputPaperInvoice-button').focus();
		var item = '<spring:message code="LAB.M10.LAB00034" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//납부방법 자동이체/카드 경우 체크
	if($('#inputPymMthd').val()== '02' || $('#inputPymMthd').val()== '03'){
		//은행 필수 체크
		if($('#inputCardBankCd').val()== 'SEL'){
			$('#inputCardBankCd-button').focus();
			var item = '<spring:message code="LAB.M08.LAB00111" />';
			alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
			return false;
		}
		
		//명의자명 체크
		var ownNm = $("#inputCardBankOwnNm").val();
		if(ownNm == null || ownNm.length == 0){
			$('#inputCardBankOwnNm').focus();
			var item = '<spring:message code="LAB.M05.LAB00035" />';
			alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
			return false;
		}
		
		//번호 체크
		var ownNo = $("#inputCardBankOwnNo").val();
		if(ownNo == null || ownNo.length == 0){
			$('#inputCardBankOwnNo').focus();
			var item = '<spring:message code="LAB.M01.LAB00043" />';
			alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
			return false;
		}
	}
	
	//납부방법 카드 유효기간 체크
	if($('#inputPymMthd').val()== '03'){
		//유효기간 체크
		var exp = $("#inputExp").val();
		if(exp == null || exp.length == 0){
			$('#inputExp').focus();
			var item = '<spring:message code="LAB.M08.LAB00107" />';
			alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
			return false;
		}
	}
}

/*
 * 버튼 비활성화 처리
 */
function btnDisable(id){
	$('#' + id ).addClass('white-btn');
	$('#' + id ).removeClass('grey-btn');
	$('#' + id ).addClass('not-active');
	$('#' + id ).attr('disabled',true);
	
}

/*
 * 버튼 활성화 처리
 */
function btnEnable(id){
	$('#' + id ).addClass('grey-btn');
	$('#' + id ).removeClass('white-btn');
	$('#' + id ).removeClass('not-active');
	$('#' + id ).removeAttr('disabled');
}

</script>
<!-- 상단 메뉴명 및 Navigator 작성 -->
<div class="h3_bg">
	<h3>${menuName}</h3>
	<!-- Navigator -->
	<div class="nav">                                        
		<a href="#" class="home"></a>
		<c:forEach items="${naviMenuList}" var="mu" varStatus="status">
			<span>&gt;</span>${mu.menuName}
		</c:forEach>
	</div>                               
	<!--// Navigator -->
</div>		
	
<!-- 페이지 작성 -->


<!-- 검색 버튼 -->
<div class="main_btn_box">
	<ntels:auth auth="${menuAuthR}">
		<div class="fr mt10">
			<a id='searchPymBtn' href="#"class="grey-btn" title='<spring:message code="LAB.M02.LAB00011"/>'><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
	</ntels:auth>
</div>
<!-- 검색부 -->
<table class="writeview">
	<colgroup>
		<col style="width:10%;">
		<col style="width:40%;">
		<col style="width:10%;">
		<col style="width:40%;">
	</colgroup>
	<thead>
		<tr>
			<th><spring:message code="LAB.M07.LAB00003" /></th>
			<td>
				<select id="condSo" class="w270">
					<c:if test="${session_user.soAuthList.size() >1}">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					</c:if>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
				<input id="condSoId" type="text" hidden />
			</td>
			<th><spring:message code="LAB.M01.LAB00050" /><span class="dot">*</span></th>
			<td>
				<div class="inp_date w280">
					<input id="condCustNm" type="text" class="w245" />
					<input id="condCustId" type="text" hidden/>
					<input id="condCustNmB" type="text" hidden/> 
					<input id="condZipCd" type="text" hidden/> 
					<input id="condBaseAddr" type="text" hidden/>
					<input id="condAddrDtl" type="text" hidden/> 
					<input id="condTelNo" type="text" hidden/> 
					<input id="condFaxNo" type="text" hidden/> 
					<input id="condMtelNo" type="text" hidden/> 
					<input id="condEml" type="text" hidden/> 
					<ntels:auth auth="${menuAuthR}">
						<a id='btnCustSearch' href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
				</div>
			</td>
		</tr>
	</thead>
</table> 

<!-- 납부계정리스트 테이블 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M02.LAB00007"/></h4>
	</div>
</div>
<div id='gridDiv'>
	<table id="paymentGrid" class="w100p"></table>
	<div id="paymentGridPager"></div>
</div>


<!-- 납부계정기초정보 -->
<div id="pymAcntBasicInfo">
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M02.LAB00010"/></h4>
		</div>
		<div class="fr mt10">
			<ntels:auth auth="${menuAuthR}">		
				<a id="copyCustInfo" class="grey-btn" href="#" title='<spring:message code="LAB.M01.LAB00056"/>'><spring:message code="LAB.M01.LAB00056"/></a>
			</ntels:auth>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width: 10%;">
			<col style="width: 15%;">
			<col style="width: 10%;">
			<col style="width: 15%;">
			<col style="width: 10%;">
			<col style="width: 15%;">
			<col style="width: 10%;">
			<col style="width: 15%;">
		</colgroup>
		<tbody>
			<tr>
				<!-- 납부번호 -->
				<th><spring:message code="LAB.M02.LAB00006"/></th>
				<td><input id="inputPymAcntId" name="pymAcntId" type="text" class="w100p" /></td>
				<!-- 사업 -->
				<th><spring:message code="LAB.M07.LAB00003" /></th>
				<td><select id="inputSoId" name="soId" class="w100p">
						<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
								<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
						</c:forEach>
				</select></td>
				<!-- 고객명 -->
				<th><spring:message code="LAB.M01.LAB00050" /></th>
				<td colspan="3"><input id="inputCustNm" name="custNm" type="text" class="w100p" /></td>
			</tr>
			<tr>
				<!-- 납부자명 -->
				<th><spring:message code="LAB.M02.LAB00008" /><span class="dot">*</span></th>
				<td colspan="3"><input id="inputPymAcntNm" name="acntNm" type="text" class="w100p" /></td>
				<th><spring:message code="LAB.M02.LAB00016" /><span class="dot">*</span></th>
				<!-- 납부방법 -->
				<td>
					<select id="inputPymMthd" name="pymMthd" class="w100p">
						<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${pymMthdCodeList}" var="pymMthdCode" varStatus="status">
							<option value="${pymMthdCode.commonCd}">${pymMthdCode.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<!-- 변경사유 -->
				<th><spring:message code="LAB.M06.LAB00058" /><span class="dot">*</span></th>
				<td>
					<select id="inputchgResn" name="pmcResn" class="w100p">
						<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${pymChngResnCodeList}" var="pymChngResnCode" varStatus="status">
							<option value="${pymChngResnCode.commonCd}">${pymChngResnCode.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<!-- 전화번호 -->
				<th><spring:message code="LAB.M09.LAB00065" /></th>
				<td><input id="inputTelNo" name="inputTelNo" type="text" class="w100p" /></td>
				<!-- FAX번호 -->
				<th><spring:message code="LAB.M15.LAB00036" /></th>
				<td><input id="inputFaxNo" name="inputFaxNo" type="text" class="w100p" /></td>
				<!-- 휴대폰번호 -->
				<th><spring:message code="LAB.M14.LAB00076" /><span class="dot">*</span></th>
				<td><input id="inputCellPhoneNo" name="inputCellPhoneNo" type="text" class="w100p" /></td>
				<!-- 이메일주소 -->
				<th><spring:message code="LAB.M08.LAB00119" /><span class="dot">*</span></th>
				<td><input id="inputEmail" name="eml" type="text" class="w100p" /></td>
			</tr>
			<tr>
				<!-- 선택정보 -->
				<th><spring:message code="LAB.M07.LAB00198" /></th>
				<td colspan="3">
					<!-- 청구계정명 출력여부 --> 
					<input type="checkbox" id="inputPrintPymNm" name="useAcntNmYn" /> <label for="inputPrintPymNm"><spring:message code="LAB.M02.LAB00015" /></label>
				</td>
				<th><spring:message code="LAB.M10.LAB00042" /><span class="dot">*</span></th>
				<td colspan="3">
					<select id="inputBillCyclCd" name="billCyclCd" class="w145">
						<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${billCyclCodeList}" var="billCyclCode" varStatus="status">
							<option value="${billCyclCode.commonCd}">${billCyclCode.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</tbody>
	</table>
</div>

<!-- 청구서 정보 -->
<div id="invoiceInfo">
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M10.LAB00036" /></h4>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width: 10%;">
			<col style="width: 90%;">
		</colgroup>
		<tbody>
			<tr>
				<!-- 주소 -->
				<th><spring:message code="LAB.M09.LAB00190" /><span class="dot">*</span></th>
				<td>
					<div class="date_box">
						<div class="inp_date w105">
							<input type="text" id="inputPostNo" name="zipCd" class="w73" > 
								<a id="searchAddrBtn" href="#" class="search" title='<spring:message code="LAB.M09.LAB00191" />' ></a>
						</div>
						<div class="inp_date w370">
							<input type="text" id="inputBaseAddr" name="baseAddr" class="w370">
						</div>
						<div class="inp_date w370">
							<input type="text" id="inputDtlAddr" name="addrDtl" class="w370">
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<!-- 청구매체 -->
				<th><spring:message code="LAB.M10.LAB00034" /><span class="dot">*</span></th>
				<td>
					<div class="date_box">
						<div class="date_sort">
							<!-- 지로 -->
							<ul>
								<li><spring:message code="LAB.M09.LAB00199" /></li>
								<li><select id="inputPaperInvoice" name="billMdmGiroYn" class="w100">
										<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
										<c:forEach items="${paperInvoiceCodeList}" var="paperInvoiceCode" varStatus="status">
											<option value="${paperInvoiceCode.commonCd}">${paperInvoiceCode.commonCdNm}</option>
										</c:forEach>
									</select>
								</li>
							</ul>
						</div>
						<div class="date_sort">
							<!-- 이메일 -->
							<ul>
								<li>
									<spring:message code="LAB.M08.LAB00120" />
								</li>
								<li>
									<select id="inputEmailInvoice" name="billMdmEmlYn" class="w100">
										<option value='SEL'>
											<spring:message code="LAB.M15.LAB00002"/>
										</option>
										<c:forEach items="${emailInvoiceCodeList}" var="emailInvoiceCode" varStatus="status">
											<option value="${emailInvoiceCode.commonCd}">${emailInvoiceCode.commonCdNm}</option>
										</c:forEach>
									</select>
								</li>
							</ul>
						</div>
						<div class="date_sort">
							<!-- SMS / MMS -->
							<ul>
								<li>
									<spring:message code="LAB.M15.LAB00081" />
								</li>
								<li>
									<select id="inputSmsInvoice" name="billMdmSmsYn" class="w100">
										<option value='SEL'>
											<spring:message code="LAB.M15.LAB00002"/>
										</option>
										<c:forEach items="${smsInvoiceCodeList}" var="smsInvoiceCode" varStatus="status">
											<option value="${smsInvoiceCode.commonCd}">${smsInvoiceCode.commonCdNm}</option>
										</c:forEach>
									</select>
								</li>
							</ul>
						</div>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
</div>
<!-- 카드/은행 정보 -->
<div id="cardRegInfo">
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title">
				<spring:message code="LAB.M11.LAB00001"/>
			</h4>
		</div>
		<div class="fr mt10">
			<a id="authBtn" class="grey-btn" href="#" title='<spring:message code="LAB.M08.LAB00135"/>'><spring:message code="LAB.M08.LAB00135"/></a>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width: 10%;">
			<col style="width: 15%;">
			<col style="width: 10%;">
			<col style="width: 15%;">
			<col style="width: 10%;">
			<col style="width: 15%;">
			<col style="width: 10%;">
			<col style="width: 15%;">
		</colgroup>
		<tbody>
			<tr>
				<!-- 은행/카드사 -->
				<th><spring:message code="LAB.M08.LAB00111"/><span class="dot">*</span></th>
				<td>
					<select id="inputCardBankCd" name="bnkCd" class="w145">
						<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${bankCodeList}" var="bankCode" varStatus="status">
							<option value="${bankCode.commonCd}">${bankCode.commonCdNm}</option>
						</c:forEach><%--  --%>
					</select>
				</td>
				<!-- 명의자명 -->
				<th><spring:message code="LAB.M05.LAB00035"/><span class="dot">*</span></th>
				<td>
					<input type="text" id="inputCardBankOwnNm" name="acntOwnerNm" class="w100p">
				</td>
				<!-- 카드/계좌번호 -->
				<th><spring:message code="LAB.M01.LAB00043"/><span class="dot">*</span></th>
				<td>
					<input type="text" id="inputCardBankOwnNo" name="acntNo" class="w100p">
				</td>
				<!-- 유효기간 -->
				<th><spring:message code="LAB.M08.LAB00107"/><span class="dot">*</span></th>
				<td>
					<div class="date_box">
						<div class="inp_date w135">
							<input  type="text"  id="inputExp" name="inputExp" class="month-picker">
							<a href="#" id='btnCal' class="btn_cal"></a>
						</div>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
</div>

<!-- 등록정보 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M03.LAB00077"/></h4>
	</div>
</div>
<div id="regInfo">
	<table class="writeview">
		<colgroup>
			<col style="width: 10%;">
			<col style="width: 23%;">
			<col style="width: 10%;">
			<col style="width: 23%;">
			<col style="width: 10%;">
			<col style="width: 24%;">
		</colgroup>
		<tbody>
			<tr>
				<th><spring:message code="MSG.M10.MSG00039"/></th>
				<td><input id="inputRegrNm" name="inputRegrNm" type="text" class="w100p not-active" disabled="disabled"/></td>
				<th><spring:message code="MSG.M10.MSG00040"/></th>
				<td><input id="inputRegrOrgNm" name="inputRegrOrgNm" type="text" class="w100p not-active" disabled="disabled"/></td>
				<th><spring:message code="MSG.M10.MSG00041"/></th>
				<td><input id="inputRegrDt" name="inputRegrDt" type="text" class="w100p not-active" disabled="disabled"/></td>
			</tr>
			<tr>
				<th><spring:message code="MSG.M10.MSG00042"/></th>
				<td><input id="inputChgrNm" name="inputChgrNm" type="text" class="w100p not-active" disabled="disabled" /></td>
				<th><spring:message code="MSG.M10.MSG00043"/></th>
				<td><input id="inputChgrOrgNm" name="inputChgrOrgNm" type="text" class="w100p not-active" disabled="disabled"/></td>
				<th><spring:message code="MSG.M10.MSG00044"/></th>
				<td><input id="inputChgrDt" name="inputChgrDt" type="text" class="w100p not-active" disabled="disabled"/></td>
			</tr>
		</tbody>
	</table>
</div>
<!-- 하단 버튼부 -->
<div class="main_btn_box">
	<div id="funcBtn" class="fl">
	<ntels:auth auth="${menuAuthR}">
		<a id='acntChngHistBtn' class="grey-btn" title='<spring:message code="LAB.M02.LAB00009"/>' href="#"><spring:message code="LAB.M02.LAB00009"/></a>
	</ntels:auth>
	</div>
	<div class="fr">
			<span id="commonBtn">
			<ntels:auth auth="${menuAuthR}">
			<a id="initBtn" class="grey-btn" title='<spring:message code="LAB.M10.LAB00050"/>' href="#"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>
			</ntels:auth>
			<ntels:auth auth="${menuAuthC}">
			<a id="newBtn" class="grey-btn" title='<spring:message code="LAB.M03.LAB00075"/>' href="#"><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
			</ntels:auth>
			<ntels:auth auth="${menuAuthU}">
			<a id="updateBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
			</ntels:auth>
			<ntels:auth auth="${menuAuthD}">
			<a id="deleteBtn"  class="grey-btn" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
			</ntels:auth>
			<ntels:auth auth="${menuAuthP}">
			<a id="printBtn" class="grey-btn" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
			</ntels:auth>
		</span>
	</div>
</div>
<input id="isUnmaskYn" value='' type='text' hidden />
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>

