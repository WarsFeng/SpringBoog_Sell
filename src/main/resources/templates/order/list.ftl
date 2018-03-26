<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
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
                        <td>详情</td>
                        <td>取消</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>

    <#--分页-->
        <div class="col-md-12">
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
</body>
</html>

