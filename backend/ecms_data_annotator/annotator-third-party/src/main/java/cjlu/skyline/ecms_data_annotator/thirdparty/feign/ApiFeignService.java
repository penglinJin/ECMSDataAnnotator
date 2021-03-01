package cjlu.skyline.ecms_data_annotator.thirdparty.feign;

import cjlu.skyline.ecms_data_annotator.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

/**
 * @author jinpenglin
 * @version 1.0
 * @description feign interface for api service,
 * aimming to add dataset processing process while uploading dataset
 * @date 2021/3/1
 */

@FeignClient("annotator-api")
public interface ApiFeignService {

    /**
     * @description process file into docs if the format fits, otherwise return error
     * @author jinpenglin
     * @date 2021/3/1
     * @param file
     * @return cjlu.skyline.ecms_data_annotator.common.utils.R
     */
    @PostMapping("/api/srcdoc/process")
    R processDataset(@RequestParam("file") File file);
}
