<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
</head>
<body>
	<div id="menu">
		<ul>
			<li><a href="<c:url value='/'/>">Home</a></li>
			<li><a href="<c:url value='/board/list'/>">Board</a></li>
			<li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
			<li><a href="<c:url value='/member/add'/>">Sign in</a></li>
			<li><a href=""><i class="fa fa-search"></i></a></li>
		</ul>
	</div>
	<div style="text-align: center">
		<h1>This is HOME</h1>
		<h1>This is HOME</h1>
		<h1>This is HOME</h1>
	</div>
</body>
</html>