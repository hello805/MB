
   layui.use(['table', 'form', 'element'], function() {
   var table = layui.table;
    var form = layui.form;
   // var element = layui.element;
   form.render('select');
   			table.render({
   				elem: '#push',
   				data: {},
   				url: "/agent/list",
   				page: true, //开启分页
   				totalRow: false, //开启合计行
   				loading: false,
   				toolbar: '#toolbarDemo',
   				limit: 10,
   				limits: [15, 20, 30],
   				cols: [
                        [{fixed:'left', type: 'checkbox', width: 30}
                            , {field: 'id', title: 'ID', width: 80, align: 'center'}
                            , {field: '', title: ' 	管理员', width: 120, align: 'center'}
                            , {field: '', title: ' 	IP', width: 120, align: 'center'}                         
                            , {field: '', title: ' 	推送对象', width: 120, align: 'center'},
							, {field: '', title: '推送内容', width: 120, align: 'center'}
							, {field: '', title: '时间', width: 120, align: 'center'}
							                    
												
                          
                        ]
                    ],
   				done: function(res, curr, count) {
   					/*处理固定列行高与表格行高不一致问题*/
   					$(".layui-table-main  tr").each(function(index, val) {
   						$($(".layui-table-fixed .layui-table-body tbody tr")[index]).height($(val).height());
   					});
   				}
   			});
   						table.on('toolbar(push)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
   						var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
   						console.log(11)
   						if (layEvent === 'add') {
   							layer.open({
   								type: 2,
   								title: '添加',
   								maxmin: true,
   								shadeClose: true, //点击遮罩关闭层
   								area: ['1000px', '650px'],
   								content: 'add/addPush.html'
   							});
   						}
   					// };
   				});
   			
   			//点击查看详情 跳出
   			table.on('tool(extension)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
   				var token = localStorage.getItem("token");
   				var data = obj.data; //获得当前行数据
   				var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
   				var ids = obj.data.id;
   				if (layEvent === 'edit1') {
   					localStorage.clear();
   					localStorage.setItem("id", ids);
   					layer.open({
   						type: 2,
   						title: '编辑',
   						maxmin: true,
   						shadeClose: true, //点击遮罩关闭层
   						area: ['1000px', '650px'],
   						content: 'add/addGift.html'
   					});
   				} else if (layEvent === 'edit2') {
   					layer.confirm('确认要删除吗？', function(index) {
   						$.ajax({
   							type: "post",
   							url: "/user/del",
   							data: {
   								id: ids
   							},
   							success: function(data) {
   								if (data.meta.code == 200) {
   									layer.alert('删除成功', function() {
   										location.reload();
   									});
   								}
   								location.reload();
   							}
   						});
   					});
   				}
   			});
   
   var id = localStorage.getItem("id");
   
      $.ajax({
   		type: "GET", //提交方式
   		 //baseurl + "/com/users", //路径
   		dataType: 'json',
   		data: {
   			id: id
   		}, //数据，这里使用的是Json格式进行传输
   		success: function(data) { //返回数据根据结果进行相应的处理
   
   
   
   	}
   
   });
   
   
   
   
   });
   
    
	
	
	
	
	
	
	
	
	
	
	
	