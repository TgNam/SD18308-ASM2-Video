package org.example.asm_2.service.Imple;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.asm_2.model.HdctEntity;
import org.example.asm_2.model.HoaDonEntity;
import org.example.asm_2.repository.HoaDonRepository;
import org.example.asm_2.service.HoaDonService;

import java.io.IOException;
import java.util.List;

public class HoaDonImple implements HoaDonService {
    HoaDonRepository hoaDonRepository = new HoaDonRepository();

    @Override
    public List findAll() {
        try{
            return hoaDonRepository.findAll(HoaDonEntity.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object entity) throws ServletException, IOException {
        try{
            boolean check = hoaDonRepository.create(entity);
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
            boolean check = hoaDonRepository.update(entity);
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
            boolean check = hoaDonRepository.remove(HoaDonEntity.class,id);
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
            return hoaDonRepository.find(HoaDonEntity.class,id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object[]> findBill() {
        try{
            return hoaDonRepository.findBill();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
