<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<style type="text/css">
table.ui-datepicker-calendar { display:none; }
</style>
<script type="text/javascript">
$(document).ready(function() {
    setBasicInfo(); //기본정보 셋팅 
    
    var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
	//달력처리
	if($(".month-picker").length > 0){
		if(lng == 'ko'){
			format = 'yy-mm';
		}else if (lng == 'en'){
			format = 'mm/yy';
		}
		$('.month-picker').datepicker({
	        changeMonth: true,
	        changeYear: true,
	        showButtonPanel: true,
	        dateFormat: format,
	        minDate: 0,
	        onClose: function(dateText, inst) {
	            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
	            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
	            $(this).datepicker('setDate', new Date(year, month, 1));
	        }
		 	,
	        beforeShow : function (dateText, inst) {
	            var selectDate = $(this).val().split("-");
	            var year = Number(selectDate[0]);
	            var month = Number(selectDate[1]) - 1;
	            $(this).datepicker( "option", "defaultDate", new Date(year, month, 1) );
	            $(this).datepicker('setDate', new Date(year, month, 1));
	            
	        }
	    })
	}
	$(".inp_date .btn_cal").click(function(e){e.preventDefault();$(this).prev().focus();});
	
    var param = checkInput();

    //그리드 처리
    $("#beforeAdjRegiditTable").jqGrid({
        url : '/charge/billing/billingAdjust/billingBeforeAdjust/getAdjTgtList.json?',
        datatype : 'json',
        mtype: 'POST',
        postData : param,
        colModel: [
            { label: 'soId' , name: 'soId', hidden:true, width:0},
            { label: 'gubun', name: 'gubun', hidden:true, width:0},
            { label: 'adjNo', name: 'adjNo', hidden:true, width:0},
            { label: 'useYymm', name: 'useYymm', hidden:true, width:0},
            { label: 'prodCmpsId', name: 'prodCmpsId', hidden:true, width:0},
            { label: 'svcCmpsId', name: 'svcCmpsId', hidden:true, width:0},
            { label: 'chrgItmCd', name: 'chrgItmCd', hidden:true, width:0},
            { label: 'custId', name: 'custId', hidden:true, width:0},
            { label: 'attrVal', name: 'attrVal', hidden:true, width:0},
            { label: 'adjPrvBillAmt', name: 'adjPrvBillAmt', hidden:true, width:0},
            { label: 'adjAmt', name: 'adjAmt', hidden:true, width:0},
            { label: 'adjAftBillAmt', name: 'adjAftBillAmt', hidden:true, width:0},
            { label: 'adjPt', name: 'adjPt', hidden:true, width:0},
            { label: 'prodCd', name: 'prodCd', hidden:true, width:0},
            { label: 'svcCd', name: 'svcCd', hidden:true, width:0},
            { label: 'exrate', name: 'exrate', hidden:true, width:0},
            { label: 'crncyCd', name: 'crncyCd', hidden:true, width:0},
            { label: 'exrateAplyDt', name: 'exrateAplyDt', hidden:true, width:0},
            { label: 'attrVal', name: 'attrVal', hidden:true, width:0},
            { label: 'ctrtStat', name: 'ctrtStat', hidden:true, width:0},
            { label: 'mnsFlag', name: 'mnsFlag', hidden:true, width:0},
            { label: 'orgAdjApplAmt', name: 'orgAdjApplAmt', hidden:true, width:0},
            { label: 'totAdjApplAmt', name: 'totAdjApplAmt', hidden:true, width:0},
            //화면에 보이는 컬럼
            { label: '<spring:message code="LAB.M01.LAB00050" />', name: 'custNm', width : 120, align:"left"},
            { label: '<spring:message code="LAB.M01.LAB00032" />', name: 'ctrtId', width : 80, align:"center"},
            { label: '<spring:message code="LAB.M07.LAB00186" />', name: 'svcTelNo', width : 100, align:"center"},
            { label: '<spring:message code="LAB.M07.LAB00130" />', name: 'prodNm', width : 150, align:"left"},
            { label: '서비스명', name: 'svcNm', width : 120, align:"left"},
            { label: '요금항목', name: 'chrgItmNm', width : 120, align:"left"},
            { label: '계약상태', name: 'ctrtStatNm', width : 120, align:"center"},
          	{ label: '과금개시일', name: 'rateStrtDt',  width : 120, align:"center", formatter:stringToDateformatYYYYMMDD},
          	{ label: '과금종료일', name: 'rateEndDt', width : 180, align:"center", formatter:stringToDateformatYYYYMMDD},
          	{ label: '조정신청금액', name: 'adjApplAmt', formatter:"integer", width : 80, align:"right", editable:true
            	,editoptions:{//숫자와 '-'만 입력받을수 있게 처리
                	dataInit: function(element) {
               	 		$(element).keyup(function(event){
	                     	var keyValue = event.key; //jquery로 눌려진 값을 가져온다.
	                        var str = element.value; // 현재 input태그에 입력된 문자열을 가져온다
	                          
	                        var regex =  /^[-]?\d*(\.?\d*)$/g;
	                        var totalStr = str.replace(/^[-]?\d*(\.?\d*)$/g, ""); // concat
	                        
	                        if(event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 46){
	                            return true;
	                        }else{
		                       	if(regex.test(totalStr)){
		                                 return;
	                             }else{
	                                 alert('<spring:message code="MSG.M07.MSG00134"/>');
	                                 element.value = '';
	                             }
	                        }
                     	})
              		}
           		}
    		}
       	],
        viewrecords: true,
        shrinkToFit:false,
        height: 200,
        sortable : false,
        cellEdit : true,
        cellsubmit:'clientArray',
        jsonReader: {
            repeatitems : true,
            root : "adjTgtList"
        },
        rowNum: -1,       
        onCellSelect : function(rowid, index, contents, event){
        },
        loadComplete : function (data) {
            $("#beforeAdjRegiditTable").setGridWidth($('#beforeAdjRegiditGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
        sortable: { update: function(permutation) {
            $("#beforeAdjRegiditTable").setGridWidth($('#beforeAdjRegiditGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            }
        },
        afterEditCell : function(rowid, cellname, value, iRow, iCol){
            
        },
        afterEditCell : function(rowid, cellname, value, iRow, iCol){
            $("#"+iRow+"_"+cellname).bind('blur',(function(){
                $("#beforeAdjRegiditTable").editCell(rowid, 30,false);
           	}));     
        }
    });

    //그리드 화면 재조정
    $(window).resize(function() {
        $("#beforeAdjRegiditTable").setGridWidth($('#beforeAdjRegiditGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    });
    
    //초기화 (재조회)
    $('#popInitBtn').on('click',function (e) {
        
        $("#beforeAdjRegiditTable").trigger('reloadGrid');
    });
    
    $('#popUpdateBtn').on('click',function (e) {
        //유효성 검사
        chkValidation();
    });
    
    $('#btnCancel').on('click',function (e) {
        chkCancel();
    });
  
});

//요금조정 신청전 청구상태,신청상태 체크
function chkValidation(){
	
	if('${pymRcpt[0].pymRcptAmt}' > 0){
        alert('<spring:message code="MSG.M07.MSG00129" />'); return; 
    }
	
	if($('#popAdjRsn').val() == 'SEL'){
        alert('조정사유를 선택하세요'); return;
    }
    
    if($('#popAdjReq').val() == '' || $('#popAdjReq').val() == null){
        alert('조정신청내역를 입력하세요'); 
        $('#popAdjReq').focus();
        return;
    }
    
  	//조정 데이터
    var adjustVO = new Object();
    var adj = new Object();
    
    adj.adjNo     = '${billBeforeAdj.adjNo}';
    if('${billBeforeAdj.billYymm}' == ''){
    	adj.billYymm  = '0';
    	adj.billCycl  = '0';
    	adj.billDt  = '0';
    	adj.billSeqNo  = '0';
    }else{
    	adj.billYymm  = '${billBeforeAdj.billYymm}';
   	 	adj.billCycl  = '${billBeforeAdj.billCycl}';
   	    adj.billDt    = '${billBeforeAdj.billDt}';
   	    adj.billSeqNo = '${billBeforeAdj.billSeqNo}';
    }   
    adj.pymAcntId = '${billBeforeAdj.pymAcntId}';
    adj.pymAcntNm = '${billBeforeAdj.pymAcntNm}';
    adj.soId      = '${billBeforeAdj.soId}';
    adj.hopeAplyYymm = $("#popApplyMonth").val().replace('-','');  
    adj.adjResnCd = $('#popAdjRsn').val();
    adj.adjApplConts = $('#popAdjReq').val(); 
    adj.billReIssYn = 'N';		//청구서재발행여부
    adj.dcsnProcStat = '03';	//결재처리상태 03.결재완료  
    adj.adjPt     = '1';		//청구후 요금조정,청구전 요금조정 (1,2)
    adj.adjBillDt = '0';
    adj.apprReqrId = '0';
    adj.apprReqDttm = '0';
    adj.apprrId = '0';
    adj.apprDttm = '0';
    adj.adjSbtDdCnt = 0;
    adj.adjPrvBillAmt = 0;
    adj.adjAmt = 0;
    adj.saleAplyDt = '0';
    adj.inptMenuId = '0';
    adj.saleAplyYn = '0';
    adj.applDttm = '0';
    adj.appntTelNo = '0';
      
    adjustVO.adj = adj;
    
  	//조정상세 데이터 
    var reqAdjDtlList = []; 
    var reqAdjIds = $('#beforeAdjRegiditTable').getDataIDs(); //table row수
    var reqIdx = 0;   
    var isNotChanged = 0;
    var isChanged = 0;
    
 	var isNotChanged = false;
 	
    //요금조정금액이 전부0원인 경우  
    $.each(reqAdjIds, function(index, value){
        var confValue = $('#beforeAdjRegiditTable').getRowData(value);
              
        if(confValue.adjApplAmt ==  0){
        	isNotChanged++;
        	
        }       
        if(isNotChanged == reqAdjIds.length){
       	  alert('조정신청금액을 입력하세요.');
     	  isNotChanged = true;
       	  return false; 
       	  
        }        
    });       

    if(isNotChanged){
    	return;
    }
    
  	//기존값과 비교 동일할 경우 (조정 금액 비교, 조정사유 변경여부, 신청사유내역) 	
    $.each(reqAdjIds, function(index, value){
        var confValue = $('#beforeAdjRegiditTable').getRowData(value); 
       	                          
        if(confValue.adjApplAmt != confValue.orgAdjApplAmt){
        	isChanged++;
        	
        }  

     });
	
    if($('#popAdjRsn').val() != '${billBeforeAdj.adjResnCd}'){
    	isChanged++;
    }
  
    if($('#popAdjReq').val() != '${billBeforeAdj.adjApplConts}'){
    	isChanged++;
    }
    
  	//isChanged = 변경된 값이 없다면 
    if(isChanged == 0){ 
        alert('<spring:message code="MSG.M06.MSG00026"/>'); //변경된 정보가 없습니다.
        return;
    }
     
    var applGubn = 0; //최초신청건 여부
    var applyYymm = $("#popApplyMonth").val().split('-'); 
    
  	//신청년월 청구진행중 확인 (사업구분,희망청구년월)
  	var applBillYymmCount 
  		= getApplBillYymmCount('${billBeforeAdj.soId}',$("#popApplyMonth").val().replace('-',''));  	
  	console.log("찬희3:"+applBillYymmCount);
  	if(applBillYymmCount > 0){
  		alert("현재 "+applyYymm[0]+"년 "+applyYymm[1]+"월은 청구작업 진행중입니다.");
  		return;
  	}
  	
    //조정상세 데이터 담기
    console.log("row수"+reqAdjIds);
    $.each(reqAdjIds, function(index, value){   	
        var confValue = $('#beforeAdjRegiditTable').getRowData(value); 
        
        var adjInfo = new Object();      
        adjInfo.soId = '${billBeforeAdj.soId}';
        adjInfo.pymAcntId = '${billBeforeAdj.pymAcntId}';
        adjInfo.adjNo = '${billBeforeAdj.adjNo}';        
        adjInfo.hopeAplyYymm =$("#popApplyMonth").val().replace('-','');   
        adjInfo.useYymm = confValue.useYymm;	
        adjInfo.prodCmpsId =confValue.prodCmpsId;
        adjInfo.svcCmpsId = confValue.svcCmpsId;
        adjInfo.chrgItmCd = confValue.chrgItmCd;
                
   	 	$.ajax({//신청건 여부로 조정상세데이터 INSERT UPDATE 구분
   	        url:'/charge/billing/billingAdjust/billingBeforeAdjust/getApplBeforeCount.json',
   	        type:'post',
   	        data : adjInfo,
   	     	async: false,
   	        success: function(data){	   	       
           		confValue.useYymm = dateFormatToStringYYYYMM(confValue.useYymm);             		
           		console.log("data.resultCount"+data.resultCount+"//"+reqIdx);
           		if(data.resultCount == 0){ 
       	 			confValue.gubun = "I";
       	 			
   	 				if(confValue.adjApplAmt != confValue.orgAdjApplAmt){
      	 					reqAdjDtlList[reqIdx++] = confValue;     
      	 					
   	 				}
       	 		}else{
       	 			applGubn = applGubn+1; 
       	 			confValue.gubun = "U";
       	 			
       	 			if(confValue.adjApplAmt != confValue.orgAdjApplAmt){
      	 					reqAdjDtlList[reqIdx++] = confValue;
      	 					
   	 				}
       	 		}	
   	        },
   	        beforeSend: function(data){
   	        },
   	        error : function(err){
   	        	alert('<spring:message code="MSG.M10.MSG00005"/>');
   	        	return
   	        }
     	});
       
    });
	
    //조정데이터 INSERT UPDATE 구분
    if(applGubn != 0){
    	adjustVO.gubun = 'U';
    }else{
    	adjustVO.gubun = 'I'; 
    }
   
    adjustVO.adjDtl = reqAdjDtlList;
    console.log("adjustVO.gubun"+adjustVO.gubun);
    
	//최조신청일 경우 기존 신청건 존재여부 확인 
    if(adjustVO.gubun == 'I'){
    	var applHopeYymmCount = getApplHopeYymmCount('${billBeforeAdj.pymAcntId}',$("#popApplyMonth").val().replace('-',''));
   console.log(applHopeYymmCount+"새로운계약 존재여부");
    	if(applHopeYymmCount > 0){
    		alert("현재 "+applyYymm[0]+"년 "+applyYymm[1]+"월은 신청건이 존재합니다.");
    		return;
    	}
    }
       
    if(adjustVO.gubun != ""){
    	reqAppl(adjustVO);
    }  
}
  
function reqAppl(adjustVO){   	
	console.log("/*****APPLINFO : "+JSON.stringify(adjustVO)+"*******/");
	
	$.ajax({
        url:'/charge/billing/billingAdjust/billingBeforeAdjust/reqAppl.json',
        type:'post',
        data : JSON.stringify(adjustVO),
        dataType: 'json',
        contentType : "application/json; charset=UTF-8",
     	async: false,
        success: function(data){	
			if(data.result = 0){
				alert('<spring:message code="MSG.M10.MSG00005"/>');
			}    	
			
			$("#beforeAdjTable").trigger('reloadGrid');
        	$("#btnClose").trigger('click');
        },
        beforeSend: function(data){
        },
        error : function(err){
        	alert('<spring:message code="MSG.M10.MSG00005"/>');
        }
	});   
}

//신청 취소
function chkCancel(){
	if('${billBeforeAdj.adjNo}' == null && '${billBeforeAdj.adjNo}' ==""){
        alert('<spring:message code="MSG.M09.MSG00066" />');
        return;
        
    }
	
	if('${billBeforeAdj.dcsnProcStat}' == '05'){
		alert('<spring:message code="MSG.M09.MSG00066" />');
		return;
		
	}
	
	var cancelAdjNo = new Object();	
	cancelAdjNo.adjNo = '${billBeforeAdj.adjNo}';
	var check = confirm('<spring:message code="MSG.M10.MSG00030" />');
	
	if(check){	
		$.ajax({
		     url:'/charge/billing/billingAdjust/billingBeforeAdjust/cancelAdjList.json',
		     type:'post',
	   	     data : cancelAdjNo,
	   	     async: false,
	   	     success: function(data){
		        alert('<spring:message code="MSG.M07.MSG00084"/>');
		           
				$("#beforeAdjTable").trigger("reloadGrid");
		        $("#btnClose").trigger('click');
		     },
		     beforeSend: function(data){
		     },
		     error : function(err){
		         alert('<spring:message code="MSG.M10.MSG00005"/>');
		     }
		   });
	}
}

function setBasicInfo(){//신청된건이면 disabled
    if(('${billBeforeAdj.billYymm}').length > 0 ){	
    	$('#popApplyMonth').attr('disabled',true);
    }else{
    	$('#popApplyMonth').attr('disabled',false);
    }
    
    $('#popPymAcnt').val('${billBeforeAdj.pymAcntNm}');
    $('#popApplDttm').val('${billBeforeAdj.applDttm}');	
    $('#popApplyMonth').val(stringToDateformatYYYYMM('${billBeforeAdj.billYymm}'));
    $('#popdcsnProcStat').val('${billBeforeAdj.dcsnProcStatNm}');
    $('#popAdjReq').val('${billBeforeAdj.adjApplConts}');
    
    //수납내역체크
    if('${pymRcpt[0].pymRcptAmt}' > 0){
    	$('#popPay').val('Y');
    	
   	//btnDisable("popUpdateBtn");
    }else{
    	$('#popPay').val('N');
    }
    
    if('${billBeforeAdj.adjNo}' != null && '${billBeforeAdj.adjNo}' != ""){
    	//alert(1);
    	$('#popAdjRsn').val('${billBeforeAdj.adjResnCd}');
    	$('#popAdjRsn').selectmenu();
        $('#popAdjRsn').selectmenu("refresh");
    }
    
    if('${billBeforeAdj.dcsnProcStat}' == '05' || '${billBeforeAdj.adjNo}' == null || '${billBeforeAdj.adjNo}' == ""){
    	btnDisable("btnCancel");
    }
}

function checkInput(){
    var param = new Object();
    
    param.adjNo = '${billBeforeAdj.adjNo}';
    param.adjPt = '${billBeforeAdj.adjPt}';;
    param.soId = '${billBeforeAdj.soId}';
    param.pymAcntId = '${billBeforeAdj.pymAcntId}';
    param.billSeqNo = '${billBeforeAdj.billSeqNo}';
    param.billYymm = '${billBeforeAdj.billYymm}';
    param.adjResnCd = "${billBeforeAdj.adjResnCd}";
    
    $("#popAdjRsn").val("${billBeforeAdj.adjResnCd}")
    
    return param;
}

function getApplBillYymmCount(soId,hopeAplyYymm){
	
	 var param = new Object();
	 param.soId = soId;
	 param.hopeAplyYymm = hopeAplyYymm;
	 
	 var ApplBillYymmCount = 0;
	 
	 console.log("찬희"+JSON.stringify(param));
	$.ajax({
        url:'/charge/billing/billingAdjust/billingBeforeAdjust/getApplYymmCount.json',
        data: param,
	    type:'post',
     	async: false,
        success: function(data){	
        	console.log("청구진행중 : "+data.result);			
        	ApplBillYymmCount = data.result;
        	
        },
        beforeSend: function(data){
        },
        error : function(err){
        	alert('<spring:message code="MSG.M10.MSG00005"/>');
        }
	}); 
	
	return ApplBillYymmCount;
}

function getApplHopeYymmCount(pymAcntId,hopeAplyYymm){
	console.log(pymAcntId+"/"+hopeAplyYymm);
	 var param = new Object();
	 param.pymAcntId = pymAcntId;
	 param.hopeAplyYymm = hopeAplyYymm;
	 
	 var ApplHopeYymmCount = 0;
	$.ajax({
        url:'/charge/billing/billingAdjust/billingBeforeAdjust/getApplHopeYymmCount.json',
        data: param,
	    type:'post',
     	async: false,
        success: function(data){	
        console.log("찬희2"+data.result);
       		ApplHopeYymmCount = data.result;    	
        },
        beforeSend: function(data){
        },
        error : function(err){
        	alert('<spring:message code="MSG.M10.MSG00005"/>');
        }
	});
	
	return ApplHopeYymmCount;
	
}
</script>
<!-- 검색부 -->
<div style="width:1510px;" >

    <!-- title -->
    <div class="layer_top">
        <div class="title"><spring:message code="LAB.M07.LAB00346"/></div>
         <a href="#" id="btnClose"  class="close"></a>
    </div>
	   
	<div class="layer_box">
	    <!--// title -->
	    <table class="writeview">
	        <colgroup>
	            <col style="width:15%;">
	            <col style="width:35%;">
	            <col style="width:15%;">
	            <col style="width:35%;">
	        </colgroup>
	        <tbody>
	            <tr>
	                <th><spring:message code="LAB.M07.LAB00347"/></th><!-- 신청자 -->
	                <td><input id="popPymAcnt" name="pymAcnt" type="text" class="w245" disabled /></td>
	                
	                <th><spring:message code="LAB.M07.LAB00348"/></th><!-- 신청일시 -->
	                <td><input id="popApplDttm" name="applDttm" type="text" class="w245" disabled /></td>
	            </tr>
	            <tr>
	                <th>희망적용월</th><!-- 적용월 -->
	                <td>
	                	<div class="date_box">
							<div class="inp_date w130">
								<input type="text"  class="month-picker" id="popApplyMonth" name="popApplyMonth" readonly="readonly" />
								<a href="#" class="btn_cal"></a>
							</div>
						</div>
					</td>                
	                <th><spring:message code="LAB.M09.LAB00136"/><span class="dot">*</span></th><!-- 조정사유코드 -->
	                <td>
	                    <select id="popAdjRsn" class="w100p">
	                        <option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
	                        <c:forEach items="${adjRsnCd}" var="adjRsnCd" varStatus="status">
	                            <option value="${adjRsnCd.commonCd}">${adjRsnCd.commonCdNm}</option>
	                        </c:forEach>
	                    </select>
	                </td>
	            </tr>
	            <tr>               
	                <%-- <th><spring:message code="LAB.M07.LAB00309"/><span class="dot">*</span></th><!-- 승인자 -->
	                <td>
	                    <select id="popAdjApprover" class="w100p">
	                        <option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
	                        <c:forEach items="${adjApprover}" var="adjApprover" varStatus="status">
	                            <option value="${adjApprover.commonCd}">${adjApprover.commonCdNm}</option>
	                        </c:forEach>
	                    </select>
	                </td> --%>
	                <th>신청사유내역<span class="dot">*</span></th><!-- 조정사유 -->
	                <td colspan="3"><input id="popAdjReq" name="adjRsn" type="text" class="W850" /></td>
	            </tr>
	        </tbody>
	    </table> 
	    
	    
	    
	    <div class="main_btn_box">
	        <div class="fl">
	            <h4 class="sub_title"><spring:message code="LAB.M09.LAB00245"/></h4>
	        </div>
	    </div>
	    
	    <div id='beforeAdjRegiditGrid'>
	        <table id="beforeAdjRegiditTable" class="w100p"></table>
	        <div id="beforeAdjRegiditPager"></div>
	    </div>
    
    </div>
    
<!-- <div style="width:700px;" > -->
<!--         title -->
<!--          <div class="layer_top"> -->
<%--              <div class="title"><spring:message code="LAB.M01.LAB00002"/></div> --%>
<!--              <a href="#" class="close"></a> -->
<!--         </div> -->
<!--         // title -->
                                                 
<!--         center -->
<!--         <div class="layer_box"> -->
<!--                 <table id="virAcntGrid" class="w100p"></table> -->
<!--                 <div id="virAcntGridPager"></div> -->
<!--          </div> -->
<!--         footer -->
<!--          <div class="btn_box"> -->
<%--                <ntels:auth auth="${menuAuthU}"> --%>
<%--                <a id='virChngBtn' class="grey-btn" href="#" ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252" /></span></a> --%>
<%--                </ntels:auth> --%>
<%--                <a class="grey-btn close" href="#" ><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a> --%>
<!--         </div> -->
<!-- </div> -->
    <div class="btn_box">
        <div class="fr">
            <span id="commonBtn">
                <a href="#" class="grey-btn" id="btnCancel" title='<spring:message code="LAB.M07.LAB00349"/>'><spring:message code="LAB.M07.LAB00349"/></a>
                <ntels:auth auth="${menuAuthU}">
                <a id="popUpdateBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
                </ntels:auth>
            </span>
        </div>
    </div>
 

</div>


<input id="clsBillYymm" type='text' value="${billClsInfo[0].billYymm}"  hidden />
<input id="popAdjNo" type='text' value="${billBefAdj.adjNo}"  hidden />