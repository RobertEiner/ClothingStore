package BusinessLogic;

import Util.Truncate;

import java.util.Objects;

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

    @Override
    public boolean equals(Object anotherObject) {
        if(anotherObject == null) {
            return true;
        } else if(this == anotherObject) {
            return true;
        } else if(anotherObject instanceof Transaction) {
            Transaction anotherTransaction = (Transaction) anotherObject;
            return (this.itemID.equals(anotherTransaction.getItemID())) &&
                    this.amount == anotherTransaction.getAmount() &&
                    this.totalPrice == anotherTransaction.getTotalPrice();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.itemID, this.amount, this.totalPrice);
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
