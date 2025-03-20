public class RegularPolygon implements Shape {
    private final Point origin;
    private final double sideLength;
    private final int numSides;

    public RegularPolygon(Point origin, double sideLength, int numSides) {
        if (numSides < 3) throw new IllegalArgumentException("Polygon must have at least 3 sides.");
        this.origin = origin;
        this.sideLength = sideLength;
        this.numSides = numSides;
    }

    @Override
    public ShapeType getType(){
        return ShapeType.REGULARPOLYGON;
    }

    @Override
    public double getArea() {
        return (numSides * sideLength * sideLength) / (4 * Math.tan(Math.PI / numSides));
    }

    @Override
    public double getPerimeter() {
        return numSides * sideLength;
    }

    @Override
    public Point getOrigin() {
        return origin;
    }

    @Override
    public boolean isPointEnclosed(Point p) {
        return false;
    }

    @Override
    public String toString() {
        return "RegularPolygon [Origin: " + origin + ", Sides: " + numSides + ", Side Length: " + sideLength + "]";
    }
}
