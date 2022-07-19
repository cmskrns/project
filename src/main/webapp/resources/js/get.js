$(function(){
	let fnoValue = $('input[name="fno"]').val();
	let replyUL = $('.chat');
	
	function showList(page){
		replyService.getList({fno : fnoValue, page : page}, function(list){
			
			let str ="";
			for(let reply of list){
				str += `
				<li data-rno='${reply.rno}'>
        			<div>
            			<div class='header'>
                			<strong class='primary-font'>${reply.replyer}</strong>
                			<small class='pull-right text-muted'>${displayTime(reply.regDate)}</small>
            			</div>
            			<p>${reply.reply}</p>
        			</div>
    			</li>`
			}
			replyUL.html(str);
		});
	}
	showList(1);
})