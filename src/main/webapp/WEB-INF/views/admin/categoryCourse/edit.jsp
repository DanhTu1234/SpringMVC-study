<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<c:url var="categoryCourseURL" value="/quan-tri/danh-muc/danh-sach"/>
<c:url var="editCategoryCourseURL" value="/quan-tri/danh-muc/chinh-sua"/>
<c:url var="categoryCourseAPI" value="/api/category" />

<html>
<head>
    <title>Chỉnh Sửa Khoa</title>
</head>
<body>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">
            Cập nhật Khoa
            <a href="${categoryCourseURL}" style="float:right">Danh sách</a>
        </h6>
    </div>
    <div class="card-body">
        <form id="formSubmit" modelAttribute="model">
            <div class="form-group">
                <label class="control-label col-md-2">Tên Khoa</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="Name" name="Name" value="${model.name}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2">Mô tả</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="Description" name="Description" value="${model.description}"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật" id="btnUpdateCategoryCourse"/>
                </div>
            </div>

            <input type="hidden" value="${model.id}" id="id" name="id"/>
            <input type="hidden" value="${model.lms_category_id}" id="lms_Category_Id" name="lms_Category_Id">
        </form>
    </div>
</div>

<script>
    $('#btnUpdateCategoryCourse').click(function (e) {
        e.preventDefault();
        var id = $('#id').val();
        var name = $('#Name').val();
        var description = $('#Description').val();
        var lms_Category_Id = $('#lms_Category_Id').val();
        // Tạo object dữ liệu
        var data = {
            id: id,
            name: name,
            description: description,
            lms_category_id: lms_Category_Id
        };
        updateCategoryCourse(data);
    });

    function updateCategoryCourse(data) {
        $.ajax({
            url: '${categoryCourseAPI}/' + data.id,
            type: 'PUT',
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
