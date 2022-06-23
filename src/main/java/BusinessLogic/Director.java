package BusinessLogic;

import Util.Truncate;

public class Director extends Manager{

    private final double DEPARTMENT_BONUS = 5000.00;
    private String department;
    private final double LOW_TAX = 0.1;
    private final double MEDIUM_TAX = 0.2;
    private final double HIGH_TAX = 0.4;

    public Director(String employeeID, String employeeName, double grossSalary, String degree, String department) throws Exception {
        super(employeeID, employeeName, grossSalary, degree);
        if(department.equals("Business") || department.equals("Human Resources") || department.equals("Technical")) {
            this.department = department;
        } else {
            throw new Exception("Department must be one of the options: Business, Human Resources or Technical.");
        }
    }

    @Override
    public String toString() {
        return super.getDegree() + " " + super.getEmployeeName() + "'s gross salary is " + String.format("%.2f", Truncate.truncateValue(this.getGrossSalary(), 2)) + " SEK per month. Dept: " + department;
    }

    public void setDepartment(String newDepartment) throws Exception {
        if(newDepartment.equals("Business") || newDepartment.equals("Human Resources") || newDepartment.equals("Technical")) {
            this.department = newDepartment;
        } else {
            throw new Exception("Department must be one of the options: Business, Human Resources or Technical.");
        }
    }

    public double getGrossSalary() {
        return super.getGrossSalary() + DEPARTMENT_BONUS;
    }

    @Override
    public double calculateNetSalary() {
        double netSalary = 0.0;
        double grossSalary = this.getGrossSalary();
        double lowTaxedIncome = 30000;
        double highTaxedIncome;

        if(grossSalary < 30000) {
            netSalary = this.getGrossSalary() - (this.getGrossSalary() * LOW_TAX);
        } else if(grossSalary >= 30000 && grossSalary <= 50000) {
            netSalary = this.getGrossSalary() - (this.getGrossSalary() * MEDIUM_TAX);
        } else if(grossSalary > 50000) {
            highTaxedIncome = grossSalary - lowTaxedIncome;
            highTaxedIncome = highTaxedIncome - (highTaxedIncome * HIGH_TAX);
            lowTaxedIncome = lowTaxedIncome - (lowTaxedIncome * MEDIUM_TAX);

            netSalary = highTaxedIncome + lowTaxedIncome;
        }
        return netSalary;
    }

    
}
