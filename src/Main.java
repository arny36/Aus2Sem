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



//        QuadTree tree = new QuadTree(100,100,6);
//        Tester tester =  new Tester();
//        if(tester.testInsert(tree, 1000)) {
//            System.out.println("ide");
//        }
//
//        if (tester.testSearch(tree,5000)) {
//            System.out.printf("ide");
//        }
//
//        if (tester.testInsertDelete(tree,70000,70,30)) {
//            System.out.println("ide");
//        }
    }
}




