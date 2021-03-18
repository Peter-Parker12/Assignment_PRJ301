/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Ultils.Connector;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dang Minh Quan
 */
public class LoginDAO {
    
    
    public boolean isValidLogin(String username, String password){
        Connection connector = null;
        PreparedStatement prSt = null;
        ResultSet rs = null;
        try{
            connector = Connector.Connector();
            String sql =    "SELECT *\n" +
                            "FROM User_Account \n" +
                            "WHERE Username LIKE ? AND Password LIKE ?";
            prSt = connector.prepareStatement(sql);
            prSt.setString(1, username);
            prSt.setString(2, password);
            rs = prSt.executeQuery();
            return rs.next();
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean isAdmin(String userName, String password){
        Connection connector = null;
        PreparedStatement prSt = null;
        ResultSet rs = null;
        try{
            connector = Connector.Connector();
            String sql = "SELECT * FROM User_Account WHERE Username LIKE ? AND Password LIKE ? AND isAdmin = 1";
            prSt = connector.prepareStatement(sql);
            prSt.setString(1, userName);
            prSt.setString(2, password);
            rs = prSt.executeQuery();
            return rs.next();
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        
//        System.out.println(LoginDAO.isValidLogin("admin", "admin"));
//        System.out.println(LoginDAO.isAdmin("abc", "abc"));
    }
    
}