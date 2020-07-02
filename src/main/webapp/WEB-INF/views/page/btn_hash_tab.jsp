<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <form id="ffHash" method="post">
        <table align="center">
            <tr>
                <th>输入</th>
                <td>
                    <input type="text" style="width:800px;" id="inHash" name="inHash">
                    <span id="inHashLen"></span>
                </td>

            </tr>
            <tr>
                <th>输出</th>
                <td>
                    <input type="text" style="width:800px;" id="outHash" name="outHash">
                    <span id="outHashLen"></span>
                </td>
            </tr>
            <tr>
                <th></th>
                <td align="center">
                    <select id="algoHash" name="algoHash" style="width:200px;"></select>
                    <a id="btn_Hash" onclick="form_opt_hash('${pageContext.request.contextPath}/hash/digest');" style="width:200px;" class="easyui-linkbutton" >Hash</a>
                </td>
            </tr>
        </table>
    </form>
    <script src="${pageContext.request.contextPath}/js/hash.js"></script>
    <script>
        initCombobox('${pageContext.request.contextPath}/hash/hashList');
    </script>
</body>
</html>