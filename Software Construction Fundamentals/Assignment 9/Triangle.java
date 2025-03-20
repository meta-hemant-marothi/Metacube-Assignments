

public class Triangle implements Shape {
    private final Point origin, v2, v3;

    public Triangle(Point v1, Point v2, Point v3) {
        this.origin = v1;
        this.v2 = v2;
        this.v3 = v3;
    }

    @Override
    public double getArea() {
        return Math.abs((origin.getX() * (v2.getY() - v3.getY()) + 
                         v2.getX() * (v3.getY() - origin.getY()) + 
                         v3.getX() * (origin.getY() - v2.getY())) / 2.0);
    }

    @Override
    public double getPerimeter() {
        return origin.distanceTo(v2) + v2.distanceTo(v3) + v3.distanceTo(origin);
    }

    @Override
    public Point getOrigin() {
        return origin;
    }

    @Override
    public boolean isPointEnclosed(Point p) {
        double A = getArea();
        double A1 = new Triangle(p, v2, v3).getArea();
        double A2 = new Triangle(origin, p, v3).getArea();
        double A3 = new Triangle(origin, v2, p).getArea();
        return Math.abs(A - (A1 + A2 + A3)) < 1e-9;
    }

    @Override
    public ShapeType getType() {
        return ShapeType.TRIANGLE;
    }

    @Override
    public String toString() {
        return "Triangle[origin=" + origin + ", v2=" + v2 + ", v3=" + v3 + "]";
    }
}
