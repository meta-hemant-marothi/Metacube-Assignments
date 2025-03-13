import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MutablePoly {
    private final Map<Integer, Integer> terms;
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Constructs an empty polynomial.
     */
    public MutablePoly() {
        this.terms = new HashMap<>();
    }

    /**
     * Adds a term to the polynomial.
     * @param exponent The exponent of the term.
     * @param coefficient The coefficient of the term.
     */
    public void addTerm(int exponent, int coefficient) {
        if (coefficient == 0) return;
        terms.put(exponent, terms.getOrDefault(exponent, 0) + coefficient);
        if (terms.get(exponent) == 0) {
            terms.remove(exponent);
        }
    }

    /**
     * Removes a term with a given exponent.
     * @param exponent The exponent of the term to remove.
     */
    public void removeTerm(int exponent) {
        terms.remove(exponent);
    }

    /**
     * Evaluates the polynomial at a given value of x.
     * @param x The value at which to evaluate the polynomial.
     * @return The computed result as a float.
     */
    public float evaluate(float x) {
        float result = 0;
        for (Map.Entry<Integer, Integer> entry : terms.entrySet()) {
            result += entry.getValue() * Math.pow(x, entry.getKey());
        }
        return result;
    }

    /**
     * Returns the degree of the polynomial (highest exponent).
     * @return The highest exponent present in the polynomial.
     */
    public int degree() {
        int maxDegree = -1;
        for (int exponent : terms.keySet()) {
            maxDegree = Math.max(maxDegree, exponent);
        }
        return maxDegree;
    }

    /**
     * Displays the polynomial in a readable format.
     */
    public void displayPoly() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : terms.entrySet()) {
            sb.append(entry.getValue()).append("x^").append(entry.getKey()).append(" + ");
        }
        if (sb.length() > 3) sb.setLength(sb.length() - 3);
        System.out.println(sb);
    }

    private static float getFloatInput(Scanner sc) {
        float num;
        while (true) {
            try {
                num = sc.nextFloat();
                return num; 
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
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
                if (num >= min && num <= max) return num;
                else System.out.println("Enter a valid number between " + min + " & " + max);
            } catch (Exception e) {
                System.out.println("Enter a valid number between " + min + " & " + max);
                sc.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        MutablePoly poly1 = new MutablePoly(), poly2 = new MutablePoly();
        int exponent, coefficient;

        while (true) {
            System.out.println("\n===== Polynomial Operations =====");
            System.out.println("1. Add term to Polynomial 1");
            System.out.println("2. Add term to Polynomial 2");
            System.out.println("3. Display Polynomial 1");
            System.out.println("4. Display Polynomial 2");
            System.out.println("5. Evaluate Polynomial 1");
            System.out.println("6. Find Degree of Polynomial 1");
            System.out.println("7. Remove term from Polynomial 1");
            System.out.println("8. Remove term from Polynomial 2");
            System.out.println("9. Exit");
            System.out.println("=================================");
            System.out.print("Enter your choice: ");

            int choice = getIntInput(sc, 1, 9);
            switch (choice) {
                case 1:
                    System.out.print("Enter coefficient: ");
                    coefficient = getIntInput(sc, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    System.out.print("Enter exponent: ");
                    exponent = getIntInput(sc, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    poly1.addTerm(exponent, coefficient);
                    System.out.println("Term Added to polynomial 1");
                    break;
                case 2:
                    System.out.print("Enter coefficient: ");
                    coefficient = getIntInput(sc, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    System.out.print("Enter exponent: ");
                    exponent = getIntInput(sc, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    poly2.addTerm(exponent, coefficient);
                    System.out.println("Term Added to polynomial 2");
                    break;
                case 3:
                    poly1.displayPoly();
                    break;
                case 4:
                    poly2.displayPoly();
                    break;
                case 5:
                    System.out.print("Enter value of x: ");
                    float x = getFloatInput(sc);
                    System.out.println("Result: " + poly1.evaluate(x));
                    break;
                case 6:
                    System.out.println("Degree of Polynomial 1: " + poly1.degree());
                case 7:
                    System.out.print("Enter exponent to remove: ");
                    exponent = getIntInput(sc, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    poly1.removeTerm(exponent);
                    break;
                case 8:
                    System.out.print("Enter exponent to remove: ");
                    exponent = getIntInput(sc, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    poly2.removeTerm(exponent);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
