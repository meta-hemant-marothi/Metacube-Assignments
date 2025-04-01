import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class MultivariatePolynomial {
    private PolynomialListNode head;
    class PolynomialListNode {
        private PolynomialTerm value;
        private PolynomialListNode next;
    
        public PolynomialListNode(PolynomialTerm value) {
            this.value = value;
            this.next = null;
        }
    
        public PolynomialTerm getValue() {
            return value;
        }
    
        public void setValue(PolynomialTerm value) {
            this.value = value;
        }
    
        public PolynomialListNode getNext() {
            return next;
        }
    
        public void setNext(PolynomialListNode next) {
            this.next = next;
        }
    
        @Override
        public String toString() {
            return value.toString();
        }
    }
    // Constructor to initialize an empty polynomial
    public MultivariatePolynomial() {
        this.head = null;
    }

    // Add term to the polynomial
    public void addTerm(int coefficient, Map<String, Integer> variables) {
        Map<String, Integer> term = new HashMap<>(variables);
        PolynomialListNode newNode = new PolynomialListNode(new PolynomialTerm(coefficient, term));
        if (head == null) {
            head = newNode;
        } else {
            PolynomialListNode temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
        }
    }

    // Find degree of the polynomial
    public int findDegree() {
        int maxDegree = 0;
        PolynomialListNode temp = head;

        while (temp != null) {
            PolynomialTerm term = (PolynomialTerm) temp.getValue();
            int degree = term.variables.values().stream().mapToInt(Integer::intValue).sum();
            maxDegree = Math.max(maxDegree, degree);
            temp = temp.getNext();
        }

        return maxDegree;
    }

    // Display the polynomial
    public void displayPolynomial() {
        System.out.println("Multivariate Polynomial: ");
        PolynomialListNode temp = head;

        while (temp != null) {
            PolynomialTerm term = (PolynomialTerm) temp.getValue();
            System.out.print(term + " + ");
            temp = temp.getNext();
        }
        System.out.println("0");
    }

    // PolynomialTerm class to represent a term in the polynomial
    private static class PolynomialTerm {
        int coefficient;               // Coefficient of the term
        Map<String, Integer> variables; // Variables and their exponents

        public PolynomialTerm(int coefficient, Map<String, Integer> variables) {
            this.coefficient = coefficient;
            this.variables = variables;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(coefficient);
            for (Map.Entry<String, Integer> entry : variables.entrySet()) {
                sb.append(entry.getKey()).append("^").append(entry.getValue());
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        MultivariatePolynomial polynomial = new MultivariatePolynomial();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Multivariate Polynomial Terminal UI ---");
            System.out.println("1. Add Term");
            System.out.println("2. Display Polynomial");
            System.out.println("3. Find Degree");
            System.out.println("4. Exit");
            System.out.print("Select an option (1-4): ");

            int choice;
            while (true) {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 4) break;
                }
                System.out.print("Invalid input! Please enter a number between 1 and 4: ");
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter coefficient: ");
                    int coefficient;
                    while (true) { // Validate coefficient input
                        if (scanner.hasNextInt()) {
                            coefficient = scanner.nextInt();
                            break;
                        }
                        System.out.print("Invalid input! Coefficient must be an integer: ");
                        scanner.nextLine(); // Clear invalid input
                    }

                    Map<String, Integer> variables = new HashMap<>();
                    System.out.print("Enter number of variables in the term: ");
                    int numVariables;
                    while (true) { // Validate numVariables input
                        if (scanner.hasNextInt()) {
                            numVariables = scanner.nextInt();
                            if (numVariables >= 0) break; // Ensure non-negative
                        }
                        System.out.print("Invalid input! Number of variables must be a non-negative integer: ");
                        scanner.nextLine(); // Clear invalid input
                    }

                    for (int i = 0; i < numVariables; i++) {
                        System.out.print("Variable name (letters only): ");
                        String variable;
                        while (true) { // Validate variable input
                            variable = scanner.next();
                            if (variable.matches("[a-zA-Z]+")) break; // Check for valid letters
                            System.out.print("Invalid input! Variable name must contain letters only: ");
                        }

                        System.out.print("Exponent (non-negative integer): ");
                        int exponent;
                        while (true) { // Validate exponent input
                            if (scanner.hasNextInt()) {
                                exponent = scanner.nextInt();
                                if (exponent >= 0) break; // Ensure non-negative
                            }
                            System.out.print("Invalid input! Exponent must be a non-negative integer: ");
                            scanner.nextLine(); // Clear invalid input
                        }
                        variables.put(variable, exponent);
                    }

                    polynomial.addTerm(coefficient, variables);
                    System.out.println("Term added successfully!");
                    break;

                case 2:
                    polynomial.displayPolynomial();
                    break;

                case 3:
                    int degree = polynomial.findDegree();
                    System.out.println("Degree of the polynomial: " + degree);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
