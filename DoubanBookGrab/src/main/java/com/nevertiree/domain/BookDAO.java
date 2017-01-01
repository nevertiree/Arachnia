package com.nevertiree.domain;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Lance Wang on 2016/12/29.
 */
public class BookDAO extends BaseDAO{

    public static boolean insertBook(BookVO bookVO) throws SQLException{
        Connection connection = connectMysql();
        try {
            String sql = "replace into book (isbn,name,score,vote,rank5,rank4,rank3,rank2,rank1) values(?,?,?,?,?,?,?,?,?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,bookVO.getIsbn());
            ps.setString(2,bookVO.getName());
            ps.setDouble(3,bookVO.getScore());
            ps.setInt(4,bookVO.getVoteNum());
            ps.setDouble(5,bookVO.getScoreRank5());
            ps.setDouble(6,bookVO.getScoreRank4());
            ps.setDouble(7,bookVO.getScoreRank3());
            ps.setDouble(8,bookVO.getScoreRank2());
            ps.setDouble(9,bookVO.getScoreRank1());

            int affectedRow = ps.executeUpdate();

            return (affectedRow > 0);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeMysql(connection);
        }
        return false;
    }



}
