package EmployeeCollection;

import java.util.ArrayList;
import java.util.List;

public class EmployeeCollection<T> {
    private final List<T> listOfEmployees;

    public EmployeeCollection() {
        listOfEmployees = new ArrayList<>();
    }

    public boolean addEmployee(T newEmployee){
        for(T employee : listOfEmployees){
            if(employee.equals(newEmployee)){
                throw new AssertionError("!! Employee with same ID already Present !!");
            }
        }
        return listOfEmployees.add(newEmployee);
    }

    public void displayEmployees(){
        listOfEmployees.sort(null);
        for(T employee : listOfEmployees){
            System.out.println(employee);
        }
    }

    public static void main(String[] args) {
        EmployeeCollection<EmployeeNaturalOrder> empCollection = new EmployeeCollection<>();
        empCollection.addEmployee(new EmployeeNaturalOrder(2, "Hemant", "sdcnndnfo"));
        empCollection.addEmployee(new EmployeeNaturalOrder(1, "Vishnu", "sdcnndnfo"));
        empCollection.addEmployee(new EmployeeNaturalOrder(2, "Hemant", "sdcnndnfo"));

        empCollection.displayEmployees();

        EmployeeCollection<EmployeeNameOrder> empCollection1 = new EmployeeCollection<>();
        empCollection1.addEmployee(new EmployeeNameOrder(2, "Hemant", "sdcnndnfo"));
        empCollection1.addEmployee(new EmployeeNameOrder(1, "Vishnu", "sdcnndnfo"));
        empCollection1.displayEmployees();
    }

}
