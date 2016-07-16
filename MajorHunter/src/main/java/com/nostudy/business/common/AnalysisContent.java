package com.nostudy.business.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lance on 7/14/16.
 */
public class AnalysisContent {

    //analysis whether that page have valued infomation (targetContent)
    public boolean valuedContent(String wholeContent,String targetContent){

        Pattern pattern = Pattern.compile(targetContent);
        Matcher matcher=pattern.matcher(wholeContent);

        if (matcher.find()){return true;}
        else return false;
    }

    public String parseJSONFormat(String rowData){

        //change all invisible symbol into space
        rowData=rowData.replaceAll("\\s","");

        //wrap the non-json header and tail
        rowData=rowData.replaceAll("[\\w]*\\(\\{","{");
        rowData=rowData.replaceAll("\\}[\\s]*][\\s]*}\\);","\\}\\]\\}");

        return rowData;
    }

}
