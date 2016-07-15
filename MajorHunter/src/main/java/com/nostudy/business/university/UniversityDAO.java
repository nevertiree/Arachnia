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
            String insertQuery="replace into university (id_university,name_university,province_university,type_university,property_university,is_eduMinistry_direct,attribute_university,is_985,is_211,level_university,nature_university) values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement =connection.prepareStatement(insertQuery);

            //set the ? as the specific value with getMethod of VO

            preparedStatement.setString(1,universityVO.getSchoolid());
            preparedStatement.setString(2,universityVO.getSchoolname());
            preparedStatement.setString(3,universityVO.getProvince());
            preparedStatement.setString(4,universityVO.getSchooltype());
            preparedStatement.setString(5,universityVO.getSchoolproperty());
            preparedStatement.setInt(6,universityVO.getEdudirectly());
            preparedStatement.setString(7,universityVO.getMembership());
            preparedStatement.setInt(8,universityVO.getF985());
            preparedStatement.setInt(9,universityVO.getF211());
            preparedStatement.setString(10,universityVO.getLevel());
            preparedStatement.setString(11,universityVO.getSchoolnature());

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
