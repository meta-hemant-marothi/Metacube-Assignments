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
        if (head == null || leftPosition == rightPosition) {
            displayList(head);
            return;
        }
    
        // Step 1: Create a dummy node for easier manipulation
        ListNode dummy = new ListNode(0);
        dummy.setNext(head);
    
        // Step 2: Find the previous node of the sublist and the sublist's head
        ListNode temp = dummy;
        for (int i = 1; i < leftPosition; i++) {
            if (temp == null) {
                throw new AssertionError("Not enough elements.");
            }
            temp = temp.getNext();
        }
    
        ListNode subListPrev = temp;
        ListNode subListHead = subListPrev.getNext();
    
        // Step 3: Traverse to the tail of the sublist
        ListNode subListTail = subListHead;
        int subListSize = rightPosition - leftPosition + 1;
        numberOfSteps %= subListSize; // Handle cases where steps > sublist size
        for (int i = 1; i < subListSize; i++) {
            subListTail = subListTail.getNext();
        }
        ListNode subListTailNext = subListTail.getNext();
    
        // Step 4: Find the new head and tail after rotation
        ListNode newTail = subListHead;
        for (int i = 1; i < subListSize - numberOfSteps; i++) {
            newTail = newTail.getNext();
        }
        ListNode newHead = newTail.getNext();
    
        // Step 5: Reconnect the sublist nodes
        subListPrev.setNext(newHead);
        newTail.setNext(subListTailNext);
        subListTail.setNext(subListHead);
    
        // Display the updated list
        displayList(dummy.getNext());
    }
}
