import java.util.Scanner;
    public class LinkedListApp { public static void main(String[] args) { Scanner scanner = new Scanner(System.in); ListNode head = null;
    while (true) {
            System.out.println("\n===== Linked List Operations =====");
            System.out.println("1. Insert Node");
            System.out.println("2. Display List");
            System.out.println("3. Rotate Sub Linked List");
            System.out.println("4. Detect Loop");
            System.out.println("5. Exit");
            System.out.println("====================================");
            System.out.print("Enter your choice: ");
            
            int choice = getValidIntegerInput(scanner);
            switch (choice) {
                case 1:
                    System.out.print("Enter value: ");
                    int value = getValidIntegerInput(scanner);
                    head = insertNode(head, value);
                    break;
                case 2:
                    ListNode.displayList(head);
                    break;
                case 3:
                    System.out.print("Enter left position: ");
                    int left = getValidIntegerInput(scanner);
                    System.out.print("Enter right position: ");
                    int right = getValidIntegerInput(scanner);
                    System.out.print("Enter number of steps: ");
                    int steps = getValidIntegerInput(scanner);
                    ListNode.rotateSubLinkedList(head, left, right, steps);
                    break;
                case 4:
                    System.out.println(detectLoop(head) ? "Loop detected!" : "No loop detected.");
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public static int getValidIntegerInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter an integer: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static ListNode insertNode(ListNode head, int value) {
        if (head == null) {
            return new ListNode(value);
        }
        ListNode temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(new ListNode(value));
        return head;
    }

    public static boolean detectLoop(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

}


