package com.nevertiree.business;

/**
 * Created by Lance Wang on 2016/12/27.
 */
public class Main {

    public static void main(String[] args){
        String url = "https://book.douban.com/subject/4848587/";
        String result = GetWebContent.getWebConentx(url);
//        System.out.print(BookFilter.getBookInfo(result));
        System.out.print(result);

    }
}
