<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {

	var currDate = new Date();
	var searchEndDt = dateFormatterUsingDateYYYYMMDD(currDate);
	currDate.setDate(currDate.getDate()-7);
	var searchStartDt = dateFormatterUsingDateYYYYMMDD(currDate);

	$("#searchStartDt").val(searchStartDt);
	$("#searchEndDt").val(searchEndDt);

	
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
	
	$("#advanceReceivedGrid").jqGrid({
		url:'/charge/payment/advanceReceived/advanceReceivedMng/getPrepayOccInfoListAction.json?',
		datatype: "local",
		mtype:"POST",
		postData: {},
		colModel: [
			//차후 안쓰는 필드는 hidden처리
			{ label: '<spring:message code="LAB.M06.LAB00134" />', name: 'prepayOccSeqNo', width : 100, align:"center"}, 	//발생일련번호
			{ label: '<spring:message code="LAB.M06.LAB00133" />', name: 'prepayOccTpNm', width : 130, align:"left"}, 	//발생구분
			{ label: '<spring:message code="LAB.M06.LAB00136" />', name: 'prepayOccResnNm', width : 130, align:"left"}, 	//발생사유
			{ label: '<spring:message code="LAB.M02.LAB00006" />', name: 'pymAcntId', width : 100, align:"center"}, 	//납부계정ID
			{ label: '<spring:message code="LAB.M02.LAB00008" />', name: 'acntNm', width : 150, align:"center"}, 	//납부계정명
			{ label: '<spring:message code="LAB.M07.LAB00344" />', name: 'prepayOccDttm', width : 120, formatter:stringTypeFormatterYYYYMMDDHH24MISS, align:"center"}, 	//선수금발생일시
			{ label: '<spring:message code="LAB.M08.LAB00173" />', name: 'dpstDt', width : 100, formatter:stringTypeFormatterYYYYMMDD, align:"center"}, 	//입금일자
			{ label: '<spring:message code="LAB.M08.LAB00127" />', name: 'transDt', width : 100, formatter:stringTypeFormatterYYYYMMDD, align:"center"}, 	//이체일
			{ label: '<spring:message code="LAB.M06.LAB00135" />', name: 'prepayOccAmt', width : 100, formatter: 'number', align:"right"}, 	//발생금액
			{ label: '<spring:message code="LAB.M03.LAB00088" />', name: 'prepayTransAmt', width : 100, formatter: 'number', align:"right"}, 	//대체금액
			{ label: '<spring:message code="LAB.M09.LAB00040" />', name: 'prepayBal', width : 100, formatter: 'number', align:"right"}, 	//잔액
			{ label: '<spring:message code="LAB.M10.LAB00096" />', name: 'prepayProcStatNm', width : 100, align:"center"}, 	//처리상태
			{ label: '<spring:message code="LAB.M08.LAB00166" />', name: 'dpstClNm', width : 100, align:"center"}, 	//입금구분
			{ label: '<spring:message code="LAB.M03.LAB00084" />', name: 'regrId', width : 100, align:"center"}, 	//등록자명
			{ label: '<spring:message code="LAB.M03.LAB00079" />', name: 'regDate', formatter:dateTypeFormatterYYYYMMDDHH24MISS, width : 130, align:"center"}, 	//등록일
			{ label: '<spring:message code="LAB.M10.LAB00103" />', name: 'chgrId', width : 100, align:"center"}, 	//최종수정자명
			{ label: '<spring:message code="LAB.M10.LAB00104" />', name: 'chgDate', formatter:dateTypeFormatterYYYYMMDDHH24MISS, width : 130, align:"center"}, 	//최종수정일
/* 			{ label: '<spring:message code="LAB.M06.LAB00137" />', name: 'prepayOccTp', width : 100, align:"center"}, 	//발생구분코드
			{ label: '<spring:message code="LAB.M10.LAB00105" />', name: 'prepayProcStat', width : 100, align:"center"}, 	//처리상태코드
			{ label: '<spring:message code="LAB.M08.LAB00202" />', name: 'dpstCl', width : 100, align:"center"}, 	//입금구분코드
			{ label: '<spring:message code="LAB.M06.LAB00138" />', name: 'prepayOccResn', width : 100, align:"center"}, 	//발생사유코드
			{ label: '<spring:message code="LAB.M03.LAB00083" />', name: 'regrId', width : 100, align:"center"}, 	//등록자ID
			{ label: '<spring:message code="LAB.M10.LAB00106" />', name: 'chgrId', width : 100, align:"center"} 	//최종수정자ID */
			
		],
		sortable:true,
		viewrecords: true,
		height: 400,
		rowNum:10,                   //한번에 노출되는 row 수
		rowList:[5,10,20,30,50],    //선택시 노출되는 row 수
		pager: '#advanceReceivedGridPager',
		onCellSelect : function(rowId, index, contents, event){
		},
       
		loadComplete: function(obj){
	
	   		$("#advanceReceivedGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		},
	});
   
	$("#advanceReceivedGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
   
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#advanceReceivedGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	
	//조회
	$('#btnSearch').click(function(){
		searchGridList();
	});
	
});


function searchGridList(){
	var param = new Object();
	
	param.soId = $("#searchSoId").val();
	if(param.soId == null || param.soId == ''){
		alert('<spring:message code="MSG.M07.MSG00001"/>');
		return;
	}

   	param.pymAcntId = $("#searchPymAcntId").val();
   	if(param.pymAcntId == null || param.pymAcntId == ''){
   		alert('<spring:message code="MSG.M02.MSG00013"/>');
		return;
   	}

	param.dpstCl = $("#searchDpstCl").val();
	param.prepayOccTp = $("#searchPrepayOccTp").val();

	param.dtTp = $("#searchDtTp").val();
	param.startDt = dateFormatToStringYYYYMMDD($("#searchStartDt").val());
	param.endDt = dateFormatToStringYYYYMMDD($("#searchEndDt").val());
	
	param.prepayProcStat = $("#searchprepayProcStat").val();
	
	$("#advanceReceivedGrid").setGridParam({
		datatype : "json",
		postData :param    
	});
    
	$("#advanceReceivedGrid").trigger("reloadGrid");
	
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
			<a id='btnSearch' href="#" class="grey-btn" title='<spring:message code="LAB.M09.LAB00158"/>'><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
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
					<c:forEach items="${dpstCl}" var="dpstCl" varStatus="status">
						<option value="${dpstCl.commonCd}">${dpstCl.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M06.LAB00133" /></th><!-- 발생구분 -->
			<td>
				<select id="searchPrepayOccTp" name="searchPrepayOccTp" class="w100p">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${prepayOccTp}" var="prepayOccTp" varStatus="status">
						<option value="${prepayOccTp.commonCd}">${prepayOccTp.commonCdNm}</option>
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
					<c:forEach items="${prepayOccStat}" var="prepayOccStat" varStatus="status">
						<option value="${prepayOccStat.commonCd}">${prepayOccStat.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M07.LAB00343" /></h4><!-- 선수금발생내역 -->
	</div>
	<div class="fr mt10">
		<!-- <a id='disableMaskBtn' class="grey-btn" href="#" title='파일다운로드'>파일다운로드</a> -->
	</div>
</div>
<div id='gridDiv'>
	<table id="advanceReceivedGrid" class="w100p"></table>
	<div id="advanceReceivedGridPager"></div>
</div>


<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>

