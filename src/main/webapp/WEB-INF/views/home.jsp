<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>
<div class="jumbotron text-center">
	<h1>Gourmet in the Corner of the Room</h1>
	<p>방구석 미식가들을 위한 커뮤니티</p>
</div>

<div class="container-fluid bg-3 text-center">    
  <h3>오늘의 음식</h3><br>
  <div class="row">
    <div class="col-sm-3">
      <p>라면</p>
      <img src="${contextPath}/resources/img/라면.jpg" class="img-responsive" style="width:100%" alt="Image">
    </div>
    <div class="col-sm-3"> 
      <p>비빔밥</p>
      <img src="${contextPath}/resources/img/비빔밥.jpg" class="img-responsive" style="width:100%" alt="Image">
    </div>
    <div class="col-sm-3"> 
      <p>삼겹살</p>
      <img src="${contextPath}/resources/img/삼겹살.jpg" class="img-responsive" style="width:100%" alt="Image">
    </div>
    <div class="col-sm-3">
      <p>피자</p>
      <img src="${contextPath}/resources/img/피자.jpg" class="img-responsive" style="width:100%" alt="Image">
    </div>
  </div>
</div><br>

<div class="container-fluid bg-3 text-center">    
  <div class="row">
    <div class="col-sm-3">
      <p>Some text..</p>
      <img src="" class="img-responsive" style="width:100%" alt="Image">
    </div>
    <div class="col-sm-3"> 
      <p>Some text..</p>
      <img src="" class="img-responsive" style="width:100%" alt="Image">
    </div>
    <div class="col-sm-3"> 
      <p>Some text..</p>
      <img src="" class="img-responsive" style="width:100%" alt="Image">
    </div>
    <div class="col-sm-3">
      <p>Some text..</p>
      <img src="" class="img-responsive" style="width:100%" alt="Image">
    </div>
  </div>
</div><br><br>

<%@ include file="layout/footer.jsp" %>
</body>
</html>