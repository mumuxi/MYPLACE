<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="product.name"/></title>
<link rel="icon" href="<%=request.getContextPath() %>/images/default.png" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/base.css" media="all"  />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/auction.index.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/auction.product.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jq_scroll.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/sys.js"></script>
</head>
<body>
<s:include value="../head.jsp"></s:include>
<div class="w">
    <div class="breadcrumb"><strong><s:a action="Index" namespace="/">首页</s:a></strong><span>&nbsp;&gt;&nbsp;<a href="#">正在热拍</a>&nbsp;&gt;&nbsp;<a href="#">xxx</a></span></div>
</div>
<div class="w">
    <div class="m" id="product-intro">
        <div class="p-img">
            <img  width="350" height="350" alt="#"  src="<%=request.getContextPath() %>/<s:property value="product.images"/>" />
        </div>
        <div class="p-info">
	    <h1><s:property value="product.name" /></h1>
		<ul class="list-info">
        <li class="fore1">商品编号：<s:property value="product.id" /></li>
        <li class="fore2">所在地：<s:property value="product.area" /></li>
        <li class="fore3">当前价格：
        	<strong class="ftx-01" id="cur_price">￥<s:if test="bid == null"><s:property value="product.minPrice"/></s:if><s:else><s:property value="bid.price"/></s:else></strong>
        	<span class="ftx-03">(<span class="ftx-04" id="buyerName"><s:if test="candit == null">无人</s:if><s:else><s:property value="candit.name"/></s:else></span>)出价</span>
        </li>
        <li class="fore8">剩余时间：<span class="over-time"></span></li>
        <li class="fore6">附件清单：<span id="detailProductMemo"><s:property value="product.attach" /></span></li>
		</ul>
        <div id="au-key" class="">
        <div class="a-key" id="bid-info">
        	<!-- 判断拍卖状态：未开始，正在进行，or 已经结束 -->
        	<s:set name="now" value="new java.util.Date()"/>
        	<s:if test="@sus.scrofa.action.CommonAction@DATE_TIME_FORMAT_1.format(product.startTime).compareTo(@sus.scrofa.action.CommonAction@DATE_TIME_FORMAT_1.format(#now)) > 0">
        	<!-- 未开始 -->
        	<div class="key-text"><strong class="ftx-04">即将拍卖</strong></div>
        	</s:if>
        	<s:elseif test="@sus.scrofa.action.CommonAction@DATE_TIME_FORMAT_1.format(product.finishTime).compareTo(@sus.scrofa.action.CommonAction@DATE_TIME_FORMAT_1.format(#now)) < 0">
        	<!-- 已经结束 -->
        	<div class="key-text"><strong class="ftx-03">已经结束</strong></div>
        	</s:elseif>
        	<s:else>
        	<!-- 正在进行 -->
        	<div class="quantity-form" id="quantityFormId">
		    	<s:form action="Bid" namespace="/bid" method="post" id="bid_form">
		    	<s:textfield name="bid.price" id="bid_price" cssClass="text quantity-text"/>&nbsp;&nbsp;<span>元</span>
		        <s:hidden name="bid.productId" value="%{product.id}"/>
		        </s:form>
		        <div class="ftx-03">最低加价：￥1.00&nbsp;&nbsp;最高加价：￥300.00</div>
		    </div>
			<div class="btns"><a class="btn-bid" id="buy-btn" href="javascript:submit_bid_form()">出价</a></div><!-- 先验证出价是否正确，然后验证是否登录，然后提交表单 -->
			<div class="auc-infos"><span id="checkLast8BidRecords">【点击查看（共n次出价）】<b class="icon-tria"></b></span></div>
		    <div class="auctioninfo">
		        <ul class="tb-th">
		            <li class="fore1">出价人</li>
		            <li class="fore2">价格</li>
		            <li class="fore3">时间</li>
		        </ul>
		        <div class="tb-void">
		            <table id="bidRecordItem" width="100%" cellspacing="0" cellpadding="0" border="0"><tbody>
		            <!-- 迭代 -->
		            <tr class="odd">
			        <td class="user-info" width="34%">曲歌19741688</td>
			        <td width="33%"><span class="price">345.00</span>元</td>
			        <td class="timer" width="33%">2013-01-14 18:33:30</td>
			    	</tr>
			    	</tbody></table>
		        </div>
		        <div id="bidRecordMoreDiv" class="more" style="display: none;">
		            <s:a><span class="ftx-05 checkBidRecordsAll">更多出价记录»</span></s:a>
		        </div>
		    </div>
        	</s:else>
		</div>
        </div>
	    </div>
	</div>
    <div class="m a-flex clearfix" style="clear:left">
        <dl>
            <dt>拍卖流程</dt>
            <dd class="fore1"><s></s><h5><span class="ftx-span">获取参与资格</span></h5><b class="flex-icon"></b><div style="display: none;" class="prompt"><div class="pc">50积分、银牌(含)以上会员3次试拍机会</div><div class="pt"></div></div></dd>
            <dd class="fore2"><s></s><h5><span class="ftx-span">拍得商品</span></h5><b class="flex-icon"></b><div class="prompt"><div class="pc">保证拍卖结束时出价最高，获得拍卖商品</div><div class="pt"></div></div></dd>
            <dd class="fore3"><s></s><h5><span class="ftx-span">确认转订单</span></h5><b class="flex-icon"></b><div class="prompt"><div class="pc">填写订单信息，提交订单</div><div class="pt"></div></div></dd>
            <dd class="fore4"><s></s><h5><span class="ftx-span">支付订单</span></h5><b class="flex-icon"></b><div class="prompt"><div class="pc">支付已提交的订单</div><div class="pt"></div></div></dd>
            <dd class="fore5"><s></s><h5><span class="ftx-span">成功拍卖</span></h5><div class="prompt"><div class="pc">支付成功后等待收货，拍卖完成</div><div class="pt"></div></div></dd>
        </dl>
    </div>
    <div class="m a-pinfo" id="pinfo">
        <div class="smt">
            <ul class="tab">
                <li id="aprinfo" class="curr">商品介绍</li>
                <li>规格参数</li>
            </ul>
            <div class="extra"><a href="#" class="flk-05">去个人中心查看商品&#187;</a></div>
        </div>
        <div class="smc" id="infomation">
            <div>
                <ul>
                    <li>1.本站所售商品均为“非全新商品”，请您仔细阅读拍卖商品信息页面中的具体说明，对商品要求较高者请您慎重购买</li>
                    <li>2.订单提交成功后请在24小时内履行进行支付（拍卖成功后请在24小时内转订单），否则系统将扣除200积分</li>
                </ul>
            </div>
            <div id="detail-content">
            </div>
        </div>
        <div class="smc hide" id="specification"></div><!-- 隐藏这段文字 -->
    </div>


</div>
<script type="text/javascript">
    doAuctionInterval();
    loadBidRecord(getUrlParameter("dealId"));

    $(function(){
       //bid
       $("#buy-btn").livequery('click',function(){
           var quantityVal = $(".quantity-text:last").val();
           bid(getUrlParameter("dealId"),quantityVal);
       });

        $(document).keyup(function(e){
            var buybtn = $("#buy-btn");
            if(buybtn.length == 1 && e.keyCode == 13){
                var quantityVal = $(".quantity-text:last").val();
                bid(getUrlParameter("dealId"),quantityVal);
            }
        });
        $('#detailProductMemo').text('此商品是维修返回的，包装良好，附件 电池 充电头 数据线 耳机，再无其他附件！');
    });

    $(function(){
        //per second
        auctionTimedIntervalId=setInterval(function(){
            doAuctionInterval();
        },1000);

        //per minute
        bidRecordIntervalId=setInterval(function(){
            if(!isDealEnded(new Date(dealModel.endTimeMili))){
               loadBidRecord(getUrlParameter("dealId"));
            }
        },60000);
    });

    $(function(){
        $(".a-flex s,.a-flex dt a").hover(function () { $(this).siblings(".prompt").show() }, function () { $(this).siblings(".prompt").hide() });

        //quantityText - priceLowerOffset*1 合法输入价格
        $('.decrement').livequery('click',function(){
            var rawQuantityText = $('.quantity-text:last');
            var quantityText = jQuery.trim(rawQuantityText.val());

            var limitPrice = !isNaN(jdPrice) && jdPrice >= 1;

            if(!/^-?\d+$/.test(quantityText)){
                alert('请输入价格(整数)');
                return;
            }

            var curPrice = ($('#cur_price').text() || startPrice).match(/\d+/);

            if(limitPrice){
                if(quantityText <= jdPrice ){
                    if(quantityText - (+priceLowerOffset) <= curPrice){
                        rawQuantityText.val((+curPrice) + parseInt((+priceHigherOffset)/10));
                        alert('出价必须大于当前最高出价记录');
                    }else{
                        rawQuantityText.val(quantityText - parseInt((+priceHigherOffset)/10));
                    }
                }else{
                    rawQuantityText.val(jdPrice);
                }
            }else{
                if(quantityText - (+priceLowerOffset) <= curPrice){
                    rawQuantityText.val((+curPrice) + parseInt((+priceHigherOffset)/10));
                    alert('出价必须大于当前最高出价记录');
                }else{
                    rawQuantityText.val(quantityText - parseInt((+priceHigherOffset)/10));
                }
            }
        });

        $('.increment').livequery('click',function(){
            var rawQuantityText = $('.quantity-text:last');
            var quantityText = jQuery.trim(rawQuantityText.val());

            var limitPrice = !isNaN(jdPrice) && jdPrice >= 1;

            if(!/^-?\d+$/.test(quantityText)){
                alert('请输入价格(整数)');
                return;
            }

            var curPrice = ($('#cur_price').text() || startPrice).match(/\d+/);

            if(limitPrice){
                if(quantityText < jdPrice ){
                    if(quantityText - (+priceLowerOffset) < curPrice){
                        rawQuantityText.val((+curPrice) + parseInt((+priceHigherOffset)/10));
                    }else{
                        rawQuantityText.val((+quantityText)+ parseInt((+priceHigherOffset)/10));
                    }
                }else{
                    rawQuantityText.val(jdPrice);
                    alert('出价必须小于京东价');
                }
            }else{
                if(quantityText - (+priceLowerOffset) < curPrice){
                    rawQuantityText.val((+curPrice) + parseInt((+priceHigherOffset)/10));
                }else{
                    rawQuantityText.val((+quantityText)+ parseInt((+priceHigherOffset)/10));
                }
            }
        });

        $('.addInterest').livequery('click',function(){
            var url = '/json/paimai/addInterest';
            var data = {dealId:'2978321'};

            jQuery.getJSON(url,data,function(jqXHR){
                if(jqXHR.flag){
                    alert('恭喜您，添加成功。\n\n曾参与拍卖、已拍得的商品可在‘我的夺宝箱’中查看哦。');
                }else{
                    if(jqXHR.nologin){
                        window.location.href='http://passport.360buy.com/new/login.aspx?ReturnUrl=http://auction.360buy.com/detail.action?dealId=2978321';
                    }else{
                        alert('抱歉，加入失败了，再试一次吧。');
                    }
                }
            })
        });

        $('.convert2order').livequery('click',function(){
            var data ={"deal.id":"2978321","deal.beijianId":"AS101448383" };

            var url = "/order.action";

            jQuery.getJSON(url,data,function(jqXHR){
                if(jqXHR.flag){
                    window.location.href = "http://jd2008.360buy.com/purchase/orderinfo_pm.aspx?bidid=2978321&amp;fxid=AS101448383";
                }else{
                    if(jqXHR.nologin){
                        window.location.href='http://passport.360buy.com/new/login.aspx?ReturnUrl=http://auction.360buy.com/detail.action?dealId=2978321';
                    }else{
                        alert(jqXHR.msg||'操作失败,请重试');
                    }
                }
            })
        });

        // checkBidRecordsAll MORE
        $('.checkBidRecordsAll').livequery('click',function(){
            loadBidRecord(getUrlParameter("dealId"),1,2147483647);
        });

        //checkLast8BidRecords
        $('#checkLast8BidRecords').livequery('click',function(){
            loadBidRecord(getUrlParameter("dealId"));

            var parentAuKey = $(this).parents("#au-key");
            if (parentAuKey.hasClass('select')) {
                parentAuKey.removeClass();
            } else {
                parentAuKey.addClass('select');
            }
        });


        $('#readDetail').click(function(){
           $('#aprinfo').click();
        });
    })
</script>
<s:include value="../foot.jsp"></s:include>
</body>
</html>