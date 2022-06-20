package BusinessLogic;

import Util.Truncate;

public class Transaction {

    private String itemID;
    private int amount;
    private double totalPrice;

    public Transaction(String itemID, int amount, double totalPrice) {
        this.itemID = itemID;
        this.amount = amount;
        totalPrice = Truncate.truncateValue(totalPrice, 2);
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return this.itemID + ": " + this.amount + " item(s). " + String.format("%.2f", totalPrice) + " SEK";
    }

    public String getItemID() {
        return this.itemID;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public int getAmount() {
        return this.amount;
    }
}
