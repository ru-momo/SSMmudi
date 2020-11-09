
$(function () {
    $("#subfrm").click(function () {
        var bigImg = $("#img1").get(0).files[0];
        var bigImg2 = $("#img2").get(0).files[0];
        dateFormat =/^(\d{4})-(\d{2})-(\d{2})$/;
        if ($("#title").val() === null || $("#title").val() === "") {
            alert("标题不能为空");
            return false;
        }
        if ($("#pubdate").val() === null) {
            alert("发布日期不能为空");
            return false;
        }
        if (!dateFormat.test($("#pubdate").val())) {
            alert("日期格式不正确");
            return false;
        }
        if ($("#content").val() === null || $("#content").val() === "") {
            alert("新闻内容不能为空");
            return false;
        }
        var label = $("#myModalLabel").text();

        if (label === "添加" && !bigImg) {
            alert("必须上传文件");
            return false;
        }
        if (label === "添加" && !bigImg2) {
            alert("必须上传文件");
            return false;
        }
        if (label === "编辑") {
            var typeurl = "change";
            sub(typeurl);
        }
        if (label === "添加") {
            var url = "add";
            sub(url);
        }
    })

    $("#addnew").click(function () {
        $("input[type=reset]").trigger("click");
        $("#content").text("");
        $("#id").val("");
        $("#myModalLabel").text("添加");
    })

})


function del(id) {
    if (confirm("你确定要删除吗？")) {
        $.ajax({
            url: "del?id=" + id,
            type: "get",
            success: function (data) {
                console.log(data);
                if (data.code === 200) {
                    alert(data.data);
                    window.location.href = "index";
                } else {
                    alert(data.data);
                    reload();
                }
            }
        });
    }

}

function sub(url) {
    var formData = new FormData($("#frm")[0]);
    $.ajax({
        type: "post",
        url: url,
        data: formData,
        cache: false,        // 不缓存数据
        processData: false,  // 不处理数据
        contentType: false,   // 不设置内容类型
        success: function (data) {
            if (data.code === 200) {
                alert(data.data);
                window.location.href = "index";
            } else {
                alert(data.data);
                if (data.data.indexOf("id") !== -1){
                    reload();
                }
            }
        }
    })
}

function change(id) {
    $.ajax({
        url: "findById?id=" + id,
        type: "get",
        success: function (data) {
            var info = data.data;
            console.log(info);
            if (data.code === 500) {
                alert(data.data);
                $('#myModal').modal('hide');
            } else if (data.code === 200) {
                $("#myModalLabel").text("编辑");
                $("#id").val(info.id);
                $("#title").val(info.title);
                $("#content").text(info.content);
                $("#pubdate").val(info.pubdate);
                // $("#img1").val(info.img1);
                // $("#img2").val(info.img2);
                if (info.type === 1) {
                    $("#radio1").attr("checked", "");
                }
                if (info.type === 2) {
                    $("#radio2").attr("checked", "");
                }
            }
        }
    })
}

function reload() {
    $.ajax({
        url: "reload",
        type: "get",
        success: function (data) {
            window.location.reload();
        }
    })
}