package cjlu.skyline.ecms_data_annotator.api.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

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
@RestController
@RequestMapping("annotator/srcdoc")
public class SrcDocController {
    @Autowired
    private SrcDocService srcDocService;

    @Value("${tmp.location}")
    private String tmpLocation;

    /**
     *
     * @param
     * @return cjlu.skyline.ecms_data_annotator.common.utils.R
     * @author 金鹏霖
     * @date 2021/4/8
     */
    @GetMapping("/exportFile")
    public R exportFile() {
        return R.ok();
    }

    @GetMapping("/downloadFile")
    public ResponseEntity<FileSystemResource> downloadFile() {
        ResponseEntity<FileSystemResource> downloadFile = null;
        downloadFile = srcDocService.downloadFile(tmpLocation);

        return downloadFile;
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
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = srcDocService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{srcDocId}")
    public R info(@PathVariable("srcDocId") Long srcDocId) {
        SrcDocEntity srcDoc = srcDocService.getById(srcDocId);

        return R.ok().put("srcDoc", srcDoc);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SrcDocEntity srcDoc) {
        srcDocService.save(srcDoc);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SrcDocEntity srcDoc) {
        srcDocService.updateById(srcDoc);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] srcDocIds) {
        srcDocService.removeByIds(Arrays.asList(srcDocIds));

        return R.ok();
    }

}
