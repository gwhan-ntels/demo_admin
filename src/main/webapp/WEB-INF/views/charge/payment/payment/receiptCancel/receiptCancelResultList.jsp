<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {
	
	//등록자 조회 - 수납취소자
	$('#btnSearchCust').on('click',function (e) {
		var url="/system/common/common/userSearchMng/userSearchPopup.ajax";
		var param = new Object();
		param.popType = "m";            //팝업타입 m:모달 o:일반
		param.returnId1 = "searchCustNm";
		param.returnId2 = "searchCustId";
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
	
	//납부계정 조회
	$('#btnSearchPymAcnt').on('click',function (e) {
		var url="/system/common/common/pymAcntSearch/pymAcntPopup.ajax";
		var param = new Object();
		param.popType = "m";            //팝업타입 m:모달 o:일반
		param.returnId1 = "searchPymAcntNm";
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
	
	var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
	$("#searchStatDt").val(dateFormatterUsingDateYYYYMMDD(new Date));
	$("#searchEndDt").val(dateFormatterUsingDateYYYYMMDD(new Date));
	
	//그리드 처리
	$("#receiptCancelGrid").jqGrid({
		url : '/charge/payment/payment/receiptCancel/receiptCancelResultListAction.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
		    { label: 'soId', name: 'soId',hidden:true},
		    { label: '<spring:message code="LAB.M07.LAB00338" />', name: 'cnclDttm', formatter:stringTypeFormatterYYYYMMDDHH24MISS, width : 100, align:"center"},	//수납취소일자
		    { label: '<spring:message code="LAB.M10.LAB00095" />', name: 'cnclEmpNm', width : 100, align:"center"},		     //취소자명
		    { label: '<spring:message code="LAB.M02.LAB00006" />', name: 'pymAcntId', width : 100, align:"center"},	         //남부계정ID
		    { label: '<spring:message code="LAB.M02.LAB00008" />', name: 'acntNm', width : 150, align:"center"},			 //납부계정명
		    { label: '<spring:message code="LAB.M10.LAB00033" />', name: 'billYymm', formatter:stringTypeFormatterYYYYMM, width : 100, align:"center"},			//청구년월
		    { label: '<spring:message code="LAB.M08.LAB00173" />', name: 'dpstDt', formatter:stringTypeFormatterYYYYMMDD, width : 100, align:"center"},			//입금일자
			{ label: '<spring:message code="LAB.M08.LAB00127" />', name: 'transDt', formatter:stringTypeFormatterYYYYMMDD, width : 100, align:"center"},		//이체일
		    { label: '<spring:message code="LAB.M08.LAB00175" />', name: 'dpstProcDt', formatter:stringTypeFormatterYYYYMMDD, width : 100, align:"center"},		//입금처리일자
		    { label: '<spring:message code="LAB.M07.LAB00243" />', name: 'payProcDt', formatter:stringTypeFormatterYYYYMMDD, width : 100, align:"center"},		//수납처리일자
		    { label: '<spring:message code="LAB.M08.LAB00166" />', name: 'dpstCl', width : 90, align:"center"},			    //입금구분
		    { label: '<spring:message code="LAB.M08.LAB00167" />', name: 'dpstClNm', width : 100},			                //입금구분명
		    { label: '<spring:message code="LAB.M07.LAB00236" />', name: 'payTp', width : 90, align:"center"},				//수납구분
		    { label: '<spring:message code="LAB.M07.LAB00339" />', name: 'payTpNm', width : 100},			                //수납구분명
		    { label: '<spring:message code="LAB.M10.LAB00031" />', name: 'billAmt', formatter:'number', width : 100, align:"right"},			//청구금액
		    { label: '<spring:message code="LAB.M08.LAB00125" />', name: 'preRcptAmt', formatter:'number', width : 100, align:"right"},			//이전수납금액
		    { label: '<spring:message code="LAB.M07.LAB00237" />', name: 'rcptAmt', formatter:'number', width : 100, align:"right"},			//수납금액
		    { label: '<spring:message code="LAB.M07.LAB00240" />', name: 'pymSeqNo', width : 120, align:"center"},			//수납일련번호
		    { label: '<spring:message code="LAB.M10.LAB00040" />', name: 'billDt', width : 100, align:"center"},			//청구일자
		    { label: '<spring:message code="LAB.M10.LAB00039" />', name: 'billSeqNo', width : 150, align:"center"},			//청구일련번호
		    { label: '<spring:message code="LAB.M03.LAB00083" />', name: 'regrId', width : 100, align:"center"},			//등록자ID
		    { label: '<spring:message code="LAB.M03.LAB00079" />', name: 'regDate', formatter:dateTypeFormatterYYYYMMDDHH24MISS, width : 150, align:"center"}    //등록일
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 250,
		sortable : true,
		rowList:[10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 10,
        pager: "#receiptCancelGridPager",
        onCellSelect : function(rowid, index, contents, event){
        	setSelectedData(rowid);
        },
       	loadComplete : function () {
  	      	$("#receiptCancelGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#receiptCancelGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});

	//그리드 화면 재조정
	$(window).resize(function() {
		$("#receiptCancelGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	
	
	//조회 버튼 이벤트
    $('#searchBtn').on('click',function (e) {
		searchGridList();
    });
	

});


function searchGridList(){
	
	var param = new Object();
	param.soId = $("#searchSoId").val();
	param.searchCustId = $("#searchCustId").val();
	param.searchDateTp = $("#searchDateTp").val();
	param.searchStatDt = dateFormatToStringYYYYMMDD($("#searchStatDt").val());	
	param.searchEndDt = dateFormatToStringYYYYMMDD($("#searchEndDt").val());
	param.searchPymAcntId = $("#searchPymAcntId").val();
	
	console.log(param);
	
	$("#receiptCancelGrid").setGridParam({
		datatype : "json",
		postData :param    
	});
    
	$("#receiptCancelGrid").trigger("reloadGrid");
	
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
		<col style="width:10%;">
		<col style="width:40%;">
		<col style="width:10%;">
		<col style="width:40%;">
	</colgroup>
	<thead>
		<tr>
			<th><spring:message code="LAB.M07.LAB00003" /></th><!-- 사업 -->
			<td>
				<select id="searchSoId" name="searchSoId" class="w150">
					<%-- <option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option> --%>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M07.LAB00336" /></th><!-- 수납취소자 -->
			<td>
				<div class="inp_date w280">
					<input id="searchCustNm" type="text" class="w120" readonly="readonly" />
					<input id="searchCustId" type="text" class="w120" readonly="readonly" />
					<ntels:auth auth="${menuAuthR}">
						<a id="btnSearchCust"  href="#" class="search"><spring:message code="LAB.M01.LAB00047"/></a>
					</ntels:auth>
				</div>
			</td>
		</tr>
		<tr>
			<th>
				<select id="searchDateTp" name="searchDateTp" class="w100p"><!-- 입금날짜타입 -->
					<c:forEach items="${dateTp}" var="dateTp" varStatus="status">
                        <option value="${dateTp.commonCd}">${dateTp.commonCdNm}</option>
                    </c:forEach>
				</select>			
			</th>
			<td>
				<div class="date_box">
					<div class="inp_date w130">
						<input type="text" id="searchStatDt" name="searchStatDt"  class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
					<span class="dash">~</span>
					<div class="inp_date w130">
						<input type="text" id="searchEndDt" name="searchEndDt" class="datepicker1" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</div>
			</td>
			<th><spring:message code="LAB.M02.LAB00005" /></th><!-- 납부계정 -->
			<td>
				<div class="inp_date w280">
					<input id="searchPymAcntNm" type="text" class="w120" readonly="readonly" />
					<input id="searchPymAcntId" type="text" class="w120" readonly="readonly" />
					<ntels:auth auth="${menuAuthR}">
						<a href="#" id="btnSearchPymAcnt" name="btnSearchPymAcnt" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
					</ntels:auth>
				</div>
			</td>
		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M07.LAB00337" /></h4>
	</div>
	<div class="fr mt10">
	<%-- 
		<ntels:auth auth="${menuAuthP}">
			<a id="btnExcelDown" class="grey-btn" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
		</ntels:auth>
		 --%>
	</div>
</div>
<div id='gridDiv'>
	<table id="receiptCancelGrid" class="w100p"></table>
	<div id="receiptCancelGridPager"></div>
</div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>

