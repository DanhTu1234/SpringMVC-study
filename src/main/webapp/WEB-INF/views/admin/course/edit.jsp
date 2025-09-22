<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<c:url var="courseURL" value="/quan-tri/khoa-hoc/danh-sach"/>
<c:url var="editCourseURL" value="/quan-tri/khoa-hoc/chinh-sua"/>
<c:url var="courseAPI" value="/api/course" />

<html>
<head>
    <title>Chỉnh Sửa KHóa Học</title>
</head>
<body>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">
            Cập nhật Khóa Học
            <a href="${courseURL}" style="float:right">Danh sách</a>
        </h6>
    </div>
    <div class="card-body">
        <form id="formSubmit" modelAttribute="model">
            <div class="form-group">
                <label class="control-label col-md-2">Tên khoa</label>
                <div class="col-md-10">
                    <select class="form-control" id="courseCategory_id" name="courseCategory_id">

                        <c:forEach var="item" items="${courseCategory}">
                            <option value="${item.id}"
                                    <c:if test="${item.id == model.categoryCourseId}">selected="selected"</c:if>>${item.name}
                            </option>

                        </c:forEach>

                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2">Tên Khóa Học</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="fullName" name="fullName" value="${model.fullName}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2">Mã học phần</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="shortName" name="shortName" value="${model.shortName}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2">Mô tả</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="summary" name="summary" value="${model.summary}"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật" id="btnUpdateCourse"/>
                </div>
            </div>

            <input type="hidden" value="${model.id}" id="id" name="id"/>
            <input type="hidden" value="${model.lmsCourseId}" id="lms_Course_Id" name="lms_Course_Id">
        </form>
    </div>
</div>

<script>
    $('#btnUpdateCourse').click(function (e) {
        e.preventDefault();
        var id = $('#id').val();
        var courseCategory_id = $('#courseCategory_id').val();
        var fullName = $('#fullName').val();
        var shortName = $('#shortName').val();
        var summary = $('#summary').val();
        var lms_Course_Id = $('#lms_Course_Id').val();
        // Tạo object dữ liệu
        var data = {
            id: id,
            category_id: courseCategory_id,
            fullname: fullName,
            shortname: shortName,
            summary: summary,
            lms_course_id: lms_Course_Id
        };
        updateCourse(data);
    });

    function updateCourse(data) {
        $.ajax({
            url: '${courseAPI}/' + data.id,
            type: 'PUT',
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
