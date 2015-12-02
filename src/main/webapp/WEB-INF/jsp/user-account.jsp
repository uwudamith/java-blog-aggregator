<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<c:if test="${param.available eq true }">
	<div class="alert alert-warning">
		Blog contain items. Delete with items? <a
			href='<spring:url value="/blog/removeWithItems/${param.ref}.html"></spring:url>' class="pull-right">Remove</a>
	</div>
</c:if>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal"
	data-target="#myModal">New Blog</button>

<!-- Modal -->
<form:form commandName="blog" class="form-horizontal">
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add New Blog</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Name</label>
						<div class="col-sm-10">
							<form:input path="name" cssClass="form-control" id="txtName"
								placeholder="Name" ></form:input>
								<form:errors path="name"></form:errors>
						</div>
					</div>
					<div class="form-group">
						<label for="url" class="col-sm-2 control-label">URL</label>
						<div class="col-sm-10">
							<form:input path="url" cssClass="form-control" id="txtUrl"
								placeholder="URL" />
								<form:errors path="url"></form:errors>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" class="btn btn-primary" value="Save" />
				</div>
			</div>
		</div>
	</div>
</form:form>
<br />
<br />

<script type="text/javascript">
	$(document).ready(function() {
		$('.nav-tabs a:first').tab('show'); // Select first tab
	});
</script>
<!-- Nav tabs -->
<ul class="nav nav-tabs" role="tablist">
	<c:forEach items="${user.blogs}" var="blog">
		<li role="presentation"><a href="#blog_${blog.id}"
			aria-controls="home" role="tab" data-toggle="tab">${blog.name}</a></li>
	</c:forEach>
</ul>
<div>
	<!-- Tab panes -->
	<div class="tab-content">
		<c:forEach items="${user.blogs}" var="blog">
			<div role="tabpanel" class="tab-pane" id="blog_${blog.id}">
				<h1> <c:out value="${blog.name}"></c:out> </h1>
				<p><c:out value="${blog.url}"></c:out> 
					<a href="<spring:url value="/blog/remove/${blog.id}.html" /> "
						class="btn btn-danger">Remove Blog</a> 
				</p>
				<table class="table table-bordered table-hover table-striped">
					<thead>
						<tr>
							<th>Title</th>
							<th>Link</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${blog.items}" var="item">
							<tr>
								<td><c:out value="${item.title}"></c:out></td>
								<td><c:out value="${item.link}"></c:out> </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:forEach>
	</div>
</div>
