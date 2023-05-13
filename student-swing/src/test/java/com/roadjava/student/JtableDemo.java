package com.roadjava.student;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.Vector;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public class JtableDemo extends JFrame{

    public JtableDemo(){
        super("测试jtable");
        
        Vector<Vector<Object>> data = new Vector<>();
        
        Vector<Object> rowVector1 = new Vector<>();
        rowVector1.addElement("1");
        rowVector1.addElement("张三");
        rowVector1.addElement("no1");
        rowVector1.addElement("甘肃");
        rowVector1.addElement("1");
        rowVector1.addElement("2");
        rowVector1.addElement("3");
        rowVector1.addElement("6");


        Vector<Object> rowVector2 = new Vector<>();
        rowVector2.addElement("2");
        rowVector2.addElement("李四");
        rowVector2.addElement("no2");
        rowVector2.addElement("青海");
        rowVector2.addElement("2");
        rowVector2.addElement("2");
        rowVector2.addElement("3");
        rowVector2.addElement("7");


        Vector<Object> rowVector3 = new Vector<>();
        rowVector3.addElement("3");
        rowVector3.addElement("roadjava");
        rowVector3.addElement("no3");
        rowVector3.addElement("杭州");
        rowVector3.addElement("3");
        rowVector3.addElement("2");
        rowVector3.addElement("3");
        rowVector3.addElement("8");

        data.addElement(rowVector1);
        data.addElement(rowVector2);
        data.addElement(rowVector3);

        // tablemodel:和jtable关联后，之后只需要更新model就能把数据的变化反应到jtable中
        StudentTableModel studentTableModel = StudentTableModel.assembleModel(data);
        // jtable和table关联
        JTable jTable = new JTable(studentTableModel);
        // 设置表头
        JTableHeader tableHeader = jTable.getTableHeader();
        tableHeader.setFont(new Font(null,Font.BOLD,16));
        tableHeader.setForeground(Color.RED);
        // 设置表格体
        jTable.setFont(new Font(null,Font.PLAIN,14));
        jTable.setForeground(Color.black);
        jTable.setGridColor(Color.BLACK);
        jTable.setRowHeight(30);
        // 设置多行选择
        jTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // 设置表格列的渲染方式
        Vector<String> columns = StudentTableModel.getColumns();
        StudentCellRender render = new StudentCellRender();
        for (int i =0;i < columns.size();i++) {
            TableColumn column = jTable.getColumn(columns.get(i));
            column.setCellRenderer(render);
            if (i == 0) {
                column.setPreferredWidth(50);
                column.setMaxWidth(50);
                column.setResizable(false);
            }
        }



        Container contentPane = getContentPane();
        // jtable放在jpanel上的话，默认是不展示列头的，需要特殊设置。放在JScrollPane上面
        // 默认是展示列头的
        JScrollPane jScrollPane = new JScrollPane(jTable);
        contentPane.add(jScrollPane);


        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args) {
        new JtableDemo();
    }
}

class StudentCellRender extends DefaultTableCellRenderer {
    // 在每一行的每一列显示之前都会调用
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (row % 2 == 0) {
            setBackground(Color.LIGHT_GRAY);
        } else {
            setBackground(Color.WHITE);
        }
        setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}

// 自定义tablemodel
class StudentTableModel extends DefaultTableModel {

    static Vector<String> columns = new Vector<>();
    static {
        columns.addElement("编号");
        columns.addElement("姓名");
        columns.addElement("学号");
        columns.addElement("家乡");
        columns.addElement("语文");
        columns.addElement("数学");
        columns.addElement("英语");
        columns.addElement("总分");
    }

    private StudentTableModel() {
        super(null,columns);
    }

    private static StudentTableModel studentTableModel = new StudentTableModel();

    public static StudentTableModel assembleModel(Vector<Vector<Object>> data) {
        studentTableModel.setDataVector(data,columns);
        return studentTableModel;
    }

    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}