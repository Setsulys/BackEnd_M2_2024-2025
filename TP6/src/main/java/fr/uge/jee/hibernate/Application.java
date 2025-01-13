package fr.uge.jee.hibernate;

import jakarta.persistence.*;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory emf = PersistenceUtils.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        var txn = em.getTransaction();
        try{
            txn.begin();
            var harry = new Employee("Harry","Potter",1000);
            em.persist(harry);
            txn.commit();
        }catch(Exception e){
            txn.rollback();
            throw e;
        }finally {
            em.close();
        }
    }
}
