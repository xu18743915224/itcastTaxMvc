

	//效验标签
	var valids=true;
	function validForm(){
		//效验邮箱
		$("input[validate='email']").each(function() {
			 var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			 if($(this).val()==""){
				 	  
			 }else{
				 if (filter.test($(this).val())){
					valids=true;
				 }else{
					alert('您的电子邮件格式不正确');
					valids=false;
				 }
			 }	 
		});
		if(valids==true){
			//效验帐号
			$("input[validate='account']").each(function() {
				if($(this).val().trim()==""){
					 alert('帐号不能为空!');
					 valids=false;
				}else{
					valids=true;
				}
			});
		}		
		if(valids==true){
			//效验密码
			$("input[validate='passwrod']").each(function() {
				if($(this).val().trim()==""){
					 alert('密码不能为空!');
					 valids=false;
				}else{
					valids=true;
				}
			});
		}
		if(valids==true){
			//效验用户名
			$("input[validate='name']").each(function() {
				if($(this).val().trim()==""){
					 alert('用户名不能为空!');
					 valids=false;
				}else{
					valids=true;
				}
			});
		}	
	}























