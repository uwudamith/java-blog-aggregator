<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp" %>

<c:if test="${param.available eq true }">
	<div class="alert alert-warning">
		User contain blogs. Delete with blogs? <a
			href='<spring:url value="/users/removeWithBlogs/${param.ref}.html"></spring:url>' class="pull-right">Remove</a>
	</div>
</c:if>
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>User name</th>
			<th width="170">Operations</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><c:out value="${user.name}"></c:out></td>
				<td>
				<a class="btn btn-info" href="<spring:url value='/users/${user.id}.html'/>">View</a>
				<a class="btn btn-warning" href="<spring:url value='/users/remove/${user.id}.html'/>">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>