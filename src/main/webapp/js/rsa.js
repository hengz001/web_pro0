
//set
setTextbox('#n');
setTextbox('#e');
setTextbox('#d');
setTextbox('#p');
setTextbox('#q');
setTextbox('#dp');
setTextbox('#dq');
setTextbox('#qmp');
setTextbox('#plaintextRsa');
setTextbox('#ciphertextRsa');


$('#rsaKeyLen').textbox({
    prompt:'RSA长度'
});

//form method
function form_opt_rsa(opt, url){
    var n = $('#n').val();
    var e = $('#e').val();
    var d = $('#d').val();
    var p = $('#p').val();
    var q = $('#q').val();
    var dp = $('#dp').val();
    var dq = $('#dq').val();
    var qmp = $('#qmp').val();

    if(n=="" || e=="" || d=="" || p=="" || q==""
        || dp=="" || dq=="" || qmp==""){
        alert("缺少参数 n e d p q dp dq qmp !");
        return;
    }
    var plaintextRsa = $('#plaintextRsa').val();
    var ciphertextRsa = $('#ciphertextRsa').val();

    switch(opt){
        case 0://解密
            if(ciphertextRsa==""){
                alert("请输入密文数据!");
                return ;
            }
            if(ciphertextRsa.length != n.length){
                alert("密文数据要等于RSA模长!");
                return ;
            }
            break;
        case 1://加密
            if(plaintextRsa==""){
                alert("请输入明文数据!");
                return ;
            }

            if(plaintextRsa.length != n.length){
                alert("NoPadding模式 明文数据要等于RSA模长!");
                return ;
            }
            break;
        case 3://验签
            if(ciphertextRsa==""){
                alert("请输入签名!");
                return ;
            }
            if(ciphertextRsa.length != n.length){
                alert("签名等于RSA模长!");
                return ;
            }
        case 2://签名
            if(plaintextRsa==""){
                alert("请输入签名数据!");
                return ;
            }

            if(plaintextRsa.length != n.length){
                alert("NoHash模式 签名数据要等于RSA模长!");
                return ;
            }
            break;
    }

    $('#ffRsa').form({
        url: url,
        onSubmit: function(){
            // alert(1);
        },
        success:function(data){
            alert('loading ...');
            // alert(data);
            if(opt==1 || opt== 2){
                $('#ciphertextRsa').textbox('setValue',data);
                $('#ciphertextRsaLen').html(data.length);
            }else if(opt==0){
                $('#plaintextRsa').textbox('setValue',data);
                $('#plaintextRsaLen').html(data.length);
            }else if(opt==3){
                alert(data);
            }
        }
    });
    $('#ffRsa').submit();
}

// //encrypt
// $('#rsaEncrypt').bind('click',function(){
//     // alert('加密');
//     form_opt_rsa(1,'rsa/encrypt');
// });
//
// //decrypt
// $('#rsaDecrypt').bind('click',function(){
//     // alert('解密');
//     form_opt_rsa(0,'rsa/decrypt');
// });
//
// //sign
// $('#rsaSign').bind('click',function(){
//     form_opt_rsa(2,'rsa/sign');
// });
//
// //verify
// $('#rsaVerify').bind('click',function(){
//     form_opt_rsa(3,'rsa/verify');
// });

//generate rsa key
function form_opt_rsa_gen(url){
    var rsaKeyLen = $('#rsaKeyLen').val();
    // alert(rsaKeyLen.length);
    if(rsaKeyLen.length==0){
        alert("请输入RSA长度!")
        return ;
    }

    $.ajax({
        type:"POST",
        url:url,
        data: {rsaKeyLen:rsaKeyLen},
        success:function(data){
            var n = data.n;
            var e = data.e;
            var d = data.d;
            var p = data.p;
            var q = data.q;
            var dp = data.dp;
            var dq = data.dq;
            var qmp = data.qmp;
            // alert("n= "+n);

            $('#n').textbox('setValue',data.n);
            $("#nLen").html(data.n.length);
            $('#e').textbox('setValue',data.e);
            $("#eLen").html(data.e.length);
            $('#d').textbox('setValue',data.d);
            $("#dLen").html(data.d.length);
            $('#p').textbox('setValue',data.p);
            $("#pLen").html(data.p.length);
            $('#q').textbox('setValue',data.q);
            $("#qLen").html(data.q.length);
            $('#dp').textbox('setValue',data.dp);
            $("#dpLen").html(data.dp.length);
            $('#dq').textbox('setValue',data.dq);
            $("#dqLen").html(data.dq.length);
            $('#qmp').textbox('setValue',data.qmp);
            $("#qmpLen").html(data.qmp.length);
            alert("密钥生成 successfully.");

        },
        error:function(){
            alert("请检查参数! RSA长度");
        }
    });
}

