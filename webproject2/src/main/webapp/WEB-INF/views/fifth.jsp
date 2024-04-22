<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>좋아하는 음식은?</h1>
	<c:forEach var="i" items="${foods }">
		${i}<br>
	</c:forEach>
	<br><br>
	
	<!-- Map은 반복문 사용 불가 (주로 검색용), EL을 이용하여 key값으로 바로 꺼내오기 -->
	<h1>좋아하는 과일은?</h1>
	${f1}, ${f2}, ${f3}
	<br><br>
	
	나는 ${day[1]}에 ${movie[2]}를 관람하였다.	
</body>
</html>