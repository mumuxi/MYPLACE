<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div id="shortcut">
    <div class="w">
        <ul class="fl lh">
            <li class="fore1 ld"><a href="#" rel="nofollow">加入收藏</a></li>
            <li class="fore2"><a href="#">设为首页</a></li>
        </ul>
        <ul class="fr lh">
        	<s:if test="#session.isEmpty()">
        	<li class="fore1 ld" id="loginbar">您好！欢迎来到XXX！<s:a action="Login" namespace="/">[登录]</s:a>&nbsp;<s:a action="Register" namespace="/">[免费注册]</s:a></li>
        	</s:if>
        	<s:else>
        	<li class="fore1 ld" id="loginbar">您好，<s:property value="#session['_user_name']"/>！欢迎来到XXX！<s:a action="InfoCenter" namespace="/user">[个人中心]</s:a>&nbsp;<s:a action="Logout" namespace="/user">[安全退出]</s:a></li>
        	</s:else>
            <li class="fore2"><a href="#" rel="nofollow">我的竞拍</a></li>
            <li class="fore4"><a href="#" target="_blank">手机竞拍</a></li>
        </ul>
    </div>
</div><!--shortcut end-->
<div id="o-header">
    <div class="w" id="header">
        <div id="logo" class="ld"><a href="#"><img src="<%=request.getContextPath() %>/images/default.png" width="259" height="70" alt="首页"></a></div><!--logo end-->
        <div id="search">  
			<div class="i-search ld">
				<b></b><s></s>
				<ul id="shelper" class="hide"></ul>
                <div class="form">
					<input type="text" class="text" accesskey="s" id="searchText" />
					<input type="button" id="searchButton" value="搜索" class="button" />
				</div>
            </div>
        </div><!--search end-->
    </div><!--header end-->
    <span class="clr"></span>
    <div class="w">
        <div id="nav">
            <ul id="navitems">
                <li ><s:a action="Index" namespace="/">首页</s:a></li>
                <li ><s:a action="Hot" namespace="/">正在热拍</s:a></li>
                <li ><a href="#">最近成交</a></li>
                <li ><a href="#">玩家晒图</a></li>
                <li ><s:a action="Right" namespace="/">即将拍卖</s:a></li>
                <li ><a href="#">新闻公告</a></li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>