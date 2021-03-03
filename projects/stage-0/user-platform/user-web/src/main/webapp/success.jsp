
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.geektimes.projects.user.domain.User" %>
<%@ page import="java.util.List" %>

<head>
<jsp:directive.include
	file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
<%--	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
	<title>My Home Page</title>
</head>
<body>
	<div class="container-lg">
		<!-- Content here -->
		<h1>操作成功！！！</h1>
	</div>

	<table>
		<tr>
			<td>id</td>
			<td>name</td>
			<td>password</td>
			<td>email</td>
			<td>phoneNumber</td>
		</tr>
		<c:forEach items="${userList}" var="l">
			<tr>
				<td>${l.id}</td>
				<td>${l.name}</td>
				<td>${l.password}</td>
				<td>${l.email}</td>
				<td>${l.phoneNumber}</td>
			</tr>
		</c:forEach>
	</table>



</body>