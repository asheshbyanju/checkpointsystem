import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.*;
import javax.swing.*;

import net.proteanit.sql.DbUtils;


public class showtable extends JFrame {
	
	private JTable table;
	private JTextField search;
	private JComboBox comboselect;
	private JFrame frame;
	Connection connection = null;
	public showtable(){
		
		//table();
		connection = sqliteconncetion.dbConnector();
	
	
		 frame = new JFrame("Display");
		frame.setLayout(null); 
		frame.setSize(800,600);
		//frame.setBounds(0, 0, 800, 600);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		
		
		comboselect = new JComboBox();
		comboselect.setModel(new DefaultComboBoxModel(new String[]{" VehicleNo"," VehicleType"," LicenseNo"," EndPoint"," Date"}));
		comboselect.setBounds(200,10,127,31);
		frame.add(comboselect);
		
		
		
		JButton btnshow = new JButton("Show");
		//btnshow.setBounds(247,23,165,23);
		//btnshow.add(btnshow);
		btnshow.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0){
			try{
				String query = "SELECT * FROM main ";
				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				rs.close();
				pst.close();
				
			}	
			catch(Exception e){
				JOptionPane.showMessageDialog(null, e);
				
			}
			}
			
			
			
			
		});
		btnshow.setBounds(50,500,100,23);
		frame.add(btnshow);
		
		JButton btndelete = new JButton("Delete");
		
		btndelete.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0){
			
				
				new Update();
				dispose();
			
			}
			
			
			
		});
	btndelete.setBounds(170, 500, 100, 23);
	frame.add(btndelete);
	
	JButton btnupdate = new JButton("Update");

	
btnupdate.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent arg0){
		
			new Update();
			dispose();
			
		}
		
		
	});
	btnupdate.setBounds(300,500,100,23);
	frame.add(btnupdate);
	
	JButton btnback = new JButton("Back");
	btnback.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent arg0){
			
			
			frame.setVisible(false);
			//mainpage.setVisible(true);
			frame.dispose();
			
			
		}
		
	});
	
	btnback.setBounds(420, 500, 100, 23);
	frame.add(btnback);
	
	JLabel lblsearch = new JLabel("Search");
	lblsearch.setBounds(500, 10, 70, 30);
	frame.add(lblsearch);
	
 search= new JTextField();
	
	search.addKeyListener(new KeyAdapter(){
		public void keyReleased(KeyEvent arg0){
			
			try{
				String selection = (String)comboselect.getSelectedItem();
			String query = "SELECT * FROM main where "+selection+"=? ";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1,search.getText());
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			
			
			 
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		
	});
	
	search.setBounds(580, 10, 100, 20);
	frame.add(search);
	
	
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50,81,600,400);
		frame.add(scrollPane);
		 
		table = new JTable();
		scrollPane.setViewportView(table);
	
	
	
	}
	
}
