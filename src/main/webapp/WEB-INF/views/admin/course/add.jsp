<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<c:url var="courseURL" value="/quan-tri/khoa-hoc/danh-sach"/>
<c:url var="editCourseURL" value="/quan-tri/khoa-hoc/chinh-sua"/>
<c:url var="courseAPI" value="/api/course" />

<html>
<head>
    <title>Thêm Mới Khóa Học</title>
</head>
<body>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">
            Thêm mới khóa học
            <a href="#" style="float:right">Danh sách</a>
        </h6>
    </div>
    <div class="card-body">
        <form id="formSubmit">
            <div class="form-group">
                <label class="control-label col-md-2">Tên Khoa</label>
                <div class="col-md-10">
                    <select class="form-control" id="courseCategory_id" name="courseCategory_id">
                        <option value="">Chọn tên khóa học</option>

                        <c:forEach var="item" items="${courseCategory}">
                            <option value="${item.id}">${item.name}</option>
                        </c:forEach>

                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2">Tên Khóa Học</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="fullName" name="fullName" value=""/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2">Mã học phần</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="shortName" name="shortName" value=""/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2">Mô tả</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="summary" name="summary" value=""/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm khóa học" id="btnAddCourse"/>
                </div>
            </div>

            <input type="hidden" value="${model.id}" id="id" name="id"/>
        </form>
    </div>
</div>

<script>

    $('#btnAddCourse').click(function (e) {
        e.preventDefault();
        var id = $('#id').val();
        var courseCategory_id = $('#courseCategory_id').val();
        var fullName = $('#fullName').val();
        var shortName = $('#shortName').val();
        var summary = $('#summary').val();
        // Tạo object dữ liệu
        var data = {
            id: id,
            category_id: courseCategory_id,
            fullname: fullName,
            shortname: shortName,
            summary: summary
        };
        addCourse(data);
    });

    function addCourse(data) {
        $.ajax({
            url: '${courseAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${courseURL}";
            },
            error: function (error) {
                window.location.href = "${courseURL}";
            }
        });
    }
</script>

</body>
</html>
