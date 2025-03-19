public class Square extends Rectangle {
    public Square(Point origin, double sideLength) {
        super(origin, sideLength, sideLength);
    }

    @Override
    public ShapeType getType(){
        return ShapeType.SQUARE;
    }

    @Override
    public String toString() {
        return "Square [Origin: " + getOrigin() + ", Side: " + getArea() / getPerimeter() + "]";
    }
}
