import java.util.*;
import java.util.stream.Collectors;

public class Screen {
    private final List<ShapeEntry> shapes;
    private static long timestampCounter = 0;
    private final Scanner scanner = new Scanner(System.in);

    public Screen() {
        this.shapes = new ArrayList<>();
    }

    private static class ShapeEntry {
        Shape shape;
        long timestamp;

        ShapeEntry(Shape shape, long timestamp) {
            this.shape = shape;
            this.timestamp = timestamp;
        }
    }

    public void addShape(Shape shape) {
        shapes.add(new ShapeEntry(shape, ++timestampCounter));
        System.out.println("Shape added successfully.");
    }

    private void addShapeFromUser() {
        System.out.println("Available shapes: " + Arrays.toString(ShapeType.values()));
        System.out.print("Enter shape type: ");
        String shapeTypeInput = scanner.next().toUpperCase();

        try {
            ShapeType type = ShapeType.valueOf(shapeTypeInput);
            System.out.print("Enter X coordinate of origin: ");
            double x = getDoubleInput(scanner);
            System.out.print("Enter Y coordinate of origin: ");
            double y = getDoubleInput(scanner);

            List<Double> params = new ArrayList<>();
            if (null != type) switch (type) {
                case CIRCLE:
                    System.out.print("Enter Radius: ");
                    params.add(getDoubleInput(scanner));
                    break;
                case RECTANGLE:
                    System.out.print("Enter Width: ");
                    params.add(getDoubleInput(scanner));
                    System.out.print("Enter Height: ");
                    params.add(getDoubleInput(scanner));
                    break;
                case SQUARE:
                    System.out.print("Enter Width: ");
                    params.add(getDoubleInput(scanner));
                    break;
                case TRIANGLE:
                    System.out.println("!! Treated the origin as x1, y1.");
                    System.out.print("Enter X2, Y2: ");
                    double x2 = getDoubleInput(scanner);
                    double y2 = getDoubleInput(scanner);
                    System.out.print("Enter X3, Y3: ");
                    double x3 = getDoubleInput(scanner);
                    double y3 = getDoubleInput(scanner);
                    params.addAll(Arrays.asList(new Double[]{x, y, x2, y2, x3, y3}));
                    break;
                case REGULARPOLYGON:
                    System.out.print("Enter Side Length: ");
                    params.add(getDoubleInput(scanner));
                    System.out.print("Enter Number of Sides: ");
                    params.add(getDoubleInput(scanner));
                    break;
                default:
                    break;
            }
            Shape shape = Factory.createShape(type, new Point(x, y), params);
            addShape(shape);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid shape type! Try again.");
        }
    }

    public boolean deleteShape(Shape shape) {
        boolean removed = shapes.removeIf(entry -> entry.shape.equals(shape));
        System.out.println(removed ? "Shape deleted." : "Shape not found.");
        return removed;
    }

    private void deleteShapeFromUser() {
        if (shapes.isEmpty()) {
            System.out.println("No shapes available to delete.");
            return;
        }

        System.out.println("Available shapes:");
        for (int i = 0; i < shapes.size(); i++) {
            System.out.println((i + 1) + ". " + shapes.get(i).shape);
        }

        System.out.print("Enter the number of the shape to delete: ");
        int index = getIntInput(scanner, 1, shapes.size());

        ShapeEntry selectedEntry = shapes.remove(index - 1);
        System.out.println("Deleted shape: " + selectedEntry.shape);
    }

    public void deleteShapesByType(ShapeType type) {
        boolean removed = shapes.removeIf(entry -> entry.shape.getType() == type);
        System.out.println(removed ? "All shapes of type " + type + " deleted." : "No shapes of type " + type + " found.");
    }

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

    public void displaySortedShapes(Comparator<ShapeEntry> comparator) {
        List<ShapeEntry> sortedEntries = shapes.stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        if (sortedEntries.isEmpty()) {
            System.out.println("No shapes to display.");
        } else {
            sortedEntries.forEach(entry -> System.out.println(entry.shape));
        }
    }

    private void displayShapesSorted() {
        System.out.println("Sort by: 1. Area  2. Perimeter  3. Timestamp  4. Origin Distance");
        int option = getIntInput(scanner, 1, 4);
        Comparator<ShapeEntry> comparator = null;

        switch (option) {
            case 1:
                comparator = Comparator.comparingDouble(entry -> entry.shape.getArea());
                break;
            case 2:
                comparator = Comparator.comparingDouble(entry -> entry.shape.getPerimeter());
                break;
            case 3:
                comparator = Comparator.comparingLong(entry -> entry.timestamp);
                break;
            case 4:
                comparator = Comparator.comparingDouble(entry -> entry.shape.getOrigin().distanceTo(new Point(0, 0)));
                break;
            default:
                break;
        }

        if (comparator != null) {
            displaySortedShapes(comparator);
        } else {
            System.out.println("Invalid choice!");
        }
    }

    public void findShapesEnclosingPoint(Point p) {
        List<Shape> enclosingShapes = shapes.stream()
                .map(entry -> entry.shape)
                .filter(shape -> shape.isPointEnclosed(p))
                .collect(Collectors.toList());

        if (enclosingShapes.isEmpty()) {
            System.out.println("No shapes enclosing the given point.");
        } else {
            enclosingShapes.forEach(System.out::println);
        }
    }

    private void findShapesEnclosingPointFromUser() {
        System.out.print("Enter X coordinate: ");
        double x = getDoubleInput(scanner);
        System.out.print("Enter Y coordinate: ");
        double y = getDoubleInput(scanner);
        findShapesEnclosingPoint(new Point(x, y));
    }

    public static int getIntInput(Scanner sc, int min, int max) {
        while (true) {
            try {
                int num = sc.nextInt();
                sc.nextLine();
                if (num >= min && num <= max) return num;
                System.out.println("Enter a valid number between " + min + " & " + max);
            } catch (Exception e) {
                System.out.println("Enter a valid number between " + min + " & " + max);
                sc.nextLine();
            }
        }
    }

    public static double getDoubleInput(Scanner sc) {
        while (true) {
            if (sc.hasNextDouble()) {
                return sc.nextDouble();
            } else {
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
            System.out.println("----------------------------------");
            System.out.print("Enter your choice: ");
            int choice = getIntInput(sc, 1, 6);

            switch (choice) {
                case 1:
                    screen.addShapeFromUser();
                    break;
                case 2:
                    screen.deleteShapeFromUser();
                    break;
                case 3:
                    screen.deleteShapesByTypeFromUser();
                    break;
                case 4:
                    screen.displayShapesSorted();
                    break;
                case 5:
                    screen.findShapesEnclosingPointFromUser();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
                    break;
            }
        }
    }
}
