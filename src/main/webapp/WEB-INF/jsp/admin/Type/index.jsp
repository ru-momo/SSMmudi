<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/Css/style.css"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/Js/jquery.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/Js/bootstrap.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/Js/ckform.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/Js/common.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/web/Type/main.js"></script>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/web/Type/main.css"/>


</head>
<body>
<form class="form-inline definewidth m20" action="index" method="get">
    中文名称： <input type="text" name="name" id="name"
                 class="abc input-default" placeholder="" value="${name}">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
    <button type="button" class="btn btn-success" id="addnew" data-toggle="modal" data-target="#myModal">添加</button>&nbsp;&nbsp;
    <button type="button" class="btn btn-success" onclick="reload()">刷新</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>类型编号</th>
        <th>中文名称</th>
        <th>英文名称</th>
        <th>级别编号(0为父级)</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${page.rows}" var="nl">
        <tr>
            <td width="10%">${nl.id}</td>
            <td width="20%">${nl.cname}</td>
            <td width="20%">${nl.ename}</td>
            <td width="20%">${nl.parentId}</td>
            <td width="10%">
                <button onclick="change(${nl.id})" type="button" class="btn btn-warning" data-toggle="modal"
                        data-target="#myModal">编辑
                </button>
                <button type="button" onclick="del(${nl.id})" class="btn btn-danger">删除</button>
            </td>
        </tr>
    </c:forEach>
</table>
<div class="inline pull-right page">
    ${page.sum} 条记录 ${page.page}/${page.total} 页
    <c:if test="${page.page+1<=page.total}">
        <a href='index?pageNum=${page.page+1}&name=${name}'>下一页</a>
    </c:if>
    <c:forEach begin="1" end="${page.total}" var="num">
        <c:if test="${page.page == num}">
            <span class='current'>${num}</span>
        </c:if>
        <c:if test="${page.page != num}">
            <a href='index?pageNum=${num}&name=${name}'>${num}</a>
        </c:if>
    </c:forEach>
    <a href='index?pageNum=${page.total}&name=${name}'>最后一页</a>
</div>


<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
            <form id="frm" method="post" enctype="multipart/form-data">
                <input type="hidden" name="id" id="id">
                <input type="reset" name="reset" style="display: none;" />
                <table class="table table-bordered table-hover definewidth m10">
                    <tr>
                        <td class="tableleft">中文名称</td>
                        <td><input type="text" id="cname" name="cname"/></td>
                    </tr>
                    <tr>
                        <td class="tableleft">英文名称</td>
                        <td><input type="text" id="ename" name="ename"></td>
                    </tr>
                    <tr>
                        <td class="tableleft">级别编号(0为父级)</td>
                        <td><select id="parentId" name="parentId">
                            <option>0</option>
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select></td>
                    </tr>
                </table>
            </form>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
                <button id="subfrm" type="button" class="btn btn-primary">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


</body>
</html>
