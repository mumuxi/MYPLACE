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
用户详细信息</div>
<div id="sub_nav_right">
<s:a action="Add" namespace="/user/admin">添加用户</s:a>
<s:a action="Update" namespace="/user/admin"><s:param name="id" value="user.id"/>修改用户</s:a>
</div>
</div>
<div id="main">
<table class="detail_table">
<tr><th>用户名</th><td><s:property value="user.name"/></td></tr>
<tr><th>昵称</th><td><s:property value="user.nickname"/></td></tr>
<tr><th>头像</th><td><img src="<%=request.getContextPath() %><s:property value="user.logo"/>" class="user_logo"/></td></tr>
<tr><th>邮箱</th><td><s:property value="user.email"/></td></tr>
<tr><th>角色</th><td><s:property value="@sus.scrofa.action.CommonAction@MAP_ROLE.get(user.role)"/></td></tr>
<tr><th>性别</th><td><s:property value="@sus.scrofa.action.CommonAction@MAP_GENDER.get(user.gender)"/></td></tr>
<tr><th>生日</th><td><s:property value="@sus.scrofa.action.CommonAction@DATE_FORMAT_1.format(user.birthday)"/></td></tr>
<tr><th>手机</th><td><s:property value="user.telephone"/></td></tr>
<tr><th>QQ</th><td><s:property value="user.qq"/></td></tr>
</table>
</div>
</div>
</div>
<s:include value="../foot.jsp"></s:include>
</body>
</html>