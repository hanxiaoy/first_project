<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>	
	<p>
		Hi <shiro:guest>Guest</shiro:guest><shiro:user>
		
		<%
		request.setAttribute("account", 
				org.apache.shiro.SecurityUtils.getSubject().getPrincipals().oneByType(java.util.Map.class));
		%>
		
		${account.givenName }</shiro:user>!
		
		<shiro:user><a href="<c:url value="/logout"/>">Log out</a></shiro:user>
		<shiro:guest><a href="<c:url value="/login.jsp"/>">Log in</a></shiro:guest>
		
		<shiro:authenticated><p>Visit your <a href="<c:url value="/account"/>">account page</a>.</p></shiro:authenticated>
<shiro:notAuthenticated><p>If you want to access the authenticated-only <a href="<c:url value="/account"/>">account page</a>,
    you will need to log-in first.</p></shiro:notAuthenticated>
    
    <h3>Roles you have:</h3>

=====<br/>
<shiro:hasAnyRoles name="admin">
         <shiro:principal/>拥有角色admin
</shiro:hasAnyRoles>
======     
<p>
    <shiro:hasRole name="admin">admin<br/></shiro:hasRole>
    <shiro:hasRole name="Officers">Bad Guys<br/></shiro:hasRole>
    <shiro:hasRole name="Enlisted">Enlisted<br/></shiro:hasRole>
</p>

<h3>Roles you DON'T have:</h3>

<p>
    <shiro:lacksRole name="admin">admin<br/></shiro:lacksRole>
    <shiro:lacksRole name="Officers">Officers<br/></shiro:lacksRole>
    <shiro:lacksRole name="Enlisted">Enlisted<br/></shiro:lacksRole>
</p>


<h2>Permissions</h2>

<ul>
    <li>You may <shiro:lacksPermission name="ship:NCC-1701-D:command"><b>NOT</b> </shiro:lacksPermission> command the <code>NCC-1701-D</code> Starship!</li>
    <li>You may <shiro:lacksPermission name="user:${account.username}:edit"><b>NOT</b> </shiro:lacksPermission> edit the ${account.username} user!</li>
</ul>


拥有的权限：<br/>
	<li><shiro:hasPermission name="user:create">拥有创建权限</shiro:hasPermission></li>
	<li><shiro:hasPermission name="user:update">拥有修改权限</shiro:hasPermission></li>

</body>
</html>