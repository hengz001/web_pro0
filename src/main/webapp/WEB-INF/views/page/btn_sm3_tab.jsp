<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <form id="ffSM3" method="post">
        <table align="center">
            <tr>
                <th>输入</th>
                <td><input type="text" style="width:800px;" id="inSM3" name="inSM3"></td>

            </tr>
            <tr>
                <th>输出</th>
                <td><input type="text" style="width:800px;" id="outSM3" name="outSM3"></td>
            </tr>
            <tr>
                <th></th>
                <td align="center">
                    <a id="btn_SM3" style="width:200px;" class="easyui-linkbutton" >SM3</a>
                </td>
            </tr>
        </table>
    </form>
<script>
    setTextbox('#inSM3');
    setTextbox('#outSM3');

    $('#ffSM3').form({
        url:'/sm3/hash',
        onSubmit:function(){},
        success:function (data) {
            // alert(data);
            $('#outSM3').textbox('setValue',data);
        },
    })

    $('#btn_SM3').bind('click',function () {
        $('#ffSM3').submit();
        // alert(1);
    });
</script>
</body>
</html>