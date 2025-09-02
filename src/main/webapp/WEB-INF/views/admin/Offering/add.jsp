<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<c:url var="offeringURL" value="/quan-tri/lop-hoc-phan/danh-sach"/>
<c:url var="offeringAPI" value="/api/offering" />

<html>
<head>
    <title>Thêm Mới Lớp Học Phần</title>
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
                <label class="control-label col-md-2">Mã lớp học phần</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="offeringCode" name="offeringCode" value=""/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2">Tên khóa học</label>
                <div class="col-md-10">
                    <select class="form-control" id="courseCatalog_id" name="courseCatalog_id">
                        <option value="">Chọn tên khóa học</option>

                        <c:forEach var="item" items="${courseCatalog}">
                            <option value="${item.id}">${item.title}</option>
                        </c:forEach>

                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2">Tên năm học</label>
                <div class="col-md-10">
                    <select class="form-control" id="semester_id" name="semester_id">
                        <option value="">Chọn năm học</option>

                        <c:forEach var="item" items="${semester}">
                            <option value="${item.id}">${item.name}</option>
                        </c:forEach>

                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2">Tên giáo viên</label>
                <div class="col-md-10">
                    <select class="form-control" id="user_id" name="user_id">
                        <option value="">Chọn giáo viên</option>

                        <c:forEach var="item" items="${user}">
                            <option value="${item.id}">${item.fullName}</option>
                        </c:forEach>

                    </select>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm lớp" id="btnAddOffering"/>
                </div>
            </div>

            <input type="hidden" value="${model.id}" id="id" name="id"/>
        </form>
    </div>
</div>

<script>

    $('#btnAddOffering').click(function (e) {
        e.preventDefault();
        var id = $('#id').val();
        var offeringCode = $('#offeringCode').val();
        var courseCatalog_id = $('#courseCatalog_id').val();
        var semester_id = $('#semester_id').val();
        var user_id = $('#user_id').val();
        // Tạo object dữ liệu
        var data = {
            id: id,
            offering_code: offeringCode,
            course_id: courseCatalog_id,
            semester_id: semester_id,
            teacher_id: user_id
        };
        addOffering(data);
    });

    function addOffering(data) {
        $.ajax({
            url: '${offeringAPI}',
            type: 'POST',
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
