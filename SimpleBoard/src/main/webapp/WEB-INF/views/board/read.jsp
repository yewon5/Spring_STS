<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
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
	
	<h1>글 읽기</h1>
	제목 : <input type="text" name="bTitle" value="" readonly="readonly"><br><br> 
	글쓴이 : <input type="text" name="bWrite" value="" readonly="readonly"><br><br> 
	내용 : <textarea rows="5" , cols="50" name="bContent"></textarea><br><br>
	<a href="">수정하기</a> &nbsp;&nbsp;&nbsp;&nbsp;
	<a href="">삭제하기</a>
</body>
</html>