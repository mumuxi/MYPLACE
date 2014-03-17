<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>网上拍卖系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/default.png" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/base.css" media="all" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/auction.index.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jq_scroll.js"></script>
</head>
<body>
	<s:include value="../head.jsp"></s:include>
	<div class="w">
		<div class="breadcrumb">
			<strong><s:a action="Index" namespace="/">首页</s:a></strong> <span>&nbsp;&gt;&nbsp;<s:a
					action="Hot" namespace="/">正在热拍</s:a>&nbsp;&gt;&nbsp;商品列表
			</span>
		</div>
	</div>
	<div class="wrap">
		<div class="w">
			<ul class="list-auction">
				<s:iterator value="dataHot['_list']">
					<li>
						<!-- 迭代 -->
						<div class="p-info"
							id="resultDeals|1|1358229240000|1358234640000|2980643|searchDeals">
							<div class="p-img">
								<s:a action="Detail" namespace="/product" target="_blank">
									<s:param name="id" value="product.id" />
									<img width="160" height="160" alt="xxx"
										src="<%=request.getContextPath()%>/<s:property value="product.images"/>">
								</s:a>
							</div>
							<div class="p-name">
								<s:a action="Detail" namespace="/product" target="_blank"><s:param name="id" value="product.id" /><s:property value="product.name" /></s:a>
							</div>
							<div class="p-speed" id="searchDealsSpeed2980643">
								<span style="width: 70%" class="icon-speed"></span>
							</div>
							<div class="p-time" id="searchDealsList2980643">
								剩余时间：<span class="ftx-04">xxx</span>
							</div>
							<div class="p-oper" id="oper|searchDeals|2980643">
								<div class="a-price" id="2966605|hotDeals|price">
									当前价格：<strong class="ftx-01"><s:property value="max" /></strong>
								</div>
								<div class="a-times" id="2966605|hotDeals|count">
									出价次数：<span class="ftx-06"><s:property value="count" /></span>
								</div>
							</div>
						</div>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
	<div class="w">
		<div class="m clearfix">
			<div class="pagin fr">
			<s:a action="Hot" namespace="/"><s:param name="page" value="1"/>首页</s:a>
			<s:a action="Hot" namespace="/" cssClass="prev"><s:param name="page" value="page > 1 ? page - 1 : 1"/>上一页<b></b></s:a>
			<s:a action="Hot" namespace="/" cssClass="next"><s:param name="page" value="page < dataHot['_total_page'] ? page + 1 : dataHot['_total_page']"/>下一页<b></b></s:a>
			<s:a action="Hot" namespace="/"><s:param name="page" value="dataHot['_total_page']"/>尾页</s:a>
			</div>
		</div>
	</div>
	<s:include value="../foot.jsp"></s:include>
</body>
</html>