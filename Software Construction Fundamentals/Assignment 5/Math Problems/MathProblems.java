
import java.util.Scanner;

public class MathProblems{

    public static int hcfOfTwoNumbers(int num1, int num2){
        if(num2 == 0){
            return num1;
        }
        return hcfOfTwoNumbers(num2, num1 % num2);
    }

    public static int hcfOfMultipleNumbers(int[] numbers, int index){
        if(index == numbers.length - 1){
            return numbers[index];
        }
        return hcfOfTwoNumbers(numbers[index], hcfOfMultipleNumbers(numbers, index + 1));
    }

    public static int lcmOfTwoNumbers(int num1, int num2){
        return (num1 * num2) / hcfOfTwoNumbers(num1, num2);
    }

    public static int lcmOfMultipleNumbers(int[] numbers, int index){
        if(index == numbers.length - 1){
            return numbers[index];
        }
        return lcmOfTwoNumbers(numbers[index], lcmOfMultipleNumbers(numbers, index + 1));
    }

    /**
     * This Function is to get a valid integer input in the given range.
     * @param sc
     * @param min
     * @param max
     * @return valid integer.
     */
    public static int getIntInput(Scanner sc, int min, int max){
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

    /**
     * This Function is to get a valid double input.
     * @param sc
     * @return valid double value.
     */
    public static double getDoubleInput(Scanner sc){
        while(true){
            if(sc.hasNextDouble()){
                return sc.nextDouble();
            }else{
                System.out.println("Enter a valid decimal number");
                sc.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("========= LCM & HCF Calculator =========");
            System.out.println("Choose what to do:- ");
            System.out.println("1. Calculate LCM");
            System.out.println("2. Calculate HCF");
            System.out.println("3. Exit");
            System.out.println("========================================");
            System.out.print("Enter your choice: ");
            int choice = getIntInput(sc, 1, 3);

            if (choice == 3) {
                System.out.println("Exiting...");
                break;
            }

            System.out.print("Enter the total number of elements: ");
            int noOfValues = getIntInput(sc, 1, Integer.MAX_VALUE);
            int[] arr = new int[noOfValues];
    
            for (int i = 0; i < noOfValues; i++) {
                System.out.print("Enter " + (i + 1) + " element:");
                arr[i] = getIntInput(sc, 0, Integer.MAX_VALUE);
            }
            int result;
            switch (choice) {
                case 1:
                    result = lcmOfMultipleNumbers(arr, 0);
                    System.out.println("LCM is: " + result);
                    break;
                case 2:
                    result = hcfOfMultipleNumbers(arr, 0);
                    System.out.println("HCF is: " + result);
                    break;
            }

            System.out.println("\n");
        }

    }
}