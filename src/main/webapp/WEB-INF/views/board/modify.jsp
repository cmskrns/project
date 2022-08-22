<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="container" style="text-align: center; padding-top: 20px">
  <h2>게시글 수정</h2>
</div>
<form:form action="modify" method="post" id="modifyForm" modelAttribute="board">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	<div class="container">
		<input type="hidden" name="fno" value="${board.fno}">
		<input type="hidden" name="writer" value="${board.writer}">
		<div class="form-group">
			<label for="exampleFormControlInput1">작성자</label>
			<input type="text" class="form-control" id="exampleFormControlInput1" name="writer" value="${board.writer}" disabled>
		</div> 
		<div class="form-group">
			<label for="rtName">제목</label>
			<form:input type="text" class="form-control" path="rtName" value="${board.rtName}"/>
			<form:errors path="rtName"/>
		</div>
		<div class="form-group">
			<label for="exampleFormControlInput1">업종 : </label>
			<div class="form-check-inline">
	      		<label class="form-check-label" for="radio1">
	        		<input type="radio" class="form-check-input" id="radio1" name="category"  value="K-Food" checked>한식
	       		</label>
	    	</div>
	    	<div class="form-check-inline">
	      		<label class="form-check-label" for="radio2">
	        		<input type="radio" class="form-check-input" id="radio2" name="category" value="C-Food" >중식
	      		</label>
	    	</div>
	    	<div class="form-check-inline">
	      		<label class="form-check-label" for="radio3">
	       			<input type="radio" class="form-check-input" id="radio3" name="category" value="J-Food" >일식
	      		</label>
	    	</div>
	    	<div class="form-check-inline">
	      		<label class="form-check-label" for="radio3">
	       			<input type="radio" class="form-check-input" id="radio3" name="category" value="W-Food" >양식
	      		</label>
	    	</div>
	    	<div class="form-check-inline">
	      		<label class="form-check-label" for="radio3">
	       			<input type="radio" class="form-check-input" id="radio3" name="category" value="FD-Food" >분식
	      		</label>
	    	</div>
		</div>
		<div class="form-group">
			<label for="addr">주소</label>
			<form:input type="text" class="form-control" path="addr" value="${board.addr}"/>
			<form:errors path="addr"/>
		</div>
		<div class="form-group">
			<label for="avgPrice">평균가격</label>
			<form:input type="text" class="form-control" path="avgPrice" value="${board.avgPrice}"/>
			<form:errors path="avgPrice"/>
		</div>
		<div class="form-group">
			<label for="avgDelivery">평균 배달시간</label>
			<form:input type="text" class="form-control" path="avgDelivery" value="${board.avgDelivery}"/>
			<form:errors path="avgDelivery"/>
		</div>
		<div class="form-group">
			<label for="description">내용</label>
			<form:textarea class="form-control" path="description" rows="10" value = '<c:out value="${board.description}"/>' />
			<form:errors path="description"/>
		</div>
		<div class="row my-3">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-header">
						<h4>파일첨부</h4>
					</div>
					<div class="card-body">
						<div class="uploadDiv">
							<input type="file" name="uploadFile" multiple="multiple">
						</div>
						<div class="uploadResult">
							<ul class="list-group"></ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	 	<button class="btn btn-primary btn-block">수정</button>
	</div>
</form:form>

<script>
let regex = new RegExp("(.*?)\.(exe|sh|js|alz|txt)$");
let maxSize = 5242880;

function checkExtension(fileName, fileSize) {
	if (fileSize >= maxSize) {
		alert("파일 크기가 초과하여 업로드가 불가능합니다")
		return false;
	}
	if (regex.test(fileName)) {
		alert("해당 파일의 종류는 업로드 할 수 없습니다")
		return false;
	}
	return true;
}

//업로드
let uploadResult = $('.uploadResult ul')
function showUploadResult(uploadResultArr) {
	if (!uploadResultArr || uploadResultArr.length == 0) {return;}
	let str = "";
	$(uploadResultArr).each(function(i,obj) {
	
		let fileCellPath = encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);
		let originPath = obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
		originPath = originPath.replace(new RegExp(/\\/g),"/");
		
		str+= "<li class='list-group-item' data-path='"+obj.uploadPath+"'"
	    str+= "data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>"
		str += "<img src='"+contextPath+"/display?fileName="+fileCellPath+"'>"+obj.fileName
		str+= "<div class='d-flex justify-content-end'><span data-file='"+fileCellPath+"' data-type='image' class='btn btn-primary'> 삭제 </span></div>"
		str += "</li>"
		
	})
	$(".uploadResult ul").append(str);
}

$(function(){
	let fnoValue = "${board.fno}"
	$.getJSON(contextPath+"/board/getAttachList",{fno : fnoValue}, function(attachList){
		showUploadResult(attachList);
	})
	
	$('.uploadResult ul').on('click','span',function(){
		let targetLi = $(this).closest('li');
		targetLi.remove();
	})
	
	$('input[type="file"]').change(function(){
		let formData = new FormData();
		let inputFile = $('input[name="uploadFile"]');
		let files = inputFile[0].files;
		
		for(let f of files){
			if (!checkExtension(f.name, f.siez)) return false;
			formData.append("uploadFile", f)
		}
		$.ajax({
			url : contextPath + "/uploadAjaxAction",
			type : 'POST',
			processData : false,
			contentType : false,
			data : formData,
			dataType : 'json',
			success : function(result){
				showUploadResult(result);
			}
		})
	})
	
	let modifyForm = $('#modifyForm');
	let modifyBtn = $('#modifyForm button');
	
	modifyBtn.on('click',function(e){
		e.preventDefault();
		let str = "";
		$('.uploadResult ul li').each(function(i,obj){
			let jobj = $(obj);
			str+="<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data('filename')+"'>"
			str+="<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data('uuid')+"'>"
			str+="<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data('path')+"'>"
			str+="<input type='hidden' name='attachList["+i+"].fileType' value='"+jobj.data('type')+"'>"
		})
		modifyForm.append(str).submit();
	})
//업로드 파일삭제
$('.uploadResult ul').on('click','span',function(){
	let targetFile = $(this).data('file');
	let type = $(this).data('type');
	let targetLi =$(this).closest('li');
	
	$.ajax({
		url :contextPath + "/deleteFile",
		type : 'POST',
		data : {fileName : targetFile, type : type},
		dataType : 'text',
		success : function(result){
			alert("삭제하였습니다");
			$('input[name="uploadFile"]').val("");
			targetLi.remove();
			
		}
	})
})
})
</script>
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>