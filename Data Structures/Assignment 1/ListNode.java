public class ListNode {
    private int value;
    private ListNode next;

    public ListNode(int value) {
        this.value = value;
        this.next = null;
    }

    public ListNode(int val, ListNode next) { 
        this.value = val; 
        this.next = next; 
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public static void displayList(ListNode head){
        System.out.println("======== Linked List ========");
        while(head != null){
            System.out.print(head.value + " -> ");
            head = head.getNext();
        }
        System.out.println("null");
    }

    public static void rotateSubLinkedList(ListNode head, int leftPosition, int rightPosition, int numberOfSteps){
        if (head == null || leftPosition <= 0 || rightPosition <= 0 || leftPosition > rightPosition) {
            System.out.println("Invalid input positions!");
            return;
        }

        ListNode pointerToLeftPosition = head, pointerToRightPosition, pointerToRotatingPosition;
        int sizeOfSubList = rightPosition - leftPosition + 1;
        numberOfSteps %= sizeOfSubList;
        int iterator = 0;
        
        while(iterator < leftPosition - 2){
            pointerToLeftPosition = pointerToLeftPosition.getNext();
            iterator += 1;
        }
        pointerToRotatingPosition = pointerToLeftPosition;
        while (iterator < numberOfSteps + leftPosition - 1 && pointerToRotatingPosition != null) {
            pointerToRotatingPosition = pointerToRotatingPosition.getNext();
            iterator++;
        }
        pointerToRightPosition = pointerToRotatingPosition;
        while(iterator < rightPosition - 1){
            pointerToRightPosition = pointerToRightPosition.getNext();
            iterator += 1;
        }
        
        pointerToRightPosition.setNext(pointerToLeftPosition.getNext());
        pointerToLeftPosition.setNext(pointerToRotatingPosition.getNext());
        pointerToRotatingPosition.setNext(pointerToRightPosition.getNext());
        displayList(head);
    }

}
