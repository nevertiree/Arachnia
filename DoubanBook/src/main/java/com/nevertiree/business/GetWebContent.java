package com.nevertiree.business;

import jdk.nashorn.internal.runtime.ParserException;

import javax.swing.text.html.parser.Parser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class GetWebContent {

    private static Parser parser;

    private GetWebContent(){}

    private final static int CONNECTION_TIME_OUT = 10;

    public static String getWebConentx(String url) throws ParserException,IOException{
        StringBuilder resultBuilder = new StringBuilder();
        BufferedReader inputBuffer = null;

        HttpURLConnection urlConnection = null;
        try{
            URL realURL = new URL(url);
            urlConnection = (HttpURLConnection)realURL.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            urlConnection.setRequestProperty("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36");
            urlConnection.setRequestProperty("Connection","keep-alive");
            urlConnection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml");
            urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Cache-control","no-cache, no-store");
            urlConnection.setRequestProperty("Host","www.linkedin.com");
            urlConnection.setConnectTimeout(20000);
            urlConnection.setReadTimeout(20000);
            urlConnection.connect();

            //prepare reading data
            inputBuffer = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"utf-8"));

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
                    urlConnection.disconnect();
                    return resultBuilder.toString();
                }
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }

        return resultBuilder.toString();
    }
}