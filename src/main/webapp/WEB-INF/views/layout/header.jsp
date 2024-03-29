<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방구석미식가</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script>
	let contextPath = "${contextPath}"
	let csrfHeaderName = "${_csrf.headerName}"
	let csrfTokenValue = "${_csrf.token}"
</script>
<script src="${contextPath}/resources/js/reply.js"></script>
<script src="${contextPath}/resources/js/userReply.js"></script>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.memberVO" var="vo"/>
</sec:authorize>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-White">
	<div class="container">
		<h3><a class="navbar-brand" href="${contextPath }"><img src="${contextPath}/resources/img/logo.jpg" style="width: 200px;"></a></h3>
  		<ul class="nav nav-pills">
		    <li class="nav-item">
				<a class="nav-link" href="${contextPath }/board/list/K-Food">한식</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${contextPath }/board/list/C-Food">중식</a>
			</li>
			<li class="nav-item">
			    <a class="nav-link" href="${contextPath }/board/list/J-Food">일식</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${contextPath }/board/list/W-Food">양식</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${contextPath }/board/list/FD-Food">분식</a>
			</li>
		    <li class="nav-item dropdown">
			   	<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">유저게시판</a>
			    <div class="dropdown-menu">
			      <a class="dropdown-item" href="${contextPath }/userboard/list/Free">자유게시판</a>
			      <a class="dropdown-item" href="${contextPath }/userboard/list/Tip">팁 게시판</a>
			      <a class="dropdown-item" href="${contextPath }/userboard/list/Recommend">추천합니다</a>
			    </div>
		  	</li>
	  	</ul>
		<ul class="navbar-nav">
			<sec:authorize access="isAuthenticated()">
				<li class="nav-item">
					<a class="nav-link" href="">${vo.userName}님 어서오세요</a>
				</li>
			</sec:authorize>
			<li class="nav-item">
				<sec:authorize access="isAnonymous()">
					<a class="nav-link" href="${contextPath }/member/projectLogin">로그인</a>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<form id="logoutForm" method="post">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
						<a class="btn btn-primary logout">로그아웃</a>
					</form>
				</sec:authorize>
			</li>
			<li class="nav-item">
				<sec:authorize access="isAnonymous()">
					<a class="nav-link" href="${contextPath }/member/memberinsert">회원가입</a>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_USER')">
					<a class="nav-link" href="${contextPath}/member/myPage/${vo.userId}">마이페이지</a>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<a class="nav-link" href="${contextPath }/member/memberList">회원관리</a>
				</sec:authorize>
			</li>
		</ul>
	</div>
</nav>

<script>
$(function(){
	let logoutForm = $('#logoutForm')
	$('#logoutForm a').on('click',function(e){
		alert("로그아웃 되었습니다")
		logoutForm.attr("action","${contextPath}/member/projectLogout");
		logoutForm.submit();
	})
})
</script>
