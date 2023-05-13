package com.roadjava.student;

import javax.swing.*;
import java.awt.*;

/**
 * 弹簧布局SpringLayout:
 * 就是先定位好一个组件，其他组件都可以参照已经定位好的组件来定位即可
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public class SpringLayoutTest extends JFrame{

    // 设置jpanel的布局管理器为SpringLayout
    SpringLayout springLayout = new SpringLayout();
    JPanel jPanel = new JPanel(springLayout);

    JLabel titleLabel = new JLabel("文章标题:");
    JTextField titleTxt = new JTextField();
    JLabel authorLabel = new JLabel("作者:");
    JTextField authorTxt = new JTextField();
    JLabel contLabel = new JLabel("请输入内容:");
    JTextArea contArea = new JTextArea(4,10);
    public SpringLayoutTest() {
        super("弹簧布局SpringLayout");

        Container contentPane = getContentPane();
        // 加入到jpanel中
        jPanel.add(titleLabel);
        titleTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(titleTxt);
        jPanel.add(authorLabel);
        authorTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(authorTxt);
        jPanel.add(contLabel);
        jPanel.add(contArea);

        jPanel.setBackground(Color.green);
        /*
          SpringLayout:布局管理器
          SpringLayout.Constraints：使用弹簧布局的容器里面的组件的布局约束，每个组件对应一个
          Spring:可以理解为一个能够进行四则运算的整数
         */
        Spring titleLabelWidth = Spring.width(titleLabel);
        Spring titleTxtWidth = Spring.width(titleTxt);
        Spring spaceWidth = Spring.constant(20);
        Spring childWidth = Spring.sum(Spring.sum(titleLabelWidth, titleTxtWidth), spaceWidth);
        int offsetX = childWidth.getValue() / 2;

        SpringLayout.Constraints titleLabelC = springLayout.getConstraints(titleLabel);
//        titleLabelC.setX(Spring.constant(100));
        springLayout.putConstraint(SpringLayout.WEST,titleLabel,-offsetX,
                SpringLayout.HORIZONTAL_CENTER,jPanel);
        titleLabelC.setY(Spring.constant(50));

        /*
            设置约束的第一种写法，比较复杂
         */
        // 设置标题文本框：titleTxt西边距离titleLabel的东边20px，北边相同
        SpringLayout.Constraints titleTxtC = springLayout.getConstraints(titleTxt);
        // edgeName:东南西北   s:值
        Spring titleLabelEastSpring = titleLabelC.getConstraint(SpringLayout.EAST);
        titleTxtC.setConstraint(SpringLayout.WEST,Spring.sum(titleLabelEastSpring,Spring.constant(20)));
        titleTxtC.setConstraint(SpringLayout.NORTH,titleLabelC.getConstraint(SpringLayout.NORTH));

        /*
        设置约束的第二种写法，相对简单
        e1:要设置组件的哪个边界(edgeName)
        c1:要设置的组件
        pad：距离值
        e2:参照的组件的边界名
        c2:参考物(组件)
         */
        // 设置作者label :authorLabel.东边和titleLabel对齐，北边距离titleLabel南边20px
        springLayout.putConstraint(SpringLayout.EAST,authorLabel,0,SpringLayout.EAST,titleLabel);
        springLayout.putConstraint(SpringLayout.NORTH,authorLabel,20,SpringLayout.SOUTH,titleLabel);

        // 设置authorTxt：authorTxt西边距离authorLabel的东边20px，北边和authorLabel相同
        springLayout.putConstraint(SpringLayout.WEST,authorTxt,20,SpringLayout.EAST,authorLabel);
        springLayout.putConstraint(SpringLayout.NORTH,authorTxt,0,SpringLayout.NORTH,authorLabel);

        // contLabel
        springLayout.putConstraint(SpringLayout.EAST,contLabel,0,SpringLayout.EAST,titleLabel);
        springLayout.putConstraint(SpringLayout.NORTH,contLabel,20,SpringLayout.SOUTH,authorLabel);
        // contArea
        springLayout.putConstraint(SpringLayout.WEST,contArea,20,SpringLayout.EAST,contLabel);
        springLayout.putConstraint(SpringLayout.NORTH,contArea,0,SpringLayout.NORTH,contLabel);
        // contArea的南边和东边参照jpanel
        springLayout.putConstraint(SpringLayout.SOUTH,contArea,-20,SpringLayout.SOUTH,jPanel);
        springLayout.putConstraint(SpringLayout.EAST,contArea,-20,SpringLayout.EAST,jPanel);

        contentPane.add(jPanel);

        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);

        setVisible(true);
    }
    public static void main(String[] args) {
       new SpringLayoutTest();
    }
}
