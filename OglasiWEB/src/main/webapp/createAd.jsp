<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create ad</title>
</head>
<body>
	<h1>Oglasi - Create ad</h1>
	<c:if test="${!empty message}">
		<h3>${message}</h3>
	</c:if>
	<form action="/OglasiWEB/CreateAdServlet" method="post">
		<table>
			<tr>
				<td>Text</td>
				<td><input type="text" name="text"></td>
			</tr>
		</table>
		<input type="submit" value="Create">
	</form>
</body>
</html>