package DTO;

/**
 * @author megap
 *
 */
public class User {
	private int userID;
	private String fullName;
	private String address;
	private String phoneNumber;
	private String email;

    public User(int userID, String fullName, String address, String phoneNumber, String email) {
        this.userID = userID;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public User(String fullName, String address, String phoneNumber, String email) {
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public User(){

    }
    
    

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", fullName=" + fullName + ", address=" + address + ", phoneNumber=" + phoneNumber + ", email=" + email + '}';
    }
    
    
	
}