public class EmployeeLinkedList {
    private EmployeeNode head;

    public EmployeeLinkedList(){
        head = null;
    }

    public EmployeeNode getHead(){
        return head;
    }

    public boolean add(String name, double salary, int age) {
        EmployeeNode newEmployee = new EmployeeNode(name, salary, age);
        if (head == null) {
            head = newEmployee;
            return true;
        }
    
        EmployeeNode listIterator = head;
        EmployeeNode prevListIterator = null;
    
        while (listIterator != null) {
            int comparison = listIterator.compareTo(newEmployee);
            if (comparison < 0) {
                if (prevListIterator == null) {
                    newEmployee.setNext(head);
                    head = newEmployee;
                } else {
                    prevListIterator.setNext(newEmployee);
                    newEmployee.setNext(listIterator);
                }
                return true;
            }
            prevListIterator = listIterator;
            listIterator = listIterator.getNext();
        }
        prevListIterator.setNext(newEmployee);
        return true;
    }

    public void displayList() {
        if (head == null) {
            System.out.println("The list is empty.");
            return;
        }
        EmployeeNode current = head;
        while (current != null) {
            System.out.println("Name: " + current.getName() + 
                               ", Salary: " + current.getSalary() +
                               ", Age: " + current.getAge());
            current = current.getNext();
        }
    }
}
