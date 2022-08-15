package util;

import java.sql.*;
import java.util.ResourceBundle;


/**
 * 数据库工具类
 * @author 寿豪泽
 */
public class DbUtil {
    private DbUtil(){}
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("properties/db");

    // 注册驱动
    static {
        try {
            Class.forName(BUNDLE.getString("driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     * @return 新的连接对象
     */
    public static Connection getConnection() throws SQLException {
        String url = BUNDLE.getString("url");
        String user = BUNDLE.getString("user");
        String password = BUNDLE.getString("password");
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 释放资源
     * @param conn 连接对象
     * @param stmt 数据库操作对象
     * @param rs 查询结果集
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
