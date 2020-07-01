
setTextbox('#keySm4');
setTextbox('#ivSm4');
setTextbox('#plaintextSm4');
setTextbox('#ciphertextSm4');
//
setIvInput("#ivSm4",false);
setIvValid("#ivSm4",'length[32,32]')
setModeSelect("#modeSm4","#ivSm4");
//
function form_opt(opt){
    var url;
    opt=='encrypt'?(url='sm4/encrypt'):(url='sm4/decrypt');

    $('#ffSm4').form({
        url: url,
        onSubmit: function(){
            // alert("onSubmit");
            // var algo =$('#algo').val();
            var mode =$('#modeSm4').val();
            var key = $('#keySm4').val();
            var iv = $('#ivSm4').val();
            var plaintext = $('#plaintextSm4').val();
            var ciphertext = $('#ciphertextSm4').val();

            if(mode!='ECB'){
                // alert("MODE= "+mode);
                if(iv.length != 32){
                    alert('iv 长度必须为128bit!');
                    return false;
                }
            }
            if(key.length != 32){
                alert('SM4 密钥长度必须为128bit ');
                return false;
            }

            if(opt=='encrypt'){
                if(plaintext.length % 32!=0 || plaintext.length == 0){
                    alert('明文数据长度必须为128bit的整数倍，请自行填充!');
                    return false;
                }
            }else{
                if(ciphertext.length % 32!=0 || ciphertext.length == 0){
                    alert('密文数据长度必须为128bit的整数倍，请自行填充!');
                    return false;
                }
            }

        },
        success: function(data){
            // alert(data);
            if(opt!='encrypt'){
                $('#plaintextSm4').textbox('setValue',data);
                $('#plaintextSm4Len').html(data.length);
            }else{
                $('#ciphertextSm4').textbox('setValue',data);
                $('#ciphertextSm4Len').html(data.length);
            }
        }
    });
}

$('#btn_encrypt_sm4').bind('click',function(){
    form_opt('encrypt')
    $('#ffSm4').submit();
});

$('#btn_decrypt_sm4').bind('click',function(){
    form_opt('decrypt');
    $('#ffSm4').submit();
});

