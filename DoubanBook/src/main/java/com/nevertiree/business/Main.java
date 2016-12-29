package com.nevertiree.business;

/**
 * Created by Lance Wang on 2016/12/27.
 */
public class Main {

    public static void main(String[] args) throws Exception{
        String url = "https://book.douban.com/subject/20432061/";
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        bfs.start(url);
//        String result = GetWebContent.getWebContent(url);
//        System.out.print(BookFilter.getBookInfo(result));
//        System.out.print(result);

    }
}
