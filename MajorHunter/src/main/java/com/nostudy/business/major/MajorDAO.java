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

    private MajorDAO(){}

    public static void insertMajor(List<MajorVO> majorVOs){

        Connection connection =null;

        try{
            //try to connect mysql with function connectMysql();
            connection=connectMysql();

            //edit the SQL query
            connection.setAutoCommit(false);
            String insertQuery= "replace into major (no,name,level,type,rank) values(?,?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(insertQuery);

            for (MajorVO vo:majorVOs){
                //set the ? as the specific value with getMethod of VO
                preparedStatement.setString(1,vo.getCode());
                preparedStatement.setString(2,vo.getSpecialname());
                preparedStatement.setString(3,vo.getZycengci());
                preparedStatement.setString(4,vo.getZytype());
                preparedStatement.setInt(5,vo.getRankingType());
                preparedStatement.addBatch();
            }

            connection.commit();

        }catch (Exception e){e.printStackTrace();}
        finally {closeMysql(connection);}
    }

    public static List<MajorVO> selectAllMajor(){

        List<MajorVO> majorVOs =new ArrayList<MajorVO>();

        Connection connection=null;

        try {//try to connect mysql with the connectMysql method
            connection=connectMysql();
            //prepare and execute SQL query
            String selectQuery="select * from major;";
            PreparedStatement preparedStatement=connection.prepareStatement(selectQuery);
            //attach the select result and trans into MajorVO
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                MajorVO majorVO=new MajorVO();
                majorVO.setCode(resultSet.getString("id_major"));
                majorVO.setSpecialname(resultSet.getString("name_major"));
                majorVO.setZycengci(resultSet.getString("level_major"));
                majorVO.setZytype(resultSet.getString("type_major"));
                majorVO.setRankingType(resultSet.getInt("rank_major"));
                majorVOs.add(majorVO);
            }
            return majorVOs;

        }catch (SQLException e){e.printStackTrace();}
        finally {closeMysql(connection);}
        return null;
    }

}
