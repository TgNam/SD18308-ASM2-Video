package org.example.asm_2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.asm_2.model.*;
import org.example.asm_2.service.*;
import org.example.asm_2.service.Imple.*;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet({
        "/ban-hang",
        "/ban-hang/home",
        "/ban-hang/detail/*",
        "/ban-hang/delete/*",
        "/ban-hang/addHdct/*",
        "/ban-hang/AddHD",
        "/ban-hang/Pay",
        "/ban-hang/search"
})
public class BanHangServlet extends HttpServlet {
    public CtspService ctspService = new CtspImple();
    public HoaDonService hoaDonService = new HoaDonImple();
    public HdctService hdctService = new HdctImple();
    public KhachHangService khachHangService = new KhachHangImple();
    public SanPhamService sanPhamService = new SanPhamImple();
    BigDecimal tongTien = BigDecimal.valueOf(0);
    HoaDonEntity hoaDon;
    boolean checkHD = false;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();
        if (URI.contains("detail")){
            String[] parts = URI.split("/");
            int id = Integer.parseInt(parts[parts.length-1]);
            hoaDon = (HoaDonEntity) hoaDonService.find(id);
            checkHD = true;
            this.hoaDonDetail(req, resp, id);
        }else if (URI.contains("delete")){
            String[] parts = URI.split("/");
            int id = Integer.parseInt(parts[parts.length-1]);
            hdctService.remove(req,resp,id);
            checkHD = false;
            resp.sendRedirect("/ban-hang/home");
        }else if(URI.contains("addHdct")){
            if (checkHD == true || hoaDon != null){
                String[] parts = URI.split("/");
                int idSPCT = Integer.parseInt(parts[parts.length - 1]);
                CtspEntity ctspEntity = (CtspEntity) ctspService.find(idSPCT);

                // Lấy số lượng sản phẩm từ request parameter
                String quantityParam = req.getParameter("quantity");
                int quantity = 0; // Mặc định số lượng là 0

                BigDecimal tongTienSP = BigDecimal.ZERO;

                if (quantityParam != null && !quantityParam.isEmpty()) {
                    quantity = Integer.parseInt(quantityParam);
                    if (quantity <= ctspEntity.getSoLuongTon()){
                        tongTienSP = ctspEntity.getGiaBan().multiply(BigDecimal.valueOf(quantity));
                        HdctEntity hdct = new HdctEntity();
                        hdct.setCtspEntity(ctspEntity);
                        hdct.setHoaDon(hoaDon);
                        hdct.setSoLuongMua(quantity);
                        hdct.setGiaBan(ctspEntity.getGiaBan());
                        hdct.setTongTien(tongTienSP);
                        hdct.setTrangThai("Pending");
                        hdctService.create(req,resp,hdct);
                        if(quantity == ctspEntity.getSoLuongTon()){
                            ctspEntity.setTrangThai("Inactive");
                        }
                        Integer soLuong =  ctspEntity.getSoLuongTon() - quantity;
                        ctspEntity.setSoLuongTon(soLuong);
                        ctspService.update(req,resp,ctspEntity);
                        checkHD = false;
                    }
                }
                resp.sendRedirect("/ban-hang/home");
            }else {
                req.setAttribute("message","Vui lòng trọn hóa đơn");
                this.showForm(req,resp);
            }
        }
        if (URI.contains("detail")||URI.contains("home")){
            this.showForm(req,resp);
        }
    }
    private void showForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("CTSP",ctspService.findCTSP());
        req.setAttribute("HoaDon",hoaDonService.findBill());
        req.getRequestDispatcher("/views/BanHang.jsp").forward(req,resp);
    }
    private void hoaDonDetail(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        tongTien = BigDecimal.valueOf(0);
        for (HdctEntity entity : hdctService.Hdct(id)) {
            tongTien = tongTien.add(entity.getTongTien());
        }
        request.setAttribute("check",true);
        request.setAttribute("tongTienHoaDon",tongTien);
        request.setAttribute("HDCT",hdctService.Hdct(id));
        request.setAttribute("form",hoaDon);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();
        if (URI.contains("search")){
            String sdt = req.getParameter("sdt");
            if (!sdt.isEmpty()){
                KhachHangEntity khachHang = khachHangService.khachHangSearch(sdt);
                checkHD = false;
                if (khachHang != null) {
                    req.setAttribute("numblePhone",khachHang.getSdt());
                    req.setAttribute("fullname",khachHang.getHoTen());
                    req.setAttribute("check",false);
                } else {
                    req.setAttribute("message","Không tìm thấy khách hàng");
                }
            }else {
                req.setAttribute("message","Không được để trống");
            }
        }else if (URI.contains("AddHD")){
            checkHD = false;
            String sdt = req.getParameter("sdt");
            if (!sdt.isEmpty()){
                KhachHangEntity khachHang = khachHangService.khachHangSearch(sdt);
                if (khachHang != null) {
                    req.setAttribute("check",false);
                    HoaDonEntity hoaDonEntity = new HoaDonEntity();
                    hoaDonEntity.setKhachHang(khachHang);
                    hoaDonEntity.setTrangThai("Pending");
                    hoaDonEntity.setDiaChi("FPT");
                    hoaDonEntity.setSoDienThoai(sdt);
                    hoaDonService.create(req,resp,hoaDonEntity);
                }
                else {
                    req.setAttribute("message","Không tìm thấy khách hàng");
                }
            }else {
                req.setAttribute("message","Bạn cần nhập thông tin của khách hàng");
            }
        } else if (URI.contains("Pay")) {
            if (checkHD == true || hoaDon != null){
                int ibBill = Integer.parseInt(req.getParameter("idBill"));
                HoaDonEntity hoaDonEntity = (HoaDonEntity) hoaDonService.find(ibBill);
                if (!hoaDonEntity.getHdctEntity().isEmpty()){
                    hoaDonEntity.setTrangThai("Completed");
                    hoaDonService.update(req,resp,hoaDonEntity);
                    for (HdctEntity hdct : hoaDonEntity.getHdctEntity()) {
                        hdct.setTrangThai("Completed");
                        hdctService.update(req,resp,hdct);
                    }
                    req.setAttribute("message","Đã thanh toán");
                }else {
                    req.setAttribute("message","Bạn chưa có sản phẩm nào");
                }
            }else {
                req.setAttribute("message","Vui lòng trọn hóa đơn");
            }
        }
        this.showForm(req,resp);
    }
}
