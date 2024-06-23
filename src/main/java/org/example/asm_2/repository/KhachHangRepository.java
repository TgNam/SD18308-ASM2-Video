package org.example.asm_2.repository;


import jakarta.persistence.TypedQuery;
import org.example.asm_2.model.KhachHangEntity;

import java.util.List;

public class KhachHangRepository extends Repository{
    public KhachHangEntity khachHangSearch(String sdt){
        TypedQuery<KhachHangEntity> query = em.createQuery("from KhachHangEntity k where k.sdt = :sdt",KhachHangEntity.class);
        query.setParameter("sdt",sdt);
        return query.getSingleResult();
    }
}
