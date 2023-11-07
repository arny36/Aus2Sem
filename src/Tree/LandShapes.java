package Tree;

public abstract class LandShapes  <T extends IData<T>> implements IData<T>{

    protected GPS first, second;
    protected String notes;
    protected int identifier;

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public LandShapes(int identifier, GPS first, GPS second, String notes) {
        this.first = new GPS(Math.min(first.getX(), second.getX()), Math.min(first.getY(), second.getY()), first.getHorizontal(), first.getVertical());
        this.second = new GPS(Math.max(first.getX(), second.getX()), Math.max(first.getY(), second.getY()), second.getHorizontal(), second.getVertical());
        this.notes = notes;
        this.identifier = identifier;
    }

    public GPS getFirst() {
        return first;
    }

    public GPS getSecond() {
        return second;
    }

    public String getNotes() {
        return notes;
    }

    public int getIdentifier() {
        return identifier;
    }



    @Override
    public int compareTo(Node<T> node) {
        if (this.first.getX() >= node.getX1() && this.first.getY() >= ((node.getY1() + node.getY2()) / 2) &&
                this.second.getX() <= ((node.getX1() + node.getX2()) / 2) && this.second.getY() <= (node.getY2())) {
            return 1;
        } else if (this.first.getX() >= ((node.getX1() + node.getX2()) / 2) && this.first.getY() >= ((node.getY1() + node.getY2()) / 2) &&
                this.second.getX() <= (node.getX2()) && this.second.getY() <= (node.getY2())) {
            return 2;
        } else if (this.first.getX() >= ((node.getX1() + node.getX2()) / 2) && this.first.getY() >= (node.getY1()) &&
                this.second.getX() <= (node.getX2()) && this.second.getY() <= ((node.getY1() + node.getY2()) / 2)) {
            return 3;
        } else if (this.first.getX() >= (node.getX1()) && this.first.getY() >= (node.getY1()) &&
                this.second.getX() <= ((node.getX1() + node.getX2()) / 2) && this.second.getY() <= (node.getY1() + node.getY2() )/ 2) {
            return 4;
        } else if (this.first.getX() >= node.getX1() && this.first.getY() >= node.getY1() &&
                this.second.getX() <= node.getX2() && this.second.getY() <= node.getY2()) {
            return 0;
        } else {
            return -1;
        }
    }


    @Override
    public boolean compareTo(double x1, double y1, double x2, double y2) {
        if ((x1 <= this.first.getX() && this.first.getX() <= x2) ||
                (x1 <= this.second.getX() && this.second.getX() <= x2) ||
                (this.first.getX() <= x1 && x1 <= this.second.getX()) ||
                (this.first.getX() <= x2 && x2 <= this.second.getX())) {
            if ((y1 <= this.first.getY() && this.first.getY() <= y2) ||
                    (y1 <= this.second.getY() && this.second.getY() <= y2) ||
                    (this.first.getY() <= y1 && y1 <= this.second.getY()) ||
                    (this.first.getY() <= y2 && y2 <= this.second.getY())) {
                return true;
            }
        }
        return false;
    }
}
