public class Triangle implements Shape {
    private final Point origin;
    private final double side;

    public Triangle(Point origin, double side) {
        this.origin = origin;
        this.side = side;
    }

    @Override
    public ShapeType getType(){
        return ShapeType.SQUARE;
    }

    @Override
    public double getArea() {
        return (Math.sqrt(3) / 4) * side * side;
    }

    @Override
    public double getPerimeter() {
        return 3 * side;
    }

    @Override
    public Point getOrigin() {
        return origin;
    }

    @Override
    public boolean isPointEnclosed(Point p) {
        double height = (Math.sqrt(3) / 2) * side;
        double x1 = origin.getX(), y1 = origin.getY();
        double x2 = x1 + side, y2 = y1;
        double x3 = x1 + side / 2, y3 = y1 + height;

        return isInsideTriangle(p, new Point(x1, y1), new Point(x2, y2), new Point(x3, y3));
    }

    private boolean isInsideTriangle(Point p, Point a, Point b, Point c) {
        double areaOrig = triangleArea(a, b, c);
        double area1 = triangleArea(p, b, c);
        double area2 = triangleArea(a, p, c);
        double area3 = triangleArea(a, b, p);
        return Math.abs(areaOrig - (area1 + area2 + area3)) < 1e-9;
    }

    private double triangleArea(Point a, Point b, Point c) {
        return Math.abs(a.getX() * (b.getY() - c.getY()) +
                        b.getX() * (c.getY() - a.getY()) +
                        c.getX() * (a.getY() - b.getY())) / 2.0;
    }

    @Override
    public String toString() {
        return "Triangle [Origin: " + origin + ", Side: " + side + "]";
    }
}
