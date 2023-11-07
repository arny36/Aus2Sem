package Tree;

import java.util.ArrayList;

public class Shape<T extends IData<T>> extends LandShapes {
    private  ArrayList<Property> properties = new ArrayList<Property>();

    public Shape(int identifier,GPS first, GPS second, String notes) {
        super(identifier,first,second,notes);


    }
    public ArrayList<Property> getProperties() {
        return properties;
    }

}
