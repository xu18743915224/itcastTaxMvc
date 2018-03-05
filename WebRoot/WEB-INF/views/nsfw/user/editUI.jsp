<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>用户管理</title>
<script>
    //这里使用一个相当帅气的使用方法的 全局变量 	因为使用了这个变量提交表单的时候就不需要再复制下面代码 重写而只需要判断 true和false就可以了
    var vResult=false;							
    //效验帐号唯一 ajax
    function doVerify(){
    	var account=$("#account").val();	//获取帐号
    	if(account!=""){					//判空 如果为空不去效验ajax
    		$.ajax({
    			url:"${basePath}nsfw/user_verifyAccount.action",
    			data:{"user.account":account,"user.id":"${user.id}"},
    			type:"post",
    			async:false,
    			success:function(msg){
    				if("true"==msg){
    					//帐号已经存在
    					alert("帐号已经存在,请使用其它帐号!!");
    					//定焦在帐号输入框
    					$("#account").focus();
    					vResult=false;
    				}else{
    					vResult=true;
    				}
    			},error:function(){
					alert("error");
				}
    		});
    	}
    	//
    }
    //点击保存提交表单
    function doSubmit(){
    	/* var name=$("#name").val(); if(name==""){ alert("用户名不能为空!!"); $("#name").focus(); return false;}
    	var password=$("#password").val(); if(password==""){ alert("密码不能为空!!"); $("#password").focus(); return false;}
    	doVerify();
    	//alert(vResult);	//调用这个方法的时候 需要更改ajax的async为false同步    默认是true异步 (不改为同步的话 默认异步就无论如何都会执行下面)  
    	if(vResult==true){					//如果为ture说明帐号不同名和数据库 可以使用
    		saveRoleIds(); */
    		document.forms[0].action="${basePath}nsfw/user/user_edit.action";
      		document.forms[0].submit();
    	/* } */  	
    }
  //循环将roleIds保存到数据库
    function saveRoleIds(){
    	var id="";
  		$("#baseInfo input[type='checkbox']").each(function(){
  		   // alert($(this).val());				//循环弹出每一个标签的value值
  		  if($(this).is(':checked')){
              //选中的值
                 id += $(this).val() + ',';
          }
  		 });
  		$("#roleIds").val(id);  alert(id);
    }
  	
    </script>   
</head>
<body class="rightBody">
<form id="form" name="form" action="" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>用户管理</strong>&nbsp;-&nbsp;编辑用户</div></div>
    <div class="tableH2">编辑用户</div>
    
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">所属部门：</td>
            <%-- <td><s:select name="user.dept" list="#{'部门A':'部门A','部门B':'部门B'}/></td> --%>
            <td>
            	<c:if test="${user.dept=='部门A'}">
	            	<select name="user.dept">
		            	<option value="部门A" selected = "selected">部门A</option>
		 	 			<option value="部门B">部门B</option>
	            	</select>
	            </c:if>
	            <c:if test="${user.dept=='部门B'}">
	            	<select name="user.dept">
		            	<option value="部门A">部门A</option>
		 	 			<option value="部门B" selected = "selected">部门B</option>
	            	</select>
	            </c:if>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">头像：</td>
            <td>
                <c:if test="${user.headImg!=null&&user.headImg!=''}">
                    <img src="${basePath}${user.headImg }" width="100" height="100"/>
                    <input type="hidden" name="user.headImg" value="${user.headImg}"/>
                    ${basePath}
                </c:if>
                <c:if test="${user.headImg==null&&user.headImg==''}">
                    <%-- <img src="${basePath}${user.headImg }" width="100" height="100"/> --%>
                </c:if>
                <input type="hidden" name="user.headImg" value="${user.headImg}"/>
                <input type="file" name="headImg"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">用户名：</td>
            <%-- <td><s:textfield name="user.name"/> </td> --%>
            <td>
           		<input type="text" name="user.name" id="name" value="${user.name }"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">帐号：</td>
            <%-- <td><s:textfield name="user.account"/></td> --%>
            <td>
           		<input type="text" name="user.account" id="account" value="${user.account}" onchange="doVerify();"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">密码：</td>
            <%-- <td><s:textfield name="user.password"/></td> --%>
            <td>
           		<input type="text" name="user.password" id="password" value="${user.password }"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">性别：</td>
            <%-- <td><s:radio list="#{'男':'男','女':'女'}" name="user.gender"/></td> --%>
            <td>
            	<c:if test="${user.gender=='男'}">
					<label><input name="user.gender" type="radio" value="男" />男 </label> 
					<label><input name="user.gender" type="radio" value="女" />女 </label> 
					<script>document.getElementsByName("user.gender")[0].checked="checked";</script>				
				</c:if>
            	<c:if test="${user.gender=='女'}">
					<label><input name="user.gender" type="radio" value="男" />男 </label> 
					<label><input name="user.gender" type="radio" value="女" />女 </label> 
					<script>document.getElementsByName("user.gender")[1].checked="checked";</script>				
				</c:if> 
				
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">角色：</td>
            <td>
           		<c:forEach items="${roleList}" var="role" >
            		<input type="checkbox" name="" value="${role.roleId }"/>${role.name}&nbsp;	
            	</c:forEach>
            
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">电子邮箱：</td>
            <%-- <td><s:textfield name="user.email"/></td> --%>
            <td>
           		<input type="text" name="user.email" value="${user.email }"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">手机号：</td>
            <%-- <td><s:textfield name="user.mobile"/></td> --%>
            <td>
           		<input type="text" name="user.mobile" value="${user.mobile}"/>
            </td>
        </tr>        
        <tr>
            <td class="tdBg" width="200px">生日： </td>
            <%-- <td><s:textfield id="birthday" name="user.birthday" /></td> --%>
            <td>
           		<input id="birthday" type="text" name="user.birthday" value="${user.birthday}"
				 readonly="true" onfocus="WdatePicker({'dateFmt':'yyyy-MM-dd'});"/>
            </td>
        </tr>
		<tr>
            <td class="tdBg" width="200px">状态：</td>
            <%-- <td><s:radio list="#{'1':'有效','0':'无效'}" name="user.state" value="1"/></td> --%>
            <td>
            	<c:if test="${user.state=='1'}">
            		<label><input name="user.state" type="radio" value="1" />有效 </label> 
					<label><input name="user.state" type="radio" value="0" />无效 </label> 
					<script>document.getElementsByName("user.state")[0].checked="checked";</script>
            	</c:if>
            	<c:if test="${user.state=='0'}">
            		<label><input name="user.state" type="radio" value="1" />有效 </label> 
					<label><input name="user.state" type="radio" value="0" />无效 </label> 
					<script>document.getElementsByName("user.state")[1].checked="checked";</script>
            	</c:if>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">备注：</td>
            <%-- <td><s:textarea name="user.memo" cols="75" rows="3"/></td> --%>
            <td>
           		<textarea name="user.memo" style="width:500px;height:60px;">${user.memo}</textarea>
            </td>
        </tr>
    </table>
    <input type="hidden" name="roleIds" id="roleIds" value="${roleIds}"/>
    <input type="hidden" name="user.id" value="${user.id}"/>
    <div class="tc mt20">
        <input type="button" class="btnB2" value="保存" onclick="doSubmit();"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
</body>
</html>

<script>
	//页面加载的时候回显checked
	show();
	function show(){
		var roleIds=$("#roleIds").val();
		var s=roleIds.split(',');
		$("#baseInfo input[type='checkbox']").each(function(){
			for(var i=0;i<s.length;i++){
				if($(this).val()==s[i]){
					$(this).attr("checked",true);
	          }	
			}
	  		  
	  	});
	}

</script>






















