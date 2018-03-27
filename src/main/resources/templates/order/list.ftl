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
                    <nav style="text-align: center">
                        <ul class="pagination">
                        <#--上一页-->
                    <#if curentPage lte 1>
                        <li class="page-item disabled"><a class="page-link"
                                                          href="#">上一页</a>
                        </li>
                    <#else>
                        <li class="page-item"><a class="page-link"
                                                 href="/sell/seller/order/list?page=${curentPage-1}&size=${pageSize}">上一页</a>
                        </li>
                    </#if>
                        <#--分页栏-->
                    <#import "/order/paging.ftl" as paging/>
                    <@paging.paging curentPage=curentPage totalPage=totalPage showPages=10/>
                        <#--下一页-->
                    <#if curentPage gte totalPage>
                        <li class="page-item disabled"><a class="page-link"
                                                          href="#">下一页</a>
                        </li>
                    <#else>
                        <li class="page-item"><a class="page-link"
                                                 href="/sell/seller/order/list?page=${curentPage+1}&size=${pageSize}">下一页</a>
                        </li>
                    </#if>
                        </ul>
                    </nav>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>

