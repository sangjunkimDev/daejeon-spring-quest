<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" /></title>

<%@include file="/include/jquery.jsp"%>
<%@include file="/include/bootstrap.jsp"%>

<style type="text/css">
.header {
	
}

.menu {
	float: left;
	width: 200px;
	height: 100px;
}

.body {
	float: left;
	width: 1400px;	
	height: 100px;
}

.footer {
	clear: both;
	height: 100px;
}
</style>
</head>
<body>

	<!-- <div class="container"> -->
	<div>

		<!-- header -->
		<div class="header">
			<tiles:insertAttribute name="header" />
		</div>

		<!-- menu -->
		<div class="menu">
			<tiles:insertAttribute name="menu" />
		</div>

		<!-- body -->
		<div class="body">
			<tiles:insertAttribute name="body" />
		</div>

		<!-- footer -->
		<div class="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>