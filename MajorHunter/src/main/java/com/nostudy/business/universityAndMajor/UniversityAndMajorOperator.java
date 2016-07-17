package com.nostudy.business.universityAndMajor;

import com.google.gson.Gson;
import com.nostudy.business.common.AnalysisContent;
import com.nostudy.business.common.GrabContent;
import com.nostudy.business.common.IsValueInfo;
import com.nostudy.business.major.MajorDAO;
import com.nostudy.business.major.MajorOperator;
import com.nostudy.business.major.MajorVO;

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

        List<MajorVO> originMajor=getOriginMajor();
        MajorOperator majorOperator=new MajorOperator();

        int seconds = 0;
        Random random = new Random();
        try{
            for (MajorVO majorVO:originMajor) {
                int pageNumber=1;
                String result = null;
                while (true){
                    String url="http://data.api.gkcx.eol.cn/soudaxue/querySchoolSpecialty.html?messtype=jsonp&zycengci=&page="+pageNumber+"&size=10&keyWord1="+majorVO.getSpecialname()+"&province=&schooltype=&schoolprop=&callback=jQuery18307610225111401336_1468630663527&_=1468630664312";

                    try {result =GrabContent.grabWithJavaNet(url);
                        if (
                            IsValueInfo.getInstance(url,"school")){
                            //First move header and tail
                            result=AnalysisContent.parseJSONFormat(result);
                            List<UniversityAndMajorVO> vos =praseUniversityAndMajorRespVO((parseJSON(result)));

                            praseUniversityAndMajorVO(vos);
                            majorOperator.parseMajorVO(analysisDiffMajor(majorVO,vos));
                            pageNumber++;

                            seconds = random.nextInt(2)*1000;
                            Thread.currentThread().sleep(seconds);
                        }else break;

                    }catch (Exception e){
                        continue;
                    }
                }
            }


        }catch (Exception e){e.printStackTrace();}
    }

    //attach info from major table
    public List<MajorVO> getOriginMajor(){
        return MajorDAO.selectAllMajor();
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

    //analysis the different name major
    public List<MajorVO> analysisDiffMajor(MajorVO originMajorVO, List<UniversityAndMajorVO> universityAndMajorVOs) {

        List<MajorVO> addMajorVOs = new ArrayList<>();

        int hashCode = 1;

        for (UniversityAndMajorVO universityAndMajorVO : universityAndMajorVOs) {
            if (universityAndMajorVO.getSpecialtyname() !=originMajorVO.getSpecialname()) {
                MajorVO mvo =new MajorVO();
                mvo.setCode(originMajorVO.getCode()+hashCode);
                mvo.setSpecialname(universityAndMajorVO.getSpecialtyname());
                mvo.setZycengci(originMajorVO.getZycengci());
                mvo.setRankingType(originMajorVO.getRankingType());
                mvo.setZytype(originMajorVO.getZytype());
                hashCode++;
                addMajorVOs.add(mvo);
            }
        }
        return addMajorVOs;
    }
}
