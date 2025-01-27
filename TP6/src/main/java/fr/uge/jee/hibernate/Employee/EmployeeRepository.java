package fr.uge.jee.hibernate.Employee;

import fr.uge.jee.hibernate.PersistenceUtils;

import java.util.List;
import java.util.Optional;

public class EmployeeRepository {

    //private final EntityManagerFactory entityManagerFactory = PersistenceUtils.getEntityManagerFactory();

    /**
     * Create an employee with the given information
     * @param firstName
     * @param lastName
     * @param salary
     * @return the id of the newly created employee
     */

    public long create(String firstName, String lastName, int salary) {
        return PersistenceUtils.inTransaction(em->{
            var employee = new Employee(firstName,lastName,salary);
            em.persist(employee);
            return employee.getId();
        });

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
        return PersistenceUtils.inTransaction(em->{
            var query=em.createQuery("SELECT e FROM Employee e", Employee.class);
            return query.getResultList();
        });
    }

    public boolean updateSalary(long id) {
        return PersistenceUtils.inTransaction(em -> {
            var employee = em.find(Employee.class, id);

            if (employee != null) {
                employee.setSalary((int)(employee.getSalary() * 1.1));
                if (employee.getSalary() < 1000) {
                    employee.setSalary(employee.getSalary() + 100);
                }
                return true;
            }
            return false;
        });
    }

    public List<Employee> getAllByFirstName(String firstName){
        return PersistenceUtils.inTransaction(em->{
            var query=em.createQuery("SELECT e FROM Employee e WHERE e.firstName = :firstName", Employee.class);
            query.setParameter("firstName", firstName);
            return query.getResultList();
        });
    }


}