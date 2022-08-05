
$(function(){
	console.log(userName)	
	
	let bnoValue = $('input[name="bno"]').val();
	let replyUL = $('.chat');
	let div = $('<div></div>');
	
	function showList(page){
		console.log(userName);
		userReplyService.getList({bno : bnoValue, page : page}, function(list){

			let modDelBtn = `<button type='button' class='btn btn-warning btn-sm' id='modalModBtn' data-toggle='modal' data-target='#modReply'>수정</button>
	   			<button type='button' class='btn btn-danger btn-sm' id='removeBtn'>삭제</button>`
	   		let str = '';
			for(let i=0; i<list.length;i++ ){
				str += `
				<li data-rno='${list[i].rno}' class='list-group-item'>
        			<div>
            			<div class='header'>
                			<strong class='primary-font'>${list[i].replyer}</strong>
                			<small class='pull-right text-muted'>${displayTime(list[i].regDate)}</small>
                			<span class='modBtn${i}'></span>               				
            			</div>
            			<p>${list[i].reply}</p>
        			</div>
    			</li>`	 				
			}
			replyUL.html(str);
			
			for(let i=0; i<list.length;i++ ){
				if(userName==list[i].replyer){
					$('.modBtn'+i).html($(modDelBtn));
				}	
			}
			
		});
	}
showList(1);
	
	
	function displayTime(timeValue){
		let timeArr = JSON.stringify(timeValue).substr(1).split(",");
		return `${timeArr[0]}년 ${timeArr[1]}월 ${timeArr[2]}일`;
	}
	
	//모달
	let modal = $('.modal');
	let modalInputReply = modal.find('input[name="reply"]')
	let modalInputReplyer = modal.find('input[name="replyer"]')
	let modalInputReplyDate = modal.find('input[name="regDate"]')
	
	let modalModBtn = $('#modalModBtn')
	let removeBtn = $('#RemoveBtn')
	let addReplyBtn = $('#addReplyBtn')
	let replyTag = $('.replyTag')
	
	//댓글등록
	addReplyBtn.on('click',function(){
		//alert(bnoValue);
		
		let reply = {
				reply : $('#reply').val(),
				replyer : $('#replyer').val(),
				bno : bnoValue
		}
		userReplyService.add(reply,function(result){
			alert(result)
			replyTag.find('input[name="reply"]').val('');
			showList(1);
		})
	})
	
	//댓글 수정모달
	$('.chat').on('click','#modalModBtn',function(){
		let rno = $(this).closest('li').data('rno');
		userReplyService.get(rno, function(reply){
			modalInputReply.val(reply.reply);
			modalInputReplyer.val(reply.replyer).attr("readonly","readonly");
			modalInputReplyDate.val(displayTime(reply.updateDate))
			modal.data("rno",reply.rno);
			modal.modal("show");
		})
	})
	
	
	modalModBtn.on('click',function(){
		let reply= {
				rno : modal.data('rno'),
				reply : modalInputReply.val()
		}
		userReplyService.update(reply,function(result){
			alert(result);
			modal.modal('hide');
			showList(1);
		})
	})
	
	//삭제
	$('.chat').on('click','#removeBtn',function(){
		let rno = $(this).closest('li').data('rno');
		userReplyService.remove(rno,function(result){
			alert(result);
			showList(1);
		})
	})	
	
	$.getJSON(contextPath+"/userboard/getAttachList", {bno : bnoValue}, function(attachList){
		let str = "";
		
		$(attachList).each(function(i,obj) {
		
		if (!obj.fileType) {//이미지가 아닐경우 attach가 나옴
			let fileCellPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
			// let fileLink = fileCellPath.replace(new RegExp(/\\/g),"/");
		
			str+= "<li class='list-group-item' data-path='"+obj.uploadPath+"'"
		    str+= "data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>"
			str+= "<img src='"+contextPath+"/resources/img/attach.png' style='width:50px;'>"
			str+= "<a href='"+contextPath+"/userDownload?fileName="+fileCellPath+"'>"+obj.fileName+"</a>"
			str+="</li>"
		}else {
			let fileCellPath = encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);
			let originPath = obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
			originPath = originPath.replace(new RegExp(/\\/g),"/");
			
			str+= "<li class='list-group-item' data-path='"+obj.uploadPath+"'"
		    str+= "data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>"
			str += "<img src='"+contextPath+"/userDisplay?fileName="+fileCellPath+"'>"
			str+= "<a href='"+contextPath+"/userDownload?fileName="+originPath+"'>"+obj.fileName+"</a>"
			str += "</li>"
			}
		})
		$(".uploadResult ul").append(str);
	})
})