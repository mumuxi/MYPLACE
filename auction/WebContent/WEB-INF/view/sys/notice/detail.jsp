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
新闻公告详细信息</div>
<div id="sub_nav_right">
<s:a action="Add" namespace="/notice/admin">添加新闻公告</s:a>
<s:a action="Update" namespace="/notice/admin"><s:param name="id" value="user_notice.notice.id"/>修改新闻公告</s:a>
</div>
</div>
<div id="main">
<table class="detail_table">
<tr><th>标题</th><td><s:property value="user_notice.notice.title"/></td></tr>
<tr><th>发布者</th><td><s:a action="Detail" namespace="/user"><s:param name="id" value="user_notice.user.id"/><s:property value="user_notice.user.name"/></s:a></td></tr>
<tr><th>消息来源</th><td><s:property value="user_notice.notice.source"/></td></tr>
<tr><th>发布时间</th><td><s:property value="@sus.scrofa.action.CommonAction@DATE_TIME_FORMAT_1.format(user_notice.notice.publishTime)"/></td></tr>
<tr><th>最后修改时间</th><td><s:property value="@sus.scrofa.action.CommonAction@DATE_TIME_FORMAT_1.format(user_notice.notice.updateTime)"/></td></tr>
<tr><th>具体内容</th><td><pre><s:property value="user_notice.notice.content"/></pre></td></tr>
<tr><th>显示状态</th><td><s:property value="@sus.scrofa.action.CommonAction@MAP_NOTICE_STATUS.get(user_notice.notice.status)"/></td></tr>
<tr><th>置顶级别</th><td><s:property value="@sus.scrofa.action.CommonAction@MAP_NOTICE_TOP.get(user_notice.notice.topLevel)"/></td></tr>
</table>
</div>
</div>
</div>
<s:include value="../foot.jsp"></s:include>
</body>
</html>