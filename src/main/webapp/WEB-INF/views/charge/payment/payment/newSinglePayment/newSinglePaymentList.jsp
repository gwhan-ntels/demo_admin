<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style type="text/css">
#dimMask {
position:absolute;
z-index:9000;
background-color:#000;
display:none;
left:0;
top:0;
}
.window{
display: none;
position:absolute;
left:100px;
top:100px;
z-index:10000;
}
</style>

<script type="text/javascript">

var intervalVal = 0;
var pgmExeSeqNo = '';
var batchCount = 0;

//현재년월
var globalDt = new Date();
var globalYyyy = globalDt.getFullYear();
var globalMm = globalDt.getMonth() + 1;
if(globalMm < 10){
	globalMm = "0"+globalMm;
}
var globalYyyyMm = ""+globalYyyy+globalMm;

var cashDpstClHtml;		//현금입금 구분 셀렉트 박스 html
//입금등록 파라미터
var packageName ="com.ntels.ccbs.batch.py.launcher";
var className ="NBlpyb09m02JobLauncher";
var batGrpId ="00005";
var batPgmId ="BLPYB09M02";

//입금취소 파라미터
var packageName2 ="com.ntels.ccbs.batch.py.launcher";
var className2 ="NBlpyb10m02JobLauncher";
var batGrpId2 ="00007";
var batPgmId2 ="BLPYB10M02";


$(document).ready(function() {
	
	var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
	
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
			},
			beforeShow : function (dateText, inst) {
		        	
				var selectDate = $(this).val().split("-");
				var year = Number(selectDate[0]);
				var month = Number(selectDate[1]) - 1;
				$(this).datepicker( "option", "defaultDate", new Date(year, month, 1) );
				$(this).datepicker('setDate', new Date(year, month, 1));
		            
			}
		}); 
		 
		// 년월 레이어 focus
	    $(".month-picker").focus(function () {
	        $(".ui-datepicker-calendar").hide();
	        $("#ui-datepicker-div").position({
	            my: "center top",
	            at: "center bottom",
	            of: $(this)
	        });
	    });
		
	}
	//날짜 셋팅
	var nowDt = new Date();
	var nowYyyy = nowDt.getFullYear();
	var nowMm = nowDt.getMonth() + 1 -3;
	var beforeMn = "";
	if(nowMm < 1){
		nowMm = nowMm + 12;
		nowYyyy = nowYyyy -1
	}
	if(nowMm < 10){
		beforeMn = "0" + nowMm;
	}else{
		beforeMn = "" + nowMm
	}
	$('#searchStartYymm').datepicker('setDate', new Date("" + nowYyyy +"/"+ beforeMn +"/"+ "1"));	//3달전 날짜
	$('#searchEndYymm').datepicker('setDate', new Date());
	
	
	//일반달력
	$('.datepicker').datepicker();
	// 년월 레이어 focus
    $(".datepicker").focus(function () {
        $(".ui-datepicker-calendar").show();
        $("#ui-datepicker-div").position({
            my: "center top",
            at: "center bottom",
            of: $(this)
        });
    });
	
	
	//현금입금 구분
	cashDpstClHtml = '<option value=""><spring:message code="LAB.M15.LAB00002"/></option>';
	<c:forEach items="${cashGb}" var="item">
		cashDpstClHtml += '<option value="'+ '${item.commonCd}'+'">'+ '${item.commonCdNm}'+'</option>';
	</c:forEach>
	
	// 입금구분 선택시 현금입금구분 선택
	$('#dpstCl').selectmenu({
    	change: function() {
		
	    	if($('#dpstCl').val() == '11'){
	    		$('#cashDpstCl').html(cashDpstClHtml);
	    		labelActive('cashDpstCl');
	    		$("#cashDpstCl").selectmenu("refresh");
	    	}else{
	    		$('#cashDpstCl').html('<option value=""><spring:message code="LAB.M15.LAB00002"/>');
	    		labelNonActive('cashDpstCl');
	    		$("#cashDpstCl").selectmenu("refresh");
	    	}
		}
	});
	
	//현금입금구분 만 보여주도록 
	$('#cashDpstCl').html(cashDpstClHtml);
	labelActive('cashDpstCl');
	$("#cashDpstCl").selectmenu("refresh");
	
	btn_init();
	
	//$('#dpstAmt').number( true, 2 ); 금액을 소수점 2자리로 나타내고 싶을때 사용
	
	//납부계정 조회
	$('#btnSearchPym').on('click',function (e) {
		var url="/system/common/common/pymAcntSearch/pymAcntPopup.ajax";
		var param = new Object();
		param.popType = "m";            //팝업타입 m:모달 o:일반
		param.returnId1 = "searchAcntNm";
		param.returnId2 = "searchPymAcntId";
		
		$.ajax({
			type : "post",
			url : url,
			data : param,
			async: true,
			success : function(data) {
				var html = data;
				$("#popup_dialog").html(html);
			},      
			complete : function(){
				wrapWindowByMask(); // 팝업 오픈
				//$("#txtPymSearchCustNm").focus(); //오픈 후 focus위치
			}
		}); 
	});
	 
	// 영수사원
	$('#btnSearchUser').on('click',function (e) {
		$("#rcptEmpNm").val('');  //돋보기 클릭시 초기화
		var url="/system/common/common/userSearchMng/userSearchPopup.ajax";
		var param = new Object();
		param.popType = "m";            //팝업타입 m:모달 o:일반
		param.returnId1 = "rcptEmpNm";
		param.returnId2 = "rcptEmpId";
		$.ajax({
			type : "post",
			url : url,
			data : param,
			async: true,
			success : function(data) {
				var html = data;
				$("#popup_dialog").html(html);
			},      
			complete : function(){
				wrapWindowByMask(); // 팝업 오픈
			}
		});
	});
	
	
	//그리드1
	$("#anonyReceiptTable").jqGrid({
		url:'getAnonyReceiptSubListAction.json?',
		datatype: "local",
		mtype:"POST",
		postData: {},
		colModel: [
			//차후 안쓰는 필드는 hidden처리
			{ label: '<spring:message code="LAB.M10.LAB00040" />', name: 'billDate', width : 80, formatter:stringTypeFormatterYYYYMMDD, index:'billDate', align:"center"},      //청구일자
			{ label: '<spring:message code="LAB.M10.LAB00033" />', name: 'billYymm', width : 80, formatter:stringTypeFormatterYYYYMM, align:"center"},       //청구년월
			{ label: '<spring:message code="LAB.M09.LAB00137" />', name: 'billAmtBfrAdj', index:'billAmtBfrAdj', formatter: 'integer', width : 80, align:"right", summaryTpl: "Total: {0}", summaryType: 'sum'},        //조정전청구금액
			{ label: '<spring:message code="LAB.M09.LAB00134" />', name: 'adjAmt', formatter: 'integer', width : 80, align:"right", summaryTpl: "Total: {0}", summaryType: 'sum'},          //조정금액
			{ label: '<spring:message code="LAB.M10.LAB00031" />', name: 'billAmt', formatter: 'integer', width : 80, align:"right", summaryTpl: "Total: {0}", summaryType: 'sum'},         //청구금액
			{ label: '<spring:message code="LAB.M07.LAB00237" />', name: 'receiptAmt', formatter: 'integer', summaryType: 'sum', width : 80, align:"right", summaryTpl: "Total: {0}", summaryType: 'sum'},         //수납금액
			{ label: '<spring:message code="LAB.M05.LAB00048" />', name: 'unpaidAmt', formatter: 'integer', width : 80, align:"right", summaryTpl: "Total: {0}", summaryType: 'sum'},       //미납금액
			{ label: '<spring:message code="LAB.M02.LAB00004" />', name: 'paymentDt', formatter:stringTypeFormatterYYYYMMDD, width : 80, align:"center"},            //납기일자
			{ label: '<spring:message code="LAB.M10.LAB00030" />', name: 'billingCategory', width : 80, align:"center"}, //청구구분
			{ label: '<spring:message code="LAB.M08.LAB00043" />', name: 'fullPayYn', width : 80, align:"center"},      //완납여부
			{ label: '<spring:message code="LAB.M07.LAB00212" />', name: 'smallAmtYn', width : 80, align:"center"},         //소액여부
			{ label: '<spring:message code="LAB.M07.LAB00243" />', name: 'receiptProcessingDt', width : 80, align:"center"},        //수납처리일자
			{ label: '<spring:message code="LAB.M07.LAB00236" />', name: 'receiptTypNm', width : 80, align:"left"},        //수납구분
			{ label: '<spring:message code="LAB.M07.LAB00194" />', name: 'overPaymentAply', index:'overPaymentAply', formatter: 'integer', width : 80, align:"right", summaryTpl: "Total: {0}", summaryType: 'sum'},             //선수금적용액
			{ label: 'receiptTyp' , name: 'receiptTyp', hidden:true, width : 0},
			{ label: 'pymAcntId'  , name: 'pymAcntId', hidden:true, width: 0},
			{ label: 'billSeqNo'    , name: 'billSeqNo',   hidden:true, width: 0},
			{ label: 'soId' , name: 'soId', hidden:true,width : 0},
			//{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0}
		],
		sortname:'billDate',
		sortorder:'desc',
		sortable:true,
		viewrecords: true,
		shrinkToFit:false,
		rowNum:999,                   //한번에 노출되는 row 수
		rowList:[5,10,20,30,50],    //선택시 노출되는 row 수
		pager: '#anonyReceiptPager',
		grouping: true,
		title: '엑셀 다운',
		groupingView : {
			groupField : ['pymAcntId'],
			groupColumnShow: [false],
			groupSummary : [true],
			groupText : ['<b>{0}</b>'],
			groupDataSorted: true
		},
		onCellSelect : function(rowId, index, contents, event){
			setSelectedData(rowId);
			//getReceiptList(index); //입금리스트
			btnActive("btn_insert");
		},
		loadComplete: function(obj){
			if($("#anonyReceiptTable").getGridParam("reccount") > 0) {
				settingDt();
				btnActive("printBtn");
			}else{
				settingDt();
				btnNonActive("printBtn");
			}
	   		$("#anonyReceiptTable").setGridWidth($('#anonyReceiptGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		},
		onClickButton : function(e) {
			exportExcel($("#anonyReceiptTable"), "test.xls");
		}, 
		buttonicon: 'ui-icon-arrowthickstop-1-s'
	});
   
	$("#anonyReceiptTable").setGridWidth($('#anonyReceiptGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
   
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#anonyReceiptTable").setGridWidth($('#anonyReceiptGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

    
	//조회버튼
	$("#btnSearch").click(function() {
		getAnonyReceiptTable();
	});
    
    
	//그리드2
	$("#myReceiptTable").jqGrid({
	           
		url:'getMyReceiptListAction.json?',
		datatype: "local",
		mtype:"POST",
		postData: {},
		//multiselect: true,
		
		colModel: [ 
			//차후 안쓰는 필드는 hidden처리
			{ label: '<spring:message code="LAB.M08.LAB00173" />', name: 'depositDt', formatter:stringTypeFormatterYYYYMMDD, width : 80, align:"center", sortable:false},   //입금일자
			{ label: '<spring:message code="LAB.M02.LAB00006" />', name: 'pymAcntId', width : 80, align:"center"},      //납부계정id
			{ label: '<spring:message code="LAB.M02.LAB00018" />', name: 'pymAcntNm', width : 120, align:"left"},      //납부자명
			{ label: '<spring:message code="LAB.M08.LAB00177" />', name: 'depositTypNm', width : 80, align:"center"},     //입금형태
			{ label: '<spring:message code="LAB.M08.LAB00166" />', name: 'depositOptionNm', width : 80, align:"center"}, //입금구분
			//{ label: '<spring:message code="LAB.M08.LAB00169" />', name: 'financialInstitution', width : 130, align:"left"},  //입금기관
			{ label: '<spring:message code="LAB.M08.LAB00168" />', name: 'depositAmt', formatter: 'integer', width : 80, align:"right"}, //입금금액
			{ label: '<spring:message code="LAB.M07.LAB00250" />', name: 'commission', formatter: 'integer', width : 80, align:"right"},    //수수료
			{ label: '<spring:message code="LAB.M08.LAB00127" />', name: 'transferDt', formatter:stringTypeFormatterYYYYMMDD, width : 80, align:"center"},//이체일
			{ label: '<spring:message code="LAB.M07.LAB00242" />', name: 'received', width : 80, align:"center"},       //수납처리여부
			{ label: '<spring:message code="LAB.M07.LAB00243" />', name: 'receiptProcessingDt', formatter:stringTypeFormatterYYYYMMDD, width : 80, align:"center"},  //수납처리일자
			{ label: '<spring:message code="LAB.M05.LAB00055" />', name: 'unknownPayment', width : 80, align:"center"}, //미확인입금대상
			{ label: '<spring:message code="LAB.M06.LAB00093" />', name: 'remark', width : 150, align:"left"},             //적요
			{ label: 'depositTyp' , name: 'depositTyp', hidden:true,width : 0},
			{ label: 'depositOption' , name: 'depositOption', hidden:true,width : 0},
			{ label: 'depositCl' , name: 'depositCl', hidden:true,width : 0},
			{ label: 'soId' , name: 'soId', hidden:true,width : 0},
			{ label: 'depositSeqNo' , name: 'depositSeqNo', hidden:true,width : 0}         
		],
		onSelectRow: function(row, isSelected) {
			var rowId =$("#myReceiptTable").jqGrid('getGridParam','selrow');
			if(rowId != null) {
				btnActive("btn_cancel");
			}
			else {
				btnNonActive("btn_cancel")
			}
		},
		loadComplete: function(obj){
			if($("#myReceiptTable").getGridParam("reccount") > 0) {
				btnActive("printBtn2");
			}else{
 				btnNonActive("printBtn2");
			}

			$("#myReceiptTable").setGridWidth($('#myReceiptGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}
	});
	       
	$("#myReceiptTable").setGridWidth($('#myReceiptGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	       
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#myReceiptTable").setGridWidth($('#myReceiptGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

	// 입금시 
	$('#btn_insert').click(function(){
		setPayment();
	});
	
	// 입금취소시
	$('#btn_cancel').click(function() {
		cancelPayment();
	});
	
	// 엑셀출력1
	$('#printBtn').click(function(){
		printBillExcel();
	});
	
	// 엑셀출력2
	$('#printBtn2').click(function(){
		printReceiptExcel();
	});
   
});
  
  
	function getAnonyReceiptTable(){
		var param = new Object();
		
		param.soId = $("#searchSoId").val();
		if(param.soId == null || param.soId == ''){
			alert('<spring:message code="MSG.M07.MSG00001"/>');
			return;
		}
		
		param.billStrtYymm = dateFormatToStringYYYYMM($("#searchStartYymm").val());
		param.billEndYymm   = dateFormatToStringYYYYMM($("#searchEndYymm").val());
	   	
	   	if(param.billStrtYymm == '' || param.billEndYymm == ''){
	   		alert('<spring:message code="MSG.M01.MSG00007"/>');
	        return;
	   	}
	   	if(param.billStrtYymm > param.billEndYymm){
	   		alert('<spring:message code="MSG.M01.MSG00005"/>');
	        return;
	   	}
	   	
	   	param.pymAcntId = $("#searchPymAcntId").val();
	   	if(param.pymAcntId == null || param.pymAcntId == ''){
	   		alert('<spring:message code="MSG.M02.MSG00013"/>');
			return;
	   	}
	   	
		$("#anonyReceiptTable").setGridParam({
			datatype: "json",
			postData : param              
		});
		$("#anonyReceiptTable").trigger("reloadGrid");
		
		getReceiptList(); //입금리스트
	}
  
	function settingDt(){
		var today = new Date();
		var todayYYMMDD = dateFormatterUsingDateYYYYMMDD(today);
	
		$('#dpstDt').val("");
		$('#dpstDt').val(todayYYMMDD);
		$('#transDt').val("");
		$('#transDt').val(todayYYMMDD);
	}
  
	function setSelectedData(rowId) {	
		var data = $("#anonyReceiptTable").getRowData(rowId);
		$("#dpstAmt").val(data.unpaidAmt);
	}

  
	function getReceiptList(){
	  
		var param = new Object();
		param.soId = $("#searchSoId").val();
  	
		$("#myReceiptTable").setGridParam({
			datatype: "json",
			postData : param             
		});
      
		$("#myReceiptTable").trigger("reloadGrid");
      
	}
	
	//입금등록
	function setPayment() {
		var param = checkValidation();

    	if(param) {
	    	$.ajax({
	            type : "post",
	            url : '/charge/payment/payment/newSinglePayment/insertAction.json',
	            data : param,
	            async: true,
	            success : function(data) {
					alert('<spring:message code="MSG.M07.MSG00084"/>');
					getAnonyReceiptTable();
	            },
	    		error: function() {
	    		    //에러메세지
	    		    alert('<spring:message code="MSG.M10.MSG00005"/>'); // MSG.M10.MSG00005=처리에 실패했습니다. 관리자에게 문의해 주세요.
	    		}
	        });
    	}
		
		
/* 
		if (param) {
			$.post('/charge/payment/payment/newSinglePayment/insertDeposit', param, function (response) {
				if (response.success == true) {
					alert('<spring:message code="MSG.M07.MSG00084"/>');
					getAnonyReceiptTable();
				} else {
					alert(response.message);
				}
			});
		}
 */		
	}	

	//입금등록 밸리데이션 체크
	function checkValidation(){
	  
		var param = new Object();
		
		param.dpstAmt = $("#dpstAmt").val();
		
		console.log(param.dpstAmt);
		// 빈 항목 있을시 호출 불가하며, 팝업처리
		if(param.dpstAmt == ''){
			alert('<spring:message code="MSG.M08.MSG00073"/>'); //입금액을 입력해 주세요
			$("#depositAmt").focus();
			return false;
		}
		
		param.dpstDt =dateFormatToStringYYYYMMDD($("#dpstDt").val());
		if(param.dpstDt == ''){
			alert('<spring:message code="MSG.M08.MSG00074"/>'); //입금일자를 입력해 주세요
			$("#dpstDt").focus();
			return false;
		}
		
		param.dpstCl = $("#dpstCl").val();
		if(param.dpstCl == ''){
			alert('<spring:message code="MSG.M08.MSG00072"/>'); //입금구분을 선택해 주세요
			$("#dpstCl-button").focus();
			return false;
		}
		
		param.rcptEmpNm = $("#rcptEmpNm").val();
		param.rcptEmpId = $("#rcptEmpId").val();
		if(param.rcptEmpNm == '' || param.rcptEmpId == ''){
			alert('<spring:message code="MSG.M08.MSG00015"/>'); //영수사원을 선택해 주세요
			$("#rcptEmpNm").focus();
			return false;
		}
		
		param.transDt = dateFormatToStringYYYYMMDD($("#transDt").val());
		if(param.transDt == ''){
			alert('<spring:message code="MSG.M08.MSG00055"/>'); //이체일자를 입력해 주세요
			$("#transDt").focus();
			return false;
		}
		
		param.cashDpstCl = $("#cashDpstCl").val();		
		param.smry = $("#smry").val();
		param.confirmedPayment = $("#confirmedPayment").val();
		
		//online 내부적으로 사용하는 param 
		param.workYymm     = globalYyyyMm;
		param.billCycl     = '01';
		param.user         = "${session_user.userId}";
		param.orgId        = "${session_user.orgId}";		

 		if ($("input:radio[name='rdoDpstGubn']:checked").val() == 'S' ) {
			//선택된 그리드 row
			var rowId = $("#anonyReceiptTable").getGridParam("selrow");
			var data = $("#anonyReceiptTable").getRowData(rowId);
			
			if(rowId < 1){
				alert('<spring:message code="MSG.M10.MSG00047"/>');//청구내역을 선택해 주세요.
				return;
			}
			
			//납부계정 세팅
			if($('#confirmedPayment').val() == '1') {
				param.pymAcntId = data.pymAcntId;
			}
			
			//billSeqNo 세팅
			param.billSeqNo = data.billSeqNo;
			//청구년월 세팅
			param.billYymm  = dateFormatToStringYYYYMM(data.billYymm);
			param.soId      = data.soId;
		} else {
	 		param.soId = $('#searchSoId').val();
	 		param.pymAcntId = $('#searchPymAcntId').val();
		}

		return param;	
	}
	
/*   
	//입금등록 배치 호출
	function setPayment(){
		
		var param = checkValidation();
		
		if(param != false){
			
			//선택된 그리드 row
			var rowId = $("#anonyReceiptTable").getGridParam("selrow");
			var data = $("#anonyReceiptTable").getRowData(rowId);
			
			if(rowId < 1){
				alert('<spring:message code="MSG.M10.MSG00047"/>');//청구내역을 선택해 주세요.
				return;
			}
			
			//납부계정 세팅
			var batchParam_15 = '';
			if($('#confirmedPayment').val() == '1') {
				batchParam_15 = data.pymAcntId;
			}
			
			//프로그램 실행 시퀀스 생성  
	        //var pgmExeSeqNo = '';
	        $.ajax({
				  type : "post",
		          url : '/charge/payment/payment/newSinglePayment/getPgmExeSeqNo.json', 
		          async: false,
		          success : function(data) {
		        	 pgmExeSeqNo = data.pgmExeSeqNo;
		          }
			});
	        
			//배치 실행 
			var batchParam = new Object();
			batchParam.packageName = packageName;
			batchParam.className = className;
			
			var args = "";
			args += batPgmId + "\|";			//3.프로그램Id
			args += "0" + "\|";						//4. ?
			args += batGrpId + "\|";					//5.그룹아이디?
			args += pgmExeSeqNo + "\|";				//6.배치시퀀스번호
			args += globalYyyyMm + "\|";		//7.현재년월
			args += "01" + "\|";					//8.빌싸이클
			args += "${session_user.userId}" + "\|";	//9사용자ID
			args += data.soId + "\|";				//10.so_id
			args += "" + "\|";						//11.신용카드일때 시퀀스 값 --- 신용카드 사용안함. 공백
			args += param.dpstCl + "\|";		//12.입금방법 - 입금구분
			args += param.cashDpstCl + "\|";	//13.현금입금구분
			args += dateFormatToStringYYYYMMDD(param.dpstDt) + "\|";		//14.입금일자
			args += param.dpstAmt + "\|";			//15.입금액
			args += "" + "\|";						//16.입금수수료유형 ???
			args += data.soId + "\|";				//17.so_id
			args += batchParam_15 + "\|";			//18.확인입금시 - 납부계정ID - 미확인입금시 공백
			args += param.smry + "\|";				//19.적요
			args += "" + "\|";						//20.신용카드일 때 2자리 카드종류 - 신용카드가 아닐 때 5자리 입금계좌정보
			args += dateFormatToStringYYYYMMDD(param.transDt) + "\|";	//21.이체일자
			args += "" + "\|";						//22.신용카드일 때 신용카드 번호를, 아닐 때는 공백을
			args += "" + "\|";						//23.수금사원ID - 사용하지 않음
			args += param.rcptEmpId + "\|";			//24.영수사원ID
			args += "1" + "\|";						//25.1:대리점 수납
			args += "${session_user.orgId}" + "\|";	//26.DEPT_CD
			
			args += data.billSeqNo + "\|";	//27.청구번호
			args += dateFormatToStringYYYYMM(data.billYymm);	//28.청구년월
			
			batchParam.args = args;
			
			console.log(args);
			alert(args);
			
			pageDisable();
			
			$.post('http://192.168.10.214:18080/executeJob', batchParam, function (response) {
				console.log('success : ' + response.commonResult.success);
				console.log('message : ' + response.commonResult.message);
			});
			
			//로그 호출
			getPaymentBatchLog();
		}
		
	} */
  

	//로그테이블에서 데이터를 가지고와서 작업완료 될때까지 계속 호출 setPayment
	function getPaymentBatchLog(){
		
		clearInterval(intervalVal);
		
		//선택된 그리드 row
		var rowId = $("#anonyReceiptTable").getGridParam("selrow");
		var data = $("#anonyReceiptTable").getRowData(rowId);
		
		var param = new Object();
		param.soId = data.soId;
		param.bsYymm = globalYyyyMm;
		param.batPgmId = batPgmId;
		param.batGrpId = batGrpId;
		param.pgmExeSeqNo = pgmExeSeqNo;
		
		$.ajax({
			url : '/charge/charge/charge/chargeMng/getBatchLogAction.json',
			type : 'POST',
			data : param,
			dataType: 'json',
			success : function(data) {
				console.log(data.batchLogList);
				var batchLogList = data.batchLogList;
				batchCount++;
				
				//데이터 체크
				if(data.batchLogList != null && data.batchLogList.length > 0){
					//배치작업 완료되면 딤처리 해지, 그리드 다시 검색
					if(batchLogList[0].batProcStat == "1" || batchLogList[0].batProcStat == "9"){
						
						clearInterval(intervalVal);
						batchCount = 0; //배치 카운트 초기화
						getAnonyReceiptTable();
						btnNonActive("btn_insert");
						pageEnavle();
						
						
					}else{		//진행중일경우
						clearInterval(intervalVal);
						intervalVal = setTimeout(getPaymentBatchLog, 3000);
					}
					
				}else{	//진행중일경우
					clearInterval(intervalVal);
					intervalVal = setTimeout(getPaymentBatchLog, 3000);
				
					//임시로 종료 시키기 위한작업 삭제 할것.
					if(batchCount > 10){	//루프 3번 돌동안 로그 안싸였을 경우 초기화
						alert('<spring:message code="MSG.M15.MSG00028"/>');	//MSG.M15.MSG00028=JDBC 또는 SQL 오류가 발생하였습니다.  시퀀스 채번 오류
						clearInterval(intervalVal);
						pageEnavle();
						batchCount = 0; //배치 카운트 초기화
					}
					
				}
				
			},
	     	error : function(err){
	     		alert('<spring:message code="MSG.M15.MSG00028"/>');	//MSG.M15.MSG00028=JDBC 또는 SQL 오류가 발생하였습니다.  시퀀스 채번 오류
	     		pageEnavle();
	     	}
		});
		
	}
	
	
	
	//입금등록 배치 호출
	function cancelPayment(){
		
		//선택된 그리드 row
		var rowId = $("#myReceiptTable").getGridParam("selrow");
		if (rowId == null || rowId == ''){
			alert('<spring:message code="MSG.M10.MSG00048"/>');	//취소할 입금내역을 선택해주세요.
			
			return;
		}
		var data = $("#myReceiptTable").getRowData(rowId);
		
		//프로그램 실행 시퀀스 생성  
        $.ajax({
			  type : "post",
	          url : '/charge/payment/payment/newSinglePayment/getPgmExeSeqNo.json', 
	          async: false,
	          success : function(data) {
	        	 pgmExeSeqNo = data.pgmExeSeqNo;
    	   }
		});
		
      	//배치 실행 
		var batchParam = new Object();
		batchParam.packageName = packageName2;
		batchParam.className = className2;
		
		var args = "";
		args += batPgmId2 + "\|";			//0.프로그램Id
		args += "0" + "\|";						//1. ?
		args += batGrpId2 + "\|";					//2.그룹아이디?
		args += pgmExeSeqNo + "\|";				//3.배치시퀀스번호
		args += globalYyyyMm + "\|";		//4.현재년월
		args += "01" + "\|";					//5.빌싸이클
		args += "${session_user.userId}" + "\|";	//6사용자ID
		args += data.soId + "\|";				//7.so_id
		args += "Cancel Single Deposit" + "\|";				//8.'Cancel Single Deposit'
		args += data.depositSeqNo + "\|";				//9.depositSeqNo
		args += "00" + "\|";				//10. '00'
		args += "1" + "\|";				//11. '1'
		args += "${session_user.orgId}";				//12.DEPT_CD
		
		batchParam.args = args;
		
		pageDisable();
		
		$.post('http://192.168.10.214:18080/executeJob', batchParam, function (response) {
			console.log('success : ' + response.commonResult.success);
			console.log('message : ' + response.commonResult.message);
		});

		getCanCelBatchLog();
	}
	
	//로그테이블에서 데이터를 가지고와서 작업완료 될때까지 계속 호출 cancelPayment
	function getCanCelBatchLog(){
		
		clearInterval(intervalVal);
		
		//선택된 그리드 row
		var rowId = $("#myReceiptTable").getGridParam("selrow");
		var data = $("#myReceiptTable").getRowData(rowId);
		
		var param = new Object();
		param.soId = data.soId;
		param.bsYymm = globalYyyyMm;
		param.batPgmId = batPgmId2;
		param.batGrpId = batGrpId2;
		param.pgmExeSeqNo = pgmExeSeqNo;
		
		$.ajax({
			url : '/charge/charge/charge/chargeMng/getBatchLogAction.json',
			type : 'POST',
			data : param,
			dataType: 'json',
			success : function(data) {
				console.log(data.batchLogList);
				var batchLogList = data.batchLogList;
				batchCount++;
				
				//데이터 체크
				if(data.batchLogList != null && data.batchLogList.length > 0){
					//배치작업 완료되면 딤처리 해지, 그리드 다시 검색
					if(batchLogList[0].batProcStat == "1" || batchLogList[0].batProcStat == "9"){
						
						clearInterval(intervalVal);
						batchCount = 0; //배치 카운트 초기화
						getAnonyReceiptTable();
						btnNonActive("btn_insert");
						pageEnavle();
						
						
					}else{		//진행중일경우
						clearInterval(intervalVal);
						intervalVal = setTimeout(getCanCelBatchLog, 3000);
					}
					
				}else{	//진행중일경우
					clearInterval(intervalVal);
					intervalVal = setTimeout(getCanCelBatchLog, 3000);
				
					//임시로 종료 시키기 위한작업 삭제 할것.
					if(batchCount > 10){	//루프 3번 돌동안 로그 안싸였을 경우 초기화
						alert('<spring:message code="MSG.M15.MSG00028"/>');	//MSG.M15.MSG00028=JDBC 또는 SQL 오류가 발생하였습니다.  시퀀스 채번 오류
						clearInterval(intervalVal);
						pageEnavle();
						batchCount = 0; //배치 카운트 초기화
					}
					
				}
				
			},
	     	error : function(err){
	     		alert('<spring:message code="MSG.M15.MSG00028"/>');	//MSG.M15.MSG00028=JDBC 또는 SQL 오류가 발생하였습니다.  시퀀스 채번 오류
	     		pageEnavle();
	     	}
		});
		
	}
	
	
	
  
  /* // 배치 호출 (입금등록, 입금취소)
  function goProcess(type) {
	  
   var param = checkValidation();
	  
   //입금 등록 호출
   if(type == 'I'){
	   
	    var rowId = $("#anonyReceiptTable").getGridParam("selrow");
       	   
	    //선택한 row가 없을 경우
        if (rowId == 0){
            return;
        }
      
        var data = $("#anonyReceiptTable").getRowData(rowId);
        
		//현재 년월
        var yyyymm = getStrYearMonth();
		
		//납부계정 세팅
		var batchParam_15 = '';
		if($('#confirmedPayment').val() == '1') {
			batchParam_15 = data.pymAcntId;
		}
		
        //대리점 수납취소구분 추가
        var batchParam_22 = '';
        var loanGvFlg = $("#loanGvFlg").val();
        
        if((loanGvFlg == '1') && ($('#dpstCl').val() == '11')) {
        	batchParam_22 = '1';
        }
        else {
        	batchParam_22 = '0';
        }
         
        // 인자 생성
        var params  = new Array();
        params[ 0 ]  = 'BLPYB09M02';  // BLPYB09M02
        params[ 1 ]  = '0';  	      // 0
        params[ 2 ]  = '99999';		  // 99999
        
        //프로그램 실행 시퀀스 생성  
        var pgmExeSeqNo = '';
        $.ajax({
			  type : "post",
	          url : '/charge/payment/payment/newSinglePayment/getPgmExeSeqNo.json', 
	          async: false,
	          success : function(data) {
	        	 pgmExeSeqNo = data.pgmExeSeqNo.replace(/(^0+)/, '');
    	   }
		});
        
        params[ 3 ]  = pgmExeSeqNo;
        params[ 4 ]  = yyyymm;        // 현재년월
        params[ 5 ]  = '01';		  // 01
        params[ 6 ]  = "<c:out value='${session_user.userId}'/>";  // 사용자ID
        params[ 7 ]  = data.soId;     // SO_ID
        params[ 8 ]  = '';            // 신용카드일때 시퀀스 값
        params[ 9 ]  = $('#depositOption').val();  // 입금구분
        params[ 10 ] = $('#cashDpstCl').val()      // 현금입금구분
        params[ 11 ] = $('#depositDt').val().replace(/-/gi, '');  // 입금일자
        params[ 12 ] = parseInt($('#depositAmt_notFormat').val()); // 입금액
        params[ 13 ] = '';                     // ''
        params[ 14 ] = data.soId;     // SO_ID
        params[ 15 ] = batchParam_15; // 확인입금시 납부계정 ID, 미확인입금시 공백처리
        params[ 16 ] = $('#smry').val(); // 비고란
        params[ 17 ] = '[]';          // 신용카드일 때 2자리 카드종류, 신용카드가 아닐 때 5자리 입금계좌정보
        params[ 18 ] = $('#transDt').val().replace(/-/gi, ''); // 납부일련번호
        params[ 19 ] = ''; // 신용카드일 때 신용카드 번호를, 아니면 공백처리
        params[ 20 ] = ''; // 수금사원 ID (사용하지않음)
        params[ 21 ] = $('#rcptEmpId').val(); // 영수사원 ID
        params[ 22 ] = batchParam_22;
        params[ 23 ] = $('#deptCd').val();
        
        //선택대상의 빌 번호와 청구년월 조회
        var billParam = new Object();
        billParam.billSeqNo = $("#billSeqNo").val();
	           
        $.ajax({
				  type : "post",
		          url : '/charge/payment/payment/newSinglePayment/getBillInfo.json', 
		          data: billParam,
		          async: true,
		          success : function(data) {
		        	  $(data.billList).each(function(index, item){
		        	  params[ 24 ] = item.billSeqNo;
		              params[ 25 ] = item.billYymm;
		          	 });
	        	 }
		});
        
        // 인자값 최종 정렬
        var tempParam = JSON.stringify(params);
        console.log("### temp : " +tempParam); 
        return;
	}
   
   // 입금 취소 호출
   if(type == 'C'){
	  var obj = $("#myReceiptTable");
	  var idx = obj .jqGrid('getGridParam', 'selarrrow');
	    
	  if (idx == 0){
	     return;
	  }
	     
	  for(var i = 0; i < idx.length;i++)
	  {
	   var data = $("#myReceiptTable").getRowData(idx[i]);
       
	   //현재 년월
       var yyyymm = getStrYearMonth();
       
       var params  = new Array();
       
       params[ 0 ]  = 'BLPYB10M02';  // BLPYB09M02
       params[ 1 ]  = '0';  	      // 0
       params[ 2 ]  = '99999';		  // 99999
       
       //프로그램 실행 시퀀스 생성  
       var pgmExeSeqNo = '';
       $.ajax({
			  type : "post",
	          url : '/charge/payment/payment/newSinglePayment/getPgmExeSeqNo.json', 
	          async: false,
	          success : function(data) {
	        	 pgmExeSeqNo = data.pgmExeSeqNo.replace(/(^0+)/, '');
   	   }
	});
       
       params[ 3 ]  = pgmExeSeqNo;
       params[ 4 ]  = yyyymm;        // 현재년월
       params[ 5 ]  = '01';		  // 01
       params[ 6 ]  = "<c:out value='${session_user.userId}'/>";  // 사용자ID
       params[ 7 ]  = data.soId;     // SO_ID
       params[ 8 ]  = 'Cancel Single Deposit';
       params[ 9 ]  = data.depositSeqNo;
       params[ 10 ] = '00';
           
       //대리점 수납취소구분 추가
       var batchParam_11 = '';
       var loanGvFlg = $("#loanGvFlg").val();
        
       if((loanGvFlg == '1') && ($('#depositOption').val() == '11')) {
    	   batchParam_11 = '1';
       }
       else {
    	   batchParam_11 = '0';
       }
       params [ 11 ] = batchParam_11;
       params [ 12 ] = $("#deptCd").val();// 물류
       
       var tempParam = JSON.stringify(params);
       console.log("### cancel temp : " +tempParam); 
       
	   }
       return;
	}
	  
    //확인팝업
	if(type == 'I') {
		var check = confirm('<spring:message code="MSG.M08.MSG00070" />');   //I
	}
	else if(type == 'C') {
		var check = confirm('<spring:message code="MSG.M08.MSG00071" />'); //U
	}
		
	if(!check){
		return;
	}
	
}
   */




/* 	사용안함
function getPermitOrg() {
    // 로그인한 id 대리점 조회(여신 등) 
	$.ajax({
			  type : "post",
	          url : '/charge/payment/payment/newSinglePayment/getPermitOrg.json', 
	          async: true,
	          success : function(data) {
	        	  console.log(data);
	        	  $(data.permitOrgList).each(function(index, item){
	        	  $("#deptCd").val(item.deptCd);
        	  });
	          },
			error : function(err){
	     		
	     	}
	});
  }

function getLoanAvlAmt() {
	// 여신가능금액 및 여신사용여부 조회
	$.ajax({
			  type : "post",
	          url : '/charge/payment/payment/newSinglePayment/getLoanAvlAmt.json', 
	          async: true,
	          success : function(data) {
	        	  $(data.loanAvlAmtList).each(function(index, item){
	        	  $("#loanAvlAmt").val(item.loanAvlAmt); 
	        	  $("#loanGvFlg").val(item.loanGvFlg);  
	    	  });
	         }
	});
}
 */


	function printBillExcel(){
		
		var param = new Object();
		
		param.soId = $("#searchSoId").val();
		if(param.soId == null || param.soId == ''){
			alert('<spring:message code="MSG.M07.MSG00001"/>');
			return;
		}
		
		param.billStrtYymm = dateFormatToStringYYYYMM($("#searchStartYymm").val());
		param.billEndYymm   = dateFormatToStringYYYYMM($("#searchEndYymm").val());
	   	
	   	if(param.billStrtYymm == '' || param.billEndYymm == ''){
	   		alert('<spring:message code="MSG.M01.MSG00007"/>');
	           return;
	   	}
	   	if(param.billStrtYymm > param.billEndYymm){
	   		alert('<spring:message code="MSG.M01.MSG00005"/>');
	              return;
	   	}
	   	
	   	param.pymAcntId = $("#searchPymAcntId").val();
	   	if(param.pymAcntId == null || param.pymAcntId == ''){
	   		alert('<spring:message code="MSG.M02.MSG00013"/>');
			return;
	   	}
	
		$.download('getAnonyReceiptSubExcelAction.xlsx',param,'post');	
	}

	function printReceiptExcel(){
		var soId = $('#searchSoId').val();
		var param = '&soId=' + soId;
		
		$.download('getMyReceiptExcelAction.xlsx',param,'post');	
	}
	


	//버튼 비활성화 처리
	function btn_init() {
		  btnNonActive("btn_insert");
		  btnNonActive("btn_cancel");
		  btnNonActive("printBtn");
		  btnNonActive("printBtn2");
		  
		  //labelNonActive('cashDpstCl');
		  
	}
	//버튼 활성화
	function btnActive(id){
		$('#'+id).addClass('grey-btn');
		$('#'+id).removeClass('white-btn');
		$('#'+id).removeClass('not-active');
		$('#'+id).removeAttr('disabled');
	}
	//버튼 비활성화
	function btnNonActive(id){
		$('#'+id).addClass('white-btn');
		$('#'+id).removeClass('grey-btn');
		$('#'+id).addClass('not-active');
		$('#'+id).attr('disabled',true);
	}
	
	function labelActive(id){
		$('#'+id).addClass('white-btn');
		$('#'+id).removeClass('grey-btn');
		$('#'+id).removeClass('not-active');
		$('#'+id).removeAttr('disabled', false);
		
		$('#'+id+'-button').removeClass('not-active');
		$('#'+id+'-button').removeAttr('disabled');
	}

	function labelNonActive(id){
		$('#'+id).addClass('grey-btn');
		$('#'+id).removeClass('white-btn');
		$('#'+id).addClass('not-active');
		$('#'+id).attr('disabled',true);
	}


	//페이지 로딩
	function pageDisable(){
		
		//화면의 높이와 너비를 구한다.
		var maskHeight = $(window).height();  
		var maskWidth = $(window).width();  

		//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
		$('#dimMask').css({'width': maskWidth, 'height':maskHeight});
		$('#dimMask').fadeTo(0,0.1);  
		$('#loadingImage2').removeAttr('style');
		//e.preventDefault();
		
	}

	//페이지 로딩 삭제
	function pageEnavle(){
		
		$('#dimMask').hide();  
		$('#loadingImage2').css({"display":"none"});
		
	}

</script>


<!--NaviGator-->
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

<div class="main_btn_box">
    <div class="fr mt10">
        <a href="#" id="btnSearch" class="grey-btn" ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
    </div>
</div>



	<table class="writeview" id="areaSearch" >
	    <colgroup>
	        <col style="width:10%;">
	        <col style="width:40%;">
	        <col style="width:10%;">
	        <col style="width:40%;">
	    </colgroup>
	    <tbody> 
	        <tr>
	            <th><spring:message code="LAB.M07.LAB00003"/><span class="dot">*</span><!-- 사업 --></th>
	            <td colspan="3">
	                <select class="w150" id="searchSoId" name="searchSoId">
	                    <spring:message code="LAB.M15.LAB00002"/>
	                    <c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
	                        <option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
	                    </c:forEach>
	                </select>                                           
	            </td>
	        </tr>
	        <tr>
	            <th><spring:message code="LAB.M10.LAB00033"/><!-- 청구년월 --><span class="dot">*</span></th>
	            <td>
	                <div class="date_box">
	                    <div class="inp_date w150">
	                        <input type="text" id="searchStartYymm" name="searchStartYymm" class="month-picker" readonly="readonly"/>
	                        <a href="#" class="btn_cal"></a>
	                    </div>
	                    <span class="dash">~</span>
	                    <div class="inp_date w150">
	                        <input type="text" id="searchEndYymm" name="searchEndYymm" class="month-picker" readonly="readonly"/>
	                        <a href="#" class="btn_cal"></a>
	                    </div>
	                </div>
	            </td>
	            <th><spring:message code="LAB.M02.LAB00005"/><!-- 납부계정 --><span class="dot">*</span></th>
	            <td>
	                <div class="inp_date w280">
	                    <input type="text" id="searchAcntNm" name="searchAcntNm" class="w250" disabled="disabled" />
	                    <input type="hidden" id="searchPymAcntId" name="searchPymAcntId" />
	                    <a href="#" id="btnSearchPym" name="btnSearchPym" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
	                </div> 
	            </td>
	        </tr>
	    </tbody>
	</table>
	
	<div class="main_btn_box">
    	<div class="fl">
        	<h4 class="sub_title"><spring:message code="LAB.M10.LAB00032"/><!-- 청구내역 --></h4>
    	</div>
			<label><input type="radio" id="rdoDpstGubn" name="rdoDpstGubn" value="A" />전체미납 입금</label>
			<label><input type="radio" id="rdoDpstGubn" name="rdoDpstGubn" value="S" checked/>건별미납 입금</label>
    	<ntels:auth auth="${menuAuthP}">
			<a id="printBtn" class="white-btn" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
		</ntels:auth>					
	</div>

	<div id="anonyReceiptGrid">
	    <table id="anonyReceiptTable" class="w100p"></table>
	</div>
	<!--  <input type = "text" value = "PS00088949" >-->

	<div class="main_btn_box">
	    <div class="fl">
	        <h4 class="sub_title"><spring:message code="LAB.M02.LAB00001"/><!-- 나의 입금내역 --></h4>
	    </div>
	    <!--입금취소-->		
			<a class="grey-btn" href="#" id="btn_cancel"><span class="cancel_icon"><spring:message code="LAB.M08.LAB00176"/></span></a>		
	    <ntels:auth auth="${menuAuthP}">
			<a id="printBtn2" class="white-btn" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
		</ntels:auth>
	</div>

	<div id="myReceiptGrid">
	    <table id="myReceiptTable" class="w100p"></table>
	</div>
		
	<form:form method="post" name="singlePaymentAction" commandName="singlePaymentAction">
	<input type="hidden" name = "menuNo" value="${selectedMenu.menuNo}"/>
	<input type="hidden" name = "selectMenuNo" value="${selectedMenu.selectMenuNo}"/>
	<input type="hidden" name = "selectMenuNm" value="${selectedMenu.selectMenuNm}"/>
	<input type="hidden" name = "topMenuNo" value="${selectedMenu.topMenuNo}"/>
	<input type="hidden" name = "topMenuNm" value="${selectedMenu.topMenuNm}"/>
	<input type="hidden" name = "setItmResetNm" value=""/>
        
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:40%;">
			<col style="width:10%;">
			<col style="width:40%;">
		</colgroup>
		<tbody> 
			<tr>
				<!--입금액-->
				<th><spring:message code="LAB.M08.LAB00168" /><span class="dot">*</span></th>
			   <td>
				  <input id="dpstAmt" name="dpstAmt" type="text" class="w270" >
			   </td>
				<!--입금일자-->
			   <th><spring:message code="LAB.M08.LAB00173" /></th>
			   <td>
				  <div class="date_box">
	                    <div class="inp_date w150">
	                        <input type="text" id="dpstDt" name="dpstDt" class="datepicker" readonly="readonly" />
	                        <a href="#" class="btn_cal"></a>
	                    </div>
	              </div>
			   </td>
			   <!--입금구분-->
			   <th>건별<spring:message code="LAB.M08.LAB00166" /><span class="dot">*</span></th>
			   <td>
				    <select id="dpstCl" name="dpstCl" class="w270">
						<option value=""><spring:message code="LAB.M15.LAB00002"/>
						<c:forEach items="${receiptGb}" var="receiptGb" varStatus="status">
						<option value="${receiptGb.commonCd}">${receiptGb.commonCdNm}</option>
						</c:forEach>
					</select>
			   </td>
			</tr>
			<tr>
			   <!--영수사원-->
			   <th><spring:message code="LAB.M08.LAB00024" /><span class="dot">*</span></th>
			   <td>
				   <div class="inp_date w280">
                    <input type="text" id="rcptEmpNm" name="rcptEmpNm" class="w250" disabled="disabled" />
                    <input type="hidden" id="rcptEmpId" name="rcptEmpId" />
                    <a href="#" id="btnSearchUser" name="btnSearchUser" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
                </div> 
			   </td>
			   <!--이체일-->
			   <th><spring:message code="LAB.M08.LAB00127" /></th>
			   <td>
				  <div class="date_box">
	                    <div class="inp_date w150">
	                        <input type="text" id="transDt" name="transDt" class="datepicker" readonly="readonly"/>
	                        <a href="#" class="btn_cal"></a>
	                    </div>
	              </div>
			   </td>
			   <!--현금입금구분-->
			   <th><spring:message code="LAB.M14.LAB00055" /></th>
			   <td>
					<select id="cashDpstCl" name="cashDpstCl" class="w270">
					</select>
			   </td>
			</tr>
			<tr>
			   <!--비고-->
			   <th><spring:message code="LAB.M06.LAB00093" /></th>
				  <td colspan = "3">
				  <input id="smry" name="smry" type="text" class="w800">
			      </td>
			   <!--입금처리구분-->
			    <th><spring:message code="LAB.M08.LAB00174"/><span class="dot">*</span></th>
				<td>
					<input type="radio" id="confirmedPayment" name="confirmedPayment" value="1" checked="checked"/> 
					<label for="confirmedPayment"><spring:message code="LAB.M14.LAB00065" /></label>
				</td>
			</tr>
		</tbody>
	</table> 
</form:form>
		
	<div class="main_btn_box">
		<div class="fr">
				<!--등록-->
				<ntels:auth auth="${menuAuthC}">
					<a class="grey-btn" href="#"  id="btn_insert" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
				</ntels:auth>
		</div>
	</div>

<input id="pgmExeSeqNo" type='number' hidden />
<input id="depositAmt_notFormat" type='text' hidden />
<input id="billSeqNo" type='text' hidden />
<input id="loanGvFlg" type='text' hidden />
<input id="loanAvlAmt" type='text' hidden />
<input id="deptCd" type='text' hidden />


<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;" >
</div>
    