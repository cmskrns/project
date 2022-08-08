<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<div class="container">
	<div>
		<div>
			<h2>회원가입</h2>
		</div>
	</div>
	<form action="${contextPath}/member/memberinsert" method="post">
		<div class="form-group">
			<label for="exampleFormControlInput1">아이디</label>
			<div class="row">
				<div class="col-sm-8">
					<input type="text" class="form-control" id="exampleFormControlInput1" name="userId" placeholder="사용하실 아이디를 입력해 주세요">
				</div>
				<div class="col-sm-4">
					<button class="btn btn-primary idSelectBtn" type="button">중복검사</button>
				</div>
			</div>
		</div> 
		<div class="form-group">
			<label for="exampleFormControlInput1">비밀번호</label>
			<input type="password" class="form-control" id="exampleFormControlInput1" name="userPw" placeholder="사용하실 비밀번호를 입력해 주세요">
		</div>
		<div class="form-group">
			<label for="exampleFormControlInput1">비밀번호 확인</label>
			<input type="password" class="form-control" id="exampleFormControlInput1" name="confirmUserPw" placeholder="비밀번호를 한번더 입력해 주세요">
		</div>
		<div class="form-group">
			<label for="exampleFormControlInput1">이메일</label>
			<input type="text" class="form-control" id="exampleFormControlInput1" name="userEmail" placeholder="이메일을 입력해 주세요">
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
			<label for="exampleFormControlInput1">전화번호</label>
			<input type="text" class="form-control" id="exampleFormControlInput1" name="phoneNumber" placeholder="전화번호를 입력해주세요">
		</div>
		<div class="form-group">
			<label for="exampleFormControlInput1">나이</label>
			<input type="text" class="form-control" id="exampleFormControlInput1" name="age" placeholder="나이를 입력해주세요">
		</div>
		<div class="form-group">
			<label for="exampleFormControlInput1">생년월일</label>
			<input type="text" class="form-control" id="exampleFormControlInput1" name="natalDay" placeholder="YYYY.MM.DD">
		</div>
		<div class="form-group">
			<label for="exampleFormControlInput1">성별 : </label>
			<div class="form-check-inline">
		      	<label class="form-check-label" for="radio1">
		       		<input type="radio" class="form-check-input" id="radio1" name="gender"  value="M" checked>남성
		       	</label>
		    </div>
		    <div class="form-check-inline">
		    	<label class="form-check-label" for="radio2">
		       		<input type="radio" class="form-check-input" id="radio2" name="gender" value="W" >여성
		    	</label>
		    </div>
		</div>
		<button class="btn btn-primary btn-block">회원가입</button>
	</form>
</div>
<div class="fade modal" id="select_id">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">아이디 중복 확인</h4>
          <button type="button" class="close" data-dismiss="modal">×</button>
        </div>
        <div class="modal-body">
        	<div class="form-group">
        		<input type="text" class="userId form-control">
        	</div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary findId">조회</button>
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/layout/footer.jsp" %>

<script>
$(function(){
	$('.idSelectBtn').on('click',function(){
		$('#select_id').find('.userId').val('');
		$('#select_id').modal('show');
	});
	
	$('.findId').on('click',function(){
		let userId = $('#select_id').find('.userId').val();
		if(userId.trim()=='' || userId==null) {
			alert('아이디를 입력해주세요')
			return; 
		}
		let url = contextPath + "/member/idCheck/" + userId;
		$.getJSON(url,function(result){
			if(result){ // 사용가능
				alert('사용가능한 아이디 입니다.')
				$('#signForm').find('input[name="userId"]').val(userId);
				$('#select_id').modal('hide');
				
			} else { // 사용 불가능
				alert('이미 등록된 아이디 입니다 다른 아이디를 사용해주세요')
			}			
		}).fail(function(){
			alert('통신에러')
		});
		
	})
})
</script>