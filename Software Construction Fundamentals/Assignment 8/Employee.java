public abstract class Employee{
    private int id;
    private String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract double getBasicSalary();
    public abstract double getBonus();
    public abstract double getCompensation();

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }
}