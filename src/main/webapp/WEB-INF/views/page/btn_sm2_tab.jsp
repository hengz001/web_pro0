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


<script>
    setTextbox('#sm2Pub');
    setTextbox('#sm2Pri');
    setTextbox('#sm2UserId');
    setTextbox('#sm2In');
    setTextbox('#sm2Out');
    function form_opt_sm2_gen(url){
        $.ajax({
            type:'POST',
            url:url,
            success:function(data){
                // alert("success");
                // alert(data.PublicKey);
                // alert(data.PrivateKey);
                $('#sm2Pub').textbox('setValue',data.PUB_KEY);
                $('#sm2Pri').textbox('setValue',data.PRI_KEY);
                $('#sm2PubLen').html(data.PUB_KEY.length);
                $('#sm2PriLen').html(data.PRI_KEY.length);
            },
            error:function(){
                alert("error");
            }
        });
        // alert(1);
    }

    function form_opt_sm2(opt,url){
        $('#ffSM2').form({
            url:url,
            onSubmit:function(){
                // alert('onsubmit');
                sm2Pri = $('#sm2Pri').val();
                sm2Pub = $('#sm2Pub').val();
                sm2UserId = $('#sm2UserId').val();
                sm2In = $('#sm2In').val();
                sm2Out = $('#sm2Out').val();

                if(sm2Pri.length==0 || sm2Pub.length==0 || sm2In.length==0){
                    alert('请输入数据 公私钥 明文或密文数据！');
                    return ;
                }

                if(opt == 3){
                    if(sm2UserId.length==0){
                        alert('请输入数据 用户ID！');
                        return ;
                    }
                }else if(opt == 4){
                    if(sm2UserId.length==0 || sm2Out.length==0){
                        alert('请输入数据 用户ID和签名结果！');
                        return ;
                    }
                }

            },
            success:function(data){
                // alert('success');
                if(opt!=4){
                    $('#sm2Out').textbox('setValue',data);
                    $('#sm2OutLen').html(data.length);
                }else{
                    // alert(data);
                    alert(data=='true' ? '验签成功' : '验签失败');
                }

            },
            error:function(){
                // alert('error')
            },
        });
        // alert(opt + " " +url);
        $('#ffSM2').submit();
    }
</script>

</body>
</html>