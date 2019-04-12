var tableIns;
var form;
var layer;
$(document).ready(function () {
    var element = layui.element;
    element.on('nav(nav_left)',function (elem) {
        switch (elem.context.innerText) {
            case '患者管理':
                hideAll();
                showPatients();
                $('#patient_info').show()
                break;
            case '医生注册审核':
                hideAll()
                showInactiveDoctors();
                $('#doctor_v').show()
                break;
        }
    })
    element.init();

})
function showPatients() {
    var laydate = layui.laydate;
    laydate.render({elem: '#time'})
    layer = layui.layer;
    form  = layui.form;
    form.on('submit(doSubmit)',function (data) {
        $('#myModal').modal('hide');
        var index = layer.load(2);
        $.ajax({
            url:'/hospital/patient/doctor_addpatient',
            type:'post',
            contentType:"application/json",
            datatype:"json",
            data : JSON.stringify(data.field)
        }).done(function (data) {
            if(data.code==0){
                layer.close(index);
                layer.msg("添加成功",{time: 1000},function () {
                    tableIns.reload({where: {patient_cert_code: '',}, page: {curr: 1}})
                })
            }else{
                layer.close(index);
                layer.msg(data.msg,{time:1000})
            }
        })
    })
    form.on('submit(sick_doSubmit)',function(data){
        $('#sick_Modal').modal('hide');
        var index = layer.load(2);
        $.ajax({
            url:'/hospital/patient/ArrangeSickbed',
            type:'post',
            contentType:"application/json",
            datatype:"json",
            data : JSON.stringify(data.field)
        }).done(function (data) {
            if(data.code==0){
                layer.close(index);
                layer.msg("分配成功",{time: 1000},function () {
                    tableIns.reload({where: {patient_cert_code: '',}, page: {curr: 1}})
                })
            }else{
                layer.close(index);
                layer.msg(data.msg,{time:1000})
            }
        })
    })
    form.on('submit(prescription_doSubmit)',function (data) {
        $('#sick_Modal').modal('hide');
        var result = data.field;
        var count=parseInt($("#drug_cursor").children("div:last-child").attr("value"));
        var str = []
        for(var i=1;i<=count;i++){
            var temp = '{"patient_cert_code":'+result['patient_cert_code']+',"drug_name":"'+result['drug_name'+i]+'","drug_num":'+result['drug_num'+i]+'}'
            str.push(JSON.parse(temp))
        }
        console.log(str)
        var index = layer.load(2);
        $.ajax({
            url:'/hospital/patient/makePrescribtion',
            type:'post',
            contentType:"application/json",
            datatype:"json",
            data : JSON.stringify(str)
        }).done(function (data) {
            if(data.code==0){
                layer.close(index);
                layer.msg("成功",{time: 1000},function () {
                    tableIns.reload({where: {patient_cert_code: '',}, page: {curr: 1}})
                })
            }else{
                layer.close(index);
                layer.msg(data.msg,{time:1000})
            }
        })
    })
    form.render();
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
            {field: 'treatment_time', title: '手术时间', width: '10%', unresize: true},
            {field: 'treatment_fee', title: '手术费用', width: '10%', unresize: true},
            {
                field: 'action',
                title: '操作',
                width: '10%',
                unresize: true,
                toolbar: '<div><button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="allot">分配病房</button><button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="make">开药</button></div>'
            }
        ]]
    });
    table.on('tool(patient_table)',function (obj) {
        var datas = obj.data;
        $('#sick_roms').empty();
        form.val("sickForm",{"sickbed_id":""})
        if(obj.event=='allot'){
            $.ajax({ //getSickroomCount
                url:'/hospital/patient/getSickroomCount',
                type:'post',
                contentType:"application/json",
                datatype:"json",
            }).done(function (data) {
                if(data.code==0){
                    var html = '<option value=""></option>';
                    for(var i = 0;i<data.data;i++){
                        html += '<option value="'+(i+1)+'">'+(i+1)+'</option>';
                    }
                    $('#sick_roms').append(html)
                    form.render();
                    form.val("sickForm",{"patient_cert_code":datas.patient_cert_code})
                    $('#sick_Modal').modal('show');
                }else{
                    layer.msg(data.msg)
                }
            })
        }else if(obj.event=='make'){
            $("#drug_cursor").empty();
            $("#drug_cursor").append('<div class="layui-form-item" value="1">\n' +
                '                                <div class="layui-inline">\n' +
                '                                    <label class="layui-form-label">名称</label>\n' +
                '                                    <div class="layui-input-inline">\n' +
                '                                        <input type="text" name="drug_name1" placeholder="药品名称" autocomplete="off" lay-verify="required" class="layui-input">\n' +
                '                                    </div>\n' +
                '                                    <div class="layui-form-mid">数量</div>\n' +
                '                                    <div class="layui-input-inline">\n' +
                '                                        <input type="number" name="drug_num1" placeholder="药品数量" autocomplete="off" lay-verify="required" class="layui-input">\n' +
                '                                    </div>\n' +
                '                                </div>\n' +
                '                            </div>');
            form.val("drug_form",{"patient_cert_code":datas.patient_cert_code})
            $('#prescription_Modal').modal('show')
        }
    })
}
function search() {
    tableIns.reload({where: {patient_cert_code: $('#search_id').val(),}, page: {curr: 1}})
    $('#search_id').val('');
}
function insert() {
    form.val("insertForm",{"patient_cert_code":"","treatment_time":"","treatment_name":"","treatment_fee":""})
    $('#myModal').modal('show');
}
function insertPatient() {
    $('#hideSubmit').click()
}
function allotSick() {
    $('#sickHideSubmit').click()
}
function prescription() {
    $('#prescriptionHideSubmit').click()
}
function formadd() {
    var last_id=parseInt($("#drug_cursor").children("div:last-child").attr("value"))+1;
    console.log(last_id)
    var html = '<div class="layui-form-item" value="'+last_id+'">\n' +
        '                                <div class="layui-inline">\n' +
        '                                    <label class="layui-form-label">名称</label>\n' +
        '                                    <div class="layui-input-inline">\n' +
        '                                        <input type="text" name="drug_name'+last_id+'" placeholder="药品名称" autocomplete="off" lay-verify="required" class="layui-input">\n' +
        '                                    </div>\n' +
        '                                    <div class="layui-form-mid">数量</div>\n' +
        '                                    <div class="layui-input-inline">\n' +
        '                                        <input type="number" name="drug_num'+last_id+'" placeholder="药品数量" autocomplete="off" lay-verify="required" class="layui-input">\n' +
        '                                    </div>\n' +
        '                                </div>\n' +
        '                            </div>'
    $("#drug_cursor").append(html);
    form.render()
}
function hideAll() {
    $('#patient_info').hide();
}