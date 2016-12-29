package com.nevertiree.business;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Lance Wang on 2016/12/28.
 */
public class BookFilterTest {

    private BookFilter bookFilter;
    private String url_think_in_java = "https://book.douban.com/subject/1474824/";

    @Before
    public void setUp() throws Exception{
        bookFilter = new BookFilter();
    }

    @Test
    public void testGetBookName() throws Exception{
        String testWebContent = GetWebContent.getWebConentx(url_think_in_java);
        assertEquals("Thinking in Java",BookFilter.getBookName(testWebContent));
    }

    @Test
    public void testGetBookScore() throws Exception{
        String testWebContent = GetWebContent.getWebConentx(url_think_in_java);
        assertEquals("9.0",BookFilter.getBookScore(testWebContent));
    }

    @Test
    public void testGetVoteNumber() throws Exception{
        String testWebContent = GetWebContent.getWebConentx(url_think_in_java);
        assertEquals("456",BookFilter.getVoteNumber(testWebContent));
    }

    @Test
    public void testRank5Rate() throws Exception{
        String testWebContent = GetWebContent.getWebConentx(url_think_in_java);
        assertEquals("61.4",BookFilter.getRank5Rate(testWebContent));
    }

    @Test
    public void testRank4Rate() throws Exception{
        String testWebContent = GetWebContent.getWebConentx(url_think_in_java);
        assertEquals("28.3",BookFilter.getRank4Rate(testWebContent));
    }

    @Test
    public void testRank3Rate() throws Exception{
        String testWebContent = GetWebContent.getWebConentx(url_think_in_java);
        assertEquals("8.6",BookFilter.getRank3Rate(testWebContent));
    }

    @Test
    public void testRank2Rate() throws Exception{
        String testWebContent = GetWebContent.getWebConentx(url_think_in_java);
        assertEquals("1.1",BookFilter.getRank2Rate(testWebContent));
    }

    @Test
    public void testRank1Rate() throws Exception{
        String testWebContent = GetWebContent.getWebConentx(url_think_in_java);
        assertEquals("0.7",BookFilter.getRank1Rate(testWebContent));
    }

    @Test
    public void testGetRelativeBook(){
        String testWebContent = GetWebContent.getWebConentx(url_think_in_java);
        assertEquals(15,BookFilter.getRelativeBook(testWebContent).size());
    }

}
