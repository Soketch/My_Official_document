package com.roadjava.handler;

import com.roadjava.entity.AdminDO;
import com.roadjava.service.AdminService;
import com.roadjava.service.impl.AdminServiceImpl;
import com.roadjava.student.view.LoginView;
import com.roadjava.student.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author zhaodaowen
 * @see <a href="http://wer">java</a>
 */
public class LoginHandler extends KeyAdapter implements ActionListener {

    private LoginView loginView;
    public LoginHandler(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("重置".equals(text)) {
            loginView.getUserTxt().setText("");
            loginView.getPwdField().setText("");
        }else if ("登录".equals(text)) {
            login();
        }
    }

    private void login() {
        String user = loginView.getUserTxt().getText();
        char[] chars = loginView.getPwdField().getPassword();
        if (user == null || "".equals(user.trim()) ||
                chars == null) {
            JOptionPane.showMessageDialog(loginView,"用户名密码必填");
            return;
        }
        String pwd = new String(chars);
        System.out.println(user + ":" + pwd);
        // 查询db
        AdminService adminService = new AdminServiceImpl();
        AdminDO adminDO = new AdminDO();
        adminDO.setUserName(user);
        adminDO.setPwd(pwd);
        boolean flag = adminService.validateAdmin(adminDO);
        if (flag) {
            // 跳转到主界面并销毁登录界面
            new MainView();
            loginView.dispose();
        }else {
            JOptionPane.showMessageDialog(loginView,"用户名密码错误");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (KeyEvent.VK_ENTER == e.getKeyCode()) {
            login();
        }
    }
}
