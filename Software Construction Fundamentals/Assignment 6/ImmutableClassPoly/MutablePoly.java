import java.util.HashMap;
import java.util.Map;

public class MutablePoly {
    private final Map<Integer, Integer> terms;

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
}
