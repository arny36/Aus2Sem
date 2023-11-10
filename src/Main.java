import GUI.Gui;
import Tests.Tester;
import Tree.GPS;
import Tree.QuadTree;
import Tree.Shape;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Gui gui = new Gui();
                gui.setVisible(true);
                gui.pack();
            }
        });

//
//        int counter = 0;
////        int health = 0;
//
//
//        QuadTree tree = new QuadTree<>(100, 100, 10);
//        QuadTree newTree;
//        Tester tester = new Tester();
//        for (int i = 0; i < ; i++) {
////            tree = new QuadTree<>(100, 100, 10);
//            tester.testPropertyAll(tree, 10000, 100, 0, false);
//            newTree = tree.optimize();
//            if (tree.treeHealth() < newTree.treeHealth()) {
//
//                counter++;
//                System.out.println(counter+"----  >    "+tree.treeHealth() + " < " + newTree.treeHealth());
//            }
//
//        }
//    }
//        System.out.println(counter);
////
//            System.out.println(tree.treeHealth() + " Normálny strom");
//            System.out.println(newTree.treeHealth() + " Optimalizovaný strom");
//
////            System.out.println(counter+"++++++ >    "+tree.treeHealth() + " < " + newTree.treeHealth());
//        }
//        System.out.println(counter);

//

//        QuadTree temp = new QuadTree<>(100, 100, 10);
////
//        Tester tester = new Tester();
//////
//////        for (int i = 0; i < 1000; i++) {
////            tree = new QuadTree<>(100, 100, 10);
//////            System.out.println(i);
////        for (int i = 0; i < 1000; i++) {
////            System.out.println(i);
//            QuadTree tree = new QuadTree<>(100, 100, 10);
//            tester.testShapeAll(tree,1000,100,0,true,1);
//            if(!tester.testInsertDeleteSpeed(tree,100,true)) {
//                System.out.println("piči");
//            }
////        }
//            ArrayList<Shape> arr = new ArrayList();
//
////            GPS gp1 = new GPS(75, 0, 's', 'c');
////            GPS gp2 = new GPS(75, 2, 's', 'c');
////            Shape shape = new Shape<>(5, gp1, gp2, "cc");
//            arr = tree.search(0, 0, 100, 100);
////            System.out.println(arr.size());
////            for (int j = 0; j < 10; j++) {
////                System.out.println(j);
////                tree.delete(arr.get(j));
////            }
//        }

    }
}




