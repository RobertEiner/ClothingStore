package BusinessLogic;

import Util.Truncate;

public class Manager extends Employee {

    private String degree;
    private final double MANAGER_TAX = 0.1;
    private final double BSC_BONUS = 0.10;
    private final double MSC_BONUS = 0.20;
    private final double PHD_BONUS = 0.35;

    public Manager(String employeeID, String employeeName, double grossSalary, String degree) throws Exception {
        super(employeeID, employeeName, grossSalary);

        if(degree.equals("BSc") || degree.equals("MSc") || degree.equals("PhD")) {
          this.degree = degree;
        } else {
          throw new Exception("Degree must be one of the options: BSc, MSc or PhD.");
        }
    }

    @Override
    public String toString() {
        return this.degree + " " + super.getEmployeeName() + "'s gross salary is " + String.format("%.2f", Truncate.truncateValue(this.getGrossSalary(), 2)) + " SEK per month.";
    }

    public String getDegree() {
        return this.degree;
    }

    public void setDegree(String newDegree) throws Exception {
        if(newDegree.equals("BSc") || newDegree.equals("MSc") || newDegree.equals("PhD")) {
            this.degree = newDegree;
        } else {
            throw new Exception("Degree must be one of the options: BSc, MSc or PhD.");
        }
    }

    public double getGrossSalary() {
        return super.getGrossSalary() + (super.getGrossSalary() * this.calculateDegreeBonus());
    }

    @Override
    public double calculateNetSalary() {
        return getGrossSalary() - (getGrossSalary() * MANAGER_TAX);
    }

    public double calculateDegreeBonus() {
        double degreeBonus = 0.0;
        if(this.degree.equals("BSc")) {
            degreeBonus = BSC_BONUS;
        } else if (this.degree.equals("MSc")) {
            degreeBonus = MSC_BONUS;
        } else if (this.degree.equals("PhD")) {
            degreeBonus = PHD_BONUS;
        }
        return degreeBonus;
    }







}
