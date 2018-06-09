
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;


public class login  {
	private JFrame frame;
	private JPasswordField  passwordfield; 
	private JTextField textun;
	Connection connection = null;
	
	
	public login() {
		
		createFrame();
		
		connection = sqliteconncetion.dbConnector();
		
	}
	
	
	public void createFrame()  {
         frame = new JFrame("Login");
         frame.setLayout(new FlowLayout());
         frame.setBounds(300, 300, 300, 280);
         frame.setLocationRelativeTo(null);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setVisible(true);
         frame.setLayout(null);
         
        
         

        /* JButton btnguest = new JButton("Login as Guest");
         btnguest.addActionListener(new ActionListener(){
    			
				public void actionPerformed(ActionEvent arg0) {
					new mainpage();
					
					
			          
         
         
				}
         });
		btnguest.setBounds(10,180,100,30);
         frame.add(btnguest);*/
         
         JLabel username = new JLabel("UserName :");
         username.setBounds(10,20,70,27);
         frame.add(username);
         
         JLabel Password = new JLabel("Password :");
         Password.setBounds(10,60,70,14);
         frame.add(Password);
         
         
          textun = new JTextField();
         textun.setBounds(100, 20, 100, 17);
         frame.add(textun);
        textun.setColumns(10);
        
        passwordfield = new JPasswordField();
        passwordfield.setBounds(100, 60, 100, 17);
        frame.add(passwordfield);
        passwordfield.setColumns(10);
       
       JButton btnlogin = new JButton("Login");
       btnlogin.addActionListener(new ActionListener(){
       			
				public void actionPerformed(ActionEvent arg0) {
					
					
					
					try{
						String query = "SELECT * FROM login  where username = ? and password = ?";
						PreparedStatement pst = connection.prepareStatement(query);
						pst.setString(1,textun.getText());
						pst.setString(2,passwordfield.getText());
						
						ResultSet rs = pst.executeQuery();
						int count =0;
						while(rs.next()){
							count=count+1;
							
							
						}
						/*if(rs.findColumn(query) == 1 && textun.getText() == "Guest" && passwordfield.getText()== null)
						{
							mainpage mg =new mainpage();
						}*/
					
						 if(count==1)
						{
							
							JOptionPane.showMessageDialog(null, "You can enter the main page: WELOCME");
							frame.dispose();
							mainpage mg =new mainpage();
							
						}
						else if(count>1){
							
							JOptionPane.showMessageDialog(null, "duplicate Username and password");
							
						
						}	
						else 
							
							JOptionPane.showMessageDialog(null, "Username and password is incorrect try again...");
						
					rs.close();
					pst.close();
					
					}
							
						
						
						//do()
						
					catch(Exception e)
					{
						
						JOptionPane.showMessageDialog(null, e);
						
					}
					
					}
				});
       
       btnlogin.setBounds(10,120,100,30);
       frame.add(btnlogin);
       
       JButton btncancel = new JButton("Cancel");
       btncancel.addActionListener(new ActionListener(){
       			
				public void actionPerformed(ActionEvent arg0) {
					 
					
					
					JOptionPane.showMessageDialog(null,"cancel");
					//frame.setVisible(false);
					
					frame.dispose();
				}}
       );
       
       btncancel.setBounds(150,120,100,30);
       frame.add(btncancel);
       
       
       }




	
       
}
