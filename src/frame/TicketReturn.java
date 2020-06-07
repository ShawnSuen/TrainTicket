package frame;

import dao.Dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketReturn extends JFrame implements ActionListener {
    JLabel SearchNum, ReturnNum;
    JTextField NumSearch, NumReturn;
    JButton Return;

    private String num, returnNum;
    private int Returnnum;

    /**
     * @method
     * @date: 2020/6/6 10:55 下午
     * @Param: null
     * @return
     * @description 退票界面
     */
    public TicketReturn() {
        Container c = this.getContentPane();
        c.setLayout(null);


        SearchNum = new JLabel("请输入班次");
        SearchNum.setBounds(165, 100, 120, 30);
        NumSearch = new JTextField(15);
        NumSearch.setBounds(295, 100, 120, 30);

        ReturnNum = new JLabel("请输入退票数量");
        ReturnNum.setBounds(165, 150, 120, 30);
        NumReturn = new JTextField(15);
        NumReturn.setBounds(295, 150, 120, 30);

        Return = new JButton("退票");
        Return.setBounds(239, 200, 120, 30);

        Return.addActionListener(this);
        c.add(SearchNum);
        c.add(NumSearch);
        c.add(ReturnNum);
        c.add(NumReturn);
        c.add(Return);


        this.setBounds(200, 200, 600, 400);
        this.setVisible(true);
        this.setTitle("退票");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Return){
            int code = Dao.returnTicket(Integer.parseInt(NumSearch.getText()),Integer.parseInt(NumReturn.getText()));
            String message = null;
            if (code == 0){
                message = "请输入班次";
            } else if (code == 1) {
                message = "退票成功";
            } else if (code == 2){
                message = "退票数量大于售出数量";
            } else {
                message = "输入信息有误";
            }
            JOptionPane.showMessageDialog(null,message);
        }
    }
}
