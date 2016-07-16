package com.nostudy.business.universityAndMajor;

import com.google.gson.Gson;
import com.nostudy.business.common.AnalysisContent;
import com.nostudy.business.common.GrabContent;
import com.nostudy.business.major.MajorDAO;
import com.nostudy.business.major.MajorVO;
import com.nostudy.business.university.UniversityRespVO;
import com.nostudy.business.universityDetail.UniversityDetailDAO;
import com.nostudy.business.universityDetail.UniversityDetailVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Lance on 7/16/16.
 */
public class UniversityAndMajorOperator {


    public static void main(String[] args){
        UniversityAndMajorOperator op=new UniversityAndMajorOperator();
        op.downloadOperator();
    }


    public void downloadOperator(){

        GrabContent grabContent =new GrabContent();
        AnalysisContent analysisContent=new AnalysisContent();

        List<String> nameMajors=getMajorNames();

        int seconds = 0;
        Random random = new Random();
        try{
            for (String nameMajor:nameMajors) {
                int pageNumber=1;
                String result = null;
                while (true){
                    String url="http://data.api.gkcx.eol.cn/soudaxue/querySchoolSpecialty.html?messtype=jsonp&zycengci=&page="+pageNumber+"&size=10&keyWord1="+nameMajor+"&province=&schooltype=&schoolprop=&callback=jQuery18307610225111401336_1468630663527&_=1468630664312";

                    result =grabContent.grabWithJavaNet(url);
                    if (analysisContent.valuedContent(result,"school")){
                        //First move header and tail
                        result=analysisContent.parseJSONFormat(result);

                        List<UniversityAndMajorVO> vos =praseUniversityAndMajorRespVO((parseJSON(result)));
                        praseUniversityAndMajorVO(vos);

                        pageNumber++;
                    }else break;
                    seconds = (random.nextInt(3) + 2)*1000;
                    Thread.currentThread().sleep(seconds );
                }
            }


        }catch (Exception e){e.printStackTrace();}
    }

    //attach info from major table
    public List<String> getMajorNames(){
        MajorDAO majorDAO=new MajorDAO();
        List<MajorVO> majorVOs=majorDAO.selectAllMajor();
        List<String> nameMajors = new ArrayList<>();

        for (MajorVO majorVO:majorVOs){
            nameMajors.add(majorVO.getSpecialname());}

        return nameMajors;
    }

    //input the row result and return the resp VO
    public UniversityAndMajorRespVO parseJSON(String rowResult){

        rowResult=rowResult.replaceAll("\\(","【");
        rowResult=rowResult.replaceAll("\\)","】");
        //deal with the () problem
        rowResult=rowResult.replaceAll("【\\{[\\s]*?\"","\\(");
        rowResult=rowResult.replaceAll("\\}[\\s]*?\\][\\s]*?\\}】;","\\)");

        rowResult=rowResult.replaceAll("\\[\\]","\"fuck\"");

        //parse the json string to json value
        Gson gson = new Gson();
        //return University
        return gson.fromJson(rowResult, UniversityAndMajorRespVO.class);
    }

    //trans UniversityAndMajorResp into UniversityRow
    public List<UniversityAndMajorVO> praseUniversityAndMajorRespVO(UniversityAndMajorRespVO universityAndMajorRespVO ){

        List<UniversityAndMajorVO>  universityAndMajorVOs=new ArrayList<>();

        for (UniversityAndMajorRowVO sourceVO :universityAndMajorRespVO.getSchool()){
            try{
                UniversityAndMajorVO vo=new UniversityAndMajorVO();
                vo.setSchoolid(sourceVO.getSchoolid());
                vo.setSchoolname(sourceVO.getSchoolname());
                vo.setSpecialtyname(sourceVO.getSpecialtyname());
                vo.setSpecialtytype(sourceVO.getSpecialtytype());
                vo.setEdudirectly(sourceVO.getEdudirectly());
                vo.setF985(sourceVO.getF985());
                vo.setF211(sourceVO.getF211());
                vo.setSchoolprovince(sourceVO.getSchoolprovince());
                universityAndMajorVOs.add(vo);
            }catch (Exception e){e.printStackTrace();}
        }
        return universityAndMajorVOs;
    }

    //parse UniversityAndMajorVO into DAO and insert them
    public void praseUniversityAndMajorVO(List<UniversityAndMajorVO> vos){
        for (UniversityAndMajorVO vo:vos){
            UniversityAndMajorDAO dao =new UniversityAndMajorDAO();
            dao.insertUniversityAndMajor(vo);
        }
    }

}
