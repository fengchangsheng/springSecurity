<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:url value='/' var="root"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
  <head>
    <base href="${root}">
    
    <title>My JSP 'noticeList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${root}resource/css/styles.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${root}resource/js/jquery-1.9.1.min.js"></script>
  </head>
  
  <body>
  
	<ul class="noticeList">
		 <sec:authorize ifAnyGranted="ROLE_limiton">
		 	<a href="limiton!load.action">配置权限</a></li>
         </sec:authorize>
		 <c:forEach items="${datas}" var="notice">
		 <li id="li${notice.noticeId }">[<fmt:formatDate value="${notice.addTime }"  pattern= "yyyy-MM-dd HH:mm:ss" />]
		 <a href="notice!updateA.action?nid=${notice.noticeId }">修改</a>
		 <a href="javascript:del(${notice.noticeId})">删除</a>
		 <Br/>
		 <sec:authorize ifAnyGranted="ROLE_detail">
		 	<a href="notice!detail.action?nid=${notice.noticeId }">${notice.title }</a></li>
         </sec:authorize>
          <sec:authorize ifNotGranted="ROLE_detail">
		 	${notice.title }</a></li>
         </sec:authorize>
		 
		 </c:forEach>
	</ul>
    
-------------------------------------------------------------------<br />

<%--有问题！！！
	<sec:authorize url="/b.jsp" method="GET">
		<a href="${root}b.jsp">b.jsp</a><br />
	</sec:authorize>
	<sec:authorize url="/c.jsp" method="GET">
		<a href="${root}c.jsp">c.jsp</a><br />
	</sec:authorize>
	<sec:authorize url="/d.jsp" method="GET">
		<a href="${root}d.jsp">d.jsp</a><br />
	</sec:authorize>
	<sec:authorize url="/e.jsp" method="GET">
		<a href="${root}e.jsp">e.jsp</a><br />
	</sec:authorize>
--%>

-------------------------------------------------------------------<br />
<sec:authorize access="isAuthenticated()">
   YES, you are logged in! <br />
</sec:authorize>

<sec:authorize access="hasRole('ROLE_AAA') and fullyAuthenticated">
ROLE_AAA<br />
</sec:authorize>
<sec:authorize access="hasRole('ROLE_BBB') and fullyAuthenticated">
ROLE_BBB<br />
</sec:authorize>
<sec:authorize access="hasRole('ROLE_CCC') and fullyAuthenticated">
ROLE_CCC<br />
</sec:authorize>

-------------------------------------------------------------------<br />

---------------------------------------------------------------------<br>	

a.jsp:
<sec:authorize ifNotGranted="ROLE_AAA">
	NO ROLE_AAA<br />
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_AAA">
	<a href="${root}a.jsp">有权限访问 : 自己实现的权限控制</a><br />
</sec:authorize>

b.jsp:
<sec:authorize ifNotGranted="ROLE_BBB">
	NO ROLE_BBB<br />
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_BBB">
	<a href="${root}b.jsp">有权限访问 : 自己实现的权限控制</a><br />
</sec:authorize>

c.jsp:
<sec:authorize ifNotGranted="ROLE_CCC">
	NO ROLE_CCC<br />
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_CCC">
	<a href="${root}c.jsp">有权限访问 : 自己实现的权限控制</a><br />
</sec:authorize>

d.jsp:
<sec:authorize ifNotGranted="ROLE_AAA">
	NO ROLE_AAA<br />
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_AAA">
	<a href="${root}d.jsp">有权限访问 : spring配置的权限控制</a><br />
</sec:authorize>

e.jsp:双重权限控制 --spring配置需要拥有BBB  自己又配置了EEE
<sec:authorize ifNotGranted="ROLE_EEE,ROLE_BBB">
	NO ROLE_EEE   双重权限控制(全满足才能通过)<br />
</sec:authorize>
<sec:authorize ifAllGranted="ROLE_EEE,ROLE_BBB">
	<a href="${root}e.jsp">有权限访问 : 双重的权限控制</a><br />
</sec:authorize>


<c:url value="/j_spring_security_logout" var="logoutUrl"/>
	<a href="${logoutUrl}">Log Out</a><br />


-------------------------------------权限列表-----------------------------------------<br>	

<sec:authorize ifAnyGranted="ROLE_AAA">
	ROLE_AAA<br />
</sec:authorize>
<sec:authorize ifNotGranted="ROLE_AAA">
	NO ROLE_AAA<br />
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_BBB">
	ROLE_BBB<br />
</sec:authorize>
<sec:authorize ifNotGranted="ROLE_BBB">
	NO ROLE_BBB<br />
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_CCC">
	ROLE_CCC<br />
</sec:authorize>
<sec:authorize ifNotGranted="ROLE_CCC">
	NO ROLE_CCC<br />
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_DDD">
	ROLE_DDD<br />
</sec:authorize>
<sec:authorize ifNotGranted="ROLE_DDD">
	NO ROLE_DDD<br />
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_EEE">
	ROLE_EEE<br />
</sec:authorize>
<sec:authorize ifNotGranted="ROLE_EEE">
	NO ROLE_EEE<br />
</sec:authorize>


<sec:authorize ifAllGranted="ROLE_AAA,ROLE_BBB,ROLE_CCC">
	ALL<br />
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_AAA,ROLE_BBB,ROLE_CCC">
	ANY<br />
</sec:authorize>
---------------------------------------------------------------------<br>	
  </body>
</html>
