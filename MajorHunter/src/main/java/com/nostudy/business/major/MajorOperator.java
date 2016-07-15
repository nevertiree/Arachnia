package com.nostudy.business.major;

import com.google.gson.Gson;
import com.nostudy.business.common.AnalysisContent;
import com.nostudy.business.common.GrabContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 7/14/16.
 */
public class MajorOperator {
    public static void main(String[] args) {
        MajorOperator spider = new MajorOperator();
        spider.operator("本科");
    }
    public void operator(String majorType){

        //get the origin content from data.api
        GrabContent grabContent =new GrabContent();

        //the base URL
        int pageNumber = 1;
        String urlAllMajorBK;

        try {//judge whether this page has valued infomation
            for(pageNumber=1;pageNumber<55;pageNumber++){
                urlAllMajorBK ="http://data.api.gkcx.eol.cn/soudaxue/queryspecialty.html?messtype=jsonp&zycengci="+majorType+"&zytype=&page="+pageNumber+"&size=10&keyWord2=&schoolsort=&callback=jQuery1830032572212268669354_1468135281347&_=1468135282182";//全部本科专业目录
                String resultAllMajorBK=grabContent.grabWithJavaNet(urlAllMajorBK);//本科全部专业原始数据
                List<MajorVO> majorVOs =parseMajorRespVO(parseJSON(resultAllMajorBK));
                parseMajorVO(majorVOs);
            }
        }catch (Exception e){e.printStackTrace();}

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

        List<MajorVO> resultMajorVO = new ArrayList<MajorVO>();
        for (MajorRowVO majorRowVO:majorRespVO.getSchool()){

            try {MajorVO majorVO = new MajorVO();
                majorVO.setCode(majorRowVO.getCode());
                majorVO.setSpecialname(majorRowVO.getSpecialname());
                majorVO.setRankingType(majorRowVO.getRankingType());
                resultMajorVO.add(majorVO);
            }catch (NullPointerException e){e.printStackTrace();}
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
