package projectcasestudy2.model;

import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;

    public Account() {
    }

    public Account(int id, String username, String password, String fullName, String email, String phoneNumber, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullname(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Account {" +
                "id= " + id +
                ", username='" + username +
                ", password='" + password +
                ", fullname='" + fullName +
                ", email='" + email +
                ", phoneNumber='" + phoneNumber +
                ", address='" + address +
                '}';
    }
}
