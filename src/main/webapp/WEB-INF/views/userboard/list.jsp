<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<div class="container">
	<div>
		<div>
			<h2>${category}게시판</h2>
			<p>여러분의 다양한 이야기를 들려주세요</p>
		</div>
	</div>
	<!-- 검색창 -->
	<div class="row mb-3">
		<div class="col-md-9">
			<form id="listForm">
				<div class="input-group">
			       	<div class="input-group-prepend">
			           	<select name="type" id="type">
		            		<option value="">검색종류</option>
							<option value="T" ${pageMaker.criteria.type eq 'T' ? 'selected' : ''}>제목</option>
							<option value="W" ${pageMaker.criteria.type eq 'W' ? 'selected' : ''}>작성자</option>
		           		</select>
		        	</div>
		        	<div class="input-group-prepend mx-2">
		        		<input type="text" name="keyword" class="form-control" value="${pageMaker.criteria.keyword}" placeholder="검색어를 입력해 주세요">
		        	</div>
		        	<div class="input-group-append">
		            	<button class="btn btn-success searchBtn">검색</button>
		        	</div>
		    	</div>
			</form>
		</div>
		<div class="col-md-3 text-right">
			<a href="${contextPath}/userboard/register" class="btn btn-primary">작성</a>
		</div>
	</div>
	<!-- 게시물 -->
	<div class="row">
		<div class="col-md-12">
			<table class="table table-hover table-bordered">
				<thead class="thead-dark">
					<tr>
						<th scope="col" class="text-center">번호</th>
						<th scope="col" class="text-center">제목</th>
						<th scope="col" class="text-center">글쓴이</th>
						<th scope="col" class="text-center">작성일</th>
						<th scope="col" class="text-center">조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="b">
						<tr>
							<td style="width: 10%" class="text-center">${b.bno}</td>
							<td style="width: 30%">
								<a class="text-reset get" href="${b.bno}">
									${b.title}
								</a>
							</td>
							<td style="width: 15%" class="text-center">${b.writer}</td>
							<td style="width: 30%" class="text-center">
								<fmt:parseDate var="regDate" value="${b.regDate }" pattern="yyyy-MM-dd'T'"/>
								<fmt:formatDate value="${regDate}" pattern="yyyy년MM월dd일"/>
							</td>
							<td style="width: 15%" class="text-center"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 페이징처리 -->
	<div class="pagination d-flex justify-content-center" style="margin-bottom: 20px">
		<c:if test="${pageMaker.prev }">
			<li class="page-item"><a class="page-link" href="${pageMaker.startPage-1}">이전</a></li>
		</c:if>
		<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage }" var="pageNum">
			<li class="page-item ${param.page == pageNum ? 'active': ' ' }">
				<a class="page-link" href="${pageNum}">${pageNum}</a>
			</li>
		</c:forEach>
		<c:if test="${pageMaker.next }">
			<li class="page-item"><a class="page-link" href="${pageMaker.endPage+1 }">다음</a></li>
		</c:if>
	</div> 
	<div class="listData">
		<input type="hidden" name="bno" id="bno" value="">
		<input type="hidden" name="page" id="page" value="${pageMaker.criteria.page}">
		<input type="hidden" name="type" id="type" value="${pageMaker.criteria.type}">
		<input type="hidden" name="keyword" id="keyword" value="${pageMaker.criteria.keyword}">
	</div>
</div>

<script>
$(function(){
	let listForm = $('#listForm');
	$('.pagination a').on('click',function(e){
		e.preventDefault();
		$('.listData').find('#page').val($(this).attr('href'));
		
		if (listForm.find('input[name="keyword"]').val()=='') {
			listForm.empty();
		}
		listForm.append($('#page'));
		listForm.submit();
	});
	$('.searchBtn').on('click',function(e){
		if ($('#type').val()=='') {
			alert('검색종류를 선택해주세요!')
			e.preventDefault();
		}
	})
	//단일게시물에 정보넘겨주기
	$('.get').on('click',function(e){
		e.preventDefault();
		let bno = $(this).attr('href');
		$('#bno').val(bno);
		listForm.append($('#bno'));
		listForm.append($('#page'));
		listForm.attr("action","${contextPath}/userboard/get");
		listForm.submit();
	}); 
})

</script>
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>