/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;


public class ScheduleDetails {
    private int scheduleID;
    private String doctor;
    private String content;
    private double price;

    public ScheduleDetails() {
    }

    public ScheduleDetails(int scheduleID, String doctor, String content, double price) {
        this.scheduleID = scheduleID;
        this.doctor = doctor;
        this.content = content;
        this.price = price;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ScheduleDetails{" + "scheduleID=" + scheduleID + ", doctor=" + doctor + ", content=" + content + ", price=" + price + '}';
    }
    
}