<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网上拍卖后台管理系统</title>
<link rel=stylesheet type=text/css href="<%=request.getContextPath() %>/css/sys.css"/>
</head>
<body>
<s:include value="../head.jsp"></s:include>
<div id="content">
<div id="left_bar" style="display: none;">
<ul>
<li><s:a action="List" namespace="/user/admin">用户列表</s:a></li>
<li><s:a action="Add" namespace="/user/admin">添加用户</s:a></li>
</ul>
</div>
<div id="right_content">
<div id="sub_nav">
<div id="sub_nav_left">您现在的位置：
<s:a action="Index" namespace="/user/admin">首页</s:a>&nbsp;&#187;&nbsp;
<s:a action="List" namespace="/user/admin">用户管理</s:a>&nbsp;&#187;&nbsp;
修改用户</div>
</div>
<div id="main">
<s:form action="ProcessUpdate" method="post" namespace="/user/admin" enctype="multipart/form-data">
<table class="detail_table">
<tr><th>用户名</th><td><s:textfield name="user.name" value="%{user.name}"/></td></tr>
<tr><th>密码</th><td><s:password name="user.password" value="%{user.password}"/></td></tr>
<tr><th>昵称</th><td><s:textfield name="user.nickname" value="%{user.nickname}"/></td></tr>
<tr><th>头像</th><td><img src="<%=request.getContextPath() %><s:property value="user.logo"/>" class="user_logo" onclick="document.getElementById('upfile').click();"/><s:file name="image" id="upfile" style="display: none;"/></td></tr>
<tr><th>邮箱</th><td><s:textfield name="user.email" value="%{user.email}"/></td></tr>
<tr><th>角色</th><td><s:radio name="user.role" list="@sus.scrofa.action.CommonAction@MAP_ROLE" listKey="key" listValue="value" value="user.role"></s:radio></td></tr>
<tr><th>性别</th><td><s:radio name="user.gender" list="@sus.scrofa.action.CommonAction@MAP_GENDER" listKey="key" listValue="value" value="user.gender"></s:radio></td></tr>
<tr><th>生日</th><td>
<s:select name="birthday.year" list="@sus.scrofa.action.CommonAction@YEAR_LIST" value="user.birthday.year + 1900"></s:select>年
<s:select name="birthday.month" list="@sus.scrofa.action.CommonAction@MONTH_LIST"  value="user.birthday.month + 1"></s:select>月
<s:select name="birthday.date" list="@sus.scrofa.action.CommonAction@DAY_LIST"  value="user.birthday.date"></s:select>日</td></tr>
<tr><th>手机</th><td><s:textfield name="user.telephone" value="%{user.telephone}"/></td></tr>
<tr><th>QQ</th><td><s:textfield name="user.qq" value="%{user.qq}"/></td></tr>
<tr><td class="center_td" colspan="2"><s:submit value="修改"></s:submit></td></tr>
</table>
<s:hidden name="id" value="%{user.id}" />
</s:form>
</div>
</div>
</div>
<s:include value="../foot.jsp"></s:include>
</body>
</html>