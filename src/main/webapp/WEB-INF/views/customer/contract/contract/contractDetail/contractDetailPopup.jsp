<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {
	$(".Layer_wrap select").selectmenu();
	/**
	  * 장비상품그리드
	  */ 
	$("#popupDeviceProdListGrid").jqGrid({
		datatype : 'local',
		mtype: 'POST',
		colModel: [
			{ label: 'soId' , name: 'so_id', hidden:true,width : 0, sortable :false},
			{ label: 'deviceProdGrp' , name: 'device_prod_grp', hidden:true,width : 0, sortable :false},
			{ label: 'deviceProdCd' , name: 'device_prod_cd', hidden:true,width : 0, sortable :false},
			{ label: 'svcGrp' , name: 'svc_grp', hidden:true,width : 0, sortable :false},
			{ label: 'svcCd' , name: 'svc_cd', hidden:true,width : 0, sortable :false},
			{ label: 'prodTyp' , name: 'prod_typ', hidden:true,width : 0, sortable :false},
			{ label: 'org_monthly_fee' , name: 'org_monthly_fee', hidden:true,width : 0, sortable :false},
			{ label: 'org_onetime_fee' , name: 'org_onetime_fee', hidden:true,width : 0, sortable :false},
			{ label: 'isMandatoryYn' , name: 'is_mandatory_yn', hidden:true,width : 0, sortable :false},
			{ label: 'isNego' , name: 'is_nego', hidden:true,width : 0, sortable :false},
			{ label: '<spring:message code="LAB.M09.LAB00228"/>', name: 'device_prod_cd', width : 120, align:"center", sortable :false},
		    { label: '<spring:message code="LAB.M09.LAB00229"/>', name: 'device_prod_cd_nm', width : 200, align:"left", sortable :false},
		    { label: '<spring:message code="LAB.M09.LAB00111"/>', name: 'item_nm', width : 200, align:"left", sortable :false},
		    { label: '<spring:message code="LAB.M09.LAB00122"/>', name: 'serial_no', width : 100, align:"left", sortable :false},
		    { label: '<spring:message code="LAB.M07.LAB00247"/>', name: 'device_cnt', width : 80, align:"center", sortable :false},
		    { label: '<spring:message code="LAB.M08.LAB00093"/>', name: 'monthly_fee', width : 90, align : "right", formatter : 'integer', sorttype:'number', sortable :false},
		    { label: '<spring:message code="LAB.M08.LAB00144"/>', name: 'onetime_fee', width : 90, align : "right", formatter : 'integer', sorttype:'number', sortable :false},
		    { label: '<spring:message code="LAB.M13.LAB00033"/>' , name: 'is_mandatory_yn_nm', width : 80, align:"center", sortable :false},
		    { label: '<spring:message code="LAB.M14.LAB00078"/>', name: 'is_nego_nm', width : 120, align:"center", sortable :false}
		],
		viewrecords: true,
		shrinkToFit:false,
		loadonce : true,
		height: 130,
		sortable : true,
		rowNum : 10000,
		jsonReader: {
			repeatitems : true,
			root : "deviceProdList"
		},
      onCellSelect : function(rowid, index, contents, event){

      },
      loadComplete : function () {
      	  $("#popupDeviceProdListGrid").setGridWidth($('#popupProdInfo_deviceProdInfo_deviceListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
          $("#popupDeviceProdListGrid").trigger("reloadGrid");
      },
      loadError: function (jqXHR, textStatus, errorThrown) {
       	ajaxErrorCallback(jqXHR);
	    },
  		sortable: { 
	   		update: function(permutation) {
		    		$("#popupDeviceProdListGrid").setGridWidth($('#popupProdInfo_deviceProdInfo_deviceListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	   		}
  		}
	});
	
	/**
	  * 부가상품그리드
	  */ 
	$("#popupAddonProdListGrid").jqGrid({
		datatype : 'local',
		mtype: 'POST',
		colModel: [
			{ label: 'soId' , name: 'so_id', hidden:true,width : 0,sortable :false},
			{ label: 'addProdGrp' , name: 'add_prod_grp', hidden:true,width : 0,sortable :false},
			{ label: 'bProdList' , name: 'rel_b_prod_list', hidden:true,width : 0,sortable :false},
			{ label: 'xProdList' , name: 'rel_x_prod_list', hidden:true,width : 0,sortable :false},
			{ label: 'svcGrp' , name: 'svc_grp', hidden:true,width : 0,sortable :false},
			{ label: 'svcCd' , name: 'svc_cd', hidden:true,width : 0,sortable :false},
			{ label: 'prodTyp' , name: 'prod_typ', hidden:true,width : 0,sortable :false},
			{ label: 'basicProdFl' , name: 'basic_prod_fl', hidden:true,width : 0,sortable :false},
			{ label: 'org_monthly_fee' , name: 'org_monthly_fee', hidden:true,width : 0, sortable :false},
			{ label: 'org_onetime_fee' , name: 'org_onetime_fee', hidden:true,width : 0, sortable :false},
			{ label: 'mantatory' , name: 'add_prod_mandatory_yn', hidden:true,width : 0, sortable :false},
			{ label: 'isNego' , name: 'is_nego', hidden:true,width : 0, sortable :false},
			{ label: '<spring:message code="LAB.M06.LAB00087"/>', name: 'add_prod_cd', width : 100, align:"center",sortable :false},
			{ label: '<spring:message code="LAB.M06.LAB00085"/>', name: 'add_prod_nm', width : 200, align:"left",sortable:false},
			{ label: '<spring:message code="LAB.M07.LAB00247"/>', name: 'cnt', width : 80, align:"center", editable:true, sortable :false},
		    { label: '<spring:message code="LAB.M13.LAB00033"/>', name: 'add_prod_mandatory_yn_nm', width : 80, align:"center",sortable :false}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 130,
		sortable : true,
		loadonce : true,
		rowNum : 10000,
		jsonReader: {
			repeatitems : true,
			root : "addProdList"
		},
      	onCellSelect : function(rowid, index, contents, event){
      	},
      	loadComplete : function () {

      		$("#popupAddonProdListGrid").setGridWidth($('#popupProdInfo_addonProdInfo_addonListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
          	$("#popupAddonProdListGrid").trigger("reloadGrid");
      	},
      	loadError: function (jqXHR, textStatus, errorThrown) {
       	ajaxErrorCallback(jqXHR);
	        
	    },
  		sortable: { update: function(permutation) {
  			$("#popupAddonProdListGrid").setGridWidth($('#popupProdInfo_addonProdInfo_addonListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
  		}
  		}
	});

	
	/**
	  * 월정액설정그리드
	  */ 
	$("#popupMonthlyFeeGrid").jqGrid({
		datatype : 'local',
		mtype: 'POST',
		colModel: [
					{ label: 'soId' , name: 'so_id', hidden:true,width : 0},
					{ label: 'ProdGrp' , name: 'prod_grp', hidden:true,width : 0},
					{ label: 'svcGrp' , name: 'svc_grp', hidden:true,width : 0},
					{ label: 'svcCd' , name: 'svc_cd', hidden:true,width : 0},
					{ label: 'prodTyp' , name: 'prod_typ', hidden:true,width : 0},
					{ label: 'prodKey' , name: 'prod_key', hidden:true,width : 0, key:true},
					{ label: 'singleKey' , name: 'single_key', hidden:true,width : 0},
					{ label: 'isNego' , name: 'is_nego', hidden:true,width : 0, sortable :false},
					{ label: '<spring:message code="LAB.M07.LAB00325"/>', name: 'nego_prod_cd', width : 120, align:"center", sortable :false},
				    { label: '<spring:message code="LAB.M07.LAB00130"/>', name: 'nego_prod_cd_nm', width : 180, align:"left", sortable :false},
				    { label: '<spring:message code="LAB.M07.LAB00247"/>', name: 'nego_cnt', width : 70, align:"center", sortable :false},
				    { label: '<spring:message code="LAB.M13.LAB00004"/>', name: 'sale_amt', width : 100, formatter : 'integer', align : "right"},
				    { label: '<spring:message code="LAB.M14.LAB00033"/>', name: 'discount_rate', width : 80, align:"center",sortable :false},
				    { label: '<spring:message code="LAB.M14.LAB00078"/>', name: 'nego_amt', width : 100, formatter : 'integer', align : "right", sortable :false}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 130,
		loadonce : true,
		sortable : false,
		cellEdit : true,
		rowNum : 10000,
		cellsubmit:'clientArray',
		onCellSelect : function(rowid, index, contents, event){
		},
	  	loadComplete : function () {
	  		$("#popupMonthlyFeeGrid").setGridWidth($('#popupProdInfo_monthlyFeeInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	      	$("#popupMonthlyFeeGrid").trigger("reloadGrid");
	  	},
	  	loadError: function (jqXHR, textStatus, errorThrown) {
	   		ajaxErrorCallback(jqXHR);
		}
	});
		
	/**
	  * 일회성요금설정그리드
	  */ 
	$("#popupOnetimeChargeGrid").jqGrid({
		datatype : 'local',
		mtype: 'POST',
		colModel: [
					{ label: 'soId' , name: 'so_id', hidden:true,width : 0},
					{ label: 'ProdGrp' , name: 'prod_grp', hidden:true,width : 0},
					{ label: 'svcGrp' , name: 'svc_grp', hidden:true,width : 0},
					{ label: 'svcCd' , name: 'svc_cd', hidden:true,width : 0},
					{ label: 'prodTyp' , name: 'prod_typ', hidden:true,width : 0},
					{ label: 'prodKey' , name: 'prod_key', hidden:true,width : 0, key:true},
					{ label: 'singleKey' , name: 'single_key', hidden:true,width : 0},
					{ label: 'isNego' , name: 'is_nego', hidden:true,width : 0, sortable :false},
					{ label: '<spring:message code="LAB.M07.LAB00325"/>', name: 'nego_prod_cd', width : 120, align:"center", sortable :false},
				    { label: '<spring:message code="LAB.M07.LAB00130"/>', name: 'nego_prod_cd_nm', width : 180, align:"left", sortable :false},
				    { label: '<spring:message code="LAB.M07.LAB00247"/>', name: 'nego_cnt', width : 70, align:"center", sortable :false},
				    { label: '<spring:message code="LAB.M13.LAB00004"/>', name: 'sale_amt', width : 100, formatter : 'integer', align : "right"},
				    { label: '<spring:message code="LAB.M14.LAB00033"/>', name: 'discount_rate', width : 80, align:"center", sortable :false},
				    { label: '<spring:message code="LAB.M14.LAB00078"/>', name: 'nego_amt', width : 100, formatter : 'integer', align : "right", sortable :false}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 130,
		loadonce : true,
		sortable : false,
		cellEdit : true,
		rowNum : 10000,
		cellsubmit:'clientArray',
      	onCellSelect : function(rowid, index, contents, event){
      	},
      	loadComplete : function () {
      		$("#popupOnetimeChargeGrid").setGridWidth($('#popupProdInfo_onetimeChargeInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
          	$("#popupOnetimeChargeGrid").trigger("reloadGrid");
      	},
      	loadError: function (jqXHR, textStatus, errorThrown) {
       	ajaxErrorCallback(jqXHR);
	        
	    }
	});
	

	//기본상품이력 GRID	
	$("#popupBaseProdHistGrid").jqGrid({ 
		datatype: "local",
		height : 170,
		viewrecords: true,
		shrinkToFit:false,
		sortable : true,
		loadonce : true,
		rowNum : 10000,
		jsonReader: {
			repeatitems : true,
			root : "basicHistList"
		},
		colModel:[
			{ label: '<spring:message code="LAB.M07.LAB00287"/>', name: 'act_dttm', width : 150, align:"center",formatter: stringTypeFormatterYYYYMMDDHH24MISS},
			{ label: '<spring:message code="LAB.M09.LAB00168"/>', name: 'inact_dttm', width : 150, align:"center",formatter: stringTypeFormatterYYYYMMDDHH24MISS},
			{ label: '<spring:message code="LAB.M07.LAB00126"/>', name: 'prod_grp', width : 100, align:"center"},
			{ label: '<spring:message code="LAB.M07.LAB00127"/>', name: 'prod_grp_nm', width : 120, align:"left"},
			{ label: '<spring:message code="LAB.M07.LAB00156"/>', name: 'prod_cd', width : 100, align:"center"},
			{ label: '<spring:message code="LAB.M07.LAB00130"/>', name: 'prod_cd_nm', width : 200, align:"left"},
			{ label: '<spring:message code="LAB.M07.LAB00110"/>', name: 'prod_stat_cd_nm', width : 80, align:"center"},
			{ label: '<spring:message code="LAB.M07.LAB00256"/>', name: 'chgr_nm', width : 200, align:"center"}
		],
       	loadComplete : function (data) {
       		$("#popupBaseProdHistGrid").setGridWidth($('#popupBaseProdHistGridDiv').width(),false);
            $("#popupBaseProdHistGrid").trigger("reloadGrid");
       	},
       	sortable: { 
    		update: function(permutation) {
    			$("#popupBaseProdHistGrid").setGridWidth($('#popupBaseProdHistGridDiv').width(),false);
    		}
       	},
	   	loadError: function (jqXHR, textStatus, errorThrown) {
	    	ajaxErrorCallback(jqXHR);
	    }
	});
	
	//장비상품이력 GRID
	$("#popupDeviceProdHistGrid").jqGrid({ 
		datatype: "local",
		height : 140,
		loadonce : true,
		viewrecords: true,
		shrinkToFit:false,
		sortable : true,
		loadonce : true,
		rowNum : 10000,
		jsonReader: {
			repeatitems : true,
			root : "deviceHistList"
		},
		colModel:[
			{ label: '<spring:message code="LAB.M07.LAB00287"/>', name: 'act_dttm', width : 150, align:"center",formatter: stringTypeFormatterYYYYMMDDHH24MISS},
			{ label: '<spring:message code="LAB.M09.LAB00168"/>', name: 'inact_dttm', width : 150, align:"center",formatter: stringTypeFormatterYYYYMMDDHH24MISS},
			{ label: '<spring:message code="LAB.M07.LAB00126"/>', name: 'prod_grp', width : 100, align:"center"},
			{ label: '<spring:message code="LAB.M07.LAB00127"/>', name: 'prod_grp_nm', width : 120, align:"left"},
			{ label: '<spring:message code="LAB.M07.LAB00156"/>', name: 'prod_cd', width : 100, align:"center"},
			{ label: '<spring:message code="LAB.M07.LAB00130"/>', name: 'prod_cd_nm', width : 200, align:"left"},
			{ label: '<spring:message code="LAB.M07.LAB00247"/>', name: 'prod_cnt', width : 80, align:"center"},
			{ label: '<spring:message code="LAB.M07.LAB00110"/>', name: 'prod_stat_cd_nm', width : 80, align:"center"},
			{ label: '<spring:message code="LAB.M07.LAB00256"/>', name: 'chgr_nm', width : 200, align:"center"}
		],
		loadComplete : function () {
			
      		$("#popupDeviceProdHistGrid").setGridWidth($('#popupHistInfo_deviceProdInfo_deviceProdListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
          	$("#popupDeviceProdHistGrid").trigger("reloadGrid");
      	}
	});

	//부가서비스이력 GRID
	$("#popupAddonHistGrid").jqGrid({ 
		datatype: "local",
		height : 140,
		loadonce : true,
		viewrecords: true,
		shrinkToFit:false,
		sortable : true,
		loadonce : true,
		rowNum : 10000,
		jsonReader: {
			repeatitems : true,
			root : "addonHistList"
		},
		colModel:[
			{ label: '차수', name: 'rt_id', width : 150, align:"center"},
			{ label: '<spring:message code="LAB.M07.LAB00287"/>', name: 'act_dttm', width : 150, align:"center",formatter: stringTypeFormatterYYYYMMDDHH24MISS},
			{ label: '<spring:message code="LAB.M09.LAB00168"/>', name: 'inact_dttm', width : 150, align:"center",formatter: stringTypeFormatterYYYYMMDDHH24MISS},
			{ label: '<spring:message code="LAB.M07.LAB00127"/>', name: 'prod_grp_nm', width : 120, align:"left"},
			{ label: '<spring:message code="LAB.M07.LAB00156"/>', name: 'prod_cd', width : 100, align:"center"},
			{ label: '<spring:message code="LAB.M07.LAB00130"/>', name: 'prod_cd_nm', width : 200, align:"left"},
			{ label: '<spring:message code="LAB.M07.LAB00247"/>', name: 'prod_cnt', width : 80, align:"center"},
			{ label: '<spring:message code="LAB.M07.LAB00110"/>', name: 'prod_stat_cd_nm', width : 80, align:"center"},
			{ label: '<spring:message code="LAB.M07.LAB00256"/>', name: 'chgr_nm', width : 200, align:"center"}
		],
	});
	
	//일시정지이력 GRID
	$("#popupSuspentionHistGrid").jqGrid({ 
		datatype: "local",
		height : 140,
		loadonce : true,
		viewrecords: true,
		shrinkToFit:false,
		sortable : true,
		loadonce : true,
		rowNum : 10000,
		jsonReader: {
			repeatitems : true,
			root : "suspentionHistList"
		},
		colModel:[
			{ label: '<spring:message code="LAB.M07.LAB00287"/>', name: 'act_dttm', width : 150, align:"center",formatter: stringTypeFormatterYYYYMMDDHH24MISS},
			{ label: '<spring:message code="LAB.M09.LAB00168"/>', name: 'inact_dttm', width : 150, align:"center",formatter: stringTypeFormatterYYYYMMDDHH24MISS},
			{ label: '<spring:message code="LAB.M01.LAB00037"/>', name: 'ctrt_stat_nm', width : 80, align:"center"},
			{ label: '<spring:message code="LAB.M06.LAB00058"/>', name: 'ctrt_chg_resn_cd_nm', width : 150, align:"center"},
			{ label: '<spring:message code="LAB.M06.LAB00093"/>', name: 'remark', width : 250, align:"left"},
			{ label: '<spring:message code="LAB.M07.LAB00256"/>', name: 'chgr_nm', width : 150, align:"center"}
		],
	});
	
	//설치주소이력GRID
	$("#popupInstlAddrHistGrid").jqGrid({ 
		datatype: "local",
		height : 170,
		loadonce : true,
		viewrecords: true,
		shrinkToFit:false,
		sortable : true,
		loadonce : true,
		rowNum : 10000,
		jsonReader: {
			repeatitems : true,
			root : "instlAddrHistList"
		},
		colModel:[
			{ label: '<spring:message code="LAB.M07.LAB00287"/>', name: 'act_dttm', width : 150, align:"center",formatter: stringTypeFormatterYYYYMMDDHH24MISS},
			{ label: '<spring:message code="LAB.M09.LAB00168"/>', name: 'inact_dttm', width : 150, align:"center",formatter: stringTypeFormatterYYYYMMDDHH24MISS},
			{ label: '<spring:message code="LAB.M08.LAB00087"/>', name: 'instl_zip_cd', width : 80, align:"center"},
			{ label: '<spring:message code="LAB.M01.LAB00218"/>', name: 'instl_base_addr', width : 100, align:"left"},
			{ label: '<spring:message code="LAB.M07.LAB00102"/>', name: 'instl_addr_dtl', width : 100, align:"left"}
			// { label: '<spring:message code="LAB.M03.LAB00087"/>', name: 'instl_city', width : 100, align:"left"},
			// { label: '<spring:message code="LAB.M09.LAB00232"/>', name: 'instl_state_nm', width : 100, align:"left"}
		],
	});
	
	//공정률이력 GRID
	// $("#popupProcRateHistGrid").jqGrid({ 
	// 	datatype: "local",
	// 	height : 170,
	// 	loadonce : true,
	// 	viewrecords: true,
	// 	shrinkToFit:false,
	// 	sortable : true,
	// 	loadonce : true,
	// 	rowNum : 10000,
	// 	jsonReader: {
	// 		repeatitems : true,
	// 		root : "procRateHistList"
	// 	},
	// 	colModel:[
	// 		{ label: '<spring:message code="LAB.M07.LAB00287"/>', name: 'act_dttm', width : 150, align:"center",formatter: stringTypeFormatterYYYYMMDDHH24MISS},
	// 		{ label: '<spring:message code="LAB.M09.LAB00168"/>', name: 'inact_dttm', width : 150, align:"center",formatter: stringTypeFormatterYYYYMMDDHH24MISS},
	// 		{ label: '<spring:message code="LAB.M01.LAB00235"/>', name: 'proc_rate', width : 100, align:"center"},
	// 		{ label: '<spring:message code="LAB.M06.LAB00093"/>', name: 'remark', width : 250, align:"left"},
	// 		{ label: '<spring:message code="LAB.M07.LAB00256"/>', name: 'chgr_nm', width : 100, align:"center"}
	// 	],
	// });
	
	//납부자이력GRID
	// $("#popupPayerHistGrid").jqGrid({ 
	// 	datatype: "local",
	// 	height : 170,
	// 	loadonce : true,
	// 	viewrecords: true,
	// 	shrinkToFit:false,
	// 	sortable : true,
	// 	loadonce : true,
	// 	rowNum : 10000,
	// 	jsonReader: {
	// 		repeatitems : true,
	// 		root : "payerHistList"
	// 	},
	// 	colModel:[
	// 		{ label: '<spring:message code="LAB.M07.LAB00287"/>', name: 'act_dttm', width : 150, align:"center",formatter: stringTypeFormatterYYYYMMDDHH24MISS},
	// 		{ label: '<spring:message code="LAB.M09.LAB00168"/>', name: 'inact_dttm', width : 150, align:"center",formatter: stringTypeFormatterYYYYMMDDHH24MISS},
	// 		{ label: '<spring:message code="LAB.M02.LAB00006"/>', name: 'pym_acnt_id', width : 100, align:"center"},
	// 		{ label: '<spring:message code="LAB.M02.LAB00008"/>', name: 'acnt_nm', width : 150, align:"left"},
	// 		{ label: '<spring:message code="LAB.M01.LAB00046"/>', name: 'cust_id', width : 100, align:"center"},
	// 		{ label: '<spring:message code="LAB.M01.LAB00050"/>', name: 'cust_nm', width : 150, align:"left"},
	// 		{ label: '<spring:message code="LAB.M02.LAB00016"/>', name: 'pym_mthd_nm', width : 150, align:"center"}
	// 	],
	// });
	
	/**
	  * 사용상품정보 Tab Event
	  */
	$("#popupProdInfoTabs li").click(function () {
		var _this = $(this);
		var _parent = _this.parent('#popupProdInfoTabs');
		var _preTab = _parent.find('li.active').attr("rel");
		var activeTab = $(this).attr("rel");
		if(_preTab == activeTab){
			return;
		}

		_parent.find('li').removeClass('active').css('color','#8b8d90');
		$(this).addClass("active").css('color','#2c7ed9');
        var _targetDiv = _parent.next('.tab_box');
		var _targetC = _targetDiv.find('.tab_content');
		
		_targetC.hide();
        
        _targetDiv.find("#" + activeTab).fadeIn();

        var index = $("#popupProdInfoTabs li").index(this);
		if(index == 0){
			$("#popupBasicProdListGrid").setGridWidth($('#popupProdInfo_basicProdInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}else if(index == 1){
			$("#popupDeviceProdListGrid").setGridWidth($('#popupProdInfo_deviceProdInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}else if(index == 2){
			$("#popupAddonProdListGrid").setGridWidth($('#popupProdInfo_addonProdInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}else if(index == 3){
			
		}else if(index == 4){
			$("#popupMonthlyFeeGrid").setGridWidth($('#popupProdInfo_monthlyFeeInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}else if(index == 5){
			$("#popupOnetimeChargeGrid").setGridWidth($('#popupProdInfo_onetimeChargeInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}
	});

	//변경이력 Tab 처리
	$("#popupHistInfoTabs li").click(function () {
		
		var _this = $(this);
		var _parent = _this.parent('#popupHistInfoTabs');
		var _preTab = _parent.find('li.active').attr("rel");
		var activeTab = $(this).attr("rel");
		if(_preTab == activeTab){
			return;
		}

		_parent.find('li').removeClass('active').css('color','#8b8d90');
		$(this).addClass("active").css('color','#2c7ed9');
        var _targetDiv = _parent.next('.tab_box');
		var _targetC = _targetDiv.find('.tab_content');
		
		_targetC.hide();
        
        _targetDiv.find("#" + activeTab).fadeIn();

        var index = $("#popupHistInfoTabs li").index(this);

		if(index==0){
			$("#popupBaseProdHistGrid").setGridWidth($('#popupBaseProdHistGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}else if(index == 1){
			$("#popupDeviceProdHistGrid").setGridWidth($('#popupHistInfo_deviceProdInfo_deviceProdListDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리	
		}else if(index == 2){
			$("#popupAddonHistGrid").setGridWidth($('#popupHistInfo_addonInfo_addonListDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리	
		}else if(index == 3){
			$("#popupSuspentionHistGrid").setGridWidth($('#popupHistInfo_suspentionInfo_suspentionListDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리	
		}else if(index == 4){
			$("#popupInstlAddrHistGrid").setGridWidth($('#popupHistInfo_instlAddrInfo_instlAddrListDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리	
		}
		// }else if(index == 5){
		// 	$("#popupProcRateHistGrid").setGridWidth($('#popupHistInfo_procRateHistInfo_procRateListDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리	
		// }else if(index == 6){
		// 	$("#popupPayerHistGrid").setGridWidth($('#popupHistInfo_payerHistInfo_payerListDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리	
		// }
		
	});

	// TAB 초기화
	$(".tab_content").hide();
	var _thisParent = $('#popupProdInfoTabs').next('.tab_box');
	var _thisFirst = _thisParent.find('.tab_content:first');
	_thisFirst.show();

	var _histParent = $('#popupHistInfoTabs').next('.tab_box');
	var _histFirst = _histParent.find('.tab_content:first');
	_histParent.show();

	//사용중인 계약정보 불러오기
 	getProdInfo();

 	//사용중인 계약 이력 불러오기
 	getProdHistInfo();

 	$('#popupHistInfo_deviceProdInfo_prodSel').selectmenu({
	    change: function() {
	    	 popupDeviceFilterChange(); 
	    }
	});

	$('#popupHistInfo_addonInfo_prodSel').selectmenu({
	    change: function() {
	    	 popupAddonFilterChange(); 
	    }
	});

 	
});
function popupDeviceFilterChange() {
	var searchFiler = $('#popupHistInfo_deviceProdInfo_prodSel').val();


	var filtersParam = null;
	if(searchFiler != 'SEL'){
		filtersParam = { "groupOp": "AND", "rules": [{ "field": "prod_cd", "op": "cn", "data": searchFiler}] };

	}
	$("#popupDeviceProdHistGrid").setGridParam({
            postData: {
                filters: filtersParam
            },
            search: true
        });
    $("#popupDeviceProdHistGrid").trigger("reloadGrid");
}

function popupAddonFilterChange(){
	var searchFiler = $('#popupHistInfo_addonInfo_prodSel').val();


	var filtersParam = null;
	if(searchFiler != 'SEL'){
		filtersParam = { "groupOp": "AND", "rules": [{ "field": "prod_cd", "op": "cn", "data": searchFiler}] };

	}
	$("#popupAddonHistGrid").setGridParam({
            postData: {
                filters: filtersParam
            },
            search: true
        });
    $("#popupAddonHistGrid").trigger("reloadGrid");

}
/**
 * 현재 사용중인 계약정보 조회
 */ 
function getProdInfo() {
	var url = '/customer/contract/contract/orderManagement/getCtrtInfoAction.json';
	$.ajax({
        url:url,
        type:'POST',
        data : {
        	 soId : '${ctrtInfo.soId}'
        	,custId : '${ctrtInfo.custId}'
        	,ctrtId : '${ctrtInfo.ctrtId}'
        },
        dataType: 'json',
        success: function(data){
        	setUsingCtrtInfo(data);
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		ajaxErrorCallback(err);
     	}
    });
}

/**
 * 계약 정보 이력 조회
 */
function getProdHistInfo() {
	var url = '/customer/contract/contract/orderManagement/getCtrtHistAction.json';
	$.ajax({
        url:url,
        type:'POST',
        data : {
        	 soId : '${ctrtInfo.soId}'
        	,custId : '${ctrtInfo.custId}'
        	,ctrtId : '${ctrtInfo.ctrtId}'
        },
        dataType: 'json',
        success: function(data){
        	setCtrtHistInfo(data);
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		ajaxErrorCallback(err);
     	}
    });
}

/**
 * 사용중 계약 정보 세팅
 */
function setUsingCtrtInfo(data){
	
	//고객계약현황 세팅
	$("#popCustId").append('${ctrtInfo.custId}');		//고객ID
	$("#popCustNm").append('${ctrtInfo.custNm}');		//고객명
	$("#popCtrtId").append('${ctrtInfo.ctrtId}');		//계약ID
	$("#popPymAcntId").append('${ctrtInfo.ctrtNm}');		//서비스번호
	
	//사용중인 기본상품정보 조회
	$("#popupProdInfo_basicProdInfo_prodGrp").append(data.basicProdInfo.prod_grp_nm);
	$("#popupProdInfo_basicProdInfo_rateStrtDt").append(stringToDateformatYYYYMMDD(data.basicProdInfo.rate_strt_dt));
	$("#popupProdInfo_basicProdInfo_prodNm").append(getNameAndId(data.basicProdInfo.prod_cd , data.basicProdInfo.prod_cd_nm));
	$("#popupProdInfo_basicProdInfo_actDttm").append(stringToDateformatYYYYMMDDHH24MISS(data.basicProdInfo.act_dttm));
	$("#popupProdInfo_basicProdInfo_ctrtStat").append(data.basicProdInfo.ctrt_stat_nm);
	$("#popupProdInfo_basicProdInfo_promPrd").append(data.basicProdInfo.prom_cnt+" "+'<spring:message code="LAB.M01.LAB00016"/>');
	
	$("#popupProdInfo_basicProdInfo_m_no_nm").append(data.basicProdInfo.m_no_nm);
	$("#popupProdInfo_basicProdInfo_basic_core_cnt").append(data.basicProdInfo.basic_core_cnt);
	$("#popupProdInfo_basicProdInfo_add_core_cnt").append(data.basicProdInfo.add_core_cnt);
	$("#popupProdInfo_basicProdInfo_fix_rate").append(data.basicProdInfo.fix_rate);
	$("#popupProdInfo_basicProdInfo_use_rate").append(data.basicProdInfo.use_rate);
	$("#popupProdInfo_basicProdInfo_rt_id_nm").append(data.basicProdInfo.rt_id_nm);
	$("#popupProdInfo_basicProdInfo_local_nm").append(data.basicProdInfo.local_nm);
	$("#popupProdInfo_basicProdInfo_os_typ_nm").append(data.basicProdInfo.os_typ_nm);

	$("#popupProdInfo_basicProdInfo_item01").append(data.basicProdInfo.item01);
	$("#popupProdInfo_basicProdInfo_item02").append(data.basicProdInfo.item02);
	$("#popupProdInfo_basicProdInfo_item03").append(data.basicProdInfo.item03);
	$("#popupProdInfo_basicProdInfo_item04").append(data.basicProdInfo.item04);
	$("#popupProdInfo_basicProdInfo_item05").append(data.basicProdInfo.item05);
	$("#popupProdInfo_basicProdInfo_item06").append(data.basicProdInfo.item06);
	$("#popupProdInfo_basicProdInfo_item07").append(data.basicProdInfo.item07);
	$("#popupProdInfo_basicProdInfo_item07_nm").append(data.basicProdInfo.item07_nm);
	$("#popupProdInfo_basicProdInfo_item08").append(data.basicProdInfo.item08);
	$("#popupProdInfo_basicProdInfo_item09_nm").append(data.basicProdInfo.item09_nm);
	$("#popupProdInfo_basicProdInfo_item10").append(data.basicProdInfo.item10);
	$("#popupProdInfo_basicProdInfo_package_cd_nm").append(data.basicProdInfo.package_cd_nm);

	//사용중인 장비상품정보 조회
	$("#popupDeviceProdListGrid").setGridParam({
		 data: data.deviceInfoList
		,rowNum : data.deviceInfoList.length
	});
	
   	$("#popupDeviceProdListGrid").trigger("reloadGrid");
   	
   	//사용중인 부가상품정보조회
   	$("#popupAddonProdListGrid").setGridParam({
		data: data.addInfoList
		,rowNum : data.addInfoList.length
	});
   	$("#popupAddonProdListGrid").trigger("reloadGrid");
   	
   	//사용중인 월정액 조회
   	$("#popupMonthlyFeeGrid").setGridParam({
		data: data.monthlyFeeList
		,rowNum : data.monthlyFeeList.length
	});
   	$("#popupMonthlyFeeGrid").trigger("reloadGrid");
   	//사용중인 일회성 조회
	$("#popupOnetimeChargeGrid").setGridParam({
		 data: data.oneTimeFeeList
		 ,rowNum : data.oneTimeFeeList.length
	});
   	$("#popupOnetimeChargeGrid").trigger("reloadGrid");
   
  	//사용중인 설치주소정보 조회
	$("#popupProdInfo_instAddrInfo_zipCd").append(data.basicProdInfo.instl_zip_cd);
	if(data.basicProdInfo.instl_addr_dtl == undefined || data.basicProdInfo.instl_addr_dtl == ''){
		$("#popupProdInfo_instAddrInfo_addr").append(data.basicProdInfo.instl_base_addr);
	}else{
		$("#popupProdInfo_instAddrInfo_addr").append(data.basicProdInfo.instl_base_addr+" "+data.basicProdInfo.instl_addr_dtl);
	}
	
	// $("#popupProdInfo_instAddrInfo_city").append(data.basicProdInfo.instl_city);
	// $("#popupProdInfo_instAddrInfo_state").append(data.basicProdInfo.instl_state_nm);
	

	//GRID disable
	$('#popupDeviceProdListGrid').jqGrid("resetSelection");
	disable_popup_grid('popupDeviceProdListGrid');
	
	$('#popupAddonProdListGrid').jqGrid("resetSelection");
	disable_popup_grid('popupAddonProdListGrid');
	
	$('#popupMonthlyFeeGrid').jqGrid("resetSelection");
	$('#popupOnetimeChargeGrid').jqGrid("resetSelection");
	disable_popup_grid('popupMonthlyFeeGrid');
	disable_popup_grid('popupOnetimeChargeGrid');
	
}

/**
 * 계약 이력 정보 세팅
 */
function setCtrtHistInfo(data){
	// 기본상품이력
	$("#popupBaseProdHistGrid").setGridParam({
		data: data.basicHistList
		,rowNum : data.basicHistList.length
	});
	$("#popupBaseProdHistGrid").trigger("reloadGrid");
	$('#popupBaseProdHistGrid').jqGrid("resetSelection");
	disable_popup_grid('popupBaseProdHistGrid');

	// 장비상품이력
	$("#popupDeviceProdHistGrid").setGridParam({
		data: data.deviceHistList
	   ,rowNum : data.deviceHistList.length
	});
	$("#popupDeviceProdHistGrid").trigger("reloadGrid");
	$('#popupDeviceProdHistGrid').jqGrid("resetSelection");
	disable_popup_grid('popupDeviceProdHistGrid');

	// 부가상품이력
	$("#popupAddonHistGrid").setGridParam({
		data: data.addonHistList
		,rowNum : data.addonHistList.length
	});
	$("#popupAddonHistGrid").trigger("reloadGrid");
	$('#popupAddonHistGrid').jqGrid("resetSelection");
	disable_popup_grid('popupAddonHistGrid');

	// 일시정지이력
	$("#popupSuspentionHistGrid").setGridParam({
		data: data.suspentionHistList
		,rowNum : data.suspentionHistList.length
	});
	$("#popupSuspentionHistGrid").trigger("reloadGrid");
	$('#popupSuspentionHistGrid').jqGrid("resetSelection");
	disable_popup_grid('popupSuspentionHistGrid');

	// 설치주소이력
	$("#popupInstlAddrHistGrid").setGridParam({
		data: data.instlAddrHistList
		,rowNum : data.instlAddrHistList.length
	});
	$("#popupInstlAddrHistGrid").trigger("reloadGrid");
	$('#popupInstlAddrHistGrid').jqGrid("resetSelection");
	disable_popup_grid('popupInstlAddrHistGrid');

	// 납부자이력
	// $("#popupPayerHistGrid").setGridParam({
	// 	data: data.payerHistList
	// 	,rowNum : data.payerHistList.length
	// });
	// $("#popupPayerHistGrid").trigger("reloadGrid");
	// $('#popupPayerHistGrid').jqGrid("resetSelection");
	// disable_popup_grid('popupPayerHistGrid');

	// 공정률이력
	// $("#popupProcRateHistGrid").setGridParam({
	// 	data: data.procRateHistList
	// 	,rowNum : data.procRateHistList.length
	// });
	// $("#popupProcRateHistGrid").trigger("reloadGrid");
	// $('#popupProcRateHistGrid').jqGrid("resetSelection");
	// disable_popup_grid('popupProcRateHistGrid');

	$(data.deviceProdList).each(function(index, item){
		var str = '<option value="'+ item.prod_cd+'">'+ item.prod_cd_nm+'</option>';
		$('#popupHistInfo_deviceProdInfo_prodSel').append(str);  
    });

    $(data.addonProdList).each(function(index, item){
		var str = '<option value="'+ item.prod_cd+'">'+ item.prod_cd_nm+'</option>';
		$('#popupHistInfo_addonInfo_prodSel').append(str);  
    });

    
	$('#popupHistInfo_deviceProdInfo_prodSel').val('SEL');
	$('#popupHistInfo_addonInfo_prodSel').val('SEL');

}

/*
 * 명칭 포맷팅
 */
function getNameAndId(id, name){
	if(name == '' || name == null){
		return id;
	}else{
		return name + '(' + id + ')'; 
	}
	 
}


/**
 * 그리드 Diable 처리
 */
function disable_popup_grid(id){
	$("#" + id).unbind("contextmenu");
	$("#" + id).unbind("mouseover");
	$("#" + id).unbind("mouseout");
	$("#" + id).setGridParam({
		hoverrows : false,
		beforeSelectRow: function(rowid, e) {
			return false;
		}, 
		onSelectRow: function( rowid, status, event ) {
		    return false;
		}
	});
}

function openContractDetail(){
	$('#popupProdInfo_deviceProdInfoHist').trigger('click');
	$('#popupProdInfo_basicProdInfoHist').trigger('click');
}
</script>

<div class='layer_top layer-minw1100'>
	<div class='title'><spring:message code="LAB.M07.LAB00305"/>
	</div>
	<a href='#' class='close'></a>
</div>

<div class='layer_box layer-minw1100'>
	<div class='main_btn_box'>
		<div class='fl'>
			<h4 class='sub_title'><spring:message code="LAB.M01.LAB00231"/></h4>
		</div>
	</div>
	<table class='writeview tdB column_4 row_3'>
		<tr class='col4'>
			<th><spring:message code="LAB.M01.LAB00046"/></th>
			<td>
				<div id="popCustId" class="inp_date np custInfoCls"></div>
			</td>
			<th><spring:message code="LAB.M01.LAB00050"/></th>
			<td>
				<div id="popCustNm" class="inp_date np custInfoCls"></div>
			</td>
			<th><spring:message code="LAB.M01.LAB00032"/></th>
			<td>
				<div id="popCtrtId" class="inp_date np custInfoCls"></div>
			</td>
			<th>HOST NMAE</th>
			<td>
				<div id="popPymAcntId" class="inp_date np custInfoCls"></div>
			</td>
		</tr>
	</table>
	<!--//1th table-->
	<ul id='popupProdInfoTabs' class="tabs">
		<li id="popupProdInfo_basicProdInfo" class="active" rel="popupProdInfo_basicProdInfoDiv"><spring:message code="LAB.M01.LAB00210"/></li> <!-- 기본상품 -->
		<li id="popupProdInfo_deviceProdInfo" rel="popupProdInfo_deviceProdInfoDiv"><spring:message code="LAB.M09.LAB00042"/></li> <!-- 장비상품 -->
		<li id="popupProdInfo_addonProdInfo" rel="popupProdInfo_addonProdInfoDiv">부가상품</li> <!-- 부가서비스 -->
		<li id="popupProdInfo_instAddrInfo" rel="popupProdInfo_instAddrInfoDiv"><spring:message code="LAB.M07.LAB00301"/></li> <!-- 설치주소 -->
		<li id="popupProdInfo_monthlyFeeInfo" rel="popupProdInfo_monthlyFeeInfoDiv" style="display:none"><spring:message code="LAB.M08.LAB00195"/></li> <!-- 월정액 -->
		<li id="popupProdInfo_onetimeChargeInfo" rel="popupProdInfo_onetimeChargeInfoDiv" style="display:none"><spring:message code="LAB.M08.LAB00196"/></li> <!-- 일회성 -->
	</ul>
<!--  -->
	<div class="tab_box table_col_box h270">	<!-- 사용상품현황 -->
		<div id="popupProdInfo_basicProdInfoDiv" class="tab_content">
			<table class='writeview tdB'>
				<tbody>
					<tr class='col2'>
						<th><spring:message code="LAB.M07.LAB00307"/></th>	<!-- 상품그룹 -->
						<td><div id="popupProdInfo_basicProdInfo_prodGrp" class="inp_date np custInfoCls"></div></td>
						<th><spring:message code="LAB.M01.LAB00230"/></th><!-- 과금시작일 -->
						<td><div id="popupProdInfo_basicProdInfo_rateStrtDt" class="inp_date np custInfoCls"></div></td>
					</tr>
					<tr class='col2'>
						<th><spring:message code="LAB.M07.LAB00130"/>(<spring:message code="LAB.M07.LAB00156"/>)</th><!-- 상품명(상품코드) -->
						<td><div id="popupProdInfo_basicProdInfo_prodNm" class="inp_date np custInfoCls"></div>
						</td>
						<th><spring:message code="LAB.M07.LAB00287"/></th><!-- 시작일시 -->
						<td><div id="popupProdInfo_basicProdInfo_actDttm" class="inp_date np custInfoCls"></div></td>
					</tr>
					<tr class='col2'>
						<th><spring:message code="LAB.M01.LAB00037"/></th><!-- 계약상태 -->
						<td><div id="popupProdInfo_basicProdInfo_ctrtStat" class="inp_date np custInfoCls"></div></td>
						<th><spring:message code="LAB.M08.LAB00002"/></th><!-- 약정기간 -->
						<!--<td><div id="popupProdInfo_basicProdInfo_promPrd" class="inp_date np custInfoCls"></div></td>-->
						<td><div id="popupProdInfo_basicProdInfo_item09_nm" class="inp_date np custInfoCls"></div></td>
					</tr>
					<tr class='col2'>
						<th>모델</th>
						<td><div id="popupProdInfo_basicProdInfo_m_no_nm" class="inp_date np custInfoCls"></div></td>
						<th>Basic Cor 수</th>
						<td><div id="popupProdInfo_basicProdInfo_basic_core_cnt" class="inp_date np custInfoCls"></div></td>
					</tr>
					<tr class='col2'>
						<th>Add Core 수</th>
						<td><div id="popupProdInfo_basicProdInfo_add_core_cnt" class="inp_date np custInfoCls"></div></td>
						<th>고정비(%)</th>
						<td><div id="popupProdInfo_basicProdInfo_fix_rate" class="inp_date np custInfoCls"></div></td>
					</tr>
					<tr class='col2'>
						<th>사용비(%)</th>
						<td><div id="popupProdInfo_basicProdInfo_use_rate" class="inp_date np custInfoCls"></div></td>
						<th>차수</th>
						<td><div id="popupProdInfo_basicProdInfo_rt_id_nm" class="inp_date np custInfoCls"></div></td>
					</tr>
					<tr class='col2'>
						<th>지역</th>
						<td><div id="popupProdInfo_basicProdInfo_local_nm" class="inp_date np custInfoCls"></div></td>
						<th>운영체제</th>
						<td><div id="popupProdInfo_basicProdInfo_os_typ_nm" class="inp_date np custInfoCls"></div></td>
					</tr>

					<tr class='col2'>
						<th>Business User</th>
						<td><div id="popupProdInfo_basicProdInfo_item01" class="inp_date np custInfoCls"></div></td>
						<th>Team Users</th>
						<td><div id="popupProdInfo_basicProdInfo_item02" class="inp_date np custInfoCls"></div></td>
					</tr>
					<tr class='col2'>
						<th>Enterprise User</th>
						<td><div id="popupProdInfo_basicProdInfo_item03" class="inp_date np custInfoCls"></div></td>
						<th>DR Storage (TB)</th>
						<td><div id="popupProdInfo_basicProdInfo_item04" class="inp_date np custInfoCls"></div></td>
					</tr>
					<tr class='col2'>
						<th>Business Inactive User</th>
						<td><div id="popupProdInfo_basicProdInfo_item05" class="inp_date np custInfoCls"></div></td>
						<th>Team Inactive User</th>
						<td><div id="popupProdInfo_basicProdInfo_item06" class="inp_date np custInfoCls"></div></td>
					</tr>
					<tr class='col2'>
						<th>Enterprise Inactive User</th>
						<td><div id="popupProdInfo_basicProdInfo_item08" class="inp_date np custInfoCls"></div></td>
						<th>환율</th>
						<td><div id="popupProdInfo_basicProdInfo_item07_nm" class="inp_date np custInfoCls"></div></td>
					</tr>
					<tr class='col2'>
						<th>설치비</th>
						<td><div id="popupProdInfo_basicProdInfo_item10" class="inp_date np custInfoCls"></div></td>
						<th>Package</th>
						<td><div id="popupProdInfo_basicProdInfo_package_cd_nm" class="inp_date np custInfoCls"></div></td>
					</tr>
				</tbody>
			</table>
		</div>
		
<!-- //popupProdInfo_basicProdInfoDiv -->
		<div id="popupProdInfo_deviceProdInfoDiv" class="tab_content"><!-- 기본상품변경 GRID -->
			<table id="popupDeviceProdListGrid" class="w100p"></table>
		</div>
<!-- //popupProdInfo_deviceProdInfoDiv -->
		<div id="popupProdInfo_addonProdInfoDiv" class="tab_content"><!-- 분납개월수GRID -->
			<table id="popupAddonProdListGrid" class="w100p"></table>
		</div>
<!-- //popupProdInfo_addonProdInfoDiv -->
		<div id="popupProdInfo_instAddrInfoDiv" class="tab_content">
			<table class='writeview column_2 row_1'>
			<tbody>
				<tr class='col2_left'>
					<th>
						<!-- 우편번호 -->
						<spring:message code="LAB.M08.LAB00087"/>
					</th>
					<td>
						<div id="popupProdInfo_instAddrInfo_zipCd" class="inp_date np custInfoCls"></div>
					</td>
					<th>
						 <!-- 주소 -->
						<spring:message code="LAB.M09.LAB00190"/>
					</th>
					<td>
						<div id="popupProdInfo_instAddrInfo_addr" class="inp_date np custInfoCls"></div>
					</td>
				</tr>
				<!-- <tr class='col2_left'>
					<th>
						<spring:message code="LAB.M03.LAB00087"/>
					</th>
					<td>
						<div id="popupProdInfo_instAddrInfo_city" class="inp_date np custInfoCls"></div>
					</td>
					<th>
						<spring:message code="LAB.M09.LAB00232"/>
					</th>
					<td>
						<div id="popupProdInfo_instAddrInfo_state" class="inp_date np custInfoCls"></div>
					</td>
				</tr> -->
			</tbody>
		</table>
		</div>
<!-- //popupProdInfo_monthlyFeeInfoDiv -->
		<div id="popupProdInfo_monthlyFeeInfoDiv" class="tab_content"><!-- 월정액금액GRID -->
			<table id="popupMonthlyFeeGrid" class="w100p"></table>
		</div>
<!-- //popupProdInfo_onetimeChargeInfoDiv -->		
		<div id="popupProdInfo_onetimeChargeInfoDiv" class="tab_content"><!-- 일회성금액GRID -->
			<table id="popupOnetimeChargeGrid" class="w100p"></table>
		</div>
	</div>
	<!--//col_box  -->
	<div class='main_btn_box'>
		<div class='fl'>
			<h4 class='sub_title'><spring:message code="LAB.M06.LAB00114"/></h4>
		</div>
	</div>
	<ul id="popupHistInfoTabs" class="tabs">
		<li id="popupProdInfo_basicProdInfoHist" class="active" rel="popupHistInfo_baseProdInfoDiv"><spring:message code="LAB.M01.LAB00233"/></li>
		<li id="popupProdInfo_deviceProdInfoHist" rel="popupHistInfo_deviceProdInfoDiv"><spring:message code="LAB.M09.LAB00219"/></li>
		<li id="popupProdInfo_addonProdInfoHist" rel="popupHistInfo_addonInfoDiv">부가상품이력</li>
		<li id="popupProdInfo_suspentionInfoHist" rel="popupHistInfo_suspentionInfoDiv"><spring:message code="LAB.M08.LAB00182"/></li>
		<li id="popupProdInfo_instAddrInfoHist" rel="popupHistInfo_instlAddrInfoDiv"><spring:message code="LAB.M07.LAB00302"/></li>
		<!-- <li id="popupProdInfo_procRateInfoHist" rel="popupHistInfo_procRateInfoDiv"><spring:message code="LAB.M01.LAB00234"/></li>
		<li id="popupProdInfo_payerInfoHist" rel="popupHistInfo_payerInfoDiv"><spring:message code="LAB.M02.LAB00039"/></li> -->
	</ul>
	<div class="tab_box h230">
<!-- 기본상품이력 -->
		<div id="popupHistInfo_baseProdInfoDiv" class="tab_content" >
			<div id="popupBaseProdHistGridDiv">
				<table id="popupBaseProdHistGrid" class="w100p"></table>
			</div>
		</div>
<!-- 장비상품이력 -->
		<div id="popupHistInfo_deviceProdInfoDiv" class="tab_content" >
			<div class='btn_box'>
				<div class='tabSelect'>
					<span class='selectText'>- <spring:message code="LAB.M09.LAB00220"/></span>
					<select id="popupHistInfo_deviceProdInfo_prodSel" class='w250'>
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					</select>
				</div>
			</div>
			<div id="popupHistInfo_deviceProdInfo_deviceProdListDiv">
				<table id="popupDeviceProdHistGrid" class="w100p"></table>
			</div>
		</div>
<!-- 부가서비스이력 -->
		<div id="popupHistInfo_addonInfoDiv" class="tab_content" >
			<div class='btn_box'>
				<div class='tabSelect'>
					<span class='selectText'>- <spring:message code="LAB.M06.LAB00116"/></span>
					<select id="popupHistInfo_addonInfo_prodSel" class='w250'>
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					</select>
				</div>
			</div>
			<div id="popupHistInfo_addonInfo_addonListDiv">
				<table id="popupAddonHistGrid" class="w100p"></table>
			</div>
		</div>
<!-- 일시정지이력 -->
		<div id="popupHistInfo_suspentionInfoDiv" class="tab_content" >
			<div id="popupHistInfo_suspentionInfo_suspentionListDiv">
				<table id="popupSuspentionHistGrid" class="w100p"></table>
			</div>
		</div>
<!-- 설치주소이력 -->
		<div id="popupHistInfo_instlAddrInfoDiv" class="tab_content" >
			<div id="popupHistInfo_instlAddrInfo_instlAddrListDiv">
				<table id="popupInstlAddrHistGrid" class="w100p"></table>
			</div>
		</div>
<!-- 공정률이력 -->
		<!-- <div id="popupHistInfo_procRateInfoDiv" class="tab_content" >
			<div id="popupHistInfo_procRateHistInfo_procRateListDiv">
				<table id="popupProcRateHistGrid" class="w100p"></table>
			</div>
		</div> -->
<!-- 납부자이력 -->
		<!-- <div id="popupHistInfo_payerInfoDiv" class="tab_content" >
			<div id="popupHistInfo_payerHistInfo_payerListDiv">
				<table id="popupPayerHistGrid" class="w100p"></table>
			</div>
		</div> -->
		
		</div>
		<div class='btn_box'>
			<a class="grey-btn close" href="#">
				<span class="cancel_icon"><spring:message code="LAB.M03.LAB00027"/></span>
			</a>
		</div><!--//btn_box-->
	</div>
	
		
