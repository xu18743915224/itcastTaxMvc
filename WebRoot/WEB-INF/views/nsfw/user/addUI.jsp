<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>用户管理</title>
    <script>
    $(function(){
			
 	});
    //这里使用一个相当帅气的使用方法的 全局变量 	因为使用了这个变量提交表单的时候就不需要再复制下面代码 重写而只需要判断 true和false就可以了
    var vResult=false;							
    //效验帐号唯一 ajax
    function doVerify(){
    	var account=$("#account").val();	//获取帐号
    	if(account!=""){					//判空 如果为空不去效验ajax
    		$.ajax({
    			url:"${basePath}nsfw/user/user_isAccount.action",
    			data:{"user.account":account},
    			type:"post",
    			async:false,
    			success:function(msg){
    				if("true"==msg.msg){
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
    	//全局效验
    	validForm();
    	if(valids==false){
    		return;
    	}
    	//后台帐号效验
    	if(vResult==false){
    		alert("帐号已经存在,请使用其它帐号!!");
    		$("#account").focus();
    		return;
    	}
    	/* alert($("form").serialize());*/
    	document.forms[0].action="${basePath}nsfw/user/user_add.action";
  		document.forms[0].submit(); 	
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
    //回显数据库中角色显示在角色栏中
    function show(){
    	
    }
    </script>
</head>
<body class="rightBody">
<form name="form1" action="" method="post" enctype="multipart/form-data">
	<div class="p_d_1">
	        <div class="p_d_1_1">
	            <div class="content_info">
	            	<div class="c_crumbs"><div><b></b><strong>用户管理</strong>&nbsp;-&nbsp;新增用户</div></div>
	            	<div class="tableH2">新增用户</div>
	            	<table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
	            		<tr>
				            <td class="tdBg" width="200px">所属部门：</td>
				            <td><select name="dept">
					            	<option value="部门A">部门A</option>
					 	 			<option value="部门B">部门B</option>
				            	</select>
				            </td>
				        </tr>
	            		<tr>
				            <td class="tdBg" width="200px">头像：</td>
				            <td>
				                <input type="file" name="headImg" value=""/>
				            </td>
				        </tr>
				        <tr>
				            <td class="tdBg" width="200px">用户名：</td>
				            <td>
				           		<input type="text" id="name" validate="name" name="name" value="" />
				            </td>
				        </tr>
				        <tr>
				            <td class="tdBg" width="200px">帐号：</td>
				            <td>
				           		<input type="text" id="account" name="account" validate="account" value="" onchange="doVerify();"/>
				            </td>
				        </tr>
				        <tr>
				            <td class="tdBg" width="200px">密码：</td>
				            <td>
				           		<input type="password" id="password" validate="passwrod" name="password" value=""/>
				            </td>
				        </tr>
				        <tr>
				            <td class="tdBg" width="200px">性别：</td>
				            <td>
				            	<label><input name="gender" type="radio" value="男" />男 </label> 
								<label><input name="gender" type="radio" value="女" />女 </label> 
								<script>document.getElementsByName("gender")[0].checked="checked";</script>
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
				            <td class="tdBg" width="200px" >电子邮箱：</td>
				            <td>
				           		<input type="text" id="email" name="email" value="" validate="email"/>
				            </td>
				        </tr>
				        <tr>
				            <td class="tdBg" width="200px">手机号：</td>
				            <td>
				           		<input type="text" name="mobile" value=""/>
				        </tr>
				        <tr>
				            <td class="tdBg" width="200px">生日：</td>
				            <td>
				           		<input id="birthday" type="text" name="birthday" value=""
				           		readonly="true" onfocus="WdatePicker({'dateFmt':'yyyy-MM-dd'});"/>
				            </td>
				        </tr>
				        <tr>
				            <td class="tdBg" width="200px">状态：</td>
				            <td>
				            	<label><input name="state" type="radio" value="1" />有效 </label> 
								<label><input name="state" type="radio" value="0" />无效 </label> 
								<script>document.getElementsByName("state")[0].checked="checked";</script>
				            </td>
				        </tr>
				        <tr>
				            <td class="tdBg" width="200px">备注：</td>
				            <td>
				           		<textarea name="memo" style="width:500px;height:60px;"></textarea>
				            </td>
				        </tr>
	            	</table>
            	    <input type="hidden" name="roleIds" id="roleIds" value=""/>
				    <div class="tc mt20">
				        <input type="button" class="btnB2" value="保存" onclick="doSubmit();"/>
				        &nbsp;&nbsp;&nbsp;&nbsp;
				        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
				    </div>
				</div>
			</div>
	</div>
</form>
<%-- <form name="form1" action="" method="post" enctype="multipart/form-data">
	<div class="p_d_1">
	        <div class="p_d_1_1">
	            <div class="content_info">
	            	<div class="c_crumbs"><div><b></b><strong>用户管理</strong>&nbsp;-&nbsp;新增用户</div></div>
	            	<div class="tableH2">新增用户</div>
	            	<table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
	            		<tr>
				            <td class="tdBg" width="200px">所属部门：</td>
				            <td><select name="user.dept">
					            	<option value="部门A">部门A</option>
					 	 			<option value="部门B">部门B</option>
				            	</select>
				            </td>
				        </tr>
	            		<tr>
				            <td class="tdBg" width="200px">头像：</td>
				            <td>
				                <input type="file" name="headImg" value=""/>
				            </td>
				        </tr>
				        <tr>
				            <td class="tdBg" width="200px">用户名：</td>
				            <td>
				           		<input type="text" id="name" validate="name" name="user.name" value="" />
				            </td>
				        </tr>
				        <tr>
				            <td class="tdBg" width="200px">帐号：</td>
				            <td>
				           		<input type="text" id="account" name="user.account" validate="account" value="" onchange="doVerify();"/>
				            </td>
				        </tr>
				        <tr>
				            <td class="tdBg" width="200px">密码：</td>
				            <td>
				           		<input type="password" id="password" validate="passwrod" name="user.password" value=""/>
				            </td>
				        </tr>
				        <tr>
				            <td class="tdBg" width="200px">性别：</td>
				            <td>
				            	<label><input name="user.gender" type="radio" value="男" />男 </label> 
								<label><input name="user.gender" type="radio" value="女" />女 </label> 
								<script>document.getElementsByName("user.gender")[0].checked="checked";</script>
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
				            <td class="tdBg" width="200px" >电子邮箱：</td>
				            <td>
				           		<input type="text" id="email" name="user.email" value="" validate="email"/>
				            </td>
				        </tr>
				        <tr>
				            <td class="tdBg" width="200px">手机号：</td>
				            <td>
				           		<input type="text" name="user.mobile" value=""/>
				        </tr>
				        <tr>
				            <td class="tdBg" width="200px">生日：</td>
				            <td>
				           		<input id="birthday" type="text" name="user.birthday" value=""
				           		readonly="true" onfocus="WdatePicker({'dateFmt':'yyyy-MM-dd'});"/>
				            </td>
				        </tr>
				        <tr>
				            <td class="tdBg" width="200px">状态：</td>
				            <td>
				            	<label><input name="user.state" type="radio" value="1" />有效 </label> 
								<label><input name="user.state" type="radio" value="0" />无效 </label> 
								<script>document.getElementsByName("user.state")[0].checked="checked";</script>
				            </td>
				        </tr>
				        <tr>
				            <td class="tdBg" width="200px">备注：</td>
				            <td>
				           		<textarea name="user.memo" style="width:500px;height:60px;"></textarea>
				            </td>
				        </tr>
	            	</table>
            	    <input type="hidden" name="roleIds" id="roleIds" value=""/>
				    <div class="tc mt20">
				        <input type="button" class="btnB2" value="保存" onclick="doSubmit();"/>
				        &nbsp;&nbsp;&nbsp;&nbsp;
				        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
				    </div>
				</div>
			</div>
	</div>
</form> --%>
</body>
</html>