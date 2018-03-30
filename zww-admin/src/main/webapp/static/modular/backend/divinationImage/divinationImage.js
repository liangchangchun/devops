/**
 * 占卜图片管理初始化
 */
var DivinationImage = {
    id: "DivinationImageTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DivinationImage.initColumn = function () {
    return [
        // {field: 'selectItem', radio: true},
        {field: 'selectItem', checkbox: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '占卜主题图片', field: 'divinationTopicImg', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    return '<a class="maincolor" href="javascript:;" onclick="DivinationImage.upload(\'占卜图\',\'divinationImage\/toUpload\','+row.id+',\'\',\'400\')"><img  src="'+value+'" width="100" class="picture-thumb" /></a>';
                }
            },
            {title: '占卜主题id', field: 'divinationTopicId', visible: true, align: 'center', valign: 'middle'},
            {title: '占卜主题名称', field: 'divinationName', visible: true, align: 'center', valign: 'middle'},
            // {title: '创建时间', field: 'createdDate', visible: true, align: 'center', valign: 'middle'},
            // {title: '创建人', field: 'createdBy', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'modifiedDate', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人', field: 'modifiedBy', visible: true, align: 'center', valign: 'middle'}
    ];
};


/**
 * 图片上传
 */
DivinationImage.upload = function (title,url,id,w,h) {
    //alert("upload:"+title+",url="+url+",id="+id);
    url = url+"?id="+id;
    layer_show(title,url,w,h);
}


/**
 * 检查是否选中
 */
DivinationImage.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        DivinationImage.seItem = selected[0];//单个
        DivinationImage.seItems = selected;//多个
        return true;
    }
};

/**
 * 点击添加占卜图片
 */
DivinationImage.openAddDivinationImage = function () {
    var index = layer.open({
        type: 2,
        title: '添加占卜图片',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/divinationImage/divinationImage_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看占卜图片详情
 */
DivinationImage.openDivinationImageDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '占卜图片详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/divinationImage/divinationImage_update/' + DivinationImage.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除占卜图片
 */
DivinationImage.delete = function () {
    if (this.check()) {
        var ids = '';
        for (var sitem in this.seItems){
            var val = this.seItems[sitem].id;
            ids += val + ',';
            // alert(ids)
        }
        layer.confirm('确认要删除吗？', {
            btn : [ '确定', '取消' ]//按钮
        }, function(index) {
            layer.close(index);
        var ajax = new $ax(Feng.ctxPath + "/divinationImage/delete", function (data) {
            Feng.success("删除成功!");
            DivinationImage.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        // ajax.set("divinationImageId",this.seItem.id);
        ajax.set("divinationImageId",ids);
        ajax.start();
        });
    }
};

/**
 * 查询占卜图片列表
 */
DivinationImage.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    DivinationImage.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        DivinationImage.search();
    }
});

$(function () {
    var defaultColunms = DivinationImage.initColumn();
    var table = new BSTable(DivinationImage.id, "/divinationImage/list", defaultColunms);
    table.setPaginationType("server");
    DivinationImage.table = table.init();
});
