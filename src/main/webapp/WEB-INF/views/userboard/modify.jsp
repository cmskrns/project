<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<div class="container" style="text-align: center; padding-top: 20px">
  <h2>게시글 수정</h2>
</div>
<form action="modify" method="post" id="modifyForm">
	<div class="container">
	<input type="hidden" name="bno" value="${userBoard.bno}">
	<input type="hidden" name="writer" value="${userBoard.writer}">
	<div class="form-group">
		<label for="exampleFormControlInput1">작성자</label>
		<input type="text" class="form-control" id="exampleFormControlInput1" name="writer" value="${userBoard.writer}" disabled>
	</div> 
	<div class="form-group">
		<label for="exampleFormControlInput1">제목</label>
		<input type="text" class="form-control" id="exampleFormControlInput1" name="title" value="${userBoard.title}">
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
		<textarea class="form-control" id="exampleFormControlTextarea1" name="content" rows="10">${userBoard.content}</textarea>          
	</div>
 	<button class="btn btn-primary btn-block">수정</button>
	</div>
</form>

</body>
</html>