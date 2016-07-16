package com.nostudy.business.universityOfMajor;

import com.nostudy.business.common.BaseDAO;
import com.nostudy.business.major.MajorVO;

import java.sql.Connection;

/**
 * Created by Lance on 7/16/16.
 */
public class UniversityOfMajorDAO extends BaseDAO {

    Connection connection =null;

    //insert the extra major name to major table;
    public boolean insertMajor(MajorVO majorVO){
        return false;
    }

    //insert the whole info from the URL to the universityOfMajor table
}
