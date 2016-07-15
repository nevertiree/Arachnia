package com.nostudy.business.major;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Lance on 7/14/16.
 */
public class MajorDAOTest {

    MajorDAO dao;

    @Before
    public void setup(){
        dao = new MajorDAO();
    }


    @Test
    public void testInsertMajor(){
        MajorVO vo = new MajorVO("991-aaa", "none", 3);
        boolean success = dao.insertMajor(vo);
        Assert.assertEquals(success, true);

        vo = new MajorVO("991-aaa", "none", 3);
        success = dao.insertMajor(vo);
        Assert.assertEquals(success, true);
    }

    @After
    public void tearDown(){
        dao = null;
    }
}
