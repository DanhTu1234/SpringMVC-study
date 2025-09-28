<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>

<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<c:url value='/quan-tri/trang-chu'/>">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3"> Admin</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <a class="nav-link" href="<c:url value='/quan-tri/trang-chu'/>">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span>
        </a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Chức năng chính:
    </div>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseOne"
           aria-expanded="true" aria-controls="collapseTwo">
            <i class="fas fa-calendar-day"></i>
            <span>Quản Lý Tổ Chức</span>
        </a>
        <div id="collapseOne" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Các chức năng:</h6>
                <a class="collapse-item" href="<c:url value='/quan-tri/danh-muc/danh-sach'/>">Quản Lý Khoa</a>
                <a class="collapse-item" href="<c:url value='/quan-tri/khoa-hoc/danh-sach'/>">Quản Lý Khóa Học</a>
            </div>
        </div>
    </li>

<%--    <li class="nav-item">--%>
<%--        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"--%>
<%--           aria-expanded="true" aria-controls="collapseTwo">--%>
<%--            <i class="fas fa-calendar-day"></i>--%>
<%--            <span>Quản Lý Tổ Chức</span>--%>
<%--        </a>--%>
<%--        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">--%>
<%--            <div class="bg-white py-2 collapse-inner rounded">--%>
<%--                <h6 class="collapse-header">Các chức năng:</h6>--%>
<%--                <a class="collapse-item" href="<c:url value='/quan-tri/khoa/danh-sach'/>">Quản Lý Khoa</a>--%>
<%--                <a class="collapse-item" href="<c:url value='/quan-tri/lop-hoc-phan/danh-sach'/>">Quản Lý Ngành</a>--%>
<%--                <a class="collapse-item" href="#">Quản Lý Lớp Sinh Viên</a>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </li>--%>

    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
           aria-expanded="true" aria-controls="collapseTwo">
            <i class="fab fa-product-hunt"></i>
            <span>Quản Lý Học Vụ</span>
        </a>
        <div id="collapseThree" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Các chức năng:</h6>
                <a class="collapse-item" href="#">Danh Mục Môn Học</a>
                <a class="collapse-item" href="#">Quản Lý Học Kỳ</a>
                <a class="collapse-item" href="#">Mở Lớp Học Phần</a>
                <a class="collapse-item" href="#">Quản Lý Ghi Danh</a>
            </div>
        </div>
    </li>

    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseThree"
           aria-expanded="true" aria-controls="collapseTwo">
            <i class="fas fa-wallet"></i>
            <span>Quản Lý Người Dùng</span>
        </a>
        <div id="collapseFour" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Các chức năng:</h6>
                <a class="collapse-item" href="#">Danh Sách Người Dùng</a>
                <a class="collapse-item" href="#">Phân Quyền</a>
            </div>
        </div>
    </li>

</ul>
<!-- End of Sidebar -->
