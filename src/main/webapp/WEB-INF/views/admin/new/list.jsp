<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<%--<c:url var="APIurl" value="/api-admin-new"/>--%>
<%--<c:url var ="NewURL" value="/admin-new"/>--%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách baì viết</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<form action="#" id="formSubmit" method="get">
    <div class="container mt-4">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Danh sách sản phẩm</h6>
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
                            <th>Tên bài viết</th>
                            <th>Mô tả ngắn</th>
                            <th>Thao tac</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var = "item" items = "${model.listResult}" varStatus="status">
                            <tr>
                                <td>${status.index + 1 }</td>
                                <td>${item.title }</td>
                                <td>${item.shortDescription }</td>
                                <td>
                                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                       title="Cập nhật bài viêt" href="/demo-web/admin-new?type=edit&id=${item.id}">
                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                    </a>

                                    <button type="button" class="btn btn-sm btn-danger btn-delete" data-id="${item.id}" data-toggle="tooltip" title="Xóa bài viết">
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


<!-- Script khởi tạo phân trang -->
<script type="text/javascript">
    <%--var totalPages = ${model.totalPage}--%>
    <%--var currentPage = ${model.page}--%>
    <%--var limit=2--%>
    <%--$(function () {--%>
    <%--    window.pagObj = $('#pagination').twbsPagination({--%>
    <%--        totalPages: totalPages,--%>
    <%--        visiblePages: 10,--%>
    <%--        startPage: currentPage,--%>
    <%--        onPageClick: function (event, page) {--%>
    <%--            if(currentPage != page){--%>
    <%--                $('#maxPageItem').val(limit);--%>
    <%--                $('#page').val(page);--%>
    <%--                $('#sortName').val('title');--%>
    <%--                $('#sortBy').val('desc');--%>
    <%--                $('#formSubmit').submit();--%>
    <%--            }--%>

    <%--        }--%>

    <%--    });--%>
    <%--});--%>
</script>

<%--<script>--%>
<%--    $(document).ready(function () {--%>
<%--        $('.btn-delete').click(function () {--%>
<%--            var id = $(this).data('id');--%>

<%--            if (confirm('Bạn có chắc muốn xóa bài viết này?')) {--%>
<%--                $.ajax({--%>
<%--                    url: '${APIurl}', // API xóa--%>
<%--                    method: 'DELETE',--%>
<%--                    contentType: 'application/json',--%>
<%--                    data: JSON.stringify({ id: id }),--%>
<%--                    success: function (result) {--%>
<%--                        alert('Xóa thành công!');--%>
<%--                        location.reload(); // reload lại trang danh sách--%>
<%--                    },--%>
<%--                    error: function (xhr, status, error) {--%>
<%--                        alert('Xóa thất bại! ' + error);--%>
<%--                        console.error(xhr.responseText);--%>
<%--                    }--%>
<%--                });--%>
<%--            }--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>


</body>
</html>
