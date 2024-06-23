package org.example.asm_2.service.Imple;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.asm_2.model.DanhMucEntity;
import org.example.asm_2.repository.DanhMucRepository;
import org.example.asm_2.service.DanhMucService;

import java.io.IOException;
import java.util.List;

public class DanhMucImple implements DanhMucService {
    private DanhMucRepository danhMucRepository = new DanhMucRepository();
    @Override
    public List findAll() {
        try{
            List<DanhMucEntity> list = danhMucRepository.findAll(DanhMucEntity.class);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object entity) throws ServletException, IOException {
        try{
            boolean check = danhMucRepository.create(entity);
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
            boolean check = danhMucRepository.update(entity);
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
    public void remove(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int id) throws ServletException, IOException {
        try{
            boolean check = danhMucRepository.remove(DanhMucEntity.class,id);
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
            return danhMucRepository.find(DanhMucEntity.class,id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
