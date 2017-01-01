package com.nevertiree.business.book.bookQuery;

import com.nevertiree.common.JsonUtil;
import com.nevertiree.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Lance Wang on 2016/12/31.
 */

@Scope("prototype")
@RequestMapping("/query")
@Controller
public class BookQueryController {

    @Autowired
    protected BookQueryService bookQueryService;

    // 算法导论的ISBN 9787040110500

    // 根据ISBN书号查询书籍信息
    @RequestMapping(value = "/isbn",method = RequestMethod.GET,produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String queryByISBN(@RequestParam(value = "isbn") String isbn) throws Exception{
        Book book = bookQueryService.queryBookbyISBN(isbn);
        return JsonUtil.toJson(book);
    }

    // 根据书名查询书籍信息
    @RequestMapping(value = "/name",method = RequestMethod.GET,produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String queryByName(@RequestParam(value = "name") String name) throws Exception{
        List<Book> books = bookQueryService.queryBookByName(name);
        return JsonUtil.toJson(books);
    }

    // 根据阅读人数查询书籍
    @RequestMapping(value = "/voteDesc",method = RequestMethod.GET,produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String queryByVoteDesc(@RequestParam(value = "topN") int topN) throws Exception{
        List<Book> books = bookQueryService.queryBookByVoteDesc(topN);
        return JsonUtil.toJson(books);
    }

    // 根据书籍评分人数查询书籍
    @RequestMapping(value = "/scoreDesc",method = RequestMethod.GET,produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String queryByScoreDesc(@RequestParam(value = "topN") int topN) throws Exception{
        List<Book> books = bookQueryService.queryBookByScoreDesc(topN);
        return JsonUtil.toJson(books);
    }



}
