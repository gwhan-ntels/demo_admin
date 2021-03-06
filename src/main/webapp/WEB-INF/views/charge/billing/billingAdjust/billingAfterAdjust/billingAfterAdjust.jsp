<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {
	
	// 달력(월) 처리
    settingMonthpicker();

    //그리드 처리
    $("#afterAdjTable").jqGrid({
        url : 'getBillList.json?',
        datatype : 'local',
        mtype: 'POST',
        postData : {
        },
        colModel: [
			{ label: 'billSeqNo' , name: 'billSeqNo', hidden:true, width:0},
			{ label: 'orgAdjNo', name: 'orgAdjNo', hidden:true, width:0},
			{ label: 'billCycl', name: 'billCycl', hidden:true, width:0},
			{ label: 'pymAcntId', name: 'pymAcntId', hidden:true, width:0},
			{ label: 'dcsnProcStat', name: 'dcsnProcStat', hidden:true, width:0},
			{ label: 'soId', name: 'soId', hidden:true, width:0},
			{ label: 'useYymm', name: 'useYymm', hidden:true, width:0},
			{ label: 'adjApplConts', name: 'adjApplConts', hidden:true, width:0},
			{ label: 'adjResnCd', name: 'adjResnCd', hidden:true, width:0},
            { label: '<spring:message code="LAB.M10.LAB00033" />', name: 'billYymm', formatter:stringTypeFormatterYYYYMM, width : 120, align:"center"},
            { label: '<spring:message code="LAB.M09.LAB00253" />', name: 'adjNo', width : 120, align:"left"},
            { label: '<spring:message code="LAB.M10.LAB00058" />', name: 'adjPrvBillAmt', formatter:'integer', width : 210, align:"right"},
            { label: '<spring:message code="LAB.M09.LAB00134" />', name: 'adjAmt', formatter:'integer', width : 190, align:"right"},
            { label: '<spring:message code="LAB.M10.LAB00031" />', name: 'billAmt', formatter:'integer', width : 90, align:"right"},
            { label: '<spring:message code="LAB.M09.LAB00137" />', name: 'adjPrvBillAmtA', formatter:'integer', width : 200, align:"right"},
            { label: '<spring:message code="LAB.M07.LAB00345" />', name: 'adjApplAmt', formatter:'integer', width : 120, align:"right"},
            { label: '<spring:message code="LAB.M09.LAB00134" />', name: 'adjAmtA', formatter:'integer', width : 150, align:"right"},
            { label: '<spring:message code="LAB.M09.LAB00215" />', name: 'dcsnProcStatNm', width : 120, align:"center"},
            { label: '<spring:message code="LAB.M10.LAB00040" />', name: 'billDt', formatter:stringTypeFormatterYYYYMMDD, width : 100, align:"center"},
            { label: '<spring:message code="LAB.M02.LAB00043" />', name: 'payDueDt', formatter:stringTypeFormatterYYYYMMDD, width : 120, align:"center"},
            { label: '<spring:message code="LAB.M07.LAB00304" />', name: 'applDttm', formatter:stringTypeFormatterYYYYMMDDHH24MISS, width : 120, align:"center"},
            { label: '<spring:message code="LAB.M09.LAB00077" />', name: 'rcptPsnNm', width : 100, align:"left"},
            { label: '<spring:message code="LAB.M10.LAB00107" />', name: 'billAplyDt', formatter:stringTypeFormatterYYYYMMDD, width : 100, align:"center"},
            { label: '<spring:message code="LAB.M07.LAB00256" />', name: 'chgrIdNm', width : 100, align:"left"},
            { label: '<spring:message code="LAB.M07.LAB00254" />', name: 'chgDttm', formatter:stringTypeFormatterYYYYMMDDHH24MISS, width : 120, align:"center"}
        ],
        /* multiselect : true, */
        multiselect : false,
        viewrecords: true,
        shrinkToFit:false,
        height: 400,
        sortable : true,
        onCellSelect : function(rowid, index, contents, event){
        },
        loadComplete : function () {
            $("#afterAdjTable").setGridWidth($('#afterAdjGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
        sortable: { update: function(permutation) {
            $("#afterAdjTable").setGridWidth($('#afterAdjGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            }
        }
    });

    //그리드 화면 재조정
    $(window).resize(function() {
        $("#afterAdjTable").setGridWidth($('#afterAdjGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    });
    
    //납부자 키 이벤트
    $( "#condPymAcntNm" ).keypress(function(event) {
       if(event.keyCode == 13){
        searchPymInfo();
      }
    });
    
    //납부자 조회
    $('#btnSearchPym').on('click',function (e) {
        openPymSearchPopup();
    });

    //조회버튼
    $("#searchBtn").click(function() {
        if($("#condPymAcntId").val() == ''){
            alert('<spring:message code="MSG.M02.MSG00001"/>');
            return;
        }
        
        searchPymInfo();

        getPymBillInfo($("#searchSoId").val(), $('#condPymAcntId').val());
    });
    
    //신청버튼 클릭
    $('#btn_request').on('click',function (e) {
        if($("#btn_request").hasClass('not-active')){
            return;
        }
        var chkid = '';
        var rowObject = '';
        var chkid = $("#afterAdjTable").jqGrid('getGridParam', 'selrow'); //선택된 행의 ID를 가져온다
        var rowObject = $("#afterAdjTable").jqGrid('getRowData',chkid);  //선택된 행의 Data를 가져온다
        
        if(chkid==null){
            alert('<spring:message code="MSG.M03.MSG00015"/>');
            return;
        }
        
        rowObject.addr = $('#addr').val();
        rowObject.adjPt        = '2';  /* 조정시점(1:청구전,2:청구후,3:일회성) */
        rowObject.pymAcntNm    = $('#custNm').val();
        rowObject.billYymm     = dateFormatToStringYYYYMM(rowObject.billYymm);
        rowObject.billDt       = dateFormatToStringYYYYMMDD(rowObject.billDt);
        rowObject.payDueDt     = dateFormatToStringYYYYMMDD(rowObject.payDueDt);
        rowObject.applDttm     = rowObject.applDttm;
        rowObject.billAplyDt   = dateFormatToStringYYYYMMDD(rowObject.billAplyDt);
        rowObject.chgDttm      = dateFormatToStringYYYMMDDHHMISS(rowObject.chgDttm);
        rowObject.adjResnCd    = rowObject.adjResnCd;
        
        $.ajax({
          type : "post",
          url : '/charge/billing/billingAdjust/billingAfterAdjust/openAfterAdjReqPopup.ajax',
          data : rowObject,
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
  
});

/*
 * 납부정보조회
 */
function searchPymInfo(){
    
    var checkR = "<c:out value='${menuAuthR}'/>"; 
    if(checkR == 'N') return;
        
    var soId = $("#searchSoId").val();
    var custNm = $('#condPymAcntNm').val();
        
    if(custNm == ''){
        alert('<spring:message code="MSG.M02.MSG00001"/>');
        return;
    }
    
    var url = '/charge/billing/billingAdjust/billingAfterAdjust/getPymList.json'
    
    $.ajax({
          url:url,
          type:'POST',
          data : {
            soId : soId,
            custNm : custNm
            },
          dataType: 'json',
          success: function(data){
        	console.info("data.data.pymListCnt : " + data.pymListCnt);
            if(data.pymListCnt == '0'){
                alert('<spring:message code="MSG.M09.MSG00039"/>'); 
            }else if(data.pymListCnt == '1'){

              $('#pymAcnt').val(data.pymList[0].pymAcnt);
              $('#custNm').val(data.pymList[0].custNm);
              $('#custTpNm').val(data.pymList[0].custTpNm);
              $('#addr').val(data.pymList[0].addr);
              $("#condPymAcntId").val(data.pymList[0].pymAcntId);
              //$("#condPymAcntNm").val(data.pymList[0].pymAcntNm);
              getPymBillInfo(data.pymList[0].soId, data.pymList[0].pymAcntId);

            }else{
                //다수 존재시 팝업호출
                openPymSearchPopup();
            }
            
          },
        afterSend: function(data){
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
 * 납부조회팝업
 */
function openPymSearchPopup(){
	
	$.ajax({
	    type : "post",
	    url : '/system/common/common/pymAcntSearch/pymAcntSearchPopup.ajax',
	    data : {
	         inputSoId : $("#searchSoId").val()   //input SO Id
	        ,inputCustNm : $('#condPymAcntNm').val()   //input Customer Name
	        ,inputPymAcntId : $('#condPymAcntId').val()
	        ,inputIsUnmaskYn : $('#isUnmaskYn').val() //마스크 처리 해제 Y
	        ,outputSoId : 'condCustSoId'            //output SO ID Select
	        ,outputCustNm : 'condCustNm'            //output Customer Name Text
	        ,outputCustId : 'condCustId'            //output Customer ID Text
	        ,outputPymAcntId : 'condPymAcntId'      //output Payment ID Text
	        ,outputPymAcntNm : 'condPymAcntNm'      //output Payment Nm Text
        	,outputSoId : 'searchSoId'      		//output Payment Nm Text
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

/*청구내역 조회*/
function getPymBillInfo(soId, pymAcntId){
    
    var adjPt = "2"; //청구후요금조정
    
    $("#afterAdjTable").clearGridData();
    
    $("#afterAdjTable").setGridParam({
        datatype: "json",
        postData : {
            soId : soId,
            pymAcntId : pymAcntId,
            billYymm : dateFormatToStringYYYYMM($('#searchYymm').val()),
            adjPt : adjPt
        }                
    });
    
    $("#afterAdjTable").trigger("reloadGrid");
}

</script>

<!--NaviGator-->
<input type="hidden" id="tmp" name="tmp"/>  <!-- 리스트에서 선택한 user_id의 user_group_id -->
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
            <th><spring:message code="LAB.M07.LAB00003"/><span class="dot">*</span><!-- 사업 --></th>
            <td>
               <select class="w150" id="searchSoId" name="searchSoId">
                   <spring:message code="LAB.M15.LAB00002"/>
                   <c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
                       <option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
                   </c:forEach>
               </select>                                           
            </td>
            <th><spring:message code="LAB.M02.LAB00005"/><span class="dot">*</span><!-- 납부계정 --></th>
            <td>
                <div class="inp_date w280">
                   <input type="text" id="condPymAcntNm" name="condPymAcntNm" class="w250" />
                   <input type="hidden" id="condPymAcntId" name="condPymAcntId" />
                   <a href="#" id="btnSearchPym" name="btnSearchPym" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
               </div> 
            </td>
        </tr>
        <tr>
          <th><spring:message code="LAB.M10.LAB00033"/><!-- 청구년월 --></th>
          <td>
              <div class="date_box">
                  <div class="inp_date w150">
                          <input  type="text"  id="searchYymm" name="searchYymm" class="monthpicker" readonly="readonly">
                          <a href="#" id='btnCal' class="btn_cal"></a>
                      </div>
              </div>
          </td>
        </tr>
    </thead>
</table> 
<div class="main_btn_box">
    <div class="fl">
        <h4 class="sub_title"><spring:message code="LAB.M01.LAB00217"/></h4>
    </div>
</div>
<div id="regInfo">
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
                <th><spring:message code="LAB.M02.LAB00005"/></th><!-- 납부계정 -->
                <td><input id="pymAcnt" name="pymAcnt" type="text" class="w245" disabled /></td>
                
                <th><spring:message code="LAB.M01.LAB00050"/></th><!-- 고객명 -->
                <td><input id="custNm" name="custNm" type="text" class="w245" disabled /></td>
                
                <th><spring:message code="LAB.M01.LAB00053"/></th><!-- 고객유형 -->
                <td><input id="custTpNm" name="custTpNm" type="text" class="w245" disabled /></td>
            </tr>
            <tr>
                <th><spring:message code="LAB.M09.LAB00190"/></th><!-- 주소 -->
                <td colspan="5"><input id="addr" name="addr" type="text" class="W850" disabled /></td>
            </tr>
        </tbody>
    </table>
</div>

<div class="main_btn_box">
    <div class="fl">
        <h4 class="sub_title"><spring:message code="LAB.M07.LAB00306"/></h4>
    </div>
    <div class="fr mt10">
        <a href="#" class="grey-btn" id="btn_request" title='<spring:message code="LAB.M07.LAB00346"/>'><spring:message code="LAB.M07.LAB00346"/></a>
        <%-- <a href="#" class="grey-btn" id="btn_approvalReq" title='<spring:message code="LAB.M01.LAB00231"/>'><spring:message code="LAB.M01.LAB00231"/></a> --%>
    </div>
</div>
<div id='afterAdjGrid'>
    <table id="afterAdjTable" class="w100p"></table>
</div>

<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>