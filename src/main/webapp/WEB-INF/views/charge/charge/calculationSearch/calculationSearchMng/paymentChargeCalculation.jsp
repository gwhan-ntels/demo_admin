<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<style type="text/css">
table.ui-datepicker-calendar { display:none; }
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

var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';

$(document).ready(function() {
	
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
	      }).datepicker("setDate", new Date());  
	   }
	
	$('#searchYyyyMm').datepicker('setDate', new Date());
	
	//납부계정 조회
	$('#btnSearchPym').on('click',function (e) {
		if($("#btnSearchPym").hasClass('not-active')){
			return;
		}
		openCustPymSearchPopup();
		/* var url="/system/common/common/pymAcntSearch/pymAcntPopup.ajax";
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
		});  */
	});
	
	
	$("#chrgeInfoGrid").jqGrid({
		url:'getChrgeInfoListAction.json?',
		datatype: "local",
		mtype:"POST",
		postData: {},
		colModel: [
			{ label: 'soId', name: 'soId', hidden:true, width:0},
			{ label: 'billCycl', name: 'billCycl', hidden:true, width:0},
			{ label: 'prodCd', name: 'prodCd', hidden:true, width:0},
			{ label: '<spring:message code="LAB.M07.LAB00006" />', name: 'soNm', width : 100, align:"center"}, //사업명
			{ label: '<spring:message code="LAB.M10.LAB00033" />', name: 'billYymm', formatter:stringTypeFormatterYYYYMM, width : 100, align:"center"}, //청구년월
			{ label: '<spring:message code="LAB.M09.LAB00240" />', name: 'clcWrkNo', width : 100, align:"center"}, //작업번호
			{ label: '<spring:message code="LAB.M02.LAB00005" />', name: 'pymAcntId', width : 100, align:"center"}, //납부계정
			{ label: '<spring:message code="LAB.M01.LAB00046" />', name: 'custId', width : 100, align:"center"}, //고객ID
			{ label: '<spring:message code="LAB.M01.LAB00050" />', name: 'custNm', width : 100, align:"center"}, //고객명
			{ label: '<spring:message code="LAB.M01.LAB00032" />', name: 'ctrtId', width : 100, align:"center"}, //계약ID
			{ label: '<spring:message code="LAB.M07.LAB00130" />', name: 'prodNm', width : 100, align:"left"}, //상품명
			{ label: '<spring:message code="LAB.M07.LAB00342" />', name: 'totUseAmt', formatter:'integer', width : 100, align:"right"} //사용액합계
		],
		sortable:true,
		viewrecords: true,
		height: 220,
		rowNum:10,                   //한번에 노출되는 row 수
		rowList:[5,10,20,30,50],    //선택시 노출되는 row 수
		pager: '#chrgeInfoGridPager',
		onCellSelect : function(rowId, index, contents, event){
			setSelectedData(rowId);
		},
       
		loadComplete: function(obj){
	
	   		$("#chrgeInfoGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		},
	});
   
	$("#chrgeInfoGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
   
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#chrgeInfoGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	

	//그리드2
	$("#dtlGrid").jqGrid({
	           
		url:'getDtlListAction.json?',
		datatype: "local",
		mtype:"POST",
		postData: {},
		//multiselect: true,
		
		colModel: [ 
			//차후 안쓰는 필드는 hidden처리
			{ label: '<spring:message code="LAB.M10.LAB00033" />', name: 'billYymm', formatter:stringTypeFormatterYYYYMM, width : 100, align:"center", sortable:false}, //청구년월
			{ label: '사용년월', name: 'useYymm', formatter:stringTypeFormatterYYYYMM, width : 100, align:"center", sortable:true},  
			{ label: '상품명', name: 'prodNm', width : 100, align:"center"},
			{ label: '서비스구성ID', name: 'svcCmpsId', width : 100, align:"center"},  
			{ label: '상품구성ID', name: 'prodCmpsId', width : 100, align:"center"},  
			{ label: '과금항목ID', name: 'rateItmCd', width : 100, align:"center"},  
			{ label: '과금항목명', name: 'rateItmNm', width : 100, align:"left"},  
			{ label: '사용금액', name: 'useAmt', formatter: 'integer',  width : 100, align:"right"},  
			{ label: '사용일', name: 'useCnt', formatter: 'integer', width : 100, align:"right"},  
		],
		height: 220,
		onSelectRow: function(row, isSelected) {
		},
		loadComplete: function(obj){	
	   		$("#dtlGrid").setGridWidth($('#dtlGridlDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		},
	});
   
	$("#dtlGrid").setGridWidth($('#dtlGridlDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
   
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#dtlGrid").setGridWidth($('#dtlGridlDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	
	
	
	
	//조회
	$('#searchBtn').click(function(){
		searchGridList();
	});
	
	//상세조회	 param전달 
	function setSelectedData(rowId) {	
		var data = $("#chrgeInfoGrid").getRowData(rowId);
		getSelectedData(data);
	}

	//상세 리스트 조회
	function getSelectedData(row) {
		var param = new Object();
		param.soId = row.soId;
		param.billYymm = dateFormatToStringYYYYMM(row.billYymm);
		param.billCycl = row.billCycl;
		param.clcWrkNo = row.clcWrkNo;
		param.pymAcntId = row.pymAcntId;
		param.custId = row.custId;
		param.ctrtId = row.ctrtId;

		$("#dtlGrid").setGridParam({
			datatype: "json",
			postData : param             
		});
      
		$("#dtlGrid").trigger("reloadGrid");		
	}
	
});


function searchGridList(){
	
	if ($("#searchSoId").val() == ''){
		alert('<spring:message code="MSG.M07.MSG00002"/>');
		return;
	}
	if($("#searchPymAcntId").val() == '' || $("#searchPymAcntId").val() == null){
		alert('<spring:message code="MSG.M02.MSG00013"/>');
		return;
	}
	
	var condYymm = caldate(31, $("#searchYyyyMm").val()+"01");
	console.info("condYymm"+condYymm);
		
	var param = new Object();
	param.soId = $("#searchSoId").val();
	param.billYymm = dateFormatToStringYYYYMM(condYymm);
	param.pymAcntId = $("#searchPymAcntId").val();
	
	console.log(JSON.stringify(param));
	
	$("#chrgeInfoGrid").setGridParam({
		datatype : "json",
		postData :param    
	});
    
	$("#chrgeInfoGrid").trigger("reloadGrid");
	
	$("#dtlGrid").clearGridData();
	
}

/*
 * 납부조회팝업
 */
function openCustPymSearchPopup(){
	$.ajax({
	    type : "post",
	    url : '/system/common/common/pymAcntSearch/pymAcntSearchPopup.ajax',
	    data : {
	         inputSoId : $("#searchSoId").val()   //input SO Id
	        ,inputCustNm : $('#searchAcntNm').val()   //input Customer Name
	        ,inputPymAcntId : $('#searchPymAcntId').val()
	        ,inputIsUnmaskYn : $('#isUnmaskYn').val() //마스크 처리 해제 Y
	        ,outputSoId : 'searchSoId'            //output SO ID Select
	        ,outputCustNm : 'condCustNm'            //output Customer Name Text
	        ,outputCustId : 'condCustId'            //output Customer ID Text
	        ,outputPymAcntId : 'searchPymAcntId'      //output Payment ID Text
	        ,outputPymAcntNm : 'searchAcntNm'      //output Payment Nm Text
        	,outputSoId : 'condSo'      		//output Payment Nm Text
	    },
	    async: true,
	    success : function(data) {
	        var html = data;
	        $("#popup_dialog").html(html);
	    },      
	    complete : function(){
	        wrapWindowByMask(); // 팝업 오픈
	        $("#txtPymSearchCustNm").focus(); //오픈 후 focus위치
	    }
	});
}

/**
 * conDate의 day만큼 전날의 날짜 return
 */
function caldate(day, condDate){
	 
	 var caledmonth, caledday, caledYear;
	 var loadDt = new Date(condDate);
	 var v = new Date(Date.parse(loadDt) - day*1000*60*60*24);
	 
	 caledYear = v.getFullYear();
	 
	 if( v.getMonth() < 9 ){
	  caledmonth = '0'+(v.getMonth()+1);
	 }else{
	  caledmonth = v.getMonth()+1;
	 }
	 if( v.getDate() < 9 ){
	  caledday = '0'+v.getDate();
	 }else{
	  caledday = v.getDate();
	 }
	 return caledYear+'-'+caledmonth+'-'+caledday;
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
		<col style="width:5%;">
		<col style="width:25%;">
		<col style="width:5%;">
		<col style="width:30%;">
		<col style="width:5%;">
		<col style="width:30%;">
	</colgroup>
	<thead>
		<tr>
			<th><spring:message code="LAB.M07.LAB00003" /><span class="dot">*</span></th>
			<td>
				<select id="searchSoId" class="w150">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M10.LAB00033" /><span class="dot">*</span></th><!-- 청구년월 -->
			<td>
				<div class="date_box">
					<div class="inp_date w150">
                        <input type="text" id="searchYyyyMm" name="searchYyyyMm" class="month-picker" readonly="readonly"/>
                        <a href="#" class="btn_cal"></a>
                    </div>
				</div>
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
		</tr>
	</thead>
</table> 


<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M08.LAB00201" /></h4><!-- 요금내역 -->
	</div>
</div>
<div id='gridDiv'>
	<table id="chrgeInfoGrid" class="w100p"></table>
	<div id="chrgeInfoGridPager"></div>
</div>

<div class="main_btn_box">
    <div class="fl">
        <h4 class="sub_title">요금내역상세</h4>
    </div>
</div>
	
<div id="dtlGridlDiv">
    <table id="dtlGrid" class="w100p"></table>
	<div id="detailGridPager"></div>
</div>

<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>

