package com.nostudy.business.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lance on 7/17/16.
 */
public class IsValueInfo {

    private IsValueInfo(){}

    public static boolean getInstance(String wholeContent,String targetContent){
        Pattern pattern = Pattern.compile(targetContent);
        Matcher matcher=pattern.matcher(wholeContent);
        return (matcher.find()?true:false);
    }
}
