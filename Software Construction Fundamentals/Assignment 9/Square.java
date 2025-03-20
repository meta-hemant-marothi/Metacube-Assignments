public class Square implements Shape {
    private final Point origin;
    private final double width;

    public Square(Point origin, double width) {
        this.origin = origin;
        this.width = width;
    }

    @Override
    public ShapeType getType(){
        return ShapeType.RECTANGLE;
    }

    @Override
    public double getArea() {
        return width * width;
    }

    @Override
    public double getPerimeter() {
        return 4 * width;
    }

    @Override
    public Point getOrigin() {
        return origin;
    }

    @Override
    public boolean isPointEnclosed(Point p) {
        return p.getX() >= origin.getX() && p.getX() <= origin.getX() + width &&
               p.getY() >= origin.getY() && p.getY() <= origin.getY() + width;
    }

    
    @Override
    public String toString() {
        return "Rectangle [Origin: " + origin + ", Width: " + width +  "]";
    }
}
