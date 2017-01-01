package com.nevertiree.business;

import com.nevertiree.domain.BookDAO;
import com.nevertiree.domain.BookVO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Lance Wang on 2016/12/29.
 */
public class BreadthFirstSearch {

    private Set<String> visitedWeb;
    private Set<String> nextWeb;

    public BreadthFirstSearch(){
        this.visitedWeb = new HashSet<String>();
        this.nextWeb = new HashSet<String>();
    }

    public void start(String url) throws Exception,SQLException{
        //add first page
        this.nextWeb.add(url);
        //start traverse all un-visited web link
        while (!nextWeb.isEmpty()) {

            // get a url from nextWeb set
            Iterator<String> it = nextWeb.iterator();
            url = it.next();

            // mark current web as visited
            this.visitedWeb.add(url);
            this.nextWeb.remove(url);

            // get web content from current url
            String webContent = GetWebContent.getWebContent(url);

            // create new book vo and insert to mysql table
            BookVO book = BookFilter.getBookInfo(webContent);

            try {
                BookDAO.insertBook(book);
            }catch (SQLException e){
                e.printStackTrace();
            }

            // add new web from current page
            this.nextWeb.addAll(BookFilter.getRelativeBook(webContent));
            this.nextWeb.removeAll(visitedWeb);

            // spider sleep
            Thread.sleep(1000);
        }
    }


}
