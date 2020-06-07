package frame;

import Bean.Train;
import dao.Dao;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class TicketViewAll extends JFrame {

    /**
     * @method
     * @date: 2020/6/6 10:52 下午
     * @Param: null
     * @return
     * @description 显示所有的票务信息界面
     */
    public TicketViewAll(List<Train> trainList) {
        Container c = this.getContentPane();
        c.setLayout(new GridLayout(2, 1));
        JPanel title = new JPanel(new GridLayout(1, 9));
        JLabel Num = new JLabel("班次", SwingConstants.CENTER);
        JLabel Date = new JLabel("发车日期", SwingConstants.CENTER);
        JLabel Time = new JLabel("发车时间", SwingConstants.CENTER);
        JLabel Start = new JLabel("起点站", SwingConstants.CENTER);
        JLabel End = new JLabel("终点站", SwingConstants.CENTER);
        JLabel During = new JLabel("行车时间", SwingConstants.CENTER);
        JLabel Max = new JLabel("额定载量", SwingConstants.CENTER);
        JLabel Person = new JLabel("已定票人数", SwingConstants.CENTER);
        JLabel Have = new JLabel("是否有票", SwingConstants.CENTER);

        title.add(Num);
        title.add(Date);
        title.add(Time);
        title.add(Start);
        title.add(End);
        title.add(During);
        title.add(Max);
        title.add(Person);
        title.add(Have);

        JPanel content1 = new JPanel(new GridLayout(trainList.size(), 9));

        for (int i = 0; i < trainList.size(); i++){
            JLabel id = new JLabel(trainList.get(i).getId()+"",SwingConstants.CENTER);
            JLabel date = new JLabel(trainList.get(i).getStartTimeDate().toString(),SwingConstants.CENTER);
            String message;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:ss");
            if (Dao.trainTime(trainList.get(i))){
               message = "列车已发出";
            } else {
                message = trainList.get(i).getStartTimeHour();
            }
            JLabel time = new JLabel(message,SwingConstants.CENTER);
            JLabel start = new JLabel(trainList.get(i).getStartStation(),SwingConstants.CENTER);
            JLabel end = new JLabel(trainList.get(i).getEndStation(),SwingConstants.CENTER);
            JLabel runTime = new JLabel(trainList.get(i).getRunTime()+"",SwingConstants.CENTER);
            JLabel capacity = new JLabel(trainList.get(i).getCarryingCapacity()+"",SwingConstants.CENTER);
            if (trainList.get(i).getBookNumber() < trainList.get(i).getCarryingCapacity() ){
                message = "是";
            } else {
                message = "否";
            }
            JLabel bookNumber = new JLabel(trainList.get(i).getBookNumber()+"",SwingConstants.CENTER);
            JLabel books = new JLabel(message,SwingConstants.CENTER);

            content1.add(id);
            content1.add(date);
            content1.add(time);
            content1.add(start);
            content1.add(end);
            content1.add(runTime);
            content1.add(capacity);
            content1.add(bookNumber);
            content1.add(books);
        }

        JScrollPane content2 = new JScrollPane(content1);
        c.add(title);
        c.add(content2);
        this.setBounds(200, 200, 800, 400);
        this.setVisible(true);
        this.setTitle("浏览班次信息");
    }
}
