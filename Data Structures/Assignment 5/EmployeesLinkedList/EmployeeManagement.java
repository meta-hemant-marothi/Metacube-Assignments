import java.util.Scanner;

public class EmployeeManagement {
    public static void main(String[] args) {
        EmployeeLinkedList employeeList = new EmployeeLinkedList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employee List");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();

                    double salary;
                    while (true) {
                        System.out.print("Enter Salary: ");
                        try {
                            salary = Double.parseDouble(scanner.nextLine());
                            if (salary > 0) {
                                break;
                            } else {
                                System.out.println("Salary must be a positive value. Try again.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid salary. Please enter a numeric value.");
                        }
                    }

                    int age;
                    while (true) {
                        System.out.print("Enter Age: ");
                        try {
                            age = Integer.parseInt(scanner.nextLine());
                            if (age > 0) {
                                break;
                            } else {
                                System.out.println("Age must be a positive value. Try again.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid age. Please enter a whole number.");
                        }
                    }

                    boolean success = employeeList.add(name, salary, age);
                    if (success) {
                        System.out.println("Employee added successfully.");
                    } else {
                        System.out.println("Failed to add employee.");
                    }
                    break;

                case 2:
                    System.out.println("\n===== Employee List =====");
                    employeeList.displayList();
                    break;

                case 3:
                    System.out.println("Exiting Employee Management System. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }
}