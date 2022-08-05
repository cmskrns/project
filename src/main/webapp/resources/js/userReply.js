

let userReplyService = (function(){
	
	//댓글 등록
	function add(reply, callback,error){
		
		$.ajax({
			type : 'post',
			url : contextPath+'/userReplies/new',
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
		let bno = param.bno;
		let page = param.page || 1 ;
		
		let url = contextPath + '/userReplies/pages/'+bno+'/'+page;
		let success = function(data){
			if(callback){callback(data)}
		}
		
		$.getJSON(url,success).fail(function(xhr, status, err){
			if(error){error(err)}
		});
	}
	
	//삭제
	function remove(bno, callback,error){
		$.ajax({
			type : 'delete',
			url : contextPath + '/userReplies/'+ bno,
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
	
	//수정
	function update(reply, callback, error){
		$.ajax({
			type : 'put',
			url : contextPath + '/userReplies/'+ reply.rno,
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(reply),
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
		})
	}
	
	function get(rno, callback, error){
		$.get(contextPath+"/userReplies/"+rno+".json", function(result){
			if(callback){
				callback(result);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
	}
	
	return{
		add : add,
		getList : getList,
		remove : remove,
		update : update,
		get : get
	};
}) ();