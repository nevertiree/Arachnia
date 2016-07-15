package com.nostudy.business.major;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostudy.business.common.AnalysisContent;
import com.nostudy.business.common.GrabContent;
import com.nostudy.business.common.JsonFunction;
import com.nostudy.spider.Major;

import java.util.List;

/**
 * Created by Lance on 7/14/16.
 */
public class MajorOperator {
    public static void main(String[] args) {
        MajorOperator spider = new MajorOperator();
        spider.operateBK();
    }
    public void operateBK(){

        //get the origin content from data.api
        GrabContent grabContent =new GrabContent();

        //the base URL
        int pageNumber = 1;
        String urlAllMajorBK ="http://data.api.gkcx.eol.cn/soudaxue/queryspecialty.html?messtype=jsonp&zycengci=本科&zytype=&page="+pageNumber+"&size=10&keyWord2=&schoolsort=&callback=jQuery1830032572212268669354_1468135281347&_=1468135282182";//全部本科专业目录
        String resultAllMajorBK=grabContent.grabWithJavaNet(urlAllMajorBK);//本科全部专业原始数据

        try {//judge whether this page has valued infomation
            AnalysisContent analysisContent= new AnalysisContent();
            while(analysisContent.valuedContent(resultAllMajorBK,"school")) {
                List<MajorVO> majorVOs = null;
                majorVOs=parseMajorRespVO(parseJSON(resultAllMajorBK));
                parseMajorVO(majorVOs);
                pageNumber++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    //input the row result and return the resp VO
    public MajorRespVO parseJSON(String rowResult){

        //get the core JSON string
        String jsonStr;
        int startSite = rowResult.indexOf("(");
        if (startSite  != -1) {
            int end = rowResult.indexOf(")");
            jsonStr = rowResult.substring(startSite + 1, end);
        }else return null;

        //parse the Json string
        Gson gson = new Gson();
        MajorRespVO majorRespVO = gson.fromJson(jsonStr, MajorRespVO.class);

        return majorRespVO;
    }

    //parse the MajorRespVO
    public List<MajorVO> parseMajorRespVO(MajorRespVO majorRespVO){

        List<MajorVO> resultMajorVO = null;

        for (MajorRowVO majorRowVO:majorRespVO.getSchool()){
            MajorVO majorVO = new MajorVO();
            majorVO.setCode(majorRowVO.getCode());
            majorVO.setSpecialname(majorRowVO.getSpecialname());
            majorVO.setRankingType(majorRowVO.getRankingType());
            resultMajorVO.add(majorVO);
        }

        return resultMajorVO;
    }

    //parse and insert the MajorVO
    public void parseMajorVO(List<MajorVO> majorVOs){
        for (MajorVO vo : majorVOs){
            MajorDAO majorDAO =new MajorDAO();
            majorDAO.insertMajor(vo);
        }
    }
}
