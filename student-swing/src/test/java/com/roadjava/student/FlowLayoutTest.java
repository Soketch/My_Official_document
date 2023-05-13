package com.roadjava.student;

import javax.swing.*;
import java.awt.*;

/**
 * 流布局FlowLayout
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public class FlowLayoutTest extends JFrame{
    // jpanel默认布局方式就是flowlayout
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEADING,80,30));
    JButton jb1 = new JButton("测试按钮1");
    JButton jb2 = new JButton("测试按钮2");
    JButton jb3 = new JButton("测试按钮3333333");
    JButton jb4 = new JButton("测试按钮4");
    JButton jb5 = new JButton("测试按钮5");
    JButton jb6 = new JButton("测试按钮6");
    JButton jb7 = new JButton("测试按钮7");
    JButton jb8 = new JButton("测试按钮8");
    JButton jb9 = new JButton("测试按钮9");
    JButton jb10 = new JButton("测试按钮10");


    public FlowLayoutTest() {
        super("测试流布局");

        Container contentPane = getContentPane();

        jPanel.add(jb1);
        jPanel.add(jb2);
        jPanel.add(jb3);
        jPanel.add(jb4);
        jPanel.add(jb5);
        jPanel.add(jb6);
        jPanel.add(jb7);
        jPanel.add(jb8);
        jPanel.add(jb9);
        jPanel.add(jb10);
        contentPane.add(jPanel);

        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args) {
       new FlowLayoutTest();
    }
}
