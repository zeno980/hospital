var tableIns_active;
var tableIns_inactive;
var layer;
$(document).ready(function () {
    hideAll();
    setCounts();
    layer = layui.layer;
    var element = layui.element;
    element.init();
    element.on('nav(nav_left)',function (elem) {
        switch (elem.context.innerText) {
            case '医生信息管理':
                hideAll();
                showActiveDoctors();
                $('#doctor_info').show()
                break;
            case '医生注册审核':
                hideAll()
                showInactiveDoctors();
                $('#doctor_v').show()
                break;
        }
    })
})
function search() {
    tableIns_active.reload({where: {doctor_id: $('#search_id').val(),}, page: {curr: 1}})
    $('#search_id').val('');
}
function searchInactive() {
    tableIns_inactive.reload({where: {doctor_id: $('#search_id_v').val(),inactive:true}, page: {curr: 1}})
    $('#search_id_v').val('');
}
function updateDoctor() {
    $('#hideSubmit').click();
}
function showActiveDoctors() {
    var table = layui.table;
    tableIns_active = table.render({
        elem: '#doctor_cursor',
        url: '/hospital/doctor/getDoctors.do', //数据接口
        skin: 'row ', //行边框风格
        page:true,
        id:'testReload',
        cols: [[
            {field: 'doctor_name', title: '姓名', width:'10%',unresize:true},
            {field: 'cert_code', title: '证件号', width:'10%',  unresize:true},
            {field: 'doctor_department', title: '部门', width:'20%',unresize:true},
            {field: 'doctor_position', title: '职位', width:'10%',unresize:true},
            {field: 'doctor_gender', title: '性别', width: '10%',unresize:true},
            {field: 'doctor_tel', title: '电话', width: '10%', unresize:true},
            {field: 'create_date', title: '注册时间', width: '20%', unresize:true},
            {field: 'active', title: '状态', width: '0%', unresize:true,hide:true},
            {field: 'action', title: '操作', width: '10%', unresize:true,toolbar:'<div><button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="edit">修改</button><button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="del">删除</button></div>'}
        ]]
    });
    table.on('tool(doctor_table)',function (obj) {
        var data = obj.data;
        if(obj.event=='edit'){
            var form = layui.form;
            form.val("updateForm",{
                "doctor_id":data.doctor_id,
                "doctor_name":data.doctor_name,
                "doctor_department":data.doctor_department,
                "doctor_gender":data.doctor_gender,
                "doctor_position":data.doctor_position,
                "doctor_tel":data.doctor_tel,
                "cert_code":data.cert_code,
                "active":data.active,
            })
            form.on('radio(active_change)', function(data1){
                if(data.active=="Y"&&data1.value=="N"){
                    layer.open({
                        title:'提示',
                        content: '确定修改用户状态？',btn: ['取消', '确定'],
                        yes: function(index, layero){
                            layer.close(index);
                            $('input:radio[name=active]')[0].checked = true;
                            form.render('radio');
                        },
                        btn2: function(index, layero){}
                    });
                }
            });
            form.on('submit(doSubmit)',function (data) {
                $('#myModal').modal('hide');
                var index = layer.load(2);
                $.ajax({
                    url:'/hospital/doctor/updateDoctor.do',
                    type:'post',
                    contentType:"application/json",
                    datatype:"json",
                    data : JSON.stringify(data.field)
                }).done(function (data) {
                    if(data.code==0){
                        layer.close(index);
                        layer.msg("修改成功",{time: 1000},function () {
                            tableIns_active.reload({where: {doctor_id: '',}});
                        })
                    }else{
                        layer.close(index);
                        layer.msg(data.msg,{time:1000})
                    }
                })
            })
            form.render()
            $('#myModal').modal('show');
        }else if(obj.event=='del'){
            deleteUser(data)
        }
    });
}
function hideAll(){
    $('#doctor_info').hide();
    $('#doctor_v').hide();
}
function showInactiveDoctors() {
    var table = layui.table;
    tableIns_inactive = table.render({
        elem: '#doctor_cursor_v',
        url: '/hospital/doctor/getDoctors.do', //数据接口
        skin: 'row ', //行边框风格
        page: true,
        where : {inactive:true},
        cols: [[
            {field: 'doctor_name', title: '姓名', width: '10%', unresize: true},
            {field: 'cert_code', title: '证件号', width: '10%', unresize: true},
            {field: 'doctor_department', title: '部门', width: '20%', unresize: true},
            {field: 'doctor_position', title: '职位', width: '10%', unresize: true},
            {field: 'doctor_gender', title: '性别', width: '10%', unresize: true},
            {field: 'doctor_tel', title: '电话', width: '10%', unresize: true},
            {field: 'create_date', title: '注册时间', width: '20%', unresize:true},
            {
                field: 'action',
                title: '操作',
                width: '10%',
                unresize: true,
                toolbar: '<div><button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="audit">审核</button><button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="del">删除</button></div>'
            }
        ]]
    });
    table.on('tool(doctor_table_v)',function (obj) {
        var data = obj.data;
        if(obj.event=='audit'){
            auditUser(data)
        }else if(obj.event=='del'){
            deleteUser(data)
        }
    })
}
function deleteUser(data) {
    layer.confirm('删除用户:'+data.doctor_name+"?", {icon: 7, title:'提示'}, function(index){
        layer.close(index)
        var v = layer.load(2);
        $.ajax({
            url:'/hospital/doctor/deleteDoctor.do',
            type:'post',
            contentType:"application/json",
            datatype:"json",
            data : JSON.stringify({"doctor_id":data.doctor_id})
        }).done(function (data) {
            if(data.code==0){
                layer.close(v);
                layer.msg("删除成功",{time: 1000},function () {
                    setCounts();
                    tableIns_active.reload({where: {doctor_id: '',}});
                    $('#search_id').val('');
                })
            }else{
                layer.close(v);
                layer.msg(data.msg,{time:1000})
            }
        })
    });
}
function auditUser(data) {
    layer.confirm('通过用户:'+data.doctor_name+"?", {icon: 7, title:'提示'}, function(index){
        data['active']='Y'
        layer.close(index)
        var v = layer.load(2);
        $.ajax({
            url:'/hospital/doctor/updateDoctor.do',
            type:'post',
            contentType:"application/json",
            datatype:"json",
            data : JSON.stringify(data)
        }).done(function (data) {
            if(data.code==0){
                layer.close(v);
                layer.msg("审核成功",{time: 1000},function () {
                    setCounts();
                    tableIns_inactive.reload({where: {doctor_id: '',inactive:true}});
                    $('#search_id_v').val('');
                })
            }else{
                layer.close(v);
                layer.msg(data.msg,{time:1000})
            }
        })
    });
}
function setCounts() {
    $.ajax({
        url:'/hospital/doctor/getInactiveDoctorCounts.do',
        type:'post',
        contentType:"application/json",
        datatype:"json",
    }).done(function (data) {
        console.log(data)
        if(data.data.length>0){
            $('#inactive_counts').attr("class",'layui-badge');
            $('#inactive_counts').val(data.data);
        }else{
            // $('#inactive_counts').removeClass("layui-badge");
            // $('#inactive_counts').text(data.data);
        }
    })
}