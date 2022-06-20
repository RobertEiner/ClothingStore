package BusinessLogic;

import Util.Truncate;

import java.util.ArrayList;

public class Item {

    private String itemID;
    private String itemName;
    private double unitPrice;
    private ArrayList<Review> listOfReviews;
    private String EOL = System.lineSeparator();

    public Item(String ID, String name, double price) {
        this.itemID = ID;
        this.itemName = name;
        this.listOfReviews = new ArrayList<>();
        price = Truncate.truncateValue(price, 2);
        this.unitPrice = price;
        this.unitPrice = Truncate.truncateValue(this.unitPrice, 2);
    }

    public String getItemID() {
        return this.itemID;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String newItemName) {
        this.itemName = newItemName;
    }

    public void setUnitPrice(double newUnitPrice) {
        if(newUnitPrice > 0) {
            newUnitPrice = Truncate.truncateValue(newUnitPrice, 2);
            this.unitPrice = newUnitPrice;
        }
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    @Override
    public String toString() {
        return this.itemID + ": " + this.itemName + ". " + String.format("%.2f", this.unitPrice) + " SEK";
    }

    public void addReviewToItem(Review newReview) {
        listOfReviews.add(newReview);
    }

    public String getItemReview(int reviewIndex) {
        return listOfReviews.get(reviewIndex - 1).toString();
    }

    public ArrayList<String> getComments() {
        ArrayList<String> comments = new ArrayList<>();
        for(Review review : listOfReviews) {
            if(review.getComment() != null && (!review.getComment().isBlank()))
                comments.add(review.getComment());
            }
        return comments;
    }

    public boolean hasReview() {
        if(this.listOfReviews.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public int getSizeOfReviewsList() {
        return listOfReviews.size();
    }

    public String goThroughReviews() {
        String message = "";
        for(Review review : listOfReviews) {
            message += review.toString() + EOL;
        } return message;
    }

    public double calculateMeanGrade() {
        double meanGrade = 0;
        double sum = 0;
        for(Review tempReview : listOfReviews) {
            sum += tempReview.getReviewGrade();
        }
        meanGrade = sum / listOfReviews.size();
        meanGrade = Truncate.truncateValue(meanGrade, 1);
        return meanGrade;
    }



}