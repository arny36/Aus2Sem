package Tree;



import java.util.ArrayList;
import java.util.Stack;

public class QuadTree <T extends IData<T>> {
    private Node<T> root;
    private double width;
    private double height;

    private double maxDepth;

    public QuadTree(int width, int height, int maxDepth) {
        this.root = new Node<T>(0,0,width,height,1);
        this.width = width;
        this.height = height;
        this.maxDepth = maxDepth;
    }

    public double getMaxDepth() {
        return maxDepth;
    }

    public Node<T> getRoot() {
        return root;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }


    public void insert(T data) {
        Node<T> tempNode;
        tempNode = this.root;
        int decisionMaker;
        Stack queue = new Stack<T>();
        Stack newData = new Stack<T>();
        T dataQueue = null;
        queue.push(data);
        boolean stopper = true;

        while(stopper){
            if(dataQueue==null) {
                dataQueue =(T) queue.pop();
            }
            decisionMaker = dataQueue.compareTo(tempNode);
            if(decisionMaker != -1) {
                if (tempNode.getDepth() <= this.maxDepth) {
                    if (!tempNode.hasChildren() && tempNode.getData().size() == 0) {
                        tempNode.addData(dataQueue);
                        dataQueue=null;
                    }
                    else if (tempNode.getDepth()+1 > this.maxDepth){
                        tempNode.addData(dataQueue);
                        dataQueue=null;

                    } else {
                        if (tempNode.getData().size() > 0 && !tempNode.hasChildren() ) {
                            if (decisionMaker != 0) {
                                tempNode.setNw(new Node<T>(tempNode.getX1(), (tempNode.getY1() + tempNode.getY2() )/ 2, (tempNode.getX1() + tempNode.getX2()) / 2, tempNode.getY2(), tempNode.getDepth() + 1));
                                tempNode.setNe(new Node<T>(((tempNode.getX1() + tempNode.getX2())) / 2, (tempNode.getY1() + tempNode.getY2()) / 2, tempNode.getX2(), tempNode.getY2(), tempNode.getDepth() + 1));
                                tempNode.setSe(new Node<T>((tempNode.getX1() + tempNode.getX2()) / 2, tempNode.getY1(), tempNode.getX2(), (tempNode.getY1() + tempNode.getY2()) / 2, tempNode.getDepth() + 1));
                                tempNode.setSw(new Node<T>(tempNode.getX1(), tempNode.getY1(), (tempNode.getX1() + tempNode.getX2()) / 2, (tempNode.getY1() + tempNode.getY2()) / 2, tempNode.getDepth() + 1));
                                tempNode.getNw().setParent(tempNode);
                                tempNode.getNe().setParent(tempNode);
                                tempNode.getSe().setParent(tempNode);
                                tempNode.getSw().setParent(tempNode);
                            }

                            if (tempNode.getData().size() > 0) {
                                for (T item : tempNode.getData()) {
                                    if (item.compareTo(tempNode) != 0) {
                                        queue.push(item);
                                    }
                                    else {
                                        newData.push(item);
                                    }
                                }
                                tempNode.getData().clear();
                                while(newData.size()>0){

                                    tempNode.getData().add( (T)newData.pop());
                                }

                                newData.clear();

                            }
                        }
                        if (decisionMaker == 0) {
                            tempNode.addData(dataQueue);
                            dataQueue = null;
                        } else if (decisionMaker == 1) {
                            if (tempNode.getNw().getData().size() == 0 && !tempNode.getSw().hasChildren()) {
                                tempNode = tempNode.getNw();
                                tempNode.addData(dataQueue);
                                tempNode = tempNode.getParent();
                                dataQueue = null;
                            } else {
                                tempNode = tempNode.getNw();
                            }
                        } else if (decisionMaker == 2) {
                            if (tempNode.getNe().getData().size() == 0 && !tempNode.getSw().hasChildren()) {
                                tempNode = tempNode.getNe();
                                tempNode.addData(dataQueue);
                                tempNode = tempNode.getParent();
                                dataQueue = null;
                            } else {
                                tempNode = tempNode.getNe();
                            }
                        } else if (decisionMaker == 3) {
                            if (tempNode.getSe().getData().size() == 0 && !tempNode.getSw().hasChildren()) {
                                tempNode = tempNode.getSe();
                                tempNode.addData(dataQueue);
                                tempNode = tempNode.getParent();
                                dataQueue = null;
                            } else {
                                tempNode = tempNode.getSe();
                            }
                        } else if (decisionMaker == 4) {
                            if (tempNode.getSw().getData().size() == 0 && !tempNode.getSw().hasChildren()) {
                                tempNode = tempNode.getSw();
                                tempNode.addData(dataQueue);
                                tempNode = tempNode.getParent();
                                dataQueue = null;
                            } else {
                                tempNode = tempNode.getSw();
                            }
                        }
                    }
                } else {
                    if (tempNode.getParent().hasParent()) {
                        tempNode.getParent().addData(dataQueue);
                        dataQueue=null;
                    }
                }
            }

            if (dataQueue == null && queue.size() < 1) {
                stopper = false;
            }
        }
    }
    private int checkIfNodeIn(Node<T> node, double x1, double y1, double x2, double y2) {
        if (x1 >= node.getX1() && y1 >= ((node.getY1() + node.getY2()) / 2) &&
                x2 <= ((node.getX1() + node.getX2()) / 2) && y2 <= node.getY2()) {
            return 1;
        } else if (x1 >= ((node.getX1() + node.getX2()) / 2) && y1 >= ((node.getY1() + node.getY2()) / 2) &&
                x2 <= node.getX2() && y2 <= node.getY2()) {
            return 2;
        } else if (x1 >= ((node.getX1() + node.getX2()) / 2) && y1 >= node.getY1() &&
                x2 <= node.getX2() && y2 <= ((node.getY1() + node.getY2()) / 2)) {
            return 3;
        } else if (x1 >= node.getX1() && y1 >= node.getY1() &&
                x2 <= ((node.getX1() + node.getX2()) / 2) && y2 <= (node.getY1() + node.getY2()) / 2) {
            return 4;
        } else if (x1 >= node.getX1() && y1 >= node.getY1() &&
                x2 <= node.getX2() && y2 <= node.getY2()) {
            return 0;
        } else {
            return -1;
        }
    }


    public ArrayList<T> search(double x1, double y1 , double x2 , double y2) {
        double nX1,nY1,nX2,nY2;
        nX1= Math.min(x1,x2);
        nX2= Math.max(x1,x2);
        nY1= Math.min(y1,y2);
        nY2= Math.max(y1,y2);


        ArrayList finalList = new ArrayList<T>();
        Node<T> node = this.getRoot();
        Stack queue = new Stack<T>();
        queue.push(node);
        int comparator;
        Node<T> data;
        while(queue.size()>0){
            data = (Node<T>) queue.pop();
            comparator = checkIfNodeIn(data,nX1,nY1,nX2,nY2);
            if (data.hasChildren()){
                switch (comparator) {
                    case 0:
                        queue.push(data.getNw());
                        queue.push(data.getNe());
                        queue.push(data.getSe());
                        queue.push(data.getSw());
                        break;
                    case 1:
                        queue.push(data.getNw());
                        break;
                    case 2:
                        queue.push(data.getNe());
                        break;
                    case 3:
                        queue.push(data.getSe());
                        break;
                    case 4:
                        queue.push(data.getSw());
                        break;
                    case -1:
                        queue.push(data.getNw());
                        queue.push(data.getNe());
                        queue.push(data.getSe());
                        queue.push(data.getSw());
                        break;
                    default:
                        break;
                }

            }
            if (data.getData().size() > 0) {
                for (T item : data.getData()) {
                    if (item.compareTo(nX1,nY1,nX2,nY2)){
                        finalList.add(item);
                    }
                }
            }

        }
        return finalList;
    }

    public void delete(T data) {
        Node<T> node = this.getRoot();
        Stack queue = new Stack<T>();
        int comparator;
        boolean finder =  true;
        while (finder) {
            comparator = data.compareTo(node);
            for (T item : node.getData()) {
                if (data == item) {
                    finder=false;
                    break;
                }
            }
            if (finder) {
                switch (comparator) {
                    case 0:
                        System.out.println("neexistuje");
                        break;
                    case 1:
                        node = node.getNw();
                        break;
                    case 2:
                        node = node.getNe();
                        break;
                    case 3:
                        node = node.getSe();
                        break;
                    case 4:
                        node = node.getSw();
                        break;
                    default:
                        System.out.println("prvok sa nenašiel");
                        finder=false;
                        break;
                }
            }
        }
        node.getData().remove(data);
        queue.push(node);
        if (node.getData().size()<=1) {
            if (node.hasChildren()){
                while(queue.size()>0) {
                    node= (Node<T>) queue.pop();

                    if (node.canDeleteChildren()) {
                        node.removeChildren();
                        if (node.hasParent()) {
                            queue.push(node.getParent());
                        }
                    } else {
                        if (!node.hasData()) {
                            if (!node.hasChildrenChildren()) {
                                if (node.countChildrenWithData() == 1) {
                                    if (node.getNw().getData().size() == 1) {
                                        node.setData(node.getNw().getData());
                                        if (node.hasParent()) {
                                            queue.push(node.getParent());
                                        }
                                        node.removeChildren();
                                    } else if (node.getNe().getData().size() == 1) {
                                        node.setData(node.getNe().getData());
                                        if (node.hasParent()) {
                                            queue.push(node.getParent());
                                        }
                                        node.removeChildren();
                                    } else if (node.getSe().getData().size() == 1) {
                                        node.setData(node.getSe().getData());
                                        if (node.hasParent()) {
                                            queue.push(node.getParent());
                                        }
                                        node.removeChildren();
                                    } else if (node.getSw().getData().size() == 1) {
                                        node.setData(node.getSw().getData());
                                        if (node.hasParent()) {
                                            queue.push(node.getParent());
                                        }
                                        node.removeChildren();
                                    }
                                } else if (node.countChildrenWithData() == 0) {
                                    node.removeChildren();
                                }
                            }
                        }
                    }
                }
            } else {
                while(queue.size()>0) {
                    node= (Node<T>) queue.pop();
                    if (node.hasParent()) {
                        if (node.getParent().canDeleteChildren()) {
                            node.removeChildren();
                        } else {
                            if (!node.getParent().hasChildrenChildren()) {
                                if (node.getParent().countChildrenWithData() == 1 && !node.getParent().hasData()) {
                                    if (node.getParent().getNw().getData().size() == 1) {
                                        node.getParent().setData(node.getParent().getNw().getData());
                                        if (node.getParent().hasParent()) {
                                            queue.push(node.getParent());
                                        }
                                        node.getParent().removeChildren();
                                    } else if (node.getParent().getNe().getData().size() == 1) {
                                        node.getParent().setData(node.getParent().getNe().getData());
                                        if (node.getParent().hasParent()) {
                                            queue.push(node.getParent());
                                        }
                                        node.getParent().removeChildren();
                                    } else if (node.getParent().getSe().getData().size() == 1) {
                                        node.getParent().setData(node.getParent().getSe().getData());
                                        if (node.getParent().hasParent()) {
                                            queue.push(node.getParent());
                                        }
                                        node.getParent().removeChildren();
                                    } else if (node.getParent().getSw().getData().size() == 1) {
                                        node.getParent().setData(node.getParent().getSw().getData());
                                        if (node.getParent().hasParent()) {
                                            queue.push(node.getParent());
                                        }
                                        node.getParent().removeChildren();
                                    }
                                } else if (node.getParent().countChildrenWithData() == 0) {
                                    node.getParent().removeChildren();
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public double treeHealth() {
        double health=0;
        double nodes=0;
        Node<T> node = this.getRoot();
        Stack queue = new Stack<T>();
        queue.push(node);
        while(queue.size()>0){
            node = (Node<T>) queue.pop();
            if (node.hasChildren()) {
                queue.push(node.getNw());
                queue.push(node.getNe());
                queue.push(node.getSe());
                queue.push(node.getSw());
            }
            if (node.hasData()){
                health += 1.0 / node.getData().size();
            }
            nodes++;
        }
        return health/nodes;

    }

    public void changeDepthTree (double newDepth) {
        Node<T> node = this.getRoot();
        Stack queue = new Stack<T>();
        Stack nodes = new Stack<T>();
        T temp;
        queue.push(node);
        Node<T> root = this.root;
        if (newDepth!=this.maxDepth){
            if (newDepth < this.maxDepth) {
                while (queue.size()>0) {
                    node = (Node<T>) queue.pop();
                    if (node.hasChildren()) {
                        queue.push(node.getNw());
                        queue.push(node.getNe());
                        queue.push(node.getSe());
                        queue.push(node.getSw());

                    } else {
                        if (node.getDepth() > newDepth){
                            nodes.push(node);
                        }
                    }
                }
                while(nodes.size() > 0) {
                    node = (Node<T>) nodes.pop();
                    if (node.getDepth() != newDepth) {
                        if (!node.hasChildren()) {
                            if (node.hasData()) {
                                for (T data : node.getData()) {
                                    node.getParent().addData(data);
                                }
                            }
                        }
                        if (node.getParent().getNw() == node) {
                            node.getParent().setNw(null);
                        } else if (node.getParent().getNe() == node) {
                            node.getParent().setNe(null);
                        } else if (node.getParent().getSe() == node) {
                            node.getParent().setSe(null);
                        } else if (node.getParent().getSw() == node) {
                            node.getParent().setSw(null);
                        }

                        if (node.getParent().getDepth() != newDepth) {
                            nodes.push(node.getParent());
                        }
                        if (!node.hasChildren()) {

                            node.setParent(null);
                        }
                    }

                }
            } else {
                while (queue.size()>0) {
                    node = (Node<T>) queue.pop();
                    if (node.hasChildren()) {
                        queue.push(node.getNw());
                        queue.push(node.getNe());
                        queue.push(node.getSe());
                        queue.push(node.getSw());

                    } else {
                        if (node.getDepth() == this.maxDepth && node.hasData()){
                            nodes.push(node);
                        }
                    }
                }
                while (nodes.size()>0) {
                    node = (Node<T>) nodes.pop();
                    for (T data:node.getData()) {
                        queue.push(data);
                    }
                    this.maxDepth=newDepth;
                    node.getData().clear();
                    this.root = node;
                    while(queue.size()>0){
                        temp= (T) queue.pop();
                        this.insert(temp);
                    }
                }
            }
        }
        this.root= root;
        this.maxDepth=newDepth;
    }

}
