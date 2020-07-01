
function setTextbox(id){
    $(id).textbox({prompt:'请输入16进制数据'});
    $(id).textbox('textbox').bind('input propertychange', function(e){
        var text = $(id).textbox('getText');
        var len = text.length;
        $(id+"Len").html(len);
    });
}

function setIvValid(id, validType){
    $(id).textbox({
        validType: validType
    });
}

function setIvInput(id,flag){
    if(flag){
        $(id).textbox('enable');
    }else{
        $(id).textbox('disable');
    }
}

function setModeSelect(modeId, ivId){
    $(modeId).combobox({
        onChange:function(){
            mode  = $(modeId+' option:selected').val();
            setIvInput(ivId, !(mode == 'ECB'))
        }
    });
}