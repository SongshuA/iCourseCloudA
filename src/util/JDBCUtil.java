package util;

import config.JDBCConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtil {
    private JDBCUtil() {}

    public static Connection getConnection(){
        Connection connection = null;

        try{
            Class.forName(JDBCConfig.driver);
            connection = DriverManager.getConnection(JDBCConfig.host, JDBCConfig.username, JDBCConfig.password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void free(ResultSet rs, Statement statement, Connection conn){
        try{
            if(rs != null)
                rs.close();

            if(statement != null)
                statement.close();

            if(conn != null)
                conn.close();

        } catch (SQLException e) {
            
            System.err.println("资源释放失败！");
        }

    }
}
