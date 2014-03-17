<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>网上拍卖系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="<%=request.getContextPath() %>/images/default.png" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/base.css" media="all"  />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/auction.index.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jq_scroll.js"></script>
</head>
<body>
<s:include value="head.jsp" />
<div class="wrap wrapbg">
	<div class="w clearfix" id="auc-main">
		<div id="slide" class="m">
			<img alt="" src="<%=request.getContextPath() %>/images/float.jpg">
		</div>
		<div id="notice" class="m"><!-- 网站公告，放右侧 -->
			<h3>新闻公告</h3>
			<ul>
			<s:iterator value="dataNotice['_list']">
			<li><!-- 迭代 -->
			<div class="notice_t"><s:property value="title.length() > 8 ? title.substring(0, 8) : title"/></div>
			<div class="notice_d"><s:property value="@sus.scrofa.action.CommonAction@DATE_FORMAT_1.format(updateTime)"/></div>
			<span class="clr"></span>
			</li>
			</s:iterator>
			</ul>
			<p><a href="#">更多&#187;</a></p>
		</div>
	</div>
	<div class="w" id="auc-hot">
		<div class="o-mt">
				<h2>正在热拍</h2>
				<div class="extra"><s:a action="Hot" namespace="/">更多&#187;</s:a></div>
		</div>
		<ul class="list-auction" id="List-hotDeals">
		<s:iterator value="dataHot['_list']">
		<li id="Li-2966605|hotDeals">
		<div class="p-info" id="resultDeals|1|1357720680000|1357726080000|2966605|hotDeals">
			<div class="p-img">
			<s:a action="Detail" namespace="/product" target="_blank"><s:param name="id" value="product.id"/>
			<img width="160" height="160" alt="xxx" src="<%=request.getContextPath() %><s:property value="product.images"/>"></s:a>
			</div>
			<div class="p-name"><s:a action="Detail" namespace="/product" target="_blank"><s:param name="id" value="product.id"/><s:property value="product.name"/></s:a></div>
			<div class="p-speed" id="hotDealsSpeed2966605"><span style="width: 70%" class="icon-speed"></span></div>
			<div class="p-time" id="hotDealsList2966605">剩余时间：<span class="ftx-04">xxx</span></div>
			<div class="p-oper" id="oper|hotDeals|2966605">
				<div class="a-price" id="2966605|hotDeals|price">当前价格：<strong class="ftx-01"><s:property value="max"/></strong></div>
				<div class="a-times" id="2966605|hotDeals|count">出价次数：<span class="ftx-06"><s:property value="count"/></span></div>
			</div>
		</div>
		</li>
		</s:iterator>
		</ul>
	</div>
	<div class="w" id="auc-right">
		<div class="o-mt">
				<h2>即将拍卖</h2>
				<div class="extra"><a href="#">更多&#187;</a></div>
		</div>
		<ul class="list-auction"  id="List-auctionLater">
		<s:iterator value="dataRight['_list']">
		<li id="Li-2973192|auctionLater">
			<div class="p-info" id="resultDeals|2|1357726080000|1357729680000|2973192|auctionLater">
				<div class="p-img"><s:a action="Detail" namespace="/product" target="_blank"><s:param name="id" value="id"/>
					<img width="160" height="160" src="<%=request.getContextPath() %><s:property value="images"/>" ></s:a>
				</div>
				<div class="p-name"><s:a action="Detail" namespace="/product" target="_blank"><s:param name="id" value="id"/><s:property value="name"/></s:a></div>
				<div class="p-time" id="auctionLaterList2973192">起拍时间：<span class="ftx-04"><br /><s:property value="@sus.scrofa.action.CommonAction@DATE_TIME_FORMAT_1.format(startTime)"/></span></div>
				<div class="p-oper02">
					<div class="a-price" id="2966605|hotDeals|price">起拍价：<strong class="ftx-01"><s:property value="minPrice"/></strong></div>
				</div>
			</div>
		</li>
		</s:iterator>
		</ul>
	</div>
	<div class="w" id="auc-price">
		<div class="o-mt">
			<h2>玩家晒图</h2>
			<div class="extra"><a href="#">更多&#187;</a></div>
		</div>
		<ul class="list-auction">
		<s:iterator value="dataShow['_list']">
			<li>
				<div class="p-info" title="<s:property value="product.name"/>">
						<div class="p-img">
						<s:a action="Detail" namespace="/product" target="_blank"><s:param name="id" value="product.id"/>
						<img width="160" height="160" 
						alt="product.name" 
						src="<%=request.getContextPath() %><s:property value="showPic.images"/>"></s:a>
						</div>
						<div class="p-name">
						<a href="#"><s:property value="showPic.content"/><font class="adwords" style="color:#ff0000"></font></a></div>
						<div class="p-oper01">
								<div class="p-price">起&nbsp;拍&nbsp;价：<del>￥<s:property value="product.minPrice"/></del></div>
								<div class="a-price01">拍&nbsp;下&nbsp;价：<strong class="ftx-01">￥<s:property value="deal.price"/></strong></div>
						</div>
				</div>	
			</li>
		</s:iterator>
		</ul>
	</div>
</div>
<s:include value="foot.jsp" />
</body>
</html>