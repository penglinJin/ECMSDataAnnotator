package cjlu.skyline.ecms_data_annotator.api.service.impl;

import cjlu.skyline.ecms_data_annotator.api.dao.DocStateDao;
import cjlu.skyline.ecms_data_annotator.api.entity.DocStateEntity;
import cjlu.skyline.ecms_data_annotator.api.service.DocStateService;
import cjlu.skyline.ecms_data_annotator.api.utils.ApiUtils;
import cjlu.skyline.ecms_data_annotator.api.vo.DocVo;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.Query;
import cjlu.skyline.ecms_data_annotator.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import cjlu.skyline.ecms_data_annotator.api.dao.DocDao;
import cjlu.skyline.ecms_data_annotator.api.entity.DocEntity;
import cjlu.skyline.ecms_data_annotator.api.service.DocService;


@Service("docService")
public class DocServiceImpl extends ServiceImpl<DocDao, DocEntity> implements DocService {

    @Autowired
    DocDao docDao;

    @Autowired
    DocStateDao docStateDao;

    @Autowired
    DocStateService docStateService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DocEntity> page = this.page(
                new Query<DocEntity>().getPage(params),
                new QueryWrapper<DocEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public R deleteBatch(Long[] docIds) {
        Arrays.stream(docIds).forEach(id->{
            QueryWrapper<DocEntity> docQueryWrapper=new QueryWrapper<>();
            docQueryWrapper.eq("doc_id",id);
            docDao.delete(docQueryWrapper);

            QueryWrapper<DocStateEntity> docStateQueryWrapper=new QueryWrapper<>();
            docStateQueryWrapper.eq("doc_id",id);
            docStateDao.delete(docStateQueryWrapper);


        });
        return R.ok();
    }

    @Override
    public PageUtils queryPrePage(Map<String, Object> params) {

        List<DocStateEntity> docStatList = docStateService.list(new QueryWrapper<DocStateEntity>().eq("doc_stat", 0));

        List<Long> docIdList = new ArrayList<>();
        docStatList.forEach(item->{
            docIdList.add(item.getDocId());
        });

        IPage<DocEntity> page = this.page(
                new Query<DocEntity>().getPage(params),
                new QueryWrapper<DocEntity>().in("doc_id",docIdList)
        );

        IPage<DocVo> convert = page.convert(DocEntity -> ApiUtils.copyProperties(DocEntity, DocVo.class));

        List<DocVo> docVos=new ArrayList<>();
        convert.getRecords().forEach(i->{
            i.setDocState("unannotated");
            docVos.add(i);
        });

        convert.setRecords(docVos);



        return new PageUtils(convert);
    }

}