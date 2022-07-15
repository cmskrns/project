

let replyService = (function(){
	
	//댓글 등록
	function add(reply, collback){
		
		$.ajax({
			type : 'post',
			url : '/replies/new',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
			
		});
	}
	
	//목록
	function getList(param, callback, error){
		let fno = param.fno;
		let page = param.page || 1 ;
		
		let url = contextPath + '/replies/pages/'+fno+'/'+page;
		let success = function(data){
			if(callback){callback(data)}
		}
		
		$.getJSON(url,success).fail(function(xhr, status, err){
			if(error){error(err)}
		});
	}
	
	//삭제
	function remove(fno, callback,error){
		$.ajax({
			type : 'delete',
			url : contextPath + '/replies/'+ fno,
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	return{
		add : add,
		getList : getList,
		remove : remove
	};
}) ();