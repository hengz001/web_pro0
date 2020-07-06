
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