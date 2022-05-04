package BusinessLogic;

import java.util.ArrayList;

public class Item {

    private String ID;
    private String name;
    private double price;
    private static ArrayList<Item> listOfItems = new ArrayList<>();

    public Item(String ID, String name, double price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
    }

    public static void createItem(String ID, String name, double price) {

    }
}
