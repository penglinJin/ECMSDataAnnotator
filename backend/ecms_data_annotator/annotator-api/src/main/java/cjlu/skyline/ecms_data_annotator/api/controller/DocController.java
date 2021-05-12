package cjlu.skyline.ecms_data_annotator.api.controller;

import java.util.Arrays;
import java.util.Map;

import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cjlu.skyline.ecms_data_annotator.api.entity.DocEntity;
import cjlu.skyline.ecms_data_annotator.api.service.DocService;




/**
 * 
 *
 * @author jinpenglin
 * @email nsp4289@autuni.ac.nz
 * @date 2021-01-11 15:35:16
 */
@Api("Manage documents annotation actions")

@RestController
@RequestMapping("annotator/doc")
public class DocController {
    @Autowired
    private DocService docService;


    @ApiOperation("Reject single annotation")
    @PostMapping("reject")
    public R reject(@RequestParam("annotateRecordId") Long annotateRecordId, @RequestParam("userId") Long userId){
        return docService.reject(annotateRecordId,userId);
    }

    @ApiOperation("Reject multiple annotations")
    @PostMapping("rejectBatch")
    public R rejectBatch(@RequestBody Long[] annotateRecordIds,@RequestParam("userId") Long userId){
        if (annotateRecordIds!=null){
            Arrays.asList(annotateRecordIds).forEach(annotateRecordId->{
                docService.reject(annotateRecordId,userId);
            });
            return R.ok();
        }else {
            return R.error("empty selection");
        }
    }

    @ApiOperation("Approve multiple annotations")
    @PostMapping("/approveBatch")
    public R approveBatch(@RequestBody Long[] annotateRecordIds,@RequestParam("userId") Long userId){
        if (annotateRecordIds!=null){
            Arrays.asList(annotateRecordIds).forEach(annotateRecordId->{
                docService.approve(annotateRecordId,userId);
            });
            return R.ok();
        }else {
            return R.error("empty selection");
        }
    }

    @ApiOperation("Approve single annotation")
    @PostMapping("/approve")
    public R approve(@RequestParam("annotateRecordId") Long annotateRecordId,@RequestParam("userId") Long userId){
        return docService.approve(annotateRecordId,userId);
    }

    @ApiOperation("Get documents  needed to be approved")
    @ApiImplicitParam(name = "params", value = "documents  needed to be approved")
    @GetMapping("/approvalList")
    public R approvalList(@RequestParam Map<String, Object> params){
        PageUtils page = docService.queryApprovalPage(params);
        return R.ok().put("page", page);
    }

    @ApiOperation("")
    @GetMapping("/preList")
    public R preList(@RequestParam Map<String, Object> params){
        PageUtils page = docService.queryPrePage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @ApiOperation("Get All documents")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = docService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation("Get a document information by ID")
    @ApiImplicitParam(name = "docId",value = "Document ID")
    @GetMapping("/info/{docId}")
    public R info(@PathVariable("docId") Long docId){
		DocEntity doc = docService.getById(docId);

        return R.ok().put("doc", doc);
    }

    /**
     * 保存
     */
    @ApiOperation("Save Document")
    @ApiImplicitParam(name = "doc",value = "Document")
    @PostMapping("/save")
    public R save(@RequestBody DocEntity doc){
		docService.save(doc);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("Update document")
    @ApiImplicitParam(name = "doc",value = "Document")

    public R update(@RequestBody DocEntity doc){
		docService.updateById(doc);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("Delete documents")
    @ApiImplicitParam(name = "docIds", value = "Documents IDs")
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] docIds){
		docService.removeByIds(Arrays.asList(docIds));

        return R.ok();
    }

    /**
     * BatchDetele
     */
    @PostMapping("/deleteBatch")
    public R deleteBatch(@RequestBody Long[] docIds){
        docService.deleteBatch(docIds);

        return R.ok();
    }

}
