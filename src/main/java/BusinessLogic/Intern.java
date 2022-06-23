package BusinessLogic;

import Util.Truncate;

public class Intern extends Employee {

    private int Gpa;
    private final int ACADEMIC_AWARD = 1000;


    public Intern (String employeeID, String employeeName, double grossSalary, int Gpa) throws Exception {
        super(employeeID, employeeName, grossSalary);
        if(Gpa >= 0 && Gpa <= 10) {
            this.Gpa = Gpa;
        } else {
            throw new Exception(Gpa + " outside range. Must be between 0-10.");
        }
    }

    public int getGpa() {
        return this.Gpa;
    }

    public void setGpa(int Gpa) throws Exception {
        if(Gpa >= 0 && Gpa <= 10) {
            this.Gpa = Gpa;
        } else {
            throw new Exception(Gpa + " outside range. Must be between 0-10.");
        }
    }

    @Override
    public double calculateNetSalary() {
        double netSalary = 0.0;

        if (this.Gpa <= 5) {
            netSalary = 0.0;
        } else if(this.Gpa > 5 && this.Gpa < 8) {
            netSalary = super.getGrossSalary();
        } else if(this.Gpa >= 8) {
            netSalary = super.getGrossSalary() + ACADEMIC_AWARD;
        }
        return netSalary;
    }

    @Override
    public double getGrossSalary() {
        return this.calculateNetSalary();
    }
    @Override
    public String toString(){
        return super.getEmployeeName() + "'s gross salary is " +  String.format("%.2f", Truncate.truncateValue(this.calculateNetSalary(), 2)) + " SEK per month. GPA: " + this.Gpa;
    }




}
