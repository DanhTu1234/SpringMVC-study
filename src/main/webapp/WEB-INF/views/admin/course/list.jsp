<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<c:url var="courseAPI" value="/api/course"/>
<c:url var ="editCourseURL" value="/quan-tri/khoa-hoc/chinh-sua"/>
<c:url var ="courseURL" value="/quan-tri/khoa-hoc/danh-sach"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách khóa học</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<form action="<c:url value="quan-tri/khoa-hoc/danh-sach"/>" id="formSubmit" method="get">
    <div class="container mt-4">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Danh sách khóa học</h6>
            </div>

            <div class="card-body">
                <c:if test="${not empty messageResponse}">
                    <div class="alert alert-${alert}">
                            ${messageResponse}
                    </div>
                </c:if>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover text-center align-middle shadow-sm" style="background-color:#fff">
                        <thead class="thead-dark">
                        <tr>
                            <th>STT</th>
                            <th>Tên danh mục</th>
                            <th>Tên khóa học</th>
                            <th>Mô tả ngắn</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var = "item" items = "${model.listResult}" varStatus="status">
                            <tr>
                                <td>${status.index + 1 }</td>
                                <td>${item.category.name }</td>
                                <td>${item.fullName }</td>
                                <td>${item.summary}</td>
                                <td>
<%--                                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"--%>
<%--                                       title="Cập nhật khóa học" href="/demo-web/admin-new?type=edit&id=${item.id}">--%>
<%--                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>--%>
<%--                                    </a>--%>
                                    <c:url var="editCourseURL" value="/quan-tri/khoa-hoc/chinh-sua">
                                        <c:param name="id" value="${item.id}"/>
                                    </c:url>
                                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                       title="Cập nhật khóa học" href='${editCourseURL}'><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                    </a>

                                    <button type="button" class="btn btn-sm btn-danger btn-delete" data-id="${item.id}" data-toggle="tooltip" title="Xóa khóa học">
                                        <i class="fa fa-trash-o bigger-110 pink" aria-hidden="true"></i>
                                    </button>

                                </td>
                            </tr>
                        </c:forEach>


                        </tbody>
                    </table>

                    <!-- Phân trang -->
                    <%--                    <ul class="pagination justify-content-center" id="pagination"></ul>--%>
                    <%--                    <input type="hidden" value="" id="page" name="page"/>--%>
                    <%--                    <input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>--%>
                    <%--                    <input type="hidden" value="" id="sortName" name="sortName"/>--%>
                    <%--                    <input type="hidden" value="" id="sortBy" name="sortBy"/>--%>
                    <%--                    <input type="hidden" value="list" id="type" name="type"/>--%>
                </div>
            </div>
        </div>
    </div>
</form>

<script>
    $(document).ready(function () {
        $('.btn-delete').click(function () {
            var id = $(this).data('id');

            if (confirm('Bạn có chắc muốn xóa bài viết này?')) {
                $.ajax({
                    url: '${courseAPI}/'+ id, // API xóa
                    method: 'DELETE',
                    contentType: 'application/json',
                    data: JSON.stringify({ id: id }),
                    success: function (result) {
                        alert('Xóa thành công!');
                        location.reload(); // reload lại trang danh sách
                    },
                    error: function (xhr, status, error) {
                        alert('Xóa thất bại! ' + error);
                        console.error(xhr.responseText);
                    }
                });
            }
        });
    });
</script>

</body>
</html>
