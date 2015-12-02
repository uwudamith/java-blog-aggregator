<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<form:form commandName="user" class="form-horizontal registrationForm">

	<c:if test="${param.success eq true }">
		<div class="alert alert-success">Registration successful!</div>
	</c:if>

	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">Name</label>
		<div class="col-sm-10">
			<form:input path="name" cssClass="form-control" id="txtName"
				placeholder="Name" />
			<form:errors path="name"></form:errors>
		</div>
	</div>
	<div class="form-group">
		<label for="email" class="col-sm-2 control-label">Email</label>
		<div class="col-sm-10">
			<form:input path="email" class="form-control" id="txtEmail"
				placeholder="Email" />
			<form:errors path="email"></form:errors>
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-2 control-label">Password</label>
		<div class="col-sm-10">
			<form:password path="password" class="form-control" id="txtPassword"
				placeholder="Password" />
			<form:errors path="password"></form:errors>
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-2 control-label">Password</label>
		<div class="col-sm-10">
			<input type="password" name="password_again" id="password_again" class="form-control" placeholder="Re enter password" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-primary">Create</button>
		</div>
	</div>
</form:form>

<script>
	$(document).ready(function() {
		$(".registrationForm").validate({
			rules : {
				name : {
					required : true,
					minlength : 3,
					remote:{
						url:"<spring:url value='/register/available.html' />",
						type:"get",
						data:{
							username:function(){
								return $("#txtName").val();
							}
						}
					}
				},
				email : {
					required : true,
					email : true
				},
				password : {
					required : true,
					minlength : 4
				},
				password_again : {
					required : true,
					minlength : 4,
					equalTo:"#txtPassword"
				}
			},
			highlight:function(element){
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight:function(element){
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			},
			messages:{
				name:{
					remote:"Username already registered"
				}
			}
		});
	});
</script>