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

    private GrabContent(){}

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

}
