/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Account;
import DTO.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Ultils.Connector;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dang Minh Quan
 */
public class UserDaos {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

   public User getUserByUsername(String username){
        Connection conn = null;
        PreparedStatement prSt = null;
        ResultSet rs = null;
        try{
            conn = Connector.Connector();
            String sql =    "SELECT userInfo.* \n" +
                            "FROM User_Account userAcc, User_Info userInfo\n" +
                            "WHERE userAcc.UserID = userInfo.UserID AND userAcc.Username = ?";
            prSt = conn.prepareStatement(sql);
            prSt.setString(1, username);
            rs = prSt.executeQuery();
            while(rs.next()){
                int userId = rs.getInt("UserID");
                String fullName = rs.getString("Fullname");
                String address = rs.getString("Address");
                String phoneNumber = rs.getString("Phonenumber");
                String email = rs.getString("Email");
                User user = new User(userId,fullName, address, phoneNumber, email);
                return user;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> getAllUser() throws SQLException {
        List<Account> list = new ArrayList<>();
        String query = "select * from User_Account a, User_Info i\n"
                + "where i.UserID= a.UserID";
        try {
            conn = new Connector().Connector();
            PreparedStatement prSt = conn.prepareStatement(query);
            rs = prSt.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getString(2), rs.getString(3), rs.getBoolean(4),
                        new User(rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public User getUserByID(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select * from User_Account a, User_Info i\n"
                + "where i.UserID= a.UserID and a.UserID=?";
        try {
            conn = Connector.Connector();
            if (conn != null) {
                ps= conn.prepareStatement(query);
                ps.setInt(1, id);
                rs= ps.executeQuery();
            }
            if (rs.next()) {
                String userName= rs.getString("UserName");
                String password= rs.getString("Password");
                boolean isAdmin= rs.getBoolean("isAdmin");
                int userID= rs.getInt("UserID");
                String fullName= rs.getString("FullName");
                String address= rs.getString("Address");
                String phone= rs.getString("Phonenumber");
                String email= rs.getString("Email");
                User user= new User(userID, fullName, address, phone, email);
                return user;
            }
        } 
        finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

    public Account getAccountByID(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select * from User_Account a, User_Info i\n"
                + "where i.UserID= a.UserID and a.UserID=?";
        try {
            conn = Connector.Connector();
            if (conn != null) {
                ps= conn.prepareStatement(query);
                ps.setInt(1, id);
                rs= ps.executeQuery();
            }
            if (rs.next()) {
                String userName= rs.getString("UserName");
                String password= rs.getString("Password");
                boolean isAdmin= rs.getBoolean("isAdmin");
                int userID= rs.getInt("UserID");
                String fullName= rs.getString("FullName");
                String address= rs.getString("Address");
                String phone= rs.getString("Phonenumber");
                String email= rs.getString("Email");
                Account user= new Account(userName, password, isAdmin, new User(userID, fullName, address, phone, email));
                return user;
            }
        } 
        finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }
    public boolean deleteUser(int id) throws SQLException {
        String sql= " delete from User_Account where UserID=?";
        try {
            conn= Connector.Connector();
            if(conn != null) {
                ps= conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                return true;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn!= null) {
                conn.close();
            }
        }
        return false;
    }
    public  boolean updateUserInfo(int userId, String fullName, String address, String email, String phoneNumber){
        Connection conn = null;
        PreparedStatement prSt = null;
        try{
            conn = Connector.Connector();
            String sql = "UPDATE User_Info SET Fullname = ?, Address = ?, Email = ?, Phonenumber = ? WHERE UserID = ?";
            prSt = conn.prepareStatement(sql);
            prSt.setString(1, fullName);
            prSt.setString(2, address);
            prSt.setString(3, email);
            prSt.setString(4, phoneNumber);
            prSt.setInt(5, userId);
            return prSt.executeUpdate()==1;
        }catch(SQLException e){
        }
        return false;
    }
    public boolean isValidUser(String fullName, String address, String phoneNumber, String email){
        if(fullName==null || address==null || phoneNumber==null || email==null) return false;
        if(fullName.equals("") || address.equals("") || phoneNumber.equals("") || email.equals("")) return false;
        return true;
    }
    public boolean isValidUsername(String userName){
        Connection conn = null;
        PreparedStatement prSt = null;
        ResultSet rs = null;
        try{
            conn = Connector.Connector();
            String sql = "SELECT * FROM User_Account WHERE Username LIKE ?";
            prSt = conn.prepareStatement(sql);
            prSt.setString(1, userName);
            rs = prSt.executeQuery();
            return !rs.next();
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
    private boolean addUserAccount(String username, String password){
        Connection conn = null;
        PreparedStatement prSt = null;
        try{
            conn = Connector.Connector();
            String sql = "INSERT INTO User_Account VALUES (?,?,0)";
            prSt = conn.prepareStatement(sql);
            prSt.setString(1, username);
            prSt.setString(2, password);
            prSt.execute();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    private int getAccountIdByUsername(String username){
        Connection conn = null;
        PreparedStatement prSt = null;
        ResultSet rs = null;
        int res = 0 ;
        try{
            conn = Connector.Connector();
            String sql = "SELECT UserID FROM User_Account WHERE Username LIKE ?";
            prSt = conn.prepareStatement(sql);
            prSt.setString(1, username);
            rs = prSt.executeQuery();
            while(rs.next()){
                res = rs.getInt("UserID");
            }
            return res;
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }
    private boolean addUserInfo(User user, int userId){
        Connection conn = null;
        PreparedStatement prSt = null;
        try{
            conn = Connector.Connector();
            String sql = "INSERT INTO User_Info VALUES (?,?,?,?,?)";
            prSt = conn.prepareStatement(sql);
            prSt.setInt(1, userId);
            prSt.setString(2, user.getFullName());
            prSt.setString(3, user.getAddress());
            prSt.setString(4, user.getPhoneNumber());
            prSt.setString(5, user.getEmail());
            prSt.execute();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
        public boolean addUserToDB(User user, Account account){
        boolean addAccount = addUserAccount(account.getUserName(), account.getPassword());
        int userId = getAccountIdByUsername(account.getUserName());
        boolean addInfo = addUserInfo(user, userId);
        return addAccount && addInfo;
    }
}
