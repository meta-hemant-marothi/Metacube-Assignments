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
    public static int linearSearch(int[] arr, int target, int index) {
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
    public static int binarySearch(int[] arr, int target, int left, int right) {
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

    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter the number of elements in the array: ");
            int n = sc.nextInt();
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                System.out.println("Enter " + i + " element:");
                arr[i] = sc.nextInt();
            }

            while (true) {
                System.out.println("\nChoose a search method:");
                System.out.println("1. Linear Search");
                System.out.println("2. Binary Search");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                if (choice == 3) {
                    System.out.println("Exiting...");
                    break;
                }

                System.out.print("Enter the element to search: ");
                int target = sc.nextInt();
                int result = -1;

                switch (choice) {
                    case 1:
                        result = linearSearch(arr, target, 0);
                        break;
                    case 2:
                        Arrays.sort(arr);
                        result = binarySearch(arr, target, 0, arr.length - 1);
                        break;
                    default:
                        System.out.println("Invalid choice! Try again.");
                        continue;
                }

                if (result != -1) {
                    System.out.println("Element found at index: " + result);
                } else {
                    System.out.println("Element not found in the array.");
                }
            }

            sc.close();
        }

}
