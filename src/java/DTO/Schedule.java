package DTO;

public class Schedule {
	private int scheduleID;
	private String room;
	private String time;
	private boolean status;
        private String date;
        private ScheduleDetails detail;

    public Schedule() {
    }

    public Schedule(int scheduleID, String room, String time, boolean status, String date, ScheduleDetails detail) {
        this.scheduleID = scheduleID;
        this.room = room;
        this.time = time;
        this.status = status;
        this.date = date;
        this.detail = detail;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ScheduleDetails getDetail() {
        return detail;
    }

    public void setDetail(ScheduleDetails detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Schedule{" + "scheduleID=" + scheduleID + ", room=" + room + ", time=" + time + ", status=" + status + ", date=" + date + ", detail=" + detail + '}';
    }
    

    
}