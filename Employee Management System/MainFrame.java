/*
SQL> create table employee
  2  (
  3  eid int primary key,
  4  ename varchar(30) ,
  5  esalary float
  6  );

Table created.
*/
//C:\Program Files\Java\jdk-10.0.2\bin

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainFrame extends JFrame
{
Container c;
JButton btnAdd, btnView,btnUpdate,btnDelete;


MainFrame()
{
setContentPane(new JLabel(new ImageIcon("emp_img7.png")));

c = getContentPane();
c.setLayout(null);
btnAdd = new JButton("Add");
btnAdd.setFont(btnAdd.getFont().deriveFont(18.0f));
btnView = new JButton("View");
btnView.setFont(btnView.getFont().deriveFont(18.0f));
btnUpdate = new JButton("Update");
btnUpdate.setFont(btnUpdate.getFont().deriveFont(18.0f));
btnDelete = new JButton("Delete");
btnDelete.setFont(btnDelete.getFont().deriveFont(18.0f));

btnAdd.setBounds(525,250,130,45);
btnAdd.setBackground(Color.BLACK);
btnAdd.setForeground(Color.WHITE);
btnView.setBounds(700,250,130,45);
btnView.setBackground(Color.BLACK);
btnView.setForeground(Color.WHITE);
btnUpdate.setBounds(525,335,130,45);
btnUpdate.setBackground(Color.BLACK);
btnUpdate.setForeground(Color.WHITE);
btnDelete.setBounds(700,335,130,45);
btnDelete.setBackground(Color.BLACK);
btnDelete.setForeground(Color.WHITE);


c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnDelete);

//setContentPane(new JLabel(new ImageIcon("emp_img5.png")));

btnAdd.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
AddFrame a = new AddFrame();
dispose();
}
});

btnView.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
ViewFrame a = new ViewFrame();
dispose();
}
});

btnUpdate.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
UpdateFrame a = new UpdateFrame();
dispose();
}
});

btnDelete.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
DeleteFrame a = new DeleteFrame();
dispose();
}
});


setTitle("E. M. S");
setSize(1450,1400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}

public static void main(String args[])
{
MainFrame mf = new MainFrame();
}
}


