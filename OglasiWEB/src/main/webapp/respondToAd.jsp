<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Respond to ad</title>
</head>
<body>
	<h1>Oglasi - Respond to ad</h1>
	<c:if test="${!empty error}">
		<h3>${error}</h3>
	</c:if>
	<form action="/OglasiWEB/RespondToAdServlet" method="post">
		<table>
			<tr>
				<td>Ad id</td>
				<td><input type="text" readonly="readonly"
					value="${param.adId}" name="adId"></td>
			</tr>
			<tr>
				<td>Text</td>
				<td><textarea name="text"></textarea></td>
			</tr>
		</table>
		<input type="submit" value="Respond">
	</form>
</body>
</html>