package frame;

import dao.Dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketDelete extends JFrame implements ActionListener {
    JButton Delete;
    JTextField DeleteTxt;
    public TicketDelete(){
        Container c = this.getContentPane();
        c.setLayout(null);

        JLabel temp = new JLabel("请输入班次");
        temp.setBounds(150,100,120,30);
        DeleteTxt = new JTextField(15);
        DeleteTxt.setBounds(250,100,120,30);

        Delete = new JButton("删除");
        Delete.setBounds(250,150,100,30);

        Delete.addActionListener(this);
        c.add(temp);
        c.add(DeleteTxt);
        c.add(Delete);

        this.setBounds(200, 200, 600, 400);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = false;
        if (e.getSource() == Delete){
            try{
            if (DeleteTxt != null){
                flag = Dao.deleteTrain(Integer.parseInt(DeleteTxt.getText()));
                if (flag){
                    JOptionPane.showMessageDialog(null,"删除列车成功");
                } else {
                    JOptionPane.showMessageDialog(null,"删除失败","失败",JOptionPane.ERROR_MESSAGE);
                }
            }
            } catch (Exception ee){
                JOptionPane.showMessageDialog(null,"请输入正确的班次信息","警告",JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
