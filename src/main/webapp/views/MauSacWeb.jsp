<%--
  Created by IntelliJ IDEA.
  User: TgNam
  Date: 01/04/2024
  Time: 8:28 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<a  class="btn btn-primary" href="http://localhost:8080">Trang chủ</a>
<div class="container">
    <div class="mb-3">
        <c:url var="url" value="/mau-sac"></c:url>
        <form action="${url}/home" method="post">
            <div class="mb-3">
                <label class="form-label">ID</label>
                <input type="text" value="${form.id}" name="id" class="form-control" readonly>
            </div>
            <div class="mb-3">
                <label>Mã màu sắc</label>
                <input type="text" value="${form.maMau}" name="maMau" class="form-control">
            </div>
            <div class="mb-3">
                <label>Tên màu sắc</label>
                <input type="text" value="${form.tenMau}" name="tenMau" class="form-control">
            </div>
            <div class="mb-3">
                <label>Trạng thái</label>
                <input type="radio" value="Active" name="trang_thai" ${form.getTrangThai() eq "Active" ? "checked":""} class="form-check-label">Active
                <input type="radio" value="Inactive" name="trang_thai" ${form.getTrangThai() eq "Active" ? "":"checked"} class="form-check-label">Inactive
            </div>
            <div class="mb-3">
                <button class="btn btn-primary" formaction="${url}/create">Add</button>
                <button class="btn btn-success" formaction="${url}/update">Update</button>
                <a href="${url}/home" class="btn btn-info">Reset</a>
            </div>
        </form>
        <h3 class="text-center text-danger">${message}</h3>
    </div>
    <div class="mb-3">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Ma màu sắc</th>
                <th scope="col">Ten màu sắc</th>
                <th scope="col">TrangThai</th>
                <th scope="col">NgayTao</th>
                <th scope="col">NgaySua</th>
                <th scope="col">Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${Item}">
                <tr>
                    <td>${item.getId()}</td>
                    <td>${item.getMaMau()}</td>
                    <td>${item.getTenMau()}</td>
                    <td>${item.getTrangThai()}</td>
                    <td>${item.getNgayTao()}</td>
                    <td>${item.getNgaySua()}</td>
                    <td>
                        <a href="${url}/detail/${item.getId()}" class="btn btn-warning">Detail</a>
                        <a href="${url}/delete/${item.getId()}" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
