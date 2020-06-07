package frame;

import Bean.Train;
import dao.Dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

public class TrainTicket extends JFrame implements ActionListener {

    JButton Add, ViewAll, Search, SellTicket,ReturnTicket, Close;

    /**
     * @method
     * @date: 2020/6/6 10:51 下午
     * @Param: null
     * @return
     * @description 车票系统主界面
     */
    public TrainTicket(){
        setBounds(400, 100, 700, 500);
        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);
        JLabel label = new JLabel("欢迎进入车票管理系统");
        label.setFont(new Font("BOLD", Font.BOLD, 28));
        panel.add(label);
        label.setBounds(200, 20, 400, 100);

        Add = new JButton("录入");
        panel.add(Add);
        Add.setBounds(50,200,80,50);

        ViewAll = new JButton("浏览");
        panel.add(ViewAll);
        ViewAll.setBounds(150,200,80,50);

        Search = new JButton("查询");
        panel.add(Search);
        Search.setBounds(250,200,80,50);

        SellTicket = new JButton("售票");
        panel.add(SellTicket);
        SellTicket.setBounds(350,200,80,50);

        ReturnTicket = new JButton("退票");
        panel.add(ReturnTicket);
        ReturnTicket.setBounds(450,200,80,50);

        Close = new JButton("退出");
        panel.add(Close);
        Close.setBounds(550,200,80,50);


        Add.addActionListener(this);
        ViewAll.addActionListener(this);
        Search.addActionListener(this);
        SellTicket.addActionListener(this);
        ReturnTicket.addActionListener(this);
        Close.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Add){
            TicketAdd ticketAdd = new TicketAdd();
        }
        if (e.getSource() == ViewAll){
            List<Train> trains = Dao.showAll();
            TicketViewAll ticketViewAll = new TicketViewAll(trains);
        }
        if (e.getSource() == Search){
            TicketSearch ticketSearch = new TicketSearch();
        }
        if (e.getSource() == SellTicket){
            TicketSell ticketSell = new TicketSell();
        }
        if (e.getSource() == ReturnTicket){
            TicketReturn ticketReturn = new TicketReturn();
        }
        if (e.getSource() == Close){
            System.exit(0);
        }
    }

    public static void main(String[] args) throws ParseException {
        TrainTicket trainTicket = new TrainTicket();
    }


}
