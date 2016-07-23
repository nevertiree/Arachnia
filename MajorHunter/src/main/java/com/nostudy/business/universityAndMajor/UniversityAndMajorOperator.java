package com.nostudy.business.universityAndMajor;

import com.google.gson.Gson;
import com.nostudy.business.common.AnalysisContent;
import com.nostudy.business.common.GrabContent;
import com.nostudy.business.common.IsValueInfo;
import com.nostudy.business.major.MajorDAO;
import com.nostudy.business.major.MajorOperator;
import com.nostudy.business.major.MajorVO;
import com.nostudy.business.university.UniversityDAO;

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
                    //得到本页结果
                    result =GrabContent.grabWithJavaNet(queryUrl);
                    //判断该页返回是否空值
                    if (IsValueInfo.getInstance(result, "school")){

                        //原数据解析步骤
                        result=AnalysisContent.parseJSONFormat(result);
                        UniversityAndMajorRespVO resonse = parseJSON(result);
                        List<UniversityAndMajorRowVO> rows = resonse.getSchool();

                        int schlNo=-1;
                        int counter=1;
                        String identifier;
                        String finalMajorId;//最后插入中间表的majorid
                        String originMajorId;//需要进过算法加工的majorid；
                        // TODO: UniversityAndMajorRowVO 的可用数据——》
                        // TODO: 1.schlName(用于查询自增的schlid)
                        // TODO: 2.majorName(各式各样的专业)
                        // TODO: 3.majorType（重要的专业区分方式）
                        String currentMajorType = null;
                        for (UniversityAndMajorRowVO row:rows) {
                            //type字段可能为null，默认按照教育部的类型进行数据处理
                            currentMajorType = row.getSpecialtytype();
                            if (currentMajorType == null || currentMajorType.length() == 0 || currentMajorType.endsWith("nothing")) {
                                row.setSpecialtytype( majorVO.getZytype() );
                            }


                            //1. 根据学校名字schlName，获取university表中对应的schlNo
                            schlNo= UniversityDAO.querySchlNoByName(row.getSchoolname());
                            //如果学校id为0则说明没有该学校 插入之
                            if (schlNo==-1){
                                // TODO: 7/21/16 insert a university record.？？？
                                System.out.println("------------------没有学校"+row.getSchoolname()+"的"+row.getSpecialtyname());

                                continue;
                            }

                            //2.查询该专业名字以及type&level对应的专业是否存在,返回一个结果id
                            finalMajorId=MajorDAO.queryMajorIdByDetail(row.getSpecialtyname(),row.getSpecialtytype());
                            //(1)根据majorVO.getSpecialname(),获取教育部目录中的专业代码specNO
                            originMajorId = MajorDAO.queryMajorIdByDetail(majorVO.getSpecialname(),majorVO.getZytype());

                            //  2.2 不存在该专业时,majorid为null
                            if(finalMajorId!=null) {//如果该专业存在
                                // 查询该专业代码
                                finalMajorId=originMajorId;
                                // (2)根据规则计算，specNO打头的专业已经存在的专业数量
                            }else {//如果该专业不存在
                                //2.2.2 计算新加专业的代码：10位代码
                                counter = MajorDAO.getTotalNumOfSameMajorId( originMajorId );

                                //转化为四位字符串，不足四位前面补0，即1--》0001
                                identifier = identifierTrans(counter);
                                //前面拼接原专业的ID
                                finalMajorId = originMajorId.concat(identifier);

                                //如果返回空值说明需要在major表中插入row
                                //2.2.3 insert into major table
                                MajorVO mvo = new MajorVO();
                                // TODO: major表的需要的内容
                                // TODO:  no(根据算法新设)
                                // TODO:  name(插入各式各样的专业名字)
                                // TODO:  level(以大学的专业为准)
                                // TODO:  type(majorType中以有)
                                // TODO:  rank(设计为4)
                                mvo.setCode(finalMajorId);
                                mvo.setSpecialname(row.getSpecialtyname());
                                mvo.setZycengci(majorVO.getZycengci());
                                mvo.setZytype(row.getSpecialtytype());
                                mvo.setRankingType(4);
                                MajorDAO.insertMajorSingle(mvo);

                            }
                            //3.根据第2步的操作，确定专业的代码
                            // TODO: 插入到universityAndMajor表
                            // TODO: 1.schlNo(需要从university表中取得 不可使用爬虫数据)
                            // todo: 2.majorNo(上文生成的新id)
                            // todo: 3.majorName()
                            // todo: 4.majorType()
                            UniversityAndMajorVO umvo=new UniversityAndMajorVO();
                            umvo.setSchoolid(schlNo);
                            umvo.setMajorid(finalMajorId);
                            umvo.setSpecialtyname(row.getSpecialtyname());
                            umvo.setSpecialtytype(row.getSpecialtytype());
                            UniversityAndMajorDAO.insertUniversityAndMajorSingle(umvo);

                        }
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
               /* UniversityAndMajorVO vo=new UniversityAndMajorVO();
                vo.setSchoolid(sourceVO.getSchoolid());
                vo.setMajorid(identifierTrans(counter));
                vo.setSpecialtyname(sourceVO.getSpecialtyname());
                vo.setSpecialtytype(sourceVO.getSpecialtytype());
                universityAndMajorVOs.add(vo);*/
            }catch (Exception e){e.printStackTrace();}

        }

        return universityAndMajorVOs;
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
