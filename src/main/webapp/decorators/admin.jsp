<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ</title>

    <!-- css -->

    <link href="<c:url value='/template/admin/vendor/fontawesome-free/css/all.min.css' />" rel="stylesheet"
          type="text/css" media="all"/>
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
    <link href="<c:url value='/template/admin/css/sb-admin-2.min.css' />" rel="stylesheet" type="text/css" media="all"/>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <%--    <script type="text/javascript" src="<c:url value='/template/page/jquery.twbsPagination.js' />"></script>--%>
    <script type="text/javascript" src="<c:url value='/template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js' />"></script>

    <%--    <script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js' />"></script>--%>

</head>
<body id="page-top">
<!-- Page Wrapper -->
<div id="wrapper">

    <!-- menu -->
    <%@ include file="/common/admin/menu.jsp" %>
    <!-- menu -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">
        <!-- Main Content -->
        <div id="content">

            <!-- header -->
            <%@ include file="/common/admin/header.jsp" %>
            <!-- header -->

            <div class="container-fluid">
                <dec:body/>
            </div>

        </div>
        <!-- End of Main Content -->

        <!-- footer -->
        <%@ include file="/common/admin/footer.jsp" %>
        <!-- footer -->

    </div>
    <!-- End Of Content Wrapper -->
</div>
<!-- End Of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="login.html">Logout</a>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="<c:url value='/template/admin/vendor/jquery-easing/jquery.easing.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/template/admin/js/sb-admin-2.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/template/admin/vendor/chart.js/Chart.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/template/admin/js/demo/chart-area-demo.js' />"></script>


</body>
</html>