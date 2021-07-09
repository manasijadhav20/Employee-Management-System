
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

class Test extends JFrame
{
    Container c;
    JButton btnSave;
    ImageIcon imageIcon;
    JLabel label;

Test(){
    c = getContentPane();
    c.setLayout(null);
    btnSave = new JButton("Save");
btnSave.setFont(btnSave.getFont().deriveFont(18.0f));
btnSave.setBackground(Color.BLACK);
btnSave.setForeground(Color.WHITE);
btnSave.setBounds(300,0,100,30);
URL url = Test.class.getResource("mini.gif");
        imageIcon = new ImageIcon(url);
        label = new JLabel(imageIcon);
        //JFrame f = new JFrame("Animation");
        //c.add(label);

c.add(btnSave);
c.setImage(Image imageIcon);

setTitle("E. M. S");
setSize(1450,1400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
public static void main(String[] args){
      Test t = new Test();
    }
}