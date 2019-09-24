$(document).ready(function(){
	getPassword();	
})
function hasClass(obj, cls) { 
 return obj.className.match(new RegExp('(\\s|^)' + cls + '(\\s|$)')); 
} 
 
function addClass(obj, cls) { 
 if (!this.hasClass(obj, cls)) obj.className +=  cls; 
} 
 
function removeClass(obj, cls) { 
 if (hasClass(obj, cls)) { 
 var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)'); 
 obj.className = obj.className.replace(reg, ''); 
 } 
}

	$("#jzmm").click(function(){
		var flag = $("#jzmm").attr("class"); 
		if(flag == "click"){
			removeClass(document.getElementById("jzmm"),"click");
			
			//document.getElementById("jzmm").classList.remove("click");
			//document.getElementById("jzmm").className="";
		}else{
			addClass(document.getElementById("jzmm"),"click");
			//document.getElementById("jzmm").classList.add("click");
			//document.getElementById("jzmm").className += " "+"click";
		}
	})

	
/*		document.body.addEventListener('keyup', function (e) {
            console.log(e)
            if (e.keyCode == '13') {
            	js_method();
            }
        });*/
$("body").keydown(function(e){
	var curKey = e.which;
	if(curKey == 13){		
		   $("#login").click();	
	}
});

function js_method(){
	var username = document.getElementById("username");
	var password = document.getElementById("password");
	if(username.value == ""||username.value ==null){
		$("#prompt").text("用户名不能为空");
		return;
	}
	if(password.value ==""||password.value==null){
		$("#prompt").text("密码不能为空");
		return;
	}
	var rememberme;
	var flag = $("#jzmm").attr("class"); 
	if(flag == "click"){
		rememberme = "1";
	}else{
		rememberme = "0";
	}
	$.ajax({
		url:getRootPath()+"/ruser/login",
		type: "POST",
		data:{
			"username":username.value,
			"password":password.value,
			"rememberme" : rememberme
		},
		success:function(data){
			if(data){
				location.href = getRootPath()+"/index";
			}else{
				$("#prompt").text("用户名或密码错误");
			}
		}
	})
}   
function getPassword(){
	$.ajax({
		url:getRootPath()+"/ruser/getPassword",
		type: "POST",
		success:function(data){
			if(data !=null&&data!=""){
				$("#username").val(data.userName);
				$("#password").val(data.password);
				//document.getElementById("jzmm").classList.add("click");
				addClass(document.getElementById("jzmm"),"click");
			}
		}
	})
}
