<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>电影列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/book/list.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/pager/pager.css'/>" />
    <script type="text/javascript" src="<c:url value='/jsps/pager/pager.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jsps/js/book/list.js'/>"></script>
  </head>
  
  <body>
<div style="font-size: 10pt">
	<h1 style="text-align: center;">在线视频推荐系统</h1>
	&nbsp; &nbsp;&nbsp; &nbsp;

	<a href="/movie/MovieServlet?method=getRecommendation&type=1">基于内容推荐</a>
	&nbsp; &nbsp;
	<a href="/movie/MovieServlet?method=getRecommendation&type=1">LDA主题推荐</a>
	&nbsp; &nbsp;
	<a href="/movie/MovieServlet?method=getRecommendation&type=0">主题扩展推荐</a>




</div>
<ul>
<c:forEach items = "${pb.beanList }" var = "movie">
  <li>
  <div class="inner">
    <a class="pic" href="<c:url value='/BookServlet?method=load&bid=${movie.movieId }'/>"><img src="<c:url value='${movie.img }'/>" border="0"/></a>
    <%--<p class="price">--%>
		<%--<span class="price_n">&yen;${book.currPrice}</span>--%>
		<%--<span class="price_r">&yen;${book.price}</span>--%>
		<%--(<span class="price_s">${book.discount}</span>)--%>
	<%--</p>--%>
	<p><a id="movieName" title="${movie.movieName}" href="<c:url value='/BookServlet?method=load&bid=${movie.movieId }'/>">${movie.movieName}</a></p>
	<%--url标签会自动对参数进行URL编码 --%>
	<%--<c:url value="/BookServlet" var="authorUrl">--%>
		<%--<c:param name="method" value="findByAuthor"/>--%>
		<%--<c:param name="author" value="${book.author}"/>--%>
	<%--</c:url>--%>
	<%--<c:url value="/BookServlet" var="pressUrl">--%>
		<%--<c:param name="method" value="findByPress"/>--%>
		<%--<c:param name="press" value="${book.press}"/>--%>
	<%--</c:url>--%>
	<%--<p><a href="${authorUrl }" name='P_zz' title='${book.author}'>${book.author}</a></p>--%>
	<%--<p class="publishing">--%>
		<%--<span>出 版 社：</span><a href="${pressUrl }">${book.press}</a>--%>
	<%--</p>--%>
	<p class="publishing_time"><span>评分：</span>${movie.rate}</p>
  </div>
  </li>

</c:forEach>



</ul>

<div style="float:left; width: 100%; text-align: center;">
	<hr/>
	<br/>
	<%@include file="/jsps/pager/pager.jsp" %>
</div>

  </body>
 
</html>

