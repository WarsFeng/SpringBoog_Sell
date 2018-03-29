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
                    <h2>商品修改/新增</h2><br>
                    <form role="form" method="post" action="/sell/seller/product/save">
                        <div class="form-group">
                            <input type="hidden" name="productId" value="${(product.productId)!''}"
                                   class="form-control">
                        </div>
                        <div class="form-group">
                            <label>
                                名称
                            </label>
                            <input type="text" name="productName" value="${(product.productName)!''}"
                                   class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>
                                价格
                            </label>
                            <input type="number" name="productPrice" value="${(product.productPrice)!''}"
                                   class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>
                                库存
                            </label>
                            <input type="number" name="productStock" value="${(product.productStock)!''}"
                                   class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>
                                描述
                            </label>
                            <input type="text" name="productDescription" value="${(product.productDescription)!''}"
                                   class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>
                                图片地址
                            </label>
                            <input type="text" name="productIcon" value="${(product.productIcon)!''}"
                                   class="form-control"/>
                            <img src="${(product.productIcon)!''}">
                        </div>
                        <div class="form-group" style="width: 75px">
                            <label>
                                状态
                            </label>
                            <select class="form-control" name="productStatus">
                                <option <#if product?? && product.productStatus==0>selected</#if>
                                        value="0">上架</option>
                                <option <#if product?? && product.productStatus==1>selected</#if>
                                        value="1">下架</option>
                            </select>
                        </div>
                        <div class="form-group" style="width: 150px">
                            <label>
                                类目
                            </label>
                            <select name="categoryType" class="form-control"">
                                <#list categoryList as category>
                                <option
                                    <#if product?? && product.categoryType==category.categoryType>
                                    selected
                                    </#if>
                                    value="${category.categoryType}">${category.categoryName}</option>
                                </#list>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">
                            保存
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
