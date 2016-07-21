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

        int seconds = 0;
        Random random = new Random();
        try{
            for (MajorVO majorVO:originMajor) {
                int pageNumber=1;
                String result = null;
                while (true){
                    String url="http://data.api.gkcx.eol.cn/soudaxue/querySchoolSpecialty.html?messtype=jsonp&zycengci=&page="+pageNumber+"&size=10000&keyWord1="+majorVO.getSpecialname()+"&province=&schooltype=&schoolprop=&callback=jQuery18307610225111401336_1468630663527&_=1468630664312";

                    try {result =GrabContent.grabWithHttpClient(url);
                        if (IsValueInfo.getInstance(result,"school")){
                            //First move header and tail
                            result=AnalysisContent.parseJSONFormat(result);
                            List<UniversityAndMajorVO> vos =praseUniversityAndMajorRespVO((parseJSON(result)),majorVO.getSpecialname());

                            UniversityAndMajorDAO.insertUniversityAndMajor(vos);
                            //majorOperator.parseMajorVO(analysisDiffMajor(majorVO,vos));
                            pageNumber++;

                            seconds = random.nextInt(2)*100;
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

        rowResult=rowResult.replaceAll("（","【");
        rowResult=rowResult.replaceAll("）","】");

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
    public List<UniversityAndMajorVO> praseUniversityAndMajorRespVO(UniversityAndMajorRespVO universityAndMajorRespVO ,String father_major ){

        List<UniversityAndMajorVO>  universityAndMajorVOs=new ArrayList<>();

        for (UniversityAndMajorRowVO sourceVO :universityAndMajorRespVO.getSchool()){
            try{
                UniversityAndMajorVO vo=new UniversityAndMajorVO();
                vo.setSchoolid(sourceVO.getSchoolid());
                vo.setSpecialtyname(sourceVO.getSpecialtyname());
                vo.setSpecialtytype(sourceVO.getSpecialtytype());
                universityAndMajorVOs.add(vo);
            }catch (Exception e){e.printStackTrace();}
        }
        return universityAndMajorVOs;
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
