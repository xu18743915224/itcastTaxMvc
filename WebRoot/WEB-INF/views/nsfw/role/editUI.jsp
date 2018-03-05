<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>角色管理</title>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath }nsfw/role_edit.action" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>角色管理</strong>&nbsp;-&nbsp;编辑角色</div></div>
    <div class="tableH2">编辑角色</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">角色名称：</td>
            <td><s:textfield name="role.name" /></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">角色权限：</td>
            <td>
            	<c:forEach items="${privilegeMap}" var="mymap" > 
					<input type="checkbox" name="" value="${mymap.key}" id="privilegeMap"/>${mymap.value}&nbsp;
				</c:forEach>	
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">状态：</td>
            <td><s:radio list="#{'1':'有效','0':'无效'}" name="role.state"/></td>
        </tr>
    </table>
    <input type="hidden" name="role.roleId"  value="${role.roleId}"/>
    <input type="hidden" name="privilegeIds" id="privilegeIds" value="${privilegeIds}"/>

    <div class="tc mt20">
        <input type="submit" class="btnB2" value="保存" onclick="checkboxDel();"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
   
</form>
</body>
</html>
<script>
	show();
	function show(){
		var ids=$("#privilegeIds").val();
		var s=ids.split(',');
		$("#baseInfo input[type='checkbox']").each(function(){
			for(var i = 0;i<s.length;i++){
				if($(this).val()==s[i]){
					$(this).attr("checked",true);
				}
				
			}
   		 });
	}
	function checkboxDel(){
			var id="";
  		$("#baseInfo input[type='checkbox']").each(function(){
  		  if($(this).is(':checked')){
              //选中的值
                 id += $(this).val() + ',';
          }
  		 });
  		$("#privilegeIds").val(id);
    }

</script>




