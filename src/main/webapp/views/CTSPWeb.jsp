<%--
  Created by IntelliJ IDEA.
  User: TgNam
  Date: 01/04/2024
  Time: 8:29 CH
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
        <c:url var="url" value="/ctsp"></c:url>
        <form action="${url}/home" method="post">
            <div class="mb-3">
                <label class="form-label">ID</label>
                <input type="text" value="${form.id}" name="id" class="form-control" readonly>
            </div>
            <div class="mb-3">
                <label>San Pham</label>
                <select class="form-select" aria-label="Default select example" name="sanPham">
                    <c:forEach var="sanPham" items="${SanPham}">
                        <option value="${sanPham.id}" ${sanPham.id eq form.sanPham.id ? "selected":""}>
                                ${sanPham.tenSanPham}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label>San Pham</label>
                <select class="form-select" aria-label="Default select example" name="mauSac">
                    <c:forEach var="mauSac" items="${MauSac}">
                        <option value="${mauSac.id}" ${mauSac.id eq form.mauSac.id ? "selected":""}>
                                ${mauSac.tenMau}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label>San Pham</label>
                <select class="form-select" aria-label="Default select example" name="size">
                    <c:forEach var="size" items="${Size}">
                        <option value="${size.id}" ${size.id eq form.size.id ? "selected":""}>
                                ${size.tenSize}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label>Giá Bán</label>
                <input type="number" value="${form.giaBan}" name="giaBan" class="form-control">
            </div>
            <div class="mb-3">
                <label>Số lượng tồn</label>
                <input type="number" value="${form.soLuongTon}" name="soLuongTon" class="form-control">
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
                <th scope="col">Tên Sản phẩm</th>
                <th scope="col">Tên màu sắc</th>
                <th scope="col">Tên size</th>
                <th scope="col">Giá bán</th>
                <th scope="col">Số lượng tồn</th>
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
                    <td>${item.getSanPham().getTenSanPham()}</td>
                    <td>${item.getMauSac().getTenMau()}</td>
                    <td>${item.getSize().getTenSize()}</td>
                    <td>${item.getGiaBan()}</td>
                    <td>${item.getSoLuongTon()}</td>
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
