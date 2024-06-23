package org.example.asm_2.service.Imple;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.asm_2.model.SizeEntity;
import org.example.asm_2.repository.SizeRepository;
import org.example.asm_2.service.SizeService;

import java.io.IOException;
import java.util.List;

public class SizeImple  implements SizeService {
    private SizeRepository sizeRepository =new SizeRepository();
    @Override
    public List findAll() {
        try{
            List<SizeEntity> list = sizeRepository.findAll(SizeEntity.class);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object entity) throws ServletException, IOException {
        try{
            boolean check = sizeRepository.create(entity);
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
            boolean check = sizeRepository.update(entity);
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
            boolean check = sizeRepository.remove(SizeEntity.class,id);
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
            return sizeRepository.find(SizeEntity.class,id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}