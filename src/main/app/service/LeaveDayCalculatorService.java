package main.app.service;

import main.app.domain.Employee;
import main.app.domain.EmployeeType;
import main.app.domain.Leave;
import main.app.domain.LeaveType;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;


public class LeaveDayCalculatorService {
    public static Map<LeaveType, Integer> calculateLeaveDays(Leave leave, Employee employee) {
        Map<LeaveType, Integer> leaveDays = new HashMap<>();
        LocalDate today = LocalDate.now();
        int monthsWorked = (int) ChronoUnit.MONTHS.between(employee.getJoiningDate(), today);

        if (employee.getEmployeeType() == EmployeeType.NON_STAFF) {
            switch (leave.getLeaveType()) {
                case ANNUAL:
                    int annualDays = Math.max(15 - (monthsWorked / 12), 0);
                    leaveDays.put(LeaveType.ANNUAL, (annualDays < 0.5) ? (int) Math.floor(annualDays) : (int) Math.ceil(annualDays));
                    break;
                case SICK:
                    int sickDays = Math.max(10 - (monthsWorked / 12), 0);
                    leaveDays.put(LeaveType.SICK, (sickDays < 0.5) ? (int) Math.floor(sickDays) : (int) Math.ceil(sickDays));
                    break;
            }
        } else if (employee.getEmployeeType() == EmployeeType.STAFF) {
            switch (leave.getLeaveType()) {
                case ANNUAL:
                    int annualDays = Math.max(10 - (monthsWorked / 12), 0);
                    leaveDays.put(LeaveType.ANNUAL, (annualDays < 0.5) ? (int) Math.floor(annualDays) : (int) Math.ceil(annualDays));
                    break;
                case SICK:
                    int sickDays = Math.max(7 - (monthsWorked / 12), 0);
                    leaveDays.put(LeaveType.SICK, (sickDays < 0.5) ? (int) Math.floor(sickDays) : (int) Math.ceil(sickDays));
                    break;
            }
        }
        return leaveDays;
    }
}




