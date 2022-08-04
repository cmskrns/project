<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<div class="container">
	<div>
		<div>
			<h2>회원가입</h2>
		</div>
	</div>
	<form action="register"method="post">
	<div class="form-group">
		<label for="exampleFormControlInput1">아이디</label>
		<input type="text" class="form-control" id="exampleFormControlInput1" name="userId" placeholder="사용하실 아이디를 입력해 주세요">
	</div> 
	<div class="form-group">
		<label for="exampleFormControlInput1">비밀번호</label>
		<input type="text" class="form-control" id="exampleFormControlInput1" name="userPw" placeholder="사용하실 비밀번호를 입력해 주세요">
	</div>
	<div class="form-group">
		<label for="exampleFormControlInput1">비밀번호 확인</label>
		<input type="text" class="form-control" id="exampleFormControlInput1" name="confirmUserPw" placeholder="비밀번호를 한번더 입력해 주세요">
	</div>
	<div class="form-group">
		<label for="exampleFormControlInput1">이름</label>
		<input type="text" class="form-control" id="exampleFormControlInput1" name="userName" placeholder="사용자 이름을 입력해 주세요">
	</div>
	<div class="form-group">
		<label for="exampleFormControlInput1">주소</label>
		<input type="text" class="form-control" id="exampleFormControlInput1" name="addr" placeholder="주소를 입력해주세요">
	</div>
	<div class="form-group">
		<label for="exampleFormControlInput1">신청등급 : </label>
		<div class="form-check-inline">
      		<label class="form-check-label" for="radio1">
        		<input type="radio" class="form-check-input" id="radio1" name=""  value="" checked>일반회원
       		</label>
    	</div>
    	<div class="form-check-inline">
      		<label class="form-check-label" for="radio2">
        		<input type="radio" class="form-check-input" id="radio2" name="" value="" >관리자
      		</label>
    	</div>
	</div>
		<button class="btn btn-primary btn-block">신청</button>
	</form>
</div>
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>