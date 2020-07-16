<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="/scripts/nccbs/function.js"></script>
<script type="text/javascript">

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}

</Script>

<!-- title -->
<div class="layer_top">
   <div class="title">결재상신</div>
   <a href="javascript:closeModal();" class="close">Close</a>
</div>
<!--// title -->   

<form id="searchProd" name="searchProd" method="post">			

<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title">결재대상자</h4>
		</div>
		<!--<div class="fr mt10">
			<a href="javascript:searchProdGrid();" class="grey-btn" ><span class="search_icon">이력조회</span></a> 
		</div>-->
	</div>
	<table class="writeview2">
		<colgroup>
			<col style="width:10%;">
			<col style="width:40%;">
			<col style="width:10%;">
			<col style="width:40%;">
		</colgroup>
			 <thead> 
			   <tr>						
					<th>업무ID</th>
					<td>A001</td>
					<th>업무명</th>
					<td>가망고객 견적서 결재 라인</td>
				</tr>
			</thead>
	</table> 	
	<br>
	<table class="writeview2">
		<colgroup>
			<col style="width:40%;">
			<col style="width:40%;">
			<col style="width:10%;">
			<col style="width:10%;">
		</colgroup>	
		<tr>
			<th>결재자 조직명(조직코드)</th>
			<th>결재자명(결재자ID)</th>
			<th>결재순서</th>
			<th>결재상태</th>
		</tr>
		<tr>
			<td>본사영업1팀(SALES_TEAM1)</td>
			<td>박영석(yspark)</td>
			<td align="center">1</td>
			<td align="center">접수</td>
		</tr>
		<tr>
			<td>본사영업2팀(SALES_TEAM2)</td>
			<td>한기석(ksHan)</td>
			<td align="center">2</td>
			<td align="center">접수</td>
		</tr>
		<tr>
			<td>본사영업본부(HQ_SALES)</td>
			<td>이송정(leesongjung)</td>
			<td align="center">3</td>
			<td align="center">접수</td>
		</tr>
		<tr>
			<td>본사영업본부(HQ_SALES)</td>
			<td>김흥기(hkkim)</td>
			<td align="center">4</td>
			<td align="center">접수</td>
		</tr>
	</table>
</div>	
	


<div class="btn_box">
	<a class="blue-btn" href="#" id="btnClose"><span class="confirm_icon">상신</span></a>
	<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
 </div>

</form>	