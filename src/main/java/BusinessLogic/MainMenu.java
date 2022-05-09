package BusinessLogic;

import Util.Input;
import facade.Facade;

public class MainMenu {

    private String EOL = System.lineSeparator();
    private int answer = 0;
    private Facade facade = new Facade();
    private Input input = new Input();

    public void MainMenu() {

        do {
            System.out.println("Main menu: Please choose among the options below " + EOL +
                    "0. Close system. " + EOL +
                    "1. Open Item Options. " + EOL +
                    "2. Open Review options. " + EOL +
                    "3. Open Transaction History options. " + EOL
            );
            answer = input.readInt("Type an option number: ");

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

    public void itemOptions() {
        int answer = 0;
        System.out.println("Item options menu: " + EOL +
                "0. Return to Main Menu." + EOL +
                "1. Create an Item." + EOL +
                "2. Remove an Item." + EOL +
                "3. Print all registered Items." + EOL +
                "4. Buy an Item." + EOL +
                "5. Update an item's name." + EOL +
                "6. Update an item's price.");
        answer = input.readInt("Type an option number: ");

        switch(answer) {
            case 0:
                MainMenu();
                break;
            case 1:
                String ID = input.readString("Enter item ID: ");
                String name = input.readString("Enter item name: ");
                double price = input.readInt("Enter item price: ");
                System.out.println(facade.createItem(ID, name, price));
                break;
            case 5:
                String itemID = input.readString("Enter ID for item you wish to rename:");
                String newName = input.readString("Enter the new name for the item:");
                System.out.println(facade.updateItemName(itemID, newName));
        }
    }

    public void reviewsOptions() {
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
        answer = input.readInt("Type an option number: ");
    }

    public void transactionsHistoryOptions() {
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
        answer = input.readInt("Type an option number: ");
    }




}
