package com.example.spring_boot.dao;

import com.example.spring_boot.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository                                   // @ для DAO объектов
public class EmployeeDAOImplementation implements EmployeeDAO{

    @Autowired
    private EntityManager entityManager;    // Связь с БД, бин создается автоматически

    @Override
    public List<Employee> getAllEmployees() {

        // Использование Hibernate
//        Session session = entityManager.unwrap(Session.class);
//        Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
//        List<Employee> allEmployees = query.getResultList();

        // Использование JPA
        Query query = entityManager.createQuery("FROM Employee", Employee.class);
        List<Employee> allEmployees = query.getResultList();

        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {

        // Использование Hibernate
//        Session session = entityManager.unwrap(Session.class);
//        session.saveOrUpdate(employee);                     // id == 0 INSERT, id != 0 UPDATE

        Employee emp = entityManager.merge(employee);
        employee.setId(emp.getId());
    }
    @Override
    public Employee getEmployee(int id) {

        // Использование Hibernate
//        Session session = entityManager.unwrap(Session.class);
//        Employee employee = session.get(Employee.class, id);

        // Использование JPA
        Employee employee = entityManager.find(Employee.class, id);

        return employee;
    }

    @Override
    public Employee deleteEmployee(int id) {

        // Использование Hibernate
//        Session session = entityManager.unwrap(Session.class);
//        Query<Employee> query = session.createQuery("DELETE FROM Employee WHERE id=:employeeId");
//        query.setParameter("employeeId", id);
//        query.executeUpdate();                          // UPDATE / DELETE

        // Использование JPA
        Query query = entityManager.createQuery("DELETE FROM Employee WHERE id=:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();

        return null;
    }
}
