package BusinessLogic;

import Util.Truncate;

public class Item {

    private String itemID;
    private String itemName;
    private double unitPrice;


    public Item(String ID, String name, double price) {
        this.itemID = ID;
        this.itemName = name;

        price = Truncate.truncateValue(price, 2);
        this.unitPrice = price;
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
        this.unitPrice = newUnitPrice;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    @Override
    public String toString() {
        return this.itemID + ": " + this.itemName + ". " + String.format("%.2f", this.unitPrice) + " SEK";
    }
}