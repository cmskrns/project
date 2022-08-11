<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<div class="container" style="text-align: center; padding-top: 20px">
  <h2>게시글 작성</h2>
</div>
<form action="register" method="post" id="registerForm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	<div class="container">
		<div class="form-group">
			<label for="exampleFormControlInput1">작성자</label>
			<input type="text" class="form-control" id="exampleFormControlInput1" name="writer" value="관리자" placeholder="관리자" readonly="readonly">
		</div> 
		<div class="form-group">
			<label for="exampleFormControlInput1">제목</label>
			<input type="text" class="form-control" id="exampleFormControlInput1" name="rtName">
		</div>
		<div class="form-group">
			<label for="exampleFormControlInput1">업종 : </label>
			<div class="form-check-inline">
	      		<label class="form-check-label" for="radio1">
	        		<input type="radio" class="form-check-input" id="radio1" name="category"  value="K-Food" checked>K-Food
	       		</label>
	    	</div>
	    	<div class="form-check-inline">
	      		<label class="form-check-label" for="radio2">
	        		<input type="radio" class="form-check-input" id="radio2" name="category" value="C-Food" >C-Food
	      		</label>
	    	</div>
	    	<div class="form-check-inline">
	      		<label class="form-check-label" for="radio3">
	       			<input type="radio" class="form-check-input" id="radio3" name="category" value="J-Food" >J-Food
	      		</label>
	    	</div>
		</div>
		<div class="form-group">
			<label for="exampleFormControlInput1">주소</label>
			<input type="text" class="form-control" id="exampleFormControlInput1" name="addr">
		</div>
		<div class="form-group">
			<label for="exampleFormControlInput1">평균가격</label>
			<input type="text" class="form-control" id="exampleFormControlInput1" name="avgPrice" >
		</div>
		<div class="form-group">
			<label for="exampleFormControlInput1">평균 배달시간</label>
			<input type="text" class="form-control" id="exampleFormControlInput1" name="avgDelivery" >
		</div>
		<div class="form-group">
			<label for="exampleFormControlTextarea1">내용</label>
			<textarea class="form-control" id="exampleFormControlTextarea1" name="description" rows="10"></textarea>          
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
	 	<button class="btn btn-primary btn-block">등록</button>
	</div>
</form>

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
		str += "<img src='${contextPath}/display?fileName="+fileCellPath+"'>"+obj.fileName
		str+= "<div class='d-flex justify-content-end'><span data-file='"+fileCellPath+"' data-type='image' class='btn btn-primary'> 삭제 </span></div>"
		str += "</li>"
		
	})
	uploadResult.append(str);
}


$(function() {
	let form = $('#registerForm');
	let submitBtn = $('#registerForm button')
	
	//register작동
	submitBtn.on('click',function(e){
		e.preventDefault();
		let str = "";
		$('.uploadResult li').each(function(i,obj){
			let jobj = $(obj);
			console.log(jobj.data('filename'));
			
			str+="<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data('filename')+"'>"
			str+="<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data('uuid')+"'>"
			str+="<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data('path')+"'>"
			str+="<input type='hidden' name='attachList["+i+"].fileType' value='"+jobj.data('type')+"'>"
		})
		form.append(str).submit();
	})
	
	//업로드
	$('input[type="file"]').change(function() {
		let formData = new FormData();
		let inputFile = $('input[name="uploadFile"]');
		let files = inputFile[0].files;
		
		for(let f of files){
			if(!checkExtension(f.name, f.size)) return false; // 적합성검사
			formData.append("uploadFile", f)
		}
		$.ajax({
			url :contextPath + "/uploadAjaxAction",
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