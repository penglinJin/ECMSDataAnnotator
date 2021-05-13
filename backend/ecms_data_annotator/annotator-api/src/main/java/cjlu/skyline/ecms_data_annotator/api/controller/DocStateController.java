package cjlu.skyline.ecms_data_annotator.api.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api("Manage documents information")

@RestController
@RequestMapping("annotator/docstate")
public class DocStateController {
    @Autowired
    private DocStateService docStateService;

    /**
     * 列表
     */
    @ApiOperation("Get document's states list")
    @ApiImplicitParam(name = "params", value = "documents' states")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = docStateService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation("Get document state by ID")
    @ApiImplicitParam(name = "docStatId", value = "document state ID")
    @GetMapping("/info/{docStatId}")
    public R info(@PathVariable("docStatId") Long docStatId){
		DocStateEntity docState = docStateService.getById(docStatId);

        return R.ok().put("docState", docState);
    }

    /**
     * 保存
     */
    @ApiOperation("Save new document state")
    @ApiImplicitParam(name = "docLabel",value = "document state,like approved ,rejected,wait for annotating")
    @PostMapping("/save")
    public R save(@RequestBody DocStateEntity docState){
		docStateService.save(docState);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation("Update document state")
    @ApiImplicitParam(name = "docState",value = "document state")
    @PostMapping("/update")
    public R update(@RequestBody DocStateEntity docState){
		docStateService.updateById(docState);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("Delete document states")
    @ApiImplicitParam(name = "docLabelIds",value = "document states IDs")
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] docStatIds){
		docStateService.removeByIds(Arrays.asList(docStatIds));

        return R.ok();
    }

}
