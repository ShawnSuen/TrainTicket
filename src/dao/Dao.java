package dao;

import Bean.Train;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Dao {

    protected static String driver = "com.mysql.cj.jdbc.Driver";
    protected static String url = "jdbc:mysql:///train";
    protected static String user = "root";
    protected static String password = "19980818";
    public static Connection connection;

    protected static Statement statement;
    protected static ResultSet resultSet;

    protected static PreparedStatement preparedStatement;

    protected static String sql = null;

    protected static String message = null;

    protected static boolean flag = true;

    static{
        try{
            if(connection == null){
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("数据库连接成功");
            }
        } catch (Exception e){
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
    }

    private Dao(){}

    /**
     * @method
     * @date: 2020/6/7 12:56 下午
     * @Param: null
     * @return
     * @description 录入功能
     */
    public static boolean addTrain(Train train) {
        if (train != null){
            sql = "INSERT INTO train(t_no,start_time_date,start_time_hour,start_station,end_station,run_time,carrying_capacity,book_no) value (?,?,?,?,?,?,?,?)";

            try {
                //将util.Date转化为sql.Date
                Date sDate = new Date(train.getStartTimeDate().getTime());

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,train.getId());
                preparedStatement.setDate(2,sDate);
                preparedStatement.setString(3,train.getStartTimeHour());
                preparedStatement.setString(4,train.getStartStation());
                preparedStatement.setString(5,train.getEndStation());
                preparedStatement.setInt(6,train.getRunTime());
                preparedStatement.setInt(7,train.getCarryingCapacity());
                preparedStatement.setInt(8,train.getBookNumber());
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException throwables) {
                System.out.println("添加数据失败");
                throwables.printStackTrace();
            }
        }
        return false;
    }

    /**
     * @method
     * @date: 2020/6/7 12:57 下午
     * @Param: null
     * @return
     * @description 按照id或者终点站查询
     */
    public static List<Train> searchTrain(int id, String endStation){
        List<Train> trains = new ArrayList<>();
        if (id != 0){
            sql = "SELECT * FROM train where t_no = " + id;
        } else {
            if (endStation != null){
                sql = "SELECT * FROM train where end_station like '%" + endStation + "%'";
            }
        }
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id1 = resultSet.getInt(1);
                java.util.Date startTime = resultSet.getDate(2);
                String startTimeHour = resultSet.getString(3);
                String startStation = resultSet.getString(4);
                String endStation1 = resultSet.getString(5);
                int runTime = resultSet.getInt(6);
                int carryingCapacity = resultSet.getInt(7);
                int bookNumber = resultSet.getInt(8);
                Train train = new Train();
                train.setId(id1);
                train.setStartTimeDate(startTime);
                train.setStartTimeHour(startTimeHour);
                train.setStartStation(startStation);
                train.setEndStation(endStation1);
                train.setRunTime(runTime);
                train.setCarryingCapacity(carryingCapacity);
                train.setBookNumber(bookNumber);
                trains.add(train);
            }
        } catch (Exception e) {
            System.out.println("查询失败");
            e.printStackTrace();
        }
        return trains;
    }

    /**
     * @method
     * @date: 2020/6/7 12:57 下午
     * @Param: null
     * @return
     * @description 显示所有
     */
    public static List<Train> showAll(){
        List<Train> trains = new ArrayList<>();
            sql = "SELECT * FROM train";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("t_no");
                java.util.Date startTime = resultSet.getDate("start_time_date");
                String startTimeHour = resultSet.getString("start_time_hour");
                String startStation = resultSet.getString("start_station");
                String endStation = resultSet.getString("end_station");
                int runTime = resultSet.getInt("run_time");
                int carryingCapacity = resultSet.getInt("carrying_capacity");
                int bookNumber = resultSet.getInt("book_no");
                Train train = new Train();
                train.setId(id);
                train.setStartTimeDate(startTime);
                train.setStartTimeHour(startTimeHour);
                train.setStartStation(startStation);
                train.setEndStation(endStation);
                train.setRunTime(runTime);
                train.setCarryingCapacity(carryingCapacity);
                train.setBookNumber(bookNumber);
                trains.add(train);
            }
        } catch (Exception e) {
            System.out.println("查询失败");
            e.printStackTrace();
        }
        return trains;
    }


    /**
     * @method
     * @date: 2020/6/7 12:59 下午
     * @Param: null
     * @return
     * @description 购票
     */
    public static int sellTicket(int id0, int ticketNUmber){
        if (id0 != 0){
            sql = "SELECT * FROM train where t_no = " + id0;
        } else {
            return 0;
        }
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Train train = new Train();
                int id = resultSet.getInt("t_no");
                java.util.Date startTime = resultSet.getDate("start_time_date");
                String startTimeHour = resultSet.getString("start_time_hour");
                String startStation = resultSet.getString("start_station");
                String endStation = resultSet.getString("end_station");
                int runTime = resultSet.getInt("run_time");
                int carryingCapacity = resultSet.getInt("carrying_capacity");
                int bookNumber = resultSet.getInt("book_no");
                train.setId(id);
                train.setStartTimeDate(startTime);
                train.setStartTimeHour(startTimeHour);
                train.setStartStation(startStation);
                train.setEndStation(endStation);
                train.setRunTime(runTime);
                train.setCarryingCapacity(carryingCapacity);
                train.setBookNumber(bookNumber);
                if (trainTime(train)) {
                    return 1;
                } else {
                    if (train.getBookNumber() < train.getCarryingCapacity()) {
                        int left = train.getCarryingCapacity() - train.getBookNumber();
                        if (left >= ticketNUmber) {
                            left = left - ticketNUmber;
                            bookNumber = train.getBookNumber() + ticketNUmber;
                            sql = "UPDATE train SET book_no = " + bookNumber + " where t_no = " + train.getId();
                        } else {
                            return 2;
                        }
                    } else {
                        return 3;
                    }
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.executeUpdate();
                    return 4;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    /**
     * @method
     * @date: 2020/6/7 1:21 下午
     * @Param: null
     * @return
     * @description 退票
     */
    public static int returnTicket(int id0, int ticketNumber){
        if (id0 != 0){
            sql = "SELECT * FROM train where t_no = " + id0;
        } else {
            return 0;
        }
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Train train = new Train();
                int id = resultSet.getInt("t_no");
                java.util.Date startTime = resultSet.getDate("start_time_date");
                String startTimeHour = resultSet.getString("start_time_hour");
                String startStation = resultSet.getString("start_station");
                String endStation = resultSet.getString("end_station");
                int runTime = resultSet.getInt("run_time");
                int carryingCapacity = resultSet.getInt("carrying_capacity");
                int bookNumber = resultSet.getInt("book_no");
                train.setId(id);
                train.setStartTimeDate(startTime);
                train.setStartTimeHour(startTimeHour);
                train.setStartStation(startStation);
                train.setEndStation(endStation);
                train.setRunTime(runTime);
                train.setCarryingCapacity(carryingCapacity);
                train.setBookNumber(bookNumber);
                if (ticketNumber <= train.getBookNumber()){
                    bookNumber = train.getBookNumber() - ticketNumber;
                    sql = "UPDATE train SET book_no = " + bookNumber + " where t_no = " + id;
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.executeUpdate();
                    return 1;
                } else {
                    return 2;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public static boolean trainTime(Train train){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = train.getStartTimeDate()+ " " + train.getStartTimeHour() ;
            java.util.Date date = simpleDateFormat.parse(time);
            flag = date.getTime() <= (new java.util.Date()).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
