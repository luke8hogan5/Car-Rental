package models;
public class UserModel {
	private int id;
	private String name;
	private String email;
	private String password;
	private String address;
	private double balance;
	private int loyaltyPts;

	public UserModel(){}

	public UserModel(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public UserModel(String name, String password, String email) {
		this.name = name;
		this.password = password;
		this.email = email;
	}

	public UserModel(int id, String name, String email, String password, String address, double balance, int loyaltyPts) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.balance = balance;
		this.loyaltyPts = loyaltyPts;
	}

	public int getId() {
		return id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getLoyaltyPts() {
		return loyaltyPts;
	}

	public void setLoyaltyPts(int loyaltyPts) {
		this.loyaltyPts = loyaltyPts;
	}
}
