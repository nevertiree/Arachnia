package com.nostudy.business.major;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Lance on 7/14/16.
 */
public class MajorDAOTest {

    @Before
    public void setup(){
    }


    @Test
    public void testInsertMajor(){
        MajorVO vo = new MajorVO("991-aaa", "none","本科","哲学",3);
        boolean success = MajorDAO.insertMajor(vo);
        Assert.assertEquals(success, true);

        vo = new MajorVO("991-aaa", "none","本科","哲学", 3);
        success = MajorDAO.insertMajor(vo);
        Assert.assertEquals(success, true);
    }

}
