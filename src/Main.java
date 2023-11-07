import GUI.Gui;


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




    }
}




