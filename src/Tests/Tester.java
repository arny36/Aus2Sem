package Tests;

import Tree.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Tester {

    public boolean testInsert(QuadTree quadTree, int counter) {
        int x1, y1, x2, y2;
        Shape shape;
        Random rand = new Random();
        ArrayList<Shape> arrayList = new ArrayList();
        ArrayList<Shape> helpForSearch;

        GPS first, second;
        char h1, v1, h2, v2;
        for (int i = 0; i < counter; i++) {
            h1 = (rand.nextInt(100) < 50) ? 'W' : 'E';
            v1 = (rand.nextInt(100) < 50) ? 'N' : 'S';
            h2 = (rand.nextInt(100) < 50) ? 'W' : 'E';
            v2 = (rand.nextInt(100) < 50) ? 'N' : 'S';

            x1 = rand.nextInt((int) quadTree.getWidth() );
            y1 = rand.nextInt((int) quadTree.getHeight() );
            x2 = rand.nextInt((int) quadTree.getWidth() );
            y2 = rand.nextInt((int) quadTree.getHeight() );

            first = new GPS(x1, y1, h1, v1);
            second = new GPS(x2, y2, h2, v2);

            shape = new Shape(i, first, second, "blabla");

            quadTree.insert(shape);
            arrayList.add(shape);
        }
        helpForSearch = quadTree.search(0,0,quadTree.getWidth(),quadTree.getHeight());


        if (arrayList.size() == helpForSearch.size()) {
            return true;
        } else {
            return false;
        }
    }



    public boolean testInsertDelete(QuadTree quadTree, int counter, int insert , int delete) {
        int x1,y1,x2,y2;
        Shape shape;
        Random rand = new Random();
        ArrayList<Shape> shapes = new ArrayList();
        ArrayList<Shape> helpForSearch;
        GPS first,second;
        char h1,v1,h2,v2;
        int decisionMaker;

        for (int i = 0; i < counter; i++) {
            decisionMaker = rand.nextInt(100);
            if (decisionMaker <= insert) {
                h1 = (rand.nextInt(100) < 50) ? 'W' : 'E';
                v1 = (rand.nextInt(100) < 50) ? 'N' : 'S';
                h2 = (rand.nextInt(100) < 50) ? 'W' : 'E';
                v2 = (rand.nextInt(100) < 50) ? 'N' : 'S';

                x1 = rand.nextInt((int) quadTree.getWidth());
                y1 = rand.nextInt((int) quadTree.getHeight());
                x2 = rand.nextInt((int) quadTree.getWidth());
                y2 = rand.nextInt((int) quadTree.getHeight());

                first = new GPS(x1, y1, h1, v1);
                second = new GPS(x2, y2, h2, v2);
                shape = new Shape(i,first, second,"blabla");
                quadTree.insert(shape);
                shapes.add(shape);
            } else if (decisionMaker <= delete) {
                if (shapes.size() > 0) {
                    shape = (Shape) shapes.get(rand.nextInt(shapes.size()));
                    quadTree.delete(shape);
                    shapes.remove(shape);
                }
            } else {
                if (shapes.size() > 0) {
                    shape = (Shape) shapes.get(rand.nextInt(shapes.size()));
                    quadTree.search(shape.getFirst().getX(),shape.getFirst().getY(), shape.getSecond().getX(),shape.getSecond().getY());
                }
            }
        }

        helpForSearch = quadTree.search(0,0,quadTree.getWidth(),quadTree.getHeight());
        if (shapes.size() == helpForSearch.size()) {
            return true;
        } else {
            return false;
        }

    }

    public ArrayList<Shape> testShapeAll(QuadTree tree, double counter, double insert, double delete, boolean optimize, int seed) {


        int x1, y1, x2, y2, maxDistanceX, maxDistanceY;
        Shape shape;

        Random rand = new Random();
        ArrayList shapes = new ArrayList();
        GPS first, second;
        char h1, v1, h2, v2 ;
        double decisionMaker;

        for (int i = 0; i < counter; i++) {
            decisionMaker = rand.nextInt(100);
            h1 = (rand.nextInt(100) < 50) ? 'W' : 'E';
            v1 = (rand.nextInt(100) < 50) ? 'N' : 'S';
            h2 = (rand.nextInt(100) < 50) ? 'W' : 'E';
            v2 = (rand.nextInt(100) < 50) ? 'N' : 'S';

            x1 = rand.nextInt((int) tree.getWidth());
            y1 = rand.nextInt((int) tree.getHeight());
            maxDistanceX = (int) Math.round(tree.getWidth()/3);
            maxDistanceY = (int) Math.round(tree.getHeight()/3);
            x2 = (int) Math.max(0, Math.min(tree.getWidth(), x1 + rand.nextInt(2 * maxDistanceX ) - maxDistanceX));
            y2 = (int) Math.max(0, Math.min(tree.getHeight(), y1 + rand.nextInt(2 * maxDistanceY ) - maxDistanceY));

            first = new GPS(x1, y1, h1, v1);
            second = new GPS(x2, y2, h2, v2);
            shape = new Shape(i,first, second,"blabla");
            if (decisionMaker < insert) {// insertik
                if (optimize){
                    tree.optimalizedInsert(shape);
                }else {
                    tree.insert(shape);
                }
                shapes.add(shape);
            } else if (decisionMaker <= delete) {
                if (optimize){
                    tree.optimalizedInsert(shape);
                }else {
                    tree.insert(shape);
                }
                tree.delete(shape);


            } else {
                if (shapes.size() > 0) {
                    shape = (Shape) shapes.get(rand.nextInt(shapes.size()));
                    tree.search(shape.getFirst().getX(),shape.getFirst().getY(), shape.getSecond().getX(),shape.getSecond().getY());
                }


            }
        }
        return shapes;
    }

    public ArrayList<Property> testPropertyAll(QuadTree tree, double counter, double insert, double delete, boolean optimize) {


        int x1, y1, x2, y2 , maxDistanceX, maxDistanceY;
        Property property;
        Random rand = new Random();
        ArrayList properties = new ArrayList();
        GPS first, second;
        char h1, v1, h2, v2;
        double decisionMaker;


        for (int i = 0; i < counter; i++) {
            decisionMaker = rand.nextInt(100);
            h1 = (rand.nextInt(100) < 50) ? 'W' : 'E';
            v1 = (rand.nextInt(100) < 50) ? 'N' : 'S';
            h2 = (rand.nextInt(100) < 50) ? 'W' : 'E';
            v2 = (rand.nextInt(100) < 50) ? 'N' : 'S';

            x1 = rand.nextInt((int) tree.getWidth());
            y1 = rand.nextInt((int) tree.getHeight());
            maxDistanceX = (int) Math.round(tree.getWidth()/3);
            maxDistanceY = (int) Math.round(tree.getHeight()/3);
            x2 = (int) Math.max(0, Math.min(tree.getWidth(), x1 + rand.nextInt(2 * maxDistanceX) - maxDistanceX));
            y2 = (int) Math.max(0, Math.min(tree.getHeight(), y1 + rand.nextInt(2 * maxDistanceY) - maxDistanceY));

            first = new GPS(x1, y1, h1, v1);
            second = new GPS(x2, y2, h2, v2);
            property = new Property(i,first, second,"blabla");
            if (decisionMaker <= insert) {
                if (optimize){
                    tree.optimalizedInsert(property);
                } else {
                    tree.insert(property);
                }
                properties.add(property);
            } else if (decisionMaker <= delete) {
                if (optimize){
                    tree.optimalizedInsert(property);
                } else {
                    tree.insert(property);
                }
                tree.delete(property);
                properties.remove(property);

            } else {
                if (properties.size() > 0) {
                    property = (Property) properties.get(rand.nextInt(properties.size()));
                    tree.search(property.getFirst().getX(),property.getFirst().getY(), property.getSecond().getX(),property.getSecond().getY());
                }


            }
        }
        return properties;
    }
    public boolean testSearch(QuadTree tree, double counter) {
        int x1, y1, x2, y2;
        Shape shape;

        ArrayList<Shape> shapes = new ArrayList();
        ArrayList<Shape> helpForSearch;
        GPS first, second;
        char h1, v1, h2, v2;
        Random rand = new Random();


        for (int i = 0; i < counter; i++) {

            h1 = (rand.nextInt(100) < 50) ? 'W' : 'E';
            v1 = (rand.nextInt(100) < 50) ? 'N' : 'S';
            h2 = (rand.nextInt(100) < 50) ? 'W' : 'E';
            v2 = (rand.nextInt(100) < 50) ? 'N' : 'S';

            x1 = rand.nextInt((int) tree.getWidth());
            y1 = rand.nextInt((int) tree.getHeight());
            x2 = rand.nextInt((int) tree.getWidth());
            y2 = rand.nextInt((int) tree.getHeight());

            first = new GPS(x1, y1, h1, v1);
            second = new GPS(x2, y2, h2, v2);
            shape = new Shape(i, first, second, "blabla");
            tree.insert(shape);
            shapes.add(shape);
        }

        int nX1,nY1,nX2,nY2;
        nX1 = rand.nextInt((int) tree.getWidth());
        nX2 = rand.nextInt((int) tree.getHeight());
        nY1 = rand.nextInt((int) tree.getWidth());
        nY2 = rand.nextInt((int) tree.getHeight());

        x1 = Math.min(nX1,nX2);
        x2 = Math.max(nX1,nX2);
        y1= Math.min(nY1,nY2);
        y2= Math.max(nY1,nY2);


        double correctFound = 0;
        helpForSearch = tree.search(x1, y1, x2, y2);
        ArrayList<Shape> test= new ArrayList<>();
        for (Shape s : shapes) {
            if ((x1 <= s.getFirst().getX() && s.getFirst().getX() <= x2) ||
                    (x1 <= s.getSecond().getX() && s.getSecond().getX() <= x2) ||
                    (s.getFirst().getX() <= x1 && x1 <= s.getSecond().getX()) ||
                    (s.getFirst().getX() <= x2 && x2 <= s.getSecond().getX())) {
                if ((y1 <= s.getFirst().getY() && s.getFirst().getY() <= y2) ||
                        (y1 <= s.getSecond().getY() && s.getSecond().getY() <= y2) ||
                        (s.getFirst().getY() <= y1 && y1 <= s.getSecond().getY()) ||
                        (s.getFirst().getY() <= y2 && y2 <= s.getSecond().getY())) {
                    correctFound++;
                    test.add(s);
                }
            }
        }


        if (correctFound == helpForSearch.size()) {
            return true;
        }
        return false;
    }



    public boolean testInsertDeleteSpeed(QuadTree tree, double counter, boolean isOptimized) {
        QuadTree tempTree=tree;
        boolean isWorking = false;
        char h1, v1, h2, v2;
        Shape shape;
        Random rand = new Random();
        GPS first, second;
        int x1, y1, x2, y2 , maxDistanceX, maxDistanceY;
//        int x1N, x2N, y1N, y2N;

        for (int i = 0; i < counter; i++) {

            h1 = (rand.nextInt(100) < 50) ? 'W' : 'E';
            v1 = (rand.nextInt(100) < 50) ? 'N' : 'S';
            h2 = (rand.nextInt(100) < 50) ? 'W' : 'E';
            v2 = (rand.nextInt(100) < 50) ? 'N' : 'S';

            x1 = rand.nextInt((int) tree.getWidth());
            y1 = rand.nextInt((int) tree.getHeight());
            maxDistanceX = (int) Math.round(tree.getWidth()/3);
            maxDistanceY = (int) Math.round(tree.getHeight()/3);
            x2 = (int) Math.max(0, Math.min(tree.getWidth(), x1 + rand.nextInt(2 * maxDistanceX ) - maxDistanceX));
            y2 = (int) Math.max(0, Math.min(tree.getHeight(), y1 + rand.nextInt(2 * maxDistanceY ) - maxDistanceY));



            first = new GPS(x1, y1, h1, v1);
            second = new GPS(x2, y2, h2, v2);
            shape = new Shape(i, first, second, "blabla");

            if (isOptimized) {

                tree.optimalizedInsert(shape);
            }else
            {
                tree.insert(shape);
            }

            tree.delete(shape);
        }

        ArrayList array;
        array = tree.search(0,0,100,100);
        if (tempTree.equals(tree)){
            isWorking=true;
        }
        return isWorking;
    }



}
