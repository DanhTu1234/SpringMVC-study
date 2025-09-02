<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<c:url var="departmentURL" value="/quan-tri/khoa/danh-sach"/>
<c:url var="editDepartmentURL" value="/quan-tri/khoa/chinh-sua"/>
<c:url var="departmentAPI" value="/api/department" />

<html>
<head>
    <title>Thêm Mới Khoa/Viện</title>
</head>
<body>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">
            Thêm mới sản phẩm
            <a href="#" style="float:right">Danh sách</a>
        </h6>
    </div>
    <div class="card-body">
        <form id="formSubmit">
            <div class="form-group">
                <label class="control-label col-md-2">Tên Khoa/Vện</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="Name" name="Name" value=""/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm khoa" id="btnAddDepartment"/>
                </div>
            </div>

            <input type="hidden" value="${model.id}" id="id" name="id"/>
        </form>
    </div>
</div>

<script>

    $('#btnAddDepartment').click(function (e) {
        e.preventDefault();
        var id = $('#id').val();
        var name = $('#Name').val();
        // Tạo object dữ liệu
        var data = {
            id: id,
            name: name
        };
        addDepartment(data);
    });

    function addDepartment(data) {
        $.ajax({
            url: '${departmentAPI}',
            type: 'POST',
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
