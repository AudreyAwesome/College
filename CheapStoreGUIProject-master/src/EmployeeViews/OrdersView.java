package EmployeeViews;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Icon;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import CustomerViews.CartView;
import DBClass.CheapStoreDB;
import model.Item;
import model.UserAccount;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.JList;
import javax.swing.JTextArea;

public class OrdersView extends JPanel {

	private UserAccount customerSelected;
	private int currentOrderNumber;
	
	
	private CheapStoreDB db;
	private List<UserAccount> usersInOrderList;
	private JList customersOrderslist;
	private JList itemsInCartList;
	private List<Item> itemsInOrderList;
	private boolean itemSelected = false;
	private JPanel itemsOrderedByCustomerPanel;
	private JScrollPane scrollPane_1;
	
	private JPanel HolderPanel; 


	/**
	 * Create the panel.
	 */
	public OrdersView() {

		

		this.HolderPanel = this;
		db = new CheapStoreDB();

		try {
			usersInOrderList = db.getUsersWhoPlacedOrders();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}


		// Size of layoutPane W: 788, H: 424
		setBounds(0, 0, 788, 424);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 102, 0));
		HolderPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(255, 102, 0));
		panel.add(panel_2, BorderLayout.CENTER);

		JPanel ordersInProcessPanel = new JPanel();
		ordersInProcessPanel.setBackground(new Color(255, 153, 51));
		ordersInProcessPanel.setBounds(0, 11, 391, 392);
		panel_2.add(ordersInProcessPanel);
		ordersInProcessPanel.setLayout(new BorderLayout(0, 0));

		customersOrderslist = new JList(usersInOrderList.toArray());
		customersOrderslist.setBackground(Color.WHITE);
		customersOrderslist.setCellRenderer(new OrdersCellRendererCart());
		customersOrderslist.setVisibleRowCount(4);

		customersOrderslist.addListSelectionListener(new ListSelectionListener() {

			

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				customerSelected = (UserAccount) customersOrderslist.getSelectedValue();

				System.out.println("User selected in ordersView " + customerSelected.getName());

				try {
					CheapStoreDB tempdb = new CheapStoreDB();
					currentOrderNumber = tempdb.getLatestOrderNumber(customerSelected.getUsersEmail());
					System.out.println("current Order Number in ordersView " + currentOrderNumber);

					itemsInOrderList = tempdb.getItemsInOrder(customerSelected.getUsersEmail(), currentOrderNumber);
					System.out.println("item in order list at pos 0 in ordersView " + itemsInOrderList.get(0).getName());

					itemSelected = true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (itemSelected) {
					itemsOrderedByCustomerPanel.removeAll();
					
					
					itemsInCartList = new JList(itemsInOrderList.toArray());
					itemsInCartList.setCellRenderer(new ItemCellRendererCart());
					itemsInCartList.setVisibleRowCount(4);
					itemsInCartList.repaint();
					itemsInCartList.revalidate();
					scrollPane_1 = new JScrollPane(itemsInCartList);
					itemsOrderedByCustomerPanel.add(scrollPane_1);
					itemsOrderedByCustomerPanel.revalidate();
					itemsOrderedByCustomerPanel.repaint();

				}
			}

		});

		JScrollPane scrollPane = new JScrollPane(customersOrderslist);
		scrollPane.setBackground(new Color(255, 153, 51));
		ordersInProcessPanel.add(scrollPane);

		JLabel lblNewLabel = new JLabel("Orders In Process:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(255, 153, 51));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		scrollPane.setColumnHeaderView(lblNewLabel);

		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(255, 153, 51));
		titlePanel.setBounds(401, 11, 387, 78);
		panel_2.add(titlePanel);
		titlePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCustomersOrders = new JLabel(" Customer's Order:");
		lblCustomersOrders.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titlePanel.add(lblCustomersOrders, BorderLayout.CENTER);

		JPanel customersOrdersPanel = new JPanel();
		customersOrdersPanel.setBackground(new Color(153, 153, 51));
		customersOrdersPanel.setBounds(401, 100, 387, 302);
		panel_2.add(customersOrdersPanel);
		customersOrdersPanel.setLayout(new BorderLayout(0, 0));

		JPanel buttonPanel = new JPanel();
		customersOrdersPanel.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setBackground(new Color(153, 153, 51));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnNewButton_1 = new JButton("No Show Up");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		buttonPanel.add(btnNewButton_1);

		JButton btnNewButton = new JButton("Purchased");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//if the Orders in Progress panel is not blank
				if(usersInOrderList.size() != 0){
					Date date = new Date(); // your date
				    Calendar cal = Calendar.getInstance();
				    cal.setTime(date);
				    int year = cal.get(Calendar.YEAR);
				    int month = cal.get(Calendar.MONTH) +1;
				    int day = cal.get(Calendar.DAY_OF_MONTH);
				    String dateOfPayment = year + "-" + String.format("%02d-%02d",month, day);
				    System.out.println(dateOfPayment);
					
				    
				    try {
						db.updatePurchase( customerSelected.getUsersEmail(), currentOrderNumber,  dateOfPayment);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					HolderPanel.removeAll();
					JPanel secondPane = new OrdersView();
					
					HolderPanel.add(secondPane);
					HolderPanel.revalidate();
					HolderPanel.repaint();
					
				    
				    
				}
				
				   
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		buttonPanel.add(btnNewButton);

		itemsOrderedByCustomerPanel = new JPanel();
		customersOrdersPanel.add(itemsOrderedByCustomerPanel, BorderLayout.CENTER);
		itemsOrderedByCustomerPanel.setLayout(new BorderLayout(0, 0));

		
		

	}
}

////////////////////////////////////////////////////////////////////////////////////
class OrdersCellRendererCart extends JLabel implements ListCellRenderer {
	private static final Color HIGHLIGHT_COLOR = new Color(100, 0, 128);

	public OrdersCellRendererCart() {
		setOpaque(true);
		setIconTextGap(12);
	}

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		UserAccount entry = (UserAccount) value;

//		System.out.println("Product Selected " + entry.getName());

		setText(entry.getName());
		setFont(new Font("Tahoma", Font.PLAIN, 30));

		setIcon((Icon) entry.getUsersImage());
		if (isSelected) {
			setBackground(HIGHLIGHT_COLOR);
			setForeground(Color.white);
		} else {
			setBackground(Color.white);
			setForeground(Color.black);
		}
		return this;
	}

}

////////////////////////////////////////////////////////////////////////////////////
class ItemCellRendererCart extends JLabel implements ListCellRenderer {
	private static final Color HIGHLIGHT_COLOR = new Color(100, 0, 128);

	public ItemCellRendererCart() {
		setOpaque(true);
		setIconTextGap(12);
	}

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		Item entry = (Item) value;

//		System.out.println("Product Selected " + entry.getName());

		setText(entry.getName() + " $" + String.format("%.2f", entry.getPrice()));
		setFont(new Font("Tahoma", Font.PLAIN, 20));

		setIcon((Icon) entry.getImage());
		if (isSelected) {
			setBackground(HIGHLIGHT_COLOR);
			setForeground(Color.white);
		} else {
			setBackground(Color.white);
			setForeground(Color.black);
		}
		return this;
	}

}
