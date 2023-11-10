package Tree;

import java.util.ArrayList;

public class Property<T extends IData<T>> extends LandShapes {

    private ArrayList<Shape> shapes = new ArrayList();


    public Property(int identifier,GPS first, GPS second, String notes) {
        super(identifier,first,second,notes);
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }


}
