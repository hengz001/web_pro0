<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>zhuheng</title>
<%--    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.7.0/themes/default/easyui.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.7.0/themes/icon.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.7.0/demo/demo.css">--%>
<%--    <script type="text/javascript" src="/jquery-easyui-1.7.0/jquery.min.js"></script>--%>
<%--    <script type="text/javascript" src="/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>--%>
<%--    <%@ include file="common.jsp" %>--%>
    <jsp:include page="/WEB-INF/views/common.jsp"/>
</head>
<body class="easyui-layout">
<%--<div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>--%>
<%--<div data-options="region:'south',title:'',split:false" style="height:20px;"></div>--%>
<%--<div data-options="region:'east',title:'East',split:true" style="width:100px;"></div>--%>

<div data-options="region:'west',title:'操作选项',split:false, collapsible:false" style="width:100px;">
    <a id="btn_des" class="easyui-linkbutton" data-options="iconCls:'icon-lock'" style="width:100px;">DES</a>
    <a id="btn_aes" class="easyui-linkbutton" data-options="iconCls:'icon-lock'" style="width:100px;">AES</a>
    <a id="btn_rsa" class="easyui-linkbutton" data-options="iconCls:'icon-lock'" style="width:100px;">RSA</a>
    <a id="btn_sm1" class="easyui-linkbutton" data-options="iconCls:'icon-lock'" style="width:100px;">SM1</a>
    <a id="btn_sm2" class="easyui-linkbutton" data-options="iconCls:'icon-lock'" style="width:100px;">SM2</a>
    <a id="btn_sm3" class="easyui-linkbutton" data-options="iconCls:'icon-lock'" style="width:100px;">SM3</a>
    <a id="btn_sm4" class="easyui-linkbutton" data-options="iconCls:'icon-lock'" style="width:100px;">SM4</a>
    <a id="btn_sponsor" class="easyui-linkbutton" data-options="iconCls:'icon-tip'" style="width:100px;">赞助</a>
</div>

<div data-options="region:'center',title:''" style="padding:1px;background:#eee;">
    <div id="tt" class="easyui-tabs" data-options="fit:true">
<%--        <div title="Tab1" style="padding:20px;display:none;">--%>
<%--            tab1--%>
<%--        </div>--%>
<%--        <div title="Tab2" data-options="closable:true" style="overflow:auto;padding:20px;display:none;">--%>
<%--            tab2--%>
<%--        </div>--%>
<%--        <div title="Tab3" data-options="iconCls:'icon-reload',closable:true" style="padding:20px;display:none;">--%>
<%--            tab3--%>
<%--        </div>--%>
    </div>
</div>
</body>
</html>

<script>
    // $('#tt').tabs({
    //     border:false,
    //     onSelect:function(title){
    //         // alert(title + ' is selected')
    //     }
    // });

    // $(function(){
    //     $('#tt').tabs('add',{
    //         title: 'test',
    //         id: 'tab1',
    //         selected: true,
    //         closable: true
    //     });
    //     var pp = $('#tt').tabs('getSelected');
    //     pp.panel('open').panel('refresh','welcome/welcomeIndex');
    // });

    // $('#btn_des').bind('click',function(){
    //     // alert("------------> click");
    //     var title = $(this).text();
    //
    //     if(existsTab(title)){
    //         // alert("exists");
    //         selectTab(title);
    //     }else{
    //         // alert("not exists");
    //         newTab(title);
    //     }
    // });

    $('a').bind('click',function(){
        // alert("------------> click");
        var title = $(this).text();
        var id = $(this).attr('id');
        id = id+"_tab";
        // alert(id);

        if(existsTab(title)){
            // alert("exists");
            selectTab(title);
        }else{
            // alert("not exists");
            newTab(title,id);
        }
    });

    function selectTab(title){
        var flag = $('#tt').tabs('select',title);
    }

    function existsTab(title){
        var flag = $('#tt').tabs('exists',title);
        // alert(flag);
        return flag;
    }

    function newTab(title,id){
        $('#tt').tabs('add',{
            title: title,
            id: id,
            selected: true,
            closable: true
        });

       setPanel('tabPage?page='+id);
    }

    function setPanel(url){
        var pp = $('#tt').tabs('getSelected');
        pp.panel('open').panel('refresh',url);
    }

</script>