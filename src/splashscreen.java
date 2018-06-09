import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Container;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class splashscreen extends JWindow 
{
	static boolean isRegistered;
    private static JProgressBar progressBar = new JProgressBar();
   // private static splashscreen execute;
    private static int count;
    private static Timer timer1;
    
    Image pic = Toolkit.getDefaultToolkit().getImage("checkpoint.png");
	public splashscreen()
    {
		 // Container container = getContentPane();
	      //container.setLayout(null);

    	//this.setTitle(" WELCOME");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel1 = new JPanel();
        
        //ImageIcon pic = new ImageIcon("checkpoint.png");
       // panel1.add(new JLabel(pic));
        //this.add(panel1);
        this.setSize(800,600);
       setLocationRelativeTo(null);
        this.setVisible(true);
        JLabel label1= new JLabel("CHECK POINT SYSTEM");
        label1.setBounds(120,220,100,50);
        add(label1); 
        progressBar.setMaximum(50);
       progressBar.setBounds(55, 500, 500, 15);
      // add(progressBar);
      // setLocationRelativeTo(null);
        loadProgressBar();
      
        
    }
	 public void loadProgressBar() {
	        ActionListener al = new ActionListener() {

	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                count++;

	                progressBar.setValue(count);

	                System.out.println(count);

	                if (count == 50) {

	                	
	                	
	                    dispose();
	                    login lg = new login();
	                	 //setVisible(false);//swapped this around with timer1.stop()

	                    timer1.stop();
	                
	                }

	            }

	           /* public void createFrame() throws HeadlessException {
	                JFrame frame = new JFrame();
	                frame.setSize(500, 500);
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.setVisible(true);
	            }*/
	            };
	        timer1 = new Timer(50, al);
	        timer1.start();
	    }
	 public void paint(Graphics g){
		 g.drawImage(pic,00,00,this);
		 
	 }
}
	