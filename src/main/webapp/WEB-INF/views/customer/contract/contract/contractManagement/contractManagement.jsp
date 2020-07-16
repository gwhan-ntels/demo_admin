<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">

$(document).ready(function(){
	//화면 초기화
	pageInit();

	//계약 그리드
    $("#ctrtGrid").jqGrid({
		datatype : 'local',
		colModel: [ 
					{ label: 'soId' , name: 'soId', hidden:true,width : 0},
					{ label: 'custId' , name: 'custId', hidden:true,width : 0},
					{ label: 'orderId' , name: 'orderId', hidden:true,width : 0},
					{ label: 'orderTp' , name: 'orderTp', hidden:true,width : 0},
					{ label: 'pymAcntId' , name: 'pymAcntId', hidden:true,width : 0},
					{ label: 'basicProdGrp' , name: 'basicProdGrp', hidden:true,width : 0},
					{ label: 'basicProdCd' , name: 'basicProdCd', hidden:true,width : 0},
					{ label: 'ctrtStat' , name: 'ctrtStat', hidden:true,width : 0},
					{ label: 'instlAddrDtl' , name: 'instlAddrDtl', hidden:true,width : 0},
					{ label: 'instlBaseAddr' , name: 'instlBaseAddr', hidden:true,width : 0},
					{ label: 'instlCity' , name: 'instlCity', hidden:true,width : 0},
					{ label: 'instlStateNm' , name: 'instlStateNm', hidden:true,width : 0},
					{ label: '<spring:message code="LAB.M01.LAB00032"/>', name: 'ctrtId', width : 100, align:"center"}, 
				    { label: '<spring:message code="LAB.M01.LAB00210"/>', name: 'basicProdCdNm', width : 160, align:"left"},
				    { label: '<spring:message code="LAB.M01.LAB00037"/>', name: 'ctrtStatNm', width : 100, align:"left"},
				    { label: '<spring:message code="LAB.M08.LAB00087"/>', name: 'instlZipCd', width : 100, align:"center"},
				    { label: '<spring:message code="LAB.M07.LAB00301"/>', name: 'instlallAddr', width : 300, align:"left", formatter:concatInstAddress, sorttype : function (cell, obj) {
				    												var baseAddr = obj.instlBaseAddr == null ? '' : obj.instlBaseAddr ;
																	var addrDtl = obj.instlAddrDtl == null ? '' : obj.instlAddrDtl; 
																	return baseAddr + addrDtl;
															    }},
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 270,
		sortable : true,
		scrollrows : true,
		//rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        //rowNum: 10,
		//pager: "#ctrtGridPager",
		rowNum : 10000,
		loadonce:true,
		jsonReader: {
			repeatitems : true,
			root : "ctrtList"
			// total : "totalPages",  //총페이지수
			// page : "page"          //현재 페이지
		},
        onCellSelect : function(rowid, index, contents, event){
        	var data = $("#ctrtGrid").getRowData(rowid);
        	selectContract(data.soId, data.custId, data.ctrtId, 'N');
        },
       	loadComplete : function (data) {
			$("#ctrtGrid").setGridWidth($('#ctrtGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#ctrtGrid").trigger("reloadGrid");
            var linkGbn = $('#linkGbn').val();
			if(linkGbn == 'Y' && data != null && data.totalCount != undefined && data.totalCount != null && data.totalCount > 0){
				var ctrtId = '${condCtrtId}';
				var ctrtRowId = findRow($("#ctrtGrid"), "ctrtId", ctrtId, 0);
				$("#ctrtGrid").setSelection(ctrtRowId, false);
				selectContract(data.soId, data.custId, ctrtId, 'N');	
			}
       	},
       	sortable: { 
       		update: function(permutation) {
       			$("#ctrtGrid").setGridWidth($('#ctrtGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	},
        loadError: function (jqXHR, textStatus, errorThrown) {
            ajaxErrorCallback(jqXHR);
        },
        beforeSelectRow: function (rowid) {
		    if ($(this).jqGrid("getGridParam", "selrow") === rowid) {
			   	$("#ctrtGrid").jqGrid("resetSelection");
			   	if($('#condCustSoId').val() != '' && $('#condCustId').val() != ''){
			   		selectContract($('#condCustSoId').val(), $('#condCustId').val(), '', 'N');	
			   	}
		    }else{
        		$("#ctrtGrid").setSelection(rowid, false);
        		var data = $("#ctrtGrid").getRowData(rowid);
        		selectContract(data.soId, data.custId, data.ctrtId, 'N');
		    }
		}
	});

	//계약변경이력 그리드
    $("#ctrtHistGrid").jqGrid({
		datatype : 'local',
		colModel: [ 
					{ label: 'soId' , name: 'soId', hidden:true,width : 0},
					{ label: 'custId' , name: 'custId', hidden:true,width : 0},
					{ label: 'orderTp' , name: 'orderTp', hidden:true,width : 0},
					{ label: 'orderStat' , name: 'orderStat', hidden:true,width : 0},
					{ label: 'ctrtStat' , name: 'ctrtStat', hidden:true,width : 0},
					{ label: 'orderTp' , name: 'orderTp', hidden:true,width : 0},
					{ label: 'pymAcntId' , name: 'pymAcntId', hidden:true,width : 0},
					{ label: 'basicProdGrp' , name: 'basicProdGrp', hidden:true,width : 0},
					{ label: 'basicProdCd' , name: 'basicProdCd', hidden:true,width : 0},
					{ label: 'rcptId' , name: 'rcptId', hidden:true,width : 0},
					{ label: 'rcptUsrId' , name: 'rcptUsrId', hidden:true,width : 0},
					{ label: 'cmplUsrId' , name: 'cmplUsrId', hidden:true,width : 0},
					{ label: 'rcptDesc' , name: 'rcptDesc', hidden:true,width : 0},
					{ label: 'orderCd' , name: 'orderCd', hidden:true,width : 0},
					{ label: 'rcptId' , name: 'rcptId', hidden:true,width : 0},
					{ label: '<spring:message code="LAB.M09.LAB00076"/>', name: 'rcptDttm', width : 150, align:"center", formatter:stringToDateformatYYYYMMDDHH24MISS},
					{ label: '<spring:message code="LAB.M01.LAB00032"/>', name: 'ctrtId', width : 100, align:"center"}, 
					{ label: '<spring:message code="LAB.M08.LAB00033"/>', name: 'orderId', width : 100, align:"center"}, 
					{ label: '<spring:message code="LAB.M08.LAB00033"/>', name: 'orderTpNm', width : 150, align:"left"}, 
					{ label: '<spring:message code="LAB.M08.LAB00035"/>', name: 'orderStatNm', width : 100, align:"left"}, 
					{ label: '<spring:message code="LAB.M01.LAB00037"/>', name: 'ctrtStatNm', width : 100, align:"left"},
					{ label: '<spring:message code="LAB.M09.LAB00077"/>', name: 'rcptUsrNm', width : 150, align:"left", formatter:concatRcptUsrNm},
					{ label: '<spring:message code="LAB.M08.LAB00045"/>', name: 'cmplDttm', width : 150, align:"center", formatter:stringToDateformatYYYYMMDDHH24MISS},
					{ label: '<spring:message code="LAB.M08.LAB00046"/>', name: 'cmplUsrNm', width : 150, align:"left", formatter:concatCmplUsrNm}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 270,
		sortable : true,
		scrollrows : true,
		loadonce:true,
		//rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        //rowNum: 10,
		//pager: "#ctrtHistGridPager",
		rowNum : 10000,
		jsonReader: {
			repeatitems : true,
			root : "orderList"
			// records : "totalCount", //총 레코드 
			// total : "totalPages",  //총페이지수
			// page : "page"          //현재 페이지
		},
        onCellSelect : function(rowid, index, contents, event){
        	var data = $("#ctrtHistGrid").getRowData(rowid);
        	selectOrder(data);
        },
       	loadComplete : function (data) {
       		$("#ctrtHistGrid").setGridWidth($('#ctrtHistGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#ctrtHistGrid").trigger("reloadGrid");
            var linkGbn = $('#linkGbn').val();
			if(linkGbn == 'Y' && data != null && data.totalCount != undefined && data.totalCount != null && data.totalCount > 0){
				var orderId = '${condOrderId}';
				var orderRowId = findRow($("#ctrtHistGrid"), "orderId", orderId, 0);
				$("#ctrtHistGrid").setSelection(orderRowId, false);
				var data = $("#ctrtHistGrid").getRowData(orderRowId);
	        	selectOrder(data);
	        	$('#linkGbn').val('N');
			}
       	},
       	sortable: { 
       		update: function(permutation) {
       			$("#ctrtHistGrid").setGridWidth($('#ctrtHistGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	},
        loadError: function (jqXHR, textStatus, errorThrown) {
            ajaxErrorCallback(jqXHR);
        }

	});

	 //계약정보 btn Event
	 $('#btnCtrtInfo').on('click',function (e) {
		  	if($("#btnCtrtInfo").hasClass('not-active')){
				return;
			}
		  	btnCtrtInfoClickEvent();
		}
    );

	 //납부정보 btn Event
	 // $('#btnPymInfo').on('click',function (e) {
		//   	if($("#btnPymInfo").hasClass('not-active')){
		// 		return;
		// 	}
		//   	btnPymInfoClickEvent();
		// }
  //   );
    
  	//청구정보 btn Event
	 // $('#btnInvoiceInfo').on('click',function (e) {
		//   	if($("#btnInvoiceInfo").hasClass('not-active')){
		// 		return;
		// 	}
		//   	btnInvoiceInfoClickEvent();
		// }
  //  	);
  	
	//계약확인서 btn Event
	 // $('#btnCtrtConfInfo').on('click',function (e) {
		//   	if($("#btnCtrtConfInfo").hasClass('not-active')){
		// 		return;
		// 	}
		//   	btnContractConfirmClickEvent();
		// }
  //  );

	 //검색 조건 select 변경 이벤트
    $('#condSearchTp').selectmenu({
        change: function() {
        	$("#condSearchKey").val('');
        	var type = $('#condSearchTp').val();
        	if(type == 'SEL'){
        		$("#condSearchKey").addClass('not-active');
        		$("#condSearchKey").attr('disabled', true);
        	}else{
        		$('#condSearchKey').removeClass('not-active');
        		$("#condSearchKey").removeAttr('disabled');
        	}
        }
    });

    // 고객조회 버튼 이벤트
	$('#btnCustSearch').on('click',function (e) {
			if($("#btnCustSearch").hasClass('not-active')){
				return;
			}
			openCustSearchPopup();	
		}
	);

	//홈서비스 조회 이벤트

	$('#btnHomeId').on('click',function (e) {
			if($("#btnHomeId").hasClass('not-active')){
				return;
			}
			openHomeIdSearchPopup();	
		}
	);

	// 오더 초기화 버튼 이벤트
	$('#btnOrderInit').on('click',function (e) {
			if($("#btnOrderInit").hasClass('not-active')){
				return;
			}

			initOrderProcInfo('I');	//삽입모드
		}
	);

	//검색키 이벤트
	$('#condSearchKey').keypress(function(event) {
			if(event.keyCode == 13){
				$('#condCustId').val('');
				//고객정보초기화
				$(".custInfoCls").empty();
				//계약정보초기화
				initContractInfo();
				//계약진행정보초기화
				initOrderProcInfo('R'); //읽기모드
				//고객정보조회
				searchCustInfo();
			}
    	}
    );

	//고객명 키 이벤트
    $( "#condCustNm" ).keypress(function(event) {
			if(event.keyCode == 13){
				$('#condCustId').val('');
				//고객정보초기화
				$(".custInfoCls").empty();
				//계약정보초기화
				initContractInfo();
				//계약진행정보초기화
				initOrderProcInfo('R'); //읽기모드
				//고객정보조회
				searchCustInfo();
			}

    	}
    );

    //오더 진행 이벤트
    $('#btnOrderProcess').on('click',function(e){
	    	if($("#btnOrderProcess").hasClass('not-active')){
				return;
			}
		  	btnOrderProcessEvent();
    	}
	);

	//화면 초기화
    $('#btnInit').on('click',function(e){
	    	if($("#btnInit").hasClass('not-active')){
				return;
			}
		  	pageInit();
    	}
	);

	//해지포함 이벤트
	$('#inputIncludeTermCtrt').on('change', function(e){
        	initOrderProcInfo('C'); //완료
        	var soId = $('#condCustSoId').val();
        	var custId = $('#condCustId').val();
        	var isIncludeTermYn = ($('#inputIncludeTermCtrt').prop("checked") ? 'Y' : 'N');
        	//게약정보 호출
        	getCtrtInfoList(soId, custId, 'N', isIncludeTermYn);

		}
	);

	 // 그리드 사이즈 재조정
	$("#ctrtGrid").setGridWidth($('#ctrtGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#ctrtHistGrid").setGridWidth($('#ctrtHistGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$(window).resize(function() {
		$("#ctrtGrid").setGridWidth($('#ctrtGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		$("#ctrtHistGrid").setGridWidth($('#ctrtHistGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	

	pageMove();
});

function pageMove(){
	$('#linkGbn').val('${linkGbn}');
	var linkGbn = $('#linkGbn').val();
	if(linkGbn == 'Y'){
		//------1. 조건 세팅------
		//사업
		$("#condCustSoId").val('${condCustSoId}');	
		$("#condCustSoId").selectmenu("refresh");					
		//고객ID/명
		$("#condCustId").val('${condCustId}');	
		$("#condCustNm").val('${condCustNm}');	
		
		searchCustInfo();
	}
}
/*
 * 화면 초기화
 */
function pageInit(){
	//조회조건 초기화
	initCondtion();

	//고객정보초기화
	$(".custInfoCls").empty();
	btnDisable('btnHomeId');
	
	//체크박스 초기화
	$("#inputIncludeTermCtrt").removeAttr("checked");
	$("#inputIncludeTermCtrt").addClass('not-active');
	$("#inputIncludeTermCtrt").attr('disabled',true);

	//계약정보초기화
	initContractInfo();

	//계약진행정보초기화
	initOrderProcInfo('R'); //읽기모드
}

/*
 * 조회조건 초기화
 */
function initCondtion(){
	$("#condCustSoId").prop('selectedIndex', 0);
	$('#condCustSoId').selectmenu("refresh");
	$("#condSearchTp").prop('selectedIndex', 0);
	$('#condSearchTp').selectmenu("refresh");
	$("#condSearchKey").val('');
	$("#condSearchKey").addClass('not-active');
    $("#condSearchKey").attr('disabled', true);
	$("#condCustNm").val('');
	$("#condCustId").val('');
	$("#condCustNm").focus();
}

/*
 * 계약정보초기화
 */
function initContractInfo(){
	// 계약리스트 초기화
	$("#ctrtGrid").clearGridData();
	$("#ctrtGrid").setGridParam({
		url : '',
		datatype : 'local',
	});
   	$("#ctrtGrid").trigger("reloadGrid");

   	//계약변경이력초기화
   	$("#ctrtHistGrid").clearGridData();
	$("#ctrtHistGrid").setGridParam({
		url : '',
		datatype : 'local',
	});
   	$("#ctrtHistGrid").trigger("reloadGrid");

   	btnDisable('btnCtrtInfo');
   	// btnDisable('btnPymInfo');
   	// btnDisable('btnInvoiceInfo');
   	// btnDisable('btnCtrtConfInfo');
}

/*
 * 계약진행정보초기화
 */
function initOrderProcInfo(mode){
	//계약진행정보초기화
	$("#orderCdSel").prop('selectedIndex', 0);
	$('#orderCdSel').selectmenu("refresh");
	$(".orderProcCls").empty();
	$('#rcptDesc').val("");

	if(mode=="I"){ //삽입모드
		$("#orderCdSel").selectmenu("enable");
		$("#rcptDesc").removeClass('not-active');
		$('#rcptDesc').removeAttr('readonly');
		$('#orderCdSel-button').focus();
		btnEnable('btnOrderInit');
   		btnEnable('btnOrderProcess');
   		$("#ctrtHistGrid").jqGrid("resetSelection");
	}else if(mode=="R"){ //읽기모드
		$("#orderCdSel").selectmenu("disable");
		$("#rcptDesc").addClass('not-active');
		$('#rcptDesc').attr('readonly',true);
		btnDisable('btnOrderInit');
   		btnDisable('btnOrderProcess');
	}else if(mode=='P'){ //진행모드 
		$("#orderCdSel").selectmenu("disable");
		$("#rcptDesc").removeClass('not-active');
		$('#rcptDesc').removeAttr('readonly');
		btnEnable('btnOrderInit');
   		btnEnable('btnOrderProcess');
	}else if(mode=='C'){ //완료모드 
		$("#orderCdSel").selectmenu("disable");
		$("#rcptDesc").addClass('not-active');
		$('#rcptDesc').attr('readonly',true);
		btnEnable('btnOrderInit');
   		btnDisable('btnOrderProcess');
	}
}


/*
 * 고객조회팝업
 */
function openCustSearchPopup(){
	
	$.ajax({
		type : "post",
		url : '/system/common/common/customerSearch/customerSearchPopup.ajax',
		data : {
			 inputSoId : $('#condCustSoId').val()   //input SO Id
			,inputCustNm : $('#condCustNm').val()   //input Customer Name
			,inputIsUnmaskYn : 'N'                  //마스크 처리
			,outputSoId : 'condCustSoId'            //output SO ID Select
			,outputCustNm : 'condCustNm'            //output Customer Name Text
			,outputCustId : 'condCustId'            //output Customer ID Text

		},
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog").hide();
			$("#popup_dialog").html(html);
		},		
		complete : function(){
			wrapWindowByMask(); // 팝업 오픈
			$("#txtCustSearchCustNm").focus(); //오픈 후 focus위치
		}
	}); 
}

/*
 * 홈서비스ID 조회 팝업
 */
function openHomeIdSearchPopup(){
	
	$.ajax({
		type : "post",
		url : '/customer/contract/contract/homeService/openHomeService.ajax',
		data : {
			 soId : $('#condCustSoId').val()   //input SO Id
			,custId : $('#condCustId').val()   //input Customer Name
		},
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog").hide();
			$("#popup_dialog").html(html);
		},		
		complete : function(){
			wrapWindowByMask(); // 팝업 오픈
		}
	}); 
}

/*
 * 고객정보찾기
 */
function searchCustInfo(){
	var checkR = "<c:out value='${menuAuthR}'/>"; 
	if(checkR == 'N') return;
	
	var condCustSoId = $('#condCustSoId').val();
  	var condCustNm = $('#condCustNm').val();
  	var condCustId = $('#condCustId').val();
	var condSearchTp = $('#condSearchTp').val();
	var condSearchKey = getTelNo($('#condSearchKey').val());
	var isUnmaskYn = 'N'; //마스크 처리
	
	if(condCustNm == '' && condSearchTp=='SEL'){
		alert('<spring:message code="MSG.M01.MSG00016"/>');
		return;
	}
	
	var url = '/customer/contract/contract/contractManagement/getCustInfoSearchAction.json';
	
	$.ajax({
          url:url,
          type:'POST',
          data : {
        	condCustSoId : condCustSoId,
        	condCustNm : condCustNm,
        	condCustId : condCustId,
        	condSearchTp : condSearchTp,
        	condSearchKey : condSearchKey,
            isUnmaskYn : isUnmaskYn
          },
          dataType: 'json',
          success: function(data){
          	if(data.custListCnt == '0'){
          		alert('<spring:message code="MSG.M09.MSG00039"/>');	
          	}else if(data.custListCnt == 1){
            	$('#condCustSoId').val(data.custList[0].so_id);
            	$('#condCustSoId').selectmenu("refresh");
             	$('#condCustNm').val(data.custList[0].cust_nm);
            	$('#condCustId').val(data.custList[0].cust_id);
            	getCustInfo($('#condCustSoId').val(), $('#condCustId').val(), isUnmaskYn);
          	}else{
          		//다수 존재시 팝업호출
          		openCustSearchPopup();
          	}
          	
          },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
      });
}


/**
 * 고객정보조회
 */
function getCustInfo(soId, custId, isUnmaskYn){
	
	var url = '/customer/contract/contract/contractManagement/getCustInfoAction.json';
	$.ajax({
          url:url,
          type:'POST',
          data : {
        	  soId : soId,
        	  custId : custId,
              isUnmaskYn : isUnmaskYn
          	},
          dataType: 'json',
          success: function(data){
        	  //고객정보 세팅
			$("#custId").append(data.custInfo.custId); 
			$("#custTpNm").append(data.custInfo.custTpNm); 
			$("#custClNm").append(data.custInfo.custClNm); 
			$("#corpRegNo").append(corpRegNoAutoFormatter(data.custInfo.corpRegNoAsMask)); 
			$("#faxNo").append(telNoAutoFormatter(data.custInfo.faxNo)); 
			$("#telNo").append(telNoAutoFormatter(data.custInfo.telNo)); 
			$("#mTelNo").append(telNoAutoFormatter(data.custInfo.mtelNoAsMask)); 
			$("#email").append(data.custInfo.emlAsMask); 
			$("#regDate").append(dateFormatterUsingDateYYYYMMDDHH24MISS(new Date(data.custInfo.regDate))); 
        	$('#condCustSoId').selectmenu("disable");

		    $('#orderCdSel').each( function() {
			    	$('#orderCdSel option:gt(0)').remove();
			});
	    	$(data.orderCdSel).each(function(index, item){
				var str = '<option value="'+ item.orderCd+'">'+ item.orderTpNm+'</option>';
				$('#orderCdSel').append(str);  	
		    });
		    $('#orderCdSel').val('SEL');
			$('#orderCdSel').selectmenu("refresh");

			$("#inputIncludeTermCtrt").removeClass('not-active');
			$("#inputIncludeTermCtrt").removeAttr('disabled');
			$("#inputIncludeTermCtrt").removeAttr("checked");

			btnEnable('btnHomeId');

        	initOrderProcInfo('C'); //완료

        	var isIncludeTermYn = ($('#inputIncludeTermCtrt').prop("checked") ? 'Y' : 'N');
        	//게약정보 호출
        	getCtrtInfoList(soId, custId, isUnmaskYn, isIncludeTermYn);
          },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
      });
}

/**
 * 고객 조회 팝업 콜백
 */
function customerSearchCallback(){
	$("#condSearchTp").prop('selectedIndex', 0);
	$('#condSearchTp').selectmenu("refresh");
	$("#condSearchKey").val('');
	//고객정보초기화
	$(".custInfoCls").empty();
	//계약정보초기화
	initContractInfo();
	//계약진행정보초기화
	initOrderProcInfo('R'); //읽기모드
	//고객정보조회
	searchCustInfo();
}

/**
 * 설치 주소값 연결
 */
function concatInstAddress( cellvalue, options, rowObject ){
	var baseAddr = rowObject.instlBaseAddr == null ? '' : rowObject.instlBaseAddr ;
	var addrDtl = rowObject.instlAddrDtl == null ? '' : rowObject.instlAddrDtl; 
	return baseAddr + ' ' + addrDtl;
}

/**
 * 접수자 연결
 */
function concatRcptUsrNm( cellvalue, options, rowObject ){
	var rcptUsrId = rowObject.rcptUsrId == null ? '' : rowObject.rcptUsrId;
	var rcptUsrNm = rowObject.rcptUsrNm == null ? '' : rowObject.rcptUsrNm; 
	return rcptUsrId == '' ? '' : rcptUsrNm + '(' + rcptUsrId + ')';
}

/**
 * 완료자 연결
 */
function concatCmplUsrNm( cellvalue, options, rowObject ){
	var cmplUsrId = rowObject.cmplUsrId == null ? '' : rowObject.cmplUsrId;
	var cmplUsrNm = rowObject.cmplUsrNm == null ? '' : rowObject.cmplUsrNm; 
	return cmplUsrId == '' ? '' : cmplUsrNm + '(' + cmplUsrId + ')';
}


/**
 * 오더 진행 페이지 오픈
 */
function openOrderPage(orderCommonInfo){
	/* alert(JSON.stringify(orderCommonInfo)); */
	$.ajax({
		type : "post",
		url : '/customer/contract/contract/orderManagement/openOrderProcess.ajax',
		data : JSON.stringify(orderCommonInfo),
		contentType : "application/json; charset=UTF-8",
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog").html(html);
		},		
		complete : function(){
			
		},
		error : function(err){
     		alert('<spring:message code="MSG.M10.MSG00005"/>');	
     	}
	}); 	

}

/**
 * 계약정보조회
 */
function getCtrtInfoList(soId, custId, isUnmaskYn, isIncludeTermYn){

	//완료모드
	initOrderProcInfo('C'); //완료모드
	//계약정보로드
	$("#ctrtGrid").clearGridData();
	$("#ctrtGrid").setGridParam({
		url : '/customer/contract/contract/contractManagement/getCtrtInfoListAction.json',
		datatype : 'json',
		mtype: 'POST',
  	    postData : {
  			soId : soId,
  			custId : custId,
  			isUnmaskYn : isUnmaskYn,
  			isIncludeTermYn : isIncludeTermYn
		}
	});

	      	
   	$("#ctrtGrid").trigger("reloadGrid");


	if($('#condCustSoId').val() != '' && $('#condCustId').val() != ''){
   		selectContract($('#condCustSoId').val(), $('#condCustId').val(), '', 'N');	
	}
}

/**
 * 계약 선택
 */
function selectContract(soId, custId, ctrtId, isUnmaskYn){
	//계약진행정보초기화
	initOrderProcInfo('C'); //완료모드
	//버튼 활성화
	btnEnable('btnCtrtInfo');
	// btnEnable('btnPymInfo');
	// btnEnable('btnInvoiceInfo');
	// btnEnable('btnCtrtConfInfo');
	
	//계약이력정보로드
	$("#ctrtHistGrid").clearGridData();
	$("#ctrtHistGrid").setGridParam({
		url : '/customer/contract/contract/contractManagement/getCtrtChangeInfoList.json',
		datatype : 'json',
		mtype: 'POST',
  	    postData : {
  			soId : soId,
  			custId : custId,
  			ctrtId : ctrtId,
  			isUnmaskYn : isUnmaskYn
		}
	});
	
	$("#ctrtHistGrid").trigger("reloadGrid");
	
}

/**
 * 오더 선택
 */ 
 function selectOrder(data){
 	if(data.orderStat == '04'){
 		initOrderProcInfo('C'); //완료모드	
 	}else{
		initOrderProcInfo('P'); //진행모드	
 	}
 	$('#orderCdSel').val(data.orderCd);
	$('#orderCdSel').selectmenu('refresh');
	$('#orderId').append(data.orderId); 
	$('#orderStat').append(data.orderStatNm); 
	$('#rcptDate').append(data.rcptDttm);
	$('#cmplDate').append(data.cmplDttm);
	$('#rcptDesc').val(data.rcptDesc);

 }

/**
 * 계약상세 버튼클릭이벤트
 */
function btnCtrtInfoClickEvent(){
	var ctrtRowId  = $("#ctrtGrid").jqGrid('getGridParam', 'selrow');
	if(ctrtRowId == null) return;
  	var ctrtData = $("#ctrtGrid").getRowData(ctrtRowId);


	$.ajax({
		type : "post",
		url : '/customer/contract/contract/contractDetail/openContractDetail.ajax',
		data : {
			  soId : ctrtData.soId
			 ,custId : ctrtData.custId
			 ,ctrtId : ctrtData.ctrtId
		},
		success : function(data) {
			var html = data;
			$("#popup_dialog").html(html);
		},		
		complete : function(){
			wrapWindowByMask(); // 팝업 오픈
			openContractDetail();
		}
	}); 
}



/**
 * 납부계정버튼클릭이벤트
 */
// function btnPymInfoClickEvent(){
// 	var ctrtRowId  = $("#ctrtGrid").jqGrid('getGridParam', 'selrow');
// 	if(ctrtRowId == null) return;
//   	var ctrtData = $("#ctrtGrid").getRowData(ctrtRowId);

// 	$.ajax({
// 		type : "post",
// 		url : '/customer/contract/contract/paymentDetail/openPaymentDetail.ajax',
// 		data : {
// 			  soId : ctrtData.soId
// 			 ,custId : ctrtData.custId
// 			 ,ctrtId : ctrtData.ctrtId
// 			 ,pymAcntId : ctrtData.pymAcntId
// 		},
// 		success : function(data) {
// 			var html = data;
// 			$("#popup_dialog").html(html);
// 		},		
// 		complete : function(){
// 			wrapWindowByMask(); // 팝업 오픈
// 		}
// 	}); 
// }

// function btnInvoiceInfoClickEvent(){
// 	var ctrtRowId  = $("#ctrtGrid").jqGrid('getGridParam', 'selrow');
// 	if(ctrtRowId == null) return;
//   	var ctrtData = $("#ctrtGrid").getRowData(ctrtRowId);

// 	$.ajax({
// 		type : "post",
// 		url : '/customer/contract/contract/invoiceDetail/openInvoiceDetail.ajax',
// 		data : {
// 			  soId : ctrtData.soId
// 			 ,custId : ctrtData.custId
// 			 ,ctrtId : ctrtData.ctrtId
// 			 ,pymAcntId : ctrtData.pymAcntId
// 		},
// 		success : function(data) {
// 			var html = data;
// 			$("#popup_dialog").html(html);
// 		},		
// 		complete : function(){
// 			wrapWindowByMask(); // 팝업 오픈
// 		}
// 	}); 
// }

// function btnContractConfirmClickEvent(){
// 	var ctrtRowId  = $("#ctrtGrid").jqGrid('getGridParam', 'selrow');
// 	if(ctrtRowId == null) return;
//   	var ctrtData = $("#ctrtGrid").getRowData(ctrtRowId);

// 		var url="/customer/contract/contract/contractConfirm/openContractConfirm.ajax";
// 		 url = url + "?soId="+ctrtData.soId+"&ctrtId="+ctrtData.ctrtId+ "&custId="+ctrtData.custId+"&pymAcntId="+ctrtData.pymAcntId+"&popType=o";   //모드 o지정 
		  
// 		 var width = 1055;
// 		 var height = 600;
// 		 var LeftPosition=(screen.width-width)/2;
// 		 var TopPosition=(screen.height-height)/2;
		  
// 		 window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no, resizeable=no,fullscreen=no");
// }
/**
 * 오더 진행 프로세스
 */
function btnOrderProcessEvent(){
	if($('#orderCdSel').val() == 'SEL'){
		$('#orderCdSel-button').focus();
		alert('<spring:message code="MSG.M08.MSG00078"/>'); //오더 유형이 선택되지 않은 경우
		return;
	}
	var ctrtRowId  = $("#ctrtGrid").jqGrid('getGridParam', 'selrow');
  	var ctrtData = $("#ctrtGrid").getRowData(ctrtRowId);
  	var orderHistRowId  = $("#ctrtHistGrid").jqGrid('getGridParam', 'selrow');
  	var ctrtId = '';
  	var rcptId = '';

  	if(ctrtRowId != null){
  		ctrtId = ctrtData.ctrtId;
  	}else{
  		ctrtId = "0000000000";

  	}
  	if(orderHistRowId != null){
  		var orderData = $("#ctrtHistGrid").getRowData(orderHistRowId);
  		rcptId = orderData.rcptId;
  		ctrtId = orderData.ctrtId;
  	}
  	
	var url = '/customer/contract/contract/orderManagement/getOrderMastInfoAction.json';
	$.ajax({
        url:url,
        type:'POST',
        data : {
        	 soId : $('#condCustSoId').val()
        	,custId : $('#condCustId').val()
        	,ctrtId : ctrtId
        	,orderCd : $('#orderCdSel').val() 
        	,rcptId : rcptId
		},
        dataType: 'json',
        success: function(data){
        	openOrderPage(data.orderCommonInfo);
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		ajaxErrorCallback(err);
     		
     	}
    });
}

/**
 * 오더 팝업 Callback
 */

function orderPopupCallback(soId, custId, ctrtId, rcptId){
	var isUnmaskYn = 'N';

	initContractInfo();
    initOrderProcInfo('C'); //완료
    var isIncludeTermYn = ($('#inputIncludeTermCtrt').prop("checked") ? 'Y' : 'N');
	//게약정보 호출
	getCtrtInfoList(soId, custId, isUnmaskYn, isIncludeTermYn);
}



</script>
<div class="h3_bg">
	<h3>${menuName}</h3>
		<!-- Navigator -->
		<div class="nav">                                        
   			<a href="#" class="home">Home</a>
			<c:forEach items="${naviMenuList}" var="mu" varStatus="status">
				<span>&gt;</span>${mu.menuName}
			</c:forEach>
		</div>
</div>

<!-- 초기화 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M01.LAB00054"/></h4> <!--고객정보 -->
	</div>
	<div class="fr mt10">
		<a id="btnInit" class="grey-btn">
			<span class=""><spring:message code="LAB.M10.LAB00050"/> <!-- 초기화 --></span>
		</a>
		<!-- caution -->
		<div class='inpCaution'>
			<span class='dot'>*</span>
			<spring:message code="LAB.M13.LAB00034"/> <!-- 필수항목 -->
		</div>
	</div>
</div>

<!-- 검색조건-->
<table id="condTbl" class="writeview column_3 row_1">
	<tbody>
		<tr class='col3'>
			<th><spring:message code="LAB.M07.LAB00003"/></th> <!-- 사업 -->
			<td>
				<select id="condCustSoId" class="w100p">
					<c:if test="${session_user.soAuthList.size() > 1}">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					</c:if>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M09.LAB00163"/></th> <!-- 조회 유형 -->
			<td class='cols_selectInp'>
				<select id="condSearchTp" class="w35p">
					<c:if test="${searchTpCodeList.size() > 1}">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					</c:if>
					<c:forEach items="${searchTpCodeList}" var="searchTp" varStatus="status">
						<option value="${searchTp.commonCd}">${searchTp.commonCdNm}</option>
					</c:forEach>
				</select>
				<div class="inp_date">
					<input id="condSearchKey" type="text" class="" maxlength='15'/>
				</div>
			</td>
			<th><spring:message code="LAB.M01.LAB00050"/> <!-- 고객명 -->
				<span class="dot">*</span>
			</th>
			<td>
				<div class="inp_date">
					<input id="condCustNm" type="text" autofocus="autofocus" class="w100p" />
					<input id="condCustId" type="text" hidden >
					<a id="btnCustSearch" href="#" title='' class="search"></a>
				</div>
			</td>
		</tr>
	</tbody>
</table> 
<!--고객정보 -->
<table id="custInfoTbl" class="writeview column_3 row_4 tdB">
	<tbody> 
		<tr class='col3'>
			<th><spring:message code="LAB.M01.LAB00046"/></th> <!-- 고객ID-->
			<td colspan='2'>
				<div class="main_btn_box mt0 mb0">
					<div id="custId" class="inp_date np fl custInfoCls">
					</div>
					<div class="fr">
						<a href="#" class="white-btn" id="btnHomeId"><span class=""><spring:message code="LAB.M14.LAB00087"/></span></a>
					</div>
				</div>
			</td>
			<th><spring:message code="LAB.M01.LAB00053"/></th><!-- 고객유형-->
			<td colspan='2'>
				<div id="custTpNm" class="inp_date np custInfoCls">
				</div>
			</td>
			<th><spring:message code="LAB.M01.LAB00048"/></th><!-- 고객구분-->
			<td colspan='2'>
				<div id="custClNm" class="inp_date np custInfoCls">
				</div>
			</td>
		</tr>
		<tr class='col3'>
			<th><spring:message code="LAB.M09.LAB00188"/></th> <!-- 주민/법인번호-->
			<td colspan='2'>
				<div id="corpRegNo" class="inp_date np custInfoCls">
				</div>
			</td>
			<%-- <th><spring:message code="LAB.M07.LAB00014"/></th> <!-- 사업자번호-->
			<td colspan='2'>
				<div id="busiNo" class="inp_date np custInfoCls">
				</div>
			</td> --%>
			<th><spring:message code="LAB.M14.LAB00076"/></th> <!-- 휴대폰번호-->
			<td colspan='2'>
				<div id="mTelNo" class="inp_date np custInfoCls">
				</div>
			</td>
			<th><spring:message code="LAB.M08.LAB00119"/></th>   <!-- 이메일--> 
			<td colspan='2'>
				<div id="email" class="inp_date np custInfoCls">
				</div>
			</td>
		</tr>
		<tr class='col3'>
			<th><spring:message code="LAB.M09.LAB00065"/></th> <!-- 전화번호-->
			<td colspan='2'>
				<div id="telNo" class="inp_date np custInfoCls">
				</div>
			</td>
			<th><spring:message code="LAB.M15.LAB00036"/></th> <!-- FAX번호-->
			<td colspan='2'>
				<div id="faxNo" class="inp_date np custInfoCls">
				</div>
			</td>
			<th><spring:message code="LAB.M03.LAB00080"/></th>  <!-- 등록일시 -->
			<td colspan='2'>
				<div id="regDate" class="inp_date np custInfoCls">
				</div>
			</td>
		</tr>
	</tbody>
</table>

<!-- 계약정보-->
<div class='table_col_box'>
	<div class='col_left'>
		<div class="main_btn_box">
				<div class="fl">
					<h4 class="sub_title"><spring:message code="LAB.M01.LAB00040"/></h4><!-- 계약정보-->
				</div>
				<div class="fr mt10">
					<input type="checkbox" id="inputIncludeTermCtrt" name="inputIncludeTermCtrt" /> <label for="inputIncludeTermCtrt"><spring:message code="LAB.M14.LAB00051" /></label> <!--해지계약 -->
				</div>
		</div>
		<div id='ctrtGridDiv' class='col_box_table'>
			<table id='ctrtGrid' class='w100p'></table>
			<!-- <div id="ctrtGridPager"></div> -->
		</div>
	</div>
	<div class='col_right'>
		<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M01.LAB00236"/></h4> <!-- 계약변경이력-->
			</div>
			<div class="fr mt10">
			</div>
		</div>
		<div id='ctrtHistGridDiv' class='col_box_table'>
			<table id='ctrtHistGrid' class='w100p'></table>
			<!-- <div id="ctrtHistGridPager"></div> -->
		</div>
	</div>
</div>
<div class="main_btn_box">
	<div class="fr">
		<a href="#" class="white-btn" id="btnCtrtInfo"><span class=""><spring:message code="LAB.M01.LAB00040"/></span></a> <!-- 계약정보 -->
		<!-- 납부정보 -->
		<!-- <a href="#" class="white-btn" id="btnPymInfo"><span class=""><spring:message code="LAB.M02.LAB00038"/></span></a>   -->
		<!-- 청구정보 -->
		<!-- <a href="#" class="white-btn" id="btnInvoiceInfo"><span class=""><spring:message code="LAB.M10.LAB00036"/></span></a>  -->
		<!-- 청구정보 -->
		<!-- <a href="#" class="white-btn" id="btnCtrtConfInfo"><span class=""><spring:message code="LAB.M01.LAB00259"/></span></a>  -->
	</div>
</div>	

<!--계약진행 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M01.LAB00237"/></h4><!-- 계약변경-->
	</div>
	<div class="fr mt10">
		<a id='btnOrderInit' href="#" class="white-btn" title=''><span class=""><spring:message code="LAB.M10.LAB00050"/></span></a> <!-- 초기화 -->
		<a id='btnOrderProcess' href="#" class="white-btn" title=''><span class=""><spring:message code="LAB.M09.LAB00214"/></span></a> <!-- 진행 -->
		<!-- caution -->
		<div class='inpCaution'>
			<span class='dot'>*</span>
			<spring:message code="LAB.M13.LAB00034"/> <!-- 필수항목 -->
		</div>
	</div>
</div>

<!--오더진행-->
<table id="orderProcTbl" class="writeview column_3 row_3">
	<tbody> 
		<tr class='col3'>
			<th><spring:message code="LAB.M08.LAB00036"/> <!--오더유형 -->
				<span class="dot">*</span>
			</th>
			<td colspan='2'>
				<select id="orderCdSel" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
				</select>
			</td>
			<th><spring:message code="LAB.M08.LAB00033"/></th>  <!--오더ID -->
			<td colspan='2'>
				<div id="orderId" class="inp_date np orderProcCls">
				</div>
			</td>
			<th><spring:message code="LAB.M08.LAB00035"/></th> <!-- 오더상태 -->
			<td colspan='2'>
				<div id="orderStat" class="inp_date np orderProcCls">
				</div>
			</td>
		</tr>
		<tr class='col2'>
			<th><spring:message code="LAB.M09.LAB00076"/></th>  <!-- 접수일시 -->
			<td colspan='4'>
				<div id="rcptDate" class="inp_date np orderProcCls">
				</div>
			</td>
			<th><spring:message code="LAB.M08.LAB00045"/></th> <!-- 완료일시 -->
			<td colspan='4'>
				<div id="cmplDate" class="inp_date np orderProcCls">
				</div>
			</td>
		</tr>
		<tr class='col1'>
			<th><spring:message code="LAB.M06.LAB00057"/></th>  <!-- 변경내용 -->
			<td colspan='8'>
				<div class="inp_date np">
					<textarea id='rcptDesc'></textarea>
				</div>
			</td>
		</tr>
	</tbody>
</table>
<input id="linkGbn" value="N" hidden />
<!-- Layer Popup-->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>
