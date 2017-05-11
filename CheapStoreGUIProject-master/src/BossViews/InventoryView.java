/*
 * 445 Database
 * Ariel McNamara and Audrey
 */
package BossViews;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DBClass.CheapStoreDB;
import model.Inventory;
import model.UserAccount;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Color;

public class InventoryView extends JPanel {	

	private CheapStoreDB db;
	private List<Inventory> inventoryList;
	private final JTextArea productDescriptionTextArea = new JTextArea();
	private JList currentSelectedIventoryList;
	private UserAccount currentUser;
	private int numberOfNoShowUps = 5;
	private JPanel HolderPanel; 


	/**
	 * Create the panel.
	 * @param currentUser TODO
	 */
	public InventoryView(UserAccount currentUser ) {
		
		
		this.currentUser = currentUser;
		db = new CheapStoreDB();
		this.HolderPanel = this;
		
		System.out.println(currentUser.getUsersEmail());
		
		try {
			inventoryList = db.getInventory();
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
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setFont(new Font("Tahoma", Font.PLAIN, 40));
		panel_3.setBackground(new Color(255, 153, 51));
		panel_3.setBounds(346, 11, 442, 247);
		panel_2.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel imageHolderLabel = new JLabel("Select an Item");
		imageHolderLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		imageHolderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(imageHolderLabel, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 153, 51));
		panel_3.add(panel_6, BorderLayout.EAST);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JTextArea inventoryInfoTextArea = new JTextArea();
		panel_6.add(inventoryInfoTextArea);
		inventoryInfoTextArea.setFont(new Font("Tahoma", Font.PLAIN, 22));
		inventoryInfoTextArea.setWrapStyleWord(true);
		inventoryInfoTextArea.setLineWrap(true);
		inventoryInfoTextArea.setAlignmentX(CENTER_ALIGNMENT);
		
		inventoryInfoTextArea.setEditable(false);
		inventoryInfoTextArea.setBackground(new Color(255, 153, 51));
		
		Component horizontalStrut = Box.createHorizontalStrut(45);
		panel_6.add(horizontalStrut, BorderLayout.EAST);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 153, 51));
		panel_3.add(panel_7, BorderLayout.NORTH);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JLabel nameOfProduct = new JLabel("");
		panel_7.add(nameOfProduct);
		nameOfProduct.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(17);
		panel_7.add(horizontalStrut_1, BorderLayout.WEST);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 153, 51));
		panel_4.setBounds(346, 269, 442, 134);
		panel_2.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 153, 51));
		panel_4.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(19);
		panel_5.add(horizontalStrut_2, BorderLayout.WEST);
		
		
		
		productDescriptionTextArea.setFont(new Font("Tahoma", Font.PLAIN, 22));
		productDescriptionTextArea.setWrapStyleWord(true);
		productDescriptionTextArea.setLineWrap(true);
		productDescriptionTextArea.setEditable(false);
		productDescriptionTextArea.setBackground(new Color(255, 153, 51));
//		panel_5.add(productDescriptionLabel, BorderLayout.CENTER);
		

		JScrollPane scrollPane = new JScrollPane(productDescriptionTextArea);
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 153, 51));
		panel_5.add(scrollPane, BorderLayout.CENTER);
		
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(19);
		panel_5.add(horizontalStrut_3, BorderLayout.EAST);
//		productDescriptionLabel.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 153, 51));
		panel_1.setBounds(0, 11, 336, 392);
		panel_2.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		
		
		
		

		currentSelectedIventoryList = new JList(inventoryList.toArray());
		currentSelectedIventoryList.setCellRenderer(new ItemCellRenderer());
		currentSelectedIventoryList.setVisibleRowCount(4);
		
		
		currentSelectedIventoryList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt) {
            	Inventory i = (Inventory) currentSelectedIventoryList.getSelectedValue();
            	imageHolderLabel.setText("");
            	nameOfProduct.setText(i.getName());
            	productDescriptionTextArea.setText(i.getDescription() + " \nPrice: $" + String.format("%.2f", i.getPrice())
            			+ "\nNumber of items in stock: " + i.getInStock());
        		
            	imageHolderLabel.setIcon(i.getImage());
//            	System.out.println(String.format("%.2f", i.getPrice())  );
            	inventoryInfoTextArea.setText("\nTotal items ever bought: \t" + i.getTotalAmountPurchased());
            }
        });
		
	   		
		JScrollPane pane = new JScrollPane(currentSelectedIventoryList);
		panel_1.add(pane, BorderLayout.CENTER);

	}
}


////////////////////////////////////////////////////////////////////////////////////
class ItemCellRenderer extends JLabel implements ListCellRenderer {
	private static final Color HIGHLIGHT_COLOR = new Color(100, 0, 128);

	public ItemCellRenderer() {
		setOpaque(true);
		setIconTextGap(12);
	}

	public Component getListCellRendererComponent(
			JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		Inventory entry = (Inventory) value;
		
		setText("$" + String.format("%.2f", entry.getPrice()));
		setFont(new Font("Tahoma", Font.PLAIN, 58));
		
		setIcon( (Icon) entry.getImage());
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
