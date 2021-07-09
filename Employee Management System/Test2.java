
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

class Test2{

public static void main(String[] args){

    
        URL url = Test2.class.getResource("mini.gif");
        ImageIcon imageIcon = new ImageIcon(url);
        JLabel label = new JLabel(imageIcon);
        JFrame f = new JFrame("Animation");
        f.getContentPane().add(label);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}