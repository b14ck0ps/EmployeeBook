package main.app.service;

import main.app.domain.Leave;
import main.app.repository.LeaveRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LeaveService {
    private LeaveRepository leaveRepository;

    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    public List<Leave> list() throws SQLException {
        return leaveRepository.list();
    }

    public Leave get(Long id) throws SQLException {
        return leaveRepository.get(id);
    }

    public boolean create(Leave leave) throws SQLException {
        return leaveRepository.create(leave);
    }

    public boolean update(Leave leave) throws SQLException {
        return leaveRepository.update(leave);
    }

    public boolean delete(Long id) throws SQLException {
        return leaveRepository.delete(id);
    }
}
