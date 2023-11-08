import GUI.Gui;
import Tests.Tester;
import Tree.QuadTree;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Gui gui = new Gui();
                gui.setVisible(true);
                gui.pack();
            }
        });
//        int counter = 0;
//        int health = 0;
//        for (int i = 0; i <1000 ; i++) {
//
//
//            QuadTree tree = new QuadTree<>(100,100,5);
//            QuadTree newTree;
//            Tester tester = new Tester();
//
//            tester.testShapeAll(tree,9,100,0);
//            newTree = tree.optimize();
//
//            if (tree.treeHealth() <= newTree.treeHealth()) {
//                for (Object item: newTree.getRoot().getData()) {
//                    counter++;
//                }
//                health++;
//                System.out.println(counter+"----  >    "+tree.treeHealth() + " < " + newTree.treeHealth());
//                counter=0;
//            }
//        }
//        System.out.println(health);
//
//
//
//
    }
}




