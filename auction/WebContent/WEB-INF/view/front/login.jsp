<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<title>网上拍卖系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/default.png" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/base.css" media="all" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/auction.index.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/passport.base.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/regist.entry.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jq_scroll.js"></script>
</head>
<body>
	<s:include value="head.jsp"></s:include>
	<div class="w" id="entry">
		<div class="mt">
			<h2>用户登录</h2>
			<b></b><span style="text-align: right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
				href="#">English</a></span>
		</div>
		<div class="mc" style="padding-top: 20px;">
			<div class="form">
			<s:form action="ProcessLogin" namespace="/user" method="post">
				<div class="item">
					<span class="label">账户名：</span>
					<div class="fl">
						<input type="text" id="loginname" name="name" tabindex="1"
							onblur="if(this.value==''){this.value='用户名';this.style.color='#999'}"
							onfocus="if(this.value=='用户名'){this.value='';this.style.color='#333'}"
							value="用户名" class="text_blank"> <label
							id="loginname_succeed" class="blank invisible"></label> <span
							class="clr"></span> <label id="loginname_error"></label>
					</div>
				</div>
				<div class="item">
					<span class="label">密码：</span>
					<div class="fl">
						<input type="password" id="loginpwd" name="password" class="text"
							tabindex="2"> <label id="loginpwd_succeed"
							class="blank invisible"></label> <label><a href="#"
							class="flk13">找回密码</a></label> <span class="clr"></span> <label
							id="loginpwd_error"></label>
					</div>
				</div>
				<div class="item " id="o-authcode" style="display: none">
					<!-- 验证码，先不显示 -->
					<span class="label">验证码：</span>
					<div class="fl">
						<input type="text" id="authcode" name="authcode"
							class="text text-1" tabindex="6"> <label class="img">
							<img
							style="cursor: pointer; width: 100px; height: 26px; display: block;"
							src="#">
						</label> <label class="ftx23">&nbsp;看不清？<a href="#" class="flk13">换一张</a></label>
						<label id="authcode_succeed" class="blank invisible"></label> <span
							class="clr"></span> <span id="authcode_error"></span>
					</div>
				</div>
				<div class="item">
					<span class="label">&nbsp;</span> <input type="button" onclick="javascript:submit()"
						class="btn-img btn-entry" id="loginsubmit" value="登录" tabindex="8">
				</div>
			</s:form>
			</div>
			<div id="guide">
				<h5>还不是我们的会员？</h5>
				<div class="content">现在免费注册成为会员，便能立刻享受便宜又放心的购物乐趣。</div>
				<s:a action="Register" namespace="/"
					cssClass="btn-link btn-personal">注册新用户</s:a>
				<div class="btns">
					<a href="#">企业用户注册</a> <a href="#">校园用户注册</a>
				</div>
				<div class="ept-enter">
					<a href="#">International Customers</a>
				</div>
			</div>
			<span class="clr"></span>
		</div>
	</div>
	<s:include value="foot.jsp"></s:include>
</body>
</html>