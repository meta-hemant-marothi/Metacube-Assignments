
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "" + this.value;
    }

    public static void displayList(ListNode head) {
        System.out.println("======== Linked List ========");
        while (head != null) {
            System.out.print(head.value + " -> ");
            head = head.getNext();
        }
        System.out.println("null");
    }

    public static ListNode rotateSubLinkedList(ListNode head, int leftPosition, int rightPosition, int numberOfSteps) {
        if (head == null || leftPosition == rightPosition) {
            return head;
        }

        int subListSize = rightPosition - leftPosition + 1;
        numberOfSteps %= subListSize;

        if (numberOfSteps == 0) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.setNext(head);

        ListNode temp = dummy;

        for (int i = 1; i < leftPosition; i++) {
            if (temp == null) {
                throw new AssertionError("Not enough elements.");
            }
            temp = temp.getNext();
        }

        ListNode subListPrev = temp;
        ListNode subListHead = subListPrev.getNext();
        ListNode subListBreakPoint = null;
        ListNode subListBreakPointNext = null;
        ListNode subListTail;
        ListNode subListTailNext;

        temp = subListHead;
        for (int i = 1; i < subListSize; i++) {
            if (temp == null) {
                throw new AssertionError("Not enough elements.");
            }
            if (i == subListSize - numberOfSteps) {
                subListBreakPoint = temp;
                if (numberOfSteps == 1 || numberOfSteps == subListSize - 1) {
                    subListBreakPointNext = subListBreakPoint.getNext();
                }
            }
            if (i == subListSize - numberOfSteps + 1) {
                subListBreakPointNext = subListBreakPoint.getNext();
            }
            temp = temp.getNext();
        }
        if (temp == null) {
            throw new AssertionError("Not enough elements.");
        }
        subListTail = temp;
        subListTailNext = subListTail.getNext();

        subListPrev.setNext(subListBreakPointNext);
        subListTail.setNext(subListHead);
        subListBreakPoint.setNext(subListTailNext);
        head = dummy.getNext();
        return head;
    }
}
