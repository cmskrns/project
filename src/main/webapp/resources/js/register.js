$(function(){
	let contextPath = '${pageContext.request.contextPath}'
	let regex = new RegExp("(.*?)\.(exe|sh|js|alz)$");
	let maxSize = 5242880;
	
	function checkExtension(fileName, fileSize){
		if(fileSize >= maxSize){
			alert("파일사이즈가 초과하였습니다");
			return false;
		}
		if(regex.test(fileName)){
			alert("허용하지 않은 확장자입니다")
			return false;
		}
		return true;
	}
	
	$('input[type="file"]').change(function(){
		let formData = new FormData();
		let inputFile = $('input[name="uploadFile"]');
		let files = inputFile[0].files;
		
		for(let f of files){
			if(!checkExtension(f.name, f.size)){ return false;}
			formData.append("uploadFile", f)
		}
		$.ajax({
			url : contextPath+"/uploadAjaxAction",
			type : 'POST',
			processData : false,
			contentType : false,
			data : formData,
			success : function(result){
				showUploadResult(result);
			}
		})
	})
})