package myWindow;

import java.awt.*;           // Needed for FlowLayout.           //Note 1

import javax.swing.*;

////////////////////////////////////////////////////// class TinyWindow4
@SuppressWarnings("serial")
class TinyWindow4 extends JFrame {

    //============================================================= main
    public static void main(String[] args) {
        TinyWindow4 window = new TinyWindow4();
        window.setVisible(true);
    }

    //======================================================= constructor
    public TinyWindow4() {
        //... Create content panel, set layout
        JPanel content = new JPanel();                           //Note 2
        content.setLayout(new FlowLayout());   // Use FlowLayout //Note 3

        //... Add one label to the content pane.
        JLabel greeting = new JLabel("We come in peace.");       //Note 4
        content.add(greeting);                 // Add label      //Note 5

        //... Set window (JFrame) characteristics
        setContentPane(content);                                 //Note 6
        pack();                                // Do layout.     //Note 7
        setTitle("Tiny Window using JFrame Subclass");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);           // Center window.
    }
}