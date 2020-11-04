$(function () {
    $("#subfrm").click(function () {
        if ($("#tname").val() === null || $("#title").val() === "") {
            alert("地区名称不能为空");
            return false;
        }
        if ($("#parentId").val() === null) {
            alert("地区等级不能为空");
            return false;
        }
        var label = $("#myModalLabel").text();
        if (label === "编辑") {
            var url = "updInfo";
            sub(url);
        }
        if (label === "添加") {
            var url = "addInfo";
            sub(url);
        }
    })

    $("#addnew").click(function () {
        $("input[type=reset]").trigger("click");
        $("#myModalLabel").text("添加");
    })
})




function del(id) {
    if (confirm("你确定要删除吗？")) {
        $.ajax({
            url: "delInfo?id=" + id,
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
                if (data.data.indexOf("不") !== -1){
                    reload();
                }
            }
        }
    })
}

function change(id) {
    $.ajax({
        url: "getInfo?id=" + id,
        type: "get",
        success: function (data) {
            console.log(data);
            var info = data.data;
            if (data.code === 500) {
                alert(data.data);
                $('#myModal').modal('hide');
            } else if (data.code === 200) {
                $("#myModalLabel").text("编辑");
                $("#id").val(info.id);
                $("#tname").val(info.name);
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