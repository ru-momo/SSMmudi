
$(function () {
    $("#subfrm").click(function () {
        var label = $("#myModalLabel").text();
        var name = $("#tname");
        var info = $("#info");
        var introduce = $("#introduce");
        var alcoholicStrength = $("#alcoholicStrength");
        var netWeight = $("#netWeight");
        var saleCount = $("#saleCount");
        var bigImg = $("#file").get(0).files[0];
        console.log(bigImg)

        if (name.val() === null || name.val() === "") {
            alert("名称不能为空");
            return false;
        }
        if (info.val() === null || info.val() === "") {
            alert("简介内容不能为空");
            return false;
        }
        if (introduce.val() === null || introduce.val() === "") {
            alert("介绍内容不能为空");
            return false;
        }
        if (alcoholicStrength.val() === null || alcoholicStrength.val() === "") {
            alert("酒精度不能为空");
            return false;
        }
        if (netWeight.val() === null || netWeight.val() === "") {
            alert("净含量不能为空");
            return false;
        }
        if (saleCount.val() === null || saleCount.val() === "") {
            alert("销量不能为空");
            return false;
        }
        if (label === "添加" && !bigImg) {
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
                $("#tname").val(info.name);
                $("#info").val(info.info);
                $("#introduce").val(info.introduce);
                $("#typeId").val(info.typeId);
                $("#alcoholicStrength").val(info.alcoholicStrength);
                $("#areaId").val(info.areaId);
                $("#netWeight").val(info.netWeight);
                $("#saleCount").val(info.saleCount);
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