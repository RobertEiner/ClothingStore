package BusinessLogic;

public class Item {

    private String itemID;
    private String itemName;
    private double unitPrice;

    public Item(String ID, String name, double price) {
        this.itemID = ID;
        this.itemName = name;
        this.unitPrice = price;
    }

    public String getItemID() {
        return this.itemID;
    }

    public void setItemName(String newItemName) {
        this.itemName = newItemName;
    }

    public void setUnitPrice(double newUnitPrice) {
        this.unitPrice = newUnitPrice;
    }

}