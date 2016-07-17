package com.nostudy.business.university;

import com.google.gson.Gson;
import com.nostudy.business.common.AnalysisContent;
import com.nostudy.business.common.GrabContent;
import com.nostudy.business.common.IsValueInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 7/15/16.
 */
public class UniversityOperator {

    public static void main(String[] args){
        UniversityOperator spider =new UniversityOperator();
        spider.downloadOperator();
    }

    public void downloadOperator(){

        //the base URL
        int pageNumber=1;
        String urlAllUniversity;

        try{//judge whether this page has valued infomation
            while (true){
                urlAllUniversity="http://data.api.gkcx.eol.cn/soudaxue/queryschool.html?messtype=jsonp&province=&schooltype=&page="+pageNumber+"&size=50&keyWord1=&schoolprop=&schoolflag=&schoolsort=&schoolid=&callback=jQuery1830041806086262917264_1468555273424&_=1468555274537.";

                String resultAllUniversity=GrabContent.grabWithJavaNet(urlAllUniversity);

                if (IsValueInfo.getInstance(resultAllUniversity,"school")){

                    List<UniversityVO> universityVOs =parseUniversityRespVO(parseJSON(AnalysisContent.parseJSONFormat(resultAllUniversity)));
                    parseUniversityVO(universityVOs);
                    pageNumber++;
                }

            }
        }catch (Exception e){e.printStackTrace();}
    }

    //input the row result and return the resp VO
    public UniversityRespVO parseJSON(String rowResult){

        //wrap the introduction, charging standard and other thing that may be null value for json.
        rowResult=rowResult.replaceAll("\"shoufei\":\\[ \\]*?\".*?\",","\"shoufei\":\"fuck\",");
        rowResult=rowResult.replaceAll("\"jianjie\":\\[ \\]*?\".*?\",","\"jianjie\":\"fuck\",");
        rowResult=rowResult.replaceAll("\\[\\]","\"fuck\"");

        //parse the json string to json value
        Gson gson = new Gson();
        return gson.fromJson(rowResult, UniversityRespVO.class);
    }

    //parse the MajorRespVO
    public List<UniversityVO> parseUniversityRespVO(UniversityRespVO universityRespVO){

        List<UniversityVO> resultUniversityVOs= new ArrayList<UniversityVO>();
        for (UniversityRowVO universityRowVO:universityRespVO.getSchool()){
            try {UniversityVO universityVO= new UniversityVO();
                universityVO.setSchoolid(universityRowVO.getSchoolid());
                universityVO.setSchoolname(universityRowVO.getSchoolname());
                universityVO.setProvince(universityRowVO.getProvince());
                universityVO.setSchooltype(universityRowVO.getSchooltype());
                universityVO.setSchoolproperty(universityRowVO.getSchoolproperty());
                universityVO.setEdudirectly(universityRowVO.getEdudirectly());
                universityVO.setMembership(universityRowVO.getMembership());
                universityVO.setF985(universityRowVO.getF985());
                universityVO.setF211(universityRowVO.getF211());
                universityVO.setLevel(universityRowVO.getLevel());
                universityVO.setSchoolnature(universityRowVO.getSchoolnature());

                resultUniversityVOs.add(universityVO);

            }catch (NullPointerException e){e.printStackTrace();}
        }

        return resultUniversityVOs;
    }

    //parse and insert the MajorVO
    public void parseUniversityVO(List<UniversityVO> universityVOs){
        for (UniversityVO vo : universityVOs){
            UniversityDAO universityDAO =new UniversityDAO();
            universityDAO.insertUniversity(vo);
        }
    }

}
