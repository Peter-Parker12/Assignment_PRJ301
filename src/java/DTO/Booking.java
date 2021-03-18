package DTO;

public class Booking {

    private int bookingID;
    private User user;
    private Schedule schedule;
    private boolean status;

    public Booking(int bookingID, boolean status) {
        super();
        this.bookingID = bookingID;
        this.status = status;
    }

    public Booking(int bookingID, User user, Schedule schedule, boolean status) {
        this.bookingID = bookingID;
        this.user = user;
        this.schedule = schedule;
        this.status = status;
    }
    

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    

    @Override
    public String toString() {
        return "Booking{" + "bookingID=" + bookingID + ", user=" + user + ", schedule=" + schedule + ", status=" + status + '}';
    }

}
