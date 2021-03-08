package cjlu.skyline.ecms_data_annotator.api.controller;

import java.util.Arrays;
import java.util.Map;

import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.R;
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
@RestController
@RequestMapping("annotator/doc")
public class DocController {
    @Autowired
    private DocService docService;

    @PostMapping
    public R approve(@RequestParam("docId") Long docId,@RequestParam("userId") Long userId){
        return docService.approve(docId,userId);
    }

    @GetMapping("/approvalList")
    public R approvalList(@RequestParam Map<String, Object> params){
        PageUtils page = docService.queryApprovalPage(params);
        return R.ok().put("page", page);
    }

    @GetMapping("/preList")
    public R preList(@RequestParam Map<String, Object> params){
        PageUtils page = docService.queryPrePage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = docService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{docId}")
    public R info(@PathVariable("docId") Long docId){
		DocEntity doc = docService.getById(docId);

        return R.ok().put("doc", doc);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DocEntity doc){
		docService.save(doc);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DocEntity doc){
		docService.updateById(doc);

        return R.ok();
    }

    /**
     * 删除
     */
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
