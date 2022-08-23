<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>
<div class="jumbotron text-center">
	<h1>Gourmet in the Corner of the Room</h1>
	<p>방구석 미식가들을 위한 커뮤니티</p>
</div>
<div class="container">
	<div class="container-fluid bg-3 text-center">    
		<h3>오늘의 음식</h3><br>
		<div class="row todayMenu" ></div>
	</div><br><hr><br>
	<div class="container-fluid bg-3 text-center">
		<h3>인기게시글</h3><br>  
		<div class="row">
			<div class="col-sm-6">
				<table class="table table-striped">
					<thead class="thead-dark">
						<tr>
							<th scope="col" class="text-center">카테고리</th>
							<th scope="col" class="text-center">제목</th>
							<th scope="col" class="text-center">조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${board}" var="b">
							<tr>
								<td style="width: 10%" class="text-center">
									<c:if test="${b.category eq 'K-Food'}">
										한식
									</c:if>
									<c:if test="${b.category eq 'C-Food'}">
										중식
									</c:if>
									<c:if test="${b.category eq 'J-Food'}">
										일식
									</c:if>
									<c:if test="${b.category eq 'W-Food'}">
										양식
									</c:if>
									<c:if test="${b.category eq 'FD-Food'}">
										분식
									</c:if>
								</td>
								<td style="width: 30%" class="text-center">
									<a class="text-reset" href="${contextPath}/board/get?fno=${b.fno}">
										${b.rtName}[${b.replyCnt}]
									</a>
								</td>
								<td style="width: 10%" class="text-center">${b.viewCount}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		    </div>
		    <div class="col-sm-6"> 
		    	<table class="table table-striped">
					<thead class="thead-dark">
						<tr>
							<th scope="col" class="text-center">카테고리</th>
							<th scope="col" class="text-center">제목</th>
							<th scope="col" class="text-center">조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${userBoard}" var="u">
							<tr>
								<td style="width: 10%" class="text-center">
									<c:if test="${u.category eq 'Free'}">
										자유게시판
									</c:if>
									<c:if test="${u.category eq 'Tip'}">
										팁게시판
									</c:if>
									<c:if test="${u.category eq 'Recommend'}">
										추천합니다
									</c:if>
								</td>
								<td style="width: 30%" class="text-center">
									<a class="text-reset" href="${contextPath}/userboard/get?bno=${u.bno}">
										${u.title}[${u.replyCnt}]
									</a>
								</td>
								<td style="width: 10%" class="text-center">${u.viewCount}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		    </div>
		</div>
	</div>
</div><br>
<%@ include file="layout/footer.jsp" %>
<script>
$(function(){
	let menu = [
		 '라면.jpg', 
		 '비빔밥.jpg', 
		 '삼겹살.jpg', 
		 '피자.jpg',
		 '떡볶이.jpg',
		 '막창.jpg'
	 ];
	 
	menu.sort(() => Math.random() - 0.5)
	 
	let menuDiv = ""; 
	for(let i=0; i<4 ; i++){
		menuDiv +='<div class="col-sm-3">' 
		menuDiv +='<p>'+menu[i].slice(0,menu[i].length-4)+'</p>'
		menuDiv += '<img src="${contextPath}/resources/img/'+ menu[i] +'"class="img-responsive" style="width: 180px; height: 140px;" alt="Image">'
		menuDiv += '</div>'
	}
 	$('.todayMenu').html(menuDiv);
 	
})

</script>