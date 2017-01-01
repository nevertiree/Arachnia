package com.nostudy.business.university;

import com.nostudy.business.common.BaseDAO;
import sun.dc.pr.PRError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Lance on 7/15/16.
 */
public class UniversityDAO extends BaseDAO {

    public boolean insertUniversity(UniversityVO universityVO){
        try{
            //edit the SQL query
            String insertQuery="replace into university (name,province,city) values(?,?,?)";
            PreparedStatement preparedStatement =connection.prepareStatement(insertQuery);
            //set the ? as the specific value with getMethod of VO
            preparedStatement.setString(1,universityVO.getSchoolname());
            preparedStatement.setString(2,universityVO.getProvince());
            preparedStatement.setString(3,"未知");
            //execute the SQL query
            int affectedCount = preparedStatement.executeUpdate();

            //closeMysql(connection);

            //judge if success
            if (affectedCount>0){return true;}
            else return false;


        }catch (SQLException e){e.printStackTrace();}

        return false;
    }

    public static int querySchlNoByName(String name){

        String selectQueryByName="select no from university where name = ? ";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(selectQueryByName);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return resultSet.getInt("no");
            }
        }catch (SQLException e){e.printStackTrace();}

        finally {
                //closeMysql(connection);
        }

        return -1;
    }
}
