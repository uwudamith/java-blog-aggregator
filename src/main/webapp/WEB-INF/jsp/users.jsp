<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp" %>
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>User name</th>
			<th>View Profile</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.name}</td>
				<td><a type="submit" class="btn btn-info" href="<spring:url value='/users/${user.id}.html'/>">View</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>