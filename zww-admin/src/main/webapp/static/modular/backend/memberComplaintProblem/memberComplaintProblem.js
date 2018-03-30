/**
 * 设置申诉问题列表管理初始化
 */
var MemberComplaintProblem = {
    id: "MemberComplaintProblemTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MemberComplaintProblem.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '申诉问题', field: 'problem', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MemberComplaintProblem.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MemberComplaintProblem.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加设置申诉问题列表
 */
MemberComplaintProblem.openAddMemberComplaintProblem = function () {
    var index = layer.open({
        type: 2,
        title: '添加设置申诉问题列表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/memberComplaintProblem/memberComplaintProblem_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看设置申诉问题列表详情
 */
MemberComplaintProblem.openMemberComplaintProblemDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '设置申诉问题列表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/memberComplaintProblem/memberComplaintProblem_update/' + MemberComplaintProblem.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除设置申诉问题列表
 */
MemberComplaintProblem.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/memberComplaintProblem/delete", function (data) {
            Feng.success("删除成功!");
            MemberComplaintProblem.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("memberComplaintProblemId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询设置申诉问题列表列表
 */
MemberComplaintProblem.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    MemberComplaintProblem.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MemberComplaintProblem.initColumn();
    var table = new BSTable(MemberComplaintProblem.id, "/memberComplaintProblem/list", defaultColunms);
    table.setPaginationType("client");
    MemberComplaintProblem.table = table.init();
});
