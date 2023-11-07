package Tree;

public class GPS {


    private double x;
    private double y;
    private char horizontal;
    private char vertical;

    public GPS(double x, double y, char horizontal , char vertical) {
        this.x = x;
        this.y = y;
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public char getHorizontal() {
        return horizontal;
    }

    public char getVertical() {
        return vertical;
    }
}
