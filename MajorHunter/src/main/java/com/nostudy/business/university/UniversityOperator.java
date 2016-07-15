package com.nostudy.business.university;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.nostudy.business.common.AnalysisContent;
import com.nostudy.business.common.GrabContent;

import javax.swing.text.AbstractDocument;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jdk.nashorn.internal.objects.NativeString.trim;

/**
 * Created by Lance on 7/15/16.
 */
public class UniversityOperator {

    public static void main(String[] args){
        UniversityOperator spider =new UniversityOperator();
        spider.operator();
    }

    public void operator(){

        //get the origin content from data.api
        GrabContent grabContent =new GrabContent();

        //the base URL
        int pageNumber=1;
        String urlAllUniversity;

        AnalysisContent analysisContent=new AnalysisContent();

        try{//judge whether this page has valued infomation
            while (true){
                urlAllUniversity="http://data.api.gkcx.eol.cn/soudaxue/queryschool.html?messtype=jsonp&province=&schooltype=&page="+pageNumber+"&size=50&keyWord1=&schoolprop=&schoolflag=&schoolsort=&schoolid=&callback=jQuery1830041806086262917264_1468555273424&_=1468555274537.";

                String resultAllUniversity=grabContent.grabWithJavaNet(urlAllUniversity);

                if (analysisContent.valuedContent(resultAllUniversity,"school")){
                    List<UniversityVO> universityVOs =parseUniversityRespVO(parseJSON(resultAllUniversity));
                    parseUniversityVO(universityVOs);
                    pageNumber++;
                }

            }
        }catch (Exception e){e.printStackTrace();}
    }

    //input the row result and return the resp VO
    public UniversityRespVO parseJSON(String rowResult){

        //get the core JSON string
       /* String jsonStr;*/
        Gson gson = new Gson();
/*
        int startSite = rowResult.indexOf("(");
        if (startSite  != -1) {
            int end = rowResult.indexOf(")");
            jsonStr = rowResult.substring(startSite + 1, end);

            *//*Pattern patternShoufei =Pattern.compile("\"shoufei\":\".*?\",");
            Matcher matcherShoufei =patternShoufei.matcher(jsonStr);
            matcherShoufei.replaceAll("\"shoufei\":\"fuck\",");

            Pattern patternJianjie=Pattern.compile("\"jianjie\":\".*?\",\"");
            Matcher matcherJianjie=patternShoufei.matcher(jsonStr);
            matcherJianjie.replaceAll("\"jianjie\":\"fuck\",");*//*

            jsonStr=jsonStr.replaceAll("\"shoufei\": \".*?\",","\"shoufei\": \"fuck\",");
            jsonStr=jsonStr.replaceAll("\"jianjie\": \".*?\",","\"jianjie\": \"fuck\",");
            jsonStr=jsonStr.replaceAll("\\[\\]","\"fuck\"");
            jsonStr=jsonStr.replaceAll("\\s","");

        }else return null;

        //parse the Json string*/

        rowResult=rowResult.replaceAll("\\s","");

        rowResult=rowResult.replaceAll("[\\w]*\\(\\{","{");
        rowResult=rowResult.replaceAll("\\}[\\s]*][\\s]*}\\);","\\}\\]\\}");

        rowResult=rowResult.replaceAll("\"shoufei\":\".*?\",","\"shoufei\":\"fuck\",");
        rowResult=rowResult.replaceAll("\"jianjie\":\".*?\",","\"jianjie\":\"fuck\",");
        rowResult=rowResult.replaceAll("\\[\\]","\"fuck\"");

        UniversityRespVO universityRespVO = gson.fromJson(rowResult, UniversityRespVO.class);

        return universityRespVO;
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
