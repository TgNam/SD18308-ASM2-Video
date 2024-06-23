package org.example.asm_2;

import java.io.*;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.asm_2.model.SanPhamEntity;
import org.example.asm_2.service.Imple.SanPhamImple;
import org.example.asm_2.service.SanPhamService;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    public  static SanPhamService service = new SanPhamImple();
    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Tạo một đối tượng SanPhamEntity mới
        SanPhamEntity newSanPham = new SanPhamEntity();
        newSanPham.setMaSanPham("SP1212"); // Thiết lập mã sản phẩm
        newSanPham.setTenSanPham("Tên sản phẩm mới"); // Thiết lập tên sản phẩm
        newSanPham.setTrangThai("Active"); // Thiết lập trạng thái
        // Gọi phương thức create từ service
        newSanPham.setNgayTaoDate();
        service.create(request,  response, newSanPham);
        System.out.println(newSanPham.getId());
    }

    public void destroy() {
    }
}
