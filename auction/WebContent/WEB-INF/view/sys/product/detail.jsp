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
<li><s:a action="List" namespace="/product/admin">商品列表</s:a></li>
<li><s:a action="Add" namespace="/product/admin">添加商品</s:a></li>
</ul>
</div>
<div id="right_content">
<div id="sub_nav">
<div id="sub_nav_left">您现在的位置：
<s:a action="Index" namespace="/user/admin">首页</s:a>&nbsp;&#187;&nbsp;
<s:a action="List" namespace="/product/admin">商品管理</s:a>&nbsp;&#187;&nbsp;
商品详细信息</div>
<div id="sub_nav_right">
<s:a action="Add" namespace="/product/admin">添加商品</s:a>
<s:a action="Update" namespace="/product/admin"><s:param name="id" value="product.id"/>修改商品</s:a>
</div>
</div>
<div id="main">
<table class="detail_table">
<tr><th>商品名称</th><td><s:property value="product.name"/></td></tr>
<tr><th>发布者</th><td><s:a action="Detail" namespace="/user"><s:param name="id" value="product.owner"/><s:property value="product.owner"/></s:a></td></tr>
<tr><th>商品图片</th><td><img src="<%=request.getContextPath() %><s:property value="product.images"/>" class="user_logo"/></td></tr>
<tr><th>所在地</th><td><s:property value="product.area"/></td></tr>
<tr><th>起拍价</th><td><s:property value="product.minPrice"/></td></tr>
<tr><th>起拍时间</th><td><s:property value="@sus.scrofa.action.CommonAction@DATE_TIME_FORMAT_1.format(product.startTime)"/></td></tr>
<tr><th>结束时间</th><td><s:property value="@sus.scrofa.action.CommonAction@DATE_TIME_FORMAT_1.format(product.finishTime)"/></td></tr>
<tr><th>附件清单</th><td><s:property value="product.attach"/></td></tr>
</table>
</div>
</div>
</div>
<s:include value="../foot.jsp"></s:include>
</body>
</html>