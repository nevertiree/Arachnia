package com.nostudy.business.university;

import com.nostudy.business.common.BaseDAO;
import sun.dc.pr.PRError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Lance on 7/15/16.
 */
public class UniversityDAO extends BaseDAO {

    public boolean insertUniversity(UniversityVO universityVO){
        Connection connection =null;

        try{//try to connect mysql with function connectMysql();
            connection=this.connectMysql();

            //edit the SQL query
            String insertQuery="replace into university (name,province,city) values(?,?,?)";
            PreparedStatement preparedStatement =connection.prepareStatement(insertQuery);
            //set the ? as the specific value with getMethod of VO
            preparedStatement.setString(1,universityVO.getSchoolname());
            preparedStatement.setString(2,universityVO.getProvince());
            preparedStatement.setString(3,"未知");
            //execute the SQL query
            int affectedCount = preparedStatement.executeUpdate();

            closeMysql(connection);

            //judge if success
            if (affectedCount>0){return true;}
            else return false;


        }catch (SQLException e){e.printStackTrace();}

        return false;
    }
}
