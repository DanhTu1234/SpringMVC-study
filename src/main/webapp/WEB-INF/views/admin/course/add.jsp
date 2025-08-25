<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<c:url var="courseURL" value="/quan-tri/khoa-hoc/danh-sach"/>
<c:url var="editCourseURL" value="/quan-tri/khoa-hoc/chinh-sua"/>
<c:url var="courseAPI" value="/api/course" />

<html>
<head>
    <title>Thêm mới khóa học</title>
</head>
<body>

<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            <ul class="breadcrumb">

                <li class="active">Thêm mới khóa học</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-42">
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-${alert}">
                                ${messageResponse}
                        </div>
                    </c:if>
                    <form id="formSubmit" modelAttribute="model">
                        <div class="form-group">
                            <label class="col-sm-42 control-label no-padding-right">Thể loại</label>
                            <div class="col-sm-42">
                                <select class="form-control" id="category_id" name="category_id">
                                    <option value="">Chọn loại khóa học</option>

                                    <c:forEach var="item" items="${categories}">
                                        <option value="${item.id}">${item.name}</option>
                                    </c:forEach>

                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-42 control-label no-padding-right">Tên khóa học</label>
                            <div class="col-sm-42">
                                <input type="text" class="form-control" id="fullName" name="fullName" value=""/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-42 control-label no-padding-right">Tên ngắn</label>
                            <div class="col-sm-42">
                                <input type="text" class="form-control" id="shortName" name="shortName" value=""/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-42 control-label no-padding-right">Mô tả ngắn</label>
                            <div class="col-sm-42">
                                <input type="text" class="form-control" id="summary" name="summary" value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-12">
                                <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm khoá học" id="btnAddCourse"/>
                            </div>
                        </div>

                        <input type="hidden" value="${model.id}" id="id" name="id"/>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>


<script>

    $('#btnAddCourse').click(function (e) {
        e.preventDefault();

        var id = $('#id').val();
        var category_id = $('#category_id').val();
        var fullname = $('#fullName').val();
        var shortname = $('#shortName').val();
        var summary = $('#summary').val();

        // Tạo object dữ liệu
        var data = {
            id: id,
            category_id: category_id,
            fullname: fullname,
            shortname: shortname,
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
