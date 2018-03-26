<#-- 参数说明：pageNo当前的页码，totalPage总页数， showPages显示的页码个数-->
<#macro paging curentPage totalPage showPages>
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
                                           href="/sell/seller/order/list?page=${index}&size=${pageSize}">${index}</a>
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
                                               href="/sell/seller/order/list?page=${index}&size=${pageSize}">${index}</a>
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
                                       href="/sell/seller/order/list?page=${index}&size=${pageSize}">${index}</a>
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
                               href="/sell/seller/order/list?page=${index}&size=${pageSize}">${index}</a>
                        </li>
            </#if>
        </#list>
    </#if>

</#macro>