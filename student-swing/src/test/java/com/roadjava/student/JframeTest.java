package com.roadjava.student;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public class JframeTest extends JFrame{
    JButton jButton;

    public JframeTest () {
        // 容器组件:jframe,jpanel,jscrollpane    非容器组件:jbutton,jlabel,jtextfield....
        super("这是frame的标题");

        jButton = new JButton("这是一个按钮");
        Container contentPane = getContentPane();
        contentPane.add(jButton);

        // 设置窗体图标
        URL resource = JframeTest.class.getClassLoader().getResource("xiaoji.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);
        setSize(600,400);//单位：px
        // 居中
        //1.
//        setLocationRelativeTo(null);
        // 2.自己计算位置来居中
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int offsetX = (screenSize.width - 600) / 2;
        int offsetY = (screenSize.height - 400) / 2;
        setLocation(offsetX,offsetY);
        // 关闭退出程序
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // 大小不可改变
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args) {
       new JframeTest();
    }
}
