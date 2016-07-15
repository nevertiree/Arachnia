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

        try{
            //prepare connection
            URL realURL =new URL(url);
            URLConnection urlConnection = realURL.openConnection();
            urlConnection.connect();

            //prepare read
            inputBuffer=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String singleLine;
            while ((singleLine=inputBuffer.readLine())!=null){
                grabResult.append(singleLine);
            }

            return grabResult.toString();

        }catch (Exception e){e.printStackTrace();}

        finally {try{if (inputBuffer!=null){
                    inputBuffer.close();
                    return grabResult.toString();}
            }catch (Exception e2) {e2.printStackTrace();}
        }

        return grabResult.toString();
    }

    public static void main(String[] args){
        //String testURL ="http://gkcx.eol.cn/schoolhtm/schoolTemple/school3000.htm";学校详细信息

        //String testURL="http://gkcx.eol.cn/schoolhtm/specialty/specialtyList/specialty31.htm";，某大学全部专业

        GrabContent grabContent= new GrabContent();

        String urlAllUniversity="http://data.api.gkcx.eol.cn/soudaxue/queryschool.html?messtype=jsonp&province=&schooltype=&page=1&size=50&keyWord1=&schoolprop=&schoolflag=&schoolsort=&schoolid=&callback=jQuery1830041806086262917264_1468555273424&_=1468555274537.";

        String rowResult=grabContent.grabWithJavaNet(urlAllUniversity);

        /*resultAllUniversity=resultAllUniversity.replaceAll("\"shoufei\": \"\\.\\*\",","\"shoufei\": \"shoufei\",");
        resultAllUniversity=resultAllUniversity.replaceAll("\\[\\],","\"shoufei\": \"null\",");*/

        /*Pattern patternShoufei =Pattern.compile("\\\"shoufei\\\": \\\".*\\\",");
        Matcher matcherShoufei =patternShoufei.matcher(resultAllUniversity);
        matcherShoufei.replaceAll("\"shoufei\": \"null\",");

        Pattern patternJianjie=Pattern.compile("\\\"jianjie\": \\\".*\\\",");
        Matcher matcherJianjie=patternShoufei.matcher(resultAllUniversity);
        matcherJianjie.replaceAll("\"jianjie\": \"null\",");*/

        String jsonStr =null;

        rowResult=rowResult.replaceAll("\\s","");

        rowResult=rowResult.replaceAll("[\\w]*\\(\\{","{");
        rowResult=rowResult.replaceAll("\\}[\\s]*][\\s]*}\\);","\\}\\]\\}");

        rowResult=rowResult.replaceAll("\"shoufei\":\".*?\",","\"shoufei\":\"fuck\",");
        rowResult=rowResult.replaceAll("\"jianjie\":\".*?\",","\"jianjie\":\"fuck\",");
        rowResult=rowResult.replaceAll("\\[\\]","\"fuck\"");

       /* int startSite = rowResult.indexOf("({\n" +
                " \"totalRecord\"");
        if (startSite  != -1) {
            int end = rowResult.indexOf(" }\n" +
                    " ]\n" +
                    "})");
            jsonStr = rowResult.substring(startSite + 1, end);*/


            /*Pattern patternShoufei =Pattern.compile("\"shoufei\":\".*?\",");
            Matcher matcherShoufei =patternShoufei.matcher(jsonStr);
            matcherShoufei.replaceAll("\"shoufei\":\"fuck\",");

            Pattern patternJianjie=Pattern.compile("\"jianjie\":\".*?\",\"");
            Matcher matcherJianjie=patternShoufei.matcher(jsonStr);
            matcherJianjie.replaceAll("\"jianjie\":\"fuck\",");


        }*/
        System.out.println(rowResult);


    }

}
