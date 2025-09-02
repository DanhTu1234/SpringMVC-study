<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<c:url var="offeringAPI" value="/api/offering"/>

<body>

<form action="<c:url value="/quan-tri/lop-hoc-phan/danh-sach"/>" id="formSubmit" method="get">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Danh sách các khoa</h6>
                <a href="<c:url value='/quan-tri/lop-hoc-phan/them-moi' />" class="btn btn-primary btn-icon-split btn-sm">
                    <span class="icon text-white-50">
                        <i class="fas fa-plus"></i>
                    </span>
                    <span class="text">Thêm mới</span>
                </a>
            </div>

            <div class="card-body">

                    <input type="text" name="Name" placeholder="Tìm kiếm theo tên..." />
                    <button type="submit">Tìm kiếm</button>

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
                            <th>Mã lớp</th>
                            <th>Tên khóa học</th>
                            <th>Học kỳ</th>
                            <th>Giáo viên dạy</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var = "item" items = "${model.listResult}" varStatus="status">
                            <tr>
                                <td>${status.index + 1 }</td>
                                <td>${item.offeringCode }</td>
                                <th>${item.course_catalog.title}</th>
                                <th>${item.semester.name}</th>
                                <th>${item.user.fullName}</th>
                                <td>
                                    <a class="btn btn-sm btn-outline-primary" href="<c:url value='/quan-tri/lop-hoc-phan/chinh-sua'>
                                        <c:param name='id' value='${item.id}'/>
                                    </c:url>">Sửa</a>

                                    <button class="btn btn-sm btn-outline-danger btn-delete" data-id="${item.id}">Xóa</button>
                                </td>
                            </tr>
                        </c:forEach>


                        </tbody>
                    </table>

                </div>
            </div>
        </div>

</form>

<script>
    $(document).ready(function () {
        $('.btn-delete').click(function () {
            var id = $(this).data('id');

            if (confirm('Bạn có chắc muốn xóa lớp học phần này?')) {
                $.ajax({
                    url: '${offeringAPI}/'+ id, // API xóa
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

