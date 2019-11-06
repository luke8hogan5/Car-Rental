package models;

public class Customer extends UserModel {
    private int custID;
    private double balance;
    private int loyaltyPts;

    public Customer(){
    }
    public Customer(String name, String password, int custID, int loyaltyPts, double balance) {
        super(name, password);
        this.custID = custID;
        this.balance = balance;
        this.loyaltyPts = loyaltyPts;
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
}
