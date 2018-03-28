<nav style="text-align: center">
    <ul class="pagination">
    <#--上一页-->
        <#if curentPage lte 1>
            <li class="page-item disabled"><a class="page-link"
                                              href="#">上一页</a>
            </li>
        <#else>
            <li class="page-item"><a class="page-link"
                                     href="/sell/seller/product/list?page=${curentPage-1}&size=${pageSize}">上一页</a>
            </li>
        </#if>
    <#--分页栏-->
        <#if totalPage gt 10>
            <#if curentPage gt 5>
                <#if totalPage lt curentPage+4>
                    <#list totalPage-9..totalPage as index>
                        <#if curentPage==index>
                        <li class="page-item disabled">
                            <a class="page-link" href="#">${index}</a>
                        </li>
                        <#else>
                        <li class="page-item">
                            <a class="page-link"
                               href="/sell/seller/product/list?page=${index}&size=${pageSize}">${index}</a>
                        </li>
                        </#if>
                    </#list>
                <#else>
                    <#list curentPage-5..curentPage+4 as index>
                        <#if curentPage==index>
                            <li class="page-item disabled">
                                <a class="page-link" href="#">${index}</a>
                            </li>
                        <#else>
                            <li class="page-item">
                                <a class="page-link"
                                   href="/sell/seller/product/list?page=${index}&size=${pageSize}">${index}</a>
                            </li>
                        </#if>
                    </#list>
                </#if>
            <#else>
                <#list 1..10 as index>
                    <#if curentPage==index>
                    <li class="page-item disabled">
                        <a class="page-link" href="#">${index}</a>
                    </li>
                    <#else>
                    <li class="page-item">
                        <a class="page-link"
                           href="/sell/seller/product/list?page=${index}&size=${pageSize}">${index}</a>
                    </li>
                    </#if>
                </#list>
            </#if>
        <#else>
            <#list 1..totalPage as index>
                <#if curentPage==index>
            <li class="page-item disabled">
                <a class="page-link" href="#">${index}</a>
            </li>
                <#else>
            <li class="page-item">
                <a class="page-link"
                   href="/sell/seller/product/list?page=${index}&size=${pageSize}">${index}</a>
            </li>
                </#if>
            </#list>
        </#if>
    <#--下一页-->
        <#if curentPage gte totalPage>
            <li class="page-item disabled"><a class="page-link"
                                              href="#">下一页</a>
            </li>
        <#else>
            <li class="page-item"><a class="page-link"
                                     href="/sell/seller/product/list?page=${curentPage+1}&size=${pageSize}">下一页</a>
            </li>
        </#if>
    </ul>
</nav>
