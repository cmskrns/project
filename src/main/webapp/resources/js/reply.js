

let replyService = (function(){
	
	//댓글 등록
	function add(reply, callback,error){
		
		$.ajax({
			type : 'post',
			url : contextPath+'/replies/new',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			beforeSend : function(xhr){
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			} ,
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
			beforeSend : function(xhr){
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			} ,
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
			url : contextPath + '/replies/'+ reply.rno,
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(reply),
			beforeSend : function(xhr){
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
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
		$.get(contextPath+"/replies/"+rno+".json", function(result){
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