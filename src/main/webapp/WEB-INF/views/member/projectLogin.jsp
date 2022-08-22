<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="container">	
	<div class="loginForm my-5">
		<h2>로그인 페이지</h2>
		<form:form action="${contextPath}/member/projectLogin" method="post" modelAttribute="memberVO">
			<div class="form-group">
				<input type="text" name="loginId" id="loginId" value="${loginId }" placeholder="아이디" class="form-control">
			</div>
			<div class="form-group">
				<input type="password" name="loginPw" id="loginPw" placeholder="비밀번호" class="form-control">
			</div>
			<c:if test="${not empty error}">
				<div class="alert alert-danger">
				${error}				
			</div>
			</c:if>
			
			<div class="form-group">
				<label for="remember-me">Remember Me</label>
				<input type="checkbox" name="remember-me" id="remember-me">
			</div>
			<div class="form-group">
				<button class="btn btn-primary form-control loginBtn">로그인</button>
			</div>
		</form:form>
	</div>
</div>

<script>
$(function(){
	$('.loginBtn').on('click',function(e){
		if ($('#loginId').val()=='') {
			alert('아이디를 입력해주세요')
			e.preventDefault();
		}else if($('#loginPw').val()=='') {
			alert('비밀번호를 입력해주세요')
			e.preventDefault();
		}
		
	})
})
</script>
<style>
	.loginForm{width: 400px; margin: auto;}
</style>
</body>
</html>