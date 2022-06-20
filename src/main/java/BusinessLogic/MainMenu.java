package BusinessLogic;

import Util.Input;
import facade.Facade;

import java.sql.SQLOutput;

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
                String itemID1 = input.readString("Enter item ID: ");
                String name = input.readString("Enter item name: ");
                double price = input.readDouble("Enter item price: ");
                System.out.println(facade.createItem(itemID1, name, price));
                break;
            case 2:
                String itemToRemove = input.readString("Enter item ID for the item you wish to remove:");
                System.out.println(facade.removeItem(itemToRemove));
                break;
            case 3:
                System.out.println(facade.printAllItems());
                break;
            case 4:
                String itemID2 = input.readString("Enter item ID for the item you wish to buy:");
                int amount = input.readInt("Enter the amount of items you wish to buy:");
                double priceForBoughtItems = facade.buyItem(itemID2, amount);
                if(priceForBoughtItems == -1) {
                    System.out.println(priceForBoughtItems);
                } else {
                    System.out.println("The total price for your purchased items is " + priceForBoughtItems);
                }
                break;
            case 5:
                String itemID3 = input.readString("Enter ID for item you wish to rename:");
                String newName = input.readString("Enter the new name for the item:");
                System.out.println(facade.updateItemName(itemID3, newName));
                break;
            case 6:
                String itemID4 = input.readString("Enter ID for item you wish to rename:");
                double newPrice = input.readDouble("Enter the new price for the item:");
                System.out.println(facade.updateItemPrice(itemID4, newPrice));
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

        switch (answer) {

            case 0:
                MainMenu();
                break;
            case 1:
                String itemID5 = input.readString("Enter the item ID for the item you wish to review:");
                int reviewGrade = input.readInt("Enter a grade between 1-5:");
                String reviewComment = input.readString("Enter a review comment if you wish:");
                facade.reviewItem(itemID5, reviewComment, reviewGrade);
                break;
            case 2:
                String itemID6 = input.readString("Enter the item ID to retrieve a specific review for this item:");
                int reviewindex = input.readInt("Enter the review's review number:");
                System.out.println(facade.getPrintedItemReview(itemID6, reviewindex));
                break;
            case 3:
                String itemID7 = input.readString("Enter the item ID to print all reviews for the item:");
                System.out.println(facade.getPrintedReviews(itemID7));
                break;
            case 4:
                String itemID8 = input.readString("Enter the item ID to get the mean grade for the item:");
                System.out.println(facade.getItemMeanGrade(itemID8));
                break;
            case 5:
                String itemID9 = input.readString("Enter the item ID to print all comments for the item:");
                System.out.println(facade.getItemCommentsPrinted(itemID9));
                break;
            case 6:
                System.out.println(facade.printAllReviews());
                break;
            case 7:
                System.out.println(facade.printMostReviewedItems());
                break;
            case 8:
                System.out.println(facade.printLeastReviewedItems());
                break;
            case 9:
                System.out.println(facade.printBestReviewedItems());
                break;
            case 10:
                System.out.println(facade.printWorseReviewedItems());
                break;

        }

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


        switch(answer) {
            case 0:
                MainMenu();
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;


        }



    }




}
