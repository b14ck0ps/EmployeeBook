package main.app.domain;

public class Leave extends Employee {
    private Long leaveId;
    private Employee employee;
    private LeaveType leaveType;
    private double numberOfDays;

    public Leave() {

    }

    public Leave(Long leaveId, Employee employee, LeaveType leaveType, double numberOfDays) {
        this.leaveId = leaveId;
        this.employee = employee;
        this.leaveType = leaveType;
        this.numberOfDays = numberOfDays;
    }

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public double getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(double numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
}
