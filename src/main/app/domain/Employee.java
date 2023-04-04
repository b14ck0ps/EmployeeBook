package main.app.domain;

import java.time.LocalDate;

public class Employee {
    private Long employeeId;
    private String name;
    private EmployeeType employeeType;
    private LocalDate joiningDate;

    public Employee() {

    }

    public Employee(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Employee(Long employeeId, String name, EmployeeType employeeType, LocalDate joiningDate) {
        this.employeeId = employeeId;
        this.name = name;
        this.employeeType = employeeType;
        this.joiningDate = joiningDate;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }
}
