package com.roadjava.student.view;

import com.roadjava.handler.LoginHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

/**
 * @author zhaodaowen
 * @see <a href="http://wer"></a>
 */
public class LoginView extends JFrame{
    JLabel nameLabel = new JLabel("java-swing项目",JLabel.CENTER);

    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    JLabel userNameLabel = new JLabel("用户名:");
    JTextField userTxt = new JTextField();
    JLabel pwdLabel = new JLabel("密码:");
    JPasswordField pwdField = new JPasswordField();
    JButton loginBtn = new JButton("登录");
    JButton resetBtn = new JButton("重置");

    SystemTray systemTray;
    TrayIcon trayIcon;
    LoginHandler loginHandler;
    public LoginView() {
        super("java学生成绩管理");

        loginHandler = new LoginHandler(this);

        Container contentPane = getContentPane();

        nameLabel.setFont(new Font("华文行楷",Font.PLAIN,40));
        nameLabel.setPreferredSize(new Dimension(0,80));

        Font centerFont = new Font("楷体", Font.PLAIN, 20);
        userNameLabel.setFont(centerFont);
        userTxt.setPreferredSize(new Dimension(200,30));
        pwdLabel.setFont(centerFont);
        pwdField.setPreferredSize(new Dimension(200,30));
        loginBtn.setFont(centerFont);
        resetBtn.setFont(centerFont);
        // 把组件加入面板
        centerPanel.add(userNameLabel);
        centerPanel.add(userTxt);
        centerPanel.add(pwdLabel);
        centerPanel.add(pwdField);
        loginBtn.addActionListener(loginHandler);
        // 增加按键事件
        loginBtn.addKeyListener(loginHandler);
        centerPanel.add(loginBtn);
        resetBtn.addActionListener(loginHandler);
        centerPanel.add(resetBtn);
        // 弹簧布局
        layoutCenter();


        contentPane.add(nameLabel,BorderLayout.NORTH);
        contentPane.add(centerPanel,BorderLayout.CENTER);

        if (SystemTray.isSupported()) {
            systemTray = SystemTray.getSystemTray();
            URL imgUrl = LoginView.class.getClassLoader().getResource("tray.png");
            trayIcon = new TrayIcon(new ImageIcon(imgUrl).getImage());
            // 设置托盘图片大小自动缩放
            trayIcon.setImageAutoSize(true);
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            // 增加最小化时销毁资源
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowIconified(WindowEvent e) {
                    LoginView.this.dispose();
                }
            });
            // 托盘事件监听
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int clickCount = e.getClickCount();
                    if (clickCount == 1) {
                        LoginView.this.setExtendedState(JFrame.NORMAL);
                    }
                    LoginView.this.setVisible(true);
                }
            });

        }

        // 设置loginBtn为默认按钮
        getRootPane().setDefaultButton(loginBtn);
        // 自定义图标
        URL imgUrl = LoginView.class.getClassLoader().getResource("xiaoji.png");
        setIconImage(new ImageIcon(imgUrl).getImage());
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void layoutCenter() {
        // 布局userNameLabel
        Spring childWidth = Spring.sum(Spring.sum(Spring.width(userNameLabel), Spring.width(userTxt)),
                Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST,userNameLabel,-offsetX,
                SpringLayout.HORIZONTAL_CENTER,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,userNameLabel,20,SpringLayout.NORTH,centerPanel);
        // userTxt
        springLayout.putConstraint(SpringLayout.WEST,userTxt,20,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,userTxt,0,SpringLayout.NORTH,userNameLabel);
        // pwdLabel
        springLayout.putConstraint(SpringLayout.EAST,pwdLabel,0,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdLabel,20,SpringLayout.SOUTH,userNameLabel);
        // pwdField
        springLayout.putConstraint(SpringLayout.WEST,pwdField,20,SpringLayout.EAST,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdField,0,SpringLayout.NORTH,pwdLabel);
        // loginBtn
        springLayout.putConstraint(SpringLayout.WEST,loginBtn,50,SpringLayout.WEST,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,loginBtn,20,SpringLayout.SOUTH,pwdLabel);
        // resetBtn
        springLayout.putConstraint(SpringLayout.WEST,resetBtn,50,SpringLayout.EAST,loginBtn);
        springLayout.putConstraint(SpringLayout.NORTH,resetBtn,0,SpringLayout.NORTH,loginBtn);
    }

    public static void main(String[] args) {
        new LoginView();
    }

    public JTextField getUserTxt() {
        return userTxt;
    }

    public JPasswordField getPwdField() {
        return pwdField;
    }

}
