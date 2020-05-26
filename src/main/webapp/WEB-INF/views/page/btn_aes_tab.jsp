<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <%--    <jsp:include page="/WEB-INF/views/common.jsp"/>--%>
</head>
<body>

<form id="ffAes" method="post" style="text-align:center">
<%--<form id="ffAes" method="post" align="center">--%>
    <div>
        <label>密钥</label>
        <input id="keyAes" name="keyAes" type="text" style="width:600px"><span id="keyAesLen"></span>
        <%--            <input id="keyAes" class="easyui-textbox" data-options="iconCls:'icon-lock',prompt:'请输入16进制数据'" style="width:600px">--%>
    </div>
    <div>
        <label>&nbsp;IV&nbsp;</label>
        <input id="ivAes" name="ivAes" type="text" style="width:600px" maxlength="16"><span id="ivAesLen"></span>
        <%--            <input id="keyAes" class="easyui-textbox" data-options="iconCls:'icon-lock',prompt:'请输入16进制数据'" style="width:600px">--%>
    </div>
    <div>
        <label>明文</label>
        <input id="plaintextAes" name="plaintextAes" type="text" style="width:600px"><span id="plaintextAesLen"></span>
        <%--            <input id="plaintextAes" class="easyui-textbox" data-options="iconCls:'icon-lock',prompt:'请输入16进制数据'" style="width:600px">--%>
    </div>
    <div>
        <label>密文</label>
        <input id="ciphertextAes" name="ciphertextAes" type="text" style="width:600px"><span id="ciphertextAesLen"></span>
        <%--            <input id="chiphertext" class="easyui-textbox" data-options="iconCls:'icon-lock',prompt:'请输入16进制数据'" style="width:600px">--%>
    </div>
    <div>
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

        <a id="btn_encrypt_aes" class="easyui-linkbutton" style="width:100px;">加密</a>
        <a id="btn_decrypt_aes" class="easyui-linkbutton" style="width:100px;">解密</a>
    </div>

</form>

<script>
    $('input').textbox({
        prompt:'请输入16进制数据'
    });
    $('#ivAes').textbox({
        validType: 'length[32,32]'
    });

    showLen('keyAes','keyAesLen');
    showLen('ivAes','ivAesLen');
    showLen('plaintextAes','plaintextAesLen');
    showLen('ciphertextAes','ciphertextAesLen');

    function showLen(id, lenId){
        // alert(1);
        $('#'+id).textbox('textbox').bind('input propertychange', function(e){
            var text = $('#'+id).textbox('getText');
            var len = text.length;
            $('#'+lenId).html(len);
        });
    }

    setIvInput(false);

    $('#modeAes').combobox({
        onChange:function(){
            var vs = $('#modeAes option:selected').val();
            vs=='ecb'?setIvInput(false):setIvInput(true);
        }
    });

    function setIvInput(flag){
        if(flag){
            $('#ivAes').textbox('enable');
        }else{
            $('#ivAes').textbox('disable');
        }
    }

    function form_opt(opt){
        var url;
        opt=='encrypt'?(url='aes/encrypt'):(url='aes/decrypt');

        $('#ffAes').form({
            url: url,
            onSubmit: function(){
                // alert("onSubmit");
                var algoAes =$('#algoAes').val();
                var modeAes =$('#modeAes').val();
                var keyAes = $('#keyAes').val();
                var iv = $('#ivAes').val();
                var plaintextAes = $('#plaintextAes').val();
                var ciphertextAes = $('#ciphertextAes').val();

                if(modeAes!='ECB'){
                    if(iv.length != 32){
                        alert('iv 长度必须为128bit!');
                        return false;
                    }
                }
                // alert(algoAes);
                if(algoAes=="aes128"){
                    if(keyAes.length != 32 ){
                        alert('AES128 密钥长度必须为128bit!');
                        return false;
                    }
                }
                else if(algoAes=="aes192"){
                    if(keyAes.length != 48){
                        alert('AES192 密钥长度必须为192bit!');
                        return false;
                    }
                }
                else if(algoAes=="aes256"){
                    if(keyAes.length != 64){
                        alert('AES256 密钥长度必须为256bit!');
                        return false;
                    }
                }
                // if(keyAes.length != 32 && keyAes.length != 48 && keyAes.length != 64){
                //     alert('AES 密钥长度必须为128bit 192bit 256bit!');
                //     return false;
                // }

                if(opt=='encrypt'){
                    if(plaintextAes.length % 32!=0 || plaintextAes.length == 0){
                        alert('明文数据长度必须为128bit的整数倍，请自行填充!');
                        return false;
                    }
                }else{
                    if(ciphertextAes.length % 32!=0 || ciphertextAes.length == 0){
                        alert('密文数据长度必须为128bit的整数倍，请自行填充!');
                        return false;
                    }
                }

            },
            success: function(data){
                // alert(data);
                if(opt!='encrypt'){
                    $('#plaintextAes').textbox('setValue',data);
                    $('#plaintextAesLen').html(data.length);
                }else{
                    $('#ciphertextAes').textbox('setValue',data);
                    $('#ciphertextAesLen').html(data.length);
                }
            }
        });
    }

    $('#btn_encrypt_aes').bind('click',function(){
        form_opt('encrypt')
        $('#ffAes').submit();
    });

    $('#btn_decrypt_aes').bind('click',function(){
        form_opt('decrypt');
        $('#ffAes').submit();
    });


</script>
</body>
</html>
