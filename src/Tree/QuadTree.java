package Tree;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;



public class QuadTree <T extends IData<T>> {
    private Node<T> root;
    private double width;
    private double height;

    private double maxDepth;

    public QuadTree(double width, double height, double maxDepth) {
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
                            if (dataQueue.canCreateSons(tempNode)) {
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
                                    if (item.canCreateSons(tempNode)) {
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

                        if (!dataQueue.canCreateSons(tempNode)){
                            tempNode.addData(dataQueue);
                            dataQueue = null;
                        }
                        else if (dataQueue.compareTo(tempNode.getNw()) == 1) {
                            if (tempNode.getNw().getData().size() == 0 && !tempNode.getNw().hasChildren()) {
                                tempNode = tempNode.getNw();
                                tempNode.addData(dataQueue);
                                tempNode = tempNode.getParent();
                                dataQueue = null;
                            } else {
                                tempNode = tempNode.getNw();
                            }
                        } else if (dataQueue.compareTo(tempNode.getNe()) == 1) {
                            if (tempNode.getNe().getData().size() == 0 && !tempNode.getNe().hasChildren()) {
                                tempNode = tempNode.getNe();
                                tempNode.addData(dataQueue);
                                tempNode = tempNode.getParent();
                                dataQueue = null;
                            } else {
                                tempNode = tempNode.getNe();
                            }
                        } else if (dataQueue.compareTo(tempNode.getSe()) == 1) {
                            if (tempNode.getSe().getData().size() == 0 && !tempNode.getSe().hasChildren()) {
                                tempNode = tempNode.getSe();
                                tempNode.addData(dataQueue);
                                tempNode = tempNode.getParent();
                                dataQueue = null;
                            } else {
                                tempNode = tempNode.getSe();
                            }
                        } else if (dataQueue.compareTo(tempNode.getSw()) == 1) {
                            if (tempNode.getSw().getData().size() == 0 && !tempNode.getSw().hasChildren()) {
                                tempNode = tempNode.getSw();
                                tempNode.addData(dataQueue);
                                tempNode = tempNode.getParent();
                                dataQueue = null;
                            } else {
                                tempNode = tempNode.getSw();
                            }
                        } else {
                            tempNode.addData(dataQueue);
                            dataQueue = null;
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
        if (x1 >= node.getX1() && y1 >= node.getY1()
                && x2 <= node.getX2() && y2 <= node.getY2()) {
            return 1;
        } else if ((node.getX1() <= x1 && x1 <= node.getX2()) ||
                (node.getX1() <= x2 && x2 <= node.getX2()) ||
                (x1 <= node.getX1() && node.getX1() <= x2) ||
                (x1 <= node.getX2() && node.getX2() <= x2)
                        && (node.getY1() <= y1 && y1 <= node.getY2()) ||
                (node.getY1() <= y2 && y2 <= node.getY2()) ||
                (y1 <= node.getY1() && node.getY1() <= y2) ||
                (y1 <= node.getY2() && node.getY2() <= y2)) {
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
        Node<T> data;
        while(queue.size()>0){
            data = (Node<T>) queue.pop();

            if (data.hasChildren()){
                if (checkIfNodeIn(data.getNw(),x1,y1,x2,y2) != -1) {
                    queue.push(data.getNw());
                }
                if (checkIfNodeIn(data.getNe(),x1,y1,x2,y2) != -1) {
                    queue.push(data.getNe());
                }
                if (checkIfNodeIn(data.getSe(),x1,y1,x2,y2) != -1) {
                    queue.push(data.getSe());
                }
                if (checkIfNodeIn(data.getSw(),x1,y1,x2,y2) != -1) {
                    queue.push(data.getSw());
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

    private Node<T> findNode(T data){
        Node<T> node = this.getRoot();
        boolean finder =  true;
        while (finder) {

            if (node.hasChildren()){
                if (data.compareTo(node.getNw()) == 1) {
                    node=node.getNw();
                }
                else if (data.compareTo(node.getNe()) == 1) {
                    node=node.getNe();
                }
                else if (data.compareTo(node.getSe()) == 1) {
                    node=node.getSe();
                }
                else if (data.compareTo(node.getSw()) == 1) {
                    node=node.getSw();
                }
                else {
                    for (T item : node.getData()) {
                        if (data.equals(item)) {
                            finder=false;
                            break;
                        }
                    }

                }
            } else {
                for (T item : node.getData()) {
                    if (data.equals(item)) {
                        finder=false;
                    }
                }
            }
        }
        return node;

    }
    public void delete(T data) {
        Node<T> node;
        Stack queue = new Stack<T>();
        node = this.findNode(data);
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

        double nodes=0;
        Node<T> node = this.getRoot();
        LinkedList<Node<T>> list = new LinkedList<>();
        list.push(node);

        double allLands = this.countLands();
        double allNodes =this.countNodes();
        double allNodesWithData = allNodes-this.countFreeNodes();
        double average = allLands/allNodes;


        list.push(node);
        while(list.size()>0){
            node =list.pop();
            if (node.hasData()) {
                if (node.getData().size() >  average) {
                    nodes++;
                }

            }
            if (node.hasChildren()) {
                list.push(node.getNw());
                list.push(node.getNe());
                list.push(node.getSe());
                list.push(node.getSw());
            }
        }

        return (allNodesWithData-nodes);

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

    private ArrayList<T> findAllLands(){
        ArrayList<T> finalArray = new ArrayList<>();
        Node<T> node = this.getRoot();
        LinkedList<Node<T>> list = new LinkedList<>();
        list.push(node);
        while(list.size()>0){
            node =list.pop();
            if (node.hasData()) {
                for (T item:node.getData()) {
                    finalArray.add(item);
                }
            }
            if (node.hasChildren()) {
                list.push(node.getNw());
                list.push(node.getNe());
                list.push(node.getSe());
                list.push(node.getSw());
            }
        }
        return finalArray;
    }

    private int countLands() {
        int counter = 0;
        Node<T> node = this.getRoot();
        LinkedList<Node<T>> list = new LinkedList<>();

        list.push(node);
        while(list.size()>0){
            node =list.pop();
            if (node.hasData()) {
                for (T item:node.getData()) {
                    counter++;
                }
            }
            if (node.hasChildren()) {
                list.push(node.getNw());
                list.push(node.getNe());
                list.push(node.getSe());
                list.push(node.getSw());
            }
        }
        return counter;
    }

    private int countNodes() {
        int nodes=0;
        Node<T> node = this.getRoot();
        LinkedList<Node<T>> list = new LinkedList<>();

        list.push(node);
        while(list.size()>0){
            node =list.pop();
            nodes++;
            if (node.hasChildren()) {
                list.push(node.getNw());
                list.push(node.getNe());
                list.push(node.getSe());
                list.push(node.getSw());
            }
        }
        return nodes;

    }

    private int countFreeNodes() {
        int counter = 0;
        Node<T> node = this.getRoot();
        LinkedList<Node<T>> list = new LinkedList<>();
        list.push(node);
        while(list.size()>0){
            node =list.pop();
            if (!node.hasData()) {
              counter++;
            }
            if (node.hasChildren()) {
                list.push(node.getNw());
                list.push(node.getNe());
                list.push(node.getSe());
                list.push(node.getSw());
            }
        }
        return counter;
    }
    private int countLandsInMaxDepth() {
        int counter = 0;
        Node<T> node = this.getRoot();
        LinkedList<Node<T>> list = new LinkedList<>();
        list.push(node);
        while(list.size()>0){
            node =list.pop();
            if (node.hasData() && node.getDepth() == this.maxDepth) {
                for (T item:node.getData()) {
                        counter++;
                }
            }
            if (node.hasChildren()) {
                list.push(node.getNw());
                list.push(node.getNe());
                list.push(node.getSe());
                list.push(node.getSw());
            }
        }
        return counter;
    }


    public QuadTree<T> optimize(){



        QuadTree<T> newTree = new QuadTree<>(this.width,this.height,this.maxDepth);
        ArrayList<T> finalArray = findAllLands();
        for (T item:finalArray) {
            newTree.optimalizedInsert(item);

        }
        double newDepth;
        double freeNodes = newTree.countFreeNodes();
        double countLands = newTree.countLands();
        double countLandsMaxDepth = newTree.countLandsInMaxDepth();
        if (freeNodes > countLands) {
            newDepth = Math.ceil(Math.cbrt(freeNodes)) ;
            newTree.changeDepthTree(newDepth);
//            newTree.changeDepthTree(this.maxDepth--);
        } else if (countLandsMaxDepth > freeNodes) {
            newTree.changeDepthTree(Math.ceil(Math.cbrt(countLands)));
//            newTree.changeDepthTree(this.maxDepth++);
        }



        return newTree;

    }

    public void optimalizedInsert(T data) {
        Node<T> tempNode;
        tempNode = this.root;
        int decisionMaker;
        Stack queue = new Stack<T>();
        Stack newData = new Stack<T>();
        T dataQueue = null;
        queue.push(data);
        boolean stopper = true;

        double midY=0;
        double midX=0;


        if (this.root.hasChildren()){
            midX = this.root.getNe().getX1();
            midY = this.root.getNe().getY1();

        }

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
                        if (tempNode.getData().size() > 0 && !tempNode.hasChildren()) {
                            if (tempNode != this.root) {
                                if (dataQueue.canCreateSons(tempNode)) {
                                    tempNode.setNw(new Node<T>(tempNode.getX1(), (tempNode.getY1() + tempNode.getY2()) / 2, (tempNode.getX1() + tempNode.getX2()) / 2, tempNode.getY2(), tempNode.getDepth() + 1));
                                    tempNode.setNe(new Node<T>(((tempNode.getX1() + tempNode.getX2())) / 2, (tempNode.getY1() + tempNode.getY2()) / 2, tempNode.getX2(), tempNode.getY2(), tempNode.getDepth() + 1));
                                    tempNode.setSe(new Node<T>((tempNode.getX1() + tempNode.getX2()) / 2, tempNode.getY1(), tempNode.getX2(), (tempNode.getY1() + tempNode.getY2()) / 2, tempNode.getDepth() + 1));
                                    tempNode.setSw(new Node<T>(tempNode.getX1(), tempNode.getY1(), (tempNode.getX1() + tempNode.getX2()) / 2, (tempNode.getY1() + tempNode.getY2()) / 2, tempNode.getDepth() + 1));
                                    tempNode.getNw().setParent(tempNode);
                                    tempNode.getNe().setParent(tempNode);
                                    tempNode.getSe().setParent(tempNode);
                                    tempNode.getSw().setParent(tempNode);
                                }
                            } else {
                                midX = data.calculateX(tempNode);
                                midY = data.calculateY(tempNode);
                                if (dataQueue.canCreateSonsOptimize(tempNode,midX,midY)) {
                                    tempNode.setNw(new Node<T>(tempNode.getX1(), midY, midX, tempNode.getY2(), tempNode.getDepth() + 1));
                                    tempNode.setNe(new Node<T>(midX, midY, tempNode.getX2(), tempNode.getY2(), tempNode.getDepth() + 1));
                                    tempNode.setSe(new Node<T>(midX, tempNode.getY1(), tempNode.getX2(), midY, tempNode.getDepth() + 1));
                                    tempNode.setSw(new Node<T>(tempNode.getX1(), tempNode.getY1(), midX, midY, tempNode.getDepth() + 1));
                                    tempNode.getNw().setParent(tempNode);
                                    tempNode.getNe().setParent(tempNode);
                                    tempNode.getSe().setParent(tempNode);
                                    tempNode.getSw().setParent(tempNode);

                                }
                            }
                        }

                        if (tempNode.getData().size() > 0) {
                            if (tempNode != this.root) {
                                for (T item : tempNode.getData()) {
                                    if (item.canCreateSons(tempNode)) {
                                        queue.push(item);
                                    } else {
                                        newData.push(item);
                                    }
                                }
                            } else {
                                if (!this.root.hasChildren()){
                                    for (T item : tempNode.getData()) {
                                        if (item.canCreateSonsOptimize(tempNode, midX, midY)) {
                                            queue.push(item);
                                        } else {
                                            newData.push(item);
                                        }
                                    }
                                } else {
                                    for (T item : tempNode.getData()) {
                                        if (item.canCreateSonsOptimize(tempNode, midX, midY)) {
                                            queue.push(item);
                                        } else {
                                            newData.push(item);
                                        }
                                    }
                                }
                            }
                            tempNode.getData().clear();
                            while (newData.size() > 0) {
                                tempNode.getData().add((T) newData.pop());
                            }

                            newData.clear();
                        }


                        if (!dataQueue.canCreateSonsOptimize(tempNode,midX,midY) || !tempNode.hasChildren()) {
                            tempNode.addData(dataQueue);
                            dataQueue = null;
                        } else if (dataQueue.compareTo(tempNode.getNw()) == 1) {
                            if (tempNode.getNw().getData().size() == 0 && !tempNode.getNw().hasChildren()) {
                                tempNode = tempNode.getNw();
                                tempNode.addData(dataQueue);
                                tempNode = tempNode.getParent();
                                dataQueue = null;
                            } else {
                                tempNode = tempNode.getNw();
                            }
                        } else if (dataQueue.compareTo(tempNode.getNe()) == 1) {
                            if (tempNode.getNe().getData().size() == 0 && !tempNode.getNe().hasChildren()) {
                                tempNode = tempNode.getNe();
                                tempNode.addData(dataQueue);
                                tempNode = tempNode.getParent();
                                dataQueue = null;
                            } else {
                                tempNode = tempNode.getNe();
                            }
                        } else if (dataQueue.compareTo(tempNode.getSe()) == 1) {
                            if (tempNode.getSe().getData().size() == 0 && !tempNode.getSe().hasChildren()) {
                                tempNode = tempNode.getSe();
                                tempNode.addData(dataQueue);
                                tempNode = tempNode.getParent();
                                dataQueue = null;
                            } else {
                                tempNode = tempNode.getSe();
                            }
                        } else if (dataQueue.compareTo(tempNode.getSw()) == 1) {
                            if (tempNode.getSw().getData().size() == 0 && !tempNode.getSw().hasChildren()) {
                                tempNode = tempNode.getSw();
                                tempNode.addData(dataQueue);
                                tempNode = tempNode.getParent();
                                dataQueue = null;
                            } else {
                                tempNode = tempNode.getSw();
                            }
                        } else {
                            tempNode.addData(dataQueue);
                            dataQueue = null;
                        }
                    }
                }
            } else {
                if (tempNode.hasParent()) {
                    tempNode.getParent().addData(dataQueue);
                    dataQueue=null;
                }
            }
            if (dataQueue == null && queue.size() < 1) {
                stopper = false;
            }
        }
//
    }

}


