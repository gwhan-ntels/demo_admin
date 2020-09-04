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

//수납취소 파라미터
var packageName ="com.ntels.ccbs.batch.py.launcher";
var className ="NBlpyc03m02JobLauncher";
var batGrpId ="00008";
var batPgmId ="BLPYC03M02";

$(document).ready(function() {
    	
	//등록자 조회
	$('#btnSearchUser').on('click',function (e) {
		$("#condUserNm").val('');  //돋보기 클릭시 초기화
		var url="/system/common/common/userSearchMng/userSearchPopup.ajax";
		var param = new Object();
		param.popType = "m";            //팝업타입 m:모달 o:일반
		param.returnId1 = "condUserNm";
		param.returnId2 = "condUserId";
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
	$('#btnSearchPym').on('click',function (e) {
		var url="/system/common/common/pymAcntSearch/pymAcntPopup.ajax";
		var param = new Object();
		param.popType = "m";            //팝업타입 m:모달 o:일반
		param.returnId1 = "condPymAcntNm";
		param.returnId2 = "condPymAcntId";
		
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
	$("#strtDt").val(dateFormatterUsingDateYYYYMMDD(new Date));
	$("#endDt").val(dateFormatterUsingDateYYYYMMDD(new Date));
	
	
	$("#recCnclTable").jqGrid({
		url:'/charge/payment/payment/receiptCancel/recCnclListAction.json?',
		datatype: "local",
		mtype:"POST",
		postData: {},
		colModel: [
			//차후 안쓰는 필드는 hidden처리
			{ label: '<spring:message code="LAB.M10.LAB00033" />', name: 'billYymm',formatter:stringTypeFormatterYYYYMM, width : 100, align:"center"},              //청구년월
			{ label: '<spring:message code="LAB.M10.LAB00040" />', name: 'billDt', formatter:stringTypeFormatterYYYYMMDD, width : 100, align:"center"},             //청구일자
			{ label: '<spring:message code="LAB.M10.LAB00039" />', name: 'billSeqNo', width : 150, align:"center"},                                                 //청구일련번호
			{ label: '<spring:message code="LAB.M07.LAB00245" />', name: 'cncl', width : 100, align:"center"},                                                      //취소여부
			{ label: '<spring:message code="LAB.M10.LAB00095" />', name: 'cnclEmpId', width : 150, align:"center"},                                                 //취소자명
			{ label: '<spring:message code="LAB.M10.LAB00094" />', name: 'cnclDt', formatter:stringTypeFormatterYYYYMMDD, width : 100, align:"center"},             //취소일자
			{ label: '<spring:message code="LAB.M02.LAB00006" />', name: 'pymAcntId', width : 100, align:"center"},                                                 //납부계정ID
			{ label: '<spring:message code="LAB.M02.LAB00018" />', name: 'acntNm', width : 200, align:"center"},                                                    //납부계정명
			{ label: '<spring:message code="LAB.M08.LAB00173" />', name: 'dpstDt', formatter:stringTypeFormatterYYYYMMDD, width : 100, align:"center"},             //입금일자
			{ label: '<spring:message code="LAB.M08.LAB00128" />', name: 'transDt', formatter:stringTypeFormatterYYYYMMDD, width : 100, align:"center"},            //이체일
			{ label: '<spring:message code="LAB.M08.LAB00175" />', name: 'dpstProcDt', formatter:stringTypeFormatterYYYYMMDD, width : 100, align:"center"},         //입금처리일자
			{ label: '<spring:message code="LAB.M07.LAB00243" />', name: 'payProcDt', formatter:stringTypeFormatterYYYYMMDD, width : 100, align:"center"},          //수납처리일자
			{ label: '<spring:message code="LAB.M08.LAB00166" />', name: 'dpstClNm', width : 100, align:"center"},                                                  //입금구분
			{ label: '<spring:message code="LAB.M07.LAB00236" />', name: 'payTpNm', width : 100, align:"center"},                                                   //수납구분
			{ label: '<spring:message code="LAB.M10.LAB00031" />', name: 'billAmt', formatter:'number', width : 100, align:"right"},                  //청구금액
			{ label: '<spring:message code="LAB.M08.LAB00125" />', name: 'preRcptAmt', formatter:'number', width : 150, align:"right"},               //이전수납금액
			{ label: '<spring:message code="LAB.M07.LAB00237" />', name: 'rcptAmt', formatter:'number', width : 100, align:"right"},                  //수납금액
			{ label: '<spring:message code="LAB.M03.LAB00083" />', name: 'regrId', width : 100, align:"center"},                                                 //등록자ID
			{ label: '<spring:message code="LAB.M03.LAB00080" />', name: 'regDate', formatter:dateTypeFormatterYYYYMMDDHH24MISS, width : 150, align:"center"},   //등록일
			{ label: '<spring:message code="LAB.M08.LAB00172" />', name: 'pymSeqNo', hidden:true, width : 0},
			{ label: '<spring:message code="LAB.M07.LAB00010" />' , name: 'soId', hidden:true, width : 0},
			
			//{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0}
		],
		viewrecords: true,  
		shrinkToFit:false,
		height: 250,
		sortable : true,  //드래그로 컬럼간의 위치 변경 가능 여부
		rowList:[5,10,20,30,50],    //선택시 노출되는 row 수
		rowNum:10,      //한번에 노출되는 row 수
		pager: '#recCnclTablePager',
		onCellSelect : function(rowId, index, contents, event){
		},
		
		loadComplete: function(obj){
			$("#recCnclTable").setGridWidth($('#recCnclGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}
	});
        
	$("#recCnclTable").setGridWidth($('#recCnclGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#recCnclTable").setGridWidth($('#recCnclGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});	
    	
	//조회버튼
	$("#btnSearch").click(function() {
		searchReceipt();
	});    	
	    
	//수납취소
	$("#cancelRcpt").click(function(){
		setCnclReceipt();  
	});
});


function searchReceipt(){
	
	var startDt = $("#strtDt").val();
	var endDt   = $("#endDt").val();
	
	if(startDt == '' || endDt == ''){
		alert('<spring:message code="MSG.M01.MSG00007"/>');
		return;
	}
	if(startDt > endDt){
		alert('<spring:message code="MSG.M01.MSG00005"/>');
		return;
	}
	
	$("#recCnclTable").setGridParam({
		datatype: "json",
		postData : {
			soId : $("#searchSoId").val(),
			usrId : $("#condUserId").val(),
			pymAcntId : $("#condPymAcntId").val(),
			pymDtTp : $('#searchDateTpCd').val(),
			strtDt : dateFormatToStringYYYYMMDD($("#strtDt").val()),
			endDt : dateFormatToStringYYYYMMDD($("#endDt").val()),
			pymTp : $('#searchPymTp').val(),
			rcptTp : $('#searchRcptTp').val(),
			cncl : $("input[name=pymCancel]:checked").val()
		}                
	});
    
	$("#recCnclTable").trigger("reloadGrid");
	
}

function setCnclReceipt() {
	var param = checkValidation();	

	if (param) {
		$.ajax({
	        type : "post",
	        url : '/charge/payment/payment/receiptCancel/chkIsCancelAble.json',
	        data : param,
	        async: true,
	        success : function(data) {
	        	if (data.returnResult == '-1') {            
					alert('수납취소가 불가능한 대상입니다.');
					return;
				} else {
	                if(param) {
	                	var result = confirm('수납취소하시겠습니까?');
	                	
	                	if (result) {
	                		insert(param) // 수납취소처리
	                	}
	                }
				}
	        }
	    });
	}
}


//수납취소 처리
function insert(param) {

	$.ajax({
        type : "post",
        url : '/charge/payment/payment/receiptCancel/insertAction.json',
        data : param,
        async: true,
        success : function(data) {
			alert('<spring:message code="MSG.M07.MSG00084"/>');
			searchReceipt();
        },
		error: function() {
		    //에러메세지
		    alert('<spring:message code="MSG.M10.MSG00005"/>'); // MSG.M10.MSG00005=처리에 실패했습니다. 관리자에게 문의해 주세요.
		}
	})
	/* 
	$.ajax({
		url:'/charge/payment/payment/receiptCancel/insertAction.json',
		type:'POST',
		data : param,
		dataType: 'json',
		async: true,
		success: function(data){
			if(data.data === 1) {
				pageEnable();
				alert('정상적으로 수납취소처리 되었습니다. ');
				// 조회
				searchReceipt();
			} else {
				pageEnable();
				alert('<spring:message code="MSG.M10.MSG00005"/>'); // 처리에 실패했습니다. 관리자에게 문의해 주세요.
			}
		},
		error: function() {
			pageEnable();
			alert('<spring:message code="MSG.M10.MSG00005"/>'); // 처리에 실패했습니다. 관리자에게 문의해 주세요.
		}
	}); */
}


function checkValidation(){	
	//선택된 그리드 row
	var rowId = $("#recCnclTable").getGridParam("selrow");
	if (rowId == null || rowId == ''){
		alert('<spring:message code="MSG.M10.MSG00049"/>');	  
		return;
	}
	var data = $("#recCnclTable").getRowData(rowId);
	
	//수납취소건일 경우
    if(data.cncl == "Y"){
        alert('<spring:message code="MSG.M10.MSG00038"/>');
        return;
    }
    //취소사유가 없을경우
    if($('#rsn').val().length == 0){
        alert('<spring:message code="MSG.M10.MSG00037"/>');
        $("#rsn").focus();
        return;
    }
    
    return data;
}

//수납 취소 배치 호출
function cancelReceipt(){
	
	//선택된 그리드 row
	var rowId = $("#recCnclTable").getGridParam("selrow");
	if (rowId == null || rowId == ''){
		alert('<spring:message code="MSG.M10.MSG00049"/>');	  
		return;
	}
	var data = $("#recCnclTable").getRowData(rowId);
	
	//수납취소건일 경우
    if(data.cncl == "Y"){
        alert('<spring:message code="MSG.M10.MSG00038"/>');
        return;
    }
    //취소사유가 없을경우
    if($('#rsn').val().length == 0){
        alert('<spring:message code="MSG.M10.MSG00037"/>');
        $("#rsn").focus();
        return;
    }
    
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
	batchParam.packageName = packageName;
	batchParam.className = className;
	
	var args = "";
	args += batPgmId + "\|";			//0.프로그램Id
	args += "0" + "\|";						//1. ?
	args += batGrpId + "\|";					//2.그룹아이디?
	args += pgmExeSeqNo + "\|";				//3.배치시퀀스번호
	args += globalYyyyMm + "\|";		//4.현재년월
	args += "01" + "\|";					//5.빌싸이클
	args += "${session_user.userId}" + "\|";	//6사용자ID
	args += data.soId + "\|";				//7.so_id
	args += $('#rsn').val() + "\|";				//8.취소사유
	args += '' + "\|";					//9. ''
	args += data.pymSeqNo;				//10. 
	
	batchParam.args = args;
	
	console.log(batchParam);
	
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
	var rowId = $("#recCnclTable").getGridParam("selrow");
	var data = $("#recCnclTable").getRowData(rowId);
	
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
					//clearInterval(intervalVal);
					
					batchCount = 0; //배치 카운트 초기화
					searchReceipt();
					//btnNonActive("btn_insert");
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
            <th><spring:message code="LAB.M03.LAB00083"/><!-- 등록자 --></th>
            <td>
                <div class="inp_date w280">
                    <input type="text" id="condUserNm" name="condUserNm" class="w250" disabled="disabled" />
                    <input type="hidden" id="condUserId" name="condUserId" />
                    <a href="#" id="btnSearchUser" name="btnSearchUser" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
                </div> 
            </td>
        </tr>
        <tr>
            <th><spring:message code="LAB.M02.LAB00005"/><!-- 납부계정 --></th>
            <td>
                <div class="inp_date w280">
                    <input type="text" id="condPymAcntNm" name="condPymAcntNm" class="w250" disabled="disabled" />
                    <input type="hidden" id="condPymAcntId" name="condPymAcntId" />
                    <a href="#" id="btnSearchPym" name="btnSearchPym" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
                </div> 
            </td>
            <th><select id="searchDateTpCd" name="searchDateTpCd" class="w130"><!-- 조회일자 -->
                    <!-- <option value=""><spring:message code="LAB.M15.LAB00002"/></option> -->
                    <spring:message code="LAB.M15.LAB00002"/>
                    <c:forEach items="${dateTp}" var="dateTp" varStatus="status">
                        <option value="${dateTp.commonCd}">${dateTp.commonCdNm}</option>
                    </c:forEach>
                </select>
                <span class="dot">*</span>
            </th>
            <td>
                <div class="date_box">
                    <div class="inp_date w150">
                        <input type="text" id="strtDt" name="strtDt" class="datepicker" readonly="readonly" />
                        <a href="#" class="btn_cal"></a>
                    </div>
                    <span class="dash">~</span>
                    <div class="inp_date w150">
                        <input type="text" id="endDt" name="endDt" class="datepicker" readonly="readonly" />
                        <a href="#" class="btn_cal"></a>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <th><spring:message code="LAB.M08.LAB00166"/><!-- 입금구분 --></th>
            <td>
                <select id="searchPymTp" name="searchPymTp" class="w150">
                    <option value=""><spring:message code="LAB.M15.LAB00002"/></option>
                    <spring:message code="LAB.M15.LAB00002"/>
                    <c:forEach items="${paymentTp}" var="paymentTp" varStatus="status">
                        <option value="${paymentTp.commonCd}">${paymentTp.commonCdNm}</option>
                    </c:forEach>
                </select>
            </td>
            <th><spring:message code="LAB.M07.LAB00236"/><!-- 수납구분 --></th>
            <td>
                <select id="searchRcptTp" name=""searchRcptTp"" class="w250">
                    <option value=""><spring:message code="LAB.M15.LAB00002"/></option>
                    <spring:message code="LAB.M15.LAB00002"/>
                    <c:forEach items="${receiptTp}" var="receiptTp" varStatus="status">
                        <option value="${receiptTp.commonCd}">${receiptTp.commonCdNm}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th><spring:message code="LAB.M07.LAB00245"/><!-- 수납취소여부 --></th>
            <td colspan="3">
                <input type="radio" id="pymCancel1" name="pymCancel" value="A" checked="checked" /> 
                    <label><spring:message code="LAB.M09.LAB00063" /></label> 
                <input type="radio" id="pymCancel2" name="pymCancel" value="Y" /> 
                    <label>&nbsp;<spring:message code="LAB.M10.LAB00091" />&nbsp;</label>
                <input type="radio" id="pymCancel3" name="pymCancel" value="N" /> 
                    <label>&nbsp;<spring:message code="LAB.M05.LAB00053" />&nbsp;</label>
            </td>
        </tr>
    </thead>
</table> 

<div class="main_btn_box">
    <div class="fl">
        <h4 class="sub_title"><spring:message code="LAB.M07.LAB00238"/><!-- 수납내역 --></h4>
    </div>
</div>

<div id="recCnclGrid">
    <table id="recCnclTable" class="w100p"></table>
    <div id="recCnclTablePager"></div>
</div>

<div class="main_btn_box">
</div>

<table class="writeview">
    <colgroup>
        <col style="width:10%;">
        <col style="width:40%;">
        <col style="width:10%;">
        <col style="width:40%;">
    </colgroup>
    <tbody> 
        <tr>
           <th><spring:message code="LAB.M10.LAB00093" /></th>    <!--취소사유-->
           <td colspan="3">
              <textarea id="rsn" name="rsn" class="w100p h50" maxlength = "1500">${attribute.remark}</textarea>
           </td>
        </tr>
    </tbody>
</table>

<div class="main_btn_box">
    <div class="fr">
    <ntels:auth auth="${menuAuthC}">
        <a class="grey-btn" id='cancelRcpt' title='<spring:message code="LAB.M07.LAB00244"/>' href="#"><spring:message code="LAB.M07.LAB00244"/></a>
    </ntels:auth> 
    </div>
</div>

<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;" >
</div>
    