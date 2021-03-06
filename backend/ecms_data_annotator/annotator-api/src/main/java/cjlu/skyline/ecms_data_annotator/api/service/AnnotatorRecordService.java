package cjlu.skyline.ecms_data_annotator.api.service;

import cjlu.skyline.ecms_data_annotator.api.vo.StaticsVo;
import com.baomidou.mybatisplus.extension.service.IService;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.api.entity.AnnotatorRecordEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author jinpenglin
 * @email nsp4289@autuni.ac.nz
 * @date 2021-01-11 15:35:15
 */
public interface AnnotatorRecordService extends IService<AnnotatorRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryApprovalPage(Map<String, Object> params);

    AnnotatorRecordEntity getLabelApproveRecord(Long labelId);

    List<StaticsVo> getStatics();

    List<StaticsVo> getTagsStatics();

    List<StaticsVo> getCompleteSituation();


}

