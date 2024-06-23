package org.example.asm_2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.example.asm_2.model.DanhMucEntity;
import org.example.asm_2.model.SanPhamEntity;
import org.example.asm_2.service.DanhMucService;
import org.example.asm_2.service.Imple.DanhMucImple;
import org.example.asm_2.service.Imple.SanPhamImple;
import org.example.asm_2.service.SanPhamService;

import java.io.IOException;

@WebServlet({
        "/san_pham",
        "/san_pham/home",
        "/san_pham/create",
        "/san_pham/update",
        "/san_pham/delete/*",
        "/san_pham/detail/*",
})
public class SanPhamServlet extends HttpServlet {
    public SanPhamService sanPhamService =new SanPhamImple();
    public DanhMucService danhMucService = new DanhMucImple();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();
        if (URI.contains("detail")){
            String[] part = URI.split("/");
            int id = Integer.parseInt(part[part.length-1]);
            req.setAttribute("form",sanPhamService.find(id));
        } else if (URI.contains("delete")) {
            String[] part = URI.split("/");
            int id =  Integer.parseInt(part[part.length-1]);
            sanPhamService.remove(req,resp,id);
        }
        req.setAttribute("DanhMuc",danhMucService.findAll());
        req.setAttribute("Item",sanPhamService.findAll());
        req.getRequestDispatcher("/views/SanPhamWeb.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            SanPhamEntity sanPhamEntity = new SanPhamEntity();
            BeanUtils.populate(sanPhamEntity,req.getParameterMap());

            int idDanhMuc = Integer.parseInt(req.getParameter("danhMuc"));
            String TrangThai = req.getParameter("trang_thai");
            DanhMucEntity danhMucEntity = (DanhMucEntity) danhMucService.find(idDanhMuc);
            sanPhamEntity.setDanhMucEntity(danhMucEntity);
            sanPhamEntity.setTrangThai(TrangThai);
            String URI = req.getRequestURI();
            if (URI.contains("create")){
                sanPhamService.create(req,resp,sanPhamEntity);
                resp.sendRedirect("/san_pham/home");
            }else if (URI.contains("update")){
                sanPhamService.update(req,resp,sanPhamEntity);
                resp.sendRedirect("/san_pham/home");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
