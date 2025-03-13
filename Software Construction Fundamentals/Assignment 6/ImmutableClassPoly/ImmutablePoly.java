import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The ImmutablePoly class represents an immutable polynomial with integer coefficients.
 * It only stores nonzero terms in a hashmap where keys are exponents and values are coefficients.
 */
public class ImmutablePoly {
    private static final Scanner sc = new Scanner(System.in);
    private final Map<Integer, Integer> terms; // Maps exponent to coefficient

    public ImmutablePoly(Map<Integer, Integer> terms) {
        this.terms = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : terms.entrySet()) {
            if (entry.getValue() != 0) {
                this.terms.put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Calculates the value of the polynomial for the given value of the variable
     * @param valueOfX
     * @return The calculated value.
     */
    public float evaluate(float valueOfX) {
        float result = 0;
        for (Map.Entry<Integer, Integer> entry : terms.entrySet()) {
            result += entry.getValue() * Math.pow(valueOfX, entry.getKey());
        }
        return result;
    }

    /**
     * Calculates the highest degree of the polynomial.
     * @return Degree of the polynomial.
     */
    public int degree() {
        int maxDegree = -1;
        for (int exponent : terms.keySet()) {
            maxDegree = Math.max(maxDegree, exponent);
        }
        return maxDegree;
    }

    /**
     * Calculates and returns the sum of the polynomials p1 and p2
     * @param p1
     * @param p2
     * @return The sum of the polynomials p1 and p2
     */
    public static ImmutablePoly addPoly(ImmutablePoly p1, ImmutablePoly p2) {
        Map<Integer, Integer> result = new HashMap<>(p1.terms);
        for (Map.Entry<Integer, Integer> entry : p2.terms.entrySet()) {
            result.put(entry.getKey(), result.getOrDefault(entry.getKey(), 0) + entry.getValue());
        }
        return new ImmutablePoly(result);
    }

    /**
     * Calculates the result of multiplication of two polynomials.
     * @param p1
     * @param p2
     * @return multiplied polynomial object.
     */
    public static ImmutablePoly multiplyPoly(ImmutablePoly p1, ImmutablePoly p2) {
        Map<Integer, Integer> result = new HashMap<>();
        for (Map.Entry<Integer, Integer> term1 : p1.terms.entrySet()) {
            for (Map.Entry<Integer, Integer> term2 : p2.terms.entrySet()) {
                int newExponent = term1.getKey() + term2.getKey();
                int newCoefficient = term1.getValue() * term2.getValue();
                result.put(newExponent, result.getOrDefault(newExponent, 0) + newCoefficient);
            }
        }
        return new ImmutablePoly(result);
    }

    /**
     * Systematically prints the polynomial;
     */
    public void displayPoly() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : terms.entrySet()) {
            sb.append(entry.getValue()).append("x^").append(entry.getKey()).append(" + ");
        }
        if (sb.length() > 3){
            sb.setLength(sb.length() - 3);
        }
        System.out.println(sb);
    }

    /**
     * Based on user inputs creates a Immutable polynomial.
     * @return Immutable Polynomial Object.
     */
    private static ImmutablePoly createPoly() {
        System.out.println("Enter total polynomial terms you want to enter: ");
        int length = getIntInput(sc, 0, Integer.MAX_VALUE);
        Map<Integer, Integer> terms = new HashMap<>();
        for(int i = 0; i < length; i++){
            System.out.print("Enter " + (i + 1) + " terms Coefficient: ");
            int coefficient = getIntInput(sc, Integer.MIN_VALUE, Integer.MAX_VALUE);
            System.out.print("Enter " + (i + 1) + " terms Exponent: ");
            int exponent = getIntInput(sc, Integer.MIN_VALUE, Integer.MAX_VALUE);
            terms.put(exponent, coefficient);
        }
        return new ImmutablePoly(terms);
    }

    /**
     * Validates whether the input is a valid floating number or not.
     * @param sc Scanner object.
     * @return Valid floating number.
     */
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
        ImmutablePoly poly1 = null, poly2 = null;

        while (true) {
            System.out.println("\n===== Polynomial Operations =====");
            System.out.println("1. Create Polynomial 1");
            System.out.println("2. Create Polynomial 2");
            System.out.println("3. Display Polynomial 1");
            System.out.println("4. Display Polynomial 2");
            System.out.println("5. Evaluate Polynomial 1");
            System.out.println("6. Find Degree of Polynomial 1");
            System.out.println("7. Add Polynomial 1 and 2");
            System.out.println("8. Multiply Polynomial 1 and 2");
            System.out.println("9. Exit");
            System.out.println("=================================");
            System.out.print("Enter your choice: ");

            int choice = getIntInput(sc, 1, 9);
            switch (choice) {
                case 1:
                    poly1 = createPoly();
                    System.out.println("Polynomial 1 Created!");
                    break;
                case 2:
                    poly2 = createPoly();
                    System.out.println("Polynomial 2 Created!");
                    break;
                case 3:
                    if (poly1 != null) poly1.displayPoly();
                    else System.out.println("Polynomial 1 is not created yet.");
                    break;
                case 4:
                    if (poly2 != null) poly2.displayPoly();
                    else System.out.println("Polynomial 2 is not created yet.");
                    break;
                case 5:
                    if (poly1 != null) {
                        System.out.print("Enter value of x: ");
                        float x = getFloatInput(sc);
                        System.out.println("Result: " + poly1.evaluate(x));
                    } else {
                        System.out.println("Polynomial 1 is not created yet.");
                    }
                    break;
                case 6:
                    if (poly1 != null) {
                        System.out.println("Degree of Polynomial 1: " + poly1.degree());
                    } else {
                        System.out.println("Polynomial 1 is not created yet.");
                    }
                    break;
                case 7:
                    if (poly1 != null && poly2 != null) {
                        ImmutablePoly sum = addPoly(poly1, poly2);
                        System.out.print("Sum: ");
                        sum.displayPoly();
                    } else {
                        System.out.println("Both polynomials must be created first.");
                    }
                    break;
                case 8:
                    if (poly1 != null && poly2 != null) {
                        ImmutablePoly product = multiplyPoly(poly1, poly2);
                        System.out.print("Product: ");
                        product.displayPoly();
                    } else {
                        System.out.println("Both polynomials must be created first.");
                    }
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
