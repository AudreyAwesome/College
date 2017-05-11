package model;

public class Purchase {

	private String usersEmail;
	private int itemId;
	private int orderNumber;
	private int quantityOfItemPurchased;
	private String dateOfPurchase;
	
	
	public Purchase(String usersEmail, int itemId, int orderNumber, int quantityOfItemPurchased,
			String dateOfPurchase) {
		super();
		this.usersEmail = usersEmail;
		this.itemId = itemId;
		this.orderNumber = orderNumber;
		this.quantityOfItemPurchased = quantityOfItemPurchased;
		this.dateOfPurchase = dateOfPurchase;
	}
	
	
	public String getUsersEmail() {
		return usersEmail;
	}
	public void setUsersEmail(String usersEmail) {
		this.usersEmail = usersEmail;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getQuantityOfItemPurchased() {
		return quantityOfItemPurchased;
	}
	public void setQuantityOfItemPurchased(int quantityOfItemPurchased) {
		this.quantityOfItemPurchased = quantityOfItemPurchased;
	}
	public String getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	

}
