<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>我拍下的商品</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="<%=request.getContextPath() %>/images/default.png" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/base.css" media="all"  />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/user.base.2012.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/user.order.2011.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jq_scroll.js"></script>
<body>
<s:include value="../head.jsp"></s:include>
<div class="w">
	<div class="breadcrumb"><strong><a href="#">个人中心</a></strong><span>&nbsp;&gt;&nbsp;我的订单<span></span></span></div>
</div>
<div class="w main">
  <div class="right">
	<div class="m m3" id="safeinfo" style="display:none"></div>
    <div class="o-mt">
      <h2>订单中心</h2>
    </div>
    <div class="m m5" id="myorder">
      <div class="mt">
    <ul class="tab" id="toptab">
    <li class="curr"><s></s><b></b>
    <a href="#">我拍下的商品</a></li></ul>
</div>
<div class="mc">
<div class="form search-01">
    <div class="item">
        <div class="fl fore">
            <select id="ordertype" class="sele" name="">
            	<option value="1">近一个月订单</option>
            	<option value="2">一个月前订单</option>
            </select>
            <select id="orderstateselect" class="sele" name="">
                <option value="1">全部订单</option>
                <option value="2">有效订单</option>
                <option value="3">已取消订单</option>
            </select>
        </div>
        <div class="fr"><!-- 查询部分 -->
        </div>
        <div class="clr">
        </div>
    </div>
</div>
<div class="tb-void tb-line">
<table cellpadding="0" cellspacing="0" width="100%">
<tbody><tr>
<th width="12%">订单编号</th>
<th width="28%">订单商品</th>
<th width="10%">收货人</th>
<th width="12%">订单金额</th>
<th width="12%">下单时间</th>
<th width="12%">订单状态</th>
<th width="14%">操作</th>
</tr>
<s:iterator value="data['_list']">
<tr id="track348492393">
<td><s:a name="orderIdLinks" id="idUrl348492393"><s:property value="bid.id"/></s:a></td>
<td align="left"><div id="img348492393" class="img-list"><a href="#" target="_blank">
<img src="<%=request.getContextPath() %><s:property value="product.images"/>" width="50" height="50">
</a></div></td>
<td><s:property value="user.name"/></td>
<td>￥<s:property value="bid.price"/></td>
<td><span class="ftx-03"><s:property value="bid.bidTime"/></span></td>
<td><span class="ftx-03">还未生成，待确认</span></td>
<td id="operate348492393" class="order-doi">
<s:a action="ProcessConfirm" namespace="/deal"><s:param name="bidId" value="bid.id"/>确认下单</s:a>
</td></tr>
</s:iterator>
</tbody></table>
</div>
</div>
</div>
	<div class="m clearfix">
		<div class="m clearfix">
			<div class="pagin fr">
			<s:a action="Confirm" namespace="/deal"><s:param name="page" value="1"/>首页</s:a>
			<s:a action="Confirm" namespace="/deal" cssClass="prev"><s:param name="page" value="page > 1 ? page - 1 : 1"/>上一页<b></b></s:a>
			<s:a action="Confirm" namespace="/deal" cssClass="next"><s:param name="page" value="page < data['_total_page'] ? page + 1 : data['_total_page']"/>下一页<b></b></s:a>
			<s:a action="Confirm" namespace="/deal"><s:param name="page" value="data['_total_page']"/>尾页</s:a>
			</div>
		</div>
	</div>
	<div id="morder01" class="m m7">
		<div class="mt">
			<h3>特殊说明</h3>
		</div>
		<div class="mc">
            <p>
				如果您购买的商品需要返修、退货或换货服务，可以点击“订单中心-
            <a href="#">返修/退换货</a>
            ”自助办理。
            </p>
		</div>
    </div>
  </div>
  <div class="left" id="Api_Lefter">
			<div id="myjd" class="m">
				<div class="mt">
					<h2><a href="#">个人中心</a></h2>
				</div>
				<div class="mc">
					<dl >
						<dt >订单中心<b></b></dt>
						<dd>
							<div id="_MYJD_ordercenter" class="item">
								<s:a action="Confirm" namespace="/deal">我拍下的商品</s:a>
							</div>
							<div id="_MYJD_favorite" class="item">
								<s:a action="List" namespace="/deal">我下的订单</s:a>
							</div>
						</dd>
					</dl>
				</div>
			</div>
		</div>
  <div class="clr"></div>
</div>
<s:include value="../foot.jsp"></s:include>
</body>
</html>