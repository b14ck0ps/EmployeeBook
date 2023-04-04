package main.app.service;

import main.app.domain.Employee;
import main.app.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> list() throws SQLException {
        return employeeRepository.list();
    }

    public Employee get(Long id) throws SQLException {
        return employeeRepository.get(id);
    }

    public boolean create(Employee employee) throws SQLException {
        return employeeRepository.create(employee);
    }

    public boolean update(Employee employee) throws SQLException {
        return employeeRepository.update(employee);
    }

    public boolean delete(Long id) throws SQLException {
        return employeeRepository.delete(id);
    }
}

