package com.nevertiree.business;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetWebContent {

    private GetWebContent(){}

    private final static int CONNECTION_TIME_OUT = 10;

    public static String getWebConentx(String url){
        StringBuilder resultBuilder = new StringBuilder();
        BufferedReader inputBuffer = null;

        URLConnection urlConnection = null;
        try{
            URL realURL = new URL(url);
            urlConnection = realURL.openConnection();
            urlConnection.connect();

            //prepare reading data
            inputBuffer = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String singleLine = null;
            while((singleLine = inputBuffer.readLine())!=null){
                resultBuilder.append(singleLine);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (inputBuffer != null){
                    inputBuffer.close();
                    return resultBuilder.toString();
                }
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }

        return resultBuilder.toString();
    }
}