package com.haoze.javaweb.mysql;

import java.sql.*;

/**
 * @author 寿豪泽
 */
public class Method {
    public static void close(Connection conn,PreparedStatement ps,ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }if (ps != null) {
            try {
                ps.close();
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
