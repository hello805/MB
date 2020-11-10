
layui.use(['table', 'layer','element','laydate'], function () {
    var table = layui.table;
    var layer = layui.layer;
    var element = layui.element;
    var laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#time1' //指定元素
    });
    laydate.render({
        elem: '#time2' //指定元素
    });

    var id = localStorage.getItem("id");

    $.ajax({
        type: "GET", //提交方式
        url: "/usersAuth/list", //baseurl + "/com/users", //路径
        dataType: 'json',
        data: {
            id:id
        }, //数据，这里使用的是Json格式进行传输
        success: function(data) { //返回数据根据结果进行相应的处理
            table.render({
                elem: '#usersAuth',
                data: data.data,
                page: true, //开启分页
                totalRow: false, //开启合计行
                loading: false,
                // toolbar: '#toolbarDemo',
                limit: 10,
                limits: [15, 20, 30],
                cols: [
                    [{fixed:'left', type: 'checkbox', width: 30}
                        , {field: 'uid', title: 'ID', width: 80, align: 'center'}
                        , {field: 'realName', title: '真实姓名', width: 120, align: 'center'}
                        , {field: 'mobile', title: '手机号码', width: 120, align: 'center'}
                        , {field: 'cerNo', title: '身份证号', width: 120, align: 'center'}
                        , {field: 'status', title: '审核状态', width: 120, align: 'center'}
                        , {field: 'addtime', title: '提交时间', align: 'center'}
                        , {field: 'uptime', title: '处理时间', align: 'center'}
                        , {fixed: 'right', title: '操作', width: 510, align: 'center', toolbar: '#operate'}
                    ]
                ],done: function(res, curr, count){
                    /*处理固定列行高与表格行高不一致问题*/
                    $(".layui-table-main  tr").each(function (index ,val) {
                        $($(".layui-table-fixed .layui-table-body tbody tr")[index]).height($(val).height());
                    });
                }
            });


            //点击查看详情 跳出
            table.on('tool(usersAuth)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var token = localStorage.getItem("token");
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var ids = obj.data.id;
                if (layEvent === 'edit') {
                    localStorage.clear();
                    localStorage.setItem("id", ids);
                    layer.open({
                        type: 2,
                        title: '编辑',
                        maxmin: true,
                        shadeClose: true, //点击遮罩关闭层
                        area: ['1000px', '650px'],
                        content: 'userEdit'
                    });
                }

            });
        }
    });
});
