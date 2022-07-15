<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<nav class="navbar navbar-expand-sm bg-light">
  <h3><a class="navbar-brand" href="${contextPath }">G.C.R</a></h3>
  <div class="container">
	  <ul class="navbar-nav">
	    <li class="nav-item">
	      <a class="nav-link" href="#">소개</a>
	    </li>
	    <li class="nav-item dropdown">
		    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">업종</a>
		    <div class="dropdown-menu">
		      <a class="dropdown-item" href="${contextPath }/board/list/K-Food">한식</a>
		      <a class="dropdown-item" href="${contextPath }/board/list/C-Food">중식</a>
		      <a class="dropdown-item" href="${contextPath }/board/list/J-Food">일식</a>
		      <a class="dropdown-item" href="#">양식</a>
		      <a class="dropdown-item" href="#">분식</a>
		      <a class="dropdown-item" href="#">기타</a>
		    </div>
	  	</li>
	    <li class="nav-item dropdown">
		   	<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">유저게시판</a>
		    <div class="dropdown-menu">
		      <a class="dropdown-item" href="#">자유게시판</a>
		      <a class="dropdown-item" href="#">나만의 팁</a>
		      <a class="dropdown-item" href="#">음식 자랑</a>
		    </div>
	  	</li>
	  	</ul>
  	</div>
	<ul class="navbar-nav">
		<li class="nav-item">
			<a class="nav-link">로그인</a>
		</li>
		<li class="nav-item">
			<a class="nav-link">회원가입</a>
		</li>
		<li class="nav-item">
			<a class="nav-link">한국어/영어</a>
		</li>
	</ul>
</nav>



<style>

</style>
