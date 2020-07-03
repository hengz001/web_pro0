<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>算法验证</title>
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico">
<%--    WEB-INF下文件无法使用pageContext访问--%>
    <jsp:include page="/WEB-INF/views/common.jsp"/>

</head>

<body class="easyui-layout">
    <%--<div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>--%>
    <%--<div data-options="region:'south',title:'',split:false" style="height:20px;"></div>--%>
    <%--<div data-options="region:'east',title:'East',split:true" style="width:100px;"></div>--%>

    <div data-options="region:'west',title:'算法验证',split:false, collapsible:false" style="width:100px;">
        <a id="btn_des" class="easyui-linkbutton"  style="width:100px;">DES</a>
        <a id="btn_aes" class="easyui-linkbutton"  style="width:100px;">AES</a>
        <a id="btn_rsa" class="easyui-linkbutton"  style="width:100px;">RSA</a>
        <a id="btn_hash" class="easyui-linkbutton"  style="width:100px;">HASH</a>
    <%--    <a id="btn_sm1" class="easyui-linkbutton"  style="width:100px;">SM1</a>--%>
        <a id="btn_sm2" class="easyui-linkbutton"  style="width:100px;">SM2</a>
        <a id="btn_sm3" class="easyui-linkbutton"  style="width:100px;">SM3</a>
        <a id="btn_sm4" class="easyui-linkbutton"  style="width:100px;">SM4</a>
        <a id="btn_sponsor" class="easyui-linkbutton" style="width:100px;">捐赠</a>
    </div>

    <div data-options="region:'center',title:''" style="padding:1px;background:#eee;">
        <div id="tt" class="easyui-tabs" data-options="fit:true"> </div>
    </div>
</body>

<script src="${pageContext.request.contextPath}/js/home.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
</html>

