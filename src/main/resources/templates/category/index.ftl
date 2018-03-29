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
                    <h2>类目修改/新增</h2><br>
                    <form role="form" method="post" action="/sell/seller/category/save">
                        <div class="form-group">
                            <input type="hidden" name="categoryId" value="${(category.categoryId)!''}"
                                   class="form-control">
                        </div>
                        <div class="form-group">
                            <label>
                                名称
                            </label>
                            <input type="text" name="categoryName" value="${(category.categoryName)!''}"
                                   class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>
                                类型
                            </label>
                            <input type="number" name="categoryType" value="${(category.categoryType)!''}"
                                   class="form-control"/>
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
