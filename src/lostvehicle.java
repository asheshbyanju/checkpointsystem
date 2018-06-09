import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
public class lostvehicle extends JFrame {

	private JTable table;
	//private JPanel contentPane;
	private JFrame frame;
	Connection connection = null;

		
	//private JTextField id;
	private JTextField vno;
	private JTextField vtype;
	
 	private JComboBox comboselect;
 	private JTextField search;
 	private JFormattedTextField lostdate;
	
	public lostvehicle(){
		
		 final Date date = new Date();
		 DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
		connection = sqliteconncetion.dbConnector();
		
		
		
		
		 frame = new JFrame("Lost Vehicle Records");
		//frame.setLayout(new FlowLayout()); 
		//setSize(800,600);
		
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setBounds(100, 100, 800, 600);
		frame.setLayout(null);
		frame.setVisible(true);
		
		JLabel lblid = new JLabel("ID");
		lblid.setBounds(10, 70, 70, 30);
		frame.add(lblid);
		
	JLabel lblv = new JLabel("Vechile No.");
	lblv.setBounds(10, 100, 70, 30);
	frame.add(lblv);
	
	
	JLabel lblvt = new JLabel("Vechile type");
	lblvt.setBounds(10, 130, 70, 30);
	frame.add(lblvt);
	
	JLabel lblld = new JLabel("Lost Date");
	lblld.setBounds(10, 160, 70, 30);
	frame.add(lblld);
	
	//id = new JTextField();
	//id.setBounds(100, 70, 100, 20);
	//frame.add(id);
	
	
	vno = new JTextField();
	vno.setBounds(100, 100, 100, 20);
	frame.add(vno);
	
	vtype = new JTextField();
	vtype.setBounds(100, 130, 100, 20);
	frame.add(vtype);
	
	lostdate =new JFormattedTextField();
	lostdate.setBounds(100, 160, 100, 20);
	frame.add(lostdate);
	date.getTime();
	 lostdate.setValue(date);
	
	
		JButton btnshow = new JButton("Show");
		//btnshow.setBounds(247,23,165,23);
		//btnshow.add(btnshow);
		btnshow.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0){
			try{
				String query = "SELECT * FROM lostvehicle";
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
		
		JButton btnsave = new JButton("Save");
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				try{
				String query = "insert into lostvehicle (ID, Vehicle_Plate_No, Vehicle_Type,  Lost_Date ) values(?,? ,? ,?  ) ";
				PreparedStatement pst = connection.prepareStatement(query);
				//pst.setString(1, id.getText() );
				pst.setString(2, vno.getText() );
				pst.setString(3, vtype.getText() );
				pst.setString(4, lostdate.getText() );
				
				 
				if( (vno.getText()) == null)
				{
					JOptionPane.showMessageDialog(null, "Invalid Vehicle number.\n Reenter");
					
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
		btnsave.setBounds(170, 500, 100, 23);
		frame.add(btnsave);
		
		comboselect = new JComboBox();
		comboselect.setModel(new DefaultComboBoxModel(new String[]{" Vehicle_Plate_No", "Vehicle_Type",  "Lost_Date"}));
		comboselect.setBounds(370,10,100,30);
		frame.add(comboselect);
		
		JLabel lblsearch = new JLabel("Search");
		lblsearch.setBounds(500, 10, 70, 30);
		frame.add(lblsearch);
		
		search= new JTextField();
		search.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent arg0){
				
				try{
					String selection = (String)comboselect.getSelectedItem();
				String query = "SELECT * FROM  lostvehicle where "+selection+"=? ";
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
		
		
	
		search.setBounds(600, 10, 100, 20);
		frame.add(search);
		
		
		
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(250,81,500,400);
			frame.add(scrollPane);
			 
			table = new JTable();
			scrollPane.setViewportView(table);
		
		
		
		}
		
		
		
		
		
	}
	

