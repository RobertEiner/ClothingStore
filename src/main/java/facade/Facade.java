package facade;

import BusinessLogic.Item;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Facade {

    // This class only has the skeleton of the methods used by the test.
    // You must fill in this class with your own code. You can (and should) create more classes
    // that implement the functionalities listed in the Facade and in the Test Cases.

    private ArrayList<Item> listOfItems;

    public Facade(){
        this.listOfItems = new ArrayList<>();

    }

    public String createItem(String itemID, String itemName, double unitPrice){
        if(itemID.isEmpty() || this.containsItem(itemID) || itemName.isEmpty() ||  unitPrice <= 0) {
            return "Invalid data for item.";
        } else {
            Item newItem = new Item(itemID, itemName, unitPrice);
            listOfItems.add(newItem);
            return  "Item " + itemID + " was registered successfully";
        }
    }


    public String printItem(String itemID) {
        return "";
    }

    public String removeItem(String itemID) {
        return "";
    }

    public boolean containsItem(String itemID) {
        boolean exists = false;
        for(Item item : listOfItems) {
            if(item.getItemID().equals(itemID)) {
                exists = true;
            }
        }
        return exists;
    }

    public double buyItem(String itemID, int amount) {
        double price = 0;
        if (this.containsItem(itemID)) {
            for (Item item : listOfItems) {
                if (item.getItemID().equals(itemID)) {
                    if (amount <= 4) {
                        price = item.getUnitPrice() * amount;
                    } else {
                        price = item.getUnitPrice() * 4 + ((amount - 4) * item.getUnitPrice() * 0.7);
                    }
                }
            } return price;
        } else {
            return -1;
        }
    }
    public String reviewItem(String itemID, String reviewComment, int reviewGrade) {
        return "";
    }

    public String reviewItem(String itemID, int reviewGrade) {
        return "";
    }

    public String getItemCommentsPrinted(String itemID) {
        return "";
    }

    public List<String> getItemComments(String itemID) {
        return null;
    }

    public double getItemMeanGrade(String itemID) {
        return -1.0;
    }

    public int getNumberOfReviews(String itemID) {
        return -1;
    }

    public String getPrintedItemReview(String itemID, int reviewNumber) {
        return "";
    }

    public String getPrintedReviews(String itemID) {
        return "";
    }

    public String printMostReviewedItems() {
        return "";
    }

    public List<String> getMostReviewedItems() {
        return null;
    }

    public List<String> getLeastReviewedItems() {
        return null;
    }

    public String printLeastReviewedItems() {
        return "";
    }

    public double getTotalProfit() {
        return -1.0;
    }

    public String printItemTransactions(String itemID) {
        return "";
    }

    public int getTotalUnitsSold() {
        return -1;
    }

    public int getTotalTransactions() {
        return -1;
    }

    public double getProfit(String itemID) {
        return -1.0;
    }

    public int getUnitsSolds(String itemID) {
        return -1;
    }

    public String printAllTransactions() {
        return "";
    }

    public String printWorseReviewedItems() {
        return "";
    }

    public String printBestReviewedItems() {
        return "";
    }

    public List<String> getWorseReviewedItems() {
        return null;
    }

    public List<String> getBestReviewedItems() {
        return null;
    }

    public String printAllReviews() {
        return "";
    }

    public String updateItemName(String itemID, String newName) {
        if(itemID.isEmpty() || newName.isEmpty()){
            return "Invalid data for item.";
        } else if(this.containsItem(itemID)) {
            for(Item item : listOfItems) {
                if(itemID.equals(item.getItemID())) {
                    item.setItemName(newName);
                }
            } return "Item " + itemID + " successfully had its name updated to: " + newName;
        } else {
            return "Item " + itemID + " was not registered yet";
        }
    }

    public String updateItemPrice(String itemID, double newPrice) {
        return "";
    }

    public String printAllItems() {
        return "";
    }

    public String printMostProfitableItems() {
        return "";
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary) throws Exception {
        return "";
    }

    public String printEmployee(String employeeID) throws Exception {
        return "";
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree) throws Exception {
        return "";
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, int gpa) throws Exception {
        return "";
    }

    public double getNetSalary(String employeeID) throws Exception {
        return -1.0;
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree, String dept) throws Exception {
        return "";
    }

    public String removeEmployee(String empID) throws Exception {
        return "";
    }

    public String printAllEmployees() throws Exception {
        return "";
    }

    public double getTotalNetSalary() throws Exception {
        return -1.0;
    }

    public String printSortedEmployees() throws Exception {
        return "";
    }

    public String updateEmployeeName(String empID, String newName) throws Exception {
        return "";
    }

    public String updateInternGPA(String empID, int newGPA) throws Exception {
        return "";
    }

    public String updateManagerDegree(String empID, String newDegree) throws Exception {
        return "";
    }

    public String updateDirectorDept(String empID, String newDepartment) throws Exception {
        return "";
    }

    public String updateGrossSalary(String empID, double newSalary) throws Exception {
        return "";
    }

    public Map<String, Integer> mapEachDegree() throws Exception {
        return null;
    }

    public String promoteToManager(String empID, String degree) throws Exception {
        return "";

    }

    public String promoteToDirector(String empID, String degree, String department) throws Exception {
        return "";
    }

    public String promoteToIntern(String empID, int gpa) throws Exception {
        return "";
    }
}
