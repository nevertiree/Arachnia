package com.nostudy.business.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Lance on 7/14/16.
 */
public class BaseDAO {

    protected BaseDAO(){}

    public static Connection connectMysql(){
        try {
            //load the jdbc driver class of mysql
            Class.forName("com.mysql.jdbc.Driver");
//            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Spider",
                    "root","0512");
        }catch (Exception e) {e.printStackTrace();}
        return null;
    }

    public static void closeMysql(Connection connection){
        if (connection!=null){
            try {
                connection.close();
            }catch (SQLException e){e.printStackTrace();}
        }
    }


}
