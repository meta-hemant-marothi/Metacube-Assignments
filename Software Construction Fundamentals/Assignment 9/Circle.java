public class Circle implements Shape {
    private final Point center;
    private final double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public ShapeType getType(){
        return ShapeType.CIRCLE;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public Point getOrigin() {
        return center;
    }

    @Override
    public boolean isPointEnclosed(Point p) {
        return center.distanceTo(p) <= radius;
    }

    @Override
    public String toString() {
        return "Circle [Center: " + center + ", Radius: " + radius + "]";
    }
}
