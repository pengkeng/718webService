package Utils;

import java.sql.*;
import java.util.ResourceBundle;


/**
 * @author pengqiancheng
 * 数据库连接工具类
 */
public class JDBCUtils {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
    private static final String DRIVER = bundle.getString("driver");
    private static final String URL = bundle.getString("url2048");
    private static final String USER = bundle.getString("user");
    private static final String PASSWORD = bundle.getString("password");


    private JDBCUtils() {
    }

    static {

        /**
         * 驱动注册
         */
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }

    }

    /**
     * 获取 Connetion
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * 释放资源
     *
     * @param conn
     * @param st
     * @param rs
     */
    public static void colseResource(Connection conn, Statement st, ResultSet rs) {
        closeResultSet(rs);
        closeStatement(st);
        closeConnection(conn);
    }

    /**
     * 释放连接 Connection
     *
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //等待垃圾回收
        conn = null;
    }

    /**
     * 释放语句执行者 Statement
     *
     * @param st
     */
    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //等待垃圾回收
        st = null;
    }

    /**
     * 释放结果集 ResultSet
     *
     * @param rs
     */
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //等待垃圾回收
        rs = null;
    }
}
