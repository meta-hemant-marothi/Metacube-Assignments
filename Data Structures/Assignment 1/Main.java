
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of values you want in the list: ");
        int sizeOfList = sc.nextInt();
        ListNode head = new ListNode(0);
        ListNode copy = head;
        for(int  i = 0; i < sizeOfList; i++){
            System.out.printf("Enter %d element: ", i + 1);
            int value = sc.nextInt();
            ListNode temp = new ListNode(value);
            copy.setNext(temp);
            copy = copy.getNext();
        }
        head = head.getNext();
        ListNode.rotateSubLinkedList(head, 2, 5, 2);
    }
}
