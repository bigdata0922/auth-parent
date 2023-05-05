package com.qi.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qi.common.result.Result;
import com.qi.common.utils.MD5;
import com.qi.model.system.SysUser;
import com.qi.model.vo.SysUserQueryVo;
import com.qi.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author qi
 * @since 2023-05-04
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 条件分页查询
     *
     * @param page
     * @param limit
     * @param userQueryVo
     * @return
     */
    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页显示记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "userQueryVo", value = "查询对象", required = false)
                    SysUserQueryVo userQueryVo
    ) {

        Page<SysUser> pageParam = new Page<>(page, limit);
        IPage<SysUser> pageModel = sysUserService.selectPage(pageParam, userQueryVo);
        return Result.ok(pageModel);
    }

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取用户")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable Long id) {
        SysUser user = sysUserService.getById(id);
        return Result.ok(user);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "保存用户")
    @PostMapping("/save")
    public Result save(@RequestBody SysUser user) {
        //对密码进行加密
        String userPassword = MD5.encrypt(user.getPassword());
        user.setPassword(userPassword);


        boolean is_Success = sysUserService.save(user);
        if (is_Success){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "更新用户")
    @PutMapping("/update")
    public Result updateById(@RequestBody SysUser user) {
        sysUserService.updateById(user);
        return Result.ok();
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @ApiOperation(value = "删除用户")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Long id) {
        sysUserService.removeById(id);
        return Result.ok();
    }
}

