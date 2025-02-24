package fr.uge.jee.hibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.function.Consumer;
import java.util.function.Function;

public class PersistenceUtils {

    static final EntityManagerFactory ENTITY_MANAGER_FACTORY
            = Persistence.createEntityManagerFactory("main-persistence-unit");

    static EntityManagerFactory getEntityManagerFactory(){
        return ENTITY_MANAGER_FACTORY;
    }

    public static void inTransaction(Consumer<EntityManager> consumer){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            consumer.accept(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public static <T> T inTransaction(Function<EntityManager,T> action){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            var result = action.apply(em);
            tx.commit();
            return result;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}