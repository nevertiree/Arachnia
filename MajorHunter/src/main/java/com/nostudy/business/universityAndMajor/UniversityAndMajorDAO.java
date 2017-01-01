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

    //insert school and major into University&Major table
    public static void insertUniversityAndMajorList(List<UniversityAndMajorVO> universityAndMajorVOs){

        try {
            connection.setAutoCommit(false);

            //prepare SQL query and execute it;
            String insertQuery="INSERT into universityAndMajor (schlNo,majorNo,majorName,majorType) values(?,?,?,?)";
            PreparedStatement preparedStatement =connection.prepareStatement(insertQuery);

            for (UniversityAndMajorVO universityAndMajorVO:universityAndMajorVOs) {
                preparedStatement.setInt(1,universityAndMajorVO.getSchoolid());
                preparedStatement.setString(2,universityAndMajorVO.getMajorid());
                preparedStatement.setString(3,universityAndMajorVO.getSpecialtyname());
                preparedStatement.setString(4,universityAndMajorVO.getSpecialtytype());
                preparedStatement.addBatch();
            }

            preparedStatement.addBatch();

            //judge if inserting successfully;

            connection.commit();
        }catch (SQLException e2){e2.printStackTrace();}
        finally{//closeMysql(connection);
        }
    }

    public static boolean insertUniversityAndMajorSingle(UniversityAndMajorVO universityAndMajorVO){
        boolean flag = false;
        try {
            //prepare SQL query and execute it;
            String insertQuery="INSERT into universityAndMajor (schlNo,majorNo,majorName,majorType) values(?,?,?,?)";
            PreparedStatement preparedStatement =connection.prepareStatement(insertQuery);

            preparedStatement.setInt(1,universityAndMajorVO.getSchoolid());
            preparedStatement.setString(2,universityAndMajorVO.getMajorid());
            preparedStatement.setString(3,universityAndMajorVO.getSpecialtyname());
            preparedStatement.setString(4,universityAndMajorVO.getSpecialtytype());

            int affectedCount=preparedStatement.executeUpdate();
            if (affectedCount > 0){
                flag = true;
                System.out.println("+++++插入成功UM+++++++");
            }else System.out.println("xxxxxxx插入UM失败xxxxxxx");

        }catch (SQLException e2){e2.printStackTrace();}
        finally{//closeMysql(connection);
        }
        return flag;
    }

}
