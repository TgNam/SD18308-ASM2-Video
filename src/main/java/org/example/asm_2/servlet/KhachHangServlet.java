package org.example.asm_2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.example.asm_2.model.KhachHangEntity;
import org.example.asm_2.service.Imple.KhachHangImple;
import org.example.asm_2.service.KhachHangService;

import java.io.IOException;

@WebServlet({
        "/khachHang",
        "/khachHang/home",
        "/khachHang/create",
        "/khachHang/update",
        "/khachHang/delete/*",
        "/khachHang/detail/*",
})
public class KhachHangServlet extends HttpServlet {
    public KhachHangService khachHangService = new KhachHangImple();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();
        if (URI.contains("detail")){
            String[] part = URI.split("/");
            int id = Integer.parseInt(part[part.length-1]);
            req.setAttribute("form",khachHangService.find(id));
        } else if (URI.contains("delete")) {
            String[] part = URI.split("/");
            int id =  Integer.parseInt(part[part.length-1]);
            khachHangService.remove(req,resp,id);
        }
        req.setAttribute("Item",khachHangService.findAll());
        req.getRequestDispatcher("/views/KhachHangWeb.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            KhachHangEntity khachHangEntity = new KhachHangEntity();
            BeanUtils.populate(khachHangEntity,req.getParameterMap());
            String TrangThai = req.getParameter("trang_thai");
            khachHangEntity.setTrangThai(TrangThai);
            String URI = req.getRequestURI();
            if (URI.contains("create")){
                khachHangService.create(req,resp,khachHangEntity);
                resp.sendRedirect("/khachHang/home");
            }else if (URI.contains("update")){
                khachHangService.update(req,resp,khachHangEntity);
                int idKhachHang = Integer.parseInt(req.getParameter("id"));
                khachHangEntity.setId(idKhachHang);
                resp.sendRedirect("/khachHang/home");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
