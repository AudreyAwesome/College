package model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Item {

	private int itemId;
	private String name;
	private String description;
	private ImageIcon image;
	private String date;// format from mysql is in Yyyy-mm-dd
	private double price;
	private int inStock;
	
	public Item(int itemId, String name, String description, ImageIcon image2, String date, double price, int inStock) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.description = description;
		this.image = image2;
		this.date = date;
		this.price = price;
		this.inStock = inStock;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	
	
	
	
}
