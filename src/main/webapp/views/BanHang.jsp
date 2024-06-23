<%--
  Created by IntelliJ IDEA.
  User: nguyenvv
  Date: 12/03/2024
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<a  class="btn btn-primary" href="http://localhost:8080">Trang chủ</a>
<div class="row">
    <h3 class="text-center text-danger">${message}</h3>
    <div class="col-7">
        <h2>Danh sách hoá đơn</h2>
        <c:url var="url" value="/ban-hang"></c:url>
        <table class="table">
            <thead>
            <tr>
                <td>STT</td>
                <td>ID</td>
                <td>Ten khach hang</td>
                <td>Ngay tao</td>
                <td>Tong tien</td>
                <td>Trang Thai</td>
                <td>Chuc nang</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="hd" items="${HoaDon}" varStatus="i">
                <tr>
                    <td>${i.index +1}</td>
                    <td>${hd[0]}</td>
                    <td>${hd[1]}</td>
                    <td>${hd[2]}</td>
                    <td>${hd[3]}</td>
                    <td>${hd[4]}</td>
                    <td><a href="${url}/detail/${hd[0]}" class="btn btn-primary">Chi tiết</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <h2>Danh sách hoá đơn chi tiết</h2>
        <table class="table">
            <thead>
            <tr>
                <td>STT</td>
                <td>ID</td>
                <td>Ten san pham</td>
                <td>So luong</td>
                <td>Gia ban</td>
                <td>Tong tien</td>
                <td>Chuc nang</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="hdct" items="${HDCT}" varStatus="i">
                <tr>
                    <td>${i.index +1}</td>
                    <td>${hdct.getId()}</td>
                    <td>${hdct.getCtspEntity().getSanPham().getTenSanPham()}</td>
                    <td>${hdct.getSoLuongMua()}</td>
                    <td>${hdct.getGiaBan()}</td>
                    <td>${hdct.getTongTien()}</td>
                    <td><a href="${url}/delete/${hdct.getId()}" class="btn btn-primary">Xóa</a></td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <div class="col-5">
        <h2>Tạo hoá đơn</h2>

        <div class="row">
            <form action="${url}/home" method="post">

                <div>
                    <div>
                        <label class="mb-3 col-3">Số điện thoại</label>
                        <input type="text" class="col-7"   value="${check ? form.khachHang.sdt : numblePhone}"  name="sdt" >
                    </div>
                    <button  class="btn btn-primary" formaction="${url}/search">Search</button>
                </div>
            <div class="mb-3">
                <label class="col-3">Ten Khach hang</label>
                <input type="text" class="col-7" readonly  value="${check ? form.khachHang.hoTen : fullname}" >
            </div>
            <div class="mb-3">
                <label class="col-3">ID Hoa don</label>
                <input type="text" class="col-7"value="${form.id}" name="idBill" readonly>
            </div>
            <div class="mb-3">
                <label class="col-3">Tong tien</label>
                <input type="text" class="col-7" value="${tongTienHoaDon}" readonly>
            </div>
            <div>
                <button  class="btn btn-primary" formaction="${url}/AddHD">Tạo hoá đơn</button>
                <button  class="btn btn-primary"formaction="${url}/Pay">Thanh toán</button>
            </div>
            </form>
        </div>

    </div>
</div>
<div>
    <h2>Danh sách chi tiếtsản phẩm</h2>
    <table class="table">
        <thead>
        <tr>
            <td>STT</td>
            <td>ID CTSP</td>
            <td>Ten san pham</td>
            <td>Mau sac</td>
            <td>Size</td>
            <td>Gia ban</td>
            <td>So luong ton</td>
            <td>Trang Thai</td>
            <td>Chuc nang</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ctsp" items="${CTSP}" varStatus="i">
            <tr>
                <td>${i.index +1}</td>
                <td>${ctsp.getId()}</td>
                <td>${ctsp.getSanPham().getTenSanPham()}</td>
                <td>${ctsp.getMauSac().getTenMau()}</td>
                <td>${ctsp.getSize().getTenSize()}</td>
                <td>${ctsp.getGiaBan()}</td>
                <td>${ctsp.getSoLuongTon()}</td>
                <td>${ctsp.getTrangThai()}</td>
                <td>
                    <a href="#" class="btn btn-primary" onclick="addHdct(${ctsp.getId()},${ctsp.getSoLuongTon()})">Chọn mua</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
<script>
    function addHdct(ctspId,ctspSoLong) {
        var quantity = prompt("Nhập số lượng sản phẩm:");
        // Kiểm tra nếu quantity không null và không rỗng và là một số
        if (quantity !== null && quantity !== "" && !isNaN(quantity)) {
            if (quantity <= ctspSoLong){
                // Chuyển đến trang addHdct với tham số ctspId và quantity
                window.location.href = "${url}/addHdct/" + ctspId + "?quantity=" + quantity;
            }else {
                // Hiển thị thông báo nếu số lượng không hợp lệ
                alert("Không còn đủ hàng.");
            }
        } else {
            // Hiển thị thông báo nếu số lượng không hợp lệ
            alert("Số lượng không hợp lệ.");
        }
    }
</script>

</html>
