package Tree;

import Tree.Node;
import Tree.Shape;

public interface IData <T> {
    public int compareTo(Node<T> node);
    public boolean compareTo(double x1, double y1, double x2, double y2);
}
