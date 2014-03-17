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
<li><s:a action="List" namespace="/product/admin">订单列表</s:a></li>
</ul>
</div>
<div id="right_content">
<div id="sub_nav">
<div id="sub_nav_left">您现在的位置：
<s:a action="Index" namespace="/user/admin">首页</s:a>&nbsp;&#187;&nbsp;
<s:a action="List" namespace="/deal/admin">订单管理</s:a></div>
<div id="sub_nav_right">
<s:a action="Add" namespace="/product/admin">添加商品</s:a>
</div>
</div>
<div id="main">
<table class="list_table">
<tr>
<th class="list_table_th w50">序号</th>
<th class="list_table_th w100">订单编号</th>
<th class="list_table_th w150">订单商品</th>
<th class="list_table_th w100">收货人</th>
<th class="list_table_th w100">订单金额</th>
<th class="list_table_th w150">下单时间</th>
<th class="list_table_th w150">订单状态</th>
<th class="list_table_th w150">操作选项</th></tr>
<s:iterator value="data['_list']" status="st">
<tr>
<td><s:property value="#st.count + count * (page - 1)"/></td>
<td><s:property value="deal.id"/></td>
<td><s:a action="Detail" namespace="/product/admin"><s:param name="dealId" value="product.id"/><s:property value="product.name"/></s:a></td>
<td><s:property value="user.name"/></td>
<td><s:property value="deal.price"/></td>
<td><s:property value="@sus.scrofa.action.CommonAction@DATE_TIME_FORMAT_1.format(deal.createTime)"/></td>
<s:if test="deal.status == @sus.scrofa.entity.Deal@STATUS_CREATED">
<td><span class="ftx-03">待买家付款</span></td>
<td>删除</td>
</s:if><s:elseif test="deal.status == @sus.scrofa.entity.Deal@STATUS_PAID">
<td><span>待商家发货</span></td>
<td><s:a action="ProcessDeliver" namespace="/deal/admin"><s:param name="dealId" value="deal.id"/>发货</s:a>删除</td>
</s:elseif><s:elseif test="deal.status == @sus.scrofa.entity.Deal@STATUS_DELIVERED">
<td><span class="ftx-03">待确认收货</span></td>
<td><a href="#">确认收货</a>删除</td>
</s:elseif><s:else>
<td><span class="ftx-03">已完成</span></td>
<td><a href="#">晒单</a></td>
</s:else>
</tr>
</s:iterator>
</table>
<div id="page_nav">
<span>共<s:property value="data['_total_record']"/>条&nbsp;</span>
<s:a action="List" namespace="/deal/admin"><s:param name="page" value="1"/>首页</s:a>
<s:a action="List" namespace="/deal/admin"><s:param name="page" value="page > 1 ? page - 1 : 1"/>上一页</s:a>
<s:a action="List" namespace="/deal/admin"><s:param name="page" value="page < data['_total_page'] ? page + 1 : data['_total_page']"/>下一页</s:a>
<s:a action="List" namespace="/deal/admin"><s:param name="page" value="data['_total_page']"/>尾页</s:a>
<span>当前第<s:property value="page"/>页&nbsp;共<s:property value="data['_total_page']"/>页</span>
</div>
</div>
</div>
</div>
<s:include value="../foot.jsp"></s:include>
</body>
</html>