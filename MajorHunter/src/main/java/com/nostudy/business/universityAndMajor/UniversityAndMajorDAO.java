package com.nostudy.business.universityAndMajor;

import com.nostudy.business.common.BaseDAO;
import com.nostudy.business.major.MajorVO;
import com.nostudy.business.university.UniversityVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Lance on 7/16/16.
 */
public class UniversityAndMajorDAO extends BaseDAO {

    Connection connection =null;

    //insert school and major into University&Major table
    public boolean insertUniversityAndMajor(UniversityAndMajorVO universityAndMajorVO){

        Connection connection=null;

        try {connection=this.connectMysql();

            //prepare SQL query and execute it;
            String insertQuery="replace into universityAndMajor (id_university,name_university,name_major,province_university,type_major,is_eduMinistry_direct,is_985,is_211) values(?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement =connection.prepareStatement(insertQuery);
            preparedStatement.setString(1,universityAndMajorVO.getSchoolid());
            preparedStatement.setString(2,universityAndMajorVO.getSchoolname());
            preparedStatement.setString(3,universityAndMajorVO.getSpecialtyname());
            preparedStatement.setString(4,universityAndMajorVO.getSchoolprovince());
            preparedStatement.setString(5,universityAndMajorVO.getSpecialtytype());
            preparedStatement.setInt(6,universityAndMajorVO.getEdudirectly());
            preparedStatement.setInt(7,universityAndMajorVO.getF985());
            preparedStatement.setInt(8,universityAndMajorVO.getF211());

            //judge if inserting successfully;
            int affectedCount=preparedStatement.executeUpdate();
            if (affectedCount>0) return  true;

        }catch (SQLException e2){e2.printStackTrace();}
        finally{closeMysql(connection);}
        return false;
    }


    //insert the extra major name to major table;
    public boolean insertMajor(MajorVO majorVO){
        return false;
    }

}
