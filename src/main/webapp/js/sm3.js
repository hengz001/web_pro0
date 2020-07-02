setTextbox('#inSM3');
setTextbox('#outSM3');

function hashSm3(url){
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

// $('#ffSM3').form({
//     url:'/sm3/hash',
//     onSubmit:function(){},
//     success:function (data) {
//         // alert(data);
//         $('#outSM3').textbox('setValue',data);
//         $('#outSM3Len').html(data.length);
//     },
// })

// $('#btn_SM3').bind('click',function () {
//     $('#ffSM3').submit();
//     // alert(1);
// });