package org.example.asm_2.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.asm_2.connect.Connect;

import java.util.List;

public class Repository {
    public static EntityManager em = Connect.getEntityManager();
    public static <T> List<T> findAll(Class<T> entityClass) {
        String jpql = "from " + entityClass.getSimpleName();
        TypedQuery<T> query = em.createQuery(jpql, entityClass);
        return query.getResultList();
    }

    public <T> T find(Class<T> entityClass, int id) {
        return em.find(entityClass, id);
    }

    public <T> boolean create(T entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public <T> boolean update(T entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
    }
    public <T> boolean remove(Class<T> entityClass, int id) {
        try {
            T entity = find(entityClass, id);
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
    }

}



