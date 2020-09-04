<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript" src="/scripts/nccbs/util.js"></script>

<script type="text/javascript">
$(document).ready(function() {

	pageInit();

	var currDate = new Date();
	var searchEndDt = dateFormatterUsingDateYYYYMMDD(currDate);
	currDate.setDate(currDate.getDate()-7);
	var searchStartDt = dateFormatterUsingDateYYYYMMDD(currDate);

	$("#searchEndDt").val(searchEndDt);
	$("#searchStartDt").val(searchStartDt);

	
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
	$('#searchStartMonth').datepicker('setDate', new Date());
	$('#searchEndMonth').datepicker('setDate', new Date());
	
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
			}
		}); 
	});
	
	//상세 납부계정 조회
	$('#btnDtlSearchPym').on('click',function (e) {
		var url="/system/common/common/pymAcntSearch/pymAcntPopup.ajax";
		var param = new Object();
		param.popType = "m";            //팝업타입 m:모달 o:일반
		param.returnId1 = "searchDtlAcntNm";
		param.returnId2 = "searchDtlPymAcntId";
		
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

	//그리드 처리
	$("#mainGrid").jqGrid({
		url : '/charge/payment/advanceReceived/advanceReceivedMng/getPrepayOccInfoListAction.json?',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
		    { label: 'soId', name: 'soId', width : 100, align:"center", hidden:true},
		    
			{ label: '<spring:message code="LAB.M06.LAB00134" />', name: 'prepayOccSeqNo', width : 90, align:"center"}, 	//발생일련번호
			{ label: '<spring:message code="LAB.M06.LAB00133" />', name: 'prepayOccNm', width : 100, align:"center"}, 	//발생구분
			{ label: '<spring:message code="LAB.M02.LAB00006" />', name: 'pymAcntId', width : 100, align:"center"}, 	//납부계정ID
			{ label: '<spring:message code="LAB.M02.LAB00008" />', name: 'acntNm', width : 130, align:"center"}, 	//납부계정명
			{ label: '<spring:message code="LAB.M07.LAB00344" />', name: 'prepayOccDttm', width : 120, formatter:stringTypeFormatterYYYYMMDDHH24MISS, align:"center"}, 	//선수금발생일시
			{ label: '<spring:message code="LAB.M08.LAB00173" />', name: 'dpstDt', width : 100, formatter:stringTypeFormatterYYYYMMDD, align:"center"}, 	//입금일자
			{ label: '<spring:message code="LAB.M08.LAB00127" />', name: 'transDt', width : 100, formatter:stringTypeFormatterYYYYMMDD, align:"center"}, 	//이체일
			{ label: '<spring:message code="LAB.M06.LAB00135" />', name: 'prepayOccAmt', width : 100, formatter: 'integer', align:"right"}, 	//발생금액
			{ label: '<spring:message code="LAB.M03.LAB00088" />', name: 'prepayTransAmt', width : 100, formatter: 'integer', align:"right"}, 	//대체금액
			{ label: '<spring:message code="LAB.M09.LAB00040" />', name: 'prepayBal', width : 100, formatter: 'integer', align:"right"}, 	//잔액
			{ label: '<spring:message code="LAB.M10.LAB00096" />', name: 'prepayProcStatNm', width : 100, align:"center"}, 	//처리상태
			{ label: '<spring:message code="LAB.M08.LAB00166" />', name: 'dpstClNm', width : 100, align:"center"}, 	//입금구분
			{ label: '<spring:message code="LAB.M06.LAB00136" />', name: 'prepayOccResnNm', width : 100, align:"center"}, 	//발생사유
			{ label: '<spring:message code="LAB.M03.LAB00084" />', name: 'regrId', width : 100, align:"center"}, 	//등록자명
			{ label: '<spring:message code="LAB.M03.LAB00079" />', name: 'regDate', formatter:dateTypeFormatterYYYYMMDDHH24MISS, width : 120, align:"center"}, 	//등록일
			{ label: '<spring:message code="LAB.M10.LAB00103" />', name: 'chgrId', width : 100, align:"center"}, 	//최종수정자명
			{ label: '<spring:message code="LAB.M10.LAB00104" />', name: 'chgDate', formatter:dateTypeFormatterYYYYMMDDHH24MISS, width : 120, align:"center"} 	//최종수정일
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 120,
		sortable : true,
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 5,
        pager: "#mainGridPager",
        onCellSelect : function(rowid, index, contents, event){
        	setSelectedData(rowid);
        },
       	loadComplete : function () {
  	      	$("#mainGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
  	      	
  	  	    $("#dtlGird").clearGridData();
  	      	
  	  	    $("#prepayBal").val('');
  	  	    $("#transApplAmt").val('');  	  	    
  	  	    $("#pymAcntId").val('');
  	  	    $("#unpayAmt").val('');

    		btnDisable('searchDtlBtn'); //빌링 조회 비활성화 
    		btnDisable('saveBtn');      //등록 버튼  활성화 
    		$("#transTp").selectmenu("refresh");
        },
    	sortable: { 
    		update: function(permutation) {
    			$("#mainGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});

	//그리드 처리
	$("#dtlGird").jqGrid({
		url : '/charge/payment/advanceReceived/advanceReceivedMng/getBillInvoiceListAction.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
		    { label: 'soId', name: 'soId', width : 100, align:"center", hidden:true},
		    
			{ label: '<spring:message code="LAB.M10.LAB00033" />', formatter:stringTypeFormatterYYYYMM, name: 'billYymm', width : 100, align:"center"}, 	//청구년월
			{ label: '<spring:message code="LAB.M10.LAB00040" />', formatter:stringTypeFormatterYYYYMMDD, name: 'billDt', width : 100, align:"center"}, //청구일자
			{ label: '<spring:message code="LAB.M02.LAB00005" />', name: 'pymAcntId', width : 100, align:"center"}, //납부계정
			{ label: '<spring:message code="LAB.M10.LAB00039" />', name: 'billSeqNo', width : 150, align:"center"}, //청구일련번호
			{ label: '<spring:message code="LAB.M10.LAB00058" />', formatter:'integer', name: 'adjPrvBillAmt', width : 100, align:"right"}, //최초조정전청구액 ADJ_PRV_BILL_AMT
			{ label: '<spring:message code="LAB.M02.LAB00037" />', formatter:'integer', name: 'adjAmt', width : 100, align:"right"}, //누적조정금액 ADJ_AMT
			{ label: '<spring:message code="LAB.M10.LAB00031" />', formatter:'integer', name: 'billAmt', width : 100, align:"right"}, //청구금액 BILL_AMT
			{ label: '<spring:message code="LAB.M07.LAB00237" />', formatter:'integer', name: 'rcptAmt', width : 100, align:"right"}, //수납금액 RCPT_AMT
			{ label: '<spring:message code="LAB.M05.LAB00048" />', formatter:'integer', name: 'unpayAmt', width : 100, align:"right"}, //미납금액 UNPAY_AMT
			{ label: '<spring:message code="LAB.M02.LAB00043" />', formatter:stringTypeFormatterYYYYMMDD, name: 'payDueDt', width : 100, align:"center"}, //납기마감일 PAY_DUE_DT
			{ label: '<spring:message code="LAB.M08.LAB00043" />', name: 'fullPayYn', width : 100, align:"center"}, //완납여부 FULL_PAY_YN
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 120,
		sortable : true,
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 5,
        pager: "#dtlGirdPager",
        onCellSelect : function(rowid, index, contents, event){
        	setSelectedDtlData(rowid);
        },
       	loadComplete : function () {
  	      	$("#dtlGird").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
  	  	    $("#pymAcntId").val('');
  	  	    $("#unpayAmt").val('');
        },
    	sortable: { update: function(permutation) {
    		$("#dtlGird").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});

	//그리드 화면 재조정
	$(window).resize(function() {
		$("#mainGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		$("#dtlGird").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

    //조회 버튼 이벤트
    $('#searchBtn').on('click',function (e) {
    	if($("#searchBtn").hasClass('not-active')){
			return;
		}
    	searchGridList();
	});
	
	//청구내역 조회 버튼 이벤트
	$('#searchDtlBtn').click(function(){
		searchDtlGridList();
	});

	//대체처리 버튼 이벤트
    $("#transTp").selectmenu({
    	change: function( event, ui ) {
    		btnEnable('searchDtlBtn'); //빌링 조회 활성화 
    		
			$("#dtlGird").clearGridData(); // 빌링내역 조기화
			
  	  	    $("#pymAcntId").val(''); // 수납대체 납부계정 초기화 
  	  	    $("#unpayAmt").val('');  // 수납대체 미납금액 초기화
    		
    		if ($("#transTp").val() == "01") {
				//선택된 Row data 가져오기
				var rowId = $("#mainGrid").jqGrid('getGridParam', "selrow" );
				var data = $("#mainGrid").getRowData(rowId);

    			//청구내역 조회조건에 납부계정 Setting   
    			$("#searchDtlAcntNm").val( data.acntNm );
    			$("#searchDtlPymAcntId").val( data.pymAcntId );    			
    		} else {
    			$("#searchDtlAcntNm").val('');
    			$("#searchDtlPymAcntId").val('');
    		}
    	}
    });	

    //신규등록 버튼 이벤트
    $('#saveBtn').on('click',function (e) {
      	if($("#saveBtn").hasClass('not-active')){
      		return;
  		}
    	insert();
	});	

    /* 
    	$("input:radio[name=transferPrepayBal]").change(function(){  
    		//선택된 Row data 가져오기
    		var rowId = $("#mainGrid").jqGrid('getGridParam', "selrow" );
    		var data = $("#mainGrid").getRowData(rowId);
    		
        	if ($("input:radio[name='transferPrepayBal']:checked").val() == 'Y') {
        		$("#transApplAmt").val(data.prepayBal);
        		$('#transApplAmt').attr('disabled',true);
        	} else  {
        		$('#transApplAmt').val('');
        		$('#transApplAmt').attr('disabled',false);
        	}    	
        });
    */
	
});

/*
 * 페이지 초기화
 */
function pageInit(){
	$("#mainGrid").clearGridData();
	$("#dtlGird").clearGridData();
	
	//빌링조회 disable
	btnDisable('searchDtlBtn');
	btnDisable('saveBtn');
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




function searchGridList(){	
	var param = new Object();
	
	param.soId = $("#searchSoId").val();
	/* if(param.soId == null || param.soId == ''){
		alert('<spring:message code="MSG.M07.MSG00001"/>');
		return;
	} */

   	param.pymAcntId = $("#searchPymAcntId").val();
   	/* if(param.pymAcntId == null || param.pymAcntId == ''){
   		alert('<spring:message code="MSG.M02.MSG00013"/>');
		return;
   	} */

	param.dpstCl = $("#searchDpstCl").val();
	param.prepayOccTp = $("#searchPrepayOccTp").val();

	param.dtTp = $("#searchDtTp").val();
	param.startDt = dateFormatToStringYYYYMMDD($("#searchStartDt").val());
	param.endDt = dateFormatToStringYYYYMMDD($("#searchEndDt").val());
	
	param.prepayProcStat = $("#searchprepayProcStat").val();
   	
	
	$("#mainGrid").setGridParam({
		datatype : "json",
		postData :param    
	});
    
	$("#mainGrid").trigger("reloadGrid");
	
}


function searchDtlGridList(){
	var param = new Object();
	

	//선택된 Row data 가져오기
	var rowId = $("#mainGrid").jqGrid('getGridParam', "selrow" );
	var data = $("#mainGrid").getRowData(rowId);
	
	param.soId = data.soId;
	if(param.soId == null || param.soId == ''){
		alert('<spring:message code="MSG.M07.MSG00001"/>');
		return;
	}

   	param.pymAcntId = $("#searchDtlPymAcntId").val();
   	if(param.pymAcntId == null || param.pymAcntId == ''){
   		alert('<spring:message code="MSG.M02.MSG00013"/>');
		return;
   	}

   	param.searchStartMonth = $("#searchStartMonth").val();
   	param.searchEndMonth = $("#searchEndMonth").val();   	
	
	$("#dtlGird").setGridParam({
		datatype : "json",
		postData :param    
	});
    
	$("#dtlGird").trigger("reloadGrid");
	
}

function setSelectedData(rowId) {	
	var data = $("#mainGrid").getRowData(rowId);

	$("#prepayBal").val(data.prepayBal);
	$("#transApplAmt").val(data.prepayBal);
/* 	
	if ($("input:radio[name='transferPrepayBal']:checked").val() == 'Y') {
		$("#transApplAmt").val(data.prepayBal);
		$('#transApplAmt').attr('disabled',true);
	} else {
		$('#transApplAmt').val();
		$('#transApplAmt').attr('disabled',false);
	}
*/
}

function setSelectedDtlData(rowId) {	
	var data = $("#dtlGird").getRowData(rowId);

	$("#pymAcntId").val(data.pymAcntId);
	$("#unpayAmt").val(data.unpayAmt);

	btnEnable('saveBtn');	
}

function insert() {
	//환불신청 여부 체크
	var chkResult = chkIsRefundApplied();
	if(chkResult) {
 		alert('환불신청된 건은 대체수납신청이 불가능합니다.', '', 'error'); // MSG.M14.MSG00075=환불신청된 건은 대체수납신청이 불가능합니다.
		return false;
	}
	
	var param = new Object();
	

    //선택된 Row Data 가져오기
	var mainRowId = $("#mainGrid").jqGrid('getGridParam', "selrow" );
	var mainData = $("#mainGrid").getRowData(mainRowId);

	//선택된 Row dtlData 가져오기
	var dtlRowId = $("#dtlGird").jqGrid('getGridParam', "selrow" );
	var dtlData = $("#dtlGird").getRowData(dtlRowId);

	param.soId = mainData.soId;	
	param.prepayOccSeqNo = mainData.prepayOccSeqNo;   	
   	param.prepayBal = mainData.prepayBal;    // 대체 신청 가능액 = 잔액
    param.transTp = $("#transTp").val();
   	param.pymAcntId = $("#pymAcntId").val();
   	
   	if ( $("#transTp").val() == '01') { // 선택 청구
   	   	param.billSeqNo = dtlData.billSeqNo; 
   	} else {	// 납부계정별 2 
   	   	param.billSeqNo = null; 
   	}
   	
	if (param) {
       var check = confirm("대체수납 처리하시겠습니까?"); 
       if(!check){
           return;
       }
       console.log (param);
   	   insertAction(param);
	}
   	
   	

/*
    //선택된 Row data 가져오기
	var mainRowId = $("#mainGrid").jqGrid('getGridParam', "selrow" );
	var mainData = $("#mainGrid").getRowData(mainRowId);

	param.transApplTgtNo = mainData.prepayOccSeqNo;

	//선택된 Row data 가져오기
	var dtlRowId = $("#dtlGird").jqGrid('getGridParam', "selrow" );
	var dtlData = $("#dtlGird").getRowData(dtlRowId);
	
	param.soId = dtlData.soId;	
	param.billSeqNo = dtlData.billSeqNo;	
	param.billYymm = dateFormatToStringYYYYMM(dtlData.billYymm);
	
	param.unpayAmt = $("#unpayAmt").val();
   	param.pymAcntId = $("#pymAcntId").val();
   	
   	param.transApplAvlamt = mainData.prepayBal;    // 대체 신청 가능액 = 잔액   	
	param.transApplAmt = $("#transApplAmt").val(); // 대체신청금액

    //param.appntId = param.pymAcntId;               // 신청자ID (납부계정ID)
    //param.appntNm = $("searchDtlAcntNm").val();    // 신청자 명 (납부자명)
    param.applDt = dateFormatToStringYYYYMMDD(dateFormatterUsingDateYYYYMMDD(new Date)); // 신청 일자
	
	param.transApplCl = '01';    // 선수대체신청 구분자
    param.transTp = $("#transTp").val(); 
	if (param) {
       var check = confirm("대체수납 처리하시겠습니까?"); 
       if(!check){
           return;
       }
   	   insertAction(param);
	}
*/
}

function chkIsRefundApplied() {
	var result = false;

	//선택된 Row data 가져오기
	var rowId = $("#mainGrid").jqGrid('getGridParam', "selrow" );
	var data = $("#mainGrid").getRowData(rowId);
	
	$.ajax({
        url:'/charge/payment/advanceReceived/advanceReceivedMng/chkIsRefundApplied.json',
        type:'POST',
        data : {seqNo : data.prepayOccSeqNo},
        dataType: 'json',
        async: false,
        success: function(data){
        	result = data.cnt > 0;
		}
    });

	return result;
}

function insertAction(param){	 

	if(param) {
    	$.ajax({
            type : "post",
            url : '/charge/payment/advanceReceived/advanceReceivedMng/insertAction.json',
            data : param,
            async: true,
            success : function(data) {
				alert('<spring:message code="MSG.M07.MSG00084"/>');
				searchGridList();
            },
    		error: function() {
    		    //에러메세지
    		    alert('<spring:message code="MSG.M10.MSG00005"/>'); // MSG.M10.MSG00005=처리에 실패했습니다. 관리자에게 문의해 주세요.
    		}
        });
	}
	
	/* 
	
	if (param) {
		$.post('/charge/payment/advanceReceived/advanceReceivedMng/insertAction.json', param, function (response) {
			if (response.success == true) {
				alert('<spring:message code="MSG.M07.MSG00084"/>');
				searchGridList(); // 대체 완료 후 mainGrid 조회
			} else {
	            alert('<spring:message code="MSG.M10.MSG00005"/>'); // MSG.M10.MSG00005=처리에 실패했습니다. 관리자에게 문의해 주세요.
			}
		});
	}
 */
/*	
    $.post({
        url:'/charge/payment/advanceReceived/advanceReceivedMng/insertAction.json',
        type:'POST',
        data : param,
        dataType: 'json',
        async: false,
        success: function(data){
            if(data.data === 1) {
                //popInfoDisabled(); //상세정보 초기화

                alert('대체수납 처리완료되었습니다.') 
            }else {
                //에러메세지
                alert('<spring:message code="MSG.M10.MSG00005"/>'); // MSG.M10.MSG00005=처리에 실패했습니다. 관리자에게 문의해 주세요.
            }
        },
        error: function() {
            //에러메세지
            alert('<spring:message code="MSG.M10.MSG00005"/>'); // MSG.M10.MSG00005=처리에 실패했습니다. 관리자에게 문의해 주세요.
        } 
    });
*/
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


<!-- 검색 버튼 -->
<div class="main_btn_box">
	<ntels:auth auth="${menuAuthR}">
		<div class="fr mt10">
			<a id='searchBtn' href="#" class="grey-btn" title='<spring:message code="LAB.M09.LAB00158"/>'><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
	</ntels:auth>
</div>


<!-- 검색부 -->
<table class="writeview">
	<colgroup>
		<col style="width: 8%;">
		<col style="width: 25%;">
		<col style="width: 8%;">
		<col style="width: 25%;">
		<col style="width: 8%;">
		<col style="width: 25%;">
	</colgroup>
	<thead>
		<tr>
           <th><spring:message code="LAB.M07.LAB00003"/><span class="dot">*</span><!-- 사업 --></th>
			<td>
				<select id="searchSoId" name="searchSoId" class="w100p">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M02.LAB00005" /><span class="dot">*</span></th><!-- 납부계정 -->
			<td>
				<div class="inp_date w280">
					<input id="searchAcntNm" name="searchAcntNm" type="text" class="w120" disabled="disabled"/>
					<input id="searchPymAcntId" name="searchPymAcntId" type="text" class="w120" disabled="disabled"/>
					<ntels:auth auth="${menuAuthR}">
						<a id="btnSearchPym"  href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
				</div>
			</td>
			<th><spring:message code="LAB.M08.LAB00166" /></th><!-- 입금구분 -->
			<td>
				<select id="searchDpstCl" name="searchDpstCl" class="w100p">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${depositTp}" var="depositTp" varStatus="status">
						<option value="${depositTp.commonCd}">${depositTp.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M06.LAB00133" /></th><!-- 발생구분 -->
			<td>
				<select id="searchPrepayOccTp" name="searchPrepayOccTp" class="w100p">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${advanceReceivedTp}" var="advanceReceivedTp" varStatus="status">
						<option value="${advanceReceivedTp.commonCd}">${advanceReceivedTp.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<th>
				<select id="searchDtTp" name="searchDtTp" class="w100p">
					<c:forEach items="${dtTp}" var="dtTp" varStatus="status">
						<option value="${dtTp.commonCd}">${dtTp.commonCdNm}</option>
					</c:forEach>
				</select>			
			</th>
			<td>
				<div class="date_box">
					<div class="inp_date w130">
						<input type="text" id="searchStartDt" name="searchStartDt"  class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
					<span class="dash">~</span>
					<div class="inp_date w130">
						<input type="text" id="searchEndDt" name="searchEndDt" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</div>
			</td>
			<th><spring:message code="LAB.M10.LAB00096" /></th><!-- 처리상태 -->
			<td>
				<select id="searchprepayProcStat" name="searchprepayProcStat" class="w100p">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${traetStat}" var="traetStat" varStatus="status">
						<option value="${traetStat.commonCd}">${traetStat.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title">선수금발생내역</h4>
	</div>
	<!-- <div class="fr mt10">
		<a id='disableMaskBtn' class="grey-btn" href="#" title='파일다운로드'>파일다운로드</a>
	</div> -->
</div>
<div id='gridDiv'>
	<table id="mainGrid" class="w100p"></table>
	<div id="mainGridPager"></div>
</div>



<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title">청구내역</h4>
	</div>
	<div class="fr mt10">
	<ntels:auth auth="${menuAuthR}">
		<div class="fr mt10">
			<a id='searchDtlBtn' href="#" class="grey-btn" title='<spring:message code="LAB.M09.LAB00158"/>'><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
	</ntels:auth>
	</div>
</div>
<div>
	<table class="writeview">
		<colgroup>
			<col style="width: 8%;">
			<col style="width: 25%;">
			<col style="width: 8%;">
			<col style="width: 25%;">
			<col style="width: 8%;">
			<col style="width: 25%;">
		</colgroup>
		
		<tbody>
			<tr>
				<th>대체처리구분<span class="dot">*</span></th>
				<td>	
					<select id="transTp" name=transTp class="w100p">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${transTp}" var="transTp" varStatus="status">
							<option value="${transTp.commonCd}">${transTp.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
                <th><spring:message code="LAB.M10.LAB00033"/><span class="dot">*</span><!-- 청구년월 --></th>
				<td>				
					<div class="date_box">
						<div class="inp_date w130">
                      <input type="text" id="searchStartMonth" name="searchYyyyMm" class="month-picker" readonly="readonly"/>
							<a href="#" class="btn_cal"></a>
						</div>
						<span class="dash">~</span>
						<div class="inp_date w130">
                      <input type="text" id="searchEndMonth" name="searchYyyyMm" class="month-picker" readonly="readonly"/>
							<a href="#" class="btn_cal"></a>
						</div>
					</div>
				</td>
	            <th><spring:message code="LAB.M02.LAB00005"/><!-- 납부계정 --><span class="dot">*</span></th>
	            <td>
					<div class="inp_date w280">
						<input id="searchDtlAcntNm" name="searchDtlAcntNm" type="text" class="w120" disabled="disabled"/>
						<input id="searchDtlPymAcntId" name="searchDtlPymAcntId" type="text" class="w120" disabled="disabled"/>
						<ntels:auth auth="${menuAuthR}">
							<a id="btnDtlSearchPym"  href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
						</ntels:auth>
					</div>
	            </td>
			</tr>
		</tbody>
	</table>
</div>
<div id='gridDiv'>
	<table id="dtlGird" class="w100p"></table>
	<div id="dtlGirdPager"></div>
</div>



<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title">수납대체</h4>
	</div>
</div>
<div id="regInfo">
	<table class="writeview">
		<colgroup>
			<col style="width: 10%;">
			<col style="width: 40%;">
			<col style="width: 10%;">
			<col style="width: 40%;">
		</colgroup>
		<tbody>
			<tr>
				<th>선수금잔액</th>
				<td>				
					<div class="inp_date w280">
						<input id="prepayBal" type="text" class="w245" disabled/>
					</div>
				</td>
				<th>대체신청금액</th>
				<td>				
					<div class="inp_date w280">
						<input id="transApplAmt" type="text" class="w245" disabled/>
					</div>
				</td>
			</tr>			
			<tr>
				<th>납부계정</th>
				<td>				
					<div class="inp_date w280">
						<input id="pymAcntId" type="text" class="w245" disabled/>
					</div>
				</td>
				<th>미납금액</th>
				<td>				
					<div class="inp_date w280">
						<input id="unpayAmt" type="text" class="w245" disabled/>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
</div>


<!-- 하단 버튼부 -->
<div class="main_btn_box">
	<div class="fr">
			<span id="commonBtn">
			<ntels:auth auth="${menuAuthC}">
			<a id="saveBtn" class="grey-btn" title='<spring:message code="LAB.M09.LAB00048"/>' href="#"><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
			</ntels:auth>
		</span>
	</div>
</div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>

