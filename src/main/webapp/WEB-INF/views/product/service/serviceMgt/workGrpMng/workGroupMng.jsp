<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {

	pageInit();

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
		    { label: '<spring:message code="LAB.M07.LAB00003"/>', name: 'SO_NM', width : 100, align:"left", sortable:false},
		    { label: '<spring:message code="LAB.M09.LAB00011"/>', name: 'SVC_WRK_GRP_ID', width : 100, align:"center", sortable:false},
		    { label: '<spring:message code="LAB.M09.LAB00013"/>', name: 'SVC_WRK_GRP_NM', width : 200, align:"left", sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00026"/>', name: 'USE_YN_NM', width : 100, align:"center", sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00256"/>', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00254"/>', name: 'CHG_DATE', formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center", sortable:false},
		    { label: '<spring:message code="LAB.M03.LAB00082"/>', name: 'REGR_NM', width : 150, sortable:false},
		    { label: '<spring:message code="LAB.M03.LAB00080"/>', name: 'REG_DATE',  formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center", sortable:false},
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

	$("#userGrid").jqGrid({
		url : '/product/service/serviceMgt/workGrpMng/getUserListAction.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
		    { label: '<spring:message code="LAB.M09.LAB00139"/>', name: 'ORG_ID', width : 100, align:"left", sortable:false},
		    { label: '<spring:message code="LAB.M09.LAB00147"/>', name: 'ORG_NM', width : 100, align:"left", sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00067"/>', name: 'USER_ID', width : 100, align:"left", sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00071"/>', name: 'USER_NAME', width : 100, align:"left", sortable:false}
		    
		],
		viewrecords: true,
		shrinkToFit:false,
		multiselect: true,
		height: 240,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "userList"
		},
        rowNum: -1,
        onSelectRow : function(rowid, status, event){
        	var rowIds = $("#userGrid").jqGrid('getGridParam', 'selarrrow');
        	if(rowIds.length == 0){
        		btnDisable('addUserBtn');
        	}else{
        		btnEnable('addUserBtn');
        	}
        },
       	loadComplete : function () {
       		$("#userGrid").jqGrid('hideCol', 'cb');
  	      	$("#userGrid").setGridWidth($('#userGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#userGrid").setGridWidth($('#userGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});
	
	$("#workGrpUserGrid").jqGrid({
		url : '/product/service/serviceMgt/workGrpMng/getWorkGrpUserListAction.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
			{ label: 'useYn', name: 'USE_YN', width : 100, align:"center", hidden:true},
			{ label: 'smsYn', name: 'SMS_YN', width : 100, align:"center", hidden:true},
		    { label: '<spring:message code="LAB.M07.LAB00067"/>', name: 'USER_ID', width : 100, align:"left", sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00071"/>', name: 'USER_NAME', width : 100, align:"left", sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00026"/>', name: 'USE_YN_NM', width : 100, sortable:false,index:'USE_YN',editable: true, edittype: 'select', editoptions: { value: "Y:Yes;N:No" }},
		    { label: '<spring:message code="LAB.M15.LAB00083"/>', name: 'SMS_YN_NM', width : 100, sortable:false,index:'SMS_YN', editable: true, edittype: 'select', editoptions: { value: "Y:Yes;N:No" }},
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 240,
		cellEdit: true,
		sortable : true,
		multiselect: true,
		cellsubmit: 'clientArray',
		jsonReader: {
			repeatitems : true,
			root : "workGrpUserList"
		},
        rowNum: -1,
        onCellSelect : function(rowid, index, contents, event){
        },
       	loadComplete : function (data) {
  	      	$("#workGrpUserGrid").setGridWidth($('#workGrpUserGridDiv').width()-3,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
  	      	$("#workGrpUserGrid").setGridParam({
				datatype: 'local'
			});

  	      	var rowIds = $("#workGrpUserGrid").jqGrid('getDataIDs');
  	      	if(rowIds.length > 0){
  	      		btnEnable('updateWorkUserBtn');
				btnEnable('deleteWorkUserBtn');	
  	      	}else{
  	      		btnDisable('updateWorkUserBtn');
				btnDisable('deleteWorkUserBtn');	
  	      	}
  	      	
        },
    	sortable: { update: function(permutation) {
    		$("#workGrpUserGrid").setGridWidth($('#workGrpUserGridDiv').width()-3,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	},
    	afterEditCell:function (rowid, cellname, value, iRow, iCol){
    		$("#" + iRow+'_'+cellname).selectmenu().selectmenu( "option", "width", "100%" );
		},
		afterSaveCell (rowid, cellname, value, iRow, iCol){
			if(cellname == 'USE_YN_NM'){
				$("#workGrpUserGrid").jqGrid('setCell', rowid, 'USE_YN', value);
			}else if(cellname == 'SMS_YN_NM'){
				$("#workGrpUserGrid").jqGrid('setCell', rowid, 'SMS_YN', value);
			}
		} 
	});
	$("#workGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#userGrid").setGridWidth($('#userGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#workGrpUserGrid").setGridWidth($('#workGrpUserGridDiv').width()-3,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리

	//그리드 화면 재조정
	$(window).resize(function() {
		$("#workGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		$("#userGrid").setGridWidth($('#userGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		$("#workGrpUserGrid").setGridWidth($('#workGrpUserGridDiv').width()-3,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
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
 * 작업그룹조회
 */
function searchWorkGrpList(){

	pageInit();
	
	$("#workGrpGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			soId : $('#condSo').val(),
        	workGrpNm : $('#condWorkGrpNm').val()
		}
	});
	      
   	$("#workGrpGrid").trigger("reloadGrid");	
}

/*
 * 작업그룹 선택 이벤트
 */

function setSelectedData(rowId){
	var data = $("#workGrpGrid").getRowData(rowId);

	$('#workGrpIdTxt').val(data.SVC_WRK_GRP_ID);
	$('#workGrpNmTxt').val(data.SVC_WRK_GRP_NM);
	$('#workGrpNmTxt').removeClass('not-active');
	$("#workGrpNmTxt").removeAttr('disabled');
    $("#workGrpUseYnSel").val(data.USE_YN);
    $("#workGrpUseYnSel").selectmenu('refresh');
	$("#workGrpUseYnSel").selectmenu('enable');
	$("#workGrpSoSel").val(data.SO_ID);
    $("#workGrpSoSel").selectmenu('refresh');
	$("#workGrpSoSel").selectmenu('disable');

	$("#userGrid").clearGridData();
	$("#workGrpUserGrid").clearGridData();

	btnEnable('initBtn');
	btnDisable('newBtn');
	btnEnable('updateBtn');
	btnEnable('deleteBtn');
	btnDisable('addUserBtn');
	btnDisable('updateWorkUserBtn');
	btnDisable('deleteWorkUserBtn');

	$("#userGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			svcWrkGrpId : data.SVC_WRK_GRP_ID
		}
	});
   	$("#userGrid").trigger("reloadGrid");	

   	$("#workGrpUserGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			svcWrkGrpId : data.SVC_WRK_GRP_ID
		}
	});
   	$("#workGrpUserGrid").trigger("reloadGrid");	
}

function reloadUserDtl(workGrpId){
	$("#userGrid").clearGridData();
	$("#workGrpUserGrid").clearGridData();

	btnEnable('initBtn');
	btnDisable('newBtn');
	btnEnable('updateBtn');
	btnEnable('deleteBtn');
	btnDisable('addUserBtn');
	btnDisable('updateWorkUserBtn');
	btnDisable('deleteWorkUserBtn');

	$("#userGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			svcWrkGrpId : workGrpId
		}
	});
   	$("#userGrid").trigger("reloadGrid");	

   	$("#workGrpUserGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			svcWrkGrpId : workGrpId
		}
	});
   	$("#workGrpUserGrid").trigger("reloadGrid");	

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
 * 사용자추가
 */
function addUserBtn(){
	var rowIds = $("#userGrid").jqGrid('getGridParam', 'selarrrow');

	var addUserList = [];
	for(var i = 0; i < rowIds.length;i++){
		var userData = $("#userGrid").getRowData(rowIds[i]);
		var addUserInfo = new Object();
		addUserInfo.WORK_GRP_ID = $('#workGrpIdTxt').val();
		addUserInfo.ADD_USER_ID = userData.USER_ID;
		addUserList[i] = addUserInfo;
	}

	var url = '/product/service/serviceMgt/workGrpMng/inserWorkGrpUserAction.json';
	$.ajax({
		url:url,
		type:'POST',
		data : JSON.stringify(addUserList),
		dataType: 'json',
		contentType : "application/json; charset=UTF-8",
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	reloadUserDtl($('#workGrpIdTxt').val());
        },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
    });


}

/*
 * 사용자수정
 */
function updateWorkUserBtn(){
	var data = $("#workGrpUserGrid").getRowData();
	$.each(data, function(index, value){
		$("#workGrpUserGrid").editCell(index,3,false);
		$("#workGrpUserGrid").editCell(index,4,false);
	});
	var selectedRowIds = $("#workGrpUserGrid").jqGrid('getGridParam', 'selarrrow');	//체크된 row id들을 배열로 반환
	var updateDataList = [];
	$.each(selectedRowIds, function(index, value){
		var rowData = $("#workGrpUserGrid").jqGrid('getRowData', value);
		var updateData = new Object();
		updateData.WORK_GRP_ID = $('#workGrpIdTxt').val();
		updateData.USER_ID = rowData.USER_ID;
		updateData.USE_YN = rowData.USE_YN;
		updateData.SMS_YN = rowData.SMS_YN;
		updateDataList[index] = updateData;
	});

	

	if(updateDataList.length == 0){
		alert('<spring:message code="MSG.M07.MSG00073"/>');
		return;
	}
	var url = '/product/service/serviceMgt/workGrpMng/updateWorkGrpUserAction.json';
	$.ajax({
		url:url,
		type:'POST',
		data : JSON.stringify(updateDataList),
		dataType: 'json',
		contentType : "application/json; charset=UTF-8",
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	reloadUserDtl($('#workGrpIdTxt').val());

        },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
    });
}


/*
 * 사용자삭제
 */
function deleteWorkUserBtn(){
	var selectedRowIds = $("#workGrpUserGrid").jqGrid('getGridParam', 'selarrrow');	//체크된 row id들을 배열로 반환
	var deleteDataList = [];
	$.each(selectedRowIds, function(index, value){
		var rowData = $("#workGrpUserGrid").jqGrid('getRowData', value);
		var deleteData = new Object();
		deleteData.WORK_GRP_ID = $('#workGrpIdTxt').val();
		deleteData.USER_ID = rowData.USER_ID;
		deleteDataList[index] = deleteData;
	});

	if(deleteDataList.length == 0){
		alert('<spring:message code="MSG.M07.MSG00073"/>');
		return;
	}
	var url = '/product/service/serviceMgt/workGrpMng/deleteWorkGrpUserAction.json';
	$.ajax({
		url:url,
		type:'POST',
		data : JSON.stringify(deleteDataList),
		dataType: 'json',
		contentType : "application/json; charset=UTF-8",
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	reloadUserDtl($('#workGrpIdTxt').val());

        },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
    });
}

/*
 * 신규등록처리
 */
function insertBtn(){

	var soId = $("#workGrpSoSel").val();
	if($('#workGrpSoSel').val()== 'SEL'){
		
		var item = '<spring:message code="LAB.M07.LAB00003" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return;
    }

	//작업그룹명 필수
	var workGrpNm = $("#workGrpNmTxt").val();
	if(workGrpNm == null || workGrpNm.length == 0){
		$("#workGrpNmTxt").focus();
		var item = '<spring:message code="LAB.M09.LAB00013" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return;
	}


	//사용유무
	var useYn = $("#workGrpUseYnSel").val();
	if($('#workGrpUseYnSel').val()== 'SEL'){
		$('#workGrpUseYnSel-button').focus();
		var item = '<spring:message code="LAB.M07.LAB00026" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return;
    }

    var url = '/product/service/serviceMgt/workGrpMng/insertWorkGrpAction.json';
	$.ajax({
		url:url,
		type:'POST',
		data : {
		  workGrpNm : workGrpNm
		 ,soId : soId
		 ,useYn : useYn 
			},
		dataType: 'json',
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	searchWorkGrpList();
        },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
    });
}


/*
 * 수정처리
 */
function updateBtn(){

	//작업그룹명 필수
	var workGrpNm = $("#workGrpNmTxt").val();
	if(workGrpNm == null || workGrpNm.length == 0){
		$("#workGrpNmTxt").focus();
		var item = '<spring:message code="LAB.M09.LAB00013" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return;
	}


	//사용유무
	var useYn = $("#workGrpUseYnSel").val();
	if($('#workGrpUseYnSel').val()== 'SEL'){
		$('#workGrpUseYnSel-button').focus();
		var item = '<spring:message code="LAB.M07.LAB00026" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return;
    }

    var url = '/product/service/serviceMgt/workGrpMng/updateWorkGrpAction.json';
	$.ajax({
		url:url,
		type:'POST',
		data : {
		  workGrpId : $('#workGrpIdTxt').val()
		 ,workGrpNm : workGrpNm 
		 ,useYn : useYn 
			},
		dataType: 'json',
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	searchWorkGrpList();
        },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
    });
}

/*
 * 삭제처리
 */
function deleteBtn(){
    var url = '/product/service/serviceMgt/workGrpMng/deleteWorkGrpAction.json';
	$.ajax({
		url:url,
		type:'POST',
		data : {
		  workGrpId : $('#workGrpIdTxt').val()
		},
		dataType: 'json',
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	searchWorkGrpList();
        },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
    });
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
			<th><spring:message code="LAB.M09.LAB00013" /></th>
			<td>
				<input id="condWorkGrpNm" type="text" class="w100p" />
			</td>
		</tr>
	</thead>
</table> 

<!--작업그룹표시부 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M09.LAB00012"/></h4>
	</div>
</div>
<div id='gridDiv'>
	<table id="workGrpGrid" class="w100p"></table>
	<div id="workGrpGridPager"></div>
</div>

<!--작업그룹상세-->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M09.LAB00015"/></h4>
	</div>
</div>
<table class="writeview">
	<colgroup>
		<col style="width:10%;">
		<col style="width:10%;">
		<col style="width:10%;">
		<col style="width:10%;">
		<col style="width:10%;">
		<col style="width:30%;">
		<col style="width:10%;">
		<col style="width:10%;">
	</colgroup>
	<thead>
		<tr>
			<th><spring:message code="LAB.M09.LAB00011" /></th>
			<td>
				<input id="workGrpIdTxt" type="text" class="w100p" />
			</td>
			<th><spring:message code="LAB.M07.LAB00003" /><span class="dot">*</span></th>
			<td>
				<select id="workGrpSoSel" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M09.LAB00013" /><span class="dot">*</span></th>
			<td>
				<input id="workGrpNmTxt" type="text" class="w100p" />
			</td>
			<th><spring:message code="LAB.M07.LAB00026" /><span class="dot">*</span></th>
			<td>
				<select id="workGrpUseYnSel" name="workGrpUseYnSel" class="w100p">
					<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${useYnList}" var="useYnCode" varStatus="status">
						<option value="${useYnCode.commonCd}">${useYnCode.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</thead>
</table> 
<div class="main_btn_box">
	<div class="fr">
		<span id="commonBtn">
			<ntels:auth auth="${menuAuthR}">
			<a id="initBtn" class="grey-btn" title='<spring:message code="LAB.M10.LAB00050"/>' href="#"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>
			</ntels:auth>
			<ntels:auth auth="${menuAuthC}">
			<a id="newBtn" class="grey-btn" title='<spring:message code="LAB.M03.LAB00075"/>' href="#"><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
			</ntels:auth>
			<ntels:auth auth="${menuAuthU}">
			<a id="updateBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
			</ntels:auth>
			<ntels:auth auth="${menuAuthD}">
			<a id="deleteBtn"  class="grey-btn" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
			</ntels:auth>
		</span>
	</div>
</div>

<!--사용자부 -->
<div class="lay_box">
	<div class="lay_left">
		<div class="main_btn_box">
			<div class="fl">
		            <h4 class="sub_title"><spring:message code="LAB.M07.LAB00070"/></h4>
			</div>
		</div>
		<div id='userGridDiv'>
			<table id="userGrid" class="w100p"></table>
		</div>
		<div class="main_btn_box">
			<div class="fr">
				<span id="userGridBtn">
					<ntels:auth auth="${menuAuthU}">
					<a id="addUserBtn" class="grey-btn" title='<spring:message code="LAB.M10.LAB00059"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M10.LAB00059"/></span></a>
					</ntels:auth>
				</span>
			</div>
		</div>-
	</div>
	<div class="lay_right">
		<div class="main_btn_box">
			<div class="fl">
		            <h4 class="sub_title"><spring:message code="LAB.M09.LAB00014"/></h4>
			</div>
		</div>
		<div id='workGrpUserGridDiv'>
			<table id="workGrpUserGrid" class="w100p"></table>
		</div>
		<div class="main_btn_box">
			<div class="fr">
				<span id="workUserBtn">
					<ntels:auth auth="${menuAuthU}">
					<a id="updateWorkUserBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
					</ntels:auth>
					<ntels:auth auth="${menuAuthD}">
					<a id="deleteWorkUserBtn"  class="grey-btn" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
					</ntels:auth>
				</span>
			</div>
		</div>
	</div>
</div>

<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>

