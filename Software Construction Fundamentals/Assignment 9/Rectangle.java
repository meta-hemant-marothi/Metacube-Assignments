public class Rectangle implements Shape {
    private final Point origin;
    private final double width, height;

    public Rectangle(Point origin, double width, double height) {
        this.origin = origin;
        this.width = width;
        this.height = height;
    }

    @Override
    public ShapeType getType(){
        return ShapeType.RECTANGLE;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public Point getOrigin() {
        return origin;
    }

    @Override
    public boolean isPointEnclosed(Point p) {
        return p.getX() >= origin.getX() && p.getX() <= origin.getX() + width &&
               p.getY() >= origin.getY() && p.getY() <= origin.getY() + height;
    }

    
    @Override
    public String toString() {
        return "Rectangle [Origin: " + origin + ", Width: " + width + ", Height: " + height + "]";
    }
}
