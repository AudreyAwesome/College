package CustomerViews;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import model.UserAccount;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JScrollPane;

public class CustomerMenu extends JPanel {

	
	private JPanel secondPane;	
	private JPanel panel;
	
	private JPanel panel_2;
	
	JButton myLogOutButton ;

	private UserAccount currentUser;
	
	/**
	 * Create the panel.
	 */
	public CustomerMenu( JButton logoutButton , UserAccount currentUser) {
		
		myLogOutButton = logoutButton;
		
		this.currentUser = currentUser;
		
		
		
		setBounds(100, 100, 848, 471);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 102, 0));
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		

		Component horizontalStrut = Box.createHorizontalStrut(30);
		panel.add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(30);
		panel.add(horizontalStrut_1, BorderLayout.EAST);
		
		//Panel 2 - Contains all stuff about store View
		createStoreView();
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(204, 102, 0));
		panel.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_6.add(panel_1);
		panel_1.setBackground(new Color(204, 102, 0));
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton button_1 = new JButton("Store");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				panel_2.removeAll();
				secondPane = new StoreView(currentUser);
				
				panel_2.add(secondPane);
				panel_2.revalidate();
				panel_2.repaint();

			}
		});
		panel_1.add(button_1, BorderLayout.WEST);
		
		Component verticalStrut_1 = Box.createVerticalStrut(12);
		verticalStrut_1.setBackground(new Color(204, 102, 0));
		panel_1.add(verticalStrut_1, BorderLayout.NORTH);
		
		Component verticalStrut = Box.createVerticalStrut(12);
		panel_1.add(verticalStrut, BorderLayout.SOUTH);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		JButton button = new JButton("Cart");
		panel_3.add(button);
		
//		JButton btnLogout = new JButton("Logout");
//		btnLogout.setAlignmentX(0.5f);
		panel_3.add(myLogOutButton);
		
		JLabel lblNewLabel = new JLabel("Welcome Customer");
		lblNewLabel.setText("Welcome " + currentUser.getName() );
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel, BorderLayout.CENTER);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				secondPane = new CartView(currentUser);
				
				panel_2.removeAll();
				panel_2.add(secondPane);
				panel_2.revalidate();
				panel_2.repaint();
				
			}
		});
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(30);
		panel_6.add(horizontalStrut_2, BorderLayout.WEST);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(30);
		panel_6.add(horizontalStrut_3, BorderLayout.EAST);

	}


	private void createStoreView() {
	    
	    panel_2 = new StoreView(currentUser);
	    panel.add(panel_2, BorderLayout.CENTER);
	    
	}
}