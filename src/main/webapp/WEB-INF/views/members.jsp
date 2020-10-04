<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>한성고 랭킹</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<h2 style="margin-top: 25px">HansungGO</h2>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>순위</th>
					<th>학번</th>
					<th>이름</th>
					<th>연락처</th>
					<th>코인개수</th>
					<th>업데이트된 시각</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="member" items="${members}">
				<tr>
					<td><c:out value="${members.indexOf(member)+1}"></c:out></td>
					<td><c:out value="${member.id}"></c:out></td>
					<td><c:out value="${member.name}"></c:out></td>
					<td><c:out value="${member.phone}"></c:out></td>
					<td><c:out value="${member.coinCount}"></c:out></td>
					<td><c:out value="${member.updateTime}"></c:out></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>