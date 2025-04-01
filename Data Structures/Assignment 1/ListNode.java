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

    public static ListNode rotateSubLinkedList(ListNode head, int leftPosition, int rightPosition, int numberOfSteps){  
        if (head == null || leftPosition >= rightPosition || rightPosition < 0) {  
            displayList(head);  
            return head;  
        }  
      
        int subListSize = rightPosition - leftPosition + 1;  
        numberOfSteps %= subListSize;  
        if(numberOfSteps <= 0){  
            displayList(head);  
            return head;  
        }  
      
        ListNode dummy = new ListNode(0);  
        dummy.setNext(head);  
      
        // Move temp to one node before the leftPosition  
        ListNode temp = dummy;  
        for(int i = 1; i < leftPosition; i++){  
            if(temp == null){  
                throw new AssertionError("Not enough elements.");  
            }  
            temp = temp.getNext();  
        }  
        ListNode subListPrev = temp;  
        ListNode subListHead = subListPrev.getNext();  
      
        // For clockwise rotation, the break point should be at index = subListSize - numberOfSteps  
        int breakIndex = subListSize - numberOfSteps;  
        ListNode subListBreakPoint = null;  
        
        temp = subListHead;  
        for(int i = 1; i <= subListSize; i++){  
            if(i == breakIndex){  
                subListBreakPoint = temp;  
            }
            if(i < subListSize){  
                temp = temp.getNext();  
            }  
        }  
        ListNode subListTail = temp;  
        ListNode subListTailNext = subListTail.getNext();  
      
        // Reconnect pointers to perform the rotation  
        subListPrev.setNext(subListBreakPoint.getNext());
        subListTail.setNext(subListHead);
        subListBreakPoint.setNext(subListTailNext);          
      
        // Update head and display the rotated list  
        head = dummy.getNext();  

        displayList(head);  
        return head;
    }
}
