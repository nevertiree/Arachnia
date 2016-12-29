package com.nevertiree.business;

import com.nevertiree.domain.BookVO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Lance Wang on 2016/12/28.
 */
public class BookFilterTest {

    private BookFilter bookFilter;

    private static String url = "https://book.douban.com/subject/20432061/";
    private static String exceptedISBN = "9787111407010";
    private static String exceptedName = "算法导论（原书第3版）";
    private static String exceptedScore = "9.2";
    private static String exceptedVote = "334";
    private static String exceptedRank5 = "71.6";
    private static String exceptedRank4 = "22.8";
    private static String exceptedRank3 = "3.9";
    private static String exceptedRank2 = "0.9";
    private static String exceptedRank1 = "0.9";

    private BookVO book = new BookVO();

    @Before
    public void setUp() throws Exception{
        bookFilter = new BookFilter();
    }

//    @Test
//    public void testGetBookInfo() throws Exception{
//        String testWebContent = GetWebContent.getWebContent(url);
//        book.setName(exceptedName);
//        book.setScore(Double.parseDouble(exceptedScore));
//        book.setVoteNum(Integer.parseInt(exceptedVote));
//        book.setScoreRank1(Double.parseDouble(exceptedRank1));
//        book.setScoreRank2(Double.parseDouble(exceptedRank2));
//        book.setScoreRank3(Double.parseDouble(exceptedRank3));
//        book.setScoreRank4(Double.parseDouble(exceptedRank4));
//        book.setScoreRank5(Double.parseDouble(exceptedRank5));
//
//        assertEquals(book,BookFilter.getBookInfo(url));
//    }

    @Test
    public void testGetBookISBN() throws Exception{
        String testWebContent = GetWebContent.getWebContent(url);
        assertEquals(exceptedISBN,BookFilter.getBookISBN(testWebContent));
    }

    @Test
    public void testGetBookName() throws Exception{
        String testWebContent = GetWebContent.getWebContent(url);
        assertEquals(exceptedName,BookFilter.getBookName(testWebContent));
    }

    @Test
    public void testGetBookScore() throws Exception{
        String testWebContent = GetWebContent.getWebContent(url);
        assertEquals(exceptedScore,BookFilter.getBookScore(testWebContent));
    }

    @Test
    public void testGetVoteNumber() throws Exception{
        String testWebContent = GetWebContent.getWebContent(url);
        assertEquals(exceptedVote,BookFilter.getVoteNumber(testWebContent));
    }

    @Test
    public void testRank5Rate() throws Exception{
        String testWebContent = GetWebContent.getWebContent(url);
        assertEquals(exceptedRank5,BookFilter.getRank5Rate(testWebContent));
    }

    @Test
    public void testRank4Rate() throws Exception{
        String testWebContent = GetWebContent.getWebContent(url);
        assertEquals(exceptedRank4,BookFilter.getRank4Rate(testWebContent));
    }

    @Test
    public void testRank3Rate() throws Exception{
        String testWebContent = GetWebContent.getWebContent(url);
        assertEquals(exceptedRank3,BookFilter.getRank3Rate(testWebContent));
    }

    @Test
    public void testRank2Rate() throws Exception{
        String testWebContent = GetWebContent.getWebContent(url);
        assertEquals(exceptedRank2,BookFilter.getRank2Rate(testWebContent));
    }

    @Test
    public void testRank1Rate() throws Exception{
        String testWebContent = GetWebContent.getWebContent(url);
        assertEquals(exceptedRank1,BookFilter.getRank1Rate(testWebContent));
    }

//    @Test
//    public void testGetRelativeBook(){
//        String testWebContent = GetWebContent.getWebContent(url);
//        assertEquals(15,BookFilter.getRelativeBook(testWebContent).size());
//    }

}
