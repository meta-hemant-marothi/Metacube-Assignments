import java.util.HashMap;
import java.util.Map;

/**
 * Represents a multivariate polynomial where variables have unlimited exponents.
 * Terms are stored as a map where keys are variable-exponent pairs.
 */
public class MultiVariatePoly {
    private final Map<Map<String, Integer>, Integer> terms; // { {x=2, y=1} -> 5 }

    /**
     * Constructs an empty multivariate polynomial.
     */
    public MultiVariatePoly() {
        this.terms = new HashMap<>();
    }

    /**
     * Adds a term with given variable exponents and coefficient.
     * @param variables A map of variables to their exponents.
     * @param coefficient The coefficient of the term.
     */
    public void addTerm(Map<String, Integer> variables, int coefficient) {
        if (coefficient == 0) return;
        terms.put(variables, coefficient);
    }

    /**
     * Displays the polynomial.
     */
    public void displayPoly() {
        for (Map.Entry<Map<String, Integer>, Integer> entry : terms.entrySet()) {
            System.out.print(entry.getValue() + " ");
            for (Map.Entry<String, Integer> varExp : entry.getKey().entrySet()) {
                System.out.print(varExp.getKey() + "^" + varExp.getValue() + " ");
            }
            System.out.print("+ ");
        }
        System.out.println();
    }
}
