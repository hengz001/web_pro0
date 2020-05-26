<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
<%--    <jsp:include page="/WEB-INF/views/common.jsp"/>--%>
</head>
<body>
    <form id="ff" method="post" style="text-align:center">
<%--    <form id="ff" method="post" align="center">--%>
        <div>
            <label>密钥</label>
            <input id="key" name="key" type="text" style="width:600px"><span id="keyLen"></span>
<%--            <input id="key" class="easyui-textbox" data-options="iconCls:'icon-lock',prompt:'请输入16进制数据'" style="width:600px">--%>
        </div>
        <div>
            <label>&nbsp;IV&nbsp;</label>
            <input id="iv" name="iv" type="text" style="width:600px" maxlength="16"><span id="ivLen"></span>
            <%--            <input id="key" class="easyui-textbox" data-options="iconCls:'icon-lock',prompt:'请输入16进制数据'" style="width:600px">--%>
        </div>
        <div>
            <label>明文</label>
            <input id="plaintext" name="plaintext" type="text" style="width:600px"><span id="plaintextLen"></span>
<%--            <input id="plaintext" class="easyui-textbox" data-options="iconCls:'icon-lock',prompt:'请输入16进制数据'" style="width:600px">--%>
        </div>
        <div>
            <label>密文</label>
            <input id="ciphertext" name="ciphertext" type="text" style="width:600px"><span id="ciphertextLen"></span>
<%--            <input id="chiphertext" class="easyui-textbox" data-options="iconCls:'icon-lock',prompt:'请输入16进制数据'" style="width:600px">--%>
        </div>
        <div>
            <select id="algo" name="algo" class="easyui-combobox" style="width:100px;">
                <option value="des">DES</option>
                <option value="3des">3DES</option>
            </select>

            <select id="mode" name="mode" class="easyui-combobox" style="width:100px;">
                <option value="ECB">ECB</option>
                <option value="CBC">CBC</option>
                <option value="CFB">CFB</option>
                <option value="OFB">OFB</option>
            </select>

            <a id="btn_encrypt" class="easyui-linkbutton" style="width:100px;">加密</a>
            <a id="btn_decrypt" class="easyui-linkbutton" style="width:100px;">解密</a>
        </div>

    </form>

    <script>
        $('input').textbox({
            prompt:'请输入16进制数据'
        });
        $('#iv').textbox({
            validType: 'length[16,16]'
        });

        showLen('key','keyLen');
        showLen('iv','ivLen');
        showLen('plaintext','plaintextLen');
        showLen('ciphertext','ciphertextLen');

        function showLen(id, lenId){
            // alert(1);
            $('#'+id).textbox('textbox').bind('input propertychange', function(e){
                var text = $('#'+id).textbox('getText');
                var len = text.length;
                $('#'+lenId).html(len);
            });
        }

        setIvInput(false);

        $('#mode').combobox({
            onChange:function(){
                var vs = $('#mode option:selected').val();
                vs=='ecb'?setIvInput(false):setIvInput(true);
            }
        });

        function setIvInput(flag){
            if(flag){
                $('#iv').textbox('enable');
            }else{
                $('#iv').textbox('disable');
            }
        }

        function form_opt(opt){
            var url;
            opt=='encrypt'?(url='des/encrypt'):(url='des/decrypt');

            $('#ff').form({
                url: url,
                onSubmit: function(){
                    // alert("onSubmit");
                    var algo =$('#algo').val();
                    var mode =$('#mode').val();
                    var key = $('#key').val();
                    var iv = $('#iv').val();
                    var plaintext = $('#plaintext').val();
                    var ciphertext = $('#ciphertext').val();

                    if(mode!='ECB'){
                        if(iv.length != 16){
                            alert('iv 长度必须为64bit!');
                            return false;
                        }
                    }
                    if(algo=='des'){
                        if(key.length != 16){
                            alert('DES 密钥长度必须为64bit!');
                            return false;
                        }
                    }else{
                        if(key.length != 32 && key.length != 48){
                            alert('3DES 密钥长度必须为128bit 或192bit!');
                            return false;
                        }
                    }

                    if(opt=='encrypt'){
                        if(plaintext.length % 16!=0 || plaintext.length == 0){
                            alert('明文数据长度必须为64bit的整数倍，请自行填充!');
                            return false;
                        }
                    }else{
                        if(ciphertext.length % 16!=0 || ciphertext.length == 0){
                            alert('密文数据长度必须为64bit的整数倍，请自行填充!');
                            return false;
                        }
                    }

                },
                success: function(data){
                    // alert(data);
                    if(opt!='encrypt'){
                        $('#plaintext').textbox('setValue',data);
                        $('#plaintextLen').html(data.length);
                    }else{
                        $('#ciphertext').textbox('setValue',data);
                        $('#ciphertextLen').html(data.length);
                    }
                }
            });
        }

        $('#btn_encrypt').bind('click',function(){
            form_opt('encrypt')
            $('#ff').submit();
        });

        $('#btn_decrypt').bind('click',function(){
            form_opt('decrypt');
            $('#ff').submit();
        });


    </script>
</body>
</html>
