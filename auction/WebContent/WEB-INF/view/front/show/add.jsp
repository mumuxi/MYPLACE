<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发表晒图</title>
<link rel="icon" href="<%=request.getContextPath() %>/images/default.png" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/base.css" media="all"  />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/club.show.2012.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jq_scroll.js"></script>
</head>
<body>
<s:include value="../head.jsp"></s:include>
<div class="w">
<div class="crumb"><a href="#">首页</a>&nbsp;&gt;&nbsp;
<a href="#">我的订单</a>&nbsp;&gt;&nbsp;发表晒图</div>
<div class="w">
<div class="m" id="show">
<div class="mt">
<h2>发表晒图</h2>
</div>
<s:form action="ProcessAdd" namespace="/show" method="post" enctype="multipart/form-data" style="width: 100%; padding: 0px;">
<div class="mc">
<div class="o-item">
<div class="item fore  ">
<div class="tit">晒图商品</div>
<div class="i-item">
<div class="value"><s:a action="Detail" namespace="/product" target="_blank"><s:param name="id" value="udp.product.id"/><s:property value="udp.product.name"/></s:a></div>
<span class="clr"></span>
</div>
</div>
<div class="item text">
<div class="tit">内容<s>*</s></div>
<div class="i-item"><s:textarea name="showPic.content" id="cont" rows="5"/>
<div id="cont_verif"></div>
<div class="tips" id="v_c">还能输入10000个字符</div>
<div class="err"></div>
</div>
</div>
<div class="item">
<div class="tit">上传图片<s>*</s></div>
<div class="i-item"> 
<div id="divFileProgressContainer" style="height: 105px; position: relative;">
<div style="position: relative; z-index: 0;" id="pickbutton" class="pickbutton" 
onmouseout="this.className='pickbuttonout'" 
onmouseover="this.className='pickbuttonhover'" 
onclick="this.className='pickbuttonclick'; document.getElementById('upfile').click()"><a href="javascript:;" id="pickfiles"> </a></div>
<div id="divFileProgressStatus"></div>
<div class="tips">图片不超过4M，支持的图片格式为jpg，jpeg，bmp，png，gif</div>
</div>
<s:file name="image" style="display:none;" id="upfile"/>
</div>
<div class="item">
<div class="i-item"><s:submit id="release" value="发布" cssClass="release"/></div>
</div>
</div>
</div>
<div class="expl">
<h3>关于晒图：</h3>
<ul>
	<li>·您可以将自己的使用感受、选购建议、实物照片、使用场景、拆包过程等与网友们分享；</li>
	<li>·每个商品前5位成功晒单者且图片数在3张及以上的客户可获得10个积分奖励；其他用户不再赠送积分；</li>
	<li>·请保证所上传的图片是原创的及合法的，否则京东商城有权删除图片及冻结帐号，且保留追究其法律责任的权利；</li>
	<li><a href="#">更多晒单说明</a></li>
</ul>
</div>
</div>
<s:hidden name="showPic.dealId" value="%{udp.deal.id}"/>
</s:form>
</div>
</div>
</div>
<s:include value="../foot.jsp"></s:include>
</body>
</html>