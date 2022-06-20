package facade;


import BusinessLogic.Item;
import BusinessLogic.Review;
import BusinessLogic.Transaction;
import Util.Truncate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Facade {

    // This class only has the skeleton of the methods used by the test.
    // You must fill in this class with your own code. You can (and should) create more classes
    // that implement the functionalities listed in the Facade and in the Test Cases.

    private ArrayList<Item> listOfItems;
    private ArrayList<Transaction> listOfTransactions;
    private String EOL = System.lineSeparator();

    public Facade(){
        this.listOfItems = new ArrayList<>();
        this.listOfTransactions = new ArrayList<>();

    }

    public String createItem(String itemID, String itemName, double unitPrice){
        if(itemID.isEmpty() || this.containsItem(itemID) || itemName.isEmpty() || unitPrice <= 0) {
            return "Invalid data for item.";
        } else {
            Item newItem = new Item(itemID, itemName, unitPrice);
            listOfItems.add(newItem);
            return  "Item " + itemID + " was registered successfully.";
        }
    }

    public String printItem(String itemID) {
        String message = "";
        if(this.containsItem(itemID)) {
            for (Item item : listOfItems) {
                if(item.getItemID().equals(itemID)) {
                    message = item.toString();
                }
            }
        } else {
            message = "Item " + itemID + " was not registered yet.";
        }
        return message;
    }

    public String removeItem(String itemID) {
        String message = "";
        if(this.containsItem(itemID)) {
            listOfItems.removeIf(item -> item.getItemID().equals(itemID));
            message = "Item " + itemID + " was successfully removed.";
        } else {
            message = "Item " + itemID + " could not be removed.";
        } return message;
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
            }
            price = Truncate.truncateValue(price, 2);
            Transaction newTransaction = new Transaction(itemID, amount, price);
            listOfTransactions.add(newTransaction);
            return price;
        } else {
            return -1;
        }
    }

    public String reviewItem(String itemID, String reviewComment, int reviewGrade) {
        String message = "";
        if(!this.containsItem(itemID)) {
            message = "Item " + itemID + " was not registered yet.";
        } else if(reviewGrade < 1 || reviewGrade >5) {
            message = "Grade values must be between 1 and 5.";
        } else {
            Review newReview = new Review(itemID, reviewGrade, reviewComment);
            for(Item item : listOfItems) {
                if(item.getItemID().equals(itemID)) {
                    item.addReviewToItem(newReview);
                }
            }
            message = "Your item review was registered successfully.";
        }
        return message;
    }

    public String reviewItem(String itemID, int reviewGrade) {
        String message = "";
        if(!this.containsItem(itemID)) {
            message = "Item " + itemID + " was not registered yet.";
        } else if(reviewGrade < 1 || reviewGrade >5) {
            message = "Grade values must be between 1 and 5.";
        } else {
            Review newReview = new Review(itemID, reviewGrade);
            for(Item item : listOfItems) {
                if(item.getItemID().equals(itemID)) {
                    item.addReviewToItem(newReview);
                }
            }
            message = "Your item review was registered successfully.";
        }
        return message;
    }

    public String getItemCommentsPrinted(String itemID) {
        String message = "";
        Item tempItem = getItem(itemID);
        List<String> listOfComments = getItemComments(itemID);

        for(String comment : listOfComments) {
            message += comment + EOL;
        } message = "Comments for " + itemID + ":" + EOL + message;

        return message;
    }

    public List<String> getItemComments(String itemID) {
        Item tempItem = getItem(itemID);
        return tempItem.getComments();
    }

    public double getItemMeanGrade(String itemID) {
       double meanGrade = 0;
       if(this.containsItem(itemID)) {
           Item tempitem = getItem(itemID);
           if(tempitem.hasReview()) {
              meanGrade = tempitem.calculateMeanGrade();
           } else {
               System.out.println("Item " + tempitem.getItemName() + " has not been reviewed yet.");
           }
       } else {
           System.out.println("Item " + itemID + " was not registered yet.");
       }
        return meanGrade;
    }

    public int getNumberOfReviews(String itemID) {
        Item tempItem = getItem(itemID);
        return tempItem.getSizeOfReviewsList();
    }

    public String getPrintedItemReview(String itemID, int reviewNumber) {
        String message = "";

        if (this.containsItem(itemID)) {
              Item tempItem = getItem(itemID);
              if(!tempItem.hasReview()) {
                  message = "Item " + tempItem.getItemName() + " has not been reviewed yet.";
              } else if(reviewNumber < 1 || reviewNumber > tempItem.getSizeOfReviewsList()) {
                  message = "Invalid review number. Choose between 1 and " + tempItem.getSizeOfReviewsList() + ".";
              } else {
                  message = tempItem.getItemReview(reviewNumber);
              }
        } else {
            message = "Item " + itemID + " was not registered yet.";
        }
        return message;
    }

    public String getPrintedReviews(String itemID) {
        String message = "";
        if(!this.containsItem(itemID)) {
            message = "Item " + itemID + " was not registered yet.";
        } else {
            Item tempItem = getItem(itemID);
            if (tempItem.hasReview()) {
                message = "Review(s) for " + tempItem + EOL + tempItem.goThroughReviews();
            } else {
                message = "Review(s) for " + tempItem + EOL + "The item " + tempItem.getItemName() + " has not been reviewed yet.";
            }
        }
        return message;
    }

    public String printMostReviewedItems() {
        String message = "";
        List<String> itemsWithMostReviews = this.getMostReviewedItems();
        int mostReviews = 0;
        if(listOfItems.isEmpty()) {
            message = "No items registered yet.";
        } else if(reviewDoesExist()) {
            for(String itemID : itemsWithMostReviews) {
                Item tempItem = getItem(itemID);
                message += tempItem.toString() + EOL;
                mostReviews = tempItem.getSizeOfReviewsList();
            } message = "Most reviews: " + mostReviews + " review(s) each." + EOL + message;
        } else {
            message = "No items were reviewed yet.";
        }
        return message;
    }

    public List<String> getMostReviewedItems() {
        ArrayList<String> mostReviewedItemsList = new ArrayList<>();
        int mostReviewedItem = 0;
        int currentItemReviewsSize;
        String message = "";

        for(Item tempItem : listOfItems) {
            if (tempItem.hasReview()) {
                currentItemReviewsSize = tempItem.getSizeOfReviewsList();
                if(currentItemReviewsSize > mostReviewedItem) {
                   message = tempItem.getItemID();

                   mostReviewedItemsList.clear();
                   mostReviewedItemsList.add(message);
                   mostReviewedItem = currentItemReviewsSize;
                } else if(currentItemReviewsSize == mostReviewedItem) {
                    message = tempItem.getItemID();
                    mostReviewedItemsList.add(message);
                }
            }
        }
        return mostReviewedItemsList;
    }

    public List<String> getLeastReviewedItems() {
       ArrayList<String> leastReviewedItemsList = new ArrayList<>();
       String message = "";
       int currentItemReviewsSize;
       int leastReviewedItem = 0;

       for(Item item : listOfItems) {
           if(item.hasReview()) {
               leastReviewedItem = item.getSizeOfReviewsList();
               break;
           }
       }
       for(Item tempItem : listOfItems) {
           if(tempItem.hasReview()) {
              currentItemReviewsSize = tempItem.getSizeOfReviewsList();
              if(currentItemReviewsSize < leastReviewedItem) {
                  message = tempItem.getItemID();

                  leastReviewedItemsList.clear();
                  leastReviewedItemsList.add(message);
                  leastReviewedItem = currentItemReviewsSize;
              } else if(currentItemReviewsSize == leastReviewedItem) {
                  message = tempItem.getItemID();
                  leastReviewedItemsList.add(message);
              }
           }
       }
        return leastReviewedItemsList;
    }

    public String printLeastReviewedItems() {
        String message = "";
        List<String> leastReviewedItems = getLeastReviewedItems();
        int leastReviews = 0;

        if(listOfItems.isEmpty()) {
            message = "No items registered yet.";
        } else if(reviewDoesExist()) {
            for(String itemID : leastReviewedItems) {
                Item tempItem = getItem(itemID);
                message += tempItem + EOL;
                leastReviews = tempItem.getSizeOfReviewsList();
            } message = "Least reviews: " + leastReviews + " review(s) each." + EOL + message;
        } else {
            message = "No items were reviewed yet.";
        }
        return message;
    }

    public double getTotalProfit() {
        double sumOfItemPurchases = 0;

        if(listOfTransactions.isEmpty()) {
            sumOfItemPurchases = 0;
        } else {
            for (Transaction tempTransaction : listOfTransactions) {
                sumOfItemPurchases += tempTransaction.getTotalPrice();
            }
        }
        return sumOfItemPurchases;
    }

    public String printItemTransactions(String itemID) {
        String transactionMessage = "";

       if(!this.containsItem(itemID)) {
           transactionMessage = "Item " + itemID + " was not registered yet.";
       } else if(transactionsForItemWereMade(itemID)) {
           Item tempItem = getItem(itemID);
           for(Transaction tempTransaction : listOfTransactions) {
               if(tempTransaction.getItemID().equals(itemID)) {
                   transactionMessage += tempTransaction + EOL;
               }
           } transactionMessage = "Transactions for item: " + tempItem + EOL + transactionMessage;
       } else {
           Item tempItemTwo = getItem(itemID);
           transactionMessage = "Transactions for item: " + tempItemTwo + EOL + "No transactions have been registered for item " + itemID + " yet.";
       }
       return transactionMessage;
    }

    public int getTotalUnitsSold() {
        int sumOfUnitsSold = 0;

        if(listOfTransactions.isEmpty()) {
            sumOfUnitsSold = 0;
        } else {
            for(Transaction tempTransaction : listOfTransactions) {
                sumOfUnitsSold += tempTransaction.getAmount();
            }
        }
        return sumOfUnitsSold;
    }

    public int getTotalTransactions() {
        int totalNumberOfTransactions = 0;
        if(listOfTransactions.isEmpty()) {
            totalNumberOfTransactions = 0;
        } else {
            totalNumberOfTransactions = listOfTransactions.size();
        }
        return totalNumberOfTransactions;
    }

    public double getProfit(String itemID) {
       double sumOfProfit = 0;

       if(!this.containsItem(itemID) || (!transactionsForItemWereMade(itemID))) {
          sumOfProfit = 0;
       } else {
          for(Transaction tempTransaction : listOfTransactions) {
              if(tempTransaction.getItemID().equals(itemID)) {
                  sumOfProfit += tempTransaction.getTotalPrice();
              }
          }
       } sumOfProfit = Truncate.truncateValue(sumOfProfit, 2);
       return sumOfProfit;
    }

    public int getUnitsSolds(String itemID) {
       int sumOfUnitsSold = 0;

       if (!this.containsItem(itemID) || (!transactionsForItemWereMade(itemID))) {
           sumOfUnitsSold = 0;
       } else {
           for(Transaction tempTransaction : listOfTransactions) {
               if(tempTransaction.getItemID().equals(itemID)) {
                   sumOfUnitsSold += tempTransaction.getAmount();
               }
           }
       }
       return sumOfUnitsSold;
    }

    public String printAllTransactions() {
        String transactionMessage = "";

        if(listOfTransactions.isEmpty()) {
            transactionMessage = "All purchases made: " + EOL
                    + "Total profit: 0.00 SEK" + EOL
                    + "Total items sold: 0 units" + EOL
                    + "Total purchases made: 0 transactions" + EOL
                    + "------------------------------------" + EOL
                    + "------------------------------------" + EOL;
        } else {
            for(Transaction tempTransaction : listOfTransactions) {
                transactionMessage += tempTransaction + EOL;
            } transactionMessage = "All purchases made: " + EOL
                    + "Total profit: " + String.format("%.2f", getTotalProfit()) + " SEK" + EOL
                    + "Total items sold: " + getTotalUnitsSold() + " units" + EOL
                    + "Total purchases made: " + getTotalTransactions() + " transactions" + EOL
                    + "------------------------------------" + EOL
                    + transactionMessage
                    + "------------------------------------" + EOL;
        }
        return transactionMessage;
    }

    public String printWorseReviewedItems() {
        List<String> listOfWorstReviewedItems = getWorseReviewedItems();
        String message = "";
        double lowestMeanGrade = 0;

        if(listOfItems.isEmpty()) {
            message = "No items registered yet.";
        } else if(reviewDoesExist()) {
            for(String itemID : listOfWorstReviewedItems) {
                Item tempItem = getItem(itemID);
                message += tempItem + EOL;
                lowestMeanGrade = tempItem.calculateMeanGrade();
            } message = "Items with worst mean reviews:" + EOL + "Grade: " + lowestMeanGrade + EOL + message;
        } else {
            message = "No items were reviewed yet.";
        }
        return message;
    }

    public String printBestReviewedItems() {
        List<String> listOfBestReviewedItems = getBestReviewedItems();
        String message= "";
        double meanGrade = 0;

        if(listOfItems.isEmpty()) {
            message = "No items registered yet.";
        } else if(reviewDoesExist()) {
            for (String itemID : listOfBestReviewedItems) {
                Item tempItem = getItem(itemID);
                message += tempItem + EOL;
                meanGrade = tempItem.calculateMeanGrade();
            }
            message = "Items with best mean reviews:" + EOL + "Grade: " + meanGrade + EOL + message;
        } else {
            message = "No items were reviewed yet.";
        }
        return message;
    }

    public List<String> getWorseReviewedItems() {
        ArrayList<String> listOfWorstReviewedItems = new ArrayList<>();
        double lowestMeanGrade = 0;
        double currentItemMeanGrade;
        String itemIDOfItem = "";

        for(Item item : listOfItems) {
            if(item.hasReview()){
                lowestMeanGrade = item.calculateMeanGrade();
                break;
            }
        }

        for(Item tempItem : listOfItems) {
           if(tempItem.hasReview()) {
               currentItemMeanGrade = tempItem.calculateMeanGrade();
               if (currentItemMeanGrade < lowestMeanGrade) {
                   lowestMeanGrade = currentItemMeanGrade;

                   itemIDOfItem = tempItem.getItemID();
                   listOfWorstReviewedItems.clear();
                   listOfWorstReviewedItems.add(itemIDOfItem);
               } else if (currentItemMeanGrade == lowestMeanGrade) {
                   itemIDOfItem = tempItem.getItemID();
                   listOfWorstReviewedItems.add(itemIDOfItem);
               }
           }
        }
        return listOfWorstReviewedItems;
    }

    public List<String> getBestReviewedItems() {
        ArrayList<String> listOfBestReviewedItems = new ArrayList<>();
        String itemIDOfItem = "";
        double highestMeanGrade = 0;
        double tempItemMeanGrade;

        for(Item tempItem : listOfItems) {
            if(tempItem.hasReview()) {
                tempItemMeanGrade = tempItem.calculateMeanGrade();
                if(tempItemMeanGrade > highestMeanGrade) {
                    highestMeanGrade = tempItemMeanGrade;

                    itemIDOfItem = tempItem.getItemID();
                    listOfBestReviewedItems.clear();
                    listOfBestReviewedItems.add(itemIDOfItem);
                } else if(tempItemMeanGrade == highestMeanGrade) {
                    itemIDOfItem = tempItem.getItemID();
                    listOfBestReviewedItems.add(itemIDOfItem);
                }

            }
        }
        return listOfBestReviewedItems;
    }

    public String printAllReviews() {
        String message = "";
        if(listOfItems.isEmpty()) {
            message = "No items registered yet.";
        } else if(this.reviewDoesExist()) {
            for(Item tempItem : listOfItems) {
                if(tempItem.hasReview()) {
                    message += "Review(s) for " + tempItem.getItemID()
                            + ": " + tempItem.getItemName() + ". "
                            + String.format("%.2f", tempItem.getUnitPrice()) + " SEK" + EOL
                            + tempItem.goThroughReviews()
                            + "------------------------------------" + EOL;
                }
            } message = "All registered reviews:" + EOL
                       + "------------------------------------" + EOL + message;
        } else {
            message = "No items were reviewed yet.";
        }
        return message;
    }

    public String updateItemName(String itemID, String newName) {
        String message = "";
        if(!this.containsItem(itemID)) {
            message = "Item " + itemID + " was not registered yet.";
        } else if(itemID.isEmpty() || newName.isEmpty()){
            message = "Invalid data for item.";
        } else if(this.containsItem(itemID)) {
            for(Item item : listOfItems) {
                if(itemID.equals(item.getItemID())) {
                    item.setItemName(newName);
                }
            } message = "Item " + itemID + " was updated successfully.";
        }
        return message;
    }

    public String updateItemPrice(String itemID, double newPrice) {
        String message = "";

        if(!this.containsItem(itemID)) {
            message = "Item " + itemID + " was not registered yet.";
        } else if(itemID.isEmpty() || newPrice <= 0) {
            message = "Invalid data for item.";
        }
        else {
            for(Item item : listOfItems) {
                if(item.getItemID().equals(itemID)) {
                    item.setUnitPrice(newPrice);
                    message = "Item " + itemID + " was updated successfully.";
                }
            }
        }
        return message;
    }

    public String printAllItems() {
        String message = "";
        if(listOfItems.isEmpty()) {
            message = "No items registered yet.";
        } else {
            for(Item item : listOfItems) {
                message += item.toString() + EOL;
            } message = "All registered items:" + EOL + message;
        }
        return message;
    }

    public String printMostProfitableItems() {
        String message = "";
        double mostProfitableItem = 0;
        double profitOfCurrentItem;

        if(listOfItems.isEmpty()) {
            message = "No items registered yet.";
        } else if(transactionsWereMade()) {
            for(Item tempItem : listOfItems) {
                String itemIDOfItem = tempItem.getItemID();
                profitOfCurrentItem = getProfit(itemIDOfItem);
                if(profitOfCurrentItem > mostProfitableItem) {
                   mostProfitableItem = profitOfCurrentItem;

                   message = getItem(itemIDOfItem).toString() + EOL;
                } else if(profitOfCurrentItem == mostProfitableItem) {
                   message += EOL + getItem(itemIDOfItem);
                }
            } message = "Most profitable items: " + EOL
                    + "Total profit: " + String.format("%.2f", mostProfitableItem) + " SEK" + EOL
                    + message;
        } else {
            message = "No items were bought yet.";
        }
        return message;
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


    public Item getItem(String itemID) {
        Item item = null;
        for(Item tempItem : listOfItems) {
            if(tempItem.getItemID().equals(itemID)) {
                item = tempItem;
            }
        } return item;
    }

    public boolean reviewDoesExist() {
        boolean reviewExists = false;
        for(Item item : listOfItems) {
            if(item.hasReview()){
                reviewExists = true;
                break;
            }
        }
        return reviewExists;
    }

    public boolean transactionsForItemWereMade(String itemID) {
        boolean transactionExists = false;
        for(Transaction tempTransaction : listOfTransactions ) {
            if(tempTransaction.getItemID().equals(itemID)) {
                transactionExists = true;
            }
        }
        return transactionExists;
    }

    public boolean transactionsWereMade() {
        boolean transactionsDoExist = false;
        if(!listOfTransactions.isEmpty()) {
            transactionsDoExist = true;
        }
        return transactionsDoExist;
    }

}
