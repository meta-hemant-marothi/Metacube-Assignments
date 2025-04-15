public class EmployeeNode{
    private String name;
    private double salary;
    private int age;
    private EmployeeNode next;

    public EmployeeNode(String name, double salary, int age){
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.next = null;
    }

    public EmployeeNode getNext() {
        return next;
    }

    public void setNext(EmployeeNode next) {
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Salary: " + salary + ", Age: " + age;
    }
}