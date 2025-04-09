package Dictionary;
import java.util.Scanner;

public class DictionaryMenu {

    public static int getValidInteger(Scanner scanner, String prompt) {
        int validInput;
        while (true) {
            try {
                System.out.print(prompt);
                validInput = Integer.parseInt(scanner.nextLine());
                return validInput;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }
    }

    public static String getValidString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int getValidIntegerInRange(Scanner scanner, String prompt, int min, int max) {
        int validInput;
        while (true) {
            validInput = getValidInteger(scanner, prompt);
            if (validInput >= min && validInput <= max) {
                return validInput;
            }
            System.out.println("Invalid input! Please enter a number between " + min + " and " + max + ".");
        }
    }
    public static void main(String[] args) {
        DictionaryBST<Integer, String> bst = new DictionaryBST<>();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Dictionary Menu ---");
            System.out.println("1. Add key-value pair");
            System.out.println("2. Get value by key");
            System.out.println("3. Delete key-value pair");
            System.out.println("4. Display all key-value pairs in sorted order");
            System.out.println("5. Exit");

            // Use validation function for choice input
            int choice = getValidIntegerInRange(scanner, "Enter your choice: ", 1, 5);

            switch (choice) {
                case 1:
                    // Add key-value pair
                    int key = getValidInteger(scanner, "Enter key (integer): ");
                    String value = getValidString(scanner, "Enter value (string): ");
                    bst.add(key, value);
                    System.out.println("Added key-value pair: " + key + " -> " + value);
                    break;

                case 2:
                    // Get value by key
                    key = getValidInteger(scanner, "Enter key to search (integer): ");
                    try {
                        System.out.println("Value for key " + key + ": " + bst.get(key));
                    } catch (AssertionError e) {
                        System.out.println("Error: Key not found in the dictionary.");
                    }
                    break;

                case 3:
                    // Delete key-value pair
                    key = getValidInteger(scanner, "Enter key to delete (integer): ");
                    boolean deleted = bst.delete(key);
                    if (deleted) {
                        System.out.println("Deleted key " + key + " successfully.");
                    } else {
                        System.out.println("Error: Key not found in the dictionary.");
                    }
                    break;

                case 4:
                    System.out.println("Displaying all key-value pairs:");
                    bst.display();
                    break;

                case 5:
                    exit = true;
                    System.out.println("Exiting the menu. Goodbye!");
                    break;
            }
        }
        scanner.close();
    }
}