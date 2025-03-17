public class Manager extends Employee {
    private final double basicSalary;
    private final double bonus;
    private final double compensation;

    public Manager(String name, int id, double basicSalary, double bonus, double compensation){
        super(id, name);
        this.basicSalary = basicSalary;
        this.bonus = bonus;
        this.compensation = compensation;
    }

    @Override
    public double getBasicSalary(){
        return this.basicSalary;
    }

    @Override
    public double getBonus(){
        return this.bonus;
    }

    @Override
    public double getCompensation(){
        return this.compensation;
    }
}
