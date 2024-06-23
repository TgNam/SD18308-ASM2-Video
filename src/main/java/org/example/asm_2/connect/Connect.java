package org.example.asm_2.connect;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Connect {
    public static EntityManagerFactory factory;
    public static EntityManager getEntityManager(){
        if (factory == null || !factory.isOpen()){
            factory = Persistence.createEntityManagerFactory("FPL");
        }
        return factory.createEntityManager();
    }
}
