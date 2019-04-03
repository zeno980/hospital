$(document).ready(function () {
    $.ajax({
        url:'/hospital/user/getDepartment.do',
        type:'post',
        contentType:"application/json",
        datatype:"json",
    }).done(function (data) {
        console.log(data.data)
        if(data.code==0){
            var html = "<option value=''></option>";
            for(var i=0;i<data.data.length;i++){
                html += "<option value='"+data.data[i].department_id+"'>"+data.data[i].department_name+"</option>"
            }
            $('#department_cursor').append(html)
            var form = layui.form;
            form.on('select(department)', function (data) {
                var id = data.value;
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
                    }
                })
            })
            form.on('submit(submitForm)',function(data){
                var json = data.field;
                console.log(json)
                json['password'] = "88888888";
                console.log(json)
            })
            form.render();
        }
    })
})