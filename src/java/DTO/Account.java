/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Dang Minh Quan
 */
public class Account {
    private User user;
    private String userName;
    private String password;
    private boolean isAdmin;

    public Account(User user, String userName, String password,boolean isAdmin) {
        this.user = user;
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    
     public Account(String userName, String password,boolean isAdmin, User user) {
        this.user = user;
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Account() {
    }


    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" + "user=" + user + ", userName=" + userName + ", password=" + password + ", isAdmin=" + isAdmin + '}';
    }
    
    

    
    
    
}