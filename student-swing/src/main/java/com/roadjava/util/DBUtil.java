package com.roadjava.util;

import java.sql.*;

/**
 * @author zhaodaowen
 * @see <a href="http://wer">java</a>
 */
public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/student_gui?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER_NAME = "root";
    private static final String PWD = "root";

    static {
        try {
            // com.mysql.jdbc.Driver 静态代码块
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // 获取数据库连接
    public static Connection getConn() {
        try {
            return DriverManager.getConnection(URL,USER_NAME,PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConn(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closePs(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeRs(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
