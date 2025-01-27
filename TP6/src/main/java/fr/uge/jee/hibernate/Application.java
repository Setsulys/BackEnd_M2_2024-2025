package fr.uge.jee.hibernate;

import fr.uge.jee.hibernate.Employee.EmployeeRepository;
import fr.uge.jee.hibernate.Employee.*;
import jakarta.persistence.*;

import java.util.List;

public class Application {
    public static void main(String[] args) {
//        EntityManagerFactory emf = PersistenceUtils.getEntityManagerFactory();
//        EntityManager em = emf.createEntityManager();
//        var txn = em.getTransaction();
//        try{
//            txn.begin();
//            var harry = new Employee("Harry","Potter",1000);
//            em.persist(harry);
//            txn.commit();
//        }catch(Exception e){
//            txn.rollback();
//            throw e;
//        }finally {
//            em.close();
//        }
        EmployeeRepository er = new EmployeeRepository();

        var bob = er.create("Bob","Moran",500);
        var bobD = er.create("Bob","Dylan",600);
        var lisa = er.create("Lisa","Simpson",100);
        var marge = er.create("Marge", "Simpson", 1000);
        var homer = er.create("Homer", "Simpson", 450);
        er.update(homer,550);
        er.delete(lisa);
        System.out.println(er.getAll());
        var emp=er.getAll().stream().filter(f->f.getSalary()<500).map(Employee::getId);
        emp.forEach(er::updateSalary);

        System.out.println(er.getAllByFirstName("Bob"));
    }
}
