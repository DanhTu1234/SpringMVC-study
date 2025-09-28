<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<c:url var="categoryCourseURL" value="/quan-tri/danh-muc/danh-sach"/>
<c:url var="editCategoryCourseURL" value="/quan-tri/danh-muc/chinh-sua"/>
<c:url var="categoryCourseAPI" value="/api/category" />

<html>
<head>
    <title>Thêm Mới Khoa</title>
</head>
<body>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">
            Thêm mới khóa học
            <a href="${categoryCourseURL}" style="float:right">Danh sách</a>
        </h6>
    </div>
    <div class="card-body">
        <form id="formSubmit">
            <div class="form-group">
                <label class="control-label col-md-2">Tên Khoa</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="Name" name="Name" value=""/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2">Mô tả</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="Description" name="Description" value=""/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm khoa" id="btnAddCategoryCourse"/>
                </div>
            </div>

            <input type="hidden" value="${model.id}" id="id" name="id"/>
        </form>
    </div>
</div>

<script>

    $('#btnAddCategoryCourse').click(function (e) {
        e.preventDefault();
        var id = $('#id').val();
        var name = $('#Name').val();
        var description = $('#Description').val();
        // Tạo object dữ liệu
        var data = {
            id: id,
            name: name,
            description: description
        };
        addCategoryCourse(data);
    });

    function addCategoryCourse(data) {
        $.ajax({
            url: '${categoryCourseAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${categoryCourseURL}";
            },
            error: function (error) {
                window.location.href = "${categoryCourseURL}";
            }
        });
    }
</script>

</body>
</html>
