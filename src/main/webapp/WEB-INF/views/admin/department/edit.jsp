<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<c:url var="departmentURL" value="/quan-tri/khoa/danh-sach"/>
<c:url var="editDepartmentURL" value="/quan-tri/khoa/chinh-sua"/>
<c:url var="departmentAPI" value="/api/department" />

<html>
<head>
    <title>Chỉnh Sửa Khoa/Viện</title>
</head>
<body>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">
            Cập nhật Khoa/Viện
            <a href="${departmentURL}" style="float:right">Danh sách</a>
        </h6>
    </div>
    <div class="card-body">
        <form id="formSubmit" modelAttribute="model">
            <div class="form-group">
                <label class="control-label col-md-2">Tên Khoa/Vện</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="Name" name="Name" value="${model.name}"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật" id="btnUpdateDepartment"/>
                </div>
            </div>

            <input type="hidden" value="${model.id}" id="id" name="id"/>
        </form>
    </div>
</div>

<script>

    $('#btnUpdateDepartment').click(function (e) {
        e.preventDefault();
        var id = $('#id').val();
        var name = $('#Name').val()
        // Tạo object dữ liệu
        var data = {
            id: id,
            name: name
        };
        updateDepartment(data);
    });

    function updateDepartment(data) {
        $.ajax({
            url: '${departmentAPI}/' + data.id,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${departmentURL}";
            },
            error: function (error) {
                window.location.href = "${departmentURL}";
            }
        });
    }
</script>

</body>
</html>
