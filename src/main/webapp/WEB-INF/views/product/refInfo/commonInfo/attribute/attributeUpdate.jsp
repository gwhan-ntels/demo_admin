<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
<!--

$(document).ready(function(){

	$.datepicker.setDefaults($.datepicker.regional['ko']);
	$( ".date" ).datepicker();

	var date1=new Date();
	date1.setDate( date1.getDate() + 1 );
	reault_y = date1.getFullYear();
	reault_m = eval(date1.getMonth()+1);
	reault_d = date1.getDate();
	if(parseInt(reault_m)<10){reault_m="0"+reault_m;}
	if(parseInt(reault_d)<10){reault_d="0"+reault_d;}
	var date2 = reault_y + "-" + reault_m + "-" + reault_d;	
	$("#nowDate").val(date2);

	// 각각의 셀렉트 박스 값 가져오기
	var ifSys = "${attribute.ifSys}";
	 $('#ifSys').val(ifSys);
	 $('#ifSys').selectmenu("refresh");

	//버튼 클릭시
	$("#btn_update").click(function() {
		goUpdate();
	});	
	
	$("#btn_delete").click(function() {
		goDelete();
	});
	
	$("#btn_cancel").click(function() {
		$("#attribute").attr("action", "/product/refInfo/commonInfo/attribute/attributeList");
		$("#attribute").attr("method", "post");
		$("#attribute").submit();
	});
	
	$("#btn_popUp").click(function() {
		var param = new Object();
		var url="commListPopUp.ajax";
		openModal(url, param);
	});
});

function goUpdate() {
	if($("#attrNm").val() == "") {
		alert("<spring:message code="MSG.M07.MSG00086"/>");
		$("#attrNm").focus();
		return;
	}
	if($("#attrStrtVal").val() == "") {
		alert("<spring:message code="MSG.M07.MSG00122"/>");
		$("#attrStrtVal").focus();
		return;
	}
	if($("#attrEndVal").val() == "") {
		alert("<spring:message code="MSG.M09.MSG00042"/>");
		$("#attrEndVal").focus();
		return;
	}
	if($("#ifSys").val() == "") {
		alert("<spring:message code="MSG.M08.MSG00012" />"); 
		$("#ifSys").focus();
		return;
	}
	if($("#refCd").val() == "") {
		alert("<spring:message code="MSG.M10.MSG00003"/>");
		return;
	}
	if($("#actDt").val() == "") {
		alert("<spring:message code="MSG.M09.MSG00011"/>");
		return;
	}
	
	if($("#actDt").val() < $("#nowDate").val() ) {
		alert("<spring:message code="MSG.M09.MSG00010"/>");
		return;
	}
	
	if($("#inactDt").val() == "") {
		alert("<spring:message code="MSG.M09.MSG00013"/>");
		return;
	}
	$("#attribute").attr("action", "/product/refInfo/commonInfo/attribute/attributeUpdateAction");
	$("#attribute").attr("method", "post");
	$("#attribute").submit();
}

//공통 코드 조회 POPUP에서 검색 했을 경우 POPUP을 닫고 조회 결과를 다시 POPUP으로 Open
function openPop(commonGrpNm){
	var param = new Object();
	param.commonGrpNm =  commonGrpNm;
	var url="commListPopUp.ajax";
	openModal(url, param);
}

function closeModal() {
	$("#popup_dialog").remove();
}

//팝업창 modal로 열기
function openModal(url, param) {
	$.ajax({
		type : "post",
		url : url,
		data : param,
		async: true,
		success : function(data) {
			var html = data;
			console.log("data = " + data)
			$('body').append("<div id=\"popup_dialog\"></div>");			
			$("#popup_dialog").html(html);
		},		
		complete : function(){
			var ewidth = $("#popup_dialog .pop_layer").width();
			var eheight = $("#popup_dialog .pop_layer").height();			
			$("#popup_dialog .pop_layer").css({"top":"0" , "left":"0"});	
	  		$('#popup_dialog').dialog({				
				height: 600 ,
				width: 600,
				modal: true,
				resizable:true,				
			}); 			 
		}
	}); 
} 

function goDelete(){
	if (!confirm('<spring:message code="MSG.M07.MSG00052" />')){
		return;
	}
	$("#attribute").attr("action", "/product/refInfo/commonInfo/attribute/deleteAction");
	$("#attribute").attr("method", "post");
	$("#attribute").submit();
}
//-->
</script>
<form:form method="post" action="/product/refInfo/commonInfo/attribute/attribute/attributeUpdateAction" name="attribute" commandName="attribute">
<input id="attrCd" name="attrCd" value="${attribute.attrCd}" type="hidden">
<input id="oldActDt" name="oldActDt" value="${attribute.actDt}" type="hidden">
<input id="nowDate" name="nowDate" value="${nowDate}" type="hidden">
<input type="hidden" name = "menuNo" value="${selectedMenu.menuNo}"/>
<input type="hidden" name = "selectMenuNo" value="${selectedMenu.selectMenuNo}"/>
<input type="hidden" name = "selectMenuNm" value="${selectedMenu.selectMenuNm}"/>
<input type="hidden" name = "topMenuNo" value="${selectedMenu.topMenuNo}"/>
<input type="hidden" name = "topMenuNm" value="${selectedMenu.topMenuNm}"/>
<div id="mask"></div> 
	<div class="right">
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
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M07.LAB00229"/></h4>
			</div>
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
			<!--속성코드-->
				   <th><label><strong>*</strong><spring:message code="LAB.M07.LAB00232"/></label></th>
				   <td>${attribute.attrCd}</td>
			<!--속성명-->
					<th><strong>*</strong><spring:message code="LAB.M07.LAB00220"/></th>
					<td colspan="3">
						<input id="attrNm" name="attrNm" value="${attribute.attrNm}" type="text" class="w270">
						<form:errors path="attrNm" class="caution"/>
					</td>
				</tr>
				<tr>
				<tr>
					<!--시작값-->
				   <th><spring:message code="LAB.M07.LAB00286" /></th>
				   <td>
					  <input id="attrStrtVal" name="attrStrtVal" value="${attribute.attrStrtVal}" type="text"  class="w270">
					  <form:errors path="attrStrtVal" class="caution"/>
				   </td>
					<!--종료값-->
				   <th><spring:message code="LAB.M09.LAB00166" /></th>
				   <td>
					  <input id="attrEndVal" name="attrEndVal" value="${attribute.attrEndVal}" type="text"  class="w270">
					  <form:errors path="attrEndVal" class="caution"/>
				   </td>
				</tr>
				<!--원부여부-->
				<tr>
					<th><spring:message code="LAB.M08.LAB00090" /></th>
					<td>
						<div class="date_box">
							<input type="radio" id="mstrFl" name="mstrFl" value="1"checked="checked" />
								<label for="mstrFl"><spring:message code="LAB.M15.LAB00102" /></label>
							<input type="radio" id="mstrFl2" name="mstrFl" value="0" /> 
							<label for="mstrFl2"> <spring:message code="LAB.M15.LAB00062" /></label>
						</div>
					</td>
					<!--연동구분 PP00111-->
				   <th><spring:message code="LAB.M08.LAB00020" /></th>
				   <td>
						<select id="ifSys" name="ifSys" class="w270">
							<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
							<c:forEach items="${listIfSys}" var="listIfSys" varStatus="status">
							<option value="${listIfSys.COMMON_CD}">${listIfSys.COMMON_CD_NM}</option>
							</c:forEach>
						</select>
				   </td>
				</tr>
				<tr>
					<!--참조코드-->
				   <th><spring:message code="LAB.M10.LAB00013" /></th>
				   <td colspan="3">
						<div class="date_box">
							<div class="inp_date w220">
								<input id="refCdNm" name="refCdNm" value="${attribute.refCdNm}" type="text" class="w180" readonly>
								<input id="refCd" name="refCd" value="${attribute.refCd}" type="hidden" class="w180" > 
								<a href="#" id="btn_popUp" class="search" >search</a>		
							</div>	
						</div>
				   </td>
				</tr>
				<tr>
					<!--속성설명-->
				   <th><spring:message code="LAB.M07.LAB00221" /></th>
				   <td colspan="3">
					  <textarea id="remark" name="remark" class="w100p h200">${attribute.remark}</textarea>
				   </td>
				</tr>

				<tr>
					<!--적용시작일-->
				   <th><strong>*</strong><spring:message code="LAB.M09.LAB00052" /></th>
				   <td>
				   <div class="date_box">
					   <div class="inp_date w135">
						  <fmt:parseDate value="${attribute.actDt}" var="actDt" pattern="yyyyMMdd"/>
						  <input  type="text"  id="actDt" name="actDt" value="<fmt:formatDate value="${actDt}" pattern="yyyy-MM-dd" />" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
						</div>
					</div>
				   </td>
					<!--적용종료일-->
				   <th><spring:message code="LAB.M09.LAB00058" /></th>
				   <td>
				   <div class="date_box">
				   <div class="inp_date w135">
					  <fmt:parseDate value="99991231" var="inactDt" pattern="yyyyMMdd"/>
					  <input  type="text" id="inactDt" name="inactDt" value="<fmt:formatDate value="${inactDt}" pattern="yyyy-MM-dd" />" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
					</div>
					</div>
				   </td>
				</tr>
			</tbody>
		</table>       
		<div class="main_btn_box">
			<div class="fr">
				<a class="white-btn" href="#" id="btn_update"><span class="write_icon"><spring:message code="LAB.M09.LAB00048"/></span></a>
				<a class="white-btn" href="#" id="btn_cancel"><span class="edit_icon"><spring:message code="LAB.M05.LAB00039"/></span></a>
				<a class="grey-btn" href="#" id="btn_delete" ><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
			</div>
		</div>
	</div>
</div> 
	</form:form>
	<div id="pop_layer" style="height:600px;width:600px;overflow-x:none;overflow-y:auto;display:none;"></div>      

