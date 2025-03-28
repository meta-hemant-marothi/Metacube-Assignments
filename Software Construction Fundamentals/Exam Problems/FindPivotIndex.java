import java.util.ArrayList;
import java.util.List;

public class FindPivotIndex {
    public static int pivotIndex(int[] nums) {
        int n = nums.length;
        List<Integer> leftProd = new ArrayList<>(n), rightProd = new ArrayList<>(n);

        leftProd.add(1); // Left product starts with 1
        rightProd.add(1); // Right product starts with 1

        // Build the left product list
        for (int i = 1; i < n; i++) {
            leftProd.add(leftProd.get(i - 1) * nums[i - 1]);
            rightProd.add(rightProd.get(i - 1) * nums[n - i]);
        }

        // Find the pivot index
        for (int i = 0; i < n; i++) {
            if (leftProd.get(i) == rightProd.get(n - i - 1)) {
                return i; // Found the pivot index
            }
        }

        return -1; // No pivot index found
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 6};

        System.out.println("Pivot Index: " + pivotIndex(nums));
    }
}