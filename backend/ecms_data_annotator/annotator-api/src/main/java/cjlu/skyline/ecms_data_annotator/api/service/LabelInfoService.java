package cjlu.skyline.ecms_data_annotator.api.service;

import cjlu.skyline.ecms_data_annotator.common.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.api.entity.LabelInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author jinpenglin
 * @email nsp4289@autuni.ac.nz
 * @date 2021-01-11 15:35:15
 */
public interface LabelInfoService extends IService<LabelInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    R deleteBatch(Long[] labelIds);

    List<Long> getOldLabels(Long docId);
    LabelInfoEntity getOneByName(String name);
}

