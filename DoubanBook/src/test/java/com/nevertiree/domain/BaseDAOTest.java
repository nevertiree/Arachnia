package com.nevertiree.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Lance Wang on 2016/12/29.
 */
public class BaseDAOTest {

    @Before
    public void setUp() throws Exception{
        BaseDAO baseDAO = new BaseDAO();
    }

    @Test
    public void testConnectMysql() throws Exception{
        assertEquals(null,BaseDAO.connectMysql());
    }
}
