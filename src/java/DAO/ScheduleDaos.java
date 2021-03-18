/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Schedule;
import DTO.ScheduleDetails;
import Ultils.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author megap
 */
public class ScheduleDaos {

    public ArrayList<Schedule> getAllSchedule() {
        ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();

        try {
            Connection conn = Connector.Connector();
            Statement stmt = conn.createStatement();
            String sql = "SELECT s.*, sd.* FROM Schedule s, Schedule_details sd WHERE s.ScheduleID = sd.ScheduleID";

            ResultSet re = stmt.executeQuery(sql);
            while (re.next()) {
                int scheduleID = re.getInt("ScheduleID");
                String room = re.getString("room");
                String time = re.getString("time");
                boolean status = re.getBoolean("status");
                String doctor = re.getString("doctor");
                String content = re.getString("content");
                Double price = Double.valueOf(re.getString("price"));
                String date = re.getString("date");

                ScheduleDetails details = new ScheduleDetails(scheduleID, doctor, content, price);
                Schedule tempo = new Schedule(scheduleID, room, time, status, date, details);
                scheduleList.add(tempo);
            }
            return scheduleList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Schedule> getScheduleNotBooked(String InputContent, String fromDate) {
        ArrayList<Schedule> slist = new ArrayList<Schedule>();
        String sql = "SELECT s.*, sd.* \n"
                + "FROM Schedule s, Schedule_Details sd \n"
                + "WHERE s.ScheduleID = sd.ScheduleID AND sd.Content=? AND DATE BETWEEN ? and ? AND NOT s.scheduleID IN (SELECT ScheduleID FROM Booking)";
        try {
            Connection conn = Connector.Connector();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, InputContent);
            ps.setString(2, fromDate);
            ps.setString(3, fromDate);

            ResultSet re = ps.executeQuery();
            while (re.next()) {
                int scheduleID = re.getInt("ScheduleID");
                String room = re.getString("room");
                String time = re.getString("time");
                boolean status = re.getBoolean("status");
                String doctor = re.getString("doctor");
                String content = re.getString("content");
                Double price = Double.valueOf(re.getString("price"));
                String date = re.getString("date");

                ScheduleDetails details = new ScheduleDetails(scheduleID, doctor, content, price);
                Schedule tempo = new Schedule(scheduleID, room, time, status, date, details);
                slist.add(tempo);
            }
            return slist;
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {

        ScheduleDaos daos = new ScheduleDaos();
        List<Schedule> list = daos.getScheduleNotBooked("Skin Allergy", "2021-03-18");
        System.out.println(list);

    }

    public boolean addSchedule(Schedule input1) {

        try {
            Connection conn = Connector.Connector();
            String sql = "INSERT INTO Schedule VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, input1.getRoom());
            stmt.setString(2, input1.getDate());
            stmt.setBoolean(3, input1.isStatus());
            stmt.setString(4, input1.getTime());

            stmt.executeUpdate();

            sql = "INSERT INTO Schedule_Details VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            sql = "SELECT * FROM Schedule";
            Statement temp = conn.createStatement();
            ResultSet re = temp.executeQuery(sql);

            //Lay ID cua schedule vua đuoc them vao do Schedule vua đuoc them se luon nam o cuoi day.
            while (re.next()) {
                input1.setScheduleID(re.getInt("scheduleID"));
            }

            stmt.setInt(1, input1.getScheduleID());
            stmt.setString(2, input1.getDetail().getDoctor());
            stmt.setString(3, input1.getDetail().getContent());
            stmt.setString(4, String.valueOf(input1.getDetail().getPrice()));

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteSchedule(int ScheduleID) {
        try {
            Connection conn = Connector.Connector();
            String sql = "DELETE FROM Schedule WHERE ScheduleID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, ScheduleID);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateSchedule(Schedule input1) {
        try {
            Connection conn = Connector.Connector();
            String sql = "UPDATE Schedule SET Time = ?, room = ?, status = ?, date = ? WHERE ScheduleID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, input1.getTime());
            stmt.setString(2, input1.getRoom());
            stmt.setBoolean(3, input1.isStatus());
            stmt.setString(4, input1.getDate());
            stmt.setInt(5, input1.getScheduleID());

            stmt.executeUpdate();

            sql = "UPDATE Schedule_Details SET Doctor = ?, Content = ?, Price = ? WHERE ScheduleID = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, input1.getDetail().getDoctor());
            stmt.setString(2, input1.getDetail().getContent());
            stmt.setString(3, String.valueOf(input1.getDetail().getPrice()));
            stmt.setInt(4, input1.getScheduleID());

            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Schedule> getScheduleByTime(String fromDate, String toDate) {
        ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();
        if (toDate.isEmpty()) {
            toDate = fromDate;
        }
        try {
            Connection conn = Connector.Connector();
            String sql = "SELECT s.*, sd.* FROM Schedule s, Schedule_details sd WHERE s.ScheduleID = sd.ScheduleID AND s.Date BETWEEN ? and ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, fromDate);
            stmt.setString(2, toDate);

            ResultSet re = stmt.executeQuery();
            while (re.next()) {
                int scheduleID = re.getInt("ScheduleID");
                String room = re.getString("room");
                String time = re.getString("time");
                String doctor = re.getString("doctor");
                String content = re.getString("content");
                double price = Double.valueOf(re.getString("price"));
                boolean status = re.getBoolean("status");
                String date = re.getString("date");

                ScheduleDetails details = new ScheduleDetails(scheduleID, doctor, content, price);
                Schedule tempo = new Schedule(scheduleID, room, time, status, date, details);
                scheduleList.add(tempo);
            }
            return scheduleList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Schedule getScheduleByID(int scheduleID) {
        ScheduleDetails details = new ScheduleDetails();
        Schedule output = new Schedule(scheduleID, "", "", true, "", details);

        try {
            Connection conn = Connector.Connector();
            String sql = "SELECT s.*, sd.* FROM Schedule s, Schedule_details sd WHERE s.scheduleID = sd.ScheduleID AND sd.ScheduleID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, scheduleID);

            ResultSet re = stmt.executeQuery();

            while (re.next()) {
                output.setScheduleID(Integer.decode(re.getString("scheduleID")));
                output.setRoom(re.getString("Room"));

                output.setStatus(re.getBoolean("status"));
                String content = re.getString("content");
                output.getDetail().setContent(content);
                output.getDetail().setDoctor(re.getString("doctor"));
                output.setTime(re.getString("Time"));
                output.getDetail().setPrice(Double.valueOf(re.getString("price")));
                output.setDate(re.getString("date"));
            }
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean getScheduleByTimeAndDoctor(String time, String doctor, String date) {
        try {
            int count = 0;
            Connection conn = Connector.Connector();
            String sql = "SELECT s.*, sd.* FROM Schedule s, Schedule_details sd WHERE sd.doctor = ? AND s.date = ? AND s.date = ? AND s.ScheduleID = sd.ScheduleID";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, doctor);
            stmt.setString(2, time);
            stmt.setString(3, date);

            ResultSet re = stmt.executeQuery();

            while (re.next()) {
                count++;
            }

            return count >= 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean getScheduleByTimeAndRoom(String time, String room, String date) {
        try {
            int count = 0;
            Connection conn = Connector.Connector();
            String sql = "SELECT * FROM Schedule WHERE Room = ? AND time=? AND date = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, room);
            stmt.setString(2, time);
            stmt.setString(3, date);

            ResultSet re = stmt.executeQuery();

            while (re.next()) {
                count++;
            }
            return count >= 1; //Nếu số lần xuất hiện lớn hơn hoặc bằng 1 thì chứng tỏ phòng đó đã có người dùng vào lúc đó. 
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public ArrayList<Schedule> getScheduleContent(String content, String fromDate, String toDate) {
        ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();
        if (toDate.isEmpty()) {
            toDate = fromDate;
        }
        try {
            Connection conn = Connector.Connector();
            String sql = "SELECT s.*, sd.* FROM Schedule s, Schedule_details sd WHERE s.scheduleID = sd.ScheduleID AND sd.Content=? AND s.Date BETWEEN ? and ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, content);
            stmt.setString(2, fromDate);
            stmt.setString(3, toDate);

            ResultSet re = stmt.executeQuery();
            while (re.next()) {
                int scheduleID = re.getInt("ScheduleID");
                String room = re.getString("room");
                String time = re.getString("time");
                String doctor = re.getString("doctor");
                double price = Double.valueOf(re.getString("price"));
                boolean status = re.getBoolean("status");
                String date = re.getString("date");

                ScheduleDetails details = new ScheduleDetails(scheduleID, doctor, content, price);
                Schedule tempo = new Schedule(scheduleID, room, time, status, date, details);
                scheduleList.add(tempo);
            }
            return scheduleList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
