
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
        System.out.println("+-----------------------------------------------------------------------+");
        System.out.println("|  Payroll Summary for Organization: " + organization.getName() + "                           |");
        System.out.println("+-----------------------------------------------------------------------+");
        System.out.println("|  EMP_ID  |     EMP_NAME     | BASIC_SALARY |   BONUS   | COMPENSATION |");
        for(Employee employee : organization.getAllEmployees()){
            System.out.printf("| %-8d | %-16s | %-12.2f | %-9.2f | %-12.2f |\n", employee.getId(),
            employee.getName(), employee.getBasicSalary(), employee.getBonus(), employee.getCompensation());
        }
        System.out.println("\n+----------------------------------------------------------------------+");
    }
}
