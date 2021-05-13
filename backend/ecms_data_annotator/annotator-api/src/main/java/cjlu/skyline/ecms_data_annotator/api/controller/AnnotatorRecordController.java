package cjlu.skyline.ecms_data_annotator.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cjlu.skyline.ecms_data_annotator.api.vo.StaticsVo;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.R;
import io.swagger.annotations.*;
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
@Api("Manage statistics of annotation records")

@RestController
@RequestMapping("annotator/annotatorrecord")
public class AnnotatorRecordController {
    @Autowired
    private AnnotatorRecordService annotatorRecordService;

    @ApiOperation("Get those annotations needed to be approved")
    @ApiImplicitParam(name = "params", value = "annotations to be approved")
    @GetMapping("/approvalList")
    public R approvalList(@RequestParam Map<String, Object> params){
        PageUtils page = annotatorRecordService.queryApprovalPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @ApiOperation("Get  annotation record list")
    @ApiImplicitParam(name = "params", value = "annotation record list")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = annotatorRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation("Get annotation information record by ID")
    @ApiImplicitParam(name = "recordId", value = " annotation record ID",dataType = "Long")
    @GetMapping("/info/{recordId}")
    public R info(@PathVariable("recordId") Long recordId){
		AnnotatorRecordEntity annotatorRecord = annotatorRecordService.getById(recordId);

        return R.ok().put("annotatorRecord", annotatorRecord);
    }

    /**
     * 保存
     */
    @ApiOperation("save annotation record")
    @ApiImplicitParam(name = "annotatorRecord",value = "annotation record", dataType = "AnnotatorRecordEntity")
    @PostMapping("/save")
    public R save(@RequestBody AnnotatorRecordEntity annotatorRecord){
		annotatorRecordService.save(annotatorRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation("Update annotation record")
    @ApiImplicitParam(name = "annotatorRecord",value = "annotation record update", dataType = "AnnotatorRecordEntity")
    @PostMapping("/update")
    public R update(@RequestBody AnnotatorRecordEntity annotatorRecord){
		annotatorRecordService.updateById(annotatorRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("Delete annotation record")
    @ApiImplicitParam(name = "recordIds",value = "annotation record IDs", dataType = "Long[]")
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] recordIds){
		annotatorRecordService.removeByIds(Arrays.asList(recordIds));

        return R.ok();
    }

    /**
     * 统计标签完成情况
     * @author
     * @date 2021/5/4
     * @param
     * @return cjlu.skyline.ecms_data_annotator.common.utils.R
     */
    @ApiOperation("Get the annotation completion situation for all docs")
    @GetMapping("/completeSituation")
    public R completeSituation(){
        List<StaticsVo> statics=annotatorRecordService.getCompleteSituation();
        return R.ok().put("completeSituation",statics);
    }

    /**
     *  注释数量统计
     */
    @ApiOperation("Get count statics for each user's annotation action")
    @GetMapping("/numStatics")
    public R annotationNum(){
        List<StaticsVo> statics = annotatorRecordService.getStatics();

        return R.ok().put("numStatics",statics);
    }


    /**
     *  被注释标签数量统计
     */
    @ApiOperation("Get count statics for annotated labels ")
    @GetMapping("/labelStatics")
    public R labelStatics(){
        List<StaticsVo> labelStatics = annotatorRecordService.getTagsStatics();

        return R.ok().put("labelStatics",labelStatics);
    }
}