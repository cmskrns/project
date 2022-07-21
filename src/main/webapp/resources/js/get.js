console.log(replyService)

$(function(){
	let fnoValue = $('input[name="fno"]').val();
	let replyUL = $('.chat');
	
	function showList(page){
		replyService.getList({fno : fnoValue, page : page}, function(list){
			
			let str ="";
			for(let reply of list){
				str += `
				<li data-rno='${reply.rno}' class='list-group-item'>
        			<div>
            			<div class='header'>
                			<strong class='primary-font'>${reply.replyer}</strong>
                			<small class='pull-right text-muted'>${displayTime(reply.regDate)}</small>
                			<button type='button' class='btn btn-warning btn-sm' id='modalModBtn' data-toggle='modal' data-target='#modReply'>수정</button>
    						<button type='button' class='btn btn-danger btn-sm' id='removeBtn'>삭제</button>
            			</div>
            			<p>${reply.reply}</p>
        			</div>
    			</li>`
			}
			replyUL.html(str);
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
		//alert(fnoValue);
		
		let reply = {
				reply : $('#reply').val(),
				replyer : $('#replyer').val(),
				fno : fnoValue
		}
		replyService.add(reply,function(result){
			alert(result)
			replyTag.find('input').val('');
			showList(1);
		})
	})
	
	//댓글 수정모달
	$('.chat').on('click','#modalModBtn',function(){
		let rno = $(this).closest('li').data('rno');
		replyService.get(rno, function(reply){
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
		replyService.update(reply,function(result){
			alert(result);
			modal.modal('hide');
			showList(1);
		})
	})
	
	//삭제
	$('.chat').on('click','#removeBtn',function(){
		let rno = $(this).closest('li').data('rno');
		replyService.remove(rno,function(result){
			alert('삭제완료');
			showList(1);
		})
	})	
})