<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
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
<s:a action="List" namespace="/notice/admin">新闻公告管理</s:a></div>
<div id="sub_nav_right">
<s:a action="Add" namespace="/notice/admin">添加新闻公告</s:a>
</div>
</div>
<div id="main">
<table class="list_table">
<tr>
<th class="list_table_th w50">序号</th>
<th class="list_table_th w300">标题</th>
<th class="list_table_th w150">发布时间</th>
<th class="list_table_th w150">最后修改时间</th>
<th colspan="2" class="list_table_th w150">操作选项</th></tr>
<s:iterator value="data['_list']" status="st">
<tr>
<td><s:property value="#st.count + count * (page - 1)"/></td>
<td><s:a action="Detail" namespace="/notice/admin"><s:param name="id" value="id"/><s:property value="title"/></s:a></td>
<td><s:property value="@sus.scrofa.action.CommonAction@DATE_TIME_FORMAT_1.format(publishTime)"/></td>
<td><s:property value="@sus.scrofa.action.CommonAction@DATE_TIME_FORMAT_1.format(updateTime)"/></td>
<td colspan="2">
<s:a action="Update" namespace="/notice/admin"><s:param name="id" value="id"/>修改</s:a>
<s:a action="ProcessDelete" namespace="/notice/admin"><s:param name="id" value="id"/>删除</s:a>
</td>
</tr>
</s:iterator>
</table>
<div id="page_nav">
<span>共<s:property value="data['_total_record']"/>条&nbsp;</span>
<s:a action="List" namespace="/notice/admin"><s:param name="page" value="1"/>首页</s:a>
<s:a action="List" namespace="/notice/admin"><s:param name="page" value="page > 1 ? page - 1 : 1"/>上一页</s:a>
<s:a action="List" namespace="/notice/admin"><s:param name="page" value="page < data['_total_page'] ? page + 1 : data['_total_page']"/>下一页</s:a>
<s:a action="List" namespace="/notice/admin"><s:param name="page" value="data['_total_page']"/>尾页</s:a>
<span>当前第<s:property value="page"/>页&nbsp;共<s:property value="data['_total_page']"/>页</span>
</div>
</div>
</div>
</div>
<s:include value="../foot.jsp"></s:include>
</body>
</html>