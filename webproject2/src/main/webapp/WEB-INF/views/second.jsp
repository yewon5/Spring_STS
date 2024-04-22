<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>여기는 second.jsp 입니다.</h1>
	param1 : <%=request.getAttribute("param1") %><br>
	param2 : ${param2 }
	<!-- EL방식이 실행 안되는 이유는? isELIgnored="true" El 무시가 디폴트 값이라서 false로 수정해준다. -->
</body>
</html>