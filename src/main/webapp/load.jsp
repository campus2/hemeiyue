<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/file/load" enctype="multipart/form-data" method="post">
	hrefUrl<input type="text" name="hrefUrl"><br>
	url<input type="text" name="url"><br>
	file<input type="file" name="file"><br>
	<input type="submit">
</form>
<br><br><br>
<form action="${pageContext.request.contextPath }/file/updateStatus">
	id<input type="text" name="id"><br>
	<!-- hrefUrl<input type="text" name="hrefUrl"><br> -->
	<input type="submit">
	
</form>

<img src="${pageContext.request.contextPath}assets/wechatImage/0a0d14a3b93b4e94988cc464acaab6a6.png">

</body>
</html>