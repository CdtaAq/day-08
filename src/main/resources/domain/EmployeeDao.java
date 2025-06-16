package com.synergisticit.dao;

import java.util.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.RowMapper;

import com.synergisticit.domain.Employee;

public class EmployeeDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // CREATE
    public int addEmployee(Employee employee) {
        String sql = "INSERT INTO employee (empId, name, designation, salary) VALUES (:empId, :name, :designation, :salary)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("empId", employee.getEmpId()); 
        paramMap.put("name", employee.getName()); 
        paramMap.put("designation", employee.getDesignation()); 
        paramMap.put("salary", employee.getSalary()); 
        
        return jdbcTemplate.update(sql, paramMap);
    }

    // READ
    public Employee getEmployee(int empId) {
        String sql = "SELECT * FROM employee WHERE empId = :empId";

        SqlParamSource paramSource = new MapSqlParamSource("empId", empId);
        return jdbcTemplate.queryForObject(sql, paramSource, new RowMapper<Employee>() {
            @Override
            public Employee mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                Employee employee = new Employee();
                employee.setEmpId(rs.getInt("empId"));
                employee.setName(rs.getString("name"));
                employee.setDesignation(rs.getString("designation"));
                employee.setSalary(rs.getInt("salary"));
                return employee;
            }
        });
    }

    // UPDATE
    public int updateEmployee(Employee employee) {
        String sql = "UPDATE employee SET name = :name, designation = :designation, salary = :salary WHERE empId = :empId";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("empId", employee.getEmpId()); 
        paramMap.put("name", employee.getName()); 
        paramMap.put("designation", employee.getDesignation()); 
        paramMap.put("salary", employee.getSalary()); 
        
        return jdbcTemplate.update(sql, paramMap);
    }

    // REMOVE
    public int deleteEmployee(int empId) {
        String sql = "DELETE FROM employee WHERE empId = :empId";

        SqlParamSource paramSource = new MapSqlParamSource("empId", empId);
        return jdbcTemplate.update(sql, paramSource);
    }

    // List All
    public List<Employee> listAllEmployees(){
        String sql = "SELECT * FROM employee";

        return jdbcTemplate.query(sql, new RowMapper<Employee>() {
            @Override
            public Employee mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                Employee employee = new Employee();
                employee.setEmpId(rs.getInt("empId"));
                employee.setName(rs.getString("name"));
                employee.setDesignation(rs.getString("designation"));
                employee.setSalary(rs.getInt("salary"));
                return employee;
            }
        });
    }
}

