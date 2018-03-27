<!doctype html>
<html lang="en">
<#include "../common/head.ftl">
<body>
<#--订单数据-->
<div id="wrapper" class="toggled">
<#--边栏-->
    <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4">
                    <table class="table table-sm table-hover table-bordered">
                        <thead>
                        <tr>
                            <th>订单Id</th>
                            <th>定单总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.orderAmount}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        <#--订单详情数据-->
            <div class="row">
                <div class="col-md-12">
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th>商品Id</th>
                            <th>商品名称</th>
                            <th>价格</th>
                            <th>数量</th>
                            <th>总额</th>
                        </tr>
                        </thead>
                        <tbody>
                <#list orderDTO.orderDetailList as detail>
                <tr>
                    <td>${detail.detailId}</td>
                    <td>${detail.productName}</td>
                    <td>${detail.productPrice}</td>
                    <td>${detail.productQuantity}</td>
                    <td>${detail.productQuantity*detail.productPrice}</td>
                </tr>
                </#list>
                        </tbody>
                    </table>
                </div>
        <#if orderDTO.getOrderStatusEnum() == "新订单">
        <div class="col-md-4">
            </a>
            <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}">
                <button type="button" class="btn btn-lg btn-success">
                    完结订单
                </button>
            </a>
            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">
                <button type="button" class="btn btn-warning btn-lg">
                    取消订单
                </button>
        </div>
        </#if>
            </div>
        </div>
    </div>
</div>
</body>
</html>