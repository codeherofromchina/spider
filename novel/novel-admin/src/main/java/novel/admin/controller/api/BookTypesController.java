package novel.admin.controller.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import novel.comm.result.Result;
import novel.comm.result.ResultStatusEnum;
import novel.dao.model.BookTypes;
import novel.service.comm.BookTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wangxiaodan on 2018/8/8.
 */
@RestController
@RequestMapping("api/bookTypes")
public class BookTypesController {
    @Autowired
    private BookTypesService bookTypesService;

    /**
     * 图书分类树信息
     *
     * @return
     */
    @RequestMapping("tree")
    public Result<List<BookTypes>> tree() {
        List<BookTypes> data = bookTypesService.tree();
        return new Result<>(data);
    }

    /**
     * 编辑图书分类
     *
     * @param bookTypes
     * @return
     */
    @RequestMapping("edit")
    public Result edit(BookTypes bookTypes) {
        Result result = new Result();
        if (bookTypes.getId() == null) {
            result.setStatus(ResultStatusEnum.FAIL); // TODO 这里可以更精确的信息返回
            return result;
        }
        int updateFlag = bookTypesService.update(bookTypes);
        if (updateFlag == 1) {
            result.setStatus(ResultStatusEnum.FAIL);
        } else if (updateFlag == 2) {
            // 图书分类不存在
            result.setStatus(ResultStatusEnum.FAIL);
        } else if (updateFlag == 3) {
            // 图书分类名称重复
            result.setStatus(ResultStatusEnum.FAIL);
        }
        return result;
    }


    /**
     * 添加图书分类
     * @param bookTypes
     * @return
     */
    @RequestMapping(value = "add",produces = "application/json")
    public Result<BookTypes> add(BookTypes bookTypes) {
        Result result = new Result();

        int addFlag = bookTypesService.add(bookTypes);
        if (addFlag == 1) {
            result.setStatus(ResultStatusEnum.FAIL);
        } else if (addFlag == 3) {
            // 图书分类名称重复
            result.setStatus(ResultStatusEnum.FAIL);
        } else {
            result.setData(bookTypes);
        }
        return result;
    }


    /**
     * 删除图书分类信息
     * @param id
     * @return
     */
    @RequestMapping("delete")
    public Result delete(Integer id) {
        Result result = new Result();
        int del = bookTypesService.delete(id);
        if (del == 1) {
            result.setStatus(ResultStatusEnum.FAIL);
        } else if (del == 2) {
            // 图书分类不存在
            result.setStatus(ResultStatusEnum.FAIL);
        }

        return result;
    }
}
