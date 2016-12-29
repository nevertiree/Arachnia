package com.nevertiree.domain;

import java.sql.Connection;

/**
 * Created by Lance Wang on 2016/12/29.
 */
public class BookDAO extends BaseDAO{

    public static boolean insertBook(BookVO bookVO){
        Connection connection = connectMysql();
        try {
            String sql = "insert into book ()";
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeMysql(connection);
        }
    }



}
