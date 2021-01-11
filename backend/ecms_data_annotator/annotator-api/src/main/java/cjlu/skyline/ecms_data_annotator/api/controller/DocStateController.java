package cjlu.skyline.ecms_data_annotator.api.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cjlu.skyline.ecms_data_annotator.api.entity.DocStateEntity;
import cjlu.skyline.ecms_data_annotator.api.service.DocStateService;
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
@RequestMapping("api/docstate")
public class DocStateController {
    @Autowired
    private DocStateService docStateService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = docStateService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{docStatId}")
    public R info(@PathVariable("docStatId") Long docStatId){
		DocStateEntity docState = docStateService.getById(docStatId);

        return R.ok().put("docState", docState);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DocStateEntity docState){
		docStateService.save(docState);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DocStateEntity docState){
		docStateService.updateById(docState);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] docStatIds){
		docStateService.removeByIds(Arrays.asList(docStatIds));

        return R.ok();
    }

}
