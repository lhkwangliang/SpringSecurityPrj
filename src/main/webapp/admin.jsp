<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin 管理界面</title>
</head>
<body>
	<c:url value="/logout" var="logoutUrl"/>
	<li><a href="${logoutUrl}">Log Out</a></li>
	<p style="color:red">admin.jsp页面</p>
</body>
</html>