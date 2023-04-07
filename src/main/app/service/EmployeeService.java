package main.app.service;

import main.app.domain.Employee;
import main.app.domain.Leave;
import main.app.domain.LeaveType;
import main.app.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    public boolean createEmployeeAndLeave(Employee employee) throws SQLException {
        employee.setJoiningDate(LocalDate.now());
//      employee.setJoiningDate(LocalDate.of(2023, 12, 12)); //test

        long emp_id = employeeRepository.create(employee);
        if (emp_id == -1) {
            return false;
        }
        employee.setEmployeeId(emp_id);
        // get the employee id from the database
        Leave annualLeave = createLeave(employee, LeaveType.ANNUAL);
        if (annualLeave != null) {
            LeaveService.create(annualLeave);
        } else return false;

        Leave sickLeave = createLeave(employee, LeaveType.SICK);
        if (sickLeave != null) {
            LeaveService.create(sickLeave);
        } else return false;

        return true;
    }

    private Leave createLeave(Employee employee, LeaveType leaveType) {
        Leave leave = new Leave();
        leave.setEmployee(employee);
        leave.setLeaveType(leaveType);

        Map<LeaveType, Integer> leaveDaysMap = LeaveDayCalculatorService.calculateLeaveDays(employee);
        int leaveDays = leaveDaysMap.get(leaveType);
        if (leaveDays >= 0) {
            leave.setNumberOfDays(leaveDays);
            return leave;
        } else {
            return null;
        }
    }


    public boolean update(Employee employee) throws SQLException {
        return employeeRepository.update(employee);
    }

    public boolean delete(Long id) throws SQLException {
        return employeeRepository.delete(id);
    }
}

