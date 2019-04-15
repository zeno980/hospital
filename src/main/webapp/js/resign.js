$(document).ready(function () {
    var layer = layui.layer;
    var index1 = layer.msg('获取部门信息中',{time: 20*5000,icon: 16})
    $.ajax({
        url:'/hospital/user/getDepartment.do',
        type:'post',
        contentType:"application/json",
        datatype:"json",
    }).done(function (data) {
        console.log(data.data)
        if(data.code==0){
            layer.close(index1)
            var html = "<option value=''></option>";
            for(var i=0;i<data.data.length;i++){
                html += "<option value='"+data.data[i].department_id+"'>"+data.data[i].department_name+"</option>"
            }
            $('#department_cursor').append(html)
            var form = layui.form;
            form.on('select(department)', function (data) {
                var id = data.value;
                if(id=='')return;
                $.ajax({
                    url:'/hospital/user/getPosition.do',
                    type:'post',
                    contentType:"application/json",
                    datatype:"json",
                    data:JSON.stringify({'department_id':id})
                }).done(function (data) {
                    if(data.code==0) {
                        $('#position_cursor').empty()
                        var html = "<option value=''></option>";
                        for (var i = 0; i < data.data.length; i++) {
                            html += "<option value='" + data.data[i].position_id + "'>" + data.data[i].position_name + "</option>"
                        }
                        $('#position_cursor').append(html)
                        form.render("select")
                    }else{
                        layer.close(index1)
                        layer.msg(data.msg,{time: 1000})
                    }
                })
            })
            form.on('submit(submitForm)',function(data){
                var index = layer.load(2);
                var json = data.field;
                json['password'] = hex_md5(json['password']);
                console.log(json)
                $.ajax({
                    url:'/hospital/user/insertDoctor.do',
                    type:'post',
                    contentType:"application/json",
                    datatype:"json",
                    data:JSON.stringify(json)
                }).done(function (data) {
                    if(data.code==0){
                        layer.close(index);
                        layer.msg("提交成功",{time: 1000},function () {
                            console.log(data)
                            window.location.href='/hospital/user/login';
                        })
                    }else{
                        layer.close(index);
                        layer.msg(data.msg,{time: 1000})
                    }
                })
            })
            form.render();
        }
    })
})