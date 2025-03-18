import static org.junit.Assert.assertEquals;

import java.net.http.HttpRequest;
import org.junit.jupiter.api.Test;

public class EmployeeManagementTest {
    @Test
    public void departmentCreationTest(){
        Organization metacubeOrganization = new Organization("Metacube");
        Department hrDepartment = new Department("HR");
        Department managerDepartment = new Department("MANAGER");
        assertEquals(true, metacubeOrganization.addDepartment(hrDepartment));
        assertEquals(true, metacubeOrganization.addDepartment(managerDepartment));
        assertEquals(false, metacubeOrganization.addDepartment(hrDepartment));
        assertEquals(2, metacubeOrganization.departments.size());
    }

    @Test
    public void employeeCreationAndJoinTest(){
        Developer e1 = new Developer("Hemant", 1, 100000, 2000.30, 500);
        Developer e2 = new Developer("Amit", 2, 200000, 2000.30, 500);
        Manager e3 = new Manager("Abdu", 3, 100000, 2000.30, 500);
        Organization metacubeOrganization = new Organization("Metacube");
        Department itDepartment = new Department("IT");
        Department managerDepartment = new Department("MANAGER");

        metacubeOrganization.addDepartment(itDepartment);
        metacubeOrganization.addDepartment(managerDepartment);

        assertEquals(true, itDepartment.join(e1));
        assertEquals(true, itDepartment.join(e2));
        assertEquals(true, managerDepartment.join(e3));
        assertEquals(false, managerDepartment.join(e3));

        assertEquals(3, metacubeOrganization.getAllEmployees().size());
    }
}
