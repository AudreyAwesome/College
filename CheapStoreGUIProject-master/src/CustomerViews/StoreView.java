package CustomerViews;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DBClass.CheapStoreDB;
import model.Item;
import model.UserAccount;

public class StoreView extends JPanel {
	

	private CheapStoreDB db;
	private List<Item> itemsList;
	private final JTextArea productDescriptionLabel = new JTextArea();
	private JList currentSelectedItemList;
	private UserAccount currentUser;
	private int numberOfNoShowUps = 5;
	private JPanel HolderPanel; 


	/**
	 * Create the panel.
	 * @param currentUser TODO
	 */
	public StoreView(UserAccount currentUser ) {
		
		
		this.currentUser = currentUser;
		db = new CheapStoreDB();
		this.HolderPanel = this;
		
		System.out.println(currentUser.getUsersEmail());
		
		try {
			itemsList = db.getItems();
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
		
		JLabel imageHolderLabel = new JLabel("Please select an Item");
		imageHolderLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		imageHolderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(imageHolderLabel, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 153, 51));
		panel_3.add(panel_6, BorderLayout.EAST);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel PriceTagLabel = new JLabel("");
		panel_6.add(PriceTagLabel);
		PriceTagLabel.setHorizontalAlignment(SwingConstants.LEFT);
		PriceTagLabel.setFont(new Font("Tahoma", Font.PLAIN, 70));
		
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
		
		

		// Create IMage
		Image addToCartImage = new ImageIcon(this.getClass().getResource(
				"/pictures/addToCart.png")).getImage();
		
		
		JButton cartButton = new JButton("");
		cartButton.setIcon(new ImageIcon(addToCartImage));
		cartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// if item is selected check if user is not in rejection list then take them to the cart view 
				

            	Item itemSelected = (Item) currentSelectedItemList.getSelectedValue();
				if (itemSelected !=null) {
					
					
					Boolean usersInRejectionList = false;// Boolean to determine wether user is in rejection list
					
						try {
							usersInRejectionList = db.getUserInRejectionList(currentUser.getUsersEmail(), numberOfNoShowUps);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
					if(!usersInRejectionList){
//						JOptionPane.showMessageDialog(null, "You are able to order. :)", "Information",
//								JOptionPane.INFORMATION_MESSAGE);
						
						int currentOrderNumber = 0;
						try {
							currentOrderNumber = db.getLatestOrderNumber(currentUser.getUsersEmail());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						// Insert into db with most current Order number Insert Query
						// So far You can only add one Item to your order, not change the quanity
						try {
							
							db.InsertIntoOrder(currentUser.getUsersEmail(), currentOrderNumber, itemSelected.getItemId(), 1, null);
							HolderPanel.removeAll();
							JPanel secondPane = new CartView(currentUser);
							
							HolderPanel.add(secondPane);
							HolderPanel.revalidate();
							HolderPanel.repaint();
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}else{// user is in rejection list
						JOptionPane.showMessageDialog(null, "You are in the rejection List, and will not be able to purchase items until... :(", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				} else{
					JOptionPane.showMessageDialog(null, "Please, first select an Item to add to the cart.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		cartButton.setForeground(new Color(0, 0, 0));
		cartButton.setBackground(new Color(255, 153, 51));
		panel_4.add(cartButton, BorderLayout.EAST);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 153, 51));
		panel_4.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(19);
		panel_5.add(horizontalStrut_2, BorderLayout.WEST);
		
		
		
		productDescriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		productDescriptionLabel.setWrapStyleWord(true);
		productDescriptionLabel.setLineWrap(true);
		productDescriptionLabel.setEditable(false);
		productDescriptionLabel.setBackground(new Color(255, 153, 51));
//		panel_5.add(productDescriptionLabel, BorderLayout.CENTER);
		

		JScrollPane scrollPane = new JScrollPane(productDescriptionLabel);
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
		
		
		
		
		

		currentSelectedItemList = new JList(itemsList.toArray());
		currentSelectedItemList.setCellRenderer(new ItemCellRenderer());
		currentSelectedItemList.setVisibleRowCount(4);
		
		
		currentSelectedItemList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt) {
            	Item i = (Item) currentSelectedItemList.getSelectedValue();
            	imageHolderLabel.setText("");
            	nameOfProduct.setText(i.getName());
            	productDescriptionLabel.setText(i.getDescription());
        		
            	imageHolderLabel.setIcon(i.getImage());
//            	System.out.println(String.format("%.2f", i.getPrice())  );
            	PriceTagLabel.setText("$" + String.format("%.2f", i.getPrice()));
            }
        });
		
	   		
		JScrollPane pane = new JScrollPane(currentSelectedItemList);
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
		
		Item entry = (Item) value;
		
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
