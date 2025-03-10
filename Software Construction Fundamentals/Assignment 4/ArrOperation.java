import java.util.Scanner;

public class ArrOperation{
    /**
     * Calculates the size of the largest mirror section found in the input array.
     * @param arr
     * @return Return the size of the largest mirror section found in the input array.
     * @throws AssertionError If Array is empty.
     */
    public int maxMirror(int[] arr) throws AssertionError{
        if(arr.length == 0){
            throw new AssertionError("Given array should not be empty.");
        }
        int maxSize = 0;
        for(int i = 0; i < arr.length; i++){
            for(int j = arr.length - 1; j >= 0; j--){
                int size = 0;
                while(i + size < arr.length && j - size >= 0 && arr[i + size] == arr[j - size]){
                    size++;
                }
                if(maxSize < size){
                    maxSize = size;
                }
            }
        }
        return maxSize;
    }

    /**
     * Calculates the number of clumps in the input array.
     * @param arr
     * @return Return the number of clumps in the input array.
     * @throws AssertionError If array is empty.
     */
    public int countClumps(int[] arr) throws AssertionError{
        if(arr.length == 0){
            throw new AssertionError("Given array should not be empty.");
        }
        int count = 0;
        int iterator = 1;
        while(iterator < arr.length){
            if(arr[iterator - 1] == arr[iterator]){
                count++;
                while(iterator < arr.length && arr[iterator - 1] == arr[iterator]){
                    iterator++;
                }
            }else{
                iterator++;
            }
        }
        return count;
    }

    /**
     * Return the index if there is a place to split the input array so that the 
     * sum of the numbers on one side is equal to the sum of the numbers on the 
     * other side else return -1.
     * @param arr
     * @return The splitting index if exists else -1.
     * @throws AssertionError If given array is empty.
     */
    public int splitArray(int[] arr) throws AssertionError{
        if(arr.length == 0){
            throw new AssertionError("Given array should not be empty.");
        }
        int totalSumOfArray = 0;
        for(int i : arr){
            totalSumOfArray += i;
        }
        int leftSum = 0;
        for(int i = 0; i < arr.length; i++){
            leftSum += arr[i];
            if(leftSum == totalSumOfArray - leftSum){
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * Return an array that contains exactly the same numbers as the input array, 
     * but rearranged so that every X is immediately followed by a Y. 
     * Do not move X within array, but every other number may move.
     * @param arr
     * @param X
     * @param Y
     * @return Return an array rearranged so that every X is immediately followed by a Y.
     * @throws AssertionError
     */
    public int[] fixXY(int[] arr, int X, int Y) throws AssertionError{
        if(arr.length == 0){
            throw new AssertionError("Empty array passed as an argument");
        }
        
        int countOfX = 0, countOfY = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == X){
                countOfX++;
            }else if(arr[i] == Y){
                countOfY++;
            }
        }
        if(countOfX != countOfY){
            throw new AssertionError("Count of both X and Y is unequal.");
        }
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == X && arr[i+1] == X){
                throw new AssertionError("Multiple values of X occured adjacently.");
            }
        }
        if(arr[arr.length - 1] == X){
            throw new AssertionError("Found value of X at last index.");
        }
        int posOfY = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == X){
                while(arr[posOfY] != Y){
                    posOfY++;
                }
                int temp = arr[posOfY];
                arr[posOfY] = arr[i + 1];
                arr[i + 1] = temp;
                posOfY++;
            }
        }
        return arr;
    }

    /**
     * Prints the particular array.
     * @param arr
     */
    public void printArray(int[] arr){
        System.out.print("[  ");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + "  ");
        }
        System.out.println("]");
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
        ArrOperation obj = new ArrOperation();
        while(true){
            System.out.println("====== Welcome to Array Operator ======");
            System.out.println("1. Get size of the largest mirror section.");
            System.out.println("2. Get the number of clumps.");
            System.out.println("3. fixXY.");
            System.out.println("4. Get split index such that leftsum is equal to right sum.");
            System.out.println("5. Exit the program.");
            System.out.println("========================================");
            System.out.print("Enter the Option number you want to choose: ");
            int choice = getIntInput(sc, 1, 5);
            if(choice == 5)break;
            System.out.print("Enter the total number of values in the array: ");
            int noOfValues = getIntInput(sc, 0, Integer.MAX_VALUE);
            int[] arr = new int[noOfValues];
            for(int i = 0; i < noOfValues; i++){
                System.out.print("Enter " + i+1 + " value: ");
                arr[i] = getIntInput(sc, Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
            System.out.print("The input array is: ");
            obj.printArray(arr);
            try{
                switch(choice){
                    case 1:
                        System.out.println("Size of the largest mirror section is " + obj.maxMirror(arr));
                        break;
                    case 2:
                        System.out.println("The number of clumps in this array are " + obj.countClumps(arr));
                        break;
                    case 3:
                        System.out.print("Enter the value of X: ");
                        int X = getIntInput(sc, Integer.MIN_VALUE, Integer.MAX_VALUE);
                        System.out.print("Enter the value of Y: ");
                        int Y = getIntInput(sc, Integer.MIN_VALUE, Integer.MAX_VALUE);
                        int[] res = obj.fixXY(arr, X, Y);
                        System.out.print("The fitted array is: ");
                        obj.printArray(res);
                        break;
                    case 4:
                        System.out.println("The index of splitting is " + obj.splitArray(arr));
                        break;
                }
            }catch(Exception e){
                System.out.println("Error: " + e);
                System.out.println("Try Again!!!");
            }
            System.out.println("\n\n");
        }
    }
}