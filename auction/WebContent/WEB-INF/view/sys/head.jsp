<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<!-- 左侧一张后台LOGO，右侧一条导航 -->
<div id="head">
<div id="logo">
</div>
<p>欢迎您，<s:property value="#session['_user_name']"/>！[<s:a action="Logout" namespace="/user/admin">安全退出</s:a>]&nbsp;[<s:a action="Index" namespace="/" target="_blank">前台首页</s:a>]</p>
<div id="nav">
<ul>
<li><s:a action="Index" namespace="/user/admin">首页</s:a></li>
<li><s:a action="List" namespace="/user/admin">用户管理</s:a></li>
<li><s:a action="List" namespace="/product/admin">商品管理</s:a></li>
<li><s:a action="List" namespace="/deal/admin">订单管理</s:a></li>
<li><s:a href="#">玩家晒图</s:a></li>
<li><s:a action="List" namespace="/notice/admin">新闻公告</s:a></li>
<li><s:a href="#">图片广告</s:a></li>
<li><s:a action="AdminLogout" namespace="/user">安全退出</s:a></li>
</ul>
</div>
</div>
</body>
</html>