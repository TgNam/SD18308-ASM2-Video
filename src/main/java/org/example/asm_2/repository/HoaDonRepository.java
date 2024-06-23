package org.example.asm_2.repository;

import jakarta.persistence.TypedQuery;
import org.example.asm_2.model.HoaDonEntity;

import java.util.List;

public class HoaDonRepository extends Repository {

    public static List<Object[]> findBill(){
        String jpql = "SELECT h.id, kh.hoTen, h.ngay_tao, SUM(hdct.tongTien),h.trang_thai " +
                "FROM HoaDonEntity h " +
                "LEFT JOIN h.hdctEntity hdct " +
                "JOIN h.khachHang kh " +
                "WHERE h.trang_thai = 'Pending' " +
                "GROUP BY h.id, kh.hoTen, h.ngay_tao,h.trang_thai";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        return query.getResultList();
    }

}
"SELECT f" +
                "FROM FavoritesEntity  f " +
                "JOIN f.videoEntity v " +
                "WHERE f.user_id = :idUser and f.likes = 1";