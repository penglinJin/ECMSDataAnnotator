package cjlu.skyline.ecms_data_annotator.api.controller;

import java.util.Arrays;
import java.util.Map;

import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cjlu.skyline.ecms_data_annotator.api.entity.SysUserEntity;
import cjlu.skyline.ecms_data_annotator.api.service.SysUserService;



/**
 * 系统用户
 *
 * @author jinpenglin
 * @email nsp4289@autuni.ac.nz
 * @date 2021-03-09 23:46:56
 */
@Api("Manage System Users")
@RestController
@RequestMapping("api/sysuser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 列表
     */
    @ApiOperation("Get all User list")
    @ApiImplicitParam(name = "params", value = "All Users")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation("Get User information by ID")
    @ApiImplicitParam(name = "userId", value = "User's ID")
    @GetMapping("/info/{userId}")
    public R info(@PathVariable("userId") Long userId){
		SysUserEntity sysUser = sysUserService.getById(userId);

        return R.ok().put("sysUser", sysUser);
    }

    /**
     * 保存
     */
    @ApiOperation("Save User information ")
    @ApiImplicitParam(name = "sysUser", value = "User information")
    @PostMapping("/save")
    public R save(@RequestBody SysUserEntity sysUser){
		sysUserService.save(sysUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation("Update User information ")
    @ApiImplicitParam(name = "sysUser", value = "New User information")
    @PostMapping("/update")
    public R update(@RequestBody SysUserEntity sysUser){
		sysUserService.updateById(sysUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("Delete User information ")
    @ApiImplicitParam(name = "userIds", value = "Users ID")
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] userIds){
		sysUserService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
