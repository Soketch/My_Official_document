package com.roadjava.student.view;

import com.roadjava.entity.StudentDO;
import com.roadjava.handler.LoginHandler;
import com.roadjava.handler.MainViewHandler;
import com.roadjava.req.StudentRequest;
import com.roadjava.res.TableDTO;
import com.roadjava.service.StudentService;
import com.roadjava.service.impl.StudentServiceImpl;
import com.roadjava.student.view.ext.MainViewTable;
import com.roadjava.student.view.ext.MainViewTableModel;
import com.roadjava.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.Vector;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public class MainView extends JFrame{
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn = new JButton("增加");
    JButton updateBtn = new JButton("修改");
    JButton delBtn = new JButton("删除");
    JTextField searchTxt = new JTextField(15);
    JButton searchBtn = new JButton("查询");
    JButton rankBtn = new JButton("排序");

    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");

    MainViewTable mainViewTable = new MainViewTable();
    private int pageNow = 1; // 当前是第几页
    private int pageSize = 10; // 一页显示多少条记录

    MainViewHandler mainViewHandler;
    public MainView() {
        super("主界面-java学生成绩管理");
        Container contentPane = getContentPane();
        Rectangle bounds = DimensionUtil.getBounds();
        pageSize = Math.floorDiv(bounds.height,35);

        mainViewHandler = new MainViewHandler(this);
        // 放置北边的组件
        layoutNorth(contentPane);
        // 设置中间的jtable
        layoutCenter(contentPane);

        //放置南边的组件
        layoutSouth(contentPane);

        // 自定义图标
        URL imgUrl = MainView.class.getClassLoader().getResource("xiaoji.png");
        setIconImage(new ImageIcon(imgUrl).getImage());
        // 根据屏幕大小设置主界面大小
        setBounds(bounds);
        // 设置窗体完全充满整个屏幕的可见大小
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    private void layoutCenter(Container contentPane) {
        TableDTO dto = getTableDTO();
        MainViewTableModel mainViewTableModel = MainViewTableModel.assembleModel(dto.getData());
        // 把jtable和model关联
        mainViewTable.setModel(mainViewTableModel);
        mainViewTable.renderRule();
        JScrollPane jScrollPane = new JScrollPane(mainViewTable);
        contentPane.add(jScrollPane,BorderLayout.CENTER);
        showPreNext(dto.getTotalCount());
    }

    private TableDTO getTableDTO() {
        StudentService studentService = new StudentServiceImpl();
        StudentRequest request = new StudentRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchTxt.getText().trim());
        TableDTO tableDTO = studentService.retrieveStudents(request);
        return tableDTO;
    }

    private void layoutSouth(Container contentPane) {
        preBtn.addActionListener(mainViewHandler);
        nextBtn.addActionListener(mainViewHandler);
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        contentPane.add(southPanel,BorderLayout.SOUTH);
    }
    /*
    设置上一页下一页是否可见
     */
    private void showPreNext(int totalCount) {
        if (pageNow == 1) {
            preBtn.setVisible(false);
        } else {
            preBtn.setVisible(true);
        }
        int pageCount = 0;//总共有多少页
        if (totalCount % pageSize == 0) {
            pageCount = totalCount / pageSize;
        } else {
            pageCount = totalCount / pageSize + 1;
        }
        if (pageNow == pageCount) {
            nextBtn.setVisible(false);
        } else {
            nextBtn.setVisible(true);
        }
    }

    private void layoutNorth(Container contentPane) {
        // 增加事件监听
        addBtn.addActionListener(mainViewHandler);
        updateBtn.addActionListener(mainViewHandler);
        delBtn.addActionListener(mainViewHandler);
        searchBtn.addActionListener(mainViewHandler);
        rankBtn.addActionListener(mainViewHandler);
        northPanel.add(addBtn);
        northPanel.add(updateBtn);
        northPanel.add(delBtn);
        northPanel.add(searchTxt);
        northPanel.add(searchBtn);
        northPanel.add(rankBtn);
        contentPane.add(northPanel,BorderLayout.NORTH);
    }


    public static void main(String[] args) {
        new MainView();
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void reloadTable() {
        TableDTO dto = getTableDTO();
        MainViewTableModel.updateModel(dto.getData());
        mainViewTable.renderRule();
        showPreNext(dto.getTotalCount());
    }



    public int[] getSelectedStudentIds() {
        int[] selectedRows = mainViewTable.getSelectedRows();
        int[] ids = new int[selectedRows.length];
        for (int i = 0; i < selectedRows.length ;i++) {
            int rowIndex = selectedRows[i];
            Object idObj = mainViewTable.getValueAt(rowIndex, 0);
            ids[i] = Integer.valueOf(idObj.toString());
        }
        return ids;
    }
}
