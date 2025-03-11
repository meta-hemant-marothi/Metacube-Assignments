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

}