package com.nostudy.business.major;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static com.nostudy.business.common.BaseDAO.closeMysql;
import static com.nostudy.business.common.BaseDAO.connectMysql;

/**
 * Created by Lance on 7/14/16.
 */
public class MajorDAOTest {

    @Before
    public void setup(){
    }


    @Test
    public void testInsertMajor(){
       /* MajorVO vo = new MajorVO("991-aaa", "none","本科","哲学",3);
        boolean success = MajorDAO.insertMajor(vo);
        Assert.assertEquals(success, true);

        vo = new MajorVO("991-aaa", "none","本科","哲学", 3);
        success = MajorDAO.insertMajor(vo);
        Assert.assertEquals(success, true);*/

    }

    @Test
    public void testQueryMajorIdByDetail(){
        Assert.assertEquals("010101",MajorDAO.queryMajorIdByDetail("哲学","哲学类"));
    }

    @Test
    public void testGetTotalNumOfSameMajorId(){
        Assert.assertEquals(1,MajorDAO.getTotalNumOfSameMajorId("010101"));
    }

    @After
    public void tearDown(){
        //closeMysql(connectMysql());
    }
}
