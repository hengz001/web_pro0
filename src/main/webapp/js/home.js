
$('a').bind('click',function(){
    // alert("------------> click");
    var title = $(this).text();
    var id = $(this).attr('id');
    id = id+"_tab";
    // alert(id);

    if(existsTab(title)){
        // alert("exists");
        selectTab(title);
    }else{
        // alert("not exists");
        newTab(title,id);
    }
});

function selectTab(title){
    var flag = $('#tt').tabs('select',title);
}

function existsTab(title){
    return $('#tt').tabs('exists',title);
}

function newTab(title,id){
    $('#tt').tabs('add',{
        title: title,
        id: id,
        selected: true,
        closable: true
    });
    setPanel('tabPage?page='+id);
}

function setPanel(url){
    var pp = $('#tt').tabs('getSelected');
    pp.panel('open').panel('refresh',url);
}