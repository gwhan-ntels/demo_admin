<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ page import="org.apache.commons.lang.StringEscapeUtils"%>

<%
	String useLanguage = StringEscapeUtils.escapeHtml((String)request.getAttribute("useLanguage"));
	String lastPagePath = (String)session.getAttribute("lastPagePath");
	String mainPath = (String)session.getAttribute("mainPath");
%>

	<script type="text/javascript">
		var useLanguage = "<%=useLanguage%>";
		var lastPagePath = "<%=lastPagePath%>";
		var mainPath = "<%=mainPath%>";
		var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
		
		function logout() {
			document.frmMenuHandle.action="<%=request.getContextPath()%>/system/login/logoutAction";
			document.frmMenuHandle.method="post";
			document.frmMenuHandle.submit();
		}
		
		function goMainPage(url){
			var mainUrl = url + '?noSelectMenu=Y';
			goMenuPage(mainUrl);
		}
		
		$(function() {      
	        $('#useLanguage').selectmenu({
	            change: function() {
	            	if(lastPagePath == ''){
	            		goMainPage(mainPath);	
	            		document.frmLanguage.appendChild(AddData('isChangeMenu', 'Y'));
		            	document.frmLanguage.method="post";
	            		document.frmLanguage.action="<%=request.getContextPath()%>" + mainPath;
	            		document.frmLanguage.submit();
	            	}else{
	            		document.frmLanguage.appendChild(AddData('isChangeMenu', 'Y'));
		            	document.frmLanguage.method="post";
	            		document.frmLanguage.action="<%=request.getContextPath()%>" + lastPagePath;
	            		document.frmLanguage.submit();
	            	}
	            	
	    			
	            }
	        });
		});
		
        $(document).ready(function(){
     		 $('#useLanguage').val(useLanguage);
     		 $('#useLanguage').selectmenu("refresh");
     		 
             if($('#leftMenuDiv').length > 0){
                 $("#hideLeftBtn").show();
             }


     	});
		
    function hideLeftMenu(){
        $("#home_left").resizeTriggering().on("resize", function(){
            if (typeof commonResizeGrid == 'function') {
                commonResizeGrid();
            }
        });
        $("#home_left").hide(500, function(){
        	$(".left").off("resize");
        	if (typeof commonResizeGrid == 'function') {
                commonResizeGrid();
            }
        	$("#hideLeftBtn").hide();
            $("#showLeftBtn").show();
        	
        });
   	}
    

    function showLeftMenu(){
    	$("#home_left").resizeTriggering().on("resize", function(){
            if (typeof commonResizeGrid == 'function') {
                commonResizeGrid();
            }
        });
   		$("#home_left").show(500, function(){
   		     $(".left").off("resize");
   		     if (typeof commonResizeGrid == 'function') {
                commonResizeGrid();
            }
   		  	$("#hideLeftBtn").show();
          	$("#showLeftBtn").hide();
        });


   	}
    
    function changePw(){
    	/*  var url="/system/common/common/changePwMng/changePwMngPopup.ajax";
	    	var pwInfo = new Object();
	    	pwInfo.userId = "${session_user.userName}";
	    	$.ajax({
	    		type : "post",
	    		url : url,
	    		data : pwInfo,
	    		success : function(data) {
	    			var html = data;
					$("#popup_dialog").html(html);
	    		},		
	    		complete : function(){
	    			wrapWindowByMask(); // 팝업 오픈
	    		}
	    	}); */
	    	
    	var url = "/system/common/common/changePwMng/changePwMngPopup.ajax";
		 url = url + "?userId="+"${session_user.userName}";           //단말일련번호
		 
		 var width = 500;
		 var height = 230;
		 var LeftPosition=(screen.width-width)/2;
		 var TopPosition=(screen.height-height)/2;

		window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no, resizeable=no,fullscreen=no");
    }
        
	</script>
<fmt:setLocale value="${sessionLanguage}_${sessionCountry}" scope="request" />

<!-- 국제화 날짜 포멧 관련 변수 선언 -->
<c:if test="${sessionLanguage == 'ko'}">
	<c:set var="dateToStrFormat1" value="yyyy-MM-dd HH:mm:ss" scope="request"/>
	<c:set var="dateToStrFormat2" value="yyyy-MM-dd HH:mm" scope="request"/>
	<c:set var="dateToStrFormat3" value="yyyy-MM-dd HH" scope="request"/>
	<c:set var="dateToStrFormat4" value="yyyy-MM-dd" scope="request"/>
	<c:set var="dateToStrFormat5" value="yyyy-MM" scope="request"/>
	<c:set var="dateToStrFormat6" value="yyyy" scope="request"/>
</c:if>


<c:if test="${sessionLanguage == 'en'}">
	<c:set var="dateToStrFormat1" value="MM/dd/yyyy HH:mm:ss" scope="request"/>
	<c:set var="dateToStrFormat2" value="MM/dd/yyyy HH:mm" scope="request"/>
	<c:set var="dateToStrFormat3" value="MM/dd/yyyy HH" scope="request"/>
	<c:set var="dateToStrFormat4" value="MM/dd/yyyy" scope="request"/>
	<c:set var="dateToStrFormat5" value="MM/yyyy" scope="request"/>
	<c:set var="dateToStrFormat6" value="yyyy" scope="request"/>
</c:if>

<c:set var="dateFormat1" value="yyyyMMddHHmmss" scope="request" />
<c:set var="dateFormat2" value="yyyyMMddHHmm" scope="request"/>
<c:set var="dateFormat3" value="yyyyMMddHH" scope="request"/>
<c:set var="dateFormat4" value="yyyyMMdd" scope="request"/>
<c:set var="dateFormat5" value="yyyyMM" scope="request"/>
<c:set var="dateFormat6" value="yyyy" scope="request"/>


<!--s : top-->
<form name="fmHeader" id="fmHeader" method="post">
	<fmt:parseDate value="${session_user.lastLoginDate}" var="last_login_date" pattern="yyyyMMdd"/>
	<fmt:parseDate value="${session_user.lastLoginTime}" var="last_login_time" pattern="HHmmss"/>
	<!-- header -->
            <div id="header_wrapper">
                        <div id="header">
<!--                         	<h1><img src="/images/common/VADS_logo_header.png" onclick="javascript:goMainPage('${session_user.mainView}');" style="cursor:pointer;" alt='logo'/></h1> -->
<!--          <h1><img src="/images/common/${session_user.soId}_logo_header.png" onclick="javascript:goMainPage('${session_user.mainView}');" style="cursor:pointer;" alt='logo'/></h1>-->
                             	<!-- lobal -->  
                                   <div id="globalmenu">
                                            <ul>
                                               <li class="user">
                                               		<a href="javascript:changePw();">${session_user.userName}</a> (<c:if test="${not empty last_login_date }"><fmt:formatDate value="${last_login_date}" pattern='${dateToStrFormat4}'/> <fmt:formatDate value="${last_login_time}" pattern="HH:mm:ss"/></c:if>)
                                               	</li>
                                               <li>
													<c:if test="${listCountryLanguage != '[]'}">
														<select class="w90" id="useLanguage" name="useLanguage">
														<c:forEach items="${listCountryLanguage}" var="countryLanguage" varStatus="status">
																<option value="<c:out value='${countryLanguage.countryCode}-${countryLanguage.languageCode}'/>">${countryLanguage.languageName}</option>
														</c:forEach>
														</select>
													</c:if>
												</li>
                                               <li><a href="javascript:logout();" class="logout_btn"><span>Logout</span></a></li>
                                           </ul>
                              </div>
                             	<!--// lobal -->  
                             	<!-- gnb -->  
                             		<div class="gnb_wrap">
                                                   <ul id="gnb">
                                                   	   <ntels:topmenu />
                                                  </ul>
                                    </div>
                               <!--// gnb -->  
						</div>
            </div>
            <div class="header_top">
              <a id="hideLeftBtn" class="icona" style="display : none;" href="javascript:hideLeftMenu();" ></a>
              <a id="showLeftBtn" class="iconb" style="display : none;" href="javascript:showLeftMenu();" ></a>
            </div>
 <!--// header -->
 <!-- 팝업참조 -->
</form>
<!--e : top-->
