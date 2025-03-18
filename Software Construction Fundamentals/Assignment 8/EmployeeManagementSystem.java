import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class EmployeeManagementSystem {
    private static final Organization organization = new Organization("Metacube");
    private static final Scanner sc = new Scanner(System.in);
    private static final Set<Integer> employeeIds = new HashSet<>();

    /**
     * Adds a new department to the oraganization.
     * @return true if successfully added else false. 
     */
    private static boolean addDepartment() {
        System.out.print("Enter Department Name: ");
        String name = sc.nextLine().trim();

        if (findDepartment(name) != null) {
            System.out.println("Error: Department already exists!");
            return false;
        }

        Department dept = new Department(name);
        organization.addDepartment(dept);
        System.out.println("Department added successfully!");
        return true;
    }

    /**
     * Relieves or removes an employee if present in the organisation based on ID.
     * @return true if removed else false.
     */
    private static boolean removeEmployee() {
        int id;
        System.out.print("Enter Employee ID: ");
        id = getIntInput(sc, 1, Integer.MAX_VALUE);

        if (!employeeIds.contains(id)) {
            System.out.println("Error: Employee ID doesn't exists!");
            return false;
        }
        Employee employeeToRemove = null;
        Department departmentOfEmployee = null;
        for(Department dep : organization.departments){
            for(Employee employee : dep.employees){
                if(employee.getId() == id){
                    departmentOfEmployee = dep;
                    employeeToRemove = employee;
                    break;
                }
            }
        }
        departmentOfEmployee.relieve(employeeToRemove);
        employeeIds.remove(id);
        System.out.println("Employee removed successfully!");
        return true;
    }

    /**
     * Adds an employee to a specific department under the organisation.
     * @return true if added else false.
     */
    private static boolean  addEmployee() {
        System.out.print("Enter Department Name: ");
        String deptName = sc.nextLine().trim();
        Department department = findDepartment(deptName);

        if (department == null) {
            System.out.println("Error: Department not found! Please add the department first.");
            return false;
        }

        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine().trim();

        int id;
        while (true) {
            System.out.print("Enter Employee ID: ");
            id = getIntInput(sc, 1, Integer.MAX_VALUE);

            if (employeeIds.contains(id)) {
                System.out.println("Error: Employee ID already exists! Enter a unique ID.");
            } else {
                break;
            }
        }

        System.out.print("Enter Basic Salary: ");
        double salary = getDoubleInput(sc);
        System.out.print("Enter Bonus: ");
        double bonus = getDoubleInput(sc);
        System.out.print("Enter Compensation: ");
        double compensation = getDoubleInput(sc);

        System.out.println("Select Employee Type: 1. Developer  2. Manager");
        int type = getIntInput(sc, 1, 2);

        Employee employee = (type == 1) ? new Developer(name, id, salary, bonus, compensation) : new Manager(name, id, salary, bonus, salary);
        department.join(employee);
        employeeIds.add(id);
        System.out.println("Employee added successfully!");
        return true;
    }

    /**
     * Prints all Existing employees of an organisation.
     */
    private static void viewEmployees() {
        List<Employee> allEmployees = organization.getAllEmployees();
        if (allEmployees.isEmpty()) {
            System.out.println("No employees found in the organization.");
            return;
        }

        System.out.println("\n==== Employees in Organization ====");
        for (Department dept : organization.departments) {
            System.out.println("\nDepartment: " + dept.getName());
            for (Employee emp : dept.getEmployees()) {
                System.out.println(" - " + emp.getName() + " (ID: " + emp.getId() + ")");
            }
        }
    }

    /**
     * Checks if department exists or not in the organisation and if exists return its object.
     * @param name of the department.
     * @return object of department if exists else null.
     */
    private static Department findDepartment(String name) {
        for (Department dept : organization.departments) {
            if (dept.getName().equalsIgnoreCase(name)) {
                return dept;
            }
        }
        return null;
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

    public static void main(String[] args) {
        while (true) {
            try{
                System.out.println("\n======= Welcome to employee management system =======");
                System.out.println("1. Add Department.");
                System.out.println("2. Add employee.");
                System.out.println("3. View all employees.");
                System.out.println("4. Print salary slips.");
                System.out.println("5. Remove an employee.");
                System.out.println("6. Exit.");
                System.out.println("=====================================================");

                System.out.print("Enter your choice: ");
                int choice = getIntInput(sc, 1, 6);
                switch (choice) {
                    case 1:
                        addDepartment();
                        break;
                    case 2:
                        addEmployee();
                        break;
                    case 3:
                        viewEmployees();
                        break;
                    case 4:
                        Payroll.printSalarySlips(organization);
                        break;
                    case 5:
                        removeEmployee();
                        break;
                    case 6:
                        return;
                }
            }catch(Exception e){
                System.out.println("Error Occured: " + e + "\n!! Try again !!");
            }
        }
    }
}
