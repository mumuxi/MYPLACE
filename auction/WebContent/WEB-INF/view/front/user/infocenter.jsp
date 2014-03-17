<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>个人中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="<%=request.getContextPath() %>/images/default.png" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/base.css" media="all"  />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/user.base.2012.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/user.self.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jq_scroll.js"></script>
</head>
<body>
<s:include value="../head.jsp"></s:include>
<div class="w">
    <div class="breadcrumb">
		<strong><a href="#">个人中心</a></strong>
		<span>&nbsp;&gt;&nbsp;账户信息<span></span></span>
	</div>
</div>
<div class="w main">
		<!--left-->
		<s:form name="userForm" id="userForm" method="post" action="ProcessUpdate" namespace="/user" enctype="multipart/form-data">
		<div class="right">
			<div class="o-mt">
				<h2>账户信息</h2>
			</div>
			<div class="m" id="baseinfo">
				<div class="mt">
					<ul class="tab">
						<li class="curr"><s></s><b></b><a href="/user/info">基本信息</a>
						</li>
					</ul>
				</div>
				<div class="mc">
					<div class="i-m">
						<div class="i-mc">
							<div class="form">
								<div class="item">
									<span class="label"><em>*</em>用户名：</span>
									<div class="fl">			
										<strong class="username"><s:textfield name="user.name" cssClass="text" value="%{user.name}"/></strong>
									</div>
									<div class="clr"></div>
								</div>
								<div class="item">
									<span class="label"><em>*</em>性别：</span>
									<div class="fl">
										<s:radio name="user.gender" list="#{0:'保密',1:'男',2:'女'}" listKey="key" listValue="value" value="user.gender"></s:radio>
									</div>
									<div class="clr"></div>
								</div>
								<div class="item">
									<span class="label"><em>*</em>邮箱：</span>
									<div class="fl">
										<s:textfield name="user.email" cssClass="text" value="%{user.email}"/>
									</div>
									<div class="clr"></div>
								</div>
							</div>
							<h5>选填信息</h5>
							<div class="form">
								<div class="item">
									<span class="label">密码：</span>
									<div class="fl">			
										<s:password name="user.password" cssClass="text" />
									</div>
									<div class="clr"></div>
								</div>
								<div class="item">
									<span class="label">确认密码：</span>
									<div class="fl">			
										<s:password name="confirmPassword" cssClass="text" />
									</div>
									<div class="clr"></div>
								</div>
								<div class="item">
									<span class="label">昵称：</span>
									<div class="fl">
										<div>
											<s:textfield id="petName" name="user.nickname" cssClass="text" value="%{user.nickname}"/><s id="petName_orderly"></s>
                                            <div class="clr"></div><div class="prompt-06"><span id="petName_msg"></span></div>
										</div>
									</div>
									<div class="clr"></div>
								</div>
								<div class="item">
									<span class="label">生日：</span>
									<div class="fl">
									<s:select name="birthday.year" list="@sus.scrofa.action.CommonAction@YEAR_LIST" value="user.birthday.year + 1900" cssClass="sele"></s:select><label>年</label>
									<s:select name="birthday.month" list="@sus.scrofa.action.CommonAction@MONTH_LIST"  value="user.birthday.month + 1" cssClass="sele"></s:select><label>月</label>
									<s:select name="birthday.date" list="@sus.scrofa.action.CommonAction@DAY_LIST"  value="user.birthday.date" cssClass="sele"></s:select><label>日</label>
									</div>
									<div class="clr"></div>
								</div>
								<div class="item">
									<span class="label">手机号码：</span>
									<div class="fl">
										<s:textfield name="user.telephone" cssClass="text" value="%{user.telephone}"/>
									</div>
									<div class="clr"></div>
								</div>
								<div class="item">
									<span class="label">QQ号码：</span>
									<div class="fl">
										<s:textfield name="user.qq" cssClass="text" value="%{user.qq}"/>
									</div>
									<div class="clr"></div>
								</div>
								<div class="item">
									<span class="label">&nbsp;</span>
									<div class="fl">
										<a href="javascript:void();" onclick="javascript:submit();" class="btn btn-7"><s></s>提交</a>
									</div>
									<div class="clr"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="right-extra">
						<div class="m">
							<div class="mc">
								<div class="headpic">
									<img id="infoimg" src="<%=request.getContextPath() %><s:property value="user.logo"/>" />
								</div>
								<div class="btns">
									<s:file id="up_info_img" name="image" style="display:none"/>
									<a id="uploadthickbox" onclick="document.getElementById('up_info_img').click();" href="javascript:void();">修改头像</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<s:hidden name="id" value="%{user.id}"/>
		</s:form>
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