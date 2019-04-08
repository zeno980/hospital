$(document).ready(function () {
    var form = layui.form;
    form.render();
})
function login() {
    var layer = layui.layer;
    var cert_code = $('#account').val();
    var pwd = hex_md5($('#password').val());
    var status = $('#status').val();
    if(cert_code == "" || cert_code == null || cert_code == undefined||
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
        data : JSON.stringify({"cert_code":cert_code,"password":pwd,"page":status})
    }).done(function (data) {
        if(data.code==0){
            layer.close(index)
            layer.msg("登录成功，正在跳转...",{time: 1000},function () {
                console.log(data)
                window.location.href='/hospital/'+data.data;
            })
        }else{
            layer.close(index)
            layer.msg(data.msg)
        }
    })
}