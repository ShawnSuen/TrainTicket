package frame;

import Bean.Train;
import dao.Dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TicketAdd extends JFrame implements ActionListener {

    JTextField NumText, TimeText, StartText, EndText, DuringText, MaxText, PersonText;

    JButton b1, b2, b3;

    /**
     * @method
     * @date: 2020/6/6 10:55 下午
     * @Param: null
     * @return
     * @description 录入界面
     */
    public TicketAdd() {
        Container c = this.getContentPane();
        c.setLayout(null);
        JLabel label = new JLabel("录入班次信息");
        label.setFont(new Font("TRUE", Font.TRUETYPE_FONT, 20));
        label.setBounds(190, 15, 500, 100);
        c.add(label);
        NumText = new JTextField(15);
        NumText.setBounds(220, 95, 125, 15);
        TimeText = new JTextField(15);
        TimeText.setBounds(220, 120, 125, 15);
        StartText = new JTextField(15);
        StartText.setBounds(220, 165, 125, 15);
        EndText = new JTextField(15);
        EndText.setBounds(220, 190, 125, 15);
        DuringText = new JTextField(15);
        DuringText.setBounds(220, 215, 125, 15);
        MaxText = new JTextField(15);
        MaxText.setBounds(220, 240, 125, 15);
        PersonText = new JTextField(15);
        PersonText.setBounds(220, 265, 125, 15);

        c.add(NumText);
        c.add(TimeText);
        c.add(StartText);
        c.add(EndText);
        c.add(DuringText);
        c.add(MaxText);
        c.add(PersonText);

        JLabel label1 = new JLabel("班次");
        label1.setBounds(150, 52, 100, 100);
        JLabel label2 = new JLabel("发车时间");
        label2.setBounds(150, 77, 500, 100);
        JLabel label8 = new JLabel("(格式：年-月-日&时:分:秒)");
        label8.setBounds(150, 140, 225, 15);
        JLabel label3 = new JLabel("起点站");
        label3.setBounds(150, 122, 500, 100);
        JLabel label4 = new JLabel("终点站");
        label4.setBounds(150, 147, 500, 100);
        JLabel label5 = new JLabel("行车时间");
        label5.setBounds(150, 172, 500, 100);
        JLabel label6 = new JLabel("载定额量");
        label6.setBounds(150, 197, 500, 100);
        JLabel label7 = new JLabel("已订票人数");
        label7.setBounds(150, 222, 500, 100);

        c.add(label1);
        c.add(label2);
        c.add(label3);
        c.add(label4);
        c.add(label5);
        c.add(label6);
        c.add(label7);
        c.add(label8);


        b1 = new JButton("录入");
        b1.setBounds(100, 300, 100, 30);
        b2 = new JButton("清除");
        b2.setBounds(200, 300, 100, 30);
        b3 = new JButton("退出");
        b3.setBounds(300, 300, 100, 30);

        b1.addActionListener(this);
//        b2.addActionListener(this);
        b3.addActionListener(this);

        c.add(b1);
//        c.add(b2);
        c.add(b3);

        this.setBounds(400, 100, 500, 500);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1){
            Train train = new Train();
            train.setId(Integer.parseInt(NumText.getText()));
            String time =TimeText.getText();
            String[] times = time.split("&");
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            try {
                train.setStartTimeDate(simpleDateFormat1.parse(times[0]));
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
            train.setStartTimeHour(times[1]);
            train.setStartStation(StartText.getText());
            train.setEndStation(EndText.getText());
            train.setRunTime(Integer.parseInt(DuringText.getText()));
            train.setCarryingCapacity(Integer.parseInt(MaxText.getText()));
            train.setBookNumber(Integer.parseInt(PersonText.getText()));
            boolean flag = Dao.addTrain(train);
            if (flag){
                JOptionPane.showMessageDialog(null, "录入成功");
            } else {
                JOptionPane.showMessageDialog(null, "录入失败");
            }
        }
        if (e.getSource() == b3){
            this.dispose();
        }
    }
}
