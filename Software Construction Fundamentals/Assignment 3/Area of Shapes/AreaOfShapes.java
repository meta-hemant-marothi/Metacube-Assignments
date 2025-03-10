
import java.util.Scanner;

public class AreaOfShapes {

    /**
     * Calculates the area of triangle on given base and height.
     *
     * @param base
     * @param height
     * @return Area of Triangle.
     * @throws ArithmeticException If base or height are negative.
     */
    public double calculateAreaOfTriangle(double base, double height) {
        if (base < 0 || height < 0) {
            throw new ArithmeticException("The base or height of a triangle should not be negative");
        }
        double areaOfTriangle = 0.5 * base * height;
        return areaOfTriangle;
    }

    /**
     * Calculate the area of rectangle on given length and breadth.
     *
     * @param length
     * @param breadth
     * @return Area of rectangle.
     * @throws ArithmeticException If length or breadth are negative.
     */
    public double calculateAreaOfRectangle(double length, double breadth) {
        if (length < 0 || breadth < 0) {
            throw new ArithmeticException("The length or breadth of a rectangle should not be negative");
        }
        double areaOfRectangle = length * breadth;
        return areaOfRectangle;
    }

    /**
     * Calculates the area of square on given length of the side.
     *
     * @param side
     * @return Area of square.
     * @throws ArithmeticException If length of side is negative.
     */
    public double calculateAreaOfSquare(double side) {
        if (side < 0) {
            throw new ArithmeticException("The length of side of a square should not be negative");
        }
        double areaOfSquare = side * side;
        return areaOfSquare;
    }

    /**
     * Calculates the area of circle on given radius.
     *
     * @param radius
     * @return Area of circle.
     * @throws ArithmeticException If radius is negative.
     */
    public double calculateAreaOfCircle(double radius) {
        if (radius < 0) {
            throw new ArithmeticException("The length of radius of a circle should not be negative");
        }
        double areaOfCircle = Math.PI * radius * radius;
        return areaOfCircle;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AreaOfShapes obj = new AreaOfShapes();
        boolean flag = true;
        while (flag) {
            System.out.println("====== Welcome to Shape Area Calculator ======");
            System.out.println("1. Calculate area of triangle.");
            System.out.println("2. Calculate area of rectangle.");
            System.out.println("3. Calculate area of square.");
            System.out.println("4. Calculate area of circle.");
            System.out.println("5. Exit the program.");
            System.out.println("========================================");
            System.out.print("Enter the Option number you want to choose: ");
            int choice = sc.nextInt();
            double result = 0;

            switch (choice) {
                case 1:
                    double base,
                     height;
                    System.out.print("Enter the base of the triangle: ");
                    base = sc.nextDouble();
                    System.out.print("Enter the base of the triangle: ");
                    height = sc.nextDouble();
                    result = obj.calculateAreaOfTriangle(base, height);
                    break;
                case 2:
                    double length,
                     breadth;
                    System.out.print("Enter the length of the rectangle: ");
                    length = sc.nextDouble();
                    System.out.print("Enter the breadth of the rectangle: ");
                    breadth = sc.nextDouble();
                    result = obj.calculateAreaOfRectangle(length, breadth);
                    break;
                case 3:
                    double side;
                    System.out.println("Enter the length of side of square: ");
                    side = sc.nextDouble();
                    result = obj.calculateAreaOfSquare(side);
                    break;
                case 4:
                    double radius;
                    System.out.println("Enter the radius of circle: ");
                    radius = sc.nextDouble();
                    result = obj.calculateAreaOfCircle(radius);
                    break;
                default:
                    flag = false;
            }
            System.out.println("Result = " + result);
            System.out.println("\n\n");
        }
    }
}
