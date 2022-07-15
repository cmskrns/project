<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<script src="${contextPath}/resources/reply.js"></script>
<form id="getForm">
	<div class="getData">
		<input type="hidden"name="page" id="page" value="${param.page}">
		<input type="hidden"name="type" id="type" value="${param.type}">
		<input type="hidden"name="keyword" id="keyword" value="${param.keyword}">
	</div>
	<input type="hidden" name="fno" value="${board.fno}">
	<input type="hidden" name="category" value="${board.category}">
	<input type="hidden" name="writer" value="${board.writer}">
	<input type="hidden" name="rtName" value="${board.rtName}">
	<input type="hidden" name="addr" value="${board.addr}">
	<input type="hidden" name="avgPrice" value="${board.avgPrice}">
	<input type="hidden" name="avgDelivery" value="${board.avgDelivery}">
	<input type="hidden" name="description" value="${board.description}">
	<div class="media">
		<div class="media-body">
			<h4>${board.rtName}</h4>
		    <p>업종 : ${board.category} </p>
		    <p>주소 : ${board.addr} </p>
		    <p>평균가격 : ${board.avgPrice}원 </p>
		    <p>평균 배달시간 : ${board.avgDelivery} </p>
		    <p>설명 : ${board.description} </p>
		    <p>작성자 : ${board.writer} </p>
		</div>
	</div> <br>
	<button class="btn btn-outline-primary btn-block modify">수정</button>
	<button class="btn btn-outline-danger btn-block remove">삭제</button>
	<button class="btn btn-outline-secondary btn-block list">목록</button>
	<div class="reply_area">
		<c:if test="${not empty board.replyList}">
			<c:forEach items="${board.replyList}" var="r">
				<div>
					<p>작성자 : ${r.replyer}
					<p>내용 : ${r.reply}
					<p>등록일 : ${r.regDate}
				</div>
			</c:forEach>
		</c:if>
	</div>
	
</form>
<script>
$(function() {
	let getForm = $("#getForm");
	$('#getForm .list').on('click',function(e){
		e.preventDefault();
		let categoryTag = $('input[name="category"]').clone();
		let pageTag = $('input[name="page"]').clone();
		let typeTag = $('input[name="type"]').clone();
		let keywordTag = $('input[name="keyword"]').clone();
		let category = categoryTag.val();

		getForm.empty();
		getForm.append(categoryTag);
		getForm.append(pageTag);
		getForm.append(typeTag);
		getForm.append(keywordTag);
		getForm.attr("action","list/"+category);
		getForm.submit();
	})
	$('#getForm .modify').on('click',function(e){
		e.preventDefault();
		getForm.attr("action","modify");
		getForm.submit();
	}) 
	$('#getForm .remove').on('click',function(e){
		e.preventDefault();
		getForm.attr("method","post");
		getForm.attr("action","remove");
		getForm.submit();
	})
})

</script>
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>

