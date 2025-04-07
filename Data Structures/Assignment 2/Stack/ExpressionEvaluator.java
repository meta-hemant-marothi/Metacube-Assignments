package Stack;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExpressionEvaluator {

    private final Stack<Double> operandStack = new Stack<>();
    private final Stack<String> operatorStack = new Stack<>();

    public double evaluateInfixExpression(String expression, Map<String, Double> variables) {
        String[] tokens = expression.split("\\s+");

        for (String token : tokens) {
            if (isNumber(token)) {
                operandStack.push(Double.parseDouble(token));
            } else if (variables.containsKey(token)) {
                operandStack.push(variables.get(token));
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    evaluateTopOperation();
                }
                if (operatorStack.isEmpty()) {
                    throw new IllegalArgumentException("Unmatched parenthesis.");
                }
                operatorStack.pop(); // Pop '('
            } else if (isOperator(token)) {
                while (!operatorStack.isEmpty() &&
                        precedence(operatorStack.peek()) >= precedence(token)) {
                    evaluateTopOperation();
                }
                operatorStack.push(token);
            } else {
                throw new IllegalArgumentException("Invalid token: " + token);
            }
        }
        
        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek().equals("(")) {
                throw new IllegalArgumentException("Unmatched parenthesis.");
            }
            evaluateTopOperation();
        }

        if (operandStack.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression: No operands found.");
        }

        return operandStack.pop();
    }

    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(String token) {
        return "+-*/==!=<><=>=&&||!".contains(token);
    }

    private int precedence(String operator) {
        switch (operator) {
            case "||": return 1;
            case "&&": return 2;
            case "==": case "!=": return 3;
            case "<": case ">": case "<=": case ">=": return 4;
            case "+": case "-": return 5;
            case "*": case "/": return 6;
            case "!": return 7;
            default: return 0;
        }
    }

    private void evaluateTopOperation() {
        if (operatorStack.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression: Operator stack is empty.");
        }

        String operator = operatorStack.pop();

        if (operandStack.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression: Operand stack is empty.");
        }

        double rightOperand = operandStack.pop();

        if (operator.equals("!")) {
            operandStack.push(rightOperand == 0 ? 1.0 : 0.0);
        } else {
            if (operandStack.isEmpty()) {
                throw new IllegalArgumentException("Invalid expression: Missing left operand.");
            }
            double leftOperand = operandStack.pop();
            operandStack.push(applyOperator(leftOperand, rightOperand, operator));
        }
    }

    private double applyOperator(double leftOperand, double rightOperand, String operator) {
        switch (operator) {
            case "+": return leftOperand + rightOperand;
            case "-": return leftOperand - rightOperand;
            case "*": return leftOperand * rightOperand;
            case "/":
                if (rightOperand == 0) {
                    throw new ArithmeticException("Division by zero.");
                }
                return leftOperand / rightOperand;
            case "==": return leftOperand == rightOperand ? 1.0 : 0.0;
            case "!=": return leftOperand != rightOperand ? 1.0 : 0.0;
            case "<": return leftOperand < rightOperand ? 1.0 : 0.0;
            case ">": return leftOperand > rightOperand ? 1.0 : 0.0;
            case "<=": return leftOperand <= rightOperand ? 1.0 : 0.0;
            case ">=": return leftOperand >= rightOperand ? 1.0 : 0.0;
            case "&&": return (leftOperand != 0 && rightOperand != 0) ? 1.0 : 0.0;
            case "||": return (leftOperand != 0 || rightOperand != 0) ? 1.0 : 0.0;
            default: throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    public static class ExpressionEvaluatorUI {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            ExpressionEvaluator evaluator = new ExpressionEvaluator();
            Map<String, Double> variables = new HashMap<>();

            System.out.println("================== Welcome to the Expression Evaluator! ==================");
            System.out.println("Supported Operators: +, -, *, /, ==, !=, <, >, <=, >=, &&, ||, !");
            System.out.println("Enter expressions with variables or numbers, separated by spaces.");
            System.out.println("Type 'exit' to quit.");
            System.out.println("Type 'set var=value' to define variables (e.g., 'set x=5').");
            System.out.println("============================================================================");

            while (true) {
                evaluator.operandStack.clear();
                evaluator.operatorStack.clear();
                System.out.print("\nEnter your expression: ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Goodbye!");
                    break;
                } else if (input.startsWith("set ")) {
                    try {
                        String[] parts = input.substring(4).split("=");
                        String varName = parts[0].trim();
                        double value = Double.parseDouble(parts[1].trim());
                        variables.put(varName, value);
                        System.out.println("Variable '" + varName + "' set to " + value);
                    } catch (Exception e) {
                        System.out.println("Invalid variable syntax. Use 'set var=value'.");
                    }
                } else {
                    try {
                        double result = evaluator.evaluateInfixExpression(input, variables);
                        System.out.println("Result: " + result);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            }
            scanner.close();
        }
    }
}