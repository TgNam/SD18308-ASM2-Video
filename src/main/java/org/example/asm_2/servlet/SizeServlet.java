package org.example.asm_2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.example.asm_2.model.SizeEntity;
import org.example.asm_2.service.Imple.SizeImple;
import org.example.asm_2.service.SizeService;

import java.io.IOException;

@WebServlet({
        "/size",
        "/size/home",
        "/size/create",
        "/size/update",
        "/size/delete/*",
        "/size/detail/*",
})
public class SizeServlet extends HttpServlet {
    public SizeService sizeService = new SizeImple();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();
        if (URI.contains("detail")){
            String[] part = URI.split("/");
            int id = Integer.parseInt(part[part.length-1]);
            req.setAttribute("form",sizeService.find(id));
        } else if (URI.contains("delete")) {
            String[] part = URI.split("/");
            int id =  Integer.parseInt(part[part.length-1]);
            sizeService.remove(req,resp,id);
        }
        req.setAttribute("Item",sizeService.findAll());
        req.getRequestDispatcher("/views/SizeWeb.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            SizeEntity sizeEntity = new SizeEntity();
            BeanUtils.populate(sizeEntity,req.getParameterMap());
            String TrangThai = req.getParameter("trang_thai");
            sizeEntity.setTrangThai(TrangThai);
            String URI = req.getRequestURI();
            if (URI.contains("create")){
                sizeService.create(req,resp,sizeEntity);
                resp.sendRedirect("/size/home");
            }else if (URI.contains("update")){
                sizeService.update(req,resp,sizeEntity);
                int idSize = Integer.parseInt(req.getParameter("id"));
                sizeEntity.setId(idSize);
                resp.sendRedirect("/size/home");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
