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
	
	let uploadResult = $('.uploadResult ul')
	function showUploadResult(uploadResultArr){
		if(!uploadResultArr || uploadResultArr.length == 0) {return;}
		
		let st = "";
	}
})