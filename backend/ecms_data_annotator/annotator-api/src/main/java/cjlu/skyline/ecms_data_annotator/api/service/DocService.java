package cjlu.skyline.ecms_data_annotator.api.service;

import cjlu.skyline.ecms_data_annotator.common.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.api.entity.DocEntity;

import java.util.Map;

/**
 * 
 *
 * @author jinpenglin
 * @email nsp4289@autuni.ac.nz
 * @date 2021-01-11 15:35:16
 */
public interface DocService extends IService<DocEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R deleteBatch(Long[] docIds);

    PageUtils queryPrePage(Map<String, Object> params);

    PageUtils queryApprovalPage(Map<String, Object> params);

    R approve(Long annotateRecordId,Long userId);

    R reject(Long annotateRecordId, Long userId);
}

