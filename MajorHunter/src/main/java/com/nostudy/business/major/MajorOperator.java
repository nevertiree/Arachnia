package com.nostudy.business.major;

import com.google.gson.Gson;
import com.nostudy.business.common.AnalysisContent;
import com.nostudy.business.common.GrabContent;
import com.nostudy.business.common.IsValueInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 7/14/16.
 */
public class MajorOperator {
    public static void main(String[] args) {
        MajorOperator spider = new MajorOperator();
        spider.downloadOperator("本科");
        spider.downloadOperator("专科");

    }
    public void downloadOperator(String typeMajor){

        //the base URL
        int pageNumber = 1;
        String urlAllMajorBK;

        try {//judge whether this page has valued infomation
            while(true){
                urlAllMajorBK ="http://data.api.gkcx.eol.cn/soudaxue/queryspecialty.html?messtype=jsonp&zycengci="+typeMajor+"&zytype=&page="+pageNumber+"&size=10&keyWord2=&schoolsort=&callback=jQuery1830032572212268669354_1468135281347&_=1468135282182";//全部本科专业目录
                String resultAllMajorBK=GrabContent.grabWithJavaNet(urlAllMajorBK);//本科全部专业原始数据
                if (/*analysisContent.valuedContent(resultAllMajorBK,"specialname")*/IsValueInfo.getInstance(resultAllMajorBK,"specialname")){
                    List<MajorVO> majorVOs =parseMajorRespVO(parseJSON(AnalysisContent.parseJSONFormat(resultAllMajorBK)));
                    parseMajorVO(majorVOs);
                    pageNumber++;
                }else break;
            }
        }catch (Exception e){e.printStackTrace();}

    }

    //parse the Json string to Json object
    public MajorRespVO parseJSON(String rowResult){
        Gson gson = new Gson();
        return gson.fromJson(rowResult, MajorRespVO.class);
    }

    //parse the MajorRespVO
    public List<MajorVO> parseMajorRespVO(MajorRespVO majorRespVO){

        List<MajorVO> resultMajorVO = new ArrayList<MajorVO>();
        for (MajorRowVO majorRowVO:majorRespVO.getSchool()){

            try {MajorVO majorVO = new MajorVO();
                majorVO.setCode(majorRowVO.getCode());
                majorVO.setSpecialname(majorRowVO.getSpecialname());
                majorVO.setZycengci(majorRowVO.getZycengci());
                majorVO.setZytype(majorRowVO.getZytype());
                majorVO.setRankingType(majorRowVO.getRankingType());
                resultMajorVO.add(majorVO);
            }catch (NullPointerException e){e.printStackTrace();}
        }

        return resultMajorVO;
    }

    //parse and insert the MajorVO
    public void parseMajorVO(List<MajorVO> majorVOs){
        for (MajorVO vo : majorVOs){
            MajorDAO.insertMajor(vo);
        }
    }
}
