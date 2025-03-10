import java.util.ArrayList;
import java.util.Scanner;

public class Marksheet {
    private final ArrayList<Double> grades;
    Marksheet(){
        grades = new ArrayList<>();
    }
    
    /**
     * Calculates the average grade of all the students.
     * @return Average grade.
     * @throws ArithmeticException If no grades are available.
     */
    public double getAverageGrade(){
        if(grades.isEmpty()){
            throw new ArithmeticException("No grades available to calculate average");
        }
        double totalGrades = 0;
        for(int i = 0; i < grades.size(); i++){
            totalGrades += grades.get(i);
        }
        return totalGrades * 1.0 / grades.size();
    }

    /**
     * Calculates the maximum grade from all the students.
     * @return Maximum grade.
     * @throws ArithmeticException If no grades are available.
     */
    public double getMaximumGrade(){
        if(grades.isEmpty()){
            throw new ArithmeticException("No grades available to find maximum marks.");
        }
        double maxGrade = grades.get(0);
        for(int i = 1; i < grades.size(); i++){
            if(grades.get(i) > maxGrade){
                maxGrade = grades.get(i);
            }
        }
        return maxGrade;
    }

    /**
     * Calculates the minimum grade from all the students.
     * @return Minimum grade.
     * @throws ArithmeticException If no grades are available.
     */
    public double getMinimumGrade(){
        if(grades.isEmpty()){
            throw new ArithmeticException("No grades available to find maximum marks.");
        }
        double minGrade = grades.get(0);
        for(int i = 0; i < grades.size(); i++){
            if(grades.get(i) < minGrade){
                minGrade = grades.get(i);
            }
        }
        return minGrade;
    }

    /**
     * Calculates the percentage of students who passed on the basis of thier grades.
     * @return Passing percentage.
     * @throws ArithmeticException If no grades are available.
     */
    public double getPassPercentage(){
        if(grades.isEmpty()){
            throw new ArithmeticException("No grades available to find pass percentage.");
        }
        int noOfPassingStudents = 0;
        for(int i = 0; i < grades.size(); i++){
            if(grades.get(i) >= 40){
                noOfPassingStudents += 1;
            }
        }
        return (noOfPassingStudents * 100.0 / grades.size());
    }

    /**
     * Adds grade of any new student.
     * @param grade.
     * @throws ArithmeticException if grade is not in range 0 to 100.
     */
    public void addGrade(double grade){
        if(grade < 0 || grade > 100){
            throw new ArithmeticException("Grade exceed the limit of permissible range 0 to 100");
        }
        grades.add(grade);
    }

    /**
     * This Function is to get a valid integer input in the given range.
     * @param sc Scanner class object.
     * @param min Lowest range of input.
     * @param max Highest range of input.
     * @return Valid integer between the range.
     */
    public static int getNumInput(Scanner sc, int min, int max){
        int num;
        while(true){
            try{
                num = sc.nextInt();
                sc.nextLine();
                if(num >= min && num <= max)return num;
                else System.out.println("Enter a valid number between " + min + " & " + max);
            }catch(Exception e){
                System.out.println("Enter a valid number between " + min + " & " + max);
                sc.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Marksheet obj = new Marksheet();
        boolean flag = true;
        while(flag){
            System.out.println("====== Welcome to Digital Marksheet ======");
            System.out.println("1. Add grades of new student.");
            System.out.println("2. Get average grade.");
            System.out.println("3. Get maximum grade.");
            System.out.println("4. Get minimum grade.");
            System.out.println("5. Get passing percentage.");
            System.out.println("6. Exit the program.");
            System.out.println("========================================");
            System.out.print("Enter the Option number you want to choose: ");
            int choice = getNumInput(sc, 1, 6);
            double result;
            double grade; 
            switch(choice){
                case 1:
                    System.out.print("Enter the grade you want to add: ");
                    grade = sc.nextDouble();
                    obj.addGrade(grade);
                    break;
                case 2:
                    result = obj.getAverageGrade();
                    System.out.printf("Result = %.2f\n", result);
                    break;
                case 3:
                    result = obj.getMaximumGrade();
                    System.out.printf("Result = %.2f\n", result);
                    break;
                case 4:
                    result = obj.getMinimumGrade();
                    System.out.printf("Result = %.2f\n", result);
                    break;
                case 5:
                    result = obj.getPassPercentage();
                    System.out.printf("Result = %.2f\n", result );
                    break;
                default:
                    flag = false;
            }
            
            System.out.println("\n\n");
        }
    }
}
