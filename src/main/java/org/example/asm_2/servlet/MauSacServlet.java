package org.example.asm_2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.example.asm_2.model.MauSacEntity;
import org.example.asm_2.service.Imple.MauSacImple;
import org.example.asm_2.service.MauSacService;

import java.io.IOException;

@WebServlet({
        "/mau-sac",
        "/mau-sac/home",
        "/mau-sac/create",
        "/mau-sac/update",
        "/mau-sac/delete/*",
        "/mau-sac/detail/*",
})
public class MauSacServlet extends HttpServlet {
    public MauSacService mauSacService = new MauSacImple();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();
        if (URI.contains("detail")){
            String[] part = URI.split("/");
            int id = Integer.parseInt(part[part.length-1]);
            req.setAttribute("form",mauSacService.find(id));
        } else if (URI.contains("delete")) {
            String[] part = URI.split("/");
            int id =  Integer.parseInt(part[part.length-1]);
            mauSacService.remove(req,resp,id);
        }
        req.setAttribute("Item",mauSacService.findAll());
        req.getRequestDispatcher("/views/MauSacWeb.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            MauSacEntity mauSacEntity = new MauSacEntity();
            BeanUtils.populate(mauSacEntity,req.getParameterMap());
            String TrangThai = req.getParameter("trang_thai");
            mauSacEntity.setTrangThai(TrangThai);
            String URI = req.getRequestURI();
            if (URI.contains("create")){
                mauSacService.create(req,resp,mauSacEntity);
                resp.sendRedirect("/mau-sac/home");
            }else if (URI.contains("update")){
                mauSacService.update(req,resp,mauSacEntity);
                int idSize = Integer.parseInt(req.getParameter("id"));
                mauSacEntity.setId(idSize);
                resp.sendRedirect("/mau-sac/home");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
