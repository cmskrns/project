<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<div class="container">
	<div>
		<div>
			<h2>회원 목록</h2>
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
							<option value="I" ${pageMaker.criteria.type eq 'I' ? 'selected' : ''}>아이디</option>
							<option value="A" ${pageMaker.criteria.type eq 'A' ? 'selected' : ''}>주소</option>
							<option value="N" ${pageMaker.criteria.type eq 'N' ? 'selected' : ''}>이름</option>
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
	</div>		
	<div class="row">
		<div class="col-md-12">
			<table class="table table-hover table-bordered">
				<thead class="thead-dark">
					<tr>
						<th scope="col" class="text-center">아이디</th>
						<th scope="col" class="text-center">이름</th>
						<th scope="col" class="text-center">이메일</th>
						<th scope="col" class="text-center">주소</th>
						<th scope="col" class="text-center">전화번호</th>
						<th scope="col" class="text-center">출생일</th>
						<th scope="col" class="text-center">성별</th>
						<th scope="col" class="text-center">회원가입날짜</th>
						<th scope="col" class="text-center">강퇴</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="b">
						<tr>
							<td style="width: 10%" class="text-center">${b.userId}</td>
							<td style="width: 9%" class="text-center">${b.userName}</td>
							<td style="width: 13%" class="text-center">${b.userEmail}</td>
							<td style="width: 12%" class="text-center">${b.addr}</td>
							<td style="width: 12%" class="text-center">${b.phoneNumber}</td>
							<td style="width: 15%" class="text-center">
								<fmt:parseDate var="natalDay" value="${b.natalDay }" pattern="yyyy-MM-dd"/>
								<fmt:formatDate value="${natalDay}" pattern="yyyy년MM월dd일"/>
							</td>
							<td style="width: 6%" class="text-center">${b.gender}</td>
							<td style="width: 15%" class="text-center">
								<fmt:parseDate var="regDate" value="${b.regDate }" pattern="yyyy-MM-dd'T'"/>
								<fmt:formatDate value="${regDate}" pattern="yyyy년MM월dd일"/>
							</td>
							<td style="width: 8%">
								<c:choose>
									<c:when test="${b.userId == 'admin'}">
										<a class="btn btn-secondary">불가</a>
									</c:when>
									<c:otherwise>
										<form id="memberRemove">
											<a class="btn btn-outline-danger">강퇴</a>
											<input type="hidden" class="userId" value="${b.userId}">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
										</form>
									</c:otherwise>
								</c:choose>
							</td>
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
		
		<input type="hidden" name="page" id="page" value="${pageMaker.criteria.page}">
		<input type="hidden" name="type" id="type" value="${pageMaker.criteria.type}">
		<input type="hidden" name="keyword" id="keyword" value="${pageMaker.criteria.keyword}">
	</div> 
</div>
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>
<script>
$(function(){
	let listForm = $('#listForm');
	let memberRemove = $("#memberRemove")
	$('.pagination a').on('click',function(e){
		e.preventDefault();
		$('.listData').find('#page').val($(this).attr('href'));
		
		if (listForm.find('input[name="keyword"]').val()=='') {
			listForm.empty();
		}
		listForm.append($('#page'));
		listForm.submit();
	});
	
	$('#memberRemove a').on('click',function(e){
		e.preventDefault();
		let userId = $(this).next().val();
		memberRemove.attr("method","post");
		memberRemove.attr("action","${contextPath}/member/adminRemove");
		memberRemove.append('<input type="hidden" name="userId" value="'+userId+'">');
		memberRemove.submit();
	})
	
	$('.searchBtn').on('click',function(e){
		if ($('#type').val()=='') {
			alert('검색종류를 선택해주세요!')
			e.preventDefault();
		}
	})
	
})
	
</script>