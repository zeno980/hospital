$(document).ready(function () {
    var form = layui.form;
    form.render();
})
function login() {
    var layer = layui.layer;
    var doctorId = $('#account').val();
    var pwd = hex_md5($('#password').val());
    var status = $('#status').val();
    if(doctorId == "" || doctorId == null || doctorId == undefined||
        pwd == "" || pwd == null || pwd == undefined||
        status == "" || status == null || status == undefined){
        return
    };
    var index = layer.load(2);
    $.ajax({
        url:'/hospital/user/login.do',
        type:'post',
        contentType:"application/json",
        datatype:"json",
        data : JSON.stringify({"doctor_id":doctorId,"password":pwd,"page":status})
    }).done(function (data) {
        if(data.code==0){
            layer.close(index)
            layer.msg("登录成功，正在跳转...",{time: 1000},function () {
                window.location.href='/hospital/doctor/'+data.data;
            })
        }else{
            layer.close(index)
            layer.msg(data.msg)
        }
    })
}