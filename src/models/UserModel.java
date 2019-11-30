package models;


public class UserModel {
	private int userId;
	private int userType;
	private String name;
	private String email;
	private String address;
	private int loyaltyRating;
	private double balanceDue;
	private String password;

	public UserModel(){}

	public UserModel(int userId, int userType, String name, String email, String address, int loyaltyRating, double balanceDue) {
		this.userId = userId;
		this.userType = userType;
		this.name = name;
		this.email = email;
		this.address = address;
		this.loyaltyRating = loyaltyRating;
		this.balanceDue = balanceDue;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
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

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public int getLoyaltyRating() {
		return loyaltyRating;
	}
	public void setLoyaltyRating(int loyaltyRating) {
		this.loyaltyRating = loyaltyRating;
	}

	public double getBalanceDue() {
		return balanceDue;
	}
	public void setBalanceDue(double balanceDue) {
		this.balanceDue = balanceDue;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
