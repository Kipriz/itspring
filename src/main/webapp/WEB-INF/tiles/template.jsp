<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
	<title>ItSpring</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" media="screen" />
	<link href="<c:url value="/resources/css/core.css" />" rel="stylesheet" media="screen" />

	<tilesx:useAttribute id="styles" name="styles" classname="java.util.List" ignore="true" />
	<c:forEach var="cssName" items="${styles}">
		<link type="text/css" href="<c:url value="/resources/css/${cssName}"/>" rel="stylesheet" media="screen" />
	</c:forEach>

	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <script>
        var Global = {}
        Global.contextPath = "${pageContext.request.contextPath}"
        Global.resources = Global.contextPath + "/resources"
    </script>

</head>
<body>

<div id="wrap">
	<tiles:insertAttribute name="header"  defaultValue="" />
	<!-- Page content -->
	<div class="container">
        <% /* Show a message. See support.web package */ %>
        <c:if test="${not empty message}">
            <c:choose>
                <c:when test="${message.type == 'WARNING'}">
                    <c:set value="" var="alertClass" />
                </c:when>
                <c:otherwise>
                    <c:set value="alert-${fn:toLowerCase(message.type)}" var="alertClass" />
                </c:otherwise>
            </c:choose>
            <div class="alert ${alertClass}">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <% /* Display a message by its code. If the code was not found, it will be displayed as default text */ %>
                <s:message code="${message.message}" arguments="${message.args}" text="${message.message}" />
            </div>
        </c:if>
        <div class="row">
            <div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
                <tiles:insertAttribute name="body" defaultValue=""/>
            </div>
            <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                <security:authorize access="isAuthenticated()">
                    <tiles:insertTemplate template="/WEB-INF/tiles/profile-tile.jsp"/>
                </security:authorize>
            </div>

        </div>
	</div>
    <div id="push"></div>
    <!-- End of page content -->
</div>
<div id="footer">
    <div class="container">
        <tiles:insertAttribute name="footer" defaultValue=""/>
    </div>
</div>

	<script src="<c:url value="/resources/js/third-party/jquery/1.10.2/jquery-min.js" />"></script>
    <script src="<c:url value="/resources/js/third-party/bootstrap/bootstrap.min.js" />"></script>

    <tilesx:useAttribute id="scripts" name="scripts" classname="java.util.List" ignore="true"/>
    <c:forEach var="script" items="${scripts}">
        <script src="<c:url value="/resources/${script}"/>"></script>
    </c:forEach>

</body>
</html>