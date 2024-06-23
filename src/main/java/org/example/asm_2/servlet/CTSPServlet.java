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
import java.text.DecimalFormat;

@WebServlet({
        "/ctsp",
        "/ctsp/home",
        "/ctsp/create",
        "/ctsp/update",
        "/ctsp/delete/*",
        "/ctsp/detail/*",
})
public class CTSPServlet extends HttpServlet {
    public CtspService ctspService = new CtspImple();
    public SanPhamService sanPhamService =new SanPhamImple();
    public MauSacService mauSacService = new MauSacImple();
    public SizeService sizeService = new SizeImple();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();
        if (URI.contains("detail")){
            String[] part = URI.split("/");
            int id = Integer.parseInt(part[part.length-1]);
            req.setAttribute("form",ctspService.find(id));
        } else if (URI.contains("delete")) {
            String[] part = URI.split("/");
            int id =  Integer.parseInt(part[part.length-1]);
            ctspService.remove(req,resp,id);
        }
        req.setAttribute("SanPham",sanPhamService.findAll());
        req.setAttribute("MauSac",mauSacService.findAll());
        req.setAttribute("Size",sizeService.findAll());
        req.setAttribute("Item",ctspService.findAll());
        req.getRequestDispatcher("/views/CTSPWeb.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CtspEntity ctspEntity = new CtspEntity();

            SanPhamEntity sanPhamEntity = (SanPhamEntity) sanPhamService.find(Integer.parseInt(req.getParameter("sanPham")));

            SizeEntity sizeEntity = (SizeEntity) sizeService.find(Integer.parseInt(req.getParameter("size")));

            MauSacEntity mauSacEntity = (MauSacEntity) mauSacService.find(Integer.parseInt(req.getParameter("mauSac")));

            ctspEntity.setSanPham(sanPhamEntity);

            ctspEntity.setMauSac(mauSacEntity);

            ctspEntity.setSize(sizeEntity);

            // Lấy giá trị chuỗi từ tham số "giaBan"
            String giaBanString = req.getParameter("giaBan");

            // Tạo một đối tượng DecimalFormat để chuyển đổi chuỗi thành số thực
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

            // Chuyển đổi chuỗi thành số thực
            Number giaBanNumber = decimalFormat.parse(giaBanString);

            // Chuyển đổi số thực thành đối tượng BigDecimal
            BigDecimal giaBanBigDecimal = BigDecimal.valueOf(giaBanNumber.doubleValue());

            // Thiết lập giá trị cho ctspEntity
            ctspEntity.setGiaBan(giaBanBigDecimal);

            ctspEntity.setSoLuongTon(Integer.parseInt(req.getParameter("soLuongTon")));

            ctspEntity.setTrangThai(req.getParameter("trang_thai"));

            String URI = req.getRequestURI();
            if (URI.contains("create")){
                ctspService.create(req,resp,ctspEntity);
                resp.sendRedirect("/ctsp/home");
            }else if (URI.contains("update")){
                ctspEntity.setId(Integer.parseInt(req.getParameter("id")));
                ctspService.update(req,resp,ctspEntity);
                resp.sendRedirect("/ctsp/home");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
