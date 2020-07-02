
setTextbox('#inHash');
setTextbox('#outHash');

function initCombobox(url){
    $('#algoHash').combobox({
        url:url,
        valueField:'id',
        textField:'text',
        onLoadSuccess:function(){
            data = $(this).combobox('getData');
            if(data.length>0){
                $(this).combobox('select',data[0].text);
            }
        }
    });
}

function form_opt_hash(url){
    $('#ffHash').form({
        url:url,
        onSubmit:function(){},
        success:function (data) {
            // alert(data);
            $('#outHash').textbox('setValue',data);
            $('#outHashLen').html(data.length);
        },
    })
    $('#ffHash').submit();
}

