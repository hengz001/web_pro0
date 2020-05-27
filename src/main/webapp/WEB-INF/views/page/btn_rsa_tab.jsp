<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <jsp:include page="/WEB-INF/views/common.jsp"/>
</head>
<body>
    <%--RSA--%>
    <form id="ffRsa" method="post" style="text-align: center">
        <div>
            <label>n</label>
            <input id="n" name="n" type="text" style="width:600px"><span id="nLen"></span>
        </div>
        <div>
            <label>e</label>
            <input id="e" name="e" type="text" style="width:600px"><span id="eLen"></span>
        </div>
        <div>
            <label>d</label>
            <input id="d" name="d" type="text" style="width:600px"><span id="dLen"></span>
        </div>
        <div>
            <label>p</label>
            <input id="p" name="p" type="text" style="width:600px"><span id="pLen"></span>
        </div>
        <div>
            <label>q</label>
            <input id="q" name="q" type="text" style="width:600px"><span id="qLen"></span>
        </div>
        <div>
            <label>dp</label>
            <input id="dp" name="dp" type="text" style="width:600px"><span id="dpLen"></span>
        </div>
        <div>
            <label>dq</label>
            <input id="dq" name="dq" type="text" style="width:600px"><span id="dqLen"></span>
        </div>
        <div>
            <label>qmp</label>
            <input id="qmp" name="qmp" type="text" style="width:600px"><span id="qmpLen"></span>
        </div>
        <div>
            <label>明文</label>
            <input id="plaintextRsa" name="plaintextRsa" type="text" style="width:600px"><span id="plaintextRsaLen"></span>

        </div>
        <div>
            <label>密文</label>
            <input id="ciphertextRsa" name="ciphertextRsa" type="text" style="width:600px"><span id="ciphertextRsaLen"></span>
        </div>

        <div>
            <input class="easyui-numberbox" id="keyLen" name="keyLen"/>
            <a id="generateRsa" class="easyui-linkbutton" style="width:100px;">生成密钥</a>
            <a id="rsaEncrypt" class="easyui-linkbutton" style="width:100px;">加密</a>
            <a id="rsaDecrypt" class="easyui-linkbutton" style="width:100px;">解密</a>
        </div>
    </form>


<script>
    //form method
    function formOpt(opt, url){
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

        // if(opt==1 && plaintextRsa==""){
        //     alert("请输入明文数据");
        //     return ;
        // }else if(opt==0 && ciphertextRsa==""){
        //     alert("请输入密文数据");
        //     return ;
        // }
        if(opt==1){
            if(plaintextRsa==""){
                alert("请输入明文数据");
                return ;
            }

            if(plaintextRsa.length != n.length){
                alert("NoPadding模式 明文数据要等于RSA模长");
                return ;
            }

        }else if(opt==0){
            if(ciphertextRsa==""){
                alert("请输入密文数据");
                return ;
            }
            if(ciphertextRsa.length != n.length){
                alert("NoPadding模式 密文数据要等于RSA模长");
                return ;
            }
        }

        $('#ffRsa').form({
            url: url,
            onSubmit: function(){
                // alert(1);
            },
            success:function(data){
                alert('successfully.');
                // alert(data);
                if(opt==1){
                    $('#ciphertextRsa').textbox('setValue',data);
                    $('#ciphertextRsaLen').html(data.length);
                }else{
                    $('#plaintextRsa').textbox('setValue',data);
                    $('#plaintextRsaLen').html(data.length);
                }
            }
        });
        $('#ffRsa').submit();
    }

    //encrypt
    $('#rsaEncrypt').bind('click',function(){
        // alert('加密');
        formOpt(1,'rsa/encrypt');
    });

    //decrypt
    $('#rsaDecrypt').bind('click',function(){
        // alert('解密');
        formOpt(0,'rsa/decrypt');
    });

    //generate rsa key
    $('#generateRsa').bind('click',function(){
        var keyLen = $('#keyLen').val();
        // alert(keyLen.length);
        if(keyLen.length==0){
            alert("请输入RSA长度!")
            return ;
        }

        $.ajax({
            type:"POST",
            url:"rsa/generate",
            data: {keyLen:keyLen},
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
    });

    //set prompt
    $('input').textbox({
        prompt:'请输入16进制数据'
    });

    $('#keyLen').textbox({
        prompt:'RSA长度'
    });

    //set
    showLen('n','nLen');
    showLen('e','eLen');
    showLen('d','dLen');
    showLen('p','pLen');
    showLen('q','qLen');
    showLen('dp','dpLen');
    showLen('dq','dqLen');
    showLen('qmp','qmpLen');
    showLen('plaintextRsa','plaintextRsaLen');
    showLen('ciphertextRsa','ciphertextRsaLen');

    //show len method
    function showLen(id, lenId){
        // alert(1);
        $('#'+id).textbox('textbox').bind('input propertychange', function(e){
            var text = $('#'+id).textbox('getText');
            var len = text.length;
            $('#'+lenId).html(len);
        });
    }

</script>
</body>
</html>
