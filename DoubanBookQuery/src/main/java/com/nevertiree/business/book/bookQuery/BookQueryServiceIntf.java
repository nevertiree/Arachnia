package com.nevertiree.business.book.bookQuery;

import com.nevertiree.business.dao.BookMapper;
import com.nevertiree.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Lance Wang on 2016/12/31.
 */
public interface BookQueryServiceIntf {

    Book queryBookbyISBN(String isbn);

}
