package cjlu.skyline.ecms_data_annotator.api.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cjlu.skyline.ecms_data_annotator.api.entity.ProjectEntity;
import cjlu.skyline.ecms_data_annotator.api.service.ProjectService;
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
@RequestMapping("annotator/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = projectService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{projectId}")
    public R info(@PathVariable("projectId") Long projectId){
		ProjectEntity project = projectService.getById(projectId);

        return R.ok().put("project", project);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ProjectEntity project){
		projectService.save(project);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ProjectEntity project){
		projectService.updateById(project);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] projectIds){
		projectService.removeByIds(Arrays.asList(projectIds));

        return R.ok();
    }

}
