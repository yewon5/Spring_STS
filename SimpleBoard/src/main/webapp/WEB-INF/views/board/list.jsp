<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

	<h1 >게시판 리스트</h1>
	<a href="/board/write">글쓰기</a>
	<br><br>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>등록자</td>
			<td>등록일</td>
			<td>조회수</td>
		</tr>
		<tr>
			<td></td>
			<td><a href=""></a></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>

</body>
</html>