<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
  <link rel="stylesheet" href="	https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<table class="table">
  <thead>
  <tr>
    <th scope="col">CRUD</th>
    <th scope="col">Link</th>
  </tr>
  </thead>
  <tbody>
  <tr>
    <th scope="row">Bán Hàng</th>
    <td><a class="btn btn-primary" href="ban-hang/home">Bán Hàng</a></td>
  </tr>
  <tr>
    <th scope="row">CTSP</th>
    <td><a class="btn btn-primary" href="ctsp/home">CTSP</a></td>
  </tr>
  <tr>
    <th scope="row">Danh mục</th>
    <td><a class="btn btn-primary" href="DanhMuc/home">Danh mục</a></td>
  </tr>
  <tr>
    <th scope="row">Khách hàng</th>
    <td><a class="btn btn-primary" href="khachHang/home">Khách hàng</a></td>
  </tr>
  <tr>
    <th scope="row">Màu sắc</th>
    <td><a class="btn btn-primary" href="mau-sac/home">Màu sắc</a></td>
  </tr>
  <tr>
    <th scope="row">Sản phẩm</th>
    <td><a class="btn btn-primary" href="san_pham/home">Sản phẩm</a></td>
  </tr>
  <tr>
    <th scope="row">Size</th>
    <td><a class="btn btn-primary" href="size/home">Size</a></td>
  </tr>
  </tbody>
</table>
</body>
</html>