<!doctype html>
<html lang="en">
<#include "../common/head.ftl">
<body>
<div id="wrapper" class="toggled">
<#--边栏-->
    <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>商品Id</th>
                            <th>分类Id</th>
                            <th>名称</th>
                            <th>图片</th>
                            <th>单价</th>
                            <th>库存</th>
                            <th>描述</th>
                            <th>修改时间</th>
                            <th>状态</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                    <#list productList.content as products>
                    <tr>
                        <td>${products.productId}</td>
                        <td>${products.categoryType}</td>
                        <td>${products.productName}</td>
                        <td width="75px"><img src="${products.productIcon}"></td>
                        <td>${products.productPrice}</td>
                        <td>${products.productStock}</td>
                        <td width="200px">${products.productDescription}</td>
                        <td>${products.updateTime}</td>
                        <#if products.getProductStatusEnum()=="上架">
                            <td>${products.getProductStatusEnum()}</td>
                        <#else>
                            <td><s><strong>${products.getProductStatusEnum()}</strong></td>
                        </#if>
                        <td><a href="/sell/seller/product/index?productId=${products.productId}">修改</a></td>
                        <td>
                            <#if products.getProductStatusEnum()=="上架">
                                <a href="/sell/seller/product/downProduct?productId=${products.productId}">下架</a>
                            <#else>
                                <a href="/sell/seller/product/upProduct?productId=${products.productId}">上架</a>
                            </#if>
                        </td>
                    </tr>
                    </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    <#--分页-->
    <#include "paging.ftl">
    </div>
</body>
</html>