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

        // get all major info from db.
        List<MajorVO> originMajor=getOriginMajor();

        // current thread should sleep 3-5 second .
        int seconds = 0;
        Random random = new Random();

        String baseUrl="http://data.api.gkcx.eol.cn/soudaxue/querySchoolSpecialty.html?messtype=jsonp&zycengci=&page=pageParamValue&size=10000&keyWord1=majorNameParamValue&province=&schooltype=&schoolprop=&callback=jQuery18307610225111401336_1468630663527&_=1468630664312";
        String queryUrl = "";
        //遍历所有的专业
        for (MajorVO majorVO:originMajor) {
            //找到专业对应的学校
            int pageNumber=1;//start from page no.1
            String result = null;
            while (true){
//                queryUrl="http://data.api.gkcx.eol.cn/soudaxue/querySchoolSpecialty.html?messtype=jsonp&zycengci=&page="+pageNumber+"&size=10000&keyWord1="+majorVO.getSpecialname()+"&province=&schooltype=&schoolprop=&callback=jQuery18307610225111401336_1468630663527&_=1468630664312";
                queryUrl = baseUrl.replaceAll("pageParamValue", String.valueOf(pageNumber));
                queryUrl = queryUrl.replaceAll("majorNameParamValue", majorVO.getSpecialname());

                try {
                    result =GrabContent.grabWithHttpClient(queryUrl);
                    //判断该页返回是否空值
                    if (IsValueInfo.getInstance(result, "school")){
                        //First move header and tail
                        result=AnalysisContent.parseJSONFormat(result);
                        //得到多个返回的中间表VO对象
                        UniversityAndMajorRespVO resonse = parseJSON(result);
                        List<UniversityAndMajorRowVO> rows = resonse.getSchool();

                        //convert every row into UniversityAndMajorVO
                        for (UniversityAndMajorRowVO row:rows) {
                            UniversityAndMajorVO vo = new UniversityAndMajorVO();
                            //1. 根据学校名字，获取数据库中学校对应的no
                            int schoolNo = 0;//// TODO: 7/21/16

                            //2.查询该专业名字以及type&level对应的专业是否存在
                            //  2.1 存在，获取major的no
                            //majorDao.getMajorNoByNameTypeLevel
                            //  2.2 不存在:
                            //      2.2.1 查询该专业代码
                            //          (1)根据majorVO.getSpecialname(),获取教育部目录中的专业代码specNO
                            //          (2)根据规则计算，specNO打头的专业已经存在的专业数量
                            //      2.2.2 计算新加专业的代码：10位代码
                            //      2.2.3 insert into major table

                            //3.根据第2步的操作，确定专业的代码
                            //4. new VO--schoolNo, majorNo,


                            //vo.getSpecialtyname()
                        }




                        List<UniversityAndMajorVO> umvo =praseUniversityAndMajorRespVO((parseJSON(result)),majorVO);
                        //把专业所在的学校插入数据库
                        UniversityAndMajorDAO.insertUniversityAndMajor(umvo);

                        //得到多个major表VO对象（用于添加各式各样的专业别名）
                        List<MajorVO> mvo= praseDuplicateMajor((parseJSON(result)),majorVO);
                        //把专业别名插入数据库
                        MajorDAO.insertMajor(mvo);

                        pageNumber++;

                        seconds = random.nextInt(2)*100;
                        Thread.currentThread().sleep(seconds);

                    }else break;

                }catch (Exception e){
                    continue;
                }
            }
        }
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
    public List<UniversityAndMajorVO>  praseUniversityAndMajorRespVO(UniversityAndMajorRespVO universityAndMajorRespVO ,MajorVO majorVO){

        //输出的VO队列
        List<UniversityAndMajorVO>  universityAndMajorVOs=new ArrayList<>();

        int counter = 1;

        for (UniversityAndMajorRowVO sourceVO :universityAndMajorRespVO.getSchool()){

            try{
                UniversityAndMajorVO vo=new UniversityAndMajorVO();
                vo.setSchoolid(sourceVO.getSchoolid());
                vo.setMajorid(identifierTrans(counter));
                vo.setSpecialtyname(sourceVO.getSpecialtyname());
                vo.setSpecialtytype(sourceVO.getSpecialtytype());
                universityAndMajorVOs.add(vo);
            }catch (Exception e){e.printStackTrace();}

        }

        return universityAndMajorVOs;
    }

    public List<MajorVO> praseDuplicateMajor(UniversityAndMajorRespVO universityAndMajorRespVO ,MajorVO majorVO){
        List<MajorVO> majorVOs=new ArrayList<>();

        int counter = 1;

        for (UniversityAndMajorRowVO sourceVO :universityAndMajorRespVO.getSchool()){
            try {
                MajorVO mvo=new MajorVO();
                mvo.setCode(identifierTrans(counter));
                mvo.setSpecialname(majorVO.getSpecialname());
                mvo.setZycengci(majorVO.getZycengci());
                mvo.setZytype(majorVO.getZytype());
                mvo.setRankingType(majorVO.getRankingType());
                majorVOs.add(mvo);

            }catch (Exception UAndM){UAndM.printStackTrace();}
        }
        return majorVOs;
    }


    //analysis the different name major
   public static String identifierTrans(int counter){
       String  identifier;

       if (counter<10){identifier="000"+counter;}
       else {
           if (counter<100){identifier="00"+counter;}
           else
           if (counter<1000){identifier="0"+counter;}
           else identifier=String.valueOf(counter);
       }
       return identifier;
   }
}
