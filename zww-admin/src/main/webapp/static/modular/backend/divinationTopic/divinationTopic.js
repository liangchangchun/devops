/**
 * 占卜主题管理初始化
 */
var DivinationTopic = {
    id: "DivinationTopicTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DivinationTopic.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '预测名称', field: 'divinationName', visible: true, align: 'center', valign: 'middle'},
            {title: '模板图片', field: 'modeUrl', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    return '<a class="maincolor" href="javascript:;" onclick="DivinationTopic.upload(\'模板图\',\'/divinationTopic\/toUpload\','+row.id+',\'\',\'400\')"><img  src="'+value+'" width="100" class="picture-thumb" /></a>';
                }
            },
            // {title: '机器id', field: 'dollId', visible: true, align: 'center', valign: 'middle'},
            // {title: '机器名称', field: 'dollName', visible: true, align: 'center', valign: 'middle'},
            // {title: '机器编号', field: 'dollNum', visible: true, align: 'center', valign: 'middle'},
            // {title: '机器图片', field: 'dollImg', visible: true, align: 'center', valign: 'middle',
            //     formatter:function (value,row,index) {
            //         return '<img  src="'+value+'" width="80" class="img-rounded" />';
            //     }
            // },
            // {title: '预测显示次数', field: 'resultStatus', visible: true, align: 'center', valign: 'middle',
            //     formatter:function (value,row,index) {
            //         if(value == 0){
            //             return '每次不同';
            //         }if(value == 1){
            //             return '每日一变';
            //         }if(value == 2){
            //             return '不变';
            //         }
            //
            //     }
            // },
            {title: '过期时间', field: 'wxpireTime', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createdDate', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createdBy', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'modifiedDate', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人', field: 'modifiedBy', visible: true, align: 'center', valign: 'middle'}
    ];
};


/**
 * 图片上传
 */
DivinationTopic.upload = function (title,url,id,w,h) {
    //alert("upload:"+title+",url="+url+",id="+id);
    url = Feng.ctxPath + url+"?id="+id;
    layer_show(title,url,w,h);
}


/**
 * 检查是否选中
 */
DivinationTopic.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        DivinationTopic.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加占卜主题
 */
DivinationTopic.openAddDivinationTopic = function () {
    var index = layer.open({
        type: 2,
        title: '添加占卜主题',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/divinationTopic/divinationTopic_add'
    });
    this.layerIndex = index;
};


/**
 * 点击添加占卜图片
 */
DivinationTopic.addImg = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '占卜主题详情',
            area: ['900px', '520px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/divinationTopic/addImg/' + DivinationTopic.seItem.id
        });
        this.layerIndex = index;
    }

};


/**
 * 打开查看占卜主题详情
 */
DivinationTopic.openDivinationTopicDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '占卜主题详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/divinationTopic/divinationTopic_update/' + DivinationTopic.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除占卜主题
 */
DivinationTopic.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/divinationTopic/delete", function (data) {
            Feng.success("删除成功!");
            DivinationTopic.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("divinationTopicId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询占卜主题列表
 */
DivinationTopic.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    DivinationTopic.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        DivinationTopic.search();
    }
});

$(function () {
    var defaultColunms = DivinationTopic.initColumn();
    var table = new BSTable(DivinationTopic.id, "/divinationTopic/list", defaultColunms);
    table.setPaginationType("server");
    DivinationTopic.table = table.init();
});
