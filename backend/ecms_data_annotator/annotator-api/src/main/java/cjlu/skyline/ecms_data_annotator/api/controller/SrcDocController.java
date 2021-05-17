package cjlu.skyline.ecms_data_annotator.api.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import cjlu.skyline.ecms_data_annotator.api.entity.SrcDocEntity;
import cjlu.skyline.ecms_data_annotator.api.service.SrcDocService;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.R;


/**
 * @author jinpenglin
 * @email nsp4289@autuni.ac.nz
 * @date 2021-01-11 15:35:16
 */
@Log
@Api("Manage Source documents")
@RestController
@RequestMapping("annotator/srcdoc")
public class SrcDocController {
    @Autowired
    private SrcDocService srcDocService;

//    @Value("${tmp.location}")
//    private String tmpLocation;


    @ApiOperation("Export CSV")
    @GetMapping("/downloadCSV")
    public String downloadCSV(){
        return srcDocService.downloadCSV();
    }

    @ApiOperation("Export annotated files")
    @GetMapping("/downloadFile")
    public JSONArray downloadFile() {
        return srcDocService.downloadFile();
    }


    @PostMapping("test")
    public R test() {
        return R.ok("ok");
    }

    @PostMapping("annotate")
    public R annotate(@RequestBody Long[] labelIds, @RequestParam("userId") Long userId, @RequestParam("docId") Long docId,@RequestParam("htmlContent") String htmlContent) {
        if (labelIds == null || userId == null || docId == null) {
            return R.error("null error");
        }
        return srcDocService.annotate(labelIds, userId, docId,htmlContent);
    }

    @ApiOperation("Process Dataset to small slice")
    @PostMapping("/process")
    public R processDataset(@RequestParam("filePath") String filePath, @RequestParam("userId") Long userId) {
        if (StringUtils.isEmpty(filePath)) {
            return R.error("empty");
        }
        return srcDocService.processDataset(filePath, userId);
    }

    /**
     * 列表
     */
    @ApiOperation("Get Source Documents list")
    @ApiImplicitParam(name = "params", value = "source documents")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = srcDocService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation("Get Source Document information by ID")
    @ApiImplicitParam(name = "srcDocId", value = "source document ID")
    @GetMapping("/info/{srcDocId}")
    public R info(@PathVariable("srcDocId") Long srcDocId) {
        SrcDocEntity srcDoc = srcDocService.getById(srcDocId);

        return R.ok().put("srcDoc", srcDoc);
    }

    /**
     * 保存
     */
    @ApiOperation("Save Source Document information ")
    @ApiImplicitParam(name = "srcDoc", value = "New source document ")
    @PostMapping("/save")
    public R save(@RequestBody SrcDocEntity srcDoc) {
        srcDocService.save(srcDoc);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation("Update Source Document information ")
    @ApiImplicitParam(name = "srcDoc", value = "updated source document ")
    @PostMapping("/update")
    public R update(@RequestBody SrcDocEntity srcDoc) {
        srcDocService.updateById(srcDoc);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("Delete Source Document information ")
    @ApiImplicitParam(name = "srcDocIds", value = "source documents ID")
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] srcDocIds) {
        srcDocService.removeByIds(Arrays.asList(srcDocIds));

        return R.ok();
    }

}
