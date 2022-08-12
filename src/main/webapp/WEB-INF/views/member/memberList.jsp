<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<div class="container">
	<div>
		<div>
			<h2>회원 목록</h2>
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
										<input type="hidden" name="userId" value="${b.userId}">
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
</div>
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>
<script>
$(function(){
	let memberRemove = $("#memberRemove")
	$('#memberRemove a').on('click',function(e){
		memberRemove.attr("method","post");
		memberRemove.attr("action","${contextPath}/member/memberRemove");
		memberRemove.submit();
	})
	
})
	
</script>