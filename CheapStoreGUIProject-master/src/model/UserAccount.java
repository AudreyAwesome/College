package model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class UserAccount {
	

	private ImageIcon usersImage;
	private String typeOfAccount;
	private String name;
	private String address;
	private String password;
	
	private String usersEmail;
	public UserAccount(String usersEmail, ImageIcon usersImage, String typeOfAccount, String name, String address, String password) {
		super();
		this.usersEmail = usersEmail;
		this.usersImage = usersImage;
		this.typeOfAccount = typeOfAccount;
		this.name = name;
		this.address = address;
		this.password = password;
	}
	
	
	
	
	public String getUsersEmail() {
		return usersEmail;
	}
	public void setUsersEmail(String usersEmail) {
		this.usersEmail = usersEmail;
	}
	public ImageIcon getUsersImage() {
		return usersImage;
	}
	public void setUsersImage(ImageIcon usersImage) {
		this.usersImage = usersImage;
	}
	public String getTypeOfAccount() {
		return typeOfAccount;
	}
	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}

}
