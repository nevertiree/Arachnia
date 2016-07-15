package com.nostudy.business.university;

import com.nostudy.business.common.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Lance on 7/15/16.
 */
public class UniversityDAO extends BaseDAO {

    public boolean insertUniversity(){
        Connection connection =null;

        try{//try to connect mysql with function connectMysql();
            connection=this.connectMysql();

            //edit the SQL query
            String insertQuery="replace into university () values()";
            PreparedStatement preparedStatement


        }catch (SQLException e){e.printStackTrace();}

    }
}
