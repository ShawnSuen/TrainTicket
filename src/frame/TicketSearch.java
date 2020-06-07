package frame;

import Bean.Train;
import dao.Dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TicketSearch extends JFrame implements ActionListener {


    private String Num, End;

    JButton SearchNum, SearchEnd;
    JTextField NumSearch,EndSearch;

    /**
     * @method
     * @date: 2020/6/6 10:55 下午
     * @Param: null
     * @return
     * @description 查询界面
     */
    public TicketSearch() {
        Container c = this.getContentPane();
        c.setLayout(null);


        SearchNum = new JButton("按班次查询");
        SearchNum.setBounds(165, 100, 120, 30);
        SearchEnd = new JButton("按终点站查询");
        SearchEnd.setBounds(165, 150, 120, 30);

        SearchNum.addActionListener(this);
        SearchEnd.addActionListener(this);
        NumSearch = new JTextField(15);
        NumSearch.setBounds(295, 100, 120, 30);
        EndSearch = new JTextField(15);
        EndSearch.setBounds(295, 150, 120, 30);
        c.add(SearchNum);
        c.add(NumSearch);
        c.add(SearchEnd);
        c.add(EndSearch);



        this.setBounds(200, 200, 600, 400);
        this.setVisible(true);
        this.setTitle("录入班次信息");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Train> trains = null;
        if (e.getSource() == SearchNum && (NumSearch.getText() != null)){
             trains = Dao.searchTrain(Integer.parseInt(NumSearch.getText()),EndSearch.getText());
        }
        if (e.getSource() == SearchEnd){
            trains = Dao.searchTrain(0,EndSearch.getText());
        }
        if (trains.size() >= 1){
            TicketViewAll ticketViewAll = new TicketViewAll(trains);
        } else {
            JOptionPane.showMessageDialog(null,"未查询到此列车");
        }

    }
}
