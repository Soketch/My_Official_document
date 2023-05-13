package com.roadjava.student;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * 边界布局BorderLayout:
 * 尽可能充满整个所在的容器
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public class BorderLayoutTest extends JFrame{

    JButton northBtn = new JButton("北边的按钮");
    JLabel southLabel = new JLabel("南边的label");
    JRadioButton westRadioBtn = new JRadioButton("男");
    JTextArea eastArea = new JTextArea("输入内容",10,20);
    JButton centerBtn = new JButton("中间的按钮");
    public BorderLayoutTest() {
        super("测试边界布局");

        Container contentPane = getContentPane();
        // 设置布局管理器
//        contentPane.setLayout(new BorderLayout());
        contentPane.add(northBtn,BorderLayout.NORTH);
        southLabel.setPreferredSize(new Dimension(0,80));
        contentPane.add(southLabel,BorderLayout.SOUTH);
        // 0:表示默认
        westRadioBtn.setPreferredSize(new Dimension(200,0));
        contentPane.add(westRadioBtn,BorderLayout.WEST);
        contentPane.add(eastArea,BorderLayout.EAST);
//        contentPane.add(centerBtn,BorderLayout.CENTER);
        contentPane.add(centerBtn);


        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args) {
       new BorderLayoutTest();
    }
}
