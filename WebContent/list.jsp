<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
    <form action="${pageContext.request.contextPath }/listTermServlet">
        <font color="red">输入时间格式：HH:mm:ss</font></br> 
        开始时间：<input type="text"   name="begintime"> 
        截止时间：<input type="text" name="endtime">
        <input type="submit" value="提交">
    </form>
    
    <a href="${pageContext.request.contextPath }/add.jsp"><input type="button" value="添加"></a>
    <a href="${pageContext.request.contextPath }/excelServlet"><input type="button" value="导出到excel"></a>
    <font color="green">excel会导出到D盘根目录下</font>
    <form action="${pageContext.request.contextPath }/deleteSomeServlet">
    <input type="submit" value="批量删除">
    <table>
        <tr>
            <td align="center"></td>
            <td align="center">id</td>
            <td align="center">时间</td>
            <td align="center">服务</td>
            <td align="center">接口</td>
            <td align="center">IP值</td>
        </tr>
        <c:forEach items="${pageBean.list}" var="bean" >
            <tr>
                <td><input  type="checkbox"  name="userid" value="${bean.id}"></td> 
                <td align="center">${bean.id}</td>
                <td align="center">${bean.dates}</td>
                <td align="center">${bean.service}</td>
                <td align="center">${bean.operate}</td>
                <td align="center">${bean.IP}</td>
                <td align="center"><a href="${pageContext.request.contextPath }/updateServlet?page=${pageBean.curnum}&id=${bean.id}">修改</a></td>
                <td align="center"><a href="${pageContext.request.contextPath }/deleteServlet?page=${pageBean.curnum}&id=${bean.id}">删除</a></td>
            </tr>
        </c:forEach>
    </table>
    </form>
    
    <a href = "${pageContext.request.contextPath }/listServlet?page=1" >首页</a>
     <c:if test="${pageBean.curnum!='1'}">  
    <a href = "${pageContext.request.contextPath }/listServlet?page=${pageBean.before}">上一页</a>
    </c:if>  
    
    <c:if test="${pageBean.curnum!=pageBean.totalPage}">
    <a href = "${pageContext.request.contextPath }/listServlet?page=${pageBean.next}">下一页</a> 
    </c:if> 
    <a href = "${pageContext.request.contextPath }/listServlet?page=${pageBean.totalPage }" >尾页</a>  
第${pageBean.curnum}页/共${pageBean.totalPage}页  
</body>
</html>