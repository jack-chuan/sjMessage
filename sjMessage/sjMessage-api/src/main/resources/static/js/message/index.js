$(document).ready(function() {
	queryCount();
	queryUser();
	setInterval(function(){
		queryCount();
		 }, 1000*60*5);
	$("#outbox").click(function(){
		location.href = getRootPath()+"/pageJump/toListPage?iframe=toOutboxPage";
	});
	$("#draftbox").click(function(){
		location.href = getRootPath()+"/pageJump/toListPage?iframe=toDraftboxPage";
	});
	$("#dustbin").click(function(){
		location.href = getRootPath()+"/pageJump/toListPage?iframe=toDustbinPage";
	});
	$("#inbox").click(function(){
		location.href = getRootPath()+"/pageJump/toListPage?iframe=receiveMsg";
	});
})

function queryCount(){
	$.ajax({
		url:getRootPath()+"/ruser/index",
		type:"post",
		success:function (data){
			var unReadCount = data[0];
			var readCount = data[1];
			//var count = data[2];
			//$("#count").html(count);
			$("#unReadCount").html(unReadCount);
			$("#readCount").html(readCount);
		}
	})
}

function quit(){
	  if (confirm("您确定要退出消息管理系统吗？")) {
			$.ajax({
				url:getRootPath()+"/ruser/quit",
				type:"post",
				success:function (data){
					if(data){
						location.href = getRootPath()+"/login";
					}
				}
			})
		  } 

}

function index(){
	location.href = getRootPath()+"/index";
}

function queryUser(){
	$.ajax({
		url:getRootPath()+"/ruser/queryUser",
		type:"post",
		dataType: "json",
		success:function (data){
			if(data!=null){
				var username = data[0];
				var deptname = data[1];
				$("#username").text(data[0]);
				$("#deptname").text(data[1]);
			}
		}
	})
}
function writeMessage(){
	location.href = getRootPath()+"/pageJump/toListPage?iframe=toWriteMessagePage";
}
function unReadMessage(){
	location.href = getRootPath()+"/pageJump/toListPage?iframe=receiveMsg&isRead=0";
}
function ReadMessage(){
	location.href = getRootPath()+"/pageJump/toListPage?iframe=receiveMsg&isRead=1";
}
