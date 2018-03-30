<!doctype html>
<html lang="en">
<#include "../common/head.ftl">
<body>
<div id="wrapper" class="toggled">
<#--边栏-->
    <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <h1 style="text-align: center" id="message"></h1>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">

                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>订单Id</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                    <#list orderDTOPage.content as orderDTO>
                    <tr>
                        <td>${orderDTO.orderId}</td>
                        <td>${orderDTO.buyerName}</td>
                        <td>${orderDTO.buyerPhone}</td>
                        <td>${orderDTO.buyerAddress}</td>
                        <td>${orderDTO.orderAmount}</td>
                        <td>${orderDTO.getOrderStatusEnum()}</td>
                        <td>${orderDTO.getPayStatusEnum()}</td>
                        <td>${orderDTO.createTime}</td>
                        <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                        <td>
                            <#if orderDTO.getOrderStatusEnum()=="新订单">
                                <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                            </#if>
                        </td>
                    </tr>
                    </#list>
                        </tbody>
                    </table>
                <#--分页-->
                    <#include "../order/paging.ftl">
                </div>

            </div>
        </div>
    </div>
</div>
<script>
    function createXmlHttp() {
        var xmlHttp;
        try { // Firefox, Opera 8.0+, Safari
            xmlHttp = new XMLHttpRequest();
        }
        catch (e) {
            try {// Internet Explorer
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
            }
            catch (e) {
                try {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                catch (e) {
                }
            }
        }

        return xmlHttp;
    }

    var xhr = createXmlHttp();
    var audio = new Audio();
    audio.src = "http://wars.natapp1.cc/sell/audio/song.mp3";
    xhr.onreadystatechange = function () {
        if (xhr.readyState = 4) {
            if (xhr.status = 200) {
                if (xhr.responseText > 0) {
                    audio.play();
                    document.getElementById("message").innerText = "您有新的订单,请刷新页面！";
                }
            }
        }
    }
    setInterval(function () {
        xhr.open("POST", "/sell/seller/message", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send();
    }, 10000);

</script>
</body>
</html>

