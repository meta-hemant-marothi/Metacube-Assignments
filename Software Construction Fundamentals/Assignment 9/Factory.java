import java.util.List;

public class Factory {
    public static Shape createShape(ShapeType type, Point origin, List<Double> params) {
        switch (type) {
            case CIRCLE: 
                if (params.size() < 1) throw new IllegalArgumentException("Circle needs a radius.");
                return new Circle(origin, params.get(0));
            
            case SQUARE: 
                if (params.size() < 1) throw new IllegalArgumentException("Square needs a side length.");
                return new Square(origin, params.get(0));
            
            case RECTANGLE: 
                if (params.size() < 2) throw new IllegalArgumentException("Rectangle needs width and height.");
                return new Rectangle(origin, params.get(0), params.get(1));
            
            case TRIANGLE: 
                if (params.size() < 1) throw new IllegalArgumentException("Triangle needs a side length.");
                Point v1 = new Point(params.get(0), params.get(1));
                Point v2 = new Point(params.get(2), params.get(3));
                Point v3 = new Point(params.get(4), params.get(5));
                return new Triangle(v1, v2, v3);
            
            case REGULARPOLYGON: 
                if (params.size() < 2) throw new IllegalArgumentException("Polygon needs side length and number of sides.");
                return new RegularPolygon(origin, params.get(0), params.get(1).intValue());
                
            default: throw new IllegalArgumentException("Invalid shape type.");
        }
    }
}
