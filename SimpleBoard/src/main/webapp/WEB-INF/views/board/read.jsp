<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>글 읽기</h1>
	제목 : <input type="text" name="bTitle" value="${board.bTitle }" readonly="readonly"><br><br> 
	글쓴이 : <input type="text" name="bWrite" value="${board.bWriter }" readonly="readonly"><br><br> 
	내용 : <textarea rows="5" , cols="50" name="bContent">${board.bContent }</textarea><br><br>
	<a href="">수정하기</a> &nbsp;&nbsp;&nbsp;&nbsp;
	<a href="">삭제하기</a>
</body>
</html>