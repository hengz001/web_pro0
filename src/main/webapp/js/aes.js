
setIvValid("#ivAes",'length[32,32]')

setTextbox('#keyAes');
setTextbox('#ivAes');
setTextbox('#plaintextAes');
setTextbox('#ciphertextAes');
setModeSelect("#modeAes","#ivAes");
setIvInput("#ivAes",false);

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
