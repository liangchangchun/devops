/**
 * 娃娃机列表管理初始化
 */
var TDoll = {
    id: "TDollTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TDoll.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '机器id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '机器号', field: 'machineCode', visible: true, align: 'center', valign: 'middle'},
            {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '价格', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '兑换币数', field: 'redeemCoins', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'machineStatus', visible: true, align: 'center', valign: 'middle',
          	  formatter:function (value,row,index) {  
          		if(value == '空闲中'){
          			return '<span class="label label-success radius">'+value+'</span>';
          		}
          		if(value == '游戏中'){
          			return '<span class="label label-warning radius">'+value+'</span>';
          		}
          		if(value != '游戏中' && value != '空闲中'){
          			return '<span class="label label-danger radius">'+value+'</span>';
          		}
              }  
            },
            {title: '娃娃编号', field: 'dollID', visible: true, align: 'center', valign: 'middle'},
            {title: '封面', field: 'tbimgRealPath', visible: true, align: 'center', valign: 'middle',
          	  formatter:function (value,row,index) {  
                  return '<a class="maincolor" href="javascript:;" onclick="TDoll.upload(\'广告头像\',\'tDoll\/toUpload\','+row.id+',\'\',\'350\')"><img  src="'+value+'" width="80" class="img-rounded" /></a>';  
              }  
            },
            {title: '游戏时长', field: 'timeout', visible: true, align: 'center', valign: 'middle',
            	  formatter:function (value,row,index) {  
                      return value+'秒';  
                  }  
                },
            {title: '排序', field: 'watchingNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '机器类型', field: 'machineType', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {
            		if (value=="0")return '普通房';
                    if (value=="1")return '练习房';
                    if (value=="2")return '钻石房';
                    if (value=="3")return '占卜房';
            	}
            },
            {title: '机器地址', field: 'adress', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {
            		return row.province+row.city+row.county+row.street;
            	}
            },
            {title: '操作人', field: 'person', visible: true, align: 'center', valign: 'middle'},
            //{title: '', field: 'quantity', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'description', visible: true, align: 'center', valign: 'middle'},
            //{title: '机器序列号', field: 'machineSerialNum', visible: true, align: 'center', valign: 'middle'},
            //{title: '', field: 'machineIp', visible: true, align: 'center', valign: 'middle'},
            //{title: '', field: 'machineUrl', visible: true, align: 'center', valign: 'middle'},
            //{title: '', field: 'tbimgContextPath', visible: true, align: 'center', valign: 'middle'},
            //{title: '', field: 'tbimgFileName', visible: true, align: 'center', valign: 'middle'},
            //{title: '', field: 'createdDate', visible: true, align: 'center', valign: 'middle'},
            //{title: '', field: 'createdBy', visible: true, align: 'center', valign: 'middle'},
            //{title: '后台修改时间', field: 'modifiedDate', visible: true, align: 'center', valign: 'middle'},
            //{title: '', field: 'modifiedBy', visible: true, align: 'center', valign: 'middle'},
            //{title: '', field: 'rtmpUrl1', visible: true, align: 'center', valign: 'middle'},
            //{title: '', field: 'rtmpUrl2', visible: true, align: 'center', valign: 'middle'},
            //{title: '', field: 'rtmpUrl3', visible: true, align: 'center', valign: 'middle'},
            //{title: '', field: 'rtmpPushUrl', visible: true, align: 'center', valign: 'middle'},
            //{title: '', field: 'mnsTopicName', visible: true, align: 'center', valign: 'middle'},
            //{title: '七牛连麦房间名称', field: 'piliRoomName', visible: true, align: 'center', valign: 'middle'},
            //{title: '娃娃机地址id', field: 'dollAddressId', visible: true, align: 'center', valign: 'middle'},
            //{title: '', field: 'bgm', visible: true, align: 'center', valign: 'middle'},
            //{title: '删除状态（1未删除 0已删除）', field: 'deleteStatus', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
TDoll.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TDoll.seItem = selected[0];
        return true;
    }
};



TDoll.upload = function (title,url,id,w,h) {
	//alert("upload:"+title+",url="+url+",id="+id);
	url = url+"?id="+id;
	layer_show(title,url,w,h);
} 

/**
 * 点击添加娃娃机列表
 */
TDoll.openAddTDoll = function () {
    var index = layer.open({
        type: 2,
        title: '添加娃娃机列表',
        area: ['900px', '520px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tDoll/tDoll_add'
    });
    this.layerIndex = index;
};


/**
 * 点击添加主题
 */
TDoll.addDollTopic = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '添加娃娃机主题',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/tDoll/dollTopic_add/'+ TDoll.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 点击添加背景图
 */
TDoll.addBackground = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '添加娃娃机背景',
            area: ['900px', '520px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/tDoll/addBackgroung/'+ TDoll.seItem.id
        });
        this.layerIndex = index;
    }

};

/**
 * 打开查看娃娃机列表详情
 */
TDoll.openTDollDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '娃娃机列表详情',
            area: ['900px', '520px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/tDoll/tDoll_update/' + TDoll.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除娃娃机列表
 */
TDoll.delete = function () {
    if (this.check()) {
    	var tDollId = this.seItem.id;
    	 layer.confirm('确认要删除吗？', {
             btn : [ '确定', '取消' ]//按钮
         }, function(index) {
        	 layer.close(index);
        var ajax = new $ax(Feng.ctxPath + "/tDoll/delete", function (data) {
            Feng.success("删除成功!");
            TDoll.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("tDollId",tDollId);
        ajax.start();
      }); 
    }
};

/**
 * 查询娃娃机列表列表
 */
TDoll.search = function () {
    var queryData = {};
    queryData['dollId'] = $("#dollId").val();
    queryData['name'] = $("#name").val();
    queryData['machineCode'] = $("#machineCode").val();
    queryData['machineStates'] = $("#machineStates").val();
    queryData['machineType'] = $("#machineType").val();
    queryData['modifiedBy'] = $("#modifiedBy").val();
    TDoll.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        TDoll.search();
    }
});

$(function () {
    var defaultColunms = TDoll.initColumn();
    var table = new BSTable(TDoll.id, "/tDoll/list", defaultColunms);
    table.setPaginationType("server");
    TDoll.table = table.init();

    // // 初始化头像上传
    // var avatarUp = new $WebUploadFile("avatar");
    // avatarUp.setUploadBarId("progressBar");
    // avatarUp.init();
});
