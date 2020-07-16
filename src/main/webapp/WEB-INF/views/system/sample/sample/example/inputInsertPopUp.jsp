<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="/scripts/nccbs/function.js"></script>
<script type="text/javascript">

$(document).ready(function() {

var prodCd = '${prodCd}';
var StartDt = '${StartDt}';
var basicYn = '${basicYn}';

initGrid(prodCd,StartDt, basicYn);	

	//그리드 재검색 
	function searchDataP1(){
		$("#attributePop").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.searchAttrTyp =  $("#searchAttrTyp2").val();
		
        $("#attributePop").setGridParam({ postData: param }).trigger("reloadGrid");
        
        $("#attributePop").clearGridData();  // 이전 데이터 삭제
        
	}

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	}

	$('#btnClose2').click(function() {
		modalClose();
	});
	$('#btnClose3').click(function() {
		modalClose();
	});

	$('#btn_update').on('click', function(){
		//$("#"+'${prodCd}_RT_ID_A').attr('value', $("#"+'${prodCd}_RT_ID').val());
		//$("#"+'${prodCd}_CNT_A').attr('value', $("#"+'${prodCd}_CNT').val());
		//$("#"+'${prodCd}_OS_TYP_A').attr('value', $("#"+'${prodCd}_OS_TYP').val());
		
		$("#"+'${prodCd}_CNT_A').attr('value', $("#"+'${prodCd}_CNT').val());
		$("#"+'${prodCd}_OTHER_A').attr('value', $("#"+'${prodCd}_OTHER').val());
		$("#"+'${prodCd}_MONTHLY_NEGO_AMT_A').attr('value', $("#"+'${prodCd}_MONTHLY_NEGO_AMT').val());
		$("#"+'${prodCd}_MONTHLY_NEGO_RATE_A').attr('value', $("#"+'${prodCd}_MONTHLY_NEGO_RATE').val());
		$("#"+'${prodCd}_item01_A').attr('value', $("#"+'${prodCd}_item01').val());
		$("#"+'${prodCd}_item02_A').attr('value', $("#"+'${prodCd}_item02').val());
		$("#"+'${prodCd}_item03_A').attr('value', $("#"+'${prodCd}_item03').val());
		$("#"+'${prodCd}_item04_A').attr('value', $("#"+'${prodCd}_item04').val());
		$("#"+'${prodCd}_item05_A').attr('value', $("#"+'${prodCd}_item05').val());
		$("#"+'${prodCd}_item06_A').attr('value', $("#"+'${prodCd}_item06').val());
		$("#"+'${prodCd}_item07_A').attr('value', $("#"+'${prodCd}_item07').val());
		$("#"+'${prodCd}_item08_A').attr('value', $("#"+'${prodCd}_item08').val());
		$("#"+'${prodCd}_item09_A').attr('value', $("#"+'${prodCd}_item09').val());
		$("#"+'${prodCd}_item10_A').attr('value', $("#"+'${prodCd}_item10').val());
		modalClose();
	});




});

function modalClose(){
	$("#popup_dialog2").html("");
	$("#popup_dialog2").hide();
}

function getParam(){
	var param = new Object();	
	param.searchAttrTyp2 =  '${attrTypMap.searchAttrTyp2}';
	return param;
}

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}


	function initGrid(prodCd,StartDt, basicYn) {
		
			var param = new Object();
			param.prodCd = prodCd;
			param.StartDt = StartDt;
			param.basicYn = basicYn;

			var url = '/system/sample/sample/example/getInputProdList.json';

			$.ajax({
				url:url,
				type:'POST',
				async: false,
				data : param,
				dataType: 'json',
				success : function(data) {
					getinputList3(data);

				},
				beforeSend: function(data){
				},
				error : function(err){
					ajaxErrorCallback(err);     		
				}
			});		
		
	}


 function getinputList3(data){
	var inputList = data.prodList;
		if(inputList == null){
			alert("Data가 없습니다.");
			return;
		}
		var contents =  '<colgroup><col style="width: 30%;"><col style="width: 70%;"></colgroup><thead >';

		$('#inputList').empty();
		colspan=0;

	for(i =0; i < inputList.length; i++){
		
		if(inputList[i].colTp =="C"){

			var commonCodeList = inputList[i].commonCodeList

			contents += '<tr>';
			contents +=  '<th class=w' +inputList[i].colSize+'>'+inputList[i].title+'</th>';
			contents +=  '<td >';
			contents +=  '<select class=w' +inputList[i].colSize+' name= ${prodCd}_'+inputList[i].columnId+' id= ${prodCd}_'+inputList[i].columnId+' >';
			contents +=  '<option value=""><spring:message code="LAB.M15.LAB00002"/></option>';
			for(j = 0; j < commonCodeList.length; j++ ){
							contents +=  '<option value=' + commonCodeList[j].commonCd + ' >' +  commonCodeList[j].commonCdNm  +'</option>';
			}
			contents +=  '</select></td>';
			contents += '</tr>';
		}else if(inputList[i].colTp =="T"){
			contents += '<tr>';
			contents +=  '<th class=w' +inputList[i].colSize+'>'+inputList[i].title+'</th>';
			contents +=  '<td><input type=text name= ${prodCd}_'+ inputList[i].columnId + ' id= ${prodCd}_'+inputList[i].columnId+' class=w'+inputList[i].colSize+' /></td>';
			contents += '</tr>';
			
		}
	}
			contents +=  '</thead>';
	//onVisible('inputList');
	$('#inputList').append(contents); // 추가기능
 }
 function buttonEvent(){

	//alert($("#"+'${prodCd}_OS_TYP').val());
//	alert($("#"+'${prodCd}_CNT').val());
//	alert($("#"+'${prodCd}_RT_ID').val());



//window.opener.getElementById("PD00000173_RT_ID").value = "somethingSpecial";




	$("#"+'${prodCd}_OS_TYP').val($("#"+'${prodCd}_OS_TYP').val());
	$("#"+'${prodCd}_CNT').val($("#"+'${prodCd}_CNT').val());
//	$('#PD00000173_RT_ID').val($("#"+'${prodCd}_RT_ID').val());
		//	$('#PD00000173_RT_ID').val($("#"+'${prodCd}_RT_ID').val());
		//	$("#"+'${prodCd}_OS_TYP',opener.document).val($("#"+'${prodCd}_RT_ID').val());
		//	document.getElementById('PD00000173_RT_ID').value = "30";
		//	window.opener.('#PD00000173_RT_ID').val($("#"+'${prodCd}_RT_ID').val());


//$("#PD00000173_RT_ID",opener).val($("#"+'${prodCd}_RT_ID').val());



 }
</Script>
<!-- title -->
<div class="layer_top">
   <div class="title">상품가입정보상세</div>
   <a class="grey-btn close" href="#" id="btnClose2">Close</a>
</div>
<!--// title -->   

<form id="inputInsert" name="inputInsert" method="post">			


<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title">상품가입정보상세</h4>
		</div>
	</div>
	<table class="writeview" id="inputList">

	</table>
</div>			
</div>
<div class="btn_box">
<a class="blue-btn" href="javascript:;" id="btn_update"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>

<a class="grey-btn close" href="#" id="btnClose3"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>


	
</form>