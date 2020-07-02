<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <form id="ffSm4" method="post">
        <table  align="center">
            <tr>
                <th>密钥</th>
                <td>
                    <input id="keySm4" name="keySm4" type="text" style="width:600px">
                </td>
                <td>
                    <span id="keySm4Len"></span>
                </td>
            </tr>
            <tr>
                <th>IV</th>
                <td>
                    <input id="ivSm4" name="ivSm4" type="text" style="width:600px">
                </td>
                <td>
                    <span id="ivSm4Len"></span>
                </td>
            </tr>
            <tr>
                <th>明文</th>
                <td>
                    <input id="plaintextSm4" name="plaintextSm4" type="text" style="width:600px">
                </td>
                <td>
                    <span id="plaintextSm4Len"></span>
                </td>
            </tr>
            <tr>
                <th>密文</th>
                <td>
                    <input id="ciphertextSm4" name="ciphertextSm4" type="text" style="width:600px">
                </td>
                <td>
                    <span id="ciphertextSm4Len"></span>
                </td>
            </tr>
            <tr>
                <th></th>
                <td>
                    <select id="modeSm4" name="modeSm4" class="easyui-combobox" style="width:100px;">
                        <option value="ECB">ECB</option>
                        <option value="CBC">CBC</option>
    <%--                    <option value="CFB">CFB</option>--%>
    <%--                    <option value="OFB">OFB</option>--%>
                    </select>

                    <a id="btn_encrypt_sm4" onclick="form_opt(1,'${pageContext.request.contextPath}/sm4/encrypt')" class="easyui-linkbutton" style="width:60px;">加密</a>
                    <a id="btn_decrypt_sm4" onclick="form_opt(0,'${pageContext.request.contextPath}/sm4/decrypt')" class="easyui-linkbutton" style="width:60px;">解密</a>
                </td>
            </tr>
        </table>
    </form>

    <script src="${pageContext.request.contextPath}/js/sm4.js"></script>
</body>
</html>
