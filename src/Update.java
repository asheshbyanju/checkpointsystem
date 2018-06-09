import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;


public class Update {
	Connection connection =null;

	private JFrame updateframe;
	
	private JTextField id;
	private JTextField vno;
	private JTextField vtype;
	private JTextField licenseno;
	private JTextField dname;
	private JTextField endpoint;
	private JFormattedTextField  time;
	private JFormattedTextField cdate;
	
	
	public Update(){
		DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
	 final Date date = new Date();
	 DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
	
		connection = sqliteconncetion.dbConnector();
		
		updateframe = new JFrame("UpdatePage");
		updateframe.setLayout(new FlowLayout());
		updateframe.setSize(500, 450);
		updateframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		updateframe.setVisible(true);
		updateframe.setLayout(null);
	
		JLabel lblid = new JLabel("ID");
		lblid.setBounds(10, 70, 70, 30);
		updateframe.add(lblid);
		
	JLabel lblv = new JLabel("Vechile No.");
	lblv.setBounds(10, 100, 70, 30);
	updateframe.add(lblv);
	
	
	JLabel lblvt = new JLabel("Vechile type");
	lblvt.setBounds(10, 130, 70, 30);
	updateframe.add(lblvt);
	
	JLabel lblli = new JLabel("License No.");
	lblli.setBounds(10, 160, 70, 30);
	updateframe.add(lblli);
	
	JLabel lbldn = new JLabel("Driver Name");
	lbldn.setBounds(10, 190, 70, 30);
	updateframe.add(lbldn);
	
	
	JLabel lblep = new JLabel("Endpoint");
	lblep.setBounds(10, 220, 70, 30);
	updateframe.add(lblep);
	
	JLabel lblat = new JLabel("Arrival time");
	lblat.setBounds(10, 250, 70, 30);
	updateframe.add(lblat);
	
	JLabel lbldate = new JLabel("Date");
	lbldate.setBounds(10, 280, 70, 30);
	updateframe.add(lbldate);
	
	id = new JTextField();
	id.setBounds(100, 70, 100, 20);
	updateframe.add(id);
	
	
	vno = new JTextField();
	vno.setBounds(100, 100, 100, 20);
	updateframe.add(vno);
	
	vtype = new JTextField();
	vtype.setBounds(100, 130, 100, 20);
	updateframe.add(vtype);
	
	licenseno = new JTextField();
	licenseno.setBounds(100, 160, 100, 20);
	updateframe.add(licenseno);
	
	dname = new JTextField();
	dname.setBounds(100, 190, 100, 20);
	updateframe.add(dname);
	
	
	endpoint = new JTextField();	
	endpoint.setBounds(100, 220, 100, 20);
	updateframe.add(endpoint);
	
	
	
	 time = new JFormattedTextField(dateFormat1);	
	time.setBounds(100, 250, 100, 20);
	updateframe.add(time);
	
	 cdate = new JFormattedTextField(dateFormat2);	
	cdate.setBounds(100, 280, 100, 20);
	updateframe.add(cdate);
	 
	
	
	id.setVisible(false);
	JButton btntime = new JButton("GetTime");
	
	btntime.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			date.getTime();
			 time.setValue(date);
		
		}
		
	});
	
	btntime.setBounds(220, 250, 100, 20);
	updateframe.add(btntime);
	
JButton btndate = new JButton("Getdate");
	
	btndate.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			date.getTime();
			 cdate.setValue(date);
		
		}
		
	});
	
	btndate.setBounds(220, 280, 100, 20);
	updateframe.add(btndate);
	
	JButton btnsave = new JButton("Save");
	btnsave.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0){
			try{
			String query = "insert into main (ID, VehicleNo, VehicleType, LicenseNo, DriverName, EndPoint, ArrivalTime, Date ) values(?,? ,? ,? ,? ,? ,?,? ) ";
			PreparedStatement pst = connection.prepareStatement(query);
			//pst.setString(1, id.getText() );
			pst.setString(2, vno.getText() );
			pst.setString(3, vtype.getText() );
			pst.setString(4, licenseno.getText() );
			pst.setString(5, dname.getText() );
			pst.setString(6, endpoint.getText() );
			pst.setString(7, time.getText() );
			pst.setString(8, cdate.getText() );
			
			 
			if( (licenseno.getText()) == null)
			{
				JOptionPane.showMessageDialog(null, "Invalid license number.\n Reenter");
				
			}
			pst.execute(); 
			 JOptionPane.showMessageDialog(null, "Data saved");
			pst.close();
			
			}
			catch(Exception e){
				e.printStackTrace();
				 JOptionPane.showMessageDialog(null, e);
				
			}
			
		}
		
		
	});
	btnsave.setBounds(10, 340, 100, 20);
	updateframe.add(btnsave);
	
	JButton btndelete = new JButton("delete");
	
	btndelete.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent arg0){
		
			id.setVisible(true);
			JOptionPane.showMessageDialog(null, "Enter a ID to be deleted");
			
			if(id == null){
				JOptionPane.showMessageDialog(null, "Enter a ID to be deleted");
				
			
			}
			else{
				try{
			String query = "delete  FROM main where ID='"+id.getText()+"' ";
			PreparedStatement pst = connection.prepareStatement(query);
			
			
			pst.execute(); 
			 
			JOptionPane.showMessageDialog(null, "Data Deleted");
		
			pst.close();
		}	
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			
		}
		}
		}
			
		
		
		
		
	});
	btndelete.setBounds(120, 340, 100, 20);
	updateframe.add(btndelete);
	
	JButton btnback = new JButton("Back");
	btnback.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent arg0){
			
			
				updateframe.setVisible(false);
			//mainpage.setVisible(true);
			updateframe.dispose();
			
			
		}
		
	});
	
	btnback.setBounds(240, 340, 100, 20);
	updateframe.add(btnback);
	
	JButton btnupdate = new JButton("Update");
	
	btnupdate.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent arg0){
		
			id.setVisible(true);
			
				try{
			String query = "update main set ID=='"+id.getText()+"',VehicleNo=='"+vno.getText()+"',VehicleType='"+vtype.getText()+"',LicenseNo='"+licenseno.getText()+"',DriverName='"+dname.getText()+"',EndPoint='"+endpoint.getText()+"',ArrivalTime='"+time.getText()+"' where ID='"+id.getText()+"' ";
			PreparedStatement pst = connection.prepareStatement(query);
			
			
			pst.execute(); 
			 
			JOptionPane.showMessageDialog(null, "Data Updated");
		
			pst.close();
		}	
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			
		}
		}
		
			
		
		
		
		
	});
	
	
	btnupdate.setBounds(10, 390, 100, 20);
	updateframe.add(btnupdate);
	
	
	}
	
	
	
	
}
