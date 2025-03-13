import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The ImmutablePoly class represents an immutable polynomial with integer coefficients.
 * It only stores nonzero terms in a hashmap where keys are exponents and values are coefficients.
 */
public class ImmutablePoly {
    private final Map<Integer, Integer> terms; // Maps exponent to coefficient

    public ImmutablePoly(Map<Integer, Integer> terms) {
        this.terms = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : terms.entrySet()) {
            if (entry.getValue() != 0) {
                this.terms.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public float evaluate(float x) {
        float result = 0;
        for (Map.Entry<Integer, Integer> entry : terms.entrySet()) {
            result += entry.getValue() * Math.pow(x, entry.getKey());
        }
        return result;
    }

    public int degree() {
        int maxDegree = -1;
        for (int exponent : terms.keySet()) {
            maxDegree = Math.max(maxDegree, exponent);
        }
        return maxDegree;
    }

    public static ImmutablePoly addPoly(ImmutablePoly p1, ImmutablePoly p2) {
        Map<Integer, Integer> result = new HashMap<>(p1.terms);
        for (Map.Entry<Integer, Integer> entry : p2.terms.entrySet()) {
            result.put(entry.getKey(), result.getOrDefault(entry.getKey(), 0) + entry.getValue());
        }
        return new ImmutablePoly(result);
    }

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

    public void displayPoly() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : terms.entrySet()) {
            sb.append(entry.getValue()).append("x^").append(entry.getKey()).append(" + ");
        }
        if (sb.length() > 3) sb.setLength(sb.length() - 3); // Remove trailing " + "
        System.out.println(sb);
    }

    private static ImmutablePoly createPoly(Scanner sc) {
        System.out.println("Enter polynomial terms in format: coefficient exponent (separated by space), type 'done' when finished:");
        Map<Integer, Integer> terms = new HashMap<>();
        
        while (true) {
            System.out.print("> ");
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("done")) break;
            
            String[] parts = input.split("\\s+");
            if (parts.length != 2) {
                System.out.println("Invalid format! Enter in format: coefficient exponent (e.g., 3 2 for 3x^2)");
                continue;
            }

            try {
                int coefficient = Integer.parseInt(parts[0]);
                int exponent = Integer.parseInt(parts[1]);
                terms.put(exponent, terms.getOrDefault(exponent, 0) + coefficient);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter integers for coefficient and exponent.");
            }
        }

        return new ImmutablePoly(terms);
    }

    private static float getFloatInput(Scanner sc) {
        while (true) {
            try {
                return Float.parseFloat(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    private static int getIntInput(Scanner sc, int min, int max) {
        int num;
        while (true) {
            try {
                num = Integer.parseInt(sc.nextLine().trim());
                if (num >= min && num <= max) return num;
                System.out.println("Enter a valid number between " + min + " & " + max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter a valid number between " + min + " & " + max);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
            System.out.print("Enter your choice: ");

            int choice = getIntInput(sc, 1, 9);
            switch (choice) {
                case 1:
                    poly1 = createPoly(sc);
                    System.out.println("Polynomial 1 Created!");
                    break;
                case 2:
                    poly2 = createPoly(sc);
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
