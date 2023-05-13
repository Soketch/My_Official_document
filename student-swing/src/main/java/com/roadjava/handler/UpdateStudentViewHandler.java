package com.roadjava.handler;

import com.roadjava.entity.StudentDO;
import com.roadjava.service.StudentService;
import com.roadjava.service.impl.StudentServiceImpl;
import com.roadjava.student.view.AddStudentView;
import com.roadjava.student.view.MainView;
import com.roadjava.student.view.UpdateStudentView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author zhaodaowen
 * @see <a href=wer>java</a>
 */
public class UpdateStudentViewHandler implements ActionListener {

    private UpdateStudentView updateStudentView;
    private MainView mainView;
    public UpdateStudentViewHandler(UpdateStudentView updateStudentView, MainView mainView) {
        this.updateStudentView = updateStudentView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("修改".equals(text)) {
            StudentService studentService = new StudentServiceImpl();
            StudentDO studentDO = updateStudentView.buildUpdatedStudentDO();
            boolean updateResult = studentService.update(studentDO);
            if (updateResult) {
                // 重新加载表格查到最新数据
                mainView.reloadTable();
                updateStudentView.dispose();
            } else {
                JOptionPane.showMessageDialog(updateStudentView,"修改失败");
            }
        }
    }
}
