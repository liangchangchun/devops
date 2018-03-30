/**
 * 娃娃机背景图片列表管理初始化
 */
var TDollImage = {
    id: "TDollImageTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TDollImage.initColumn = function () {
    return [
        // {field: 'selectItem', radio: true},
        {field: 'selectItem', checkbox: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '娃娃机名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            //{title: '', field: 'imgContextPath', visible: true, align: 'center', valign: 'middle'},
           // {title: '', field: 'imgFileName', visible: true, align: 'center', valign: 'middle'},
            {title: '背景图片', field: 'imgRealPath', visible: true, align: 'center', valign: 'middle',
          	  formatter:function (value,row,index) {  
                  return '<a class="maincolor" href="javascript:;" onclick="TDollImage.upload(\'娃娃机背景图\',\'/tDollImage\/toUpload\','+row.id+',\'\',\'400\')"><img  src="'+value+'" width="100" class="picture-thumb" /></a>';
              }  
            },
           // {title: '', field: 'createdDate', visible: true, align: 'center', valign: 'middle'},
           // {title: '', field: 'createdBy', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'modifiedDate', visible: true, align: 'center', valign: 'middle'},
            {title: '操作人', field: 'person', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 图片上传 
 */
TDollImage.upload = function (title,url,id,w,h) {
	// alert("upload:"+title+",url="+url+",id="+id);
	url = Feng.ctxPath + url+"?id="+id;
	layer_show(title,url,w,h);
}

/**
 * 检查是否选中
 */
TDollImage.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TDollImage.seItem = selected[0];//单个
        TDollImage.seItems = selected;//多个
        return true;
    }
};

/**
 * 点击添加娃娃机背景图片列表
 */
TDollImage.openAddTDollImage = function () {
    var index = layer.open({
        type: 2,
        title: '添加娃娃机背景图片列表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tDollImage/tDollImage_adds/'+$("#dollId").val()
    });
    this.layerIndex = index;
};

/**
 * 打开查看娃娃机背景图片列表详情
 */
TDollImage.openTDollImageDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '娃娃机背景图片列表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/tDollImage/tDollImage_update/' + TDollImage.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除娃娃机背景图片列表
 */
TDollImage.delete = function () {
    if (this.check()) {
        var ids = '';
        for (var sitem in this.seItems){
            var val = this.seItems[sitem].id;
            ids += val + ',';
            // alert(ids)
        }
        //alert(ids)
        // var tDollImageId = this.seItem.id;
        layer.confirm('确认要删除吗？', {
            btn : [ '确定', '取消' ]//按钮
        }, function(index) {
            layer.close(index);
            var ajax = new $ax(Feng.ctxPath + "/tDollImage/delete", function (data) {
                Feng.success("删除成功!");
                TDollImage.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            // ajax.set("tDollImageId",tDollImageId);
            ajax.set("tDollImageIds",ids);
            ajax.start();
        });
    }
};



/**
 * 查询娃娃机背景图片列表列表
 */
TDollImage.search = function () {
    var queryData = {};
    queryData['name'] = $("#condition").val();
    TDollImage.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        TDollImage.search();
    }
});

$(function () {
    var defaultColunms = TDollImage.initColumn();
    var table = new BSTable(TDollImage.id, "/tDollImage/list", defaultColunms);
    table.setPaginationType("server");
    var queryData = {};
    queryData['name'] = $("#condition").val();
    table.setQueryParams(queryData);
    TDollImage.table = table.init();
});
