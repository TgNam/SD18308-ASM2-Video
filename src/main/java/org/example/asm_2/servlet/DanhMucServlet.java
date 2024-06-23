package org.example.asm_2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.example.asm_2.model.DanhMucEntity;
import org.example.asm_2.model.SizeEntity;
import org.example.asm_2.service.DanhMucService;
import org.example.asm_2.service.Imple.DanhMucImple;

import java.io.IOException;

@WebServlet({
        "/DanhMuc",
        "/DanhMuc/home",
        "/DanhMuc/create",
        "/DanhMuc/update",
        "/DanhMuc/delete/*",
        "/DanhMuc/detail/*",
})
public class DanhMucServlet extends HttpServlet {
    public DanhMucService danhMucService = new DanhMucImple();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();
        if (URI.contains("detail")){
            String[] part = URI.split("/");
            int id = Integer.parseInt(part[part.length-1]);
            req.setAttribute("form",danhMucService.find(id));
        } else if (URI.contains("delete")) {
            String[] part = URI.split("/");
            int id =  Integer.parseInt(part[part.length-1]);
            danhMucService.remove(req,resp,id);
        }
        req.setAttribute("Item",danhMucService.findAll());
        req.getRequestDispatcher("/views/DanhMucWeb.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DanhMucEntity danhMucEntity = new DanhMucEntity();
            BeanUtils.populate(danhMucEntity,req.getParameterMap());
            String TrangThai = req.getParameter("trang_thai");
            danhMucEntity.setTrangThai(TrangThai);
            String URI = req.getRequestURI();
            if (URI.contains("create")){
                danhMucService.create(req,resp,danhMucEntity);
                resp.sendRedirect("/DanhMuc/home");
            }else if (URI.contains("update")){
                danhMucService.update(req,resp,danhMucEntity);
                int idDanhMuc = Integer.parseInt(req.getParameter("id"));
                danhMucEntity.setId(idDanhMuc);
                resp.sendRedirect("/DanhMuc/home");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
