package com.nostudy.business.universityAndMajor;

import com.nostudy.business.common.BaseDAO;
import com.nostudy.business.major.MajorVO;
import com.nostudy.business.university.UniversityVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Lance on 7/16/16.
 */
public class UniversityAndMajorDAO extends BaseDAO {

    private UniversityAndMajorDAO(){}

    Connection connection =null;

    //insert school and major into University&Major table
    public static void insertUniversityAndMajor(List<UniversityAndMajorVO> universityAndMajorVOs){

        Connection connection=null;

        try {
            connection=connectMysql();
            connection.setAutoCommit(false);

            //prepare SQL query and execute it;
            String insertQuery="replace into universityAndMajor (schlNo,majorNo,majorName,majorType) values(?,?,?,?)";
            PreparedStatement preparedStatement =connection.prepareStatement(insertQuery);

            for (UniversityAndMajorVO universityAndMajorVO:universityAndMajorVOs) {
                preparedStatement.setString(1,universityAndMajorVO.getSchoolid());
                preparedStatement.setString(2,universityAndMajorVO.getMajorid());
                preparedStatement.setString(3,universityAndMajorVO.getSpecialtyname());
                preparedStatement.addBatch();
            }

            preparedStatement.addBatch();

            //judge if inserting successfully;

            connection.commit();
        }catch (SQLException e2){e2.printStackTrace();}
        finally{closeMysql(connection);}
    }


    //insert the extra major name to major table;
    public boolean insertMajor(MajorVO majorVO){
        return false;
    }

}
