package main.app.service;

import main.app.domain.Employee;
import main.app.domain.EmployeeType;
import main.app.domain.Leave;
import main.app.repository.LeaveRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LeaveService {
    private static LeaveRepository leaveRepository;

    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    public List<Leave> list() throws SQLException {
        return leaveRepository.list();
    }

    public static Leave get(Long id) throws SQLException {
        return leaveRepository.get(id);
    }

    public static boolean create(Leave leave) throws SQLException {
        return leaveRepository.create(leave);
    }

    public static boolean update(Leave leave) throws SQLException {
        return leaveRepository.update(leave);
    }

    public static boolean delete(Long id) throws SQLException {
        return leaveRepository.delete(id);
    }

    public static List<Leave> listByEmployeeId(Long employeeId) throws SQLException {
        return leaveRepository.listByEmployeeId(employeeId);
    }
}
