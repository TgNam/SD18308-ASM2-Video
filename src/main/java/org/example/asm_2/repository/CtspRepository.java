package org.example.asm_2.repository;

import jakarta.persistence.TypedQuery;
import org.example.asm_2.model.CtspEntity;

import java.util.List;

public class CtspRepository extends Repository {
    public static List<CtspEntity> findCTSP(){
        String jpql ="FROM CtspEntity c WHERE c.trang_thai = 'Active'";
        TypedQuery<CtspEntity> query = em.createQuery(jpql, CtspEntity.class);
        return query.getResultList();
    }

}
