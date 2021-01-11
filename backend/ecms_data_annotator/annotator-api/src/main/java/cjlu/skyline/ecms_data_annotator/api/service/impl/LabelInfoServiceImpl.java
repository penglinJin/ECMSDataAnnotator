package cjlu.skyline.ecms_data_annotator.api.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.Query;

import cjlu.skyline.ecms_data_annotator.api.dao.LabelInfoDao;
import cjlu.skyline.ecms_data_annotator.api.entity.LabelInfoEntity;
import cjlu.skyline.ecms_data_annotator.api.service.LabelInfoService;


@Service("labelInfoService")
public class LabelInfoServiceImpl extends ServiceImpl<LabelInfoDao, LabelInfoEntity> implements LabelInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LabelInfoEntity> page = this.page(
                new Query<LabelInfoEntity>().getPage(params),
                new QueryWrapper<LabelInfoEntity>()
        );

        return new PageUtils(page);
    }

}