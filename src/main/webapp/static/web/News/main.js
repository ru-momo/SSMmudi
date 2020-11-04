
$(function () {
    $("#subfrm").click(function () {
        console.log($("#pubdate").val());
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
        if (label === "编辑") {
            var url = "change";
            sub(url);
        }
        if (label === "添加") {
            var url = "add";
            sub(url);
        }
    })

    $("#addnew").click(function () {
        $("input[type=reset]").trigger("click");
        $("#content").text("");
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
    $.ajax({
        type: "post",
        url: url,
        data: $("#frm").serialize(),
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
            if (data.code === 500) {
                alert(data.data);
                $('#myModal').modal('hide');
            } else if (data.code === 200) {
                $("#myModalLabel").text("编辑");
                $("#id").val(info.id);
                $("#title").val(info.title);
                $("#content").text(info.content);
                $("#pubdate").val(info.pubdate);
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