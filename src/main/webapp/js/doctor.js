var tableIns;
$(document).ready(function () {
    var element = layui.element;
    element.init();
    element.on('nav(nav_left)',function (elem) {
        // console.log(elem.context.innerText)
    })
    var table = layui.table;
    tableIns = table.render({
        elem: '#doctor_cursor',
        url: '/hospital/doctor/getDoctors.do', //数据接口
        skin: 'row ', //行边框风格
        page:true,
        id:'testReload',
        cols: [[ //表头
            {field: 'doctor_id', title: '编号', width:'10%',  unresize:true},
            {field: 'doctor_name', title: '姓名', width:'10%',unresize:true},
            {field: 'doctor_department', title: '部门', width:'10%',unresize:true},
            {field: 'doctor_position', title: '职位', width:'20%',unresize:true},
            {field: 'doctor_gender', title: '性别', width: '10%',unresize:true},
            {field: 'doctor_tel', title: '电话', width: '20%', unresize:true},
            {field: 'password', title: '密码', width: '10%', unresize:true,hide:true},
            {field: 'action', title: '操作', width: '10%', unresize:true,toolbar:'<div><button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="edit">修改</button><button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="del">删除</button></div>'}
        ]]
    });
    table.on('tool(doctor_table)',function (obj) {
        var data = obj.data;
        if(obj.event=='edit'){
            $('#doctorid').val(data.doctor_id);
            $('#doctorname').val(data.doctor_name);
            $('#doctordepartment').val(data.doctor_department);
            $('#doctorposition').val(data.doctor_position);
            $('#doctorgender').val(data.doctor_gender);
            $('#doctortel').val(data.doctor_tel);
            $('#password').val(data.password);
            $('#myModal').modal('show');
        }else if(obj.event=='del'){
            var layer = layui.layer;
            layer.confirm('删除用户:'+data.doctor_name+"?", {icon: 7, title:'提示'}, function(index){
                layer.close(index)
                var v = layer.load(2);
                $.ajax({
                    url:'/hospital/doctor/DeleteDoctor',
                    type:'post',
                    contentType:"application/json",
                    datatype:"json",
                    data : JSON.stringify({"doctor_id":data.doctor_id})
                }).done(function (data) {
                    if(data.code==0){
                        layer.close(v);
                        layer.msg("删除成功",{time: 1000},function () {
                            tableIns.reload({where: {doctor_id: '',}, page: {curr: 1}});
                            $('#search_id').val('');
                        })
                    }else{
                        layer.close(v);
                        layer.msg(data.msg,{time:1000})
                    }
                })
            });
        }
    });
})
function search() {
    tableIns.reload({
        where: {
            doctor_id: $('#search_id').val(),
        },
        page: {
            curr: 1
        }
    })
    $('#search_id').val('');
}