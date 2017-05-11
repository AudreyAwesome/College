package DBClass;





import java.awt.Image;
import java.sql.Blob;

///////////////////////////////////////////////

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;

import model.Inventory;
import model.Item;
import model.UserAccount;

/**
 * A class that consists of the database operations to insert and update the item information.
 * @author mmuppa
 *
 */

public class CheapStoreDB {
	private static String userName = "_445team9"; //local    "root";//
	private static String password = "yicKamna";  // local   "";// 
	private static String serverName = "cssgate.insttech.washington.edu";  // local   "localhost";
	private static Connection conn;
	private List<UserAccount> list;
	private List<Item> itemsList;
	private List<Inventory> inventoryList;


	/**
	 * Creates a sql connection to MySQL using the properties for
	 * userid, password and server information.
	 * @throws SQLException
	 */
	public static void createConnection() throws SQLException {
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);

		conn = DriverManager.getConnection("jdbc:" + "mysql" + "://"
				+ serverName + "/", connectionProps);

		System.out.println("Connected to database");
	}

	/**
	 * Returns a list of item objects from the database.
	 * @return list of items
	 * @throws SQLException
	 */
	public List<UserAccount> getUsers() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select usersEmail, usersImage, typeOfAccount, name, address, password "
				+ "from  "+  "_445team9.Account"; //"_445team9.Account ";

		list = new ArrayList<UserAccount>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String usersEmail = rs.getString("usersEmail");
				
				
				Blob blob = rs.getBlob("usersImage");
				ImageIcon imageIcon = new ImageIcon(
						blob.getBytes(1, (int)blob.length()));

				ImageIcon image = imageIcon;
				
//				
//				
//				Image usersImage = (Image) rs.getBlob("usersImage");
				String typeOfAccount = rs.getString("typeOfAccount");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String password = rs.getString("password");

				UserAccount user = new UserAccount(usersEmail, image, typeOfAccount, name, address, password);
				list.add(user);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return list;
	}



	
	
	
	
	/**
	 * Returns a list of item objects from the database.
	 * @return list of items
	 * @throws SQLException
	 */
	public List<UserAccount> getUsersWhoPlacedOrders() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = " select usersEmail, usersImage, typeOfAccount, name, address, password  "
				+ "from _445team9.Account "
				+ "natural join _445team9.Purchases "
				+ "where dateOfPurchase is NULL "
				+ "group by usersEmail"; 

		list = new ArrayList<UserAccount>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String usersEmail = rs.getString("usersEmail");
				
				
				Blob blob = rs.getBlob("usersImage");
				ImageIcon imageIcon = new ImageIcon(
						blob.getBytes(1, (int)blob.length()));

				ImageIcon image = imageIcon;
				
				String typeOfAccount = rs.getString("typeOfAccount");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String password = rs.getString("password");

				UserAccount user = new UserAccount(usersEmail, image, typeOfAccount, name, address, password);
				list.add(user);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return list;
	}


	
	
	

	/**
	 * Returns a list of item objects from the database.
	 * @return list of items
	 * @throws SQLException
	 */
	public List<Item> getItems() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select date, description, image, inStock, itemId, name, price "
				+ "from "+  "_445team9.Item"; //_445team9.Item ";

		itemsList = new ArrayList<Item>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String date = rs.getString("date"); 
				String description = rs.getString("description");

				Blob blob = rs.getBlob("image");
				ImageIcon imageIcon = new ImageIcon(
						blob.getBytes(1, (int)blob.length()));

				ImageIcon image = imageIcon; //(Image) rs.getBlob("image");
				int inStock = rs.getInt("inStock");
				int itemId = rs.getInt("itemId");
				String name = rs.getString("name");
				double price = rs.getDouble("price");


				Item item = new Item(itemId, name, description, image, date, price, inStock );
				itemsList.add(item);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return itemsList;
	}
	
	/**
	 * Returns a list of item objects from the database.
	 * @return list of items
	 * @throws SQLException
	 */
	public List<Inventory> getInventory() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select description, image, inStock, itemId, name, price, totalAmountPurchased "
				+ "from _445team9.Item "
				+ "natural join _445team9.Inventory"; //_445team9.Item ";
	

		inventoryList = new ArrayList<Inventory>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String description = rs.getString("description");
				Blob blob = rs.getBlob("image");
				ImageIcon imageIcon = new ImageIcon(
						blob.getBytes(1, (int)blob.length()));

				ImageIcon image = imageIcon; //(Image) rs.getBlob("image");
				int inStock = rs.getInt("inStock");
				int itemId = rs.getInt("itemId");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				int totalAmountPurchased = rs.getInt("totalAmountPurchased");


				Inventory inventory = new Inventory(itemId, name, description, image, price, inStock, totalAmountPurchased);
				inventoryList.add(inventory);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return inventoryList;
	}



	
	
	/**
	 * Returns a list of item objects from the database.
	 * @return list of items
	 * @throws SQLException
	 */
	public List<Item> getItemsInOrder(String currentUsersEmail , int currentOrderNumber) throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		


		
		String query = "select date, description, image, inStock, itemId, name, price "
				+ "from _445team9.Purchases "
				+ "natural join _445team9.Item "
				+ "where usersEmail = '" + currentUsersEmail + "' "
				+ "and orderNumber = "+ currentOrderNumber;


		itemsList = new ArrayList<Item>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String date = rs.getString("date"); 
				String description = rs.getString("description");

				Blob blob = rs.getBlob("image");
				ImageIcon imageIcon = new ImageIcon(
						blob.getBytes(1, (int)blob.length()));

				ImageIcon image = imageIcon; //(Image) rs.getBlob("image");
				int inStock = rs.getInt("inStock");
				int itemId = rs.getInt("itemId");
				String name = rs.getString("name");
				double price = rs.getDouble("price");


				Item item = new Item(itemId, name, description, image, date, price, inStock );
				itemsList.add(item);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return itemsList;
	}

	
	
	


	//
	//-- query to check if given user is on rejection list after 6 or more no show ups
	//select * from RejectionList 
	//where usersEmail = whoeverWeAreLooing for
	//and noShows > 5; -- number of no show ups to be added on rejection list 
	//		
	//		


	/**
	 * Returns the whether the given user is in the RejectionList table.
	 * @return the true if the user is in the rejection list and has missed more than the given amount of allowed times
	 * @throws SQLException
	 */
	public Boolean getUserInRejectionList(String soughtUsersEmail, int numberAllowedOfNoShowUps) throws SQLException {
		Boolean foundUsersEmail = false;

		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select usersEmail "
				+ " from "+ " _445team9.RejectionList " + " where usersEmail = '" + soughtUsersEmail + "' and noShows > " + numberAllowedOfNoShowUps ;

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String s = rs.getString("usersEmail");
//				System.out.println(s);
				if(rs.getString("usersEmail").equals(soughtUsersEmail)){

					foundUsersEmail = true;	
				}

			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return foundUsersEmail;

	}


	public int getLatestOrderNumber(String soughtUsersEmail) throws SQLException {
		int latestOrderNumber = 0;


		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;

		String query = "select max(orderNumber) as num "
				+ " from "+ " _445team9.Purchases " + " where usersEmail = '" + soughtUsersEmail + "'";
		

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);


			while (rs.next()) {


				if( rs.getObject("num") == null){// Return number 1 if this is the first time the user places an order

					latestOrderNumber = 1;
					//System.out.println("order number = " +  latestOrderNumber); //TODO delete when done
				} else { // return the latest order 
					

					int queriedOrderNumber = rs.getInt("num");
//					queriedOrderNumber
					String query2 = "select dateOfPurchase "
							+ "from _445team9.Purchases "
							+ "where orderNumber = " + queriedOrderNumber + " "
							+ "and usersEmail = '" + soughtUsersEmail + "'";
 
							
					ResultSet rs2 = stmt.executeQuery(query2);
					
					while (rs2.next()) {

						if( rs2.getObject("dateOfPurchase") == null ){ // Return number the latest order if it has not been purchased yet, meaning its date is null
							latestOrderNumber = queriedOrderNumber;

							//System.out.println("order number = " +  latestOrderNumber); //TODO delete when done
						} else{ // Return number the latest order + 1 because the previous order has already been closed or paid off so we return a new order number for the next order to be palced.
							latestOrderNumber = queriedOrderNumber + 1;

						}
					}


				}				 
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}

//		System.out.println("And the orderNumber was "+ latestOrderNumber + " for " +soughtUsersEmail);

		return latestOrderNumber;
	}
	
	public void InsertIntoOrder(String usersEmail, int currentOrderNumber, int itemId, int Quantity, Date dateOfPurchase)  throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;

		String query = "insert into "
				+ "_445team9.Purchases values "
				+ "('"+usersEmail+"', "+itemId+", "+currentOrderNumber+", "+Quantity+", "+dateOfPurchase+") ";

		try {
			stmt = conn.createStatement();
			int rs = stmt.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}

	}
	
	
	
	
//
//
//update _445team9.Purchases set dateOfPurchase ="2015-06-22"
//where  usersEmail  = 'a' 
//and orderNumber = 4 
//and dateOfPurchase IS NULL

	
	public void updatePurchase(String usersEmail, int orderNumber, String dateOfPayment) throws SQLException {

		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;

		String query = "update _445team9.Purchases set dateOfPurchase =\"" + dateOfPayment + "\" "
				+ "where  usersEmail  = '"+ usersEmail +"' "
				+ "and orderNumber = "+ orderNumber +" "
				+ "and dateOfPurchase IS NULL";


		try {
			stmt = conn.createStatement();
			int rs = stmt.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}

	}


}
