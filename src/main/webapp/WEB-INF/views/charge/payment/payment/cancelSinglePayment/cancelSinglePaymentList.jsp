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

//입금취소 파라미터
var packageName ="com.ntels.ccbs.batch.py.launcher";
var className ="NBlpyb10m02JobLauncher";
var batGrpId ="00007";
var batPgmId ="BLPYB10M02";

//현재년월
var globalDt = new Date();
var globalYyyy = globalDt.getFullYear();
var globalMm = globalDt.getMonth() + 1;
if(globalMm < 10){
	globalMm = "0"+globalMm;
}
var globalYyyyMm = ""+globalYyyy+globalMm;

$(document).ready(function() {
    	
	var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
	
	var fromDt = new Date();
	fromDt.setDate(fromDt.getDate()-7);
	$('#depositDtFrom').datepicker('setDate', fromDt);
	$('#depositDtTo').datepicker('setDate', new Date());
	
	btnNonActive("printBtn");
	btnNonActive("btn_cancel");
	
	// 영수사원
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
	
	
	//그리드1
	$("#getCaseByCancelTable").jqGrid({
		url:'getCaseByCancelListAction.json?',
		datatype: "local",
		mtype:"POST",
		postData: {},
		//multiselect: true,
		colModel: [
			//차후 안쓰는 필드는 hidden처리
			{ label: '<spring:message code="LAB.M08.LAB00172" />', name: 'depositSeqNo', width : 80, align:"center"},          //입금일련번호
			{ label: '<spring:message code="LAB.M03.LAB00082" />', name: 'regId', width : 70, align:"center"},                    //등록자 ID
			{ label: '<spring:message code="LAB.M03.LAB00084" />', name: 'registantNm', width : 79, align:"center", summaryTpl: "Total: {0}", summaryType: 'count'},      //등록자명
			{ label: '<spring:message code="LAB.M03.LAB00079" />', name: 'registDate', width : 100, formatter:dateTypeFormatterYYYYMMDD, align:"center"},       //등록일
			{ label: '<spring:message code="LAB.M02.LAB00006" />', name: 'pymAcntId', width : 90, align:"center"},        //납부계정ID
			{ label: '<spring:message code="LAB.M02.LAB00018" />', name: 'pymAcntNm', width : 90, align:"center"},          //납부계정명
			{ label: '<spring:message code="LAB.M02.LAB00017" />', name: 'pymAcntMethodNm', width : 75, align:"center"},   //납부방법명
			{ label: '<spring:message code="LAB.M08.LAB00178" />', name: 'depositTypNm', width : 75, align:"center"},        //입금형태명
			{ label: '<spring:message code="LAB.M08.LAB00167" />', name: 'depositOptionNm', width : 75, align:"center"},   //입금구분명
			{ label: '<spring:message code="LAB.M08.LAB00170" />', name: 'financialInstitutionNm', width : 90, align:"center"},   //입금기관명
			{ label: '<spring:message code="LAB.M01.LAB00198" />', name: 'financialInstitutionTypeNm', width : 90, align:"center"}, //입금유형명
			{ label: '<spring:message code="LAB.M01.LAB00042" />', name: 'acntCardNo', width : 90, align:"center"},      //계좌 및 카드번호
			{ label: '<spring:message code="LAB.M08.LAB00171" />', name: 'depositAmt', formatter: 'integer', width : 70, align:"right", summaryTpl: "Total: {0}", summaryType: 'sum'},     //입금금액
			{ label: '<spring:message code="LAB.M08.LAB00173" />', name: 'paymentDt', width : 70, formatter:stringTypeFormatterYYYYMMDD, align:"center"},     //고객납부일자
			{ label: '<spring:message code="LAB.M08.LAB00127" />', name: 'transferDt', width : 70, formatter:stringTypeFormatterYYYYMMDD, align:"center"}, //이체일
			{ label: '<spring:message code="LAB.M08.LAB00175" />', name: 'depositProcessingDt', width : 80, align:"center", formatter:stringTypeFormatterYYYYMMDD}, //입금처리일자
			/* { label: '<spring:message code="LAB.M05.LAB00055" />', name: 'unknownPayment', width : 80, align:"center"},             //미확인입금대상
			{ label: '<spring:message code="LAB.M07.LAB00191" />', name: 'overPaymentRecovered', width : 80, align:"center"},             //선수금대상 */
			{ label: '<spring:message code="LAB.M07.LAB00243" />', name: 'rcptProcessingDt', width : 80, align:"center", formatter:stringTypeFormatterYYYYMMDD}, //수납처리일자
			{ label: '<spring:message code="LAB.M07.LAB00242" />', name: 'payProcYn', width : 80, align:"center"},             //수납처리여부
			/* { label: '<spring:message code="LAB.M08.LAB00177" />', name: 'depositTyp', width : 60, align:"center"},         //입금형태
			{ label: '<spring:message code="LAB.M08.LAB00166" />', name: 'depositOption', width : 60, align:"center"},    //입금구분
			{ label: '<spring:message code="LAB.M08.LAB00169" />', name: 'financialInstitution', width : 60, align:"center"}, //입금기관
			{ label: '<spring:message code="LAB.M01.LAB00197" />', name: 'financialInstitutionType', width : 60, align:"center"}, //기관유형
			{ label: '<spring:message code="LAB.M02.LAB00016" />', name: 'pymAcntMethod', width : 60, align:"center"},     //납부방법 */
			{ label: 'soId' , name: 'soId', hidden:true,width : 0},
			{ label: 'chk' , name: 'chk', hidden:true,width : 0}
		],
		viewrecords: true,
		height: 250,                //화면 상태에 따라 크기 조절 할 것
		shrinkToFit:true,
		rowNum:5,                   //한번에 노출되는 row 수
		rowList:[5,10,20,30,50],    //선택시 노출되는 row 수
		pager: '#getCaseByCancelPager',
		/* 
		grouping: true,
		groupingView : {
			groupField : ['chk'],
			groupColumnShow: [false],
			groupSummary : [true],
			groupText : ['<b>{0}</b>'],
			groupDataSorted: true
		},
		 */
		onCellSelect : function(rowId, index, contents, event){
			btnActive("btn_cancel");
		},
       
		loadComplete: function(obj){
    	   
			if($("#getCaseByCancelTable").getGridParam("reccount") > 0) {
				btnActive("printBtn");
			}else{
				btnNonActive("printBtn");
			}

			$("#getCaseByCancelTable").setGridWidth($('#getCaseByCancelGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리

		}
	});
   
	$("#getCaseByCancelTable").setGridWidth($('#getCaseByCancelGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
   
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#getCaseByCancelTable").setGridWidth($('#getCaseByCancelGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	
	//조회버튼
	$("#btnSearch").click(function() {
		searchPayment();
	});
	
	// 입금취소시
	$('#btn_cancel').click(function() {
		cancelPayment();
	});
	
	// 엑셀출력
	$('#printBtn').click(function(){
		printExcel();
	})
	
});
	
	
	
   
	function searchPayment(){
		var depositDtFrom = dateFormatToStringYYYYMMDD($("#depositDtFrom").val());
		var depositDtTo   = dateFormatToStringYYYYMMDD($("#depositDtTo").val());
	   	
		if(depositDtFrom == '' || depositDtTo == ''){
			alert('<spring:message code="MSG.M01.MSG00007"/>');
			return;
		}
		if(depositDtFrom > depositDtTo){
			alert('<spring:message code="MSG.M01.MSG00005"/>');
			return;
		}
	   	
		$("#getCaseByCancelTable").setGridParam({
			datatype: "json",
			postData : {
				soId : $("#searchSoId").val(),
				depositDtFrom : dateFormatToStringYYYYMMDD($("#depositDtFrom").val()),
				depositDtTo : dateFormatToStringYYYYMMDD($("#depositDtTo").val()),
				depositOption : $("#depositOption").val(),
				payProcYn : $("#payProcYn").val(),
				regId : $("#condUserId").val(),
				acntCardNo : ''
			}                
		});
	          
		$("#getCaseByCancelTable").trigger("reloadGrid");
    	   
	}
  
	function btnActive(id){
		$('#'+id).addClass('grey-btn');
		$('#'+id).removeClass('white-btn');
		$('#'+id).removeClass('not-active');
		$('#'+id).removeAttr('disabled');
	}

	function btnNonActive(id){
		$('#'+id).addClass('white-btn');
		$('#'+id).removeClass('grey-btn');
		$('#'+id).addClass('not-active');
		$('#'+id).attr('disabled',true);
	}
  
	function printExcel(){
		var soId = $('#searchSoId').val();
		var depositDtFrom = dateFormatToStringYYYYMMDD($("#depositDtFrom").val());
		var depositDtTo   = dateFormatToStringYYYYMMDD($("#depositDtTo").val());
		var depositOption = $("#depositOption").val();
		var payProcYn     = $("#payProcYn").val();
		var regId         = $("#condUserId").val();
		var acntCardNo    = '';
   	
		if(depositDtFrom == '' || depositDtTo == ''){
			alert('<spring:message code="MSG.M01.MSG00007"/>');
			return;
		}
		if(depositDtFrom > depositDtTo){
			alert('<spring:message code="MSG.M01.MSG00005"/>');
			return;
		}

		var param = 'depositDtFrom=' + depositDtFrom;
		param = param + '&depositDtTo=' + depositDtTo;
		param = param + '&soId=' + soId;
		param = param + '&depositOption=' + depositOption;
		param = param + '&payProcYn=' + payProcYn;
		param = param + '&regId=' + regId;
		param = param + '&acntCardNo=' + acntCardNo;
	
		$.download('getCaseByCancelListExcelAction.xlsx',param,'post');	
	}
	
	
	//입금등록 배치 호출
	function cancelPayment(){
		
		// 취소사유 없을시 호출 불가
		if($("#rsnForCnsl").val() == ''){
			alert('<spring:message code="MSG.M10.MSG00036"/>'); //취소사유를 입력해 주세요
			$("#rsnForCnsl").focus();
			return false;
		}
		
		//선택된 그리드 row
		var rowId = $("#getCaseByCancelTable").getGridParam("selrow");
		if (rowId == null || rowId == ''){
			alert('<spring:message code="MSG.M10.MSG00048"/>');	//취소할 입금내역을 선택해주세요.
			return;
		}
		var data = $("#getCaseByCancelTable").getRowData(rowId);
		
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
		args += $("#rsnForCnsl").val() + "\|";				//8.'Cancel Single Deposit'
		args += data.depositSeqNo + "\|";				//9.depositSeqNo
		args += "00" + "\|";				//10. '00'
		args += "1" + "\|";				//11. '00'
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
		var rowId = $("#getCaseByCancelTable").getGridParam("selrow");
		var data = $("#getCaseByCancelTable").getRowData(rowId);
		
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
						searchPayment();
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
	            <span style="color:red" class="dash"><spring:message code="LAB.M15.LAB00003"/></span>
	        </tr>
	        <tr>
		        <th><spring:message code="LAB.M08.LAB00166"/><!-- 입금구분 --></th>
				<td>
						<select id="depositOption" name="depositOption" class="w150">
						<c:forEach items="${depositTp}" var="depositTpList" varStatus="status">
							<option value="${depositTpList.commonCd}">${depositTpList.commonCdNm}</option>
						</c:forEach>
					</select>
			    </td>
	            <th><spring:message code="LAB.M08.LAB00173"/><!-- 입금일자 --><span class="dot">*</span><!-- 입금일자 --></th>
	            <td>
	                <div class="date_box">
	                    <div class="inp_date w150">
	                        <input type="text" id="depositDtFrom" name="depositDtFrom" class="datepicker" readonly="readonly"/>
	                        <a href="#" class="btn_cal"></a>
	                    </div>
	                    <span class="dash">~</span>
	                    <div class="inp_date w150">
	                        <input type="text" id="depositDtTo" name="depositDtTo" class="datepicker" readonly="readonly"/>
	                        <a href="#" class="btn_cal"></a>
	                    </div>
	                </div>
	            </td>
	        </tr>
	        <tr>
	            <th><spring:message code="LAB.M03.LAB00082" /><!-- 등록자 --></th>
			    <td>
				   <div class="inp_date w280">
                    <input type="text" id="condUserNm" name="condUserNm" class="w250" disabled="disabled" />
                    <input type="hidden" id="condUserId" name="condUserId" />
                    <a href="#" id="btnSearchUser" name="btnSearchUser" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
                </div> 
			    </td>
	            <th><spring:message code="LAB.M07.LAB00242"/><!-- 수납처리여부 --></th>
				<td>
					<select id="payProcYn" name="payProcYn" class="w150">
						<option value="ALL"><spring:message code="LAB.M09.LAB00063"/></option>
						<option value="Y"><spring:message code="LAB.M10.LAB00018"/></option>
						<option value="N"><spring:message code="LAB.M05.LAB00052"/></option>
					</select>
			    </td>
	        </tr>
	    </tbody>
	</table>
	
	<div class="main_btn_box">
    	<div class="fl">
        	<h4 class="sub_title"><spring:message code="LAB.M01.LAB00022"/><!-- 건별입금내역 --></h4>
    	</div>
    	<span style="color:red" class="dash"><spring:message code="LAB.M15.LAB00004"/></span>
    	
    	<ntels:auth auth="${menuAuthP}">
			<a id="printBtn" class="white-btn" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
		</ntels:auth>
	</div>

	<div id="getCaseByCancelGrid">
	    <table id="getCaseByCancelTable" class="w100p"></table>
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
			
		</colgroup>
		<tbody> 
			<tr>
				<!--취소사유-->
				<th><spring:message code="LAB.M10.LAB00093" /><span class="dot">*</span></th>
			   <td rowspan="5">
				  <textarea id="rsnForCnsl" name="rsnForCnsl" type="text" class="w900" style="height:50px" rows="3"></textarea>
			   </td>
			</tr>
			<tr>
			</tr>
		</tbody>
	</table> 
</form:form>
		
	<div class="main_btn_box">
		<div class="fr">
				<!--등록-->
				<ntels:auth auth="${menuAuthC}">
					<a class="grey-btn" href="#" id="btn_cancel"><span class="cancel_icon"><spring:message code="LAB.M08.LAB00176"/></span></a>
				</ntels:auth>
		</div>
	</div>

<input id="pgmExeSeqNo" type='number' hidden />
<input id="depositAmt_notFormat" type='text' hidden />
<input id="billSeqNo" type='text' hidden />
<input id="loanGvFlg" type='text' hidden />
<input id="loanAvlAmt" type='text' hidden />
<input id="deptCd" type='text' hidden />
<input id="rcptCnclCnt" type='text' hidden />


<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;" >
</div>
    