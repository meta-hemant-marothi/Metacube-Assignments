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

    @Override
    public String toString(){
        return "" + this.value;
    }

    public static void displayList(ListNode head){
        System.out.println("======== Linked List ========");
        while(head != null){
            System.out.print(head.value + " -> ");
            head = head.getNext();
        }
        System.out.println("null");
    }

    public static void rotateSubLinkedList(ListNode head, int leftPosition, int rightPosition, int numberOfSteps) {
        if (head == null || leftPosition >= rightPosition) {
            return;
        }
    
        // Dummy node to handle cases where leftPosition == 1
        ListNode dummy = new ListNode(0);
        dummy.setNext(head);
        ListNode temp = dummy;
    
        // Step 1: Find the node before the sublist
        for (int i = 1; i < leftPosition; i++) {
            if (temp.getNext() == null) {
                System.out.println("Left position out of bounds.");
                return;
            }
            temp = temp.getNext();
        }
    
        ListNode subListPrev = temp;
        ListNode subListHead = subListPrev.getNext();
    
        if (subListHead == null) {
            System.out.println("Sublist head is null.");
            return;
        }
    
        // Step 2: Find the tail of the sublist
        ListNode subListTail = subListHead;
        int subListSize = rightPosition - leftPosition + 1;
        numberOfSteps %= subListSize; // Optimize step count
    
        if (numberOfSteps == 0) {
            displayList(dummy.getNext());
            return;
        }
    
        for (int i = 1; i < subListSize; i++) {
            if (subListTail.getNext() == null) {
                System.out.println("Right position out of bounds.");
                return;
            }
            subListTail = subListTail.getNext();
        }
        ListNode subListTailNext = subListTail.getNext();
    
        // Step 3: Find the new tail and new head after rotation
        ListNode newTail = subListHead;
        for (int i = 1; i < subListSize - numberOfSteps; i++) {
            newTail = newTail.getNext();
        }
        ListNode newHead = newTail.getNext();
    
        // Step 4: Reconnect the sublist
        subListPrev.setNext(newHead);
        subListTail.setNext(subListHead);
        newTail.setNext(subListTailNext);
    
        // Print the modified list
        displayList(dummy.getNext());
    }
    
    

}
