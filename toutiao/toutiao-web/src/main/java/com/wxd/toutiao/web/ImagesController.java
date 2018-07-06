package com.wxd.toutiao.web;

import com.wxd.toutiao.comm.Result;
import com.wxd.toutiao.comm.ResultStatusEnum;
import com.wxd.toutiao.domain.ImageNews;
import com.wxd.toutiao.domain.ImagesDetail;
import com.wxd.toutiao.exception.ToutiaoException;
import com.wxd.toutiao.service.ImageNewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 图片控制器
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/images")
public class ImagesController {
    private Logger logger = LoggerFactory.getLogger(ImagesController.class);
    @Autowired
    private ImageNewsService imageNewsService;

    /**
     * 图片详情
     *
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/imageDetails", method = RequestMethod.POST)
    public Result<ImagesDetail> imageDetails(String uuid) throws ToutiaoException {
        ImagesDetail imagesDetail = imageNewsService.findImagesDetail(uuid);
        if (imagesDetail == null) {
            return new Result<ImagesDetail>(ResultStatusEnum.PAGE_NOT_EXIST);
        }

        return new Result<ImagesDetail>(imagesDetail);
    }

    /**
     * 所有图片
     *
     * @param timePoint
     * @return
     */
    @RequestMapping(value = "/default", method = RequestMethod.POST)
    public Result<List<ImageNews>> execute(long timePoint) {
        return get(timePoint, null);
    }

    /**
     * 组图
     *
     * @param timePoint
     * @return
     */
    @RequestMapping(value = "/gallery", method = RequestMethod.POST)
    public Result<List<ImageNews>> gallery(long timePoint) {
        return get(timePoint, "组图");
    }

    /**
     * 老照片
     *
     * @return
     */
    @RequestMapping(value = "/old_picture", method = RequestMethod.POST)
    public Result<List<ImageNews>> oldPicture(long timePoint) {
        return get(timePoint, "gallery_old_picture");
    }

    /**
     * 摄影集
     *
     * @return
     */
    @RequestMapping(value = "/photography", method = RequestMethod.POST)
    public Result<List<ImageNews>> photography(long timePoint) {
        return get(timePoint, "gallery_photograthy");
    }


    private Result<List<ImageNews>> get(long timePoint, String category) {
        ResultStatusEnum resultStatus = ResultStatusEnum.FAIL;
        try {
            List<ImageNews> data = imageNewsService.findImageNews(category, timePoint);
            if (data != null && data.size() > 0) {
                return new Result<List<ImageNews>>(data);
            }
            resultStatus = ResultStatusEnum.EMPTY;
        } catch (ToutiaoException ex) {
            logger.error("获取默认图片列表异常", ex, timePoint);
            resultStatus = ex.getResultStatusEnum();
        }
        return new Result<List<ImageNews>>(resultStatus);
    }

}
