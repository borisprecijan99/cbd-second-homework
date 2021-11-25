<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search ads</title>
</head>
<body>
	<h1>Oglasi - Search ads</h1>
	<form action="/OglasiWEB/SearchAdsServlet" method="get">
		<table>
			<tr>
				<td>Text</td>
				<td><input type="text" name="text"></td>
			</tr>
		</table>
		<input type="submit" value="Search">
	</form>
	<c:if test="${!empty ads}">
		<br>
		<table border="1">
			<tr>
				<th>Id</th>
				<th>Text</th>
				<th>User</th>
				<th>Number of views</th>
				<th></th>
			</tr>
			<c:forEach var="ad" items="${ads}">
				<tr>
					<td>${ad.id}</td>
					<td>${ad.tekst}</td>
					<td>${ad.korisnik.nadimak}</td>
					<td>${ad.brojPregleda}</td>
					<td><a href="/OglasiWEB/respondToAd.jsp?adId=${ad.id}">Respond
							to ad</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>