package com.nevertiree.business.book.bookQuery;

import com.nevertiree.business.dao.BookMapper;
import com.nevertiree.domain.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by Lance Wang on 2016/12/31.
 */
public class BookQueryServiceTest {
    @Autowired
    BookQueryService bookQueryService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void queryBookByISBN() throws Exception {
        String isbn = "9787040110500";
        assertEquals("算法导论",bookQueryService.queryBookbyISBN(isbn).getName());
    }

    @Test
    public void queryBookByVoteDesc() throws Exception {

    }


}