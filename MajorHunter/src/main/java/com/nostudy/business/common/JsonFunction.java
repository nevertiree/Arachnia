package com.nostudy.business.common;

import com.nostudy.business.major.MajorVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Lance on 7/14/16.
 */
public class JsonFunction {

    //convert json to the string or the DAO
    public MajorVO jsonConvert(String jsonStringResult){

        Gson gson =new GsonBuilder().create();
        MajorVO majorVO =gson.fromJson(jsonStringResult,MajorVO.class);

        return majorVO;
    }

    //json to VO
}
