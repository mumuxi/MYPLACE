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
修改商品</div>
</div>
<div id="main">
<s:form action="ProcessUpdate" method="post" namespace="/product/admin" enctype="multipart/form-data">
<table class="detail_table">
<tr><th>商品名称</th><td><s:textfield name="product.name" value="%{product.name}"/></td></tr>
<tr><th>商品图片</th><td>
<img src="<%=request.getContextPath() %><s:property value="product.images"/>" class="user_logo" onclick="document.getElementById('upfile').click();"/>
<s:file name="image" id="upfile" style="display: none;"/></td></tr>
<tr><th>所在地</th><td><s:textfield name="product.area" value="%{product.area}"/></td></tr>
<tr><th>起拍价</th><td><s:textfield name="product.minPrice" value="%{product.minPrice}"/>元</td></tr>
<tr><th>起拍时间</th><td>
<s:select name="startTime.year" list="@sus.scrofa.action.CommonAction@YEAR_LIST_2" value="product.startTime.year"></s:select>年
<s:select name="startTime.month" list="@sus.scrofa.action.CommonAction@MONTH_LIST" value="product.startTime.month + 1"></s:select>月
<s:select name="startTime.date" list="@sus.scrofa.action.CommonAction@DAY_LIST" value="product.startTime.date"></s:select>日
<s:select name="startTime.hour" list="@sus.scrofa.action.CommonAction@HOUR_LIST" value="product.startTime.hours"></s:select>点
<s:select name="startTime.minute" list="@sus.scrofa.action.CommonAction@MINUTE_LIST" value="product.startTime.minutes"></s:select>分
<s:select name="startTime.second" list="@sus.scrofa.action.CommonAction@SECOND_LIST" value="product.startTime.seconds"></s:select>秒</td></tr>
<tr><th>结束时间</th><td>
<s:select name="finishTime.year" list="@sus.scrofa.action.CommonAction@YEAR_LIST_2" value="product.finishTime.year"></s:select>年
<s:select name="finishTime.month" list="@sus.scrofa.action.CommonAction@MONTH_LIST" value="product.finishTime.month + 1"></s:select>月
<s:select name="finishTime.date" list="@sus.scrofa.action.CommonAction@DAY_LIST" value="product.finishTime.date"></s:select>日
<s:select name="finishTime.hour" list="@sus.scrofa.action.CommonAction@HOUR_LIST" value="product.finishTime.hours"></s:select>点
<s:select name="finishTime.minute" list="@sus.scrofa.action.CommonAction@MINUTE_LIST" value="product.finishTime.minutes"></s:select>分
<s:select name="finishTime.second" list="@sus.scrofa.action.CommonAction@SECOND_LIST" value="product.finishTime.seconds"></s:select>秒</td></tr>
<tr><th>附件清单</th><td><s:textfield name="product.attach" value="%{product.attach}"/></td></tr>
<tr><td class="center_td" colspan="2"><s:submit value="修改"></s:submit></td></tr>
</table>
<s:hidden name="product.owner" value="%{product.owner}" />
<s:hidden name="id" value="%{product.id}" />
</s:form>
</div>
</div>
</div>
<s:include value="../foot.jsp"></s:include>
</body>
</html>