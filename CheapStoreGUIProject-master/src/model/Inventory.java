package model;

import javax.swing.ImageIcon;

public class Inventory {

	private int itemId;
	private String name;
	private String description;
	private ImageIcon image;
	private double price;
	private int inStock;
	private int totalAmountPurchased;
	
	public Inventory(int itemId, String name, String description, ImageIcon image2, double price, int inStock,
			int totalAmountPurchased) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.description = description;
		this.image = image2;
		this.price = price;
		this.inStock = inStock;
		this.totalAmountPurchased = totalAmountPurchased;
	}
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ImageIcon getImage() {
		return image;
	}
	public void setImage(ImageIcon image) {
		this.image = image;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getInStock() {
		return inStock;
	}
	public void setInStock(int inStock) {
		this.inStock = inStock;
	}
	public int getTotalAmountPurchased(){
		return totalAmountPurchased;
	}
	public void setTotalAmountPurchased(int totalAmountPurchased) {
		this.totalAmountPurchased = totalAmountPurchased;
	}
}
