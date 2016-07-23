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

    public MajorDAO(){}

    public static void insertMajorList(List<MajorVO> majorVOs){
        try{
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
                preparedStatement.setInt(5,3);
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

            connection.commit();

        }catch (Exception e){e.printStackTrace();}
        finally {//closeMysql(connection);
             }
    }

    public static boolean insertMajorSingle(MajorVO vo){
        String insertQuery= "replace into major (no,name,level,type,rank) values(?,?,?,?,?)";

        boolean flag= false;

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(insertQuery);
            //set the ? as the specific value with getMethod of VO
            preparedStatement.setString(1,vo.getCode());
            preparedStatement.setString(2,vo.getSpecialname());
            preparedStatement.setString(3,vo.getZycengci());
            preparedStatement.setString(4,vo.getZytype());
            preparedStatement.setInt(5,vo.getRankingType());

            int affectedCount=preparedStatement.executeUpdate();

            if (affectedCount>0){
                flag=true;
                System.out.println("++++++插入Major成功++++++");
            }else System.out.println("xxxxxxx插入Major失败xxxxxxx");


        }catch (Exception e){e.printStackTrace();}
        finally {//closeMysql(connection);
        }
        return flag;
    }

    public static List<MajorVO> selectAllMajor(){

        List<MajorVO> majorVOs =new ArrayList<MajorVO>();

        try {//try to connect mysql with the connectMysql method
            //prepare and execute SQL query
            String selectQuery="select * from major";
            PreparedStatement preparedStatement=connection.prepareStatement(selectQuery);
            //attach the select result and trans into MajorVO
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                MajorVO majorVO=new MajorVO();
                majorVO.setCode(resultSet.getString("no"));
                majorVO.setSpecialname(resultSet.getString("name"));
                majorVO.setZycengci(resultSet.getString("level"));
                majorVO.setZytype(resultSet.getString("type"));
                majorVO.setRankingType(resultSet.getInt("rank"));
                majorVOs.add(majorVO);
            }
            //closeMysql(connection);
            return majorVOs;

        }catch (SQLException e){e.printStackTrace();}
        finally {//closeMysql(connection);
             }
        return null;
    }

    // 输入专业号码为教育部的6位编码，
    // 查询专业表的专业代码中，以此六位编码开头的专业数目
    // 规则：教育部电子商务编码010203
    // 爬虫搜到的第1个子专业：专业电子商务（运维）将以010203开头，后面拼接四位编码，即0102030001
    // 爬虫搜到的第2个子专业：专业电子商务（TAOBAO）将以010203开头，依次后面拼接四位编码，即0102030002
    // 以此类推，
    // 该方法用于确认即将新增的电子商务子专业后面新增的四位编码对应的整数值
    public static int getTotalNumOfSameMajorId(String majorId) {
        String sql="SELECT count(*) AS count FROM major WHERE NO regexp ? ";
        int count = -1;
        String paramValue = majorId;//regex更新
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, paramValue);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                count =  resultSet.getInt("count");
            }
        }catch (SQLException e){e.printStackTrace();}
        finally {
            //closeMysql(connection);
        }
        return count;
    }
    //查看有相同名字、层次、类别的专业有没有存在
    public static String queryMajorIdByDetail(String name,String type){
        ResultSet resultSet=null;
        String majorid = null;
        String queryMajorByDetail="SELECT no AS majorid FROM major WHERE name=? and type=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(queryMajorByDetail);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,type);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                majorid= resultSet.getString("majorid");
            }

        }catch (SQLException e){e.printStackTrace();}
        finally {
            //closeMysql(connection);
            return majorid;
        }

    }

}
