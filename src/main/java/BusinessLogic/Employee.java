package BusinessLogic;

import Util.Truncate;


import java.util.Objects;

public class Employee implements Comparable<Employee> {

    private String employeeID;
    private String employeeName;
    private double grossSalary;
    private final double EMPLOYEE_TAX = 0.1;

    public Employee(String employeeID, String employeeName, double grossSalary) throws Exception {
        if(employeeID.isBlank()) {
            throw new Exception("ID cannot be blank.");
        }
        if(employeeName.isBlank()) {
            throw new Exception("Name cannot be blank.");
        }
        if(grossSalary <= 0.0) {
            throw new Exception("Salary must be greater than zero.");
        }
        this.employeeID = employeeID;
        this.employeeName = employeeName;

        grossSalary = Truncate.truncateValue(grossSalary, 2);
        this.grossSalary = grossSalary;
    }

    public void setGrossSalary(double grossSalary) throws Exception {
        if(grossSalary <= 0.0) {
            throw new Exception("Salary must be greater than zero.");
        } else {
            this.grossSalary = grossSalary;
        }
    }

    public void setEmployeeName(String newEmployeeName) throws Exception {
        if(newEmployeeName.isBlank()) {
            throw new Exception("Name cannot be blank.");
        } else {
            this.employeeName = newEmployeeName;
        }
    }

    public double getGrossSalary() {
        return this.grossSalary;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public String getEmployeeID() {
        return this.employeeID;
    }

    public double calculateNetSalary(){
        return this.grossSalary - (this.grossSalary * EMPLOYEE_TAX);
    }

    @Override
    public String toString() {
        return this.employeeName + "'s gross salary is " + String.format("%.2f", Truncate.truncateValue(this.grossSalary, 2)) + " SEK per month.";
    }

    @Override
    public boolean equals(Object anotherObject) {
        if(anotherObject == null) {
            return false;
        } else if (this == anotherObject) {
            return true;
        } else if (anotherObject instanceof Employee) {
            Employee employee = (Employee) anotherObject;
            if (this.employeeID.equals(employee.getEmployeeID())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.employeeID);
    }

    public double getRawSalary() {
        return this.grossSalary;
    }


    @Override
    public int compareTo(Employee employee) {
        if(this.getGrossSalary() > employee.getGrossSalary()) {
            return 1;
        } else if(this.getGrossSalary() == employee.getGrossSalary()) {
            return 0;
        } else {
            return -1;
        }
    }
}
