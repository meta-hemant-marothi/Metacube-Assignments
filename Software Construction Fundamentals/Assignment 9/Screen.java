import java.util.*;
import java.util.stream.Collectors;

public class Screen {
    private final List<ShapeEntry> shapes;
    private static long timestampCounter = 0; // To track order of addition
    private final Scanner scanner = new Scanner(System.in);

    public Screen() {
        this.shapes = new ArrayList<>();
    }

    // Internal class to store shape along with timestamp
    private static class ShapeEntry {
        Shape shape;
        long timestamp;

        ShapeEntry(Shape shape, long timestamp) {
            this.shape = shape;
            this.timestamp = timestamp;
        }
    }

    // Add a shape to the screen
    public void addShape(Shape shape) {
        shapes.add(new ShapeEntry(shape, ++timestampCounter));
        System.out.println("Shape added successfully.");
    }

    // Delete a specific shape instance from the screen
    public boolean deleteShape(Shape shape) {
        boolean removed = shapes.removeIf(entry -> entry.shape.equals(shape));
        System.out.println(removed ? "Shape deleted." : "Shape not found.");
        return removed;
    }

    // Delete all shapes of a specific type
    public void deleteShapesByType(ShapeType type) {
        boolean removed = shapes.removeIf(entry -> entry.shape.getType() == type);
        System.out.println(removed ? "All shapes of type " + type + " deleted." : "No shapes of type " + type + " found.");
    }

    // Get all shapes sorted by a given comparator
    public void displaySortedShapes(Comparator<Shape> comparator) {
        List<Shape> sortedShapes = shapes.stream()
                .map(entry -> entry.shape)
                .sorted(comparator)
                .collect(Collectors.toList());
        if (sortedShapes.isEmpty()) {
            System.out.println("No shapes to display.");
        } else {
            sortedShapes.forEach(System.out::println);
        }
    }

    // Get all shapes enclosing a given point
    public void findShapesEnclosingPoint(Point p) {
        List<Shape> enclosingShapes = shapes.stream()
                .map(entry -> entry.shape)
                .filter(shape ->  shape.isPointEnclosed(p))
                .collect(Collectors.toList());
        if (enclosingShapes.isEmpty()) {
            System.out.println("No shapes enclosing the given point.");
        } else {
            enclosingShapes.forEach(System.out::println);
        }
    }

    

    // Helper method to get timestamp of a shape
    private long getShapeTimestamp(Shape targetShape) {
        return shapes.stream()
                .filter(entry -> entry.shape.equals(targetShape))
                .map(entry -> entry.timestamp)
                .findFirst()
                .orElse(-1L);
    }

    // Comparators for sorting
    public static Comparator<Shape> sortByArea() {
        return Comparator.comparingDouble(Shape::getArea);
    }

    public static Comparator<Shape> sortByPerimeter() {
        return Comparator.comparingDouble(Shape::getPerimeter);
    }

    public static Comparator<Shape> sortByTimestamp() {
        return Comparator.comparingLong(shape -> timestampCounter);
    }

    public static Comparator<Shape> sortByOriginDistance() {
        return Comparator.comparingDouble(shape -> shape.getOrigin().distanceTo(new Point(0, 0)));
    }

    // Get user input for adding a shape
    private void addShapeFromUser() {
        System.out.println("Available shapes: " + Arrays.toString(ShapeType.values()));
        System.out.print("Enter shape type: ");
        String shapeTypeInput = scanner.next().toUpperCase();

        try {
            ShapeType type = ShapeType.valueOf(shapeTypeInput);
            System.out.print("Enter X coordinate of origin: ");
            double x = scanner.nextDouble();
            System.out.print("Enter Y coordinate of origin: ");
            double y = scanner.nextDouble();

            List<Double> params = new ArrayList<>();
            System.out.println("Enter required parameters (separated by space): ");
            switch (type) {
                case CIRCLE: {
                    System.out.print("Radius: ");
                    params.add(scanner.nextDouble());
                }
                break;
                case RECTANGLE: {
                    System.out.print("Width: ");
                    params.add(scanner.nextDouble());
                    if (type == ShapeType.RECTANGLE) {
                        System.out.print("Height: ");
                        params.add(scanner.nextDouble());
                    }
                }
                break;

                case SQUARE: {
                    System.out.print("Width: ");
                    params.add(scanner.nextDouble());
                    if (type == ShapeType.RECTANGLE) {
                        System.out.print("Height: ");
                        params.add(scanner.nextDouble());
                    }
                }
                break;
                case TRIANGLE: {
                    System.out.print("Side Length: ");
                    params.add(scanner.nextDouble());
                    System.out.print("Number of Sides: ");
                    params.add(scanner.nextDouble());
                }
                break;
                case REGULARPOLYGON: {
                    System.out.print("Side Length: ");
                    params.add(scanner.nextDouble());
                    System.out.print("Number of Sides: ");
                    params.add(scanner.nextDouble());
                }
                break;
            }
            Shape shape = Factory.createShape(type, new Point(x, y), params);
            addShape(shape);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid shape type! Try again.");
        }
    }

    // Get user input for deleting a shape
    private void deleteShapeFromUser() {
        System.out.println("Enter shape details to delete:");
        addShapeFromUser(); // Simulate getting shape input
    }

    // Get user input for deleting all shapes of a type
    private void deleteShapesByTypeFromUser() {
        System.out.println("Available shapes: " + Arrays.toString(ShapeType.values()));
        System.out.print("Enter shape type to delete: ");
        try {
            ShapeType type = ShapeType.valueOf(scanner.next().toUpperCase());
            deleteShapesByType(type);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid shape type! Try again.");
        }
    }

    // Get user input for displaying sorted shapes
    private void displayShapesSorted() {
        System.out.println("Sort by: 1. Area  2. Perimeter  3. Timestamp  4. Origin Distance");
        int option = getIntInput(scanner, 1, 4);
        Comparator<Shape> comparator = null;
        switch (option) {
            case 1: comparator = sortByArea(); break;
            case 2: comparator = sortByPerimeter(); break;
            case 3: comparator = sortByTimestamp(); break;
            case 4: comparator = sortByOriginDistance(); break;
        };
        if (comparator != null) {
            displaySortedShapes(comparator);
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private void findShapesEnclosingPointFromUser() {
        System.out.print("Enter X coordinate: ");
        double x = getDoubleInput(scanner);
        System.out.print("Enter Y coordinate: ");
        double y = getDoubleInput(scanner);
        findShapesEnclosingPoint(new Point(x, y));
    }

    /**
     * This Function is to get a valid integer input in the given range.
     * @param sc
     * @param min
     * @param max
     * @return valid integer.
     */
    public static int getIntInput(Scanner sc, int min, int max){
        int num;
        while(true){
            try{
                num = sc.nextInt();
                sc.nextLine();
                if(num >= min && num <= max)return num;
                else System.out.println("Enter a valid number between " + min + " & " + max);
            }catch(Exception e){
                System.out.println("Enter a valid number between " + min + " & " + max);
                sc.nextLine();
            }
        }
    }

    /**
     * This Function is to get a valid double input.
     * @param sc
     * @return valid double value.
     */
    public static double getDoubleInput(Scanner sc){
        while(true){
            if(sc.hasNextDouble()){
                return sc.nextDouble();
            }else{
                System.out.println("Enter a valid decimal number");
                sc.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        Screen screen = new Screen();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n------ Graphics Library Menu -------");
            System.out.println("1. Add Shape");
            System.out.println("2. Delete a Shape");
            System.out.println("3. Delete All Shapes of a Type");
            System.out.println("4. Display Shapes (Sorted)");
            System.out.println("5. Find Shapes Enclosing a Point");
            System.out.println("6. Exit");
            System.out.println("\n----------------------------------");
            System.out.print("Enter your choice: ");
            int choice = getIntInput(sc, 1, 6);

            switch (choice) {
                case 1: screen.addShapeFromUser(); break;
                case 2: screen.deleteShapeFromUser(); break;
                case 3: screen.deleteShapesByTypeFromUser(); break;
                case 4: screen.displayShapesSorted(); break;
                case 5: screen.findShapesEnclosingPointFromUser(); break;
                case 6: 
                    System.out.println("Exiting...");
                    return;
                default: System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
