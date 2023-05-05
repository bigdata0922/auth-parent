package com.qi.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qi.common.result.Result;
import com.qi.model.system.SysRole;
import com.qi.model.vo.AssginRoleVo;
import com.qi.model.vo.SysRoleQueryVo;
import com.qi.system.annotation.Log;
import com.qi.system.enums.BusinessType;
import com.qi.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author admin
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 1.查询所有记录
     *
     * @return
     */
    @ApiOperation(value = "获取全部角色列表")
    @GetMapping("/findAll")
    public Result findAll() {
        List<SysRole> roleList = sysRoleService.list();
        return Result.ok(roleList);
    }


    /**
     * 条件分页查询
     *
     * @return
     */
    //@PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(@ApiParam(name = "page", value = "当前页码", required = true)
                        @PathVariable Long page,
                        @ApiParam(name = "limit", value = "每页记录数", required = true)
                        @PathVariable Long limit,
                        @ApiParam(name = "roleQueryvo", value = "查询对象", required = false)
                                SysRoleQueryVo sysRoleQueryVo) {
        Page<SysRole> pageParam = new Page<>(page, limit);
        IPage<SysRole> pageModel1 = sysRoleService.selectPage(pageParam, sysRoleQueryVo);
        return Result.ok(pageModel1);

    }

    /**
     * 添加角色
     *
     * @param sysRole
     * @return
     * @RequestBody 不能使用get提交方式
     * 传递json格式数据，把json格式数据封装到对象里面
     */
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
   //@PreAuthorize("hasAuthority('bnt.sysRole.add')")
    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result saveRole(@RequestBody SysRole sysRole) {
        boolean isSucceed = sysRoleService.save(sysRole);
        if (isSucceed) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    /**
     * 根据id进行查询角色
     *
     * @param id
     * @return
     */
    //@PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation(value = "获取角色")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable Long id) {
        SysRole role = sysRoleService.getById(id);
        return Result.ok(role);
    }

    /**
     * 修改角色
     *
     * @param sysRole
     * @return
     */
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    //@PreAuthorize("hasAuthority('bnt.sysRole.update')")
    @ApiOperation(value = "修改角色")
    @PutMapping("/update")
    public Result updateById(@RequestBody SysRole sysRole) {
        sysRoleService.updateById(sysRole);
        return Result.ok();
    }


    /**
     * 405错误：HttpRequestMethodNorSupportedException:Request method 'GET' not supported
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    //@PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @DeleteMapping("/remove/{id}")
    @ApiOperation(value = "根据id进行删除")
    public Result removeRole(@PathVariable Long id) {
        boolean isSucceed = sysRoleService.removeById(id);
        if (isSucceed == true) {
            return Result.ok();
        } else {
            return Result.fail();
        }

    }


    /**
     * 根据id列表做批量删除
     *
     * @param idList
     * @return
     */
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        sysRoleService.removeByIds(idList);
        return Result.ok();

    }

    //TODO 为用户分配角色
    /**
     * 接口分析：
     *   1）进入分配页面：获取已分配角色与全部角色，进行页面展示
     *   2）保存分配角色：删除之前分配的角色和保存现在分配的角色
     * */
    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable Long userId) {
        Map<String, Object> roleMap = sysRoleService.getRolesByUserId(userId);
        return Result.ok(roleMap);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }


}
