package cjlu.skyline.ecms_data_annotator.api.controller;

import java.util.Arrays;
import java.util.Map;

import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cjlu.skyline.ecms_data_annotator.api.entity.AnnotatorRecordEntity;
import cjlu.skyline.ecms_data_annotator.api.service.AnnotatorRecordService;



/**
 * 
 *
 * @author jinpenglin
 * @email nsp4289@autuni.ac.nz
 * @date 2021-01-11 15:35:15
 */
@RestController
@RequestMapping("annotator/annotatorrecord")
public class AnnotatorRecordController {
    @Autowired
    private AnnotatorRecordService annotatorRecordService;

    @GetMapping("/approvalList")
    public R approvalList(@RequestParam Map<String, Object> params){
        PageUtils page = annotatorRecordService.queryApprovalPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = annotatorRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{recordId}")
    public R info(@PathVariable("recordId") Long recordId){
		AnnotatorRecordEntity annotatorRecord = annotatorRecordService.getById(recordId);

        return R.ok().put("annotatorRecord", annotatorRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AnnotatorRecordEntity annotatorRecord){
		annotatorRecordService.save(annotatorRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AnnotatorRecordEntity annotatorRecord){
		annotatorRecordService.updateById(annotatorRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] recordIds){
		annotatorRecordService.removeByIds(Arrays.asList(recordIds));

        return R.ok();
    }

}
