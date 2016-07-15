package com.nostudy.business.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

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

    /*    GrabContent grabContent =new GrabContent();

        String testResult = grabContent.grabWithJavaNet(testURL);

        System.out.println(testResult);*/
    }

}
