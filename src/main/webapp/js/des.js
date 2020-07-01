

setTextbox('#keyDes');
setTextbox('#ivDes');
setTextbox('#plaintextDes');
setTextbox('#ciphertextDes');
//
setIvInput("#ivDes",false);
setIvValid("#ivDes",'length[16,16]')
setModeSelect("#modeDes","#ivDes");


function form_opt(opt){
    var url;
    opt=='encrypt'?(url='des/encrypt'):(url='des/decrypt');

    $('#ffDes').form({
        url: url,
        onSubmit: function(){
            // alert("onSubmit");
            var algo =$('#algoDes').val();
            var mode =$('#modeDes').val();
            var key = $('#keyDes').val();
            var iv = $('#ivDes').val();
            var plaintext = $('#plaintextDes').val();
            var ciphertext = $('#ciphertextDes').val();

            if(mode!='ECB'){
                // alert("mode= "+mode);
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
                $('#plaintextDes').textbox('setValue',data);
                $('#plaintextDesLen').html(data.length);
            }else{
                $('#ciphertextDes').textbox('setValue',data);
                $('#ciphertextDesLen').html(data.length);
            }
        }
    });
}

$('#btn_encrypt_des').bind('click',function(){
    form_opt('encrypt')
    $('#ffDes').submit();
});

$('#btn_decrypt_des').bind('click',function(){
    form_opt('decrypt');
    $('#ffDes').submit();
});

