public interface Shape {
    double getArea();
    double getPerimeter();
    Point getOrigin();
    boolean isPointEnclosed(Point p);
    ShapeType getType();
}