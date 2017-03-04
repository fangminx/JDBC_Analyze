<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body> 
    <form action="${pageContext.request.contextPath}/addServlet">
        时间：<input type="text" name="time"><font color="red">格式：yyyy-MM-dd HH:mm:ss</font></br>
        服务：<input type="text" name="service"></br>
        接口：<input type="text" name="interface"> </br>
        IP值：<input type="text" name="ip"></br>
        <input type="submit" value="提交">
    </form>
</body>
</html>