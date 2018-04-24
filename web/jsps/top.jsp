<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>top</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		width:100%;
		background: #b0bbb9;
		margin: 0px;
		color: #ffffff;
	}
	a {
		text-transform:none;
		text-decoration:none;
		color: #ffffff;
		font-weight: 900;
	} 
	a:hover {
		text-decoration:underline;
	}
</style>
  </head>
  
  <body>
<h1 style="text-align: center;">在线视频推荐系统</h1>
<div style="font-size: 10pt; line-height: 10px;">
<!-- 根据用户是否登陆，显示不同的连接 -->
	<table>
		<tr>
<td>
  		<a href="<c:url value='/jsps/user/login.jsp'/>" target="_parent">登录</a>;
 		<%--<a href="#" target="_parent">注册会员</a>--%>

</td>

	<td class="tdSearch" style="border-bottom-width: 0px;">
		<c:import url="/jsps/search.jsp"></c:import>
		<%--<iframe frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="<c:url value='/jsps/search.jsp'/>" name="search"></iframe>--%>
	</td>
		</tr>

	</table>

</div>
  </body>
</html>
