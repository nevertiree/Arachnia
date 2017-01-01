package com.nevertiree.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Lance Wang on 2016/12/29.
 */
public class BaseDAO {
    protected static Connection connection;
    static {
        connection = connectMysql();
    }

    public static Connection connectMysql(){
        try {
            //load the jdbc driver class of mysql
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/doubanBook?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root","123456");
        }catch (Exception e) {e.printStackTrace();}
        return null;
    }

    public static void closeMysql(Connection connection){
        if (connection != null){
            try {
                connection.close();
            }catch (SQLException e){e.printStackTrace();}
        }
    }


}
