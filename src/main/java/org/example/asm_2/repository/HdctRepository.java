package org.example.asm_2.repository;


import jakarta.persistence.TypedQuery;
import org.example.asm_2.model.HdctEntity;

import java.util.List;

public class HdctRepository extends Repository {
    public List<HdctEntity> Hdct(int id){
        TypedQuery<HdctEntity> query = em.createQuery("select h from HdctEntity h where h.hoaDon.id = :id",HdctEntity.class);
        query.setParameter("id",id);
        return query.getResultList();
    }
}
