<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>网上拍卖后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/sys.js"></script>
<link rel=stylesheet type=text/css href="<%=request.getContextPath() %>/css/sys.css"/>
</head>

<body>
<div>
<div id="header" style="width: 800px;margin: 20px auto 0;min-height: 80px;">
<div id="head_left" style="float:left;display: inline;">
<img src="<%=request.getContextPath() %>/images/logo.png" border="0" style="height:55px;"/>
</div>
<div style="width: 480px;height:80px;overflow:hidden;float:right;display: inline;">
<div id="head_right">
<a href="#" onclick="SetHome(this,window.location)" style="">设为首页</a>|<a href="#">关注我们</a>&nbsp;&nbsp;
</div>
</div>
</div>
     <div style="overflow-x:hidden;width: 800px;margin: 0px auto 0;">

     
  <div style="float:left;display: inline;margin-left:10px;width:400px; height:337px; background:url(/Content/img/netboss_left_logo.png) no-repeat;">
     </div>

<div id="conright" style="width:350px; display:inline;">
<div  style="border:1px solid #ccc;width:340px;height:280px;margin-top:10px;">
     <div style="margin-left:20px;margin-top:15px; height: 220px; width: 300px;">
         <label  style="font-size: 14px;color:#4A4A4A;font-weight: 600;">网上拍卖后台管理系统</label>
         <s:form action="ProcessLogin" namespace="/user/admin" method="post">
         <table border="0" style="width:300px;height:200px;">
             <tbody>
             <tr><td height="8"></td></tr>

             <tr>
                 <td class="style1" style="font-size:12px;">账户名</td>
                 <td><input type="text" id="userid" name="name" style="font-size:14px;color:#888" maxlength="128" class="loginName"/>
                 </td>
             </tr>
             <tr>
             <td class="style1" style="font-size:12px;font-variant: normal;">密  码</td>
                 <td><input style="font-size:14px;color:#888" id="password" name="password" type="password"  class="loginName" >
                 </td>
             </tr>
             <tr>
	             <td colspan="2" style="text-align:center;">
	             <s:actionerror/>
	             </td>
             </tr>
             <tr>
	             <td colspan="2" style="text-align:center;">
	             <s:submit value="登 录"/>
	             </td>
             </tr>
             </tbody>
         </table>
         </s:form>
      </div>
     
     <div style="margin-left:25px;width:310px; height:40px;border-top:1px solid #CCC">
         <table border="0" style="margin-left:60px;margin-top:10px;">
            <tr>
             <td ><a href="#" style="text-decoration:none;font-size:12px;color: blue;">忘记密码？</a></td>                
             <td><a href="#" class="regt">免费注册</a></td>
            </tr>
         </table>
     </div>           
</div>
    
<div id="warning" style="display:none;color: red;margin:5px 20px; font-size: 14px;"></div>  
</div>

</div>
	<div style="width: 800px;margin: 0px auto 0;text-align: center;font-size:12px;">
	<ul class="footer_link">
	<li><a href="#" target="_blank">关于</a>|</li>
	<li><a href="#" target="_blank">About</a>|</li>
	<li><a href="#" target="_blank">服务条款</a>|</li>
	<li><a href="#" target="_blank">客服中心</a></li>
	</ul>
	<p style="color: #9E9E9E;">&copy;2012 xxx.cn All Rights Reserved. 鄂ICP备12017462</p>
	</div>
</div>
</body>
</html>