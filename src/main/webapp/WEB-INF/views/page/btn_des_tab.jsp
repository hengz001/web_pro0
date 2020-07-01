<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <form id="ffDes" method="post">
        <table  align="center">
            <tr>
                <th>密钥</th>
                <td>
<%--                    <input id="keyDes" name="keyDes" type="text" style="width:600px" class="easyui-textbox" data-options="prompt:'请输入16进制数据'">--%>
                    <input id="keyDes" name="keyDes" type="text" style="width:600px">

                </td>
                <td>
                    <span id="keyDesLen"></span>
                </td>
            </tr>
            <tr>
                <th>IV</th>
                <td>
<%--                    <input id="ivDes" name="ivDes" type="text" style="width:600px" maxlength="16" class="easyui-textbox" data-options="prompt:'请输入16进制数据'">--%>
                        <input id="ivDes" name="ivDes" type="text" style="width:600px" maxlength="16">
                </td>
                <td>
                    <span id="ivDesLen"></span>
                </td>
            </tr>
            <tr>
                <th>明文</th>
                <td>
<%--                    <input id="plaintextDes" name="plaintextDes" type="text" style="width:600px" class="easyui-textbox" data-options="prompt:'请输入16进制数据'">--%>
                        <input id="plaintextDes" name="plaintextDes" type="text" style="width:600px" >
                </td>
                <td>
                    <span id="plaintextDesLen"></span>
                </td>
            </tr>
            <tr>
                <th>密文</th>
                <td>
<%--                    <input id="ciphertextDes" name="ciphertextDes" type="text" style="width:600px" class="easyui-textbox" data-options="prompt:'请输入16进制数据'">--%>
                        <input id="ciphertextDes" name="ciphertextDes" type="text" style="width:600px" >
                </td>
                <td>
                    <span id="ciphertextDesLen"></span>
                </td>
            </tr>
            <tr>
                <th></th>
                <td>
                    <select id="algoDes" name="algoDes" class="easyui-combobox" style="width:100px;">
                        <option value="des">DES</option>
                        <option value="3des">3DES</option>
                    </select>

                    <select id="modeDes" name="modeDes" class="easyui-combobox" style="width:100px;">
                        <option value="ECB">ECB</option>
                        <option value="CBC">CBC</option>
                        <option value="CFB">CFB</option>
                        <option value="OFB">OFB</option>
                    </select>

                    <a id="btn_encrypt_des" class="easyui-linkbutton" style="width:100px;">加密</a>
                    <a id="btn_decrypt_des" class="easyui-linkbutton" style="width:100px;">解密</a>
                </td>
            </tr>
        </table>
    </form>
    <script src="${pageContext.request.contextPath}/js/des.js"></script>
</body>
</html>
