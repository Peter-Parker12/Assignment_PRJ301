/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Account;
import DTO.Booking;
import DTO.Schedule;
import DTO.User;
import Ultils.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dang Minh Quan
 */
public class BookingDaos {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Booking> getAllBooking() {
        List<Booking> list = new ArrayList<>();
        String query = "SELECT b.*, s.*, sd.*, u.* FROM Booking b, Schedule s, Schedule_details sd, User_Info u \n"
                + "WHERE b.userID = u.userID AND s.ScheduleID = sd.ScheduleID AND b.scheduleID = s.ScheduleID";
        try {
            conn = new Connector().Connector();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserDaos udao = new UserDaos();
                ScheduleDaos sdao = new ScheduleDaos();
                int uID= rs.getInt("UserID");
                int scheID = rs.getInt("ScheduleID");
                User u = udao.getUserByID(uID);
                Schedule s = sdao.getScheduleByID(scheID);
                list.add(new Booking(rs.getInt("BookingID"), u, s, rs.getBoolean("Status")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Booking> getUserBookingByTime(int id, String fromDate, String toDate) throws SQLException {
        List<Booking> list = new ArrayList<>();
        UserDaos udao = new UserDaos();
        ScheduleDaos sdao = new ScheduleDaos();
        User u = udao.getUserByID(id);

        String query = "SELECT b.*, s.*, sd.*, u.* FROM Booking b, Schedule s, Schedule_details sd, User_Info u \n"
                + "WHERE b.userID = u.userID AND s.ScheduleID = sd.ScheduleID AND b.scheduleID = s.ScheduleID AND b.userID = ? "
                + "AND s.date  BETWEEN ? AND ?";
        try {
            conn = new Connector().Connector();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, fromDate);
            ps.setString(3, toDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                int scheID = rs.getInt("ScheduleID");
                Schedule s = sdao.getScheduleByID(scheID);
                list.add(new Booking(rs.getInt("BookingID"), u, s, rs.getBoolean("Status")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Booking> getAllUserBooking(int id) throws SQLException {
        List<Booking> list = new ArrayList<>();
        UserDaos udao = new UserDaos();
        ScheduleDaos sdao = new ScheduleDaos();
        User u = udao.getUserByID(id);

        String query = "SELECT b.*, s.*, sd.*, u.* FROM Booking b, Schedule s, Schedule_details sd, User_Info u \n"
                + "WHERE b.userID = u.userID AND s.ScheduleID = sd.ScheduleID AND b.scheduleID = s.ScheduleID AND b.userID = ? ORDER BY s.Date";
        try {
            conn = new Connector().Connector();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int scheID = rs.getInt("ScheduleID");
                Schedule s = sdao.getScheduleByID(scheID);
                list.add(new Booking(rs.getInt("BookingID"), u, s, rs.getBoolean("Status")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public  boolean deleteBooking(User user, String scheduleId){
       Connection conn = null;
       PreparedStatement prSt = null;
       try{
           conn = Connector.Connector();
           int userId = user.getUserID();
           String sql = "DELETE FROM Booking WHERE UserID = ? AND ScheduleID = ?";
           prSt = conn.prepareStatement(sql);
           prSt.setString(1, String.valueOf(userId));
           prSt.setString(2, String.valueOf(scheduleId));
           return prSt.executeUpdate() == 1;
       }catch(Exception e){
           e.printStackTrace();
       }
       return false;
   }

    public boolean addBooking(int UserID, int ScheduleID) {
        String sql = "insert into Booking (UserID, ScheduleID, Booking_status) values (?, ?, '0')";
        try {
            conn = new Connector().Connector();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, UserID);
                ps.setInt(2, ScheduleID);
                ps.executeUpdate();
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    

    public static void main(String[] args) throws SQLException {
        BookingDaos bdao = new BookingDaos();
        System.out.println(bdao.getAllUserBooking(18));
    }
}
