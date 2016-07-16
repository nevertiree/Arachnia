package com.nostudy.business.major;

import com.nostudy.business.common.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 7/14/16.
 */
public class MajorDAO extends BaseDAO {

    public boolean insertMajor(MajorVO majorVO){
        Connection connection =null;

        try{//try to connect mysql with function connectMysql();
            connection=this.connectMysql();
            //edit the SQL query
            String insertQuery= "replace into major (id_major,name_major,level_major,type_major,rank_major) values(?,?,?,?,?)";
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
            if (affectedCount>0){return true;}

        }catch (Exception e){e.printStackTrace();}
        finally {this.closeMysql(connection);}
        return false;
    }

    public List<MajorVO> selectAllMajor(){
        List<MajorVO> majorVOs =new ArrayList<MajorVO>();

        Connection connection=null;

        try {//try to connect mysql with the connectMysql method
            connection=this.connectMysql();
            //prepare and execute SQL query
            String selectQuery="select * from major;";
            PreparedStatement preparedStatement=connection.prepareStatement(selectQuery);
            //attach the select result and trans into MajorVO
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                MajorVO majorVO=new MajorVO();
                majorVO.setCode(resultSet.getString(""));
                majorVO.setSpecialname(resultSet.getString(""));
                majorVO.setZycengci(resultSet.getString(""));
                majorVO.setZytype(resultSet.getString(""));
                majorVO.setRankingType(resultSet.getType());
                majorVOs.add(majorVO);
            }
            return majorVOs;

        }catch (SQLException e){e.printStackTrace();}
        finally {closeMysql(connection);}
        return null;
    }

}
