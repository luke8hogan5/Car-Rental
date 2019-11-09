package models;


public class UserModel {
	private int userId;
	private String name;
	private String password;
<<<<<<< HEAD
	private String email;
	
=======

	public UserModel(){}
>>>>>>> 246bbd16297e5ddccc1701a7c289c4e98ae064e2
	public UserModel(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public UserModel(int userId) {
		this.userId = userId;
	}
	
	public UserModel(String name, String password, String email) {
		this.name = name;
		this.password = password;
		this.email = email;
	}
	
	public UserModel(String name, String email, int userId) {
		this.name = name;
		this.userId = userId;
		this.email = email;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
