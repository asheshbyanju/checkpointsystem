import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import net.proteanit.sql.DbUtils;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date; 
import java.util.GregorianCalendar;

class mainpage extends JFrame implements MenuListener, ActionListener, KeyListener {

	JMenuBar menubar;
	JMenu File, Edit,  Exit;
	JMenuItem exit, display, add;
	private JTable table;
		
	JTextField timeF;
 private JLabel lbl;

	JMenu Aboutus;
	JMenuItem  contact, About_us, Lost_Vehicle;
	
	private JFrame mainframe;
	private BufferedImage img;
	Connection connection = null;
	//@Override
	protected void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		 //ImageIO.write(img, "png", new File("a.png"));
		
	}
	public void clock() {
		
		Thread clock = new Thread(){
			public void run()
			{
				try {
					while(true){
						
					Calendar rightNow = new GregorianCalendar();
					int day = rightNow.get(Calendar.DAY_OF_MONTH);
					int month= rightNow.get(Calendar.MONTH);
					int year = rightNow.get(Calendar.YEAR);
					int hour = rightNow.get(Calendar.HOUR_OF_DAY);
					int min= rightNow.get(Calendar.MINUTE);
					int sec = rightNow.get(Calendar.SECOND);
					lbl.setText( +year+"/"+ month +"/"+day+" :" +hour+":"+min+":"+sec);
					}
				} catch(Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	
		clock.start();
}
	public mainpage(){
		mainpage1();
		clock();
	}
	
	
	
public void mainpage1(){
	
	try{
		//img = ImageIO.read(new File("backimg.png"));
		//ImageIcon img = new ImageIcon("checkpoint.png");
		
	}
	catch(Exception e){
		
	}
	connection = sqliteconncetion.dbConnector();
	
	this.addKeyListener(this);
	menubar = new JMenuBar();
	mainframe = new JFrame("HomePage");
	mainframe.setLayout(new FlowLayout());
	mainframe.setSize(1366,768);
	mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainframe.setVisible(true);
	mainframe.setLayout(null);
	mainframe.setJMenuBar(menubar);
	
	
	File = new JMenu("File");
	//File.addMenuListener(new thisMenuListener());
	menubar.add(File);
	
	Edit = new JMenu("Edit");
	//Edit.addMenuListener(new thisMenuListener());
	menubar.add(Edit);
	
	Exit = new JMenu("Exit");
	Exit.setMnemonic(KeyEvent.VK_X);
	Exit.addMenuListener(this); 
	menubar.add(Exit);
	
	Aboutus = new JMenu("Aboutus");
	menubar.add(Aboutus);
	
	contact= new JMenuItem("contact");
	//contact.add(contact)
	Aboutus.add(contact);
	
	About_us= new JMenuItem("About_us");
	//contact.add(contact)
	Aboutus.add(About_us);
	
	display = new JMenuItem("display");
	display.addActionListener(this);
	Edit.add(display);
	
	Lost_Vehicle = new JMenuItem("Lost_Vehicle");
	Lost_Vehicle.addActionListener(this);
	Edit.add(Lost_Vehicle);

	add = new JMenuItem("add");
	add.addActionListener(this);
	Edit.add(add);
	

	exit = new JMenuItem("exit");
	exit.addActionListener(this);
	File.add(exit);
	
	

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(100,200,1200,400);

	mainframe.add(scrollPane);
	 
	table = new JTable();
	scrollPane.setViewportView(table);
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
	
	
	
	
	

	/*JButton btn = new JButton("Bus");
	btnbus.setBounds(600,320,100,30);
	mainframe.add(btnbus);
	*/
	
	/*JButton btn = new JButton("Show");
	btn.setBounds(600,500,100,30);
	mainframe.add(btn);
	
	btn.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent arg0){
			
			try{
				
				showtable st= new showtable();	
				
				
				
			}
			catch(Exception e){
				
				
			}
			
		}
		
		
	});*/
	
	
	
	lbl = new JLabel("clock");
	lbl.setFont(new Font("Tahoma",Font.BOLD,30));
	lbl.setBounds(1020,8,400,100);
	lbl.setForeground(Color.red);
	mainframe.getContentPane().add(lbl);
	 
	JLabel lbl2 = new JLabel("Check-Point System");
	lbl2.setFont(new Font("Tahoma",Font.BOLD,30));
	lbl2.setForeground(Color.red);
	lbl2.setBounds(500,4,400,100);
	mainframe.add(lbl2);
	
	JLabel logo = new JLabel("LOGO");
	logo.setFont(new Font("Tahoma",Font.BOLD,30));
	//logo.setForeground(Color.red);
	logo.setBounds(5,1,100,100);
	mainframe.add(logo);
	
	
}


@Override
public void keyPressed(KeyEvent ke) {
	// TODO Auto-generated method stub
	if(ke.getKeyChar()=='x'){
		System.exit(0);
		
	}
}

@Override
public void keyReleased(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void menuCanceled(MenuEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void menuDeselected(MenuEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void menuSelected(MenuEvent me) {
	// TODO Auto-generated method stub
	if (me.getSource().equals(Exit)){
		System.exit(0);
		//showtable st= new showtable();	
	}

}



@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
		if(e.getSource().equals(add)){
			Update up = new Update();	
			//this.dispose();
		}
		
		if(e.getSource().equals(About_us)){
			new aboutus();	
				
			}
	if(e.getSource().equals(display)){
		
		
		 new showtable();	
		// this.dispose();	
	}
	
	if(e.getSource().equals(Lost_Vehicle)){
		lostvehicle lv = new lostvehicle();	
		//this.dispose();
		
		
	}
		
		if (e.getSource().equals(exit)){
			System.exit(0);
			}
		
		
	


}
}


