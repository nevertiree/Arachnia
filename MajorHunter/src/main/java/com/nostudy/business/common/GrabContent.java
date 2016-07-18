package com.nostudy.business.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Lance on 7/14/16.
 */
public class GrabContent {

    private GrabContent(){}

    private final static int CONNECTION_TIME_OUT =10;

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

    public static String grabWithHttpClient(String baseurl){
        HttpClientBuilder httpClientBuilder =HttpClientBuilder.create();
        HttpClient httpClient=httpClientBuilder.build();

        //Time out setting
        RequestConfig requestConfig =RequestConfig.custom().setConnectTimeout(CONNECTION_TIME_OUT*1000).setConnectionRequestTimeout(CONNECTION_TIME_OUT*1000).setSocketTimeout(CONNECTION_TIME_OUT*1000).build();

        //Get method
        HttpGet httpGet=new HttpGet(baseurl);
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("Host", "data.api.gkcx.eol.cn");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Cache-Control","max-age=0");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
        httpGet.setHeader("Accept", "*/*");
        httpGet.setHeader("refer", "http://gkcx.eol.cn/soudaxue/querySchoolSpecialty.html?messtype=jsonp&zycengci=&page=1&size=10&keyWord1=&province=&schooltype=&schoolprop=&callback=jQuery18307610225111401336_1468630663527&_=1468630664312");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
        httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.8");
        httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
        httpGet.setHeader("Cookie", "gkcx_guess_info_pc=||; tool_ipuse=202.204.120.158; tool_ipprovince=11; tool_iparea=1; sys_cernet_SchoolHistory=824|/schoolhtm/schoolTemple/school824.htm|å\u008C\u0097äº¬ç»\u008Fè´¸è\u0081\u008Cä¸\u009Aå\u00AD¦é\u0099¢,262|/schoolhtm/schoolTemple/school262.htm|å±±è¥¿å¤§å\u00AD¦,459|/schoolhtm/schoolTemple/school459.htm|æ²³å\u008D\u0097å¤§å\u00AD¦,3000|/schoolhtm/schoolTemple/school3000.htm|å\u008C\u0097äº¬ç\u0090\u0086å·¥å¤§å\u00AD¦å\u009B½é\u0099\u0085æ\u0095\u0099è\u0082²å\u00AD¦é\u0099¢,1000|/schoolhtm/schoolTemple/school1000.htm|å¹¿å·\u009Eç§\u0091æ\u008A\u0080è\u0081\u008Cä¸\u009Aæ\u008A\u0080æ\u009C¯å\u00AD¦é\u0099¢,331|/schoolhtm/schoolTemple/school331.htm|å®\u0089å¾½ç§\u0091æ\u008A\u0080å\u00AD¦é\u0099¢,30|/schoolhtm/schoolTemple/school30.htm|å\u008C\u0097äº¬å·¥ä¸\u009Aå¤§å\u00AD¦,40|/schoolhtm/schoolTemple/school40.htm|ä¸\u00ADå¤®é\u009F³ä¹\u0090å\u00AD¦é\u0099¢,50|/schoolhtm/schoolTemple/school50.htm|è¾½å®\u0081å¤§å\u00AD¦,601|/schoolhtm/schoolTemple/school601.htm|æ²³å\u008C\u0097å\u008C\u0097æ\u0096¹å\u00AD¦é\u0099¢,318|/schoolhtm/schoolTemple/school318.htm|ä¸\u008Aæµ·å¯¹å¤\u0096ç»\u008Fè´¸å¤§å\u00AD¦,604|/schoolhtm/schoolTemple/school604.htm|å\u008D\u008Eå\u008C\u0097ç§\u0091æ\u008A\u0080å\u00AD¦é\u0099¢,31|/schoolhtm/schoolTemple/school31.htm|å\u008C\u0097äº¬å¤§å\u00AD¦; sys_wenda_info=");

        //Grab the content
        HttpEntity httpEntity=null;

        BufferedReader inputBuffer = null;
        StringBuffer stringBuffer =null;

        String content =null;
        int statueCode =0;

        try {
            HttpResponse httpResponse=httpClient.execute(httpGet);
            httpEntity=httpResponse.getEntity();
            inputBuffer=new BufferedReader(new InputStreamReader(httpEntity.getContent()));
            stringBuffer=new StringBuffer("");
            String singleLine="";
            //String nLine = System.getProperty("line.separator");
            while ((singleLine=inputBuffer.readLine())!=null){
                stringBuffer.append(singleLine);
            }
            inputBuffer.close();
            content=stringBuffer.toString();
            statueCode=httpResponse.getStatusLine().getStatusCode();
            System.out.println("Successful!!!"+statueCode+baseurl);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error!"+"StatusCode"+statueCode+baseurl);
            return "";
        }
        finally {
            if (inputBuffer!=null){
                try {
                    inputBuffer.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                stringBuffer=null;
            }
        }
        return content;
    }

    public static void main(String[] args){
        String url ="http://gkcx.eol.cn/schoolhtm/schoolTemple/school1118.htm";

        String result = GrabContent.grabWithHttpClient(url);
        result=result.replaceAll(" ","");
        System.out.println(result);
    }
}
