package com.nostudy.business.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lance on 7/14/16.
 */
public class AnalysisContent {

    private AnalysisContent(){}

    public static String parseJSONFormat(String rowData){
        //change all invisible symbol into space
        rowData=rowData.replaceAll("\\s","");

        //wrap the non-json header and tail
        rowData=rowData.replaceAll("[\\w]*\\(\\{","{");
        rowData=rowData.replaceAll("\\}[\\s]*][\\s]*}\\);","\\}\\]\\}");

        return rowData.replaceAll("\\[\\]","\"nothing\"");
    }

}
