package com.nostudy.business.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lance on 7/14/16.
 */
public class GrabContent {

    public static String grabWithJavaNet(String url){

        StringBuilder grabResult = new StringBuilder();
        BufferedReader inputBuffer=null;

        URLConnection urlConnection = null;
        try{
            //prepare connection
            URL realURL =new URL(url);
            urlConnection = realURL.openConnection();
            urlConnection.connect();

            //prepare read
            inputBuffer=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String singleLine = null;
            while ((singleLine=inputBuffer.readLine())!=null){
                grabResult.append(singleLine);
            }

            return grabResult.toString();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if (inputBuffer!=null){
                    inputBuffer.close();
                    return grabResult.toString();
                }
                inputBuffer = null;
                urlConnection = null;
            }catch (Exception e2) {e2.printStackTrace();}
        }

        return grabResult.toString();
    }

    public static void main(String[] args){
        //String testURL ="http://gkcx.eol.cn/schoolhtm/schoolTemple/school3000.htm";学校详细信息

        //String testURL="http://gkcx.eol.cn/schoolhtm/specialty/specialtyList/specialty31.htm";，某大学全部专业

        GrabContent grabContent= new GrabContent();

        String urlAllUniversity="http://data.api.gkcx.eol.cn/soudaxue/querySchoolSpecialty.html?messtype=jsonp&zycengci=&page=1&size=10&keyWord1=%E7%94%B5%E5%AD%90%E5%95%86%E5%8A%A1&province=&schooltype=&schoolprop=&callback=jQuery18307610225111401336_1468630663527&_=1468630664312%22";

        String rowResult=grabContent.grabWithJavaNet(urlAllUniversity);

        //rowResult=rowResult.replaceAll(":[ ]+?\".+[\\(].+?[\\)]\"",":[ ]+?\".+[（].+?[）]\"");

        /*Pattern pattern =Pattern.compile("(:[ ]+?\".+)([\\(])(.+?)([\\)])(\")");
        Matcher matcher=pattern.matcher(rowResult);

        if (matcher.find()){
            rowResult.replaceAll("\\"+matcher.group(2),"【");
            rowResult.replaceAll("\\"+matcher.group(4),"】");

        }*/
        rowResult=rowResult.replaceAll("\\(","【");
        rowResult=rowResult.replaceAll("\\)","】");

        rowResult=rowResult.replaceAll("【\\{[\\s]*?\"","\\(");
        rowResult=rowResult.replaceAll("\\}[\\s]*?\\][\\s]*?\\}】;","\\)");

        rowResult=rowResult.replaceAll("\\[\\]","\"fuck\"");

        rowResult=rowResult.replaceAll("\\s","");

        System.out.println(rowResult);
        //System.out.println(matcher.group());

    }

}
