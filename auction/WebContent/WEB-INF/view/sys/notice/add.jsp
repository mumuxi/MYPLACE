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
<li><s:a action="List" namespace="/notice/admin">新闻公告列表</s:a></li>
<li><s:a action="Add" namespace="/notice/admin">添加新闻公告</s:a></li>
</ul>
</div>
<div id="right_content">
<div id="sub_nav">
<div id="sub_nav_left">您现在的位置：
<s:a action="Index" namespace="/user/admin">首页</s:a>&nbsp;&#187;&nbsp;
<s:a action="List" namespace="/notice/admin">新闻公告管理</s:a>&nbsp;&#187;&nbsp;
添加新闻公告</div>
</div>
<div id="main">
<s:form action="ProcessAdd" method="post" namespace="/notice/admin" enctype="multipart/form-data">
<table class="detail_table">
<tr><th>标题</th><td><s:textfield name="notice.title"/></td></tr>
<tr><th>消息来源</th><td><s:textfield name="notice.source"/></td></tr>
<tr><th>显示状态</th><td><s:radio name="notice.status" list="@sus.scrofa.action.CommonAction@MAP_NOTICE_STATUS" 
listKey="key" listValue="value" value="1"/></td></tr>
<tr><th>置顶级别</th><td><s:radio name="notice.topLevel" list="@sus.scrofa.action.CommonAction@MAP_NOTICE_TOP" 
listKey="key" listValue="value" value="0"/></td></tr>
<tr><th>具体内容</th><td><s:textarea name="notice.content"/></td></tr>
<tr><td class="center_td" colspan="2"><s:submit value="添加"></s:submit></td></tr>
</table>
</s:form>
</div>
</div>
</div>
<s:include value="../foot.jsp"></s:include>
</body>
</html>