import java.util.Scanner;

public class EmployeeManagement {
    private static Scanner scanner = new Scanner(System.in);
    private EmployeeNode head;

    public EmployeeManagement() {
        this.head = null;
    }

    /**
     * This Function is to get a valid integer input in the given range.
     * @param sc
     * @param min
     * @param max
     * @return valid integer.
     */
    public static int getIntInput(Scanner sc, int min, int max){
        int num;
        while(true){
            try{
                num = sc.nextInt();
                sc.nextLine();
                if(num >= min && num <= max)return num;
                else System.out.println("Enter a valid number between " + min + " & " + max);
            }catch(Exception e){
                System.out.println("Enter a valid number between " + min + " & " + max);
                sc.nextLine();
            }
        }
    }

    /**
     * This Function is to get a valid double input.
     * @param sc
     * @return valid double value.
     */
    public static double getDoubleInput(Scanner sc){
        while(true){
            if(sc.hasNextDouble()){
                return sc.nextDouble();
            }else{
                System.out.println("Enter a valid decimal number");
                sc.nextLine();
            }
        }
    }

    public void addEmployee(){
        System.out.print("Enter the name of the employee: ");
        String name = scanner.nextLine();
        System.out.print("Enter his/her salary: ");
        double salary = getDoubleInput(scanner);
        System.out.print("Enter his/her age: ");
        int age = getIntInput(scanner, 0, 100);
        EmployeeNode newNode = new EmployeeNode(name, salary, age);
        if(head == null){
            head = newNode; 
            return;
        }
        EmployeeNode lastNode = head;
        while(lastNode.getNext() != null){
            lastNode = lastNode.getNext();
        }
        lastNode.setNext(newNode);
    }

    public void partition(EmployeeNode pivot,  EmployeeNode[] leftRight){
        EmployeeNode temp = pivot.getNext();
        while(temp != null){
            EmployeeNode tempNext = temp.getNext();
            if(temp.getSalary() > pivot.getSalary()){
                temp.setNext(leftRight[0]);
                leftRight[0] = temp;
            }else if(temp.getSalary() < pivot.getSalary()){
                temp.setNext(leftRight[1]);
                leftRight[1] = temp;
            }else{
                if(temp.getAge() <= pivot.getAge()){
                    temp.setNext(leftRight[0]);
                    leftRight[0] = temp;
                }else{
                    temp.setNext(leftRight[1]);
                    leftRight[1] = temp;
                }
            }
            temp = tempNext;
        }
    }

    public EmployeeNode quickSortEmployees(EmployeeNode head){
        if(head == null || head.getNext() == null){
            return head;
        }
        EmployeeNode[] leftRight = {null, null};
        partition(head, leftRight);
        leftRight[0] = quickSortEmployees(leftRight[0]);
        leftRight[1] = quickSortEmployees(leftRight[1]);

        EmployeeNode traversal = leftRight[0];
        while(traversal != null && traversal.getNext() != null){
            traversal = traversal.getNext();
        }
        if(traversal != null){
            traversal.setNext(head);
        }else{
            leftRight[0] = head;
        }
        head.setNext(leftRight[1]);
        return leftRight[0];
    }

    public void sortEmployees(){
        head = quickSortEmployees(head);
    }

    public void display(){
        EmployeeNode current = head;
        if(head == null){
            System.out.println("No employees added yet.");
            return;
        }
        System.out.println("List of all Employees:- ");
        while(current != null){
            System.out.println(current);
            current = current.getNext();
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        int choice;
        EmployeeManagement metacube = new EmployeeManagement();

        do {
            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Sort Employees");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = getIntInput(scanner, 1, 4);

            switch (choice) {
                case 1:
                    metacube.addEmployee();
                    break;
                case 2:
                    metacube.display();
                    break;
                case 3:
                    metacube.sortEmployees();
                    System.out.println("Employees sorted successfully!");
                    break;
                case 4:
                    System.out.println("Exiting the Employee Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

}
