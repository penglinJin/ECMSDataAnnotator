package cjlu.skyline.ecms_data_annotator.api.controller;

import java.util.Arrays;
import java.util.Map;

import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cjlu.skyline.ecms_data_annotator.api.entity.DocLabelEntity;
import cjlu.skyline.ecms_data_annotator.api.service.DocLabelService;




/**
 * 
 *
 * @author jinpenglin
 * @email nsp4289@autuni.ac.nz
 * @date 2021-01-11 15:35:16
 */
@RestController
@RequestMapping("annotator/doclabel")
public class DocLabelController {
    @Autowired
    private DocLabelService docLabelService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = docLabelService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{docLabelId}")
    public R info(@PathVariable("docLabelId") Long docLabelId){
		DocLabelEntity docLabel = docLabelService.getById(docLabelId);

        return R.ok().put("docLabel", docLabel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DocLabelEntity docLabel){
		docLabelService.save(docLabel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DocLabelEntity docLabel){
		docLabelService.updateById(docLabel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] docLabelIds){
		docLabelService.removeByIds(Arrays.asList(docLabelIds));

        return R.ok();
    }

}
