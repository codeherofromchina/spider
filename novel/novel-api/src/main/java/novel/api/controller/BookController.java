package novel.api.controller;

import novel.dao.model.Book;
import novel.service.comm.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wangxiaodan on 2018/7/12.
 */
@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("listAll")
    public List<Book> listAll() {
        System.out.println("..............");
        List<Book> bookList = bookService.list();

        return bookList;
    }


}
