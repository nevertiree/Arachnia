package com.nevertiree.business.book.bookQuery;

import com.nevertiree.business.dao.BookMapper;
import com.nevertiree.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lance Wang on 2016/12/31.
 */

@Service
public class BookQueryService implements BookQueryServiceIntf{

    @Autowired
    BookMapper bookMapper;

    public Book queryBookbyISBN(String isbn){
        return bookMapper.selectByPrimaryKey(isbn);
    }

    public List<Book> queryBookByName(String name){
        return bookMapper.queryBookByName(name);
    }

    // 分页
    // select * from book where vote >= (select vote from book order by vote desc limit 10,1) order by vote desc;

    // select * from book order by vote desc limit 0,10;

    public List<Book> queryBookByVoteDesc(int topN){
        return bookMapper.queryBookByVoteDesc(topN);
    }

    public List<Book> queryBookByScoreDesc(int topN){
        return bookMapper.queryBookByScoreDesc(topN);
    }
}
