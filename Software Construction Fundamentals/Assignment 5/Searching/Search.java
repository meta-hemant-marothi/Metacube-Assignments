import java.util.Arrays;
import java.util.Scanner;

public class Search {

    /**
     * Performs a recursive Linear Search to find the index of a target element in an array.
     * @param arr The array in which to search.
     * @param target The element to find.
     * @param index The current index being checked.
     * @return The index of the target element if found, otherwise -1.
     */
    public static int linearSearch(double[] arr, double target, int index) {
        if (index >= arr.length) {
            return -1;
        }
        if (arr[index] == target) {
            return index;
        }
        return linearSearch(arr, target, index + 1);
    }

    /**
     * Performs a recursive Binary Search to find the index of a target element in a sorted array.
     * @param arr The sorted array in which to search.
     * @param target The element to find.
     * @param left The left boundary of the search range.
     * @param right The right boundary of the search range.
     * @return The index of the target element if found, otherwise -1.
     */
    public static int binarySearch(double[] arr, double target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            return binarySearch(arr, target, left, mid - 1);
        } else {
            return binarySearch(arr, target, mid + 1, right);
        }
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
                System.out.println("==========  Welcome to Search Service =========");
                System.out.println("Choose a search method:");
                System.out.println("1. Linear Search");
                System.out.println("2. Binary Search");
                System.out.println("3. Exit");
                System.out.println("===============================================");
                System.out.print("Enter your choice: ");
                int choice = getIntInput(sc, 1, 3);
                if (choice == 3) {
                    System.out.println("Exiting...");
                    break;
                }

                System.out.print("Enter the number of elements in the array: ");
                int noOfInputValues = getIntInput(sc, 1, Integer.MAX_VALUE);
                double[] arr = new double[noOfInputValues];

                for (int i = 0; i < noOfInputValues; i++) {
                    System.out.print("Enter " + (i+1) + " element: ");
                    arr[i] = getDoubleInput(sc);
                }

                System.out.print("Enter the element to search: ");
                double target = getDoubleInput(sc);
                int result = -1;

                switch (choice) {
                    case 1:
                        result = linearSearch(arr, target, 0);
                        break;
                    case 2:
                        Arrays.sort(arr);
                        result = binarySearch(arr, target, 0, arr.length - 1);
                        break;
                }

                if (result != -1) {
                    System.out.println("Element found at position: " + (result + 1));
                } else {
                    System.out.println("Element not found in the array.");
                }
                System.out.println("\n");
            }
        }
    }