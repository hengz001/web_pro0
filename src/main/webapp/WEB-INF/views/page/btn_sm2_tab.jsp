<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <form id="ffSM2">
        <table align="center">
            <tr>
                <th>公钥</th>
                <td>
                    <input type="text" id="sm2Pub" name="sm2Pub" style="width:600px">
                    <span id="sm2PubLen"></span>
                </td>

            </tr>
            <tr>
                <th>私钥</th>
                <td>
                    <input type="text" id="sm2Pri" name="sm2Pri" style="width:600px">
                    <span id="sm2PriLen"></span>
                </td>

            </tr>
            <tr>
                <th>用户ID</th>
                <td>
                    <input type="text" id="sm2UserId" name="sm2UserId" style="width:600px">
                    <span id="sm2UserIdLen"></span>
                </td>

            </tr>

            <tr>
                <th>输入</th>
                <td>
                    <input type="text" id="sm2In" name="sm2In" style="width:600px">
                    <span id="sm2InLen"></span>
                </td>

            </tr>
            <tr>
                <th>输出</th>
                <td>
                    <input type="text" id="sm2Out" name="sm2Out" style="width:600px">
                    <span id="sm2OutLen"></span>
                </td>

            </tr>
            <tr>
                <th></th>
                <td>
                    <a id="btn_gen_sm2" onclick="form_opt_sm2_gen('${pageContext.request.contextPath}/sm2/gen')" class="easyui-linkbutton" style="width:100px;">密钥生成</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a id="btn_enc_sm2" onclick="form_opt_sm2(1,'${pageContext.request.contextPath}/sm2/enc')" class="easyui-linkbutton" style="width:100px;">加密</a>
                    <a id="btn_dec_sm2" onclick="form_opt_sm2(0,'${pageContext.request.contextPath}/sm2/dec')" class="easyui-linkbutton" style="width:100px;">解密</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a id="btn_sign_sm2" onclick="form_opt_sm2(3,'${pageContext.request.contextPath}/sm2/sign')" class="easyui-linkbutton" style="width:100px;">签名</a>
                    <a id="btn_verify_sm2" onclick="form_opt_sm2(4,'${pageContext.request.contextPath}/sm2/verify')" class="easyui-linkbutton" style="width:100px;">验签</a>
                </td>
            </tr>
        </table>
    </form>

<script src="${pageContext.request.contextPath}/js/sm2.js"></script>

</body>
</html>