package cjlu.skyline.ecms_data_annotator.api.service.impl;

import cjlu.skyline.ecms_data_annotator.api.dao.DocDao;
import cjlu.skyline.ecms_data_annotator.api.entity.DocEntity;
import cjlu.skyline.ecms_data_annotator.api.feign.ThirdPartyFeignService;
import cjlu.skyline.ecms_data_annotator.api.utils.ApiUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.Query;

import cjlu.skyline.ecms_data_annotator.api.dao.SrcDocDao;
import cjlu.skyline.ecms_data_annotator.api.entity.SrcDocEntity;
import cjlu.skyline.ecms_data_annotator.api.service.SrcDocService;
import org.springframework.util.StringUtils;


@Service("srcDocService")
public class SrcDocServiceImpl extends ServiceImpl<SrcDocDao, SrcDocEntity> implements SrcDocService {

    @Autowired
    ThirdPartyFeignService thirdPartyFeignService;

    private static String JSON="json";

    private static String TXT="txt";

    private static String PNG="png";

    private static String JPG="jpg";


    @Value("${stock.dir}")
    private String stockDir;

    @Autowired
    SrcDocDao srcDocDao;

    @Autowired
    DocDao docDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SrcDocEntity> page = this.page(
                new Query<SrcDocEntity>().getPage(params),
                new QueryWrapper<SrcDocEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public R processDataset(String filePath,Long userId) {

        //insert srcDoc into database
        SrcDocEntity srcDocEntity=new SrcDocEntity();
        Long srcDocId=ApiUtils.getUniqId();
        srcDocEntity.setSrcDocId(srcDocId);
        srcDocEntity.setSrcDocPath(filePath);
        int i=filePath.indexOf("_");
        String fileName=filePath.substring(i+1);
        srcDocEntity.setSrcDocName(fileName);

        int j=fileName.indexOf(".");
        String fileType=fileName.substring(j+1);
        if (!StringUtils.isEmpty(fileType)){
            if (fileType.equals(JSON)||fileType.equals(TXT)){
                srcDocEntity.setDocType(0);
            }else if (fileType.equals(PNG)||fileType.equals(JPG)){
                srcDocEntity.setDocType(1);
            }
        }
        srcDocEntity.setCreateUserId(userId);
        srcDocEntity.setCreateTime(new Date());
        srcDocDao.insert(srcDocEntity);


        //if file is txt
        URL url = null;

        try {
            url = new URL(filePath);

            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String current;
            while ((current = in.readLine()) != null) {
                DocEntity docEntity=new DocEntity();
                docEntity.setSrcDocId(srcDocId);
                docEntity.setDocType(0);
                docEntity.setCreateUserId(userId);
                docEntity.setCreateTime(new Date());
                docEntity.setDocContent(current);
                docDao.insert(docEntity);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return R.ok();
    }

}