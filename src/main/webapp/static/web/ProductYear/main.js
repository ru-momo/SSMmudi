
$(function () {
    $("#subfrm").click(function () {
        var label = $("#myModalLabel").text();
        var wineId = $("#wineId");
        var year = $("#year");
        var price = $("#price");
        var stock = $("#stock");

        if (wineId.val() === null || wineId.val() === "") {
            alert("编号不能为空");
            return false;
        }
        if (year.val() === null || year.val() === "") {
            alert("年份不能为空");
            return false;
        }
        if (price.val() === null || price.val() === "") {
            alert("价格不能为空");
            return false;
        }
        if (stock.val() === null || stock.val() === "") {
            alert("库存不能为空");
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
                $("#wineId").val(info.wineId);
                $("#year").val(info.year);
                $("#price").val(info.price);
                $("#stock").val(info.stock);
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