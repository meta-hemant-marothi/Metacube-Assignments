import java.util.Stack;

public class EfficientMolecularMassCalculator {

    public static int calculateMass(String formula) {
        final int MASS_C = 12, MASS_O = 16, MASS_H = 1;

        Stack<Integer> stack = new Stack<>();
        int mass = 0;

        for (int i = 0; i < formula.length(); i++) {
            char ch = formula.charAt(i);

            if (ch == 'C') {
                mass += MASS_C;
            } else if (ch == 'O') {
                mass += MASS_O;
            } else if (ch == 'H') {
                mass += MASS_H;
            } else if (Character.isDigit(ch)) {
                int multiplier = ch - '0';
                if (!stack.isEmpty()) {
                    mass += stack.pop() * (multiplier - 1);
                } else {
                    mass *= multiplier;
                }
            } else if (ch == '(') {
                stack.push(mass);
                mass = 0;
            } else if (ch == ')') {
                if (!stack.isEmpty()) {
                    int groupMass = mass;
                    mass = stack.pop() + groupMass;
                }
            }
        }

        return mass;
    }

    public static void main(String[] args) {
        String formula = "CHOC(CH3)3";
        System.out.println("Molecular mass of " + formula + ": " + calculateMass(formula));
    }
}