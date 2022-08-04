<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<div class="container" style="text-align: center; padding-top: 20px">
  <h2>게시글 작성</h2>
</div>
<form action="register" method="post" id="registerForm">
	<div class="container">
		<div class="form-group">
			<label for="exampleFormControlInput1">작성자</label>
			<input type="text" class="form-control" id="exampleFormControlInput1" name="writer">
		</div> 
		<div class="form-group">
			<label for="exampleFormControlInput1">제목</label>
			<input type="text" class="form-control" id="exampleFormControlInput1" name="title">
		</div>
		<div class="form-group">
			<label for="exampleFormControlInput1">카테고리 : </label>
			<div class="form-check-inline">
	      		<label class="form-check-label" for="radio1">
	        		<input type="radio" class="form-check-input" id="radio1" name="category"  value="Free" checked>자유게시판
	       		</label>
	    	</div>
	    	<div class="form-check-inline">
	      		<label class="form-check-label" for="radio2">
	        		<input type="radio" class="form-check-input" id="radio2" name="category" value="Tip" >팁게시판
	      		</label>
	    	</div>
	    	<div class="form-check-inline">
	      		<label class="form-check-label" for="radio3">
	       			<input type="radio" class="form-check-input" id="radio3" name="category" value="Recommend" >추천합니다
	      		</label>
	    	</div>
		</div>
		<div class="form-group">
			<label for="exampleFormControlTextarea1">내용</label>
			<textarea class="form-control" id="exampleFormControlTextarea1" name="content" rows="10"></textarea>          
		</div>
	 	<button class="btn btn-primary btn-block">수정</button>
	</div>
</form>

<%@ include file="/WEB-INF/views/layout/footer.jsp" %>