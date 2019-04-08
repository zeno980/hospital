var tableIns;
$(document).ready(function () {
    var element = layui.element;
    element.init();
    showPatients();
})
function showPatients() {
    var table = layui.table;
    tableIns = table.render({
        elem: '#patient',
        url: '/hospital/patient/doctor_patients', //数据接口
        skin: 'row ', //行边框风格
        page: true,
        cols: [[
            {field: 'patient_name', title: '姓名', width: '10%', unresize: true},
            {field: 'patient_cert_code', title: '证件号', width: '20%', unresize: true},
            {field: 'patient_gender', title: '性别', width: '10%', unresize: true},
            {field: 'patient_age', title: '年龄', width: '10%', unresize: true},
            {field: 'treatment_name', title: '手术', width: '20%', unresize: true},
            {field: 'treatment_time', title: '手术时间', width: '20%', unresize: true},
            {field: 'treatment_fee', title: '手术费用', width: '10%', unresize: true}
            // {
            //     field: 'action',
            //     title: '操作',
            //     width: '10%',
            //     unresize: true,
            //     toolbar: '<div><button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="audit">审核</button><button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="del">删除</button></div>'
            // }
        ]]
    });
    // table.on('tool(doctor_table_v)',function (obj) {
    //     var data = obj.data;
    //     if(obj.event=='audit'){
    //         auditUser(data)
    //     }else if(obj.event=='del'){
    //         deleteUser(data)
    //     }
    // })
}