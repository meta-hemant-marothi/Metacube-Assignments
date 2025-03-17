
import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;
    public List<Employee> employees;

    public String getName(){
        return this.name;
    }

    Department(String name){
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public boolean join(Employee employee){
        if(!employees.contains(employee)){
            return employees.add(employee);
        }
        return false;
    }

    public boolean relieve(Employee employee){
        return employees.remove(employee);
        
    }

    public List<Employee> getEmployees(){
        return this.employees;
    }
}
