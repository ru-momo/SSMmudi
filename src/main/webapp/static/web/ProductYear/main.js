
$(function () {
    $("#subfrm").click(function () {
        var label = $("#myModalLabel").text();
        var cname = $("#cname");
        var ename = $("#ename");
        var parentId = $("#parentId");

        if (cname.val() === null || cname.val() === "") {
            alert("名称不能为空");
            return false;
        }
        if (ename.val() === null || ename.val() === "") {
            alert("简介内容不能为空");
            return false;
        }
        if (parentId.val() === null || parentId.val() === "") {
            alert("介绍内容不能为空");
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
        $("#id").val("");
        $("#myModalLabel").text("添加");
    })

})


function del(id) {
    if (confirm("你确定要删除吗？")) {
        $.ajax({
            url: "del",
            type: "post",
            data:{id:id},
            dataType:"json",
            success: function (data) {
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
            }
        }
    })
}

function change(id) {
    $.ajax({
        url: "getInfoById?id=" + id,
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
                $("#cname").val(info.cname);
                $("#ename").val(info.ename);
                $("#parentId").val(info.parentId);
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