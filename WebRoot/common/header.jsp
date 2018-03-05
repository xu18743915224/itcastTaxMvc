<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>				<!-- jstl  c标签库 -->
<%
    pageContext.setAttribute("basePath", request.getContextPath()+"/") ;
%>
    
    <link href="${basePath}css/skin1.css" rel="stylesheet" type="text/css" />
    <!-- 引入的jquery插件的js -->
    <script type="text/javascript" src="${basePath}js/jquery/jquery-1.10.2.min.js"></script>
    <!-- 引入的jquery旗下的效验提交表单的插件 需要和jquery插件一起使用 1.6版本 -->
    <script type="text/javascript" src="${basePath}js/jquery/validateMine.js"></script>
    <!-- 为了配合效验给的样式  红色 -->
    <style>.error{color:red;}</style>
    <!-- 引入的时间显示的插件js -->
    <script type="text/javascript" src="${basePath}js/datepicker/WdatePicker.js"></script>
    
    