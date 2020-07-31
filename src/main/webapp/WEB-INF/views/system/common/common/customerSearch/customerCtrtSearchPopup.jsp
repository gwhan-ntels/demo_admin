<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {
	
	init();
	
	//그리드 선언
	$("#custPopupGrid").jqGrid({
		url : '/system/common/common/customerSearch/getCustomerCtrtListAction.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
			
		},
		colModel: [
			{ label: 'soId' , name: 'SO_ID', hidden:true,width : 0},
			{ label: '<spring:message code="LAB.M07.LAB00003"/>', name: 'SO_NM', width : 100, align:"left"},
			{ label: '<spring:message code="LAB.M01.LAB00032"/>', name: 'CTRT_ID', width : 100, align:"center"},
		    { label: '<spring:message code="LAB.M01.LAB00050"/>', name: 'CUST_NM', width : 180, align:"left"},
		    { label: '<spring:message code="LAB.M02.LAB00006"/>', name: 'PYM_ACNT_ID', width : 130, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M02.LAB00008"/>', name: 'ACNT_NM', width : 130, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M09.LAB00065"/>', name: 'MTEL_NO', width : 130, align:"left",sortable:false}
		    ],
	    viewrecords: true,
		shrinkToFit:false,
		height: 300,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "custInfoList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 10,
        pager: "#custPopupGridPager",
        ondblClickRow : function(rowid){
        	selectCustInfo(rowid);
        },
        loadComplete : function (data) {
        	$("#custPopupGrid").setGridWidth($('#popupOrderProcessDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        	
       		if( data.totalCount != null && data.totalCount > 0){
       			//선택처리
       		  $("#custPopupGrid").setSelection(1, false);
       		}
        },
    	sortable: { update: function(permutation) {
    		$("#custPopupGrid").setGridWidth($('#popupOrderProcessDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});
	
	$("#custPopupGrid").setGridWidth($('#popupOrderProcessDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	//검색 버튼
   	$('#btnSearchCustPopup').on('click',function (e) {
		  	if($("#btnSearchCustPopup").hasClass('not-active')){
				return;
			}
		  	custSearchPopup('S');
		}
    );
	
	$("#btnCustSearchSelect").on('click',function(e){
			if($("#btnCustSearchSelect").hasClass('not-active')){
				return;
			}
			var rowid  = $("#custPopupGrid").jqGrid('getGridParam', 'selrow');
			if(rowid == null){
				alert('<spring:message code="MSG.M03.MSG00015"/>');
	  			return;
			}
			selectCustInfo(rowid);	
		
		}
   	);
   	
  	//고객명 키 이벤트
    $( "#txtCustSearchCustNm" ).keypress(function(event) {
		  	if($("#btnSearchCustPopup").hasClass('not-active')){
				return;
			}
			if(event.keyCode == 13){
				custSearchPopup('S');
			}
    	}
    );
})

function custSearchPopup(mode){
	// if(mode=='I'){
	// 	if($('#txtCustSearchCustNm').val() == ''){
	// 		$('#txtCustSearchCustNm').focus();
	// 		return;
	// 	}
	// }else{
 // 		if($('#txtCustSearchCustNm').val() == '' &&
	// 			$('#txtCustSearchCustId').val() == '' &&
	// 			$('#txtCustSearchSvcTelNo').val() == '' &&
	// 			$('#txtCustSearchCtrtId').val() == '' &&
	// 			$('#txtCustSearchCorpReg').val() == ''){
	// 		alert('<spring:message code="MSG.M01.MSG00016"/>');
	// 		$('#txtCustSearchCustNm').focus();
	// 		return;
	// 	}
 		
 // 		if($('#txtCustSearchCustNm').val() == '' &&
	// 			$('#txtCustSearchCustId').val() == '' &&
	// 			$('#txtCustSearchSvcTelNo').val() == '' &&
	// 			$('#txtCustSearchCtrtId').val() == '' &&
	// 			$('#txtCustSearchCorpReg').val() != ''){
	// 		alert('<spring:message code="MSG.M01.MSG00016"/>');
	// 		$('#txtCustSearchCustNm').focus();
	// 		return;
	// 	}
	// }

	if(mode == 'I' && $('#txtCustSearchCustNm').val() == ''){
		$('#txtCustSearchCustNm').focus();
	}
	$("#custPopupGrid").clearGridData();
	$("#custPopupGrid").setGridParam({
		url : '/system/common/common/customerSearch/getCustomerCtrtListAction.json',
		datatype : 'json',
		mtype: 'POST',
  	    postData : {
  			soId : $('#selCustSearchCondSoId').val(),
  			custNm : $('#txtCustSearchCustNm').val(),
  			isUnmaskYn : $('#popupInputIsUnmaskYn').val()
		}
	});
	      	
   	$("#custPopupGrid").trigger("reloadGrid");
}

function selectCustInfo(rowid){
	var data = $("#custPopupGrid").getRowData(rowid);

	$('#' + $('#popupOuputSoId').val()).val(data.SO_ID);
	$('#' + $('#popupOuputSoId').val()).selectmenu("refresh");
	$('#' + $('#popupOuputCustNm').val()).val(data.CUST_NM);
	$('#' + $('#popupOuputCustId').val()).val(data.CUST_ID);
	$('#' + $('#popupOuputCtrtId').val()).val(data.CTRT_ID);
	$("#btnCustSearchClose").trigger('click');
	if (typeof customerSearchCallback == 'function') { 
		customerSearchCallback();  
	}
}

/*
 * 화면 초기화
 */
function init(){
	var soId = "<c:out value='${INPUT_SO_ID}'/>";
	var custNm = "<c:out value='${INPUT_CUST_NM}'/>";
	var pymAcntId = "<c:out value='${INPUT_PYM_ACNT_ID}'/>";
	$('#selCustSearchCondSoId').val(soId);
	$('#selCustSearchCondSoId').selectmenu({});
	$('#selCustSearchCondSoId').selectmenu("refresh");
	$('#txtPymSearchCustNm').val(custNm);
	$('#txtPymSearchAcntId').val(pymAcntId);
}

</script>
<div style="width:1200px; height:550px;" >
<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M01.LAB00047"/></div>
	<a href="#" id="btnCustSearchClose" class="close">Close</a>
</div>
<!--// title -->

<!-- center --> 
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M01.LAB00047"/></h4>
		</div>
		<div class="fr">
			<a href="#"class="grey-btn" id="btnSearchCustPopup" ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
	</div>            
	                
	<table class="writeview">
		<colgroup>
			<col style="width:15%;">
			<col style="width:35%;">
			<col style="width:15%;">
			<col style="width:35%;">
		</colgroup>
		<thead> 
			<tr>
				<th><spring:message code="LAB.M07.LAB00003"/></th>
				<td>
					<select class="w170" id="selCustSearchCondSoId">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}" <c:if test="${soAuthList.so_id eq session_user.soId}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
						</c:forEach>
					</select>  
				</td>
				<th><spring:message code="LAB.M01.LAB00050"/></th>
				<td>
					<input type="text" id="txtCustSearchCustNm" name="txtCustSearchCustNm" class="w100p">
				</td>
			</tr>
		</thead>
	</table> 
	
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M01.LAB00049"/></h4>
		</div>
	</div>
	<div id="popupOrderProcessDiv" class="w1100">
		<table id="custPopupGrid" class="w100p"></table>
		<div id="custPopupGridPager"></div>
	</div>

	<!--// center -->
	<div class="btn_box">
	      <a class="grey-btn" id="btnCustSearchSelect" href="#" ><span class="confirm_icon"><spring:message code="LAB.M07.LAB00195" /></span></a>
	      <a class="grey-btn close" id="btnCustSearchClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
	</div>
</div>
</div>

<input id="popupInputIsUnmaskYn" value="${INPUT_IS_UNMASK_YN}" hidden /> 
<input id="popupOuputSoId" value="${OUTPUT_SO_ID}" hidden /> 
<input id="popupOuputCustNm" value="${OUTPUT_CUST_NM}" hidden /> 
<input id="popupOuputCustId" value="${OUTPUT_CUST_ID}" hidden /> 
<input id="popupOuputCtrtId" value="${OUTPUT_CTRT_ID}" hidden />