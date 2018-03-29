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
                            <th>类目id</th>
                            <th>类目名称</th>
                            <th>类型</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                    <#list categoryList as categorys>
                    <tr>
                        <td>${categorys.categoryId}</td>
                        <td>${categorys.categoryName}</td>
                        <td>${categorys.categoryType}</td>
                        <td>${categorys.createTime}</td>
                        <td>${categorys.updateTime}</td>
                        <td><a href="/sell/seller/category/index?categoryId=${categorys.categoryId}">修改</a></td>
                    </tr>
                    </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>