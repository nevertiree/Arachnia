package com.nostudy.business.major;

import com.nostudy.business.common.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Lance on 7/14/16.
 */
public class MajorDAO extends BaseDAO {

    public boolean insertMajor(MajorVO majorVO){
        Connection connection =null;

        try{//try to connect mysql with function connectMysql();
            connection=this.connectMysql();

            //edit the SQL query
            String insertQuery= "replace into major (codeMajor,nameMajor,levelMajor,typeMajor,rankMajor) values(?,?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(insertQuery);

            //set the ? as the specific value with getMethod of VO
            preparedStatement.setString(1,majorVO.getCode());
            preparedStatement.setString(2,majorVO.getSpecialname());
            preparedStatement.setString(3,majorVO.getZycengci());
            preparedStatement.setString(4,majorVO.getZytype());
            preparedStatement.setInt(5,majorVO.getRankingType());

            //execute the SQL query
            int affectedCount = preparedStatement.executeUpdate();

            //judge if success insert
            if (affectedCount>0){
                return true;
            }

        }catch (Exception e){
            //e.printStackTrace();
        }finally {
            this.closeMysql(connection);
        }
        return false;
    }

}
