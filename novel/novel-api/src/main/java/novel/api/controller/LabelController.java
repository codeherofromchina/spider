package novel.api.controller;

import novel.dao.model.Label;
import novel.service.comm.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangxiaodan on 2018/7/6.
 */
@RestController
@RequestMapping("label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @RequestMapping(value = "get", produces = "application/json")
    public Label get(Integer id) {
        Label label = labelService.findById(id);
        return label;
    }


}
