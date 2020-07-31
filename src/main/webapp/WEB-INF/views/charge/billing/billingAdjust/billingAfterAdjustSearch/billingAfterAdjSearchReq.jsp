<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {
	
    setBasicInfo(); //기본정보 셋팅
    
    var param = checkInput();
    
    console.info(JSON.stringify(param));

    //그리드 처리
    $("#afterAdjRegiditTable").jqGrid({
        url : '/charge/billing/billingAdjust/billingAfterAdjustSearch/getAfterAdjTgtList.json?',
        datatype : 'json',
        mtype: 'POST',
        postData : param,
        colModel: [
            { label: '<spring:message code="LAB.M01.LAB00050" />', name: 'custNm', width : 120, align:"left"},
            { label: '<spring:message code="LAB.M01.LAB00032" />', name: 'ctrtId', width : 80, align:"center"},
            { label: '<spring:message code="LAB.M07.LAB00186" />', name: 'svcTelNo', width : 100, align:"center"},
            //{ label: '<spring:message code="LAB.M07.LAB00021" />', name: 'useYymm', formatter:stringTypeFormatterYYYYMM, width : 150, align:"center"},
            { label: '<spring:message code="LAB.M07.LAB00130" />', name: 'prodNm', width : 150, align:"left"},
            { label: '서비스명', name: 'svcNm', width : 150, align:"left"},
            { label: '<spring:message code="LAB.M08.LAB00058" />', name: 'chrgItmNm', width : 120, },
            //{ label: '<spring:message code="LAB.M07.LAB00018" />', name: 'usgCnt', formatter:'integer', width : 120, align:"right"},
            { label: '<spring:message code="LAB.M09.LAB00137" />', name: 'adjPrvBillAmt', formatter:'integer', width : 180, align:"right", summaryType:'sum'},
            { label: '<spring:message code="LAB.M09.LAB00134" />', name: 'adjAmt', formatter:'integer', width : 120, align:"right", summaryType:'sum'},
            //{ label: '<spring:message code="LAB.M05.LAB00048" />', name: 'unpayAmt', formatter:'integer', width : 100, align:"right"},
            { label: '<spring:message code="LAB.M10.LAB00031" />', name: 'adjAftBillAmt', formatter:'integer', width : 90, align:"right", summaryType:'sum'},
            { label: '<spring:message code="LAB.M07.LAB00345" />', name: 'adjApplAmt', formatter:"integer", width : 90, align:"right", summaryType:'sum'},	//신청금액
            { label: '<spring:message code="LAB.M09.LAB00257" />', name: 'preAdjApplAmt', formatter:"integer", width : 100, align:"right", summaryType:'sum'} //조정반영예상금액
            
            //{name:'money', index:'money',width:165, align:"right", sorttype:'int', sortable:true, formatter: 'integer', formatoptions:{thousandsSeparator:","}, summaryType:'sum', summaryTpl: 'Totals :'}

        ],
        viewrecords: true,
		shrinkToFit:false,
		height: 150,
		sortable : true,
		footerrow: true, 
		userDataOnFooter : true,
		jsonReader: {
			repeatitems : true,
			root : "adjTgtList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		//rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		//rowNum: -1,
        rowNum: 5,
        pager: "#workGrpGridPager",
       	loadComplete : function (data) {
       		$("#afterAdjRegiditTable").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
  	      	var adjAmtSum = $("#afterAdjRegiditTable").jqGrid('getCol','adjAmt', false, 'sum');
  	      	var adjAftBillAmtSum = $("#afterAdjRegiditTable").jqGrid('getCol','adjAftBillAmt', false, 'sum');
			var adjPrvBillAmtSum = $("#afterAdjRegiditTable").jqGrid('getCol','adjPrvBillAmt', false, 'sum');
    		var adjApplAmtSum = $("#afterAdjRegiditTable").jqGrid('getCol','adjApplAmt', false, 'sum');
  			var preAdjApplAmtSum = $("#afterAdjRegiditTable").jqGrid('getCol','preAdjApplAmt', false, 'sum');
  
          	$('#afterAdjRegiditTable').jqGrid('footerData', 'set', { chrgItmNm:'합계', adjPrvBillAmt:adjPrvBillAmtSum, adjAmt:adjAmtSum , adjAftBillAmt:adjAftBillAmtSum, adjApplAmt: adjApplAmtSum, preAdjApplAmt:preAdjApplAmtSum});
    
  	      	$("#afterAdjRegiditTable").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
       	},
    	sortable: { update: function(permutation) {
    		$("#afterAdjRegiditTable").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
    });

    //그리드 화면 재조정
    $(window).resize(function() {
        $("#afterAdjRegiditTable").setGridWidth($('#afterAdjRegiditGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    });
    
  //엑셀 다운로드 이벤트
    $('#btn_print').on('click',function (e) {

    	popPrintExcel();
		}
    );
});

function setBasicInfo(){
    $('#popPymAcntId').text('${basicCustInfo.pymAcntId}');
    $('#popPymAcnt').text('${basicCustInfo.pymAcnt}');
    $('#popRsdtCrrno').text('${basicCustInfo.rsdtCrrno}');
    $('#popCustTpNm').text('${basicCustInfo.custTpNm}');
    $('#popCrrNm').text('${basicCustInfo.crrNm}');
    $('#popRepNm').text('${basicCustInfo.repNm}');
    $('#popBusiCndt').text('${basicCustInfo.busiCndt}');
    $('#popBusiTp').text('${basicCustInfo.busiTp}');
    $('#popAddr').text('${basicCustInfo.addr}');
}

function checkInput(){
    var param = new Object();
    
    param.adjNo = $('#adjNo').val();
    param.adjPt = $('#adjPt').val();
    param.ctrtId = $('#ctrtId').val();
    param.billSeqNo = $('#billSeqNo').val();
    param.billYymm = $('#billYymm').val();
    
    return param;
}

/**
 * 엑셀다운로드
 */
function popPrintExcel(){

	var param = new Object();
	
	param.adjNo = $('#adjNo').val();
    param.adjPt = $('#adjPt').val();
    param.ctrtId = $('#ctrtId').val();
    param.billSeqNo = $('#billSeqNo').val();
    param.billYymm = $('#billYymm').val();
		
  	$.download('getPopBillChargeAdjAfterReportExcel.xlsx',param,'post');
}


</script>
<!-- 검색부 -->
<div style="width:1510px; height:550px;" >

    <!-- title -->
    <div class="layer_top">
        <div class="title"><spring:message code="LAB.M07.LAB00346"/></div>
         <a href="#" id="btnClose"  class="close"></a>
    </div>
	   
	<div class="layer_box">
		<div class="main_btn_box" style="margin-top:-20px">
	        <div class="fl">
	            <h4 class="sub_title">기본정보</h4>
	        </div>
	    </div>
	    <!--// title -->
	    <table class="writeview">
	        <colgroup>
	            <col style="width:10%;">
	            <col style="width:20%;">
	            <col style="width:10%;">
	            <col style="width:20%;">
	            <col style="width:10%;">
	            <col style="width:20%;">
	        </colgroup>
	        <tbody>
	            <tr>
	                <th><spring:message code="LAB.M02.LAB00006"/></th><!-- 납부계정ID -->
	                <td><p id="popPymAcntId"></p></td>
	                
	                <th><spring:message code="LAB.M02.LAB00018"/></th><!-- 납부자명 -->
	                <td><p id="popPymAcnt" /></td>
	                
	                <th><spring:message code="LAB.M09.LAB00188"/></th><!-- 주민/사업자번호 -->
	                <td><p id="popRsdtCrrno" /></td>
	            </tr>
	            <tr>
	            <tr>
	                <th><spring:message code="LAB.M01.LAB00053"/></th><!-- 고객유형 -->
	                <td><p id="popCustTpNm"></p></td>
	                
	                <th>업체명</th>
	                <td><p id="popCrrNm" /></td>
	                
	                <th>대표자명</th><!-- 신청일시 -->
	                <td><p id="popRepNm" /></td>
	            </tr>
	            <tr>
	                <th>업태</th><!-- 신청자 -->
	                <td><p id="popBusiCndt"></p></td>
	                
	                <th>업종</th><!-- 신청일시 -->
	                <td colspan="3"><p id="popBusiTp" /></td>
	
	            </tr>
	            <tr>
	                <th>납부자 주소</th><!-- 신청자 -->
	                <td colspan="5"><p id="popAddr"></p></td>
	                
	            </tr>
	        </tbody>
	    </table> 
	    
	    
	    
	    <div class="main_btn_box" style="margin-top:-10px">
	        <div class="fl">
	            <h4 class="sub_title">조정금액</h4>
	        </div>
	        <div class="fr mt10">
			<a class="grey-btn" id="btn_print" href="#" onclick="popPrintExcel();"><span class="print_icon"><spring:message code="LAB.M08.LAB00015" /></span></a>
		<%-- <a id='disableMaskBtn' class="grey-btn" href="#" title='액셀저장'><spring:message code="LAB.M08.LAB00015" /></a><!-- 엑셀다운로드 --> --%>
			</div>
	    </div>
	    
	    <div id='gridDiv'>
	        <table id="afterAdjRegiditTable" class="w100p"></table>
	        <div id="workGrpGridPager"></div>
	    </div>
    
    </div>
    
<!-- <div style="width:700px;" > -->
<!--         title -->
<!--          <div class="layer_top"> -->
<%--              <div class="title"><spring:message code="LAB.M01.LAB00002"/></div> --%>
<!--              <a href="#" class="close"></a> -->
<!--         </div> -->
<!--         // title -->
                                                 
<!--         center -->
<!--         <div class="layer_box"> -->
<!--                 <table id="virAcntGrid" class="w100p"></table> -->
<!--                 <div id="virAcntGridPager"></div> -->
<!--          </div> -->
<!--         footer -->
<!--          <div class="btn_box"> -->
<%--                <ntels:auth auth="${menuAuthU}"> --%>
<%--                <a id='virChngBtn' class="grey-btn" href="#" ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252" /></span></a> --%>
<%--                </ntels:auth> --%>
<%--                <a class="grey-btn close" href="#" ><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a> --%>
<!--         </div> -->
<!-- </div> -->
<%--     <div class="btn_box">
        <div class="fr">
            <span id="commonBtn">
                <a href="#" class="grey-btn" id="btnCancel" title='<spring:message code="LAB.M07.LAB00349"/>'><spring:message code="LAB.M07.LAB00349"/></a>
                <ntels:auth auth="${menuAuthU}">
                <a id="popUpdateBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
                </ntels:auth>
            </span>
        </div>
    </div> --%>
 

</div>


<input id="clsBillYymm" type='text' value="${billClsInfo[0].billYymm}"  hidden />
<input id="popAdjNo" type='text' value="${billBefAdj.adjNo}"  hidden />
<input type="text" id="adjNo" name="adjNo" value="${billingAdjust.adjNo}" hidden />
<input type="text" id="ctrtId" name="ctrtId" value="${billingAdjust.ctrtId}" hidden />
<input type="text" id="billSeqNo" name="billSeqNo" value="${billingAdjust.billSeqNo}" hidden />
<input type="text" id="adjPt" name="adjPt" value="${billingAdjust.adjPt}" hidden />
<input type="text" id="billYymm" name="billYymm" value="${billingAdjust.billYymm}" hidden />

