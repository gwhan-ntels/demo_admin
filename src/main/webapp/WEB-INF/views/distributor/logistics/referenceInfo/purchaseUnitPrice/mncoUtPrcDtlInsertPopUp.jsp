<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
		
		byteCheckKeyEvent();	//바이트체크
		$('#mncoOtptUtPrcInP').number( true );
		
		setDataInP();
		
		$("#btnInsert").click(function() {
			insertDataInP();
		});
	
	});
	
	function setDataInP(){
		
		var rowId  = $("#purchaseUnitPriceTable").jqGrid("getGridParam", "selrow" );
		var data = $("#purchaseUnitPriceTable").getRowData(rowId);
		
		
		$("#soIdInP").val(data.soId);
		$("#itemIdInP").val(data.itemId);
		$("#itemNmInP").val(data.itemNm);
		
		var yyyymmdd = $.datepicker.formatDate('yymmdd', new Date());
		$("#eftStrtDtInP").val(stringToDateformatYYYYMMDD(yyyymmdd));
		$("#eftEndDtInP").val(stringToDateformatYYYYMMDD("99991231"));
		
	}
	
	//등록
	function insertDataInP(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		if(!check){
			return;
		}
		
		var param = checkValidationInP();
		
		if(param != false){
			
			$.ajax({
				url : 'insertMncoUtPrcDtl.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchData();//부모창 새로고침
						
						$("#btnClose").trigger('click');
						
					}
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});
		}
	}
	
	//밸리데이션 체크
	function checkValidationInP(){
		var param = new Object();
		
		param.soId = $("#soIdInP").val();
		param.itemId = $("#itemIdInP").val();
		param.itemNm = $("#itemNmInP").val();
		param.eftStrtDt = dateFormatToStringYYYYMMDD($("#eftStrtDtInP").val());
		param.eftEndDt = dateFormatToStringYYYYMMDD($("#eftEndDtInP").val());
		
		param.mncoOtptUtPrc = $("#mncoOtptUtPrcInP").val();			//매입단가
		
		if(param.mncoOtptUtPrc == ''){
			//매입단가를 등록해 주세요.
			alert('<spring:message code="LAB.M05.LAB00005" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		return param;
	}
	
</script>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M05.LAB00006"/><!-- 매입단가등록 --></div>
	<a href="#" class="close" >Close</a>
</div>
<!--// title -->

<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M05.LAB00006"/><!-- 매입단가등록 --></h4>
		</div>
	</div>

	<table class="writeview">
		<colgroup>
			<col style="width:15%;">
			<col style="width:35%;">
			<col style="width:15%;">
			<col style="width:35%;">
		</colgroup>
		<tbody> 
			<tr>
				<th><spring:message code="LAB.M09.LAB00106"/><!-- 제품ID --><span class="dot">*</span></th>
				<td>
					<input type="text" id="itemIdInP" name="itemIdInP" class="w170" disabled="disabled">
					<input type="hidden" id="soIdInP" name="soIdInP" />
				</td>
				<th><spring:message code="LAB.M09.LAB00111"/><!-- 제품명 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="itemNmInP" name="itemNmInP" class="w170" disabled="disabled">
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M14.LAB00073"/><!-- 효력시작일자 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="eftStrtDtInP" name="eftStrtDtInP" class="w170" disabled="disabled" />
					<!-- <a href="#" class="btn_cal"></a> -->
				</td>
				<th><spring:message code="LAB.M14.LAB00075"/><!-- 효력종료일자 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="eftEndDtInP" name="eftEndDtInP" class="w170" disabled="disabled" />
					<!-- <a href="#" class="btn_cal"></a> -->
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M05.LAB00005"/><!-- 매입단가 --><span class="dot">*</span></th>
				<td colspan="3">
					<input type="text" id="mncoOtptUtPrcInP" name="mncoOtptUtPrcInP" class="w170" checkbyte="12" />
				</td>
			</tr>
			
		</tbody>
	</table>

</div>
<!--// center -->

<div class="btn_box">
	<a class="grey-btn" href="#" id="btnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
	