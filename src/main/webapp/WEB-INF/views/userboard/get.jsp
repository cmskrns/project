<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<div class="container">
	<form id="getForm">
		<div class="getData">
			<input type="hidden"name="page" id="page" value="${param.page}">
			<input type="hidden"name="type" id="type" value="${param.type}">
			<input type="hidden"name="keyword" id="keyword" value="${param.keyword}">
		</div>
		<div>
			<input type="hidden" name="bno" value="${board.bno}">
			<input type="hidden" name="title" value="${board.title}">
			<input type="hidden" name="category" value="${board.category}">
			<input type="hidden" name="writer" value="${board.writer}">
			<input type="hidden" name="content" value="${board.content}">
		</div>
		<div class="media">
			<div class="media-body">
				<h4>${board.title}</h4>
				<div>
				    <p>카테고리 : ${board.category} </p>
				    <p>작성자 : ${board.writer} </p>
				    <p>내용 : ${board.content} </p>
			    </div>
			</div>
		</div>
		<button class="btn btn-outline-primary btn-block modify">수정</button>
		<button class="btn btn-outline-danger btn-block remove">삭제</button>
		<button class="btn btn-outline-secondary btn-block list">목록</button>
	</form>
</div>

<script>
$(function(){
	let getForm = $("#getForm");
	//목록이동
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
	//게시물수정
	$('#getForm .modify').on('click',function(e){
		e.preventDefault();
		getForm.attr("action","modify");
		getForm.submit();
	})
	$('#getForm .remove').on('click',function(e){
		getForm.attr("method","post");
		getForm.attr("action","remove");
		getForm.submit();
	})
})
</script>
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>