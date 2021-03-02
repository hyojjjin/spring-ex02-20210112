// console.log("reply module...");
//${root}
//context root 확인 : 폴더 오른쪽 키 > properties > web project settings 에서 확인

var replyService = (function() {

	function add(reply, callback, error) {
		// console.log("add1 method");
		console.log(reply);
		
		$.ajax({
			type: "post",
		//	url : appRoot + "replies/new" //내 appRoot는 /
			url: "/replies/new",  // context root로 변경
			data: JSON.stringify(reply),     // form data를 json으로 변환
			contentType: "application/json; charset=utf-8",
			success: function(result, stauts, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error: function(xhr, status, er) {
				if (error) {
					error(er);
				}
				
			}
		});
	}

	function getList(param, callback, error) {
		var bno = param.bno;
		var page = param.page || 1;
		//	|| 둘중 하나는 true. 앞에 값이 undefined이면 1이 입력됨.
		// || if(page == undefined) {
		//	page = 1; }
		
		//javascript
		//boolean false : 0, "", null, undefined
		
		$.getJSON("/replies/pages/"+ bno +"/"+ page, function(data){
			if (callback) {
				callback(data);
			}
		}).fail(function(xhr, status, err){
			if (error) {
				error();
				}
		});
	
	}
	
	function remove(rno, callback) {
		$.ajax({
			type:'delete',
			url:'/replies/' + rno,
			success: function (result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error: function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}
	
	function update(reply, callback, error) {
		$.ajax({
			type: 'put',
			url: '/replies/' + reply.rno,
			data: JSON.stringify(reply), //자바 스크립트를 json으로 바꿔주는 메소드
			contentType: 'application/json; charset=utf-8',
			success: function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error: function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}		
	
	function get(rno, callback, error) {
		$.get('/replies/' + rno, function(data) {
			if (callback) {
				callback(data);
			}
		}).fail(function() {
			if (error) {
				error();
			}
		});
	}
	
	
	return {
//		name:"AAAA",
		add: add,	
		getList: getList,
		remove: remove,
		update: update,
		get: get
	};
})();