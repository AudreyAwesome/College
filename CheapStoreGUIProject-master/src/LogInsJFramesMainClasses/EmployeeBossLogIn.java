package LogInsJFramesMainClasses;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CustomerViews.CustomerMenu;
import DBClass.CheapStoreDB;
import model.UserAccount;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.ComponentOrientation;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class EmployeeBossLogIn extends JFrame{


	private CheapStoreDB db;
	private List<UserAccount> list;

	private UserAccount currentUser;

	private JPanel containerPanel;
	private JLabel labelPicture;
	private JPanel loginFieldsPanel;
	private JPanel panel_2;
	private JTextField passwordTextField;
	private JTextField emailTextField;
	private JLabel lblEmail;
	private JPanel loginPanel;


	private boolean logoutFlag = false;


	private JButton customerMenuLogout;

	private JPanel secondPane;
	private JButton employeeMenuLogout;	

	private static EmployeeBossLogIn frame;
	private JButton signinButton;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					frame = new EmployeeBossLogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public void createLoginPanel(){
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		
		containerPanel = new JPanel();
		containerPanel.setBackground(new Color(255, 102, 0));
		containerPanel.setBounds(100, 100, 848, 510);
		containerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Create IMage
		Image audreyP = new ImageIcon(this.getClass().getResource(
				"/pictures/CheapStoreLogo.png")).getImage();
		containerPanel.setLayout(new BorderLayout(0, 0));

		loginPanel = new JPanel();
		loginPanel.setBackground(new Color(255, 102, 0));
		containerPanel.add(loginPanel);
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

		JPanel welcomeImagePanel = new JPanel();
		loginPanel.add(welcomeImagePanel);
		welcomeImagePanel.setLayout(new BoxLayout(welcomeImagePanel, BoxLayout.X_AXIS));

		labelPicture = new JLabel("");
		labelPicture.setAlignmentX(Component.CENTER_ALIGNMENT);
		welcomeImagePanel.add(labelPicture);
		labelPicture.setHorizontalAlignment(SwingConstants.CENTER);
		labelPicture.setIcon(new ImageIcon(audreyP));

		loginFieldsPanel = new JPanel();
		loginPanel.add(loginFieldsPanel);
		loginFieldsPanel.setLayout(null);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 102, 0));
		panel_2.setBounds(0, 0, 864, 315);
		loginFieldsPanel.add(panel_2);
		panel_2.setLayout(null);

		passwordTextField = new JTextField();
		passwordTextField.setText("");
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(176, 95, 509, 29);
		panel_2.add(passwordTextField);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(39, 95, 127, 28);
		panel_2.add(lblPassword);

		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBounds(176, 35, 509, 29);
		panel_2.add(emailTextField);

		lblEmail = new JLabel("Email");
		lblEmail.setBounds(39, 35, 127, 28);
		panel_2.add(lblEmail);

		signinButton = new JButton("Sign In");

		
		
		
		
		//allows you to press enter on sign in button
		Action signIn = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				signInHelper();
			}
		};
		signinButton.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
				"signIn");
		signinButton.getActionMap().put("signIn",
				signIn);
		
		//listens for the click on the sign in button
		signinButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				signInHelper();
			}
		});
		
		signinButton.setBounds(376, 158, 127, 37);
		panel_2.add(signinButton);



		Component verticalStrut = Box.createVerticalStrut(25);
		containerPanel.add(verticalStrut, BorderLayout.NORTH);


	}

	public void signInHelper(){
		//	Verify that account exists on DB List of Users
		boolean userInDB = false;

		if(emailTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()){

			JOptionPane.showMessageDialog(frame, "Empty the Email and Password cannot be.", "Error",
					JOptionPane.ERROR_MESSAGE);
			// Print dialog wrong Password or empty Password

		} else {


			for(UserAccount user: list) {

				if(user.getUsersEmail().equals(emailTextField.getText())){
					currentUser = user; // Setting the user to be the current user
					userInDB = true; 
				}
			}



			if(userInDB){

				System.out.println(currentUser.getPassword());
				if (currentUser.getPassword().equals(passwordTextField.getText())) {
					// Check if account is customer
					if (currentUser.getTypeOfAccount().equalsIgnoreCase("c")) {

						secondPane = new CustomerMenu(customerMenuLogout, currentUser);
						containerPanel.removeAll();
						containerPanel.add(secondPane);
						containerPanel.revalidate();
						containerPanel.repaint();

						// Check if account is Boss or Employee
					} else if (currentUser.getTypeOfAccount().equalsIgnoreCase("e")) {

						secondPane = new EmployeeBossNavigationMenu(employeeMenuLogout, currentUser);
						containerPanel.removeAll();
						containerPanel.add(secondPane);
						containerPanel.revalidate();
						containerPanel.repaint();
					} else if (currentUser.getTypeOfAccount().equalsIgnoreCase("m")) {

						secondPane = new EmployeeBossNavigationMenu(employeeMenuLogout, currentUser);
						containerPanel.removeAll();
						containerPanel.add(secondPane);
						containerPanel.revalidate();
						containerPanel.repaint();
					} 
				}else {
					JOptionPane.showMessageDialog(frame, "Incorrect Password", 
							"Try Again",
							JOptionPane.ERROR_MESSAGE);
					// Print dialog wrong Password or empty Password
				}

			} else{
				JOptionPane.showMessageDialog(frame, "Incorrect Email", 
						"Try Again",
						JOptionPane.ERROR_MESSAGE);
				// Print dialog wrong email or empty email
			}
		}
	}

	//	public void keyPressed(KeyEvent e) {
	//        if (e.getKeyCode()==KeyEvent.VK_ENTER){
	//            System.out.println("Hello");
	//        }
	//        Component frame = new JFrame();
	//        JOptionPane.showMessageDialog(frame , "You've Submitted the name " + nameInput.getText());
	//
	//    }

	/**
	 * Create the frame.
	 */
	public EmployeeBossLogIn() {
		db = new CheapStoreDB();

		try {
			list = db.getUsers();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}



		setResizable(false);
		setBackground(new Color(255, 102, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 510);

		customerMenuLogout = new JButton("Logout");

		customerMenuLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if Logout button gets clicked, logout and rebuild Login Screen
				//Clear Users info
				currentUser = null;

				containerPanel.removeAll();
				createLoginPanel();
				setContentPane(containerPanel);
				containerPanel.revalidate();
				containerPanel.repaint();
			}

		});



		employeeMenuLogout = new JButton("LogOut");

		employeeMenuLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// if Logout button gets clicked, logout and rebuild Login Screen
				//Clear Users info
				currentUser = null;

				containerPanel.removeAll();
				createLoginPanel();
				setContentPane(containerPanel);
				containerPanel.revalidate();
				containerPanel.repaint();


			}
		});



		// call on mathod that build Login Panel
		createLoginPanel();

		setContentPane(containerPanel);



	}









}
