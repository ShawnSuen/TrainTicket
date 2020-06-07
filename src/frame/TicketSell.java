package frame;

import dao.Dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketSell extends JFrame implements ActionListener {

    JLabel SearchNum, BuyNum;
    JTextField NumSearch, NumBuy;
    JButton Sell;

    private String num, buyNum;
    private int Buynum;
    
    /**
     * @method
     * @date: 2020/6/6 10:54 下午
     * @Param: null
     * @return 
     * @description 售票界面
     */
    public TicketSell(){
        Container c = this.getContentPane();
        c.setLayout(null);


        SearchNum = new JLabel("请输入班次");
        SearchNum.setBounds(165, 100, 120, 30);
        NumSearch = new JTextField(15);
        NumSearch.setBounds(295, 100, 120, 30);

        BuyNum = new JLabel("请输入购票数量");
        BuyNum.setBounds(165, 150, 120, 30);
        NumBuy = new JTextField(15);
        NumBuy.setBounds(295, 150, 120, 30);

        Sell = new JButton("购票");
        Sell.setBounds(239, 200, 120, 30);

        c.add(SearchNum);
        c.add(NumSearch);
        c.add(BuyNum);
        c.add(NumBuy);
        c.add(Sell);

        Sell.addActionListener(this);

        this.setBounds(400, 100, 600, 400);
        this.setVisible(true);
        this.setTitle("售票");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Sell){
            int code = Dao.sellTicket(Integer.parseInt(NumSearch.getText()),Integer.parseInt(NumBuy.getText()));
            String message = null;
            if (code == -1) {
                message = "未找到该列车";
            } else if (code == 0){
                message = "未找到该列车";
            } else if (code == 1){
                message = "列车已发出";
            } else if (code == 2){
                message = "剩余票数不足，请重新选择购买数量";
            } else if (code == 3){
                message = "车票售罄";
            } else if (code == 4){
                message = "购票成功";
            } else {
                message = "信息有误";
            }
            JOptionPane.showMessageDialog(null,message);
        }
    }
}
