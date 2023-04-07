package main.app.repository;

import main.app.domain.Employee;
import main.app.domain.Leave;
import main.app.domain.LeaveType;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LeaveRepository {
    private DataSource dataSource;
    private static final String ALL = "select leave_id, employee_id, leave_type, number_of_days from `leave`";
    private static final String SELECT_ONE = "select leave_id, employee_id, leave_type, number_of_days from `leave` where leave_id = ?";
    private static final String CREATE = "insert into `leave` (employee_id, leave_type, number_of_days) values (?, ?, ?)";
    private static final String UPDATE = "update `leave` set employee_id = ?, leave_type = ?, number_of_days = ? where leave_id = ?";
    private static final String DELETE = "delete from `leave` where leave_id = ?";

    public LeaveRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Leave> list() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(ALL);
        return mapper(resultSet);
    }

    public Leave get(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Leave leave = new Leave();
        while (resultSet.next()) {
            leave.setLeaveId(resultSet.getLong(1));
            leave.setEmployee(new Employee(resultSet.getLong(2)));
            leave.setLeaveType(LeaveType.valueOf(resultSet.getString(3)));
            leave.setNumberOfDays(resultSet.getFloat(4));
        }
        return leave;
    }

    public boolean create(Leave leave) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE);
        preparedStatement.setLong(1, leave.getEmployee().getEmployeeId());
        preparedStatement.setString(2, String.valueOf(leave.getLeaveType()));
        preparedStatement.setFloat(3, (float) leave.getNumberOfDays());
        return preparedStatement.execute();
    }

    public boolean update(Leave leave) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setLong(1, leave.getEmployee().getEmployeeId());
        preparedStatement.setString(2, String.valueOf(leave.getLeaveType()));
        preparedStatement.setFloat(3, (float) leave.getNumberOfDays());
        preparedStatement.setLong(4, leave.getLeaveId());
        return preparedStatement.execute();
    }

    public boolean delete(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
        preparedStatement.setLong(1, id);
        return preparedStatement.execute();
    }

    private List<Leave> mapper(ResultSet resultSet) throws SQLException {
        List<Leave> leaves = new ArrayList<>();
        while (resultSet.next()) {
            Leave leave = new Leave();
            leave.setLeaveId(resultSet.getLong(1));
            leave.setEmployee(new Employee(resultSet.getLong(2)));
            leave.setLeaveType(LeaveType.valueOf(resultSet.getString(3)));
            leave.setNumberOfDays(resultSet.getFloat(4));
            leaves.add(leave);
        }
        return leaves;
    }
}
