package fr.uge.jee.hibernate;

import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Optional;

public class EmployeeRepository {

    private final EntityManagerFactory entityManagerFactory = PersistenceUtils.getEntityManagerFactory();

    /**
     * Create an employee with the given information
     * @param firstName
     * @param lastName
     * @param salary
     * @return the id of the newly created employee
     */

    public long create(String firstName, String lastName, int salary) {
//        var em= entityManagerFactory.createEntityManager();
//        var tx = em.getTransaction();
//        try{
//            tx.begin();
//            var employee = new Employee(firstName,lastName,salary);
//            em.persist(employee);
//            tx.commit();
//        }catch(Exception e){
//            tx.rollback();
//            throw e;
//        }
//        finally {
//            em.close();
//            return 0L;
//        }
        PersistenceUtils.inTransaction(em->{
            var employee = new Employee(firstName,lastName,salary);
            em.persist(employee);
        });
        return 0L;
    }

    /**
     * Remove the employee with the given id from the DB
     * @param id
     * @return true if the removal was successful
     */

    public boolean delete(long id) {
        PersistenceUtils.inTransaction(em->{
            var employee = em.find(Employee.class,id);
            if(employee==null){
                throw new IllegalArgumentException();
            }
            em.remove(employee);
            return true;
        });
        return false;
    }

    /**
     * Update the salary of the employee with the given id
     * @param id
     * @param salary
     * @return true if the removal was successful
     */

    public boolean update(long id, int salary) {
        PersistenceUtils.inTransaction(em->{
            var employee = em.find(Employee.class,id);
            if(employee!=null){
                System.out.println(employee);
                employee.setSalary(salary);
            }
            return true;
        });
        return false;
    }

    /**
     * Retrieve the employee with the given id
     * @param id
     * @return the employee wrapped in an {@link Optional}
     */

    public Optional<Employee> get(long id) {
        return Optional.of(PersistenceUtils.inTransaction(em->{
            var employee = em.find(Employee.class,id);
            if(employee!=null){
                System.out.println(employee);
            }
            return employee;
        }));
    }

    /**
     * Return the list of the employee in the DB
     */

    public List<Employee> getAll() {

        return null;
    }

}