import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.awt.Color;

class DeleteFrame extends JFrame
{
Container c;
JLabel lblEid;
JTextField txtEid;
JButton btnSave, btnBack;
int eid;
AudioClip click;

DeleteFrame()
{
c = getContentPane();
c.setBackground(Color.YELLOW); 
c.setLayout(null);
lblEid = new JLabel("Enter eid ");
txtEid = new JTextField();
btnSave = new JButton("Save");
btnSave.setFont(btnSave.getFont().deriveFont(18.0f));
btnSave.setBackground(Color.BLACK);
btnSave.setForeground(Color.WHITE);
btnBack = new JButton("Back");
btnBack.setFont(btnBack.getFont().deriveFont(18.0f));
btnBack.setBackground(Color.BLACK);
btnBack.setForeground(Color.WHITE);

lblEid.setBounds(465,100,100,30);
txtEid.setBounds(575,100,150,30);
btnSave.setBounds(465,175,100,30);
btnBack.setBounds(600,175,100,30);


c.add(lblEid);
c.add(txtEid);
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
/*session.delete(s);		
t.commit();
*/
int result =JOptionPane.showConfirmDialog(new JDialog(),"Please click YES_OPTION to Delete record");

if (result  == JOptionPane.YES_OPTION) {		
	session.delete(s);		
	t.commit();

    URL urlClick = DeleteFrame.class.getResource("claps.wav");
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
txtEid.requestFocus();

}
else{
JOptionPane.showMessageDialog(new JDialog(),"record does not exists ");
txtEid.setText("");
txtEid.requestFocus();
}
//JOptionPane.showConfirmDialog(new JDialog(),"do u want to delete more records");
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



setTitle("Delete E");
setSize(1300,1400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}
