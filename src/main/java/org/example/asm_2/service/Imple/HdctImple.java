package org.example.asm_2.service.Imple;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.asm_2.model.HdctEntity;
import org.example.asm_2.model.HoaDonEntity;
import org.example.asm_2.model.SanPhamEntity;
import org.example.asm_2.model.SizeEntity;
import org.example.asm_2.repository.HdctRepository;
import org.example.asm_2.service.HdctService;

import java.io.IOException;
import java.util.List;

public class HdctImple implements HdctService {
    HdctRepository hdctRepository = new HdctRepository();


    @Override
    public List findAll() {
        try{
            return hdctRepository.findAll(HdctEntity.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object entity) throws ServletException, IOException {
        try{
            boolean check = hdctRepository.create(entity);
            if (check){
                httpServletRequest.setAttribute("message", "Bạn đã thêm thành công!");
            }else {
                httpServletRequest.setAttribute("message", "Bạn đã thêm thất bại!");
            }
        }catch (Exception e){
            e.printStackTrace();
            httpServletRequest.setAttribute("message", "Bạn đã thêm thất bại!");
        }
    }

    @Override
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object entity) throws ServletException, IOException {
        try{
            boolean check = hdctRepository.update(entity);
            if (check){
                httpServletRequest.setAttribute("message", "Bạn đã sửa thành công!");
            }else {
                httpServletRequest.setAttribute("message", "Bạn đã sửa thất bại!");
            }
        }catch (Exception e){
            e.printStackTrace();
            httpServletRequest.setAttribute("message", "Bạn đã sửa thất bại!");
        }
    }

    @Override
    public void remove(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int id) throws ServletException, IOException {
        try{
            boolean check = hdctRepository.remove(HdctEntity.class,id);
            if (check){
                httpServletRequest.setAttribute("message", "Bạn đã xóa thành công!");
            }else {
                httpServletRequest.setAttribute("message", "Bạn đã xóa thất bại!");
            }
        }catch (Exception e){
            e.printStackTrace();
            httpServletRequest.setAttribute("message", "Bạn đã xóa thất bại!");
        }
    }

    @Override
    public Object find(int id) {

        try{
            return hdctRepository.find(HdctEntity.class,id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<HdctEntity> Hdct(int id) {
        try{
            return hdctRepository.Hdct(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
