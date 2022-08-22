<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<div class="container">
	<form id="memberForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<div class="media">
			<div class="media-body">
				<h4>마이페이지</h4>
				<p>${member.userName}님의 회원정보입니다</p>
				<div class="getData">
					<input type="hidden"name="page" id="page" value="${param.page}">
					<input type="hidden"name="type" id="type" value="${param.type}">
					<input type="hidden"name="keyword" id="keyword" value="${param.keyword}">
				</div>
				<div>
					<input type="hidden" name="userId" value="${member.userId}">
					<input type="hidden" name="userName" value="${member.userName}">
					<input type="hidden" name="userEmail" value="${member.userEmail}">
					<input type="hidden" name="addr" value="${member.addr}">
					<input type="hidden" name="phoneNumber" value="${member.phoneNumber}">
					<input type="hidden" name="natalDay" value="${member.natalDay}">
					<input type="hidden" name="gender" value="${member.gender}">
				</div>
				<div>
				    <p>아이디 : ${member.userId} </p>
				    <p>작성자 : ${member.userName} </p>
				    <p>이메일 : ${member.userEmail} </p>
				    <p>주소 : ${member.addr} </p>
				    <p>전화번호 : ${member.phoneNumber} </p>
				    <p>생년월일 : ${member.natalDay} </p>
				    <p>성별 : ${member.gender} </p>
				    <p>회원가입날짜 :
				    		<fmt:parseDate var="regDate" value="${member.regDate }" pattern="yyyy-MM-dd'T'"/>
							<fmt:formatDate value="${regDate}" pattern="yyyy년MM월dd일"/> 
					</p>
			    </div>
			</div>
		</div><br>
		<button class="btn btn-outline-primary btn-block modify">수정</button>
		<button class="btn btn-outline-danger btn-block remove">탈퇴</button>
	</form>	
</div>
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>

<script>
$(function(){
	let memberForm = $("#memberForm");
	
	$('#memberForm .modify').on('click',function(e){
		e.preventDefault();
		memberForm.attr("action","${contextPath}/member/memberModify")
		memberForm.submit();
	})
	$('#memberForm .remove').on('click',function(e){
		e.preventDefault();
		memberForm.attr("method","post");
		memberForm.attr("action","${contextPath}/member/memberRemove")
		memberForm.submit();
	})
})
</script>