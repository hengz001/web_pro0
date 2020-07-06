<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<%--<div style="padding:5px;background:#fafafa;width:500px;border:1px solid #ccc">--%>
<%--    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel">Cancel</a>--%>
<%--    <a href="#" class="easyui-linkbutton" iconCls="icon-reload">Refresh</a>--%>
<%--    <a href="#" class="easyui-linkbutton" iconCls="icon-search">Query</a>--%>
<%--    <a href="#" class="easyui-linkbutton">text button</a>--%>
<%--    <a href="#" class="easyui-linkbutton" iconCls="icon-print">Print</a>--%>
<%--</div>--%>

<%--<div style="padding:5px;background:#fafafa;width:500px;border:1px solid #ccc">--%>
<%--    <a href="#" id="btn1" class="easyui-linkbutton" plain="true" iconCls="icon-cancel">Cancel</a>--%>
<%--    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-reload">Refresh</a>--%>
<%--    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search">Query</a>--%>
<%--    <a href="#" class="easyui-linkbutton" plain="true">text button</a>--%>
<%--    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-print">Print</a>--%>
<%--    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-help"></a>--%>
<%--    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-save"></a>--%>
<%--    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-back"></a>--%>
<%--</div>--%>

<%--<script>--%>
<%--    $('a').linkbutton();--%>
<%--    $('a').linkbutton('disable')--%>
<%--</script>--%>

<div id="dd1" class="dd-demo">1</div>
<div id="dd2" class="dd-demo">2</div>
<div id="dd3" class="dd-demo">3</div>

<script>
    $('#dd1').draggable();
    $('#dd2').draggable({
        proxy:'clone'
    });
    $('#dd3').draggable({
        proxy:function(source){
            var p = $('<div class="proxy">proxy</div>');
            p.appendTo('body');
            return p;
        }
    });
</script>

</body>
</html>