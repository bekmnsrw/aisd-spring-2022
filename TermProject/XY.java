package QTree;

public class XY<T> {
    private double x;
    private double y;
    private T value;

    public XY(double x, double y, T value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public XY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value != null) {
            return value + ":(" + x + "; " + y + ")";
        } else {
            return "(" + x + "; " + y +")";
        }
    }
}