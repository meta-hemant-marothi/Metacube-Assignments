
import java.util.ArrayList;
import java.util.List;

public class Organization {
    private String name;
    public List<Department> departments;
    
    public String getName(){
        return this.name;
    }

    Organization(String name) {
        this.name = name;
        departments = new ArrayList<>();
    }

    public boolean addDepartment(Department dep){
        if(!departments.contains(dep)){
            departments.add(dep);
            return true;
        }
        return false;
    }

    public List<Employee> getAllEmployees(){
        List<Employee> allEmployees = new ArrayList<>();
        for(Department dept : departments){
            allEmployees.addAll(dept.getEmployees());
        }
        return allEmployees;
    }

}
