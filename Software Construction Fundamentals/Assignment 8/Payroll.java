

public class Payroll {
    public static double calculateTax(double salary){
        if(salary <= 25000){
            return salary * 0.1;
        }else if(salary <= 50000){
            return salary * 0.2;
        }
        return salary * 0.3;
    }

    public static void printSalarySlips(Organization organization){
        System.out.println("+--------------------------------------------------------------+");
        System.out.println("|  Payroll Summary for Organization: " + organization.getName() + "  |");
        System.out.println("+--------------------------------------------------------------+");
        System.out.println("| EMP_ID | EMP_NAME | BASIC_SALARY | ");
        for(Employee employee : organization.getAllEmployees()){
            
        }
    }
}
