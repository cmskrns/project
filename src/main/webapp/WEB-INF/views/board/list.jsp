<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<div class="container">
	<div class="d-flex justify-content-between">
		<div>
			<h2>${category}게시판</h2>
			<p>칠곡 안에 한식을 배달해주는 음식점들을 모아놨습니다</p>
		</div>
		<div>
			<a href="${contextPath}/board/register" class="d-flex justify-content-end">작성</a>
		</div>
	</div>
	 <table class="table table-hover table-bordered">
		<thead class="thead-dark">
			<tr>
				<th scope="col" class="text-center">번호</th>
				<th scope="col" class="text-center">제목</th>
				<th scope="col" class="text-center">주소</th>
				<th scope="col" class="text-center">글쓴이</th>
				<th scope="col" class="text-center">작성일</th>
				<th scope="col" class="text-center">조회수</th>
			</tr>
		</thead>
		<c:forEach items="${list}" var="b">
		<tbody>
			<tr>
				<td style="width: 8%" class="text-center">${b.fno}</td>
				<td style="width: 37%">
					
					<a class="text-reset get" href="${b.fno}">
						${b.rtName}
					</a>
				</td>
				<td style="width: 15%" class="text-center">${b.addr}</td>
				<td style="width: 10%" class="text-center">${b.writer}</td>
				<td style="width: 20%" class="text-center">
					<fmt:parseDate var="regDate" value="${b.regDate }" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
					<fmt:formatDate value="${regDate}" pattern="yyyy년MM월dd일"/>
				</td>
				<td style="width: 10%" class="text-center"></td>
			</tr>
	
		</tbody>
		</c:forEach>
	</table>
		<div class="pagination" style="margin-bottom: 20px">
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
	<form id="listForm">
		<div>
			<select name="type">
				<option value="">====</option>
				<option value="R" ${pageMaker.criteria.type eq 'R' ? 'selected' : ''}>제목</option>
				<option value="A" ${pageMaker.criteria.type eq 'A' ? 'selected' : ''}>주소</option>
			</select>	
			<input type="text" name="keyword" value="${pageMaker.criteria.keyword}">
			<button>검색</button>
		</div>
	</form>
	<div class="listData">
		<input type="hidden" name="fno" id="fno" value="">
		<input type="hidden" name="page" id="page" value="${pageMaker.criteria.page}">
		<input type="hidden" name="type" id="type" value="${pageMaker.criteria.type}">
		<input type="hidden" name="keyword" id="keyword" value="${pageMaker.criteria.keyword}">
	</div>
 </div>
 

<%@ include file="/WEB-INF/views/layout/footer.jsp" %>
<script>
$(function() {
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
	
	$('.get').on('click',function(e){
		e.preventDefault();
		let fno = $(this).attr('href');
		$('#fno').val(fno);
		listForm.append($('#fno'));
		listForm.append($('#page'));
		listForm.attr("action","${contextPath}/board/get");
		listForm.submit();
	}); 
	
	$('.searchBtn').on('click',function(e){
		e.preventDefault();
		listForm.attr("action","${contextPath }/board/list/${category}");
		listForm.submit();
	})
	
})
</script>