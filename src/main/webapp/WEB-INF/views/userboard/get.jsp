<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<%-- get.js에 로그인한 사용자 값 전달 --%>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.memberVO" var="vo"/>
	<sec:authentication property="principal.username" var="userName"/>
	<script>let userName = "${userName}"</script>
</sec:authorize>
<sec:authorize access="isAnonymous()">
	<script>let userName = "_anonymous"</script>
</sec:authorize>
<script src="${contextPath}/resources/js/userGet.js"></script>
<div class="container">
	<div class="row my-5">
		<div class="col-lg-12">
			<div class="card">
				<div class="card-header">
					<h4>첨부된 파일</h4>
				</div>
				<div class="card-body">
					<div class="uploadResult">
						<ul class="list-group"></ul>
					</div>
				</div>
			</div>
		</div>
	</div>
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
		<c:if test="${vo.userId eq board.writer}">
			<button class="btn btn-outline-primary btn-block modify">수정</button>
			<button class="btn btn-outline-danger btn-block remove">삭제</button>
		</c:if>
		<button class="btn btn-outline-secondary btn-block list">목록</button>
	</form>
	<!-- 댓글 -->
	<div class="card my-3">
    	<div class="card-header">
			<h4 class="card-title">댓글란</h4>
	    </div>
	    <!-- 로그인시 -->
	    <sec:authorize access="isAuthenticated()">
		    <div class="input-group replyTag">
		    	<div class="input-group-prepend">
		    		<input type="text" class="form-control" name="replyer" id="replyer" value="${userName}" readonly="readonly">
				</div>
				<input type="text" class="form-control" name="reply" id="reply" placeholder="댓글을 입력하세요">
				<input type="hidden" name="regDate" >
				<div class="input-group-append">
			    	<button class="btn btn-success" id="addReplyBtn" type="submit">등록</button>
				</div>
			</div>
		</sec:authorize>
		<!-- 로그인이 안됐을 때 -->
		<sec:authorize access="isAnonymous()">
			<div class="input-group replyTag">
		    	<div class="input-group-prepend">
		    		<input type="text" class="form-control" placeholder="닉네임" disabled="disabled">
				</div>
				<input type="text" class="form-control"  placeholder="댓글 사용시 로그인하셔야 합니다" disabled="disabled">
				<input type="hidden" name="regDate" >
			</div>
		</sec:authorize>
		<!-- 댓글목록 -->
		<div class="card-body">
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-defoult">
						<div class="panel-heading">
							<h4 class="test">댓글</h4>
						</div>
						<div class="panel-body">
							<ul class="chat list-group"></ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- The Modal -->
<div class="modal fade" id="modReply">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">댓글 수정</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                <div class="form-group">
                    <label for="reply">내용입력</label>
                    <input class="form-control" name="reply" id="reply">
                </div>
                <div class="form-group">
                    <label for="replyer">작성자</label>
                    <input class="form-control" name="replyer" id="replyer">
                </div>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="modalModBtn">수정</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
            </div>            
        </div>
    </div>
</div>

<script>
$(function(){
	let getForm = $("#getForm");
	let tagLi = $('.chat');
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
		e.preventDefault();
		if (tagLi.children().length != 0) {
			alert("댓글이 존재합니다")
		}else {
			getForm.append('<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">')
			getForm.attr("method","post");
			getForm.attr("action",contextPath+"/userboard/remove");
			getForm.submit();
			
		}
	})
})
</script>
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>