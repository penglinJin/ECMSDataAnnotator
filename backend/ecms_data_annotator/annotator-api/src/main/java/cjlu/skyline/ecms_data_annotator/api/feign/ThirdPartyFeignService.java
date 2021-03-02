package cjlu.skyline.ecms_data_annotator.api.feign;

import cjlu.skyline.ecms_data_annotator.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author jinpenglin
 * @version 1.0
 * @description
 * @date 2021/3/2
 */
@FeignClient("annotator-third-party")
public interface ThirdPartyFeignService {
    /**
     * download oss file to local directory
     * @author 金鹏霖
     * @date 2021/3/2
     * @param objectName
     * @return cjlu.skyline.ecms_data_annotator.common.utils.R
     */
    @PostMapping("/oss/download")
    R download(String objectName);
}
