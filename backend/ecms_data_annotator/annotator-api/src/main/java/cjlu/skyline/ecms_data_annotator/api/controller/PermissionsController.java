package cjlu.skyline.ecms_data_annotator.api.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cjlu.skyline.ecms_data_annotator.api.entity.PermissionsEntity;
import cjlu.skyline.ecms_data_annotator.api.service.PermissionsService;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.R;



/**
 * 
 *
 * @author jinpenglin
 * @email nsp4289@autuni.ac.nz
 * @date 2021-01-11 15:35:16
 */
@RestController
@RequestMapping("api/permissions")
public class PermissionsController {
    @Autowired
    private PermissionsService permissionsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = permissionsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{permissionsId}")
    public R info(@PathVariable("permissionsId") Long permissionsId){
		PermissionsEntity permissions = permissionsService.getById(permissionsId);

        return R.ok().put("permissions", permissions);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PermissionsEntity permissions){
		permissionsService.save(permissions);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PermissionsEntity permissions){
		permissionsService.updateById(permissions);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] permissionsIds){
		permissionsService.removeByIds(Arrays.asList(permissionsIds));

        return R.ok();
    }

}
