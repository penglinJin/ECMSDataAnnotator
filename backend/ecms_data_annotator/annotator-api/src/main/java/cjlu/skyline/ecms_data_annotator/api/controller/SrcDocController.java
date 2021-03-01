package cjlu.skyline.ecms_data_annotator.api.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cjlu.skyline.ecms_data_annotator.api.entity.SrcDocEntity;
import cjlu.skyline.ecms_data_annotator.api.service.SrcDocService;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.R;



/**
 * 
 *
 * @author jinpenglin
 * @email nsp4289@autuni.ac.nz
 * @date 2021-01-11 15:35:16
 */
@RestController
@RequestMapping("api/srcdoc")
public class SrcDocController {
    @Autowired
    private SrcDocService srcDocService;

    @PostMapping("/process")
    public R processDataset(@RequestParam("file") File file){
        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = srcDocService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{srcDocId}")
    public R info(@PathVariable("srcDocId") Long srcDocId){
		SrcDocEntity srcDoc = srcDocService.getById(srcDocId);

        return R.ok().put("srcDoc", srcDoc);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SrcDocEntity srcDoc){
		srcDocService.save(srcDoc);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SrcDocEntity srcDoc){
		srcDocService.updateById(srcDoc);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] srcDocIds){
		srcDocService.removeByIds(Arrays.asList(srcDocIds));

        return R.ok();
    }

}
