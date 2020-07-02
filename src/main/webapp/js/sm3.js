setTextbox('#inSM3');
setTextbox('#outSM3');

function form_opt_sm3(url){
    $('#ffSM3').form({
        // url:'/sm3/hash',
        url:url,
        onSubmit:function(){},
        success:function (data) {
            // alert(data);
            $('#outSM3').textbox('setValue',data);
            $('#outSM3Len').html(data.length);
        },
    })
    $('#ffSM3').submit();
}
