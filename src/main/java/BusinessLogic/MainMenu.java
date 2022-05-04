package BusinessLogic;

import Util.Input;

import java.util.*;

public class MainMenu {
    static  String EOL = System.lineSeparator();
    static int answer = 0;

    public static void MainMenu() {

        do {
            System.out.println("Main menu: Please choose among the options below " + EOL +
                    "0. Close system. " + EOL +
                    "1. Open Item Options. " + EOL +
                    "2. Open Review options. " + EOL +
                    "3. Open Transaction History options. " + EOL + EOL
            );
            answer = Input.readInt("Type an option number: ");

            switch (answer) {
                case 0:
                    break;
                case 1:
                    itemOptions();
                    break;
                case 2:
                    reviewsOptions();
                    break;
                case 3:
                    transactionsHistoryOptions();
                    break;
            }
        } while (answer != 0);
    }

    public static void itemOptions() {
        int answer = 0;
        System.out.println("Item options menu: " + EOL +
                "0. Return to Main Menu." + EOL +
                "1. Create an Item." + EOL +
                "2. Remove an Item." + EOL +
                "3. Print all registered Items." + EOL +
                "4. Buy an Item." + EOL +
                "5. Update an item's name." + EOL +
                "6. Update an item's price.");
        answer = Input.readInt("Type an option number: ");

        switch(answer) {
            case 0:
                MainMenu();
                break;
            case 1:
                String ID = Input.readString("Item ID: ");
                String name = Input.readString("Item name: ");
                double price = Input.readInt("Item price: ");
                Item.createItem(ID, name, price);
        }
    }

    public static void reviewsOptions() {
        int answer = 0;
        System.out.println("Reviews options menu: " + EOL + EOL +
                "0. Return to Main Menu." + EOL +
                "1. Create a review for an Item." + EOL +
                "2. Print a specific review of an Item." + EOL +
                "3. Print all reviews of an Item." + EOL +
                "4. Print mean grade of an Item" + EOL +
                "5. Print all comments of an Item." + EOL +
                "6. Print all registered reviews." + EOL +
                "7. Print item(s) with most reviews." + EOL +
                "8. Print item(s) with least reviews." + EOL +
                "9. Print item(s) with best mean review grade." + EOL +
                "10. Print item(s) with worst mean review grade." + EOL);
        answer = Input.readInt("Type an option number: ");
    }

    public static void transactionsHistoryOptions() {
        int answer = 0;
        System.out.println("Transaction History options menu:" + EOL +
                "0. Return to Main Menu." + EOL +
                "1. Print total profit from all item purchases" + EOL +
                "2. Print total units sold from all item purchases" + EOL +
                "3. Print the total number of item transactions made." + EOL +
                "4. Print all transactions made." + EOL +
                "5. Print the total profit of a specific item." + EOL +
                "6. Print the number of units sold of a specific item." + EOL +
                "7. Print all transactions of a specific item." + EOL +
                "8. Print item with highest profit." + EOL);
        answer = Input.readInt("Type an option number: ");
    }




}
