import java.util.Arrays;
import java.util.Scanner;

/**
 * ImmutableIntSet represents an immutable set of integers from a universal set of size 1000.
 * It provides operations like membership check, union, intersection, difference, subset check, and complement.
 */
public class ImmutableIntSet {
    private static final Scanner sc = new Scanner(System.in);
    private final boolean[] setOfElements;
    public static final int UNIVERSAL_SET_SIZE = 1000;

    /**
     * Constructs an ImmutableIntSet from an array of integers.
     * @param nums Array of integers to be included in the set.
     */
    public ImmutableIntSet(int[] nums) {
        setOfElements = new boolean[ImmutableIntSet.UNIVERSAL_SET_SIZE + 1];
        for (int num : nums) {
            setOfElements[num] = true;
        }
    }

    /**
     * Checks if a given number is a member of the set.
     * @param num The number to check.
     * @return True if the number is in the set, false otherwise.
     */
    public boolean isMember(int num) {
        return (num >= 0 && num <= 1000 && setOfElements[num]);
    }

    /**
     * Returns the number of elements in the set.
     * @return The size of the set.
     */
    public int size() {
        int count = 0;
        for (boolean bool : setOfElements) {
            if (bool) count++;
        }
        return count;
    }

    /**
     * Checks if the current set is a subset of another set.
     * @param s The set to compare with.
     * @return True if the current set is a subset of s, false otherwise.
     */
    public boolean isSubSet(ImmutableIntSet s) {
        for (int i = 1; i <= UNIVERSAL_SET_SIZE; i++) {
            if (this.setOfElements[i] != s.isMember(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the complement of the current set within the universal set.
     * @return A new ImmutableIntSet representing the complement.
     */
    public ImmutableIntSet getCompliment() {
        int[] complimentedSet = new int[UNIVERSAL_SET_SIZE - this.size()];
        int index = 0;
        for (int i = 1; i <= UNIVERSAL_SET_SIZE; i++) {
            if (!setOfElements[i]) {
                complimentedSet[index++] = i;
            }
        }
        return new ImmutableIntSet(complimentedSet);
    }

    /**
     * Computes the union of the current set with another set.
     * @param s The set to union with.
     * @return A new ImmutableIntSet representing the union.
     */
    public ImmutableIntSet union(ImmutableIntSet s) {
        int[] unionSet = new int[s.size() + this.size()];
        int index = 0;
        for (int i = 1; i <= UNIVERSAL_SET_SIZE; i++) {
            if (this.setOfElements[i] || s.isMember(i)) {
                unionSet[index++] = i;
            }
        }
        return new ImmutableIntSet(unionSet);
    }

    /**
     * Computes the difference between the current set and another set.
     * @param s The set to subtract from the current set.
     * @return A new ImmutableIntSet representing the difference.
     */
    public ImmutableIntSet difference(ImmutableIntSet s) {
        int[] differenceSet = new int[this.size()];
        int index = 0;
        for (int i = 1; i <= UNIVERSAL_SET_SIZE; i++) {
            if (this.setOfElements[i] && !s.isMember(i)) {
                differenceSet[index++] = i;
            }
        }
        return new ImmutableIntSet(differenceSet);
    }

    /**
     * Computes the intersection of the current set with another set.
     * @param s The set to intersect with.
     * @return A new ImmutableIntSet representing the intersection.
     */
    public ImmutableIntSet intersection(ImmutableIntSet s) {
        int[] intersectionSet = new int[s.size() + this.size()];
        int index = 0;
        for (int i = 1; i <= UNIVERSAL_SET_SIZE; i++) {
            if (this.setOfElements[i] || s.isMember(i)) {
                intersectionSet[index++] = i;
            }
        }
        return new ImmutableIntSet(intersectionSet);
    }

    /**
     * Displays the elements of the set.
     */
    public void displaySet() {
        System.out.print("{ ");
        for (int i = 1; i <= UNIVERSAL_SET_SIZE; i++) {
            if (this.setOfElements[i]) {
                System.out.print(i + ", ");
            }
        }
        System.out.println("}");
    }

    /**
     * Creates a new ImmutableIntSet by taking user input.
     * @return An instance of ImmutableIntSet created from user input.
     */
    private static ImmutableIntSet createSet() {
        System.out.print("Enter elements (comma-separated, max 1000): ");
        String input = sc.nextLine();
        int[] nums = Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt).toArray();
        return new ImmutableIntSet(nums);
    }

    /**
     * Ensures valid integer input from the user within a specified range.
     * @param sc  Scanner object for input.
     * @param min Minimum valid value.
     * @param max Maximum valid value.
     * @return The validated integer input.
     */
    public static int getIntInput(Scanner sc, int min, int max) {
        int num;
        while (true) {
            try {
                num = sc.nextInt();
                sc.nextLine();
                if (num >= min && num <= max) return num;
                else System.out.println("Enter a valid number between " + min + " & " + max);
            } catch (Exception e) {
                System.out.println("Enter a valid number between " + min + " & " + max);
                sc.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        ImmutableIntSet set1 = null, set2 = null;
        while (true) {
            System.out.println("\n===== Immutable Int Set Operations =====");
            System.out.println("1. Create Set 1");
            System.out.println("2. Create Set 2");
            System.out.println("3. Display Set 1");
            System.out.println("4. Display Set 2");
            System.out.println("5. Union of Sets");
            System.out.println("6. Intersection of Sets");
            System.out.println("7. Difference (Set1 - Set2)");
            System.out.println("8. Complement of Set 1");
            System.out.println("9. Check if Set1 is Subset of Set2");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            int choice = getIntInput(sc, 1, 10);
            switch (choice) {
                case 1:
                    set1 = createSet();
                    break;
                case 2:
                    set2 = createSet();
                    break;
                case 3:
                    if (set1 != null) set1.displaySet();
                    else System.out.println("Set 1 is not created yet.");
                    break;
                case 4:
                    if (set2 != null) set2.displaySet();
                    else System.out.println("Set 2 is not created yet.");
                    break;
                case 5:
                    if (set1 != null && set2 != null) set1.union(set2).displaySet();
                    else System.out.println("Both sets must be created first.");
                    break;
                case 6:
                    if (set1 != null && set2 != null) set1.intersection(set2).displaySet();
                    else System.out.println("Both sets must be created first.");
                    break;
                case 7:
                    if (set1 != null && set2 != null) set1.difference(set2).displaySet();
                    else System.out.println("Both sets must be created first.");
                    break;
                case 8:
                    if (set1 != null) set1.getCompliment().displaySet();
                    else System.out.println("Set 1 is not created yet.");
                    break;
                case 9:
                    if (set1 != null && set2 != null) 
                        System.out.println(set1.isSubSet(set2) ? "Set 1 is a subset of Set 2" : "Set 1 is not a subset of Set 2");
                    else System.out.println("Both sets must be created first.");
                    break;
                case 10:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
