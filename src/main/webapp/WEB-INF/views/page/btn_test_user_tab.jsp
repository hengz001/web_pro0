<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<body>--%>
<%--    <table id="dg" title="My Users" class="easyui-datagrid" style="width:600px;height:400px;"--%>
<%--        url="/testUser/getUsers"--%>
<%--        toolbar="#toolbar"--%>
<%--        rownumbers="true" fitColumns="true" singleSelect="true">--%>
<%--        <thead>--%>
<%--            <tr>--%>
<%--                <th field="firstname" width="20">First Name</th>--%>
<%--                <th field="lastname" width="20">Last Name</th>--%>
<%--                <th field="phone" width="20">Phone</th>--%>
<%--                <th field="email" width="20">Email</th>--%>
<%--            </tr>--%>
<%--        </thead>--%>
<%--    </table>--%>
<%--    <div id="toolbar">--%>
<%--        <a herf="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>--%>
<%--        <a herf="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>--%>
<%--        <a herf="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>--%>
<%--    </div>--%>

<%--    <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"--%>
<%--        closed="true" buttons="#dlg-buttons">--%>
<%--        <div class="ftitle">User Information</div>--%>
<%--        <form id="fm" methon="post">--%>
<%--            <table>--%>
<%--                <tr>--%>
<%--                    <th>--%>
<%--                        <label>First Name:</label>--%>
<%--                    </th>--%>
<%--                    <td>--%>
<%--                        <input name="id" hidden="true">--%>
<%--                        <input name="firstname" class="easyui-validatebox" required="true">--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th>--%>
<%--                        <label>Last Name:</label>--%>
<%--                    </th>--%>
<%--                    <td>--%>
<%--                        <input name="lastname" class="easyui-validatebox" required="true">--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th>--%>
<%--                        <label>Phone:</label>--%>
<%--                    </th>--%>
<%--                    <td>--%>
<%--                        <input name="phone">--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th>--%>
<%--                        <label>Email:</label>--%>
<%--                    </th>--%>
<%--                    <td>--%>
<%--                        <input name="email" class="easyui-validatebox" required="true">--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th></th>--%>
<%--                    <td>--%>
<%--                        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser();">Save</a>--%>
<%--                        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--            </table>--%>
<%--        </form>--%>
<%--    </div>--%>

<%--<script>--%>
<%--    function newUser(){--%>
<%--        $('#dlg').dialog('open').dialog('setTitle','New User');--%>
<%--        $('#fm').form('clear');--%>
<%--        url = '/testUser/saveUser';--%>
<%--    }--%>

<%--    function saveUser(){--%>
<%--        $('#fm').form('submit',{--%>
<%--            url:url,--%>
<%--            onSubmit:function(){--%>
<%--                return $(this).form('validate');--%>
<%--            },--%>
<%--            success:function(result){--%>
<%--                var result = eval("("+result+")");--%>
<%--                if(result.errorMsg){--%>
<%--                    $.message.show({--%>
<%--                        title:'Error',--%>
<%--                        msg: result.errorMsg--%>
<%--                    });--%>
<%--                }else{--%>
<%--                    $('#dlg').dialog('close');--%>
<%--                    $('#dg').datagrid('reload');--%>
<%--                }--%>
<%--            }--%>
<%--        });--%>
<%--    }--%>

<%--    function editUser(){--%>
<%--        // alert(1);--%>
<%--        var row = $('#dg').datagrid('getSelected');--%>
<%--        if(row){--%>
<%--            // alert(row.id);--%>
<%--            $('#dlg').dialog('open').dialog('setTitle','Edit User');--%>
<%--            $('#fm').form('load',row);--%>
<%--            url = 'testUser/editUser?id='+row.id;--%>
<%--        }--%>
<%--    }--%>

<%--    function destroyUser(){--%>
<%--        var row = $('#dg').datagrid('getSelected');--%>
<%--        if(row){--%>
<%--            // alert(row.id);--%>
<%--            $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){--%>
<%--                if(r){--%>
<%--                  $.post('/testUser/destroyUser',{id:row.id},function(result){--%>
<%--                      if(result.success){--%>
<%--                          $('#dg').datagrid('reload');--%>
<%--                      }else{--%>
<%--                          $.messager.show({--%>
<%--                              title:'Error',--%>
<%--                              msg:result.errorMsg--%>
<%--                          },'json');--%>
<%--                      }--%>
<%--                  });--%>
<%--                }--%>
<%--            })--%>
<%--        }--%>
<%--    }--%>

<%--</script>--%>
<%--</body>--%>
<%--</html>--%>