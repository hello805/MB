
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
            url: "/users/list", //baseurl + "/com/users", //路径
            dataType: 'json',
            data: {
                id:id
            }, //数据，这里使用的是Json格式进行传输
            success: function(data) { //返回数据根据结果进行相应的处理
                table.render({
                    elem: '#users',
                    data: data.data,
                    page: true, //开启分页
                    totalRow: false, //开启合计行
                    loading: false,
                    toolbar: '#toolbarDemo',
                    limit: 10,
                    limits: [15, 20, 30],
                    cols: [
                        [{fixed:'left', type: 'checkbox', width: 30}
                            , {field: 'id', title: 'ID', width: 80, align: 'center'}
                            , {field: 'user_login', title: '用户名', width: 120, align: 'center'}
                            , {field: 'user_nicename', title: '昵称', width: 120, align: 'center'}
                            , {field: 'avatar', title: '头像', width: 120, align: 'center'}
                            , {field: 'coin', title: '余额', width: 120, align: 'center'}
                            , {field: 'consumption', title: '累计消费', width: 120, align: 'center'}
                            , {field: 'votes', title: '映票', width: 120, align: 'center'}
                            , {field: 'votestotal', title: '累计映票', width: 120, align: 'center'}
                            , {field: 'user_activation_key', title: '邀请码', width: 120, align: 'center'}
                            , {field: 'source', title: '注册设备', width: 160, align: 'center'}
                            , {field: 'create_time', title: '注册时间', width: 160, align: 'center'}
                            , {field: 'last_login_time', title: '最后登录时间', width: 160, align: 'center'}
                            , {field: 'last_login_ip', title: '最后登录IP', width: 160, align: 'center'}
                            , {field: 'user_status', title: '状态', width: 120, align: 'center'}
                            , {fixed: 'right', title: '操作', width: 510, align: 'center', toolbar: '#operate'}
                        ]
                    ],done: function(res, curr, count){
                        /*处理固定列行高与表格行高不一致问题*/
                        $(".layui-table-main  tr").each(function (index ,val) {
                            $($(".layui-table-fixed .layui-table-body tbody tr")[index]).height($(val).height());
                        });
                    }
                });

                table.on('toolbar(parameter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                    if (layEvent === 'addUsers') {
                        layer.open({
                            type: 2,
                            title: '添加',
                            maxmin: true,
                            shadeClose: true, //点击遮罩关闭层
                            area: ['1000px', '650px'],
                            content: 'usersAdd'
                        });
                    }
                    var checkStatus = table.checkStatus(obj.config.id)
                        ,data = checkStatus.data; //获取选中的数据
                    switch(obj.event){
                        case 'batchSetZombie':
                            if(data.length === 0){
                                layer.msg('请选择一行');
                            } else {
                                var ids = [];
                                for (let i = 0; i < data.length; i++) {
                                    ids.push(data[i].id);
                                }
                                layer.confirm('确认全部设置为僵尸粉吗？', function(index) {
                                    $.ajax({
                                        type: "post",
                                        url: "/user/del",
                                        data: {
                                            id: ids
                                        },
                                        success: function(data) {
                                            if(data.meta.code == 200) {
                                                layer.alert('更新成功', function() {
                                                    location.reload();
                                                });
                                            }
                                            location.reload();
                                        }
                                    });
                                });
                            }
                            break;
                        case 'batchCancelZombie':
                            if(data.length === 0){
                                layer.msg('请选择一行');
                            } else {
                                var ids = [];
                                for (let i = 0; i < data.length; i++) {
                                    ids.push(data[i].id);
                                }
                                layer.confirm('确认全部取消僵尸粉吗？', function(index) {
                                    $.ajax({
                                        type: "post",
                                        url: "/user/del",
                                        data: {
                                            id: ids
                                        },
                                        success: function(data) {
                                            if(data.meta.code == 200) {
                                                layer.alert('更新成功', function() {
                                                    location.reload();
                                                });
                                            }
                                            location.reload();
                                        }
                                    });
                                });
                            }
                            break;
                        case 'batchOnZombie':
                            if(data.length === 0){
                                layer.msg('请选择一行');
                            } else {
                                var ids = [];
                                for (let i = 0; i < data.length; i++) {
                                    ids.push(data[i].id);
                                }
                                layer.confirm('确认全部开启僵尸粉吗？', function(index) {
                                    $.ajax({
                                        type: "post",
                                        url: "/user/del",
                                        data: {
                                            id: ids
                                        },
                                        success: function(data) {
                                            if(data.meta.code == 200) {
                                                layer.alert('更新成功', function() {
                                                    location.reload();
                                                });
                                            }
                                            location.reload();
                                        }
                                    });
                                });
                            }
                            break;
                        case 'batchOffZombie':
                            if(data.length === 0){
                                layer.msg('请选择一行');
                            } else {
                                var ids = [];
                                for (let i = 0; i < data.length; i++) {
                                    ids.push(data[i].id);
                                }
                                layer.confirm('确认全部关闭僵尸粉吗？', function(index) {
                                    $.ajax({
                                        type: "post",
                                        url: "/user/del",
                                        data: {
                                            id: ids
                                        },
                                        success: function(data) {
                                            if(data.meta.code == 200) {
                                                layer.alert('更新成功', function() {
                                                    location.reload();
                                                });
                                            }
                                            location.reload();
                                        }
                                    });
                                });
                            }
                            break;
                    };
                });

                //点击查看详情 跳出
                table.on('tool(users)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                    var token = localStorage.getItem("token");
                    var data = obj.data; //获得当前行数据
                    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                    var ids = obj.data.id;
                    if (layEvent === 'edit7') {
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
                    }else if (layEvent === 'edit8') {
                        layer.confirm('确认要删除吗？', function(index) {
                            $.ajax({
                                type: "post",
                                url: "/user/del",
                                data: {
                                    id: ids
                                },
                                success: function(data) {
                                    if(data.meta.code == 200) {
                                        layer.alert('更新成功', function() {
                                            location.reload();
                                        });
                                    }
                                    location.reload();
                                }
                            });
                        });
                    }else if (layEvent === 'edit1') {
                        layer.confirm('确认要拉黑此用户吗？', function(index) {
                            $.ajax({
                                type: "post",
                                url: "/user/del",
                                data: {
                                    id: ids
                                },
                                success: function(data) {
                                    if(data.meta.code == 200) {
                                        layer.alert('更新成功', function() {
                                            location.reload();
                                        });
                                    }
                                    location.reload();
                                }
                            });
                        });
                    }else if (layEvent === 'edit2') {
                        layer.confirm('确认要设置此用户超管吗？', function(index) {
                            $.ajax({
                                type: "post",
                                url: "/user/del",
                                data: {
                                    id: ids
                                },
                                success: function(data) {
                                    if(data.meta.code == 200) {
                                        layer.alert('更新成功', function() {
                                            location.reload();
                                        });
                                    }
                                    location.reload();
                                }
                            });
                        });
                    }else if (layEvent === 'edit3') {
                        layer.confirm('确认要取消热门吗？', function(index) {
                            $.ajax({
                                type: "post",
                                url: "/user/del",
                                data: {
                                    id: ids
                                },
                                success: function(data) {
                                    if(data.meta.code == 200) {
                                        layer.alert('更新成功', function() {
                                            location.reload();
                                        });
                                    }
                                    location.reload();
                                }
                            });
                        });
                    }else if (layEvent === 'edit4') {
                        layer.confirm('确认要推荐吗？', function(index) {
                            $.ajax({
                                type: "post",
                                url: "/user/del",
                                data: {
                                    id: ids
                                },
                                success: function(data) {
                                    if(data.meta.code == 200) {
                                        layer.alert('更新成功', function() {
                                            location.reload();
                                        });
                                    }
                                    location.reload();
                                }
                            });
                        });
                    }else if (layEvent === 'edit5') {
                        layer.confirm('确认要开启僵尸粉吗？', function(index) {
                            $.ajax({
                                type: "post",
                                url: "/user/del",
                                data: {
                                    id: ids
                                },
                                success: function(data) {
                                    if(data.meta.code == 200) {
                                        layer.alert('更新成功', function() {
                                            location.reload();
                                        });
                                    }
                                    location.reload();
                                }
                            });
                        });
                    }else if (layEvent === 'edit6') {
                        layer.confirm('确认要设置此用户为僵尸粉吗？', function(index) {
                            $.ajax({
                                type: "post",
                                url: "/user/del",
                                data: {
                                    id: ids
                                },
                                success: function(data) {
                                    if(data.meta.code == 200) {
                                        layer.alert('更新成功', function() {
                                            location.reload();
                                        });
                                    }
                                    location.reload();
                                }
                            });
                        });
                    }

                });
            }
        });
    });
