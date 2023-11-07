package Tree;

import java.util.ArrayList;

public class Node<T> {
    private double x1,x2,y1,y2;
    private ArrayList<T> data;
    private int depth;
    private Node<T> nw, ne, sw, se , parent;
    public Node(double x1, double y1, double x2, double y2, int depth) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.depth = depth;
        this.data = new ArrayList<T>();
        this.nw = this.ne = this.se = this.sw =this.parent = null;
    }
    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getY1() {
        return y1;
    }

    public double getY2() {
        return y2;
    }

    public ArrayList<T> getData() {
        return data;
    }

    public void addData(T data) {
        this.data.add(data);
    }

    public int getDepth() {
        return depth;
    }

    public boolean hasParent() { return this.parent != null; }

    public Node<T> getNw() {
        return nw;
    }

    public void setNw(Node<T> nw) {
        this.nw = nw;
    }

    public Node<T> getNe() {
        return ne;
    }

    public void setNe(Node<T> ne) {
        this.ne = ne;
    }

    public Node<T> getSw() {
        return sw;
    }

    public void setSw(Node<T> sw) {
        this.sw = sw;
    }

    public Node<T> getSe() {
        return se;
    }

    public void setSe(Node<T> se) {
        this.se = se;
    }

    public boolean hasData() {
        return this.getData().size()>0;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void removeChildren() {
        this.sw = null;
        this.se = null;
        this.nw = null;
        this.ne = null;

    }

    public boolean hasChildren() {return this.sw != null || this.se != null || this.nw != null || this.ne != null;}
    public boolean hasChildrenChildren() {
        if (this.hasChildren()) {
            return this.sw.hasChildren() || this.se.hasChildren() || this.nw.hasChildren() || this.ne.hasChildren();
        }
        return false;
    }

    public boolean canDeleteChildren() {
        if (this.countChildrenWithData() >0 || this.hasChildrenChildren()) {
            return false;
        }
        return true;
    }


    public int countChildrenWithData(){
        int counter= 0;
        if (this.sw.getData().size() > 0){
            counter++;
        }
        if (this.se.getData().size() > 0){
            counter++;
        }
        if (this.nw.getData().size() > 0){
            counter++;
        }
        if (this.ne.getData().size() > 0){
            counter++;
        }


        return counter;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public void setData(ArrayList<T> data) {
        for (T item:data
             ) {
            this.data.add(item);
        }
    }











}
