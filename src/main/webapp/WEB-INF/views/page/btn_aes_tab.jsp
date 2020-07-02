<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

    <form id="ffAes" method="post">
        <table align="center">
            <tr>
                <th>密钥</th>
                <td>
<%--                    <input id="keyAes" name="keyAes" type="text" style="width:600px" class="easyui-textbox" data-options="prompt:'请输入16进制数据'">--%>
                    <input id="keyAes" name="keyAes" type="text" style="width:600px">
                    <span id="keyAesLen"></span>
                </td>
            </tr>
            <tr>
                <th>IV</th>
                <td>
<%--                    <input id="ivAes" name="ivAes" type="text" style="width:600px" class="easyui-textbox" data-options="prompt:'请输入16进制数据'">--%>
                    <input id="ivAes" name="ivAes" type="text" style="width:600px">
                    <span id="ivAesLen"></span>
                </td>
            </tr>
            <tr>
                <th>明文</th>
                <td>
<%--                    <input id="plaintextAes" name="plaintextAes" type="text" style="width:600px" class="easyui-textbox" data-options="prompt:'请输入16进制数据'">--%>
                    <input id="plaintextAes" name="plaintextAes" type="text" style="width:600px">
                    <span id="plaintextAesLen"></span>
                </td>

            </tr>
            <tr>
                <th>密文</th>
                <td>
<%--                    <input id="ciphertextAes" name="ciphertextAes" type="text" style="width:600px" class="easyui-textbox" data-options="prompt:'请输入16进制数据'">--%>
                    <input id="ciphertextAes" name="ciphertextAes" type="text" style="width:600px">
                    <span id="ciphertextAesLen"></span>
                </td>
            </tr>
            <tr>
                <th></th>
                <td>

                    <select id="algoAes" name="algoAes" class="easyui-combobox" style="width:100px;">
                        <option value="aes128">AES-128</option>
                        <option value="aes192">AES-192</option>
                        <option value="aes256">AES-256</option>
                    </select>
                    <select id="modeAes" name="modeAes" class="easyui-combobox" style="width:100px;">
                        <option value="ECB">ECB</option>
                        <option value="CBC">CBC</option>
                        <option value="CFB">CFB</option>
                        <option value="OFB">OFB</option>
                    </select>
                    <a id="btn_encrypt_aes" class="easyui-linkbutton" style="width:100px;" onclick="form_opt_aes(1,'${pageContext.request.contextPath}/aes/encrypt')">加密</a>
                    <a id="btn_decrypt_aes" class="easyui-linkbutton" style="width:100px;" onclick="form_opt_aes(0,'${pageContext.request.contextPath}/aes/decrypt')">解密</a>
                </td>
            </tr>
        </table>
    </form>
    <script src="${pageContext.request.contextPath}/js/aes.js"></script>
</body>
</html>
