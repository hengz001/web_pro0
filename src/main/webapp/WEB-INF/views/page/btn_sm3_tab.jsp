<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <form id="ffSM3" method="post">
        <table align="center">
            <tr>
                <th>输入</th>
                <td>
                    <input type="text" style="width:800px;" id="inSM3" name="inSM3">
                    <span id="inSM3Len"></span>
                </td>

            </tr>
            <tr>
                <th>输出</th>
                <td>
                    <input type="text" style="width:800px;" id="outSM3" name="outSM3">
                    <span id="outSM3Len"></span>
                </td>
            </tr>
            <tr>
                <th></th>
                <td align="center">
                    <a id="btn_SM3" onclick="hashSm3('${pageContext.request.contextPath}/sm3/hash')" style="width:200px;" class="easyui-linkbutton" >SM3</a>
                </td>
            </tr>
        </table>
    </form>
<script src="${pageContext.request.contextPath}/js/sm3.js"></script>
</body>
</html>