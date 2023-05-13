package com.roadjava.student.view.ext;

import com.roadjava.entity.StudentDO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @author zhaodaowen
 * @see <a href="http://wer">java</a>
 */
public class MainViewCellRender extends DefaultTableCellRenderer {
    // 在每一行的每一列显示之前都会调用
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (row % 2 == 0) {
            setBackground(Color.LIGHT_GRAY);//
        }
        else {
            setBackground(Color.WHITE);
        }

        setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}