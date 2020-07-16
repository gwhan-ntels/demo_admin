<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {

	pageInit();

	var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
	if($(".datepicker").length > 0){
		$( ".datepicker" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
		      regional:lng
		    }).datepicker("setDate", "-7"); 
	}
	$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	$( ".datepicker.disabled" ).datepicker( "option", "disabled", true );
	

	if($(".datepicker1").length > 0){
		$( ".datepicker1" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
		      regional:lng
		    }).datepicker("setDate", "0"); 
	}
	$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	$( ".datepicker1.disabled" ).datepicker( "option", "disabled", true );

	//그리드 처리
	$("#workGrpGrid").jqGrid({
		url : '/product/service/serviceMgt/workGrpMng/getWorkGrpListAction.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
		    { label: 'soId', name: 'SO_ID', width : 100, align:"center", hidden:true},
		    { label: 'useYn', name: 'USE_YN', width : 100, align:"center", hidden:true},
		    { label: '자동환불여부', name: 'SO_NM', width : 100, align:"left", sortable:false},
		    { label: '납부계정ID', name: 'USE_YN_NM', width : 100, align:"center", sortable:false},
		    { label: '납부자명', name: 'CHGR_NM', width : 150, sortable:false},
			{ label: '신청일시', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '신청자명', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '환불대상액', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '환불신청액', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '환불은행명', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '예금주명', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '예금주전화번호', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '계좌번호', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '결제처리상태', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '환불완료', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '선지급여부', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '환불발생금구분', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '환불사유', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '접수자명', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '접수일시', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '결제자명', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '결제일시', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '환불이체일', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '자금이체신청일자', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '발생일련번호', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '대체일련번호', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '환불일련번호', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '결제처리상태코드', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '환불발생금구분코드', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '환불사유코드', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '환불은행코드', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '접수자ID', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '결제요청자ID', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '결제자ID', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '납부자와의관계', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '신청자전화번호', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '신청자E-Mail', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '추가정보', name: 'CHGR_NM', width : 150, sortable:false}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 120,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "workGrpList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 5,
        pager: "#workGrpGridPager",
        onCellSelect : function(rowid, index, contents, event){
        	setSelectedData(rowid);
        },
       	loadComplete : function () {
  	      	$("#workGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#workGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});

	//그리드 화면 재조정
	$(window).resize(function() {
		$("#workGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

	//작업명 조회 이벤트
	$( "#condWorkGrpNm" ).keypress(function(event) {
		if($("#searchBtn").hasClass('not-active')){
			return;
		}
      if(event.keyCode == 13){
        searchWorkGrpList();
      }
    });

    //조회 버튼 이벤트
    $('#searchBtn').on('click',function (e) {
	    	if($("#searchBtn").hasClass('not-active')){
				return;
			}
    		searchWorkGrpList();
		}
    );


    //초기화 버튼 이벤트
   	$('#initBtn').on('click',function (e) {
	   		if($("#initBtn").hasClass('not-active')){
				return;
			}
    		initBtn();
		}
    );

    //신규등록 버튼 이벤트
    $('#newBtn').on('click',function (e) {
      	if($("#newBtn").hasClass('not-active')){
          return;
  		  }
    		insertBtn();
		  }
    );

    //수정 버튼 이벤트
    $('#updateBtn').on('click',function (e) {
      	if($("#updateBtn").hasClass('not-active')){
          return;
  		  }
    		updateBtn();
		  }
    );

    //삭제 버튼 이벤트
    $('#deleteBtn').on('click',function (e) {
      	if($("#deleteBtn").hasClass('not-active')){
          return;
  		  }
    		deleteBtn();
		  }
    );

    //사용자 추가 버튼
    $('#addUserBtn').on('click',function (e) {
      	if($("#addUserBtn").hasClass('not-active')){
          return;
  		  }
    		addUserBtn();
		  }
    );

    //사용자수정버튼
    $('#updateWorkUserBtn').on('click',function (e) {
      	if($("#updateWorkUserBtn").hasClass('not-active')){
          return;
  		  }
    		updateWorkUserBtn();
		  }
    );

    //사용자삭제버튼
    $('#deleteWorkUserBtn').on('click',function (e) {
      	if($("#deleteWorkUserBtn").hasClass('not-active')){
          return;
  		  }
    		deleteWorkUserBtn();
		  }
    );

	$('#workGrpNmTxt').keyup(function(){
	  		var str = getMaxStr($('#workGrpNmTxt').val(), 30);
	  		if(str != $('#workGrpNmTxt').val()){
	  			$('#workGrpNmTxt').val(str);
	  		}
  		}
	);
});

/*
 * 페이지 초기화
 */
function pageInit(){

	btnEnable('initBtn');
	btnDisable('newBtn');
	btnDisable('updateBtn');
	btnDisable('deleteBtn');
	btnDisable('addUserBtn');
	btnDisable('updateWorkUserBtn');
	btnDisable('deleteWorkUserBtn');

	$("#workGrpGrid").clearGridData();
	$("#userGrid").clearGridData();
	$("#workGrpUserGrid").clearGridData();

	$('#workGrpIdTxt').val('');
	$('#workGrpIdTxt').addClass('not-active');
    $('#workGrpIdTxt').attr('disabled', true);
    $('#workGrpNmTxt').val('');
	$('#workGrpNmTxt').addClass('not-active');
    $('#workGrpNmTxt').attr('disabled', true);
    $("#workGrpSoSel").val('SEL');
    $("#workGrpSoSel").selectmenu('refresh');
	$("#workGrpSoSel").selectmenu('disable');
    $("#workGrpUseYnSel").val('SEL');
    $("#workGrpUseYnSel").selectmenu('refresh');
	$("#workGrpUseYnSel").selectmenu('disable');
}

/*
 * 초기화 버튼
 */
function initBtn(){

	btnEnable('initBtn');
	btnEnable('newBtn');
	btnDisable('updateBtn');
	btnDisable('deleteBtn');
	btnDisable('addUserBtn');
	btnDisable('updateWorkUserBtn');
	btnDisable('deleteWorkUserBtn');

	$("#userGrid").clearGridData();
	$("#workGrpUserGrid").clearGridData();


	$('#workGrpIdTxt').val('');
	$('#workGrpIdTxt').addClass('not-active');
    $('#workGrpIdTxt').attr('disabled', true);
    $('#workGrpNmTxt').val('');
	$('#workGrpNmTxt').removeClass('not-active');
    $('#workGrpNmTxt').removeAttr('disabled');
    $("#workGrpSoSel").val('SEL');
    $("#workGrpSoSel").selectmenu('refresh');
	$("#workGrpSoSel").selectmenu('enable');
    $("#workGrpUseYnSel").val('SEL');
    $("#workGrpUseYnSel").selectmenu('refresh');
	$("#workGrpUseYnSel").selectmenu('enable');
	$('#workGrpSoSel-button').focus();
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
			<th><spring:message code="LAB.M07.LAB00003" /></th>
			<td>
				<select id="condSo" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
			<th>접수자</th>
			<td>
				<div class="inp_date w280">
					<input id="condCustNm" type="text" class="w120" />
					<input id="condCustNm" type="text" class="w120" disabled/>
					<ntels:auth auth="${menuAuthR}">
						<a id="btnCustSearch"  href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
				</div>
			</td>
		</tr>
		<tr>
			<th>납부계정</th>
			<td>
				<div class="inp_date w280">
					<input id="condCustNm" type="text" class="w120" />
					<input id="condCustNm" type="text" class="w120" disabled/>
					<ntels:auth auth="${menuAuthR}">
						<a id="btnCustSearch"  href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
				</div>
			</td>
			<th>환불신청일</th>
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
		</tr>
		<tr>
			<th>고객ID</th>
			<td>
				<div class="inp_date w280">
					<input id="condCustNm" type="text" class="w120" />
					<input id="condCustNm" type="text" class="w120" disabled/>
					<ntels:auth auth="${menuAuthR}">
						<a id="btnCustSearch"  href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
				</div>
			</td>
			<th>환불여부</th>
			<td>
					<div class="inp_date w280">
						<div class="date_box">
							<input type="radio" id="mstrFl" name="mstrFl" value="1"checked="checked" />
								<label for="mstrFl">완료</label>
							<input type="radio" id="mstrFl2" name="mstrFl" value="0" /> 
							<label for="mstrFl2"> 신청</label>
							<input type="radio" id="mstrFl2" name="mstrFl" value="0" /> 
							<label for="mstrFl2"> 취소</label>
						</div>
					</div>
			</td>
		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title">환불신청내역</h4>
	</div>
	<div class="fr mt10">
		<a id='disableMaskBtn' class="grey-btn" href="#" title='파일다운로드'>파일다운로드</a>
	</div>
</div>
<div id='gridDiv'>
	<table id="workGrpGrid" class="w100p"></table>
	<div id="workGrpGridPager"></div>
</div>
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"></h4>
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
				<th>신청자명</th>
				<td>				
					<div class="inp_date w280">
						<input id="serviceNo" type="text" class="w245" disabled/>
					</div>
				</td>
				<th>납입자와의관계</th>
				<td>				
					<div class="inp_date w280">
						<input id="serviceNo" type="text" class="w245" disabled/>
					</div>
				</td>
				<th>신청자E-Mail</th>
				<td>				
					<div class="inp_date w280">
						<input id="serviceNo" type="text" class="w245" disabled/>
					</div>
				</td>
				</td>
			</tr>
			<tr>
				<th>환불사유</th>
				<td>				
					<div class="inp_date w280">
						<input id="serviceNo" type="text" class="w245" disabled/>
					</div>
				</td>
				<th>신청자전화번호</th>
				<td>				
					<div class="inp_date w280">
						<input id="serviceNo" type="text" class="w245" disabled/>
					</div>
				</td>
				<th>환불요청금액</th>
				<td>				
					<div class="inp_date w280">
						<input id="serviceNo" type="text" class="w245" disabled/>
					</div>
				</td>
			</tr>
			<tr>
				<th>추가정보</th>
				<td colspan="5">				
					<div class="inp_date w900">
						<textarea id="rsn" name="rsn" class="w100p h100" maxlength = "1500"></textarea>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
</div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>

