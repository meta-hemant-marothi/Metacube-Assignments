package EmployeeCollection;
import java.util.Objects;

public class EmployeeNameOrder implements Comparable<EmployeeNameOrder> {

    private int empId;
    private String name;
    private String address;

    public EmployeeNameOrder(int empId, String name, String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int compareTo(EmployeeNameOrder other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EmployeeNameOrder employee = (EmployeeNameOrder) obj;
        return empId == employee.empId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId);
    }

    @Override
    public String toString() {
        return "[empId=" + empId + ", name=" + name + ", address=" + address + "]";
    }
}
