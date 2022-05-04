package Util;

import java.util.Scanner;

public class Input {

    private static Scanner scanner = new Scanner(System.in);

    public static int readInt (String message) {
        System.out.println(message);
        int answer = scanner.nextInt();
        scanner.nextLine();

        return answer;
    }

    public static String readString(String message) {
        System.out.println(message);
        String answer = scanner.nextLine();
        scanner.nextLine();

        return answer;
    }
}
