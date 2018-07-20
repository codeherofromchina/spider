package novel.api.controller;

import novel.dao.model.Catalog;
import novel.dao.model.Content;
import novel.service.comm.CatalogService;
import novel.service.comm.ContentService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxiaodan on 2018/7/12.
 */
@RestController
@RequestMapping("catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;
    @Autowired
    private ContentService contentService;

    /**
     * 图书的目录列表
     *
     * @param bookId
     * @return
     */
    @RequestMapping("listBook")
    public List<Catalog> listBook(HttpServletRequest request, HttpServletResponse response, Integer bookId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        List<Catalog> result = null;
        if (bookId == null) {// 图书不存在，返回空
            result = new ArrayList<>();
        } else {
            result = catalogService.findByBookId(bookId);
        }


//        long start = System.currentTimeMillis();
//        String etag = encoderByMd5(bookId + ":" + result);
//        System.out.println(System.currentTimeMillis() - start);
//
//        response.setHeader("Cache-Control", "no-cache");
//        response.setHeader("ETag", etag);
//        String etag1 = request.getHeader("If-None-Match");
//
//        if (StringUtils.isNotBlank(etag1) && etag1.equals(etag)) {
//            response.setStatus(304);
//            return null;
//        }
//        response.setHeader("ETag", etag);

        return result;
    }


    /**
     * 目录内容
     *
     * @param catalogId
     * @return
     */
    @RequestMapping("content")
    public Content content(Integer catalogId) {

        Content content = contentService.findByCatalog(catalogId);
        return content;
    }


    /**
     * 利用MD5进行加密
     *
     * @param str 待加密的字符串
     * @return 加密后的字符串
     * @throws NoSuchAlgorithmException     没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException
     */
    public static String encoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
}
