package cjlu.skyline.ecms_data_annotator.api.controller;

import java.util.Arrays;
import java.util.Map;

import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("Get document's annotations list")
    @ApiImplicitParam(name = "params", value = "documents' annotations")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = docLabelService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation("Get document annotations by ID")
    @ApiImplicitParam(name = "docLabelId", value = "document annotation's ID")
    @GetMapping("/info/{docLabelId}")
    public R info(@PathVariable("docLabelId") Long docLabelId){
		DocLabelEntity docLabel = docLabelService.getById(docLabelId);

        return R.ok().put("docLabel", docLabel);
    }

    /**
     * 保存
     */
    @ApiOperation("Save new document labels")
    @ApiImplicitParam(name = "docLabel",value = "document label")
    @PostMapping("/save")
    public R save(@RequestBody DocLabelEntity docLabel){
		docLabelService.save(docLabel);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation("Update document labels")
    @ApiImplicitParam(name = "docLabel",value = "document label")
    @PostMapping("/update")
    public R update(@RequestBody DocLabelEntity docLabel){
		docLabelService.updateById(docLabel);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("Delete document labels")
    @ApiImplicitParam(name = "docLabelIds",value = "document label IDs")
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] docLabelIds){
		docLabelService.removeByIds(Arrays.asList(docLabelIds));

        return R.ok();
    }

}
