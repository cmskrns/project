<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<div class="container" style="text-align: center; padding-top: 20px">
  <h2>게시글 수정</h2>
</div>
<form action="modify" method="post">
	<div class="container">
	<input type="hidden" name="fno" value="${board.fno}">
	<input type="hidden" name="writer" value="${board.writer}">
	<div class="form-group">
		<label for="exampleFormControlInput1">작성자</label>
		<input type="text" class="form-control" id="exampleFormControlInput1" name="writer" value="${board.writer}" disabled>
	</div> 
	<div class="form-group">
		<label for="exampleFormControlInput1">제목</label>
		<input type="text" class="form-control" id="exampleFormControlInput1" name="rtName" value="${board.rtName}">
	</div>
	<div class="form-group">
		<label for="exampleFormControlInput1">업종 : </label>
		<div class="form-check-inline">
      		<label class="form-check-label" for="radio1">
        		<input type="radio" class="form-check-input" id="radio1" name="category"  value="K-Food" checked>K-Food
       		</label>
    	</div>
    	<div class="form-check-inline">
      		<label class="form-check-label" for="radio2">
        		<input type="radio" class="form-check-input" id="radio2" name="category" value="C-Food" >C-Food
      		</label>
    	</div>
    	<div class="form-check-inline">
      		<label class="form-check-label" for="radio3">
       			<input type="radio" class="form-check-input" id="radio3" name="category" value="J-Food" >J-Food
      		</label>
    	</div>
	</div>
	<div class="form-group">
		<label for="exampleFormControlInput1">주소</label>
		<input type="text" class="form-control" id="exampleFormControlInput1" name="addr" value="${board.addr}">
	</div>
	<div class="form-group">
		<label for="exampleFormControlInput1">평균가격</label>
		<input type="text" class="form-control" id="exampleFormControlInput1" name="avgPrice" value="${board.avgPrice}">
	</div>
	<div class="form-group">
		<label for="exampleFormControlInput1">평균 배달시간</label>
		<input type="text" class="form-control" id="exampleFormControlInput1" name="avgDelivery" value="${board.avgDelivery}">
	</div>
	<div class="form-group">
		<label for="exampleFormControlTextarea1">내용</label>
		<textarea class="form-control" id="exampleFormControlTextarea1" name="description" rows="10">${board.description}</textarea>          
	</div>
 	<button class="btn btn-primary btn-block">수정</button>
	</div>
</form>

<%@ include file="/WEB-INF/views/layout/footer.jsp" %>