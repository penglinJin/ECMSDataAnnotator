package cjlu.skyline.ecms_data_annotator.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api("Manage Labels information")

@RestController
@RequestMapping("annotator/labelinfo")
public class LabelInfoController {
    @Autowired
    private LabelInfoService labelInfoService;


    /**
     * get the old labels of this doc
     * @author
     * @date 2021/3/9
     * @param docId
     * @return cjlu.skyline.ecms_data_annotator.common.utils.R
     */
    @ApiOperation("Get the old labels of this doc")
    @ApiImplicitParam(name = "docId", value = "document ID")
    @GetMapping("/oldLabels")
    public R getOldLabels(@RequestParam("docId") Long docId){
        List<Long> oldLabels = labelInfoService.getOldLabels(docId);
        return R.ok().put("oldLabels",oldLabels);
    }

    /**
     * 列表
     */
    @ApiOperation("Get Labels information list")
    @ApiImplicitParam(name = "params", value = "Labels information list")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = labelInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation("Get label information by label ID")
    @GetMapping("/info/{labelId}")
    public R info(@PathVariable("labelId") Long labelId){
		LabelInfoEntity labelInfo = labelInfoService.getById(labelId);

        return R.ok().put("labelInfo", labelInfo);
    }

    /**
     * 保存
     */
    @ApiOperation("Save label information")
    @ApiImplicitParam(name = "labelInfo", value = "label information")
    @PostMapping("/save")
    public R save(@RequestBody LabelInfoEntity labelInfo){
		labelInfoService.save(labelInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation("Update label information")
    @ApiImplicitParam(name = "labelInfo", value = "label information")
    @PostMapping("/update")
    public R update(@RequestBody LabelInfoEntity labelInfo){
		labelInfoService.updateById(labelInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("Delete label information")
    @ApiImplicitParam(name = "labelIds", value = "label IDs")
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] labelIds){
		labelInfoService.removeByIds(Arrays.asList(labelIds));

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("Delete labels information")
    @ApiImplicitParam(name = "labelIds", value = "label IDs")
    @PostMapping("/deleteBatch")
    public R deleteBatch(@RequestBody Long[] labelIds){
        labelInfoService.removeByIds(Arrays.asList(labelIds));

        return R.ok();
    }

}
