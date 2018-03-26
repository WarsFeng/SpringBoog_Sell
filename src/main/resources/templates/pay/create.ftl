<script>
    function onBridgeReady() {
        WeixinJSBridge.invoke(
                'getBrandWCPayRequest', {
                    "appId": "${map.appId}",     //公众号名称，由商户传入
                    "timeStamp": "${map.timeStamp}",         //时间戳，自1970年以来的秒数
                    "nonceStr": "${map.nonceStr}", //随机串
                    "package": "${map.packageValue}",
                    <#--"package": "prepay_id=${map.packageValue}",-->
                    "signType": "${map.signType}",         //微信签名方式：
                    "paySign": "${map.paySign}" //微信签名
                },
                function (res) {
                    location.href(${returnUrl});
                    // if (res.err_msg == "get_brand_wcpay_request:ok") {
                    // }
                    // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                }
        );
    }

    if (typeof WeixinJSBridge == "undefined") {
        if (document.addEventListener) {
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        } else if (document.attachEvent) {
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    } else {
        onBridgeReady();
    }
</script>