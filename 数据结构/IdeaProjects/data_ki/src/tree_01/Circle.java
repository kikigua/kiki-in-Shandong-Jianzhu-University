package tree_01;

public class Circle {
    private double radius;

    public Circle() {
    }

    public Circle(double r) {
        radius = r;
    }

    public void setRadius(double r) {
        radius = r;
    }

    public double getRadius() {
        return radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public String toString() {
        return String.valueOf(radius);
    }
}
