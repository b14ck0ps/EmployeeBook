package main.app.service;

import main.app.domain.Employee;
import main.app.domain.EmployeeType;
import main.app.domain.Leave;
import main.app.domain.LeaveType;

import java.time.LocalDate;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;


public class LeaveDayCalculatorService {
    public static Map<LeaveType, Integer> calculateLeaveDays(Employee employee) {
        Map<LeaveType, Integer> leaveDays = new HashMap<>();
        LocalDate joiningDate = employee.getJoiningDate();

        int daysInYear = Year.now().length();

        // Calculate the number of days worked in the current year
        int daysWorked = Math.max(1, daysInYear - joiningDate.getDayOfYear() + 1);

        int annualDays;
        int sickDays;

        if (employee.getEmployeeType() == EmployeeType.NON_STAFF) {
            annualDays = Math.max(15 * daysWorked / daysInYear, 1);
            sickDays = Math.max(10 * daysWorked / daysInYear, 1);
        } else { // Staff
            annualDays = Math.max(10 * daysWorked / daysInYear, 1);
            sickDays = Math.max(7 * daysWorked / daysInYear, 1);
        }

        leaveDays.put(LeaveType.ANNUAL, annualDays);
        leaveDays.put(LeaveType.SICK, sickDays);

        return leaveDays;
    }
}




