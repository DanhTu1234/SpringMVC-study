<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>

<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/admin/home">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3"> Admin</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <a class="nav-link" href="#">
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
            <span>Khóa học</span>
        </a>
        <div id="collapseOne" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Các chức năng:</h6>
                <a class="collapse-item" href="<c:url value='/quan-tri/khoa-hoc/danh-sach'/>">
                    Liệt kê
                </a>
                <a class="collapse-item" href='<c:url value='/quan-tri/khoa-hoc/them-moi'/>'>Thêm mới</a>
            </div>
        </div>
    </li>

    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
           aria-expanded="true" aria-controls="collapseTwo">
            <i class="fas fa-calendar-day"></i>
            <span>Danh mục sản phẩm</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Các chức năng:</h6>
                <a class="collapse-item" href="/admin/Category/Index">Liệt kê</a>
                <a class="collapse-item" href="/admin/Category/Add">Thêm mới</a>
            </div>
        </div>
    </li>

    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseThree"
           aria-expanded="true" aria-controls="collapseTwo">
            <i class="fab fa-product-hunt"></i>
            <span>Sản phẩm</span>
        </a>
        <div id="collapseThree" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Các chức năng:</h6>
                <a class="collapse-item" href="/admin/Product/Index">Liệt kê</a>
                <a class="collapse-item" href="/admin/Product/Add">Thêm mới</a>
            </div>
        </div>
    </li>

    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseFour"
           aria-expanded="true" aria-controls="collapseTwo">
            <i class="fas fa-wallet"></i>
            <span>Đơn hàng</span>
        </a>
        <div id="collapseFour" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Các chức năng:</h6>
                <a class="collapse-item" href="/admin/Order/Index">Liệt kê</a>
            </div>
        </div>
    </li>

    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseFive"
           aria-expanded="true" aria-controls="collapseTwo">
            <i class="fas fa-user"></i>
            <span>Danh mục Bài viết</span>
        </a>
        <div id="collapseFive" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Các chức năng:</h6>
                <a class="collapse-item" href="/admin/CategoryPost/Index">Liệt kê</a>
                <a class="collapse-item" href="/admin/CategoryPost/Add">Thêm mới</a>
            </div>
        </div>
    </li>

    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseSix"
           aria-expanded="true" aria-controls="collapseTwo">
            <i class="fas fa-user"></i>
            <span>Bài viết</span>
        </a>
        <div id="collapseSix" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Các chức năng:</h6>
                <a class="collapse-item" href="/admin/Post/Index">Liệt kê</a>
                <a class="collapse-item" href="/admin/Post/Add">Thêm mới</a>
            </div>
        </div>
    </li>

    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseSeven"
           aria-expanded="true" aria-controls="collapseTwo">
            <i class="fas fa-user"></i>
            <span>Tài khoản</span>
        </a>
        <div id="collapseSeven" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Các chức năng:</h6>
                <a class="collapse-item" href="/admin/Account/Index">Liệt kê</a>
            </div>
        </div>
    </li>


</ul>
<!-- End of Sidebar -->
