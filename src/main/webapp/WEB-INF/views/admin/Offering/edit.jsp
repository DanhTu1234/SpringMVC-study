<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<c:url var="offeringURL" value="/quan-tri/lop-hoc-phan/danh-sach"/>
<c:url var="offeringAPI" value="/api/offering" />

<html>
<head>
    <title>Chỉnh Sửa Khoa/Viện</title>
</head>
<body>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">
            Cập nhật Khoa/Viện
            <a href="${offeringURL}" style="float:right">Danh sách</a>
        </h6>
    </div>
    <div class="card-body">
        <form id="formSubmit" modelAttribute="model">
            <div class="form-group">
                <label class="control-label col-md-2">Mã lớp học phần</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="offeringCode" name="offeringCode" value="${model.offeringCode}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2">Tên khóa học</label>
                <div class="col-md-10">
                    <select class="form-control" id="courseCatalog_id" name="courseCatalog_id">

                        <c:forEach var="item" items="${courseCatalog}">
                            <option value="${item.id}"
                                <c:if test="${item.id == model.courseId}">selected="selected"</c:if>>${item.title}
                            </option>

                        </c:forEach>

                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2">Tên năm học</label>
                <div class="col-md-10">
                    <select class="form-control" id="semester_id" name="semester_id">

                        <c:forEach var="item" items="${semester}">
                            <option value="${item.id}"
                                <c:if test="${item.id == model.semesterId}">selected="selected"</c:if>>${item.name}
                            </option>
                        </c:forEach>

                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2">Tên giáo viên</label>
                <div class="col-md-10">
                    <select class="form-control" id="user_id" name="user_id">

                        <c:forEach var="item" items="${user}">
                            <option value="${item.id}"
                                    <c:if test="${item.id == model.teacherId}">selected="selected"</c:if>>${item.fullName}
                            </option>
                        </c:forEach>

                    </select>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật" id="btnUpdateOffering"/>
                </div>
            </div>

            <input type="hidden" value="${model.id}" id="id" name="id"/>
            <input type="hidden" value="${model.lms_Offering_Id}" id="lms_Offering_id" name="lms_Offering_id">
        </form>
    </div>
</div>

<script>

    $('#btnUpdateOffering').click(function (e) {
        e.preventDefault();
        var id = $('#id').val();
        var offeringCode = $('#offeringCode').val();
        var courseId = $('#courseCatalog_id').val();
        var semesterId = $('#semester_id').val();
        var teacherId = $('#user_id').val();
        var lms_Offering_Id = $('#lms_Offering_id').val();
        // Tạo object dữ liệu
        var data = {
            id: id,
            offering_code: offeringCode,
            course_id: courseId,
            semester_id: semesterId,
            teacher_id: teacherId,
            lms_offering_id: lms_Offering_Id
        };
        updateOffering(data);
    });

    function updateOffering(data) {
        $.ajax({
            url: '${offeringAPI}/' + data.id,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${offeringURL}";
            },
            error: function (error) {
                window.location.href = "${offeringURL}";
            }
        });
    }
</script>

</body>
</html>
