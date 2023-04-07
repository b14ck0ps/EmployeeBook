package main.app.repository;

import main.app.domain.Employee;
import main.app.domain.EmployeeType;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private DataSource dataSource;
    private static final String ALL = "SELECT employee_id, name, employee_type, joining_date FROM employees";
    private static final String SELECT_ONE = "SELECT employee_id, name, employee_type, joining_date FROM employees WHERE employee_id = ?";
    private static final String CREATE = "INSERT INTO employees (name, employee_type, joining_date) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE employees SET name = ?, employee_type = ?, joining_date = ? WHERE employee_id = ?";
    private static final String DELETE = "DELETE FROM employees WHERE employee_id = ?";

    public EmployeeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Employee> list() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(ALL);
        return mapper(resultSet);
    }

    public Employee get(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Employee employee = new Employee();
        while (resultSet.next()) {
            employee.setEmployeeId(resultSet.getLong(1));
            employee.setName(resultSet.getString(2));
            employee.setEmployeeType(EmployeeType.valueOf(resultSet.getString(3)));
            employee.setJoiningDate(resultSet.getDate(4).toLocalDate());
        }
        return employee;
    }

    public long create(Employee employee) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getEmployeeType().name());
        preparedStatement.setDate(3, Date.valueOf(employee.getJoiningDate()));
        preparedStatement.executeUpdate();

        // Retrieve the generated keys from the statement
        ResultSet rs = preparedStatement.getGeneratedKeys();
        long id = -1;
        if (rs.next()) {
            id = rs.getLong(1);
        }

        rs.close();
        preparedStatement.close();
        connection.close();

        return id;
    }


    public boolean update(Employee employee) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getEmployeeType().name());
        preparedStatement.setDate(3, Date.valueOf(employee.getJoiningDate()));
        preparedStatement.setLong(4, employee.getEmployeeId());
        return preparedStatement.execute();
    }

    public boolean delete(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
        preparedStatement.setLong(1, id);
        return preparedStatement.execute();
    }

    private List<Employee> mapper(ResultSet resultSet) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        while (resultSet.next()) {
            Employee employee = new Employee();
            employee.setEmployeeId(resultSet.getLong(1));
            employee.setName(resultSet.getString(2));
            employee.setEmployeeType(EmployeeType.valueOf(resultSet.getString(3)));
            employee.setJoiningDate(resultSet.getDate(4).toLocalDate());
            employees.add(employee);
        }
        return employees;
    }
}

