<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="container">
	<div>
		<div>
			<h2>회원수정</h2>
		</div>
	</div>
	<form:form action="${contextPath}/member/memberModify" id="modifyForm" method="post" modelAttribute="memberVO">
		<div class="form-group">
			<label for="userId">아이디</label>
				<div class="row">
					<div class="col-sm-8">
						<input type="text" class="form-control"  name="userId"  placeholder="아이디 중복검사를 해주세요" value='<c:out value="${memberVO.userId}"/>' readonly="readonly"/>
					</div>
				</div>
			<form:errors path="userId" style="color:red;"/>
		</div> 
		<div class="form-group">
			<label for="userPw">비밀번호</label>
			<div class="row">
				<div class="col-sm-8">
					<form:input type="password" class="form-control" path="userPw" placeholder="사용하실 비밀번호를 입력해 주세요"/>
				</div>
			</div>
			<form:errors path="userPw"/>
		</div>
		<div class="form-group">
			<label for="confirmUserPw">비밀번호 확인</label>
			<div class="row">
				<div class="col-sm-8">
					<form:input type="password" class="form-control" path="confirmUserPw" placeholder="비밀번호를 한번더 입력해 주세요" />
				</div>
			</div>
			<form:errors path="confirmUserPw"/>
		</div>
		<div class="form-group">
			<label for="userEmail">이메일</label>
			<div class="row">
				<div class="col-sm-8">
					<form:input type="email" class="form-control"  path="userEmail"  placeholder="이메일을 입력해 주세요"/>
				</div>
			</div>
			<form:errors path="userEmail"/>
		</div>
		<div class="form-group">
			<label for="userName">이름</label>
			<div class="row">
				<div class="col-sm-8">
					<form:input type="text" class="form-control" path="userName"  placeholder="사용자 이름을 입력해 주세요"/>
				</div>
			</div>
			<form:errors path="userName"/>
		</div>
		<div class="form-group">
			<label for="addr">주소</label>
			<div class="row">
				<div class="col-sm-8">
					<form:input type="text" class="form-control" path="addr"  placeholder="주소를 입력해주세요"/>
				</div>
			</div>
			<form:errors path="addr"/>
		</div>
		<div class="form-group">
			<label for="exampleFormControlInput1">전화번호</label>
			<div class="row">
				<div class="col-sm-8">
					<form:input type="text" class="form-control"  path="phoneNumber"  placeholder="전화번호를 입력해주세요 EX)000-0000-0000"/>
				</div>
			</div>
			<form:errors path="phoneNumber"/>
		</div>
		<div class="form-group">
			<label for="natalDay">생년월일</label>
			<div class="row">
				<div class="col-sm-8">
					<form:input type="date" class="form-control" path="natalDay"  placeholder="YYYY.MM.DD"/>
				</div>
			</div>
			<form:errors path="natalDay"/>
		</div>
		<div class="form-group">
			<label for="exampleFormControlInput1">성별 : </label> 
			<div class="form-check-inline">
		      	<label class="form-check-label" >
		    		<form:radiobutton path="gender" value="M"/> 남성   		
		       	</label>
		    </div>
		    <div class="form-check-inline">
		    	<label class="form-check-label">
		       		<form:radiobutton path="gender" value="W"/> 여성
		    	</label>
		    </div>
		</div>
		<button class="btn btn-primary btn-block">수정하기</button>
	</form:form>
</div>


<%@ include file="/WEB-INF/views/layout/footer.jsp" %>

<script>
$(function(){
	
})
</script>