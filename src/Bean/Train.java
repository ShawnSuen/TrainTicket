package Bean;


import java.util.Date;

public class Train {

    private int id;
    private Date startTimeDate;
    private String startTimeHour;
    private String startStation;
    private String endStation;
    private int runTime;
    private int carryingCapacity;
    private int bookNumber;

    public Train() {
    }

    public Train(int id, Date startTimeDate, String startTimeHour, String startStation, String endStation, int runTime, int carryingCapacity, int bookNumber) {
        this.id = id;
        this.startTimeDate = startTimeDate;
        this.startTimeHour = startTimeHour;
        this.startStation = startStation;
        this.endStation = endStation;
        this.runTime = runTime;
        this.carryingCapacity = carryingCapacity;
        this.bookNumber = bookNumber;
    }

    public Train(Date startTimeDate, String startTimeHour, String startStation, String endStation, int runTime, int carryingCapacity, int bookNumber) {
        this.startTimeDate = startTimeDate;
        this.startTimeHour = startTimeHour;
        this.startStation = startStation;
        this.endStation = endStation;
        this.runTime = runTime;
        this.carryingCapacity = carryingCapacity;
        this.bookNumber = bookNumber;
    }

    public String getStartTimeHour() {
        return startTimeHour;
    }

    public void setStartTimeHour(String startTimeHour) {
        this.startTimeHour = startTimeHour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartTimeDate() {
        return startTimeDate;
    }

    public void setStartTimeDate(Date startTimeDate) {
        this.startTimeDate = startTimeDate;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(int bookNumber) {
        this.bookNumber = bookNumber;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", startTimeDate=" + startTimeDate +
                ", startTimeHour='" + startTimeHour + '\'' +
                ", startStation='" + startStation + '\'' +
                ", endStation='" + endStation + '\'' +
                ", runTime=" + runTime +
                ", carryingCapacity=" + carryingCapacity +
                ", bookNumber=" + bookNumber +
                '}';
    }
}
