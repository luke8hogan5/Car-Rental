package models;

public class Customer extends UserModel {
    private int custID;
    private double balance;
    private int loyaltyPts;
    private String address;

    public Customer(){
        super("John Doe", "", "john.doe@rand.com");
        custID = 1;
        balance = 1000.00;
        loyaltyPts = 50;
        address = "Somewhere, Somewhere";
    }
    public Customer(String name, String password, String email, int custID, int loyaltyPts, double balance, String address) {
        super(name, password, email);
        this.custID = custID;
        this.balance = balance;
        this.loyaltyPts = loyaltyPts;
        this.address = address;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLoyaltyTier(){
        String tier = "Scrub";

        return tier;
    }
}
