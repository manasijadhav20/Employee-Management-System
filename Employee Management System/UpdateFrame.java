import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.awt.Color;

class UpdateFrame extends JFrame
{
Container c;
JLabel lblEid, lblEname, lblEsal;
JTextField txtEid, txtEname, txtEsal;
JButton btnSave, btnBack;
int eid;
String ename;
float esalary;
AudioClip click;

UpdateFrame()
{
c = getContentPane();
c.setBackground(Color.YELLOW); 
c.setLayout(null);
lblEid = new JLabel("Enter eid ");
lblEname = new JLabel("Enter name ");
lblEsal = new JLabel("Enter salary ");		
txtEid = new JTextField();
txtEname = new JTextField();
txtEsal = new JTextField();
btnSave = new JButton("Save");
btnSave.setFont(btnSave.getFont().deriveFont(18.0f));
btnSave.setBackground(Color.BLACK);
btnSave.setForeground(Color.WHITE);
btnBack = new JButton("Back");
btnBack.setFont(btnBack.getFont().deriveFont(18.0f));
btnBack.setBackground(Color.BLACK);
btnBack.setForeground(Color.WHITE);

lblEid.setBounds(450,100,100,30);
txtEid.setBounds(600,100,150,30);
lblEname.setBounds(450,175,100,30);
txtEname.setBounds(600,175,150,30);
lblEsal.setBounds(450,250,100,30);
txtEsal.setBounds(600,250,150,30);
btnSave.setBounds(475,325,100,30);
btnBack.setBounds(625,325,100,30);


c.add(lblEid);
c.add(txtEid);
c.add(lblEname);
c.add(txtEname);
c.add(lblEsal);
c.add(txtEsal);
c.add(btnSave);
c.add(btnBack);

btnBack.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
MainFrame a = new MainFrame();
dispose();
}
});

btnSave.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact = cfg.buildSessionFactory();

Session session = sfact.openSession();

Transaction t = null;

try{
//System.out.println("begin");
t = session.beginTransaction();

try{
eid = Integer.parseInt(txtEid.getText());

if (eid <= 0)
	throw new NumberFormatException();
}
catch(NumberFormatException nfe){
	JOptionPane.showMessageDialog(new JDialog(),"EID should be +ve Integer");
	txtEid.setText("");
	txtEid.requestFocus();

	return;
}
catch(Exception e){
	JOptionPane.showMessageDialog(new JDialog(),"Some Issue");
	txtEid.setText("");
	txtEid.requestFocus();
	return;
}

Employee s = (Employee)session.get(Employee.class,eid);
if(s != null)
{

try{
ename = txtEname.getText();
if (ename.matches(".*\\d+.*")) 
	throw new NullPointerException();  
if (ename.length() < 2)	
	throw new IllegalAccessException();  	
}
catch(NullPointerException npe){
	JOptionPane.showMessageDialog(new JDialog(),"Only alphabets allowed ");
	txtEname.setText("");
	txtEname.requestFocus();
	return;
}
catch(IllegalAccessException iae){
	JOptionPane.showMessageDialog(new JDialog(),"Minimum length is 2 for name");
	txtEname.setText("");
	txtEname.requestFocus();
	return;
}
catch(Exception e){
	JOptionPane.showMessageDialog(new JDialog(),"Some Issue");
	txtEname.setText("");
	txtEname.requestFocus();
	return;
}

s.setEname(ename);

try{
esalary = Float.parseFloat(txtEsal.getText());

if (esalary < 8000)
	throw new NullPointerException();
}
catch(NullPointerException npe){
	JOptionPane.showMessageDialog(new JDialog(),"Minimum salary should be 8000");
	txtEsal.setText("");
	txtEsal.requestFocus();
	return;
}
catch(NumberFormatException nfe){
	JOptionPane.showMessageDialog(new JDialog(),"Please enter appropriate salary value");
	txtEsal.setText("");
	txtEsal.requestFocus();
	return;
}
catch(Exception e){
	JOptionPane.showMessageDialog(new JDialog(),"Some Issue");
	txtEsal.setText("");
	txtEsal.requestFocus();
	return;
}
/*s.setEsalary(esalary);

session.save(s);		
t.commit();
*/
int result =JOptionPane.showConfirmDialog(new JDialog(),"Please click YES_OPTION to Update record");

if (result  == JOptionPane.YES_OPTION) {		
	s.setEname(ename);	
	s.setEsalary(esalary);
	session.save(s);		
	t.commit();

     URL urlClick = UpdateFrame.class.getResource("claps.wav");
     click = Applet.newAudioClip(urlClick);
     click.play();
     //click.stop();
} else if (result  == JOptionPane.NO_OPTION) {
     t.rollback();
}
else{                             //CANCEL_OPTION SELECTED
	t.rollback();
}

txtEid.setText("");
txtEname.setText("");
txtEsal.setText("");
txtEid.requestFocus();

}
else{
JOptionPane.showMessageDialog(new JDialog(),"record does not exists ");
txtEid.setText("");
txtEname.setText("");
txtEsal.setText("");
txtEid.requestFocus();

}
}
catch(Exception e){
t.rollback();
JOptionPane.showMessageDialog(new JDialog(),e);
}
finally{
session.close();
}

}
});



setTitle("Update E");
setSize(1300,1400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}
