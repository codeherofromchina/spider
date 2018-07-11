package novel.service.comm;

import novel.dao.mapper.BookMapper;
import novel.dao.model.Book;
import novel.dao.model.BookExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by wangxiaodan on 2018/7/7.
 */
@Service
public class BookService {
    @Autowired
    private BookMapper bookMapper;

    /**
     * 通过图书名称和作者唯一查找一本图书
     *
     * @param name
     * @param author
     * @return
     */
    public Book findByNameAndAuthor(String name, String author) {
        BookExample example = new BookExample();
        BookExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andAuthorEqualTo(author);
        List<Book> books = bookMapper.selectByExample(example);
        if (books != null && books.size() > 0) {
            return books.get(0);
        }
        return null;
    }

    /**
     * 通过站点内唯一标识查询图书
     *
     * @param uuid
     * @return
     */
    public Book findByUuid(String uuid) {
        BookExample example = new BookExample();
        BookExample.Criteria criteria = example.createCriteria();
        criteria.andUuidEqualTo(uuid);
        List<Book> books = bookMapper.selectByExample(example);
        if (books != null && books.size() > 0) {
            return books.get(0);
        }
        return null;
    }

    /**
     * 插入图书并返回是否成功
     *
     * @param book
     * @return
     */
    public boolean insert(Book book) {
        int insert = bookMapper.insert(book);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    /**
     * 更新图书
     *
     * @param book
     */
    public boolean update(Book book) {
        int i = bookMapper.updateByPrimaryKey(book);
        if (i > 0) {
            return true;
        }
        return false;
    }

    /**
     * 设置图书的采集时间和采集标记
     * @param bookId
     */
    public void updateSpiderMarkAndDate(Integer bookId) {
        Book book = new Book();
        book.setId(bookId);
        book.setMark(true);
        book.setSpiderDate(new Date());
        bookMapper.updateByPrimaryKeySelective(book);
    }
}
