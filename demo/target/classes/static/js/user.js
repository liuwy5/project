$(document).ready(function () {
    // 初始化表格
    var _table = $('#dataTable').DataTable({
        language: {
            search: '在表格中搜索：'
        },
        ordering: false,
        "serverSide": true,
        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = getQueryCondition($("#formData"));
            //ajax请求数据方法
            $.ajax({
                type: "POST",
                url: "/rest/api/user/list",//url请求的地址
                cache: false,  //禁用缓存
                data: param,  //传入组装的参数
                dataType: "json",
                success: function (result) {
                    //封装返回数据重要
                    var returnData = {};
                    //这里直接自行返回了draw计数器,应该由后台返回
                    // returnData.draw = data.draw;
                    //返回数据全部记录
                    //后台不实现过滤功能，每次查询均视作全部结果
                    //返回的数据列表
                    returnData.data = result;

                    //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                    //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                    callback(returnData);
                }
            });
        },
        columns: [
            { data: 'id' },
            { data: 'uuid' },
            { data: 'username' },
            { data: 'password' },
            {
                data: 'imgPath' ,
                render: function(data, type, full, meta) {
                    return '<img src="' + data + '" style="width: 100px;" /> ';
                }
            }
        ]

    });

    // 搜索
    $("#searchBtn").click(function(){
        _table.draw();
    });

    // 添加
    $('#saveAddData').click(function () {
        $("#addDataForm .warn-info").html('');
        var flag = true;
        var params = {};
        var formData = new FormData();
        $("#addDataForm input").each(function () {
            var value = $(this).val();
            var name = $(this).attr("name");
            if (value != null && name != 'name') {
                var val = value.trim();
                if (val != '') {
                    params[name] = val;
                    formData.append(name, val);
                } else {
                    $(".warn-info").html("<i class='fa fa-exclamation-circle'></i>&nbsp;请完善user信息");
                    flag = false;
                    return;
                }
            }
        });

        if (!flag) {
            return;
        }

        var img = $('#addName')[0]
        if (img.files && img.files[0]) {
            var reader = new FileReader();
            reader.onload = function (ev) {
                $('#addImg').src = ev.target.result;
            }
        } else {
            alert('请上传图片');
        }

        formData.append("file", $('#addName')[0].files[0]);

        console.log(formData);
        console.log(params);

        $.ajax({
            url: "/rest/api/user/add",
            type: "POST",
            data: formData,
            dataType: "json",
            async: false,
            cache: false,
            processData: false,
            contentType: false,
            success: function(result) {
                alert(result);
            }
        });
    });

    $("#addName").change(function () {
        var file = $('#addName')[0].files[0]
        var img = $('#addName')[0];
        if (img.files && img.files[0]) {
            var reader = new FileReader();
            reader.readAsDataURL(file);
            console.log('------------');
            reader.onload = function () {
                // $('#addImg').src = ev.target.result;
                $('#addImg').show();
                $('#addImg').attr("src", this.result);
            }
        }
        // else {
        //     alert('请上传图片');
        // }
    })

    function getQueryCondition($form) {
        var params = {}
        $form.find(':input[name]').each(function () {
            var name = $(this).attr("name");
            var value = $(this).val();
            if(value != null){
                var val = value.trim();
                if (val != "") {
                    params[name] = val;
                }
            }
        });
        return params;
    }
});