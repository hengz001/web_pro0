<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <form id="ffRsa" method="post">
        <table align="center">
            <tr>
                <th>n</th>
                <td>
                    <input id="n" name="n" type="text" style="width:600px">
                    <span id="nLen"></span>

                </td>
            </tr>
            <tr>
                <th>e</th>
                <td>
                    <input id="e" name="e" type="text" style="width:600px" >
                    <span id="eLen"></span>
                </td>
            </tr>
            <tr>
                <th>d</th>
                <td>
                    <input id="d" name="d" type="text" style="width:600px" >
                    <span id="dLen"></span>
                </td>
            </tr>
            <tr>
                <th>p</th>
                <td>
                    <input id="p" name="p" type="text" style="width:600px" >
                    <span id="pLen"></span>
                </td>
            </tr>
            <tr>
                <th>q</th>
                <td>
                    <input id="q" name="q" type="text" style="width:600px" >
                    <span id="qLen"></span>
                </td>
            </tr>
            <tr>
                <th>dp</th>
                <td>
                    <input id="dp" name="dp" type="text" style="width:600px" >
                    <span id="dpLen"></span>
                </td>
            </tr>
            <tr>
                <th>dq</th>
                <td>
                    <input id="dq" name="dq" type="text" style="width:600px" >
                    <span id="dqLen"></span>
                </td>
            </tr>
            <tr>
                <th>qmp</th>
                <td>
                    <input id="qmp" name="qmp" type="text" style="width:600px" >
                    <span id="qmpLen"></span>
                </td>
            </tr>
            <tr>
                <th>明文</th>
                <td>
                    <input id="plaintextRsa" name="plaintextRsa" type="text" style="width:600px" >
                    <span id="plaintextRsaLen"></span>
                </td>
            </tr>
            <tr>
                <th>密文</th>
                <td>
                    <input id="ciphertextRsa" name="ciphertextRsa" type="text" style="width:600px" >
                    <span id="ciphertextRsaLen"></span>
                </td>
            </tr>

            <tr>
                <th>
                </th>
                <td>
                    <input class="easyui-numberbox" id="rsaKeyLen" name="rsaKeyLen" style="width:100px" class="easyui-textbox" data-options="prompt:'密钥长度'">
                    <a id="generateRsa" onclick="form_opt_rsa_gen('${pageContext.request.contextPath}/rsa/generate')" class="easyui-linkbutton" style="width:100px;">生成密钥</a>
                    <a id="rsaEncrypt" onclick="form_opt_rsa(1,'${pageContext.request.contextPath}/rsa/encrypt');" class="easyui-linkbutton" style="width:64px;">加密</a>
                    <a id="rsaDecrypt" onclick="form_opt_rsa(0,'${pageContext.request.contextPath}/rsa/decrypt');" class="easyui-linkbutton" style="width:64px;">解密</a>
                    <a id="rsaSign" onclick="form_opt_rsa(2,'${pageContext.request.contextPath}/rsa/sign');" class="easyui-linkbutton" style="width:64px;">签名</a>
                    <a id="rsaVerify" onclick="form_opt_rsa(3,'${pageContext.request.contextPath}/rsa/verify');" class="easyui-linkbutton" style="width:64px;">验签</a>
                </td>
            </tr>
        </table>
    </form>

    <script src="${pageContext.request.contextPath}/js/rsa.js"></script>
</body>
</html>
