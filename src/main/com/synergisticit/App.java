package com.synergisticit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.synergisticit.dao.EmployeeDao;
import com.synergisticit.domain.Employee;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        EmployeeDao employeeDao = context.getBean("employeeDao", EmployeeDao.class);

        // CREATE
        Employee newEmployee = new Employee(1, "Alice", "Developer", 50000);
        employeeDao.addEmployee(newEmployee);

        // READ
        Employee retrieved = employeeDao.getEmployee(1);
        System.out.println("Retrieved: " + retrieved);

        // UPDATE
        retrieved.setDesignation("Senior Developer");

        employeeDao.updateEmployee(retrieved);
        System.out.println("After update: " + employeeDao.getEmployee(1));

        // REMOVE
        employeeDao.deleteEmployee(1);
        System.out.println("Employee after delete: " + employeeDao.getEmployee(1));

        context.close();
    }
}

