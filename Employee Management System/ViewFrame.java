import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;
import java.awt.Color;

class ViewFrame extends JFrame
{
Container c;
TextArea ta;
JButton btnBack;

ViewFrame()
{
c = getContentPane();
c.setBackground(Color.YELLOW); 
c.setLayout(null);
ta = new TextArea();
btnBack = new JButton("Back");
btnBack.setFont(btnBack.getFont().deriveFont(18.0f));
btnBack.setBackground(Color.BLACK);
btnBack.setForeground(Color.WHITE);


btnBack.setBounds(615,400,100,30);
ta.setBounds(435,100,450,250);
c.add(ta);
c.add(btnBack);


btnBack.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
MainFrame a = new MainFrame();
dispose();
}
});


setTitle("View E");
setSize(1300,1400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact = cfg.buildSessionFactory();

Session session = sfact.openSession();

Transaction t = null;

try{
//System.out.println("begin");

java.util.List<Employee> emp = new ArrayList<>();
emp = session.createQuery("from Employee").list();
for (Employee e:emp){
	ta.append(e.getEid()+" "+e.getEname()+" "+e.getEsalary() +"\n");
}
//System.out.println("end");
}
catch(Exception e){
JOptionPane.showMessageDialog(new JDialog(),e);
}
finally{
session.close();
}


}
}
