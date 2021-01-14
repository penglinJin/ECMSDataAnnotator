package cjlu.skyline.ecms_data_annotator.api.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cjlu.skyline.ecms_data_annotator.api.entity.LabelInfoEntity;
import cjlu.skyline.ecms_data_annotator.api.service.LabelInfoService;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.R;



/**
 * 
 *
 * @author jinpenglin
 * @email nsp4289@autuni.ac.nz
 * @date 2021-01-11 15:35:15
 */
@RestController
@RequestMapping("api/labelinfo")
public class LabelInfoController {
    @Autowired
    private LabelInfoService labelInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = labelInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{labelId}")
    public R info(@PathVariable("labelId") Long labelId){
		LabelInfoEntity labelInfo = labelInfoService.getById(labelId);

        return R.ok().put("labelInfo", labelInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LabelInfoEntity labelInfo){
		labelInfoService.save(labelInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody LabelInfoEntity labelInfo){
		labelInfoService.updateById(labelInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] labelIds){
		labelInfoService.removeByIds(Arrays.asList(labelIds));

        return R.ok();
    }

}
