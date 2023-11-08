package Tree;

import Tree.Node;
import Tree.Shape;

public interface IData <T> {
     int compareTo(Node<T> node);
     boolean compareTo(double x1, double y1, double x2, double y2);

     boolean canCreateSons(Node<T> node);
     boolean canCreateSonsOptimize(Node<T> node);

     double calculateX(Node<T> node);
     double calculateY(Node<T> node);



}
